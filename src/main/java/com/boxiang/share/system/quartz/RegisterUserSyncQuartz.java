package com.boxiang.share.system.quartz;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.product.order.service.OrderMainService;


@Component
public class RegisterUserSyncQuartz {

	private static Logger logger = LoggerFactory.getLogger(RegisterUserSyncQuartz.class);
	@Autowired
	private OrderMainService orderMainService;
	
	@Resource private String profileId;

	// 启动调度任务
	public void startTask() throws Exception {
		if(Constants.PROFILE_ID_PRE.equalsIgnoreCase(this.profileId)  || Constants.PROFILE_ID_PROD.equalsIgnoreCase(this.profileId)){
			/**
			 * 任务查询比较耗时,发送邮件比较耗时,远程访问比较耗时,都可能导致触发器线程的阻塞
			 * 解决这个问题的方法是，另启一个工作线程来执行定时任务
			 */
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						orderMainService.synWhiteList(null);
					} catch (Exception e) {
						logger.info("in 白名单接口同步自动调度出错:" + e.getMessage());
					}
				}
			}).start();
			
		}

	}
}
