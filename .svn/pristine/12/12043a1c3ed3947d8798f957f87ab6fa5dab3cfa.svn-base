package com.boxiang.share.system.service;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.po.SysResources;

public interface SysResourcesService {

	List<SysResources> selectList(SysResources sysResources);
	
	List<String> queryUrlByAuthName(String authName);
	
	Page<SysResources> queryListPage(Page<SysResources> page);
	
	SysResources queryById(java.lang.String id);
	
	void add(SysResources sysResources);

	void delete(java.lang.String id);
	
	void update(SysResources sysResources);
	
	void batchUpdate(List<SysResources> sysResourcesList);
	List<SysResources> queryList(String resourceId);
}