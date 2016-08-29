package com.boxiang.framework.log.po;

import java.io.Serializable;
import java.util.Date;

public class SystemLog implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 日志日期 */
	private Date logDate;
	
	/** 日志类型 */
	private String logType;
	
	/** 日志摘要*/
	private String logSummary;
	
	/** IP地址 */
	private String ipAddress;
	
	/** 主机名 */
	private String hostName;
	
	/** 操作者用户ID */
	private String sysUserId;
	
	/** 操作者用户名称 */
	private String userName;
	
	/** 操作对应的类名 */
	private String className;
	
	/** 操作对应的方法名 */
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
	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getLogSummary() {
		return logSummary;
	}

	public void setLogSummary(String logSummary) {
		this.logSummary = logSummary;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}
