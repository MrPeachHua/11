package com.boxiang.share.product.customer.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.customer.po.EventQuestionnaire;

public interface EventQuestionnaireDao extends IMybatisBaseDao<EventQuestionnaire> {
    
    void batchUpdate(List<EventQuestionnaire> eventQuestionnaireList);
}