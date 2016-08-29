package com.boxiang.share.product.coupon.service;

import com.boxiang.share.product.coupon.po.CouponRule;
import com.boxiang.share.product.coupon.po.CouponTemplate;
import com.boxiang.share.product.coupon.po.RedeemRule;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;

public interface RedeemRuleService {

    List<RedeemRule> selectList(RedeemRule redeemRule);

    Page<RedeemRule> queryListPage(Page<RedeemRule> page);

    RedeemRule queryById(Integer id);

    void add(RedeemRule redeemRule);

    void delete(Integer id);

    void update(RedeemRule redeemRule);

    void batchUpdate(List<RedeemRule> redeemRuleList);

    void addWithCouponTemplate(RedeemRule redeemRule) throws Exception;

    void updateWithCouponTemplate(RedeemRule redeemRule) throws Exception;

    /**
     * 获取兑换规则
     * @param type 类型，1：新注册用户使用兑换码获取的优惠券 2：老用户兑换码被兑换一定次数后可领取的优惠券
     * @return
     */
    List<Map<String, Object>> getRule(int type, String customerId);

    public List<RedeemRule> selectListWithCouponTemplate(RedeemRule redeemRule);

    /**
     * 是否领取过这个规则的优惠券
     * @param ruleId
     * @param customerId
     * @return
     */
    public boolean isReceive(int ruleId, String customerId);

    /**
     * 到达次数后领取优惠券
     * @param ruleId
     * @param customerId
     */
    String receiveCouponByCount(int ruleId, String customerId) throws Exception;

    /**
     * 根据兑换码拿优惠券
     */
    public boolean receiveCouponByRedeemCode(String redeemCode, String customerId) throws Exception;

    /**
     * 根据规则模板生成优惠券
     */
    public void generateCouponByTemplate(List<CouponTemplate> templateList, String customerId,CouponRule rule) throws Exception;
}