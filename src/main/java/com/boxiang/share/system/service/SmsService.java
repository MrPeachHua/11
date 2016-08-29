package com.boxiang.share.system.service;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.po.Sms;

public interface SmsService {

	List<Sms> selectList(Sms sms);
	
	Page<Sms> queryListPage(Page<Sms> page);
	
	Sms queryById(java.lang.String id);
	
	void add(Sms sms);

	void delete(java.lang.String id);
	
	void update(Sms sms);
	
	void batchUpdate(List<Sms> smsList);
	//普通&月租短信推送
	String pushNomalMessage(Map<String,Object> param);
}