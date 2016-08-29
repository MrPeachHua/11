drop table if exists T_ORDER_MAIN;

/*==============================================================*/
/* Table: T_ORDER_MAIN                                          */
/*==============================================================*/
create table T_ORDER_MAIN
(
   ORDER_ID             varchar(50) not null comment '订单ID(uuid)',
   ORDER_TYPE           char(2) not null comment '订单类型：10共享临停，11临停缴费，12代泊，13月租，14产权，15加油卡',
   ORDER_STATUS         char(3) not null comment '订单状态',
   INVOICE_STATUS       char(1) not null comment '发票状态 0：未开具,1：已开具，默认0',
   CUSTOMER_ID          varchar(50) comment '客户ID',
   AMOUNT_PAYABLE       int(10) comment '应付金额，到分，没有小数点',
   AMOUNT_DISCOUNT      int(10) comment '优惠金额，到分，没有小数点',
   AMOUNT_PAID          int(10) comment '实付金额，到分，没有小数点',
   PAY_TYPE             char(2) comment '支付类型 00:支付宝，01:微信，02:银联',
   PAY_TIME             datetime comment '支付时间',
   TRADE_NO             varchar(255) comment '支付方交易号',
   IS_USED              char(1) not null comment '是否可用',
   CREATEOR             varchar(30) not null comment '创建人',
   MODIFIED             varchar(30) comment '修改人',
   CREATE_DATE          datetime not null comment '创建日期',
   MODIFY_DATE          datetime comment '修改日期',
   primary key (ORDER_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table T_ORDER_MAIN comment '订单主表';
