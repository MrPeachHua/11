package com.boxiang.share.product.ccic.bo;

import java.io.Serializable;

/**
 * 投保人信息
 * @author junior
 *
 */
public class ApplicantInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 投保人姓名  */
	private String applicantName;
	
	/** 投保人证件类型 */
	private String applicantIdentifyType;
	
	/** 投保人证件号码  */
	private String applicantIdentifyNumber;
	
	/** 投保人手机号  */
	private String applicantMobile;
	
	/** 投保人邮箱地址  */
	private String applicantEmail;

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplicantIdentifyType() {
		return applicantIdentifyType;
	}

	public void setApplicantIdentifyType(String applicantIdentifyType) {
		this.applicantIdentifyType = applicantIdentifyType;
	}

	public String getApplicantIdentifyNumber() {
		return applicantIdentifyNumber;
	}

	public void setApplicantIdentifyNumber(String applicantIdentifyNumber) {
		this.applicantIdentifyNumber = applicantIdentifyNumber;
	}

	public String getApplicantMobile() {
		return applicantMobile;
	}

	public void setApplicantMobile(String applicantMobile) {
		this.applicantMobile = applicantMobile;
	}

	public String getApplicantEmail() {
		return applicantEmail;
	}

	public void setApplicantEmail(String applicantEmail) {
		this.applicantEmail = applicantEmail;
	}

}
