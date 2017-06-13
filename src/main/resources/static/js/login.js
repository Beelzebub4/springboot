/**
 * <br>Created by 吴海南 on 2017/5/5.
 * <br>星期五 at 10:25.
 */
$(function () {
    $('.top-content').css({height: $(window).height()});
});
function refreshCode() {
    $('#captchaJpg')[0].src = "captcha.jpg?t=" + $.now();
    $('#captcha').val("");
}
document.onkeydown = function (e) {//enter提交
    var theEvent = window.event || e;
    var code = theEvent.keyCode || theEvent.which;
    if (13 === code) {
        $("#submit").click();
    }
}
$('#submit').click(function () {
    var data = "username=" + $('#username').val()
        + "&password=" + $('#password').val() + "&captcha=" + $('#captcha').val();
    $.ajax({
        type: 'POST',
        url: 'doLogin',
        data: data,
        dataType: 'json',
        success: function (result) {
            /** @namespace result.msg */
            if (result.code === 0) {
                window.location.href = result.msg;
            } else if (result.code === 500) {
                $('#errorDiv').css({display: 'block'});
                $('#errorMsg')[0].innerHTML = '<i class="fa fa-exclamation-triangle"></i>' + result.msg;
                refreshCode();
            }
        }
    })
});