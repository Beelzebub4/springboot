saveRoleMenu
===
    *保存角色菜单关系
    insert into sys_role_menu
    (`role_id`,`menu_id`)
    values
    @for(menuId in menuIdList){
        (#roleId#,#menuId#)#text(menuIdLP.last?"":"," )#
    @}
addRoleMenu
===
    *增加角色菜单 保证管理员最大权限
    insert into sys_role_menu
    (`role_id`,`menu_id`)
    values
    (#roleId#,#menuId#)
deleteByRoleId
===
    *根据角色id删除
    delete from sys_role_menu
    @if(isNotEmpty(roleId)){
        where role_id=#roleId#
    @}
deleteByMenuId
===
    *根据菜单id删除
    delete from sys_role_menu
    @if(isNotEmpty(menuId)){
        where menu_id=#menuId#
    @}