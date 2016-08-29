package com.boxiang.share.system.service;


import java.util.List;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.po.SysLogs;

public interface SysLogsService {

	List<SysLogs> selectList(SysLogs sysLogs);
	
	Page<SysLogs> queryListPage(Page<SysLogs> page);
	
	SysLogs queryById(Integer id);
	
	void add(SysLogs sysLogs);

	void delete(Integer id);
	
	void update(SysLogs sysLogs);
	
	void batchUpdate(List<SysLogs> sysLogsList);
}