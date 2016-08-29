package com.boxiang.share.system.synwhite.po;

/**
 * 进行远程调用返回的结果对象,针对只返回如下格式的数据
 * 成功返回: {"status":"success","errorCode":null}
 * 失败返回: {"status":"fail","errorCode":"100007"}
 * @author lizheng
 *
 */
public class SyncResult {

	/**
	 * 请求状态
	 */
	private String status;
	
	/**
	 * 错误码
	 */
	private String errorCode;

	public SyncResult(){}
	public SyncResult(String status, String errorCode) {
		this.status = status;
		this.errorCode = errorCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "RegisterUserSyncResult [status=" + status + ", errorCode=" + errorCode + "]";
	}

	

	
}
