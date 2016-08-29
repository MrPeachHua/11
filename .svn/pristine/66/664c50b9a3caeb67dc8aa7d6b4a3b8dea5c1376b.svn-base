package com.boxiang.share.product.activity.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/** 活动开始时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date startDate;

	/** 活动结束时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date endDate;

	/** 活动名称 */
	private String name;

	/** 活动地址 */
	private String url;

	/** 是否可用 */
	private String isUsed;

	/** 创建人 */
	private String createor;

	private java.util.Date createDate;

	/** 0:已过期 1:未过期 */
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

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

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	
	public java.util.Date getStartDate() {
		return this.startDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	
	public java.util.Date getEndDate() {
		return this.endDate;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	
	public String getIsUsed() {
		return this.isUsed;
	}

	public void setCreateor(String createor) {
		this.createor = createor;
	}
	
	public String getCreateor() {
		return this.createor;
	}

}