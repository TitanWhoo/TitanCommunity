$(function () {
    $("#btn-login").click(function (e) {
        let formData = $("#login-form").serialize()
        $.post("/api/login", formData,
            function (data, textStatus, jqXHR) {
                $('#login-tip').removeClass("bg-danger").removeClass("bg-success");
                if (textStatus === 'success' && data.status === 0) {
                    $("#login-tip").addClass("bg-success")
                        .html(data.text)
                        .show();
                    setTimeout(function () {
                        location.href = location.href
                    }, 2000)
                } else {
                    $("#login-tip").addClass("bg-danger")
                        .html(data.text)
                        .show();
                }
            }
        );
    });
    $("#btn-register").click(function (e) {
        let formData = $("#register-form").serialize()
        $.post("/api/register", formData,
            function (data, textStatus, jqXHR) {
                $('#form-tip').removeClass("bg-danger").removeClass("bg-success");
                if (textStatus === 'success' && data.status === 0) {
                    $("#form-tip").addClass("bg-success")
                        .html(data.text)
                        .show();
                    setTimeout(function () {
                        location.href = location.href
                    }, 2000)
                } else {
                    $("#form-tip").addClass("bg-danger")
                        .html(data.text)
                        .show();
                }
            }
        );
    });
    $("#logout").click(function (e) {
        e.preventDefault();
        $.get("/api/logout",
            function (data, textStatus, jqXHR) {
                if (data.status === 0) {
                    alert("注销成功!")
                    setTimeout(function () {
                        location.href = "/index";
                    }, 500)
                }
            }
        );
    });
});