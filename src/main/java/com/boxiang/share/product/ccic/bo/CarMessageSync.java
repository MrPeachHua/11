package com.boxiang.share.product.ccic.bo;

import java.io.Serializable;
import java.util.Date;

import com.boxiang.share.utils.xml.DateTimeXStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("message")
public class CarMessageSync implements Serializable {
	private static final long serialVersionUID = 1L;

	@XStreamAsAttribute
	@XStreamConverter(value=DateTimeXStreamConverter.class)
	private Date finishTime;

	@XStreamAlias("request")
	private CarRequest request;

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public CarRequest getRequest() {
		return request;
	}

	public void setRequest(CarRequest request) {
		this.request = request;
	}
	
}
