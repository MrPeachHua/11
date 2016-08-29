package com.boxiang.framework.log;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.product.customer.dao.CustomerDao;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.system.dao.SysLogsDao;
import com.boxiang.share.system.po.SysLogs;
import com.boxiang.share.user.po.UserInfo;

/**
 * @author junior.pan
 *
 */
public class SystemLogAspect {
	private static final Logger logger = Logger.getLogger(SystemLogAspect.class);
	
	/** 日志线程池的倍数 */
	private short multiple;

	/** 日志对应的线程池 */
	private ExecutorService executorService;
	
	@Resource
	private SysLogsDao sysLogsDao;

	@Resource
	private CustomerDao customerDao;

	/**
	 * 初始化
	 * 
	 * @author junior.pan
	 * @date 2016-6-30
	 */
	public void init() {
		// 初始化线程池
		// 线程池数量 = 处理器数量 * 配置业务日志线程池的倍数
		// 处理器数量
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		int threadNum = availableProcessors * multiple;
		executorService = Executors.newFixedThreadPool(threadNum);
	}
	
	/**
	 * 在方法执行完成后插入业务日志
	 * @author junior.pan
	 * @date 2016-6-30
	 * @param joinPoint		切入点
	 */
	public void afterReturning(JoinPoint joinPoint) {
		try {
			// 1、获取切面拦截的相关信息
			// 目标类
			Object target = joinPoint.getTarget();
			// 目标类Class字节码
			Class<?> targetClass = target.getClass();
			// 目标方法的签名
			Signature signature = joinPoint.getSignature();
			// 目标方法名
			String methodName = signature.getName();
			// 目标方法的参数类型数组
			Class<?>[] parameterTypes = ((MethodSignature) signature).getMethod().getParameterTypes();
			Method method = targetClass.getMethod(methodName, parameterTypes);
			if (method != null && method.isAnnotationPresent(SystemLog.class)) {
				String sysUserId = null;
				String userName = null;
				String ipAddress = null;
				String hostName = null;
				String customerId = null;
	            try {
		            ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		            ipAddress = getClientIP(attr.getRequest());
		            hostName = attr.getRequest().getHeader("Host");
		            HttpSession session=attr.getRequest().getSession(true);
		            UserInfo user = (UserInfo) session.getAttribute(Constants.LOGIN_USER);
		            sysUserId = (user==null?null:user.getSysUserId());
		            
		            userName = (user==null?null:user.getUserName());
		            if(StringUtils.isEmpty(sysUserId)){
		            	customerId = attr.getRequest().getParameter("customerId");
		            }
				} catch (Exception e) {
					logger.info("获取用户信息失败");
				}

				// 业务日志注解
				SystemLog systemLog = method.getAnnotation(SystemLog.class);
				// 业务日志POJO
				SysLogs systemLogPO = new SysLogs();
				systemLogPO.setLogDate(new Date());
				systemLogPO.setLogType(systemLog.logType().getDescription());
				systemLogPO.setLogSummary(systemLog.logSummary());
				systemLogPO.setIpAddress(ipAddress);
				systemLogPO.setHostName(hostName);
				systemLogPO.setSysUserId(sysUserId);
				systemLogPO.setUserName(userName);
				systemLogPO.setClassName(targetClass.getName());
				systemLogPO.setMethodName(methodName);
				logger.info(systemLogPO);	

				// 5、 通过线程池的方式将业务日志保存到MongoDB
				Runnable SystemLogRunnable = new SystemLogRunnable(systemLogPO,customerId);
				executorService.execute(SystemLogRunnable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getClientIP(HttpServletRequest request) {  
	    String fromSource = "X-Real-IP";  
	    String ip = request.getHeader("X-Real-IP");  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("X-Forwarded-For");  
	        fromSource = "X-Forwarded-For";  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	        fromSource = "Proxy-Client-IP";  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	        fromSource = "WL-Proxy-Client-IP";  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	        fromSource = "request.getRemoteAddr";  
	    }  
	    logger.info("App Client IP: "+ip+", fromSource: "+fromSource+", host: "+ request.getHeader("Host"));  
	    return ip;  
	} 
	public short getMultiple() {
		return multiple;
	}

	public void setMultiple(short multiple) {
		this.multiple = multiple;
	}

	/**
	 * @author junior.pan
	 *
	 */
	private class SystemLogRunnable implements Runnable {
		private SysLogs systemLogPO;
		
		private String customerId;

		public SystemLogRunnable(SysLogs systemLogPO,String customerId) {
			this.systemLogPO = systemLogPO;
			this.customerId = customerId;
		}

		@Override
		public void run() {	
			try {
	        	if(!StringUtils.isEmpty(this.customerId)){
	        		Customer customer = customerDao.queryByCustomerId(this.customerId);
	        		systemLogPO.setUserName((customer==null?"":customer.getCustomerMobile()));
	        		systemLogPO.setSysUserId(customerId);
	        	}	
				sysLogsDao.insert(systemLogPO);
			} catch (Exception e) {
				logger.error("写日志异常",e);
			}
		}
	}
}
