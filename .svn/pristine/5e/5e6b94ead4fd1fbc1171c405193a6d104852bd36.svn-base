package com.boxiang.share.system.service;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.po.SysUsers;

public interface SysUsersService {

	List<SysUsers> selectList(SysUsers sysUsers);
	
	Page<SysUsers> queryListPage(Page<SysUsers> page);
	
	SysUsers queryById(java.lang.String id);
	
	void add(SysUsers sysUsers);

	void delete(java.lang.String id);
	
	void update(SysUsers sysUsers);
	
	void batchUpdate(List<SysUsers> sysUsersList);
	
	List<SysUsers> queryUserRoleByAccount(String userAccount);
	
	List<SysUsers> queryUserByRole(String roleId);
}