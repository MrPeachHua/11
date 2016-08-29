package com.boxiang.share.system.po;

import java.io.Serializable;

public class Sms implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 唯一标识 */
	private java.lang.Integer recordId;

	/** 推送内容 */
	private java.lang.String content;

	/** 手机号 */
	private java.lang.String mobile;

	/** 添加时间 */
	private java.lang.String addTime;

	public void setRecordId(java.lang.Integer recordId) {
		this.recordId = recordId;
	}
	
	public java.lang.Integer getRecordId() {
		return this.recordId;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}

	public void setAddTime(java.lang.String addTime) {
		this.addTime = addTime;
	}
	
	public java.lang.String getAddTime() {
		return this.addTime;
	}

}