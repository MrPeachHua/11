drop table if exists T_ORDER_TEMPORARY_SHARE;

/*==============================================================*/
/* Table: T_ORDER_TEMPORARY_SHARE                               */
/*==============================================================*/
create table T_ORDER_TEMPORARY_SHARE
(
   ID                   int(10) not null auto_increment,
   PARKING_ID           varchar(50) not null comment '车场ID',
   ORDER_ID             varchar(50) not null comment '订单主表ID(uuid)',
   CAR_NUMBER           varchar(30) comment '车牌号',
   PARKING_CODE         varchar(50) comment '停车码',
   IS_USED              char(1) not null comment '是否可用',
   CREATEOR             varchar(30) not null comment '创建人',
   MODIFIED             varchar(30) comment '修改人',
   CREATE_DATE          datetime not null comment '创建日期',
   MODIFY_DATE          datetime comment '修改日期',
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table T_ORDER_TEMPORARY_SHARE comment '共享临停订单表';
