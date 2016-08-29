package com.boxiang.share.system.po;

import java.io.Serializable;

public class SysResources implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private java.lang.String resourceId;

	/**  */
	private java.lang.String resourceName;

	/**  */
	private java.lang.String resourceDesc;

	/**  */
	private java.lang.String resourceType;

	/**  */
	private java.lang.String resourceString;

	/**  */
	private java.lang.Integer priority;

	/**  */
	private java.lang.Integer enabled;

	/**  */
	private java.lang.Integer issys;

	/** 所属的子系统，比如平台里面包括10个系统，分别为成本、作业、集输等。  */
	private java.lang.String module;
	private java.lang.String authorityId;
	private java.lang.String authorityName;
	private java.lang.String authorityDesc;

	public String getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public void setResourceId(java.lang.String resourceId) {
		this.resourceId = resourceId;
	}
	
	public java.lang.String getResourceId() {
		return this.resourceId;
	}

	public void setResourceName(java.lang.String resourceName) {
		this.resourceName = resourceName;
	}
	
	public java.lang.String getResourceName() {
		return this.resourceName;
	}

	public void setResourceDesc(java.lang.String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}
	
	public java.lang.String getResourceDesc() {
		return this.resourceDesc;
	}

	public void setResourceType(java.lang.String resourceType) {
		this.resourceType = resourceType;
	}
	
	public java.lang.String getResourceType() {
		return this.resourceType;
	}

	public void setResourceString(java.lang.String resourceString) {
		this.resourceString = resourceString;
	}
	
	public java.lang.String getResourceString() {
		return this.resourceString;
	}

	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
	}
	
	public java.lang.Integer getPriority() {
		return this.priority;
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

	public void setModule(java.lang.String module) {
		this.module = module;
	}
	
	public java.lang.String getModule() {
		return this.module;
	}

}