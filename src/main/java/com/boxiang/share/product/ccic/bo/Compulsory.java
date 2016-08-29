package com.boxiang.share.product.ccic.bo;

import java.io.Serializable;

/**
 * 交强险信息
 * @author junior
 *
 */
public class Compulsory implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 交强险保费(不含车船税)  */
	private String insurePremium;

	/** 车船税  */
	private String travelTax;

	public String getInsurePremium() {
		return insurePremium;
	}

	public void setInsurePremium(String insurePremium) {
		this.insurePremium = insurePremium;
	}

	public String getTravelTax() {
		return travelTax;
	}

	public void setTravelTax(String travelTax) {
		this.travelTax = travelTax;
	}

}
