package com.boxiang.share.product.parking.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class ParkingPrice implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private String id;

	/**  */
	private String schemeId;

	/**  */
	private String parkingId;

	/**  */
	private BigDecimal price;

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	
	public String getSchemeId() {
		return this.schemeId;
	}

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}
	
	public String getParkingId() {
		return this.parkingId;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getPrice() {
		return this.price;
	}

}