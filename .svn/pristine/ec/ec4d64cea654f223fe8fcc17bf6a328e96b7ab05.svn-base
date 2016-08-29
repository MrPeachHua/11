package com.boxiang.share.product.parker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.parker.dao.ParkerStateRecordDao;
import com.boxiang.share.product.parker.po.ParkerStateRecord;
import com.boxiang.share.product.parker.service.ParkerStateRecordService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("parkerStateRecord")
public class ParkerStateRecordServiceImpl implements ParkerStateRecordService {
	@Resource(name="parkerStateRecordDao")
	private ParkerStateRecordDao parkerStateRecordDao;
	
	@Override
	public List<ParkerStateRecord> selectList(ParkerStateRecord parkerStateRecord) {
		return parkerStateRecordDao.selectList(parkerStateRecord);
	}

	@Override
	public Page<ParkerStateRecord> queryListPage(Page<ParkerStateRecord> page) {
	    page.setResultList(parkerStateRecordDao.queryListPage(page));
		return page;
	}
	
	@Override
	public ParkerStateRecord queryById(Integer id) {
		return parkerStateRecordDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(ParkerStateRecord parkerStateRecord) {
		parkerStateRecordDao.insert(parkerStateRecord);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		parkerStateRecordDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(ParkerStateRecord parkerStateRecord) {
		parkerStateRecordDao.update(parkerStateRecord);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<ParkerStateRecord> parkerStateRecordList) {
		parkerStateRecordDao.batchUpdate(parkerStateRecordList);
	}
}