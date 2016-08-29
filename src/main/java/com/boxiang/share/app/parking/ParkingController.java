package com.boxiang.share.app.parking;

import cn.b2m.eucp.client.SingletonClient;
import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.car.po.Car;
import com.boxiang.share.product.car.service.CarService;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.order.po.Monthlyparkinginfo;
import com.boxiang.share.product.order.po.Propertyparkinginfo;
import com.boxiang.share.product.order.service.MonthlyparkinginfoService;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.product.order.service.PropertyparkinginfoService;
import com.boxiang.share.product.parking.po.PackagePrice;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.po.ParkingStatus;
import com.boxiang.share.product.parking.po.ParkingVoucher;
import com.boxiang.share.product.parking.service.*;
import com.boxiang.share.product.qrcode.po.CarlovQrcode;
import com.boxiang.share.product.qrcode.service.CarlovQrcodeService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.OrderConstants;
import com.boxiang.share.utils.ShangAnMessageType;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("app/parking")
public class ParkingController {
	private static final Logger logger = Logger.getLogger(ParkingController.class);
	@Resource
	private OrderMainService orderMainService;
	@Resource
	private CustomerService customerService;
	@Resource
	private ParkingService parkingService;
	@Resource
	private ParkingVoucherService parkingVoucherService;
	@Resource
	private CarService carService;
	@Resource
	private ParkingStatusService parkingStatusService;
	/** 月租停车信息*/
	@Resource
	private MonthlyparkinginfoService monthlyparkinginfoService;
	/** 产权停车信息*/
	@Resource
	private PropertyparkinginfoService PropertyparkinginfoService;

	@Resource
	private PackagePriceService packagePriceService;

	@Resource
	private DiscountParkingPriceService discountParkingPriceService;

	@Resource
	private CarlovQrcodeService carlovQrcodeService;



	/**
	 * 提交预约凭证
	 * @param customerId
	 * @param parkingId
	 * @param request
	 * @param response
	 */
	@RequestMapping("addVoucher/{customerId}/{parkingId}/{carNumber}/{summary}")
	public void addVoucher(@PathVariable String customerId,@PathVariable String parkingId,@PathVariable String carNumber,HttpServletRequest request,HttpServletResponse response){
		String message=null;
		ParkingVoucher pv = new ParkingVoucher();
		pv.setParkingId(parkingId);
		pv.setCarNumber(carNumber);
		List<ParkingVoucher> dateList = parkingVoucherService.selectListByDate(pv);
		if(dateList.size()>0){
			message = ShangAnMessageType.operateToJson("2", "已存在凭证");
		}else{
			try{
				pv.setCreateDate(new Date());
				pv.setIsUsed(Constants.TRUE);
				pv.setCreateor("admin");
				pv.setCustomerId(customerId);
				pv.setPvStatus("0");
				parkingVoucherService.add(pv);
				Parking pk = new Parking();
				pk.setParkingId(parkingId);
				if(parkingService.selectList(pk) !=null){
					Parking park = parkingService.selectList(pk).get(0);
					if(park.getParkingCanUse()!=null && park.getParkingCanUse()>1)
					{
						park.setParkingCanUse(park.getParkingCanUse()-1);
					}
					parkingService.update(park);
				}
				message = ShangAnMessageType.operateToJson("0", "预约成功");
			}catch(Exception e){
				logger.error("",e);
				message = ShangAnMessageType.operateToJson("1", "预约失败");
			}
		}
		
		
		/*
		String message=null;
		ParkingVoucher parkingVoucher = new ParkingVoucher();
		parkingVoucher.setCustomerId(customerId);
		parkingVoucher.setPvStatus("0");
		parkingVoucher.setCarNumber(carNumber);
		List<ParkingVoucher> pvList = parkingVoucherService.queryCountStatus(parkingVoucher);
		if(pvList!=null && pvList.size()>0){
			for(ParkingVoucher pvh :pvList){
				pvh.setPvStatus("2");
				parkingVoucherService.update(pvh);
				Parking pk = new Parking();
				pk.setParkingId(pvh.getParkingId());
				if(parkingService.selectList(pk) !=null){
					Parking park = parkingService.selectList(pk).get(0);
					if(park.getParkingCanUse()!=null)
					{
						park.setParkingCanUse(park.getParkingCanUse()+1);
					}
					parkingService.update(park);
				}
			}
		}
		ParkingVoucher pv = new ParkingVoucher();
		pv.setCustomerId(customerId);
		pv.setParkingId(parkingId);
		pv.setCarNumber(carNumber);
		pv.setPvStatus("0");
		pv.setCreateDate(new Date());
		pv.setIsUsed(Constants.TRUE);
		pv.setCreateor("admin");
		parkingVoucherService.add(pv);
		Parking pk = new Parking();
		pk.setParkingId(parkingId);
		if(parkingService.selectList(pk) !=null){
			Parking park = parkingService.selectList(pk).get(0);
			if(park.getParkingCanUse()!=null && park.getParkingCanUse()>1)
			{
				park.setParkingCanUse(park.getParkingCanUse()-1);
			}
			parkingService.update(park);
		}
		message = ShangAnMessageType.operateToJson("0", "预约成功");*/
//		List<ParkingVoucher> dateList = parkingVoucherService.selectListByDate(pv);
//		if(dateList.size()>0){
//			message = ShangAnMessageType.operateToJson("2", "已存在凭证");
//		}else{
//			try{
//				pv.setCreateDate(new Date());
//				pv.setIsUsed(Constants.TRUE);
//				pv.setCreateor("admin");
//				parkingVoucherService.add(pv);
//				Parking pk = new Parking();
//				pk.setParkingId(parkingId);
//				if(parkingService.selectList(pk) !=null){
//					Parking park = parkingService.selectList(pk).get(0);
//					if(park.getParkingCanUse()!=null && park.getParkingCanUse()>1)
//					{
//						park.setParkingCanUse(park.getParkingCanUse()-1);
//					}
//					parkingService.update(park);
//				}
//				message = ShangAnMessageType.operateToJson("0", "预约成功");
//			}catch(Exception e){
//				logger.error("",e);
//				message = ShangAnMessageType.operateToJson("1", "预约失败");
//			}
//		}
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(message);
		} catch (IOException e) {
			logger.error("", e);
		}
	}
	/**
	 * 预约凭证列表
	 * @param customerId
	 * @param pageIndex
	 * @param request
	 * @param response
	 */
	@RequestMapping("voucherList/{customerId}/{pageIndex}/{summary}")
	public void voucherList(@PathVariable String customerId,@PathVariable String pageIndex,HttpServletRequest request,HttpServletResponse response){
		String message =null;
		message = parkingVoucherService.getList(customerId,pageIndex);
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(message);
		} catch (IOException e) {
			logger.error("", e);
		}
	}
	@RequestMapping("getprice/{parkingId}/{carNumber}/{orderType}/{summary}")
	public void eventPageList(@PathVariable String parkingId,@PathVariable String carNumber,@PathVariable String orderType,HttpServletRequest request, HttpServletResponse response) {
		String mess = null;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("parkingId", parkingId);
		param.put("carNumber", carNumber);
		param.put("orderType", orderType);
		if(!"".equals(orderType)&&orderType.equals("13")){//月租
			mess = orderMainService.getMonPrice(param);
		}else if (!"".equals(orderType)&&orderType.equals("14")) {//产权
			mess = orderMainService.getEquiPrice(param);
		}

		//设置utf-8
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(mess);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("carlist/{customerId}/{orderType}/{summary}")
	public void carList(@PathVariable String customerId,@PathVariable String orderType ,HttpServletRequest request,HttpServletResponse response){
		String message = null;
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		try{
			Car car = new Car();
			car.setCustomerId(customerId);
			List<Car> list = carService.selectList(car);
			if(list==null || list.size()==0){
				message = ShangAnMessageType.operateToJson("1", "无数据");
			}else {

				//parkingList
				Map<String, Object> map2 = new HashMap<String, Object>();

				// cooperation image
				Customer customer = customerService.queryByCustomerId(customerId);
				if(null==customer){
					out = response.getWriter();
					message = ShangAnMessageType.operateToJson("1", "没有该客户信息");
					out.print(message);
					return;
				}
				List<Parking> cooperationList1 = parkingService.queryCooperation();
				List<String> cooperationList = new ArrayList<String>();
				for(Parking park : cooperationList1){
					cooperationList.add(park.getImgUrl());
				}
				map2.put("cooperation",cooperationList);

				Parking parking = new Parking();
				parking.setCustomerId(customerId);

				List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
				if("13".equals(orderType)){
					//月租
					List<Parking> carListByCM = parkingService.carListByCM(parking);
					if(carListByCM!=null && carListByCM.size()>0){
						for(Parking p1 :carListByCM){
							Map<String,String> mp1 = new HashMap<String,String>();
							mp1.put("parkingId",p1.getParkingId());
							mp1.put("parkingName",p1.getParkingName());
							mp1.put("carNumber",p1.getCarNumber());
							mp1.put("mobile",p1.getPhone());
							mp1.put("price", p1.getPriceM());
							mp1.put("maxDate", (null!=p1.getMaxDate())?DateUtil.date2str(p1.getMaxDate(), DateUtil.DATE_FORMAT):"");
							mp1.put("endDate",(null!=p1.getEndDate())?DateUtil.date2str(p1.getEndDate(), DateUtil.DATE_FORMAT):"");
							if((customer.getCustomerMobile()!=null && 
									customer.getCustomerMobile().equals(p1.getPhone()))
									||(p1.getItem01()!=null && p1.getItem01().indexOf(customerId)!=-1)){
								mp1.put("allow", "1");//允许
							}else{
								mp1.put("allow","0");//不允许
							}
							mapList.add(mp1);
						}
						map2.put("parkingList", mapList);
						//					map2.put("mobile", customer.getCustomerMobile());
						message=ShangAnMessageType.toShangAnJson("0", "查询成功", "carlist", map2);
					}else{
						map2.put("parkingList", mapList);
						message=ShangAnMessageType.toShangAnJson("0", "查询成功", "carlist", map2);
					}
				}else if("14".equals(orderType)){
					//产权
					List<Parking> carListByCP = parkingService.carListByCP(parking);
					if(carListByCP!=null && carListByCP.size()>0){
						for(Parking p2 :carListByCP){
							Map<String,String> mp = new HashMap<String,String>();
							mp.put("parkingId",p2.getParkingId());
							mp.put("price", p2.getPriceP());
							mp.put("parkingName",p2.getParkingName());
							mp.put("carNumber",p2.getCarNumber());
							mp.put("mobile", p2.getPhone());
							mp.put("maxDate", (null!=p2.getMaxDate())?DateUtil.date2str(p2.getMaxDate(), DateUtil.DATE_FORMAT):"");
							mp.put("endDate",(null!=p2.getEndDate())?DateUtil.date2str(p2.getEndDate(), DateUtil.DATETIME_FORMAT):"");
							if((customer.getCustomerMobile()!=null && 
									customer.getCustomerMobile().equals(p2.getPhone()))
									||(p2.getItem01()!=null && p2.getItem01().indexOf(customerId)!=-1)){
								mp.put("allow", "1");//允许
							}else{
								mp.put("allow","0");//不允许
							}

							mapList.add(mp);
						}
						map2.put("parkingList", mapList);
						//					map2.put("mobile", customer.getCustomerMobile());
						message=ShangAnMessageType.toShangAnJson("0", "查询成功", "carlist", map2);
					}else{
						map2.put("parkingList", mapList);
						message=ShangAnMessageType.toShangAnJson("0", "查询成功", "carlist", map2);
					}

				}
			}
		}catch(Exception e){
			e.printStackTrace();
			message=ShangAnMessageType.operateToJson("1", "查询失败");
		}
		try {
			out = response.getWriter();
			System.out.println("####"+message);
			out.print(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("getlist")
	public void getlist(HttpServletRequest request,HttpServletResponse response){
		String parkingCountry = request.getParameter("parkingCountry");
		String parkingProvince = request.getParameter("parkingProvince");
		String parkingCity = request.getParameter("parkingCity");
		String parkingArea = request.getParameter("parkingArea");
		String parkingName = request.getParameter("parkingName");
		Parking parking = new Parking();
		parking.setParkingCountry(parkingCountry);
		parking.setParkingProvince(parkingProvince);
		parking.setParkingCity(parkingCity);
		parking.setParkingArea(parkingArea);
		parking.setParkingName(parkingName);
		List<Parking> parkingList = parkingService.getList(parking);
		String message = ShangAnMessageType.toShangAnJson("0", "查询成功", "parkingList", parkingList);
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(message);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("", e);
		}
	}
	/**
	 * 月租(产权)提交验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping("validatecode")
	public void validatecode(HttpServletRequest request, HttpServletResponse response) {
		String mess = null;
		String customerId = request.getParameter("customerId");
		String orderType = request.getParameter("orderType");
		String varlidateCode = request.getParameter("varlidateCode");
		String parkingId = request.getParameter("parkingId");
		String carNumber = request.getParameter("carNumber");
		if(null!=customerId&&null!=orderType&&null!=varlidateCode){
			Customer cusParam = new Customer();
			cusParam.setCustomerId(customerId);
			List<Customer> cusList = null;
			cusList = customerService.selectList(cusParam);
			if(null!=cusList&&cusList.size()>0){//用户存在时

				if(OrderConstants.ORDER_TYPE_MONTHLY.equals(orderType)){//月租订单时
					Monthlyparkinginfo monthlyparkinginfo =new Monthlyparkinginfo();
					monthlyparkinginfo.setCarNumber(carNumber);
					monthlyparkinginfo.setVillageId(parkingId);
					List<Monthlyparkinginfo> mpiList = monthlyparkinginfoService.selectList(monthlyparkinginfo);

					if(null!=mpiList&&mpiList.size()>0){
						//验证注册短信验证码
						int flag = 2;
						flag = SingletonClient.validMessageCode(mpiList.get(0).getPhone(), varlidateCode);
						if (flag == 2) {
							mess = ShangAnMessageType.operateToJson("1", "验证码失效");
						} else if (flag == 3) {
							mess = ShangAnMessageType.operateToJson("2", "请输入正确验证码");
						}else{
							monthlyparkinginfo = mpiList.get(0);
							monthlyparkinginfo.setItem01(customerId);
							monthlyparkinginfoService.update(monthlyparkinginfo);
							mess = ShangAnMessageType.operateToJson("0", "月租订单修改成功");
						}
					}

				}else if(OrderConstants.ORDER_TYPE_EQUITY.equals(orderType)){//产权订单时
					Propertyparkinginfo propertyparkinginfo =new Propertyparkinginfo();
					propertyparkinginfo.setCarNumber(carNumber);
					propertyparkinginfo.setVillageId(parkingId);
					List<Propertyparkinginfo> mpiList = PropertyparkinginfoService.selectList(propertyparkinginfo);
					if(null!=mpiList&&mpiList.size()>0){
						//验证注册短信验证码
						int flag = 2;
						flag = SingletonClient.validMessageCode(mpiList.get(0).getPhone(), varlidateCode);
						if (flag == 2) {
							mess = ShangAnMessageType.operateToJson("1", "验证码失效");
						} else if (flag == 3) {
							mess = ShangAnMessageType.operateToJson("2", "请输入正确验证码");
						}else{
							propertyparkinginfo = mpiList.get(0);
							propertyparkinginfo.setItem01(customerId);
							PropertyparkinginfoService.update(propertyparkinginfo);
							mess = ShangAnMessageType.operateToJson("0", "产权订单修改成功");
						}
					}
				}

			}

		}else{
			mess = ShangAnMessageType.operateToJson("2", "数据异常");
		}

		//设置utf-8
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(mess);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("getIsParking/{parkingLatitude}/{parkingLongitude}/{summary}")
	public void getIsParking(@PathVariable String parkingLatitude,@PathVariable String parkingLongitude, HttpServletRequest request,HttpServletResponse response){
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String message = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		try {
			Parking parking = new Parking();
			parking.setParkingLatitude(parkingLatitude);
			parking.setParkingLongitude(parkingLongitude);
			String dayOfWeek = DateUtil.getDayOfWeek(new Date(), 0);
			String weekStr = discountParkingPriceService.numToStr(dayOfWeek);
			parking.setVipStartTime(weekStr + "_begin_time");
			parking.setVipStopTime(weekStr + "_end_time");
			parking.setVipSharePriceComment(weekStr + "_price");
			List<Parking> parkingList = parkingService.getListParking(parking);
			if(null!=parkingList&&parkingList.size()>0){
				for(Parking p:parkingList){
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("parkingId", null!=p.getParkingId()?p.getParkingId():"");
					paramMap.put("parkingName", null!=p.getParkingName()?p.getParkingName():"");
					paramMap.put("peacetimePrice", null!=p.getPeacetimePrice()?p.getPeacetimePrice():"");
					paramMap.put("sharePrice", null!=p.getSharePrice()?p.getSharePrice():"");
					paramMap.put("startTime", null!=p.getStartTime()?p.getStartTime():"");
					paramMap.put("stopTime", null!=p.getStopTime()?p.getStopTime():"");
					paramMap.put("vipSharePrice", null!=p.getVipSharePrice()?p.getVipSharePrice():"");
					paramMap.put("vipStartTime", null!=p.getVipStartTime()?p.getVipStartTime():"");
					paramMap.put("vipStopTime", null!=p.getVipStopTime()?p.getVipStopTime():"");
					paramMap.put("parkingLatitude", null!=p.getParkingLatitude()?p.getParkingLatitude():"");//维度
					paramMap.put("parkingLongitude", null!=p.getParkingLongitude()?p.getParkingLongitude():"");//经度
					if(null!=p.getParkingPath()){
						paramMap.put("parkingPath", basePath+p.getParkingPath());
					}
					paramMap.put("isDirect", null!=p.getIsDirect()?p.getIsDirect():"");
					paramMap.put("isCooperate", null!=p.getIsCooperate()?p.getIsCooperate():"");
					paramMap.put("isOpen", null!=p.getIsOpen()?p.getIsOpen():"");
					paramMap.put("isIndex", null!=p.getIsIndex()?p.getIsIndex():"");
					paramMap.put("canUse", null!=p.getCanUse()?p.getCanUse():"");
					paramMap.put("parkingCanUse", null!=p.getParkingCanUse()?p.getParkingCanUse():"");
					paramMap.put("parkingAddress", p.getParkingAddress() == null ? "" : p.getParkingAddress());
					paramMap.put("isCharge", p.getIsCharge() == null ? "0" : p.getIsCharge().toString());
					paramMap.put("parkingCount", p.getParkingCount() == null ? 0 : p.getParkingCount());
					paramMap.put("parkPriceComment",p.getParkPriceComment() == null ? "" :p.getParkPriceComment());
					String llen = "";
					if(null!=p.getLen()){
						llen = p.getLen().split("\\.")[0];
					}
					paramMap.put("len", llen);
					list.add(paramMap);
				}
				message = ShangAnMessageType.toShangAnJson("0", "查询成功", "parkingList", list);
			}else{
				message = ShangAnMessageType.toShangAnJson("1", "周围暂无停车场服务", "parkingList", list);
			}
			out = response.getWriter();
			out.print(message);
		} catch (IOException e) { 
			e.printStackTrace();
			logger.error("", e);
		}
	}

	@RequestMapping("getIsParking")
	public void getIsParking(String parkingId,
							 String parkingLatitude,
							 String parkingLongitude,
							 String version,
							 HttpServletRequest request,
							 HttpServletResponse response){
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String message = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");
		try {
			Parking parking = new Parking();
			parking.setParkingId(parkingId);
			parking.setParkingLatitude(parkingLatitude);
			parking.setParkingLongitude(parkingLongitude);
			String dayOfWeek = DateUtil.getDayOfWeek(new Date(), 0);
			String weekStr = discountParkingPriceService.numToStr(dayOfWeek);
			parking.setVipStartTime(weekStr + "_begin_time");
			parking.setVipStopTime(weekStr + "_end_time");
			parking.setVipSharePriceComment(weekStr + "_price");
			List<Parking> parkingList = parkingService.getListParking(parking);
			if(null!=parkingList&&parkingList.size()>0){
				for(Parking p:parkingList){
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("parkingId", null!=p.getParkingId()?p.getParkingId():"");
					paramMap.put("parkingName", null!=p.getParkingName()?p.getParkingName():"");
					paramMap.put("peacetimePrice", null!=p.getPeacetimePrice()?p.getPeacetimePrice():"");
					paramMap.put("sharePrice", null!=p.getSharePrice()?p.getSharePrice():"");
					paramMap.put("startTime", null!=p.getStartTime()?p.getStartTime():"");
					paramMap.put("stopTime", null!=p.getStopTime()?p.getStopTime():"");
					paramMap.put("vipSharePrice", null!=p.getVipSharePrice()?p.getVipSharePrice():"");
					paramMap.put("vipStartTime", null!=p.getVipStartTime()?p.getVipStartTime():"");
					paramMap.put("vipStopTime", null!=p.getVipStopTime()?p.getVipStopTime():"");
					paramMap.put("parkingLatitude", null!=p.getParkingLatitude()?p.getParkingLatitude():"");//维度
					paramMap.put("parkingLongitude", null!=p.getParkingLongitude()?p.getParkingLongitude():"");//经度
					if(null!=p.getParkingPath()){
						paramMap.put("parkingPath", basePath+p.getParkingPath());
					}
					paramMap.put("isDirect", null!=p.getIsDirect()?p.getIsDirect():"");
					paramMap.put("isCooperate", null!=p.getIsCooperate()?p.getIsCooperate():"");
					paramMap.put("isOpen", null!=p.getIsOpen()?p.getIsOpen():"");
					paramMap.put("isIndex", null!=p.getIsIndex()?p.getIsIndex():"");
					paramMap.put("canUse", null!=p.getCanUse()?p.getCanUse():"");
					paramMap.put("parkingCanUse", null!=p.getParkingCanUse()?p.getParkingCanUse():"");
					paramMap.put("parkingAddress", p.getParkingAddress() == null ? "" : p.getParkingAddress());
					paramMap.put("isCharge", p.getIsCharge() == null ? "0" : p.getIsCharge().toString());
					paramMap.put("parkingCount", p.getParkingCount() == null ? 0 : p.getParkingCount());
					paramMap.put("parkPriceComment",p.getParkPriceComment() == null ? "" :p.getParkPriceComment());
					String llen = "";
					if(null!=p.getLen()){
						llen = p.getLen().split("\\.")[0];
					}
					paramMap.put("len", llen);
					list.add(paramMap);
				}
				message = ShangAnMessageType.toShangAnJson("0", "查询成功", "parkingList", list);
			}else{
				message = ShangAnMessageType.toShangAnJson("1", "周围暂无停车场服务", "parkingList", list);
			}
			out = response.getWriter();
			out.print(message);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("", e);
		}
	}

	/**
	 * 查询能够代泊的车场
	 */
	@RequestMapping("canParkList")
	public void canParkList(@RequestParam String version,
							String lng,
							String lat,
							HttpServletRequest request,
							HttpServletResponse response) throws IOException {
		String message;
		try {
			List<?> list = parkingService.canParkList(lng, lat);
			message = ShangAnMessageType.toShangAnJson("0", "success", "data", list);
		} catch (Exception e) {
			logger.error("", e);
			message = ShangAnMessageType.operateToJson("2", "异常");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(message);
	}

	/**
	 * 预约停车
	 */
	@RequestMapping("reservedParking")
	public void reservedParking(@RequestParam String version,
								@RequestParam String parkingId,
								HttpServletRequest request,
								HttpServletResponse response) throws IOException {
		String message;
		try {
			List<?> week = parkingService.getWeekPriceModel(parkingId);
			Parking parking = parkingService.queryById(parkingId);
			Map<String, Object> map = new HashMap<>(2);
			map.put("week", week);
			map.put("sharePriceComment", parking.getSharePriceComment() == null ? "" : parking.getSharePriceComment());
			message = ShangAnMessageType.toShangAnJson("0", "success", "data", map);
		} catch (Exception e) {
			logger.error("", e);
			message = ShangAnMessageType.operateToJson("2", "异常");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(message);
	}
	/**
	 * 获取车的空位数和空位状态
	 */
	@RequestMapping("getParkingStatus")
	public void getParkingStatus(@RequestParam String parkingId,
								 @RequestParam String version,
								 HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String message = null;
		if("2.0.0".equals(version)){
			message = parkingStatusService.getParkingStatus(parkingId);
		}else if("2.0.1".equals(version)){
			message = parkingStatusService.getParkingStatus2_0_1(parkingId);
		}else{
			message = ShangAnMessageType.operateToJson("2","版本错误");
		}

		response.getWriter().print(message);
	}
	/**
	 * 选择星期
	 */
	@RequestMapping("choseWeek")
	public void choseWeek(@RequestParam String version,
						  @RequestParam String parkingId,
						  @RequestParam String week,
						  HttpServletRequest request,
						  HttpServletResponse response) throws IOException {
		String message;
		try {
			String[] weeks = week.split(",");
			for (int i = 0; i <weeks.length ; i++) {
				if (weeks[i].equals("1")){
					weeks[i]="一";
				}else if (weeks[i].equals("2")){
					weeks[i]="二";
				}else if (weeks[i].equals("3")){
					weeks[i]="三";
				}else if (weeks[i].equals("4")){
					weeks[i]="四";
				}else if (weeks[i].equals("5")){
					weeks[i]="五";
				}else if (weeks[i].equals("6")){
					weeks[i]="六";
				}else if (weeks[i].equals("7")){
					weeks[i]="日";
				}

			}
			List<PackagePrice> list = packagePriceService.queryByParkingIdAndWeek(parkingId, weeks);
			message = ShangAnMessageType.toShangAnJson("0", "success", "data", list);
		} catch (Exception e) {
			logger.error("", e);
			message = ShangAnMessageType.operateToJson("2", "异常");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(message);
	}

	/**
	 * 查询车位紧张的时间段
	 */
	@RequestMapping("tenseTime")
	public void tenseTime(@RequestParam String parkingId,
						  String version,
						  HttpServletRequest request,
						  HttpServletResponse response) throws IOException {
		String message;
		try {
			Map<String, Object> map = new HashMap<>(1);
			map.put("parkingId", parkingId);
			List<ParkingStatus> list = parkingStatusService.tenseTime(map);
			if (list != null && list.size() > 0) {
				message = ShangAnMessageType.toShangAnJson("0", "success", "data", String.format("%02d:00", list.get(0).getHourSection()));
			} else {
				message = ShangAnMessageType.toShangAnJson("1", "无数据", "data", "");
			}
		} catch (Exception e) {
			logger.error("", e);
			message = ShangAnMessageType.operateToJson("2", "异常");
		}
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(message);
	}
	/**
	 * 爱车生活获取车场列表
	 */
	@RequestMapping("carLov/getParkingList")
	public void getParkingList(@RequestParam String version,
							   @RequestParam Integer pageIndex,
							   @RequestParam(required=false) Integer pageSize,
						  HttpServletRequest request,
						  HttpServletResponse response) throws IOException {
		String message = ShangAnMessageType.operateToJson("2", "异常");
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		if("2.0.2".equals(version)) {
			List<Map<String, Object>> resultMapList = new ArrayList<Map<String, Object>>();
			try {
				Page<Parking> page = new Page<Parking>();
				page.setCurrentPage(pageIndex);
				if(null!=pageSize){
					page.setPageSize(pageSize);
				}

				List<Parking> parkingList = parkingService.getParkingListForCarLov(page);
				if (parkingList != null && parkingList.size() > 0) {
					for (Parking parking : parkingList) {
						Map<String, Object> paraMap = new HashMap<String, Object>();
						paraMap.put("parkingName", parking.getParkingName()==null?"":parking.getParkingName());
						paraMap.put("parkingArea", parking.getParkingArea()==null?"":parking.getParkingArea());
						paraMap.put("parkingAddress", parking.getParkingAddress()==null?"":parking.getParkingAddress());
						paraMap.put("parkingLatitude", parking.getParkingLatitude()==null?"":parking.getParkingLatitude());
						paraMap.put("parkingLongitude", parking.getParkingLongitude()==null?"":parking.getParkingLongitude());
						paraMap.put("parkingId", parking.getParkingId()==null?"":parking.getParkingId());
						paraMap.put("parkingPath",parking.getParkingPath()==null?"":basePath+parking.getParkingPath());
						resultMapList.add(paraMap);
					}
					message = ShangAnMessageType.toShangAnJson("0", "查询成功", "data", resultMapList);
				}
			} catch (Exception e) {
				logger.error("", e);
				message = ShangAnMessageType.operateToJson("2", "异常");
			}
		}else{
			message = ShangAnMessageType.operateToJson("2", "版本号错误");
		}
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(message);
	}
	/**
	 * 爱车生活微信二维码
	 */
	@RequestMapping("carLov/getCarlovQRCode")
	public void getParkingList(@RequestParam String version,
							   @RequestParam String parkingId,
							   HttpServletRequest request,
							   HttpServletResponse response) throws IOException {
		String message = null;
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		if("2.0.2".equals(version)) {
			Map<String,Object> resultMap = new HashMap<String, Object>();
			try {
				CarlovQrcode param = new CarlovQrcode();
				param.setParkingId(parkingId);
				List<CarlovQrcode> cList =  carlovQrcodeService.selectList(param);
				if(null!=cList&&cList.size()>0&&null!=cList.get(0)&&!StringUtils.isEmpty(cList.get(0).getQrcodeUrl())){
					//resultMap.put("qrcodeUrl",cList.get(0).getQrcodeUrl()==null?"":basePath+cList.get(0).getQrcodeUrl());
					message = ShangAnMessageType.toShangAnJson("0", "查询成功", "qrcodeUrl", basePath+cList.get(0).getQrcodeUrl());
				}else{
					//resultMap.put("qrcodeUrl","");
					message = ShangAnMessageType.toShangAnJson("1", "此社区暂无微信社区", "qrcodeUrl", "");
				}
			} catch (Exception e) {
				logger.error("", e);
				message = ShangAnMessageType.operateToJson("2", "异常");
			}
		}else{
			message = ShangAnMessageType.operateToJson("2", "版本号错误");
		}
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(message);
	}
	/**
	 * 爱车生活微信二维码
	 */
	@RequestMapping("carLov/getQiyuId")
	public void getQiyuId(@RequestParam String version,
							   @RequestParam String parkingId,
							   HttpServletRequest request,
							   HttpServletResponse response) throws IOException {
		String message = null;
		if("2.0.2".equals(version)) {
			Map<String,Object> resultMap = new HashMap<String, Object>();
			try {
				Parking param = new Parking();
				param.setParkingId(parkingId);
				List<Parking> cList =  parkingService.selectList(param);
				if(null!=cList&&cList.size()>0&&null!=cList.get(0)){
					Set<String> qId = new HashSet<String>();
					if(!StringUtils.isEmpty(cList.get(0).getQiyuId())){
						qId = getArryByString(cList.get(0).getQiyuId());
					}
					message = ShangAnMessageType.toShangAnJson("0", "查询成功", "qiyuId",qId);
				}else{
					message = ShangAnMessageType.operateToJson("1", "无数据");
				}
			} catch (Exception e) {
				logger.error("", e);
				message = ShangAnMessageType.operateToJson("2", "异常");
			}
		}else{
			message = ShangAnMessageType.operateToJson("2", "版本号错误");
		}
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(message);
	}
	public Set<String> getArryByString(String str){
		String [] strs = str.split("\\,");
		Set<String> set = new HashSet<String>(Arrays.asList(strs));
		return set;
	}
	//获取预约停车列表
	/**
	 * 爱车生活微信二维码
	 */
	@RequestMapping("carLov/getAppoPkList")
	public void getAppoPkList(@RequestParam String latitude,//维度
						  @RequestParam String longitude,//经度
						  @RequestParam String pageIndex,
						  @RequestParam String pageSize,
						  HttpServletRequest request,
						  HttpServletResponse response) throws IOException, ParseException {
		//设置utf-8
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		out = response.getWriter();
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("latitude",latitude);
		param.put("longitude",longitude);
		param.put("basePath",basePath);
		param.put("pageIndex",pageIndex);
		param.put("pageSize",pageSize);
		String result = parkingService.getAppoPkList(param);
		out.print(result);
	}
}
