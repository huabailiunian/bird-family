CREATE DATABASE IF NOT EXISTS `gateway`;
USE `gateway`;

DROP TABLE IF EXISTS `gateway_channel`;
CREATE TABLE `gateway_channel`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `channel_id`   varchar(64)         NOT NULL COMMENT '通道ID',
    `channel_name` varchar(128)        NOT NULL COMMENT '通道名称',
    `gmt_create`   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_channel_id` (`channel_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '通道表';

DROP TABLE IF EXISTS `gateway_communication`;
CREATE TABLE `gateway_communication`
(
    `id`               bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `communication_id` varchar(64)         NOT NULL COMMENT '通讯ID',
    `connect_type`     varchar(64)         NOT NULL COMMENT '连接类型',
    `protocol`         varchar(64)         NOT NULL COMMENT '协议',
    `channel_id`       varchar(64)         NOT NULL COMMENT '通道',
    `gmt_create`       timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`     timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '通讯类型表';

DROP TABLE IF EXISTS `gateway_internal_interface`;
CREATE TABLE `gateway_internal_interface`
(
    `id`                      bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `internal_interface_id`   varchar(64)         NOT NULL COMMENT '内部接口ID',
    `internal_interface_name` varchar(128)        NOT NULL COMMENT '内部接口名',
    `channel_id`              varchar(64)         NOT NULL COMMENT '通道ID',
    `interactive_type`        varchar(64)         NOT NULL COMMENT '交互类型',
    `gmt_create`              timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`            timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_interface_id` (`internal_interface_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '内部接口表';

DROP TABLE IF EXISTS `gateway_internal_communication`;
CREATE TABLE `gateway_internal_communication`
(
    `id`                    bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `internal_interface_id` varchar(64)         NOT NULL COMMENT '内部接口',
    `communication_id`      varchar(64)         NOT NULL COMMENT '通讯类型',
    `communication_charset` varchar(16)         NOT NULL DEFAULT 'UTF-8' COMMENT '字符编码',
    `communication_url`     varchar(255)        NOT NULL COMMENT '通讯地址',
    `timeout`               varchar(8)          NOT NULL DEFAULT '3000' COMMENT '超时时间',
    `ext_param`             varchar(2000)       NOT NULL DEFAULT '' COMMENT '扩展参数',
    `gmt_create`            timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`          timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_interface_id` (`internal_interface_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '内部通讯表';

DROP TABLE IF EXISTS `gateway_external_interface`;
CREATE TABLE `gateway_external_interface`
(
    `id`                      bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `external_interface_id`   varchar(64)         NOT NULL COMMENT '外部接口ID',
    `external_interface_name` varchar(128)        NOT NULL COMMENT '外部接口名',
    `channel_id`              varchar(64)         NOT NULL COMMENT '通道ID',
    `interactive_type`        varchar(64)         NOT NULL COMMENT '交互类型',
    `gmt_create`              timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`            timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_interface_id` (`external_interface_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '外部接口表';

DROP TABLE IF EXISTS `gateway_external_communication`;
CREATE TABLE `gateway_external_communication`
(
    `id`                    bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `external_interface_id` varchar(64)         NOT NULL COMMENT '内部接口',
    `communication_id`      varchar(64)         NOT NULL COMMENT '通讯类型',
    `communication_charset` varchar(16)         NOT NULL DEFAULT 'UTF-8' COMMENT '字符编码',
    `communication_url`     varchar(255)        NOT NULL COMMENT '通讯地址',
    `timeout`               varchar(8)          NOT NULL DEFAULT '3000' COMMENT '超时时间',
    `ext_param`             varchar(2000)       NOT NULL DEFAULT '' COMMENT '扩展参数',
    `gmt_create`            timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`          timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_interface_id` (`external_interface_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT '外部通讯表';
