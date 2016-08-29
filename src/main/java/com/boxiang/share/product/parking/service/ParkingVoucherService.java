package com.boxiang.share.product.parking.service;

import com.boxiang.share.product.parking.po.ParkingVoucher;
import java.util.List;

import com.boxiang.framework.base.page.Page;

public interface ParkingVoucherService {

	List<ParkingVoucher> selectList(ParkingVoucher parkingVoucher);
	
	Page<ParkingVoucher> queryListPage(Page<ParkingVoucher> page);
	List<ParkingVoucher> selectListByDate(ParkingVoucher pv);
	ParkingVoucher queryById(java.lang.Integer id);
	
	void add(ParkingVoucher parkingVoucher);

	void delete(java.lang.Integer id);
	
	void update(ParkingVoucher parkingVoucher);
	
	void batchUpdate(List<ParkingVoucher> tParkingVoucherList);
	public String getList(String customerId, String pageIndex);
	List<ParkingVoucher> queryCountStatus(ParkingVoucher pv);
}