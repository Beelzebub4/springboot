/**
 * <br>Created by 吴海南 on 2017/5/8.
 * <br>星期一 at 16:32.
 */
//ajax替换页面
function reHtml(url, name) {
    $.ajax({
        url: url,
        dataType: 'html',
        success: function (data) {
            $('#page').html(data);
            $('#navTitle').html(name);
            $(".treeview-menu li").removeClass("active");
            $('#'+url).parents('li').addClass("active");
        }
    });
}
//更改密码
$('#updatePassword').click(function () {
    layer.open({
        skin: 'layui-layer-molv',
        title: "修改密码",
        area: ['500px', '270px'],
        content: $('#passwordLayer'),
        btn: ['修改', '取消'],
        btn1: function (index) {
            $.ajax({
                type: "POST",
                url: "updatePassword",
                data: $('#editPswForm').serialize(),
                dataType: "json",
                success: function (result) {
                    if (result.code === 0) {
                        layer.close(index);
                        $.confirm({
                            type: 'green',
                            typeAnimated: true,
                            title: '确认信息',
                            content: '修改密码成功，请重新登录',
                            buttons: {
                                确定: {
                                    text: '确定',
                                    btnClass: 'btn-green',
                                    action: function () {
                                        $.ajax({
                                            url: 'doLogout',
                                            type: 'get',
                                            success: function (result) {
                                                window.location.href = result.msg;
                                            }
                                        })
                                    }
                                }
                            }
                        });
                    } else {
                        layer.msg(result.msg);
                    }
                }
            });
        }
    });
});
//注销
$('#logout').click(function () {
    $.confirm({
        type: 'red',
        typeAnimated: true,
        title: '确认信息',
        content: '确定要退出系统吗?',
        buttons: {
            确定: {
                text: '确定',
                btnClass: 'btn-red',
                action: function () {
                    $.ajax({
                        url: 'doLogout',
                        type: 'get',
                        success: function (result) {
                            window.location.href = result.msg;
                        }
                    })
                }
            },
            返回: {
                text: '返回'
            }
        }
    });
});
/**
 * 反序列化form表单
 */
function deSerForm(formData) {
    formData.split('&').forEach(function (param) {
        param = param.split('=');
        var name = param[0],
            val = param[1];
        $('[name=' + name + ']').val(val);
    })
}
function selectRow(formId, row, barring) {//表单id,行数据,排除的属性
    Object.keys(row).forEach(function (p1) {
        for (var i = 0; i < barring.length; i++) {
            if (p1 !== barring[i]) {
                $('#' + formId + ' [name=' + p1 + ']').val(row[p1]);
            }
        }
    });
}