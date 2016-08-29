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
import com.boxiang.share.user.dao.DepartmentInfoDao;
import com.boxiang.share.user.po.DepartmentInfo;
import com.boxiang.share.user.service.DepartmentInfoService;

/**
 * @author junior.pan
 * @date 2016-1-2
 */
@DataSource(DataSourceEnum.MASTER)
@Service("departmentInfoService")
public class DepartmentInfoServiceImpl implements DepartmentInfoService {
	@Resource(name="departmentInfoDao")
	private DepartmentInfoDao departmentInfoDao;
	@Override
	public List<DepartmentInfo> selectList(DepartmentInfo departmentInfo) {
		return departmentInfoDao.selectList(departmentInfo);
	}

	@Override
	public Page<DepartmentInfo> queryListPage(Page<DepartmentInfo> page) {
	    page.setResultList(departmentInfoDao.queryListPage(page));
		return page;
	}
	
	@Override
	public DepartmentInfo queryById(java.lang.Integer id) {
		return departmentInfoDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(DepartmentInfo departmentInfo) {
		departmentInfo.setIsUsed(Constants.TRUE);
		departmentInfoDao.insert(departmentInfo);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		departmentInfoDao.delete(id);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void deleteFalse(java.lang.Integer id) {
		departmentInfoDao.deleteFalse(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(DepartmentInfo departmentInfo) {
		departmentInfoDao.update(departmentInfo);
	}

    @Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<DepartmentInfo> departmentInfoList) {
		departmentInfoDao.batchUpdate(departmentInfoList);
	}
}