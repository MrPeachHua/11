package com.boxiang.share.user.service;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.user.po.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserInfoService {
	/**
	 * 根据条件查询员工信息
	 * @param userInfo
	 * @return
	 */
	List<UserInfo> selectList(UserInfo userInfo);	
	
	/**
	 * @Title: queryListPage
	 * @Description:  根据条件分页查询员工信息
	 * @param page
	 * @return
	 */
	Page<UserInfo> queryListPage(Page<UserInfo> page);
	
	/**
	 * 根据员工id查询员工信息
	 * @param userId
	 * @return
	 */
	UserInfo queryById(int userId);
	
	/**
	 * 添加员工信息
	 * @param userInfo
	 * @return
	 */
	void add(UserInfo userInfo);

	/**
	 * 逻辑删除员工信息
	 * @param userId
	 * @return
	 */
	void delete(int userId);

	void deleteFalse(int userId);
	
	/**
	 * @Title: update
	 * @Description: 修改员工信息
	 * @param userInfo
	 */
	void update(UserInfo userInfo);
	void del(UserInfo userInfo,int userId);
	void batchUpdate(List<UserInfo> userInfoList);
	
	void updatePw(UserInfo userInfo);
	List<UserInfo> selectList2(UserInfo userInfo);
	void relationAdd(HttpServletRequest request, HttpServletResponse response,UserInfo userInfo,String module);
	List<UserInfo> selectSysUser(UserInfo userInfo);
}
