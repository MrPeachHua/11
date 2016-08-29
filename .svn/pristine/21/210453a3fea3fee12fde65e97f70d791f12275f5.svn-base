package com.boxiang.share.system.synwhite.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.synwhite.dao.WhitesynRecordDao;
import com.boxiang.share.system.synwhite.po.WhitesynRecord;
import com.boxiang.share.system.synwhite.service.WhitesynRecordService;

@DataSource(DataSourceEnum.MASTER)
@Service("tWhitesynRecord")
public class WhitesynRecordServiceImpl implements WhitesynRecordService {
	@Resource(name="whitesynRecordDao")
	private WhitesynRecordDao tWhitesynRecordDao;
	
	@Override
	public List<WhitesynRecord> selectList(WhitesynRecord tWhitesynRecord) {
		return tWhitesynRecordDao.selectList(tWhitesynRecord);
	}

	@Override
	public Page<WhitesynRecord> queryListPage(Page<WhitesynRecord> page) {
	    page.setResultList(tWhitesynRecordDao.queryListPage(page));
		return page;
	}
	
	@Override
	public WhitesynRecord queryById(java.lang.Integer id) {
		return tWhitesynRecordDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(WhitesynRecord tWhitesynRecord) {
		tWhitesynRecordDao.insert(tWhitesynRecord);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		tWhitesynRecordDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(WhitesynRecord tWhitesynRecord) {
		tWhitesynRecordDao.update(tWhitesynRecord);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<WhitesynRecord> tWhitesynRecordList) {
		tWhitesynRecordDao.batchUpdate(tWhitesynRecordList);
	}
}