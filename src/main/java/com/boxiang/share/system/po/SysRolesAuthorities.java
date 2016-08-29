package com.boxiang.share.system.po;

import java.io.Serializable;

public class SysRolesAuthorities implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/**  */
	private String roleId;

	/**  */
	private String authorityId;

	/**  */
	private Integer enabled;

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleId() {
		return this.roleId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}
	
	public String getAuthorityId() {
		return this.authorityId;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	public Integer getEnabled() {
		return this.enabled;
	}

}