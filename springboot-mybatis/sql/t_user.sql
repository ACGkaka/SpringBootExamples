-- ------------------------------
-- 用户表
-- ------------------------------
CREATE TABLE `t_user` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dept_id` bigint(16) DEFAULT NULL COMMENT '部门id',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(16) DEFAULT NULL COMMENT '电话',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标识（0未删除 1已删除）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';