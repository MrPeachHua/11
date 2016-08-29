package com.boxiang.share.customer.po;

import java.io.Serializable;

public class MemberGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/** 分组，数据来源字典member_group */
	private String groupName;

	/** 分组，数据来源字典member_group */
	private String groupCode;

	/** 成长值 */
	private Integer growthValue;

	/** 是否可用 */
	private String isValid;

	/** 创建人 */
	private String createor;

	/** 创建日期 */
	private java.util.Date createDate;

	/** 修改人 */
	private String modified;

	/** 修改日期 */
	private java.util.Date modifyDate;

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGrowthValue(Integer growthValue) {
		this.growthValue = growthValue;
	}
	
	public Integer getGrowthValue() {
		return this.growthValue;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	public String getIsValid() {
		return this.isValid;
	}

	public void setCreateor(String createor) {
		this.createor = createor;
	}
	
	public String getCreateor() {
		return this.createor;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}
	
	public String getModified() {
		return this.modified;
	}

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}

}