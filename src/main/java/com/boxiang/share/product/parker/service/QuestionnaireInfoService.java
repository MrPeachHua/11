package com.boxiang.share.product.parker.service;

import com.boxiang.share.product.parker.po.QuestionnaireInfo;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface QuestionnaireInfoService {

	List<QuestionnaireInfo> selectList(QuestionnaireInfo questionnaireInfo);
	
	Page<QuestionnaireInfo> queryListPage(Page<QuestionnaireInfo> page);
	
	QuestionnaireInfo queryById(java.lang.Integer id);
	
	void add(QuestionnaireInfo questionnaireInfo);

	void delete(java.lang.Integer id);
	
	void update(QuestionnaireInfo questionnaireInfo);
	
	void batchUpdate(List<QuestionnaireInfo> questionnaireInfoList);
	String selectQuestionNaireInfoList(QuestionnaireInfo questionnaireInfo);
}