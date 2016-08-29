package com.boxiang.share.product.order.po;

import java.io.Serializable;
import java.util.Date;

public class OrderRecharge implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/** 订单主表ID(uuid) */
	private String orderId;

	/** 赠送金额，到分，没有小数点 */
	private Integer giftAmount;

	/** 是否可用 */
	private String isUsed;

	/** 创建人 */
	private String createor;

	/** 修改人 */
	private String modified;

	/** 修改日期 */
	private java.util.Date modifyDate;

	/** 创建日期 */
	private java.util.Date createDate;

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderId() {
		return this.orderId;
	}

	public void setGiftAmount(Integer giftAmount) {
		this.giftAmount = giftAmount;
	}
	
	public Integer getGiftAmount() {
		return this.giftAmount;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	
	public String getIsUsed() {
		return this.isUsed;
	}

	public void setCreateor(String createor) {
		this.createor = createor;
	}
	
	public String getCreateor() {
		return this.createor;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}