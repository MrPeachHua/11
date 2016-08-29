package com.boxiang.share.product.carlife.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.carlife.po.CarlifeSrvBilling;
import com.boxiang.share.product.carlife.po.CarlifeSrvInfo;
import com.boxiang.share.product.carlife.service.CarlifeSrvBillingService;
import com.boxiang.share.product.carlife.service.CarlifeSrvInfoService;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.system.po.Dictionary;
import com.boxiang.share.system.service.DictionaryService;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.utils.json.JacksonUtil;

@Controller
@RequestMapping("products/carlife/srvbilling")
public class CarlifeSrvBillingController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(CarlifeSrvBillingController.class);
	
	@Resource private CarlifeSrvBillingService carlifeSrvBillingService;
	@Resource private CarlifeSrvInfoService carlifeSrvInfoService;
	@Resource private DictionaryService dictionaryService;
	@Resource private ParkingService parkingService;
	@RequestMapping("list.html")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		try {
			Page<CarlifeSrvBilling> page = new Page<CarlifeSrvBilling>();
			PageHelper.initPage(request, page);
			page.getParams().put("isUsed", Constants.TRUE);
			Map<String, Object> map = super.getParamMap(request);
			String queryType = (String)map.get("queryType");
			String queryValue = (String)map.get("queryValue");
			if(!StringUtils.isEmpty(queryType)){
				switch (Integer.parseInt(queryType)) {
				case 1://车场名称
					page.getParams().put("parkingName", queryValue);
					break;
				case 2://服务名称
					page.getParams().put("srvName", queryValue);
					break;
				default:
					break;
				}
			}
			page = carlifeSrvBillingService.queryListPage(page);
			PageHelper.setPageModel(request, page);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询服务信息出现异常",e);
		}
		return new ModelAndView("products/carlife/srvbilling_list");
	}
//删除
	@RequestMapping("{id}/del.html")
	public ModelAndView msgdel(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		if(id==0){
			throw new NullPointerException("车场与服务计费信息ID不能为空!");
		}
		try {
			CarlifeSrvBilling carlifeSrvBilling=carlifeSrvBillingService.queryById(id);
			carlifeSrvBilling.setIsUsed(Constants.FALSE);
			carlifeSrvBillingService.update(carlifeSrvBilling);
			request.setAttribute("info", "删除成功");
		} catch (NumberFormatException nfe) {
			logger.error("车场与服务计费信息ID转换类型失败，请检查参数是否正确！userId="+id+".",nfe);
			throw new NumberFormatException("车场与服务计费信息ID转换类型失败，请检查参数是否正确！"+id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除车场与服务计费信息出现异常",e);
		}
		return new ModelAndView("products/carlife/srvbilling_save",null);
	}
	//跳转添加
	@RequestMapping("add.html")
	public ModelAndView msgadd(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = super.getParamMap(request);
		Dictionary dictionary=new Dictionary();
		dictionary.setDictCode("car_type");
	    List<Dictionary> dictionaryList=dictionaryService.selectList(dictionary);
		map.put("dictionary", dictionaryList);
		return new ModelAndView("products/carlife/srvbilling_add",map);
	}
	//跳转添加
		@RequestMapping("pop.html")
		public ModelAndView pop(HttpServletRequest request, HttpServletResponse response) {
			Map<String,Object> map = super.getParamMap(request);
			Dictionary dictionary=new Dictionary();
			dictionary.setDictCode("car_type");
		    List<Dictionary> dictionaryList=dictionaryService.selectList(dictionary);
			map.put("dictionary", dictionaryList);
			return new ModelAndView("products/carlife/srvbilling_pop",map);
		}
	//跳转编辑
		@RequestMapping("{id}/edit.html")
		public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,@PathVariable int id) {
			if(id==0){
				throw new NullPointerException("ID不能为空!");
			}	
			Map<String,Object> map = super.getParamMap(request);
			try {
				Dictionary dictionary=new Dictionary();
				dictionary.setDictCode("car_type");
			List<Dictionary> dictionaryList=	dictionaryService.selectList(dictionary);
			CarlifeSrvBilling carlifeSrvBilling = carlifeSrvBillingService.queryById(id);
				map.put("carlifeSrvBilling", carlifeSrvBilling);
				map.put("dictionary", dictionaryList);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("信息出现异常",e);
			}
			return new ModelAndView("products/carlife/srvbilling_edit",map);
		}
	//添加
	@RequestMapping("save.html")
	public ModelAndView magsave(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("carlifeSrvBilling") CarlifeSrvBilling carlifeSrvBilling) {
		try {
			CarlifeSrvBilling carlifeSrvBilling2=new CarlifeSrvBilling();
			carlifeSrvBilling2.setParkingId(carlifeSrvBilling.getParkingId());
			carlifeSrvBilling2.setSrvId(carlifeSrvBilling.getSrvId());
			carlifeSrvBilling2.setCarType(carlifeSrvBilling.getCarType());
			carlifeSrvBilling2.setIsUsed(Constants.TRUE);
		    List<CarlifeSrvBilling> list=carlifeSrvBillingService.selectList(carlifeSrvBilling2);
			if (list!=null&&list.size()>0) {
				request.setAttribute("info", "存在重复数据！");
				request.setAttribute("add", "info");
			} else {
				UserInfo currUser = (UserInfo) super.getLoginUser(request);
				carlifeSrvBilling.setCreateor(currUser.getUserNum());
				carlifeSrvBilling.setCreateDate(new Date());
				carlifeSrvBilling.setIsUsed(Constants.TRUE);
				carlifeSrvBillingService.add(carlifeSrvBilling);
				request.setAttribute("info", "添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加服务信息出现异常",e);
		}
		return new ModelAndView("products/carlife/srvbilling_save",null);
	}
	//修改
	@RequestMapping("update.html")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("carlifeSrvBilling") CarlifeSrvBilling carlifeSrvBilling) {
		try {
			
			CarlifeSrvBilling carlifeSrvBilling2=new CarlifeSrvBilling();
			carlifeSrvBilling2.setParkingId(carlifeSrvBilling.getParkingId());
			carlifeSrvBilling2.setSrvId(carlifeSrvBilling.getSrvId());
			carlifeSrvBilling2.setCarType(carlifeSrvBilling.getCarType());
			carlifeSrvBilling2.setIsUsed(Constants.TRUE);
		    List<CarlifeSrvBilling> list=carlifeSrvBillingService.selectList(carlifeSrvBilling2);
		    boolean flag = true;
			if (list!=null&&list.size()>1) {
				flag = false;
			} else if(list.size()==1){
				if(!carlifeSrvBilling.getId().equals(list.get(0).getId())){
					flag = false;
				}
			}
			if(flag){
				UserInfo currUser = (UserInfo) super.getLoginUser(request);
				carlifeSrvBilling.setModified(currUser.getUserNum());
				carlifeSrvBilling.setModifyDate(new Date());
				//	carlifeSrvInfo.setLogoPath(request.getParameter("savePath"));
					//carlifeSrvInfo.setIsUsed(Constants.TRUE);
					//carlifeSrvInfo.setStatus(Constants.TRUE);
				carlifeSrvBillingService.update(carlifeSrvBilling);
				request.setAttribute("info", "修改成功");
			}else{
				request.setAttribute("info", "存在重复数据！");
				request.setAttribute("add", "info");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改服务信息出现异常",e);
		}
		return new ModelAndView("products/carlife/srvbilling_save",null);
	}
	@RequestMapping("parkingData.html")
	public void getParkingData(HttpServletRequest request , HttpServletResponse response)
	{
		response.setContentType("text/html;charset=UTF-8");
		List<Parking> parkingList = new ArrayList<Parking>();
		UserInfo user = (UserInfo) super.getLoginUser(request);
		Parking parking = new Parking();
		String queryValue = request.getParameter("queryValue");
		String queryType = request.getParameter("queryType");
		String region = request.getParameter("region");
		if(!StringUtils.isEmpty(queryType)){
			switch (Integer.parseInt(queryType)) {
			case 1://车场代码
				parking.setParkingId(queryValue);
				break;
			case 2://车场名称
				parking.setParkingName(queryValue);
				break;
			case 3://车场地址
				parking.setParkingAddress(queryValue);
				break;
			default:
				break;
			}
		}
		if(!StringUtils.isEmpty(region)){
			parking.setRegion(region);
		}
		if(user.getParkingId()!=null&&!"".equals(user.getParkingId())){
         String[] parkingId=user.getParkingId().split(",");
			if(parkingId!=null&&!parkingId.equals("")){
				for (int i = 0; i <parkingId.length ; i++) {
					parking.setParkingId(parkingId[i]);
				    List list=	parkingService.selectList(parking);
					parkingList.addAll(list);
				}
			}
		}else {
			parkingList = parkingService.selectList(parking);
		}
		for(Parking pk : parkingList){
			if(pk.getParkingArea()==null){
				pk.setParkingArea("");
			}
			if(pk.getParkingAddress()==null){
				pk.setParkingAddress("");
			}
		}
		try {
			response.getWriter().print(JacksonUtil.toJson(parkingList));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("srvinfoPop.html")
	public void srvinfoPop(HttpServletRequest request , HttpServletResponse response)
	{
		response.setContentType("text/html;charset=UTF-8");
		List<CarlifeSrvInfo> srvinfoList = new ArrayList<CarlifeSrvInfo>();
		CarlifeSrvInfo carlifeSrvInfo = new CarlifeSrvInfo();
		carlifeSrvInfo.setIsUsed(Constants.TRUE);
		String queryValue = request.getParameter("queryValue");
		String queryType = request.getParameter("queryType");
		if(!StringUtils.isEmpty(queryType)){
			switch (Integer.parseInt(queryType)) {
			case 1://车场代码
				carlifeSrvInfo.setSrvName(queryValue);
				break;
			default:
				break;
			}
		}
		srvinfoList = carlifeSrvInfoService.selectList(carlifeSrvInfo);
		try {
			response.getWriter().print(JacksonUtil.toJson(srvinfoList));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
