package com.boxiang.share.product.order.po;

import java.io.Serializable;
import java.util.Date;

public class OrderCarwash implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/** 车场ID */
	private String parkingId;

	/** 订单主表ID(uuid) */
	private String orderId;

	/** 车牌号 */
	private String carNumber;

	/** 是否可用 */
	private String isUsed;

	/** 创建人 */
	private String createor;
/** 创建日期 */
	private  java.util.Date createDate;
	/** 修改人 */
	private String modified;

	/** 修改日期 */
	private java.util.Date modifyDate;
	private  Date reserveDate;
	private  String carType;

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Date getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}
	
	public String getParkingId() {
		return this.parkingId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderId() {
		return this.orderId;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	
	public String getCarNumber() {
		return this.carNumber;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	
	public String getIsUsed() {
		return this.isUsed;
	}

	public void setCreateor(String createor) {
		this.createor = createor;
	}
	
	public String getCreateor() {
		return this.createor;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}
	
	public String getModified() {
		return this.modified;
	}

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}

}