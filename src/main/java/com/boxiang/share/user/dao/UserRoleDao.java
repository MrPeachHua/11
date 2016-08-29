package com.boxiang.share.user.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.user.po.UserRole;

public interface UserRoleDao extends IMybatisBaseDao<UserRole> {
	void deleteFalse(java.lang.Integer id);
    
    void batchUpdate(List<UserRole> userRoleList);
    
    UserRole queryMaxPower();
}