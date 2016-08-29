package com.boxiang.share.system.synwhite.po;

import java.io.Serializable;
public class Specialparkinginfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 小区ID */
	private java.lang.String villageId;
	
	/** 车场名称*/
	private String parkingName;

	/** 车牌号 */
	private java.lang.String carNumber;

	/** 车主姓名 */
	private java.lang.String owner;

	/** 身份信息 */
	private java.lang.String certificate;

	/** 车主联系地址 */
	private java.lang.String address;

	/** 车主联系电话 */
	private java.lang.String phone;

	/** 车辆颜色(1黑2白3其他) */
	private java.lang.Integer carColor;
	private String isUsed;

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
	
	/** 车场编号*/
	private String  parkCode;
	
	private String parkingStr;

	private String newCarNumber;

	public String getNewCarNumber() {
		return newCarNumber;
	}

	public void setNewCarNumber(String newCarNumber) {
		this.newCarNumber = newCarNumber;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getParkingStr() {
		return parkingStr;
	}

	public void setParkingStr(String parkingStr) {
		this.parkingStr = parkingStr;
	}

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
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

	public void setOwner(java.lang.String owner) {
		this.owner = owner;
	}
	
	public java.lang.String getOwner() {
		return this.owner;
	}

	public void setCertificate(java.lang.String certificate) {
		this.certificate = certificate;
	}
	
	public java.lang.String getCertificate() {
		return this.certificate;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}

	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	
	public java.lang.String getPhone() {
		return this.phone;
	}

	public void setCarColor(java.lang.Integer carColor) {
		this.carColor = carColor;
	}
	
	public java.lang.Integer getCarColor() {
		return this.carColor;
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