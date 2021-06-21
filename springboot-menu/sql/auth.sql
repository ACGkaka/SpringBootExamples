-- ------------------------------
-- 用户表
-- ------------------------------
CREATE TABLE `T_USER` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dept_id` bigint(16) COMMENT '部门id',
  `username` varchar(64) COMMENT '用户名',
  `password` varchar(32) COMMENT '密码',
  `email` varchar(64) COMMENT '邮箱',
  `phone` varchar(16) COMMENT '电话',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识（0未删除 1已删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';


-- ------------------------------
-- 部门表
-- ------------------------------
CREATE TABLE `T_DEPT` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` bigint(16) COMMENT '父部门id',
  `dept_name` varchar(64) COMMENT '部门名称',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识（0未删除 1已删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';


-- ------------------------------
-- 菜单表
-- ------------------------------
CREATE TABLE `T_MENU` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` bigint(16) COMMENT '父菜单id',
  `menu_name` varchar(64) COMMENT '菜单名称',
  `menu_url` varchar(128) COMMENT '菜单链接',
  `menu_type` char(1) COMMENT '菜单类型（C菜单 B按钮）',
  `order_num` int(4) COMMENT '显示顺序',
  `visible` char(1) COMMENT '是否显示（0显示 1隐藏）',
  `icon` varchar(64) COMMENT '菜单图标',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识（0未删除 1已删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';


-- ------------------------------
-- 角色表
-- ------------------------------
CREATE TABLE `T_ROLE` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(64) COMMENT '角色名称',
  `role_nickname` varchar(64) COMMENT '角色昵称',
  `status` char(1) COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识（0未删除 1已删除）',
  `remark` varchar(255) COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';


-- ------------------------------
-- 角色和菜单关联表
-- ------------------------------
CREATE TABLE `T_ROLE_MENU` (
  `role_id` bigint(16) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(16) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';


-- ------------------------------
-- 用户和角色关联表
-- ------------------------------
CREATE TABLE `T_USER_ROLE` (
  `user_id` bigint(16) NOT NULL COMMENT '用户ID',
  `role_id` bigint(16) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`user_id`, `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';