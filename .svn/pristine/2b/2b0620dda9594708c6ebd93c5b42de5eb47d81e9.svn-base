package com.boxiang.share.product.carlife.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.carlife.po.CarlifeSrvInfo;

public interface CarlifeSrvInfoService {

	List<CarlifeSrvInfo> selectList(CarlifeSrvInfo carlifeSrvInfo);
	
	Page<CarlifeSrvInfo> queryListPage(Page<CarlifeSrvInfo> page);
	
	CarlifeSrvInfo queryById(java.lang.Integer id);
	
	void add(CarlifeSrvInfo carlifeSrvInfo);

	void delete(java.lang.Integer id);
	
	void update(CarlifeSrvInfo carlifeSrvInfo);
	
	void batchUpdate(List<CarlifeSrvInfo> carlifeSrvInfoList);
	
	String getCarlifeSrvInfoMessage(HttpServletRequest request, HttpServletResponse response);
	int querySortMax();
	String queryCharge(String parkingId);
	 String queryImage(String parkingId,HttpServletRequest request);
}