package com.boxiang.share.user.service;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.user.po.Menu;

public interface MenuService {
	List<Menu> queryByPower(Menu menu);

	List<Menu> selectList(Menu menu);
	
	Page<Menu> queryListPage(Page<Menu> page);
	
	Menu queryById(java.lang.Integer id);
	
	void add(Menu menu);

	void delete(java.lang.Integer id);
	
	void update(Menu menu);
	
	void batchUpdate(List<Menu> menuList);
}