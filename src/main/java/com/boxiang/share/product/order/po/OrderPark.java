package com.boxiang.share.product.order.po;

import java.io.Serializable;
import java.util.Date;

public class OrderPark implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private java.lang.Integer id;

	/** 订单主表ID(uuid) */
	private java.lang.String orderId;

	/** 车场ID */
	private java.lang.String parkingId;

	/** 接车代泊员ID */
	private java.lang.String parkerId;

	/** 还车代泊员ID */
	private java.lang.String parkerBackId;

	/** 验车照片路径（代泊员接车） */
	private java.lang.String validateImagePath;

	/** 停车照片路径（代泊员停车） */
	private java.lang.String parkingImagePath;

	/** 车牌号 */
	private java.lang.String carNumber;

	/** 订单创建时间 */
	private java.util.Date orderBeginDate;

	/** 预约取车时间（代泊员填写） */
	private java.util.Date orderEndDate;

	/** 代泊员接车拍完验车照上传完照片开始（用户：交车时间，代泊员：接车时间） */
	private java.util.Date actualBeginDate;

	/** 用户付完款结束或代泊员订单结束 */
	private java.util.Date actualEndDate;

	/** 是否可用 */
	private java.lang.String isUsed;

	/** 创建人 */
	private java.lang.String createor;

	/** 修改人 */
	private java.lang.String modified;

	/** 修改日期 */
	private java.util.Date modifyDate;
	/** 创建日期 */
	private java.util.Date createDate;

	private String targetParkingId; // TARGET_PARKING_ID; // 目标车场id
	private String keyBox; // KEY_BOX;  // 钥匙箱
	private String backPark; // BACK_PARK; // 泊回地点
	private java.lang.String isPush;
	

	public String getIsPush() {
		return isPush;
	}

	public void setIsPush(String isPush) {
		this.isPush = isPush;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}

	public void setOrderId(java.lang.String orderId) {
		this.orderId = orderId;
	}
	
	public java.lang.String getOrderId() {
		return this.orderId;
	}

	public void setParkingId(java.lang.String parkingId) {
		this.parkingId = parkingId;
	}
	
	public java.lang.String getParkingId() {
		return this.parkingId;
	}

	public void setParkerId(java.lang.String parkerId) {
		this.parkerId = parkerId;
	}
	
	public java.lang.String getParkerId() {
		return this.parkerId;
	}

	public void setParkerBackId(java.lang.String parkerBackId) {
		this.parkerBackId = parkerBackId;
	}
	
	public java.lang.String getParkerBackId() {
		return this.parkerBackId;
	}

	public void setValidateImagePath(java.lang.String validateImagePath) {
		this.validateImagePath = validateImagePath;
	}
	
	public java.lang.String getValidateImagePath() {
		return this.validateImagePath;
	}

	public void setParkingImagePath(java.lang.String parkingImagePath) {
		this.parkingImagePath = parkingImagePath;
	}
	
	public java.lang.String getParkingImagePath() {
		return this.parkingImagePath;
	}

	public void setCarNumber(java.lang.String carNumber) {
		this.carNumber = carNumber;
	}
	
	public java.lang.String getCarNumber() {
		return this.carNumber;
	}

	public void setOrderBeginDate(java.util.Date orderBeginDate) {
		this.orderBeginDate = orderBeginDate;
	}
	
	public java.util.Date getOrderBeginDate() {
		return this.orderBeginDate;
	}

	public void setOrderEndDate(java.util.Date orderEndDate) {
		this.orderEndDate = orderEndDate;
	}
	
	public java.util.Date getOrderEndDate() {
		return this.orderEndDate;
	}

	public void setActualBeginDate(java.util.Date actualBeginDate) {
		this.actualBeginDate = actualBeginDate;
	}
	
	public java.util.Date getActualBeginDate() {
		return this.actualBeginDate;
	}

	public void setActualEndDate(java.util.Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}
	
	public java.util.Date getActualEndDate() {
		return this.actualEndDate;
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

	public void setModified(java.lang.String modified) {
		this.modified = modified;
	}
	
	public java.lang.String getModified() {
		return this.modified;
	}

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTargetParkingId() {
		return targetParkingId;
	}

	public void setTargetParkingId(String targetParkingId) {
		this.targetParkingId = targetParkingId;
	}

	public String getKeyBox() {
		return keyBox;
	}

	public void setKeyBox(String keyBox) {
		this.keyBox = keyBox;
	}

	public String getBackPark() {
		return backPark;
	}

	public void setBackPark(String backPark) {
		this.backPark = backPark;
	}
}