package com.boxiang.share.product.ccic.bo;

import java.io.Serializable;

/**
 * 被保人信息
 * @author junior
 *
 */
public class InsuredInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 被保人姓名  */
	private String insuredName;
	
	/** 被保人证件类型 */
	private String insuredIdentifyType;
	
	/** 被保人证件号码  */
	private String insuredIdentifyNumber;
	
	/** 被保人手机号  */
	private String insuredMobile;
	
	/** 被保人邮箱地址  */
	private String insuredEmail;

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getInsuredIdentifyType() {
		return insuredIdentifyType;
	}

	public void setInsuredIdentifyType(String insuredIdentifyType) {
		this.insuredIdentifyType = insuredIdentifyType;
	}

	public String getInsuredIdentifyNumber() {
		return insuredIdentifyNumber;
	}

	public void setInsuredIdentifyNumber(String insuredIdentifyNumber) {
		this.insuredIdentifyNumber = insuredIdentifyNumber;
	}

	public String getInsuredMobile() {
		return insuredMobile;
	}

	public void setInsuredMobile(String insuredMobile) {
		this.insuredMobile = insuredMobile;
	}

	public String getInsuredEmail() {
		return insuredEmail;
	}

	public void setInsuredEmail(String insuredEmail) {
		this.insuredEmail = insuredEmail;
	}

}
