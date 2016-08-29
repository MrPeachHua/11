package com.boxiang.share.product.order.po;

import java.io.Serializable;

public class OrderMonthly implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private java.lang.Integer id;

	/** 车场ID */
	private java.lang.String parkingId;

	/** 订单主表ID(uuid) */
	private java.lang.String orderId;

	/** 车牌号 */
	private java.lang.String carNumber;

	/** 单价，到分，没有小数点 */
	private java.lang.Integer price;

	/** 开始时间 */
	private java.util.Date beginDate;

	/** 结束时间 */
	private java.util.Date endDate;

	/** 缴费月数 */
	private java.lang.Integer monthNum;

	/** 客户名称，前端输入 */
	private java.lang.String customer;

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
	
	/** 订单类型 */
	private String orderType;
	
	/** 订单状态 */
	private String orderStatus;
	
	/** 停车场名称*/
	private String parkingName;
	
	/** 实付金额*/
    private String amountPaid;
    
    /** 应付金额*/
    private String amountPayable;
    
    /** 优惠金额*/
    private String amountDiscount;
	
	
	public String getAmountPayable() {
		return amountPayable;
	}

	public void setAmountPayable(String amountPayable) {
		this.amountPayable = amountPayable;
	}

	public String getAmountDiscount() {
		return amountDiscount;
	}

	public void setAmountDiscount(String amountDiscount) {
		this.amountDiscount = amountDiscount;
	}

	public String getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}


	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

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

	public void setParkingId(java.lang.String parkingId) {
		this.parkingId = parkingId;
	}
	
	public java.lang.String getParkingId() {
		return this.parkingId;
	}

	public void setOrderId(java.lang.String orderId) {
		this.orderId = orderId;
	}
	
	public java.lang.String getOrderId() {
		return this.orderId;
	}

	public void setCarNumber(java.lang.String carNumber) {
		this.carNumber = carNumber;
	}
	
	public java.lang.String getCarNumber() {
		return this.carNumber;
	}

	public void setPrice(java.lang.Integer price) {
		this.price = price;
	}
	
	public java.lang.Integer getPrice() {
		return this.price;
	}

	public void setBeginDate(java.util.Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public java.util.Date getBeginDate() {
		return this.beginDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	
	public java.util.Date getEndDate() {
		return this.endDate;
	}

	public void setMonthNum(java.lang.Integer monthNum) {
		this.monthNum = monthNum;
	}
	
	public java.lang.Integer getMonthNum() {
		return this.monthNum;
	}

	public void setCustomer(java.lang.String customer) {
		this.customer = customer;
	}
	
	public java.lang.String getCustomer() {
		return this.customer;
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