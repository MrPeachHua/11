package com.boxiang.share.system.po;

import java.io.Serializable;

public class SysAuthoritiesResources implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/**  */
	private String authorityId;

	/**  */
	private String resourceId;

	/**  */
	private Integer enabled;

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}
	
	public String getAuthorityId() {
		return this.authorityId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
	public String getResourceId() {
		return this.resourceId;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	
	public Integer getEnabled() {
		return this.enabled;
	}

}