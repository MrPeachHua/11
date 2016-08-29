package com.boxiang.share.product.order.po;

import java.io.Serializable;

public class Cenuse implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/** 支付时间 */
	private java.util.Date payTime;

	/** 区域 */
	private String region;

	/** 车场ID */
	private String parkingId;

	/** 车场名称 */
	private String parkingName;

	/** 线上收入 */
	private Integer lineon;

	/** 线下收入 */
	private Integer lineoff;

	/** 项目负责人 */
	private String grojectLeader;

	/** 区域负责人 */
	private String regionalHead;

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setPayTime(java.util.Date payTime) {
		this.payTime = payTime;
	}
	
	public java.util.Date getPayTime() {
		return this.payTime;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getRegion() {
		return this.region;
	}

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}
	
	public String getParkingId() {
		return this.parkingId;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}
	
	public String getParkingName() {
		return this.parkingName;
	}

	public void setLineon(Integer lineon) {
		this.lineon = lineon;
	}
	
	public Integer getLineon() {
		return this.lineon;
	}

	public void setLineoff(Integer lineoff) {
		this.lineoff = lineoff;
	}
	
	public Integer getLineoff() {
		return this.lineoff;
	}

	public void setGrojectLeader(String grojectLeader) {
		this.grojectLeader = grojectLeader;
	}
	
	public String getGrojectLeader() {
		return this.grojectLeader;
	}

	public void setRegionalHead(String regionalHead) {
		this.regionalHead = regionalHead;
	}
	
	public String getRegionalHead() {
		return this.regionalHead;
	}

}