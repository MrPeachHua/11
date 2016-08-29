package com.boxiang.share.customer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.customer.dao.MemberGroupDao;
import com.boxiang.share.customer.po.MemberGroup;
import com.boxiang.share.customer.service.MemberGroupService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("memberGroup")
public class MemberGroupServiceImpl implements MemberGroupService {
	@Resource(name="memberGroupDao")
	private MemberGroupDao memberGroupDao;
	
	@Override
	public List<MemberGroup> selectList(MemberGroup memberGroup) {
		return memberGroupDao.selectList(memberGroup);
	}

	@Override
	public Page<MemberGroup> queryListPage(Page<MemberGroup> page) {
	    page.setResultList(memberGroupDao.queryListPage(page));
		return page;
	}
	
	@Override
	public MemberGroup queryById(Integer id) {
		return memberGroupDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(MemberGroup memberGroup) {
		memberGroupDao.insert(memberGroup);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		memberGroupDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(MemberGroup memberGroup) {
		memberGroupDao.update(memberGroup);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<MemberGroup> memberGroupList) {
		memberGroupDao.batchUpdate(memberGroupList);
	}
}