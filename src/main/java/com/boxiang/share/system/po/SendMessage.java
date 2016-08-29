package com.boxiang.share.system.po;

import java.io.Serializable;

public class SendMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 短信ID */
	private int id;

	/** 车场ID */
	private String parkingId;
private String parkingName;
	/** 订单类型 */
	private String orderType;

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	private String personName;
	private String personMobile;

	/** 创建时间 */
	private java.util.Date createTime;

	/** 创建者 */
	private String createUser;

	/** 更新时间 */
	private java.util.Date updateTime;

	/** 更新者 */
	private String updateUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}
	
	public String getParkingId() {
		return this.parkingId;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	public String getOrderType() {
		return this.orderType;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonMobile() {
		return personMobile;
	}

	public void setPersonMobile(String personMobile) {
		this.personMobile = personMobile;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	public String getCreateUser() {
		return this.createUser;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	public String getUpdateUser() {
		return this.updateUser;
	}

}