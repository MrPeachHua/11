package com.boxiang.share.product.parking.po;

import java.io.Serializable;
import java.util.Date;

public class ClearanceRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	/** ID */
	private Integer id;

	/** 车牌号 */
	private String carNumber;

	/** 车场ID */
	private String parkingId;

	/** 价格 */
	private Integer price;

	/** 创建人 */
	private String createor;

	private Date createDate;

	private String sumPrice;

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(String sumPrice) {
		this.sumPrice = sumPrice;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/** 修改人 */
	private String modified;

	/** 修改日期 */
	private java.util.Date modifyDate;

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	
	public String getCarNumber() {
		return this.carNumber;
	}

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}
	
	public String getParkingId() {
		return this.parkingId;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getPrice() {
		return this.price;
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