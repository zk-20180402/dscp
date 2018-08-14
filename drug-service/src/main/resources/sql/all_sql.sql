SET NAMES utf8;
CREATE DATABASE `dscp` DEFAULT CHARACTER SET utf8;

USE `dscp`;

/*==============================================================*/
/* Table: t_drug                                         */
/*==============================================================*/
DROP TABLE IF EXISTS t_drug;
create table t_drug
(
   id                   int(10) unsigned NOT NULL AUTO_INCREMENT comment '编号',
   create_time         timestamp comment '创建时间',
   create_user         varchar(20) comment '创建人',
   role_name      varchar(20) comment '角色名称',
   role_status         tinyint(1) comment '角色状态',
   update_time           timestamp comment '更新时间',
   update_user               varchar(20) comment '更新用户',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '药品表';