package com.boxiang.share.system.synwhite.service;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.synwhite.po.WhitesynInfo;

public interface WhitesynInfoService {

	List<WhitesynInfo> selectList(WhitesynInfo tWhitesynInfo);
	
	Page<WhitesynInfo> queryListPage(Page<WhitesynInfo> page);
	
	WhitesynInfo queryById(java.lang.Integer id);
	
	void add(WhitesynInfo tWhitesynInfo);

	void delete(java.lang.Integer id);
	
	void update(WhitesynInfo tWhitesynInfo);
	
	void batchUpdate(List<WhitesynInfo> tWhitesynInfoList);
}