package com.boxiang.share.system.quartz;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.boxiang.share.product.order.po.OrderTemporaryShare;
import com.boxiang.share.product.order.service.OrderParkService;
import com.boxiang.share.product.order.service.OrderTemporaryShareService;
import com.boxiang.share.product.parker.po.Parker;
import com.boxiang.share.product.parker.service.ParkerService;
import com.boxiang.share.product.parking.service.CarInOutRecordService;
import com.boxiang.share.product.parking.service.CarInOutRecordV2Service;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.product.qrcode.po.CarlovQrcode;
import com.boxiang.share.product.qrcode.service.CarlovQrcodeService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.jpush.JPush;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.product.carlife.po.OrderRefuel;
import com.boxiang.share.product.carlife.service.OrderRefuelService;
import com.boxiang.share.product.coupon.po.Coupon;
import com.boxiang.share.product.coupon.service.CouponRuleService;
import com.boxiang.share.product.coupon.service.CouponService;
import com.boxiang.share.product.coupon.service.RedeemRuleService;
import com.boxiang.share.product.coupon.service.SceneRecordService;
import com.boxiang.share.product.customer.dao.CustomerDao;
import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.system.po.ValidMessage;
import com.boxiang.share.utils.OrderConstants;
import com.boxiang.share.utils.json.JacksonUtil;

import cn.b2m.eucp.client.SingletonClient;

@Component("validateMessageJob")
public class ValidateMessageJob {
    private static final Logger logger = Logger.getLogger(ValidateMessageJob.class);
    //订单主表
    @Resource
    private OrderMainService orderMainService;
    //优惠券
    @Resource
    private CouponService couponService;
    //加油卡service
    @Resource
    private OrderRefuelService orderRefuelService;
    //优惠券赠送规则
    @Resource
    private CouponRuleService couponRuleService;

    @Resource
    private RedeemRuleService redeemRuleService;

    @Resource
    private SceneRecordService sceneRecordService;

    @Resource
    private OrderTemporaryShareService orderTemporaryShareService;

    @Resource
    private CustomerDao customerDao;
    @Resource
    private OrderParkService orderParkService;

    @Resource private String profileId;
    @Resource
    private ParkingService parkingService;

    @Resource
    private CarInOutRecordService carInOutRecordService;

    @Resource
    private CarInOutRecordV2Service carInOutRecordV2Service;

    @Resource
    private CarlovQrcodeService carlovQrcodeService;

    @Resource
    private ParkerService parkerService;

    /**
     * 定时清空存放短信验证码的缓存，时间间隔为每4小时一次
     */
    @Scheduled(cron = "0 0 0/4 * * ?")
    public void job1() {
        Map<String, ValidMessage> map = SingletonClient.valideCode;
        Map<String, ValidMessage> cash = new HashMap<String, ValidMessage>();
        Date date = new Date();
        logger.info("begin clean cach");
        for (Map.Entry<String, ValidMessage> entry : map.entrySet()) {
            ValidMessage message = entry.getValue();
            if (date.getTime() > message.getTime()) {
                cash.put(entry.getKey(), message);
            }
        }
        map.remove(cash);
        logger.info("clean cach finish , left: " + map.size());
    }

    /**
     * 定时修改十五分钟后未支付订单 改为已取消
     */
    @Scheduled(cron = "0 0/15 * * * ?")
    public void job2() {
        List<Coupon> couponList = couponService.queryCouponByOrderId();
        if (couponList != null && couponList.size() > 0) {
            for (Coupon coupon : couponList) {
                coupon.setCouponStatus("100201");
                coupon.setUseTime(null);
                coupon.setCouponOrder(null);
                //couponService.update(coupon);
            }
            couponService.batchUpdate(couponList);
        }
        List<OrderMain> orderList = orderMainService.selectListByStatus();
        if (orderList != null && orderList.size() > 0) {
            for (OrderMain orderMain2 : orderList) {
            	logger.warn("***************************************************");
            	logger.warn("要取消的订单号="+orderMain2.getOrderId());
            	logger.warn("***************************************************");
                orderMain2.setOrderStatus(OrderConstants.ORDER_STATUS_CANCELED);
                orderMain2.setModified("job2");
                orderMain2.setModifyDate(new Date());
                //orderMainService.update(orderMain2);
            }
            orderMainService.batchUpdate(orderList);
        }
    }

    /**
     * 1. 订单状态已支付，充值状态充值中，调用接口“订单状态查询”，根据返回值修改充值状态
     * 2. 订单状态已支付，充值状态撤销，调用接口“提交加油卡充值”，根据返回值修改充值状态
     */
    @Scheduled(cron = "0 0/3 * * * ?")
    public void job3() {
        List<OrderRefuel> refuelList = orderRefuelService.selectListForQuartz(new HashMap<String, Object>());
        if (null != refuelList) {
            for (OrderRefuel orf : refuelList) {
                //0、充值中 1、成功 9、失败
                if ("0".equals(orf.getTopupStatus())) {
                    try {
                        //调用聚合API查询订单信息
                        String mess = orderRefuelService.getOrderStateByJuheAPI(orf.getOrderId());
                        logger.info("查询加油卡充值状态返m回值" + mess);
                        Map<String, Object> allMap = new HashMap<String, Object>();
                        Map<String, Object> resultMap = new HashMap<String, Object>();
                        allMap = JacksonUtil.jsonToMap(mess);
                        //获取errorcode
                        Object error_code = allMap.get("error_code");
                        logger.info("加油卡状态查询接口errorcode不为" + error_code);
                        //获取result部分
                        String result = (String) allMap.get("result");
                        if (null != result) {
                            resultMap = JacksonUtil.jsonToMap(result);
                            //获取状态值
                            String game_state = (String) resultMap.get("game_state");
                            //状态更改为返回的状态
                            if (game_state != null) {
                                orf.setTopupStatus(game_state);
                                orderRefuelService.update(orf);
                            }
                        } else {
                            logger.info("获取result部分异常" + result);
                        }
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                }/*else if("9".equals(orf.getTopupStatus())){
                    try {
	    				//1、状态是撤销的重新调用提交接口
						String mess = orderRefuelService.rechargeByJuheAPI(orf.getOrderId());
						logger.info("提交加油卡接口返回值"+mess);
						//2、调用查询接口更改状态
						//调用聚合API查询订单信息
						String messCheck = orderRefuelService.getOrderStateByJuheAPI(orf.getOrderId());
						logger.info("查询加油卡充值状态返回值"+messCheck);
						Map<String,Object> allMap = new HashMap<String,Object>();
						Map<String,Object> resultMap = new HashMap<String,Object>();
						allMap = JacksonUtil.jsonToMap(messCheck);
						//获取errorcode
						Object error_code = allMap.get("error_code");
						logger.info("加油卡状态查询接口errorcode不为"+error_code);
						logger.info(((int)error_code==208710)+"0000000000");
						//获取result部分
						String result = (String) allMap.get("result");
						if(null!=result&&(int)error_code==0){
							resultMap = JacksonUtil.jsonToMap(result);
							//获取状态值
							String game_state = (String) resultMap.get("game_state");
							//状态更改为返回的状态
							if(game_state!=null){
								orf.setTopupStatus(game_state);
								orderRefuelService.update(orf);
							}
						}else{
							logger.info("获取result部分异常"+result);
						}

					} catch (Exception e) {
						logger.error("",e);
					}

	    		}*/
            }
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void job4() {
        //未过期未领取  100101 检查是否过期 过期则设置为已过期未领取 200101
        //已领取未使用  100201 检查是否过期 过期则设置为已过期已领取 200102
        String nowDay = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        List<Coupon> couponList1 = couponService.queryByCouponStatus();
        for (Coupon coupon1 : couponList1) {
            if (coupon1.getEffectiveEnd() != null && coupon1.getEffectiveEnd().length() > 0) {
                int c = nowDay.compareTo(coupon1.getEffectiveEnd());
                //当前时间大于有效结束时间 则视为过期
                if (c > 0) {
                    if ("100101".equals(coupon1.getCouponStatus()))
                        coupon1.setCouponStatus("200101");
                    else if ("100201".equals(coupon1.getCouponStatus()))
                        coupon1.setCouponStatus("200102");
                    couponService.update(coupon1);
                }
            } else if (coupon1.getReceiveEnd() != null && coupon1.getReceiveEnd().length() > 0) {
                if ("100101".equals(coupon1.getCouponStatus())) {
                    //未过期未领取
                    Calendar cal = new GregorianCalendar();
                    try {
                        cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(nowDay));
                        Calendar cl = new GregorianCalendar();
                        cl.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(coupon1.getReceiveEnd()));
                        int b = cal.compareTo(cl);
                        if (b > 0) {
                            coupon1.setCouponStatus("200101");
                            couponService.update(coupon1);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else if ("100201".equals(coupon1.getCouponStatus())) {
                    //已领取未使用
                    Calendar cl = new GregorianCalendar();
                    try {
                        cl.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(coupon1.getReceiveTime()));
                        cl.add(cl.DATE, (null != coupon1.getEffectivetime()) ? Integer.parseInt(coupon1.getEffectivetime()) : 0);
                        Calendar cal = new GregorianCalendar();
                        cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(nowDay));
                        int a = cal.compareTo(cl);
                        //当前时间大于有效结束时间 则视为过期
                        if (a > 0) {
                            coupon1.setCouponStatus("200102");
                            couponService.update(coupon1);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 定时更新车场空位数
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void job5() {
        try {
            String nowDay = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//            String nowDay ="2016-04-22";
            parkingService.updateCanUse();
            OrderTemporaryShare orderTemporaryShare = new OrderTemporaryShare();
            orderTemporaryShare.setAppointmentDate(nowDay);
            orderTemporaryShareService.updateParking(orderTemporaryShare);
//            List<OrderTemporaryShare> list = orderTemporaryShareService.selectCountToday(orderTemporaryShare);
//            for (OrderTemporaryShare orderTemporaryShare1:list)
//            {
//                String parkingId = orderTemporaryShare1.getParkingId();
//                Parking parking = parkingService.queryById(parkingId);
//                if (parking.getParkingInitialUse() !=null && parking.getParkingInitialUse()>0)
//                {
//                    parking.setParkingCanUse(parking.getParkingInitialUse());
//                    parkingService.update(parking);
//                }
//            }
//            for (OrderTemporaryShare orderTemporaryShare1:list)
//            {
//                String parkingId = orderTemporaryShare1.getParkingId();
//                Parking parking = parkingService.queryById(parkingId);
//                if (parking.getParkingCanUse()>1)
//                {
//                    parking.setParkingCanUse(parking.getParkingCanUse()-1);
//                    parkingService.update(parking);
//                }
//            }
            logger.info("------------------------------定时更新当天车场车位数成功");
        }catch (Exception e){
            logger.error("定时更新当天车场空位数失败", e);
        }
    }
    /**
     * 定时任务,共享临停订单 通知蓝卡抬杆
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void orderTemporaryShareCommitToBlue() {
        logger.info("------------------------------共享临停订单 通知蓝卡抬杆" + this.profileId);
        if(Constants.PROFILE_ID_PRE.equalsIgnoreCase(this.profileId)  || Constants.PROFILE_ID_PROD.equalsIgnoreCase(this.profileId)){
            try {
                logger.info("------------------------------共享临停订单 通知蓝卡抬杆开始");
                boolean flag = orderTemporaryShareService.timeTaskForCommitToBlue(30);
                logger.info(flag + "------------------------------共享临停订单 通知蓝卡抬杆结束");
            } catch (Exception e) {
                logger.error("共享临停订单 通知蓝卡抬杆异常", e);
            }
        }
    }

    /**
     * 场景推送
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void inParkTimedTask() {
        logger.info("------------------------------场景推送开始" + this.profileId);
        if(Constants.PROFILE_ID_PRE.equalsIgnoreCase(this.profileId)  || Constants.PROFILE_ID_PROD.equalsIgnoreCase(this.profileId)){
            try {
                logger.info("------------------------------场景推送开始");
                // 这个时间最好大于轮询时间，避免送漏
                couponRuleService.inParkTimedTask(20);
                logger.info("------------------------------场景推送结束");
            } catch (Exception e) {
                logger.error("场景推送异常", e);
            }
        }
    }

    /**
     * 下单推送
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void orderPushTimedTask() {
        logger.info("------------------------------下单推送开始" + this.profileId);
        if(Constants.PROFILE_ID_PRE.equalsIgnoreCase(this.profileId)  || Constants.PROFILE_ID_PROD.equalsIgnoreCase(this.profileId)){
            try {
                logger.info("------------------------------下单推送开始");
                couponRuleService.orderPushTimedTask();
                logger.info("------------------------------下单推送结束");
            } catch (Exception e) {
                logger.error("下单推送异常", e);
            }
        }
    }

    /**
     * 月租、产权缴费提醒  月租、产权过期后提醒
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void monthlyPropertyExpireTimedTask() {
        logger.info("------------------------------月租、产权提醒开始" + this.profileId);
        if(Constants.PROFILE_ID_PRE.equalsIgnoreCase(this.profileId)  || Constants.PROFILE_ID_PROD.equalsIgnoreCase(this.profileId)){
            try {
                logger.info("------------------------------月租、产权缴费提醒推送开始");
                couponRuleService.monthlyPropertyExpireBeforeTimedTask();
                logger.info("------------------------------月租、产权缴费提醒推送结束");
            } catch (Exception e) {
                logger.error("月租、产权缴费提醒异常", e);
            }
            try {
                logger.info("------------------------------月租、产权过期后提醒推送开始");
                couponRuleService.monthlyPropertyExpireAfterTimedTask();
                logger.info("------------------------------月租、产权过期后提醒推送结束");
            } catch (Exception e) {
                logger.error("月租、产权过期后提醒异常", e);
            }
        }
    }

    /**
     * 优惠券到期前提醒
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void couponExpireAlertTimedTask() {
        logger.info("------------------------------优惠券到期前提醒开始" + this.profileId);
        if(Constants.PROFILE_ID_PRE.equalsIgnoreCase(this.profileId)  || Constants.PROFILE_ID_PROD.equalsIgnoreCase(this.profileId)){
            try {
                logger.info("------------------------------优惠券到期前提醒开始");
                couponRuleService.couponExpireAlertTimedTask();
                logger.info("------------------------------优惠券到期前提醒结束");
            } catch (Exception e) {
                logger.error("优惠券到期前提醒异常", e);
            }
        }
    }

//    /**
//     * 注册送券
//     */
//    @Scheduled(cron = "0 0/1 * * * ?")
//    public void registerTimedTask() {
//        if (Constants.PROFILE_ID_PRE.equalsIgnoreCase(this.profileId) || Constants.PROFILE_ID_PROD.equalsIgnoreCase(this.profileId)) {
//            logger.info("------------------------------注册送券开始");
//            try {
//                couponRuleService.registerTimedTask(); // 注册送券
//            } catch (Exception e) {
//                logger.error("注册送券异常", e);
//            }
//            logger.info("------------------------------注册送券结束");
//        }
//    }

    /**
     * 送优惠券给知雅汇的用户,这个定时任务只跑一次，2016年4月25号早上7点
     */
//    @Scheduled(cron = "0 0 7 25 4 ?")
//    public void zhiChengHuaYuan() {
//        logger.info("begin----------------------送优惠券给知雅汇的用户,这个定时任务只跑一次，2016年4月25号早上7点");
//        try {
//            // 手动创建一个模板
//            CouponTemplate template = new CouponTemplate();
//            template.setId(-99);
//            template.setType("1");
//            template.setTypeId(-99);
//            template.setCouponKind("2");
//            template.setCouponName("志成老用户");
//            template.setCouponType("1");
//            template.setParAmount(new BigDecimal("49"));
//            template.setParDiscount(new BigDecimal("0"));
//            template.setMinconsumption(new BigDecimal("50"));
//            template.setEffectiveType("1");
//            template.setEffectiveBegin(DateUtil.str2date("2016-04-25", DateUtil.DATE_FORMAT));
//            template.setEffectiveEnd(DateUtil.str2date("2016-05-01", DateUtil.DATE_FORMAT));
//            template.setEffectiveDay(0);
//            template.setExclusionRule("不得与其他优惠同时使用");
//            template.setInfo("已注册用户");
//            template.setIsUsed(Constants.TRUE);
//            template.setCreateor("admin");
//            template.setCreateDate(new Date());
//            List<CouponTemplate> templateList = new ArrayList<>(1);
//            templateList.add(template);
//
//            // 查询志成花苑的所有月租产权用户信息 zchy20151028000004 zchy20151028000004
//            List<Customer> customerList = new ArrayList<>();
//            Map<String, Object> params = new HashMap<>(2);
//            params.put("parkingId", "zchy20151028000004");
//            params.put("tableName", "t_monthlyparkinginfo");
//            List<Customer> list = customerDao.customerWithInParkRule(params);
//            if (list != null && list.size() != 0) customerList.addAll(list);
//            params.put("tableName", "t_propertyparkinginfo");
//            list = customerDao.customerWithInParkRule(params);
//            if (list != null && list.size() != 0) customerList.addAll(list);
//
//            //遍历每一个用户
//            for (Customer cus : customerList) {
//                try {
//                    // 判断是否赠送过
//                    if (couponRuleService.existSceneRecord(cus.getCarNumber(), -99)) continue;
//
//                    // 写入领取记录
//                    SceneRecord sceneRecord = new SceneRecord();
//                    sceneRecord.setRuleId(-99);
//                    sceneRecord.setSceneMode("3");
//                    sceneRecord.setCustomerId(cus.getCustomerId());
//                    sceneRecord.setCarNumber(cus.getCarNumber());
//                    sceneRecord.setIsUsed(Constants.TRUE);
//                    sceneRecord.setCreateor("admin");
//                    sceneRecord.setCreateDate(new Date());
//                    sceneRecordService.add(sceneRecord);
//
//                    // 根据规则模板生成优惠券
//                    redeemRuleService.generateCouponByTemplate(templateList, cus.getCustomerId());
//                } catch (Exception e) {
//                    logger.error("", e);
//                }
//
//                try {
//                    // 发送短信
//                    String[] mobiles = {cus.getCustomerMobile()};
//                    SingletonClient.getInstance().sendMessage(mobiles, "【口袋停】志成花苑月租产权优惠券已发送至您的口袋停钱包，您可用于支付月租或产权费用，请查看口袋停APP", 5);
//                    // 极光
//                    JPush.CUSTERMER.sendPushByAlias("志成花苑月租产权优惠券已发送至您的口袋停钱包，您可用于支付月租或产权费用，请查看口袋停APP", cus.getCustomerId());
//                } catch (Exception e) {
//                    logger.error("", e);
//                }
//
//            }
//        } catch (Exception e) {
//            logger.error("", e);
//        }
//        logger.info("end----------------------送优惠券给知雅汇的用户,这个定时任务只跑一次，2016年4月25号早上7点");
//    }

    //取车消息推送


    @Scheduled(cron = "0 0/5 * * * ?")
    public void getCar() {
        if (Constants.PROFILE_ID_PRE.equalsIgnoreCase(this.profileId) || Constants.PROFILE_ID_PROD.equalsIgnoreCase(this.profileId)) {
            logger.info("------------------------------取车推送开始");
            try {
                //订单状态为已停车或者已泊回的代泊订单    提前30分钟推送短信及极光 提醒用户取车
                List<Map> list =  orderParkService.queryByOrderIspush();
                if(list!=null&&list.size()>0){
                    for(Map obj:list){
                        try {
                                 /*  Date endDate= DateUtil.str2date(obj.get("order_begin_date").toString(),DateUtil.DATETIME_FORMAT)  ;
                                  Date now= DateUtil.getPreOrNextMinute(new Date(), 30);
                                   if(now.getTime()<=endDate.getTime()){*/
                            //短信
                            if(null!=obj.get("customer_mobile")){
                                String[] mobiles = {obj.get("customer_mobile").toString()};
                                SingletonClient.getInstance().sendMessage(mobiles, "【口袋停】您的代泊车辆将即到预计取车时间，请在该取车时间后15分钟内到指定取车点取车，谢谢！", 5);
                            }
                            // 极光
                            JPush.CUSTERMER.sendPushByAlias("您的代泊车辆将即到预计取车时间，请在该取车时间后15分钟内到指定取车点取车，谢谢！",obj.get("customer_id").toString());
                            //修改为已推送
                             /*  OrderPark orderPark=new OrderPark();
                               orderPark.setIsPush("1");*/
                            orderParkService.merge(obj.get("order_id").toString());
                            //   }
                        }catch (Exception e){
                            logger.error("推送异常,但是还是会推送", e);
                        }
                    }


                }

            } catch (Exception e) {
                logger.error("推送异常", e);
            }
            logger.info("------------------------------取车推送结束");
        }
    }
//    /**
//     * 定时一分钟记录一下出厂入场记录
//     */
//    @Scheduled(cron = "0 0/1 * * * ?")
//
//    public void recordInOrOutCar() {
//        try {
//            logger.info("入场出场----定时记录执行");
//            carInOutRecordService.recordInOrOutCar();
//        } catch (Exception e) {
//            logger.info("入场出场----定时记录异常");
//        }
//    }
    /**
     * 定时一分钟同步财务报表
     */
    @Scheduled(cron = "0 0/1 * * * ?")

    public void queryStatistics() {
        try {
            logger.info("同步财务报表----定时记录执行");
            Map<String,String> map=new HashMap<>();
            map.put("wasai", DateUtil.date2str(new Date(),DateUtil.DATE_FORMAT));
            map.put("num","");
            orderMainService.getStatistics(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("同步财务报表----定时记录异常");
            logger.info("同步财务报表----定时记录异常");
            logger.info("同步财务报表----定时记录异常");
            logger.info("同步财务报表----定时记录异常",e);

        }
    }

        /**
    * 定时一分钟记录一下出厂入场记录
     */
    @Scheduled(cron = "0 0/3 * * * ?")
    public void recordInOrOutCarV2() throws Exception {
        try {
            logger.info("同步蓝卡云出入场记录---start");
            carInOutRecordV2Service.recordInOrOutCar();
            logger.info("同步蓝卡云出入场记录---end");
        } catch (Exception e)  {
            e.printStackTrace();
            logger.info("同步蓝卡云出入场记录---异常",e);
            throw e;
        }
    }

    /**
     * 定时一分钟同步财务报表
     *//**0 0 12 * * ?*/
    @Scheduled(cron = "0 0 14 * * ?")
    public void weixinJpush() {
        try {
            logger.info("微信社区二维码极光推送----定时记录执行");
            List<CarlovQrcode> cList = carlovQrcodeService.selectDayData(null);
            Set<String> parkers = new HashSet<String>();
            if(null!=cList&&cList.size()>0){
                for(CarlovQrcode c:cList){
                    Parker param = new Parker();
                    param.setParkingId(c.getParkingId());
                    List<Parker> pList =  parkerService.selectList(param);
                    if(null!=pList&&pList.size()>0){
                        for(Parker p:pList){
                            parkers.add(p.getParkerId());
                        }

                    }
                }
            }
            if(parkers.size()>0){
                for(String s:parkers){
                    String content = "请尽快更新社群二维码提醒";
                    JPush.PARKER.sendPushToParkerOther(content, s);
                }
            }
        } catch (Exception e) {
            logger.info("微信社区二维码极光推送----定时记录执行");
            e.printStackTrace();
        }
    }



}
