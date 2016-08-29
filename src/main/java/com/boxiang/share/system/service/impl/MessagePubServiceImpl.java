package com.boxiang.share.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.dao.MessagePubDao;
import com.boxiang.share.system.po.MessagePub;
import com.boxiang.share.system.service.MessagePubService;

@DataSource(DataSourceEnum.MASTER)
@Service("messagePubService")
public class MessagePubServiceImpl implements MessagePubService {
	@Resource(name="messagePubDao")
	private MessagePubDao messagePubDao;
	
	@Override
	public List<MessagePub> selectList(MessagePub messagePub) {
		return messagePubDao.selectList(messagePub);
	}

	@Override
	public Page<MessagePub> queryListPage(Page<MessagePub> page) {
	    page.setResultList(messagePubDao.queryListPage(page));
		return page;
	}
	
	@Override
	public MessagePub queryById(java.lang.Integer id) {
		return messagePubDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(MessagePub messagePub) {
		messagePubDao.insert(messagePub);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		messagePubDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(MessagePub messagePub) {
		messagePubDao.update(messagePub);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<MessagePub> messagePubList) {
		messagePubDao.batchUpdate(messagePubList);
	}
}