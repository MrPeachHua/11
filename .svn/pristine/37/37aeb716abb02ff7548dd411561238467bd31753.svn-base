drop table if exists T_ORDER_EQUITY;

/*==============================================================*/
/* Table: T_ORDER_EQUITY                                        */
/*==============================================================*/
create table T_ORDER_EQUITY
(
   ID                   int(10) not null auto_increment,
   PARKING_ID           varchar(50) not null comment '车场ID',
   ORDER_ID             varchar(50) not null comment '订单主表ID(uuid)',
   CAR_NUMBER           varchar(30) comment '车牌号',
   PRICE                int(10) comment '单价，到分，没有小数点',
   BEGIN_DATE           datetime not null comment '开始时间',
   END_DATE             datetime comment '结束时间',
   MONTH_NUM            int(2) comment '缴费月数',
   CUSTOMER             varchar(50) comment '客户名称，前端输入',
   PARKING_NO           varchar(50) comment '车位号',
   IS_USED              char(1) not null comment '是否可用',
   CREATEOR             varchar(30) comment '创建人',
   MODIFIED             varchar(30) comment '修改人',
   CREATE_DATE          datetime not null comment '创建日期',
   MODIFY_DATE          datetime comment '修改日期',
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table T_ORDER_EQUITY comment '产权订单表';
