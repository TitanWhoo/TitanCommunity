$(function () {
    var E = window.wangEditor;
    var editor = new E('#edit-menu', '#edit-text');
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
    editor.customConfig.zIndex = 1000;
    editor.create();

    function doPost(postData) {
        $.post("/api/post/article", postData,
            function (data, textStatus, jqXHR) {
                if (data.status === 0) {
                    alert(data.text);
                    setTimeout(function () {
                        location.href = "/";
                    }, 500)
                } else {
                    alert(data.text);
                }
            }
        );
    }

    $("#edit-post").click(function (e) {
        e.preventDefault();
        let postData = {
            title: $("#edit-title").val(),
            content: editor.txt.html()
        }
        doPost(postData);
    });
});