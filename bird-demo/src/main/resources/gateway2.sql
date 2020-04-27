CREATE DATABASE IF NOT EXISTS `gateway`;
USE `gateway`;

DROP TABLE IF EXISTS `channel`;
CREATE TABLE `channel`
(
    `id`           bigint(19) unsigned NOT NULL comment '主键',
    `channel_id`   varchar(60)         NOT NULL DEFAULT '' COMMENT '通道ID',
    `channel_name` varchar(128)        NOT NULL DEFAULT '' COMMENT '通道名称',
    `gmt_create`   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    primary key (`id`),
    unique key (`channel_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '通道表';

DROP TABLE IF EXISTS `communication_type`;
CREATE TABLE communication_type
(
    `id`               bigint(19) unsigned NOT NULL COMMENT '主键',
    `communication_id` varchar(60)         NOT NULL DEFAULT '' COMMENT '通讯ID',
    `connect_type`     varchar(60)         NOT NULL DEFAULT '' COMMENT '连接类型',
    `protocol`         varchar(60)         NOT NULL DEFAULT '' COMMENT '协议',
    `channel_id`       varchar(60)         NOT NULL DEFAULT '' COMMENT '通道ID',
    `gmt_create`       timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`     timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '通讯类型表';

DROP TABLE IF EXISTS `external_communication`;
CREATE TABLE external_communication
(
    id                    bigint(19) unsigned NOT NULL comment '主键',
    external_interface_id varchar(60)         NOT NULL default '',
    communication_id      varchar(60)         NOT NULL default '',
    communication_charset varbinary(16)       NOT NULL default '',
    communication_url     varchar(255)        NOT NULL default '',
    timeout               varchar(8)          NOT NULL default '0',
    ext_param             varchar(2000)       NOT NULL default '',
    `gmt_create`          timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`        timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '外部通讯表';

DROP TABLE IF EXISTS `channel`;
CREATE TABLE external_interface
(
    id                      bigint(19)   NOT NULL,
    external_interface_id   varchar(60)  NOT NULL,
    external_interface_name varchar(128) NOT NULL,
    channel_id              varchar(60)  NOT NULL,
    `gmt_create`            timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`          timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    primary key (`id`)
);

DROP TABLE IF EXISTS `channel`;
CREATE TABLE external_parse_template
(
    id                    bigint(19)     NOT NULL,
    external_interface_id varchar(60)    NOT NULL,
    groovy_content        varchar(20000) NOT NULL,
    groovy_bean_name      varchar(255)   NOT NULL,
    memo                  varchar(255)   NOT NULL,
    channel_id            varchar(60)    NOT NULL,
    `gmt_create`          timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`        timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    primary key (`id`)
);

DROP TABLE IF EXISTS `channel`;
CREATE TABLE interface_mapping
(
    id                    bigint(19)  NOT NULL,
    internal_interface_id varchar(60) NOT NULL,
    internal_channel_id   varchar(60) NOT NULL,
    external_interface_id varchar(60) NOT NULL,
    external_channel_id   varchar(60) NOT NULL,
    `gmt_create`          timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`        timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    primary key (`id`)
);

DROP TABLE IF EXISTS `internal_cluster`;
CREATE TABLE internal_cluster
(
    id                bigint(19)   NOT NULL AUTO_INCREMENT,
    cluster_id        varchar(60)  NOT NULL,
    memo              varchar(255) NOT NULL,
    cluster_name      varchar(128) NOT NULL,
    communication_url varchar(255) NOT NULL,
    `gmt_create`      timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`    timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '内部集群表';

DROP TABLE IF EXISTS `internal_communication`;
CREATE TABLE internal_communication
(
    id                    bigint(19)    NOT NULL,
    internal_interface_id varchar(60)   NOT NULL,
    communication_id      varchar(60)   NOT NULL,
    communication_charset varchar(16)   NOT NULL,
    communication_url     varchar(255)  NOT NULL,
    timeout               varchar(8)    NOT NULL,
    ext_param             varchar(2000) NOT NULL,
    `gmt_create`          timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`        timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '内部通讯表';

CREATE TABLE internal_interface
(
    id                      bigint(19)   NOT NULL,
    internal_interface_id   varchar(60)  NOT NULL,
    internal_interface_name varchar(128) NOT NULL,
    channel_id              varchar(60)  NOT NULL,
    `gmt_create`            timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`          timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    primary key (`id`)
);
CREATE TABLE internal_parse_template
(
    id                    bigint(19)     NOT NULL,
    internal_interface_id varchar(60)    NOT NULL,
    groovy_content        varchar(20000) NOT NULL,
    groovy_bean_name      varchar(255)   NOT NULL,
    memo                  varchar(255)   NOT NULL,
    channel_id            varchar(60)    NOT NULL,
    `gmt_create`          timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified`        timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    primary key (`id`)
);
CREATE TABLE param
(
    `id`           bigint(19) unsigned NOT NULL comment '主键',
    `channel_id`   varchar(60)         NOT NULL default '',
    `type`         varchar(128)        NOT NULL default '',
    `name`         varchar(128)        NOT NULL default '',
    `value`        varchar(2000)       NOT NULL default '',
    `memo`         varchar(255)        NOT NULL comment '备注',
    `gmt_create`   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '参数配置表';