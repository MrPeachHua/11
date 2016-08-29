package com.boxiang.share.product.coupon.service;

import com.boxiang.share.product.coupon.po.CouponRule;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface CouponRuleService {

    List<CouponRule> selectList(CouponRule couponRule);

    Page<CouponRule> queryListPage(Page<CouponRule> page);

    CouponRule queryById(Integer id);

    void add(CouponRule couponRule);

    void delete(Integer id);

    void update(CouponRule couponRule);

    void batchUpdate(List<CouponRule> couponRuleList);

    void addWithCouponTemplate(CouponRule couponRule) throws Exception;

    void updateWithCouponTemplate(CouponRule couponRule) throws Exception;

//    void registerTimedTask() throws Exception;

    boolean registerGiveCoupon(String customerId) throws Exception;

    void parkingStatusPush(String parkingId);

    public void inParkTimedTask(int minute) throws Exception;

    void orderPushTimedTask() throws Exception;

    void monthlyPropertyExpireBeforeTimedTask() throws Exception;

    void monthlyPropertyExpireAfterTimedTask() throws Exception;

    void couponExpireAlertTimedTask() throws Exception;
}