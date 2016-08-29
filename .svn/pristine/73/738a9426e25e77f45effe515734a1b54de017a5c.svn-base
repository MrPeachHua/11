package com.boxiang.share.system.controller;

import cn.b2m.eucp.client.SingletonClient;
import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.system.po.ParkingTicket;
import com.boxiang.share.system.po.Sms;
import com.boxiang.share.system.po.SysLogs;
import com.boxiang.share.system.service.ParkingTicketService;
import com.boxiang.share.system.service.SmsService;
import com.boxiang.share.system.service.SysLogsService;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("system/smsLogs")
public class SysLogsController extends BaseController{
	private static final Logger logger = Logger.getLogger(DictionaryController.class);
	/** sms*/
	@Resource
	private SysLogsService sysLogsService;

	@Resource
	private CustomerService customerService;
	
	@Resource
	private ParkingTicketService parkingTicketService;
	
	/**
	 * 查询短信推送信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("list.html")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		try {
			Page<SysLogs> page = new Page<SysLogs>();
			PageHelper.initPage(request, page);
			String userName = request.getParameter("userName");
			String logSummary = request.getParameter("logSummary");
			String form_beginTime = request.getParameter("form_beginTime");
			String form_endTime = request.getParameter("form_endTime");
			String className = request.getParameter("className");
			String methodName = request.getParameter("methodName");
			page.getParams().put("userName", userName);
			page.getParams().put("logSummary", logSummary);

			page.getParams().put("form_beginTime", form_beginTime);
			page.getParams().put("form_endTime", form_endTime);
			page.getParams().put("logSummary", logSummary);
			page.getParams().put("className", className);
			page.getParams().put("methodName", methodName);
			page = sysLogsService.queryListPage(page);
			PageHelper.setPageModel(request, page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入列表页出现异常",e);
		}
		
		return new ModelAndView("system/sysLogs/sys_logs_list");
	}

}