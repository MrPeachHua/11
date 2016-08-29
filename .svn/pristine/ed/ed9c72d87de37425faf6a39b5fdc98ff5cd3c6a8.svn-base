package com.boxiang.share.product.coupon.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CouponRule implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/** 蓝卡云车场ID */
	private String blueParkingId;

	/** 车场ID */
	private String parkingId;

	/** 车场名称 */
	private String parkingName;

	/** 场景模式：1：入场 2：出场 */
	private String sceneMode;

	/** 赠送的用户类型：1：月租 */
	private String userType;

	/** 推送方式：0：不推送 1：短信 2：APP极光推送 */
	private String pushMode;

	/** 开始时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date beginDate;

	/** 结束时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date endDate;

	/** 推送内容 */
	private String pushContent;

	/** 车辆入场时,推送的次数 */
	private Integer inParkPushCount;

	/** 订单类型 */
	private String orderType;
	/** 下单次数 */
	private Integer orderCount;
	/** 月租产权过期前多少天提醒 */
	private Integer monthlyPropertyExpireBefore;
	/** 月租产权过期后多少天提醒 */
	private Integer monthlyPropertyExpireAfter;
	/** 优惠券过期前多少天提醒 */
	private Integer couponExpireBefore;
	/** 优惠券类型,参考字典表,逗号分隔,*表示全部 */
	private String couponExpireType;

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

	public void setBlueParkingId(String blueParkingId) {
		this.blueParkingId = blueParkingId;
	}
	
	public String getBlueParkingId() {
		return this.blueParkingId;
	}

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}
	
	public String getParkingId() {
		return this.parkingId;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}
	
	public String getParkingName() {
		return this.parkingName;
	}

	public void setSceneMode(String sceneMode) {
		this.sceneMode = sceneMode;
	}
	
	public String getSceneMode() {
		return this.sceneMode;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getUserType() {
		return this.userType;
	}

	public void setPushMode(String pushMode) {
		this.pushMode = pushMode;
	}
	
	public String getPushMode() {
		return this.pushMode;
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

	public void setPushContent(String pushContent) {
		this.pushContent = pushContent;
	}
	
	public String getPushContent() {
		return this.pushContent;
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

	public Integer getInParkPushCount() {
		return inParkPushCount;
	}

	public void setInParkPushCount(Integer inParkPushCount) {
		this.inParkPushCount = inParkPushCount;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getMonthlyPropertyExpireBefore() {
		return monthlyPropertyExpireBefore;
	}

	public void setMonthlyPropertyExpireBefore(Integer monthlyPropertyExpireBefore) {
		this.monthlyPropertyExpireBefore = monthlyPropertyExpireBefore;
	}

	public Integer getMonthlyPropertyExpireAfter() {
		return monthlyPropertyExpireAfter;
	}

	public void setMonthlyPropertyExpireAfter(Integer monthlyPropertyExpireAfter) {
		this.monthlyPropertyExpireAfter = monthlyPropertyExpireAfter;
	}

	public Integer getCouponExpireBefore() {
		return couponExpireBefore;
	}

	public void setCouponExpireBefore(Integer couponExpireBefore) {
		this.couponExpireBefore = couponExpireBefore;
	}

	public String getCouponExpireType() {
		return couponExpireType;
	}

	public void setCouponExpireType(String couponExpireType) {
		this.couponExpireType = couponExpireType;
	}
}