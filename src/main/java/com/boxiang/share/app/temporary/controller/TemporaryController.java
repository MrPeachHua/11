package com.boxiang.share.app.temporary.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.product.order.service.OrderTemporaryService;
@Controller
@RequestMapping("app/temporary")
public class TemporaryController extends BaseController {
	@Resource
	private OrderTemporaryService orderTemporaryService;
	/**
	 * 临停缴费首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("carlist/{customerId}/{carNumber}/{summary}")
	public void carlist(@PathVariable String customerId,@PathVariable String carNumber,HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin","*");
		try {
			out = response.getWriter();
			out.print(orderTemporaryService.getCarlist(customerId, carNumber));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	@RequestMapping("carlist/{customerId}/{summary}")
	public void carlist(@PathVariable String customerId,HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin","*");
		try {
			out = response.getWriter();
			out.print(orderTemporaryService.getCarlist(customerId, null));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
}
