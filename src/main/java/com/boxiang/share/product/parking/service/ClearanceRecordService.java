package com.boxiang.share.product.parking.service;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.parking.po.ClearanceRecord;

import java.util.List;

public interface ClearanceRecordService {

	List<ClearanceRecord> selectList(ClearanceRecord ClearanceRecord);
	
	Page<ClearanceRecord> queryListPage(Page<ClearanceRecord> page);
	
	ClearanceRecord queryById(Integer id);
	
	void add(ClearanceRecord ClearanceRecord);

	void delete(Integer id);
	
	void update(ClearanceRecord ClearanceRecord);
	
	void batchUpdate(List<ClearanceRecord> ClearanceRecordList);
}