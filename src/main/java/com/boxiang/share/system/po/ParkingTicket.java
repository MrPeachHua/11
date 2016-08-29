package com.boxiang.share.system.po;

import java.io.Serializable;

public class ParkingTicket implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private java.lang.Integer id;

	/** 用户ID */
	private java.lang.String customerId;

	/** 用户手机号 */
	private java.lang.String mobile;

	/** 有效期开始时间 */
	private java.util.Date startDate;

	/** 有效期结束时间 */
	private java.util.Date endDate;

	/** 停车码 */
	private java.lang.String code;

	/** 是否可用 */
	private java.lang.String isUsed;

	/** 创建人 */
	private java.lang.String createor;

	/** 修改人 */
	private java.lang.String modified;

	/** 修改日期 */
	private java.util.Date modifyDate;
	
	/** 创建日期 */
	private java.util.Date createDate;
	
	
	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}

	public void setCustomerId(java.lang.String customerId) {
		this.customerId = customerId;
	}
	
	public java.lang.String getCustomerId() {
		return this.customerId;
	}
	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	
	public java.util.Date getStartDate() {
		return this.startDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	
	public java.util.Date getEndDate() {
		return this.endDate;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}
	
	public java.lang.String getCode() {
		return this.code;
	}

	public void setIsUsed(java.lang.String isUsed) {
		this.isUsed = isUsed;
	}
	
	public java.lang.String getIsUsed() {
		return this.isUsed;
	}

	public void setCreateor(java.lang.String createor) {
		this.createor = createor;
	}
	
	public java.lang.String getCreateor() {
		return this.createor;
	}

	public void setModified(java.lang.String modified) {
		this.modified = modified;
	}
	
	public java.lang.String getModified() {
		return this.modified;
	}

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}

}