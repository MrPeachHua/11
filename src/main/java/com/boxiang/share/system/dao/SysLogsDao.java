package com.boxiang.share.system.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.system.po.SysLogs;

public interface SysLogsDao extends IMybatisBaseDao<SysLogs> {
    
    void batchUpdate(List<SysLogs> sysLogsList);
}