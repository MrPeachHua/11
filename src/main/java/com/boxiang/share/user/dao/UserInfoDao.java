package com.boxiang.share.user.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.user.po.UserInfo;

public interface UserInfoDao extends IMybatisBaseDao<UserInfo> {
	void deleteFalse(java.lang.Integer id);

	void deleteFalse(int id);
	
	void updatePw(UserInfo userInfo);
	List<UserInfo> selectList2(UserInfo userInfo);
	void batchUpdate(List<UserInfo> userInfoList);
	List<UserInfo> selectSysUser(UserInfo userInfo);
}
