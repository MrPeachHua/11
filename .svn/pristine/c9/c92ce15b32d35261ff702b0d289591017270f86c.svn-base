package com.boxiang.share.product.coupon.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.coupon.po.Coupon;

public interface CouponDao extends IMybatisBaseDao<Coupon> {
    
    void batchUpdate(List<Coupon> tCouponList);
    
    List<Coupon> selectRegCoupons(Map<String,String> param);
    List<Coupon> selectNum(Coupon coupon);
    List<Coupon> selectNums(Coupon coupon);
    List<Coupon> selectCouponByData(Coupon coupon);
    List<Coupon> selectCouponByCus(Coupon coupon);
    List<Coupon> queryCouponByOrder(Coupon coupon);
    List<Coupon> queryCouponByOrderId();
    List<Coupon> queryByStatusAndCustomerId(Coupon coupon);

    List<Coupon> queryList(Coupon coupon);
    List<Coupon> queryByCouponStatus();
    List<Coupon> getcouponlist(Page<Coupon> page);
}