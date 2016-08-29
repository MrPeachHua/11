package com.boxiang.share.product.record.po;

import java.io.Serializable;

public class ParkingScanRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 车场ID */
	private String parkingId;

	/** 浏览次数 */
	private Integer count;

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}
	
	public String getParkingId() {
		return this.parkingId;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getCount() {
		return this.count;
	}

}