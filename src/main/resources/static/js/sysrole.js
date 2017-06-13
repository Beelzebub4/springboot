/**
 * <br>Created by 吴海南 on 2017/5/4.
 * <br>星期四 at 9:42.
 */
$('#table').bootstrapTable({
    url: 'rolePageList',
    columns: [
        {
            checkbox: true,
            width:'3%'
        }, {
            formatter: function (value, row, index) {
                return index + 1;
            },
            width:'3%'
        }, {
            field: 'roleName',
            title: '角色名称',
            sortable:true,
            width:'20%'
        }, {
            field: 'remark',
            title: '备注',
            sortable:true,
            width:'20%'
        }, {
            field: 'createTime',
            title: '创建时间',
            sortable:true,
            width:'20%'
        }
    ]
});
/**
 * 新增方法
 */
$('#add').click(function () {
    layer.open({
        title: [$('#add').val(), 'background: mediumpurple;font-size:20px'],
        maxmin: true,
        offset: '50px',
        area: ['620px', '480px'],
        content: $('#editLayer'),
        btn: ['保存', '关闭'],
        success: function () {
            $('#editForm')[0].reset();
            ztree.setChkDisabled(ztree.getNodes()[0], false, true, true);
            //取消勾选角色所拥有的菜单
            ztree.checkAllNodes(false);
        },
        yes: function (index) {
            var nodes = ztree.getCheckedNodes(true);
            var menuIdList = [];
            for (var i = 0; i < nodes.length; i++) {
                menuIdList.push(nodes[i].menuId);
            }
            var data = $('#editForm').serialize() + '&menuIdList=' + menuIdList;
            if ($('#roleName').val() === "") {
                layer.msg("角色名不能为空");
            } else {
                $.ajax({
                    type: 'post',
                    data: data,
                    url: 'addRole',
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
            var nodes = ztree.getNodes();
            for (var i = 0; i < nodes.length; i++) {
                ztree.setChkDisabled(ztree.getNodes()[i], false, true, true);
            }
            //取消勾选角色所拥有的菜单
            ztree.checkAllNodes(false);
            layer.open({
                title: [$('#edit').val(), 'background: mediumpurple;font-size:20px'],
                maxmin: true,
                offset: '50px',
                area: ['620px', '480px'],
                content: $('#editLayer'),
                btn: ['保存', '关闭'],
                success: function () {
                    /** @namespace r.menuIdList */
                    var sysRoleMenus = row.tails.sysRoleMenu;
                    for (var i = 0; i < sysRoleMenus.length; i++) {
                        var node = ztree.getNodeByParam(
                            "menuId", sysRoleMenus[i].menuId);
                        ztree.checkNode(node, true, false);
                        if (1 === row.roleId) {//管理员权限默认最大 不可编辑
                            ztree.setChkDisabled(node, true, false, false);
                        }
                    }
                    selectRow('editForm', row,[])
                },
                yes: function (index) {
                    var nodes = ztree.getCheckedNodes(true);
                    var menuIdList = [];
                    for (var i = 0; i < nodes.length; i++) {
                        menuIdList.push(nodes[i].menuId);
                    }
                    var data = $('#editForm').serialize() + '&menuIdList=' + menuIdList;
                    if ($('#roleName').val() === "") {
                        layer.msg("角色名不能为空");
                    } else {
                        $.ajax({
                            type: 'post',
                            data: data,
                            url: 'editRole/' + row.roleId,
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
            /** @namespace row.roleId 角色ID */
            /** @namespace row.roleName 角色名 */
            $.confirm({
                type: 'red',
                typeAnimated: true,
                title: '确认信息',
                content: '确定要删除角色' + row.roleName + '吗?',
                buttons: {
                    确定: {
                        text: '确定',
                        btnClass: 'btn-red',
                        action: function () {
                            $.ajax({//ajax提交
                                type: "POST",
                                url: "delRole/" + row.roleId,
                                success: function (result) {
                                    if (result) {
                                        layer.msg(result.msg);
                                        $('#table').bootstrapTable('refresh');
                                    } else {
                                        $.alert({
                                            type: 'red',
                                            title: '错误',
                                            content: '删除角色失败',
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
var ztree;
var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "menuId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    },
    check: {
        enable: true,
        nocheckInherit: true
    }
};
$(function () {
    $.get("roleMenuTree", function (r) {
        /** @namespace r.roleMenuList */
        ztree = $.fn.zTree.init($("#menuTree"), setting, r.roleMenuList);
    });
});