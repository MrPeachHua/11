package com.boxiang.share.utils;

/**
 * @author junior.pan
 */
public enum LogTypeEnum {
	/**
	 * 订单
	 */
	ORDER_INFO("订单管理"),
	/**
	 * 车辆管理
	 */
	CAR_MANAGE("车辆管理"),
	/**
	 * 车场管理
	 */
	PARKING_MANAGE("车场管理"),
	/**
	 * 优惠促销
	 */
	PROMOTION("优惠促销");
	
	/** 日志类型中文描述 */
	private String description;
	
	private LogTypeEnum(String description) 
	{
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
