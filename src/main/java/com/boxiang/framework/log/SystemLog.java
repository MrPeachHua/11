package com.boxiang.framework.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.boxiang.share.utils.LogTypeEnum;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SystemLog {
	
	/**
	 * @return  日志类型
	 */
	LogTypeEnum logType();
	
	/**
	 * 设置日志摘要(简要描述日志功能)
	 * @return	String		日志摘要
	 */
	String logSummary();
}
