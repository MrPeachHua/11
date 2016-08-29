package com.boxiang.share.product.parking.service;

import com.boxiang.share.product.parking.po.DiscountParkingPrice;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.po.ParkingPrice;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface ParkingPriceService {

	List<ParkingPrice> selectList(ParkingPrice parkingPrice);
	
	Page<ParkingPrice> queryListPage(Page<ParkingPrice> page);
	
	ParkingPrice queryById(String id);
	
	void add(ParkingPrice parkingPrice);

	void delete(String id);
	
	void update(ParkingPrice parkingPrice);
	
	void batchUpdate(List<ParkingPrice> parkingPriceList);

	public void delParkingAndParkingPrice(String id) throws Exception;

	public void updateParkingAndParkingPrice(Parking parking) throws Exception;

	public void saveParkingAndParkingPrice(Parking parking) throws Exception;

	String calcParkPrice(String parkingId, String startTime, String endTime) throws Exception;

	Integer calcPrice(Parking parking, String startTime, String endTime) throws Exception;

	public Parking queryParkingWithPrice(String parkingId);
}