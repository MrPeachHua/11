package com.boxiang.share.system.synwhite.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.synwhite.dao.WhitesynInfoDao;
import com.boxiang.share.system.synwhite.po.WhitesynInfo;
import com.boxiang.share.system.synwhite.service.WhitesynInfoService;

@DataSource(DataSourceEnum.MASTER)
@Service("whitesynInfoService")
public class WhitesynInfoServiceImpl implements WhitesynInfoService {
	@Resource(name="whitesynInfoDao")
	private WhitesynInfoDao tWhitesynInfoDao;
	
	@Override
	public List<WhitesynInfo> selectList(WhitesynInfo tWhitesynInfo) {
		return tWhitesynInfoDao.selectList(tWhitesynInfo);
	}

	@Override
	public Page<WhitesynInfo> queryListPage(Page<WhitesynInfo> page) {
	    page.setResultList(tWhitesynInfoDao.queryListPage(page));
		return page;
	}
	
	@Override
	public WhitesynInfo queryById(java.lang.Integer id) {
		return tWhitesynInfoDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(WhitesynInfo tWhitesynInfo) {
		tWhitesynInfoDao.insert(tWhitesynInfo);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		tWhitesynInfoDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(WhitesynInfo tWhitesynInfo) {
		tWhitesynInfoDao.update(tWhitesynInfo);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<WhitesynInfo> tWhitesynInfoList) {
		tWhitesynInfoDao.batchUpdate(tWhitesynInfoList);
	}
}