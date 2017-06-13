findByName
===
    * 根据用户名查询用户信息
    select * from sys_user where 1=1
    @if(!isEmpty(username)){
        and username = #username#
    @}
findById
===
    * 根据用户Id查询
    select * from sys_user where 1=1
    @if(!isEmpty(userId)){
        and user_id = #userId#
    @}
userPageList
===
    * 用户信息分页列表
    select * from sys_user where 1=1
    #use("search")#
    #use("sort")#
    ,user_id desc
    #use("limit")#
    @orm.many({"userId":"userId"},"sysUser.sysUserRole","SysUserRole");
userPermsList
===
    * 如果是超级管理员 查找菜单表里全部权限
   	SELECT m.perms 
   	@if(userId==@com.wuhainan.utils.Constant.SUPER_ADMIN){
   	    FROM sys_menu m where 1=1
    @}else{
        FROM sys_user_role ur
        #use("joinRoleMenu")#
        #use("joinMenu")#
        WHERE ur.user_id = #userId#
    @}
userMenuIdList
===
    * 用户拥有的菜单id集合
    select distinct rm.menu_id 
    from sys_user_role ur 			
    #use("joinRoleMenu")#
    where ur.user_id = #userId#

ajaxExistUser
===
    * ajax获取用户名
    select su.username  
    from sys_user su 
    WHERE su.username=#usernameAjax#
updatePassword
===
    * 修改密码
    update sys_user set 
    `password` = #newPassword#
    where user_id = #userId# 
    and password = #oldPassword#
joinMenu
===
    LEFT JOIN sys_menu m ON rm.menu_id = m.menu_id
joinRoleMenu
===
    LEFT JOIN sys_role_menu rm ON ur.role_id = rm.role_id
allCount
===
    select count(1) from
    sys_user where 1=1
    #use("search")#        
search
===
    *搜索内容
    @if(!isEmpty(params.search)){
        and username like #'%'+params.search+'%'#
        or email like #'%'+params.search+'%'#
        or mobile like #'%'+params.search+'%'#
    @} 
sort
===
    @if(params.sort=='username'){
        order by username
        #use("order")#
    @}else if(params.sort=='email'){
        order by email
        #use("order")#
    @}else if(params.sort=='mobile'){
        order by mobile
        #use("order")#
    @}else {
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
    
sysUserRole
===
    select sur.role_id from sys_user_role sur
    where sur.user_id=#userId#