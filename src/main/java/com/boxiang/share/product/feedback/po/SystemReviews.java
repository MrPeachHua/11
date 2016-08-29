package com.boxiang.share.product.feedback.po;

import java.io.Serializable;

public class SystemReviews implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 唯一标识 */
	private java.lang.String reviewsId;

	/** 用户ID */
	private java.lang.String customerId;

	/** 创建时间 */
	private java.lang.String createTime;

	/** 意见评价内容 */
	private java.lang.String reviewsInfo;
	
	private java.lang.String customerNickName;
	private java.lang.String customerMobile;
	
	
	
	 

	public java.lang.String getCustomerNickName() {
		return customerNickName;
	}

	public void setCustomerNickName(java.lang.String customerNickName) {
		this.customerNickName = customerNickName;
	}

	public java.lang.String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(java.lang.String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public void setReviewsId(java.lang.String reviewsId) {
		this.reviewsId = reviewsId;
	}
	
	public java.lang.String getReviewsId() {
		return this.reviewsId;
	}

	public void setCustomerId(java.lang.String customerId) {
		this.customerId = customerId;
	}
	
	public java.lang.String getCustomerId() {
		return this.customerId;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}
	
	public java.lang.String getCreateTime() {
		return this.createTime;
	}

	public void setReviewsInfo(java.lang.String reviewsInfo) {
		this.reviewsInfo = reviewsInfo;
	}
	
	public java.lang.String getReviewsInfo() {
		return this.reviewsInfo;
	}

}