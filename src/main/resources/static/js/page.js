/**
 * 页面高度适应调整
 * <br>Created by 吴海南 on 2017/5/9.
 * <br>星期二 at 15:31.
 */
$(function () {
    var windowH = $(window).height();//窗口高度
    var wrapper = $('.wrapper');//主div
    var content = $('.content');//主内容
    var table=$('#table');//表格
    var unUseH = $('.main-header').height()
        + $('.content-header').height()
        + $('.main-foot').height();
    wrapper.css({height: windowH});
    content.css({height: windowH - unUseH});
    table.bootstrapTable('resetView', {height: windowH - unUseH-7});
});