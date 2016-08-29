package com.boxiang.share.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.user.dao.MenuDao;
import com.boxiang.share.user.po.Menu;
import com.boxiang.share.user.service.MenuService;

@DataSource(DataSourceEnum.MASTER)
@Service("menu")
public class MenuServiceImpl implements MenuService {
	@Resource(name="menuDao")
	private MenuDao menuDao;

	@Override
	public List<Menu> queryByPower(Menu menu){
		return menuDao.queryByPower(menu);
	}
	
	@Override
	public List<Menu> selectList(Menu menu) {
		return menuDao.selectList(menu);
	}

	@Override
	public Page<Menu> queryListPage(Page<Menu> page) {
	    page.setResultList(menuDao.queryListPage(page));
		return page;
	}
	
	@Override
	public Menu queryById(java.lang.Integer id) {
		return menuDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(Menu menu) {
		menuDao.insert(menu);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		menuDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(Menu menu) {
		menuDao.update(menu);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Menu> menuList) {
		menuDao.batchUpdate(menuList);
	}
}