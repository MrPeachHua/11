package com.boxiang.share.product.activity.service;

import com.boxiang.share.product.activity.po.CarLifeActivity;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;

import javax.servlet.http.HttpServletRequest;

public interface CarLifeActivityService {

	List<CarLifeActivity> selectList(CarLifeActivity carLifeActivity);
	
	Page<CarLifeActivity> queryListPage(Page<CarLifeActivity> page);
	
	CarLifeActivity queryById(Integer id);
	
	void add(CarLifeActivity carLifeActivity);

	void delete(Integer id);
	
	void update(CarLifeActivity carLifeActivity);
	
	void batchUpdate(List<CarLifeActivity> carLifeActivityList);

	List<CarLifeActivity> queryListPageV2(Page<CarLifeActivity> page);

	List<Map<String, Object>> paramsFilter(List<CarLifeActivity> list, HttpServletRequest request) throws IllegalAccessException;

	List<Object> queryListPageV3(Object page);
}