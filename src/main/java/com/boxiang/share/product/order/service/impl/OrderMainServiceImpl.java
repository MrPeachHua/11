package com.boxiang.share.product.order.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.boxiang.share.product.order.po.*;
import com.boxiang.share.product.parking.dao.CarInOutRecordV2Dao;
import com.boxiang.share.product.parking.service.ParkingService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.log.SystemLog;
import com.boxiang.share.bluecard.po.ParklotFee;
import com.boxiang.share.bluecard.po.ParklotFeeResult;
import com.boxiang.share.bluecard.po.PayStatusSync;
import com.boxiang.share.payment.service.PaymentInfoService;
import com.boxiang.share.product.car.po.Car;
import com.boxiang.share.product.car.service.CarService;
import com.boxiang.share.product.carlife.dao.CarlifeRefuelCardDao;
import com.boxiang.share.product.carlife.dao.OrderRefuelDao;
import com.boxiang.share.product.carlife.po.CarlifeRefuelCard;
import com.boxiang.share.product.carlife.po.OrderRefuel;
import com.boxiang.share.product.coupon.po.Coupon;
import com.boxiang.share.product.coupon.service.CouponService;
import com.boxiang.share.product.coupon.service.impl.CouponRuleServiceImpl;
import com.boxiang.share.product.customer.dao.CustomerDao;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.order.dao.InvoiceDao;
import com.boxiang.share.product.order.dao.MonthlyparkinginfoDao;
import com.boxiang.share.product.order.dao.OrderDao;
import com.boxiang.share.product.order.dao.OrderEquityDao;
import com.boxiang.share.product.order.dao.OrderMainDao;
import com.boxiang.share.product.order.dao.OrderMonthlyDao;
import com.boxiang.share.product.order.dao.OrderTemporaryDao;
import com.boxiang.share.product.order.dao.OrderTemporaryShareDao;
import com.boxiang.share.product.order.dao.PropertyparkinginfoDao;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.product.order.service.OrderParkService;
import com.boxiang.share.product.order.service.OrderService;
import com.boxiang.share.product.order.service.OrderTemporaryShareService;
import com.boxiang.share.product.parker.po.Parker;
import com.boxiang.share.product.parker.service.ParkerService;
import com.boxiang.share.product.parking.dao.ClearanceRecordDao;
import com.boxiang.share.product.parking.dao.ParkingDao;
import com.boxiang.share.product.parking.dao.ParkingVoucherDao;
import com.boxiang.share.product.parking.po.ClearanceRecord;
import com.boxiang.share.product.parking.po.DiscountParkingPrice;
import com.boxiang.share.product.parking.po.PackagePrice;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.po.ParkingVoucher;
import com.boxiang.share.product.parking.po.Villageinfo;
import com.boxiang.share.product.parking.service.DiscountParkingPriceService;
import com.boxiang.share.product.parking.service.PackagePriceService;
import com.boxiang.share.product.parking.service.VillageinfoService;
import com.boxiang.share.system.dao.SendMessageDao;
import com.boxiang.share.system.po.SendMessage;
import com.boxiang.share.system.po.Sequence;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.system.synwhite.dao.SpecialparkinginfoDao;
import com.boxiang.share.system.synwhite.dao.WhitesynInfoDao;
import com.boxiang.share.system.synwhite.dao.WhitesynRecordDao;
import com.boxiang.share.system.synwhite.po.RegisterUserSync;
import com.boxiang.share.system.synwhite.po.RegisterUserSyncData;
import com.boxiang.share.system.synwhite.po.Specialparkinginfo;
import com.boxiang.share.system.synwhite.po.SyncResult;
import com.boxiang.share.system.synwhite.po.WhitesynInfo;
import com.boxiang.share.system.synwhite.po.WhitesynRecord;
import com.boxiang.share.user.dao.UserInfoDao;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.LogTypeEnum;
import com.boxiang.share.utils.OrderConstants;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.TableNameConstants;
import com.boxiang.share.utils.jpush.JPush;
import com.boxiang.share.utils.json.JacksonUtil;
import com.boxiang.share.utils.json.JsonMapper;
import com.boxiang.share.utils.synwhite.AESSecurityUtil;
import com.boxiang.share.utils.synwhite.ErrorCodeEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.b2m.eucp.client.SingletonClient;


@DataSource(DataSourceEnum.MASTER)
@Service("orderMainService")
public class OrderMainServiceImpl implements OrderMainService {
    private static final Logger logger = Logger.getLogger(OrderMainServiceImpl.class);
    @Resource(name = "orderMainDao")
    private OrderMainDao orderMainDao;
    @Resource(name = "orderTemporaryDao")
    private OrderTemporaryDao orderTemporaryDao;
    @Resource
    private SequenceService sequenceService;
    @Resource(name = "parkingDao")
    private ParkingDao parkingDao;
    @Resource(name = "orderTemporaryShareDao")
    private OrderTemporaryShareDao orderTemporaryShareDao;
    @Resource(name = "orderMonthlyDao")
    private OrderMonthlyDao orderMonthlyDao;
    @Resource(name = "orderEquityDao")
    private OrderEquityDao orderEquityDao;
    @Resource(name = "monthlyparkinginfoDao")
    private MonthlyparkinginfoDao monthlyparkinginfoDao;
    @Resource(name = "propertyparkinginfoDao")
    private PropertyparkinginfoDao propertyparkinginfoDao;
    @Resource(name = "dataEhCache")
    private Cache ehCache;
    @Resource(name = "carlifeRefuelCardDao")
    private CarlifeRefuelCardDao carlifeRefuelCardDao;

    @Resource(name = "orderRefuelDao")
    private OrderRefuelDao orderRefuelDao;

    @Resource
    private CustomerDao customerDao;
    @Resource
    private ParkingService parkingService;

    @Resource
    private CustomerService customerService;

    @Resource
    private PaymentInfoService paymentInfoService;
    @Resource
    private String APIKey;
    @Resource
    private String parklotFee;
    @Resource
    private String outputCarImage;

    @Resource
    private String registerUserSync;
    @Resource
    private PackagePriceService packagePriceService;

    @Resource
    private String AESKey;
    // json处理对象
    private static ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private CouponService couponService;
    @Resource
    private OrderDao orderDao;
    //凭证dao
    @Resource
    private ParkingVoucherDao parkingVoucherDao;
    //特殊车辆
    @Resource(name = "specialparkinginfoDao")
    private SpecialparkinginfoDao specialparkinginfoDao;
    //白名单同步
    @Resource(name = "whitesynRecordDao")
    private WhitesynRecordDao whitesynRecordDao;

    @Resource
    private OrderService orderService;

    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private VillageinfoService villageinfoService;

    @Resource
    private CarService carService;

    @Resource(name = "invoiceDao")
    private InvoiceDao invoiceDao;

    @Resource
    private String payStatus;

    @Resource
    private ParkerService parkerService;

    @Resource
    private OrderParkService orderParkService;

    @Resource
    private WhitesynInfoDao whitesynInfoDao;

    @Resource
    private SendMessageDao sendMessageDao;
    @Resource
    private DiscountParkingPriceService discountParkingPriceService;

    @Resource
    private CouponRuleServiceImpl couponRuleServiceImpl;
    @Resource
    private OrderTemporaryShareService orderTemporaryShareService;
    @Resource
    private ClearanceRecordDao clearanceRecordDao;

    @Resource
    private CarInOutRecordV2Dao carInOutRecordV2Dao;
    /**
     * 获取orderMap
     *
     * @param om
     * @return
     */
    private Map<String, Object> getHashMap(OrderMain om) throws ParseException {
        HashMap<String, Object> orderMap = new HashMap<String, Object>();
        orderMap.put("orderId", (null != om.getOrderId()) ? om.getOrderId() : "");
        orderMap.put("orderType", (null != om.getOrderType()) ? om.getOrderType() : "");
        orderMap.put("orderTypeName", (null != om.getOrderType()) ? OrderConstants.getOrderTypeName(om.getOrderType()) : "");
        orderMap.put("orderStatus", (null != om.getOrderStatus()) ? om.getOrderStatus() : "");
        orderMap.put("orderStatusName", (null != om.getOrderStatus()) ? OrderConstants.getOrderStatusName(om.getOrderStatus()) : "");
        orderMap.put("parkingId", (null != om.getParkingId()) ? om.getParkingId() : "");
        orderMap.put("parkingName", (null != om.getParkingName()) ? om.getParkingName() : "");
        orderMap.put("carNumber", (null != om.getCarNumber()) ? om.getCarNumber() : "");
        orderMap.put("parkingNo", (null != om.getParkingNo()) ? om.getParkingNo() : "");
        orderMap.put("orderBeginDate", (null != om.getCreateDate()) ? DateUtil.date2str(om.getCreateDate(), DateUtil.DATETIME_FORMAT) : "");
        orderMap.put("actualBeginDate", (null != om.getBeginTime()) ? DateUtil.date2str(om.getBeginTime(), DateUtil.DATETIME_FORMAT) : "");
        orderMap.put("actualEndDate", (null != om.getEndTime()) ? DateUtil.date2str(om.getEndTime(), DateUtil.DATETIME_FORMAT) : "");
        orderMap.put("payTime", (null != om.getPayTime()) ? DateUtil.date2str(om.getPayTime(), DateUtil.DATETIME_FORMAT) : "");
        orderMap.put("payType", (null != om.getPayType()) ? om.getPayType() : "");
        orderMap.put("amountPayable", (null != om.getAmountPayable()) ? om.getAmountPayable() / 100 : "");
        orderMap.put("amountDiscount", (null != om.getAmountDiscount()) ? om.getAmountDiscount() / 100 : "");
        orderMap.put("amountPaid", (null != om.getAmountPaid()) ? om.getAmountPaid() / 100 : "");
        orderMap.put("parkingCode", (null != om.getParkingCode()) ? om.getParkingCode() : "");
        orderMap.put("startTime", null != om.getStartTime() ? om.getStartTime() : "");
        orderMap.put("stopTime", null != om.getStopTime() ? om.getStopTime() : "");
        orderMap.put("appointmentDate", null != om.getAppointmentDate() ? om.getAppointmentDate() : "");
        orderMap.put("voucherStatus", null != om.getVoucherStatus() ? om.getVoucherStatus() : "");
        orderMap.put("monthNum", null != om.getMonthNum() ? om.getMonthNum() : "");
        orderMap.put("tradeNo", null != om.getTradeNo() ? om.getTradeNo() : "");
        orderMap.put("effectEndDate",null != om.getEffectEndDate() ? DateUtil.date2str(om.getEffectEndDate(), DateUtil.DATE_FORMAT) : "");
        orderMap.put("tempBeginTime",null != om.getBeginTime() ? DateUtil.date2str(om.getBeginTime(), DateUtil.DATETIME_FORMAT) : "");
        orderMap.put("tempEndTime",null != om.getEndTime() ? DateUtil.date2str(om.getEndTime(), DateUtil.DATETIME_FORMAT) : "");
        List<String> reserveTimeStr = new ArrayList<String>();
        if(!StringUtils.isEmpty(om.getAppointmentDate())){
            String []days = om.getAppointmentDate().split("\\,");
            for(String s:days){
                String str = "";
                Date da = DateUtil.str2date(s,DateUtil.DATE_FORMAT);
                String beginTime = "";
                String endTime = "";
                String week = "";
                String port = "当日";
                switch (getDay(da)) {
                    case 2:
                        beginTime = om.getMondayBeginTime();
                        endTime = om.getMondayEndTime();
                        week = "周一";
                        break;
                    case 3:
                        beginTime = om.getTuesdayBeginTime();
                        endTime = om.getTuesdayEndTime();
                        week = "周二";
                        break;
                    case 4:
                        beginTime = om.getWednesdayBeginTime();
                        endTime = om.getWednesdayEndTime();
                        week = "周三";
                        break;
                    case 5:
                        beginTime = om.getThursdayBeginTime();
                        endTime = om.getThursdayEndTime();
                        week = "周四";
                        break;
                    case 6:
                        beginTime = om.getFridayBeginTime();
                        endTime = om.getFridayEndTime();
                        week = "周五";
                        break;
                    case 7:
                        beginTime = om.getSaturdayBeginTime();
                        endTime = om.getSaturdayEndTime();
                        week = "周六";
                        break;
                    case 1:
                        beginTime = om.getSundayBeginTime();
                        endTime = om.getSundayEndTime();
                        week = "周日";
                        break;
                }
                if (!StringUtils.isEmpty(beginTime) && !StringUtils.isEmpty(endTime)) {
                    Date beginTimeDate = DateUtil.str2date(beginTime, "HH:mm");
                    Date endTimeDate = DateUtil.str2date(endTime, "HH:mm");
                    if (beginTimeDate.getTime() >= endTimeDate.getTime()) {//跨日
                        port = "次日";
                    }
                }
                str+=s;//日期
                str+=week;
                str+="  ";
                str+=beginTime;
                str+="-";
                str+=port;
                str+=endTime;
                reserveTimeStr.add(str);
            }
            orderMap.put("reserveTimeStr",reserveTimeStr);
        }
        Object object = null;
        if (om.getStartEndTime() != null) {
            try {
                object = JsonMapper.fromJson(om.getStartEndTime(), Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        orderMap.put("startEndTime", object);
        orderMap.put("mondayBeginTime", om.getMondayBeginTime());
        orderMap.put("mondayEndTime", om.getMondayEndTime());
        orderMap.put("fridayBeginTime", om.getFridayBeginTime());
        orderMap.put("fridayEndTime", om.getFridayEndTime());
        orderMap.put("saturdayBeginTime", om.getSaturdayBeginTime());
        orderMap.put("saturdayEndTime", om.getSaturdayEndTime());
        orderMap.put("sundayBeginTime", om.getSundayBeginTime());
        orderMap.put("sundayEndTime", om.getSundayEndTime());
        orderMap.put("thursdayBeginTime", om.getThursdayBeginTime());
        orderMap.put("thursdayEndTime", om.getThursdayEndTime());
        orderMap.put("tuesdayBeginTime", om.getTuesdayBeginTime());
        orderMap.put("tuesdayEndTime", om.getTuesdayEndTime());
        orderMap.put("wednesdayBeginTime", om.getWednesdayBeginTime());
        orderMap.put("wednesdayEndTime", om.getWednesdayEndTime());
        return orderMap;
    }

    @Override
    public Page<OrderMain> queryTempSharePage(Page<OrderMain> page) {
        page.setResultList(orderMainDao.queryTempSharePage(page));
        return page;
    }

    @Override
    public List<OrderMain> queryStream(Page<OrderMain> page) {
        return orderMainDao.queryStream(page);
    }

    @Override
    public List<OrderMain> queryListByStream(Page<OrderMain> page) {
        return orderMainDao.queryListByStream(page);
    }

    @Override
    public List<OrderMain> queryAmountPaid(OrderMain orderMain) {
        return orderMainDao.queryAmountPaid(orderMain);
    }

    @Override
    public Page<OrderMain> queryTempPage(Page<OrderMain> page) {
        page.setResultList(orderMainDao.queryTempPage(page));
        return page;
    }

    @Override
    public List<OrderMain> queryTempExcel(OrderMain orderMain) {
        return orderMainDao.queryTempExcel(orderMain);
    }

    @Override
    public List<OrderMain> queryTempShareExcel(OrderMain orderMain) {
        return orderMainDao.queryTempShareExcel(orderMain);
    }

    @Override
    public List<OrderMain> queryAmountPaidByMonth(OrderMain orderMain) {
        return orderMainDao.queryAmountPaidByMonth(orderMain);
    }

    @Override
    public Object queryMonthlyEquityByOrderId(String orderId) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("orderId", orderId);
        Map<String, Object> map = new HashMap<>(1);
        map.put("params", params);
        List<OrderMain> list = orderMainDao.queryMonthlyEquityListPage(map);
        Map<String, Object> orderMap = new HashMap<String, Object>();
        if (list != null && list.size() > 0) {
            OrderMain orderMain = list.get(0);
            orderMap.put("monthNum", (null != orderMain.getMonthNum()) ? orderMain.getMonthNum() : "");
            orderMap.put("orderId", (null != orderMain.getOrderId()) ? orderMain.getOrderId() : "");
            orderMap.put("orderStatus", (null != orderMain.getOrderStatus()) ? orderMain.getOrderStatus() : "");
            orderMap.put("orderType", (null != orderMain.getOrderType()) ? orderMain.getOrderType() : "");
            orderMap.put("carNumber", (null != orderMain.getCarNumber()) ? orderMain.getCarNumber() : "");
            orderMap.put("parkingId", (null != orderMain.getParkingId()) ? orderMain.getParkingId() : "");
            orderMap.put("parkingName", (null != orderMain.getParkingName()) ? orderMain.getParkingName() : "");
            orderMap.put("createDate", (null != orderMain.getCreateDate()) ? DateUtil.date2str(orderMain.getCreateDate(), DateUtil.DATETIME_FORMAT) : "");
            orderMap.put("amountPaid", (null != orderMain.getAmountPaid()) ? orderMain.getAmountPaid() / 100 : "");
            orderMap.put("amountPayable", (null != orderMain.getAmountPayable()) ? orderMain.getAmountPayable() / 100 : "");
            orderMap.put("amountDiscount", (null != orderMain.getAmountDiscount()) ? orderMain.getAmountDiscount() / 100 : "");
            orderMap.put("beginTime", (null != orderMain.getBeginTime()) ? DateUtil.date2str(orderMain.getBeginTime(), DateUtil.DATETIME_FORMAT) : "");
            orderMap.put("endTime", (null != orderMain.getEndTime()) ? DateUtil.date2str(orderMain.getEndTime(), DateUtil.DATETIME_FORMAT) : "");
            orderMap.put("payTime", (null != orderMain.getPayTime()) ? DateUtil.date2str(orderMain.getPayTime(), DateUtil.DATETIME_FORMAT) : "");
        }
        return orderMap;
    }

    @Override
    public List<Map<String,Object>> queryStatistics(Object page) {
        return orderMainDao.queryStatistics(page);
    }

    @Override
    public Object queryMoney() {
        return orderMainDao.queryMoney();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void getStatistics(Map<String, String> map) {
         orderMainDao.getStatistics(map);
    }

    @Override
    public List<Map<String, Object>> queryPayfeelsStatistics(Object page) {
        return orderMainDao.queryPayfeelsStatistics(page);
    }

    @Override
    public Page<Object> queryAllOrder(Page<Object> page) {
        page.setResultList(orderMainDao.queryAllOrder(page));
        return page;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void parkPayWithGettingCar(OrderMain orderMain) throws Exception {
        if (orderMain.getOrderStatus().equals(OrderConstants.ORDER_STATUS_WAITING_GETCAR)) { // 8
            throw new Exception("代泊订单状态不对，该订单已在取车中");
        }
        orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_WAITING_GETCAR); // 付完款 8
        // 查询副表
        OrderPark orderPark = orderParkService.queryByOrderId(orderMain.getOrderId());
        // 判断有没人去泊回
        Parker parker;
//        if (StringUtils.isEmpty(orderPark.getParkerBackId())) {
        // 分配代泊员去取车
        parker = parkerService.dispatchParker(orderPark.getParkingId());
        if (parker == null) {
            throw new Exception("没有代泊员");
        }
        // 绑定代泊员
        orderPark.setParkerBackId(parker.getParkerId());
//        } else {
//            parker = parkerService.queryById(orderPark.getParkerBackId());
//            // 修改还车代泊员的最后操作时间
//            parker.setLastOperTime(orderMain.getModifyDate());
//            parker.setVersion(null); // 强制更新,不判断version
//            parkerService.updateLastOperTime(parker);
//        }
        orderPark.setModifyDate(new Date());
        orderParkService.update(orderPark);
        // 发送短信叫代泊员去取车
        try {
//            String content = "有客户要取车，请查看取车订单";
//            // 发送短信
            String content = "亲，有车牌号码为" + orderPark.getCarNumber() + "的用户需要取车，请安排取车及交车相关事宜，谢谢！";
            String[] mobiles = {parker.getParkerMobile()};
            SingletonClient.getInstance().sendMessage(mobiles, "【口袋停】" + content, 5);
            // 极光推送
            JPush.PARKER.sendPushToParkerWithGetCar(content, parker.getParkerId());
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public String walletPay(String orderId, String payPassword) throws Exception {

        OrderMain orderMain = orderMainDao.queryById(orderId);
        if (orderMain == null) {
			logger.info("**********************************************************");
			logger.info("不存在该订单");
			logger.info("**********************************************************");
            return ShangAnMessageType.operateToJson("1", "不存在该订单");
        }

        Customer customer = customerDao.queryByCustomerId(orderMain.getCustomerId());
        if (customer == null || customer.getPay_password() == null || !customer.getPay_password().equals(payPassword)) {
			logger.info("**********************************************************");
			logger.info("支付密码不正确");
			logger.info("**********************************************************");
            return ShangAnMessageType.operateToJson("1", "支付密码不正确");
        }

        if (orderMain.getOrderType().equals("16")) {
			logger.info("**********************************************************");
			logger.info("不能用余额充值钱包");
			logger.info("**********************************************************");
            return ShangAnMessageType.operateToJson("1", "不能用余额充值钱包");
        }

        if (orderMain.getOrderType().equals(OrderConstants.ORDER_TYPE_PARK) && orderMain.getOrderStatus().equals(OrderConstants.ORDER_STATUS_GETTINGCAR)) {
			logger.info("**********************************************************");
			logger.info("代泊订单状态不对，该订单已在取车中");
			logger.info("**********************************************************");
            return ShangAnMessageType.operateToJson("1", "代泊订单状态不对，该订单已在取车中");
        } else if (!orderMain.getOrderType().equals(OrderConstants.ORDER_TYPE_PARK) && !orderMain.getOrderStatus().equals(OrderConstants.ORDER_STATUS_UNPAY)) {
			logger.info("**********************************************************");
			logger.info("订单状态不正确");
			logger.info("**********************************************************");
            return ShangAnMessageType.operateToJson("1", "订单状态不正确");
        }

        if (customer.getMoney() - orderMain.getAmountPaid() < 0) {
			logger.info("**********************************************************");
			logger.info("余额不足");
			logger.info("**********************************************************");
            return ShangAnMessageType.operateToJson("1", "余额不足");
        }

        //如果是月租产权的话钱包支付发短信操作
        //月租产权同步时间发送短信
        if (OrderConstants.ORDER_TYPE_EQUITY.equals(orderMain.getOrderType()) || OrderConstants.ORDER_TYPE_MONTHLY.equals(orderMain.getOrderType())) {
            this.monAndProPaid(orderMain);
        }

        //洗车发送短信给洗车管理员
        if (("17".equals(orderMain.getOrderType())) && ("10".equals(orderMain.getOrderStatus()))) {
            paymentInfoService.sendToCarwashManager(orderMain);
            logger.info("洗车短信-----------------" + orderMain.getOrderType() + "----------------" + orderMain.getOrderStatus());
        }

        // 处理代泊付完款的业务
        if (orderMain.getOrderType().equals(OrderConstants.ORDER_TYPE_PARK)) {
            parkPayWithGettingCar(orderMain);
        } else {
            // 修改订单状态
            orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_PAID);
//		if (orderMainDao.updateUnpayToPaid(orderMain.getOrderId()) != 1) {
//			throw new Exception("订单状态修改异常");
//		}
        }

        // 扣除用户钱包余额
        if (customerService.subtractMoney(customer.getCustomerId(), orderMain.getAmountPaid()) != 1) {
			logger.info("**********************************************************");
			logger.info("扣除余额异常，余额不足");
			logger.info("**********************************************************");
            throw new Exception("扣除余额异常，余额不足");
        }

        orderMain.setPayType("05");
        orderMain.setPayTime(new Date());
        orderMainDao.update(orderMain);

        // 通知蓝卡云，付款成功，只针对临停
        if (OrderConstants.ORDER_TYPE_TEMPORARY.equals(orderMain.getOrderType())) {
            OrderMain om = orderMainDao.selectOrderDetailT(orderMain);
			logger.info("**********************************************************");
			logger.info("通知蓝卡云，付款成功，只针对临停,om="+om);
			logger.info("**********************************************************");
            if (null != om) {
                PayStatusSync payStatusSync = new PayStatusSync();
                payStatusSync.setPlateId(om.getCarNumber());// 车牌号
                payStatusSync.setPayAmount(String.valueOf(om.getAmountPayable().intValue()));// 应付金额
                payStatusSync.setPayTime(om.getPayTime() == null ? DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) : DateUtil.date2str(om.getPayTime(), DateUtil.DATETIME_FORMAT));// 支付时间
                payStatusSync.setGetTime(DateUtil.date2str(om.getCreateDate(), DateUtil.DATETIME_FORMAT));// 算费时间
                payStatusSync.setPayType(om.getPayType());// 支付类型
                payStatusSync.setPayNo(orderId);// 订单交易流水号
                backForTempOrder(payStatusSync);
            }
        }
        return ShangAnMessageType.toShangAnJson("0", "支付成功", "orderId", orderId);
    }

    @Override
    public String backForTempOrder(PayStatusSync payStatusSync) {
        //String resultjson = payStatusSyncService.parsePayStatusSyn(payStatusSync);
		logger.info("**********************************************************");
        logger.info("into parsePayStatusSyn(PayStatusSync payStatusSync)......");
		logger.info("**********************************************************");
        try {
            String paramJson = JsonMapper.toJson(payStatusSync, false);
            logger.info("-----调用蓝卡，请求url：" + payStatus);
            logger.info("-----调用蓝卡，请求参数：" + paramJson);
        } catch (IOException e) {
            logger.error("", e);
        }
        /**--------------------调用蓝卡*/
        // 管理账户apikey 注册，激活账户后获得
        payStatusSync.setKey(APIKey);//管理员账号
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PayStatusSync> request = new HttpEntity<PayStatusSync>(payStatusSync, requestHeaders);
        String resultJson = null;
        try {
            // 返回结果处理
            resultJson = restTemplate.postForObject(payStatus, request, String.class);
            // String resultJson2 = initRestTemplate().postForObject(HTTP_URL, request, String.class);
            logger.info("resultJson = " + resultJson);
        } catch (Exception e) {
            logger.error("在线支付结果反馈rest远程调用失败:" + e.getMessage());
        }
        return resultJson;
    }

    @Deprecated
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public String payParking(String orderId, String price, String payPassword) throws Exception {
        Order order = orderService.queryById(orderId);
        if (order == null) {
            return ShangAnMessageType.operateToJson("1", "不存在该订单");
        }

        Customer customer = customerDao.queryByCustomerId(order.getCustomerId());
        if (customer == null || customer.getPay_password() == null || !customer.getPay_password().equals(payPassword)) {
            return ShangAnMessageType.operateToJson("1", "支付密码不正确");
        }

        if (!Integer.toString(order.getOrderState()).equals(OrderConstants.ORDER_STATUS_GETTINGCAR)) {
            return ShangAnMessageType.operateToJson("1", "订单状态不正确");
        }

        int amount = new BigDecimal(price).multiply(new BigDecimal("100")).intValue(); // 以分为单位的钱
        if (customer.getMoney() - amount < 0) {
            return ShangAnMessageType.operateToJson("1", "余额不足");
        }

        // 修改订单状态
        order.setOrderState(Integer.parseInt(OrderConstants.ORDER_STATUS_PAID));
        order.setUpdatedAt(DateUtil.date2str(new Date(), DateUtil.DATETIME_FORMAT));
        orderService.update(order);

        // 扣除用户钱包余额
        if (customerService.subtractMoney(customer.getCustomerId(), amount) != 1) {
            throw new Exception("扣除余额异常，余额不足");
        }

        return ShangAnMessageType.toShangAnJson("0", "支付成功", "orderId", orderId);
    }

    @Override
    public String selectCount(String customerId) {

        String mess = null;
        try {
            OrderMain om = new OrderMain();
            om.setCustomerId(customerId);
            om.setOrderType(OrderConstants.ORDER_TYPE_MONTHLY);
            int monthly = orderMainDao.selectCount(om);
            OrderMain om2 = new OrderMain();
            om.setCustomerId(customerId);
            om.setOrderType(OrderConstants.ORDER_TYPE_EQUITY);
            int equity = orderMainDao.selectCount(om);
            int order = orderDao.selectCount(customerId);
            //List<Order> list=orderDao.selectList(order);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("monthly", monthly);
            map.put("equity", equity);
            map.put("paker", order);
            mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "count", map);
        } catch (Exception e) {
            mess = ShangAnMessageType.toShangAnJson("2", "查询失败", "order", "");
            e.printStackTrace();
        }

        return mess;
    }

    @Override
    public List<OrderMainExcel> rechargeExcel(Map<String, Object> params) {
        return orderMainDao.rechargeExcel(params);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public String blueWalletPay(String parkId, String parkName, String orderId, String carNumber, String carType, Integer amountPayable, String beginDate, String endDate) throws Exception {
        //应付金额为0时直接成功
        if(amountPayable == 0){
            logger.info("-------------应付金额为0，直接成功");
            return ShangAnMessageType.operateToJson("0", "付款成功");
        }
        // 判断订单号是否重复
        OrderTemporary orderTemporaryQuery = new OrderTemporary();
        orderTemporaryQuery.setBlueOrderId(orderId);
        List<OrderTemporary> orderTemporaryList = orderTemporaryDao.selectList(orderTemporaryQuery);
        if (!(orderTemporaryList == null || orderTemporaryList.size() == 0)) {
            return ShangAnMessageType.operateToJson("10", "付款失败，重复的订单号");
        }
        // 判断车场是否存在
        Villageinfo villageinfo = new Villageinfo();
        villageinfo.setItem01(parkId);
        List<Villageinfo> villageinfoList = villageinfoService.selectList(villageinfo);
        if (villageinfoList == null || villageinfoList.size() == 0) {
            return ShangAnMessageType.operateToJson("11", "付款失败，查询不到该车场");
        }
        villageinfo = villageinfoList.get(0);
        // 判断 车牌号 自动支付 余额
        Car carQuery = new Car();
        carQuery.setCarNumber(carNumber);
        carQuery.setIsAutoPay(Constants.TRUE);
        carQuery.setCustomer(new Customer());
        carQuery.getCustomer().setMoney(amountPayable);
        List<Car> carList = carService.queryCarListWithCustomer(carQuery);
        if (carList == null || carList.size() == 0) {
            return ShangAnMessageType.operateToJson("12", "付款失败，查询不到开启了自动支付的车辆或该用户余额不足");
        }
        // 随便扣除一个人的余额
        Car car = carList.get(0);
        int resultLine = customerService.subtractMoney(car.getCustomerId(), amountPayable);
//		if (resultLine <= 0) {
//			return ShangAnMessageType.operateToJson("13", "付款失败，余额不足");
//		}
        Parking parkingEhca = new Parking();
        if (ehCache.get("t_parking" + villageinfo.getId()) != null && ehCache.get("t_parking" + villageinfo.getId()).getObjectValue() != null) {
            parkingEhca = (Parking) ehCache.get("t_parking" + villageinfo.getId()).getObjectValue();
        } else {
            parkingEhca = parkingService.queryById(villageinfo.getId());
            ehCache.put(new Element("t_parking" + villageinfo.getId(), parkingEhca));
        }

        // 生成订单 主订单
        Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_ORDER_MAIN);
        OrderMain orderMain = new OrderMain();
        orderMain.setOrderId((null == parkingEhca.getParkingIdentifier() ? "" : parkingEhca.getParkingIdentifier()) + "11" + sequence.getId());
        orderMain.setOrderType(OrderConstants.ORDER_TYPE_TEMPORARY); // 临停
        orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_PAID); // 已付款
        orderMain.setInvoiceStatus("0");
        orderMain.setCustomerId(car.getCustomerId());
        orderMain.setAmountPayable(amountPayable);
        orderMain.setAmountDiscount(0);
        orderMain.setAmountPaid(amountPayable);
        orderMain.setPayType("05");
        orderMain.setPayTime(new Date());
        orderMain.setIsUsed(Constants.TRUE);
        orderMain.setCreateor("blue");
        orderMain.setCreateDate(orderMain.getPayTime());
        orderMainDao.insert(orderMain);
        // 临停订单
        OrderTemporary orderTemporary = new OrderTemporary();
        orderTemporary.setParkingId(villageinfo.getId());
        orderTemporary.setOrderId(orderMain.getOrderId());
        orderTemporary.setCarNumber(car.getCarNumber());
        orderTemporary.setBlueParkingId(parkId);
        orderTemporary.setBlueParkingName(parkName);
        orderTemporary.setBlueOrderId(orderId);
        orderTemporary.setBeginDate(DateUtil.str2date(beginDate, DateUtil.DATETIME_FORMAT));
        orderTemporary.setEndDate(DateUtil.str2date(endDate, DateUtil.DATETIME_FORMAT));
        orderTemporary.setCarType(carType);
        orderTemporary.setIsUsed(Constants.TRUE);
        orderTemporary.setCreateor(orderMain.getCreateor());
        orderTemporary.setCreateDate(orderMain.getCreateDate());
        orderTemporaryDao.insert(orderTemporary);
        // 发送短信
        String content = "【口袋停】亲,您已成功支付此次" + parkName + "车场停车费用，总计" + (amountPayable / 100.00) + "元，感谢您的使用！如有问题，可致电400-006-2637";
        SingletonClient.getInstance().sendMessage(new String[]{car.getCustomer().getCustomerMobile()}, content, 5);
        return ShangAnMessageType.operateToJson("0", "付款成功");
    }

    @Override
    public String selectOrderDetailE(OrderMain orderMain) {
        String message = null;
        try {
            OrderMain om = orderMainDao.selectOrderDetailE(orderMain);
            if (om != null) {
                message = ShangAnMessageType.toShangAnJson("0", "查询成功", "order", this.getHashMap(om));
            } else {
                message = ShangAnMessageType.toShangAnJson("1", "查询无数据", "order", "");
            }
        } catch (Exception e) {
            message = ShangAnMessageType.toShangAnJson("2", "查询失败", "order", "");
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public String selectOrderDetailM(OrderMain orderMain) {
        String message = null;
        try {
            OrderMain om = orderMainDao.selectOrderDetailM(orderMain);
            if (om != null) {
                message = ShangAnMessageType.toShangAnJson("0", "查询成功", "order", this.getHashMap(om));
            } else {
                message = ShangAnMessageType.toShangAnJson("1", "查询无数据", "order", "");
            }
        } catch (Exception e) {
            message = ShangAnMessageType.toShangAnJson("2", "查询失败", "order", "");
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public String selectOrderDetailT(OrderMain orderMain) {
        String message = null;
        try {
            OrderMain om = orderMainDao.selectOrderDetailT(orderMain);
            if (om != null) {
                message = ShangAnMessageType.toShangAnJson("0", "查询成功", "order", this.getHashMap(om));
            } else {
                message = ShangAnMessageType.toShangAnJson("1", "查询无数据", "order", "");
            }
        } catch (Exception e) {
            message = ShangAnMessageType.toShangAnJson("2", "查询失败", "order", "");
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public String selectOrderDetailTS(OrderMain orderMain) {
        String message = null;
        try {
            OrderMain om = orderMainDao.selectOrderDetailTS(orderMain);
            if (om != null) {
                message = ShangAnMessageType.toShangAnJson("0", "查询成功", "order", this.getHashMap(om));
            } else {
                message = ShangAnMessageType.toShangAnJson("1", "查询无数据", "order", "");
            }
        } catch (Exception e) {
            message = ShangAnMessageType.toShangAnJson("2", "查询失败", "order", "");
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public Map queryTemporaryByOrderId(String orderId, String orderType) throws ParseException {
        OrderMain orderMain = new OrderMain();
        orderMain.setOrderId(orderId);
        orderMain.setOrderType(orderType);
        if ("10".equals(orderType)) {
            OrderMain om = orderMainDao.selectOrderDetailTS(orderMain);
            return this.getHashMap(om);
        } else if ("11".equals(orderType)) {
            OrderMain om = orderMainDao.selectOrderDetailT(orderMain);
            return this.getHashMap(om);
        }
        return null;
    }

    @Override
    public List<OrderMain> selectList(OrderMain orderMain) {
        return orderMainDao.selectList(orderMain);
    }

    @Override
    public Page<OrderMain> queryListPage(Page<OrderMain> page) {
        page.setResultList(orderMainDao.queryListPage(page));
        return page;
    }

    @Override
    public OrderMain queryByIdAndType(OrderMain orderMain) {
        return orderMainDao.queryByIdAndType(orderMain);
    }

    @Override
    public OrderMain queryById(java.lang.String id) {
        return orderMainDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(OrderMain orderMain) {
        orderMainDao.insert(orderMain);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(java.lang.String id) {
        orderMainDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    @SystemLog(logType = LogTypeEnum.ORDER_INFO, logSummary = "修改订单信息")
    public void update(OrderMain orderMain) {
        orderMainDao.update(orderMain);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    @SystemLog(logType = LogTypeEnum.ORDER_INFO, logSummary = "批量修改订单信息")
    public void batchUpdate(List<OrderMain> orderMainList) {
        orderMainDao.batchUpdate(orderMainList);
    }

    SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD");

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    @SystemLog(logType = LogTypeEnum.ORDER_INFO, logSummary = "创建临停缴费订单")
    public String ordercTemporary(Map<String, Object> param) throws Exception {
        String mess = null;
        String parkingId = (String) param.get("parkingId");
        String customerId = (String) param.get("customerId");
        String carNumber = (String) param.get("carNumber");
        String orderType = (String) param.get("orderType");
        ParklotFeeResult parklotFeeResult = null;
        Parking parkingEhca = new Parking();
        if (ehCache.get("t_parking" + parkingId) != null && ehCache.get("t_parking" + parkingId).getObjectValue() != null) {
            parkingEhca = (Parking) ehCache.get("t_parking" + parkingId).getObjectValue();
        } else {
            parkingEhca = parkingService.queryById(parkingId);
            ehCache.put(new Element("t_parking" + parkingId, parkingEhca));
            if(parkingEhca==null|| parkingEhca.equals("")){
                return ShangAnMessageType.operateToJson("2", "车场不存在！车场ID为："+parkingId);
            }
        }
        //查询用户是否VIP
        Customer cusParam = new Customer();
        cusParam.setCustomerId(customerId);
        List<Customer> cusList = customerDao.selectList(cusParam);
        if (null == cusList || cusList.size() == 0) {
            return ShangAnMessageType.operateToJson("2", "用户不存在");
        }
        if ("1".equals(cusList.get(0).getIdentity())) {//vip用B套餐的价格
            parklotFeeResult = sendJsonDataToRest(new ParklotFee(APIKey, carNumber, "B"));
        } else {//普通用户时
            ParkingVoucher parkingVoucher = new ParkingVoucher();
            parkingVoucher.setCustomerId(customerId);
            parkingVoucher.setCarNumber(carNumber);
            parkingVoucher.setPvStatus("0");
            parkingVoucher.setParkingId(parkingId);
            List<ParkingVoucher> pvList = parkingVoucherDao.selectList(parkingVoucher);
            //1、如果有可用凭证取A套餐 凭证置为已使用2、无凭证用午餐蓝卡价格
            if (pvList != null && pvList.size() > 0) {
                parklotFeeResult = sendJsonDataToRest(new ParklotFee(APIKey, carNumber, "A"));
            } else {
                parklotFeeResult = sendJsonDataToRest(new ParklotFee(APIKey, carNumber));
            }
        }
        if (parklotFeeResult.getStatus() == null || (!parklotFeeResult.getStatus().equals("success"))||(StringUtils.isEmpty(parklotFeeResult.getDatas().getParkId()))) {
            logger.debug("-----调蓝卡云返回失败--OrderMainServiceImpl");
            mess = ShangAnMessageType.toShangAnJson("1", "无数据", "order", "");
            return mess;
        }
        if (parklotFeeResult != null) {
            String blueCardOrderId = parklotFeeResult.getDatas().getOrderId();
            String blueCardParkId = parklotFeeResult.getDatas().getParkId();
            String carType = parklotFeeResult.getDatas().getCarType();
            String blueCardParkName = parklotFeeResult.getDatas().getParkName();
            //费用
            String payCharge = StringUtils.isEmpty(parklotFeeResult.getDatas().getPayCharge()) || parklotFeeResult.getDatas().getPayCharge().trim().equals("null") ? parklotFeeResult.getDatas().getCharge() : parklotFeeResult.getDatas().getPayCharge();
            String amountPayableStr = payCharge;//应付
            //String amountDiscountStr =  jss.getString("discountAmount");//优惠
            //String amountPaidStr = jss.getString("charge");//实付
            String inTime = parklotFeeResult.getDatas().getInTime();//取得订单开始时间
            String getTimes = parklotFeeResult.getDatas().getGetTimes();//取得订单结束时间,

            // 判断parkingId和blueParkingId是否匹配
            Villageinfo villageinfo = new Villageinfo();
            villageinfo.setItem01(blueCardParkId);
            villageinfo.setId(parkingId);
            List<Villageinfo> list = villageinfoService.selectList(villageinfo);
            if (list == null || list.size() == 0) {
                throw new Exception("车场id和蓝卡id不匹配");
            }

            OrderMain orderMain = new OrderMain();
            Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_ORDER_MAIN);
            int amountPayable = 0;
            int amountDiscount = 0;
            int amountPaid = 0;
            if (null != amountPayableStr) {
                amountPayable = Integer.parseInt(amountPayableStr);
            }
            amountPaid = amountPayable;
            orderMain.setOrderId((null == parkingEhca.getParkingIdentifier() ? "" : parkingEhca.getParkingIdentifier()) + orderType + sequence.getId());
            orderMain.setOrderType(orderType);
            orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_UNPAY);
            orderMain.setInvoiceStatus("0");
            orderMain.setCustomerId(customerId);
            orderMain.setAmountPayable(amountPayable);
            orderMain.setAmountDiscount(amountDiscount);
            orderMain.setAmountPaid(amountPaid < 0 ? 0 : amountPaid);
            orderMain.setIsUsed(Constants.TRUE);
            orderMain.setCreateor("admin");
            orderMain.setCreateDate(new Date());
            try {
                orderMainDao.insert(orderMain);
            } catch (Exception e) {
                e.printStackTrace();
                mess = ShangAnMessageType.toShangAnJson("2", "数据异常", "order", "");
                return mess;
            }
            OrderTemporary orderTemporary = new OrderTemporary();
            orderTemporary.setParkingId(parkingId);
            orderTemporary.setOrderId((null == parkingEhca.getParkingIdentifier() ? "" : parkingEhca.getParkingIdentifier()) + orderType + sequence.getId());
            orderTemporary.setCarNumber(carNumber);
            orderTemporary.setBlueParkingId(blueCardParkId);
            orderTemporary.setBlueParkingName(blueCardParkName);
            orderTemporary.setBlueOrderId(blueCardOrderId);
            try {
                orderTemporary.setBeginDate(DateUtil.str2date(inTime, DateUtil.DATETIME_FORMAT));
                orderTemporary.setEndDate(DateUtil.str2date(getTimes, DateUtil.DATETIME_FORMAT));
            } catch (ParseException e) {
                e.printStackTrace();
                mess = ShangAnMessageType.toShangAnJson("2", "数据异常", "order", "");
                return mess;
            }
            orderTemporary.setCarType(carType);
            orderTemporary.setIsUsed(Constants.TRUE);
            orderTemporary.setCreateor("admin");
            orderTemporary.setCreateDate(new Date());
            try {
                orderTemporaryDao.insert(orderTemporary);
            } catch (Exception e) {
                e.printStackTrace();
                mess = ShangAnMessageType.toShangAnJson("2", "临停表插入异常", "order", "");
                return mess;
            }
            Map<String, Object> orderMap = new HashMap<String, Object>();
            orderMap.put("orderId", orderMain.getOrderId());
            orderMap.put("orderType", orderMain.getOrderType());
            orderMap.put("orderStatus", orderMain.getOrderStatus());
            orderMap.put("amountPayable", orderMain.getAmountPayable() / 100);//应付
            orderMap.put("amountDiscount", amountDiscount / 100);//优惠
            orderMap.put("amountPaid", amountPaid / 100);//实付
            orderMap.put("beginDate", inTime);
            orderMap.put("endDate", getTimes);

            mess = ShangAnMessageType.toShangAnJson("0", "临停订单创建成功", "order", orderMap);
        } else {
            mess = ShangAnMessageType.toShangAnJson("1", "无数据", "order", "");
        }
        return mess;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    @SystemLog(logType = LogTypeEnum.ORDER_INFO, logSummary = "创建优惠临停订单")
    public String ordercTemporaryShare(Map<String, Object> param) {
        String mess = null;
        String parkingId = (String) param.get("parkingId");
        String customerId = (String) param.get("customerId");
        String orderType = (String) param.get("orderType");
        String carNumber = (String) param.get("carNumber");
        String packageId = (String) param.get("packageId");

        String appointmentDate = (String) param.get("appointmentDate");
        Parking parkingEhca = new Parking();
        if (ehCache.get("t_parking" + parkingId) != null && ehCache.get("t_parking" + parkingId).getObjectValue() != null) {
            parkingEhca = (Parking) ehCache.get("t_parking" + parkingId).getObjectValue();
        } else {
            parkingEhca = parkingService.queryById(parkingId);
            ehCache.put(new Element("t_parking" + parkingId, parkingEhca));
        }

        boolean flag = true;
        OrderTemporaryShare os = new OrderTemporaryShare();
        os.setParkingId(parkingId);
        os.setCarNumber(carNumber);
        List<OrderTemporaryShare> otsList = orderTemporaryShareService.queryDate(os);
        for (OrderTemporaryShare orderTemporaryShare1 : otsList) {
            if (!StringUtils.isEmpty(orderTemporaryShare1.getAppointmentDate())) {
                if (!StringUtils.isEmpty(packageId)) {
                    String week = DateUtil.getDayOfWeek(new Date(), 0);
                    PackagePrice packagePrice = packagePriceService.queryById(Integer.parseInt(packageId));
                    String[] weeks = packagePrice.getWeek().split(",");
                    for (int i = 0; i < weeks.length; i++) {
                        String intWeek = DateUtil.getIntOfWeek(weeks[i]);
                        int n = Integer.parseInt(intWeek) - Integer.parseInt(week);
                        Date date = null;
                        if (n >= 0) {
                            date = DateUtil.getPreOrNextDate(new Date(), n);
                        } else {
                            date = DateUtil.getPreOrNextDate(new Date(), n + 7);
                        }
                        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                        if (orderTemporaryShare1.getAppointmentDate().contains(dateStr)) {
                            flag = false;
                            mess = ShangAnMessageType.toShangAnJson("3", "不能重复预约" + dateStr, "order", "");
                            break;
                        }
                    }

                } else {
                    if (!StringUtils.isEmpty(appointmentDate)) {
                        if (appointmentDate.trim().length() < 2) {//v1.3.8
                            String week = DateUtil.getDayOfWeek(new Date(), 0);
                            int n = Integer.parseInt(appointmentDate) - Integer.parseInt(week);
                            Date date = null;
                            if (n >= 0) {
                                date = DateUtil.getPreOrNextDate(new Date(), n);
                            } else {
                                date = DateUtil.getPreOrNextDate(new Date(), n + 7);
                            }
                            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                            if (orderTemporaryShare1.getAppointmentDate().contains(dateStr)) {
                                flag = false;
                                mess = ShangAnMessageType.toShangAnJson("3", "不能重复预约" + dateStr, "order", "");
                                break;
                            }
                        } else {
                            // 预约指定日期
                            if (orderTemporaryShare1.getAppointmentDate().contains(appointmentDate)) {
                                flag = false;
                                mess = ShangAnMessageType.toShangAnJson("3", "不能重复预约" + appointmentDate, "order", "");
                                break;
                            }
                        }
                    } else {
                        // nothing
                    }
                }
            } else {
                // nothing
            }
        }
        if (flag) {
            Parking parking = parkingDao.queryById(parkingId);
            if (parking != null && customerId != null) {
                OrderMain orderMain = new OrderMain();
                Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_ORDER_MAIN);
                int amountPayable = 0;
                int amountDiscount = 0;
                int amountPaid = 0;
                String identity = "0";//默认会员身份
                //查询用户身份
                Customer cus = new Customer();
                cus.setCustomerId(customerId);
                List<Customer> cuList = customerDao.selectList(cus);
                if (null == cuList || cuList.size() == 0) {
                    logger.info("优惠停车------------用户不存在");
                    return ShangAnMessageType.operateToJson("2", "用户不存在");
                } else {
                    identity = cuList.get(0).getIdentity();
                }
                if (!StringUtils.isEmpty(packageId)) {
                    PackagePrice packagePrice = packagePriceService.queryById(Integer.parseInt(packageId));
                    if (packagePrice != null && !packagePrice.equals("")) {
                        amountPayable = packagePrice.getPrice();
                    }
                } else {
                    if (!StringUtils.isEmpty(appointmentDate)) {
                        if (appointmentDate.trim().length() < 2) {//v1.3.8
                            DiscountParkingPrice discountParkingPrice = discountParkingPriceService.queryById(parkingId);
                            if (discountParkingPrice != null && !discountParkingPrice.equals("")) {
                                if (appointmentDate.equals("1")) {
                                    amountPayable = discountParkingPrice.getMondayPrice();
                                } else if (appointmentDate.equals("2")) {
                                    amountPayable = discountParkingPrice.getTuesdayPrice();
                                } else if (appointmentDate.equals("3")) {
                                    amountPayable = discountParkingPrice.getWednesdayPrice();
                                } else if (appointmentDate.equals("4")) {
                                    amountPayable = discountParkingPrice.getThursdayPrice();
                                } else if (appointmentDate.equals("5")) {
                                    amountPayable = discountParkingPrice.getFridayPrice();
                                } else if (appointmentDate.equals("6")) {
                                    amountPayable = discountParkingPrice.getSaturdayPrice();
                                } else if (appointmentDate.equals("7")) {
                                    amountPayable = discountParkingPrice.getSundayPrice();
                                }
                            }
                        } else {
                            //  0：会员1：vip
                            //1为vip
                            if ("1".equals(identity)) {
                                if (parking.getVipSharePrice() != null) {
                                    amountPayable = (int) (parking.getVipSharePrice() * 100);//停车场钱以元为单位   订单以分为单位
                                } else {
                                    amountPayable = (int) (parking.getSharePrice() * 100);//停车场钱以元为单位   订单以分为单位
                                }

                            } else {
                                amountPayable = (int) (parking.getSharePrice() * 100);//停车场钱以元为单位   订单以分为单位
                            }
                        }
                    } else {
                        //  0：会员1：vip
                        //1为vip
                        if ("1".equals(identity)) {
                            if (parking.getVipSharePrice() != null) {
                                amountPayable = (int) (parking.getVipSharePrice() * 100);//停车场钱以元为单位   订单以分为单位
                            } else {
                                amountPayable = (int) (parking.getSharePrice() * 100);//停车场钱以元为单位   订单以分为单位
                            }

                        } else {
                            amountPayable = (int) (parking.getSharePrice() * 100);//停车场钱以元为单位   订单以分为单位
                        }
                    }

                }

                amountPaid = amountPayable;
                orderMain.setOrderId((null == parkingEhca.getParkingIdentifier() ? "" : parkingEhca.getParkingIdentifier()) + orderType + sequence.getId());
                orderMain.setOrderType(orderType);
                orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_UNPAY);
                orderMain.setInvoiceStatus("0");
                orderMain.setCustomerId(customerId);
                orderMain.setAmountPayable(amountPayable);
                orderMain.setAmountDiscount(amountDiscount);
                orderMain.setAmountPaid(amountPaid < 0 ? 0 : amountPaid);
                orderMain.setIsUsed(Constants.TRUE);
                orderMain.setCreateor("admin");
                orderMain.setCreateDate(new Date());
                try {
                    orderMainDao.insert(orderMain);
                } catch (Exception e) {
                    logger.error("", e);
                    logger.error("订单插入失败");
                    mess = ShangAnMessageType.toShangAnJson("2", "数据异常", "order", "");
                    return mess;
                }
                OrderTemporaryShare orderTemporaryShare = new OrderTemporaryShare();
                orderTemporaryShare.setParkingId(parkingId);
                orderTemporaryShare.setOrderId((null == parkingEhca.getParkingIdentifier() ? "" : parkingEhca.getParkingIdentifier()) + orderType + sequence.getId());
                orderTemporaryShare.setIsUsed(Constants.TRUE);
                orderTemporaryShare.setCreateor("admin");
                orderTemporaryShare.setCreateDate(new Date());
                orderTemporaryShare.setCarNumber(carNumber);
                orderTemporaryShareDao.insert(orderTemporaryShare);

                Map<String, Object> orderMap = new HashMap<String, Object>();
                orderMap.put("orderId", orderMain.getOrderId());
                orderMap.put("orderType", orderMain.getOrderType());
                orderMap.put("orderStatus", orderMain.getOrderStatus());
                orderMap.put("amountPayable", orderMain.getAmountPayable() / 100);//应付
                orderMap.put("amountDiscount", amountDiscount / 100);//优惠
                orderMap.put("amountPaid", amountPaid / 100);//实付

                mess = ShangAnMessageType.toShangAnJson("0", "临停订单创建成功", "order", orderMap);
            } else {
                logger.info("车场或用户ID不存在");
                mess = ShangAnMessageType.toShangAnJson("1", "车场或用户ID不存在", "order", "");
            }
        }
        return mess;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    @SystemLog(logType = LogTypeEnum.ORDER_INFO, logSummary = "创建月租订单")
    public String ordercMonthly(Map<String, Object> param) {
        String mess = null;
        String parkingId = (String) param.get("parkingId");
        String customerId = (String) param.get("customerId");
        String orderType = (String) param.get("orderType");
        String carNumber = (String) param.get("carNumber");

        Monthlyparkinginfo entity = new Monthlyparkinginfo();
        entity.setCarNumber(carNumber);
        entity.setVillageId(parkingId);
        entity.setIsUsed(Constants.TRUE);
        List<Monthlyparkinginfo> monList = monthlyparkinginfoDao.selectList(entity);
        if(monList==null||monList.size()==0){
            return ShangAnMessageType.operateToJson("1", "该月租车不存在");
        }
        entity = monList.get(0);
        String beginDate = (String) param.get("beginDate");
        String beginDate1 = DateUtil.date2str(DateUtil.getPreOrNextDate(entity.getEffect_end_time(), 1), DateUtil.DATE_FORMAT);
        if (!beginDate.equals(beginDate1)) {
            return ShangAnMessageType.operateToJson("1", "请检查您的缴费日期");
        }

        String invoiceId = (String) param.get("invoiceId");
        int monthNum = Integer.parseInt((String) param.get("monthNum"));
        String endTime = null;
        Parking parkingEhca = new Parking();
        if (ehCache.get("t_parking" + parkingId) != null && ehCache.get("t_parking" + parkingId).getObjectValue() != null) {
            parkingEhca = (Parking) ehCache.get("t_parking" + parkingId).getObjectValue();
        } else {
            parkingEhca = parkingService.queryById(parkingId);
            ehCache.put(new Element("t_parking" + parkingId, parkingEhca));
        }
        try {
            Date beginTime = DateUtil.str2date(beginDate, DateUtil.DATE_FORMAT);
            endTime = DateUtil.getMonthStartAndEndDate(DateUtil.getPreOrNextMonth(beginTime, monthNum - 1))[1];
            //endD=DateUtil.str2date(endTime, DateUtil.DATE_FORMAT);
        } catch (ParseException e1) {
            logger.error("", e1);
            mess = ShangAnMessageType.toShangAnJson("1", "日期转换错误", "order", "");
            return mess;
        }
        Monthlyparkinginfo monthlyparkinginfo = new Monthlyparkinginfo();
        monthlyparkinginfo.setCarNumber(carNumber);
        monthlyparkinginfo.setVillageId(parkingId);
        List<Monthlyparkinginfo> list = monthlyparkinginfoDao.selectList(monthlyparkinginfo);
        if (list != null && list.size() > 0) {
            Monthlyparkinginfo monthlyparkinginfo2 = list.get(0);
            if (monthlyparkinginfo2.getMax_date() != null) {
                String maxDate = DateUtil.date2str(monthlyparkinginfo2.getMax_date(), DateUtil.DATE_FORMAT);
                if (endTime.compareTo(maxDate) > 0) {
                    //不添加记录
                    mess = ShangAnMessageType.toShangAnJson("1", "付款超出范围", "order", "");
                    return mess;
                }
            }
            OrderMain orderMain = new OrderMain();
            Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_ORDER_MAIN);
            int amountPayable = 0;
            int amountDiscount = 0;
            int amountPaid = 0;
            long monthlyRentalPrice = monthlyparkinginfo2.getMonthlyRentalPrice();
            //5为月租单价的临时数据  需要从蓝卡云获得月租单价
            amountPayable = ((int) (monthlyRentalPrice) * monthNum * 100);//停车场钱以元为单位   订单以分为单位
            amountPaid = amountPayable;
            orderMain.setOrderId((null == parkingEhca.getParkingIdentifier() ? "" : parkingEhca.getParkingIdentifier()) + orderType + sequence.getId());
            //  StringUtils.defaultString(parkingEhca.getParkingIdentifier(),"");
            orderMain.setOrderType(orderType);
            orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_UNPAY);
            orderMain.setInvoiceStatus("0");
            orderMain.setCustomerId(customerId);
            orderMain.setAmountPayable(amountPayable);
            orderMain.setAmountDiscount(amountDiscount);
            orderMain.setAmountPaid(amountPaid < 0 ? 0 : amountPaid);
            orderMain.setIsUsed(Constants.TRUE);
            orderMain.setCreateor("admin");
            orderMain.setCreateDate(new Date());
            if (!StringUtils.isEmpty(invoiceId)) {
                orderMain.setIsNeedInvoice("1");
            }

            try {
                orderMainDao.insert(orderMain);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e);
                mess = ShangAnMessageType.toShangAnJson("2", "添加订单失败", "order", "");
                return mess;
            }
            OrderMonthly orderMonthly = new OrderMonthly();
            orderMonthly.setParkingId(parkingId);
            orderMonthly.setOrderId((null == parkingEhca.getParkingIdentifier() ? "" : parkingEhca.getParkingIdentifier()) + orderType + sequence.getId());
            orderMonthly.setMonthNum(monthNum);
            orderMonthly.setCarNumber(carNumber);
            orderMonthly.setPrice((int) (monthlyRentalPrice) * 100);// 单价
            try {
                orderMonthly.setBeginDate(DateUtil.str2date(beginDate, DateUtil.DATE_FORMAT));
                String date = DateUtil.getMonthStartAndEndDate(DateUtil.getPreOrNextMonth(orderMonthly.getBeginDate(), monthNum - 1))[1];
                orderMonthly.setEndDate(DateUtil.str2date(date, DateUtil.DATE_FORMAT));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            orderMonthly.setIsUsed(Constants.TRUE);
            orderMonthly.setCreateor("admin");
            orderMonthly.setCreateDate(new Date());

            orderMonthlyDao.insert(orderMonthly);
            Map<String, Object> orderMap = new HashMap<String, Object>();
            orderMap.put("orderId", orderMain.getOrderId());
            orderMap.put("orderType", orderMain.getOrderType());
            orderMap.put("orderStatus", orderMain.getOrderStatus());
            orderMap.put("amountPayable", orderMain.getAmountPayable() / 100);//应付
            orderMap.put("amountDiscount", amountDiscount / 100);//优惠
            orderMap.put("amountPaid", amountPaid / 100);//实付

            mess = ShangAnMessageType.toShangAnJson("0", "月租订单创建成功", "order", orderMap);
            //订单ID更新到发票表
            if (!StringUtils.isEmpty(invoiceId)) {
                updateInvoiceOrderId(invoiceId, orderMain.getOrderId());
            }
        }
        return mess;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    @SystemLog(logType = LogTypeEnum.ORDER_INFO, logSummary = "创建产权订单")
    public String ordercEquity(Map<String, Object> param) {
        String mess = null;
        String parkingId = (String) param.get("parkingId");
        String customerId = (String) param.get("customerId");
        String orderType = (String) param.get("orderType");
        String carNumber = (String) param.get("carNumber");

        Propertyparkinginfo entity = new Propertyparkinginfo();
        entity.setCarNumber(carNumber);
        entity.setVillageId(parkingId);
        List<Propertyparkinginfo> prList = propertyparkinginfoDao.selectList(entity);
        if(prList==null || prList.size()==0){
            return ShangAnMessageType.operateToJson("1", "该月租车不存在");
        }
        entity = prList.get(0);
        String beginDate = (String) param.get("beginDate");
        String beginDate1 = DateUtil.date2str(DateUtil.getPreOrNextDate(entity.getEffect_end_time(), 1), DateUtil.DATE_FORMAT);
        if (!beginDate.equals(beginDate1)) {
            return ShangAnMessageType.operateToJson("1", "请检查您的缴费日期");
        }

        String invoiceId = (String) param.get("invoiceId");
        int monthNum = Integer.parseInt((String) param.get("monthNum"));
        String endTime = null;
        Parking parkingEhca = new Parking();
        if (ehCache.get("t_parking" + parkingId) != null && ehCache.get("t_parking" + parkingId).getObjectValue() != null) {
            parkingEhca = (Parking) ehCache.get("t_parking" + parkingId).getObjectValue();
        } else {
            parkingEhca = parkingService.queryById(parkingId);
            ehCache.put(new Element("t_parking" + parkingId, parkingEhca));
        }
        try {
            Date beginTime = DateUtil.str2date(beginDate, DateUtil.DATE_FORMAT);
            endTime = DateUtil.getMonthStartAndEndDate(DateUtil.getPreOrNextMonth(beginTime, monthNum - 1))[1];
            //endD=DateUtil.str2date(endTime, DateUtil.DATE_FORMAT);
        } catch (ParseException e1) {
            logger.error("", e1);
            mess = ShangAnMessageType.toShangAnJson("1", "日期转换错误", "order", "");
            return mess;
        }
        Propertyparkinginfo propertyparkinginfo = new Propertyparkinginfo();
        propertyparkinginfo.setCarNumber(carNumber);
        propertyparkinginfo.setVillageId(parkingId);
        List<Propertyparkinginfo> list = propertyparkinginfoDao.selectList(propertyparkinginfo);
        if (list != null && list.size() > 0) {
            Propertyparkinginfo propertyparkinginfo2 = list.get(0);
            if (propertyparkinginfo2.getMax_date() != null) {
                String maxDate = DateUtil.date2str(propertyparkinginfo2.getMax_date(), DateUtil.DATE_FORMAT);
                if (endTime.compareTo(maxDate) > 0) {
                    //不添加记录
                    mess = ShangAnMessageType.toShangAnJson("1", "付款超出范围", "order", "");
                    return mess;
                }
            }
            OrderMain orderMain = new OrderMain();
            Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_ORDER_MAIN);
            int amountPayable = 0;
            int amountDiscount = 0;
            int amountPaid = 0;
            long managementFeeMonthlyUnit = propertyparkinginfo2.getManagementFeeMonthlyUnit();
            //5为月租单价的临时数据  需要从蓝卡云获得月租单价
            amountPayable = ((int) (managementFeeMonthlyUnit) * monthNum * 100);//停车场钱以元为单位   订单以分为单位
            amountPaid = amountPayable;
            orderMain.setOrderId((null == parkingEhca.getParkingIdentifier() ? "" : parkingEhca.getParkingIdentifier()) + orderType + sequence.getId());
            orderMain.setOrderType(orderType);
            orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_UNPAY);
            orderMain.setInvoiceStatus("0");
            orderMain.setCustomerId(customerId);
            orderMain.setAmountPayable(amountPayable);
            orderMain.setAmountDiscount(amountDiscount);
            orderMain.setAmountPaid(amountPaid < 0 ? 0 : amountPaid);
            orderMain.setIsUsed(Constants.TRUE);
            orderMain.setCreateor("admin");
            orderMain.setCreateDate(new Date());
            if (!StringUtils.isEmpty(invoiceId)) {
                orderMain.setIsNeedInvoice("1");
            }

            try {
                orderMainDao.insert(orderMain);
            } catch (Exception e) {
                e.printStackTrace();
                mess = ShangAnMessageType.toShangAnJson("2", "添加订单失败", "order", "");
                return mess;
            }
            OrderEquity orderEquity = new OrderEquity();
            orderEquity.setParkingId(parkingId);
            orderEquity.setOrderId((null == parkingEhca.getParkingIdentifier() ? "" : parkingEhca.getParkingIdentifier()) + orderType + sequence.getId());
            orderEquity.setPrice((int) (managementFeeMonthlyUnit) * 100);//临时
            try {
                orderEquity.setBeginDate(DateUtil.str2date(beginDate, DateUtil.DATE_FORMAT));
                String date = DateUtil.getMonthStartAndEndDate(DateUtil.getPreOrNextMonth(orderEquity.getBeginDate(), monthNum - 1))[1];
                orderEquity.setEndDate(DateUtil.str2date(date, DateUtil.DATE_FORMAT));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            orderEquity.setMonthNum(monthNum);
            orderEquity.setCarNumber(carNumber);
            orderEquity.setIsUsed(Constants.TRUE);
            orderEquity.setCreateor("admin");
            orderEquity.setCreateDate(new Date());
            orderEquityDao.insert(orderEquity);

            Map<String, Object> orderMap = new HashMap<String, Object>();
            orderMap.put("orderId", orderMain.getOrderId());
            orderMap.put("orderType", orderMain.getOrderType());
            orderMap.put("orderStatus", orderMain.getOrderStatus());
            orderMap.put("amountPayable", orderMain.getAmountPayable() / 100);//应付
            orderMap.put("amountDiscount", amountDiscount / 100);//优惠
            orderMap.put("amountPaid", amountPaid / 100);//实付

            mess = ShangAnMessageType.toShangAnJson("0", "产权订单创建成功", "order", orderMap);
            //订单ID更新到发票表
            if (!StringUtils.isEmpty(invoiceId)) {
                updateInvoiceOrderId(invoiceId, orderMain.getOrderId());
            }
        }
        return mess;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    @SystemLog(logType = LogTypeEnum.ORDER_INFO, logSummary = "创建加油卡订单")
    public String ordercCarlife(Map<String, Object> param) throws Exception {
        String mess = null;
        String customerId = param.get("customerId").toString(); // 客户号
        String orderType = param.get("orderType").toString(); // 订单类型
        Integer cardId = Integer.valueOf(param.get("cardId").toString()); // 加油卡ID
        Integer topupNum = Integer.valueOf(param.get("topupNum").toString()); // 充值数量
        String topupType = param.get("topupType").toString(); // 充值类型
        String amountPayable = param.get("amountPayable").toString(); // 应付金额
        String amountDiscount = param.get("amountDiscount").toString(); // 优惠金额

        // 查询客户的加油卡信息
        CarlifeRefuelCard carlifeRefuelCard = new CarlifeRefuelCard();
        carlifeRefuelCard.setIsUsed(Constants.TRUE);
        carlifeRefuelCard.setCustomerId(customerId);
        carlifeRefuelCard.setId(cardId);
        List<CarlifeRefuelCard> list = carlifeRefuelCardDao.selectList(carlifeRefuelCard);
        if (list == null || list.size() == 0) {
            mess = ShangAnMessageType.toShangAnJson("1", "无数据", "order", "");
            return mess;
        }

        // 判断传入的充值类型和加油卡卡号是否匹配
        boolean flag = false;
        carlifeRefuelCard = list.get(0);
        if ((topupType.equals("10000") || topupType.equals("10001") || topupType.equals("10003") || topupType.equals("10004") || topupType.equals("10007")) &&
                carlifeRefuelCard.getCardNo().startsWith("100011")) { // 中石化：以100011开头的卡号
            flag = true;
        } else if (topupType.equals("10008") && carlifeRefuelCard.getCardNo().startsWith("9")) { // 中石油：以9开头的卡号
            flag = true;
        }
        if (flag == false) {
            mess = ShangAnMessageType.toShangAnJson("2", "充值类型和加油卡类型不匹配", "order", "");
            return mess;
        }

        // 添加主订单
        Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_ORDER_MAIN);
        OrderMain orderMain = new OrderMain();
        orderMain.setOrderId(sequence.getId());
        orderMain.setOrderType(orderType);
        orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_UNPAY);
        orderMain.setInvoiceStatus("0");
        orderMain.setCustomerId(customerId);
        orderMain.setAmountPayable(new BigDecimal(amountPayable).multiply(new BigDecimal("100")).toBigInteger().intValue());
        orderMain.setAmountDiscount(new BigDecimal(amountDiscount).multiply(new BigDecimal("100")).toBigInteger().intValue());
        Integer amountPaid = orderMain.getAmountPayable() - orderMain.getAmountDiscount();
        orderMain.setAmountPaid(amountPaid < 0 ? 0 : amountPaid);
        orderMain.setIsUsed(Constants.TRUE);
        orderMain.setCreateor("admin");
        orderMain.setCreateDate(new Date());
        orderMainDao.insert(orderMain);

        // 添加加油卡订单
        OrderRefuel orderRefuel = new OrderRefuel();
        orderRefuel.setOrderId(sequence.getId()); // 订单ID(uuid)
        orderRefuel.setCardId(cardId); // 加油卡id
        orderRefuel.setTopupType(topupType); // 充值类型
        orderRefuel.setTopupNum(topupNum); // 充值数量
        orderRefuel.setTopupPrice(orderMain.getAmountPayable()); // 充值单价
        orderRefuel.setOrderCash(0); // 进货价格
        orderRefuel.setTopupStatus("0");  // '充值状态0充值中 1成功 9撤销'
        orderRefuel.setTopupName(""); // 充值名称
        orderRefuel.setSporderId(""); // 商家订单号
        orderRefuel.setIsUsed(Constants.TRUE); // 是否可用
        orderRefuel.setCreateor("admin"); // 创建人
        orderRefuel.setCreateDate(new Date()); // 创建日期
        orderRefuelDao.insert(orderRefuel);

        // 返回值
        Map<String, String> orderMap = new HashMap<String, String>();
        orderMap.put("orderId", orderMain.getOrderId());
        orderMap.put("orderType", orderMain.getOrderType());
        orderMap.put("orderStatus", orderMain.getOrderStatus());
        orderMap.put("amountPayable", amountPayable); // 应付
        orderMap.put("amountDiscount", amountDiscount); // 优惠
        orderMap.put("amountPaid", moneyFormat(orderMain.getAmountPaid())); // 实付
        mess = ShangAnMessageType.toShangAnJson("0", "加油卡订单创建成功", "order", orderMap);
        return mess;
    }

    public String moneyFormat(int num) {
        String result = String.format("%03d", num);
        int index = result.length() - 2;
        result = result.substring(0, index) + "." + result.substring(index);
        return result;
    }

    public String getMonPrice(Map<String, Object> param) {
        String parkingId = (String) param.get("parkingId");
        String carNumber = (String) param.get("carNumber");
        String mess = null;
        Map<String, Object> parkingMap = new HashMap<String, Object>();
        Monthlyparkinginfo monthlyparkinginfo = new Monthlyparkinginfo();
        monthlyparkinginfo.setCarNumber(carNumber);
        monthlyparkinginfo.setVillageId(parkingId);
        List<Monthlyparkinginfo> list = monthlyparkinginfoDao.selectList(monthlyparkinginfo);
        Monthlyparkinginfo monthlyparkinginfo2 = null;
        if (list != null && list.size() > 0) {
            monthlyparkinginfo2 = list.get(0);
            parkingMap.put("parkingId", parkingId);
            parkingMap.put("price", monthlyparkinginfo2.getMonthlyRentalPrice());
            mess = ShangAnMessageType.toShangAnJson("0", "获取单价", "parking", parkingMap);
        } else {
            mess = ShangAnMessageType.toShangAnJson("1", "无数据", "parking", "");
        }
        return mess;
    }

    //月租2
    public long getMonPrice2(String carNumber, String parkingId) {
        Monthlyparkinginfo monthlyparkinginfo = new Monthlyparkinginfo();
        monthlyparkinginfo.setCarNumber(carNumber);
        monthlyparkinginfo.setVillageId(parkingId);
        List<Monthlyparkinginfo> list = monthlyparkinginfoDao.selectList(monthlyparkinginfo);
        Monthlyparkinginfo monthlyparkinginfo2 = null;
        long price = 0L;
        if (list != null && list.size() > 0) {
            monthlyparkinginfo2 = list.get(0);
            price = monthlyparkinginfo2.getMonthlyRentalPrice();
        }
        return price;
    }

    //产权
    public String getEquiPrice(Map<String, Object> param) {
        String parkingId = (String) param.get("parkingId");
        String carNumber = (String) param.get("carNumber");
        String mess = null;
        Map<String, Object> parkingMap = new HashMap<String, Object>();
        Propertyparkinginfo propertyparkinginfo = new Propertyparkinginfo();
        propertyparkinginfo.setCarNumber(carNumber);
        propertyparkinginfo.setVillageId(parkingId);
        List<Propertyparkinginfo> list = propertyparkinginfoDao.selectList(propertyparkinginfo);
        Propertyparkinginfo Propertyparkinginfo2 = null;
        if (list != null && list.size() > 0) {
            Propertyparkinginfo2 = list.get(0);
            parkingMap.put("parkingId", parkingId);
            parkingMap.put("price", Propertyparkinginfo2.getManagementFeeMonthlyUnit());
            mess = ShangAnMessageType.toShangAnJson("0", "获取单价", "parking", parkingMap);
        } else {
            mess = ShangAnMessageType.toShangAnJson("1", "无数据", "parking", "");
        }
        return mess;
    }

    public long getEquiPrice2(String carNumber, String parkingId) {
        Propertyparkinginfo propertyparkinginfo = new Propertyparkinginfo();
        propertyparkinginfo.setCarNumber(carNumber);
        propertyparkinginfo.setVillageId(parkingId);
        List<Propertyparkinginfo> list = propertyparkinginfoDao.selectList(propertyparkinginfo);
        Propertyparkinginfo Propertyparkinginfo2 = null;
        long price = 0L;
        if (list != null && list.size() > 0) {
            Propertyparkinginfo2 = list.get(0);

            price = Propertyparkinginfo2.getManagementFeeMonthlyUnit();
        }
        return price;
    }

    @Override
    public String getOrderMoneqList(String customerId, String pageIndex) {
        String mess = null;
        Page<OrderMain> page = new Page<OrderMain>();
        page.getParams().put("customerId", customerId);
        long pageIndex2 = 1;
        if (customerId != null && pageIndex != null) {
            pageIndex2 = (long) Integer.parseInt(pageIndex);
            page.setCurrentPage(pageIndex2);
            page.setPageSize(Page.PAGE_SIZE_10);
            List<OrderMain> list = orderMainDao.queryListByPage(page);
            List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();
            if (list != null && list.size() > 0) {
                for (OrderMain orderMain : list) {
                    Map<String, Object> orderMap = new HashMap<String, Object>();
                    if (orderMain.getOrderType().equals("13")) {
                        orderMap.put("orderTypeName", "月租");
                    } else if (orderMain.getOrderType().equals("14")) {
                        orderMap.put("orderTypeName", "产权");
                    }
                    orderMap.put("orderId", (null != orderMain.getOrderId()) ? orderMain.getOrderId() : "");
                    orderMap.put("orderType", (null != orderMain.getOrderType()) ? orderMain.getOrderType() : "");
                    orderMap.put("orderStatusName", "已付款");
                    orderMap.put("carNumber", (null != orderMain.getCarNumber()) ? orderMain.getCarNumber() : "");
                    orderMap.put("parkingName", (null != orderMain.getParkingName()) ? orderMain.getParkingName() : "");
                    orderMap.put("payTime", (null != orderMain.getPayTime()) ? DateUtil.date2str(orderMain.getPayTime(), DateUtil.DATETIME_FORMAT) : "");
                    orderMap.put("amountPaid", (null != orderMain.getAmountPaid()) ? orderMain.getAmountPaid() / 100 : "");

                    orderList.add(orderMap);
                }
                mess = ShangAnMessageType.toShangAnJson("0", "历史订单", "order", orderList);
            } else {
                mess = ShangAnMessageType.toShangAnJson("1", "无数据", "order", "[]");
            }

        }

        return mess;
    }

    @Override
    public String getOrderTempList(String customerId, String pageIndex) {
        String mess = null;
        Page<OrderMain> page = new Page<OrderMain>();
        page.getParams().put("customerId", customerId);
        long pageIndex2 = 1;
        if (customerId != null && pageIndex != null) {
            pageIndex2 = (long) Integer.parseInt(pageIndex);
            page.setCurrentPage(pageIndex2);
            page.setPageSize(Page.PAGE_SIZE_10);
            List<OrderMain> list = orderMainDao.queryListByTempPage(page);
            List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();
            if (list != null && list.size() > 0) {
                for (OrderMain orderMain : list) {
                    Map<String, Object> orderMap = new HashMap<String, Object>();
                    if (orderMain.getOrderType().equals("11")) {
                        orderMap.put("orderTypeName", "临停缴费");
                    } else if (orderMain.getOrderType().equals("10")) {
                        orderMap.put("orderTypeName", "共享临停");
                    }
                    orderMap.put("orderId", (null != orderMain.getOrderId()) ? orderMain.getOrderId() : "");
                    orderMap.put("orderType", (null != orderMain.getOrderType()) ? orderMain.getOrderType() : "");
                    orderMap.put("orderStatusName", "已付款");
                    orderMap.put("carNumber", (null != orderMain.getCarNumber()) ? orderMain.getCarNumber() : "");
                    orderMap.put("parkingName", (null != orderMain.getParkingName()) ? orderMain.getParkingName() : "");
                    orderMap.put("payTime", (null != orderMain.getPayTime()) ? DateUtil.date2str(orderMain.getPayTime(), DateUtil.DATETIME_FORMAT) : "");
                    orderMap.put("amountPaid", (null != orderMain.getAmountPaid()) ? orderMain.getAmountPaid() / 100 : "");
                    orderList.add(orderMap);
                }
                mess = ShangAnMessageType.toShangAnJson("0", "历史订单", "order", orderList);
            } else {
                mess = ShangAnMessageType.toShangAnJson("1", "无数据", "order", "[]");
            }

        }

        return mess;
    }

    /**
     * 根据传入的ParklotFee对象，处理成json数据，调用post方法发送
     *
     * @param parklotFee
     */
    @Override
    public ParklotFeeResult sendJsonDataToRest(ParklotFee parklotFee) {
        if (StringUtils.isEmpty(parklotFee.getKey())) {
            parklotFee.setKey(this.APIKey);
        }
        logger.info("into sendJsonDataToRest()...");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ParklotFee> request = new HttpEntity<ParklotFee>(parklotFee, requestHeaders);
        try {
            // 返回结果处理
            String resultJson = restTemplate.postForObject(this.parklotFee, request, String.class);

            logger.info("调用蓝卡云算费接口-resultJson:" + resultJson);

            ParklotFeeResult result = mapper.readValue(resultJson, ParklotFeeResult.class);

            logger.info("调用蓝卡云算费接口-result:" + result.toString());

            return result;
        } catch (Exception e) {
            logger.error("停车场算费接口rest远程调用失败:" + e.getMessage());
        }
        return null;
    }


    @Override
    public List<OrderMain> selectListByStatus() {
        return orderMainDao.selectListByStatus();
    }

    @Override
    public List<OrderMain> selectListExcel(OrderMain orderMain) {
        return orderMainDao.selectListExcel(orderMain);
    }

    @Override
    public Page<OrderMain> queryListPageWithCarNumber(Page<OrderMain> page) {
        page.setResultList(orderMainDao.queryListPageWithCarNumber(page));
        return page;
    }

    @Override
    public List<OrderMain> selectListExcelWithCarNumber(OrderMain orderMain) {
        return orderMainDao.selectListExcelWithCarNumber(orderMain);
    }

    @Override
    public List<OrderMain> queryMonthlyEquityListPage(Page<OrderMain> page) {
        return orderMainDao.queryMonthlyEquityListPage(page);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
	@SystemLog(logType=LogTypeEnum.ORDER_INFO,logSummary="取消订单")
    public String cancelOrder(String orderId) throws Exception {
        //String msg = null;
        // 查询订单
        OrderMain orderMain = this.queryById(orderId);
        if (orderMain == null) {
            return ShangAnMessageType.operateToJson("1", "修改失败，不存在该订单");
        }
        if (orderMain.getOrderType().equals(OrderConstants.ORDER_TYPE_PARK)
                && !orderMain.getOrderStatus().equals(OrderConstants.ORDER_STATUS_BESPOKE)) {
            return ShangAnMessageType.operateToJson("1", "修改失败，只有预约中的代泊订单才能取消");
        }
//        if (!orderMain.getOrderStatus().equals("10")) { // 10 表示未付款
//            return ShangAnMessageType.operateToJson("1", "修改失败，只有未付款的订单才可以取消");
//        }
        // 修改订单状态
        orderMain.setOrderStatus("12"); // 12表示已取消
        orderMain.setAmountDiscount(0);
        orderMain.setAmountPaid(orderMain.getAmountPayable());
        this.update(orderMain);
        // 代泊取消订单把扣除的车位数加回去
        if (orderMain.getOrderType().equals(OrderConstants.ORDER_TYPE_PARK)) {
            OrderPark orderPark = orderParkService.queryByOrderId(orderId);
            parkingService.updateParkingCanUse(orderPark.getTargetParkingId(), 1);
        }
        // 查询订单下的优惠券
        Coupon coupon = new Coupon();
        coupon.setCouponOrder(orderId);
        List<Coupon> couponList = couponService.queryList(coupon);
        // 解绑优惠券
        if (couponList != null && couponList.size() > 0) {
            for (Coupon item : couponList) {
                item.setCouponOrder("");
                item.setUseTime("");
                item.setCouponStatus("100201");
            }
            couponService.batchUpdate(couponList);
        }
        return ShangAnMessageType.operateToJson("0", "修改成功");
    }

    /**
     * 定时任务处理同步白名单
     * 1、同步特殊车辆
     * 2、月租车辆
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void synWhiteList(Map<String, Object> param) {
        String parkingStr = null;
        if (null != param) {
            parkingStr = (String) param.get("parkingStr");

        } else {//定时任务，取数据库参数
            WhitesynInfo wsParam = new WhitesynInfo();
            wsParam.setName("syn_parking_str");
            List<WhitesynInfo> wList = whitesynInfoDao.selectList(wsParam);
            if (null != wList && wList.size() > 0) {
                parkingStr = wList.get(0).getValue();
            }
        }
        String[] pkStr = parkingStr.split("\\,");
        if (null != pkStr && pkStr.length > 0) {
            parkingStr = "";
            for (int i = 0; i < pkStr.length; i++) {
                parkingStr += "'";
                parkingStr += pkStr[i];
                parkingStr += "'";
                parkingStr += ",";
            }
            parkingStr = parkingStr.substring(0, parkingStr.length() - 1);
        } else {
            parkingStr = null;
        }

        // 存放以停车场编号为KEY，全部车位存放set
        Map<String, Set<RegisterUserSyncData>> map = new LinkedHashMap<String, Set<RegisterUserSyncData>>();
        // 不分停车场编号，全部存入set集合,区分同相同车位的条件是：小区ID和车牌号
        Set<RegisterUserSyncData> allSet = new LinkedHashSet<RegisterUserSyncData>();
        //特殊车辆
        Specialparkinginfo speParam = new Specialparkinginfo();
        speParam.setParkingStr(parkingStr);
        List<Specialparkinginfo> specialList = specialparkinginfoDao.findByVillageIdForUserSync(speParam);
        if ((specialList != null) && (specialList.size() > 0)) {
            for (Specialparkinginfo spi : specialList) {
                RegisterUserSyncData rspe = new RegisterUserSyncData(spi);
                //rspe.setExpiredTime("2016-03-08");
                allSet.add(rspe);

            }
        }


        // 月租
        Monthlyparkinginfo monthParam = new Monthlyparkinginfo();
        monthParam.setParkingStr(parkingStr);
        List<Monthlyparkinginfo> monthList = monthlyparkinginfoDao.findByVillageIdForUserSync(monthParam);
        //月租加入set
        if ((monthList != null) && (monthList.size() > 0)) {
            for (Monthlyparkinginfo mpi : monthList) {
                if (null != mpi.getEffect_begin_time() && null != mpi.getEffect_end_time()) {
                    allSet.add(new RegisterUserSyncData(mpi));
                }
            }
        }

        // 产权
        Propertyparkinginfo proParam = new Propertyparkinginfo();
        proParam.setParkingStr(parkingStr);
        List<Propertyparkinginfo> manageList = propertyparkinginfoDao.findByVillageIdForUserSync(proParam);
        if ((manageList != null) && (manageList.size() > 0)) {
            for (Propertyparkinginfo ppi : manageList) {
                if (null != ppi.getEffect_begin_time() && null != ppi.getEffect_end_time()) {
                    allSet.add(new RegisterUserSyncData(ppi));
                }
            }
        }


        // set集合里面存放的是各个小区的月租，产权，特殊车位信息
        // 遍历set集合，整合到以停车场编号为key的map集合面去，
        for (RegisterUserSyncData rusd : allSet) {
            // 停车场编号停车场编号
            String parkCode = rusd.getParkCode();
            logger.info("parkCode=" + parkCode + ",datas===" + rusd);
            if (map.get(parkCode) == null) {
                Set<RegisterUserSyncData> tempSet = new HashSet<RegisterUserSyncData>();
                tempSet.add(rusd);
                map.put(parkCode, tempSet);
            } else {
                map.get(parkCode).add(rusd);
            }
        }
        // 将集合置空
        allSet = null;
        // 根据map的停车场编号来同步数据
        for (String mapKey : map.keySet()) {
            // 把集合处理成json字符串之后并加密
            String jsonStr = parseObjecToJsonString(map.get(mapKey));
            logger.info("RegisterUserSyncData TO Json = " + jsonStr);
            //插入白名单同步记录表
            WhitesynRecord whitesynRecord = new WhitesynRecord();
            whitesynRecord.setIsUsed(Constants.TRUE);
            whitesynRecord.setCreateor("admin");
            whitesynRecord.setParkingInfo(jsonStr);
            whitesynRecord.setCreateDate(new Date());
            String datas = parseEncryption(jsonStr);
            // 调用此方法请求服务器
            sendJsonDataToRest(new RegisterUserSync(APIKey, mapKey, datas), whitesynRecord);

            logger.info("key=" + APIKey + ",parkCode=" + mapKey + ",datas=" + datas);
        }

        logger.info("out parseRegisterUserSync() end");
    }

    /**
     * 把传入的集合处理成json字符串
     *
     * @param dataList
     * @return
     */
    private String parseObjecToJsonString(Object dataList) {
        logger.info("转换成json数据");
        try {
            return mapper.writeValueAsString(dataList);
        } catch (JsonProcessingException e) {
            logger.error("转换成json数据失败");
        }
        return null;
    }

    /**
     * 对字符串按要求进行加密
     *
     * @param data
     * @return
     */
    private String parseEncryption(String data) {

        // 加密后保存的数据的变量
        String encryptDate = null;
        try {
            // AES对称加密
            String aesKey = AESKey;
            encryptDate = AESSecurityUtil.encrypt(data, aesKey);
            logger.info("加密成功");
        } catch (Exception e) {
            logger.error("加密失败");
        }
        return encryptDate;
    }

    /**
     * 根据传入的RegisterUserSync对象，处理成json数据，调用post方法发送
     *
     * @param regUserSync
     */
    private String sendJsonDataToRest(RegisterUserSync regUserSync, WhitesynRecord whitesynRecord) {
        String isSucess = "0";//0：失败 1：成功
        logger.info("into sendJsonDataToRest()...");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegisterUserSync> request = new HttpEntity<RegisterUserSync>(regUserSync, requestHeaders);
        try {
            // 返回结果处理
            String resultJson = restTemplate.postForObject(registerUserSync, request, String.class);
            SyncResult result = mapper.readValue(resultJson, SyncResult.class);
            if (result.getStatus().equals("fail")) {
                String mess = "白名单同步同步失败,原因 : " + ErrorCodeEnum.getErrorDesc(result.getErrorCode()) + " 同步数据 >>> "
                        + regUserSync;
                logger.info(mess);
                if (mess.length() > 1000) {
                    mess = mess.substring(0, 1000);
                }
                whitesynRecord.setCallbackInfo(mess);
                whitesynRecordDao.insert(whitesynRecord);
            } else {
                String mess = "白名单同步同步成功, " + " 同步数据 >>> " + regUserSync;
                isSucess = "1";
                logger.info(mess);
                if (mess.length() > 1000) {
                    mess = mess.substring(0, 1000);
                }
                whitesynRecord.setCallbackInfo(mess);
                whitesynRecordDao.insert(whitesynRecord);
            }
            logger.info(result.toString());
        } catch (Exception e) {
            String mess = "白名单同步接口rest远程调用失败:" + e.getMessage();
            logger.error(mess, e);
            whitesynRecord.setCallbackInfo(mess);
            whitesynRecordDao.insert(whitesynRecord);
        }
        return isSucess;
    }

    @Override
    public Page<OrderMain> rechargelist(Page<OrderMain> page) {
        page.setResultList(orderMainDao.rechargelist(page));
        return page;
    }

    @Override
    public List<OrderMain> queryListPage2(Object page) {
        return orderMainDao.queryListPage2(page);
    }
    @Override
    public List<OrderMain> queryListPage3(Object page) {
        return orderMainDao.queryListPage3(page);
    }
    @Override
    public List<OrderMain> queryListPage4(Object page) {
        return orderMainDao.queryListPage4(page);
    }
    /**
     * 给业主和管理员发送提示短信
     *
     * @param orderMain
     */
    public void sendToUserAndMa(OrderMain orderMain) {
        //给业主发短信
        logger.info("---------------------给业主发短信，用户ID=" + orderMain.getCustomerId());
        if (null != orderMain.getCustomerId()) {
            Customer cus = new Customer();
            cus.setCustomerId(orderMain.getCustomerId());
            List<Customer> omList = customerService.selectList(cus);
            logger.info(omList.size() + "用户");
            if (null != omList && omList.size() > 0 && null != omList.get(0).getCustomerMobile()) {
                //月租短信内容
                if (OrderConstants.ORDER_TYPE_MONTHLY.equals(orderMain.getOrderType())) {
                    //发送短信
                    String content = "【口袋停】您的月租车位费已缴纳成功,系统到帐时间约为12小时,在此期间如遇到出入问题,可向收费员出示订单付款凭证。";
                    String content1 = content + orderMain.getOrderId();
                    String[] mobile = new String[1];
                    mobile[0] = omList.get(0).getCustomerMobile();
                    logger.info("给用户发送短信" + mobile + content);
                    SingletonClient.getInstance().sendMessage(mobile, content, 5);
                } else if (OrderConstants.ORDER_TYPE_EQUITY.equals(orderMain.getOrderType())) {
                    //发送短信
                    String content = "【口袋停】您的产权车位费已缴纳成功,系统到帐时间约为12小时,在此期间如遇到出入问题,可向收费员出示订单付款凭证。";
                    String content1 = content + orderMain.getOrderId();
                    String[] mobile = new String[1];
                    mobile[0] = omList.get(0).getCustomerMobile();
                    logger.info("给用户发送短信" + mobile + content);
                    SingletonClient.getInstance().sendMessage(mobile, content, 5);
                }
            }
        }
        //给车场管理员发短信
        String parkingId = null;
        int monthNum = 0;
        String carNumber = null;
        //是月租时
        if (OrderConstants.ORDER_TYPE_MONTHLY.equals(orderMain.getOrderType())) {
            OrderMonthly orderMonthly = new OrderMonthly();
            orderMonthly.setOrderType(orderMain.getOrderType());
            orderMonthly.setOrderId(orderMain.getOrderId());
            List<OrderMonthly> monList = orderMonthlyDao.selectList(orderMonthly);
            if (null != monList && monList.size() > 0) {
                parkingId = monList.get(0).getParkingId();
                monthNum = monList.get(0).getMonthNum();
                carNumber = monList.get(0).getCarNumber();
            }
        } else if (OrderConstants.ORDER_TYPE_EQUITY.equals(orderMain.getOrderType())) {
            OrderEquity orderEquity = new OrderEquity();
            orderEquity.setOrderType(orderMain.getOrderType());
            orderEquity.setOrderId(orderMain.getOrderId());
            List<OrderEquity> eList = orderEquityDao.selectList(orderEquity);
            if (null != eList && eList.size() > 0) {
                parkingId = eList.get(0).getParkingId();
                monthNum = eList.get(0).getMonthNum();
                carNumber = eList.get(0).getCarNumber();
            }
        } else {
            logger.info("---------------------给业主发短信，订单类型错误" + orderMain.getOrderType());
        }

        logger.info("停车场ID:" + parkingId + "----月数：" + monthNum + "----------车牌号:" + carNumber);
        if (null != parkingId) {//查詢userInfo表
           /* UserInfo userParam = new UserInfo();
            userParam.setParkingId(parkingId);
            userParam.setRoleId(2);
            userParam.setIsUsed(Constants.TRUE);
            List<UserInfo> viList = userInfoDao.selectList(userParam);*/
            SendMessage smeParam = new SendMessage();
            smeParam.setOrderType(orderMain.getOrderType());
            smeParam.setParkingId(parkingId);
            List<SendMessage> sList = sendMessageDao.selectList(smeParam);
            //发送短信
            //List<String> mList = new ArrayList<String>();
            Set<String> set = new HashSet<String>();
            for (SendMessage si : sList) {
                if (!StringUtils.isEmpty(si.getPersonMobile())) {
                    logger.info("手机号-----------------" + si.getPersonMobile());
                    set.add(si.getPersonMobile());
                }
            }
            logger.info("手机号-----------------" + set.size());
            if (set.size() > 0) {
                //月租短信内容
                String content = null;
                if (OrderConstants.ORDER_TYPE_MONTHLY.equals(orderMain.getOrderType())) {
                    content = "【口袋停】车牌" + carNumber + "的车主已在线上缴纳您车场" + monthNum + "个月月租车位费" + orderMain.getAmountPaid() / 100 + "元。请及时更新现场系统数据，谢谢您的支付！";

                } else if (OrderConstants.ORDER_TYPE_EQUITY.equals(orderMain.getOrderType())) {
                    content = "【口袋停】车牌" + carNumber + "的车主已在线上缴纳您车场" + monthNum + "个月产权车位费" + orderMain.getAmountPaid() / 100 + "元。请及时更新现场系统数据，谢谢您的支付！";

                }
                SingletonClient.getInstance().sendMessage((String[]) set.toArray(new String[set.size()]), content, 5);
            }
        }
    }

    //月租产权支付完成之后同步时间及发送短信
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void monAndProPaid(OrderMain om) {
        //订单支付完成就不发短信
      /*  if (OrderConstants.ORDER_STATUS_PAID.equals(om.getOrderStatus())) {
            return;
        }*/
        if (OrderConstants.ORDER_STATUS_EXCEPTION.equals(om.getOrderStatus())) {  //  数据异常   不能完成修改
            return;
        }
        String orderId = om.getOrderId();
        String orderType = om.getOrderType();
        //同步结束时间
        if (orderType.equals("13")) {
            OrderMonthly orderMonthly = new OrderMonthly();
            orderMonthly.setOrderId(orderId);
            List<OrderMonthly> list2 = orderMonthlyDao.selectList(orderMonthly);
            if (list2 != null && list2.size() > 0) {
                OrderMonthly orderMonthly1 = list2.get(0);
                Monthlyparkinginfo monthlyparkinginfo = new Monthlyparkinginfo();
                monthlyparkinginfo.setCarNumber(orderMonthly1.getCarNumber());
                monthlyparkinginfo.setVillageId(orderMonthly1.getParkingId());
                List<Monthlyparkinginfo> list = monthlyparkinginfoDao.selectList(monthlyparkinginfo);
                if (list != null && list.size() > 0) {
                    Monthlyparkinginfo monthlyparkinginfo2 = list.get(0);
                    monthlyparkinginfo2.setEffect_end_time(orderMonthly1.getEndDate());
                    monthlyparkinginfoDao.update(monthlyparkinginfo2);
                }
            }
        } else if (orderType.equals("14")) {
            OrderEquity orderEquity = new OrderEquity();
            orderEquity.setOrderId(orderId);
            List<OrderEquity> list3 = orderEquityDao.selectList(orderEquity);
            if (list3 != null && list3.size() > 0) {
                OrderEquity orderEquity2 = list3.get(0);
                // 车位
                Propertyparkinginfo propertyparkinginfo = new Propertyparkinginfo();
                propertyparkinginfo.setCarNumber(orderEquity2.getCarNumber());
                propertyparkinginfo.setVillageId(orderEquity2.getParkingId());
                List<Propertyparkinginfo> list1 = propertyparkinginfoDao.selectList(propertyparkinginfo);
                if (list1 != null && list1.size() > 0) {
                    Propertyparkinginfo propertyparkinginfo2 = list1.get(0);
                    propertyparkinginfo2.setEffect_end_time(orderEquity2.getEndDate());
                    propertyparkinginfoDao.update(propertyparkinginfo2);
                }

            }
        }
        new MyThread1(om).start();
    }

    public class MyThread1 extends Thread {
        private OrderMain orderMain;

        public MyThread1(OrderMain orderMain) {
            this.orderMain = orderMain;
        }

        public void run() {
            sendToUserAndMa(orderMain);
        }
    }

    /**
     * 创建月租产权订单修改t_invoice orderId
     */
    public void updateInvoiceOrderId(String invoiceId, String orderId) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceId);
        try {
            List<Invoice> invList = invoiceDao.selectList(invoice);
            logger.info("---------------" + invList);
            if (invList != null && invList.size() > 0) {
                invList.get(0).setOrderId(orderId);
                invoiceDao.update(invList.get(0));
                logger.info("t_invoice表订单Id更新成功");
            }
        } catch (Exception e) {
            logger.error("", e);
        }

    }

    @Override
    public String queryOrderStatus(Page<OrderMain> page) {
        String mess = null;
        List<Map<String, Object>> carwashList = new ArrayList<Map<String, Object>>();
        List<OrderMain> list = orderMainDao.queryOrderStatus(page);
        if (list != null && list.size() > 0) {

            for (OrderMain orderMain : list) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                paraMap.put("orderId", null == orderMain.getOrderId() ? "" : orderMain.getOrderId());
                paraMap.put("orderStatus", null == orderMain.getOrderStatus() ? "" : orderMain.getOrderStatus());
                paraMap.put("parkingId", null == orderMain.getParkingId() ? "" : orderMain.getParkingId());
                paraMap.put("parkingName", null == orderMain.getParkingName() ? "" : orderMain.getParkingName());
                paraMap.put("carNumber", null == orderMain.getCarNumber() ? "" : orderMain.getCarNumber());
                paraMap.put("reserveDate", null == orderMain.getReserveDate() ? "" : DateUtil.date2str(orderMain.getReserveDate(), DateUtil.DATE_FORMAT));
                paraMap.put("createDate", null == orderMain.getCreateDate() ? "" : DateUtil.date2str(orderMain.getCreateDate(), DateUtil.DATETIME_FORMAT));
                paraMap.put("amountPayable", null == orderMain.getAmountPayable() ? "" : orderMain.getAmountPayable() / 100);
                paraMap.put("amountDiscount", null == orderMain.getAmountDiscount() ? "" : orderMain.getAmountDiscount() / 100);
                paraMap.put("amountPaid", null == orderMain.getAmountPaid() ? "" : orderMain.getAmountPaid() / 100);
                paraMap.put("payTime", null == orderMain.getPayTime() ? "" : DateUtil.date2str(orderMain.getPayTime(), DateUtil.DATETIME_FORMAT));
                paraMap.put("payType", null == orderMain.getPayType() ? "" : orderMain.getPayType());
                carwashList.add(paraMap);
            }
            mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "carwashList", carwashList);

        } else {
            mess = ShangAnMessageType.toShangAnJson("1", "查询失败", "carwashList", "");
        }
        return mess;

    }

    @Override
    public Map queryCarwashByOrderId(String orderId) {
        OrderMain orderMain = orderMainDao.queryCarwashByOrderId(orderId);
        Map<String, Object> paraMap = new HashMap<String, Object>();
        if (orderMain != null) {
            paraMap.put("orderId", null == orderMain.getOrderId() ? "" : orderMain.getOrderId());
            paraMap.put("orderStatus", null == orderMain.getOrderStatus() ? "" : orderMain.getOrderStatus());
            paraMap.put("parkingId", null == orderMain.getParkingId() ? "" : orderMain.getParkingId());
            paraMap.put("parkingName", null == orderMain.getParkingName() ? "" : orderMain.getParkingName());
            paraMap.put("carNumber", null == orderMain.getCarNumber() ? "" : orderMain.getCarNumber());
            paraMap.put("reserveDate", null == orderMain.getReserveDate() ? "" : DateUtil.date2str(orderMain.getReserveDate(), DateUtil.DATETIME_FORMAT));
            paraMap.put("createDate", null == orderMain.getCreateDate() ? "" : DateUtil.date2str(orderMain.getCreateDate(), DateUtil.DATETIME_FORMAT));
            paraMap.put("amountPayable", null == orderMain.getAmountPayable() ? "" : orderMain.getAmountPayable() / 100);
            paraMap.put("amountDiscount", null == orderMain.getAmountDiscount() ? "" : orderMain.getAmountDiscount() / 100);
            paraMap.put("amountPaid", null == orderMain.getAmountPaid() ? "" : orderMain.getAmountPaid() / 100);
            paraMap.put("payTime", null == orderMain.getPayTime() ? "" : DateUtil.date2str(orderMain.getPayTime(), DateUtil.DATETIME_FORMAT));
            paraMap.put("payType", null == orderMain.getPayType() ? "" : orderMain.getPayType());
        }
        return paraMap;
    }

    /**
     * 1、没有入场提示 请确认该车辆已进入您所进入的车场！
     * 2、入场时 一、如果是月租车辆 提示  请确认该车辆信息输入是否正确！二、加入特殊车辆 白名单同步云（当天）
     *
     * @param userId
     * @param carNumber
     * @return mess
     */
    public String clearanceCar(String userId, String carNumber) {
       /* ClearanceRecord cParam1 = new ClearanceRecord();
        cParam1.setCarNumber(carNumber);
        cParam1.setParkingId("11");
        cParam1.setCreateDate(new Date());
        cParam1.setCreateor("admin");
        cParam1.setUserId(userId);
        clearanceRecordDao.insert(cParam1);
        ParklotFeeResult parklotFeeResult1 = sendJsonDataToRest(new ParklotFee(APIKey, carNumber));
        String payCharge1 = StringUtils.isEmpty(parklotFeeResult1.getDatas().getPayCharge()) || parklotFeeResult1.getDatas().getPayCharge().trim().equals("null") ? parklotFeeResult1.getDatas().getCharge() : parklotFeeResult1.getDatas().getPayCharge();
       */ //车辆是否入场0:没入场 1:入场
        String erroFlag = "0";
        String bluecardParkId = null;
        //查询该管理员的车场
        UserInfo userParam = new UserInfo();
        userParam.setSysUserId(userId);
        List<UserInfo> uList = userInfoDao.selectList(userParam);
        if (null == uList || uList.size() == 0) {
            return ShangAnMessageType.operateToJson("2", "该用户不存在");
        }
        String parkings = uList.get(0).getParkingId();
        //用户没设置车场
        if (StringUtils.isEmpty(parkings)) {
            return ShangAnMessageType.operateToJson("2", "请确认该车辆已经进入您所管理的车场");
        }

        /*//V0
        //-----------------------车辆是否入场
        String[] parkingsGroup = parkings.split("\\,");
        String blueCarParkIds = "";
        for (String s : parkingsGroup) {
            Villageinfo vi = villageinfoService.queryById(s);
            if (null != vi) {
                blueCarParkIds += vi.getItem01();
                blueCarParkIds += ",";
            }
        }
        List<Map<String, String>> carsList = new ArrayList<Map<String, String>>();
        String startTime = DateUtil.date2str(new Date(), DateUtil.DATE_FORMAT) + " 00:00:00;";
        String endTime = DateUtil.date2str(new Date(), DateUtil.DATETIME_FORMAT);
        try {
            carsList = couponRuleServiceImpl.getInParkInfo(startTime, endTime);
        } catch (Exception e) {
            logger.info("-----------------------查询入场车辆异常");
            logger.error("", e);
        }
        if(null!=carsList&&carsList.size()>0){
            for (Map<?, ?> m : carsList) {
                String parkId = (String) m.get("parkId");//蓝卡车场id
                String plateId = (String) m.get("plateId");//车牌号
                //车场在用户管理范围，车牌号匹配
                if (blueCarParkIds.indexOf(parkId) >= 0 && carNumber.equals(plateId)) {
                    erroFlag = "1";
                    bluecardParkId = parkId;
                    break;
                }
            }
        }*/
        parkings = parkings.replaceAll(",", "','");
        parkings = "'"+parkings+"'";
        String beginTime = DateUtil.date2str(new Date(), DateUtil.DATE_FORMAT) + " 00:00:00;";
        String endTime = DateUtil.date2str(new Date(), DateUtil.DATETIME_FORMAT);
        Map<String,Object> bParam = new HashMap<String,Object>();
        bParam.put("beginTime",beginTime);
        bParam.put("endTime",endTime);
        bParam.put("plateId",carNumber);
        bParam.put("parkingIds",parkings);
        //是否入场
        int vCount = carInOutRecordV2Dao.getByToday(bParam);
        //车辆没入场时
        if (vCount==0) {
            return ShangAnMessageType.operateToJson("2", "请确认该车辆已经进入您所管理的车场");
        } else {
            //----------------------查询车辆是否是月租产权特殊等车辆
            Map<String, String> param = new HashMap<String, String>();
            param.put("carNumber", carNumber);
            List<Monthlyparkinginfo> mList = monthlyparkinginfoDao.getMonProSpeByCarnumber(param);
            if (null != mList && mList.size() > 0) {
                return ShangAnMessageType.operateToJson("2", "请确认该车辆信息输入是否正确！");
            } else {//加入特殊车辆云平台
                String isSucess = this.sendSysByCarNumber(carNumber, bluecardParkId);
                if ("1".equals(isSucess)) {
                    ClearanceRecord cParam = new ClearanceRecord();
                    cParam.setCarNumber(carNumber);
                    Villageinfo viParam = new Villageinfo();
                    viParam.setItem01(bluecardParkId);
                    List<Villageinfo> viList = villageinfoService.selectList(viParam);
                    if (null != viList && viList.size() > 0) {
                        cParam.setParkingId(viList.get(0).getId());
                    }
                    //cParam.setParkingId(bluecardParkId);
                    cParam.setCreateDate(new Date());
                    cParam.setCreateor("admin");
                    cParam.setUserId(userId);
                    ParklotFeeResult parklotFeeResult = sendJsonDataToRest(new ParklotFee(APIKey, carNumber));
                    String payCharge = StringUtils.isEmpty(parklotFeeResult.getDatas().getPayCharge()) || parklotFeeResult.getDatas().getPayCharge().trim().equals("null") ? parklotFeeResult.getDatas().getCharge() : parklotFeeResult.getDatas().getPayCharge();
                    cParam.setPrice(Integer.parseInt(payCharge));
                    clearanceRecordDao.insert(cParam);
                    return ShangAnMessageType.operateToJson("0", "已成功放行该车辆！");
                } else {
                    return ShangAnMessageType.operateToJson("2", "放行失败！");
                }
            }
        }

    }

    /**
     * 通过车牌号同步白名单到特殊车辆
     *
     * @param carNumber
     * @return
     */
    public String sendSysByCarNumber(String carNumber, String bluecardParkId) {
        //logger.info("RegisterUserSyncData TO Json = " + jsonStr);
        //插入白名单同步记录表
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("plateId", carNumber);
        paramMap.put("type", "3");
        paramMap.put("owner", "");
        paramMap.put("certificate", "");
        paramMap.put("address", "");
        paramMap.put("phone", "");
        paramMap.put("carColor", "黑");
        paramMap.put("effectieTime", DateUtil.date2str(new Date(), DateUtil.DATE_FORMAT));
        paramMap.put("expiredTime", DateUtil.date2str(new Date(), DateUtil.DATE_FORMAT));
        paramMap.put("openId", null);
        paramMap.put("state", "0");
        paramMap.put("parkSpase", null);
        paramMap.put("balance", "0");
        paramMap.put("inOuts", new ArrayList<String>());
        List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
        mList.add(paramMap);
        String jsonStr = JacksonUtil.toJson(mList);
        logger.info("0000000000" + jsonStr);
        WhitesynRecord whitesynRecord = new WhitesynRecord();
        whitesynRecord.setIsUsed(Constants.TRUE);
        whitesynRecord.setCreateor("admin");
        whitesynRecord.setParkingInfo(jsonStr);
        whitesynRecord.setCreateDate(new Date());
        String datas = parseEncryption(jsonStr);
        // 调用此方法请求服务器 mpkey 为车场Id
        String isSucess = sendJsonDataToRest(new RegisterUserSync(APIKey, bluecardParkId, datas), whitesynRecord);

        logger.info("key=" + APIKey + ",parkCode=" + bluecardParkId + ",datas=" + datas);
        return isSucess;
    }

    @Override
    public Page<OrderMain> queryCarwashPage(Page<OrderMain> page) {
        page.setResultList(orderMainDao.queryCarwashPage(page));
        return page;
    }

    @Override
    public List<OrderMain> queryCarwasExport(OrderMain orderMain) {
        return orderMainDao.queryCarwasExport(orderMain);
    }

    @Override
    public Page<OrderMain> queryParkPage(Page<OrderMain> page) {
        page.setResultList(orderMainDao.queryParkPage(page));
        return page;
    }

    @Override
    public List<OrderMainExcel> queryParkExport(Map<String, Object> params) {
        return orderMainDao.queryParkExport(params);
    }
    //获取项目缴费详情
    @Override
    public List<OrderMain> getPaymentDetail(String inputDate, String region, String parkingId){
        List<OrderMain> allList = new ArrayList<OrderMain>();
        //临停线上
        Map<String,Object> param1 = new HashMap<String,Object>();
        param1.put("parkingId",parkingId);
        param1.put("payTypes","'00','01','02','04','05'");
        param1.put("inputDate",inputDate);
        param1.put("region",region);
        List<OrderMain> tmOnlineList = orderMainDao.getTempOnlinePayment(param1);

        //临停线下
        Map<String,Object> param2 = new HashMap<String,Object>();
        param2.put("parkingId",parkingId);
        param2.put("payTypes","'00','01','02','04','05'");
        param2.put("inputDate",inputDate);
        param2.put("region",region);
        List<OrderMain> tmOfflineList = orderMainDao.getTempOfflinePayment(param2);

        //月租线上
        Map<String,Object> param3 = new HashMap<String,Object>();
        param3.put("parkingId",parkingId);
        param3.put("payTypes","'00','01','02','04','05'");
        param3.put("inputDate",inputDate);
        param3.put("region",region);
        List<OrderMain> monthlyOnlineList = orderMainDao.getMonthlyPayment(param3);

        //月租线下
        Map<String,Object> param4 = new HashMap<String,Object>();
        param4.put("parkingId",parkingId);
        param4.put("payTypes","'03'");
        param4.put("inputDate",inputDate);
        param4.put("region",region);
        List<OrderMain> monthlyOfflineList = orderMainDao.getMonthlyPayment(param4);

        //产权线上
        Map<String,Object> param5 = new HashMap<String,Object>();
        param5.put("parkingId",parkingId);
        param5.put("payTypes","'00','01','02','04','05'");
        param5.put("inputDate",inputDate);
        param5.put("region",region);
        List<OrderMain> equityOnlineList = orderMainDao.getEquityPayment(param5);

        //产权线下
        Map<String,Object> param6 = new HashMap<String,Object>();
        param6.put("parkingId",parkingId);
        param6.put("payTypes","'03'");
        param6.put("inputDate",inputDate);
        param6.put("region",region);
        List<OrderMain> equityOfflineList = orderMainDao.getEquityPayment(param6);
        //临停线上
        if(null!=tmOnlineList&&tmOnlineList.size()>0&&null!=tmOnlineList.get(0)){
            tmOnlineList.get(0).setOnlineType("1");
            tmOnlineList.get(0).setOrderType("11");
            allList.addAll(tmOnlineList);
        }
        //月租线上
        if(null!=monthlyOnlineList&&monthlyOnlineList.size()>0&&null!=monthlyOnlineList.get(0)){
            monthlyOnlineList.get(0).setOnlineType("1");
            monthlyOnlineList.get(0).setOrderType("13");
            allList.addAll(monthlyOnlineList);
        }
        //产权线上
        if(null!=equityOnlineList&&equityOnlineList.size()>0&&null!=equityOnlineList.get(0)){
            equityOnlineList.get(0).setOnlineType("1");
            equityOnlineList.get(0).setOrderType("14");
            allList.addAll(equityOnlineList);
        }
        //临停线下
        if(null!=tmOfflineList&&tmOfflineList.size()>0&&null!=tmOfflineList.get(0)){
            tmOfflineList.get(0).setOnlineType("2");
            tmOfflineList.get(0).setOrderType("11");
            allList.addAll(tmOfflineList);
        }
        //月租线下
        if(null!=monthlyOfflineList&&monthlyOfflineList.size()>0&&null!=monthlyOfflineList.get(0)){
            monthlyOfflineList.get(0).setOnlineType("2");
            monthlyOfflineList.get(0).setOrderType("13");
            allList.addAll(monthlyOfflineList);
        }
        //产权线下
        if(null!=equityOfflineList&&equityOfflineList.size()>0&&null!=equityOfflineList.get(0)){
            equityOfflineList.get(0).setOnlineType("2");
            equityOfflineList.get(0).setOrderType("14");
            allList.addAll(equityOfflineList);
        }
        return allList;
    }

    public  int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
}