package com.boxiang.share.system.service.impl;

import java.util.List;

import javax.annotation.Resource;
import com.boxiang.share.system.service.SendMessageService;
import com.boxiang.share.system.po.SendMessage;
import com.boxiang.share.system.dao.SendMessageDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("sendMessage")
public class SendMessageServiceImpl implements SendMessageService {
	@Resource(name="sendMessageDao")
	private SendMessageDao sendMessageDao;
	
	@Override
	public List<SendMessage> selectList(SendMessage sendMessage) {
		return sendMessageDao.selectList(sendMessage);
	}

	@Override
	public Page<SendMessage> queryListPage(Page<SendMessage> page) {
	    page.setResultList(sendMessageDao.queryListPage(page));
		return page;
	}
	@Override
	public Page<SendMessage> selectListByParking(Page<SendMessage> sendMessagePage)
	{
		sendMessagePage.setResultList(sendMessageDao.selectListByParking(sendMessagePage));
		return sendMessagePage;
	}
	@Override
	public SendMessage queryById(int id) {
		return sendMessageDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(SendMessage sendMessage) {
		sendMessageDao.insert(sendMessage);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(int id) {
		sendMessageDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(SendMessage sendMessage) {
		sendMessageDao.update(sendMessage);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<SendMessage> sendMessageList) {
		sendMessageDao.batchUpdate(sendMessageList);
	}
}