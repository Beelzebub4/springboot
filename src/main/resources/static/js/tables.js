/**
 * 数据库表格显示
 * <br>Created by 吴海南 on 2017/5/2.
 * <br>星期二 at 14:13.
 */
$('#table').bootstrapTable({
    url: 'tablePageList',
    columns: [{
        checkbox: true,
        width:'3%'
    }, {
        formatter: function (value, row, index) {
            return index + 1;
        },
        width:'3%'
    }, {
        field: 'tableName',
        title: '表名',
        sortable:true,
        width:'25%'
    }, {
        field: 'tableRows',
        title: '数据量',
        sortable:true,
        width:'6%'
    }, {
        field: 'engine',
        title: '引擎',
        width:'10%'
    }, {
        field: 'createTime',
        title: '创建时间',
        sortable:true,
        width:'15%'
    }, {
        field: 'tableComment',
        title: '表备注',
        width:'25%'
    }]
});
$(' .search .form-control').attr('placeholder','根据表名搜索');
/**
 * 生成单表代码
 */
$('#genOne').click(function () {
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
            /** @namespace row.tableName 表名 */
            var data = "tableName=" + row.tableName;
            $.ajax({//ajax提交
                type: "POST",
                url: "genCode",
                data: data,
                success: function (result) {
                    if (result) {
                        $.alert({
                            type: 'green',
                            title: '提示信息',
                            content: '生成代码成功',
                            icon: 'fa fa-check-square',
                            buttons: {
                                confirm: {
                                    text: '确认'
                                }
                            }
                        });
                    } else {
                        $.alert({
                            type: 'red',
                            title: '错误',
                            content: '生成代码失败',
                            icon: 'fa fa-warning'
                        });
                    }
                }
            });
        })
    }
});
/**
 * 生成全部代码
 */
$('#genAll').click(function () {
    $.ajax({
        type: "POST",
        url: "genAllCode",
        success: function (result) {
            if (result) {
                $.alert({
                    type: 'green',
                    title: '提示信息',
                    content: '生成全部代码成功',
                    icon: 'fa fa-check-square',
                });
            } else {
                $.alert({
                    type: 'red',
                    title: '错误',
                    content: '生成全部代码失败',
                    icon: 'fa fa-warning'
                });
            }
        }
    });
});