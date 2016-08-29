package com.boxiang.share.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.boxiang.share.system.po.SysAuthorities;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.system.dao.SysRolesAuthoritiesDao;
import com.boxiang.share.system.po.SysRolesAuthorities;
import com.boxiang.share.system.service.SysRolesAuthoritiesService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("sysRolesAuthorities")
public class SysRolesAuthoritiesServiceImpl implements SysRolesAuthoritiesService {
	@Resource(name="sysRolesAuthoritiesDao")
	private SysRolesAuthoritiesDao sysRolesAuthoritiesDao;
	
	@Override
	public List<SysRolesAuthorities> selectList(SysRolesAuthorities sysRolesAuthorities) {
		return sysRolesAuthoritiesDao.selectList(sysRolesAuthorities);
	}

	@Override
	public Page<SysRolesAuthorities> queryListPage(Page<SysRolesAuthorities> page) {
	    page.setResultList(sysRolesAuthoritiesDao.queryListPage(page));
		return page;
	}
	
	@Override
	public SysRolesAuthorities queryById(Integer id) {
		return sysRolesAuthoritiesDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(SysRolesAuthorities sysRolesAuthorities) {
		sysRolesAuthoritiesDao.insert(sysRolesAuthorities);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		sysRolesAuthoritiesDao.delete(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})

	public void del(String roleId) {
		sysRolesAuthoritiesDao.del(roleId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delByAuthId(String authId) {
		sysRolesAuthoritiesDao.delByAuthId(authId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(SysRolesAuthorities sysRolesAuthorities) {
		sysRolesAuthoritiesDao.update(sysRolesAuthorities);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<SysRolesAuthorities> sysRolesAuthoritiesList) {
		sysRolesAuthoritiesDao.batchUpdate(sysRolesAuthoritiesList);
	}

	@Override
	public List<SysAuthorities> selectList2(String userId) {
		return sysRolesAuthoritiesDao.selectList2(userId);
	}
}