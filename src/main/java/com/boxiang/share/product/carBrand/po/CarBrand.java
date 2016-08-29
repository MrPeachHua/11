package com.boxiang.share.product.carBrand.po;

import java.io.Serializable;

public class CarBrand implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 品牌 */
	private Integer brandId;

	/** 品牌 */
	private String brandName;

	/** 品牌 */
	private String brandIcon;

	/** 是否热门品牌 */
	private Integer isHot;

	/** 首字母 */
	private String initial;

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	
	public Integer getBrandId() {
		return this.brandId;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandIcon(String brandIcon) {
		this.brandIcon = brandIcon;
	}
	
	public String getBrandIcon() {
		return this.brandIcon;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}
	
	public Integer getIsHot() {
		return this.isHot;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}
	
	public String getInitial() {
		return this.initial;
	}

}