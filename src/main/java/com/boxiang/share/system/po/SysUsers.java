package com.boxiang.share.system.po;

import java.io.Serializable;
import java.util.List;

public class SysUsers implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private java.lang.String userId;

	/**  */
	private java.lang.String userAccount;

	/**  */
	private java.lang.String userName;

	/** 该密码是经加盐值加密的，格式为password{username}。 比如用户的密码为user，用户名为user，那么通过MD5进行加密的串为： user{user} */
	private java.lang.String userPassword;

	/**  */
	private java.lang.String userDesc;

	/**  */
	private java.lang.Integer enabled;

	/** 是否是超级用户 */
	private java.lang.Integer issys;

	/** 所在单位 */
	private java.lang.String userDept;

	/** 经理或主任 */
	private java.lang.String userDuty;

	/** 该用户所负责的各子系统，可多个，中间用逗号分隔。 */
	private java.lang.String subSystem;
	private java.lang.String module;
	
	private List<SysRoles> sysRolesList;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}

	public void setUserAccount(java.lang.String userAccount) {
		this.userAccount = userAccount;
	}
	
	public java.lang.String getUserAccount() {
		return this.userAccount;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	
	public java.lang.String getUserName() {
		return this.userName;
	}

	public void setUserPassword(java.lang.String userPassword) {
		this.userPassword = userPassword;
	}
	
	public java.lang.String getUserPassword() {
		return this.userPassword;
	}

	public void setUserDesc(java.lang.String userDesc) {
		this.userDesc = userDesc;
	}
	
	public java.lang.String getUserDesc() {
		return this.userDesc;
	}

	public void setEnabled(java.lang.Integer enabled) {
		this.enabled = enabled;
	}
	
	public java.lang.Integer getEnabled() {
		return this.enabled;
	}

	public void setIssys(java.lang.Integer issys) {
		this.issys = issys;
	}
	
	public java.lang.Integer getIssys() {
		return this.issys;
	}

	public void setUserDept(java.lang.String userDept) {
		this.userDept = userDept;
	}
	
	public java.lang.String getUserDept() {
		return this.userDept;
	}

	public void setUserDuty(java.lang.String userDuty) {
		this.userDuty = userDuty;
	}
	
	public java.lang.String getUserDuty() {
		return this.userDuty;
	}

	public void setSubSystem(java.lang.String subSystem) {
		this.subSystem = subSystem;
	}
	
	public java.lang.String getSubSystem() {
		return this.subSystem;
	}

	public List<SysRoles> getSysRolesList() {
		return sysRolesList;
	}

	public void setSysRolesList(List<SysRoles> sysRolesList) {
		this.sysRolesList = sysRolesList;
	}

}