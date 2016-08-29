package com.boxiang.share.system.service;

import com.boxiang.share.system.po.SysUsersRoles;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface SysUsersRolesService {

	List<SysUsersRoles> selectList(SysUsersRoles sysUsersRoles);
	
	Page<SysUsersRoles> queryListPage(Page<SysUsersRoles> page);
	
	SysUsersRoles queryById(Integer id);
	
	void add(SysUsersRoles sysUsersRoles);

	void delete(Integer id);
	void del(String id);
	void del2(String id);
	void update(SysUsersRoles sysUsersRoles);
	
	void batchUpdate(List<SysUsersRoles> sysUsersRolesList);
}