package com.boxiang.share.product.parker.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.parker.dao.QuestionnaireInfoDao;
import com.boxiang.share.product.parker.po.QuestionnaireInfo;
import com.boxiang.share.product.parker.service.QuestionnaireInfoService;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("questionnaireInfo")
public class QuestionnaireInfoServiceImpl implements QuestionnaireInfoService {
	@Resource(name="questionnaireInfoDao")
	private QuestionnaireInfoDao questionnaireInfoDao;
	
	@Override
	public List<QuestionnaireInfo> selectList(QuestionnaireInfo questionnaireInfo) {
		return questionnaireInfoDao.selectList(questionnaireInfo);
	}

	@Override
	public Page<QuestionnaireInfo> queryListPage(Page<QuestionnaireInfo> page) {
	    page.setResultList(questionnaireInfoDao.queryListPage(page));
		return page;
	}
	
	@Override
	public QuestionnaireInfo queryById(java.lang.Integer id) {
		return questionnaireInfoDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(QuestionnaireInfo questionnaireInfo) {
		questionnaireInfoDao.insert(questionnaireInfo);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		questionnaireInfoDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(QuestionnaireInfo questionnaireInfo) {
		questionnaireInfoDao.update(questionnaireInfo);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<QuestionnaireInfo> questionnaireInfoList) {
		questionnaireInfoDao.batchUpdate(questionnaireInfoList);
	}

@Override
public String selectQuestionNaireInfoList(QuestionnaireInfo questionnaireInfo) {
	 String mess=null;
	List<QuestionnaireInfo> list= questionnaireInfoDao.selectList(questionnaireInfo);
	List <Map<String,Object>> questionnaireList = new ArrayList <Map<String,Object>>();
     if(list!=null&&list.size()>0){
    		for (QuestionnaireInfo quest : list) {
    			 Map<String,Object> questMap = new HashMap<String,Object>();
    			 questMap.put("id",null!=quest.getId()?quest.getId():"");
    			 questMap.put("surveyType",null!=quest.getSurveyType()?quest.getSurveyType():"");
    			 questMap.put("flag",null!=quest.getFlag()?quest.getFlag():"");
    			 questMap.put("content",null!=quest.getContent()?quest.getContent():"");
    			 questionnaireList.add(questMap);
    		}
    		mess = ShangAnMessageType.toShangAnJson("0", "问卷列表", "questionnaireInfo", questionnaireList); 
     }else {
		
    		mess = ShangAnMessageType.toShangAnJson("1", "无数据", "questionnaireInfo", "[]");
	}

	return mess;
}
}