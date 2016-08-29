package com.boxiang.share.product.parker.service.impl;

import java.util.*;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import cn.b2m.eucp.client.SingletonClient;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.order.dao.OrderMainDao;
import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.product.order.po.OrderPark;
import com.boxiang.share.product.order.service.OrderParkService;
import com.boxiang.share.product.parker.service.ParkerService;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingPriceService;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.system.po.Sequence;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.LogTypeEnum;
import com.boxiang.share.utils.MD5Util;
import com.boxiang.share.utils.OrderConstants;
import com.boxiang.share.utils.TableNameConstants;
import com.boxiang.share.utils.jpush.JPush;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.parker.dao.ParkerDao;
import com.boxiang.share.product.parker.po.Parker;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.log.SystemLog;


@DataSource(DataSourceEnum.MASTER)
@Service("parker")
public class ParkerServiceImpl implements ParkerService {

    private static final Logger logger = Logger.getLogger(ParkerServiceImpl.class);

    @Resource(name = "parkerDao")
    private ParkerDao parkerDao;
    @Resource(name="dataEhCache")
    private Cache ehCache;

    @Resource
    private CustomerService customerService;

    @Resource
    private ParkingPriceService parkingPriceService;

    @Resource
    private SequenceService sequenceService;

    @Resource(name = "orderMainDao")
    private OrderMainDao orderMainDao;

    @Resource
    private OrderParkService orderParkService;

    @Resource
    private ParkingService parkingService;

    @Override
    public List<Parker> selectList(Parker parker) {
        return parkerDao.selectList(parker);
    }

    @Override
    public Page<Parker> queryListPage(Page<Parker> page) {
        page.setResultList(parkerDao.queryListPage(page));
        return page;
    }

    @Override
    public Parker queryById(String id) {
        return parkerDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(Parker parker) {
        parkerDao.insert(parker);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(String id) {
        parkerDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(Parker parker) {
        parkerDao.update(parker);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<Parker> parkerList) {
        parkerDao.batchUpdate(parkerList);
    }

    @Override
    public Map<String, Object> queryBusyCount(String parkingId) {
        Date end = new Date();
        String endTime = DateUtil.date2str(end, DateUtil.DATETIME_FORMAT); // 当前时间，结束时间
        Date start = DateUtil.getPreOrNextMinute(end, -30);
        String startTime = DateUtil.date2str(start, DateUtil.DATETIME_FORMAT); // 开始时间
        Map<String, Object> params = new HashMap<>(3);
        params.put("parkingId", parkingId);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        return parkerDao.queryBusyCount(params);
    }

    @Override
    public Parker dispatchParker(String parkingId) {
        Parker parker = parkerDao.dispatchParker(parkingId);
        if (parker == null) return null;
        parker.setLastOperTime(new Date());
        // 修改代泊员最后接单时间
        if (parkerDao.updateLastOperTime(parker) == 0) {
            dispatchParker(parkingId);
        }
        return parker;
    }

    @Override
    public List<Parker> selectParkingIdByParker(Parker parker) {
        return parkerDao.selectParkingIdByParker(parker);
    }

    @Override
    public String queryParkerById(String customerId, String carNumber, String parkingId) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("customerId", customerId);
        params.put("carNumber", carNumber);
        params.put("parkingId", parkingId);
        params.put("orderStatus", " not in ('5','12')");
        Map<String, Object> map = new HashMap<>(1);
        map.put("params", params);
        List<Object> list = parkerDao.queryParkerById(map);
        return ShangAnMessageType.toShangAnJson("0", "查询成功", "parkerList", list);
    }

    @Override
    public String queryFinishParkOrder(String customerId, int pageIndex) {
        Page<Object> page = new Page<>();
        page.setCurrentPage(pageIndex);
        page.getParams().put("customerId", customerId);
        page.getParams().put("orderStatus", " = '5'");
        List<Object> list = parkerDao.queryParkerById(page);
        return ShangAnMessageType.toShangAnJson("0", "查询成功", "list", list);
    }

    /**
     * 创建代泊订单
     *
     * @param customerId
     * @param parkingId
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
	@SystemLog(logType=LogTypeEnum.ORDER_INFO,logSummary="创建代泊订单")
    public String ordercPark(String customerId, String parkingId, String startTime, String endTime, String carNumber, String isContinue, String version) throws Exception {
        Parking parkingEhca=new Parking();
        if(ehCache.get("t_parking"+parkingId)!=null && ehCache.get("t_parking"+parkingId).getObjectValue()!=null){
            parkingEhca= (Parking) ehCache.get("t_parking"+parkingId).getObjectValue();
        }else {
            parkingEhca = parkingService.queryById(parkingId);
            ehCache.put(new Element("t_parking"+parkingId, parkingEhca));
        }
        // 判断该车牌号是否已经在代泊中
        Map<String, Object> params = new HashMap<>(2);
        params.put("carNumber", carNumber);
        params.put("notOrderStatus", "('5','12')");
        if (orderParkService.queryCountJoinOrderMain(params) > 0) {
            return ShangAnMessageType.operateToJson("5", "该车牌号已经在代泊中");
        }

        Parking parking = parkingPriceService.queryParkingWithPrice(parkingId); // 车场信息

        // 是否继续
        if (isContinue.equals(Constants.FALSE) && parking.getMaxinumHour() != null && Pattern.matches("^\\d+$", parking.getMaxinumHour().trim())) {
            int hourMax = Integer.parseInt(parking.getMaxinumHour());
            // 查询单小时接单量
            int singleHourCount = orderParkService.querySingleHourCount(parkingId);
            if (singleHourCount > hourMax) {
                Map<String, String> map = new HashMap<>(2);
                int count = singleHourCount - hourMax;
                map.put("count", Integer.toString(count));
                // 忙碌时预计代泊时间默认当前时间 +20 分钟
                map.put("startTime", DateUtil.date2str(DateUtil.getPreOrNextMinute(new Date(), 20), DateUtil.DATETIME_FORMAT));
                return ShangAnMessageType.toShangAnJson("1", "忙碌中，还有" + count + "辆车需要等待", "data", map);
            }
        }

        // 计算金额
        int price = parkingPriceService.calcPrice(parking, startTime, endTime);

        // 目标车场集合
        List<Parking> targetParkingList = parkingService.queryTargetParking(parking.getParkingId());
        if (targetParkingList == null || targetParkingList.size() == 0) {
            return ShangAnMessageType.operateToJson("3", "没有目标车场");
        }

        // 循环扣除车位数
        String targetParkingId = null;
        boolean notCanUse = true;
        for (Parking p : targetParkingList) {
            if (p.getParkingCanUse() > 0) {  // 可用车位
                // 车位数量 - 1
                targetParkingId = p.getParkingId();
                parkingService.updateParkingCanUse(targetParkingId, -1);
                notCanUse = false;
                break;
            }
        }
        if (notCanUse) {
            return ShangAnMessageType.operateToJson("4", "没有车位了");
        }

        // 分配代泊员
        Parker parker = this.dispatchParker(parkingId);
        if (parker == null) {
            throw new ServiceException("没有代泊员");
        }

        // 添加主订单
        Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_ORDER_MAIN);
        OrderMain orderMain = new OrderMain();
        orderMain.setOrderId((null==parkingEhca.getParkingIdentifier()?"":parkingEhca.getParkingIdentifier())+OrderConstants.ORDER_TYPE_PARK+sequence.getId());
        orderMain.setOrderType(OrderConstants.ORDER_TYPE_PARK); // 12:代泊
        orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_BESPOKE); // 1:已预约
        orderMain.setInvoiceStatus("0");
        orderMain.setCustomerId(customerId);
        orderMain.setAmountPayable(price * 100);
        orderMain.setAmountDiscount(0);
        Integer amountPaid = orderMain.getAmountPayable() - orderMain.getAmountDiscount();
        orderMain.setAmountPaid(amountPaid < 0 ? 0 : amountPaid);
        orderMain.setIsUsed(Constants.TRUE);
        orderMain.setCreateor("admin");
        orderMain.setCreateDate(new Date());
        orderMainDao.insert(orderMain);

        // 添加 t_order_park 代泊订单
        OrderPark op = new OrderPark();
        op.setOrderId(orderMain.getOrderId());
        op.setParkingId(parkingId);
        op.setTargetParkingId(targetParkingId);
        op.setParkerId(parker.getParkerId()); // 接车代泊员ID
        op.setParkerBackId(""); // 还车代泊员ID
        op.setValidateImagePath(""); // 验车照片路径（代泊员接车）
        op.setParkingImagePath(""); // 停车照片路径（代泊员停车）
        op.setCarNumber(carNumber); // 车牌号
        op.setOrderBeginDate(DateUtil.str2date(startTime, DateUtil.DATETIME_FORMAT)); // 预约开始时间
        op.setOrderEndDate(DateUtil.str2date(endTime, DateUtil.DATETIME_FORMAT)); // 预约取车时间
        op.setActualBeginDate(null); // 代泊员接车拍完验车照上传完照片开始（用户：交车时间，代泊员：接车时间）
        op.setActualEndDate(null); // 用户付完款结束或代泊员订单结束
        op.setKeyBox("");
        op.setBackPark("");
        op.setIsUsed(Constants.TRUE);
        op.setCreateor("admin");
        op.setCreateDate(orderMain.getCreateDate());
        orderParkService.add(op);

        // 给代泊员发短信和极光推送
        pushMessage(parker, customerId, carNumber);

        // 创建订单成功
        if (version != null && version.equals("2.0.0")) {
            Map<String, Object> params1 = new HashMap<>(2);
            params1.put("orderId", orderMain.getOrderId());
            params1.put("orderStatus", " not in ('5','12')");
            Map<String, Object> map = new HashMap<>(1);
            map.put("params", params1);
            List<Object> list = parkerDao.queryParkerById(map);
            return ShangAnMessageType.toShangAnJson("0", "success", "data", list.get(0));
        } else {
            Map<String, String> map = new HashMap<>(3);
            map.put("price", Integer.toString(price));
            map.put("endTime", endTime);
            map.put("parkerMobile", parker.getParkerMobile());
            return ShangAnMessageType.toShangAnJson("0", "success", "data", map);
        }
    }

    /**
     * 给代泊员发短信和极光推送
     *
     * @param parker
     */
    private void pushMessage(Parker parker, String customerId, String carNumber) {
        try {
            // 客户
            String text = "【口袋停】恭喜您，成功预订代泊服务，您的车管家手机为" + parker.getParkerMobile() + "，如三分钟内，车管家未联系您，您可直接拨打车管家电话，谢谢！";
            Customer customer = customerService.queryByCustomerId(customerId);
            SingletonClient.getInstance().sendMessage(new String[]{customer.getCustomerMobile()}, text, 5);

            // 代泊员
            String content = "【口袋停】车牌号码" + carNumber + "，手机号码" + customer.getCustomerMobile() + "，已成功预订您车场的代泊服务，请联系客户进行代泊，谢谢！";
            // 发送短信
            String[] mobiles = {parker.getParkerMobile()};
            SingletonClient.getInstance().sendMessage(mobiles, content, 5);
            // 极光推送
            JPush.PARKER.sendPushToParkerWithStartPark(content, parker.getParkerId());
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void updateState(String parkerId, String state) {
        Parker parker = new Parker();
        parker.setParkerId(parkerId);
        parker.setState(state);
        parkerDao.updateState(parker);
    }

    @Override
    public int selectCount(Parker parker) {
        return parkerDao.selectCount(parker);
    }

    @Override
    public int updateLastOperTime(Parker parker) {
        return parkerDao.updateLastOperTime(parker);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public String startPark(String orderId, String imagePath) {
        // 查询主表订单
        OrderMain orderMain = orderMainDao.queryById(orderId);
        if (orderMain == null) {
            return ShangAnMessageType.operateToJson("1", "订单号不存在");
        }
        if (!orderMain.getOrderStatus().equals(OrderConstants.ORDER_STATUS_BESPOKE)) {
            return ShangAnMessageType.operateToJson("1", "状态不对");
        }
        // 查询副表订单
        OrderPark orderPark = orderParkService.queryByOrderId(orderId);
        if (orderPark == null) {
            return ShangAnMessageType.operateToJson("1", "订单号不存在");
        }
        // 更新订单状态
        orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_PICKCAR);
        orderMain.setModifyDate(new Date());
        orderMainDao.update(orderMain);
        // 更新图片路径
        orderPark.setValidateImagePath(imagePath);
        orderPark.setModifyDate(orderMain.getModifyDate());
        orderParkService.update(orderPark);
        return ShangAnMessageType.operateToJson("0", "success");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public String parked(String orderId, String imagePath, String keyBox) throws Exception {
        // 查询主表订单
        OrderMain orderMain = orderMainDao.queryById(orderId);
        if (orderMain == null) {
            return ShangAnMessageType.operateToJson("1", "订单号不存在");
        }
        if (!orderMain.getOrderStatus().equals(OrderConstants.ORDER_STATUS_PICKCAR)) {
            return ShangAnMessageType.operateToJson("1", "状态不对");
        }
        // 查询副表订单
        OrderPark orderPark = orderParkService.queryByOrderId(orderId);
        if (orderPark == null) {
            return ShangAnMessageType.operateToJson("1", "订单号不存在");
        }
        // 重新计算价格
        Parking parking = parkingPriceService.queryParkingWithPrice(orderPark.getParkingId());
        Integer price = parkingPriceService.calcPrice(parking, DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT), DateUtil.date2str(orderPark.getOrderEndDate(), DateUtil.DATETIME_FORMAT));
        // 更新订单状态
        orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_PARKED);
        orderMain.setAmountPayable(price * 100);
        orderMain.setAmountDiscount(0);
        Integer amountPaid = orderMain.getAmountPayable() - orderMain.getAmountDiscount();
        orderMain.setAmountPaid(amountPaid < 0 ? 0 : amountPaid);
        orderMain.setModifyDate(new Date());
        orderMainDao.update(orderMain);
        // 更新图片路径
        orderPark.setParkingImagePath(imagePath);
        if (!StringUtils.isEmpty(keyBox)) {
            orderPark.setKeyBox(keyBox); // 钥匙箱
        }
        orderPark.setActualBeginDate(orderMain.getModifyDate()); // 停好车的时间
        orderPark.setModifyDate(orderMain.getModifyDate());
        orderParkService.update(orderPark);
        return ShangAnMessageType.operateToJson("0", "success");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public String backParking(String orderId, String parkerId) {
        // 查询主表订单
        OrderMain orderMain = orderMainDao.queryById(orderId);
        if (orderMain == null) {
            return ShangAnMessageType.operateToJson("1", "订单号不存在");
        }
        if (!orderMain.getOrderStatus().equals(OrderConstants.ORDER_STATUS_PARKED)) {
            return ShangAnMessageType.operateToJson("1", "状态不对");
        }
        // 查询副表订单
        OrderPark orderPark = orderParkService.queryByOrderId(orderId);
        if (orderPark == null) {
            return ShangAnMessageType.operateToJson("1", "订单号不存在");
        }
        // 查询代泊员信息
        Parker parker = parkerDao.queryById(parkerId);
        if (parker == null) {
            return ShangAnMessageType.operateToJson("1", "代泊员id不存在");
        }
        // 更新订单状态
        orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_BACK_PARKING);
        orderMain.setModifyDate(new Date());
        orderMainDao.update(orderMain);
        // 更新副表订单
        orderPark.setParkerBackId(parkerId); // 分配给选择泊回的代泊员
        // 更新代泊员接单时间
        parker.setLastOperTime(orderMain.getModifyDate());
        parker.setVersion(null); // 强制更新,不判断version
        parkerDao.updateLastOperTime(parker);
        orderPark.setModifyDate(orderMain.getModifyDate());
        orderParkService.update(orderPark);
        return ShangAnMessageType.operateToJson("0", "success");
    }

    @Override
    public String backParked(String orderId, String backPark) {
        // 查询主表订单
        OrderMain orderMain = orderMainDao.queryById(orderId);
        if (orderMain == null) {
            return ShangAnMessageType.operateToJson("1", "订单号不存在");
        }
        if (!orderMain.getOrderStatus().equals(OrderConstants.ORDER_STATUS_BACK_PARKING)) {
            return ShangAnMessageType.operateToJson("1", "状态不对");
        }
        // 查询副表订单
        OrderPark orderPark = orderParkService.queryByOrderId(orderId);
        if (orderPark == null) {
            return ShangAnMessageType.operateToJson("1", "订单号不存在");
        }
        // 更新订单状态
        orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_BACK_PARKED);
        orderMain.setModifyDate(new Date());
        orderMainDao.update(orderMain);
        // 更新副表订单
        if (!StringUtils.isEmpty(backPark)) {
            orderPark.setBackPark(backPark); // 泊回地点
        }
        orderPark.setModifyDate(orderMain.getModifyDate());
        orderParkService.update(orderPark);
        return ShangAnMessageType.operateToJson("0", "success");
    }

    @Override
    public String gettingCar(String orderId) throws Exception {
        // 查询主表订单
        OrderMain orderMain = orderMainDao.queryById(orderId);
        if (orderMain == null) {
            return ShangAnMessageType.operateToJson("1", "订单号不存在");
        }
        if ((!orderMain.getOrderStatus().equals(OrderConstants.ORDER_STATUS_PARKED)) &&
                (!orderMain.getOrderStatus().equals(OrderConstants.ORDER_STATUS_BACK_PARKING)) &&
                (!orderMain.getOrderStatus().equals(OrderConstants.ORDER_STATUS_BACK_PARKED))
                ) {
            return ShangAnMessageType.operateToJson("1", "状态不对");
        }
        // 查询副表订单
        OrderPark orderPark = orderParkService.queryByOrderId(orderId);
        if (orderPark == null) {
            return ShangAnMessageType.operateToJson("1", "订单号不存在");
        }
        // 判断有木有超时,超时需要重新计算价格
        Date actualBeginDate = orderPark.getActualBeginDate(); // 停好车的时间
        Date orderEndDate = orderPark.getOrderEndDate(); // 预约的结束时间
        Date now = new Date();
        // 预约时间小于当前取车时间，超时
        if (orderEndDate.compareTo(now) < 0) {
            // 重新计算价格
            Parking parking = parkingPriceService.queryParkingWithPrice(orderPark.getParkingId());
            // 规则1才计算超时价格，规则2是一口价，忽略
            if (parking.getRule().equals("1")) {
                Integer price = parkingPriceService.calcPrice(parking, DateUtil.date2str(actualBeginDate, DateUtil.DATETIME_FORMAT), DateUtil.date2str(now, DateUtil.DATETIME_FORMAT));
                orderMain.setAmountPayable(price * 100);
                orderMain.setAmountDiscount(0);
                Integer amountPaid = orderMain.getAmountPayable() - orderMain.getAmountDiscount();
                orderMain.setAmountPaid(amountPaid < 0 ? 0 : amountPaid);
                orderMain.setModifyDate(now);
                orderMainDao.update(orderMain);
            }
        }
        // 更新副表
        orderPark.setActualEndDate(now); // 取车时间
        orderPark.setModifyDate(now);
        orderParkService.update(orderPark);

        // 判断有没有代泊员
        int parkerCount = parkingService.parkerCount(orderPark.getParkingId());
        if (parkerCount <= 0) {
            return ShangAnMessageType.operateToJson("1", "代泊员都下班了");
        }

        // 返回数据
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderMain.getOrderId());
        // 应付金额
        map.put("amountPayable", null == orderMain.getAmountPayable() ? "" : Integer.toString(orderMain.getAmountPayable() / 100));
        // 折扣
        map.put("amountDiscount", null == orderMain.getAmountDiscount() ? "" : Integer.toString(orderMain.getAmountDiscount() / 100));
        // 实付金额
        map.put("amountPaid", null == orderMain.getAmountPaid() ? "" : Integer.toString(orderMain.getAmountPaid() / 100));
        // 预约开始时间
        map.put("orderBeginDate", null == orderPark.getOrderBeginDate() ? "" : DateUtil.date2str(orderPark.getOrderBeginDate(), DateUtil.DATETIME_FORMAT));
        // 预约结束时间
        map.put("orderEndDate", null == orderPark.getOrderEndDate() ? "" : DateUtil.date2str(orderPark.getOrderEndDate(), DateUtil.DATETIME_FORMAT));
        // 停好车的时间
        map.put("actualBeginDate", null == orderPark.getActualBeginDate() ? "" : DateUtil.date2str(orderPark.getActualBeginDate(), DateUtil.DATETIME_FORMAT));
        // 取车时间
        map.put("actualEndDate", null == orderPark.getActualEndDate() ? "" : DateUtil.date2str(orderPark.getActualEndDate(), DateUtil.DATETIME_FORMAT));
        return ShangAnMessageType.toShangAnJson("0", "success", "data", map);
    }

    @Override
    public String gotCar(String orderId) {
        // 查询主表订单
        OrderMain orderMain = orderMainDao.queryById(orderId);
        if (orderMain == null) {
            return ShangAnMessageType.operateToJson("1", "订单号不存在");
        }
        if (!orderMain.getOrderStatus().equals(OrderConstants.ORDER_STATUS_WAITING_GETCAR)) {
            return ShangAnMessageType.operateToJson("1", "状态不对");
        }
        // 更新订单状态
        orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_GETTINGCAR);
        orderMain.setModifyDate(new Date());
        orderMainDao.update(orderMain);
        return ShangAnMessageType.operateToJson("0", "success");
    }

    @Override
    public String parkFinished(String orderId) {
        // 查询主表订单
        OrderMain orderMain = orderMainDao.queryById(orderId);
        if (orderMain == null) {
            return ShangAnMessageType.operateToJson("1", "订单号不存在");
        }
        if (!orderMain.getOrderStatus().equals(OrderConstants.ORDER_STATUS_GETTINGCAR)) {
            return ShangAnMessageType.operateToJson("1", "状态不对");
        }
        // 查询副表订单
        OrderPark orderPark = orderParkService.queryByOrderId(orderId);
        if (orderPark == null) {
            return ShangAnMessageType.operateToJson("1", "订单号不存在");
        }
        // 更新订单状态
        orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_FINISHED);
        orderMain.setModifyDate(new Date());
        orderMainDao.update(orderMain);
        // 目标车场车位数 +1
        parkingService.updateParkingCanUse(orderPark.getTargetParkingId(), 1);
        try {
            // 推送
            String content = "感谢您使用口袋停此次代泊业务";
            JPush.CUSTERMER.sendPushByAlias(content, orderMain.getCustomerId());
        } catch (Exception e) {
            logger.error("", e);
        }
        return ShangAnMessageType.operateToJson("0", "success");
    }

    @Override
    public String login(String parkerMobile, String parkerPassword) {
        String mess = null;
        Map<String, String> parkerMap = new HashMap<>();
        Parker parkerParam = new Parker();
        parkerParam.setParkerMobile(parkerMobile);
        parkerPassword = MD5Util.md5(parkerPassword);
        parkerParam.setParkerPassword(parkerPassword);
        List<Parker> parkerList = parkerDao.selectLogin(parkerParam);
        String mobile=parkerParam.getParkerMobile();
        if (parkerList != null && parkerList.size() > 0) {
            Parking parking=parkingService.queryById(parkerList.get(0).getParkingId());
            parkerMap.put("parkerId", parkerList.get(0).getParkerId());
            parkerMap.put("parkingName", parking.getParkingName());
           // parkerMap.put("parkingName", (null != parkerList.get(0).getParkingName()) ? parkerList.get(0).getParkingName() : "");
            parkerMap.put("parkingId", (null != parkerList.get(0).getParkingId()) ? parkerList.get(0).getParkingId() : "");
            parkerMap.put("parkerMobile", (null != parkerList.get(0).getParkerMobile()) ? parkerList.get(0).getParkerMobile() : "");
            parkerMap.put("parkerLevel", (null != parkerList.get(0).getParkerLevel()) ? parkerList.get(0).getParkerLevel() : "");
            parkerMap.put("parkerName", (null != parkerList.get(0).getParkerName()) ? parkerList.get(0).getParkerName() : "");
            parkerMap.put("parkerCardid", (null != parkerList.get(0).getParkerCardid()) ? parkerList.get(0).getParkerCardid() : "");
            parkerMap.put("parkerAge", parkerList.get(0).getParkerAge() != null ? Integer.toString(parkerList.get(0).getParkerAge()) : "");
            parkerMap.put("parkerSex", parkerList.get(0).getParkerSex() != null ? Integer.toString(parkerList.get(0).getParkerSex()) : "");
            parkerMap.put("parkerDriverAge", parkerList.get(0).getParkerDriverAge() != null ? Integer.toString(parkerList.get(0).getParkerDriverAge()) : "");
            parkerMap.put("parkerHead", (null != parkerList.get(0).getParkerHead()) ? parkerList.get(0).getParkerHead() : "");
            parkerMap.put("parkerDriverNumber", (null != parkerList.get(0).getParkerDriverNumber()) ? parkerList.get(0).getParkerDriverNumber() : "");
            parkerMap.put("parkerDriverImage", (null != parkerList.get(0).getParkerDriverImage()) ? parkerList.get(0).getParkerDriverImage() : "");
            parkerMap.put("parkerType", (null != parkerList.get(0).getParkerType()) ? parkerList.get(0).getParkerType() : "");
            parkerMap.put("state", (null != parkerList.get(0).getState()) ? parkerList.get(0).getState() : "");
            mess = ShangAnMessageType.toShangAnJson("0", "登录成功", "parker", parkerMap);
        } else {
            mess = ShangAnMessageType.operateToJson("2", "密码错误");
        }
        return mess;
    }

    @Override
    public Object queryParkerByOrderId(String orderId) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("orderId", orderId);
        Map<String, Object> map = new HashMap<>(1);
        map.put("params", params);
        List<Object> list = parkerDao.queryParkerById(map);
        return (list != null && list.size() > 0) ? list.get(0) : null;
    }

    @Override
    public List<Parker> selctDaiBoUser(Parker parker) {
        return parkerDao.selctDaiBoUser(parker);
    }

}