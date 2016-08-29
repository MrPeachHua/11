-- 2016-1-21 软盈与岩卓订单表ID长度不一致
alter table t_managementfeeorderinfo    modify column id  varchar(50);
alter table t_monthlyparkingfeeorderinfo    modify column id  varchar(50);
alter table t_temporaryparkfeeorderinfo    modify column id  varchar(50);

-- 2016-1-22 添加优惠券菜单
INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,memo,parent_code,is_leaf) VALUES 
	('1003006','优惠券','left.gif','2','products/coupon/list.html','6','','1003','1');
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,memo,is_used) VALUES 
	('1003006','超级管理员','1','','1');
	
-- 2016-1-22 添加优惠券字典
insert into T_DICTIONARY (DICT_CODE,DICT_NAME,DICT_VALUE,MEMO,IS_USED,CREATEOR,CREATE_DATE) values 
	('vouchers_type','停车券','1','优惠券表--通用券类型','1','admin',current_timestamp()),
	('coupon_type','定额','1','优惠券表--优惠券类型','1','admin',current_timestamp()),
	('coupon_type','折扣','2','优惠券表--优惠券类型','1','admin',current_timestamp())

-- 2016-1-22 添加优惠券字段‘领取时间’
alter table t_coupon add column receive_time varchar(30) COMMENT '领取时间'; 

-- 2016-1-26 添加短信发送菜单
INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,memo,parent_code,is_leaf) VALUES 
	('1004006','短信发送','left.gif','2','system/sms/list.html','6','','1004','1');
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,memo,is_used) VALUES 
	('1004006','超级管理员','1','','1');
	
drop table if exists t_parking_ticket;
/*==============================================================*/
/* Table: t_parking_ticket                                      */
/*==============================================================*/
create table T_PARKING_TICKET
(
   ID                   int(10) not null auto_increment,
   CUSTOMER_ID          varchar(50) comment '用户ID',
   MOBILE               varchar(20) comment '用户手机号',
   START_DATE           datetime comment '有效期开始时间',
   END_DATE             datetime comment '有效期结束时间',
   CODE                 varchar(11) comment '停车码',
   IS_USED              char(1) not null comment '是否可用',
   CREATEOR             varchar(30) not null comment '创建人',
   MODIFIED             varchar(30) default NULL comment '修改人',
   CREATE_DATE          datetime not null comment '创建日期',
   MODIFY_DATE          datetime default NULL comment '修改日期',
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table T_PARKING_TICKET comment '短信推送停车券信息';

drop table if exists T_SMS;
/*==============================================================*/
/* Table: t_sms                                                 */
/*==============================================================*/
create table T_SMS
(
   RECORD_ID            int(10) not null auto_increment,
   CONTENT              varchar(300) default '' comment '推送内容',
   MOBILE               varchar(20) not null comment '手机号',
   ADD_TIME             varchar(50) not null default '0000-00-00 00:00:00' comment '添加时间',
   primary key (RECORD_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table T_PARKING_TICKET comment '短信推送信息';

alter table t_user_info modify column user_num  varchar(50);	

-- 2016-2-1 添加优惠券菜单
INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,memo,parent_code,is_leaf) VALUES 
	('1003007','意见反馈','left.gif','2','products/feelback/list.html','7','','1003','1');
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,memo,is_used) VALUES 
	('1003007','超级管理员','1','','1');	
	
-- 2016-1-27 订单类开、添加加油卡充值类型、加油卡类型
insert into T_DICTIONARY (DICT_CODE,DICT_NAME,DICT_VALUE,MEMO,IS_USED,CREATEOR,CREATE_DATE) values 
	('order_type','共享临停','10','订单主表--订单类型','1','admin',current_timestamp()),
	('order_type','临停缴费','11','订单主表--订单类型','1','admin',current_timestamp()),
	('order_type','我要代泊','12','订单主表--订单类型','1','admin',current_timestamp()),
	('order_type','月租','13','订单主表--订单类型','1','admin',current_timestamp()),
	('order_type','产权','14','订单主表--订单类型','1','admin',current_timestamp()),
	('order_type','加油卡充值','15','订单主表--订单类型','1','admin',current_timestamp());

-- 2016-2-2
create table T_PAYMENT_INFO
(
   ID                   int(10) not null auto_increment,
   ORDER_ID             varchar(50) not null comment '订单主表ID(uuid)',
   PAY_TYPE             char(2) comment '支付类型 00:支付宝，01:微信，02:银联',
   USE_TYPE             char(1) comment '1支付请求，2支付回调',
   USE_INFO             varchar(2000) comment '支付请求信息或回调信息',
   CREATEOR             varchar(30) not null comment '创建人',
   CREATE_DATE          datetime not null comment '创建日期',
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table T_PAYMENT_INFO comment '支付请求回调信息表';

insert into T_DICTIONARY (DICT_CODE,DICT_NAME,DICT_VALUE,MEMO,IS_USED,CREATEOR,CREATE_DATE) values 
	('coupon_kind','通用券','0','优惠券表--优惠券种类','1','admin',current_timestamp()),
	('coupon_kind','停车券','1','优惠券表--优惠券种类','1','admin',current_timestamp()),
	('coupon_kind','月租产权券','2','优惠券表--优惠券种类','1','admin',current_timestamp()),
	('coupon_kind','代泊券','3','优惠券表--优惠券种类','1','admin',current_timestamp());
	

insert into T_DICTIONARY (DICT_CODE,DICT_NAME,DICT_VALUE,MEMO,IS_USED,CREATEOR,CREATE_DATE) values 
	('channel','注册','1','优惠券表--发放理由','1','admin',current_timestamp()),
	('channel','交易','2','优惠券表--发放理由','1','admin',current_timestamp()),
	('channel','活动','3','优惠券表--发放理由','1','admin',current_timestamp()),
	('channel','分享','4','优惠券表--发放理由','1','admin',current_timestamp());	
	
create table T_COOPERATION
(
   ID                   int(10) not null auto_increment,
   NAME                 varchar(255) comment '企业名称',
   MOBILE               varchar(20) comment '联系电脑',
   LINK_MAN             varchar(30) comment '企业联系人',
   NET_ADDRESS          varchar(255) comment '企业网址',
   IMG_URL              varchar(255) comment '企业图标',
   CREATEOR             varchar(30) not null comment '创建人',
   CREATE_DATE          datetime not null comment '创建日期',
   primary key (ID)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
alter table T_COOPERATION comment '合作企业信息表';

insert into T_COOPERATION (NAME,IMG_URL,CREATEOR,CREATE_DATE) values 
	('上房物业','http://139.196.24.16/share/uploadFiles/images/cooperation/logo1.png','admin',current_timestamp()),
	('尚安停车','http://139.196.24.16/share/uploadFiles/images/cooperation/logo2.png','admin',current_timestamp());
insert into T_COOPERATION (NAME,IMG_URL,CREATEOR,CREATE_DATE) values 
	('上房物业','http://www.p-share.com/share/uploadFiles/images/cooperation/logo1.png','admin',current_timestamp()),
	('尚安停车','http://www.p-share.com/share/uploadFiles/images/cooperation/logo2.png','admin',current_timestamp());
	
alter table t_coupon add column coupon_kind varchar(2)  not null COMMENT '优惠券种类';

update  t_monthlyparkinginfo t set t.phone='' where t.phone='不明';
update  t_propertyparkinginfo t set t.phone='' where t.phone='不明';

INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,parent_code,is_leaf) VALUES ('1005','订单管理','main_47.gif','1','','4','10','0');
INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,parent_code,is_leaf) VALUES ('1005001','订单信息','left.gif','2','products/order/list.html','1','1005','1');
INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,parent_code,is_leaf) VALUES ('1005002','添加订单','left.gif','2','products/order/add.html','1','1005','1');
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1005',   '超级管理员','1','1');
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1005001','超级管理员','1','1');	
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1005002','超级管理员','1','1');	

-- 订单管理
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1005','市场总监','9','1');
-- 订单信息
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1005001','市场总监','9','1');
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1005002','市场总监','9','1');
-- 产品管理
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1003','市场总监','9','1');
-- 轮播活动
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1003003','市场总监','9','1');
-- 服务信息
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1003004','市场总监','9','1');
-- 车场服务费
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1003005','市场总监','9','1');
-- 优惠券
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1003006','市场总监','9','1');
-- 意见反馈
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1003007','市场总监','9','1');
-- 系统管理
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1004','市场总监','9','1');
-- 短信推送
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1004005','市场总监','9','1');
-- 广播消息
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1004006','市场总监','9','1');


-- 订单管理
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1005','小区管理员','2','1');
-- 订单信息
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1005001','小区管理员','2','1');
-- 添加订单
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1005002','小区管理员','2','1');

-- 市场部
-- 订单管理
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1005','用户运营经理','7','1');
-- 订单信息
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1005001','用户运营经理','7','1');
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1005002','用户运营经理','7','1');

-- 运营部
-- 订单管理 
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1005','运营助理','8','1');
-- 订单信息
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1005001','运营助理','8','1');
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1005002','运营助理','8','1');

drop table if exists T_APP_VERSION;
create table T_APP_VERSION
(
   ID                   int(10) not null auto_increment,
   PLATFORM_CODE        varchar(10) not null comment '平台',
   VERSION_CODE         varchar(10) not null comment '版本号',
   VERSION_CHANNEL      varchar(10) comment '渠道',
   NOTICE               varchar(256) comment '更新提示内容',
   IS_NEEDED            char(1) not null default '0' comment '是否强制更新（1，是；0，否）',
   UPDATE_URL           varchar(255) comment '更新地址',
   IS_USED              char(1) not null default '1' comment '是否可用',
   CREATEOR             varchar(30) not null comment '创建人',
   CREATE_DATE          datetime not null comment '创建日期',
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table T_APP_VERSION comment 'APP版本信息表';

insert into T_DICTIONARY (DICT_CODE,DICT_NAME,DICT_VALUE,MEMO,IS_USED,CREATEOR,CREATE_DATE) values 
	('reg_channel','offxm001','offxm001','客户信息表--注册渠道','1','admin',current_timestamp()),
	('reg_channel','offxm002','offxm002','客户信息表--注册渠道','1','admin',current_timestamp()),
	('reg_channel','offxm003','offxm003','客户信息表--注册渠道','1','admin',current_timestamp()),
	('reg_channel','offxm004','offxm004','客户信息表--注册渠道','1','admin',current_timestamp());
insert into T_DICTIONARY (DICT_CODE,DICT_NAME,DICT_VALUE,MEMO,IS_USED,CREATEOR,CREATE_DATE) values 
	('platform_code','ios','ios','APP版本信息表--ios','1','admin',current_timestamp()),
	('platform_code','andirod','andirod','APP版本信息表--andirod','1','admin',current_timestamp());	
	
insert into T_DICTIONARY (DICT_CODE,DICT_NAME,DICT_VALUE,MEMO,IS_USED,CREATEOR,CREATE_DATE) values 
	('customer_identity','普通会员','0','客户信息表--客户身份','1','admin',current_timestamp()),
	('customer_identity','VIP会员','1','客户信息表--客户身份','1','admin',current_timestamp()),
	('coupon_kind','车管家券','4','优惠券表--优惠券种类','1','admin',current_timestamp()),
	('survey_type','取消代泊','10','问卷调查表--问卷调查类型','1','admin',current_timestamp());

	
drop table if exists T_QUESTIONNAIRE_INFO;
create table T_QUESTIONNAIRE_INFO
(
   ID                   int(10) not null auto_increment,
   SURVEY_TYPE          char(2) not null comment '类型：10-取消代泊问卷反馈',
   FLAG                 char(1) not null default '1' comment '0-输入，1-非输入',
   CONTENT              varchar(256) not null comment '问卷内容',
   CREATEOR             varchar(30) not null comment '创建人',
   CREATE_DATE          datetime not null comment '创建日期',
   MODIFIED             varchar(30) comment '修改人',
   MODIFY_DATE          datetime comment '修改日期',
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table T_QUESTIONNAIRE_INFO comment '问卷信息表';
insert into T_QUESTIONNAIRE_INFO (SURVEY_TYPE,FLAG,CONTENT,CREATEOR,CREATE_DATE) values 
	('10','1','我临时有事不需要停车了','admin',current_timestamp()),
	('10','1','好巧有车位，我停上车了','admin',current_timestamp()),
	('10','1','不小心点错了，不好意思啊','admin',current_timestamp()),
	('10','1','还没人接单，不等了','admin',current_timestamp()),
	('10','0','都不是，偷偷的告诉我们吧！（30字以内）','admin',current_timestamp());
	

drop table if exists T_QUESTIONNAIRE_SURVEY;
create table T_QUESTIONNAIRE_SURVEY
(
   ID                   int(10) not null auto_increment,
   CUSTOMER_ID          varchar(50) not null comment '开票申请人',
   QUEST_CODE           varchar(30) not null comment '问卷调查ID',
   SURVEY_TYPE          char(2) not null comment '类型：10-取消代泊问卷反馈',
   CONTENT              varchar(256) comment '问卷内容',
   CREATEOR             varchar(30) not null comment '创建人',
   CREATE_DATE          datetime not null comment '创建日期',
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table T_QUESTIONNAIRE_SURVEY comment '问卷调查表';	


drop table if exists T_ORDER_REFUEL;
create table T_ORDER_REFUEL
(
   ID                   int(10) not null auto_increment,
   ORDER_ID             varchar(50) not null comment '订单ID(uuid)',
   CARD_ID              int(10) comment '加油卡id',
   TOPUP_TYPE           varchar(5) comment '充值类型10000(中石化50元加油卡)10001(中石化100元加油卡)
            10003(中石化500元加油卡)10004(中石化1000元加油卡)
            10007(中石化任意金额充值)10008(中石油任意金额充值)',
   TOPUP_NUM            int(10) comment '充值数量',
   TOPUP_PRICE          int(10) comment '充值单价',
   ORDER_CASH           int(10) not null comment '进货价格',
   TOPUP_STATUS         char(1) not null comment '充值状态0充值中 1成功 9撤销',
   TOPUP_NAME           varchar(50) not null comment '充值名称',
   SPORDER_ID           varchar(50) not null comment '商家订单号',
   IS_USED              char(1) not null comment '是否可用',
   CREATEOR             varchar(50) not null comment '创建人',
   MODIFIED             varchar(50) comment '修改人',
   CREATE_DATE          datetime not null comment '创建日期',
   MODIFY_DATE          datetime comment '修改日期',
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table T_ORDER_REFUEL comment '加油卡订单表';
	
insert into T_DICTIONARY (DICT_CODE,DICT_NAME,DICT_VALUE,MEMO,IS_USED,CREATEOR,CREATE_DATE) values
	('refuelcard_type','中石化','1','加油卡信息表--加油卡类型','1','admin',current_timestamp()),
	('refuelcard_type','中石油','2','加油卡信息表--加油卡类型','1','admin',current_timestamp()),
	('topup_type','中石化50元加油卡','10000','加油卡订单表--充值类型','1','admin',current_timestamp()),
	('topup_type','中石化100元加油卡','10001','加油卡订单表--充值类型','1','admin',current_timestamp()),
	('topup_type','中石化500元加油卡','10003','加油卡订单表--充值类型','1','admin',current_timestamp()),
	('topup_type','中石化1000元加油卡','10004','加油卡订单表--充值类型','1','admin',current_timestamp()),
	('topup_type','中石化任意金额充值','10007','加油卡订单表--充值类型','1','admin',current_timestamp()),
	('topup_type','中石油任意金额充值','10008','加油卡订单表--充值类型','1','admin',current_timestamp());

insert into T_DICTIONARY (DICT_CODE,DICT_NAME,DICT_VALUE,MEMO,IS_USED,CREATEOR,CREATE_DATE) values
	('topup_status','充值中','0','加油卡订单表--充值状态','1','admin',current_timestamp()),
	('topup_status','成功','1','加油卡订单表--充值状态','1','admin',current_timestamp()),
	('topup_status','撤销','9','加油卡订单表--充值状态','1','admin',current_timestamp());

drop table if exists T_CARLIFE_REFUEL_CARD;
create table T_CARLIFE_REFUEL_CARD
(
   ID                   int(10) not null auto_increment,
   CUSTOMER_ID          varchar(50) not null comment '客户ID',
   CARD_NO              varchar(30) comment '充值卡号，中石化：以100011开头的卡号、中石油：以9开头的卡号',
   CARD_NAME            varchar(50) comment '持卡人名称',
   CARD_MOBILE          varchar(20) comment '加油卡手机号',
   CARD_TYPE            char(1) default '1' comment '加油卡类型（1:中石化、2:中石油；默认为1)',
   IS_USED              char(1) not null comment '是否可用',
   CREATEOR             varchar(30) not null comment '创建人',
   CREATE_DATE          datetime not null comment '创建日期',
   MODIFIED             varchar(30) comment '修改人',
   MODIFY_DATE          datetime comment '修改日期',
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table T_CARLIFE_REFUEL_CARD comment '加油卡信息表';

drop table if exists T_PARKING_VOUCHER;
create table T_PARKING_VOUCHER
(
   ID                   int not null auto_increment,
   CUSTOMER_ID          varchar(50) not null comment '用户ID',
   PARKING_ID           varchar(50) not null comment '停车场ID',
   CAR_NUMBER           varchar(50) not NULL comment '车牌号',
   IS_USED              char(1) not null comment '是否可用',
   CREATEOR             varchar(30) comment '创建人',
   CREATE_DATE          datetime not null comment '创建日期',
   primary key (ID)
);
alter table T_PARKING_VOUCHER comment '停车凭证表';

update t_menu set menu_name='系统管理' where menu_code='1004';


INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,parent_code,is_leaf) VALUES 
	('1003008','月租车位信息','left.gif','2','products/parking/monthly/list.html','8','1003','1');
INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,parent_code,is_leaf) VALUES 
	('1003009','产权车位信息','left.gif','2','products/parking/equity/list.html','9','1003','1');
INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,parent_code,is_leaf) VALUES 
	('1006','报表管理','main_47.gif','1','','3','10','0');
INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,parent_code,is_leaf) VALUES 
	('1006001','渠道统计','left.gif','2','reports/channel/list.html','1','1006','1');
INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,parent_code,is_leaf) VALUES 
	('1004007','版本控制','left.gif','2','system/appversion/list.html','7','1004','1');
INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,parent_code,is_leaf) VALUES 
	('1003010','小区车场管理','left.gif','2','products/parking/list.html','10','1003','1');
INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,parent_code,is_leaf) VALUES 
	('1005003','月租产权订单','left.gif','2','products/order/listWithCarNumber.html','3','1005','1');

INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES 
	('1003008','超级管理员','1','1'), -- 月租车位信息
	('1003009','超级管理员','1','1'), -- 产权车位信息
	('1006',   '超级管理员','1','1'), -- 报表管理
	('1006001','超级管理员','1','1'),	 -- 渠道统计
	('1004007','超级管理员','1','1'), -- 版本控制	
	('1003010','超级管理员','1','1'), -- 小区车场管理
	('1005003','超级管理员','1','1'); -- 月租产权订单

-- 产品管理
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES ('1003','小区管理员','2','1');
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES 
	('1005003','小区管理员','2','1'), -- 月租产权订单
	('1003008','小区管理员','2','1'), -- 月租车位信息
	('1003009','小区管理员','2','1'); -- 产权车位信息
-- 订单信息
delete from T_MENU_ROLE where menu_code ='1005001' and role_name = '小区管理员';
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES 
	('1005002','小区管理员','2','1');

alter table t_customer add column last_login_time datetime COMMENT '最后登录时间'; 
alter table t_customer add column channel varchar(12) COMMENT '渠道';	
alter table t_customer add column identity char(1) not null default '0' COMMENT '用户身份，0-普通会员，1-VIP会员'; 
ALTER table t_customer add column wx_id VARCHAR(50) COMMENT '快捷登录ID';

alter table t_monthlyparkinginfo add max_date datetime comment '最晚缴费时间';
alter table t_propertyparkinginfo add max_date datetime comment '最晚缴费时间';

ALTER TABLE t_parking ADD vipSharePrice double(18,4) DEFAULT NULL COMMENT 'vip共享价格';
ALTER TABLE t_parking ADD vipStartTime varchar(20) DEFAULT NULL COMMENT 'vip共享开始时段';
ALTER TABLE t_parking ADD vipStopTime varchar(20) DEFAULT NULL COMMENT 'vip共享结束时段';
ALTER TABLE t_parking ADD sharePriceComment varchar(100) DEFAULT NULL COMMENT '共享优惠描述';
ALTER TABLE t_parking ADD vipSharePriceComment varchar(100) DEFAULT NULL COMMENT 'vip共享优惠描述';

-- 添加员工
update t_menu set menu_code='1002003',parent_code='1002',sort=3 where menu_code='1004001';
-- 添加部门
update t_menu set menu_code='1002004',parent_code='1002',sort=4 where menu_code='1004002';
-- 角色信息
update t_menu set menu_code='1002005',parent_code='1002',sort=5 where menu_code='1004003';
update t_menu_role set menu_code='1002003' where menu_code='1004001';
update t_menu_role set menu_code='1002004' where menu_code='1004002';
update t_menu_role set menu_code='1002005' where menu_code='1004003';

alter table t_order add column coupon_dicount decimal(10,2); 


ALTER table t_monthlyparkinginfo add online_payment char(1) DEFAULT '0' COMMENT '是否开通线上支付,0不开通1开通';
ALTER table t_propertyparkinginfo add online_payment char(1) DEFAULT '0' COMMENT '是否开通线上支付,0不开通1开通';
ALTER TABLE `t_parking` ADD COLUMN `is_charge`  int(1) NOT NULL DEFAULT 0 COMMENT '0：不可充电 1：可以充电' AFTER `is_index`;
ALTER table t_parking_voucher add PV_STATUS char(1) DEFAULT '0' COMMENT '凭证状态,0未使用1已使用2已取消';

select REPLACE (parking_path,'http://139.196.12.103/ShanganParkingWeb/uploadFiles','/uploadFiles/images')
 from t_parking ;
update t_parking set parking_path=REPLACE(parking_path,'http://139.196.12.103/ShanganParkingWeb/uploadFiles','/uploadFiles/images')


INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES 
	('1003008','用户运营经理','7','1'), -- 月租车位信息
	('1003009','用户运营经理','7','1'), -- 产权车位信息
	('1003010','用户运营经理','7','1'), -- 小区车场管理
	('1005003','用户运营经理','7','1'); -- 月租产权订单
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES 
	('1003008','运营助理','8','1'), -- 月租车位信息
	('1003009','运营助理','8','1'), -- 产权车位信息
	('1003010','运营助理','8','1'), -- 小区车场管理
	('1005003','运营助理','8','1'); -- 月租产权订单

ALTER TABLE t_carlife_srv_info ADD COLUMN sort  varchar(10) NOT NULL AFTER MODIFY_DATE;	
ALTER TABLE t_carlife_srv_info ADD COLUMN FLAG  int(1) NOT NULL DEFAULT 0 COMMENT '服务标签0：无 1：NEW 2:HOT' AFTER SRV_LINK;
ALTER TABLE t_customer ADD sina_id  varchar(50) DEFAULT NULL COMMENT '新浪ID';
ALTER TABLE t_customer ADD qq_id  varchar(50) DEFAULT NULL COMMENT 'qqID';

INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,parent_code,is_leaf) VALUES 
	('1005004','共享临停订单','left.gif','2','products/order/listTempShare.html','4','1005','1'),
	('1005005','临停缴费订单','left.gif','2','products/order/listTemp.html','5','1005','1'),
	('1005006','油卡充值订单','left.gif','2','products/order/listCarlife.html','6','1005','1');
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES 
	('1005004','超级管理员','1','1'), -- 共享临停订单
	('1005005','超级管理员','1','1'), -- 临停缴费订单
	('1005006','超级管理员','1','1'); -- 油卡充值订单
INSERT INTO T_MENU_ROLE (menu_code,role_name,role_power,is_used) VALUES 
	('1005004','用户运营经理','7','1'), -- 共享临停订单
	('1005005','用户运营经理','7','1'), -- 临停缴费订单
	('1005006','用户运营经理','7','1'); -- 油卡充值订单	
	
	
ALTER table t_coupon add creator VARCHAR(50) DEFAULT NULL COMMENT '优惠券发放人';
ALTER table t_coupon add create_time VARCHAR(30) DEFAULT NULL COMMENT '优惠券发放时间';

CREATE TABLE `t_car_rent` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) DEFAULT NULL COMMENT '名称',
  `PRICE` int(11) DEFAULT '0' COMMENT '价格 单位：分',
  `TYPE` tinyint(4) DEFAULT '1' COMMENT '租聘类型，1：常规租车，2：按小时租车',
  `IMAGE_PATH` varchar(500) NOT NULL COMMENT '图片路径',
  `JUMP_URL` varchar(500) DEFAULT NULL COMMENT '跳转链接',
  `IS_USED` char(1) NOT NULL COMMENT '是否可用',
  `CREATEOR` varchar(30) NOT NULL COMMENT '创建人',
  `MODIFIED` varchar(30) DEFAULT NULL COMMENT '修改人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `MODIFY_DATE` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租车表';

INSERT INTO `t_dictionary`(
t_dictionary.DICT_CODE,t_dictionary.DICT_NAME,t_dictionary.DICT_VALUE,t_dictionary.MEMO,t_dictionary.IS_USED,t_dictionary.CREATEOR,t_dictionary.MODIFIED,t_dictionary.CREATE_DATE,t_dictionary.MODIFY_DATE
) VALUES ('car_rent_title', '常规租车标题', '1', '为方便居民出行，口袋停特联合知名租车公司推出，社区租车服务，足不出户，立马租车', '1', 'admin', null, '2016-03-22 13:18:55', null);
INSERT INTO `t_dictionary`(
t_dictionary.DICT_CODE,t_dictionary.DICT_NAME,t_dictionary.DICT_VALUE,t_dictionary.MEMO,t_dictionary.IS_USED,t_dictionary.CREATEOR,t_dictionary.MODIFIED,t_dictionary.CREATE_DATE,t_dictionary.MODIFY_DATE
) VALUES ('car_rent_title', '分时租车标题', '2', '为方便居民出行，口袋停特联合知名租车公司推出，社区租车服务，足不出户，立马租车', '1', 'admin', null, '2016-03-22 13:18:55', null);

INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,parent_code,is_leaf) VALUES 
	('1004008','白名单同步','left.gif','2','system/whitesyn/list.html','8','1004','1'),
	('1003011','租车管理','left.gif','2','products/carlife/carRent/list.html','11','1003','1'),
	('1003012','充值规则','left.gif','2','products/rule/list.html','12','1003','1'),
	('1005007','钱包充值','left.gif','2','products/order/rechargelist.html','7','1005','1');

create table T_WHITESYN_RECORD (
   ID                   int(10) not null auto_increment,
   PARKING_INFO         varchar(2000) comment '同步的车场json信息',
   CALLBACK_INFO        varchar(2000) comment '白名单同步返回信息',
   IS_USED              char(1) not null comment '是否可用0：不可用 1：可用',
   CREATEOR             varchar(30) not null comment '创建人',
   CREATE_DATE          datetime not null comment '创建日期',
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='白名单同步记录表';

ALTER TABLE `t_customer` ADD COLUMN `money`  int NOT NULL DEFAULT 0 COMMENT '钱包余额，单位 分' AFTER `qq_id`;
ALTER TABLE t_customer ADD COLUMN pay_password  int(6) NULL AFTER money;
ALTER TABLE `t_customer` MODIFY COLUMN `pay_password`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `money`;
INSERT INTO `t_dictionary` (t_dictionary.DICT_CODE,t_dictionary.DICT_NAME,
t_dictionary.DICT_VALUE,t_dictionary.MEMO,t_dictionary.IS_USED,t_dictionary.CREATEOR,t_dictionary.MODIFIED,t_dictionary.CREATE_DATE,t_dictionary.MODIFY_DATE)
VALUES ('order_type', '钱包充值', '16', '订单主表--订单类型', '1', 'admin', null, '2016-03-24 17:23:37', null);
ALTER TABLE t_order ADD COLUMN pay_type char(2)  COMMENT '支付类型 00:支付宝，01:微信，02:银联，05:钱包支付' AFTER order_type;

CREATE TABLE `T_RECHARGE_RULE` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `IMAGE_PATH` varchar(500) NOT NULL COMMENT '图片路径',
  `BEGIN_DATE` datetime NOT NULL COMMENT '开始时间',
  `END_DATE` datetime DEFAULT NULL COMMENT '结束时间',
  `IS_USED` char(1) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `CREATEOR` varchar(30) NOT NULL COMMENT '创建人',
  `MODIFIED` varchar(30) DEFAULT NULL COMMENT '修改人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `MODIFY_DATE` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值规则表';


CREATE TABLE `T_ORDER_RECHARGE` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` varchar(50) NOT NULL COMMENT '订单主表ID(uuid)',
  `GIFT_AMOUNT` int(10) NOT NULL DEFAULT '0' COMMENT '赠送金额，到分，没有小数点',
  `IS_USED` char(1) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `CREATEOR` varchar(30) NOT NULL COMMENT '创建人',
  `MODIFIED` varchar(30) DEFAULT NULL COMMENT '修改人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `MODIFY_DATE` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='钱包充值订单表';

CREATE TABLE `t_recharge_rule_gift_amount` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RULE_ID` int(11) NOT NULL COMMENT '充值规则表的id',
  `AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '充值金额达到多少 单位 分',
  `GIFT_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '赠送的金额 单位 分',
  `IS_USED` char(1) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `CREATEOR` varchar(30) NOT NULL COMMENT '创建人',
  `MODIFIED` varchar(30) DEFAULT NULL COMMENT '修改人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `MODIFY_DATE` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='赠送金额的充值规则表';

alter table t_whitesyn_record modify COLUMN parking_info text DEFAULT NULL COMMENT '同步的车场json信息';

-- 20160415 v1.3.6
CREATE TABLE `t_order_carwash` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `PARKING_ID` varchar(50) NOT NULL COMMENT '车场ID',
  `ORDER_ID` varchar(50) NOT NULL COMMENT '订单主表ID(uuid)',
  `RESERVE_DATE` datetime DEFAULT NULL,
  `CAR_TYPE` varchar(10) DEFAULT NULL,
  `CAR_NUMBER` varchar(30) DEFAULT NULL COMMENT '车牌号',
  `IS_USED` char(1) NOT NULL COMMENT '是否可用',
  `CREATEOR` varchar(30) NOT NULL COMMENT '创建人',
  `MODIFIED` varchar(30) DEFAULT NULL COMMENT '修改人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `MODIFY_DATE` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=685 DEFAULT CHARSET=utf8 COMMENT='洗车订单表';

CREATE TABLE `t_red_dot` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE` tinyint(4) NOT NULL COMMENT '类型：1：优惠券 ',
  `CUSTOMER_ID` varchar(50) NOT NULL COMMENT '用户id',
  `NEW_COUNT` int(11) NOT NULL DEFAULT '0' COMMENT '记录有多少条新数据未浏览',
  `IS_USED` char(1) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `CREATEOR` varchar(30) NOT NULL COMMENT '创建人',
  `MODIFIED` varchar(30) DEFAULT NULL COMMENT '修改人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `MODIFY_DATE` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小红点表';

CREATE TABLE `t_coupon_rule` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BLUE_PARKING_ID` varchar(50) NOT NULL DEFAULT '' COMMENT '蓝卡云车场ID',
  `PARKING_ID` varchar(50) NOT NULL DEFAULT '' COMMENT '车场ID',
  `PARKING_NAME` varchar(50) NOT NULL DEFAULT '' COMMENT '车场名称',
  `SCENE_MODE` char(1) NOT NULL COMMENT '场景模式：1：入场 2：出场',
  `USER_TYPE` char(1) NOT NULL COMMENT '赠送的用户类型：1：月租',
  `PUSH_MODE` char(1) NOT NULL DEFAULT '0' COMMENT '推送方式：0：不推送 1：短信 2：APP极光推送',
  `BEGIN_DATE` datetime NOT NULL COMMENT '开始时间',
  `END_DATE` datetime NOT NULL COMMENT '结束时间',
  `PUSH_CONTENT` varchar(200) NOT NULL DEFAULT '' COMMENT '推送内容',
  `IS_USED` char(1) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `CREATEOR` varchar(30) NOT NULL COMMENT '创建人',
  `MODIFIED` varchar(30) DEFAULT NULL COMMENT '修改人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `MODIFY_DATE` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券赠送规则表';

CREATE TABLE `t_coupon_template` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE` char(1) NOT NULL COMMENT '类型，1：车辆入场出场时送的优惠券 2：兑换码搞出来的优惠券',
  `TYPE_ID` int(11) NOT NULL COMMENT '外键，TYPE为1时，使用t_coupon_rule表的ID；TYPE为2时，使用t_redeem_rule表的ID',
  `COUPON_KIND` char(1) NOT NULL COMMENT '优惠券类型 0：通用卷 1：停车卷 2：月租产权劵 3：代泊券 4：车管家券',
  `COUPON_NAME` varchar(50) NOT NULL DEFAULT '' COMMENT '优惠券名称',
  `COUPON_TYPE` char(1) DEFAULT NULL COMMENT '优惠券类型：1面值，2折扣',
  `PAR_AMOUNT` decimal(10,2) DEFAULT NULL COMMENT '面值(元)',
  `PAR_DISCOUNT` decimal(10,2) DEFAULT NULL COMMENT '面值(折扣)',
  `MINCONSUMPTION` decimal(10,2) DEFAULT NULL COMMENT '最低消费',
  `EFFECTIVE_TYPE` char(1) NOT NULL COMMENT '有效类型 1：使用开始结束时间 2：使用有效天数',
  `EFFECTIVE_BEGIN` datetime DEFAULT NULL COMMENT '有效开始时间',
  `EFFECTIVE_END` datetime DEFAULT NULL COMMENT '有效结束时间',
  `EFFECTIVE_DAY` int(11) DEFAULT NULL COMMENT '自领券起有效期天数',
  `EXCLUSION_RULE` varchar(250) DEFAULT '' COMMENT '互斥规则',
  `INFO` varchar(250) DEFAULT '' COMMENT '备注说明',
  `IS_USED` char(1) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `CREATEOR` varchar(30) NOT NULL COMMENT '创建人',
  `MODIFIED` varchar(30) DEFAULT NULL COMMENT '修改人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `MODIFY_DATE` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券赠送模板表';

CREATE TABLE `t_redeem_rule` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE` char(1) NOT NULL COMMENT '类型，1：新注册用户使用兑换码获取的优惠券 2：老用户兑换码被兑换一定次数后可领取的优惠券',
  `REDEEM_COUNT` int(11) DEFAULT NULL COMMENT 'TYPE为2时，此字段才有效，老用户领取优惠券需要达到的兑换次数',
  `IS_USED` char(1) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `CREATEOR` varchar(30) NOT NULL COMMENT '创建人',
  `MODIFIED` varchar(30) DEFAULT NULL COMMENT '修改人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `MODIFY_DATE` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='兑换码兑换规则表';

CREATE TABLE `t_redeem_record` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `REDEEM_RULE_ID` int(11) NOT NULL COMMENT '外键，对应t_redeem_rule表的ID，代表你领取了哪条规则下的优惠券',
  `TYPE` char(1) NOT NULL COMMENT '类型，1：新注册用户使用兑换码获取的优惠券 2：老用户兑换码被兑换一定次数后可领取的优惠券',
  `OLD_CUSTOMER_ID` varchar(50) DEFAULT NULL COMMENT '被领取者的用户ID',
  `NEW_CUSTOMER_ID` varchar(50) NOT NULL COMMENT '领取者的用户ID',
  `IS_USED` char(1) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `CREATEOR` varchar(30) NOT NULL COMMENT '创建人',
  `MODIFIED` varchar(30) DEFAULT NULL COMMENT '修改人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `MODIFY_DATE` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`ID`),
  KEY `REDEEM_RULE_ID` (`REDEEM_RULE_ID`),
  CONSTRAINT `t_redeem_record_ibfk_1` FOREIGN KEY (`REDEEM_RULE_ID`) REFERENCES `t_redeem_rule` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='兑换码兑换记录表';

ALTER TABLE t_carlife_srv_billing ADD COLUMN NOWPRICE  int(10) NULL AFTER SRV_PRICE;
ALTER TABLE `t_carlife_srv_info` ADD COLUMN `maxCount`  varchar(50) NULL DEFAULT NULL AFTER `sort`;
ALTER TABLE `t_car` ADD COLUMN `is_auto_pay`  char(1) NOT NULL DEFAULT 0 COMMENT '是否开启了钱包自动支付，0：否 1：是' AFTER `owner_id_number`;
ALTER TABLE `t_customer` ADD COLUMN `redeem_code` varchar(20) UNIQUE NULL COMMENT '兑换码，唯一' AFTER `reg_phone`;
ALTER table t_propertyparkinginfo add MAKEUP VARCHAR(100)  COMMENT '发票抬头';
ALTER table t_monthlyparkinginfo add MAKEUP VARCHAR(100)  COMMENT '发票抬头';

INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,memo,parent_code,is_leaf) VALUES 
('1001','客户相关','main_47.gif','1','','3','','10','0'),
('1001001','客户信息','left.gif','2','customer/info/list.html','1','','1001','1'),
('1005008','洗车订单','left.gif','2','products/order/carwashList.html','8','','1005','1'),
('1003014','特殊车位信息','left.gif','2','products/parking/specialCar/list.html','14','','1003','1'),
('1003013','兑换码规则','left.gif','2','products/redeemRule/list.html','13','','1003','1');

drop table if exists T_INVOICE;

/*==============================================================*/
/* Table: T_INVOICE                                             */
/*==============================================================*/
create table T_INVOICE
(
   ID                   varchar(50) not null,
   ORDER_ID             varchar(50) comment '订单ID',
   CUSTOMER_ID          varchar(50) comment '用户ID',
   CAR_NUMBER           varchar(50) comment '车牌号',
   INVOICE_NAME         varchar(500) comment '发票抬头',
   SEND_TYPE            char(1) comment '寄送方式 0:自取1:寄送上门',
   SEND_ADDRESS         varchar(500) comment '寄送地址',
   IS_DEFAULT           char(1) comment '是否默认 0:非默认 1:默认',
   IS_USED              char(1) not null comment '是否可用',
   CREATEOR             varchar(30) not null comment '创建人',
   MODIFIED             varchar(30) comment '修改人',
   CREATE_DATE          datetime not null comment '创建日期',
   MODIFY_DATE          datetime comment '修改日期',
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发票记录表';

ALTER TABLE t_parking ADD COLUMN invoice_describe  varchar(500) COMMENT '发票说明' ;
ALTER TABLE t_order_main ADD COLUMN IS_NEED_INVOICE  CHAR(1) DEFAULT '0' COMMENT '是否需要开具发票0：不需要1：需要' AFTER INVOICE_STATUS;
alter table t_customer drop primary key;
alter table t_customer add primary key(customer_id);
ALTER table t_customer MODIFY COLUMN customer_mobile varchar(20) DEFAULT NULL COMMENT '手机'

INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,memo,parent_code,is_leaf) VALUES 
('1003015','场景推送规则','left.gif','2','/products/scenePush/list.html','15','','1003','1');
-- 优惠停车
ALTER table t_order_temporary_share add PARKING_NUM VARCHAR(10) DEFAULT '0' COMMENT '优惠停车次数';
ALTER table t_order_temporary_share add APPOINTMENT_DATE VARCHAR(100)  COMMENT '预约时间';
ALTER table t_order_temporary_share add VOUCHER_STATUS VARCHAR(1) DEFAULT '0' COMMENT '凭证状态 0 未使用 1 已使用 2 失效';

ALTER TABLE `t_coupon_rule`
MODIFY COLUMN `SCENE_MODE`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '场景模式 多选 逗号隔开：1：入场 2：出场 3：注册' AFTER `PARKING_NAME`,
MODIFY COLUMN `USER_TYPE`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '赠送的用户类型 多选 逗号隔开：1：月租 2：产权' AFTER `SCENE_MODE`,
MODIFY COLUMN `PUSH_MODE`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '推送方式 多选 逗号隔开： 1：短信 2：APP极光推送' AFTER `USER_TYPE`;

CREATE TABLE `t_scene_record` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RULE_ID` int(11) NOT NULL COMMENT '外键，对应t_coupon_rule表的ID，代表你领取了哪条规则下的优惠券',
  `SCENE_MODE` varchar(1) NOT NULL COMMENT '场景模式 1：入场 2：出场 3：注册',
  `CUSTOMER_ID` varchar(100) NOT NULL COMMENT '赠送的用户ID',
  `CAR_NUMBER` varchar(20) NOT NULL COMMENT '车牌号',
  `IS_USED` char(1) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `CREATEOR` varchar(30) NOT NULL COMMENT '创建人',
  `MODIFIED` varchar(30) DEFAULT NULL COMMENT '修改人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `MODIFY_DATE` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`ID`),
  KEY `RULE_ID` (`RULE_ID`),
  CONSTRAINT `t_scene_record_ibfk_1` FOREIGN KEY (`RULE_ID`) REFERENCES `t_coupon_rule` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='场景推送优惠券赠送记录表';

ALTER TABLE t_whitesyn_record MODIFY COLUMN CALLBACK_INFO text DEFAULT NULL COMMENT '白名单同步返回信息';
alter table t_customer add wxpay_openid VARCHAR(50) default null COMMENT '微信支付openid';
-- 用户信息
ALTER table t_user_info add column sys_user_id varchar(32) COMMENT '系统用户表ID';	

INSERT INTO T_MENU (menu_code,menu_name,icon,levels,urls,sort,parent_code,is_leaf) VALUES 
('1004009','短信名单','left.gif','2','system/sendMessage/list.html','9','1004','1'),
('1004002','权限管理','left.gif','2','system/authority/list.html','2','1004','1'),
('1004003','角色管理','left.gif','2','system/roles/list.html','3','1004','1'),
('1002006','车场用户','left.gif','2','users/parkerlist.html','6','1002','1'),
('1003016','车型管理','left.gif','2','products/carModel/list.html','16','1003','1'),
('1004001','资源管理','left.gif','2','system/resources/list.html','1','1004','1');

insert into T_DICTIONARY (DICT_CODE,DICT_NAME,DICT_VALUE,MEMO,IS_USED,CREATEOR,CREATE_DATE) values 
	('resource_type','action','action','资源表--资源类型','1','admin',current_timestamp()),
	('resource_type','url','url','资源表--资源类型','1','admin',current_timestamp());	
	
ALTER table t_parking add PARK_PRICE_COMMENT VARCHAR(100) COMMENT '代客泊车描述';
ALTER table t_parking add MAXIMUM_HOUR VARCHAR(100) COMMENT '单小时最大接单数';
	
CREATE TABLE t_send_message (
  id varchar(16) NOT NULL COMMENT '短信ID',
  parking_id varchar(50) NOT NULL COMMENT '车场ID',
  order_type varchar(50) DEFAULT NULL COMMENT '订单类型',
  person_name char(18) DEFAULT NULL COMMENT '用户ID',
  person_mobile varchar(255) DEFAULT NULL COMMENT '手机号',
  create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  create_user varchar(64) NOT NULL COMMENT '创建者',
  update_time tmestamp  COMMENT '更新时间',
  update_user varchar(64) COMMENT '更新者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信发送名单';
ALTER TABLE t_send_message MODIFY COLUMN id  int(16) NOT NULL AUTO_INCREMENT COMMENT '短信ID' FIRST ;
ALTER TABLE t_send_message MODIFY COLUMN person_name  varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户ID' AFTER order_type;	
-- ALTER TABLE t_user_info ADD CONSTRAINT UQ_USERINFO_USERNAME UNIQUE (user_num,is_used); 

SET FOREIGN_KEY_CHECKS=0;

drop table if exists SYS_AUTHORITIES;
drop table if exists SYS_RESOURCES;
drop table if exists SYS_AUTHORITIES_RESOURCES;
drop table if exists SYS_ROLES;
drop table if exists SYS_ROLES_AUTHORITIES;
drop table if exists SYS_USERS;
drop table if exists SYS_USERS_ROLES;
create table SYS_AUTHORITIES
(
   AUTHORITY_ID         varchar(32) not null,
   AUTHORITY_NAME       varchar(40),
   AUTHORITY_DESC       varchar(100),
   ENABLED              int(1) default 1 comment '是否启用',
   ISSYS                int(1) default 0,
   MODULE               varchar(4) comment '所属系统'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';
create table SYS_ROLES
(
   ROLE_ID              varchar(32),
   ROLE_NAME            varchar(40),
   ROLE_DESC            varchar(100),
   ENABLED              INT(1),
   ISSYS                INT(1),
   MODULE               varchar(4) comment '所属系统'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
create table SYS_ROLES_AUTHORITIES
(
   ID                   integer(10) not null auto_increment,
   ROLE_ID              varchar(32),
   AUTHORITY_ID         varchar(32),
   ENABLED              int(1),
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';
create table SYS_RESOURCES
(
   RESOURCE_ID          VARCHAR(32) not null,
   RESOURCE_NAME        VARCHAR(100),
   RESOURCE_DESC        VARCHAR(100),
   RESOURCE_TYPE        VARCHAR(40) comment 'action|url',
   RESOURCE_STRING      VARCHAR(200) comment '访问路径',
   PRIORITY             int(1),
   ENABLED              int(1) default 1 comment '是否启用',
   ISSYS                int(1) default 0,
   MODULE               VARCHAR(4) comment '所属系统 '
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';
create table SYS_AUTHORITIES_RESOURCES
(
   ID                   int(10) not null auto_increment,
   AUTHORITY_ID         varchar(32),
   RESOURCE_ID          varchar(32),
   ENABLED              int(1),
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限资源表';
create table SYS_USERS
(
   USER_ID              VARCHAR(32) not null,
   USER_ACCOUNT         VARCHAR(30),
   USER_NAME            VARCHAR(40),
   USER_PASSWORD        VARCHAR(100) comment '该密码是经加盐值加密的，格式为password{username}。 比如用户的密码为user，用户名为user，那么通过MD5进行加密的串为： user{user}',
   USER_DESC            VARCHAR(100),
   ENABLED              INT(1),
   ISSYS                INT(1) comment '是否是超级用户',
   USER_DEPT            VARCHAR(20) comment '部门',
   USER_DUTY            VARCHAR(10) comment '职位',
   SUB_SYSTEM           VARCHAR(32) comment '该用户所负责的各子系统，可多个，中间用逗号分隔。'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';  
create table SYS_USERS_ROLES
(
   ID                   integer(10) not null auto_increment,
   USER_ID              varchar(32),
   ROLE_ID              varchar(32),
   ENABLED              int(1),
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';  

alter table SYS_AUTHORITIES  
  add constraint PK_PUB_AUTHORITIES primary key (AUTHORITY_ID);
alter table SYS_RESOURCES  
  add constraint PK_PUB_RESOURCES primary key (RESOURCE_ID);
alter table SYS_AUTHORITIES_RESOURCES  
  add constraint FK_PUB_AUTHORITIES_RE_AU foreign key (AUTHORITY_ID) references SYS_AUTHORITIES (AUTHORITY_ID);  
alter table SYS_AUTHORITIES_RESOURCES  
  add constraint FK_PUB_AUTHORITIES_RE_RE foreign key (RESOURCE_ID)  references SYS_RESOURCES (RESOURCE_ID);
alter table SYS_ROLES  
  add constraint PK_PUB_ROLES primary key (ROLE_ID);
alter table SYS_ROLES_AUTHORITIES  
  add constraint FK_PUB_ROLES_AUTHORITIES_AU foreign key (AUTHORITY_ID)  references SYS_AUTHORITIES (AUTHORITY_ID);  
alter table SYS_ROLES_AUTHORITIES  
  add constraint FK_PUB_ROLES_AUTHORITIES_ROLES foreign key (ROLE_ID)  references SYS_ROLES (ROLE_ID); 
alter table SYS_USERS  
  add constraint PK_PUB_USERS primary key (USER_ID);   
alter table SYS_USERS_ROLES  
  add constraint FK_USERS_ROLES_ROLES foreign key (ROLE_ID)  references SYS_ROLES (ROLE_ID);  
alter table SYS_USERS_ROLES  
  add constraint FK_USERS_ROLES_USERS foreign key (USER_ID)  references SYS_USERS (USER_ID);

-- ALTER TABLE SYS_AUTHORITIES ADD CONSTRAINT UQ_AUTHORITIES_NAME UNIQUE(AUTHORITY_NAME,ENABLED);
-- ALTER TABLE SYS_ROLES ADD CONSTRAINT UQ_ROLES_NAME UNIQUE(ROLE_NAME,ENABLED);
-- ALTER TABLE SYS_RESOURCES ADD CONSTRAINT UQ_RESOURCES_NAME UNIQUE(RESOURCE_NAME,ENABLED);
-- ALTER TABLE SYS_USERS ADD CONSTRAINT UQ_USERS_ACCOUNT UNIQUE(USER_ACCOUNT,ENABLED);

insert into SYS_AUTHORITIES (AUTHORITY_ID, AUTHORITY_NAME, AUTHORITY_DESC, ENABLED, ISSYS, MODULE) values 
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW', '查看', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0001', '轮播活动-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0002', '轮播活动-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0003', '轮播活动-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0004', '轮播活动-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0005', '服务信息-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0006', '服务信息-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0007', '服务信息-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0008', '服务信息-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0009', '车场服务费-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0010', '车场服务费-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0011', '车场服务费-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0012', '车场服务费-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0013', '优惠券-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0014', '优惠券-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EXP_0015', '优惠券-导出', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0016', '月租车辆管理-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0017', '月租车辆管理-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0018', '月租车辆管理-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0019', '月租车辆管理-编辑', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_IMP_0020', '月租车辆管理-导入', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EXP_0021', '月租车辆管理-导出', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0022', '产权车辆管理-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0023', '产权车辆管理-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0024', '产权车辆管理-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0025', '产权车辆管理-编辑', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_IMP_0026', '产权车辆管理-导入', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EXP_0027', '产权车辆管理-导出', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0028', '小区车场管理-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0029', '小区车场管理-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0030', '小区车场管理-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0031', '小区车场管理-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0032', '租车管理-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0033', '租车管理-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0034', '租车管理-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0035', '租车管理-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0036', '充值规则-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0037', '充值规则-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0038', '充值规则-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0039', '充值规则-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0040', '兑换码规则-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0041', '兑换码规则-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0042', '兑换码规则-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0043', '兑换码规则-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0044', '特殊车辆管理-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0045', '特殊车辆管理-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0046', '特殊车辆管理-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0047', '特殊车辆管理-编辑', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_IMP_0048', '特殊车辆管理-导入', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EXP_0049', '特殊车辆管理-导出', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0050', '场景推送规则-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0051', '场景推送规则-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0052', '场景推送规则-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0053', '场景推送规则-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0054', '车型管理-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0055', '订单信息-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EXP_0056', '订单信息-导出', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0057', '添加订单-添加', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0058', '月租产权订单-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EXP_0059', '月租产权订单-导出', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0060', '共享临停订单-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EXP_0061', '共享临停订单-导出', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_CHK_0062', '共享临停订单-验证', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0063', '临停缴费订单-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EXP_0064', '临停缴费订单-导出', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0065', '油卡充值订单-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EXP_0066', '油卡充值订单-导出', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0067', '钱包充值-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EXP_0068', '钱包充值-导出', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0069', '洗车订单-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EXP_0070', '洗车订单-导出', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0071', '客户信息-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EXP_0072', '客户信息-导出', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0073', '渠道统计-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EXP_0074', '渠道统计-导出', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0075', '资源管理-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0076', '资源管理-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0077', '资源管理-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0078', '资源管理-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0079', '权限管理-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0080', '权限管理-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0081', '权限管理-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0082', '权限管理-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0083', '角色管理-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0084', '角色管理-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0085', '角色管理-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0086', '角色管理-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0087', '字典管理-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0088', '字典管理-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0089', '字典管理-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0090', '字典管理-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0091', '广播消息-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0092', '广播消息-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0093', '广播消息-删除', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0094', '短信发送-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0095', '短信发送-推送', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0096', '版本控制-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0097', '版本控制-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0098', '版本控制-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0099', '版本控制-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0100', '白名单同步-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0101', '员工信息-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0102', '员工信息-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0103', '员工信息-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0104', '员工信息-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0105', '部门信息-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0106', '部门信息-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0107', '部门信息-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0108', '部门信息-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0109', '车场用户-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0110', '车场用户-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0111', '车场用户-删除', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0112', '车场用户-编辑', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0113', '意见反馈-查看', 1, 0, null),
--
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_VIEW_0114', '短信名单-查看', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_ADD_0115', '短信名单-增加', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_DEL_0116', '短信名单-删除', 1, 0, null);

insert into SYS_ROLES (ROLE_ID, ROLE_NAME, ROLE_DESC, ENABLED, ISSYS, MODULE) values
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_ROLES'),8,'0')), 'P_ADMIN', '超级管理员', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_ROLES'),8,'0')), 'P_PRINCIPAL', '条线负责人', 1, 0, null),  
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_ROLES'),8,'0')), 'P_OPERATIONS_SUPERVISOR', '运营高级管理', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_ROLES'),8,'0')), 'P_OPERATIONS_MANAGEMENT', '运营基础管理', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_ROLES'),8,'0')), 'P_FINANCE_SUPERVISOR', '财务高级管理', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_ROLES'),8,'0')), 'B_ADMIN', '超级管理员', 1, 0, null),  
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_ROLES'),8,'0')), 'B_PRINCIPAL', '条线负责人', 1, 0, null),  
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_ROLES'),8,'0')), 'B_REGIONAL_MANAGER', '区域经理', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_ROLES'),8,'0')), 'B_REGIONAL_ASSISTANT', '区域助理', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_ROLES'),8,'0')), 'B_PROJECT_MANAGER', '项目经理', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_ROLES'),8,'0')), 'B_PROJECT_ASSISTANT', '项目助理', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_ROLES'),8,'0')), 'B_FINANCE_MANAGEMENT', '财务管理', 1, 0, null); 

-- 授予超级管理员所有权限
insert into SYS_ROLES_AUTHORITIES (ROLE_ID,AUTHORITY_ID,ENABLED) 
	select (select ROLE_ID from SYS_ROLES where ROLE_NAME='P_ADMIN') as ROLE_ID,AUTHORITY_ID,'1' as ENABLED from sys_authorities;

--insert into SYS_RESOURCES (RESOURCE_ID, RESOURCE_NAME, RESOURCE_DESC, RESOURCE_TYPE, RESOURCE_STRING, PRIORITY, ENABLED, ISSYS, MODULE) values  
--('2016042000000001', 'welcome', '登录后欢迎页面', 'action', '/main/index.html', null, 1, 0, null),
--('2016042000000002', 'user_to_edit', '进入编辑用户信息', 'action', '/users/*/userdetail.html', null, 1, 0, null);
insert into SYS_RESOURCES(RESOURCE_ID,RESOURCE_NAME,RESOURCE_DESC,RESOURCE_TYPE,RESOURCE_STRING,enabled)
	select CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_RESOURCES'),8,'0')) as RESOURCE_ID,
 	menu_code as RESOURCE_NAME,menu_name as RESOURCE_DESC,'action' as RESOURCE_TYPE,CONCAT('/',urls) as RESOURCE_STRING,
 	is_used as enabled from t_menu where LEVELs=2 and is_used='1';

 	
 	
 	
 	
 	
ALTER TABLE t_menu_role
MODIFY COLUMN role_power  varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限 ' AFTER `role_name`;

CREATE TABLE t_parking_relevance (
	ID INT (50) NOT NULL AUTO_INCREMENT,
	PARKING_ID VARCHAR (100) DEFAULT NULL COMMENT '车场ID',
	CORRESPONDING_ID VARCHAR (100) DEFAULT NULL COMMENT '对应车场ID',
	PRIMARY KEY (ID)
) ENGINE = INNODB AUTO_INCREMENT = 7 DEFAULT CHARSET = utf8; 
alter table t_parking_relevance comment '代泊车场关联表';	

ALTER TABLE t_order_park
MODIFY COLUMN VALIDATE_IMAGE_PATH  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '验车照片路径（代泊员接车）多张图片用逗号分隔' AFTER PARKER_BACK_ID,
MODIFY COLUMN PARKING_IMAGE_PATH  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '停车照片路径（代泊员停车）多张图片用逗号分隔' AFTER VALIDATE_IMAGE_PATH,
MODIFY COLUMN ORDER_END_DATE  datetime NULL DEFAULT NULL COMMENT '预约取车时间' AFTER ORDER_BEGIN_DATE,
MODIFY COLUMN ACTUAL_BEGIN_DATE  datetime NULL COMMENT '代泊员接车拍完验车照上传完照片开始（用户：交车时间，代泊员：接车时间）' AFTER ORDER_END_DATE,
ADD COLUMN TARGET_PARKING_ID  varchar(50) NOT NULL COMMENT '目标车场Id，车子真正停进去的车场' AFTER PARKING_ID,
ADD COLUMN KEY_BOX  varchar(255) NOT NULL DEFAULT '' COMMENT '钥匙箱' AFTER ACTUAL_END_DATE,
ADD COLUMN BACK_PARK  varchar(255) NOT NULL DEFAULT '' COMMENT '泊回地点' AFTER KEY_BOX;

ALTER TABLE t_order_park ADD COLUMN is_push  varchar(50) NULL COMMENT '是否推送   1 是  0 否' AFTER KEY_BOX;
ALTER TABLE t_order_park 
MODIFY COLUMN is_push  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否推送   1 是  0 否' AFTER KEY_BOX;


alter table t_parker add parker_type char(1) default '0'  COMMENT '代泊员身份0：代泊员,1：代泊车管家';
ALTER TABLE t_parker
ADD COLUMN state  char(1) NOT NULL DEFAULT 0 COMMENT '0:休班  1:当班' AFTER parker_type,
ADD COLUMN last_oper_time  datetime NOT NULL COMMENT '最后操作时间，取车或接车操作' AFTER state,
ADD COLUMN version  int NOT NULL DEFAULT 0 COMMENT '用来防止并发，每次派单时数量+1' AFTER last_oper_time;

ALTER TABLE t_parking
ADD COLUMN MAXIMUM_DAY  int(10) NOT NULL DEFAULT 0 COMMENT '单日最大代泊数' AFTER MAXIMUM_HOUR,
ADD COLUMN PARK_BEGIN_TIME  varchar(20) NULL COMMENT '代泊服务开始时间 例如 8:00' AFTER MAXIMUM_DAY,
ADD COLUMN PARK_END_TIME  varchar(20) NULL COMMENT '代泊服务结束时间 例如 18:00' AFTER PARK_BEGIN_TIME;

create table T_WHITESYN_INFO
(
   ID                   int(10) not null auto_increment,
   NAME                 varchar(30) comment '参数名',
   VALUE                varchar(100) comment '参数值',
   CREATE_DATE          datetime not null comment '创建日期',
   MODIFY_DATE          datetime comment '修改日期',
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table T_WHITESYN_INFO comment '白名单同步参数';
INSERT INTO `t_whitesyn_info` VALUES ('1', 'syn_parking_str', null, '2016-05-10 10:03:42', null);
INSERT INTO `t_whitesyn_info` VALUES ('2', 'syn_time', null, '2016-05-10 10:04:08', null);


-- 月租产权信息表-电话、有效开始时间、有效结束时间
-- ------------------------------------------
insert into SYS_AUTHORITIES (AUTHORITY_ID, AUTHORITY_NAME, AUTHORITY_DESC, ENABLED, ISSYS, MODULE) values 
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0117', '短信名单-编辑', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0118', '月租车辆管理-续费', 1, 0, null),
(CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_AUTHORITIES'),8,'0')), 'AUTH_EDIT_0119', '产权车辆管理-续费', 1, 0, null);




	
update t_menu set is_used='0' where menu_code in ('1002003','1002004','1002005');	
	
	
	
	
	
	
	
	
	
	
	
	


-- 角色配菜单：超级管理员
delete from t_menu_role where role_power = 'P_ADMIN';
insert into t_menu_role(menu_code,role_name,role_power,is_used)
	select menu_code,'超级管理员' as role_name,'P_ADMIN' as role_power,is_used from t_menu_role where role_power=1;
delete from t_menu_role where role_power <> 'P_ADMIN';
-- 初始化用户密码：123456
update t_user_info set user_pw='395c148de4cde3dc57bff8a982e817aa';
insert into sys_users(user_id,user_account,user_name,user_password,enabled,issys)
	select CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),LPAD(nextvalandins('SYS_USERS'),8,'0')) as user_id,
	user_num,user_name,user_pw,is_used as enabled,0 as issys from t_user_info;
UPDATE t_user_info a, sys_users b SET a.sys_user_id = b.USER_ID WHERE a.user_num = b.USER_ACCOUNT;
-- 用户配角色：超级管理员
insert into sys_users_roles (user_id,role_id,enabled) 
	select (select user_id from sys_users where USER_ACCOUNT='admin') as user_id,
	(select role_id from sys_roles where ROLE_NAME='P_ADMIN') as role_id,'1' as enabled from dual;


create table T_INVOICE_APPLY
(
   ID                   int(10) not null auto_increment,
   CUSTOMER_ID          varchar(50) not null comment '开票申请人',
   APPLY_DATE           datetime not null comment '开票申请时间',
   INVOICE_AMOUNT       int(10) not null default 0 comment '发票金额',
   INVOICE_DATE         datetime comment '发票时间',
   DRAWER               varchar(30) comment '开票人',
   IS_USED              char(1) not null comment '是否可用',
   MODIFIED             varchar(30) comment '修改人',
   MODIFY_DATE          datetime comment '修改日期',
   primary key (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table T_INVOICE_APPLY comment '开票申请信息表';
	