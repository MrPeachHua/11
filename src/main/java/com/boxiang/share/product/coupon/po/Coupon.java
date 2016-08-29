package com.boxiang.share.product.coupon.po;

import java.io.Serializable;

import com.boxiang.share.product.customer.po.Customer;

public class Coupon implements Serializable {
	
	private String couponId;//优惠券唯一标识
	private String vouchersName;//通用券名称
	private String couponKind;//通用券类型
	private String channel;//发放渠道
	private Integer parAmount;//面值(元)
	private Integer parDiscount;//面值(折)
	private String receiveBegin;//领取开始时间
	private String receiveEnd;//领取结束时间
	private String effectiveBegin;//有效开始时间
	private String effectiveEnd;//有效结束时间
	/** 使用时间 */
	private java.lang.String useTime;
	
	private String creator;
	private String createTime;
	private String effectivetime;//有效期
	private String discount;//实际抵扣金额
	private String couponType;
	private String customerMobile;//用户手机号
	
	
	
	/** 最低消费 */
	private Integer minconsumption;
	/** 最高折扣 */
	private Integer maxdiscount;
	/** 互斥规则 */
	private java.lang.String exclusionRule;
	/** 优惠券类型（是否可用） */
	private java.lang.String couponStatus;

	/** 使用者ID */
	private java.lang.String customerId;

	
	/** 支持使用的停车场 */
	private java.lang.String couponParking;

	/** 使用该优惠券的订单号 */
	private java.lang.String couponOrder;
	private java.lang.String customerName;
	private Customer customer;
	private java.lang.String receiveTime;
	private String vouchersType;
	private String startTime;
	private String stopTime;
	private String info;//备注说明
	private String couponKind2;//通用券类型2
	private String orderType;
	private  String parkingId;

	public String getParkingId() {
		return parkingId;
	}

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}

	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getCouponKind2() {
		return couponKind2;
	}
	public void setCouponKind2(String couponKind2) {
		this.couponKind2 = couponKind2;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	private static final long serialVersionUID = 1L;
	public java.lang.String getCouponId() {
		return couponId;
	}
	public void setCouponId(java.lang.String couponId) {
		this.couponId = couponId;
	}
	public java.lang.String getVouchersName() {
		return vouchersName;
	}
	public void setVouchersName(java.lang.String vouchersName) {
		this.vouchersName = vouchersName;
	}
	public java.lang.String getVouchersType() {
		return vouchersType;
	}
	public void setVouchersType(java.lang.String vouchersType) {
		this.vouchersType = vouchersType;
	}
	public java.lang.String getCouponType() {
		return couponType;
	}
	public void setCouponType(java.lang.String couponType) {
		this.couponType = couponType;
	}
	public java.lang.String getChannel() {
		return channel;
	}
	public void setChannel(java.lang.String channel) {
		this.channel = channel;
	}
	public Integer getParAmount() {
		return parAmount;
	}
	public void setParAmount(Integer parAmount) {
		this.parAmount = parAmount;
	}
	public Integer getParDiscount() {
		return parDiscount;
	}
	public void setParDiscount(Integer parDiscount) {
		this.parDiscount = parDiscount;
	}
	public Integer getMinconsumption() {
		return minconsumption;
	}
	public void setMinconsumption(Integer minconsumption) {
		this.minconsumption = minconsumption;
	}
	public Integer getMaxdiscount() {
		return maxdiscount;
	}
	public void setMaxdiscount(Integer maxdiscount) {
		this.maxdiscount = maxdiscount;
	}
	public java.lang.String getReceiveBegin() {
		return receiveBegin;
	}
	public void setReceiveBegin(java.lang.String receiveBegin) {
		this.receiveBegin = receiveBegin;
	}
	public java.lang.String getReceiveEnd() {
		return receiveEnd;
	}
	public void setReceiveEnd(java.lang.String receiveEnd) {
		this.receiveEnd = receiveEnd;
	}
	public java.lang.String getEffectivetime() {
		return effectivetime;
	}
	public void setEffectivetime(java.lang.String effectivetime) {
		this.effectivetime = effectivetime;
	}
	public java.lang.String getEffectiveBegin() {
		return effectiveBegin;
	}
	public void setEffectiveBegin(java.lang.String effectiveBegin) {
		this.effectiveBegin = effectiveBegin;
	}
	public java.lang.String getEffectiveEnd() {
		return effectiveEnd;
	}
	public void setEffectiveEnd(java.lang.String effectiveEnd) {
		this.effectiveEnd = effectiveEnd;
	}
	public java.lang.String getExclusionRule() {
		return exclusionRule;
	}
	public void setExclusionRule(java.lang.String exclusionRule) {
		this.exclusionRule = exclusionRule;
	}
	public java.lang.String getInfo() {
		return info;
	}
	public void setInfo(java.lang.String info) {
		this.info = info;
	}
	public java.lang.String getCouponStatus() {
		return couponStatus;
	}
	public void setCouponStatus(java.lang.String couponStatus) {
		this.couponStatus = couponStatus;
	}
	public java.lang.String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(java.lang.String customerId) {
		this.customerId = customerId;
	}
	public java.lang.String getUseTime() {
		return useTime;
	}
	public void setUseTime(java.lang.String useTime) {
		this.useTime = useTime;
	}
	public java.lang.String getDiscount() {
		return discount;
	}
	public void setDiscount(java.lang.String discount) {
		this.discount = discount;
	}
	public java.lang.String getCouponParking() {
		return couponParking;
	}
	public void setCouponParking(java.lang.String couponParking) {
		this.couponParking = couponParking;
	}
	public java.lang.String getCouponOrder() {
		return couponOrder;
	}
	public void setCouponOrder(java.lang.String couponOrder) {
		this.couponOrder = couponOrder;
	}
	public java.lang.String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(java.lang.String customerName) {
		this.customerName = customerName;
	}
	public java.lang.String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(java.lang.String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public java.lang.String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(java.lang.String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getCouponKind() {
		return couponKind;
	}
	public void setCouponKind(String couponKind) {
		this.couponKind = couponKind;
	}
	
}