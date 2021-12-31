var SettingHandler = function () {
    this.avatar = $("#avatar-img").attr("src")
}

SettingHandler.prototype.listenAvatarUploadEvent = function () {
    var self = this
    $("#avatar-input").on("change", function () {
        var image = this.files[0]
        var formData = new FormData()
        formData.append("image", image)
        $.ajax({
            method: 'post',
            url: '/avatar/upload',
            data: formData,
            // 如果使用jQuery上传文件，那么还需要指定以下两个参数
            processData: false,
            contentType: false,
            success: function (res) {
                if (res.code === 200) {
                    // result = {"code": 200, "data": {"avatar": "/xxx"}}
                    var avatar = res.data.avatar
                    self.avatar = avatar
                    $("#avatar-img").attr("src", avatar)
                } else {
                    sweetAlert.alertError(res.message)
                }
            }
        })
    });
}

SettingHandler.prototype.listenSubmitEvent = function () {
    var self = this
    $("#submit-btn").on("click", function (event) {
        event.preventDefault()
        var signature = $("#signagure-input").val()
        var arr = self.avatar.split('/')
        avatar = arr[arr.length - 1]
        $.ajax({
            method: 'post',
            url: "/profile/edit",
            data: {avatar, signature},
            success: function (res) {
                if (res.code === 200) {
                    sweetAlert.alertSuccessToast("修改成功")
                    setTimeout(function () {
                        location.reload()
                    }, 1200)
                } else {
                    sweetAlert.alertError(res.message)
                }
            }
        })
    });
}

SettingHandler.prototype.run = function () {
    this.listenAvatarUploadEvent();
    this.listenSubmitEvent();
}

$(function () {
    var handler = new SettingHandler();
    handler.run();
})