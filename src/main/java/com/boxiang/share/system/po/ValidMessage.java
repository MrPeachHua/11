package com.boxiang.share.system.po;

import java.io.Serializable;

/**
 * 短信验证码
 */
public class ValidMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;//短信验证码
	private long time;//短信失效时间
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
}
