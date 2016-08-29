package com.boxiang.share.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.boxiang.share.system.dao.AppVersionDao;
import com.boxiang.share.system.po.AppVersion;
import com.boxiang.share.system.service.AppVersionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("appVersion")
public class AppVersionServiceImpl implements AppVersionService {
	@Resource(name="appVersionDao")
	private AppVersionDao appVersionDao;
	
	@Override
	public List<AppVersion> selectList(AppVersion AppVersion) {
		return appVersionDao.selectList(AppVersion);
	}

	@Override
	public Page<AppVersion> queryListPage(Page<AppVersion> page) {
	    page.setResultList(appVersionDao.queryListPage(page));
		return page;
	}
	
	@Override
	public AppVersion queryById(Integer id) {
		return appVersionDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(AppVersion AppVersion) {
		appVersionDao.insert(AppVersion);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		appVersionDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(AppVersion AppVersion) {
		appVersionDao.update(AppVersion);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<AppVersion> AppVersionList) {
	  appVersionDao.batchUpdate(AppVersionList);
	}
}