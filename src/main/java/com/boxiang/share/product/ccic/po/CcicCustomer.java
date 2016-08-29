package com.boxiang.share.product.ccic.po;

import java.io.Serializable;
import java.util.Date;

public class CcicCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/** 客户id */
	private String customerId;

	/** 用户名称 */
	private String name;

	/** 手机号码 */
	private String mobile;

	/** 电子邮件地址 */
	private String email;

	/** 车牌号 */
	private String carLicence;

	/** 车辆行驶城市代码，6位 */
	private String cityCode;

	/** 车辆登记日期，到日或者到月都可以 */
	private String registerDate;

	/** 商业险生效日期 */
	private String bizBeginDate;

	/** 交强险生效日期 */
	private String forBeginDate;

	/** 订单号 */
	private String utmsn;

	/** 创建人 */
	private String createor;

	/** 修改人 */
	private String modified;

	/** 修改日期 */
	private java.util.Date modifyDate;

	private java.util.Date createDate;

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

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getCustomerId() {
		return this.customerId;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getMobile() {
		return this.mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setCarLicence(String carLicence) {
		this.carLicence = carLicence;
	}
	
	public String getCarLicence() {
		return this.carLicence;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	public String getCityCode() {
		return this.cityCode;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
	public String getRegisterDate() {
		return this.registerDate;
	}

	public void setBizBeginDate(String bizBeginDate) {
		this.bizBeginDate = bizBeginDate;
	}
	
	public String getBizBeginDate() {
		return this.bizBeginDate;
	}

	public void setForBeginDate(String forBeginDate) {
		this.forBeginDate = forBeginDate;
	}
	
	public String getForBeginDate() {
		return this.forBeginDate;
	}

	public void setUtmsn(String utmsn) {
		this.utmsn = utmsn;
	}
	
	public String getUtmsn() {
		return this.utmsn;
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