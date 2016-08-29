package com.boxiang.share.product.record.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.record.dao.ParkingScanRecordDao;
import com.boxiang.share.product.record.po.ParkingScanRecord;
import com.boxiang.share.product.record.service.ParkingScanRecordService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("parkingScanRecord")
public class ParkingScanRecordServiceImpl implements ParkingScanRecordService {
	@Resource(name="parkingScanRecordDao")
	private ParkingScanRecordDao parkingScanRecordDao;
	
	@Override
	public List<ParkingScanRecord> selectList(ParkingScanRecord parkingScanRecord) {
		return parkingScanRecordDao.selectList(parkingScanRecord);
	}

	@Override
	public Page<ParkingScanRecord> queryListPage(Page<ParkingScanRecord> page) {
	    page.setResultList(parkingScanRecordDao.queryListPage(page));
		return page;
	}
	
	@Override
	public ParkingScanRecord queryById(String id) {
		return parkingScanRecordDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(ParkingScanRecord parkingScanRecord) {
		parkingScanRecordDao.insert(parkingScanRecord);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(String id) {
		parkingScanRecordDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(ParkingScanRecord parkingScanRecord) {
		parkingScanRecordDao.update(parkingScanRecord);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<ParkingScanRecord> parkingScanRecordList) {
		parkingScanRecordDao.batchUpdate(parkingScanRecordList);
	}
}