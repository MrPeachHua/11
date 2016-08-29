package com.boxiang.share.reports.contoller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.coupon.controller.ExportExcel;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.system.po.Dictionary;
import com.boxiang.share.system.service.DictionaryService;


@Controller
@RequestMapping(value = "reports/channel")
public class ChannelReportController extends BaseController{
	private static final Logger logger = Logger.getLogger(ChannelReportController.class);
	@Resource CustomerService customerService;
	@Resource DictionaryService dictionaryService;
	
	@RequestMapping(value="list.html")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		try{
			request.setCharacterEncoding("UTF-8");
			/*渠道*/
			Dictionary dict = new Dictionary();
			dict.setDictCode("reg_channel");
			dict.setIsUsed(Constants.TRUE);
			List<Dictionary> dictList = dictionaryService.selectList(dict);
			request.setAttribute("dictList", dictList);
			Page<Customer> page = new Page<Customer>();
			PageHelper.initPage(request, page);
			String channel = request.getParameter("channel");
			String registerBegin = request.getParameter("registerBegin");
			String registerEnd = request.getParameter("registerEnd");
			String loginBegin = request.getParameter("loginBegin");
			String loginEnd = request.getParameter("loginEnd");
			page.getParams().put("channel", channel);
			page.getParams().put("registerBegin", registerBegin);
			page.getParams().put("registerEnd", registerEnd);
			page.getParams().put("loginBegin", loginBegin);
			page.getParams().put("loginEnd", loginEnd);
			page = customerService.queryByC(page);
			page.setTotalCount(page.getResultList().size());
			PageHelper.setPageModel(request, page);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("",e);
		}
		return new ModelAndView("reports/channel_list");
	}
	@RequestMapping(value="excel_export.html")
	public void excelExport(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
			String channel = request.getParameter("channel");
			String loginBegin = request.getParameter("loginBegin");
			String loginEnd = request.getParameter("loginEnd");
			String registerBegin = request.getParameter("registerBegin");
			String registerEnd = request.getParameter("registerEnd");
			Customer customer = new Customer();
			if(channel !=null && channel.length()>0)
				customer.setChannel(channel);
			if(loginBegin !=null && loginBegin.length()>0)
				customer.setLoginBegin(loginBegin);
			if(loginEnd !=null && loginEnd.length()>0)
				customer.setLoginEnd(loginEnd);
			if(registerBegin !=null && registerBegin.length()>0)
				customer.setRegisterBegin(registerBegin);
			if(registerEnd !=null && registerEnd.length()>0)
				customer.setRegisterEnd(registerEnd);
			List<Customer> customerList = customerService.exportByC(customer);
			if(customerList!=null){
				ExportExcel.exportQDExcel("新建", new String[]{"渠道","登录数","注册数"}, customerList, response);
			}
			Map<String, String> map=new HashMap<String, String>();
			map.put("result", "success");
			try {
				response.getWriter().print(JSONArray.fromObject(map).toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="view.html")
	public ModelAndView view(HttpServletRequest request,HttpServletResponse response){
		try{
 
			Page<Customer> page = new Page<Customer>();
			PageHelper.initPage(request, page);
			String channel = request.getParameter("channel");
			String registerBegin = request.getParameter("registerBegin");
			String registerEnd = request.getParameter("registerEnd");
			String loginBegin = request.getParameter("loginBegin");
			String loginEnd = request.getParameter("loginEnd");
			page.getParams().put("channel", channel);
			page.getParams().put("registerBegin", registerBegin);
			page.getParams().put("registerEnd", registerEnd);
			page.getParams().put("loginBegin", loginBegin);
			page.getParams().put("loginEnd", loginEnd);
			page = customerService.selectByChannel(page);
			PageHelper.setPageModel(request, page);
		 }catch(Exception e){
			e.printStackTrace();
			logger.error("",e);
		}
		return new ModelAndView("reports/channel_view");
	}
	
	
}
