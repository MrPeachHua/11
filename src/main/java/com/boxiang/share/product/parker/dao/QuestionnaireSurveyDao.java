package com.boxiang.share.product.parker.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.parker.po.QuestionnaireSurvey;

public interface QuestionnaireSurveyDao extends IMybatisBaseDao<QuestionnaireSurvey> {
    
    void batchUpdate(List<QuestionnaireSurvey> questionnaireSurveyList);
}