package com.boxiang.share.system.synwhite.po;

import com.boxiang.share.utils.synwhite.TimerValueEnum;


public class ScheduleJob {
	/** 任务id */
	private String jobId;
	/** 任务名称 */
	private String jobName;
	/** 任务分组 */
	private String jobGroup;
	/** 任务状态 0禁用 1启用 2删除 */
	private String jobStatus;
	private String jobStatusDesc;
	/** 任务运行时间表达式 */
	private String cronExpression;
	/** 任务描述 */
	private String desc;
	
	private String triggerName;
	private String triggerGroup;
	
	private String parkingStr;
	
	

	public String getParkingStr() {
		return parkingStr;
	}

	public void setParkingStr(String parkingStr) {
		this.parkingStr = parkingStr;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getCronExpression() {
		return cronExpression;
	}
	
	public String getCronExpressionId() {
		return TimerValueEnum.getTimerId(cronExpression);
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public String getJobStatusDesc() {
		return jobStatusDesc;
	}

	public void setJobStatusDesc(String jobStatusDesc) {
		this.jobStatusDesc = jobStatusDesc;
	}
}
