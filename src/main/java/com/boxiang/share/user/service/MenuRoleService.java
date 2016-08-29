package com.boxiang.share.user.service;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.po.SysRoles;
import com.boxiang.share.user.po.Menu;
import com.boxiang.share.user.po.MenuRole;
import com.boxiang.share.user.po.UserRole;

public interface MenuRoleService {

	List<MenuRole> selectList(MenuRole menuRole);
	
	Page<MenuRole> queryListPage(Page<MenuRole> page);
	
	MenuRole queryById(java.lang.Integer id);
	
	void add(MenuRole menuRole);

	void delete(java.lang.Integer id);
	
	void update(MenuRole menuRole);
	
	void batchUpdate(List<MenuRole> menuRoleList);

	List<Menu> menuJoinMenuRole(MenuRole menuRole);

	List<Menu> menuJoinMenuRole2(MenuRole menuRole);
	List<Menu> menuJoinMenuRole3(MenuRole menuRole);
	List<Menu> menuJoinMenuRole4(MenuRole menuRole);
	void updateRole(List<Menu> menuList, List<String> checkedMenuCodeArray, UserRole userRole);

	void updateRoleV2(List<Menu> menuList, List<String> checkedMenuCodeArray, SysRoles roles);
}