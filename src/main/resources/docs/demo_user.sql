SET NAMES utf8mb4;

DROP TABLE IF EXISTS `demo_user`;

CREATE TABLE `demo_user`
(
    `id`           int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`         varchar(50) NOT NULL COMMENT '姓名',
    `phone`        varchar(50) NOT NULL COMMENT '手机号码',
    `email`        varchar(50)          DEFAULT NULL COMMENT '邮箱',
    `gmt_create`   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` datetime             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `is_deleted`   tinyint(1) DEFAULT '0' COMMENT '逻辑删除：0-未删除 1-已删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';
