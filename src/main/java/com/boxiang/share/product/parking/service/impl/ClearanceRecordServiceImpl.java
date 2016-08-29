package com.boxiang.share.product.parking.service.impl;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.parking.dao.ClearanceRecordDao;
import com.boxiang.share.product.parking.po.ClearanceRecord;
import com.boxiang.share.product.parking.service.ClearanceRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@DataSource(DataSourceEnum.MASTER)
@Service("clearanceRecordService")
public class ClearanceRecordServiceImpl implements ClearanceRecordService {
	@Resource(name="clearanceRecordDao")
	private ClearanceRecordDao clearanceRecordDao;

	@Override
	public List<ClearanceRecord> selectList(ClearanceRecord clearanceRecord) {
		return clearanceRecordDao.selectList(clearanceRecord);
	}

	@Override
	public Page<ClearanceRecord> queryListPage(Page<ClearanceRecord> page) {
	    page.setResultList(clearanceRecordDao.queryListPage(page));
		return page;
	}

	@Override
	public ClearanceRecord queryById(Integer id) {
		return clearanceRecordDao.queryById(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(ClearanceRecord clearanceRecord) {
		clearanceRecordDao.insert(clearanceRecord);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		clearanceRecordDao.delete(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(ClearanceRecord clearanceRecord) {
		clearanceRecordDao.update(clearanceRecord);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<ClearanceRecord> clearanceRecordList) {
		clearanceRecordDao.batchUpdate(clearanceRecordList);
	}
}