package com.boxiang.share.product.coupon.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.coupon.po.RedeemRule;

public interface RedeemRuleDao extends IMybatisBaseDao<RedeemRule> {
    
    void batchUpdate(List<RedeemRule> redeemRuleList);

    List<RedeemRule> selectListWithCouponTemplate(RedeemRule redeemRule);
}