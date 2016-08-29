drop table if exists T_ORDER_PARK;

/*==============================================================*/
/* Table: T_ORDER_PARK                                          */
/*==============================================================*/
create table T_ORDER_PARK
(
   ID                   int(10) not null auto_increment,
   ORDER_ID             varchar(50) not null comment '订单主表ID(uuid)',
   PARKING_ID           varchar(50) not null comment '车场ID',
   PARKER_ID            varchar(50) not null comment '接车代泊员ID',
   PARKER_BACK_ID       varchar(50) not null comment '还车代泊员ID',
   VALIDATE_IMAGE_PATH  varchar(1000) not null comment '验车照片路径（代泊员接车）',
   PARKING_IMAGE_PATH   varchar(500) not null comment '停车照片路径（代泊员停车）',
   CAR_NUMBER           varchar(30) comment '车牌号',
   ORDER_BEGIN_DATE     datetime not null comment '订单创建时间',
   ORDER_END_DATE       datetime comment '预约取车时间（代泊员填写）',
   ACTUAL_BEGIN_DATE    datetime not null comment '代泊员接车拍完验车照上传完照片开始（用户：交车时间，代泊员：接车时间）',
   ACTUAL_END_DATE      datetime comment '用户付完款结束或代泊员订单结束',
   IS_USED              char(1) not null comment '是否可用',
   CREATEOR             varchar(30) not null comment '创建人',
   MODIFIED             varchar(30) comment '修改人',
   CREATE_DATE          datetime not null comment '创建日期',
   MODIFY_DATE          datetime comment '修改日期',
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table T_ORDER_PARK comment '代泊订单表';
