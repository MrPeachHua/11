package com.boxiang.share.payment.po;

import java.io.Serializable;

public class PaymentInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private java.lang.Integer id;

	/** 订单主表ID(uuid) */
	private java.lang.String orderId;

	/** 支付类型 00:支付宝，01:微信，02:银联 */
	private java.lang.String payType;

	/** 1支付请求，2支付回调 */
	private java.lang.String useType;

	/** 支付请求信息或回调信息 */
	private java.lang.String useInfo;

	/** 创建人 */
	private java.lang.String createor;
	
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

	public void setOrderId(java.lang.String orderId) {
		this.orderId = orderId;
	}
	
	public java.lang.String getOrderId() {
		return this.orderId;
	}

	public void setPayType(java.lang.String payType) {
		this.payType = payType;
	}
	
	public java.lang.String getPayType() {
		return this.payType;
	}

	public void setUseType(java.lang.String useType) {
		this.useType = useType;
	}
	
	public java.lang.String getUseType() {
		return this.useType;
	}

	public void setUseInfo(java.lang.String useInfo) {
		this.useInfo = useInfo;
	}
	
	public java.lang.String getUseInfo() {
		return this.useInfo;
	}

	public void setCreateor(java.lang.String createor) {
		this.createor = createor;
	}
	
	public java.lang.String getCreateor() {
		return this.createor;
	}

}