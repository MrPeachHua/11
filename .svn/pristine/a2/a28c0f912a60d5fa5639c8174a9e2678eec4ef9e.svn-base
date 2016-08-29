package com.boxiang.share.product.coupon.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.coupon.po.CouponRule;

public interface CouponRuleDao extends IMybatisBaseDao<CouponRule> {
    
    void batchUpdate(List<CouponRule> couponRuleList);
    List<CouponRule> queryByRule(Map<String,String> map);   //查询当前时间是否有优惠活动
}