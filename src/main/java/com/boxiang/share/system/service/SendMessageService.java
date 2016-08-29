package com.boxiang.share.system.service;


import java.util.List;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.po.SendMessage;
public interface SendMessageService {

	List<SendMessage> selectList(SendMessage sendMessage);
	
	Page<SendMessage> queryListPage(Page<SendMessage> page);
	
	SendMessage queryById(int id);
	
	void add(SendMessage tSendMessage);

	void delete(int id);
	
	void update(SendMessage tSendMessage);
	
	void batchUpdate(List<SendMessage> tSendMessageList);
	public Page<SendMessage> selectListByParking(Page<SendMessage> page);
}