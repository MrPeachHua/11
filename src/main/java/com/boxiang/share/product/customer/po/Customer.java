package com.boxiang.share.product.customer.po;

import java.io.Serializable;

public class Customer implements Serializable {
	/** 渠道  */
	private java.lang.String channel;
	/** 登录数*/
	private java.lang.String loginNum;
	/** 注册数*/
	private java.lang.String registerNum;
	private static final long serialVersionUID = 1L;
	
	/** 唯一标识 */
	private java.lang.String customerId;

	/** 客户昵称 */
	private java.lang.String customerNickname;

	/** 密码 */
	private java.lang.String customerPassword;

	/** 等级(1普通2白银3黄金4白金5钻石) */
	private java.lang.String customerLevel;

	/** 真实姓名 */
	private java.lang.String customerName;

	/** 性别(1男 2女 3未知) */
	private java.lang.Integer customerSex;

	/** 年龄 */
	private java.lang.Integer customerAge;

	/** 头像 */
	private java.lang.String customerHead;

	/** 手机 */
	private java.lang.String customerMobile;

	/** 邮箱 */
	private java.lang.String customerEmail;

	/** 地区 */
	private java.lang.String customerRegion;

	/** 职业(1私营业主2事业单位3企业单位4其他) */
	private java.lang.String customerJob;

	/**  */
	private java.lang.String createdAt;

	/**  */
	private java.lang.String updatedAt;

	/** 身份证号 */
	private java.lang.String customerCardId;
	
	/** 最后登录时间 */
	private java.util.Date lastLoginTime;
	
	
	/** 注册开始时间*/
	private java.lang.String registerBegin;
	/** 注册结束时间*/
	private java.lang.String registerEnd;
	/** 登录开始时间*/
	private java.lang.String loginBegin;
	/** 登录结束时间*/
	private java.lang.String loginEnd;
	/** 用户身份*/
	private String identity;
	/** 微信唯一标示*/
	private String wxId;
	/** 微信唯一标示*/
	private String sinaId;
	/** 微信唯一标示*/
	private String qqId;
	//money
	private Integer money;
    //支付密码
	private String pay_password;
	//注册手机
	private  String reg_phone;

	// 兑换码
	private String redeemCode;
	private String carNumber;
	private String wxpayOpenid;
	private String growthValue;
	private String lastLoginSys;
	private String lastLoginMachine;
	private String appVersion;
//优惠券数量
	private Integer coupon;

	public String getGrowthValue() {
		return growthValue;
	}

	public void setGrowthValue(String growthValue) {
		this.growthValue = growthValue;
	}

	public String getLastLoginSys() {
		return lastLoginSys;
	}

	public void setLastLoginSys(String lastLoginSys) {
		this.lastLoginSys = lastLoginSys;
	}

	public String getLastLoginMachine() {
		return lastLoginMachine;
	}

	public void setLastLoginMachine(String lastLoginMachine) {
		this.lastLoginMachine = lastLoginMachine;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public Integer getCoupon() {
		return coupon;
	}

	public void setCoupon(Integer coupon) {
		this.coupon = coupon;
	}

	public String getWxpayOpenid() {
		return wxpayOpenid;
	}

	public void setWxpayOpenid(String wxpayOpenid) {
		this.wxpayOpenid = wxpayOpenid;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getRedeemCode() {
		return redeemCode;
	}

	public void setRedeemCode(String redeemCode) {
		this.redeemCode = redeemCode;
	}

	public String getReg_phone() {
		return reg_phone;
	}

	public void setReg_phone(String reg_phone) {
		this.reg_phone = reg_phone;
	}

	public String getPay_password() {
		return pay_password;
	}

	public void setPay_password(String pay_password) {
		this.pay_password = pay_password;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public String getSinaId() {
		return sinaId;
	}

	public void setSinaId(String sinaId) {
		this.sinaId = sinaId;
	}

	public String getQqId() {
		return qqId;
	}

	public void setQqId(String qqId) {
		this.qqId = qqId;
	}

	public String getIdentity() {
		return identity;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public java.lang.String getRegisterNum() {
		return registerNum;
	}

	public void setRegisterNum(java.lang.String registerNum) {
		this.registerNum = registerNum;
	}

	public java.lang.String getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(java.lang.String loginNum) {
		this.loginNum = loginNum;
	}

	public void setCustomerId(java.lang.String customerId) {
		this.customerId = customerId;
	}
	
	public java.lang.String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerNickname(java.lang.String customerNickname) {
		this.customerNickname = customerNickname;
	}
	
	public java.lang.String getCustomerNickname() {
		return this.customerNickname;
	}

	public void setCustomerPassword(java.lang.String customerPassword) {
		this.customerPassword = customerPassword;
	}
	
	public java.lang.String getCustomerPassword() {
		return this.customerPassword;
	}

	public void setCustomerLevel(java.lang.String customerLevel) {
		this.customerLevel = customerLevel;
	}
	
	public java.lang.String getCustomerLevel() {
		return this.customerLevel;
	}

	public void setCustomerName(java.lang.String customerName) {
		this.customerName = customerName;
	}
	
	public java.lang.String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerSex(java.lang.Integer customerSex) {
		this.customerSex = customerSex;
	}
	
	public java.lang.Integer getCustomerSex() {
		return this.customerSex;
	}

	public void setCustomerAge(java.lang.Integer customerAge) {
		this.customerAge = customerAge;
	}
	
	public java.lang.Integer getCustomerAge() {
		return this.customerAge;
	}

	public void setCustomerHead(java.lang.String customerHead) {
		this.customerHead = customerHead;
	}
	
	public java.lang.String getCustomerHead() {
		return this.customerHead;
	}

	public void setCustomerMobile(java.lang.String customerMobile) {
		this.customerMobile = customerMobile;
	}
	
	public java.lang.String getCustomerMobile() {
		return this.customerMobile;
	}

	public void setCustomerEmail(java.lang.String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	public java.lang.String getCustomerEmail() {
		return this.customerEmail;
	}

	public void setCustomerRegion(java.lang.String customerRegion) {
		this.customerRegion = customerRegion;
	}
	
	public java.lang.String getCustomerRegion() {
		return this.customerRegion;
	}

	public void setCustomerJob(java.lang.String customerJob) {
		this.customerJob = customerJob;
	}
	
	public java.lang.String getCustomerJob() {
		return this.customerJob;
	}

	public void setCreatedAt(java.lang.String createdAt) {
		this.createdAt = createdAt;
	}
	
	public java.lang.String getCreatedAt() {
		return this.createdAt;
	}

	public void setUpdatedAt(java.lang.String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public java.lang.String getUpdatedAt() {
		return this.updatedAt;
	}

	public void setCustomerCardId(java.lang.String customerCardId) {
		this.customerCardId = customerCardId;
	}
	
	public java.lang.String getCustomerCardId() {
		return this.customerCardId;
	}

	public java.util.Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(java.util.Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public java.lang.String getChannel() {
		return channel;
	}

	public void setChannel(java.lang.String channel) {
		this.channel = channel;
	}

	public java.lang.String getRegisterBegin() {
		return registerBegin;
	}

	public void setRegisterBegin(java.lang.String registerBegin) {
		this.registerBegin = registerBegin;
	}

	public java.lang.String getRegisterEnd() {
		return registerEnd;
	}

	public void setRegisterEnd(java.lang.String registerEnd) {
		this.registerEnd = registerEnd;
	}

	public java.lang.String getLoginBegin() {
		return loginBegin;
	}

	public void setLoginBegin(java.lang.String loginBegin) {
		this.loginBegin = loginBegin;
	}

	public java.lang.String getLoginEnd() {
		return loginEnd;
	}

	public void setLoginEnd(java.lang.String loginEnd) {
		this.loginEnd = loginEnd;
	}

}