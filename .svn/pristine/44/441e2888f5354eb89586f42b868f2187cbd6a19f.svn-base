package com.boxiang.share.system.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.dao.SysRolesDao;
import com.boxiang.share.system.po.SysRoles;
import com.boxiang.share.system.service.SysRolesService;

@DataSource(DataSourceEnum.MASTER)
@Service("sysRolesService")
public class SysRolesServiceImpl implements SysRolesService {
	@Resource(name="sysRolesDao")
	private SysRolesDao sysRolesDao;
	
	@Override
	public List<SysRoles> selectList(SysRoles sysRoles) {
		return sysRolesDao.selectList(sysRoles);
	}

	@Override
	public Page<SysRoles> queryListPage(Page<SysRoles> page) {
	    page.setResultList(sysRolesDao.queryListPage(page));
		return page;
	}
	
	@Override
	public SysRoles queryById(java.lang.String id) {
		return sysRolesDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(SysRoles sysRoles) {
		sysRolesDao.insert(sysRoles);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.String id) {
		sysRolesDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(SysRoles sysRoles) {
		sysRolesDao.update(sysRoles);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<SysRoles> sysRolesList) {
		sysRolesDao.batchUpdate(sysRolesList);
	}

	@Override
	public List<SysRoles> queryList(String roleId,String userId) {
		return sysRolesDao.queryList(roleId,userId);
	}

	@Override
	public List<SysRoles> queryListRoles(Map<String,Object> map) {
		return sysRolesDao.queryListRoles(map);
	}

	@Override
	public List<SysRoles> selectListRoles(String userId) {
		return sysRolesDao.selectListRoles(userId);
	}

	@Override
	public List<SysRoles> queryListRoles2( Map<String,Object> map) {
		return sysRolesDao.queryListRoles2(map);
	}
}