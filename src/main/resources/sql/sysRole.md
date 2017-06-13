findById
===
    *根据id查找角色
    select * from sys_role where 1=1
    @if(!isEmpty(roleId)){
    and role_id = #roleId#
    @}
roleList
===
    * 如果不是管理员账号 
    * 只能查看当前账号创建的角色
    select * from sys_role
    @if(createUserId!=@com.wuhainan.utils.Constant.SUPER_ADMIN){
        where create_user_id=#createUserId#
    @}
roleIdList
===
    *角色id集合
    select role_id from sys_role 
    @if(isNotEmpty(createUserId)){
        where create_user_id = #createUserId#
    @}
rolePageList
===
    *角色分页列表
    select * from sys_role where 1=1
    @if(createUserId!=@com.wuhainan.utils.Constant.SUPER_ADMIN){
        and create_user_id=#createUserId#
    @}
    #use("search")#
    #use("sort")#
    ,role_id asc 
    #use("limit")#
    @orm.many({"roleId":"roleId"},"sysRole.sysRoleMenu","SysRoleMenu");
allCount
===
    select count(1) from
    sys_role where 1=1
    #use("search")#      
search
===
    *搜索内容
    @if(!isEmpty(params.search)){
        and role_name like #'%'+params.search+'%'#
        or remark like #'%'+params.search+'%'#
    @} 
sort
===
    @if(params.sort=='roleName'){
        order by role_name
        #use("order")#
    @}else if(params.sort=='remark'){
        order by remark
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
sysRoleMenu
===
    select srm.menu_id from sys_role_menu srm
    where srm.role_id=#roleId#