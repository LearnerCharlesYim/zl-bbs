var PublishPostHandler = function () {
    var editor = new window.wangEditor('#editor')
    editor.config.uploadImgServer = '/post/image/upload'
    editor.config.uploadFileName = 'image'
    editor.config.uploadImgMaxSize = 1024 * 1024 * 5
    editor.create()
    this.editor = editor
}

PublishPostHandler.prototype.listenSubmitEvent = function () {
    var self = this
    $('#submit-btn').on('click', function (event) {
        event.preventDefault()
        var title = $('input[name="title"]').val()
        var boardId = $('select[name="board-id"]').val()
        var content = self.editor.txt.html()
        $.post('/publish', {title, boardId, content}, function (res) {
            if (res.code === 200) {
                sweetAlert.alertSuccessToast("发表成功")
                setTimeout(function () {
                    location.href = '/'
                }, 1200)
            } else {
                sweetAlert.alertError(res.message)
            }
        })

    })
}

PublishPostHandler.prototype.run = function () {
    this.listenSubmitEvent()
}

$(function () {
    var handler = new PublishPostHandler()
    handler.run()
})