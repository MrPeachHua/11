package com.boxiang.share.app.weixin;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.product.weixin.service.WeixinService;
import com.boxiang.share.utils.ShangAnMessageType;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("app/weixinPayment")
public class WeixinPaymentController extends BaseController {
	private static final Logger logger = Logger.getLogger(WeixinPaymentController.class);
	@Resource
	private WeixinService weixinService;
	/**
	 * 获取停车场list
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getDataByScanOrMenu")
	public void getDataByScanOrMenu(HttpServletRequest request, HttpServletResponse response,
									@RequestParam(value="parkingId",required = false) String parkingId,
									@RequestParam(value="customerId",required = false) String customerId,
									@RequestParam(value="carNumber",required = false) String carNumber,
									@RequestParam(value="orderType",required = false) String orderType
									) throws IOException {
		//设置utf-8
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		out = response.getWriter();
		if(StringUtils.isEmpty(customerId)) {
			out.print(ShangAnMessageType.operateToJson("2","用户Id不能为空"));
			return;
		}
		out.print(weixinService.getDataByScanOrMenu(carNumber,orderType,customerId));
	}

	/**
	 * 获取停车场list
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getOrderData")
	public void getOrderData(HttpServletRequest request, HttpServletResponse response,
									@RequestParam(value="parkingId",required = false) String parkingId,
									@RequestParam(value="customerId",required = false) String customerId,
									@RequestParam(value="carNumber",required = false) String carNumber,
									@RequestParam(value="orderType",required = false) String orderType
	) throws IOException {
		//设置utf-8
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		out = response.getWriter();
		if(StringUtils.isEmpty(customerId)||StringUtils.isEmpty(parkingId)||StringUtils.isEmpty(carNumber)) {
			out.print(ShangAnMessageType.operateToJson("2","参数不能为空"));
			return;
		}
		out.print(weixinService.getOrderData(parkingId,customerId,carNumber,orderType));
	}

}
