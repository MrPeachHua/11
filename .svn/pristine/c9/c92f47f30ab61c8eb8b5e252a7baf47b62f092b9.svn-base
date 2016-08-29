package com.boxiang.share.system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.b2m.eucp.client.SingletonClient;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.system.po.ParkingTicket;
import com.boxiang.share.system.po.Sms;
import com.boxiang.share.system.service.ParkingTicketService;
import com.boxiang.share.system.service.SmsService;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.utils.DateUtil;

@Controller
@RequestMapping("system/sms")
public class SmsController extends BaseController{
	private static final Logger logger = Logger.getLogger(DictionaryController.class);
	/** sms*/
	@Resource
	private SmsService smsService;
	
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
			Page<Sms> page = new Page<Sms>();
			PageHelper.initPage(request, page);
			Map<String, Object> map = super.getParamMap(request);
			String content = (String) map.get("mcontent");
			String mobile = (String) map.get("mobile");
			String addTime = (String) map.get("pushdate");
			page.getParams().put("content", content);
			page.getParams().put("mobile", mobile);
			page.getParams().put("addTime", addTime);
			page = smsService.queryListPage(page);
			PageHelper.setPageModel(request, page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入列表页出现异常",e);
		}
		
		return new ModelAndView("system/sms/sms_list");
	}
	/**
	 * 查询短信推送信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("nomalMessage.html")
	public ModelAndView nomalMessage(HttpServletRequest request,HttpServletResponse response){
		try {
			//SingletonClient.sendMessage(new String[]{"13120569306"}, "【口袋停】您的月租车位费已缴纳成功,系统到帐时间约为12小时,在此期间如遇到出入问题,可向收费员出示订单付款凭证。", 5);
			Page<Customer> page = new Page<Customer>();
			PageHelper.initPage(request, page);
			page = customerService.queryListPage(page);
			PageHelper.setPageModel(request, page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入列表页出现异常",e);
		}
		
		return new ModelAndView("system/sms/nomal_message");
	}
	
	/**
	 * 查询短信推送信息页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("parkingTicketMessage.html")
	public ModelAndView parkingTicketMessage(HttpServletRequest request,HttpServletResponse response){
		try {
			Page<Customer> page = new Page<Customer>();
			PageHelper.initPage(request, page);
			page = customerService.queryListPage(page);
			PageHelper.setPageModel(request, page);			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入列表页出现异常",e);
		}
		
		return new ModelAndView("system/sms/parking_ticket_message");
	}
	/**
	 * 停车券推送
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("parkingTicketMessagePush.html")
	public String parkingTicketMessagePush(HttpServletRequest request,HttpServletResponse response){
		//返回成功失败表示
		String flag = "0";
		PrintWriter writer;		
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("rawtypes")
		Map param = request.getParameterMap();
		/** 开始时间*/
		String [] start_date = null;
		/** 结束时间*/
		String [] end_date = null;
		/** 手机号*/
		String [] numbers = null;
		/** 短信内容*/
		String [] msg_content = null;
		//param = param.get("param");
		numbers =  (String[]) param.get("numbers[]");
		start_date =  (String[]) param.get("start_date");
		end_date  =  (String[]) param.get("end_date");
		msg_content = (String[]) param.get("msg_content");
		UserInfo currUser = (UserInfo) super.getLoginUser(request);
		if(null==numbers||numbers.length==0){//无手机号时过滤
			try {
				writer = response.getWriter();
				writer.print(flag);
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		/**sms表插入信息*/
		//Sms sms = new Sms();
		//sms.setAddTime(DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT));
		ParkingTicket  parkingTicket = new ParkingTicket();
		parkingTicket.setCreateDate(new Date());
		parkingTicket.setIsUsed(Constants.TRUE);
		parkingTicket.setModified(currUser.getUserNum());
		parkingTicket.setModifyDate(new Date());
		parkingTicket.setCreateor(currUser.getUserNum());
		try {
			parkingTicket.setStartDate(DateUtil.str2date(start_date[0], DateUtil.DATE_FORMAT));
			parkingTicket.setEndDate(DateUtil.str2date(end_date[0], DateUtil.DATE_FORMAT));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String s: numbers){
			/** 获取随机码*/
			StringBuilder ranstr=new StringBuilder();//定义变长字符串
			Random random=new Random();
			//随机生成数字，并添加到字符串
			for(int i=0;i<6;i++){
				ranstr.append(random.nextInt(10));
			}
			
			String str = msg_content[0];
			str = str.replaceAll("@start_date@",start_date[0]);
			str = str.replace("@end_date@",end_date[0]);
			str = str.replace("@parking_code@",ranstr.toString());
			/*sms.setContent(str);
			sms.setMobile(s);
			try {
				smsService.add(sms);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}*/
			
			/** 查找用户信息*/
			String customer_id = "";
			Customer customer = new Customer();
			customer.setCustomerMobile(s);
			List<Customer> customerList =  customerService.selectList(customer);
			if(null!=customerList&&customerList.size()>0){
				customer_id = customerList.get(0).getCustomerId();
			}
			/** 插入code信息*/
			parkingTicket.setCustomerId(customer_id);
			parkingTicket.setMobile(s);
			parkingTicket.setCode(ranstr.toString());
			
			try {
				parkingTicketService.add(parkingTicket);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			String [] strs = new String[]{s};
			int reCode = 1;
			try {
				reCode = SingletonClient.getInstance().sendMessage(strs, str, 5);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logger.info("------------信息发送异常");
				flag = "0";
			}
			
			if(reCode!=0){
				logger.info("---------信息发送异常");
				flag = "0";
				
			}
		}
		try {
			writer = response.getWriter();
			writer.print(flag);
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 停车券推送
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("nomalMessagePush.html")
	public void nomalMessagePush(HttpServletRequest request,HttpServletResponse response){
		PrintWriter writer;		
		response.setContentType("text/html;charset=UTF-8");
		//parkingScope 0:全部 1:固定车场
		String parkingScope = request.getParameter("parkingScope");
		//userScope 0:全部 1：月租用户  2：产权用户
		String userScope = request.getParameter("userScope");
		//parkingId 停车场Id 
		String parkingId = request.getParameter("parkingId");
		String msgContent = request.getParameter("msgContent");
		String numbers = request.getParameter("numbers");
		String scheme = request.getParameter("scheme");
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("parkingScope", parkingScope);
		param.put("userScope", userScope);
		param.put("msgContent", msgContent);
		param.put("numbers", numbers);
		param.put("parkingId", parkingId);
		param.put("scheme", scheme);
		
		//返回成功失败表示
		String flag = "0";
		flag = smsService.pushNomalMessage(param);
		
		//int reCode = SingletonClient.getInstance().sendMessage(strs, msg_content.toString(), 5);
		try {
			writer = response.getWriter();
			writer.print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
	}
}