/**
 * 数据库表格显示
 * <br>Created by 吴海南 on 2017/5/2.
 * <br>星期二 at 14:13.
 */
var table = $('#table');
var startTime = FormatDate(moment().subtract('days', 30).toDate());
var endTime = FormatDate(moment().add('minutes',1).toDate());
/**
 * @return {string}
 */
function FormatDate(strTime) {
    var date = new Date(strTime);
    return date.getFullYear() + "-"
        + (date.getMonth() + 1) + "-"
        + date.getDate() + " "
        + date.getHours() + ":"
        + date.getMinutes();
}
table.bootstrapTable({
    url: 'sysLogPageList',
    queryParams: queryParams,
    columns: [
        {
            formatter: function (value, row, index) {
                return index + 1;//本地序号
            },
            width: '3%'
        }, {
            field: 'username',
            title: '用户名',
            sortable: true,
            width: '10%'
        }, {
            field: 'operation',
            title: '用户操作',
            sortable: true,
            width: '6%'
        }, {
            field: 'method',
            title: '请求方法',
            sortable: true,
            width: '15%'
        }, {
            field: 'params',
            title: '请求参数',
            width: '35%'
        }, {
            field: 'ip',
            title: 'IP地址',
            sortable: true,
            width: '10%'
        }, {
            field: 'createDate',
            title: '创建时间',
            sortable: true,
            width: '15%'
        }
    ]
});
$(' .search .form-control').attr('placeholder', '根据用户名搜索');
table.bootstrapTable('hideColumn', 'params');
function queryParams(params) {
    return {
        limit: params.limit,
        offset: params.offset,
        order: params.order,
        sort: params.sort,
        search: params.search,
        startTime: startTime,
        endTime: endTime
    };
}
var initTime = $('#initTime');
initTime.html(FormatDate(moment().subtract('days', 30).toDate()) + '<span class="fa fa-long-arrow-right"></span>' + FormatDate(moment().toDate()));
$('#queryTime').daterangepicker({
    timePicker: true
}, function (start, end) {
    initTime.html(FormatDate(start.toDate()) + '<span class="fa fa-long-arrow-right"></span>' + FormatDate(end.toDate()));
    startTime = FormatDate(start.toDate());
    endTime = FormatDate(end.toDate());
    $('#table').bootstrapTable('refresh');
});