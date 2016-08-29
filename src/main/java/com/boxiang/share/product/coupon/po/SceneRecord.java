package com.boxiang.share.product.coupon.po;

import java.io.Serializable;
import java.util.Date;

public class SceneRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/** 外键，对应t_coupon_rule表的ID，代表你领取了哪条规则下的优惠券 */
	private Integer ruleId;

	/** 场景模式 1：入场 2：出场 3：注册 */
	private String sceneMode;

	/** 赠送的用户ID */
	private String customerId;

	/** 车牌号 */
	private String carNumber;

	/** 下单推送的订单号,以逗号分隔 */
	private String orderId;

	/** 优惠券Id,场景模式为 8:优惠券到期提醒时 才有用 */
	private String couponId;

	/** 是否可用 */
	private String isUsed;

	/** 创建人 */
	private String createor;
	/** 创建时间 */
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

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	
	public Integer getRuleId() {
		return this.ruleId;
	}

	public void setSceneMode(String sceneMode) {
		this.sceneMode = sceneMode;
	}
	
	public String getSceneMode() {
		return this.sceneMode;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getCustomerId() {
		return this.customerId;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	
	public String getCarNumber() {
		return this.carNumber;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
}