package com.boxiang.share.product.ccic.bo;

import java.io.Serializable;

/**
 * 配送信息
 * @author junior
 *
 */
public class DistributionInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 配送人姓名  */
	private String receiverName;
	
	/** 配送人号码 */
	private String receiverMoblie;
	
	/** 配送人地址  */
	private String receiverAddress;

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMoblie() {
		return receiverMoblie;
	}

	public void setReceiverMoblie(String receiverMoblie) {
		this.receiverMoblie = receiverMoblie;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

}
