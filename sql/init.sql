create table sys_user
(
	user_id bigint auto_increment
		primary key,
	username varchar(50) not null comment '用户名',
	password varchar(100) null comment '密码',
	email varchar(100) null comment '邮箱',
	mobile varchar(50) null comment '手机号',
	status tinyint null comment '状态  0：禁用   1：正常',
	create_user_id bigint null comment '创建者ID',
	create_time datetime null comment '创建时间',
	constraint username
		unique (username)
)comment '系统用户';

INSERT INTO sys_user (username, password, email, mobile, status, create_user_id, create_time) VALUES ('系统管理员', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', '715848761@qq.com', '15801327133', 1, 1, '2017-05-04 09:34:46');

create table sys_role
(
	role_id bigint auto_increment
		primary key,
	role_name varchar(100) not null comment '角色名称',
	remark varchar(100) null comment '备注',
	create_user_id bigint null comment '创建者ID',
	create_time datetime null comment '创建时间'
)comment '角色';

INSERT INTO sys_role (role_name, remark, create_user_id, create_time) VALUES ('系统管理员', '系统管理员', 1, '2017-05-09 16:56:49');

create table sys_menu
(
	menu_id bigint auto_increment
		primary key,
	parent_id bigint null comment '父菜单ID，一级菜单为0',
	name varchar(50) not null comment '菜单名称',
	url varchar(200) null comment '菜单URL Controller里的RequestMapping',
	perms varchar(500) null comment '授权(多个用逗号分隔，如：user:list,user:create)',
	type int null comment '类型   0：目录   1：菜单   2：按钮',
	icon varchar(50) null comment '菜单图标',
	order_num int null comment '排序'
)comment '菜单管理';

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (0, '系统管理', '', '', 0, 'fa fa-cog', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (0, 'Sql监控', '', '', 0, 'fa fa-tv', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (1, '用户管理', 'sysUser', '', 1, 'fa fa-user', 1);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (1, '角色管理', 'sysRole', '', 1, 'fa fa-user-secret', 2);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (1, '菜单管理', 'sysMenu', '', 1, 'fa fa-telegram', 3);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (1, '系统日志', 'sysLog', '', 1, 'fa fa-sticky-note-o', 4);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (1, '代码生成', 'tables', '', 1, 'fa fa-file-code-o', 5);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (3, '查看', '', 'sys:user:list', 2, '', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (3, '新增', '', 'sys:user:add', 2, '', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (3, '修改', '', 'sys:user:edit', 2, '', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (3, '删除', '', 'sys:user:del', 2, '', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (4, '查看', '', 'sys:role:list,sys:menu:roleMenuTree', 2, '', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (4, '新增', '', 'sys:role:add', 2, '', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (4, '修改', '', 'sys:role:edit', 2, '', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (4, '删除', '', 'sys:role:del', 2, '', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (5, '查看', '', 'sys:menu:list,sys:menu:roleNbMenuTree', 2, '', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (5, '新增', '', 'sys:menu:add', 2, '', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (5, '修改', '', 'sys:menu:edit', 2, '', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (5, '删除', '', 'sys:menu:del', 2, '', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (6, '查看', '', 'sys:log:list', 2, '', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (7, '查看', '', 'sys:table:list', 2, '', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (7, '生成单表', '', 'sys:table:genOne', 2, '', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (7, '生成全部', '', 'sys:table:genAll', 2, '', null);
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num) VALUES (2, 'Druid登录', 'druidLogin', 'druid:login', 1, 'fa fa-globe', 1);

create table sys_user_role
(
	id bigint auto_increment
		primary key,
	user_id bigint null comment '用户ID',
	role_id bigint null comment '角色ID'
)comment '用户与角色对应关系';

INSERT INTO sys_user_role (user_id, role_id) VALUES (1, 1);

create table sys_role_menu
(
	id bigint auto_increment
		primary key,
	role_id bigint null comment '角色ID',
	menu_id bigint null comment '菜单ID'
)comment '角色与菜单对应关系';

INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 1);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 2);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 3);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 4);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 5);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 6);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 7);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 8);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 9);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 10);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 11);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 12);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 13);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 14);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 15);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 16);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 17);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 18);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 19);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 20);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 21);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 22);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 23);
INSERT INTO sys_role_menu (role_id, menu_id) VALUES (1, 24);

create table sys_log
(
	id bigint auto_increment
		primary key,
	username varchar(50) null comment '用户名',
	operation varchar(50) null comment '用户操作',
	method varchar(200) null comment '请求方法',
	params varchar(5000) null comment '请求参数',
	ip varchar(64) null comment 'IP地址',
	create_date datetime null comment '创建时间'
)comment '系统日志';
create table sys_file_upload
(
	id int auto_increment
		primary key,
	file_name varchar(50) null,
	file_url varchar(100) null,
	create_date datetime null
)comment '文件上传';
-- auto-generated definition
create table edit_news
(
	id int auto_increment
		primary key,
	content text null,
	title varchar(100) null,
	create_date datetime null
)comment '新闻编辑';