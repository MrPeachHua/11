package com.boxiang.share.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.boxiang.share.system.dao.SysLogsDao;
import com.boxiang.share.system.po.SysLogs;
import com.boxiang.share.system.service.SysLogsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("sysLogsService")
public class SysLogsServiceImpl implements SysLogsService {
	@Resource(name="sysLogsDao")
	private SysLogsDao sysLogsDao;
	
	@Override
	public List<SysLogs> selectList(SysLogs sysLogs) {
		return sysLogsDao.selectList(sysLogs);
	}

	@Override
	public Page<SysLogs> queryListPage(Page<SysLogs> page) {
	    page.setResultList(sysLogsDao.queryListPage(page));
		return page;
	}
	
	@Override
	public SysLogs queryById(Integer id) {
		return sysLogsDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(SysLogs sysLogs) {
		sysLogsDao.insert(sysLogs);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		sysLogsDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(SysLogs sysLogs) {
		sysLogsDao.update(sysLogs);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<SysLogs> sysLogsList) {
		sysLogsDao.batchUpdate(sysLogsList);
	}
}