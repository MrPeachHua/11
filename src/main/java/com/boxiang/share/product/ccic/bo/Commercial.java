package com.boxiang.share.product.ccic.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 商业险信息
 * @author junior
 *
 */
public class Commercial implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 商业险保费  */
	private String insurePremium;

	/** 保障条款  */
	private List<Insure> insuranceList;

	public String getInsurePremium() {
		return insurePremium;
	}

	public void setInsurePremium(String insurePremium) {
		this.insurePremium = insurePremium;
	}

	public List<Insure> getInsuranceList() {
		return insuranceList;
	}

	public void setInsuranceList(List<Insure> insuranceList) {
		this.insuranceList = insuranceList;
	}
		
}
