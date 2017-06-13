queryMenuParentId
===
    *查询菜单父id
    select * from sys_menu 
    where parent_id = #parentId# 
    order by order_num asc
menuPageList
===
    *菜单分页列表
    select m.*,(select p.name from sys_menu p 
    where p.menu_id = m.parent_id) as parentName 
    from sys_menu m
    #use("search")#
    #use("sort")#
    ,m.type,m.order_num
    #use("limit")#
roleMenuList
===
    *角色菜单列表
    select distinct m.*,
    (select p.name from sys_menu p 
    where p.menu_id = m.parent_id) 
    as parentName
    from sys_user_role ur 
    LEFT JOIN sys_role_menu rm 
    on ur.role_id = rm.role_id 
    LEFT JOIN sys_menu m on rm.menu_id = m.menu_id 
    where ur.user_id = #userId# 
    order by m.order_num asc
noButtonMenuList
===
    *不含按钮的菜单列表
    select * from sys_menu 
    where type!=2  
    order by order_num asc
allCount
===
    *总数量
    select count(1) from
    sys_menu
    #use("search")#    
search
===
    *搜索内容
    @if(!isEmpty(params.search)){
        where name like #'%'+params.search+'%'#
        or url like #'%'+params.search+'%'#
        or perms like #'%'+params.search+'%'#
        or icon like #'%'+params.search+'%'#
    @}
sort
===
    @if(params.sort=='name'){
        order by m.name
        #use("order")#
    @}else if(params.sort=='parentName'){
        order by parentName
        #use("order")#
    @}else if(params.sort=='icon'){
        order by m.icon
        #use("order")#
    @}else if(params.sort=='url'){
        order by m.url
        #use("order")#
    @}else if(params.sort=='perms'){
        order by m.perms
        #use("order")#
    @}else {
        order by m.type
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
    @if(isNotEmpty(params.offset)){
        limit #params.offset#   
    @}
    @if(isNotEmpty(params.limit)){
        ,#params.limit#
    @}
  