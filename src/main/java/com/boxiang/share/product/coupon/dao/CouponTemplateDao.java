package com.boxiang.share.product.coupon.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.coupon.po.CouponTemplate;

public interface CouponTemplateDao extends IMybatisBaseDao<CouponTemplate> {
    
    void batchUpdate(List<CouponTemplate> couponTemplateList);
}