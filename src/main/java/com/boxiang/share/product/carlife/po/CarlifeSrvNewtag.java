package com.boxiang.share.product.carlife.po;

import java.io.Serializable;

public class CarlifeSrvNewtag implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private java.lang.Integer id;

	/** 客户ID */
	private java.lang.String customerId;

	/** 服务ID */
	private java.lang.Integer srvId;

	/** 标签已点击值为1 */
	private java.lang.String flag;

	/** 是否可用 */
	private java.lang.String isUsed;

	/** 创建人 */
	private java.lang.String createor;

	/** 修改人 */
	private java.lang.String modified;
	
	/** 创建日期 */
	private java.util.Date createDate;
	
	/** 修改日期 */
	private java.util.Date modifyDate;
	/** 服务标签*/
	
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
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

	public void setSrvId(java.lang.Integer srvId) {
		this.srvId = srvId;
	}
	
	public java.lang.Integer getSrvId() {
		return this.srvId;
	}

	public void setFlag(java.lang.String flag) {
		this.flag = flag;
	}
	
	public java.lang.String getFlag() {
		return this.flag;
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