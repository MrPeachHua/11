package com.boxiang.share.payment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.payment.po.AlipayNotify;
import com.boxiang.share.payment.po.PaymentInfo;
import com.boxiang.share.payment.service.PaymentInfoService;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.utils.json.JacksonUtil;

@Controller
@RequestMapping("payment/alipay")
public class PaymentAlipayController extends BaseController {
	private static final Logger log = Logger
			.getLogger(PaymentAlipayController.class);

	@Resource
	private PaymentInfoService paymentInfoService;
	@Resource
	private CustomerService customerService;

	/** 支付类型--支付宝 */
	private final String PAY_TYPE = "00";

	/**
	 * 支付宝请求信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/reqpay")
	@Deprecated
	public void reqpay(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			response.setContentType("text/html;charset=UTF-8");
			PaymentInfo payment = new PaymentInfo();
			Map<String, Object> map = WebUtils.getParametersStartingWith(
					(ServletRequest) request, "");
			payment.setCreateor("admin");
			payment.setCreateDate(new Date());
			payment.setPayType(PAY_TYPE);
			payment.setUseType("1");// 支付请求
			payment.setUseInfo((String) map.get("useInfo"));
			payment.setOrderId((String) map.get("orderId"));
			paymentInfoService.add(payment);
			out.print("success");
		} catch (Exception e) {
			out.print("fail");
			log.error("保存支付宝请求信息异常", e);
		}
	}

	private void savePaymentInfo(String useInfo,String orderId){
		PaymentInfo payment = new PaymentInfo();
		payment.setCreateDate(new Date());
		payment.setCreateor("admin");
		payment.setUseInfo(useInfo);
		payment.setUseType("2");// 支付请求
		payment.setPayType(PAY_TYPE);
		payment.setOrderId(StringUtils.isEmpty(orderId)?"order id is not found":orderId);
		paymentInfoService.add(payment);
	}
	
	private  String updateOrderByPayback(HttpServletRequest request){
		String message = null;
		// userinfo串
		String error="";
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Object> uiMap = WebUtils.getParametersStartingWith((ServletRequest) request, "");
		try {
			Iterator<String> it =  uiMap.keySet().iterator();
			while(it.hasNext()){
				String key = it.next();
				map.put(key, (String)uiMap.get(key));
			}
			// 交易状态
			String trade_status = map.get("trade_status");
			log.info("trade_status= " + trade_status + "--------------------!!!!!!!---------------");
			if ("WAIT_BUYER_PAY".equals(trade_status)){
				message = "success";
				error="验证失效,trade_status="+trade_status;
			}else if("TRADE_FINISHED".equalsIgnoreCase(trade_status) || "TRADE_SUCCESS".equalsIgnoreCase(trade_status)){
				if (AlipayNotify.verify(map)) {
					message = paymentInfoService.updateOrderByPayback(map.get("out_trade_no"), 
							map.get("trade_no"), PAY_TYPE, map.get("price"), map.get("gmt_payment"));				
				}else {
					log.error("支付宝验证失败");
					message = "fail";
					error="支付宝验证失败";
				}
			} else {// 验证失败
				log.error("验证失效,trade_status="+trade_status);
				message = "fail";
				error="验证失效,trade_status="+trade_status;
			}
		} catch (Exception e) {
			log.error("添加支付回调信息异常", e);
			message = "fail";
			error="添加支付回调信息异常"+e.getMessage();
		}
		// 保存支付回调日志
		savePaymentInfo(error+JacksonUtil.toJson(uiMap), map.get("out_trade_no"));
		return message;
	}
	/**
	 * 支付类型 00:支付宝，01:微信，02:银联
	 * 共享临停
	 * @param request
	 * @param response
	 */
	@RequestMapping("/backpay_10")
	public void backpay_10(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer;
		try {
			String message = updateOrderByPayback(request);

			// 商户订单号
			//String out_trade_no = request.getParameter("out_trade_no");
			// 支付宝交易号
			//String trade_no = request.getParameter("trade_no");
			//customerService.submitBluecard(out_trade_no, trade_no);
			writer = response.getWriter();
			writer.print(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 月租
	 * @param request
	 * @param response
	 */
	@RequestMapping("/backpay_13")
	public void backpay_13(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(updateOrderByPayback(request));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 产权
	 * @param request
	 * @param response
	 */
	@RequestMapping("/backpay_14")
	public void backpay_14(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(updateOrderByPayback(request));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 临停
	 * @param request
	 * @param response
	 */
	@RequestMapping("/backpay_11")
	public void backpay_11(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer;
		try {
			String message = updateOrderByPayback(request);

			// 商户订单号
			String out_trade_no = request.getParameter("out_trade_no");
			// 支付宝交易号
			String trade_no = request.getParameter("trade_no");
			customerService.submitBluecard(out_trade_no, trade_no);
			writer = response.getWriter();
			writer.print(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * 代泊
     * @param request
     * @param response
     */
    @RequestMapping("/backpay_12")
    public void backpay_12(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.print(updateOrderByPayback(request));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * 加油卡
	 * @param request
	 * @param response
	 */
	@RequestMapping("/backpay_15")
	public void backpay_15(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(updateOrderByPayback(request));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 钱包充值
	 * @param request
	 * @param response
	 */
	@RequestMapping("/backpay_16")
	public void backpay_16(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(updateOrderByPayback(request));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 洗车
	 * @param request
	 * @param response
	 */
	@RequestMapping("/backpay_17")
	public void backpay_17(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(updateOrderByPayback(request));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
