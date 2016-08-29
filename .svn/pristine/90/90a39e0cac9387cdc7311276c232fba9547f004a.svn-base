package com.boxiang.share.product.villageowner.service;

import com.boxiang.share.product.villageowner.po.Invitation;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;

public interface InvitationService {

	List<Invitation> selectList(Invitation invitation);
	
	Page<Invitation> queryListPage(Page<Invitation> page);
	
	Invitation queryById(Integer id);
	
	void add(Invitation invitation);

	void delete(Integer id);
	
	void update(Invitation invitation);
	
	void batchUpdate(List<Invitation> invitationList);

	int queryTodayCount(String customerId);

	Map<String,Object> paramsFilter(Invitation invitation);

	Invitation queryUnExpire(int id);

	List<Object> queryHistory(Object page);
}