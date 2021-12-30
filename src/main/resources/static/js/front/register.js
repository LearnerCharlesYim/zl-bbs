var RegisterHandler = function () {
    this.emailUuid = null
}

RegisterHandler.prototype.listenSendCaptchaEvent = function () {
    var self = this
    var callback = function (event) {
        var $this = $(this)
        event.preventDefault()
        var email = $('input[name="email"]').val();
        var reg = /^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/;
        if (!email || !reg.test(email)) return sweetAlert.alertError('请输入正确格式邮箱')
        $.get('/email/captcha', {email}, function (res) {
            if (res.code === 200) {
                sweetAlert.alertSuccessToast("邮箱发送成功")
                self.emailUuid = res.data.uuid
                $this.off('click')
                $this.attr('disabled', true)
                var countDown = 60
                var interval = setInterval(function () {
                    if (countDown <= 1) {
                        $this.text("发送验证码")
                        $this.attr('disabled', false)
                        $this.on('click', callback)
                        clearInterval(interval)
                        return
                    }
                    countDown--
                    $this.text(countDown)
                }, 1000)
            } else {
                sweetAlert.alertErrorToast(res.msg)
            }
        })
    }
    $('#email-captcha-btn').on('click', callback)
}

RegisterHandler.prototype.listenGraphCaptchaEvent = function () {
    $('#captcha-img').on('click', function () {
        var src = '/captcha/?kw=' + new Date().getTime()
        $(this).attr('src', src)
    })
}

RegisterHandler.prototype.listenSubmitEvent = function () {
    var self = this
    $('#submit-btn').on('click', function (event) {
        event.preventDefault()
        var email = $("input[name='email']").val();
        var emailCaptcha = $("input[name='email-captcha']").val();
        var username = $("input[name='username']").val();
        var password = $("input[name='password']").val();
        var repeatPassword = $("input[name='repeat-password']").val();
        var graphCaptcha = $("input[name='graph-captcha']").val();
        var emailUuid = self.emailUuid

        $.post('/register', {
            email,
            emailCaptcha,
            username,
            password,
            repeatPassword,
            graphCaptcha,
            emailUuid
        }, function (res) {
            if(res.code === 200){
                sweetAlert.alertSuccessToast("注册成功")
                setTimeout(function () {
                    location.href = '/login.html'
                },1200)
            }else {
                sweetAlert.alertError(res.message)
            }
        })
    })
}

RegisterHandler.prototype.run = function () {
    this.listenSendCaptchaEvent();
    this.listenGraphCaptchaEvent();
    this.listenSubmitEvent();
}

$(function () {
    var handler = new RegisterHandler();
    handler.run();
})