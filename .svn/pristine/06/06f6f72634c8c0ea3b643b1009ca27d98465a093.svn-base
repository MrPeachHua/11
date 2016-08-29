package com.boxiang.share.product.coupon.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class CouponTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/** 类型，1：车辆入场出场时送的优惠券 2：兑换码搞出来的优惠券 */
	private String type;

	/** 外键，TYPE为1时，使用t_coupon_rule表的ID；TYPE为2时，使用t_redeem_rule表的ID */
	private Integer typeId;

	/** 优惠券类型 0：通用卷 1：停车卷 2：月租产权劵 3：代泊券 4：车管家券 */
	private String couponKind;

	/** 优惠券名称 */
	private String couponName;

	/** 优惠券类型：1面值，2折扣 */
	private String couponType;

	/** 面值(元) */
	private java.math.BigDecimal parAmount;

	/** 面值(折扣) */
	private java.math.BigDecimal parDiscount;

	/** 最低消费 */
	private java.math.BigDecimal minconsumption;

	/** 有效类型 1：使用开始结束时间 2：使用有效天数 */
	private String effectiveType;

	/** 有效开始时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date effectiveBegin;

	/** 有效结束时间 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date effectiveEnd;

	/** 自领券起有效期天数 */
	private Integer effectiveDay;

	/** 互斥规则 */
	private String exclusionRule;

	/** 备注说明 */
	private String info;

	/** 车场id,逗号分隔 */
	private String parkingId;
	/** 车场名称,逗号分隔 */
	private String parkingName;

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

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setCouponKind(String couponKind) {
		this.couponKind = couponKind;
	}
	
	public String getCouponKind() {
		return this.couponKind;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	
	public String getCouponName() {
		return this.couponName;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	
	public String getCouponType() {
		return this.couponType;
	}

	public void setParAmount(java.math.BigDecimal parAmount) {
		this.parAmount = parAmount;
	}
	
	public java.math.BigDecimal getParAmount() {
		return this.parAmount;
	}

	public void setParDiscount(java.math.BigDecimal parDiscount) {
		this.parDiscount = parDiscount;
	}
	
	public java.math.BigDecimal getParDiscount() {
		return this.parDiscount;
	}

	public void setMinconsumption(java.math.BigDecimal minconsumption) {
		this.minconsumption = minconsumption;
	}
	
	public java.math.BigDecimal getMinconsumption() {
		return this.minconsumption;
	}

	public void setEffectiveType(String effectiveType) {
		this.effectiveType = effectiveType;
	}
	
	public String getEffectiveType() {
		return this.effectiveType;
	}

	public void setEffectiveBegin(java.util.Date effectiveBegin) {
		this.effectiveBegin = effectiveBegin;
	}
	
	public java.util.Date getEffectiveBegin() {
		return this.effectiveBegin;
	}

	public void setEffectiveEnd(java.util.Date effectiveEnd) {
		this.effectiveEnd = effectiveEnd;
	}
	
	public java.util.Date getEffectiveEnd() {
		return this.effectiveEnd;
	}

	public void setEffectiveDay(Integer effectiveDay) {
		this.effectiveDay = effectiveDay;
	}
	
	public Integer getEffectiveDay() {
		return this.effectiveDay;
	}

	public void setExclusionRule(String exclusionRule) {
		this.exclusionRule = exclusionRule;
	}
	
	public String getExclusionRule() {
		return this.exclusionRule;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getInfo() {
		return this.info;
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

	public String getParkingId() {
		return parkingId;
	}

	public void setParkingId(String parkingId) {
		this.parkingId = parkingId;
	}

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}
}
