package com.boxiang.share.product.parking.service;

import com.boxiang.share.product.parking.po.DiscountParkingPrice;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface DiscountParkingPriceService {

	List<DiscountParkingPrice> selectList(DiscountParkingPrice discountParkingPrice);
	
	Page<DiscountParkingPrice> queryListPage(Page<DiscountParkingPrice> page);
	
	DiscountParkingPrice queryById(String id);
	
	void add(DiscountParkingPrice discountParkingPrice);

	void delete(String id);
	
	void update(DiscountParkingPrice discountParkingPrice);
	
	void batchUpdate(List<DiscountParkingPrice> discountParkingPriceList);

	String numToStr(String dayOfWeek);
}