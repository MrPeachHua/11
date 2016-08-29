package com.boxiang.share.product.coupon.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.product.coupon.po.*;
import com.boxiang.share.product.coupon.service.CouponService;
import com.boxiang.share.product.coupon.service.CouponTemplateService;
import com.boxiang.share.product.coupon.service.RedeemRecordService;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.po.Rule;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.customer.service.RedDotService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.coupon.dao.RedeemRuleDao;
import com.boxiang.share.product.coupon.service.RedeemRuleService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("redeemRule")
public class RedeemRuleServiceImpl implements RedeemRuleService {

    @Resource(name = "redeemRuleDao")
    private RedeemRuleDao redeemRuleDao;

    @Resource
    private CouponTemplateService couponTemplateService;

    @Resource
    private RedeemRecordService redeemRecordService;

    @Resource
    private RedDotService redDotService;

    @Resource
    private CouponService couponService;

    @Resource
    private CustomerService customerService;

    @Override
    public List<RedeemRule> selectList(RedeemRule redeemRule) {
        return redeemRuleDao.selectList(redeemRule);
    }

    @Override
    public Page<RedeemRule> queryListPage(Page<RedeemRule> page) {
        page.setResultList(redeemRuleDao.queryListPage(page));
        return page;
    }

    @Override
    public RedeemRule queryById(Integer id) {
        return redeemRuleDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(RedeemRule redeemRule) {
        redeemRuleDao.insert(redeemRule);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(Integer id) {
        redeemRuleDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(RedeemRule redeemRule) {
        redeemRuleDao.update(redeemRule);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<RedeemRule> redeemRuleList) {
        redeemRuleDao.batchUpdate(redeemRuleList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void addWithCouponTemplate(RedeemRule redeemRule) throws Exception {
        redeemRule.setIsUsed(Constants.TRUE);
        redeemRule.setCreateDate(new Date());
        redeemRule.setCreateor("admin");
        this.add(redeemRule);
        for (CouponTemplate ct : redeemRule.getCouponTemplateList()) {
            ct.setType("2"); // 类型，1：车辆入场出场时送的优惠券 2：兑换码搞出来的优惠券
            ct.setTypeId(redeemRule.getId());
            ct.setCouponType("1"); // 优惠券类型：1面值，2折扣
            ct.setIsUsed(Constants.TRUE);
            ct.setCreateDate(redeemRule.getCreateDate());
            ct.setCreateor(redeemRule.getCreateor());
            couponTemplateService.add(ct);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void updateWithCouponTemplate(RedeemRule redeemRule) throws Exception {
        this.update(redeemRule);
        if (redeemRule.getCouponTemplateList() != null && redeemRule.getCouponTemplateList().size() > 0) {
            for (CouponTemplate ct : redeemRule.getCouponTemplateList()) {
                ct.setType("2"); // 类型，1：车辆入场出场时送的优惠券 2：兑换码搞出来的优惠券
                ct.setCouponType("1"); // 优惠券类型：1面值，2折扣
                ct.setIsUsed(Constants.TRUE);
                ct.setTypeId(redeemRule.getId());
                if (ct.getId() == null) { // 新增
                    ct.setCreateDate(new Date());
                    ct.setCreateor(redeemRule.getCreateor());
                    couponTemplateService.add(ct);
                } else { // 修改
                    ct.setModifyDate(new Date());
                    ct.setModified("admin");
                    couponTemplateService.update(ct);
                }
            }
        }
    }

    @Override
    public List<RedeemRule> selectListWithCouponTemplate(RedeemRule redeemRule) {
        return redeemRuleDao.selectListWithCouponTemplate(redeemRule);
    }

    /**
     * 获取兑换规则
     *
     * @param type 类型，1：新注册用户使用兑换码获取的优惠券 2：老用户兑换码被兑换一定次数后可领取的优惠券
     */
    @Override
    public List<Map<String, Object>> getRule(int type, String customerId) {
        RedeemRule queryEntity = new RedeemRule();
        queryEntity.setType(Integer.toString(type));
        queryEntity.setIsUsed(Constants.TRUE);
        List<RedeemRule> list = redeemRuleDao.selectListWithCouponTemplate(queryEntity);
        List<Map<String, Object>> dataList = new ArrayList<>(list.size());
        for (RedeemRule rule : list) {
            Map<String, Object> ruleMap = new HashMap<>(3);
            ruleMap.put("id", rule.getId());
            ruleMap.put("redeemCount", rule.getRedeemCount());
            ruleMap.put("isReceive", isReceive(rule.getId(), customerId) ? "1" : "0");
            List<Map<String, Object>> couponList = new ArrayList<>(rule.getCouponTemplateList().size());
            ruleMap.put("couponList", couponList);
            for (CouponTemplate ct : rule.getCouponTemplateList()) {
                Map<String, Object> couponMap = new HashMap<>(2);
                couponMap.put("parAmount", ct.getParAmount());
                couponMap.put("couponKind", ct.getCouponKind()); // 优惠券类型 0：通用卷 1：停车卷 2：月租产权劵 3：代泊券 4：车管家券
                couponList.add(couponMap);
            }
            dataList.add(ruleMap);
        }
        return dataList;
    }

    /**
     * 判断是否领取过该规则
     *
     * @return true 领过 false 没领过
     */
    @Override
    public boolean isReceive(int ruleId, String customerId) {
        RedeemRecord recordQuery = new RedeemRecord();
        recordQuery.setRedeemRuleId(ruleId);
        recordQuery.setNewCustomerId(customerId);
        recordQuery.setIsUsed(Constants.TRUE);
        List<RedeemRecord> recordList = redeemRecordService.selectList(recordQuery);
        return recordList != null && recordList.size() > 0;
    }

    /**
     * 到达次数后领取优惠券
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public String receiveCouponByCount(int ruleId, String customerId) throws Exception {
        // 判断用户是否存在
        if (customerService.queryByCustomerId(customerId) == null) {
            return ShangAnMessageType.operateToJson("1", "没有这个用户");
        }

        // 查询规则
        RedeemRule ruleQuery = new RedeemRule();
        ruleQuery.setId(ruleId);
        ruleQuery.setType("2"); // 1：新注册用户使用兑换码获取的优惠券 2：老用户兑换码被兑换一定次数后可领取的优惠券
        ruleQuery.setIsUsed(Constants.TRUE);
        List<RedeemRule> ruleList = this.selectListWithCouponTemplate(ruleQuery);
        if (ruleList == null || ruleList.size() == 0) {
            return ShangAnMessageType.operateToJson("1", "查不到规则信息");
        }
        RedeemRule rule = ruleList.get(0); // 要领取的规则

        // 查询领取记录，判断有没有领取过该优惠券
        if (this.isReceive(rule.getId(), customerId)) {
            return ShangAnMessageType.operateToJson("1", "你已经领取过了该优惠券");
        }

        // 查询记录，判断被兑换的次数
        RedeemRecord recordQuery = new RedeemRecord();
        recordQuery.setOldCustomerId(customerId);
        recordQuery.setIsUsed(Constants.TRUE);
        int beRedeemCount = redeemRecordService.selectCount(recordQuery);
        // 比较次数够不够
        if (beRedeemCount < rule.getRedeemCount()) {
            return ShangAnMessageType.operateToJson("1", "没有达到领取条件");
        }

		/* 符合规则，领券 */
        // 插入一条领取记录
        RedeemRecord record = new RedeemRecord();
        record.setRedeemRuleId(rule.getId());
        record.setType("2"); // 类型，1：新注册用户使用兑换码获取的优惠券 2：老用户兑换码被兑换一定次数后可领取的优惠券
        record.setNewCustomerId(customerId);
        record.setIsUsed(Constants.TRUE);
        record.setCreateDate(new Date());
        record.setCreateor("admin");
        redeemRecordService.add(record);
        // 根据规则模板生成优惠券
        this.generateCouponByTemplate(rule.getCouponTemplateList(), customerId, null);
        return ShangAnMessageType.operateToJson("0", "领取成功");
    }

    /**
     * 根据规则模板生成优惠券
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void generateCouponByTemplate(List<CouponTemplate> templateList, String customerId, CouponRule rule) throws Exception {
        // 生成优惠券
        for (CouponTemplate ct : templateList) {
            Coupon coupon = new Coupon();
            coupon.setCouponId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
            coupon.setVouchersName(ct.getCouponName());
            coupon.setChannel("3"); // 渠道：活动
            coupon.setCouponType(ct.getCouponType());
            coupon.setParAmount(ct.getParAmount().intValue());
            coupon.setMinconsumption(ct.getMinconsumption().intValue());
            coupon.setParDiscount(0); // 目前用不上折扣，所以默认给个0
            if (ct.getEffectiveType().equals("1")) { // 有效类型 1：使用开始结束时间 2：使用有效天数
                coupon.setEffectiveBegin(DateUtil.date2str(ct.getEffectiveBegin(), DateUtil.DATE_FORMAT) + " 00:00:00");
                coupon.setEffectiveEnd(DateUtil.date2str(ct.getEffectiveEnd(), DateUtil.DATE_FORMAT) + " 23:59:59");
            } else if (ct.getEffectiveType().equals("2")) {
                Date now = new Date();
                coupon.setEffectiveBegin(DateUtil.date2str(now, DateUtil.DATE_FORMAT) + " 00:00:00");
                Date end = DateUtil.getPreOrNextDate(now, ct.getEffectiveDay() - 1);
                coupon.setEffectiveEnd(DateUtil.date2str(end, DateUtil.DATE_FORMAT) + " 23:59:59");
                if (rule != null) {
                    coupon.setReceiveBegin(DateUtil.date2str(rule.getBeginDate(), DateUtil.DATE_FORMAT) + " 00:00:00");
                    coupon.setReceiveEnd(DateUtil.date2str(rule.getEndDate(), DateUtil.DATE_FORMAT) + " 23:59:59");
                }
            }
            coupon.setExclusionRule(ct.getExclusionRule());
            coupon.setInfo(ct.getInfo());
            coupon.setParkingId(ct.getParkingId());
            coupon.setCouponStatus("100201"); // 未过期已领取未使用
            coupon.setCustomerId(customerId);
            coupon.setReceiveTime(DateUtil.date2str(new Date(), DateUtil.DATETIME_FORMAT));
            coupon.setCouponKind(ct.getCouponKind());
            coupon.setCreator("admin");
            coupon.setCreateTime(DateUtil.date2str(new Date(), DateUtil.DATETIME_FORMAT));
            couponService.add(coupon);
        }
        // 开启小红点
        redDotService.openRedDot(customerId, 1, templateList.size());
    }

    /**
     * 根据兑换码拿优惠券
     * @return true 兑换成功 false 兑换失败
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public boolean receiveCouponByRedeemCode(String redeemCode, String customerId) throws Exception {
        // 非空验证
        if (redeemCode == null || redeemCode.trim().length() == 0 || customerId == null || customerId.trim().length() == 0) {
            return false;
        }
        // 根据customerId查询用户信息
        Customer newCustomer = customerService.queryByCustomerId(customerId);
        if (newCustomer == null) { // 用户不存在
            return false;
        }
        if (newCustomer.getRedeemCode() != null && newCustomer.getRedeemCode().equals(redeemCode)) {
            return false; // 不允许用自己的兑换码兑换优惠券
        }
        // 根据兑换码查询这个兑换码的用户信息
        Customer oldCustomer = customerService.queryByRedeemCode(redeemCode);
        if (oldCustomer == null) { // 这个兑换码不存在
            return false;
        }
        // 查询领取记录，判读有没有领取过
        RedeemRecord recordQuery = new RedeemRecord();
        recordQuery.setType("1"); // 类型，1：新注册用户使用兑换码获取的优惠券 2：老用户兑换码被兑换一定次数后可领取的优惠券
        recordQuery.setNewCustomerId(customerId);
        recordQuery.setIsUsed(Constants.TRUE);
        int count = redeemRecordService.selectCount(recordQuery);
        if (count > 0) { // 有领取记录,表示已经领过了
            return false;
        }
        // 查询领取规则
        RedeemRule ruleQuery = new RedeemRule();
        ruleQuery.setIsUsed(Constants.TRUE);
        ruleQuery.setType("1"); // 类型，1：新注册用户使用兑换码获取的优惠券 2：老用户兑换码被兑换一定次数后可领取的优惠券
        List<RedeemRule> ruleList = this.selectListWithCouponTemplate(ruleQuery);
        if (ruleList == null || ruleList.size() == 0) {
            return false;
        }
        /* 满足条件，领券 */
        // 插入领取记录
        RedeemRecord record = new RedeemRecord();
        record.setRedeemRuleId(ruleList.get(0).getId());
        record.setType("1"); // 类型，1：新注册用户使用兑换码获取的优惠券 2：老用户兑换码被兑换一定次数后可领取的优惠券
        record.setOldCustomerId(oldCustomer.getCustomerId());
        record.setNewCustomerId(newCustomer.getCustomerId());
        record.setIsUsed(Constants.TRUE);
        record.setCreateDate(new Date());
        record.setCreateor("admin");
        redeemRecordService.add(record);
        for (RedeemRule rule : ruleList) {
            // 根据规则模板生成优惠券
            this.generateCouponByTemplate(rule.getCouponTemplateList(), customerId,null);
        }
        return true;
    }

}
