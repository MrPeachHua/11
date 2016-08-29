package com.boxiang.share.product.parking.service;

import com.boxiang.share.product.parking.po.PackagePrice;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface PackagePriceService {

	List<PackagePrice> selectList(PackagePrice packagePrice);
	
	Page<PackagePrice> queryListPage(Page<PackagePrice> page);
	
	PackagePrice queryById(Integer id);
	
	void add(PackagePrice packagePrice);

	void delete(Integer id);
	
	void update(PackagePrice packagePrice);
	
	void batchUpdate(List<PackagePrice> packagePriceList);

	List<PackagePrice> queryByParkingId(String parkingId);

	List<PackagePrice> queryByParkingIdAndWeek(String parkingId, String[] weeks);
}