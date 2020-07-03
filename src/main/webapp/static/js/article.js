$(function () {
    var E = window.wangEditor;
    var editor = new E('#comment-editor-menu', '#comment-editor');
    editor.customConfig.zIndex = 1000;
    editor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'fontSize',  // 字号
        'fontName',  // 字体
        'italic',  // 斜体
        'underline',  // 下划线
        'strikeThrough',  // 删除线
        'foreColor',  // 文字颜色
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        'quote',  // 引用
        'image',  // 插入图片
        'table',  // 表格
        'video',  // 插入视频
        'code',  // 插入代码
        'undo',  // 撤销
        'redo'  // 重复
    ]
    editor.create();

    $("#postComment").click(function (e) {
        e.preventDefault();
        postData = {
            aid: parseInt($("#articleId").val()),
            content: editor.txt.html()
        }
        $.post("/api/post/comment", postData,
            function (data, textStatus, jqXHR) {
                if (data.status === 0) {
                    alert(data.text)
                    setTimeout(function () {
                        location.href = location.href;
                    }, 500)
                } else {
                    alert(data.text)
                }
            }
        );
    });
});