package com.boxiang.share.product.car.po;

import java.io.Serializable;

public class CarUpkeepDict implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private String upkeepCode;

	/** 保养名称 */
	private String upkeepName;

	/** 排序 */
	private String upkeepSort;

	/** 说明 */
	private String description;

	/** 保养显示图片 */
	private String upkeepIcon;

	public void setUpkeepCode(String upkeepCode) {
		this.upkeepCode = upkeepCode;
	}
	
	public String getUpkeepCode() {
		return this.upkeepCode;
	}

	public void setUpkeepName(String upkeepName) {
		this.upkeepName = upkeepName;
	}
	
	public String getUpkeepName() {
		return this.upkeepName;
	}

	public void setUpkeepSort(String upkeepSort) {
		this.upkeepSort = upkeepSort;
	}
	
	public String getUpkeepSort() {
		return this.upkeepSort;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setUpkeepIcon(String upkeepIcon) {
		this.upkeepIcon = upkeepIcon;
	}
	
	public String getUpkeepIcon() {
		return this.upkeepIcon;
	}

}