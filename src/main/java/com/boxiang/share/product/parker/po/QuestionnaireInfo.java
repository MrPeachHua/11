package com.boxiang.share.product.parker.po;

import java.io.Serializable;

public class QuestionnaireInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private java.lang.Integer id;

	/** 类型：10-取消代泊问卷反馈 */
	private java.lang.String surveyType;

	/** 0-输入，1-非输入 */
	private java.lang.String flag;

	/** 问卷内容 */
	private java.lang.String content;

	/** 创建人 */
	private java.lang.String createor;

	/** 修改人 */
	private java.lang.String modified;
	/** 创建日期 */
	private java.util.Date createDate;
	/** 修改日期 */
	private java.util.Date modifyDate;

	 

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}

	public void setSurveyType(java.lang.String surveyType) {
		this.surveyType = surveyType;
	}
	
	public java.lang.String getSurveyType() {
		return this.surveyType;
	}

	public void setFlag(java.lang.String flag) {
		this.flag = flag;
	}
	
	public java.lang.String getFlag() {
		return this.flag;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	public java.lang.String getContent() {
		return this.content;
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

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}

}