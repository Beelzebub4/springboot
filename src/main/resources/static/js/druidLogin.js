/**
 * <br>Created by 吴海南 on 2017/5/17.
 * <br>星期三 at 23:45.
 */

$('#loginBtn').click(function () {
    $.ajax({
        type: 'POST',
        url: "druid/submitLogin",
        data: $("#loginForm").serialize(),
        success: function (data) {
            if ("success" === data)
                window.open("druid/index.html");
            else {
                $("#alertInfo").show();
                $("#loginForm")[0].reset();
                $("input[name=loginUsername]").focus();
            }
        },
        dataType: "text"
    });
});