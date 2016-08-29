package com.boxiang.share.product.parker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.parker.dao.QuestionnaireSurveyDao;
import com.boxiang.share.product.parker.po.QuestionnaireSurvey;
import com.boxiang.share.product.parker.service.QuestionnaireSurveyService;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("questionnaireSurvey")
public class QuestionnaireSurveyServiceImpl implements QuestionnaireSurveyService {
	@Resource(name="questionnaireSurveyDao")
	private QuestionnaireSurveyDao questionnaireSurveyDao;
	
	@Override
	public List<QuestionnaireSurvey> selectList(QuestionnaireSurvey questionnaireSurvey) {
		return questionnaireSurveyDao.selectList(questionnaireSurvey);
	}

	@Override
	public Page<QuestionnaireSurvey> queryListPage(Page<QuestionnaireSurvey> page) {
	    page.setResultList(questionnaireSurveyDao.queryListPage(page));
		return page;
	}
	
	@Override
	public QuestionnaireSurvey queryById(java.lang.Integer id) {
		return questionnaireSurveyDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(QuestionnaireSurvey questionnaireSurvey) {
		questionnaireSurveyDao.insert(questionnaireSurvey);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		questionnaireSurveyDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(QuestionnaireSurvey questionnaireSurvey) {
		questionnaireSurveyDao.update(questionnaireSurvey);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<QuestionnaireSurvey> questionnaireSurveyList) {
		questionnaireSurveyDao.batchUpdate(questionnaireSurveyList);
	}

@Override
public String addQuestionnaireSurvey(QuestionnaireSurvey questionnaireSurvey) {
	 String mess=null;
int index=	 questionnaireSurveyDao.insert(questionnaireSurvey);
	 if(index==1){
		 mess = ShangAnMessageType.operateToJson("0", "添加成功");
	 }else{
		 mess = ShangAnMessageType.operateToJson("2", "数据异常");
	 }
	return mess;
}
}