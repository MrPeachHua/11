package com.boxiang.share.system.synwhite.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.system.synwhite.po.ScheduleJob;
import com.boxiang.share.utils.synwhite.TimerValueEnum;
import com.boxiang.share.utils.synwhite.TriggerStateEnum;
import com.boxiang.share.utils.synwhite.WhiteListRunTimeUtils;

//Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class QuartzService {

	private Logger logger = LoggerFactory.getLogger(QuartzService.class);

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	/**
	 * 获取所有的Job
	 * 
	 * @throws SchedulerException
	 */
	public List<ScheduleJob> getJobAll() throws SchedulerException {
		// schedulerFactoryBean 由spring创建注入
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				ScheduleJob job = new ScheduleJob();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(String.valueOf(triggerState.ordinal()));
				job.setJobStatusDesc(TriggerStateEnum.getTriggerStateDesc(String.valueOf(triggerState.ordinal())));
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
					TriggerKey triggerKey = cronTrigger.getKey();
					job.setTriggerName(triggerKey.getName());
					job.setTriggerGroup(triggerKey.getGroup());
					job.setDesc(jobKey.getName() + "任务,<font color='red'>" + TimerValueEnum.getTimerId(cronExpression) + "</font>分钟运行一次");
					jobList.add(job);
				}
				logger.info("all job is " + job.getJobName());
			}
		}
		return jobList;
	}

	/**
	 * 暂停Job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void pauseJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复Job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void resumeJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 立即运行任务一次
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void triggerJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.triggerJob(jobKey);
	}

	/**
	 * 更新任务表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void rescheduleJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getTriggerName(), scheduleJob.getTriggerGroup());
		// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		//把间隔时间写入文件
		WhiteListRunTimeUtils.getInstance().setWhiteListRunTime(scheduleJob.getCronExpression());
		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder//
				.cronSchedule(TimerValueEnum.getTimerValue(scheduleJob.getCronExpression()));
		// 按新的cronExpression表达式重新构建trigger	
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		// 按新的trigger重新设置job执行
		scheduler.rescheduleJob(triggerKey, trigger);
		//triggerJob(scheduleJob);
	}

}
