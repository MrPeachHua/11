package com.boxiang.share.bluecard.po;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 停车场信息
 * 
 * @author tanghaibo
 *
 */
public class ParklotFeeRe {
	/**
	 * 交易订单ID
	 */
	private String blueCardOrderId;

	/**
	 * 蓝卡停车场编号
	 */
	private String blueCardParkId;

	/**
	 * 车牌号
	 */
	private String carNumber;

	/**
	 * 用户类型
	 */
	private String carType;

	/**
	 * 所停蓝卡小区名称
	 */
	private String blueCardParkName;

	/**
	 * 停车场编号
	 */
	private String parkId;

	/**
	 * 所停小区名称
	 */
	private String parkName;

	/**
	 * 进场时间
	 */
	private String inTime;

	/**
	 * 算费时间
	 */
	private String getTimes;

	/**
	 * 应缴费用单位分
	 */
	private String charge;

	/**
	 * 线上已缴费用总计单位分
	 */
	private String payAmount = "0";

	/**
	 * 线上已优惠费用总计单位分
	 */
	private String discountAmount = "0";

	/**
	 * 线上应缴费用总计单位分
	 */
	private String amountPayable = "0";

	/**
	 * 车辆照片
	 */
	private String carPhoto;

	/**
	 * 状态接收成功：success ；接收失败：fail
	 */
	private String status;

	/**
	 * 失败原因状态为：success 的时候，失败原因为null
	 */
	private String errorCode;

	public String getBlueCardOrderId() {
		return blueCardOrderId;
	}

	public void setBlueCardOrderId(String blueCardOrderId) {
		this.blueCardOrderId = blueCardOrderId;
	}

	public String getBlueCardParkName() {
		return blueCardParkName;
	}

	public void setBlueCardParkName(String blueCardParkName) {
		this.blueCardParkName = blueCardParkName;
	}

	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getGetTimes() {
		return getTimes;
	}

	public void setGetTimes(String getTimes) {
		this.getTimes = getTimes;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getAmountPayable() {
		return amountPayable;
	}

	public void setAmountPayable(String amountPayable) {
		this.amountPayable = amountPayable;
	}

	public String getBlueCardParkId() {
		return blueCardParkId;
	}

	public void setBlueCardParkId(String blueCardParkId) {
		this.blueCardParkId = blueCardParkId;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarPhoto() {
		return carPhoto;
	}

	public void setCarPhoto(String carPhoto) {
		this.carPhoto = carPhoto;
	}

	@JsonIgnore
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonIgnore
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public ParklotFeeRe(String blueCardOrderId, String blueCardParkId, String carType, String parkName, String inTime,
			String getTimes, String charge, String carPhoto) {
		this.blueCardOrderId = blueCardOrderId;
		this.blueCardParkId = blueCardParkId;
		this.carType = carType;
		this.blueCardParkName = parkName;
		this.inTime = inTime;
		this.getTimes = getTimes;
		this.charge = charge;
		this.carType = carType;
		this.carPhoto = carPhoto;
	}

	public ParklotFeeRe() {
	}

	@Override
	public String toString() {
		return "ParklotFeeRe : [blueCardOrderId=" + blueCardOrderId + ",blueCardParkId=" + blueCardParkId
				+ ",carNumber=" + carNumber + ",carType=" + carType + ",blueCardParkName=" + blueCardParkName
				+ ",parkId=" + parkId + ",parkName=" + parkName + ",inTime=" + inTime + ",getTimes=" + getTimes
				+ ",charge=" + charge + ",payAmount=" + payAmount + ",discountAmount=" + discountAmount
				+ ",amountPayable=" + amountPayable + ",carPhoto=" + carPhoto + "]";
	}
}
