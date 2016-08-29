package com.boxiang.share.system.synwhite.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.system.synwhite.po.WhitesynRecord;

public interface WhitesynRecordDao extends IMybatisBaseDao<WhitesynRecord> {
    
    void batchUpdate(List<WhitesynRecord> tWhitesynRecordList);
}