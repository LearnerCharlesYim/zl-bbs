var LoginHandler = function () {

}

LoginHandler.prototype.listenSubmitEvent = function () {
    $('#submit-btn').on('click', function (event) {
        event.preventDefault()
        var email = $("input[name='email']").val();
        var password = $("input[name='password']").val();
        var rememberMe = $("input[name='remember']").prop('checked') ? 1 : 0
        $.post('/login', {email, password, rememberMe}, function (res) {
            if (res.code === 200) {
                sweetAlert.alertSuccessToast("登录成功")
                setTimeout(function () {
                    location.href = '/'
                }, 1200)
            } else {
                sweetAlert.alertError(res.message)
            }
        })
    })
}


LoginHandler.prototype.run = function () {
    this.listenSubmitEvent()
}

$(function () {
    var handler = new LoginHandler()
    handler.run()
})