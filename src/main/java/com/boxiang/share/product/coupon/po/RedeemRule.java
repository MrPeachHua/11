package com.boxiang.share.product.coupon.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RedeemRule implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/** 类型，1：新注册用户使用兑换码获取的优惠券 2：老用户兑换码被兑换一定次数后可领取的优惠券 */
	private String type;

	/** TYPE为2时，此字段才有效，老用户领取优惠券需要达到的兑换次数 */
	private Integer redeemCount;

	/** 是否可用 */
	private String isUsed;

	/** 创建人 */
	private String createor;

	/** 修改人 */
	private String modified;

	/** 修改日期 */
	private java.util.Date modifyDate;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createDate;

	private List<CouponTemplate> couponTemplateList;

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}

	public void setRedeemCount(Integer redeemCount) {
		this.redeemCount = redeemCount;
	}
	
	public Integer getRedeemCount() {
		return this.redeemCount;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<CouponTemplate> getCouponTemplateList() {
		return couponTemplateList;
	}

	public void setCouponTemplateList(List<CouponTemplate> couponTemplateList) {
		this.couponTemplateList = couponTemplateList;
	}
}
