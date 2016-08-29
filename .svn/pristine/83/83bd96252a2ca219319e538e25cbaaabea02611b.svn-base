package com.boxiang.share.app.myaccount.controller;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.product.car.po.Car;
import com.boxiang.share.product.car.service.CarService;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.utils.ShangAnMessageType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("app/myaccount")
public class MyaccountController extends BaseController {
	private static final Logger logger = Logger.getLogger(MyaccountController.class);
	@Resource
	private OrderMainService orderMainService;
	@Resource
	private CarService carService;
	@Resource
	private CustomerService customerService;

	@RequestMapping("orderlist/{customerId}/{orderKind}/{pageIndex}/{summary}")
	public void orderlist(@PathVariable String customerId,@PathVariable String pageIndex,@PathVariable String orderKind, HttpServletRequest request, HttpServletResponse response){
		String message=null;
		if(orderKind!=null&&!"".equals(orderKind)){
			if(orderKind.equals("1")){//临停
				message = orderMainService.getOrderTempList(customerId, pageIndex);
			}else if (orderKind.equals("2")) {//月租产权
				message = orderMainService.getOrderMoneqList(customerId, pageIndex);
			}else if (orderKind.equals("0")) {//代泊
				
			}
		}
		
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(message);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		}
	}
	@RequestMapping("orderdetail/{orderId}/{orderType}/{summary}")
	public void orderdetail(@PathVariable String orderId , @PathVariable String orderType,HttpServletRequest request,HttpServletResponse response){
		String message = null;
		OrderMain orderMain = new OrderMain();
		orderMain.setOrderId(orderId);
		orderMain.setOrderType(orderType);
		if(orderType!=null && orderType!=""){
			if("10".equals(orderType)){
				message = orderMainService.selectOrderDetailTS(orderMain);
			}else if("11".equals(orderType)){
				message = orderMainService.selectOrderDetailT(orderMain);
			}else if("12".equals(orderType)){		
			
			}else if("13".equals(orderType)){
				message = orderMainService.selectOrderDetailM(orderMain);
			}else if("14".equals(orderType)){
				message = orderMainService.selectOrderDetailE(orderMain);
			}
		}else{
			message = ShangAnMessageType.toShangAnJson("1", "没有订单类型", "order", "查询无数据");
		}
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(message);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		}
	}

	@RequestMapping("addCar")
	public void addCar(HttpServletRequest request,HttpServletResponse response){
		String message = null;

		String sCustomerId = request.getParameter("customer_id");
		String car_brand = request.getParameter("car_brand");
		String sCarNo = request.getParameter("car_number");
		//String sCarColor = request.getParameter("car_color");
		// String car_location = request.getParameter("car_location");
		//String car_size = request.getParameter("car_size");

		Car car = new Car();
		String carid= UUID.randomUUID().toString();
		car.setCarId(carid.substring(0, 8) + carid.substring(9, 13) + carid.substring(14, 18) + carid.substring(19, 23) + carid.substring(24));
		car.setCustomerId(sCustomerId);
		car.setCarBrand(car_brand);
		car.setCarNumber(sCarNo);
	//	car.setCarColor(1);//1黑2白3其他
	//	car.setCarSize(1);//1轿车2跑车3SUV4其他
	//	car.setOwnerIdNumber(owner_id_number);
		try {
			message = carService.insertCar(car);
		} catch (Exception e) {
			logger.error("", e);
			message =   ShangAnMessageType.toShangAnJson("1", "数据异常", "car", e.getMessage());
		}


		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
			out.print(message);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("",e);
		}
	}

	@RequestMapping("info/{customerId}/{version}/{summary}")
	public void info(@PathVariable String customerId ,@PathVariable String version ,HttpServletRequest request,HttpServletResponse response){
		String message = null;
		Map map=new HashMap();

		message=customerService.getInfo(customerId);
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
			out.print(message);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		}
	}
}
