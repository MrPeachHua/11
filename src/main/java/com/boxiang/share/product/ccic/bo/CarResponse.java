package com.boxiang.share.product.ccic.bo;

import java.io.Serializable;
import java.util.Date;

import com.boxiang.share.utils.xml.DateTimeXStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("response")
public class CarResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@XStreamAsAttribute
	@XStreamConverter(value=DateTimeXStreamConverter.class)
	private Date finishTime;
	
	private String isSuccess;
	
	private String insuranceApplicantNo;
	
	private String errorCode;
	
	private String errorReason;

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getInsuranceApplicantNo() {
		return insuranceApplicantNo;
	}

	public void setInsuranceApplicantNo(String insuranceApplicantNo) {
		this.insuranceApplicantNo = insuranceApplicantNo;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

}
