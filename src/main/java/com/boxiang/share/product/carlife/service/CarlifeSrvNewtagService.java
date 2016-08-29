package com.boxiang.share.product.carlife.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.carlife.po.CarlifeSrvNewtag;

public interface CarlifeSrvNewtagService {

	List<CarlifeSrvNewtag> selectList(CarlifeSrvNewtag tCarlifeSrvNewtag);
	
	Page<CarlifeSrvNewtag> queryListPage(Page<CarlifeSrvNewtag> page);
	
	CarlifeSrvNewtag queryById(java.lang.Integer id);
	
	void add(CarlifeSrvNewtag tCarlifeSrvNewtag);

	void delete(java.lang.Integer id);
	
	void update(CarlifeSrvNewtag tCarlifeSrvNewtag);
	
	void batchUpdate(List<CarlifeSrvNewtag> tCarlifeSrvNewtagList);
	
	public String updateNewFlag(HttpServletRequest request, HttpServletResponse response,String mobile,String srvId);
}