package com.boxiang.share.system.synwhite.po;

import java.io.Serializable;
import java.util.Date;

public class WhitesynRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private java.lang.Integer id;

	/** 同步的车场json信息 */
	private java.lang.String parkingInfo;

	/** 白名单同步返回信息 */
	private java.lang.String callbackInfo;

	/** 是否可用0：不可用 1：可用 */
	private java.lang.String isUsed;

	/** 创建人 */
	private java.lang.String createor;
	
	/** 创建时间*/
	private Date createDate;
	

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}

	public void setParkingInfo(java.lang.String parkingInfo) {
		this.parkingInfo = parkingInfo;
	}
	
	public java.lang.String getParkingInfo() {
		return this.parkingInfo;
	}

	public void setCallbackInfo(java.lang.String callbackInfo) {
		this.callbackInfo = callbackInfo;
	}
	
	public java.lang.String getCallbackInfo() {
		return this.callbackInfo;
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

}