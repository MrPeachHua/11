package com.boxiang.share.payment.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.payment.po.PaymentInfo;
import com.boxiang.share.payment.service.PaymentInfoService;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.utils.json.JacksonUtil;

@Controller
@RequestMapping("payment/wechat")
public class PaymentWechatController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(PaymentWechatController.class);

	@Resource
	private PaymentInfoService paymentInfoService;
	@Resource
	private CustomerService customerService;

	/** 支付类型--微信 */
	private final String PAY_TYPE = "01";

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
			logger.error("保存微信请求信息异常", e);
		}
	}
	/**
	 * 共享临停
	 */
	@RequestMapping(value = "backpay_10")
	public void backpay_10(HttpServletRequest request,
			HttpServletResponse response) {
		String message = null;
		// 解析结果存储在HashMap
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(inputStream);
		} catch (DocumentException e3) {
			e3.printStackTrace();
		}
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		logger.info(elementList.size()
				+ "---------------------==========------------------");
		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getStringValue());

		// 释放资源
		try {
			inputStream.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		inputStream = null;
		logger.info(map.size() + "ss");
		logger.info(map.toString());
		if (map.size() != 0) {
			// userinfo串
			String user_info = JacksonUtil.toJson(map);
			String order_id = map.get("out_trade_no");
			String trade_no = map.get("transaction_id");
			String trade_status = map.get("return_code");
			String buyer_email = map.get("openid");
			String total_fee = map.get("total_fee");
			String pay_Type = "01";
			logger.info("out_trade_no=" + order_id + "--transaction= "
					+ trade_no + "--return_code= " + trade_status
					+ "--openid= " + buyer_email + "--payType= " + pay_Type);
			if ("SUCCESS".equals(trade_status)) {
				message = customerService.payBackTempShare(order_id, trade_no,
						trade_status, buyer_email, PAY_TYPE, total_fee,
						user_info);
			} else {
				message = "fail";
			}
		} else {
			message = "fail";
		}
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 临停
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("backpay_11")
	public void backpay_11(HttpServletRequest request,
			HttpServletResponse response) {
		String message = null;
		// 解析结果存储在HashMap
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(inputStream);
		} catch (DocumentException e3) {
			e3.printStackTrace();
		}
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		logger.info(elementList.size()
				+ "---------------------==========------------------");
		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getStringValue());

		// 释放资源
		try {
			inputStream.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		inputStream = null;
		logger.info(map.size() + "ss");
		logger.info(map.toString());
		if (map.size() != 0) {
			// userinfo串
			String user_info = JacksonUtil.toJson(map);
			String order_id = map.get("out_trade_no");
			String trade_no = map.get("transaction_id");
			String trade_status = map.get("return_code");
			String buyer_email = map.get("openid");
			String total_Fee = map.get("total_fee");
			String pay_Type = "01";
			logger.info("out_trade_no=" + order_id + "--transaction= "
					+ trade_no + "--return_code= " + trade_status
					+ "--openid= " + buyer_email + "--payType= " + pay_Type);
			if ("SUCCESS".equals(trade_status)) {
				message = customerService.payBackTemp(order_id, trade_no,
						trade_status, buyer_email, PAY_TYPE, total_Fee,
						user_info);
			} else {
				message = "fail";
			}
		} else {
			message = "fail";
		}
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 代泊
	 */
	@RequestMapping("backpay_12")
	public void backpay_12(HttpServletRequest request,
						   HttpServletResponse response) {
		String message = null;
		// 解析结果存储在HashMap
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(inputStream);
		} catch (DocumentException e3) {
			e3.printStackTrace();
		}
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		logger.info(elementList.size()
				+ "---------------------==========------------------");
		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getStringValue());

		// 释放资源
		try {
			inputStream.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		inputStream = null;
		logger.info(map.size() + "ss");
		logger.info(map.toString());
		if (map.size() != 0) {
			// userinfo串
			String user_info = JacksonUtil.toJson(map);
			String order_id = map.get("out_trade_no");
			String trade_no = map.get("transaction_id");
			String trade_status = map.get("return_code");
			String buyer_email = map.get("openid");
			String total_fee = map.get("total_fee");
			String pay_Type = "01";
			logger.info("out_trade_no=" + order_id + "--transaction= "
					+ trade_no + "--return_code= " + trade_status
					+ "--openid= " + buyer_email + "--payType= " + pay_Type);
			if ("SUCCESS".equals(trade_status)) {
				message = customerService.postParkAfterPay(order_id, trade_no,
						trade_status, buyer_email, PAY_TYPE, total_fee,
						user_info);
			} else {
				message = "fail";
			}
		} else {
			message = "fail";
		}
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 月租
	 */
	@RequestMapping("backpay_13")
	public void backpay_13(HttpServletRequest request,
			HttpServletResponse response) {
		String message = null;
		// 解析结果存储在HashMap
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(inputStream);
		} catch (DocumentException e3) {
			e3.printStackTrace();
		}
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		logger.info(elementList.size()
				+ "---------------------==========------------------");
		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getStringValue());

		// 释放资源
		try {
			inputStream.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		inputStream = null;
		logger.info(map.size() + "ss");
		logger.info(map.toString());
		if (map.size() != 0) {
			// userinfo串
			String user_info = JacksonUtil.toJson(map);
			String order_id = map.get("out_trade_no");
			String trade_no = map.get("transaction_id");
			String trade_status = map.get("return_code");
			String buyer_email = map.get("openid");
			String total_fee = map.get("total_fee");
			String pay_Type = "01";
			logger.info("out_trade_no=" + order_id + "--transaction= "
					+ trade_no + "--return_code= " + trade_status
					+ "--openid= " + buyer_email + "--payType= " + pay_Type);
			if ("SUCCESS".equals(trade_status)) {
				message = customerService.postMonthlyAfterPay(order_id,
						trade_no, trade_status, buyer_email, PAY_TYPE,
						total_fee, user_info);
			} else {
				message = "fail";
			}
		} else {
			message = "fail";
		}
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 产权
	 */
	@RequestMapping("backpay_14")
	public void backpay_14(HttpServletRequest request,
			HttpServletResponse response) {
		String message = null;
		// 解析结果存储在HashMap
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(inputStream);
		} catch (DocumentException e3) {
			e3.printStackTrace();
		}
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		logger.info(elementList.size()
				+ "---------------------==========------------------");
		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getStringValue());

		// 释放资源
		try {
			inputStream.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		inputStream = null;
		logger.info(map.size() + "ss");
		logger.info(map.toString());
		if (map.size() != 0) {
			// userinfo串
			String user_info = JacksonUtil.toJson(map);
			String order_id = map.get("out_trade_no");
			String trade_no = map.get("transaction_id");
			String trade_status = map.get("return_code");
			String buyer_email = map.get("openid");
			String total_fee = map.get("total_fee");
			String pay_Type = "01";
			logger.info("out_trade_no=" + order_id + "--transaction= "
					+ trade_no + "--return_code= " + trade_status
					+ "--openid= " + buyer_email + "--payType= " + pay_Type);
			if ("SUCCESS".equals(trade_status)) {
				message = customerService.postEquityAfterPay(order_id,
						trade_no, trade_status, buyer_email, PAY_TYPE,
						total_fee, user_info);
			} else {
				message = "fail";
			}
		} else {
			message = "fail";
		}
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加油卡
	 */
	@RequestMapping("backpay_15")
	public void backpay_15(HttpServletRequest request,
			HttpServletResponse response) {
		String message = null;
		// 解析结果存储在HashMap
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(inputStream);
		} catch (DocumentException e3) {
			e3.printStackTrace();
		}
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		logger.info(elementList.size()
				+ "---------------------==========------------------");
		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getStringValue());

		// 释放资源
		try {
			inputStream.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		inputStream = null;
		logger.info(map.size() + "ss");
		logger.info(map.toString());
		if (map.size() != 0) {
			// userinfo串
			String user_info = JacksonUtil.toJson(map);
			String order_id = map.get("out_trade_no");
			String trade_no = map.get("transaction_id");
			String trade_status = map.get("return_code");
			String buyer_email = map.get("openid");
			String total_fee = map.get("total_fee");
			String pay_Type = "01";
			logger.info("out_trade_no=" + order_id + "--transaction= "
					+ trade_no + "--return_code= " + trade_status
					+ "--openid= " + buyer_email + "--payType= " + pay_Type);
			if ("SUCCESS".equals(trade_status)) {
				message = customerService.postCardAfterPay(order_id, trade_no,
						trade_status, buyer_email, PAY_TYPE, total_fee,
						user_info);
			} else {
				message = "fail";
			}
		} else {
			message = "fail";
		}
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 钱包充值
	 */
	@RequestMapping("backpay_16")
	public void backpay_16(HttpServletRequest request,
			HttpServletResponse response) {
		String message = null;
		// 解析结果存储在HashMap
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(inputStream);
		} catch (DocumentException e3) {
			e3.printStackTrace();
		}
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		logger.info(elementList.size()
				+ "---------------------==========------------------");
		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getStringValue());

		// 释放资源
		try {
			inputStream.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		inputStream = null;
		logger.info(map.size() + "ss");
		logger.info(map.toString());
		if (map.size() != 0) {
			// userinfo串
			String user_info = JacksonUtil.toJson(map);
			String order_id = map.get("out_trade_no");
			String trade_no = map.get("transaction_id");
			String trade_status = map.get("return_code");
			String buyer_email = map.get("openid");
			String total_fee = map.get("total_fee");
			String pay_Type = "01";
			logger.info("out_trade_no=" + order_id + "--transaction= "
					+ trade_no + "--return_code= " + trade_status
					+ "--openid= " + buyer_email + "--payType= " + pay_Type);
			if ("SUCCESS".equals(trade_status)) {
				message = customerService.postCardAfterPay(order_id, trade_no,
						trade_status, buyer_email, PAY_TYPE, total_fee,
						user_info);
			} else {
				message = "fail";
			}
		} else {
			message = "fail";
		}
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 洗车
	 */
	@RequestMapping("backpay_17")
	public void backpay_17(HttpServletRequest request,
						   HttpServletResponse response) {
		String message = null;
		// 解析结果存储在HashMap
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(inputStream);
		} catch (DocumentException e3) {
			e3.printStackTrace();
		}
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		logger.info(elementList.size()
				+ "---------------------==========------------------");
		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getStringValue());

		// 释放资源
		try {
			inputStream.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		inputStream = null;
		logger.info(map.size() + "ss");
		logger.info(map.toString());
		if (map.size() != 0) {
			// userinfo串
			String user_info = JacksonUtil.toJson(map);
			String order_id = map.get("out_trade_no");
			String trade_no = map.get("transaction_id");
			String trade_status = map.get("return_code");
			String buyer_email = map.get("openid");
			String total_fee = map.get("total_fee");
			String pay_Type = "01";
			logger.info("out_trade_no=" + order_id + "--transaction= "
					+ trade_no + "--return_code= " + trade_status
					+ "--openid= " + buyer_email + "--payType= " + pay_Type);
			if ("SUCCESS".equals(trade_status)) {
				message = customerService.postCarwsahAfterPay(order_id, trade_no,
						trade_status, buyer_email, PAY_TYPE, total_fee,
						user_info);
			} else {
				message = "fail";
			}
		} else {
			message = "fail";
		}
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
