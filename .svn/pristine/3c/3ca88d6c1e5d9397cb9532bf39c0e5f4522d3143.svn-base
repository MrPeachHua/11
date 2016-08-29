package com.boxiang.share.product.coupon.po;

import java.io.Serializable;
import java.util.Date;

public class RedeemRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/** 外键，对应t_redeem_rule表的ID，代表你领取了哪条规则下的优惠券 */
	private Integer redeemRuleId;

	/** 类型，1：新注册用户使用兑换码获取的优惠券 2：老用户兑换码被兑换一定次数后可领取的优惠券 */
	private String type;

	/** 被领取者的用户ID */
	private String oldCustomerId;

	/** 领取者的用户ID */
	private String newCustomerId;

	/** 是否可用 */
	private String isUsed;

	/** 创建人 */
	private String createor;

	/** 修改人 */
	private String modified;

	/** 修改日期 */
	private java.util.Date modifyDate;

	private java.util.Date createDate;

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setRedeemRuleId(Integer redeemRuleId) {
		this.redeemRuleId = redeemRuleId;
	}
	
	public Integer getRedeemRuleId() {
		return this.redeemRuleId;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}

	public void setOldCustomerId(String oldCustomerId) {
		this.oldCustomerId = oldCustomerId;
	}
	
	public String getOldCustomerId() {
		return this.oldCustomerId;
	}

	public void setNewCustomerId(String newCustomerId) {
		this.newCustomerId = newCustomerId;
	}
	
	public String getNewCustomerId() {
		return this.newCustomerId;
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
}