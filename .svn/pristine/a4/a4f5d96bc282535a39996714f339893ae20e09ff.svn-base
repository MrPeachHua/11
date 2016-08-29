package com.boxiang.share.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.system.dao.SysAuthoritiesResourcesDao;
import com.boxiang.share.system.po.SysAuthoritiesResources;
import com.boxiang.share.system.service.SysAuthoritiesResourcesService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("sysAuthoritiesResources")
public class SysAuthoritiesResourcesServiceImpl implements SysAuthoritiesResourcesService {
	@Resource(name="sysAuthoritiesResourcesDao")
	private SysAuthoritiesResourcesDao sysAuthoritiesResourcesDao;
	
	@Override
	public List<SysAuthoritiesResources> selectList(SysAuthoritiesResources sysAuthoritiesResources) {
		return sysAuthoritiesResourcesDao.selectList(sysAuthoritiesResources);
	}

	@Override
	public Page<SysAuthoritiesResources> queryListPage(Page<SysAuthoritiesResources> page) {
	    page.setResultList(sysAuthoritiesResourcesDao.queryListPage(page));
		return page;
	}
	
	@Override
	public SysAuthoritiesResources queryById(Integer id) {
		return sysAuthoritiesResourcesDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(SysAuthoritiesResources sysAuthoritiesResources) {
		sysAuthoritiesResourcesDao.insert(sysAuthoritiesResources);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		sysAuthoritiesResourcesDao.delete(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void del(String id) {  sysAuthoritiesResourcesDao.del(id); }

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(SysAuthoritiesResources sysAuthoritiesResources) {
		sysAuthoritiesResourcesDao.update(sysAuthoritiesResources);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<SysAuthoritiesResources> sysAuthoritiesResourcesList) {
		sysAuthoritiesResourcesDao.batchUpdate(sysAuthoritiesResourcesList);
	}
}