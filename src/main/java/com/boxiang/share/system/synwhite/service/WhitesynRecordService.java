package com.boxiang.share.system.synwhite.service;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.synwhite.po.WhitesynRecord;

public interface WhitesynRecordService {

	List<WhitesynRecord> selectList(WhitesynRecord tWhitesynRecord);
	
	Page<WhitesynRecord> queryListPage(Page<WhitesynRecord> page);
	
	WhitesynRecord queryById(java.lang.Integer id);
	
	void add(WhitesynRecord tWhitesynRecord);

	void delete(java.lang.Integer id);
	
	void update(WhitesynRecord tWhitesynRecord);
	
	void batchUpdate(List<WhitesynRecord> tWhitesynRecordList);
}