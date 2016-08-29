package com.boxiang.share.bluecard.po;

import java.util.List;

/**
 * ParklotFeeDatas 取值说明
 * 
 * @author tanghaibo
 *
 */
public class ParklotFeeData {

	/**
	 * 交易订单ID,此次订单唯一标识车辆入场产生订单ID
	 */
	private String orderId;

	/**
	 * 停车场编号蓝卡云平台或API 添加停车厂生成
	 */
	private String parkId;

	/**
	 * 停车场名称
	 */
	private String parkName;

	/**
	 * 入场时间格式‘yyyy-MM-dd HH:mm:ss’
	 */
	private String inTime;

	/**
	 * 应缴费用单位分
	 */
	private String charge;

	/**
	 * 用户类型
	 */
	private String carType;

	/**
	 * 算费时间格式‘yyyy-MM-dd HH:mm:ss’
	 */
	private String getTimes;

	private String plateId;

	private String payCharge;

	private List payInfos;

	public String getPayCharge() {
		return payCharge;
	}

	public void setPayCharge(String payCharge) {
		this.payCharge = payCharge;
	}

	public List getPayInfos() {
		return payInfos;
	}

	public void setPayInfos(List payInfos) {
		this.payInfos = payInfos;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getGetTimes() {
		return getTimes;
	}

	public void setGetTimes(String getTimes) {
		this.getTimes = getTimes;
	}

	public ParklotFeeData(String orderId, String parkId, String parkName, String inTime, String charge, String carType,
			String getTimes) {
		this.orderId = orderId;
		this.parkId = parkId;
		this.parkName = parkName;
		this.inTime = inTime;
		this.charge = charge;
		this.carType = carType;
		this.getTimes = getTimes;
	}

	public ParklotFeeData() {
	}

	public String getPlateId() {
		return plateId;
	}

	public void setPlateId(String plateId) {
		this.plateId = plateId;
	}

	@Override
	public String toString() {
		return "{\"orderId\":\"" + orderId + "\",\"parkId\":\"" + parkId + "\",\"parkName\":\"" + inTime
				+ "\",\"charge\":\"" + charge + "\",\"carType\":\"" + carType + "\"}";
	}
}
