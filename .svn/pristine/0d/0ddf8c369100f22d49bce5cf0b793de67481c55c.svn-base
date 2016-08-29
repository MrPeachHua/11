package com.boxiang.share.product.advertising.service;

import com.boxiang.share.product.advertising.po.Advertising;
import com.boxiang.share.product.carlife.po.CarlifeEventPage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.framework.base.page.Page;

public interface AdvertisingService {

	List<Advertising> selectList(Advertising advertising);
	
	Page<Advertising> queryListPage(Page<Advertising> page);
	
	Advertising queryById(java.lang.Integer id);
	
	void add(Advertising advertising);

	void delete(java.lang.Integer id);
	
	void update(Advertising advertising);
	
	void batchUpdate(List<Advertising> advertisingList);
	//首页推广接口
	List<Advertising> selectAdvertising(Advertising advertising);
}