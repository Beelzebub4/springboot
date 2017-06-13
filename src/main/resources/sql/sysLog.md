logPageList
===
    * 日志分页查询
    select #use("cols")# 
    from sys_log where 1=1
    #use("search")#
    #use("sort")#
    #use("limit")#
allCount
===
    *总数量
    select count(1) from
    sys_log where 1=1 
    #use("search")#
cols
===
    *列名
    username,
    operation,
    method,
    params,ip,
    create_date

search
===
    *搜索内容
    and create_date between
    #params.startTime# 
    and #params.endTime#
    @if(!isEmpty(params.search)){
        and username like #'%'+params.search+'%'#
    @}
sort
===
    @if(params.sort=='username'){
        order by username
        #use("order")#
    @}else if(params.sort=='operation'){
        order by operation
        #use("order")#
    @}else if(params.sort=='method'){
        order by method
        #use("order")#
    @}else if(params.sort=='ip'){
        order by ip
        #use("order")#
    @}else if(params.sort=='createDate'){
        order by create_date
        #use("order")#
    @}else {
        order by create_date
        desc
    @}
order
===
    @if(params.order=='asc'){
        asc
    @}else{
        desc
    @} 

limit
===
    *分页
    @if(!isEmpty(params.offset)){
        limit #params.offset#   
    @}
    @if(!isEmpty(params.limit)){
        ,#params.limit#
    @}