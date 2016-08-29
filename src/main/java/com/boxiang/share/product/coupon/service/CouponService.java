package com.boxiang.share.product.coupon.service;

import com.boxiang.share.product.coupon.po.Coupon;

import java.util.List;

import com.boxiang.framework.base.page.Page;

public interface CouponService {

	List<Coupon> selectList(Coupon coupon);
	List<Coupon> queryCouponByOrderId();
	Page<Coupon> queryListPage(Page<Coupon> page);
	
	Coupon queryById(java.lang.String id);
	
	void add(Coupon coupon);

	void delete(java.lang.String id);
	
	void update(Coupon coupon);
	
	void batchUpdate(List<Coupon> tCouponList);
	List<Coupon> selectNum(Coupon coupon);
	List<Coupon> selectNums(Coupon coupon);
	List<Coupon> selectCouponByData(Coupon coupon);
	List<Coupon> selectCouponByCus(Coupon coupon);
	
	List<Coupon> queryCouponByOrder(Coupon coupon);
	List<Coupon> queryByStatusAndCustomerId(Coupon coupon);

	public List<Coupon> queryList(Coupon coupon);
	List<Coupon> queryByCouponStatus();
	String getcouponlist(Page<Coupon> page);

	String cancelCoupon(String orderId, String orderType);
}