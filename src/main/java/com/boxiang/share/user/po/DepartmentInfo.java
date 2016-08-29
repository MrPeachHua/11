package com.boxiang.share.user.po;

import java.io.Serializable;

public class DepartmentInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 部门编号 */
	private java.lang.Integer departmentId;

	/** 部门名称 */
	private java.lang.String departmentName;

	/**所属系统*/
	private java.lang.String module;

	/** 部门描述 */
	private java.lang.String departmentDesc;

	/** 是否有效 */
	private java.lang.String isUsed;

	public void setDepartmentId(java.lang.Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	public java.lang.Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentName(java.lang.String departmentName) {
		this.departmentName = departmentName;
	}
	
	public java.lang.String getDepartmentName() {
		return this.departmentName;
	}

	public void setModule(java.lang.String module) {this.module = module;}

	public java.lang.String getModule() {return  this.module;}

	public void setDepartmentDesc(java.lang.String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}
	
	public java.lang.String getDepartmentDesc() {
		return this.departmentDesc;
	}

	public void setIsUsed(java.lang.String isUsed) {
		this.isUsed = isUsed;
	}
	
	public java.lang.String getIsUsed() {
		return this.isUsed;
	}

}