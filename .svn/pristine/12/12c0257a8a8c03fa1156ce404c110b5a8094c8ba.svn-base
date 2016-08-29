package com.boxiang.share.product.parking.service;

import com.boxiang.share.product.parking.po.ParkingStatus;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;

public interface ParkingStatusService {

	List<ParkingStatus> selectList(ParkingStatus ParkingStatus);
	
	Page<ParkingStatus> queryListPage(Page<ParkingStatus> page);
	
	ParkingStatus queryById(Integer id);
	
	void add(ParkingStatus ParkingStatus);

	void delete(Integer id);
	
	void update(ParkingStatus ParkingStatus);
	
	void batchUpdate(List<ParkingStatus> ParkingStatusList);

	String getParkingStatus(String parkingId);

	List<ParkingStatus> tenseTime(Map<String, Object> map);

	String getParkingStatus2_0_1(String parkingId);
}