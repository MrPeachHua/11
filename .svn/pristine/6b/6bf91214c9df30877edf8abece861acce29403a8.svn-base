package com.boxiang.share.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.boxiang.share.user.dao.RegionUserDao;
import com.boxiang.share.user.po.RegionUser;
import com.boxiang.share.user.service.RegionUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("regionUserService")
public class RegionUserServiceImpl implements RegionUserService {
	@Resource(name="regionUserDao")
	private RegionUserDao regionUserDao;
	
	@Override
	public List<RegionUser> selectList(RegionUser regionUser) {
		return regionUserDao.selectList(regionUser);
	}

	@Override
	public Page<RegionUser> queryListPage(Page<RegionUser> page) {
	    page.setResultList(regionUserDao.queryListPage(page));
		return page;
	}
	
	@Override
	public RegionUser queryById(Integer id) {
		return regionUserDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(RegionUser regionUser) {
		regionUserDao.insert(regionUser);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		regionUserDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(RegionUser regionUser) {
		regionUserDao.update(regionUser);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<RegionUser> regionUserList) {
		regionUserDao.batchUpdate(regionUserList);
	}
}