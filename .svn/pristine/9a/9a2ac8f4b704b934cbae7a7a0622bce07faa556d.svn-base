package com.boxiang.share.system.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class AppVersion implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/** 平台 */
	private String platformCode;

	/** 版本号 */
	private String versionCode;

	/** 渠道 */
	private String versionChannel;

	/** 更新提示内容 */
	private String notice;

	/** 是否强制更新（1，是；0，否） */
	private String isNeeded;

	/** 更新地址 */
	private String updateUrl;

	/** 是否可用 */
	private String isUsed;

	/** 创建人 */
	private String createor;

	/** 创建日期 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createDate;

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}
	
	public String getPlatformCode() {
		return this.platformCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	
	public String getVersionCode() {
		return this.versionCode;
	}

	public void setVersionChannel(String versionChannel) {
		this.versionChannel = versionChannel;
	}
	
	public String getVersionChannel() {
		return this.versionChannel;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	public String getNotice() {
		return this.notice;
	}

	public void setIsNeeded(String isNeeded) {
		this.isNeeded = isNeeded;
	}
	
	public String getIsNeeded() {
		return this.isNeeded;
	}

	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}
	
	public String getUpdateUrl() {
		return this.updateUrl;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}