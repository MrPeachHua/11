package com.boxiang.share.app.order.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import com.boxiang.share.product.order.po.*;
import com.boxiang.share.product.parker.service.ParkerService;
import com.boxiang.share.product.parking.po.DiscountParkingPrice;
import com.boxiang.share.product.parking.po.PackagePrice;
import com.boxiang.share.product.parking.service.*;
import com.boxiang.share.utils.json.JsonMapper;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.payment.po.PaymentInfo;
import com.boxiang.share.payment.service.PaymentInfoService;
import com.boxiang.share.product.carlife.po.OrderRefuel;
import com.boxiang.share.product.carlife.service.OrderRefuelService;
import com.boxiang.share.product.coupon.po.Coupon;
import com.boxiang.share.product.coupon.service.CouponService;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.customer.service.RechargeRuleGiftAmountService;
import com.boxiang.share.product.order.service.InvoiceService;
import com.boxiang.share.product.order.service.MonthlyparkinginfoService;
import com.boxiang.share.product.order.service.OrderCarwashService;
import com.boxiang.share.product.order.service.OrderEquityService;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.product.order.service.OrderMonthlyService;
import com.boxiang.share.product.order.service.OrderRechargeService;
import com.boxiang.share.product.order.service.OrderTemporaryService;
import com.boxiang.share.product.order.service.OrderTemporaryShareService;
import com.boxiang.share.product.order.service.PropertyparkinginfoService;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.po.ParkingVoucher;
import com.boxiang.share.user.service.UserInfoService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.OrderConstants;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.json.JacksonUtil;

@Controller
@RequestMapping("app/order")
public class OrderController extends BaseController {
	private static final Logger logger = Logger.getLogger(OrderController.class);
	@Resource 
	private OrderMainService orderMainService; 
	@Resource
	private CouponService couponService;
	@Resource
	private PaymentInfoService paymentInfoService;
	@Resource
	private OrderTemporaryShareService orderTemporaryShareService;
	@Resource
	private ParkingService parkingService;
	@Resource
	private OrderMonthlyService orderMonthlyService;
	@Resource
	private MonthlyparkinginfoService monthlyparkinginfoService;
	@Resource
	private PropertyparkinginfoService propertyparkinginfoService;
	@Resource
	private OrderEquityService orderEquityService; 
	@Resource
	private CustomerService customerService;
	@Resource
	private VillageuserinfoService villageuserinfoService;
	@Resource
	private OrderRefuelService orderRefuelService;
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private OrderTemporaryService orderTemporaryService;
	@Resource
	private ParkingVoucherService parkingVoucherService;

	@Resource
	private OrderRechargeService orderRechargeService;
	@Resource
	private OrderCarwashService orderCarwashService;
	
	@Resource
	private InvoiceService invoiceService;
	@Resource
	private RechargeRuleGiftAmountService rechargeRuleGiftAmountService;

	@Resource
	private ParkerService parkerService;
	@Resource
	private PackagePriceService packagePriceService;
	@Resource
	private DiscountParkingPriceService discountParkingPriceService;

	private final static Hashtable<String, String> parkHt = new Hashtable<>();

	@RequestMapping("orderc")
	public void orderc(HttpServletRequest request, HttpServletResponse response) {
		String orderType = request.getParameter("orderType");
		String mess = null;
		Map<String,Object> param = new HashMap<String,Object>();
		//  代泊
		if (orderType.equals(OrderConstants.ORDER_TYPE_PARK)) {
			String customerId = request.getParameter("customerId");
			String parkingId = request.getParameter("parkingId");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String carNumber = request.getParameter("carNumber");
			String isContinue = request.getParameter("isContinue");
			String version = request.getParameter("version");
			try {
				synchronized (parkHt) {
					if (parkHt.containsKey(carNumber)) {
						response.getWriter().print(ShangAnMessageType.operateToJson("5", "该车牌号已经在代泊中"));
						return;
					} else {
						parkHt.put(carNumber, customerId);
					}
				}
				mess = parkerService.ordercPark(customerId, parkingId, startTime, endTime, carNumber, isContinue, version);
			} catch (ServiceException e) {
				logger.error("", e);
				mess = ShangAnMessageType.operateToJson("2", e.getMessage());
			} catch (Exception e) {
				logger.error("", e);
				mess = ShangAnMessageType.operateToJson("2", "异常");
			}
			parkHt.remove(carNumber);
		}
		//临停订单
		else if ("11".equals(orderType)){
			String parkingId = request.getParameter("parkingId");
			String customerId = request.getParameter("customerId");
			String carNumber = request.getParameter("carNumber");
			param.put("parkingId", parkingId);
			param.put("customerId", customerId);
			param.put("carNumber", carNumber);
			param.put("orderType", orderType);
			try {
				mess = orderMainService.ordercTemporary(param);
			} catch (Exception e) {
				logger.error("", e);
				mess = ShangAnMessageType.operateToJson("2", "异常");
			}
		}else if("10".equals(orderType)){//共享临停
			/*String parkingId = request.getParameter("parkingId");
			String customerId = request.getParameter("customerId");
			String carNumber = request.getParameter("carNumber");
			param.put("carNumber", carNumber);
			param.put("parkingId", parkingId);
			param.put("customerId", customerId);
			param.put("orderType", orderType);
			mess=orderMainService.ordercTemporaryShare(param);*/
			String parkingId = request.getParameter("parkingId");
			String customerId = request.getParameter("customerId");
			String carNumber = request.getParameter("carNumber");
			String packageId = request.getParameter("packageId");//套餐ID
			String appointmentDate = request.getParameter("appointmentDate");//预约时间
			/*String parkingNum = request.getParameter("parkingNum");//优惠停车次数
			String appointmentDate = request.getParameter("appointmentDate");//预约时间*/
			param.put("carNumber", carNumber);
			param.put("parkingId", parkingId);
			param.put("customerId", customerId);
			param.put("orderType", orderType);
			param.put("packageId", packageId);
			param.put("appointmentDate", appointmentDate);
			/*param.put("parkingNum",parkingNum);
			param.put("appointmentDate",appointmentDate);*/
			mess=orderMainService.ordercTemporaryShare(param);
			
		}else if("13".equals(orderType)){//月租
			
			String parkingId = request.getParameter("parkingId");
			String customerId = request.getParameter("customerId");
			String carNumber = request.getParameter("carNumber");
			String beginDate = request.getParameter("beginDate");
			String monthNum = request.getParameter("monthNum");
			String invoiceId = request.getParameter("invoiceId");
			param.put("parkingId", parkingId);
			param.put("customerId", customerId);
			param.put("orderType", orderType);
			param.put("carNumber", carNumber);
			param.put("beginDate", beginDate);
			param.put("monthNum", monthNum);
			//发票ID
			param.put("invoiceId", invoiceId);
			mess=orderMainService.ordercMonthly(param);
			 
		}else if("14".equals(orderType)) {//产权
			String parkingId = request.getParameter("parkingId");
			String customerId = request.getParameter("customerId");
			String carNumber = request.getParameter("carNumber");
			String beginDate = request.getParameter("beginDate");
			String monthNum = request.getParameter("monthNum");
			String invoiceId = request.getParameter("invoiceId");
			param.put("parkingId", parkingId);
			param.put("customerId", customerId);
			param.put("orderType", orderType);
			param.put("carNumber", carNumber);
			param.put("beginDate", beginDate);
			param.put("monthNum", monthNum);
			//发票ID
			param.put("invoiceId", invoiceId);
			mess = orderMainService.ordercEquity(param);

		} else if(orderType.equals("15")) { // 加油卡
			param.put("customerId", request.getParameter("customerId"));
			param.put("orderType", orderType);
			param.put("cardId", request.getParameter("cardId"));
			param.put("topupNum", request.getParameter("topupNum")); // 充值数量
			param.put("topupType", request.getParameter("topupType")); // 充值类型
			param.put("amountPayable", request.getParameter("amountPayable"));
			param.put("amountDiscount", request.getParameter("amountDiscount"));
			try {
				mess = orderMainService.ordercCarlife(param);
			} catch (NullPointerException e) {
				mess = ShangAnMessageType.toShangAnJson("2", "缺少必要参数", "order", "");
				logger.error("", e);
			} catch (NumberFormatException e) {
				mess = ShangAnMessageType.toShangAnJson("2", "参数类型不正确", "order", "");
				logger.error("", e);
			} catch (Exception e) {
				mess = ShangAnMessageType.toShangAnJson("2", "数据异常", "order", "");
				logger.error("", e);
			}

		} else if(orderType.equals("16")) { // 钱包充值
			try {
				String customerId = request.getParameter("customerId"); // 客户号
				String amountPayable = request.getParameter("amountPayable"); // 应付金额
				String useInfo = request.getParameter("useInfo"); // 请求支付信息
				String payType = request.getParameter("payType"); // 支付类型 00:支付宝，01:微信，02:银联
				String version= request.getParameter("version");
				mess = orderRechargeService.createRechargeOrder(customerId, amountPayable, useInfo, payType,version); // 创建钱包充值订单
			} catch (Exception e) {
				mess = ShangAnMessageType.toShangAnJson("2", "数据异常", "order", "");
				logger.error("", e);
			}
		}else if(orderType.equals("17")){//洗车
			try {
				String customerId = request.getParameter("customerId"); // 客户号
				String amountPayable = request.getParameter("amountPayable"); // 应付金额
				String parkingId = request.getParameter("parkingId"); //
				String carNumber = request.getParameter("carNumber"); //
				String carType = request.getParameter("carType"); //
				String reserveDate = request.getParameter("reserveDate"); // 预约日期
				param.put("parkingId", parkingId);
				param.put("customerId", customerId);
				param.put("orderType", orderType);
				param.put("carNumber", carNumber);
				param.put("amountPayable", amountPayable);
				param.put("reserveDate", reserveDate);
				param.put("carType", carType);
				mess=orderCarwashService.OrdercCarWash(param);
			} catch (Exception e) {
				mess = ShangAnMessageType.toShangAnJson("2", "数据异常", "order", "");
				logger.error("", e);
			}

		}
		else{
			mess = ShangAnMessageType.toShangAnJson("2", "订单类型不存在:"+orderType, "order", "");
		}
		
		
		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin","*");
		try {
			out = response.getWriter();
			out.print(mess);
		} catch (IOException e) {
			logger.error("", e);
		}
	
	}
	@RequestMapping("reqpay")
	public void reqPay(HttpServletRequest request, HttpServletResponse response){
		String orderId = request.getParameter("orderId");
		String couponId = request.getParameter("couponId");
		String useInfo = request.getParameter("useInfo");
		String orderType = request.getParameter("orderType");
		String payType = request.getParameter("payType");
		String price = request.getParameter("price");
		String version = request.getParameter("version");
		String message=null;

		if(payType.equals("00")&& StringUtils.isEmpty(version)){
			message = ShangAnMessageType.toShangAnJson("2", "支付宝异常，请更新最新版本或使用其它支付方式！", "order", "{}");
		}else {
			// 检查订单是否绑定过优惠券
			Coupon coupon = new Coupon();
			coupon.setCouponOrder(orderId);
			List<Coupon> couponList = couponService.queryList(coupon);
			// 解绑优惠券
			if (couponList != null && couponList.size() > 0) {
				for (Coupon item : couponList) {
					item.setCouponOrder("");
					item.setUseTime("");
					item.setCouponStatus("100201");
				}
				couponService.batchUpdate(couponList);
			}

			if ("13".equals(orderType)) {
				message = this.getMessageByMonthlyParking(orderId, couponId, useInfo, orderType, payType, price);
			} else if ("10".equals(orderType) || "11".equals(orderType)) {
				message = this.getMessageByShareParking(orderId, couponId, useInfo, orderType, payType);
			} else if ("14".equals(orderType)) {
				message = this.getMessageByEquityParking(orderId, couponId, useInfo, orderType, payType, price);
			} else if ("15".equals(orderType)) {
				message = this.getMessageByRefuleParking(orderId, useInfo, payType);
			} else if ("17".equals(orderType)) {
				message = this.getCarwsah(orderId, couponId, useInfo, orderType, payType);
			} else if ("12".equals(orderType)) {
				message = this.getCarwsah(orderId, couponId, useInfo, orderType, payType);
			} else {
				message = ShangAnMessageType.toShangAnJson("2", "该订单类型不存在", "order", "");
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
	/**
	 * 月租停车费  保存支付请求信息
	 * @param orderId
	 * @param couponId
	 * @param useInfo
	 * @param orderType
	 * @param payType
	 * @return
	 */
	private String getMessageByMonthlyParking(String orderId,String couponId,String useInfo,String orderType,String payType,String price){
		String message = null;
		try {
			int inPrice = 0;
			if(!StringUtils.isEmpty(price)){
				inPrice = new BigDecimal(price).intValue();
			}
			//使用优惠券时
			if(couponId!=null && couponId!="" && couponId.trim().length()>0){
				Coupon coupon = couponService.queryById(couponId);
				//优惠券可用时
				if(coupon!=null && "100201".equals(coupon.getCouponStatus())){
					coupon.setCouponOrder(orderId);
					coupon.setUseTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					coupon.setCouponStatus("100202");
					couponService.update(coupon);
					OrderMain orderMain = orderMainService.queryById(orderId);
					if(orderMain!=null){
						int dCouponAmount =0;
						String iCouponType = coupon.getCouponType();//折扣类型
						if ("1".equals(iCouponType)) {//使用定额券
							dCouponAmount = coupon.getParAmount();
						} else if("2".equals(iCouponType)){//使用折扣券
							int dMaxDiscount = coupon.getMaxdiscount();//折扣上限
							Integer iParDiscount = coupon.getParDiscount();//折扣
							int dDiscountAmount = orderMain.getAmountPayable()*(10-iParDiscount)/10;//优惠金额
							if(dMaxDiscount <= dDiscountAmount){//优惠金额超过折扣上限
								dCouponAmount = dMaxDiscount;
							}else {
								dCouponAmount = dDiscountAmount;
							}
						}
						OrderMonthly orderMonthly = new OrderMonthly();
						orderMonthly.setOrderId(orderId);
						List<OrderMonthly> orderMonthlyList = orderMonthlyService.selectList(orderMonthly);
						if(orderMonthlyList!=null && orderMonthlyList.size()>0){
							OrderMonthly getOrderMonthly = orderMonthlyList.get(0);

							// 检查缴费日期
							Monthlyparkinginfo entity = new Monthlyparkinginfo();
							entity.setCarNumber(getOrderMonthly.getCarNumber());
							entity.setVillageId(getOrderMonthly.getParkingId());
							entity = monthlyparkinginfoService.selectList(entity).get(0);
							String beginDate = DateUtil.date2str(getOrderMonthly.getBeginDate(), DateUtil.DATE_FORMAT);
							String beginDate1 = DateUtil.date2str(DateUtil.getPreOrNextDate(entity.getEffect_end_time(), 1), DateUtil.DATE_FORMAT);
							if (!beginDate.equals(beginDate1)) {
								return ShangAnMessageType.operateToJson("1", "请检查您的缴费日期");
							}

							getOrderMonthly.setPrice(inPrice*100);
							orderMonthlyService.update(getOrderMonthly);
							orderMain.setAmountPayable((inPrice*getOrderMonthly.getMonthNum())*100);
						}
						orderMain.setOrderType(orderType);
						orderMain.setAmountDiscount(dCouponAmount*100);//优惠券金额
						Integer paid = (inPrice*orderMonthlyList.get(0).getMonthNum()-dCouponAmount)*100;
						orderMain.setAmountPaid(paid < 0 ? 0 : paid);
						orderMain.setPayType(payType);
						orderMainService.update(orderMain);
					}
					PaymentInfo paymentInfo = new PaymentInfo();
					paymentInfo.setOrderId(orderId);
					paymentInfo.setPayType(payType);
					paymentInfo.setUseInfo(useInfo);
					paymentInfo.setUseType("1");
					paymentInfo.setCreateor("admin");
					paymentInfo.setCreateDate(new Date());
					paymentInfoService.add(paymentInfo);
					message = ShangAnMessageType.operateToJson("0", "绑定成功");
				}else{//优惠券不可用时
					message = ShangAnMessageType.operateToJson("3", "优惠券不可用");
				}
				
			}else{//未使用优惠券时
				OrderMain orderMain = orderMainService.queryById(orderId);
				if(orderMain !=null){
					OrderMonthly orderMonthly = new OrderMonthly();
					orderMonthly.setOrderId(orderId);
					List<OrderMonthly> orderMonthlyList = orderMonthlyService.selectList(orderMonthly);
					if(orderMonthlyList!=null && orderMonthlyList.size()>0){
						OrderMonthly getOrderMonthly = orderMonthlyList.get(0);

						// 检查缴费日期
						Monthlyparkinginfo entity = new Monthlyparkinginfo();
						entity.setCarNumber(getOrderMonthly.getCarNumber());
						entity.setVillageId(getOrderMonthly.getParkingId());
						entity = monthlyparkinginfoService.selectList(entity).get(0);
						String beginDate = DateUtil.date2str(getOrderMonthly.getBeginDate(), DateUtil.DATE_FORMAT);
						String beginDate1 = DateUtil.date2str(DateUtil.getPreOrNextDate(entity.getEffect_end_time(), 1), DateUtil.DATE_FORMAT);
						if (!beginDate.equals(beginDate1)) {
							return ShangAnMessageType.operateToJson("1", "请检查您的缴费日期");
						}

						getOrderMonthly.setPrice(inPrice*100);
						orderMonthlyService.update(getOrderMonthly);
						orderMain.setAmountPayable(inPrice*getOrderMonthly.getMonthNum()*100);
						orderMain.setAmountPaid(inPrice*getOrderMonthly.getMonthNum()*100);
					}
					orderMain.setOrderType(orderType);
					orderMain.setAmountDiscount(0);//优惠券金额
					orderMain.setPayType(payType);
					orderMainService.update(orderMain);
				}
				PaymentInfo paymentInfo = new PaymentInfo();
				paymentInfo.setOrderId(orderId);
				paymentInfo.setPayType(payType);
				paymentInfo.setUseInfo(useInfo);
				paymentInfo.setUseType("1");
				paymentInfo.setCreateor("admin");
				paymentInfo.setCreateDate(new Date());
				paymentInfoService.add(paymentInfo);
				message = ShangAnMessageType.operateToJson("0", "未使用优惠券");
			}
			
		} catch (Exception e) {
			message = ShangAnMessageType.operateToJson("2", "数据异常");
			e.printStackTrace();
			logger.error("",e);
		}
		return message;
	} 
	/**
	 * 产权
	 * @param orderId
	 * @param couponId
	 * @param useInfo
	 * @param orderType
	 * @param payType
	 * @param price
	 * @return
	 */
	private String getMessageByEquityParking(String orderId,String couponId,String useInfo,String orderType,String payType,String price){
		String message = null;
		try {
			int inPrice = 0;
			if(!StringUtils.isEmpty(price)){
				inPrice = new BigDecimal(price).intValue();
			}
			//使用优惠券时
			if(couponId!=null && couponId!="" && couponId.trim().length()>0){
				Coupon coupon = couponService.queryById(couponId);
				//优惠券可用时
				if(coupon!=null && "100201".equals(coupon.getCouponStatus())){
					coupon.setCouponOrder(orderId);
					coupon.setUseTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					coupon.setCouponStatus("100202");
					couponService.update(coupon);
					OrderMain orderMain = orderMainService.queryById(orderId);
					if(orderMain!=null){
						int dCouponAmount =0;
						String iCouponType = coupon.getCouponType();//折扣类型
						if ("1".equals(iCouponType)) {//使用定额券
							dCouponAmount = coupon.getParAmount();
						} else if("2".equals(iCouponType)){//使用折扣券
							int dMaxDiscount = coupon.getMaxdiscount();//折扣上限
							Integer iParDiscount = coupon.getParDiscount();//折扣
							int dDiscountAmount = orderMain.getAmountPayable()*(10-iParDiscount)/10;//优惠金额
							if(dMaxDiscount <= dDiscountAmount){//优惠金额超过折扣上限
								dCouponAmount = dMaxDiscount;
							}else {
								dCouponAmount = dDiscountAmount;
							}
						}
						OrderEquity orderEquity = new OrderEquity();
						orderEquity.setOrderId(orderId);
						List<OrderEquity> orderEquityList = orderEquityService.selectList(orderEquity);
						if(orderEquityList!=null && orderEquityList.size()>0){
							OrderEquity getOrderEquity = orderEquityList.get(0);

							// 检查缴费日期
							Propertyparkinginfo entity = new Propertyparkinginfo();
							entity.setCarNumber(getOrderEquity.getCarNumber());
							entity.setVillageId(getOrderEquity.getParkingId());
							entity = propertyparkinginfoService.selectList(entity).get(0);
							String beginDate = DateUtil.date2str(getOrderEquity.getBeginDate(), DateUtil.DATE_FORMAT);
							String beginDate1 = DateUtil.date2str(DateUtil.getPreOrNextDate(entity.getEffect_end_time(), 1), DateUtil.DATE_FORMAT);
							if (!beginDate.equals(beginDate1)) {
								return ShangAnMessageType.operateToJson("1", "请检查您的缴费日期");
							}

							orderEquity.setPrice(inPrice*100);
							orderEquityService.update(getOrderEquity);
							orderMain.setAmountPayable((inPrice*getOrderEquity.getMonthNum())*100);
						}
						orderMain.setOrderType(orderType);
						orderMain.setAmountDiscount(dCouponAmount*100);//优惠券金额
						Integer paid = (inPrice*orderEquityList.get(0).getMonthNum()-dCouponAmount)*100;
						orderMain.setAmountPaid(paid < 0 ? 0 : paid);
						orderMain.setPayType(payType);
						orderMainService.update(orderMain);
					}
					PaymentInfo paymentInfo = new PaymentInfo();
					paymentInfo.setOrderId(orderId);
					paymentInfo.setPayType(payType);
					paymentInfo.setUseInfo(useInfo);
					paymentInfo.setUseType("1");
					paymentInfo.setCreateor("admin");
					paymentInfo.setCreateDate(new Date());
					paymentInfoService.add(paymentInfo);
					message = ShangAnMessageType.operateToJson("0", "绑定成功");
				}else{//优惠券不可用时
					message = ShangAnMessageType.operateToJson("3", "优惠券不可用");
				}
				
			}else{//未使用优惠券时
				OrderMain orderMain = orderMainService.queryById(orderId);
				if(orderMain !=null){
					OrderEquity orderEquity = new OrderEquity();
					orderEquity.setOrderId(orderId);
					List<OrderEquity> orderEquityList = orderEquityService.selectList(orderEquity);
					if(orderEquityList!=null && orderEquityList.size()>0){
						OrderEquity getOrderEquity = orderEquityList.get(0);

						// 检查缴费日期
						Propertyparkinginfo entity = new Propertyparkinginfo();
						entity.setCarNumber(getOrderEquity.getCarNumber());
						entity.setVillageId(getOrderEquity.getParkingId());
						entity = propertyparkinginfoService.selectList(entity).get(0);
						String beginDate = DateUtil.date2str(getOrderEquity.getBeginDate(), DateUtil.DATE_FORMAT);
						String beginDate1 = DateUtil.date2str(DateUtil.getPreOrNextDate(entity.getEffect_end_time(), 1), DateUtil.DATE_FORMAT);
						if (!beginDate.equals(beginDate1)) {
							return ShangAnMessageType.operateToJson("1", "请检查您的缴费日期");
						}

						orderEquity.setPrice(inPrice*100);
						orderEquityService.update(getOrderEquity);
						orderMain.setAmountPayable(inPrice*getOrderEquity.getMonthNum()*100);
						orderMain.setAmountPaid(inPrice*getOrderEquity.getMonthNum()*100);
					}
					orderMain.setOrderType(orderType);
					orderMain.setAmountDiscount(0);//优惠券金额
					orderMain.setPayType(payType);
					orderMainService.update(orderMain);
				}
				PaymentInfo paymentInfo = new PaymentInfo();
				paymentInfo.setOrderId(orderId);
				paymentInfo.setPayType(payType);
				paymentInfo.setUseInfo(useInfo);
				paymentInfo.setUseType("1");
				paymentInfo.setCreateor("admin");
				paymentInfo.setCreateDate(new Date());
				paymentInfoService.add(paymentInfo);
				message = ShangAnMessageType.operateToJson("0", "未使用优惠券");
			}
			
		} catch (Exception e) {
			message = ShangAnMessageType.operateToJson("2", "数据异常");
			e.printStackTrace();
			logger.error("",e);
		}
		return message;
	}
	/**
	 * 共享临停  保存支付请求信息
	 * @param orderId
	 * @param couponId
	 * @param useInfo
	 * @param orderType
	 * @param payType
	 * @return
	 */
	private String getMessageByShareParking(String orderId,String couponId,String useInfo,String orderType,String payType){
		String message = null;
		try {
			//使用优惠券时
			if(couponId!=null && couponId!="" && couponId.trim().length()>0){
				Coupon coupon = couponService.queryById(couponId);
				//优惠券可用时
				if(coupon!=null && "100201".equals(coupon.getCouponStatus())){
					coupon.setCouponOrder(orderId);
					coupon.setUseTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					coupon.setCouponStatus("100202");
					couponService.update(coupon);
					OrderMain orderMain = orderMainService.queryById(orderId);
					if(orderMain!=null){
						int dCouponAmount =0;
						String iCouponType = coupon.getCouponType();//折扣类型
						if ("1".equals(iCouponType)) {//使用定额券
							dCouponAmount = coupon.getParAmount();
						} else if("2".equals(iCouponType)){//使用折扣券
							int dMaxDiscount = coupon.getMaxdiscount();//折扣上限
							Integer iParDiscount = coupon.getParDiscount();//折扣
							int dDiscountAmount = orderMain.getAmountPayable()*(10-iParDiscount)/10;//优惠金额
							if(dMaxDiscount <= dDiscountAmount){//优惠金额超过折扣上限
								dCouponAmount = dMaxDiscount;
							}else {
								dCouponAmount = dDiscountAmount;
							}
						}
						orderMain.setOrderType(orderType);
						orderMain.setAmountDiscount(dCouponAmount*100);//优惠券金额
						Integer paid = orderMain.getAmountPayable()-dCouponAmount*100;
						orderMain.setAmountPaid(paid < 0 ? 0 : paid);
						orderMain.setPayType(payType);
						orderMainService.update(orderMain);
					}
					PaymentInfo paymentInfo = new PaymentInfo();
					paymentInfo.setOrderId(orderId);
					paymentInfo.setPayType(payType);
					paymentInfo.setUseInfo(useInfo);
					paymentInfo.setUseType("1");
					paymentInfo.setCreateor("admin");
					paymentInfo.setCreateDate(new Date());
					paymentInfoService.add(paymentInfo);
					message = ShangAnMessageType.operateToJson("0", "绑定成功");
				}else{//优惠券不可用时
					message = ShangAnMessageType.operateToJson("3", "优惠券不可用");
				}

			}else{//未使用优惠券时
				OrderMain orderMain = orderMainService.queryById(orderId);
				if(orderMain!=null){
					orderMain.setPayType(payType);
					orderMainService.update(orderMain);
				}
				PaymentInfo paymentInfo = new PaymentInfo();
				paymentInfo.setOrderId(orderId);
				paymentInfo.setPayType(payType);
				paymentInfo.setUseInfo(useInfo);
				paymentInfo.setUseType("1");
				paymentInfo.setCreateor("admin");
				paymentInfo.setCreateDate(new Date());
				paymentInfoService.add(paymentInfo);
				message = ShangAnMessageType.operateToJson("0", "未使用优惠券");
			}

		} catch (Exception e) {
			message = ShangAnMessageType.operateToJson("2", "数据异常");
			e.printStackTrace();
			logger.error("",e);
		}
		return message;
	}
	/**
	 * 洗车  保存支付请求信息
	 * @param orderId
	 * @param couponId
	 * @param useInfo
	 * @param orderType
	 * @param payType
	 * @return
	 */
	private String getCarwsah(String orderId,String couponId,String useInfo,String orderType,String payType){
		String message = null;
		try {
			//使用优惠券时
			if(couponId!=null && couponId!="" && couponId.trim().length()>0){
				Coupon coupon = couponService.queryById(couponId);
				//优惠券可用时
				if(coupon!=null && "100201".equals(coupon.getCouponStatus())){
					coupon.setCouponOrder(orderId);
					coupon.setUseTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					coupon.setCouponStatus("100202");
					couponService.update(coupon);
					OrderMain orderMain = orderMainService.queryById(orderId);
					if(orderMain!=null){
						int dCouponAmount =0;
							String iCouponType = coupon.getCouponType();//折扣类型
							if ("1".equals(iCouponType)) {//使用定额券
								dCouponAmount = coupon.getParAmount();
							} else if("2".equals(iCouponType)){//使用折扣券
								int dMaxDiscount = coupon.getMaxdiscount();//折扣上限
								Integer iParDiscount = coupon.getParDiscount();//折扣
								int dDiscountAmount = orderMain.getAmountPayable()*(10-iParDiscount)/10;//优惠金额
								if(dMaxDiscount <= dDiscountAmount){//优惠金额超过折扣上限
									dCouponAmount = dMaxDiscount;
								}else {
									dCouponAmount = dDiscountAmount;
								}
							}
						orderMain.setOrderType(orderType);
						orderMain.setAmountDiscount(dCouponAmount*100);//优惠券金额
						Integer paid = orderMain.getAmountPayable()-dCouponAmount*100;
						orderMain.setAmountPaid(paid < 0 ? 0 : paid);
						orderMain.setPayType(payType);
						orderMainService.update(orderMain);
					}
					PaymentInfo paymentInfo = new PaymentInfo();
					paymentInfo.setOrderId(orderId);
					paymentInfo.setPayType(payType);
					paymentInfo.setUseInfo(useInfo);
					paymentInfo.setUseType("1");
					paymentInfo.setCreateor("admin");
					paymentInfo.setCreateDate(new Date());
					paymentInfoService.add(paymentInfo);
					message = ShangAnMessageType.operateToJson("0", "绑定成功");
				}else{//优惠券不可用时
					message = ShangAnMessageType.operateToJson("3", "优惠券不可用");
				}
				
			}else{//未使用优惠券时
				OrderMain orderMain = orderMainService.queryById(orderId);
				if(orderMain!=null){
					orderMain.setPayType(payType);
					orderMainService.update(orderMain);
				}
				PaymentInfo paymentInfo = new PaymentInfo();
				paymentInfo.setOrderId(orderId);
				paymentInfo.setPayType(payType);
				paymentInfo.setUseInfo(useInfo);
				paymentInfo.setUseType("1");
				paymentInfo.setCreateor("admin");
				paymentInfo.setCreateDate(new Date());
				paymentInfoService.add(paymentInfo);
				message = ShangAnMessageType.operateToJson("0", "未使用优惠券");
			}
			
		} catch (Exception e) {
			message = ShangAnMessageType.operateToJson("2", "数据异常");
			e.printStackTrace();
			logger.error("",e);
		}
		return message;
	}

	/**
	 * 加油卡保存支付请求
	 * @param orderId
	 * @param useInfo
	 * @param payType
	 * @return
	 */
	private String getMessageByRefuleParking(String orderId, String useInfo, String payType){
		String message = null;
		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo.setOrderId(orderId);
		paymentInfo.setPayType(payType);
		paymentInfo.setUseInfo(useInfo);
		paymentInfo.setUseType("1");
		paymentInfo.setCreateor("admin");
		paymentInfo.setCreateDate(new Date());
		try{		
			paymentInfoService.add(paymentInfo);
			message = ShangAnMessageType.operateToJson("0", "保存支付记录");
		} catch (Exception e) {
			message = ShangAnMessageType.operateToJson("2", "数据异常");
			logger.error("",e);
		}
		return message;
	}
	@RequestMapping(value = "paidOrder/{orderId}/{orderType}/{appointmentDate}/{parkingNum}/{summary}",method =RequestMethod.GET )
	public  void  paidOrder(@PathVariable String orderId,@PathVariable String orderType,@PathVariable String appointmentDate,@PathVariable String parkingNum,
							HttpServletRequest request,HttpServletResponse response){
		PrintWriter out = null;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		}
		if(StringUtils.isEmpty(orderId)||StringUtils.isEmpty(orderType)){
			logger.warn("订单ID和订单类型不能为空");
			out.print(ShangAnMessageType.operateToJson("2", "订单ID和订单类型不能为空"));
			return;
		}
		String message = null;
		try {
			if ("10".equals(orderType)) {
				message = this.getPaidMessageByTemporaryShare1(orderId, orderType,appointmentDate,parkingNum);
			}else {
				message = ShangAnMessageType.toShangAnJson("2", "该订单类型不存在", "order", "");
			}
		} catch (Exception e) {
			logger.error("", e);
			message = ShangAnMessageType.operateToJson("2", "异常");
		}

		out.print(message);

	}
	@RequestMapping(value = "paidOrder", method = RequestMethod.POST)
	public  void  paidOrder(HttpServletRequest request,HttpServletResponse response){
		///{orderId}/{orderType}/{appointmentDate}/{summary}
		String orderId=request.getParameter("orderId");
		String orderType=request.getParameter("orderType");
		String appointmentDate=request.getParameter("appointmentDate");
		String packageId=request.getParameter("packageId");
		PrintWriter out = null;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		}
		if(StringUtils.isEmpty(orderId)||StringUtils.isEmpty(orderType)){
			logger.warn("订单ID和订单类型不能为空");
			out.print(ShangAnMessageType.operateToJson("2", "订单ID和订单类型不能为空"));
			return;
		}
		String message = null;
		try {
			if ("10".equals(orderType)) {
				message = this.getPaidMessageByTemporaryShare2(orderId, orderType, appointmentDate, packageId);
			}else {
				message = ShangAnMessageType.toShangAnJson("2", "该订单类型不存在", "order", "");
			}
		} catch (Exception e) {
			logger.error("", e);
			message = ShangAnMessageType.operateToJson("2", "异常");
		}

		out.print(message);

	}
	@RequestMapping("paid/{orderId}/{orderType}/{summary}")
	public void paid(@PathVariable String orderId,@PathVariable String orderType,
			HttpServletRequest request,HttpServletResponse response){
		PrintWriter out = null;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();			
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		}
		if(StringUtils.isEmpty(orderId)||StringUtils.isEmpty(orderType)){
			logger.warn("订单ID和订单类型不能为空");
			out.print(ShangAnMessageType.operateToJson("2", "订单ID和订单类型不能为空"));
			return;
		}
		String message = null;
		try {
			if ("11".equals(orderType)) {
				message = this.getPaidMessageByTemporary(orderId, orderType);
			} else if ("12".equals(orderType)) {
				message = ShangAnMessageType.toShangAnJson("0", "success", "data", "已经在回调中处理过结果");
			}else if ("13".equals(orderType)) {
				message = this.getPaidMessageByMonthlyParking(orderId, orderType);
			} else if ("14".equals(orderType)) {
				message = this.getPaidMessageByMonthlyParking(orderId, orderType);
			} else if ("10".equals(orderType)) {
				message = this.getPaidMessageByTemporaryShare(orderId, orderType);
			} else if ("15".equals(orderType)) {
				message = this.getPaidMessageByCard(orderId, orderType);
			} else if ("16".equals(orderType)) {
				message = orderRechargeService.getPaidMessageByRecharge(orderId, orderType);
			}else if("17".equals(orderType)){
				message=this.getPaidMessageByCarwash(orderId, orderType);
			} else {
				message = ShangAnMessageType.toShangAnJson("2", "该订单类型不存在", "order", "");
			}
		} catch (Exception e) {
			logger.error("", e);
			message = ShangAnMessageType.operateToJson("2", "异常");
		}
		
		out.print(message);
	}
	/**
	 * 临停缴费  修改订单
	 * @param orderId
	 * @param orderType
	 * @return
	 */
	private String getPaidMessageByTemporary(String orderId ,String orderType){
		String message = null;
		OrderMain orderMain1 = new OrderMain();
		orderMain1.setOrderId(orderId);
		orderMain1.setOrderType(orderType);
		OrderMain orderMain = orderMainService.queryByIdAndType(orderMain1);
		if(orderMain !=null){
			if (orderMain.getPayTime() == null) {
				orderMain.setPayTime(new Date());
			}
			orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_PAID);
			orderMainService.update(orderMain);
			message = ShangAnMessageType.operateToJson("0", "修改成功");
			try {
				//修改凭证状态
				//查询从表记录
				OrderTemporary oParam = new OrderTemporary();
				oParam.setOrderId(orderMain.getOrderId());
				List<OrderTemporary> otList = orderTemporaryService.selectList(oParam);
				if(null!=otList&&otList.size()>0){
					ParkingVoucher parkingVoucher = new ParkingVoucher();
					parkingVoucher.setCustomerId(orderMain.getCustomerId());
					parkingVoucher.setCarNumber(otList.get(0).getCarNumber());
					parkingVoucher.setPvStatus("0");
					parkingVoucher.setParkingId(otList.get(0).getParkingId());
					List<ParkingVoucher> pvList = parkingVoucherService.selectList(parkingVoucher);
					if(null!=pvList&&pvList.size()>0){
						for(ParkingVoucher p:pvList){
							p.setPvStatus("1");
							parkingVoucherService.update(p);
						}
					}
					//OrderMian
					OrderMain oPm = new OrderMain();
					oPm.setOrderId(orderId);
					oPm.setOrderType(orderType);
					message = orderMainService.selectOrderDetailT(oPm);
				}else {
					message = ShangAnMessageType.operateToJson("2", "无数据");
				}

			} catch (Exception e) {
				logger.error("",e);
				message = ShangAnMessageType.operateToJson("2", "凭证修改错误");
			}
			
			
		}else{
			message = ShangAnMessageType.operateToJson("1", "修改失败，不存在该订单");
		}
		
		return message;
	}

	/**
	 * 共享临停 修改订单
	 * @param orderId
	 * @param orderType
	 * @return
	 */
	private String getPaidMessageByTemporaryShare(String orderId ,String orderType){
		String message = null;
		OrderMain orderMain1 = new OrderMain();
		orderMain1.setOrderId(orderId);
		orderMain1.setOrderType(orderType);
		OrderMain orderMain = orderMainService.queryByIdAndType(orderMain1);
		if(orderMain !=null){
			if (orderMain.getPayTime() == null) {
				orderMain.setPayTime(new Date());
			}
			orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_PAID);
			orderMainService.update(orderMain);
			OrderTemporaryShare ots = new OrderTemporaryShare();
			ots.setOrderId(orderId);
			StringBuilder parkingCode = new StringBuilder();
			Random rd = new Random();
			for(int i =0 ;i<6 ; i++){
				parkingCode.append(rd.nextInt(10));
			}
			List<OrderTemporaryShare> orderTemporaryShareList = orderTemporaryShareService.selectList(ots);
			if(orderTemporaryShareList !=null && orderTemporaryShareList.size()>0){
				for(OrderTemporaryShare o : orderTemporaryShareList){
					o.setParkingCode(parkingCode.toString());
					orderTemporaryShareService.update(o);
					Parking parking = new Parking();
					parking.setParkingId(o.getParkingId());
					List<Parking> parkingList = parkingService.selectList(parking);
					if(parkingList!=null && parkingList.size()>0){
						Parking p = parkingList.get(0);
						if(p.getParkingCanUse()>0){
							p.setParkingCanUse(p.getParkingCanUse()-1);
							parkingService.update(p);
						}
					}
				}
			}
			Map<String,String> map = new HashMap<>();
			map.put("orderId",orderId);
//			OrderTemporaryShare orderTemporaryShare = orderTemporaryShareService.selectByOrderId(map);
			message = ShangAnMessageType.toShangAnJson("0", "修改成功", "parkingCode", parkingCode);
//			Map<String,Object> orderMap = new HashMap<String,Object>();
//			orderMap.put("errorNum","0");
//			orderMap.put("errorInfo","修改成功");
//			orderMap.put("parkingCode",parkingCode.toString());
//			orderMap.put("orderId",orderTemporaryShare.getOrderId());
//			orderMap.put("parkingNum",orderTemporaryShare.getParkingNum());
//			orderMap.put("carNumber",orderTemporaryShare.getCarNumber());
//			orderMap.put("payType",orderTemporaryShare.getPayType());
//			orderMap.put("payTime",(null!=orderTemporaryShare.getPayTime())?DateUtil.date2str(orderTemporaryShare.getPayTime(), DateUtil.DATETIME_FORMAT):"");
//			orderMap.put("amountPayable",(null!=orderTemporaryShare.getAmountPayable())?orderTemporaryShare.getAmountPayable()/100:0);
//			orderMap.put("amountDiscount",(null!=orderTemporaryShare.getAmountDiscount()?orderTemporaryShare.getAmountDiscount()/100:0));
//			orderMap.put("amountPaid",(null!=orderTemporaryShare.getAmountPaid())?orderTemporaryShare.getAmountPaid()/100:0);
//			message = JacksonUtil.toJson(orderMap);
		}else{
			message = ShangAnMessageType.operateToJson("1", "修改失败，不存在该订单");
		}
		return message;
	}
	private String getPaidMessageByTemporaryShare1(String orderId ,String orderType,String appointmentDate,String parkingNum){
		String message = null;
		OrderMain orderMain1 = new OrderMain();
		orderMain1.setOrderId(orderId);
		orderMain1.setOrderType(orderType);
		OrderMain orderMain = orderMainService.queryByIdAndType(orderMain1);
		if(orderMain !=null){
			orderMain.setPayTime(new Date());
			orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_PAID);
			orderMainService.update(orderMain);
			OrderTemporaryShare ots = new OrderTemporaryShare();
			ots.setOrderId(orderId);
			StringBuilder parkingCode = new StringBuilder();
			Random rd = new Random();
			for(int i =0 ;i<6 ; i++){
				parkingCode.append(rd.nextInt(10));
			}
			List<OrderTemporaryShare> orderTemporaryShareList = orderTemporaryShareService.selectList(ots);
			if(orderTemporaryShareList !=null && orderTemporaryShareList.size()>0){
				for(OrderTemporaryShare o : orderTemporaryShareList){
					o.setParkingCode(parkingCode.toString());
					o.setVoucherStatus("0");
					o.setAppointmentDate(appointmentDate);
					o.setParkingNum(parkingNum);
					orderTemporaryShareService.update(o);
					Parking parking = new Parking();
					parking.setParkingId(o.getParkingId());
					List<Parking> parkingList = parkingService.selectList(parking);
					if(parkingList!=null && parkingList.size()>0){
						Parking p = parkingList.get(0);
						if(p.getParkingCanUse()>0){
							p.setParkingCanUse(p.getParkingCanUse()-1);
							parkingService.update(p);
						}
					}
				}
			}
			Map<String,String> map = new HashMap<>();
			map.put("orderId",orderId);
			OrderTemporaryShare orderTemporaryShare = orderTemporaryShareService.selectByOrderId(map);

//			message = ShangAnMessageType.toShangAnJson("0", "修改成功", "parkingCode", parkingCode);
			Map<String,Object> orderMap = new HashMap<String,Object>();
			orderMap.put("parkingCode",parkingCode.toString());
			orderMap.put("orderId",orderTemporaryShare.getOrderId());
			orderMap.put("parkingNum",orderTemporaryShare.getParkingNum());
			orderMap.put("carNumber",orderTemporaryShare.getCarNumber());
			orderMap.put("payType",orderTemporaryShare.getPayType());
			orderMap.put("payTime",(null!=orderTemporaryShare.getPayTime())?DateUtil.date2str(orderTemporaryShare.getPayTime(), DateUtil.DATETIME_FORMAT):"");
			orderMap.put("amountPayable",(null!=orderTemporaryShare.getAmountPayable())?orderTemporaryShare.getAmountPayable()/100:0);
			orderMap.put("amountDiscount",(null!=orderTemporaryShare.getAmountDiscount()?orderTemporaryShare.getAmountDiscount()/100:0));
			orderMap.put("amountPaid",(null!=orderTemporaryShare.getAmountPaid())?orderTemporaryShare.getAmountPaid()/100:0);
			orderMap.put("parkingCode",orderTemporaryShare.getParkingCode());
			orderMap.put("appointmentDate",orderTemporaryShare.getAppointmentDate());
			orderMap.put("voucherStatus",orderTemporaryShare.getVoucherStatus());
			orderMap.put("parkingName",orderTemporaryShare.getParkingName());
			orderMap.put("startTime",orderTemporaryShare.getStartTime());
			orderMap.put("stopTime",orderTemporaryShare.getStopTime());
			orderMap.put("parkingId",orderTemporaryShare.getParkingId());
			message = ShangAnMessageType.toShangAnJson("0","修改成功","orderMap",orderMap);
		}else{
			message = ShangAnMessageType.operateToJson("1", "修改失败，不存在该订单");
		}
		return message;
	}
	private String getPaidMessageByTemporaryShare2(String orderId ,String orderType,String appointmentDate,String packageId){
		String message = null;
		OrderMain orderMain1 = new OrderMain();
		orderMain1.setOrderId(orderId);
		orderMain1.setOrderType(orderType);
		OrderMain orderMain = orderMainService.queryByIdAndType(orderMain1);
		if(orderMain !=null){
			orderMain.setPayTime(new Date());
			orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_PAID);
			orderMainService.update(orderMain);
			OrderTemporaryShare ots = new OrderTemporaryShare();
			ots.setOrderId(orderId);
			StringBuilder parkingCode = new StringBuilder();
			Random rd = new Random();
			for(int i =0 ;i<6 ; i++){
				parkingCode.append(rd.nextInt(10));
			}
			List<OrderTemporaryShare> orderTemporaryShareList = orderTemporaryShareService.selectList(ots);
			if(orderTemporaryShareList !=null && orderTemporaryShareList.size()>0){
				String week = DateUtil.getDayOfWeek(new Date(), 0);//获取当前星期几
				for(OrderTemporaryShare o : orderTemporaryShareList){
					o.setParkingCode(parkingCode.toString());
					o.setVoucherStatus("0");
					if(packageId!=null&&!packageId.equals("")){
						PackagePrice packagePrice=	packagePriceService.queryById(Integer.parseInt(packageId));
						if(packagePrice!=null&&!packagePrice.equals("")){
							o.setPackageDay(packagePrice.getWeek());
							o.setParkingNum((packagePrice.getWeek().split(",")).length+"");
						}
						String[] weeks = packagePrice.getWeek().split(",");//获取当前套餐的所有星期几
						StringBuilder sb = new StringBuilder();
						for (int i=0;i<weeks.length;i++)
						{
							String intWeek = DateUtil.getIntOfWeek(weeks[i]);
							int n = Integer.parseInt(intWeek) - Integer.parseInt(week);
							Date date = null;
							if (n>=0){
								date = DateUtil.getPreOrNextDate(new Date(), n);
							}else {
								date = DateUtil.getPreOrNextDate(new Date(), n+7);
							}
							String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
							sb.append(dateStr+",");
							o.setAppointmentDate(sb.toString());
						}
					}else if(appointmentDate!=null&&!appointmentDate.equals("")){
						int n = Integer.parseInt(appointmentDate)-Integer.parseInt(week);
						Date date = null;
						if (n>=0){
							date = DateUtil.getPreOrNextDate(new Date(), n);
						}else {
							date = DateUtil.getPreOrNextDate(new Date(), n+7);
						}
						String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
						o.setAppointmentDate(dateStr);
						if(appointmentDate.equals("1")){
							appointmentDate="周一";
						}else if(appointmentDate.equals("2")){
							appointmentDate="周二";
						}else if(appointmentDate.equals("3")){
							appointmentDate="周三";
						}else if(appointmentDate.equals("4")){
							appointmentDate="周四";
						}else if(appointmentDate.equals("5")){
							appointmentDate="周五";
						}else if(appointmentDate.equals("6")){
							appointmentDate="周六";
						}else if(appointmentDate.equals("7")){
							appointmentDate = "周日";
						}
						o.setPackageDay(appointmentDate);
						o.setParkingNum("1");
					}
					DiscountParkingPrice discountParkingPrice = new DiscountParkingPrice();
					discountParkingPrice.setParkingId(o.getParkingId());
					List<DiscountParkingPrice> list1 = discountParkingPriceService.selectList(discountParkingPrice);
					if (list1!=null && list1.size()>0)
					{
						DiscountParkingPrice dpp = list1.get(0);
						Map map = new HashMap();
						map.put("mondayBeginTime",dpp.getMondayBeginTime());
						map.put("mondayEndTime",dpp.getMondayEndTime());
						map.put("fridayBeginTime",dpp.getFridayBeginTime());
						map.put("fridayEndTime",dpp.getFridayEndTime());
						map.put("saturdayBeginTime",dpp.getSaturdayBeginTime());
						map.put("saturdayEndTime",dpp.getSaturdayEndTime());
						map.put("sundayBeginTime",dpp.getSundayBeginTime());
						map.put("sundayEndTime",dpp.getSundayEndTime());
						map.put("thursdayBeginTime",dpp.getThursdayBeginTime());
						map.put("thursdayEndTime",dpp.getThursdayEndTime());
						map.put("tuesdayBeginTime",dpp.getTuesdayBeginTime());
						map.put("tuesdayEndTime",dpp.getTuesdayEndTime());
						map.put("wednesdayBeginTime",dpp.getWednesdayBeginTime());
						map.put("wednesdayEndTime",dpp.getWednesdayEndTime());
						String jsonString = JacksonUtil.toJson(map);
						o.setStartEndTime(jsonString);
					}
					orderTemporaryShareService.update(o);
					Parking parking = new Parking();
					parking.setParkingId(o.getParkingId());
					List<Parking> parkingList = parkingService.selectList(parking);
					if(parkingList!=null && parkingList.size()>0){
						Parking p = parkingList.get(0);
						if(p.getParkingCanUse()>0){
							if (o.getAppointmentDate()!=null && o.getAppointmentDate().contains(new SimpleDateFormat("yyyy-MM-dd").format(new Date())))
							{
								p.setParkingCanUse(p.getParkingCanUse()-1);
								parkingService.update(p);
							}
						}
					}
				}
			}
			Map<String,String> map = new HashMap<>();
			map.put("orderId",orderId);
			OrderTemporaryShare orderTemporaryShare = orderTemporaryShareService.selectByOrderId(map);

//			message = ShangAnMessageType.toShangAnJson("0", "修改成功", "parkingCode", parkingCode);
			Map<String,Object> orderMap = new HashMap<String,Object>();
			orderMap.put("parkingCode",parkingCode.toString());
			orderMap.put("orderId",orderTemporaryShare.getOrderId());
			orderMap.put("parkingNum",orderTemporaryShare.getParkingNum());
			orderMap.put("packageDay",orderTemporaryShare.getPackageDay());
			orderMap.put("carNumber",orderTemporaryShare.getCarNumber());
			orderMap.put("payType",orderTemporaryShare.getPayType());
			orderMap.put("payTime",(null!=orderTemporaryShare.getPayTime())?DateUtil.date2str(orderTemporaryShare.getPayTime(), DateUtil.DATETIME_FORMAT):"");
			orderMap.put("amountPayable",(null!=orderTemporaryShare.getAmountPayable())?orderTemporaryShare.getAmountPayable()/100:0);
			orderMap.put("amountDiscount",(null!=orderTemporaryShare.getAmountDiscount()?orderTemporaryShare.getAmountDiscount()/100:0));
			orderMap.put("amountPaid",(null!=orderTemporaryShare.getAmountPaid())?orderTemporaryShare.getAmountPaid()/100:0);
			orderMap.put("parkingCode",orderTemporaryShare.getParkingCode());
			orderMap.put("appointmentDate",orderTemporaryShare.getAppointmentDate());
			orderMap.put("voucherStatus",orderTemporaryShare.getVoucherStatus());
			orderMap.put("parkingName",orderTemporaryShare.getParkingName());
			orderMap.put("startTime",orderTemporaryShare.getStartTime());
			orderMap.put("stopTime",orderTemporaryShare.getStopTime());
			orderMap.put("parkingId",orderTemporaryShare.getParkingId());
			Object object = null;
			if (orderTemporaryShare.getStartEndTime()!=null)
			{
				try {
					object = JsonMapper.fromJson(orderTemporaryShare.getStartEndTime(), Map.class);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			orderMap.put("startEndTime",object);
			orderMap.put("mondayBeginTime",orderTemporaryShare.getMondayBeginTime());
			orderMap.put("mondayEndTime",orderTemporaryShare.getMondayEndTime());
			orderMap.put("fridayBeginTime",orderTemporaryShare.getFridayBeginTime());
			orderMap.put("fridayEndTime",orderTemporaryShare.getFridayEndTime());
			orderMap.put("saturdayBeginTime",orderTemporaryShare.getSaturdayBeginTime());
			orderMap.put("saturdayEndTime",orderTemporaryShare.getSaturdayEndTime());
			orderMap.put("sundayBeginTime",orderTemporaryShare.getSundayBeginTime());
			orderMap.put("sundayEndTime",orderTemporaryShare.getSundayEndTime());
			orderMap.put("thursdayBeginTime",orderTemporaryShare.getThursdayBeginTime());
			orderMap.put("thursdayEndTime",orderTemporaryShare.getThursdayEndTime());
			orderMap.put("tuesdayBeginTime",orderTemporaryShare.getTuesdayBeginTime());
			orderMap.put("tuesdayEndTime",orderTemporaryShare.getTuesdayEndTime());
			orderMap.put("wednesdayBeginTime",orderTemporaryShare.getWednesdayBeginTime());
			orderMap.put("wednesdayEndTime",orderTemporaryShare.getWednesdayEndTime());
			message = ShangAnMessageType.toShangAnJson("0","修改成功","orderMap",orderMap);
		}else{
			message = ShangAnMessageType.operateToJson("1", "修改失败，不存在该订单");
		}
		return message;
	}
	/**
	 * 月租停车费
	 * @param orderId
	 * @param orderType
	 * @return
	 */
	private String getPaidMessageByMonthlyParking(String orderId,String orderType){
		String message = null;
		OrderMain orderMain1 = new OrderMain();
		orderMain1.setOrderId(orderId);
		orderMain1.setOrderType(orderType);
		OrderMain orderMain = orderMainService.queryByIdAndType(orderMain1);
		
		//orderMainService.monAndProPaid(orderMain);
		if (null == orderMain) {
			logger.info("月租停车费app支付成功回调：不存在该订单" + orderId);
			message = ShangAnMessageType.operateToJson("1", "修改失败，不存在该订单");
			return message;
		}
		//orderMain.setPayTime(new Date());
		//orderMainService.update(orderMain);

		Map<String, String> map = new HashMap<>();
		map.put("orderId", orderMain.getOrderId());
		map.put("orderType", orderMain.getOrderType());
		map.put("payType", StringUtils.defaultString(orderMain.getPayType()));
		map.put("payTime", orderMain.getPayTime() != null ? DateUtil.date2str(orderMain.getPayTime(), DateUtil.DATETIME_FORMAT) : "");
		map.put("amountPayable", orderMain.getAmountPayable() != null ? Integer.toString(orderMain.getAmountPayable() / 100) : "");
		map.put("amountDiscount", orderMain.getAmountDiscount() != null ? Integer.toString(orderMain.getAmountDiscount() / 100) : "");
		map.put("amountPaid", orderMain.getAmountPaid() != null ? Integer.toString(orderMain.getAmountPaid() / 100) : "");
		if (orderType.equals("13")) {
			OrderMonthly orderMonthly = orderMonthlyService.queryByOrderId(orderMain.getOrderId());
			map.put("carNumber", StringUtils.defaultString(orderMonthly.getCarNumber()));
			map.put("monthNum", orderMonthly.getMonthNum() == null ? "" : Integer.toString(orderMonthly.getMonthNum()));
			map.put("beginDate", orderMonthly.getBeginDate() != null ? DateUtil.date2str(orderMonthly.getBeginDate(), DateUtil.DATETIME_FORMAT) : "");
			map.put("endDate", orderMonthly.getEndDate() != null ? DateUtil.date2str(orderMonthly.getEndDate(), DateUtil.DATETIME_FORMAT) : "");
		} else if (orderType.equals("14")) {
			OrderEquity orderEquity = orderEquityService.queryByOrderId(orderMain.getOrderId());
			map.put("carNumber", StringUtils.defaultString(orderEquity.getCarNumber()));
			map.put("monthNum", orderEquity.getMonthNum() == null ? "" : Integer.toString(orderEquity.getMonthNum()));
			map.put("beginDate", orderEquity.getBeginDate() != null ? DateUtil.date2str(orderEquity.getBeginDate(), DateUtil.DATETIME_FORMAT) : "");
			map.put("endDate", orderEquity.getEndDate() != null ? DateUtil.date2str(orderEquity.getEndDate(), DateUtil.DATETIME_FORMAT) : "");
		}
		message = ShangAnMessageType.toShangAnJson("0", "修改成功", "data", map);
		return message;
	}
// 修改订单状态   （现在修改订单状态已放在第三方回调接口  所以此方法暂时不用）
	private String getPaidMessageByCarwash(String orderId,String orderType){
		String message = null;
		OrderMain orderMain1 = new OrderMain();
		orderMain1.setOrderId(orderId);
		orderMain1.setOrderType(orderType);
		OrderMain orderMain = orderMainService.queryByIdAndType(orderMain1);
		if(orderMain !=null){
			if (orderMain.getPayTime() == null) {
				orderMain.setPayTime(new Date());
			}
			orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_PAID);
			//orderMainService.update(orderMain);

			Map<String, String> map = new HashMap<>();
			map.put("orderId", orderMain.getOrderId());
			map.put("orderType", orderMain.getOrderType());
			map.put("payType", StringUtils.defaultString(orderMain.getPayType()));
			map.put("payTime", orderMain.getPayTime() != null ? DateUtil.date2str(orderMain.getPayTime(), DateUtil.DATETIME_FORMAT) : "");
			map.put("amountPayable", orderMain.getAmountPayable() != null ? Integer.toString(orderMain.getAmountPayable() / 100) : "");
			map.put("amountDiscount", orderMain.getAmountDiscount() != null ? Integer.toString(orderMain.getAmountDiscount() / 100) : "");
			map.put("amountPaid", orderMain.getAmountPaid() != null ? Integer.toString(orderMain.getAmountPaid() / 100) : "");

			OrderCarwash orderCarwash = orderCarwashService.queryByOrderId(orderMain.getOrderId());
			map.put("carNumber", StringUtils.defaultString(orderCarwash.getCarNumber()));
			map.put("carType", StringUtils.defaultString(orderCarwash.getCarType()));
			map.put("reserveDate", orderCarwash.getReserveDate() != null ? DateUtil.date2str(orderCarwash.getReserveDate(), DateUtil.DATETIME_FORMAT) : "");

			message = ShangAnMessageType.toShangAnJson("0", "修改成功", "data", map);
		}else{
			message = ShangAnMessageType.operateToJson("1", "修改失败，不存在该订单");
		}
		return message;
	}
	
	@RequestMapping("cancel/{orderId}/{orderType}/{summary}")
	public void cancel(@PathVariable String orderId,@PathVariable String orderType,
			HttpServletRequest request,HttpServletResponse response){

		String message = couponService.cancelCoupon(orderId, orderType);

		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(message);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		}
	}

	/**
	 * 删除订单
	 */
	@RequestMapping("delete/{orderId}/{orderType}/{summary}")
	public void deleteOrder(@PathVariable String orderId,@PathVariable String orderType
			,HttpServletRequest request,HttpServletResponse response){
		/*String message = null;
		OrderMain orderMain = new OrderMain();
		orderMain.setOrderId(orderId);
		orderMain.setOrderType(orderType);
		OrderMain om = orderMainService.queryByIdAndType(orderMain);
		if(om!=null){
			if("13".equals(om.getOrderType())){
				om.setOrderStatus(OrderConstants.ORDER_STATUS_CANCELED);
				orderMainService.update(om);
				message = ShangAnMessageType.operateToJson("0", "删除成功");
			}else if("14".equals(om.getOrderType())){
				om.setOrderStatus(OrderConstants.ORDER_STATUS_CANCELED);
				orderMainService.update(om);
				message = ShangAnMessageType.operateToJson("0", "删除成功");
			}
		}else{
			message = ShangAnMessageType.operateToJson("1", "删除失败，不存在该订单");
		}*/
		/**
		 * 该接口无用
		 * @author junior 
		 * @date 2016-08-11
		 */
		String message = ShangAnMessageType.operateToJson("1", "不允许删除订单");
		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(message);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		}
	}
	/**
	 * 点击优惠券
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("useCoupon")
	public void useCoupon(HttpServletRequest request,HttpServletResponse response){
		String customerId = request.getParameter("customerId");
		String orderId = request.getParameter("orderId");
		String  couponId = request.getParameter("couponId");
		String mess = null;
		OrderMain orderMain = new OrderMain();
		//查询订单
		List<OrderMain> orderMainList = new ArrayList<OrderMain>();
		orderMain.setCustomerId(customerId);
		orderMain.setOrderId(orderId);
		orderMainList = orderMainService.selectList(orderMain);
		//查询优惠券
		Coupon coupon = new Coupon();
		List<Coupon> couponList = new ArrayList<Coupon>();
		coupon.setCouponId(couponId);
		couponList = couponService.selectList(coupon);
		if(null!=orderMainList&&null!=couponList&&orderMainList.size()>0&&couponList.size()>0){
			coupon = couponList.get(0);
			orderMain = orderMainList.get(0);
			int amountDiscount =0;//优惠金额
			String iCouponType = coupon.getCouponType();//折扣类型
			if ("1".equals(iCouponType)) {//使用定额券
				amountDiscount = coupon.getParAmount();
			} else if("2".equals(iCouponType)){//使用折扣券
				int dMaxDiscount = coupon.getMaxdiscount();//折扣上限
				Integer iParDiscount = coupon.getParDiscount();//折扣
				int dDiscountAmount = orderMain.getAmountPayable()*(10-iParDiscount)/10;//优惠金额
				if(dMaxDiscount <= dDiscountAmount){//优惠金额超过折扣上限
					amountDiscount = dMaxDiscount;
				}else {
					amountDiscount = dDiscountAmount;
				}
			}
			Map<String,Object> orderMap = new HashMap<String,Object>();			
			orderMap.put("amountPayable", orderMain.getAmountPayable()/100);//应付
			orderMap.put("amountDiscount", amountDiscount);//优惠
			orderMap.put("amountPaid", (orderMain.getAmountPayable()/100-amountDiscount)<0?0:orderMain.getAmountPayable()/100-amountDiscount);//实付
			
			mess = ShangAnMessageType.toShangAnJson("0", "获取金额信息", "payInfo", orderMap);
			
		}else{
			mess = ShangAnMessageType.toShangAnJson("1", "数据异常", "payInfo", "");
		}
		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(mess);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		}
	}
	/**
	 * 获取月租产权订单列表
	 * @param customerId
	 * @param orderType
	 * @param orderStatus
	 * @param pageIndex
	 * @param request
	 * @param response
	 */
	@RequestMapping("orderlist/{customerId}/{orderType}/{orderStatus}/{pageIndex}/{summary}")
	public void orderlist(@PathVariable String customerId,@PathVariable String orderType,@PathVariable String orderStatus,@PathVariable String pageIndex,HttpServletRequest request,HttpServletResponse response){
		String mess = null;
		if("13".equals(orderType)){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("customerId",customerId );
			param.put("orderType",orderType );
			param.put("orderStatus", orderStatus);
			param.put("pageIndex", pageIndex);
			mess = orderMonthlyService.getMonthlyOrders(param);
		}else if("14".equals(orderType)){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("customerId",customerId );
			param.put("orderType",orderType );
			param.put("orderStatus", orderStatus);
			param.put("pageIndex", pageIndex);
			mess = orderEquityService.getEquityOrders(param);
			
		}else{
			mess = ShangAnMessageType.toShangAnJson("2", "没有该订单类型", "order", "");
		}
		
		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(mess);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		}
	}
	/*获取钱包充值记录*/
	@RequestMapping("rechargelist/{customerId}/{pageIndex}/{summary}")
	public void rechargelist(@PathVariable String customerId,@PathVariable String pageIndex,HttpServletRequest request,HttpServletResponse response){
		String mess = null;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("customerId", customerId);
		param.put("pageIndex", pageIndex);
		mess = orderRechargeService.getRechargeList(param);
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(mess);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		}
	}
	/*获取钱包消费记录*/
	@RequestMapping("consumlist/{customerId}/{pageIndex}/{summary}")
	public void consumlist(@PathVariable String customerId,@PathVariable String pageIndex,HttpServletRequest request,HttpServletResponse response){
		String mess = null;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("customerId", customerId);
		param.put("pageIndex", pageIndex);
		mess = orderRechargeService.getConsumList(param);
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(mess);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		}
	}
	/**
	 * 加油卡支付成功
	 * @param orderId
	 * @param orderType
	 * @return
	 */
	private String getPaidMessageByCard(String orderId, String orderType) {
		String message = null;

		// 查询订单主表
		OrderMain orderMain = new OrderMain();
		orderMain.setOrderId(orderId);
		orderMain.setOrderType(orderType);
		orderMain = orderMainService.queryByIdAndType(orderMain);
		if (orderMain == null) {
			message = ShangAnMessageType.operateToJson("1", "修改失败，不存在该订单");
			return message;
		}

		// 查询加油卡订单表
		OrderRefuel orderRefuel = new OrderRefuel();
		orderRefuel.setOrderId(orderId);
		orderRefuel.setIsUsed(Constants.TRUE);
		List<OrderRefuel> orderRefuelList = orderRefuelService.selectList(orderRefuel);
		if (orderRefuelList == null || orderRefuelList.size() == 0) {
			message = ShangAnMessageType.operateToJson("1", "修改失败，不存在该订单");
			return message;
		}
		orderRefuel = orderRefuelList.get(0);

		// 调用第三方充值接口
		Map<String, Object> resultMap = null;
		try {
//			String result = "{\n" +
//					"    \"reason\":\"订单提交成功\",\n" +
//					"    \"result\":{\n" +
//					"        \"cardid\":\"\",\n" +
//					"        \"cardnum\":\"1\",\n" +
//					"        \"cardname\":\"全国 中石化加油 固定面值加油卡 直充100元\",\n" +
//					"        \"sporder_id\":\"S145734467552861953_2\",\n" +
//					"        \"game_userid\":\"1000112132123\",\n" +
//					"        \"game_state\":\"0\",\n" +
//					"        \"ordercash\":\"99.8\",\n" +
//					"        \"orderid\":\"2016030700000047\",\n" +
//					"        \"uorderid\":\"2016030700000047\"\n" +
//					"    },\n" +
//					"    \"error_code\":0\n" +
//					"}";
			String result = orderRefuelService.rechargeByJuheAPI(orderId);
			resultMap = JacksonUtil.jsonToMap(result);
			Object error_code = resultMap.get("error_code");
			if (error_code == null || (!error_code.toString().equals("0"))) {
				message = ShangAnMessageType.toShangAnJson("2", "调用第三方充值接口错误", "result", result);
				return message;
			}
		} catch (Exception e) {
			message = ShangAnMessageType.operateToJson("1", "数据异常");
			logger.error("", e);
			return message;
		}
		// 取出返回值
		resultMap = JacksonUtil.jsonToMap((String) resultMap.get("result"));
//		String cardid = (String) resultMap.get("cardid");
//		String cardnum = (String) resultMap.get("cardnum"); /*充值数量*/
		String cardname = (String) resultMap.get("cardname"); /*充值名称*/
		String sporder_id = (String) resultMap.get("sporder_id"); /*商家订单号*/
//		String game_userid = (String) resultMap.get("game_userid"); /*加油卡卡号*/
		String game_state = (String) resultMap.get("game_state"); /*充值状态:0充值中 1成功 9撤销*/
		String ordercash = (String) resultMap.get("ordercash"); /*进货价格*/
//		String orderid = (String) resultMap.get("orderid");
//		String uorderid = (String) resultMap.get("uorderid"); /*商户自定的订单号*/

		// 修改订单主表
		if (orderMain.getPayTime() == null) {
			orderMain.setPayTime(new Date()); // 支付时间
		}
		orderMain.setModifyDate(orderMain.getPayTime()); // 修改时间
		orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_PAID); // 已付款
		orderMainService.update(orderMain);

		// 修改加油卡订单表
		orderRefuel.setOrderCash(new BigDecimal(ordercash).multiply(new BigDecimal("100")).toBigInteger().intValue());
		orderRefuel.setTopupStatus(game_state);
		orderRefuel.setTopupName(cardname);
		orderRefuel.setSporderId(sporder_id);
		orderRefuel.setModifyDate(new Date());
		orderRefuelService.update(orderRefuel);

		message = ShangAnMessageType.operateToJson("0", "修改成功");
		return message;
	}

	/**
	 * 获取月租和产权订单列表
	 */
	@RequestMapping("monthlyEquityList/{orderType}/{customerId}/{orderStatus}/{pageIndex}/{summary}")
	public void monthlyEquityList(@PathVariable String orderType,
								  @PathVariable String customerId,
								  @PathVariable String orderStatus,
								  @PathVariable String pageIndex,
								  HttpServletRequest request,
								  HttpServletResponse response) {
		String msg = null;
		try {
			Page<OrderMain> page = new Page<OrderMain>();
			page.getParams().put("customerId", customerId);
			page.getParams().put("orderStatus", orderStatus);
			page.setCurrentPage(Integer.valueOf(pageIndex));
			page.setPageSize(Page.PAGE_SIZE_10);
			page.getParams().put("orderType", orderType);
			List<OrderMain> list = orderMainService.queryMonthlyEquityListPage(page);
			List <Map<String,Object>> orderList = new ArrayList <Map<String,Object>>();
			if(list != null && list.size() > 0) {
				for (OrderMain orderMain : list) {
					Map<String, Object> orderMap = new HashMap<String, Object>();
					orderMap.put("monthNum", (null != orderMain.getMonthNum()) ? orderMain.getMonthNum() : "");
					orderMap.put("orderId", (null != orderMain.getOrderId()) ? orderMain.getOrderId() : "");
					orderMap.put("orderStatus", (null != orderMain.getOrderStatus()) ? orderMain.getOrderStatus() : "");
					orderMap.put("orderType", (null != orderMain.getOrderType()) ? orderMain.getOrderType() : "");
					orderMap.put("carNumber", (null != orderMain.getCarNumber()) ? orderMain.getCarNumber() : "");
					orderMap.put("parkingId", (null != orderMain.getParkingId()) ? orderMain.getParkingId() : "");
					orderMap.put("parkingName", (null != orderMain.getParkingName()) ? orderMain.getParkingName() : "");
					orderMap.put("createDate", (null != orderMain.getCreateDate()) ? DateUtil.date2str(orderMain.getCreateDate(), DateUtil.DATETIME_FORMAT) : "");
					orderMap.put("amountPaid", (null != orderMain.getAmountPaid()) ? orderMain.getAmountPaid() / 100 : "");
					orderMap.put("amountPayable", (null != orderMain.getAmountPayable()) ? orderMain.getAmountPayable() / 100 : "");
					orderMap.put("amountDiscount", (null != orderMain.getAmountDiscount()) ? orderMain.getAmountDiscount() / 100 : "");
					orderMap.put("beginTime", (null != orderMain.getBeginTime()) ? DateUtil.date2str(orderMain.getBeginTime(), DateUtil.DATETIME_FORMAT) : "");
					orderMap.put("endTime", (null != orderMain.getEndTime()) ? DateUtil.date2str(orderMain.getEndTime(), DateUtil.DATETIME_FORMAT) : "");
					orderMap.put("payTime", (null != orderMain.getPayTime()) ? DateUtil.date2str(orderMain.getPayTime(), DateUtil.DATETIME_FORMAT) : "");
					orderList.add(orderMap);
				}
				msg = ShangAnMessageType.toShangAnJson("0", "订单", "order", orderList);
			} else {
				msg = ShangAnMessageType.toShangAnJson("1", "无数据", "order", orderList);
			}
		} catch (NumberFormatException e) {
			logger.error("", e);
			msg = ShangAnMessageType.operateToJson("2", "异常，查询失败");
		}

		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
			out.print(msg);
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	/**
	 * 取消订单
	 */
	@RequestMapping("cancelOrder/{orderId}/{summary}")
	public void cancelOrder(@PathVariable String orderId,
							HttpServletRequest request,
							HttpServletResponse response) {
		String msg = null;
		try {
			msg = orderMainService.cancelOrder(orderId);
		} catch (Exception e) {
			logger.error("", e);
			msg = ShangAnMessageType.operateToJson("2", "异常");
		}

		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(msg);
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	/**
	 * 钱包支付
	 */
	@RequestMapping("walletPay")
	public void walletPay(HttpServletRequest request,
						  HttpServletResponse response,
						  @RequestParam String payPassword,
						  @RequestParam String orderId) {
		String msg = null;
		try {
			logger.info("**********************************************************");
			logger.info("钱包支付");
			logger.info("**********************************************************");
			msg = orderMainService.walletPay(orderId, payPassword);
		} catch (Exception e) {
			logger.error("", e);
			msg = ShangAnMessageType.operateToJson("2", "异常");
		}

		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(msg);
		} catch (IOException e) {
			logger.error("", e);
		}
	}


	/**
	 * 钱包支付旧代泊订单专用(新代泊上线后废弃)
	 */
	@Deprecated
	@RequestMapping("payParking")
	public void payParking(HttpServletRequest request,
						  HttpServletResponse response,
						  @RequestParam String payPassword,
						  @RequestParam String orderId,
						  @RequestParam String price) {
		String msg = null;
		try {
			msg = orderMainService.payParking(orderId, price, payPassword);
		} catch (Exception e) {
			logger.error("", e);
			msg = ShangAnMessageType.operateToJson("2", "异常");
		}

		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(msg);
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	/**
	 * 查询未付款订单条数
	 */
	@RequestMapping("selectCount/{customerId}/{summary}")
	public void selectCount(@PathVariable String customerId,
							HttpServletRequest request,
							HttpServletResponse response) {
		String msg = null;
		try {
			msg = orderMainService.selectCount(customerId);
		} catch (Exception e) {
			logger.error("", e);
			msg = ShangAnMessageType.operateToJson("2", "异常");
		}

		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(msg);
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	/**
	 * 计算赠送的优惠金额
	 */
	@RequestMapping("calcGiftAmount/{amount}/{summary}")
	public void calcGiftAmount(@PathVariable String amount,
								HttpServletRequest request,
								HttpServletResponse response) {
		String msg = null;
		try {
			Integer priceInt = new BigDecimal(amount).multiply(new BigDecimal("100")).intValue();
			Integer giftAmount = rechargeRuleGiftAmountService.getGiftAmount(priceInt);
			msg = ShangAnMessageType.toShangAnJson("0", "success", "totalAmount", (priceInt + giftAmount) / 100.00);
		} catch (Exception e) {
			logger.error("", e);
			msg = ShangAnMessageType.operateToJson("2", "异常");
		}

		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(msg);
		} catch (IOException e) {
			logger.error("", e);
		}
	}
	/**
	 * 查询洗车订单
	 * @param customerId
	 * @param orderStatus
	 * @param pageIndex
	 * @param request
	 * @param response
	 */
	@RequestMapping("carwashList/{customerId}/{orderStatus}/{pageIndex}/{summary}")
	public void queryOrderStatus(@PathVariable String customerId,@PathVariable String orderStatus,@PathVariable String pageIndex,HttpServletRequest request,HttpServletResponse response){
		String mess = null;
		//if("10".equals(orderStatus)){
			Page<OrderMain> page = new  Page<OrderMain>();
			//Map<String,Object> param = new HashMap<String,Object>();
			
			
			 page.getParams().put("customerId",customerId );
			 page.getParams().put("orderStatus", orderStatus);
			 page.getParams().put("pageIndex", pageIndex);
			 
			page.setCurrentPage(Integer.valueOf(pageIndex));
			mess = orderMainService.queryOrderStatus(page);
//		}else if("11".equals(orderStatus)){
//			Page<OrderMain> page = new  Page<OrderMain>();
//			Map<String,Object> param = new HashMap<String,Object>();
//			param.put("customerId",customerId );
//			param.put("orderStatus", orderStatus);
//			param.put("pageIndex", pageIndex);
//			param.put("orderStatus", orderStatus);
//			mess = orderMainService.queryOrderStatus(page);
//			 page.setCurrentPage(Integer.valueOf(pageIndex));
//		}
	//	else{
			//mess = ShangAnMessageType.toShangAnJson("2", "查询失败", "order", "");
	//	}
		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(mess);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		}
	}
	/**
	 * 提交发票信息
	 */
	@RequestMapping("postInvoiceInfo")
	public void postInvoiceInfo(HttpServletRequest request,HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			logger.error("",e);
		}
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		String customerId = request.getParameter("customerId");
		String invoiceName = request.getParameter("invoiceName");
		String sendType = request.getParameter("sendType");
		String sendAddress = request.getParameter("sendAddress");
		String carNumber = request.getParameter("carNumber");
		if(sendAddress==null){
			sendAddress = "";
		}
		if(StringUtils.isEmpty(customerId)||StringUtils.isEmpty(sendType)||StringUtils.isEmpty(carNumber)){
			out.print(ShangAnMessageType.operateToJson("2", "参数不能为空"));
			logger.error("参数不能为空");
			return;
		}
		if((!"0".equals(sendType))&&(!"1".equals(sendType))){
			out.print(ShangAnMessageType.operateToJson("2", "sendType只能为0,1"));
			logger.error("sendType只能为0,1");
			return;
		}
		String msg = null;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("customerId", customerId);
		param.put("invoiceName", invoiceName);
		param.put("sendType", sendType);
		param.put("sendAddress", sendAddress);
		param.put("carNumber", carNumber);
		msg = invoiceService.postInvoiceInfo(param);		
		out.print(msg);

	}
	/**
	 * 获取发票信息
	 */
	@RequestMapping("getLatestInvoiceInfo/{customerId}/{parkingId}/{summary}")
	public void getLatestInvoiceInfo(@PathVariable String customerId,@PathVariable String parkingId,
			HttpServletRequest request,HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			logger.error("",e);
		}
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		String msg = null;
		if(StringUtils.isEmpty(customerId)||StringUtils.isEmpty(parkingId)){
			out.print(ShangAnMessageType.operateToJson("0", "参数不能为空"));
			return;
		}
		msg = invoiceService.getLatestInvoiceInfo(customerId,parkingId);		
		out.print(msg);

	}

	/**
	 * 查询所有订单
	 * @param customerId 用户id
	 * @param tab 1:全部订单 2:待付款 3:待评价
	 * @param pageIndex
	 * @param pageSize
	 * @param version
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("queryAllOrder")
	public void queryAllOrder(String customerId,
							  @RequestParam(required = false, defaultValue = "1") Integer tab,
							  @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
							  Integer pageSize,
							  String version,
							  HttpServletRequest request,
							  HttpServletResponse response) throws IOException {
		String message;
		try {
			Page<Object> page = new Page<>();
			page.setCurrentPage(pageIndex);
			if (pageSize != null) {
				page.setPageSize(pageSize);
			}
			page.getParams().put("tab", tab);
			page.getParams().put("customerId", customerId);

			Set<String> tableNames = new HashSet<>(7);
			tableNames.add("t_order_refuel");
			tableNames.add("t_order_carwash");
			tableNames.add("t_order_equity");
			tableNames.add("t_order_monthly");
			tableNames.add("t_order_park");
			if (tab != 2) { // 待付款页面 不显示 优惠停车 和 临停缴费 订单
				tableNames.add("t_order_temporary");
				tableNames.add("t_order_temporary_share");
			}
			page.getParams().put("tableNames", tableNames);

			Page<Object> result = orderMainService.queryAllOrder(page);
			message = ShangAnMessageType.toShangAnJson("0", "success", "data", result.getResultList());
		} catch (Exception e) {
			logger.error("", e);
			message = ShangAnMessageType.operateToJson("2", "异常");
		}
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(message);
	}

	/**
	 * 查询订单详情
	 */
	@RequestMapping("queryOrderDetail")
	public void queryOrderDetail(@RequestParam String orderId,
								 @RequestParam String orderType,
								 String version,
								 HttpServletRequest request,
								 HttpServletResponse response) throws IOException {
		String message;
		try {
			Object result = null;
			switch (orderType) {
				case "10":
				case "11":  // 优惠停车  临停
					result = orderMainService.queryTemporaryByOrderId(orderId, orderType);
					break;
				case "12":  // 代泊
					result = parkerService.queryParkerByOrderId(orderId);
					break;
				case "13":
				case "14":  // 月租 产权
					result = orderMainService.queryMonthlyEquityByOrderId(orderId);
					break;
				case "15":  // 加油卡
					result = orderRefuelService.queryRefuelByOrderId(orderId);
					break;
				case "17":  // 洗车
					result = orderMainService.queryCarwashByOrderId(orderId);
					break;
			}
			message = ShangAnMessageType.toShangAnJson("0", "success", "data", result);
		} catch (Exception e) {
			logger.error("", e);
			message = ShangAnMessageType.operateToJson("2", "异常");
		}
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(message);
	}

}
