/**
 * 数据库表格显示
 * <br>Created by 吴海南 on 2017/5/2.
 * <br>星期二 at 14:13.
 */
$('#table').bootstrapTable({
    url: 'newsPageList',
    columns: [{
        checkbox: true,
        width: '3%'
    }, {
        formatter: function (value, row, index) {
            return index + 1;
        },
        width: '3%'
    }, {
        field: 'title',
        title: '新闻标题',
        sortable: true,
        width: '25%'
    }, {
        field: 'createDate',
        title: '编辑时间',
        sortable: true,
        width: '15%'
    }]
});
$(' .search .form-control').attr('placeholder', '根据新闻标题搜索');

/**
 * 新增方法
 */
$('#add').click(function () {
    var ue = UE.getEditor('myEditor', {
        scaleEnabled: true,
        initialFrameHeight: 360
    });
    layer.open({
        title: [$('#add').val(), 'background: mediumpurple;font-size:20px'],
        maxmin: true,
        offset: '20px',
        area: '900px',
        content: $('#editLayer'),
        btn: ['保存', '关闭'],
        success: function () {
            ue.ready(function () {
                ue.setContent("")
            })
        },
        yes: function () {
            var content = ue.getContent();
            $.ajax({
                type: "POST",
                url: "saveNews",
                data: content,
                success: function (result) {
                    layer.closeAll();
                    layer.msg('增加新闻成功');
                    $('#table').bootstrapTable('refresh');
                }, error: function (result) {
                    layer.msg('标题不能为空');
                }
            });
            ue.destroy();
        },
        btn2: function () {
            ue.destroy();
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
        var ue = UE.getEditor('myEditor', {
            scaleEnabled: true,
            initialFrameHeight: 360
        });
        $.map(select, function (row) {
            layer.open({
                title: [$('#edit').val(), 'background: mediumpurple;font-size:20px'],
                maxmin: true,
                offset: '20px',
                area: '900px',
                content: $('#editLayer'),
                btn: ['保存', '关闭'],
                success: function () {
                    ue.ready(function () {
                        ue.setContent(row.content)
                    })
                },
                yes: function () {
                    var content = ue.getContent();
                    $.ajax({
                        type: "POST",
                        url: "editNews/" + row.id,
                        data: content,
                        success: function (result) {
                            layer.closeAll();
                            layer.msg('修改新闻成功');
                            $('#table').bootstrapTable('refresh');
                        }, error: function (result) {
                            layer.msg('标题不能为空');
                        }
                    });
                    ue.destroy();
                },
                btn2: function () {
                    ue.destroy();
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
                content: '确定要删除新闻' + row.title + '吗?',
                buttons: {
                    确定: {
                        text: '确定',
                        btnClass: 'btn-red',
                        action: function () {
                            console.log(row)
                            $.ajax({//ajax提交
                                type: "POST",
                                url: "delNews/" + row.id,
                                success: function (result) {
                                    if (result) {
                                        layer.msg("删除新闻成功");
                                        $('#table').bootstrapTable('refresh');
                                    } else {
                                        $.alert({
                                            type: 'red',
                                            title: '错误',
                                            content: '删除新闻失败',
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