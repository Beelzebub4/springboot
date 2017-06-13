newsList
===
    *数据库表分页查询
    SELECT #use("cols")# FROM 
    edit_news 
    #use("condition")#
    #use("search")#
    #use("sort")#
    #use("limit")#

cols
===
    *列名
    id,
    title,
    content,
    create_date
    
condition
===
    *条件
search
===
    *搜索内容
    @if(!isEmpty(params.search)){
        where title like #'%'+params.search+'%'#
    @}
sort
===
        order by create_date
        #use("order")#
order
===
    @if(params.order=='asc'){
        desc
    @}else{
        asc
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
    edit_news
    #use("search")#
selectNewsById
===
    select * from edit_news where id=#newsId#