package com.boxiang.share.product.customer.service.imp;

import java.util.List;

import javax.annotation.Resource;

import com.boxiang.share.product.customer.dao.EventQuestionnaireDao;
import com.boxiang.share.product.customer.po.EventQuestionnaire;
import com.boxiang.share.product.customer.service.EventQuestionnaireService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("eventQuestionnaireService")
public class EventQuestionnaireServiceImpl implements EventQuestionnaireService {
	@Resource(name="eventQuestionnaireDao")
	private EventQuestionnaireDao eventQuestionnaireDao;
	
	@Override
	public List<EventQuestionnaire> selectList(EventQuestionnaire eventQuestionnaire) {
		return eventQuestionnaireDao.selectList(eventQuestionnaire);
	}

	@Override
	public Page<EventQuestionnaire> queryListPage(Page<EventQuestionnaire> page) {
	    page.setResultList(eventQuestionnaireDao.queryListPage(page));
		return page;
	}
	
	@Override
	public EventQuestionnaire queryById(Integer id) {
		return eventQuestionnaireDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(EventQuestionnaire eventQuestionnaire) {
		eventQuestionnaireDao.insert(eventQuestionnaire);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		eventQuestionnaireDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(EventQuestionnaire eventQuestionnaire) {
		eventQuestionnaireDao.update(eventQuestionnaire);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<EventQuestionnaire> eventQuestionnaireList) {
		eventQuestionnaireDao.batchUpdate(eventQuestionnaireList);
	}
}