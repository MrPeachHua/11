package com.boxiang.share.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.dao.SysAuthoritiesDao;
import com.boxiang.share.system.po.SysAuthorities;
import com.boxiang.share.system.service.SysAuthoritiesService;

@DataSource(DataSourceEnum.MASTER)
@Service("sysAuthoritiesService")
public class SysAuthoritiesServiceImpl implements SysAuthoritiesService {
	@Resource(name = "sysAuthoritiesDao")
	private SysAuthoritiesDao sysAuthoritiesDao;

	@Override
	public List<SysAuthorities> selectList(SysAuthorities sysAuthorities) {
		return sysAuthoritiesDao.selectList(sysAuthorities);
	}

	@Override
	public Page<SysAuthorities> queryListPage(Page<SysAuthorities> page) {
		page.setResultList(sysAuthoritiesDao.queryListPage(page));
		return page;
	}

	@Override
	public SysAuthorities queryById(java.lang.String id) {
		return sysAuthoritiesDao.queryById(id);
	}
	
	@Override
	public List<String> queryAllAuthName(){
		return sysAuthoritiesDao.queryAllAuthName();
	}
	
	@Override
	public List<String> loadUserAuthoritiesByAccount(String userAccount){
		return sysAuthoritiesDao.loadUserAuthoritiesByAccount(userAccount);
	}

	@Override
	public List<SysAuthorities> queryAuthorities(String resourseId) {
		return sysAuthoritiesDao.queryAuthorities(resourseId);
	}

	@Override
	public List<SysAuthorities> queryAuthoritiesByRoles(String rolesId) {
		return sysAuthoritiesDao.queryAuthoritiesByRoles(rolesId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class, Exception.class })
	public void add(SysAuthorities sysAuthorities) {
		sysAuthoritiesDao.insert(sysAuthorities);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class, Exception.class })
	public void delete(java.lang.String id) {
		sysAuthoritiesDao.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class, Exception.class })
	public void update(SysAuthorities sysAuthorities) {
		sysAuthoritiesDao.update(sysAuthorities);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class, Exception.class })
	public void batchUpdate(List<SysAuthorities> sysAuthoritiesList) {
		sysAuthoritiesDao.batchUpdate(sysAuthoritiesList);
	}
}