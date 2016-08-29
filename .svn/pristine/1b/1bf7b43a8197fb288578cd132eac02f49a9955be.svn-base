package com.boxiang.share.product.parking.service;


import java.util.List;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.parking.po.CarInOutRecord;
import com.boxiang.share.product.parking.po.CarInOutRecordV2;

public interface CarInOutRecordService {

	List<CarInOutRecord> selectList(CarInOutRecord carInOutRecord);
	
	Page<CarInOutRecord> queryListPage(Page<CarInOutRecord> page);
	
	CarInOutRecord queryById(Integer id);List<CarInOutRecord> queryCarRecordList(CarInOutRecord carInOutRecord);
	
	void add(CarInOutRecord carInOutRecord);

	void delete(Integer id);
	
	void update(CarInOutRecord carInOutRecord);
	
	void batchUpdate(List<CarInOutRecord> carInOutRecordList);

	void recordInOrOutCar() throws Exception;
	Page<CarInOutRecord> selectCarRecord(Page<CarInOutRecord> page);
	CarInOutRecord selectParkingName(CarInOutRecord carInOutRecord);
	public String queryRecordPage(Page<CarInOutRecordV2> page);
}