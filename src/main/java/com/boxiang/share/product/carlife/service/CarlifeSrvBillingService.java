package com.boxiang.share.product.carlife.service;

import com.boxiang.share.product.carlife.po.CarlifeSrvBilling;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.parking.po.Parking;

public interface CarlifeSrvBillingService {

	List<CarlifeSrvBilling> selectList(CarlifeSrvBilling carlifeSrvBilling);
	
	Page<CarlifeSrvBilling> queryListPage(Page<CarlifeSrvBilling> page);
	
	CarlifeSrvBilling queryById(java.lang.Integer id);
	
	void add(CarlifeSrvBilling carlifeSrvBilling);

	void delete(java.lang.Integer id);
	
	void update(CarlifeSrvBilling carlifeSrvBilling);
	
	void batchUpdate(List<CarlifeSrvBilling> carlifeSrvBillingList);
	
	public String getSvrFeeInfo(String parkingId,String svrId);
	String selectListParking(String srvId);
}