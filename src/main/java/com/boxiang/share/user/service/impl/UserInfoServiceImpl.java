package com.boxiang.share.user.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.UserCache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.po.Sequence;
import com.boxiang.share.system.po.SysUsers;
import com.boxiang.share.system.po.SysUsersRoles;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.system.service.SysUsersRolesService;
import com.boxiang.share.system.service.SysUsersService;
import com.boxiang.share.user.dao.UserInfoDao;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.user.service.UserInfoService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.MD5Util;
import com.boxiang.share.utils.TableNameConstants;

/**
 * @author junior.pan
 * @date 2016-1-2
 */
@DataSource(DataSourceEnum.MASTER)
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
	@Resource(name="userInfoDao")
	private UserInfoDao userInfoDao;
	@Resource
	private SysUsersService sysUsersService;
	@Resource
	private SequenceService sequenceService;
	@Resource
	private SysUsersRolesService sysUsersRolesService;

	@Resource
	private UserCache userCache;
	
	@Override
	public List<UserInfo> selectList(UserInfo userInfo) {
		return userInfoDao.selectList(userInfo);
	}

	@Override
	public Page<UserInfo> queryListPage(Page<UserInfo> page) {
		page.setResultList(userInfoDao.queryListPage(page));
		return page;
	}

	@Override
	public List<UserInfo> selectSysUser(UserInfo userInfo) {
		return userInfoDao.selectSysUser(userInfo);
	}

	@Override
	public UserInfo queryById(int userId) {
		return userInfoDao.queryById(userId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(UserInfo userInfo) {
		userInfo.setUserAddtime(DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT));
		userInfo.setUserChangetime(userInfo.getUserAddtime());
		userInfo.setUserChangeman(userInfo.getUserAddman());
		userInfo.setIsUsed(Constants.TRUE);
		userInfoDao.insert(userInfo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(int userId) {
		userInfoDao.delete(userId);
        userCache.removeUserFromCache(this.queryById(userId).getUserNum());
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void deleteFalse(int userId){
		userInfoDao.deleteFalse(userId);
        userCache.removeUserFromCache(this.queryById(userId).getUserNum());
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(UserInfo userInfo) {
		/*UserInfo user = this.queryById(userInfo.getUserId());
		user.setUserAge(userInfo.getUserAge());
		user.setUserSex(userInfo.getUserSex());
		user.setUserNation(userInfo.getUserNation());
		user.setUserDiploma(userInfo.getUserDiploma());
		user.setIsMarried(userInfo.getIsMarried());
		user.setUserTel(userInfo.getUserTel());
		user.setUserIntest(userInfo.getUserIntest());
		user.setUserBankcard(userInfo.getUserBankcard());
		user.setUserMobile(userInfo.getUserMobile());
		user.setUserIdnum(userInfo.getUserIdnum());
		user.setUserEmail(userInfo.getUserEmail());
		user.setUserAddress(userInfo.getUserAddress());
		user.setUserChangetime(userInfo.getUserChangetime());
		user.setUserChangeman(userInfo.getUserChangeman());
		user.setDepartmentId(userInfo.getDepartmentId());
		user.setRoleId(userInfo.getRoleId());
		user.setParkingId(userInfo.getParkingId());
		userInfoDao.update(user);*/
		userInfoDao.update(userInfo);
        userCache.removeUserFromCache(this.queryById(userInfo.getUserId()).getUserNum());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<UserInfo> userInfoList) {
		userInfoDao.batchUpdate(userInfoList);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void relationAdd(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo,String module) {
		String[] sysRoles=request.getParameterValues("sysRoles");//角色数组
		//新的users表
		Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.SYS_USERS);
		SysUsers sysUsers=new SysUsers();
		sysUsers.setUserId(sequence.getId());
		sysUsers.setUserAccount(userInfo.getUserNum());
		sysUsers.setUserName(userInfo.getUserName());
		sysUsers.setUserPassword(MD5Util.getLoginPwd(userInfo.getUserPw()));
		sysUsers.setUserDesc("");
		sysUsers.setModule(module);
		sysUsers.setEnabled(1);
		sysUsers.setUserDept(userInfo.getDepartmentName());
		sysUsers.setUserDuty("");
		// sysUsers.setSysRolesList();
		sysUsersService.add(sysUsers);
		//旧表
		UserInfo currUser = (UserInfo) request.getSession().getAttribute(Constants.LOGIN_USER);
		userInfo.setUserAddman(currUser.getUserName());
		userInfo.setUserPw(MD5Util.getLoginPwd(userInfo.getUserPw()));
		userInfo.setSysUserId(sysUsers.getUserId());
		this.add(userInfo);

		//关联
		if(sysRoles!=null&&sysRoles.length>0){
			for (int i = 0; i <sysRoles.length ; i++) {
				SysUsersRoles sysUsersRoles=new SysUsersRoles();
				sysUsersRoles.setEnabled(1);
				sysUsersRoles.setRoleId(sysRoles[i]);
				sysUsersRoles.setUserId(sysUsers.getUserId());
				sysUsersRolesService.add(sysUsersRoles);
			}

		}

	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void del(UserInfo userInfo,int userId) {
		SysUsers sysUsers=sysUsersService.queryById(userInfo.getSysUserId());
		sysUsers.setEnabled(0);
		sysUsersService.update(sysUsers);//修改sysuser
		userInfoDao.deleteFalse(userId);
		//sysUsersRolesService.del2(userInfo.getSysUserId());//删除关联

	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void updatePw(UserInfo userInfo){
		userInfoDao.updatePw(userInfo);
		UserInfo info = userInfoDao.queryById(userInfo.getUserId());
		SysUsers sysUsers = sysUsersService.queryById(info.getSysUserId());
		sysUsers.setUserPassword(userInfo.getUserPw());
		sysUsersService.update(sysUsers);
        userCache.removeUserFromCache(this.queryById(userInfo.getUserId()).getUserNum());
	}

	@Override
	public List<UserInfo> selectList2(UserInfo userInfo) {
		return userInfoDao.selectList2(userInfo);
	}
}
