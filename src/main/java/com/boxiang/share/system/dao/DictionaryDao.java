package com.boxiang.share.system.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.system.po.Dictionary;

public interface DictionaryDao extends IMybatisBaseDao<Dictionary> {
    
    void batchUpdate(List<Dictionary> tDictionaryList);
}