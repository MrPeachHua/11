package com.boxiang.share.system.po;

import java.io.Serializable;

public class SysLogs implements Serializable {
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;

	/**  */
	private java.util.Date logDate;

	/**  */
	private String logType;

	/**  */
	private String logSummary;

	/**  */
	private String ipAddress;

	/**  */
	private String hostName;

	/**  */
	private String sysUserId;

	/**  */
	private String userName;

	/**  */
	private String className;

	/**  */
	private String methodName;

	@Override
	public String toString(){
		return new StringBuffer().append("logDate=").append(logDate)
				.append(",logType=").append(logType)
				.append(",logSummary=").append(logSummary)
				.append(",ipAddress=").append(ipAddress)
				.append(",hostName=").append(hostName)
				.append(",sysUserId=").append(sysUserId)
				.append(",userName=").append(userName)
				.append(",className=").append(className)
				.append(",methodName=").append(methodName)
				.toString();
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setLogDate(java.util.Date logDate) {
		this.logDate = logDate;
	}
	
	public java.util.Date getLogDate() {
		return this.logDate;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}
	
	public String getLogType() {
		return this.logType;
	}

	public void setLogSummary(String logSummary) {
		this.logSummary = logSummary;
	}
	
	public String getLogSummary() {
		return this.logSummary;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public String getHostName() {
		return this.hostName;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
	
	public String getSysUserId() {
		return this.sysUserId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return this.userName;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getClassName() {
		return this.className;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public String getMethodName() {
		return this.methodName;
	}

}