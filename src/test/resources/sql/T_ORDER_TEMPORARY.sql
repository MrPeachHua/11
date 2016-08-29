drop table if exists T_ORDER_TEMPORARY;

/*==============================================================*/
/* Table: T_ORDER_TEMPORARY                                     */
/*==============================================================*/
create table T_ORDER_TEMPORARY
(
   ID                   int(10) not null auto_increment,
   PARKING_ID           varchar(50) not null comment '车场ID',
   ORDER_ID             varchar(50) not null comment '订单主表ID(uuid)',
   CAR_NUMBER           varchar(30) comment '车牌号',
   BLUE_PARKING_ID      varchar(50) not null comment '蓝卡云车场ID',
   BLUE_PARKING_NAME    varchar(50) not null comment '蓝卡云车场名称',
   BLUE_ORDER_ID        varchar(50) not null comment '蓝卡云订单号',
   BEGIN_DATE           datetime not null comment '入场时间,来源于蓝卡云',
   END_DATE             datetime comment '出场时间（算费时间）,来源于蓝卡云',
   CAR_TYPE             varchar(10) not null comment '用户类型，来源于蓝卡云',
   IS_USED              char(1) not null comment '是否可用',
   CREATEOR             varchar(30) not null comment '创建人',
   MODIFIED             varchar(30) comment '修改人',
   CREATE_DATE          datetime not null comment '创建日期',
   MODIFY_DATE          datetime comment '修改日期',
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table T_ORDER_TEMPORARY comment '临停订单表';
