package com.boxiang.share.product.order.po;

import java.io.Serializable;

public class Monthlyparkingfeeorderinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private java.lang.String id;

	/** 小区ID */
	private java.lang.String villageId;

	/** 车牌号 */
	private java.lang.String carNumber;

	/** 订单流水号 */
	private java.lang.String orderSerialNumber;

	/** 收款平台类型 */
	private java.lang.String receivablesPlatformType;

	/** 月租单价 */
	private Long monthlyRentalPrice;

	/** 应付金额 */
	private Long amountPayable;

	/** 优惠金额 */
	private Long discountAmount;

	/** 月租车位费订单金额 */
	private Long amount;

	/** 有效期开始时间 */
	private java.util.Date validityStartTime;

	/** 有效期结束时间 */
	private java.util.Date validityEndTime;

	/** 订单状态 */
	private java.lang.String orderStatus;

	/** 发票开具信息 0：未开具,1：已开具 */
	private java.lang.String invoiceStatus;

	/** 创建时间 */
	private java.util.Date createTime;

	/** 创建者 */
	private java.lang.String createUser;

	/** 更新时间 */
	private java.util.Date updateTime;

	/** 更新者 */
	private java.lang.String updateUser;

	/**  */
	private java.lang.String item01;

	/**  */
	private java.lang.String item02;

	/**  */
	private java.lang.String item03;

	/**  */
	private java.lang.String item04;

	/**  */
	private java.lang.String item05;

	public void setId(java.lang.String id) {
		this.id = id;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	public void setVillageId(java.lang.String villageId) {
		this.villageId = villageId;
	}
	
	public java.lang.String getVillageId() {
		return this.villageId;
	}

	public void setCarNumber(java.lang.String carNumber) {
		this.carNumber = carNumber;
	}
	
	public java.lang.String getCarNumber() {
		return this.carNumber;
	}

	public void setOrderSerialNumber(java.lang.String orderSerialNumber) {
		this.orderSerialNumber = orderSerialNumber;
	}
	
	public java.lang.String getOrderSerialNumber() {
		return this.orderSerialNumber;
	}

	public void setReceivablesPlatformType(java.lang.String receivablesPlatformType) {
		this.receivablesPlatformType = receivablesPlatformType;
	}
	
	public java.lang.String getReceivablesPlatformType() {
		return this.receivablesPlatformType;
	}

	public void setMonthlyRentalPrice(Long monthlyRentalPrice) {
		this.monthlyRentalPrice = monthlyRentalPrice;
	}
	
	public Long getMonthlyRentalPrice() {
		return this.monthlyRentalPrice;
	}

	public void setAmountPayable(Long amountPayable) {
		this.amountPayable = amountPayable;
	}
	
	public Long getAmountPayable() {
		return this.amountPayable;
	}

	public void setDiscountAmount(Long discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	public Long getDiscountAmount() {
		return this.discountAmount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public Long getAmount() {
		return this.amount;
	}

	public void setValidityStartTime(java.util.Date validityStartTime) {
		this.validityStartTime = validityStartTime;
	}
	
	public java.util.Date getValidityStartTime() {
		return this.validityStartTime;
	}

	public void setValidityEndTime(java.util.Date validityEndTime) {
		this.validityEndTime = validityEndTime;
	}
	
	public java.util.Date getValidityEndTime() {
		return this.validityEndTime;
	}

	public void setOrderStatus(java.lang.String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public java.lang.String getOrderStatus() {
		return this.orderStatus;
	}

	public void setInvoiceStatus(java.lang.String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	
	public java.lang.String getInvoiceStatus() {
		return this.invoiceStatus;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateUser(java.lang.String createUser) {
		this.createUser = createUser;
	}
	
	public java.lang.String getCreateUser() {
		return this.createUser;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateUser(java.lang.String updateUser) {
		this.updateUser = updateUser;
	}
	
	public java.lang.String getUpdateUser() {
		return this.updateUser;
	}

	public void setItem01(java.lang.String item01) {
		this.item01 = item01;
	}
	
	public java.lang.String getItem01() {
		return this.item01;
	}

	public void setItem02(java.lang.String item02) {
		this.item02 = item02;
	}
	
	public java.lang.String getItem02() {
		return this.item02;
	}

	public void setItem03(java.lang.String item03) {
		this.item03 = item03;
	}
	
	public java.lang.String getItem03() {
		return this.item03;
	}

	public void setItem04(java.lang.String item04) {
		this.item04 = item04;
	}
	
	public java.lang.String getItem04() {
		return this.item04;
	}

	public void setItem05(java.lang.String item05) {
		this.item05 = item05;
	}
	
	public java.lang.String getItem05() {
		return this.item05;
	}

}