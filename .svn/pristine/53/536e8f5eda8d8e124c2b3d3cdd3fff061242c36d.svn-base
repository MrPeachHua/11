package com.boxiang.share.product.carlife.service;

import com.boxiang.share. product.carlife.po.CarlifeEventPage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.framework.base.page.Page;

public interface CarlifeEventPageService {

	List<CarlifeEventPage> selectList(CarlifeEventPage carlifeEventPage);
	
	Page<CarlifeEventPage> queryListPage(Page<CarlifeEventPage> page);
	
	CarlifeEventPage queryById(java.lang.Integer id);
	
	void add(CarlifeEventPage carlifeEventPage); 

	void delete(java.lang.Integer id);
	
	void update(CarlifeEventPage carlifeEventPage);
	
	void batchUpdate(List<CarlifeEventPage> tCarlifeEventPageList);

	List<CarlifeEventPage> selectListForLoop(CarlifeEventPage carlifeEventPage);
	//车生活轮播图片接口
	String getEventPageMessage(HttpServletRequest request, HttpServletResponse response);
}