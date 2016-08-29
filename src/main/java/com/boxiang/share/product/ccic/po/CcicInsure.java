package com.boxiang.share.product.ccic.po;

import java.io.Serializable;
import java.util.Date;

public class CcicInsure implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/** 投保单号 */
	private String insuranceApplicantNo;

	/** 险种代码 */
	private String insureCode;

	/** 险种名称 */
	private String insureName;

	/** 险种保额 */
	private String insureAmount;

	/** 险种保费 */
	private String insurePremium;

	/** 不计免赔标志 */
	private String insureFlag;

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

	public void setInsuranceApplicantNo(String insuranceApplicantNo) {
		this.insuranceApplicantNo = insuranceApplicantNo;
	}
	
	public String getInsuranceApplicantNo() {
		return this.insuranceApplicantNo;
	}

	public void setInsureCode(String insureCode) {
		this.insureCode = insureCode;
	}
	
	public String getInsureCode() {
		return this.insureCode;
	}

	public void setInsureName(String insureName) {
		this.insureName = insureName;
	}
	
	public String getInsureName() {
		return this.insureName;
	}

	public void setInsureAmount(String insureAmount) {
		this.insureAmount = insureAmount;
	}
	
	public String getInsureAmount() {
		return this.insureAmount;
	}

	public void setInsurePremium(String insurePremium) {
		this.insurePremium = insurePremium;
	}
	
	public String getInsurePremium() {
		return this.insurePremium;
	}

	public void setInsureFlag(String insureFlag) {
		this.insureFlag = insureFlag;
	}
	
	public String getInsureFlag() {
		return this.insureFlag;
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