package com.boxiang.share.system.service;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.po.SysRoles;

public interface SysRolesService {

	List<SysRoles> selectList(SysRoles sysRoles);
	
	Page<SysRoles> queryListPage(Page<SysRoles> page);
	
	SysRoles queryById(java.lang.String id);
	
	void add(SysRoles sysRoles);

	void delete(java.lang.String id);
	
	void update(SysRoles sysRoles);
	
	void batchUpdate(List<SysRoles> sysRolesList);
	List<SysRoles> queryList(String roleId,String userId);
	List<SysRoles> queryListRoles(Map<String,Object> map);
	List<SysRoles> selectListRoles(String userId);
	List<SysRoles> queryListRoles2( Map<String,Object> map);
}