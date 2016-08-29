package com.boxiang.share.app.carlife.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.share.product.carlife.po.CarRent;
import com.boxiang.share.product.carlife.po.CarlifeSrvBilling;
import com.boxiang.share.product.carlife.service.*;
import com.boxiang.share.system.po.Dictionary;
import com.boxiang.share.system.service.DictionaryService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.product.carlife.po.CarlifeRefuelCard;
import com.boxiang.share.utils.ShangAnMessageType;

@Controller
@RequestMapping("app/carlife")
public class CarlifeController extends BaseController {
	//轮播图片
	@Resource
	private CarlifeEventPageService carlifeEventPageService;
	//服务信息
	@Resource
	private CarlifeSrvInfoService carlifeSrvInfoService;
	//new标签
	@Resource
	private CarlifeSrvNewtagService carlifeSrvNewtagService;
	//车场收费服务信息
	@Resource
	private CarlifeSrvBillingService carlifeSrvBillingService;
	@Resource
	private CarlifeRefuelCardService carlifeRefuelCardService;
	@Resource
	private OrderRefuelService orderRefuelService;

	@Resource
	private CarRentService carRentService;

	@Resource
	private DictionaryService dictionaryService;
	
	private static final Logger logger = Logger.getLogger(CarlifeController.class);
	/**
	 * 获取加油卡列表
	 * @param customerId
	 * @param request
	 * @param response
	 */
	@RequestMapping("refuel/cardlist/{customerId}/{summary}")
	public void cardList(@PathVariable String customerId,HttpServletRequest request,HttpServletResponse response){
		
		String message = null;
		CarlifeRefuelCard carlifeRefuelCard = new CarlifeRefuelCard();
		carlifeRefuelCard.setCustomerId(customerId);
		carlifeRefuelCard.setIsUsed(Constants.TRUE);
		try{
			List<CarlifeRefuelCard> refuelCardList 
				= carlifeRefuelCardService.selectList(carlifeRefuelCard);
			if(refuelCardList!=null && refuelCardList.size()>0){
				List<Map<String, String>> carMapList = new ArrayList<Map<String,String>>(); 
				for(CarlifeRefuelCard carlife : refuelCardList){
					Map<String, String> refuelMap = new HashMap<String, String>();
					refuelMap.put("id", carlife.getId().toString());
					refuelMap.put("customerId",customerId);
					refuelMap.put("cardNo",carlife.getCardNo());
					refuelMap.put("cardName",carlife.getCardName());
					refuelMap.put("cardMobile", carlife.getCardMobile());
					refuelMap.put("cardType", carlife.getCardType());
					carMapList.add(refuelMap);
				}
				message = ShangAnMessageType.toShangAnJson("0", "success", "refuelCard", carMapList);
			}else{
				message = ShangAnMessageType.operateToJson("1", "无数据");
			}
		}catch(Exception e){
			e.printStackTrace();
			message = ShangAnMessageType.operateToJson("2", "查询失败");
		}
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
	 * 添加加油卡
	 * @param request
	 * @param response
	 */
	@RequestMapping("refuel/cardc")
	public void cardc(HttpServletRequest request,HttpServletResponse response){
		String customerId = request.getParameter("customerId");
		String cardNo = request.getParameter("cardNo");
		String cardName = request.getParameter("cardName");
		String cardMobile = request.getParameter("cardMobile");
		String cardType = request.getParameter("cardType");
		CarlifeRefuelCard crc = new CarlifeRefuelCard();
		crc.setCustomerId(customerId);
		crc.setCardMobile(cardMobile);
		crc.setCardName(cardName);
		crc.setCardNo(cardNo);
		crc.setCardType(cardType);
		crc.setCreateDate(new Date());
		crc.setIsUsed(Constants.TRUE);
		crc.setCreateor("admin");
		carlifeRefuelCardService.add(crc);
		String message = ShangAnMessageType.operateToJson("0", "添加成功");
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
	 *  加油卡订单列表   分页
	 * @param request
	 * @param response
	 */
	@RequestMapping("refuel/orderlist/{customerId}/{pageIndex}/{summary}")
	public void cardc(@PathVariable String customerId,@PathVariable String pageIndex,HttpServletRequest request,HttpServletResponse response){
		String message =null;
				
		message = orderRefuelService.getRefuelList(customerId, pageIndex);
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
	 * app获取轮播图片
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("eventpage/list/{summary}")
	public ModelAndView eventPageList(HttpServletRequest request, HttpServletResponse response) {
		//type=1返回循环图片 type=2返回车管家图片
		String mess = carlifeEventPageService.getEventPageMessage(request, response);
		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(mess);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * app服务信息获取
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("srvinfo/list/{summary}")
	public void srvInfoList(HttpServletRequest request, HttpServletResponse response) {
		String mess = carlifeSrvInfoService.getCarlifeSrvInfoMessage(request, response);
		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");	
		response.setHeader("Access-Control-Allow-Origin","*"); 
		try {
			out = response.getWriter();
			out.print(mess);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return null;
	}
	
	/**
	 * app修改New标签
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("srvinfo/newtag/{mobile}/{srvId}/{summary}")
	public ModelAndView updateNewFlag(@PathVariable String mobile,@PathVariable String srvId,HttpServletRequest request, HttpServletResponse response) {
		String mess = carlifeSrvNewtagService.updateNewFlag(request, response, mobile, srvId);
		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(mess);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * app过得车管家收费信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("srvinfo/intro/{parkingId}/{srvId}/{summary}")
	public ModelAndView getSvrFeeInfo(@PathVariable String parkingId,@PathVariable String srvId,HttpServletRequest request, HttpServletResponse response) {
		String mess = carlifeSrvBillingService.getSvrFeeInfo(parkingId,srvId);
		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(mess);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 租车界面
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping("carRent/list/{summary}")
	public void carRentList(HttpServletRequest request, HttpServletResponse response) {
		String message = null;
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		try {
			CarRent carRent = new CarRent();
			carRent.setIsUsed(Constants.TRUE);
			List<CarRent> rentList = carRentService.selectList(carRent);
			if (rentList != null && rentList.size() > 0) {
				Map<String, Object> commonRent = new HashMap<String, Object>();
				Map<String, Object> timeRent = new HashMap<String, Object>();
				List<Map<String, String>> commonRentCarList = new ArrayList<Map<String, String>>();
				List<Map<String, String>> timeRentCarList = new ArrayList<Map<String, String>>();
				commonRent.put("carList", commonRentCarList);
				timeRent.put("carList", timeRentCarList);
				List<Dictionary> dictList = dictionaryService.getDictListByDictCode("car_rent_title");
				for (Dictionary dict : dictList) {
					if (dict.getDictValue().equals("1")) {
						commonRent.put("title", dict.getMemo());
					} else if (dict.getDictValue().equals("2")) {
						timeRent.put("title", dict.getMemo());
					}
				}
				for (CarRent rent : rentList) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("name", rent.getName() == null ? "" : rent.getName());
					map.put("price", Double.toString((int)rent.getPrice() / 100.00));
					map.put("type", Integer.toString(rent.getType()));
					map.put("imagePath", rent.getImagePath() == null ? "" : basePath + rent.getImagePath());
					map.put("jumpUrl", rent.getJumpUrl() == null ? "" : rent.getJumpUrl());
					if (rent.getType() == 1) {
						commonRentCarList.add(map);
					} else if (rent.getType() == 2) {
						timeRentCarList.add(map);
					}
				}
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("commonRent", commonRent);
				data.put("timeRent", timeRent);
				message = ShangAnMessageType.toShangAnJson("0", "success", "data", data);
			} else {
				message = ShangAnMessageType.operateToJson("1", "无数据");
			}
		} catch (Exception e) {
			logger.error("", e);
			message = ShangAnMessageType.operateToJson("2", "查询失败");
		}

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
	 * 查询洗车规则列表
	 * @param parkingId
	 * @param request
	 * @param response
	 */
	@RequestMapping("queryCharge/{parkingId}/{summary}")
	public void queryCharge(@PathVariable String parkingId,HttpServletRequest request,HttpServletResponse response){
		String messge=null;
		messge=carlifeSrvInfoService.queryCharge(parkingId);
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(messge);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		}
	}

	/**
	 * 查询洗车人员头像
	 * @param parkingId
	 * @param request
	 * @param response
	 */
	@RequestMapping("queryImage/{parkingId}/{summary}")
	public void queryImage(@PathVariable String parkingId,HttpServletRequest request,HttpServletResponse response){
		String messge=null;
		messge=carlifeSrvInfoService.queryImage(parkingId,request);
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
			out.print(messge);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		}
	}
	/**
	 * 查询可以提供服务的车场
	 * @param
	 * @param request
	 * @param response
	 */
	@RequestMapping("getParkingList")
	public void getParkingList(HttpServletRequest request,HttpServletResponse response){
		String messge=null;
		String srvId=request.getParameter("srvId");
		String version=request.getParameter("version");
		messge= carlifeSrvBillingService.selectListParking(srvId);
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
			out.print(messge);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		}
	}
}
