package com.boxiang.share.system.quartz;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

import com.boxiang.share.utils.synwhite.TimerValueEnum;
import com.boxiang.share.utils.synwhite.WhiteListRunTimeUtils;

public class MyCronTriggerFactoryBean extends CronTriggerFactoryBean {

	public MyCronTriggerFactoryBean() {
		super();
		String dbCronExpression = WhiteListRunTimeUtils.getInstance().getWhiteListRunTime().toString();
		setCronExpression(TimerValueEnum.getTimerValue(dbCronExpression ));
	}
}
