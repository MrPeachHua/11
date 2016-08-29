package com.boxiang.share.product.customer.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class RechargeRule implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/** 图片路径 */
	private String imagePath;

	/** 开始时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date beginDate;

	/** 结束时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date endDate;

	/** 是否可用 */
	private String isUsed;

	/** 创建人 */
	private String createor;
	/** 创建日期 */
	private java.util.Date createDate;
	/** 修改人 */
	private String modified;

	/** 修改日期 */
	private java.util.Date modifyDate;

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

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String getImagePath() {
		return this.imagePath;
	}

	public void setBeginDate(java.util.Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public java.util.Date getBeginDate() {
		return this.beginDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	
	public java.util.Date getEndDate() {
		return this.endDate;
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