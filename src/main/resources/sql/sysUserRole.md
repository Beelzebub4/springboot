saveUserRole
===
    insert into sys_user_role
    (`user_id`,`role_id`)
    values
    @for(roleId in roleIdList){
        (#userId#,#roleId#)#text(roleIdLP.last?"":"," )#
    @}      
deleteByRoleId
===
    delete from sys_user_role
    @if(isNotEmpty(roleId)){
        where role_id=#roleId#
    @}
deleteByUserId
===
    delete from sys_user_role
    @if(isNotEmpty(userId)){
        where user_id=#userId#
    @}    