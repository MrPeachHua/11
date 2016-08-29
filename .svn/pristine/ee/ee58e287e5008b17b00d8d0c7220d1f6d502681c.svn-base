package com.boxiang.share.system.service;

import java.util.List;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.po.AppVersion;

public interface AppVersionService {

	List<AppVersion> selectList(AppVersion AppVersion);
	
	Page<AppVersion> queryListPage(Page<AppVersion> page);
	
	AppVersion queryById(Integer id);
	
	void add(AppVersion AppVersion);

	void delete(Integer id);
	
	void update(AppVersion AppVersion);
	
	void batchUpdate(List<AppVersion> AppVersionList);
}