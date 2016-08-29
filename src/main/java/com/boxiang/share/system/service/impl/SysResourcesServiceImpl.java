package com.boxiang.share.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.dao.SysResourcesDao;
import com.boxiang.share.system.po.SysResources;
import com.boxiang.share.system.service.SysResourcesService;

@DataSource(DataSourceEnum.MASTER)
@Service("sysResourcesService")
public class SysResourcesServiceImpl implements SysResourcesService {
	@Resource(name="sysResourcesDao")
	private SysResourcesDao sysResourcesDao;
	
	@Override
	public List<SysResources> selectList(SysResources sysResources) {
		return sysResourcesDao.selectList(sysResources);
	}
	
	@Override
	public List<String> queryUrlByAuthName(String authName){
		return sysResourcesDao.queryUrlByAuthName(authName);
	}

	@Override
	public Page<SysResources> queryListPage(Page<SysResources> page) {
	    page.setResultList(sysResourcesDao.queryListPage(page));
		return page;
	}
	
	@Override
	public SysResources queryById(java.lang.String id) {
		return sysResourcesDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(SysResources sysResources) {
		sysResourcesDao.insert(sysResources);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.String id) {
		sysResourcesDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(SysResources sysResources) {
		sysResourcesDao.update(sysResources);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<SysResources> sysResourcesList) {
		sysResourcesDao.batchUpdate(sysResourcesList);
	}

	@Override
	public List<SysResources> queryList(String resourceId) {
		return sysResourcesDao.queryList(resourceId);
	}
}