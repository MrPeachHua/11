package com.boxiang.share.product.parker.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.parker.po.QuestionnaireInfo;

public interface QuestionnaireInfoDao extends IMybatisBaseDao<QuestionnaireInfo> {
    
    void batchUpdate(List<QuestionnaireInfo> questionnaireInfoList);
}