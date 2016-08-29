package com.boxiang.share.product.coupon.service.impl;

import java.util.*;

import javax.annotation.Resource;

import cn.b2m.eucp.client.SingletonClient;
import com.boxiang.framework.base.Constants;
import com.boxiang.share.product.coupon.po.CouponTemplate;
import com.boxiang.share.product.coupon.po.SceneRecord;
import com.boxiang.share.product.coupon.service.CouponTemplateService;
import com.boxiang.share.product.coupon.service.RedeemRuleService;
import com.boxiang.share.product.coupon.service.SceneRecordService;
import com.boxiang.share.product.customer.dao.CustomerDao;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.HttpUtil;
import com.boxiang.share.utils.jpush.JPush;
import com.boxiang.share.utils.json.JsonMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.coupon.dao.CouponRuleDao;
import com.boxiang.share.product.coupon.po.CouponRule;
import com.boxiang.share.product.coupon.service.CouponRuleService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("couponRule")
public class CouponRuleServiceImpl implements CouponRuleService {

    private static final Logger log = Logger.getLogger(CouponRuleService.class);

    @Resource(name = "couponRuleDao")
    private CouponRuleDao couponRuleDao;

    @Resource
    private CouponTemplateService couponTemplateService;

    @Resource
    private RedeemRuleService redeemRuleService;

    @Resource
    private CustomerDao customerDao;

    @Resource
    private SceneRecordService sceneRecordService;

    @Override
    public List<CouponRule> selectList(CouponRule couponRule) {
        return couponRuleDao.selectList(couponRule);
    }

    @Override
    public Page<CouponRule> queryListPage(Page<CouponRule> page) {
        page.setResultList(couponRuleDao.queryListPage(page));
        return page;
    }

    @Override
    public CouponRule queryById(Integer id) {
        return couponRuleDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(CouponRule couponRule) {
        couponRuleDao.insert(couponRule);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(Integer id) {
        couponRuleDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(CouponRule couponRule) {
        couponRuleDao.update(couponRule);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<CouponRule> couponRuleList) {
        couponRuleDao.batchUpdate(couponRuleList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void addWithCouponTemplate(CouponRule couponRule) throws Exception {
        couponRule.setIsUsed(Constants.TRUE);
        couponRule.setCreateDate(new Date());
        couponRule.setCreateor("admin");
        this.add(couponRule);
        if (couponRule.getCouponTemplateList() != null && couponRule.getCouponTemplateList().size() > 0) {
            for (CouponTemplate ct : couponRule.getCouponTemplateList()) {
                ct.setType("1"); // 类型，1：车辆入场出场时送的优惠券 2：兑换码搞出来的优惠券
                ct.setTypeId(couponRule.getId());
                ct.setCouponType("1"); // 优惠券类型：1面值，2折扣
                ct.setIsUsed(Constants.TRUE);
                ct.setCreateDate(couponRule.getCreateDate());
                ct.setCreateor(couponRule.getCreateor());
                couponTemplateService.add(ct);
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void updateWithCouponTemplate(CouponRule couponRule) throws Exception {
        this.update(couponRule);
        if (couponRule.getCouponTemplateList() != null && couponRule.getCouponTemplateList().size() > 0) {
            for (CouponTemplate ct : couponRule.getCouponTemplateList()) {
                ct.setType("1"); // 类型，1：车辆入场出场时送的优惠券 2：兑换码搞出来的优惠券
                ct.setCouponType("1"); // 优惠券类型：1面值，2折扣
                ct.setIsUsed(Constants.TRUE);
                ct.setTypeId(couponRule.getId());
                if (ct.getId() == null) { // 新增
                    ct.setCreateDate(new Date());
                    ct.setCreateor(couponRule.getCreateor());
                    couponTemplateService.add(ct);
                } else { // 修改
                    ct.setModifyDate(new Date());
                    ct.setModified("admin");
                    couponTemplateService.update(ct);
                }
            }
        }
    }

//    /**
//     * 场景推送，赠送注册用户，定时任务
//     */
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
//    public synchronized void registerTimedTask() throws Exception {
//        // 查询规则信息
//        Map<String, String> map = new HashMap<>();
//        map.put("mode", "3");
//        List<CouponRule> couponRuleList = couponRuleDao.queryByRule(map);
//        for (CouponRule couponRule : couponRuleList) {
//            procRule(couponRule);
//        }
//    }

    /**
     * 用户注册时调用这个方法赠送优惠券
     * @param customerId
     * @throws Exception
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public boolean registerGiveCoupon(String customerId) throws Exception {
        // 查询规则信息
        Map<String, String> map = new HashMap<>();
        map.put("mode", "3");
        List<CouponRule> couponRuleList = couponRuleDao.queryByRule(map);
        Map<String, Boolean> isSuccessGive = new HashMap<>(1);
        isSuccessGive.put("isGive", false);
        if (couponRuleList != null && couponRuleList.size() > 0) {
            for (CouponRule couponRule : couponRuleList) {
                procRuleV2(couponRule, customerId, isSuccessGive);
            }
        }
        return isSuccessGive.get("isGive");
    }

    /**
     * 处理每一条规则
     */
    private void procRuleV2(CouponRule couponRule, String customerId, Map<String, Boolean> isSuccessGive) throws Exception {
        // 查询符合当前规则下的customerID, 车牌号等信息
        List<Customer> obj = customerWithRegisterRuleV2(couponRule, customerId);
        // 遍历每一个customer
        if (obj == null || obj.size() == 0) {
            return;
        }
        for (Customer cus : obj) {
            // 赠送优惠券给当前customer,传入车牌号，用户id
            giveCoupon(cus, cus.getCarNumber(), couponRule, "3");
        }
        isSuccessGive.put("isGive", true);
    }

    private List<Customer> customerWithRegisterRuleV2(CouponRule couponRule, String customerId) {
        if (StringUtils.isEmpty(couponRule.getUserType())) {
            return null;
        }
        List<Customer> customerList = new ArrayList<>();
        String[] userTypeArray = couponRule.getUserType().split(",");
        Map<String, Object> params = new HashMap<>(6);
        params.put("beginDate", DateUtil.date2str(couponRule.getBeginDate(), DateUtil.DATETIME_FORMAT));
        params.put("endDate", DateUtil.date2str(couponRule.getEndDate(), DateUtil.DATETIME_FORMAT));
        params.put("customerId", customerId);
        params.put("ruleId", couponRule.getId());
        if (StringUtils.isNotEmpty(couponRule.getParkingId())) {
            params.put("parkingId", couponRule.getParkingId());
        }
        for (String userType : userTypeArray) {
            List<Customer> list = null;
            if (userType.equals("1")) { // 月租
                // 根据车牌号，车场id查询
                params.put("tableName", "t_monthlyparkinginfo");
                list = customerDao.customerWithRegisterRule(params);
            } else if (userType.equals("2")) { // 产权
                // 根据车牌号，车场id查询
                params.put("tableName", "t_propertyparkinginfo");
                list = customerDao.customerWithRegisterRule(params);
            }else if (userType.equals("3")) { // 非月租产权用户
                list = customerDao.customerWithNotMonProRegisterRule(params);
            }
            if (list != null) {
                customerList.addAll(list);
            }
        }
        return customerList;
    }

//    /**
//     * 处理每一条规则
//     */
//    private void procRule(CouponRule couponRule) throws Exception {
//        // 筛选出符合条件的规则
//        // 查询符合当前规则下的customerID, 车牌号等信息
//        List<Customer> obj = customerWithRegisterRule(couponRule);
//        // 遍历每一个customer
//        if (obj == null || obj.size() == 0) {
//            return;
//        }
//        for (Customer cus : obj) {
//            // 赠送优惠券给当前customer,传入车牌号，用户id
//            giveCoupon(cus, cus.getCarNumber(), couponRule, "3");
//        }
//    }

//    /**
//     * 查询符合当前规则下的customerID
//     */
//    private List<Customer> customerWithRegisterRule(CouponRule rule) {
//        // 判读一下，看是否在记录表中有记录
//        if (rule.getUserType() == null || rule.getUserType().trim().length() == 0) {
//            return null;
//        }
//        String[] a = rule.getUserType().split(",");
//        List<Customer> listcust = new ArrayList<>();
//        if (rule.getParkingId() != null && !rule.getParkingId().equals("")) {//查询当前车场下的所有月租产权用户
//            for (String anA : a) {
//                if (anA.equals("1")) {//活动时间注册 并且赠送记录表不存在数据的月租用户 参数 ：rule
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("beginDate", DateUtil.date2str(rule.getBeginDate(), DateUtil.DATETIME_FORMAT));
//                    map.put("endDate", DateUtil.date2str(rule.getEndDate(), DateUtil.DATETIME_FORMAT));
//                    map.put("parkingId", rule.getParkingId());
//                    map.put("Id", rule.getId());
//                    List<Customer> listMonthly = customerDao.queryByNow(map);
//                    listcust.addAll(listMonthly);
//                } else if (anA.equals("2")) { //活动时间注册 并且赠送记录表不存在数据的产权用户
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("beginDate", DateUtil.date2str(rule.getBeginDate(), DateUtil.DATETIME_FORMAT));
//                    map.put("endDate", DateUtil.date2str(rule.getEndDate(), DateUtil.DATETIME_FORMAT));
//                    map.put("parkingId", rule.getParkingId());
//                    map.put("Id", rule.getId());
//                    List<Customer> listEquity = customerDao.queryByNow2(map);
//                    listcust.addAll(listEquity);
//                }
//            }
//        } else {//查询所有月租产权用户
//            for (String anA : a) {
//                if (anA.equals("1")) {//活动时间注册 并且赠送记录表不存在数据的月租用户 参数 ：rule
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("beginDate", DateUtil.date2str(rule.getBeginDate(), DateUtil.DATETIME_FORMAT));
//                    map.put("endDate", DateUtil.date2str(rule.getEndDate(), DateUtil.DATETIME_FORMAT));
//                    map.put("Id", rule.getId());
//                    List<Customer> listMonthly = customerDao.queryByNow(map);
//                    listcust.addAll(listMonthly);
//                } else if (anA.equals("2")) { //活动时间注册 并且赠送记录表不存在数据的产权用户
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("beginDate", DateUtil.date2str(rule.getBeginDate(), DateUtil.DATETIME_FORMAT));
//                    map.put("endDate", DateUtil.date2str(rule.getEndDate(), DateUtil.DATETIME_FORMAT));
//                    map.put("Id", rule.getId());
//                    List<Customer> listEquity = customerDao.queryByNow2(map);
//                    listcust.addAll(listEquity);
//                }
//            }
//        }
//        return listcust;
//    }

    /**
     * 停车场获入场的信息
     *
     * @param beginTime 格式 yyyy-MM-dd HH:mm:ss
     * @param endTime   格式 yyyy-MM-dd HH:mm:ss
     */
    public List<Map<String, String>> getInParkInfo(String beginTime, String endTime) throws Exception {
        Map<String, String> params = new HashMap<>(3);
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        params.put("status", "2"); // 2,返回对应所有进场记录
        String result = HttpUtil.netWithJsonParam("http://139.196.47.68/main/open/getInOrOut2", params);
//        String result = "{\n" +
//                "    \"status\":\"success\",\n" +
//                "    \"inparkJson\":[\n" +
//                "        {\n" +
//                "            \"parkId\":\"vyss5vde\",\n" +
//                "            \"plateId\":\"沪A12345\",\n" +
//                "            \"inTime\":\"2016-04-21 01:15:30\",\n" +
//                "            \"sysWriteDate\":\"2016-04-21 01:15:29.0\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"parkId\":\"vyss5vde\",\n" +
//                "            \"plateId\":\"冀B99999\",\n" +
//                "            \"inTime\":\"2016-04-21 03:58:03\",\n" +
//                "            \"sysWriteDate\":\"2016-04-21 03:58:01.0\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"parkId\":\"vyss5vde\",\n" +
//                "            \"plateId\":\"豫A9520K\",\n" +
//                "            \"inTime\":\"2016-04-21 10:00:49\",\n" +
//                "            \"sysWriteDate\":\"2016-04-21 10:00:49.0\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
        Map resultMap = (Map) JsonMapper.fromJson(result, Map.class);
        if (resultMap.get("status").equals("success")) {
            //noinspection unchecked
            return (List<Map<String, String>>) resultMap.get("inparkJson");
        }
        return null;
    }

    /**
     * 车场空位状态变为满时调用这个方法
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void parkingStatusPush(String parkingId) {
        // 查询入场规则信息
        Map<String, String> map = new HashMap<>();
        map.put("mode", "4");
        map.put("parkingId", parkingId);
        List<CouponRule> couponRuleList = couponRuleDao.queryByRule(map);
        if (couponRuleList == null || couponRuleList.size() == 0) {
            return;
        }
        for (CouponRule couponRule : couponRuleList) {
            procParkingStatusPush(couponRule);
        }
    }

    /**
     * 处理每一条规则,车位状态改变时候
     */
    private void procParkingStatusPush(CouponRule couponRule) {
        // 拿到符合条件的月租产权及非月租产权用户
        List<Customer> customerList = customerWithParkingStatusPush(couponRule);
        if (customerList == null || customerList.size() == 0) return;
        // 遍历每一个customer
        for (Customer cus : customerList) {
            // 赠送优惠券给当前customer,传入车牌号，用户id
            try {
                giveCoupon(cus, cus.getCarNumber(), couponRule, "4");
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }

    /**
     * 车位状态改变时候,拿到符合条件的用户
     */
    private List<Customer> customerWithParkingStatusPush(CouponRule couponRule) {
        if (couponRule.getUserType() == null || couponRule.getUserType().trim().length() == 0) {
            return null;
        }
        List<Customer> customerList = new ArrayList<>();
        String[] userTypeArray = couponRule.getUserType().split(",");
        Map<String, Object> params = new HashMap<>();
        params.put("parkingId", couponRule.getParkingId());
        for (String userType : userTypeArray) {
            List<Customer> list = null;
            if (userType.equals("1")) { // 月租
                // 根据车场id查询
                params.put("tableName", "t_monthlyparkinginfo");
                list = customerDao.customerWithParkingStatusPush(params);
            } else if (userType.equals("2")) { // 产权
                // 根据车场id查询
                params.put("tableName", "t_propertyparkinginfo");
                list = customerDao.customerWithParkingStatusPush(params);
            }
            if (list != null && list.size() > 0) {
                customerList.addAll(list);
            }
        }
        return customerList;
    }

    /**
     * 场景推送，赠送入场用户，定时任务
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public synchronized void inParkTimedTask(int minute) throws Exception {
        // 查询入场规则信息
        Map<String, String> map = new HashMap<>();
        map.put("mode", "1");
        List<CouponRule> couponRuleList = couponRuleDao.queryByRule(map);
        if (couponRuleList == null || couponRuleList.size() == 0) {
            return;
        }
        // 拉取蓝卡云数据
        Date end = new Date();
        String endTime = DateUtil.date2str(end, DateUtil.DATETIME_FORMAT); // 当前时间，结束时间
        Date begin = DateUtil.getPreOrNextMinute(end, -minute);
        String beginTime = DateUtil.date2str(begin, DateUtil.DATETIME_FORMAT); // 开始时间
        List<Map<String, String>> inParkList = getInParkInfo(beginTime, endTime);
        if (inParkList == null || inParkList.size() == 0) {
            return;
        }
        for (CouponRule couponRule : couponRuleList) {
            procRuleWithInPark(couponRule, inParkList);
        }
    }

    /**
     * 处理入场数据
     */
    private void procRuleWithInPark(CouponRule couponRule, List<Map<String, String>> inParkList) throws Exception {
        for (Map<String, String> inPark : inParkList) {
            if (couponRule.getBlueParkingId().equals(inPark.get("parkId"))) { // 车场id正好是我们的车场id
                Date inTime = DateUtil.str2date(inPark.get("inTime"), DateUtil.DATETIME_FORMAT);
                couponRule.setModifyDate(inTime); // 临时用ModifyDate传递入场时间，后面会把这个时间存进记录表
                Date beginDate = couponRule.getBeginDate();
                Date endDate = couponRule.getEndDate();
                // 并且入场时间在我们的活动有效期之内
                if (inTime.compareTo(beginDate) == 1 && inTime.compareTo(endDate) == -1) {
                    String carNumber = inPark.get("plateId"); // 车牌号
                    procCarNumber(carNumber, couponRule);
                }
            }
        }
    }

    /**
     * 处理车牌号
     */
    private void procCarNumber(String carNumber, CouponRule couponRule) throws Exception {
        // 查询记录表，判断该车牌号是否赠送过了 已经在sql中处理
        // 拿到符合条件的月租产权及注册用户
        List<Customer> customerList = customerWithInParkRule(couponRule, carNumber);
        if (customerList == null || customerList.size() == 0) return;
        // 遍历每一个customer
        for (Customer cus : customerList) {
            // 赠送优惠券给当前customer,传入车牌号，用户id
            giveCoupon(cus, cus.getCarNumber(), couponRule, "1");
        }
    }

    /**
     * 赠送优惠券给当前customer
     *
     * @param customer  用户
     * @param carNumber 车牌号
     * @param rule      规则对象
     * @param sceneMode 场景模式 1：入场 2：出场 3：注册
     * @throws Exception
     */
    private void giveCoupon(Customer customer, String carNumber, CouponRule rule, String sceneMode) throws Exception {
        // 查询当前规则下的优惠券模板
        CouponTemplate couponTemplate = new CouponTemplate();
        couponTemplate.setIsUsed(Constants.TRUE);
        couponTemplate.setTypeId(rule.getId());
        couponTemplate.setType("1"); // 类型，1：车辆入场出场时送的优惠券 2：兑换码搞出来的优惠券
        List<CouponTemplate> templateList = couponTemplateService.selectList(couponTemplate);
        // 写入记录 t_scene_record 表
        SceneRecord sceneRecord = new SceneRecord();
        sceneRecord.setRuleId(rule.getId());
        sceneRecord.setSceneMode(sceneMode);
        sceneRecord.setCustomerId(customer.getCustomerId());
        sceneRecord.setCarNumber(carNumber == null ? "" : carNumber);
        sceneRecord.setIsUsed(Constants.TRUE);
        sceneRecord.setCreateor("admin");
        sceneRecord.setCreateDate(new Date());
        if (sceneMode.equals("1")) {
            // 入场记录，暂时用修改时间字段当做入场时间用
            sceneRecord.setModifyDate(rule.getModifyDate());
        }
        sceneRecordService.add(sceneRecord);
        if (templateList != null && templateList.size() > 0) {
            // 根据规则模板生成优惠券
            redeemRuleService.generateCouponByTemplate(templateList, customer.getCustomerId(), rule);
        }
        // 发送消息
        pushMessage(customer, rule);
    }

    /**
     * 发送消息
     */
    private void pushMessage(Customer customer, CouponRule rule) {
        if (rule.getPushMode() == null || rule.getPushMode().trim().length() == 0) {
            return;
        }
        String[] pushModeArray = rule.getPushMode().split(",");
        for (String pushMode : pushModeArray) {
            try {
                if (pushMode.equals("1") && StringUtils.isNotEmpty(customer.getCustomerMobile())) { // 1：短信 2：APP极光推送
                    // 发送短信
                    String[] mobiles = {customer.getCustomerMobile()};
                    SingletonClient.getInstance().sendMessage(mobiles, "【口袋停】" + rule.getPushContent(), 5);
                } else if (pushMode.equals("2") && StringUtils.isNotEmpty(customer.getCustomerId())) {
                    // 极光
                    JPush.CUSTERMER.sendPushByAlias(rule.getPushContent(), customer.getCustomerId());
                }
            } catch (Exception e) {
                log.error("推送消息异常", e);
            }
        }
    }

    /**
     * 拿到符合条件的月租产权用户
     */
    private List<Customer> customerWithInParkRule(CouponRule couponRule, String carNumber) {
        if (couponRule.getUserType() == null || couponRule.getUserType().trim().length() == 0) {
            return null;
        }
        List<Customer> customerList = new ArrayList<>();
        String[] userTypeArray = couponRule.getUserType().split(",");
        Map<String, Object> params = new HashMap<>(6);
        params.put("carNumber", carNumber);
        params.put("parkingId", couponRule.getParkingId());
        params.put("ruleId", couponRule.getId());
        params.put("inParkPushCount", couponRule.getInParkPushCount());
        params.put("inTime", couponRule.getModifyDate());
        for (String userType : userTypeArray) {
            List<Customer> list = null;
            if (userType.equals("1")) { // 月租
                // 根据车牌号，车场id查询
                params.put("tableName", "t_monthlyparkinginfo");
                list = customerDao.customerWithInParkRule(params);
            } else if (userType.equals("2")) { // 产权
                // 根据车牌号，车场id查询
                params.put("tableName", "t_propertyparkinginfo");
                list = customerDao.customerWithInParkRule(params);
            }else if (userType.equals("3")) { // 非月租产权用户
                list = customerDao.customerByRester(params);
            }
            if (list != null && list.size() > 0) {
                customerList.addAll(list);
            }
        }
        return customerList;
    }

    /**
     * 定时任务,下单推送
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void orderPushTimedTask() throws Exception {
        final String sceneMode = "5";
        // 查询下单推送规则信息
        Map<String, String> map = new HashMap<>(1);
        map.put("mode", sceneMode);
        List<CouponRule> couponRuleList = couponRuleDao.queryByRule(map);
        if (couponRuleList == null || couponRuleList.size() == 0) {
            return;
        }
        for (CouponRule rule : couponRuleList) {
            if (StringUtils.isEmpty(rule.getOrderType()) || rule.getOrderCount() == null || rule.getOrderCount() <= 0) {
                continue;
            }
            // 查询符合条件的用户信息
            Map<String, Object> params = new HashMap<>();
            params.put("ruleId", rule.getId());
            params.put("sceneMode", sceneMode);
            params.put("orderType", rule.getOrderType());
            params.put("beginDate", rule.getBeginDate());
            params.put("endDate", rule.getEndDate());
            params.put("orderCount", rule.getOrderCount());
            /* customerId orderCount concatOrderId recordOrderId customerMobile */
            List<Map<String, Object>> customerList = customerDao.customerWithOrderPush(params);
            if (customerList == null || customerList.size() == 0) {
                continue;
            }
            for (Map<String, Object> item : customerList) {
                giveCoupon(item, rule, sceneMode);
            }
        }
    }

    /**
     * 赠送优惠券给当前customer
     */
    private void giveCoupon(Map<String, Object> map, CouponRule rule, String sceneMode) throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(MapUtils.getString(map, "customerId", ""));
        customer.setCustomerMobile(MapUtils.getString(map, "customerMobile", ""));

        // 写入记录 t_scene_record 表
        SceneRecord sceneRecord = new SceneRecord();
        sceneRecord.setRuleId(rule.getId());
        sceneRecord.setSceneMode(sceneMode);
        sceneRecord.setCustomerId(customer.getCustomerId());
        sceneRecord.setCarNumber(MapUtils.getString(map, "carNumber", ""));
        sceneRecord.setIsUsed(Constants.TRUE);
        sceneRecord.setCreateor("admin");
        sceneRecord.setCreateDate(new Date());
        if (sceneMode.equals("5")) { // 下单推送
            String[] orderIds = map.get("concatOrderId").toString().split(",");
            StringBuilder sb = new StringBuilder();
            sb.append(orderIds[0]);
            for (int i = 1; i < rule.getOrderCount(); i++) {
                sb.append(",").append(orderIds[i]);
            }
            sceneRecord.setOrderId(sb.toString());
        } else if (sceneMode.equals("6") || sceneMode.equals("7")) { // 月租产权提醒
            Date effectEndTime = (Date) map.get("effectEndTime");
            sceneRecord.setModifyDate(effectEndTime);
        } else if (sceneMode.equals("8")) {
            sceneRecord.setCouponId(map.get("couponId").toString());
        }
        sceneRecordService.add(sceneRecord);

        // 查询当前规则下的优惠券模板
        CouponTemplate couponTemplate = new CouponTemplate();
        couponTemplate.setIsUsed(Constants.TRUE);
        couponTemplate.setTypeId(rule.getId());
        couponTemplate.setType("1"); // 类型，1：车辆入场出场时送的优惠券 2：兑换码搞出来的优惠券
        List<CouponTemplate> templateList = couponTemplateService.selectList(couponTemplate);
        if (templateList != null && templateList.size() > 0 && StringUtils.isNotEmpty(customer.getCustomerId())) {
            // 根据规则模板生成优惠券
            redeemRuleService.generateCouponByTemplate(templateList, customer.getCustomerId(), rule);
        }
        // 发送消息
        pushMessage(customer, rule);
    }

    /**
     * 月租产权缴费提醒,定时任务
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void monthlyPropertyExpireBeforeTimedTask() throws Exception {
        final String sceneMode = "6";
        // 查询下单推送规则信息
        Map<String, String> map = new HashMap<>(1);
        map.put("mode", sceneMode);
        List<CouponRule> couponRuleList = couponRuleDao.queryByRule(map);
        if (couponRuleList == null || couponRuleList.size() == 0) {
            return;
        }
        for (CouponRule rule : couponRuleList) {
            if (rule.getMonthlyPropertyExpireBefore() == null || rule.getMonthlyPropertyExpireBefore() <= 0) {
                continue;
            }
            if (StringUtils.isEmpty(rule.getUserType())) {
                continue;
            }
            // 查询符合条件的用户信息
            Map<String, Object> params = new HashMap<>();
            params.put("ruleId", rule.getId());
            params.put("sceneMode", sceneMode);
            if (StringUtils.isNotEmpty(rule.getParkingId())) {
                params.put("parkingId", rule.getParkingId());
            }
            params.put("monthlyPropertyExpireBefore", rule.getMonthlyPropertyExpireBefore());
            String[] userTypeArray = rule.getUserType().split(",");
            List<Map<String, Object>> customerList = new ArrayList<>();
            for (String userType : userTypeArray) {
                List<Map<String, Object>> list = null;
                if (userType.equals("1")) { // 月租
                    params.put("tableName", "t_monthlyparkinginfo");
                    list = customerDao.monthlyPropertyExpireBefore(params);
                } else if (userType.equals("2")) { // 产权
                    params.put("tableName", "t_propertyparkinginfo");
                    list = customerDao.monthlyPropertyExpireBefore(params);
                }
                if (list != null && list.size() > 0) {
                    customerList.addAll(list);
                }
            }
            if (customerList.size() == 0) {
                continue;
            }
            /* villageId, carNumber, phone, effectEndTime, expireDays, customerMobile, customerId */
            for (Map<String, Object> item : customerList) {
                giveCoupon(item, rule, sceneMode);
            }
        }
    }

    /**
     * 月租产权过期提醒,定时任务
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void monthlyPropertyExpireAfterTimedTask() throws Exception {
        final String sceneMode = "7";
        // 查询下单推送规则信息
        Map<String, String> map = new HashMap<>(1);
        map.put("mode", sceneMode);
        List<CouponRule> couponRuleList = couponRuleDao.queryByRule(map);
        if (couponRuleList == null || couponRuleList.size() == 0) {
            return;
        }
        for (CouponRule rule : couponRuleList) {
            if (rule.getMonthlyPropertyExpireAfter() == null || rule.getMonthlyPropertyExpireAfter() <= 0) {
                continue;
            }
            if (StringUtils.isEmpty(rule.getUserType())) {
                continue;
            }
            // 查询符合条件的用户信息
            Map<String, Object> params = new HashMap<>();
            params.put("ruleId", rule.getId());
            params.put("sceneMode", sceneMode);
            if (StringUtils.isNotEmpty(rule.getParkingId())) {
                params.put("parkingId", rule.getParkingId());
            }
            params.put("monthlyPropertyExpireAfter", rule.getMonthlyPropertyExpireAfter());
            String[] userTypeArray = rule.getUserType().split(",");
            List<Map<String, Object>> customerList = new ArrayList<>();
            for (String userType : userTypeArray) {
                List<Map<String, Object>> list = null;
                if (userType.equals("1")) { // 月租
                    params.put("tableName", "t_monthlyparkinginfo");
                    list = customerDao.monthlyPropertyExpireAfter(params);
                } else if (userType.equals("2")) { // 产权
                    params.put("tableName", "t_propertyparkinginfo");
                    list = customerDao.monthlyPropertyExpireAfter(params);
                }
                if (list != null && list.size() > 0) {
                    customerList.addAll(list);
                }
            }
            if (customerList.size() == 0) {
                continue;
            }
            /* villageId, carNumber, phone, effectEndTime, expireDays, customerMobile, customerId */
            for (Map<String, Object> item : customerList) {
                giveCoupon(item, rule, sceneMode);
            }
        }
    }

    /**
     * 优惠券到期前提醒,定时任务
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void couponExpireAlertTimedTask() throws Exception {
        final String sceneMode = "8";
        // 查询下单推送规则信息
        Map<String, String> map = new HashMap<>(1);
        map.put("mode", sceneMode);
        List<CouponRule> couponRuleList = couponRuleDao.queryByRule(map);
        if (couponRuleList == null || couponRuleList.size() == 0) {
            return;
        }
        for (CouponRule rule : couponRuleList) {
            if (rule.getCouponExpireBefore() == null || rule.getCouponExpireBefore() <= 0) {
                continue;
            }
            // 查询符合条件的用户信息
            Map<String, Object> params = new HashMap<>();
            params.put("ruleId", rule.getId());
            params.put("sceneMode", sceneMode);
            params.put("couponExpireType", rule.getCouponExpireType());
            params.put("couponExpireBefore", rule.getCouponExpireBefore());
            List<Map<String, Object>> customerList = customerDao.couponExpireAlert(params);
            if (customerList == null || customerList.size() == 0) {
                continue;
            }
            /* couponId, customerId, customerMobile */
            for (Map<String, Object> item : customerList) {
                giveCoupon(item, rule, sceneMode);
            }
        }
    }

}