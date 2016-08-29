package com.boxiang.share.user.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.user.po.DepartmentInfo;

public interface DepartmentInfoDao extends IMybatisBaseDao<DepartmentInfo> {
    
	void deleteFalse(java.lang.Integer id);
	
    void batchUpdate(List<DepartmentInfo> departmentInfoList);
}