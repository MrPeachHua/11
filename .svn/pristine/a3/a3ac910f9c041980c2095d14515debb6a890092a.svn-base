package com.boxiang.share.product.ccic.bo;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class CarRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@XStreamAsAttribute
	private String func;

	@XStreamAsAttribute
	@XStreamAlias("return")
	private String retValue;

	/** from里为sellerId，需要与GET请求里所传参数保持一致  */
	@XStreamAsAttribute
	private String from;

	@XStreamAlias("content")
	private Content content;

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getRetValue() {
		return retValue;
	}

	public void setRetValue(String retValue) {
		this.retValue = retValue;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
}
