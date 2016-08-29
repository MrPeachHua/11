package com.boxiang.share.system.service;

import com.boxiang.share.system.po.SysAuthorities;
import com.boxiang.share.system.po.SysRolesAuthorities;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface SysRolesAuthoritiesService {

	List<SysRolesAuthorities> selectList(SysRolesAuthorities sysRolesAuthorities);
	
	Page<SysRolesAuthorities> queryListPage(Page<SysRolesAuthorities> page);
	
	SysRolesAuthorities queryById(Integer id);
	
	void add(SysRolesAuthorities sysRolesAuthorities);

	void delete(Integer id);
	void del(String roleId);
	void delByAuthId(String authId);
	void update(SysRolesAuthorities sysRolesAuthorities);
	
	void batchUpdate(List<SysRolesAuthorities> sysRolesAuthoritiesList);
	List<SysAuthorities> selectList2(String userId);
}