package com.boxiang.share.product.carlife.po;

import java.io.Serializable;

public class CarlifeSrvBilling implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private java.lang.Integer id;

	/** 车场ID */
	private java.lang.String parkingId;

	/** 服务ID */
	private java.lang.Integer srvId;
	/** 车场ID */
	private java.lang.String parkingName;

	/** 车型 数据字典表 */
	private java.lang.String carType;

	/** 服务费 */
	private java.lang.Integer srvFee;

	/** 服务价格 */
	private java.lang.Integer srvPrice;

	/** 是否可用 */
	private java.lang.String isUsed;

	/** 创建人 */
	private java.lang.String createor;

	/** 修改人 */
	private java.lang.String modified;
	/** 创建日期 */
	private java.util.Date createDate;
	/** 修改日期 */
	private java.util.Date modifyDate;
	
	private String dict_name;
	/** 服务名称*/
	private String srv_name;
	
	/** 服务简介*/
	private String intro;

	private Integer nowPrice;

	public Integer getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(Integer nowPrice) {
		this.nowPrice = nowPrice;
	}

	public String getSrv_name() {
		return srv_name;
	}

	public void setSrv_name(String srv_name) {
		this.srv_name = srv_name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getDict_name() {
		return dict_name;
	}

	public void setDict_name(String dict_name) {
		this.dict_name = dict_name;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}

	public void setParkingId(java.lang.String parkingId) {
		this.parkingId = parkingId;
	}
	
	public java.lang.String getParkingId() {
		return this.parkingId;
	}

	public void setSrvId(java.lang.Integer srvId) {
		this.srvId = srvId;
	}
	
	public java.lang.Integer getSrvId() {
		return this.srvId;
	}

	public void setCarType(java.lang.String carType) {
		this.carType = carType;
	}
	
	public java.lang.String getCarType() {
		return this.carType;
	}

	public void setSrvFee(java.lang.Integer srvFee) {
		this.srvFee = srvFee;
	}
	
	public java.lang.Integer getSrvFee() {
		return this.srvFee;
	}

	public void setSrvPrice(java.lang.Integer srvPrice) {
		this.srvPrice = srvPrice;
	}
	
	public java.lang.Integer getSrvPrice() {
		return this.srvPrice;
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

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}

	public java.lang.String getParkingName() {
		return parkingName;
	}

	public void setParkingName(java.lang.String parkingName) {
		this.parkingName = parkingName;
	}


}