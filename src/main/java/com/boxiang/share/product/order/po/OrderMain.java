package com.boxiang.share.product.order.po;

import java.io.Serializable;
import java.util.Date;

public class OrderMain implements Serializable {
//	private static final long serialVersionUID = 1L;

	/** 订单ID(uuid) */
	private java.lang.String orderId;

	/** 订单类型：10共享临停，11临停缴费，12代泊，13月租，14产权，15加油卡,16钱包充值 */
	private java.lang.String orderType;

	/** 订单状态 */
	private java.lang.String orderStatus;

	/** 发票状态 0：未开具,1：已开具，默认0 */
	private java.lang.String invoiceStatus;

	/** 客户名称 */
	private java.lang.String customer_nickname;
	private java.lang.String customer_mobile;
	/** 应付金额，到分，没有小数点 */
	private java.lang.Integer amountPayable;

	/** 优惠金额，到分，没有小数点 */
	private java.lang.Integer amountDiscount;

	/** 实付金额，到分，没有小数点 */
	private java.lang.Integer amountPaid;

	/** 支付类型 00:支付宝，01:微信，02:银联 03：线下  04 其他 */
	private java.lang.String payType;

	/** 支付时间 */
	private java.util.Date payTime;

	/** 支付方交易号 */
	private java.lang.String tradeNo;
	private java.lang.String parkingName;
	private java.lang.String carNumber;
	private Date beginTime;
	private Date endTime;
	private java.lang.String parkingCode;


	/** 是否可用 */
	private java.lang.String isUsed;

	/** 创建人 */
	private java.lang.String createor;

	/** 修改人 */
	private java.lang.String modified;
	/** 修改日期 */
	private java.util.Date createDate;

	public String getVoucherStatus() {
		return voucherStatus;
	}

	public void setVoucherStatus(String voucherStatus) {
		this.voucherStatus = voucherStatus;
	}

	/** 修改日期 */
	private java.util.Date modifyDate;
	/** 客户ID */

	private java.lang.String startTime;
	private java.lang.String stopTime;
	/**
	 * 历史订单
	 */
	private java.lang.String customerId;
	private java.lang.String parkingId;

	private java.lang.String parkingNo;

	private java.lang.String orderTypeName;
	private java.lang.String orderStatusName;

	private Integer giftAmount;
	private Integer monthNum; // 缴费月数
	private java.util.Date reserveDate;//预约日期
	//是否需要开具发票
	private String isNeedInvoice;
	/** 预约凭证状态*/
	private java.lang.String voucherStatus;
	private String carType;

	private java.lang.String appointmentDate;
	private String parkerName;
	private String sumPaid;
	private String sumDiscount;
	private String sumPricePoint;

	private String onlineType;
	/**月租产权有效结束时间*/
	private Date effectEndDate;

	public Date getEffectEndDate() {
		return effectEndDate;
	}

	public void setEffectEndDate(Date effectEndDate) {
		this.effectEndDate = effectEndDate;
	}

	public String getOnlineType() {
		return onlineType;
	}

	public void setOnlineType(String onlineType) {
		this.onlineType = onlineType;
	}

	public String getSumPricePoint() {
		return sumPricePoint;
	}

	public void setSumPricePoint(String sumPricePoint) {
		this.sumPricePoint = sumPricePoint;
	}

	public String getSumDiscount() {
		return sumDiscount;
	}

	public void setSumDiscount(String sumDiscount) {
		this.sumDiscount = sumDiscount;
	}

	public String getSumPaid() {
		return sumPaid;
	}

	public void setSumPaid(String sumPaid) {
		this.sumPaid = sumPaid;
	}





	public String getParkerName() {
		return parkerName;
	}

	public void setParkerName(String parkerName) {
		this.parkerName = parkerName;
	}

	public String getIsNeedInvoice() {
		return isNeedInvoice;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public void setIsNeedInvoice(String isNeedInvoice) {
		this.isNeedInvoice = isNeedInvoice;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public java.util.Date getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(java.util.Date reserveDate) {
		this.reserveDate = reserveDate;
	}

	public Integer getGiftAmount() {
		return giftAmount;
	}

	public void setGiftAmount(Integer giftAmount) {
		this.giftAmount = giftAmount;
	}

	public java.lang.String getParkingCode() {
		return parkingCode;
	}

	public void setParkingCode(java.lang.String parkingCode) {
		this.parkingCode = parkingCode;
	}

	public void setOrderId(java.lang.String orderId) {
		this.orderId = orderId;
	}
	
	public java.lang.String getOrderId() {
		return this.orderId;
	}

	public void setOrderType(java.lang.String orderType) {
		this.orderType = orderType;
	}
	
	public java.lang.String getOrderType() {
		return this.orderType;
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

	public void setCustomerId(java.lang.String customerId) {
		this.customerId = customerId;
	}
	
	public java.lang.String getCustomerId() {
		return this.customerId;
	}

	public void setAmountPayable(java.lang.Integer amountPayable) {
		this.amountPayable = amountPayable;
	}
	
	public java.lang.Integer getAmountPayable() {
		return this.amountPayable;
	}

	public void setAmountDiscount(java.lang.Integer amountDiscount) {
		this.amountDiscount = amountDiscount;
	}
	
	public java.lang.Integer getAmountDiscount() {
		return this.amountDiscount;
	}

	public void setAmountPaid(java.lang.Integer amountPaid) {
		this.amountPaid = amountPaid;
	}
	
	public java.lang.Integer getAmountPaid() {
		return this.amountPaid;
	}

	public void setPayType(java.lang.String payType) {
		this.payType = payType;
	}
	
	public java.lang.String getPayType() {
		return this.payType;
	}

	public void setPayTime(java.util.Date payTime) {
		this.payTime = payTime;
	}
	
	public java.util.Date getPayTime() {
		return this.payTime;
	}

	public void setTradeNo(java.lang.String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
	public java.lang.String getTradeNo() {
		return this.tradeNo;
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


	public java.lang.String getCustomer_nickname() {
		return customer_nickname;
	}

	public void setCustomer_nickname(java.lang.String customer_nickname) {
		this.customer_nickname = customer_nickname;
	}

	 
	public java.lang.String getCustomer_mobile() {
		return customer_mobile;
	}

	public void setCustomer_mobile(java.lang.String customer_mobile) {
		this.customer_mobile = customer_mobile;
	}

	public java.lang.String getStartTime() {
		return startTime;
	}

	public void setStartTime(java.lang.String startTime) {
		this.startTime = startTime;
	}

	public java.lang.String getStopTime() {
		return stopTime;
	}

	public void setStopTime(java.lang.String stopTime) {
		this.stopTime = stopTime;
	}

	public java.lang.String getParkingId() {
		return parkingId;
	}

	public void setParkingId(java.lang.String parkingId) {
		this.parkingId = parkingId;
	}

	public java.lang.String getParkingName() {
		return parkingName;
	}

	public void setParkingName(java.lang.String parkingName) {
		this.parkingName = parkingName;
	}

	public java.lang.String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(java.lang.String carNumber) {
		this.carNumber = carNumber;
	}

	public java.lang.String getParkingNo() {
		return parkingNo;
	}

	public void setParkingNo(java.lang.String parkingNo) {
		this.parkingNo = parkingNo;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public java.lang.String getOrderTypeName() {
		return orderTypeName;
	}

	public void setOrderTypeName(java.lang.String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public java.lang.String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(java.lang.String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public Integer getMonthNum() {
		return monthNum;
	}

	public void setMonthNum(Integer monthNum) {
		this.monthNum = monthNum;
	}
	private String mondayBeginTime;
	private String mondayEndTime;
	private String fridayBeginTime;
	private String fridayEndTime;
	private String saturdayBeginTime;
	private String saturdayEndTime;
	private String sundayBeginTime;
	private String sundayEndTime;
	private String thursdayBeginTime;
	private String thursdayEndTime;
	private String tuesdayBeginTime;
	private String tuesdayEndTime;

	private String region;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getMondayBeginTime() {
		return mondayBeginTime;
	}

	public void setMondayBeginTime(String mondayBeginTime) {
		this.mondayBeginTime = mondayBeginTime;
	}

	public String getMondayEndTime() {
		return mondayEndTime;
	}

	public void setMondayEndTime(String mondayEndTime) {
		this.mondayEndTime = mondayEndTime;
	}

	public String getFridayBeginTime() {
		return fridayBeginTime;
	}

	public void setFridayBeginTime(String fridayBeginTime) {
		this.fridayBeginTime = fridayBeginTime;
	}

	public String getFridayEndTime() {
		return fridayEndTime;
	}

	public void setFridayEndTime(String fridayEndTime) {
		this.fridayEndTime = fridayEndTime;
	}

	public String getSaturdayBeginTime() {
		return saturdayBeginTime;
	}

	public void setSaturdayBeginTime(String saturdayBeginTime) {
		this.saturdayBeginTime = saturdayBeginTime;
	}

	public String getSaturdayEndTime() {
		return saturdayEndTime;
	}

	public void setSaturdayEndTime(String saturdayEndTime) {
		this.saturdayEndTime = saturdayEndTime;
	}

	public String getSundayBeginTime() {
		return sundayBeginTime;
	}

	public void setSundayBeginTime(String sundayBeginTime) {
		this.sundayBeginTime = sundayBeginTime;
	}

	public String getSundayEndTime() {
		return sundayEndTime;
	}

	public void setSundayEndTime(String sundayEndTime) {
		this.sundayEndTime = sundayEndTime;
	}

	public String getThursdayBeginTime() {
		return thursdayBeginTime;
	}

	public void setThursdayBeginTime(String thursdayBeginTime) {
		this.thursdayBeginTime = thursdayBeginTime;
	}

	public String getThursdayEndTime() {
		return thursdayEndTime;
	}

	public void setThursdayEndTime(String thursdayEndTime) {
		this.thursdayEndTime = thursdayEndTime;
	}

	public String getTuesdayBeginTime() {
		return tuesdayBeginTime;
	}

	public void setTuesdayBeginTime(String tuesdayBeginTime) {
		this.tuesdayBeginTime = tuesdayBeginTime;
	}

	public String getTuesdayEndTime() {
		return tuesdayEndTime;
	}

	public void setTuesdayEndTime(String tuesdayEndTime) {
		this.tuesdayEndTime = tuesdayEndTime;
	}

	public String getWednesdayBeginTime() {
		return wednesdayBeginTime;
	}

	public void setWednesdayBeginTime(String wednesdayBeginTime) {
		this.wednesdayBeginTime = wednesdayBeginTime;
	}

	public String getWednesdayEndTime() {
		return wednesdayEndTime;
	}

	public void setWednesdayEndTime(String wednesdayEndTime) {
		this.wednesdayEndTime = wednesdayEndTime;
	}

	private String wednesdayBeginTime;
	private String wednesdayEndTime;
	private String amount;
private String payDate;
	public String getAmount() {
		return amount;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	private String startEndTime;

	public String getStartEndTime() {
		return startEndTime;
	}

	public void setStartEndTime(String startEndTime) {
		this.startEndTime = startEndTime;
	}
}