package com.boxiang.share.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.user.dao.UserRoleDao;
import com.boxiang.share.user.po.UserRole;
import com.boxiang.share.user.service.UserRoleService;

/**
 * @author junior.pan
 * @date 2016-1-2
 */
@DataSource(DataSourceEnum.MASTER)
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
	@Resource(name="userRoleDao")
	private UserRoleDao userRoleDao;
	@Override
	public List<UserRole> selectList(UserRole userRole) {
		return userRoleDao.selectList(userRole);
	}

	@Override
	public Page<UserRole> queryListPage(Page<UserRole> page) {
	    page.setResultList(userRoleDao.queryListPage(page));
		return page;
	}
	
	@Override
	public UserRole queryById(java.lang.Integer id) {
		return userRoleDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(UserRole userRole) {
		userRole.setIsUsed(Constants.TRUE);
		UserRole role = userRoleDao.queryMaxPower();
		userRole.setRolePower(role.getRolePower());
		userRoleDao.insert(userRole);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		userRoleDao.delete(id);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void deleteFalse(java.lang.Integer id) {
		userRoleDao.deleteFalse(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(UserRole userRole) {
		userRoleDao.update(userRole);
	}

    @Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<UserRole> userRoleList) {
		userRoleDao.batchUpdate(userRoleList);
	}
}