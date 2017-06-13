/**
 * <br>Created by 吴海南 on 2017/5/4.
 * <br>星期四 at 9:42.
 */
var table = $('#table');
table.bootstrapTable({
    url: 'userPageList',
    columns: [
        {
            checkbox: true,
            width: '3%'
        }, {
            formatter: function (value, row, index) {
                return index + 1;
            },
            width: '3%'
        }, {
            field: 'username',
            title: '用户名',
            sortable: true,
            width: '15%'
        }, {
            field: 'email',
            title: '邮箱',
            sortable: true,
            width: '20%'
        }, {
            field: 'mobile',
            title: '手机号',
            sortable: true,
            width: '16%'
        }, {
            field: 'status',
            title: '状态',
            formatter: function (value) {
                return value === 0 ?
                    "<span class='label label-danger'>禁用</span>" :
                    "<span class='label label-success'>正常</span>";
            },
            width: '6%'
        }, {
            field: 'createTime',
            title: '创建时间',
            sortable: true,
            width: '20%'
        }
    ]
});
/**
 * 增加方法
 */
$('#add').click(function () {
    layer.open({
        title: [$('#add').val(), 'background: mediumpurple;font-size:20px'],
        maxmin: true,
        offset: '50px',
        area: '600px',
        content: $('#editLayer'),
        btn: ['保存', '关闭'],
        success: function () {
            $('#editPwd').css({display: 'block'});
            $('#editForm')[0].reset();
            $('[name=status]')[1].checked = true;
            var roleIdLists = $('[name=roleIdList]');
            for (var a = 0; a < roleIdLists.length; a++) {
                roleIdLists[a].checked = false;
            }//打开窗口时请空
            ajaxExistUser("");
            mobileEmail();
        },
        yes: function (index) {
            if (userFlag === false) {
                layer.msg("用户名不正确")
            } else if (mobileFlag === false) {
                layer.msg(("手机号码不正确"))
            } else if (emailFlag === false) {
                layer.msg(("邮箱不正确"))
            } else if ($('#username').val() === "") {
                layer.msg(("用户名不能为空"))
            } else {
                $.ajax({
                    type: 'post',
                    data: $('#editForm').serialize(),
                    url: 'addUser',
                    success: function (result) {
                        layer.close(index);
                        layer.msg(result.msg);
                        $('#table').bootstrapTable('refresh');
                    }
                });
            }

        },
        btn2: function () {
            layer.close();
        }
    });
});
/**
 * 修改方法
 */
$('#edit').click(function () {
    var select = $('#table').bootstrapTable('getSelections');//获得选择行对象
    if (select.length === 0) {
        $.alert({
            type: 'red',
            title: '提示信息',
            content: '请选择一项',
            icon: 'fa fa-warning'
        });
    } else {
        $.map(select, function (row) {
            layer.open({
                title: [$('#edit').val(), 'background: mediumpurple;font-size:20px'],
                maxmin: true,
                offset: '50px',
                area: '600px',
                content: $('#editLayer'),
                btn: ['保存', '关闭'],
                success: function () {
                    $('#editPwd').css({display: 'none'});
                    var formData = "username=" + row.username
                        + "&email=" + row.email
                        + "&mobile=" + row.mobile;
                    deSerForm(formData);
                    $('[name=status]')[row.status].checked = true;
                    var roleIdLists = $('[name=roleIdList]');
                    var sysUserRole = row.tails.sysUserRole;
                    for (var a = 0; a < roleIdLists.length; a++) {
                        roleIdLists[a].checked = false;
                        roleIdLists[a].onclick = function () {
                            return true;
                        }
                    }//初始化复选框状态
                    for (var i = 0; i < roleIdLists.length; i++) {
                        for (var b = 0; b < sysUserRole.length; b++) {
                            if (sysUserRole[b].roleId.toString()
                                === roleIdLists[i].value) {
                                roleIdLists[i].checked = true;
                            }
                            if (roleIdLists[i].value === '1'
                                && row.userId === 1) {
                                roleIdLists[i].onclick = function () {
                                    return false;
                                }
                            }
                        }
                    }
                    ajaxExistUser(row.userId);
                    mobileEmail();
                },
                yes: function (index) {
                    if (userFlag === false) {
                        layer.msg("用户名不正确")
                    } else if (mobileFlag === false) {
                        layer.msg(("手机号码不正确"))
                    } else if (emailFlag === false) {
                        layer.msg(("邮箱不正确"))
                    } else if ($('#username').val() === "") {
                        layer.msg(("用户名不能为空"))
                    } else {
                        $.ajax({
                            type: 'post',
                            data: $('#editForm').serialize(),
                            url: 'editUser/' + row.userId,
                            success: function (result) {
                                layer.close(index);
                                layer.msg(result.msg);
                                $('#table').bootstrapTable('refresh');
                            }
                        });
                    }
                },
                btn2: function () {
                    layer.close();
                }
            });
        });
    }
});
/**
 * 删除方法
 */
$('#del').click(function () {
    var select = $('#table').bootstrapTable('getSelections');//获得选择行对象
    if (select.length === 0) {
        $.alert({
            type: 'red',
            title: '提示信息',
            content: '请选择一项',
            icon: 'fa fa-warning'
        });
    } else {
        $.map(select, function (row) {
            $.confirm({
                type: 'red',
                typeAnimated: true,
                title: '确认信息',
                content: '确定要删除用户' + row.username + '吗?',
                buttons: {
                    确定: {
                        text: '确定',
                        btnClass: 'btn-red',
                        action: function () {
                            /** @namespace row.userId 用户ID */
                            /** @namespace row.username 用户名 */
                            $.ajax({//ajax提交
                                type: "POST",
                                url: "delUser/" + row.userId,
                                success: function (result) {
                                    if (result) {
                                        layer.msg(result.msg);
                                        $('#table').bootstrapTable('refresh');
                                    } else {
                                        $.alert({
                                            type: 'red',
                                            title: '错误',
                                            content: '删除用户失败',
                                            icon: 'fa fa-warning'
                                        });
                                    }
                                }
                            });
                        }
                    },
                    返回: {
                        text: '返回'
                    }
                }
            });
        })
    }
});
var userFlag;
var mobileFlag;
var emailFlag;
function ajaxExistUser(userId) {//ajax实时验证用户名是否存在
    var userNameById;
    if (userId !== null && "" !== userId) {
        $.ajax({//修改时根据id查找当前用户名
            url: 'findUserById/' + userId,
            dataType: 'json',
            type: 'get',
            success: function (resunlt) {
                userNameById = resunlt.msg;
            }
        });
    }
    $('#username').bind('input propertychange', function () {
        var usernameMsg = $('#usernameMsg');
        if ($(this).val() !== null && "" !== $(this).val()
            && userNameById !== $(this).val()) {
            var username = $(this).val();
        }
        if (username !== null && "" !== username) {
            $.ajax({
                url: 'ajaxExistUser/' + username,
                dataType: 'json',
                type: 'get',
                success: function (result) {
                    if (result.msg === 'false') {
                        usernameMsg.html('*用户名已存在');
                        usernameMsg.css({color: 'red'});
                        userFlag = false;
                    } else {
                        usernameMsg.html('');
                        userFlag = true;
                    }
                }
            });
        }
    });
}
function mobileEmail() {
    $('#mobile').bind('input propertychange', function () {
        var mobile = $(this).val();
        checkMobile(mobile)
    });
    $('#email').bind('input propertychange', function () {
        var email = $(this).val();
        checkEmail(email)
    });
}
function checkMobile(mobile) {
    var mobileMsg = $('#mobileMsg');
    var re = /^\d{11}$/;
    if (re.test(mobile)) {
        mobileMsg.html("");
        mobileFlag = true;
    } else {
        mobileMsg.html("*手机号码无效");
        mobileMsg.css({color: 'red'});
        mobileFlag = false;
    }
}
function checkEmail(email) {
    var emailMsg = $('#emailMsg');
    var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
    if (re.test(email)) {
        emailMsg.html("");
        emailMsg.css({color: 'red'});
        emailFlag = true;
    } else {
        emailMsg.html("*邮箱格式错误");
        emailMsg.css({color: 'red'});
        emailFlag = false;
    }
}