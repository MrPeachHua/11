package com.boxiang.share.product.carlife.po;

import java.io.Serializable;

public class CarlifeSrvInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private java.lang.Integer id;

	/** 服务名称 */
	private java.lang.String srvName;

	/** 服务logo */
	private java.lang.String logoPath;

	/** 所属服务分类,数据字典表 */
	private java.lang.String srvType;

	/** 服务简介 */
	private java.lang.String intro;

	/** 服务状态已上线1/预上线0, */
	private java.lang.String status;

	/** 服务标准介绍 */
	private java.lang.String description;

	/** 服务链接地址 */
	private java.lang.String srvLink;

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
	/** 服务标签*/
	private java.lang.String flag;
	/** 排序规则*/
	private java.lang.String sort;
	/**最大服务次数*/
	private String maxCount;
	private String carType;
	private Integer srvFee;
	private Integer srvPrice;
	private String parkingId;
	private Integer nowPrice;

	public Integer getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(Integer nowPrice) {
		this.nowPrice = nowPrice;
	}
	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Integer getSrvFee() {
		return srvFee;
	}

	public void setSrvFee(Integer srvFee) {
		this.srvFee = srvFee;
	}

	public Integer getSrvPrice() {
		return srvPrice;
	}

	public void setSrvPrice(Integer srvPrice) {
		this.srvPrice = srvPrice;
	}

	public String getParkingId() {
		return parkingId;
	}

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}

	public String getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(String maxCount) {
		this.maxCount = maxCount;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public java.lang.String getFlag() {
		return flag;
	}

	public void setFlag(java.lang.String flag) {
		this.flag = flag;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}

	public void setSrvName(java.lang.String srvName) {
		this.srvName = srvName;
	}
	
	public java.lang.String getSrvName() {
		return this.srvName;
	}

	public void setLogoPath(java.lang.String logoPath) {
		this.logoPath = logoPath;
	}
	
	public java.lang.String getLogoPath() {
		return this.logoPath;
	}

	public void setSrvType(java.lang.String srvType) {
		this.srvType = srvType;
	}
	
	public java.lang.String getSrvType() {
		return this.srvType;
	}

	public void setIntro(java.lang.String intro) {
		this.intro = intro;
	}
	
	public java.lang.String getIntro() {
		return this.intro;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}

	public void setSrvLink(java.lang.String srvLink) {
		this.srvLink = srvLink;
	}
	
	public java.lang.String getSrvLink() {
		return this.srvLink;
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

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

}