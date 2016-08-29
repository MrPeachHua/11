package com.boxiang.share.product.activity.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class CarLifeActivity implements Serializable {

	/**  */
	private Integer id;

	/** 活动开始时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date startDate;

	/** 活动结束时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date endDate;

	/** 活动标题 */
	private String title;

	/** 类型: 1.优惠活动 2.用车心得 */
	private String type;

	/** 车场Id */
	private String parkingId;

	private String parkingName;

	/** 活动地址 */
	private String url;

	/** 图片路径 */
	private String imagePath;

	/** 排序 */
	private Integer sort;

	/** 内容: 1.文字内容  2.URL地址 */
	private String contentType;

	private String html;

	/** 是否可用 */
	private String isUsed;

	/** 创建人 */
	private String createor;

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

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}
	
	public String getParkingId() {
		return this.parkingId;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String getImagePath() {
		return this.imagePath;
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

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}