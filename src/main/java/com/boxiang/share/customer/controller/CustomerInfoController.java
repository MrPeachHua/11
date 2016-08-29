package com.boxiang.share.customer.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.share.product.order.controller.ExportOrderExcel;
import jxl.write.*;
import jxl.write.Number;
import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.customer.po.CustomerInfo;
import com.boxiang.share.product.coupon.controller.ExportExcel;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.system.service.DictionaryService;


@Controller
@RequestMapping(value = "customer/info")
public class CustomerInfoController extends BaseController{
	private static final Logger log = Logger
			.getLogger(CustomerInfoController.class);
	@Resource
	private CustomerService customerService;
	@Resource private DictionaryService dictionaryService;
	
	
	@RequestMapping("list.html")
	public ModelAndView showList(HttpServletRequest request,HttpServletResponse response){
		try{
			request.setCharacterEncoding("UTF-8");
			/*查询列表*/
			Page page = new Page<>();
			PageHelper.initPage(request, page);
			/*String customerMobile = request.getParameter("customerMobile");
			String customerId = request.getParameter("customerId");
			String customerNickname = request.getParameter("customerNickname");
			page.getParams().put("customerId", customerId);
			page.getParams().put("customerMobile", customerMobile);
			page.getParams().put("customerNickname", customerNickname);*/
			List<Map<String,Object>> list = customerService.queryListPage2(page);
			page.setResultList(list);
			PageHelper.setPageModel(request, page);
		}catch(Exception e){
			e.printStackTrace();
			log.error("进入客户信息列表页出现异常",e);
		}
		return new ModelAndView("customer/customer_list");
	}
	@RequestMapping("customerInfoDetail")
	public ModelAndView getDetail(HttpServletRequest request,HttpServletResponse response)
	{
		String customerId = request.getParameter("customerId");
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		List<Customer> customerList = customerService.selectList(customer);
		if(customerList!=null){
			request.setAttribute("customer", customerList.get(0));
			request.setAttribute("money", (0!=customerList.get(0).getMoney())?customerList.get(0).getMoney()/100:0);
		}
		return new ModelAndView("customer/customer_detail");
	}
	@RequestMapping(value="excelExport.html")
	public void excelExport(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map3 = super.getParamMap(request);
		try {

			request.setCharacterEncoding("UTF-8");
			Map<String,Object> map=new HashMap();
			Map<String,Object> map2=new HashMap();
			String customerMobile = request.getParameter("customerMobile");
			String customerCardId = request.getParameter("customerCardId");
			String appVersion = request.getParameter("appVersion");
			String registerBegin = request.getParameter("registerBegin");
			String registerEnd = request.getParameter("registerEnd");
			String loginBegin = request.getParameter("loginBegin");
			String loginEnd = request.getParameter("loginEnd");
		/*	CustomerInfo customerInfo = new CustomerInfo();
			customerInfo.setCustomerMobile(customerMobile);
			customerInfo.setCustomerCardId(customerCardId);
			customerInfo.setLoginBegin(loginBegin);
			customerInfo.setLoginEnd(loginEnd);
			customerInfo.setRegisterBegin(registerBegin);
			customerInfo.setRegisterEnd(registerEnd);
			customerInfo.setAppVersion(appVersion);*/
			map2.put("customerMobile",customerMobile);
			map2.put("customerCardId",customerCardId);
			map2.put("appVersion",appVersion);
			map2.put("registerBegin",registerBegin);
			map2.put("registerEnd",registerEnd);
			map2.put("loginBegin",loginBegin);
			map2.put("loginEnd",loginEnd);
			map.put("params",map2);


			final	List<Map<String,Object>> list = customerService.queryListPage2(map);
			ExportOrderExcel.exportExcel(new ExportOrderExcel.ExcelObject() {
				@Override
				public void dataSource(WritableSheet sheet, WritableCellFormat wcf_center, WritableCellFormat wcf_left) throws Exception {
					sheet.addCell(new Label(0, 0, "客户ID", wcf_center));
					sheet.addCell(new Label(1, 0, "客户名称", wcf_center));
					sheet.addCell(new Label(2, 0, "手机号", wcf_center));
					sheet.addCell(new Label(3, 0, "身份证号码", wcf_center));
					sheet.addCell(new Label(4, 0, "用户级别", wcf_center));
					sheet.addCell(new Label(5, 0, "注册时间", wcf_center));
					sheet.addCell(new Label(6, 0, "最后登陆时间", wcf_center));
					sheet.addCell(new Label(7, 0, "当前使用版本", wcf_center));
					sheet.addCell(new Label(8, 0, "余额", wcf_center));
					sheet.addCell(new Label(9, 0, "优惠券", wcf_center));
					sheet.addCell(new Label(10, 0, "登陆类型", wcf_center));
					sheet.addCell(new Label(11, 0, "终端设备机型", wcf_center));
					for (int i = 0; i < list.size(); i++) {
						Map ome = (Map) list.get(i);
						sheet.addCell(new Label(0, i + 1, ome.get("customer_id").toString()));
						sheet.addCell(new Label(1, i + 1, ome.get("customer_nickname") == null ? "" : ome.get("customer_nickname").toString()));
						sheet.addCell(new Label(2, i + 1, ome.get("customer_mobile") == null ? "" : ome.get("customer_mobile").toString()));
						sheet.addCell(new Label(3, i + 1, ome.get("customer_card_id") == null ? "" : ome.get("customer_card_id").toString()));
						sheet.addCell(new Label(4, i + 1, ome.get("customer_levelName") == null ? "" : ome.get("customer_levelName").toString()));
						sheet.addCell(new Label(5, i + 1, ome.get("created_at") == null ? "" : ome.get("created_at").toString()));
						sheet.addCell(new Label(6, i + 1, ome.get("last_login_time") == null ? "" : ome.get("last_login_time").toString()));
						sheet.addCell(new Label(7, i + 1, ome.get("app_version") == null ? "" : ome.get("app_version").toString()));
						sheet.addCell(new Label(8, i + 1, ome.get("money") == null ? "" : ome.get("money").toString()));
						sheet.addCell(new Label(9, i + 1, ome.get("coupon") == null ? "" : ome.get("coupon").toString()));
						sheet.addCell(new Label(10, i + 1, ome.get("last_login_sys") == null ? "" : ome.get("last_login_sys").toString()));
						sheet.addCell(new Label(11, i + 1, ome.get("last_login_machine") == null ? "" : ome.get("last_login_machine").toString()));
					}
				}
			}, response);
			map3.put("result", "success");

			response.getWriter().print(JSONArray.fromObject(map3).toString());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
