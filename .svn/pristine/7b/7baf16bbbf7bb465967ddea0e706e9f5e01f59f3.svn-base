package com.boxiang.share.customer.service;

import com.boxiang.share.customer.po.MemberGroup;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface MemberGroupService {

	List<MemberGroup> selectList(MemberGroup memberGroup);
	
	Page<MemberGroup> queryListPage(Page<MemberGroup> page);
	
	MemberGroup queryById(Integer id);
	
	void add(MemberGroup memberGroup);

	void delete(Integer id);
	
	void update(MemberGroup memberGroup);
	
	void batchUpdate(List<MemberGroup> memberGroupList);
}