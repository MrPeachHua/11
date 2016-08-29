package com.boxiang.share.product.carlife.po;

import java.io.Serializable;

public class CarlifeRefuelCard implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private java.lang.Integer id;

	/** 客户ID */
	private java.lang.String customerId;

	/** 充值卡号，中石化：以100011开头的卡号、中石油：以9开头的卡号 */
	private java.lang.String cardNo;

	/** 持卡人名称 */
	private java.lang.String cardName;

	/** 加油卡手机号 */
	private java.lang.String cardMobile;

	/** 加油卡类型（1:中石化、2:中石油；默认为1) */
	private java.lang.String cardType;

	/** 是否可用 */
	private java.lang.String isUsed;

	/** 创建人 */
	private java.lang.String createor;
	private java.util.Date createDate;
	/** 修改人 */
	private java.lang.String modified;

	/** 修改日期 */
	private java.util.Date modifyDate;

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

	public void setCardNo(java.lang.String cardNo) {
		this.cardNo = cardNo;
	}
	
	public java.lang.String getCardNo() {
		return this.cardNo;
	}

	public void setCardName(java.lang.String cardName) {
		this.cardName = cardName;
	}
	
	public java.lang.String getCardName() {
		return this.cardName;
	}

	public void setCardMobile(java.lang.String cardMobile) {
		this.cardMobile = cardMobile;
	}
	
	public java.lang.String getCardMobile() {
		return this.cardMobile;
	}

	public void setCardType(java.lang.String cardType) {
		this.cardType = cardType;
	}
	
	public java.lang.String getCardType() {
		return this.cardType;
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

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

}