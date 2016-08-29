package com.boxiang.share.product.feedback.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.feedback.po.SystemReviews;
import com.boxiang.share.product.feedback.service.SystemReviewsService;

@Controller
@RequestMapping("products/feelback")
public class SystemReviewsController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(SystemReviewsController.class);
	
	@Resource private SystemReviewsService systemReviewsService;
	@Resource private CustomerService customerService;
	@RequestMapping("list.html")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = super.getParamMap(request);
		try {
			Page<SystemReviews> page = new Page<SystemReviews>();
			PageHelper.initPage(request, page);
			//page.getParams().put("isUsed", Constants.TRUE);
			String queryType = (String)map.get("queryType");
			String queryValue = (String)map.get("queryValue");
			if(!StringUtils.isEmpty(queryType)){
				switch (Integer.parseInt(queryType)) {
				case 1://创建日期
					page.getParams().put("reviewsInfo", queryValue);
					break;
				default:
					break;
				}
			}
			page = systemReviewsService.queryListPage(page);
			PageHelper.setPageModel(request, page);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询信息出现异常",e);
		}
		return new ModelAndView("products/feelback/reviews_list",map);
	}
	@RequestMapping("view.html")
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = super.getParamMap(request);
		String reviewsId=request.getParameter("reviewsId");
		SystemReviews systemReviews =systemReviewsService.queryById(reviewsId);
	    Customer customer=	customerService.queryByCustomerId(systemReviews.getCustomerId());
	    map.put("systemReviews", systemReviews);
	    map.put("customer", customer);
		return new ModelAndView("products/feelback/view",map);
		
		
		
		
	}
}