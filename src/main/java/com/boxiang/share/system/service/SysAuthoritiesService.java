package com.boxiang.share.system.service;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.po.SysAuthorities;

public interface SysAuthoritiesService {

	List<SysAuthorities> selectList(SysAuthorities sysAuthorities);
	
	Page<SysAuthorities> queryListPage(Page<SysAuthorities> page);
	
	SysAuthorities queryById(java.lang.String id);
	
	void add(SysAuthorities sysAuthorities);

	void delete(java.lang.String id);
	
	void update(SysAuthorities sysAuthorities);
	
	void batchUpdate(List<SysAuthorities> sysAuthoritiesList);
	
	List<String> queryAllAuthName();
	
	List<String> loadUserAuthoritiesByAccount(String userAccount);
	List<SysAuthorities> queryAuthorities(String resourseId);
	List<SysAuthorities> queryAuthoritiesByRoles(String rolesId);
}