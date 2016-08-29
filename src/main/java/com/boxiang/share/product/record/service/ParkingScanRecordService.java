package com.boxiang.share.product.record.service;

import com.boxiang.share.product.record.po.ParkingScanRecord;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface ParkingScanRecordService {

	List<ParkingScanRecord> selectList(ParkingScanRecord parkingScanRecord);
	
	Page<ParkingScanRecord> queryListPage(Page<ParkingScanRecord> page);
	
	ParkingScanRecord queryById(String id);
	
	void add(ParkingScanRecord parkingScanRecord);

	void delete(String id);
	
	void update(ParkingScanRecord parkingScanRecord);
	
	void batchUpdate(List<ParkingScanRecord> parkingScanRecordList);
}