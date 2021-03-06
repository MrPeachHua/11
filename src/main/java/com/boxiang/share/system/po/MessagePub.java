package com.boxiang.share.system.po;

import java.io.Serializable;

public class MessagePub implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private java.lang.Integer messageId;

	/** 标题 */
	private java.lang.String title;

	/** 小喇叭消息 */
	private java.lang.String message;

	/** 消息（保留字段） */
	private java.lang.String messageTwo;

	/** 是否可用 */
	private java.lang.String isUsed;

	/** 创建人 */
	private java.lang.String createor;

	/** 修改人 */
	private java.lang.String modified;
	/** 创建日期 */
	private java.util.Date createDate;
	/** 修改日期 */
	private java.util.Date modifyDate;

	public void setMessageId(java.lang.Integer messageId) {
		this.messageId = messageId;
	}
	
	public java.lang.Integer getMessageId() {
		return this.messageId;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}

	public void setMessage(java.lang.String message) {
		this.message = message;
	}
	
	public java.lang.String getMessage() {
		return this.message;
	}

	public void setMessageTwo(java.lang.String messageTwo) {
		this.messageTwo = messageTwo;
	}
	
	public java.lang.String getMessageTwo() {
		return this.messageTwo;
	}

	public void setIsUsed(java.lang.String isUsed) {
		this.isUsed = isUsed;
	}
	
	public java.lang.String getIsUsed() {
		return this.isUsed;
	}

	public void setCreateor(java.lang.String createor) {
		this.createor = createor;
	}
	
	public java.lang.String getCreateor() {
		return this.createor;
	}

	public void setModified(java.lang.String modified) {
		this.modified = modified;
	}
	
	public java.lang.String getModified() {
		return this.modified;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}

}