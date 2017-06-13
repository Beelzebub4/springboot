tablePageList
===
    *数据库表分页查询
    SELECT #use("cols")# FROM 
    information_schema.tables 
    #use("condition")#
    #use("search")#
    #use("sort")#
    #use("limit")#

cols
===
    *列名
    table_name,
    engine,
    create_time,
    table_comment,
    table_rows
    
condition
===
    *条件
    WHERE table_schema = 
    (SELECT database())
search
===
    *搜索内容
    @if(!isEmpty(params.search)){
        and table_name like #'%'+params.search+'%'#
    @}
sort
===
    @if(params.sort=='tableName'){
        order by table_name
        #use("order")#
    @}else if(params.sort=='tableRows'){
        order by table_rows
        #use("order")#
    @}else{
        order by create_time
        #use("order")#
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

allCount
===
    *总数量
    SELECT COUNT(1) FROM 
    information_schema.tables 
    WHERE table_schema = 
    (SELECT database())
     #use("search")#