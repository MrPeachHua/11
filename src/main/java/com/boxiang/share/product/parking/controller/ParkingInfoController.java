package com.boxiang.share.product.parking.controller;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.order.po.*;
import com.boxiang.share.product.order.service.*;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.po.ParkingStatus;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.product.parking.service.ParkingStatusService;
import com.boxiang.share.system.po.Sequence;
import com.boxiang.share.system.service.DictionaryService;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.system.synwhite.po.Specialparkinginfo;
import com.boxiang.share.system.synwhite.service.SpecialparkinginfoService;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.user.service.UserInfoService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.TableNameConstants;
import net.sf.json.JSONArray;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.boxiang.share.system.po.Dictionary;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 最后修改于   20160426 已经很难懂了
 */
@Controller
@RequestMapping("products/parking")
public class ParkingInfoController extends BaseController {
	private static final Logger logger = Logger.getLogger(ParkingInfoController.class);

	@Resource private MonthlyparkinginfoService monthlyparkinginfoService;
	@Resource private ParkingService parkingService;
	@Resource private SpecialparkinginfoService specialparkinginfoService;
	@Resource private SequenceService sequenceService;
	@Resource private OrderMainService orderMainService;
	@Resource private OrderMonthlyService orderMonthlyService;
	@Resource private OrderEquityService orderEquityService;
	@Resource private PropertyparkinginfoService propertyparkinginfoService;
	@Resource private CustomerService customerService;
	@Resource private UserInfoService userInfoService;
	@Resource private ParkingStatusService parkingStatusService;
	@Resource private DictionaryService dictionaryService;

	@RequestMapping("monthly/list.html")
	public ModelAndView monthlyList(HttpServletRequest request, HttpServletResponse response) {
		try {
			Page<Monthlyparkinginfo> page = new Page<Monthlyparkinginfo>();
			PageHelper.initPage(request, page);
//			page.getParams().put("isUsed", Constants.TRUE);
			Map<String, Object> map = super.getParamMap(request);
			String queryType = (String)map.get("queryType");
			String queryValue = (String)map.get("queryValue");
			if(!StringUtils.isEmpty(queryType)){
				switch (Integer.parseInt(queryType)) {
				case 1://小区名称
					page.getParams().put("villageName", queryValue);
					break;
				case 2://车牌号
					page.getParams().put("carNumber", queryValue);
					break;
				default:
					break;
				}
			}
			UserInfo user = (UserInfo) super.getLoginUser(request);
			String module = user.getModule();
			user = userInfoService.queryById(user.getUserId());
			if(user!=null && user.getParkingId()!=null&&!user.getParkingId().equals("")){
				String [] pkStr = user.getParkingId().split("\\,");
				if(null!=pkStr&&pkStr.length>0){
					String parkingStr = "";
					for(int i=0;i<pkStr.length;i++){
						parkingStr +="'";
						parkingStr+=pkStr[i];
						parkingStr+="'";
						parkingStr+=",";
					}
					parkingStr = parkingStr.substring(0,parkingStr.length()-1);
				page.getParams().put("villageId",parkingStr);
				}
			}else {
				page.getParams().put("villageId",null);
			}
			String isUsed = request.getParameter("isUsed");
			if (isUsed==null)
				isUsed ="1";
			else
			if (isUsed!=null && isUsed.equals("3"))
				isUsed = null;
			page.getParams().put("isUsed",isUsed);
			page.getParams().put("module", module);
			page = monthlyparkinginfoService.queryListPage(page);
			List<Monthlyparkinginfo> list = page.getResultList();
			for (Monthlyparkinginfo monthlyparkinginfo :list)
			{
				if (monthlyparkinginfo!=null && monthlyparkinginfo.getEffect_end_time()!=null && !"".equals(monthlyparkinginfo.getEffect_end_time()))
				{
					String endDate = new SimpleDateFormat("yyyy-MM-dd").format(monthlyparkinginfo.getEffect_end_time());
					String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
					if (endDate.compareTo(nowDate)<0)
					{
						monthlyparkinginfo.setIsExpired("0");
					}else {
						monthlyparkinginfo.setIsExpired("1");
					}
				}else {
					monthlyparkinginfo.setIsExpired("1");
				}
//				if (new SimpleDateFormat("yyyy-MM-dd").format(monthlyparkinginfo.getEffect_end_time()))
			}
			if (isUsed==null)
			{
				page.getParams().put("isUsed","3");
			}
			PageHelper.setPageModel(request, page);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入列表页出现异常",e);
		}
		return new ModelAndView("products/parking/monthlyinfo_list");
	}
	@RequestMapping("monthly/addFree.html")
	public void addFree(HttpServletRequest request,HttpServletResponse response){
		try {
			String orderType = request.getParameter("orderType");//13月租 14产权
			String carNumber = request.getParameter("carNumber");//车牌号
			String villageId = request.getParameter("text1");//小区ID
			String payType = request.getParameter("payType");//收款平台
			String price = request.getParameter("price");//单价
			String effectiveBeginTime = request.getParameter("form_beginTime");//有效开始时间
			String effectiveBeginTime1 = request.getParameter("form_beginTime1");
			String monthNum = request.getParameter("monthNum");//月份数
			String effectiveEndTime = request.getParameter("form_endTime");//有效结束时间
			String orderStatus = request.getParameter("orderStatus");//订单状态
			String amountDiscount = request.getParameter("amountDiscount");//优惠金额
			String invoiceStatus = request.getParameter("invoiceStatus");//开票
			String payTime = request.getParameter("payTime");//支付时间
				Date date = new SimpleDateFormat("yyyy-MM").parse(effectiveEndTime);//当前月最后一天
			String[] zh = DateUtil.getMonthStartAndEndDate(date);

			Parking parking=parkingService.queryById(villageId);
			OrderMain om = new OrderMain();
			Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_ORDER_MAIN);
			om.setOrderId((null==parking.getParkingIdentifier()?"":parking.getParkingIdentifier())+orderType+sequence.getId());
			om.setOrderType(orderType);
			int youhui =0;
			if(amountDiscount!=null&&!amountDiscount.equals("")){
				youhui = Integer.parseInt(amountDiscount) * 100;
			}
			int yingfu = Integer.parseInt(monthNum) * Integer.parseInt(price) * 100;
			int shifu = yingfu - youhui;
			if (youhui <= yingfu) {
				om.setAmountPaid(shifu);
			} else {
				om.setAmountPaid(0);
			}
			om.setAmountPayable(yingfu);
			om.setInvoiceStatus(invoiceStatus);
			om.setPayType(payType);
			om.setAmountDiscount(youhui);
			if(orderStatus!=null && orderStatus.equals("11"))
				om.setPayTime(DateUtil.str2date(payTime, DateUtil.DATETIME_FORMAT));
			UserInfo user = (UserInfo) getLoginUser(request);
			om.setOrderStatus(orderStatus);
			om.setParkingId(villageId);
			om.setCreateor(user.getUserNum());
			om.setCreateDate(new Date());
			om.setIsUsed(Constants.TRUE);
			Monthlyparkinginfo monthlyparkinginfo6=new Monthlyparkinginfo();
			monthlyparkinginfo6.setVillageId(villageId);
			monthlyparkinginfo6.setIsUsed(Constants.TRUE);
			monthlyparkinginfo6.setCarNumber(carNumber);
			List<Monthlyparkinginfo> listmon=monthlyparkinginfoService.selectList(monthlyparkinginfo6);
			if(listmon!=null&&listmon.size()>0){
				Customer customer= customerService.queryByMobileV2(listmon.get(0).getPhone());
				if(customer!=null&&!"".equals(customer)){
					om.setCustomerId(customer.getCustomerId());//用户Id
				}else {
					om.setCustomerId("");//用户Id
				}
			}
			orderMainService.add(om);
			OrderMonthly orderMonthly = new OrderMonthly();
			orderMonthly.setCarNumber(carNumber);
			orderMonthly.setBeginDate(DateUtil.str2date(effectiveBeginTime1, DateUtil.DATE_FORMAT));
			orderMonthly.setEndDate(DateUtil.str2date(zh[1], DateUtil.DATE_FORMAT));
			orderMonthly.setMonthNum(Integer.parseInt(monthNum));
			orderMonthly.setOrderId(om.getOrderId());
			orderMonthly.setPrice(price != null ? Integer.parseInt(price)*100 : 0);
			orderMonthly.setParkingId(villageId);
			orderMonthly.setIsUsed(Constants.TRUE);
			orderMonthly.setCreateDate(new Date());
			orderMonthly.setCreateor(user.getUserNum());
			orderMonthlyService.add(orderMonthly);
			Monthlyparkinginfo monthlyparkinginfo = new Monthlyparkinginfo();
			monthlyparkinginfo.setCarNumber(carNumber);
			monthlyparkinginfo.setVillageId(villageId);
			if (effectiveEndTime!=null && !"".equals(effectiveEndTime))
			{
				String endDate = DateUtil.getMonthStartAndEndDate(DateUtil.str2date(effectiveEndTime,"yy-MM"))[1];
				monthlyparkinginfo.setEffect_end_time(DateUtil.str2date(endDate,"yyyy-MM-dd"));
			}
			monthlyparkinginfo.setEffect_begin_time(DateUtil.str2date(effectiveBeginTime,"yyyy-MM-dd"));
			monthlyparkinginfoService.updateParkingTime(monthlyparkinginfo);
			request.setAttribute("info", "1");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("info", "续费失败");
			logger.error("添加续费出现异常", e);
		}
		try {
			request.getRequestDispatcher("list.html").forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		return new ModelAndView("products/parking/monthly/list.html",null);
	}
	@RequestMapping("equity/addFree.html")
	public void addFreeE(HttpServletRequest request,HttpServletResponse response){
		try {
			String orderType = request.getParameter("orderType");//13月租 14产权
			String carNumber = request.getParameter("carNumber");//车牌号
			String villageId = request.getParameter("text1");//小区ID
			String payType = request.getParameter("payType");//收款平台
			String price = request.getParameter("price");//单价
			String effectiveBeginTime = request.getParameter("form_beginTime");//有效开始时间
			String effectiveBeginTime1 = request.getParameter("form_beginTime1");
			String monthNum = request.getParameter("monthNum");//月份数
			String effectiveEndTime = request.getParameter("form_endTime");//有效结束时间
			String orderStatus = request.getParameter("orderStatus");//订单状态
			String amountDiscount = request.getParameter("amountDiscount");//优惠金额
			String invoiceStatus = request.getParameter("invoiceStatus");//开票
			String payTime = request.getParameter("payTime");//支付时间
			Date date = new SimpleDateFormat("yyyy-MM").parse(effectiveEndTime);//当前月最后一天
			String[] zh = DateUtil.getMonthStartAndEndDate(date);
			Parking parking=parkingService.queryById(villageId);
			OrderMain om = new OrderMain();
			Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_ORDER_MAIN);
			om.setOrderId((null==parking.getParkingIdentifier()?"":parking.getParkingIdentifier())+orderType+sequence.getId());
			om.setOrderType(orderType);
			int youhui =0;
			if(amountDiscount!=null&&!amountDiscount.equals("")){
				youhui = Integer.parseInt(amountDiscount) * 100;
			}
			int yingfu = Integer.parseInt(monthNum) * Integer.parseInt(price) * 100;
			int shifu = yingfu - youhui;
			if (youhui <= yingfu) {
				om.setAmountPaid(shifu);
			} else {
				om.setAmountPaid(0);
			}
			om.setAmountPayable(yingfu);
			om.setInvoiceStatus(invoiceStatus);
			om.setPayType(payType);
			om.setAmountDiscount(youhui);
			if(orderStatus!=null && orderStatus.equals("11"))
				om.setPayTime(DateUtil.str2date(payTime, DateUtil.DATETIME_FORMAT));
			UserInfo user = (UserInfo) getLoginUser(request);
			om.setOrderStatus(orderStatus);
			om.setParkingId(villageId);
			om.setCreateor(user.getUserNum());
			om.setCreateDate(new Date());
			om.setIsUsed(Constants.TRUE);
			Propertyparkinginfo propertyparkinginfo5=new Propertyparkinginfo();
			propertyparkinginfo5.setVillageId(villageId);
			propertyparkinginfo5.setIsUsed(Constants.TRUE);
			propertyparkinginfo5.setCarNumber(carNumber);
			List<Propertyparkinginfo> listequ=propertyparkinginfoService.selectList(propertyparkinginfo5);
			if(listequ!=null&&listequ.size()>0){
				Customer customer= customerService.queryByMobileV2(listequ.get(0).getPhone());
				if(customer!=null&&!"".equals(customer)){
					om.setCustomerId(customer.getCustomerId());//用户Id
				}else {
					om.setCustomerId("");//用户Id
				}
			}
			orderMainService.add(om);
			OrderEquity orderEquity = new OrderEquity();
			orderEquity.setOrderId(om.getOrderId());
			orderEquity.setParkingId(villageId);
			orderEquity.setCarNumber(carNumber);
			orderEquity.setPrice(Integer.parseInt(price)*100);
			orderEquity.setBeginDate(DateUtil.str2date(effectiveBeginTime1, DateUtil.DATE_FORMAT));
			orderEquity.setEndDate(DateUtil.str2date(zh[1], DateUtil.DATE_FORMAT));
			orderEquity.setMonthNum(Integer.parseInt(monthNum));
			orderEquity.setParkingNo(null);
			orderEquity.setIsUsed(Constants.TRUE);
			orderEquity.setCreateDate(new Date());
			orderEquity.setCreateor(user.getUserNum());
			orderEquityService.add(orderEquity);
			Propertyparkinginfo propertyparkinginfo = new Propertyparkinginfo();
			propertyparkinginfo.setCarNumber(carNumber);
			propertyparkinginfo.setVillageId(villageId);
			if (effectiveEndTime!=null && !"".equals(effectiveEndTime))
			{
				String endDate = DateUtil.getMonthStartAndEndDate(DateUtil.str2date(effectiveEndTime,"yy-MM"))[1];
				propertyparkinginfo.setEffect_end_time(DateUtil.str2date(endDate,"yyyy-MM-dd"));
			}
			propertyparkinginfo.setEffect_begin_time(DateUtil.str2date(effectiveBeginTime,"yyyy-MM-dd"));
			propertyparkinginfoService.updateParkingTime(propertyparkinginfo);
			request.setAttribute("info", "1");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("info", "续费失败");
			logger.error("添加续费出现异常", e);
		}
		try {
			request.getRequestDispatcher("list.html").forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		return new ModelAndView("products/parking/equity_save",null);
	}
	@RequestMapping("equity/list.html")
	public ModelAndView equityList(HttpServletRequest request, HttpServletResponse response) {
		try {
			Page<Propertyparkinginfo> page = new Page<Propertyparkinginfo>();
			PageHelper.initPage(request, page);
			//page.getParams().put("isUsed", Constants.TRUE);
			Map<String, Object> map = super.getParamMap(request);
			String queryType = (String)map.get("queryType");
			String queryValue = (String)map.get("queryValue");
			if(!StringUtils.isEmpty(queryType)){
				switch (Integer.parseInt(queryType)) {
				case 1://小区名称
					page.getParams().put("villageName", queryValue);
					break;
				case 2://车牌号
					page.getParams().put("carNumber", queryValue);
					break;
				default:
					break;
				}
			}
			UserInfo user = (UserInfo) super.getLoginUser(request);
			String module = user.getModule();
			user = userInfoService.queryById(user.getUserId());
			if(user!=null && user.getParkingId()!=null&&!user.getParkingId().equals("")){
				String [] pkStr = user.getParkingId().split("\\,");
				if(null!=pkStr&&pkStr.length>0){
					String parkingStr = "";
					for(int i=0;i<pkStr.length;i++){
						parkingStr +="'";
						parkingStr+=pkStr[i];
						parkingStr+="'";
						parkingStr+=",";
					}
					parkingStr = parkingStr.substring(0,parkingStr.length()-1);
				page.getParams().put("villageId",parkingStr);
				}
			}else {
				page.getParams().put("villageId",null);
			}
				page.getParams().put("module",module);
			String isUsed = request.getParameter("isUsed");
			if (isUsed==null)
				isUsed ="1";
			else
			if (isUsed!=null && isUsed.equals("3"))
				isUsed = null;
			page.getParams().put("isUsed",isUsed);
			page = propertyparkinginfoService.queryListPage(page);
			List<Propertyparkinginfo> list = page.getResultList();
			for (Propertyparkinginfo propertyparkinginfo :list)
			{
				if (propertyparkinginfo!=null && propertyparkinginfo.getEffect_end_time()!=null && !"".equals(propertyparkinginfo.getEffect_end_time()))
				{
					String endDate = new SimpleDateFormat("yyyy-MM-dd").format(propertyparkinginfo.getEffect_end_time());
					String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
					if (endDate.compareTo(nowDate)<0)
					{
						propertyparkinginfo.setIsExpired("0");
					}else {
						propertyparkinginfo.setIsExpired("1");
					}
				}else {
					propertyparkinginfo.setIsExpired("1");
				}
//				if (new SimpleDateFormat("yyyy-MM-dd").format(monthlyparkinginfo.getEffect_end_time()))
			}
			if (isUsed==null)
			{
				page.getParams().put("isUsed","3");
			}
			PageHelper.setPageModel(request, page);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入列表页出现异常",e);
		}
		return new ModelAndView("products/parking/propertyparkinginfo_list");
	}

	/**
	 * 进入添加页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("monthly/add.html")
	public ModelAndView monthlyAdd(HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = (UserInfo) super.getLoginUser(request);
		Map<String, Object> map = super.getParamMap(request);
		Calendar calendar = new GregorianCalendar();
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
		String date=year+"-"+month+"-15";
		request.setAttribute("date",date);
	/*	if(user.getParkingId()!=null&&!user.getParkingId().equals("")){
			Parking parking=parkingService.queryById(user.getParkingId());
			map.put("villageName",parking.getParkingName());
		}*/
		List<Dictionary> dictionaryList = new ArrayList<Dictionary>();
		String module = user.getModule();
		if (module!=null && module.trim().length()>0){
			Dictionary dictionary = new Dictionary();
			dictionary.setDictCode("module");
			dictionary.setDictValue(module);
			dictionaryList = dictionaryService.selectList(dictionary);
			request.setAttribute("model",1);
		}else {
			Dictionary dictionary = new Dictionary();
			dictionary.setDictCode("module");
			dictionaryList = dictionaryService.selectList(dictionary);
			request.setAttribute("model",2);
		}
		request.setAttribute("dictionaryList",dictionaryList);
		return new ModelAndView("products/parking/monthly_add",map);
	}

	@RequestMapping("equity/add.html")
	public ModelAndView equityAdd(HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = (UserInfo) super.getLoginUser(request);
		Map<String, Object> map = super.getParamMap(request);
		Calendar calendar = new GregorianCalendar();
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
		String date=year+"-"+month+"-15";
		request.setAttribute("date",date);
	/*	if(user.getParkingId()!=null&&!user.getParkingId().equals("")){
			Parking parking=parkingService.queryById(user.getParkingId());
			map.put("villageName",parking.getParkingName());
		}*/
		return new ModelAndView("products/parking/equity_add",map);
	}

	@RequestMapping("monthly/toedit.html")
	public ModelAndView monthlyEdit(HttpServletRequest request, HttpServletResponse response) {
	String id=request.getParameter("villageId");
	String carNumber=request.getParameter("carNumber");
	Map<String, Object> map = super.getParamMap(request);
	Parking parking=parkingService.queryById(id);
	map.put("villageName", parking.getParkingName());
	Monthlyparkinginfo	monthlyparkinginfo =new Monthlyparkinginfo();
	monthlyparkinginfo.setVillageId(id);
	monthlyparkinginfo.setCarNumber(carNumber);
	List<Monthlyparkinginfo> list=monthlyparkinginfoService.selectList(monthlyparkinginfo);
	if(list!=null&&list.size()>0){
		Monthlyparkinginfo	monthlyparkinginfo2=list.get(0);
		if(monthlyparkinginfo2.getEffect_begin_time()!=null&&!monthlyparkinginfo2.getEffect_begin_time().equals("")){
			String begin=DateUtil.date2str(monthlyparkinginfo2.getEffect_begin_time(), "yyyy-MM-dd");
			map.put("begin", begin);
		}
		if(monthlyparkinginfo2.getEffect_end_time()!=null&&!monthlyparkinginfo2.getEffect_end_time().equals("")){
			String end=DateUtil.date2str(monthlyparkinginfo2.getEffect_end_time(), "yyyy-MM-dd");
			map.put("end", end);
		}
		if(monthlyparkinginfo2.getMax_date()!=null&&!monthlyparkinginfo2.getMax_date().equals("")){
			String max_date=DateUtil.date2str(monthlyparkinginfo2.getMax_date(), "yyyy-MM-dd");
			map.put("max_date", max_date);
		}
		map.put("monthlyparkinginfo",monthlyparkinginfo2);
	}
		UserInfo userInfo = (UserInfo)super.getLoginUser(request);
		String module = userInfo.getModule();
		if (module!=null && module.trim().length()>0){
			map.put("model",1);
			Dictionary dictionary = new Dictionary();
			dictionary.setIsUsed(Constants.TRUE);
			dictionary.setDictCode("module");
			dictionary.setDictValue(module);
			List<Dictionary> dictionaryList = dictionaryService.selectList(dictionary);
			map.put("dictionaryList",dictionaryList);
		}else {
			Dictionary dictionary = new Dictionary();
			dictionary.setIsUsed(Constants.TRUE);
			dictionary.setDictCode("module");
			List<Dictionary> dictionaryList = dictionaryService.selectList(dictionary);
			map.put("dictionaryList",dictionaryList);
			map.put("model",2);
		}
		return new ModelAndView("products/parking/monthly_edit",map);
	}

	@RequestMapping("equity/toedit.html")
	public ModelAndView equityEdit(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("villageId");
		String carNumber=request.getParameter("carNumber");
		Map<String, Object> map = super.getParamMap(request);
		Parking parking=parkingService.queryById(id);
		map.put("villageName", parking.getParkingName());
		Propertyparkinginfo	propertyparkinginfo =new Propertyparkinginfo();
		propertyparkinginfo.setVillageId(id);
		propertyparkinginfo.setCarNumber(carNumber);
		List<Propertyparkinginfo> list=propertyparkinginfoService.selectList(propertyparkinginfo);
		if(list!=null&&list.size()>0){
			Propertyparkinginfo	propertyparkinginfo2=list.get(0);
			if(propertyparkinginfo2.getEffect_begin_time()!=null&&!propertyparkinginfo2.getEffect_begin_time().equals("")){
				String begin=DateUtil.date2str(propertyparkinginfo2.getEffect_begin_time(), "yyyy-MM-dd");
				map.put("begin", begin);
			}
			if(propertyparkinginfo2.getEffect_end_time()!=null&&!propertyparkinginfo2.getEffect_end_time().equals("")){
				String end=DateUtil.date2str(propertyparkinginfo2.getEffect_end_time(), "yyyy-MM-dd");
				map.put("end", end);
			}
			if(propertyparkinginfo2.getMax_date()!=null&&!propertyparkinginfo2.getMax_date().equals("")){
				String max_date=DateUtil.date2str(propertyparkinginfo2.getMax_date(), "yyyy-MM-dd");
				map.put("max_date", max_date);
			}
			if(propertyparkinginfo2.getPurchaseDate()!=null&&!propertyparkinginfo2.getPurchaseDate().equals("")){
				String purchaseDate=DateUtil.date2str(propertyparkinginfo2.getPurchaseDate(), "yyyy-MM-dd");
				map.put("purchaseDate", purchaseDate);
			}
			map.put("propertyparkinginfo",propertyparkinginfo2);

		}
		return new ModelAndView("products/parking/equity_edit",map);
	}

	@RequestMapping("monthly/save.html")
	public ModelAndView monthlySave(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("monthlyparkinginfo") Monthlyparkinginfo monthlyparkinginfo) {
		try {

				Monthlyparkinginfo monthlyparkinginfo2=new Monthlyparkinginfo();
				monthlyparkinginfo2.setCarNumber(monthlyparkinginfo.getCarNumber());
				monthlyparkinginfo2.setVillageId(monthlyparkinginfo.getVillageId());
			    List<Monthlyparkinginfo> mbpList=	monthlyparkinginfoService.selectList(monthlyparkinginfo2);
			    if(mbpList!=null&&mbpList.size()>0){
				  request.setAttribute("info", "存在重复数据！");
				  request.setAttribute("add", "info");
			    }else{
			    	boolean flag=true;
					String effect_begin_time=request.getParameter("effect_begin_time1");
					String effect_end_time=request.getParameter("effect_end_time1");
					String max_date=request.getParameter("max_date1");
					//String purchaseDate=request.getParameter("purchaseDate1");
//					if(max_date!=null&&!max_date.equals("")){
//						String endTime= DateUtil.getMonthStartAndEndDate(DateUtil.str2date(max_date,"yy-MM"))[1];
//						propertyparkinginfo.setMax_date(DateUtil.str2date(endTime, "yyyy-MM-dd"));
//					}

					/*if(effect_begin_time!=null&&!effect_begin_time.equals("")){
						String beginDate= DateUtil.getMonthStartAndEndDate(DateUtil.str2date(effect_begin_time,"yy-MM"))[0];
						monthlyparkinginfo.setEffect_begin_time(DateUtil.str2date(beginDate, "yyyy-MM-dd"));
					}*/
					monthlyparkinginfo.setEffect_begin_time(DateUtil.str2date(effect_begin_time,"yyyy-MM-dd"));
					if(effect_end_time!=null&&!effect_end_time.equals("")){
						String endDate= DateUtil.getMonthStartAndEndDate(DateUtil.str2date(effect_end_time,"yy-MM"))[1];
						monthlyparkinginfo.setEffect_end_time(DateUtil.str2date(endDate, "yyyy-MM-dd"));
					}

					if(max_date!=null&&!max_date.equals("")){
						String endTime= DateUtil.getMonthStartAndEndDate(DateUtil.str2date(max_date, "yy-MM"))[1];
						monthlyparkinginfo.setMax_date(DateUtil.str2date(endTime, "yyyy-MM-dd"));
						if(monthlyparkinginfo.getEffect_end_time().compareTo(monthlyparkinginfo.getMax_date())>0){
							request.setAttribute("info", "结束日期不得大于最大付款时间！");
							request.setAttribute("add", "info");
							flag=false;
						}
			    	}
			    	 if(flag){
			    UserInfo currUser = (UserInfo) super.getLoginUser(request);
				monthlyparkinginfo.setCreateUser(currUser.getUserNum());
				monthlyparkinginfo.setUpdateTime(new Date());
				monthlyparkinginfo.setUpdateUser(currUser.getUserNum());
				monthlyparkinginfo.setCreateTime(new Date());
				monthlyparkinginfoService.add(monthlyparkinginfo);
				request.setAttribute("info", "添加成功");
			    	 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加服务信息出现异常",e);
		}
		return new ModelAndView("products/parking/monthly_save");
	}

	@RequestMapping("equity/save.html")
	public ModelAndView equitySave(@ModelAttribute("propertyparkinginfo") Propertyparkinginfo propertyparkinginfo, HttpServletRequest request, HttpServletResponse response) {
		try {
		//	Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_ORDER_MAIN);

				Propertyparkinginfo ppi = new Propertyparkinginfo();
				ppi.setCarNumber(propertyparkinginfo.getCarNumber());
				ppi.setVillageId(propertyparkinginfo.getVillageId());
				List<Propertyparkinginfo> ppiList=	propertyparkinginfoService.selectList(ppi);
				if(ppiList!=null&&ppiList.size()>0){
					request.setAttribute("info", "存在重复数据！");
					request.setAttribute("add", "info");
				}else{
					boolean flag=true;
					  UserInfo currUser = (UserInfo) super.getLoginUser(request);
					String effect_begin_time=request.getParameter("effect_begin_time1");
					String effect_end_time=request.getParameter("effect_end_time1");
					String max_date=request.getParameter("max_date1");
					String purchaseDate=request.getParameter("purchaseDate1");
					//String purchaseDate=request.getParameter("purchaseDate1");
//					if(max_date!=null&&!max_date.equals("")){
//						String endTime= DateUtil.getMonthStartAndEndDate(DateUtil.str2date(max_date,"yy-MM"))[1];
//						propertyparkinginfo.setMax_date(DateUtil.str2date(endTime, "yyyy-MM-dd"));
//					}
					if(purchaseDate!=null&&!purchaseDate.equals("")){
						propertyparkinginfo.setPurchaseDate(DateUtil.str2date(purchaseDate, "yyyy-MM-dd"));
					}
					/*if(effect_begin_time!=null&&!effect_begin_time.equals("")){
						String beginDate= DateUtil.getMonthStartAndEndDate(DateUtil.str2date(effect_begin_time,"yy-MM"))[0];
						propertyparkinginfo.setEffect_begin_time(DateUtil.str2date(beginDate, "yyyy-MM-dd"));
					}*/
					propertyparkinginfo.setEffect_begin_time(DateUtil.str2date(effect_begin_time,"yyyy-MM-dd"));
					if(effect_end_time!=null&&!effect_end_time.equals("")){
						String endDate= DateUtil.getMonthStartAndEndDate(DateUtil.str2date(effect_end_time,"yy-MM"))[1];
						propertyparkinginfo.setEffect_end_time(DateUtil.str2date(endDate, "yyyy-MM-dd"));
					}

					if(max_date!=null&&!max_date.equals("")){
						String endTime= DateUtil.getMonthStartAndEndDate(DateUtil.str2date(max_date, "yy-MM"))[1];
						propertyparkinginfo.setMax_date(DateUtil.str2date(endTime, "yyyy-MM-dd"));
						if(propertyparkinginfo.getEffect_end_time().compareTo(propertyparkinginfo.getMax_date())>0){
							request.setAttribute("info", "结束日期不得大于最大付款时间！");
							request.setAttribute("add", "info");
							flag = false;
						}
				       }
				       if(flag){
				     propertyparkinginfo.setCreateUser(currUser.getUserNum());
				     propertyparkinginfo.setCreateTime(new Date());
				     propertyparkinginfo.setUpdateTime(new Date());
				     propertyparkinginfo.setUpdateUser(currUser.getUserNum());
				     propertyparkinginfoService.add(propertyparkinginfo);
				     request.setAttribute("info", "添加成功");
				       }
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加服务信息出现异常",e);
		}
	return new ModelAndView("products/parking/equity_save");
	}

	@RequestMapping("monthly/edit.html")
	public ModelAndView monthlyEdit(@ModelAttribute("monthlyparkinginfo") Monthlyparkinginfo monthlyparkinginfo, HttpServletRequest request, HttpServletResponse response) {
		try {
			String effect_begin_time=request.getParameter("effect_begin_time1");
			String effect_end_time=request.getParameter("effect_end_time1");
			String max_date=request.getParameter("max_date1");
			if(max_date!=null&&!max_date.equals("")){
				 String endTime= DateUtil.getMonthStartAndEndDate(DateUtil.str2date(max_date,"yy-MM"))[1];
				 monthlyparkinginfo.setMax_date(DateUtil.str2date(endTime, "yyyy-MM-dd"));
			}
			/*if(effect_begin_time!=null&&!effect_begin_time.equals("")){
	    		String beginDate= DateUtil.getMonthStartAndEndDate(DateUtil.str2date(effect_begin_time,"yy-MM"))[0];
	    		monthlyparkinginfo.setEffect_begin_time(DateUtil.str2date(beginDate, "yyyy-MM-dd"));
	    	}*/
			monthlyparkinginfo.setEffect_begin_time(DateUtil.str2date(effect_begin_time,"yyyy-MM-dd"));
	    	if(effect_end_time!=null&&!effect_end_time.equals("")){
	    		String endDate= DateUtil.getMonthStartAndEndDate(DateUtil.str2date(effect_end_time,"yy-MM"))[1];
	    		monthlyparkinginfo.setEffect_end_time(DateUtil.str2date(endDate, "yyyy-MM-dd"));
	    	}
				UserInfo currUser = (UserInfo) super.getLoginUser(request);
				monthlyparkinginfo.setUpdateUser(currUser.getUserNum());
				monthlyparkinginfo.setUpdateTime(new Date());
				Monthlyparkinginfo param = new Monthlyparkinginfo();
				param.setCarNumber(monthlyparkinginfo.getCarNumber());
				param.setVillageId(monthlyparkinginfo.getVillageId());
				List<Monthlyparkinginfo> moList = monthlyparkinginfoService.selectList(param);
				Date createTime = moList.get(0).getCreateTime();
				monthlyparkinginfo.setCreateTime(createTime);
				monthlyparkinginfoService.update(monthlyparkinginfo);
				request.setAttribute("info", "修改成功！");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改服务信息出现异常",e);
		}
		return new ModelAndView("products/parking/monthly_save");
	}

	@RequestMapping("equity/edit.html")
	public ModelAndView equityEdit(@ModelAttribute("propertyparkinginfo") Propertyparkinginfo propertyparkinginfo, HttpServletRequest request, HttpServletResponse response) {

		try {
			String effect_begin_time=request.getParameter("effect_begin_time1");
			String effect_end_time=request.getParameter("effect_end_time1");
			String max_date=request.getParameter("max_date1");
			String purchaseDate=request.getParameter("purchaseDate1");

			if(purchaseDate!=null&&!purchaseDate.equals("")){
				propertyparkinginfo.setPurchaseDate(DateUtil.str2date(purchaseDate, "yyyy-MM-dd"));
			}
			if(max_date!=null&&!max_date.equals("")){
				String endTime= DateUtil.getMonthStartAndEndDate(DateUtil.str2date(max_date,"yy-MM"))[1];
				propertyparkinginfo.setMax_date(DateUtil.str2date(endTime, "yyyy-MM-dd"));
			}
			/*if(effect_begin_time!=null&&!effect_begin_time.equals("")){
				String beginDate= DateUtil.getMonthStartAndEndDate(DateUtil.str2date(effect_begin_time,"yy-MM"))[0];
				propertyparkinginfo.setEffect_begin_time(DateUtil.str2date(beginDate, "yyyy-MM-dd"));
			}*/
			propertyparkinginfo.setEffect_begin_time(DateUtil.str2date(effect_begin_time,"yyyy-MM-dd"));
			if(effect_end_time!=null&&!effect_end_time.equals("")){
				String endDate= DateUtil.getMonthStartAndEndDate(DateUtil.str2date(effect_end_time, "yy-MM"))[1];
				propertyparkinginfo.setEffect_end_time(DateUtil.str2date(endDate, "yyyy-MM-dd"));
			}
			UserInfo currUser = (UserInfo) super.getLoginUser(request);
				propertyparkinginfo.setUpdateUser(currUser.getUserNum());
				propertyparkinginfo.setUpdateTime(new Date());
				Propertyparkinginfo param = new Propertyparkinginfo();
				param.setCarNumber(propertyparkinginfo.getCarNumber());
				param.setVillageId(propertyparkinginfo.getVillageId());
				List<Propertyparkinginfo> proList = propertyparkinginfoService.selectList(param);
				Date createTime = proList.get(0).getCreateTime();
				propertyparkinginfo.setCreateTime(createTime);
				propertyparkinginfoService.update(propertyparkinginfo);
				request.setAttribute("info", "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改服务信息出现异常",e);
		}
		return new ModelAndView("products/parking/equity_save");
	}

	@RequestMapping("monthly/del.html")
	public ModelAndView delMonthly(  HttpServletRequest request, HttpServletResponse response) {
		try {
			String villageId=request.getParameter("villageId");
			String carNumber=request.getParameter("carNumber");
			Monthlyparkinginfo monthlyparkinginfo=new Monthlyparkinginfo();
			String isUsed = request.getParameter("isUsed");
			if ("0".equals(isUsed)){
				monthlyparkinginfo.setIsUsed(Constants.TRUE);
			}else if ("1".equals(isUsed)){
				monthlyparkinginfo.setIsUsed(Constants.FALSE);
				//expiretime置前一个月
				Calendar ca = Calendar.getInstance();
				ca.add(Calendar.MONTH, -1);//得到前一个月
				monthlyparkinginfo.setEffect_end_time(ca.getTime());
			}
			monthlyparkinginfo.setVillageId(villageId.trim());
			monthlyparkinginfo.setCarNumber(carNumber.trim());
			monthlyparkinginfoService.updateParkingInfo(monthlyparkinginfo);
			request.setAttribute("info", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("info", "操作失败");
			logger.error("操作出现异常",e);
		}
		return new ModelAndView("products/parking/monthly_save");
	}

	@RequestMapping("equity/del.html")
	public ModelAndView delEqity(  HttpServletRequest request, HttpServletResponse response) {
		try {
			String villageId=request.getParameter("villageId");
			String carNumber=request.getParameter("carNumber");
			Propertyparkinginfo propertyparkinginfo= new Propertyparkinginfo();
			String isUsed = request.getParameter("isUsed");
			if ("0".equals(isUsed)){
				propertyparkinginfo.setIsUsed(Constants.TRUE);
			}else if ("1".equals(isUsed)){
				propertyparkinginfo.setIsUsed(Constants.FALSE);
				//expiretime置前一个月
				Calendar ca = Calendar.getInstance();
				ca.add(Calendar.MONTH, -1);//得到前一个月
				propertyparkinginfo.setEffect_end_time(ca.getTime());
			}
			propertyparkinginfo.setVillageId(villageId.trim());
			propertyparkinginfo.setCarNumber(carNumber.trim());
			propertyparkinginfoService.updateParkingInfo(propertyparkinginfo);
			request.setAttribute("info", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("info", "操作失败");
			logger.error("操作出现异常",e);
		}
		return new ModelAndView("products/parking/equity_save");
	}
	/**
	 * 导出
	 */
	@RequestMapping("monthly/excelExport.html")
	public void excelExport(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
//			Map<String, Object> map1 = super.getParamMap(request);
//			String queryType = (String)map1.get("queryType");
//			String queryValue = (String)map1.get("queryValue");
			String queryType = request.getParameter("queryType");
			String queryValue = request.getParameter("queryValue");
			String isUsed = request.getParameter("isUsed");
			if (isUsed==null){
				isUsed = "1";
			}else if (isUsed.equals("3")){
				isUsed = null;
			}
			Monthlyparkinginfo monthlyparkinginfo=new Monthlyparkinginfo();
			if(!StringUtils.isEmpty(queryType)){
				switch (Integer.parseInt(queryType)) {
				case 1://小区名称
					monthlyparkinginfo.setVillageName(queryValue);
					break;
				case 2://车牌号
					monthlyparkinginfo.setCarNumber(queryValue);
					//page.getParams().put("carNumber", queryValue);
					break;
				default:
					break;
				}
			}
			UserInfo user = (UserInfo) super.getLoginUser(request);
			if(user.getParkingId()!=null&&!user.getParkingId().equals("")){
				String parkingIds = user.getParkingId();
				parkingIds = "('" + parkingIds.replace(",", "','") + "')";
				monthlyparkinginfo.setVillageId(parkingIds);
			}
			monthlyparkinginfo.setIsUsed(isUsed);
			String module = user.getModule();
			monthlyparkinginfo.setModule(module);
			List<Monthlyparkinginfo> monthlyList = monthlyparkinginfoService.queryListExcel(monthlyparkinginfo);
			for (Monthlyparkinginfo monthlyparkinginfo1 : monthlyList){
				String moduleValue = monthlyparkinginfo1.getModule();
				if (moduleValue!=null && moduleValue.trim().length()>0){
					Dictionary dictionary = new Dictionary();
					dictionary.setDictCode("module");
					dictionary.setDictValue(moduleValue);
					dictionary.setIsUsed(Constants.TRUE);
					String moduleName = "";
					List<Dictionary> list = dictionaryService.selectList(dictionary);
					if (list!=null && list.size()>0){
						moduleName = list.get(0).getDictName();
					}
					monthlyparkinginfo1.setModuleName(moduleName);
				}
			}
			if(monthlyList!=null){
				ExportMonthlyExcel.exportMonthlyExcel("新建", new String[]{"小区名称","车牌号","车主姓名","身份信息","车主联系地址","车主联系电话","车辆颜色","月租单价","是否违规","当月有效情况","有效期开始时间","有效期结束时间","有效期最大时间","是否开通线上支付","状态","所属系统"}, monthlyList, response);
			}
			Map<String, String> map=new HashMap<String, String>();
			map.put("result", "success");
			try {
				response.getWriter().print(JSONArray.fromObject(map).toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 产权车位导出
	 */
	/**
	 * 导出
	 */
	@RequestMapping("equity/excelExport.html")
	public void excelExport2(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
//			Map<String, Object> map1 = super.getParamMap(request);
//			String queryType = (String)map1.get("queryType");
//			String queryValue = (String)map1.get("queryValue");
			String queryType = request.getParameter("queryType");
			String queryValue = request.getParameter("queryValue");
			String isUsed = request.getParameter("isUsed");
			if (isUsed==null){
				isUsed = "1";
			}else if (isUsed.equals("3")){
				isUsed = null;
			}
			Propertyparkinginfo propertyparkinginfo=new Propertyparkinginfo();
			if(!StringUtils.isEmpty(queryType)){
				switch (Integer.parseInt(queryType)) {
				case 1://小区名称
					propertyparkinginfo.setVillageName(queryValue);
					break;
				case 2://车牌号
					propertyparkinginfo.setCarNumber(queryValue);
					//page.getParams().put("carNumber", queryValue);
					break;
				default:
					break;
				}
			}
			UserInfo user = (UserInfo) super.getLoginUser(request);
			if(user.getParkingId()!=null&&!user.getParkingId().equals("")){
				String parkingIds = user.getParkingId();
				parkingIds = "('" + parkingIds.replace(",", "','") + "')";
				propertyparkinginfo.setVillageId(parkingIds);
			}
			propertyparkinginfo.setIsUsed(isUsed);
			propertyparkinginfo.setModule(user.getModule());
			List<Propertyparkinginfo> monthlyList = propertyparkinginfoService.queryListExcel(propertyparkinginfo);
			if(monthlyList!=null){
				ExportEquityExcel.exportEquityExcel("新建", new String[]{"小区名称","车牌号","车位号","车主姓名","身份信息","车主联系地址","车主联系电话","车辆颜色","月租单价","是否违规","当月有效情况","车位地址信息","车位产权信息","车位物业信息","购买日期","有效期最大时间","有效期开始时间","有效期结束时间","是否开通线上支付","状态"}, monthlyList, response);
			}
			Map<String, String> map=new HashMap<String, String>();
			map.put("result", "success");
			try {
				response.getWriter().print(JSONArray.fromObject(map).toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 月租车位信息导入
	 * @return
	 *
	 */
	@SuppressWarnings("resource")
	@RequestMapping("monthly/uploadExcel.html")
	public ModelAndView uploadExcel(HttpServletRequest request,HttpServletResponse response,@RequestParam("file") MultipartFile file){
		/**
		 * 得到上传文件流数据
		 */
		String originalFilename = file.getOriginalFilename();
		File newFile = null;
		// 2、设置字符编码
		response.setCharacterEncoding("UTF-8");
		// 3、内容的类型
		response.setContentType("text/html;charset=UTF-8");
		BufferedReader bufferedReader = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "MonthlyParking-upload-";// 文件头名称
		fileName += dateFormat.format(new Date());
		fileName += ".csv";// 文件尾部名称
		// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\项目名称\\WEB-INF\\upload\\文件夹中
		String realPath = MessageFormat.format(
				request.getSession().getServletContext().getRealPath("/WEB-INF/upload") + "{0}xlsx{1}", File.separator,
				File.separator);
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath + fileName));
			// 判断原始文 件名是否存在，存在才进行保存
			if ((originalFilename != null) && !("".equals(originalFilename.trim()))) {
				newFile = new File(realPath + fileName);
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(newFile), "GBK"));
				String line = null;
				Monthlyparkinginfo monthlyParkingInfo = null;
				int count = 0;// 判断是否成功

				List<List<Monthlyparkinginfo>> monthlyParkingInfoSecondList = new ArrayList<List<Monthlyparkinginfo>>();

				List<Monthlyparkinginfo> monthlyParkingInfoList = null;
				while ((line = bufferedReader.readLine()) != null) {
					monthlyParkingInfo = new Monthlyparkinginfo();
					monthlyParkingInfoList = new ArrayList<Monthlyparkinginfo>();
					String[] columns = line.split(",");
					if (columns[1].trim().equalsIgnoreCase("车牌号")) {
						continue;
					}
					for(int i=0;i<columns.length;i++)
					{
						columns[i]=columns[i].replace(" ", "");
					}

					String parkingName = columns[0].replaceAll("\"", "").trim();// 小区名称
					Parking pk=new Parking();
					pk.setParkingName(parkingName);
					List<Parking> parkingList = parkingService.selectList(pk);
					if (parkingList == null || parkingList.size() == 0) {
						response.getWriter().write("小区名称为:\"" + parkingName + "\"的小区不存在,请核对信息!");
						count++;
						break;
					}
					// 获取当前登录的用户的小区
					boolean flag1=true;
					UserInfo user = (UserInfo) super.getLoginUser(request);
					String module = user.getModule();
					if(user.getParkingId()!=null&&!user.getParkingId().equals("")){
						if(parkingList.get(0).getParkingId().equals(user.getParkingId())){
							flag1=true;
						}else{
							flag1=false;
							response.getWriter().write(parkingName+" 不是当前权限下的小区车位！"+count);
							count++;
							break;
						}
					}
					if(flag1){
					monthlyParkingInfo.setVillageId(parkingList.get(0).getParkingId());// 小区ID
					String carNumber = columns[1].replaceAll("\"", "").trim();// 车牌号
					Map<String, Object> map = new HashMap<String, Object>();
					Monthlyparkinginfo	monthlyparkinginfo=new Monthlyparkinginfo();
					monthlyparkinginfo.setVillageId(parkingList.get(0).getParkingId());
					monthlyparkinginfo.setCarNumber(carNumber);
					List<Monthlyparkinginfo> list = monthlyparkinginfoService.selectList(monthlyparkinginfo);

					Monthlyparkinginfo info=null;
					if(list!=null&&list.size()>0){
						info=list.get(0);
					}//findOne(map);// 查询当前小区是否已经存在此车牌号(月租车位)
					if (info != null) {
						response.getWriter().write("当前小区已存在月租车位，车牌号为:\"" + carNumber + "\"的信息,请核对信息!");
						count++;
						break;
					}
					map.put("villageId", parkingList.get(0).getParkingId());
					monthlyParkingInfo.setVillageId(parkingList.get(0).getParkingId());// 小区ID
					monthlyParkingInfo.setCarNumber(carNumber);// 车牌号
					String owner = columns[2].replaceAll("\"", "");
					if(StringUtils.isEmpty(owner)){
						owner="";// 车主姓名
					}
					monthlyParkingInfo.setOwner(owner);// 车主姓名
					// 判断身份信息的类型
					String certificate = columns[3].replaceAll("\"", "");
					if(StringUtils.isEmpty(certificate)){
						 certificate="";//身份证信息
					}
					monthlyParkingInfo.setCertificate(certificate);//身份证信息
					String address = columns[4].replaceAll("\"", "");
					if(StringUtils.isEmpty(address)){
						address="";//车主地址
					}
					monthlyParkingInfo.setAddress(address);//车主地址

					// 判断车主联系电话的类型
					String phone = columns[5].replaceAll("\"", "");
					if(StringUtils.isEmpty(phone)){
						phone="";//电话号码
					}
					monthlyParkingInfo.setPhone(phone);
					String carColor =  columns[6].replaceAll("\"", "");
					if(StringUtils.isEmpty(carColor)){
						monthlyParkingInfo.setCarColor(1);
					}else{
						if("黑".equals(carColor)){
							monthlyParkingInfo.setCarColor(1);
						}else if("白".equals(carColor)){
							monthlyParkingInfo.setCarColor(2);
						}else{
							monthlyParkingInfo.setCarColor(3);
						}
					}

					// 判断月租单价的类型
					if (StringUtils.isEmpty(columns[7].replaceAll("\"", "").trim())) {
						response.getWriter().write("月租单价不能为空,请核对信息!");
						count++;
						break;
					}
					String s=columns[7].replaceAll("\"", "").trim();
					long a=(long) (Double.parseDouble(s));
					monthlyParkingInfo.setMonthlyRentalPrice(a);// 月租单价
					/* 判断违规情况格式是否合法 */
					if (!NumberOfRegular.IsOneNumber(columns[8].replaceAll("\"", "").trim())) {
						response.getWriter().write("月租车位违规情况只能为0或1,请核对信息!");
						count++;
						break;
					}
					monthlyParkingInfo.setIillegalFlg(columns[8].replaceAll("\"", "").trim());// 是否有违规情况
																						// 0：有效
																						// 1：无效
					if (!NumberOfRegular.IsOneNumber(columns[9].replaceAll("\"", "").trim())) {
						response.getWriter().write("月租车位当月有效标识只能为0或1,请核对信息!");
						count++;
						break;
					}
					monthlyParkingInfo.setValidityFlg(columns[9].replaceAll("\"", "").trim());// 当月有效标识
																						// 0：有效
					String beginDate = columns[10].replaceAll("\"", "");
					if(!StringUtils.isEmpty(beginDate)){
						Date date1 = DateUtil.str2date(beginDate, "yyyy/MM/dd");
						String date2 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
						monthlyParkingInfo.setEffect_begin_time(DateUtil.str2date(date2, "yyyy-MM-dd"));//开始时间
					}


					String endDate = columns[11].replaceAll("\"", "");
					if(!StringUtils.isEmpty(endDate)){
						Date date1 = DateUtil.str2date(endDate, "yyyy/MM/dd");
						String date2 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
						monthlyParkingInfo.setEffect_end_time(DateUtil.str2date(date2, "yyyy-MM-dd"));//结束时间
					}

					if(columns.length>12 && columns[12]!=null && !"".equals(columns[12].trim())){
					String maxDate = columns[12].replaceAll("\"", "");
					if(!StringUtils.isEmpty(maxDate)){
						Date date1 = DateUtil.str2date(maxDate, "yyyy/MM/dd");
						String date2 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
						monthlyParkingInfo.setMax_date(DateUtil.str2date(date2, "yyyy-MM-dd"));//最大有效时间
					}
					}
					//导入是否开通线上支付
					if(columns.length>13 && columns[13]!=null && !"".equals(columns[13].trim())){
						String onlinePayment = columns[13].replaceAll("\"", "");
						monthlyParkingInfo.setOnlinePayment((null!=onlinePayment)?onlinePayment.trim():"");
					}

					monthlyParkingInfo.setCreateTime(new Date());// 创建时间
					monthlyParkingInfo.setCreateUser(user.getUserNum());// 创建者
					monthlyParkingInfo.setUpdateTime(new Date());// 修改时间
					monthlyParkingInfo.setUpdateUser(user.getUserNum());// 修改者
					monthlyParkingInfo.setModule(module);
					monthlyParkingInfoList.add(monthlyParkingInfo);
					monthlyParkingInfoSecondList.add(monthlyParkingInfoList);
					 
				}
				}
				boolean flag = monthlyparkinginfoService.saveList(monthlyParkingInfoSecondList,
						 count);
				if ((count < 1) && flag) {
					response.getWriter().write("0");
				} else {
					response.getWriter().write("1");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			try {
				// 返回1表示添加失败
				response.getWriter().write("1");
			} catch (IOException e1) {
				e1.printStackTrace();
				logger.info(e1);
				logger.error(e1);
			}
		}
		return null;
	}
	/**
	 * 导入产权车位
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@SuppressWarnings("resource")
	@RequestMapping("equity/uploadExcel.html")
	public ModelAndView uploadEquityExcel(HttpServletRequest request,HttpServletResponse response,@RequestParam("file") MultipartFile file){
		/**
		 * 得到上传文件流数据
		 */
		String originalFilename = file.getOriginalFilename();
		File newFile = null;
		// 2、设置字符编码
		response.setCharacterEncoding("UTF-8");
		// 3、内容的类型
		response.setContentType("text/html;charset=UTF-8");
		BufferedReader bufferedReader = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "PropertyParkinginfo-upload-";// 文件头名称
		fileName += dateFormat.format(new Date());
		fileName += ".csv";// 文件尾部名称
		// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\项目名称\\WEB-INF\\upload\\文件夹中
		String realPath = MessageFormat.format(
				request.getSession().getServletContext().getRealPath("/WEB-INF/upload") + "{0}xlsx{1}", File.separator,
				File.separator);
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath + fileName));
			// 判断原始文 件名是否存在，存在才进行保存
			if ((originalFilename != null) && !("".equals(originalFilename.trim()))) {
				newFile = new File(realPath + fileName);
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(newFile), "GBK"));
				String line = null;
				Propertyparkinginfo propertyparkinginfo = null;
				int count = 0;// 判断是否成功

				List<List<Propertyparkinginfo>> propertyparkinginfoSecondList = new ArrayList<List<Propertyparkinginfo>>();

				List<Propertyparkinginfo> propertyparkinginfoList = null;
				while ((line = bufferedReader.readLine()) != null) {
					
				 
					propertyparkinginfo = new Propertyparkinginfo();
					propertyparkinginfoList = new ArrayList<Propertyparkinginfo>();
					String[] columns = line.split(",");
					if (columns[1].trim().equalsIgnoreCase("车牌号")) {
						continue;
					}
					for(int i=0;i<columns.length;i++)
					{ 
						columns[i]=columns[i].replace(" ", "");
					}
					// 获取当前登录的用户的小区
					String parkingName = columns[0].replaceAll("\"", "");// 小区名称
					Parking pk=new Parking();
					pk.setParkingName(parkingName.trim());
					List<Parking> parkingList = parkingService.selectList(pk);
					if (parkingList == null || parkingList.size() == 0) {
						response.getWriter().write("小区名称为:\"" + parkingName + "\"的小区不存在,请核对信息!");
						count++;
						break;
					}
					// 获取当前登录的用户的小区
					boolean flag1=true;
					UserInfo user = (UserInfo) super.getLoginUser(request);
					if(user.getParkingId()!=null&&!user.getParkingId().equals("")){
						if(parkingList.get(0).getParkingId().equals(user.getParkingId())){
							flag1=true;	
						}else{
							flag1=false;
							response.getWriter().write(parkingName+" 不是当前权限下的小区车位！"+count);
							count++;
							break;
						}
					}
					if(flag1){
					propertyparkinginfo.setVillageId(parkingList.get(0).getParkingId());// 小区ID
					String carNumber = columns[1].replaceAll("\"", "").trim();// 车牌号
					Map<String, Object> map = new HashMap<String, Object>();
					Propertyparkinginfo	propertyparkinginfo1=new Propertyparkinginfo();
					propertyparkinginfo1.setVillageId(parkingList.get(0).getParkingId());
					propertyparkinginfo1.setCarNumber(carNumber);
					List<Propertyparkinginfo> list = propertyparkinginfoService.selectList(propertyparkinginfo1);
					
					Propertyparkinginfo info=null;
					if(list!=null&&list.size()>0){
						info=list.get(0);
					}//findOne(map);// 查询当前小区是否已经存在此车牌号(月租车位)
					if (info != null) {
						response.getWriter().write("当前小区已存在月租车位，车牌号为:\"" + carNumber + "\"的信息,请核对信息!");
						count++;
						break;
					}
					map.put("villageId", parkingList.get(0).getParkingId());
					propertyparkinginfo.setVillageId(parkingList.get(0).getParkingId());// 小区ID
					propertyparkinginfo.setCarNumber(carNumber);// 车牌号
					String parkingNum = columns[2].replaceAll("\"", "");
					if(StringUtils.isEmpty(parkingNum)){
						parkingNum="";// 车位号
					}
					propertyparkinginfo.setParkingNumber(parkingNum);// 车主姓名
					String owner = columns[3].replaceAll("\"", "");
					if(StringUtils.isEmpty(owner)){
						owner="";// 车主姓名
					}
					propertyparkinginfo.setOwner(owner);// 车主姓名
					// 判断身份信息的类型
					String certificate = columns[4].replaceAll("\"", "");
					if(StringUtils.isEmpty(certificate)){
						 certificate="";//身份证信息
					} 
					propertyparkinginfo.setCertificate(certificate);//身份证信息
					String address = columns[5].replaceAll("\"", "");
					if(StringUtils.isEmpty(address)){
						address="";//车主地址
					}
					propertyparkinginfo.setAddress(address);//车主地址
					
					// 判断车主联系电话的类型
					String phone = columns[6].replaceAll("\"", "");
					if(StringUtils.isEmpty(phone)){
						phone="";//电话号码
					}
					propertyparkinginfo.setPhone(phone);
					String carColor =  columns[7].replaceAll("\"", "");
					if(StringUtils.isEmpty(carColor)){
						propertyparkinginfo.setCarColor(1);
					}else{
						if("黑".equals(carColor)){
							propertyparkinginfo.setCarColor(1);
						}else if("白".equals(carColor)){
							propertyparkinginfo.setCarColor(2);
						}else{
							propertyparkinginfo.setCarColor(3);
						}
					}
					
					// 判断月租单价的类型
					if (!NumberOfRegular.IsDouble(columns[8].replaceAll("\"", "").trim())) {
						response.getWriter().write("月租单价只能为整数或小数类型,请核对信息!");
						count++;
						break;
					}
					String s=columns[8].replaceAll("\"", "").trim();
					long a=(long) (Double.parseDouble(s));
					propertyparkinginfo.setManagementFeeMonthlyUnit(a);// 月租单价
					/* 判断违规情况格式是否合法 */
					if (!NumberOfRegular.IsOneNumber(columns[9].replaceAll("\"", "").trim())) {
						response.getWriter().write("月租车位违规情况只能为0或1,请核对信息!");
						count++;
						break;
					}
					propertyparkinginfo.setIillegalFlg(columns[9].replaceAll("\"", "").trim());// 是否有违规情况
																						// 0：有效
																						// 1：无效
					if (!NumberOfRegular.IsOneNumber(columns[10].replaceAll("\"", "").trim())) {
						response.getWriter().write("月租车位当月有效标识只能为0或1,请核对信息!");
						count++;
						break;
					}
					propertyparkinginfo.setValidityFlg(columns[10].replaceAll("\"", "").trim());// 当月有效标识
						
					String purchaseDate = columns[14].replaceAll("\"", "");
					if(!StringUtils.isEmpty(purchaseDate)){
					    Date date1 = DateUtil.str2date(purchaseDate, "yyyy/MM/dd");
						String date2 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
						propertyparkinginfo.setPurchaseDate(DateUtil.str2date(date2, "yyyy-MM-dd"));

					}
					
					String parkingAddrInfo = columns[11].replaceAll("\"", "");
					if(StringUtils.isEmpty(parkingAddrInfo)){
						parkingAddrInfo="";//车位地址
					}
					propertyparkinginfo.setParkingAddrInfo(parkingAddrInfo);
					String parkingPropertyInfo = columns[12].replaceAll("\"", "");
					if(StringUtils.isEmpty(parkingPropertyInfo)){
						parkingPropertyInfo="";//车位物业
					}
					propertyparkinginfo.setParkingPropertyInfo(parkingPropertyInfo);
					String parkingInfo = columns[13].replaceAll("\"", "");
					if(StringUtils.isEmpty(parkingInfo)){
						parkingInfo="";//车位产权
					}
					propertyparkinginfo.setParkingInfo(parkingInfo);
					String beginDate = columns[15].replaceAll("\"", "");
					if(!StringUtils.isEmpty(beginDate)){
						   Date date1 = DateUtil.str2date(beginDate, "yyyy/MM/dd");
							String date2 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
						propertyparkinginfo.setEffect_begin_time(DateUtil.str2date(date2, "yyyy-MM-dd"));//开始时间
					}
					
					
					String endDate = columns[16].replaceAll("\"", "");
					if(!StringUtils.isEmpty(endDate)){
						  Date date1 = DateUtil.str2date(endDate, "yyyy/MM/dd");
							String date2 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
						propertyparkinginfo.setEffect_end_time(DateUtil.str2date(date2, "yyyy-MM-dd"));//结束时间
					}
					
					if(columns.length>17 && columns[17]!=null && !"".equals(columns[17].trim())){
					String maxDate = columns[17].replaceAll("\"", "");
					if(!StringUtils.isEmpty(maxDate)){
						 Date date1 = DateUtil.str2date(maxDate, "yyyy/MM/dd");
							String date2 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
						propertyparkinginfo.setMax_date(DateUtil.str2date(date2, "yyyy-MM-dd"));//最大有效时间
					}
					}
					//导入是否开通线上支付
					if(columns.length>18 && columns[18]!=null && !"".equals(columns[18].trim())){
						String onlinePayment = columns[18].replaceAll("\"", "");
						propertyparkinginfo.setOnlinePayment((null!=onlinePayment)?onlinePayment.trim():"");
					}
					
					propertyparkinginfo.setCreateTime(new Date());// 创建时间
					propertyparkinginfo.setCreateUser(user.getUserNum());// 创建者
					propertyparkinginfo.setUpdateTime(new Date());// 修改时间
					propertyparkinginfo.setUpdateUser(user.getUserNum());// 修改者
					propertyparkinginfoList.add(propertyparkinginfo);
					propertyparkinginfoSecondList.add(propertyparkinginfoList);
					 
				} 
				}
				boolean flag = propertyparkinginfoService.saveList(propertyparkinginfoSecondList,
						 count);
				if ((count < 1) && flag) {
					response.getWriter().write("0");
				} else {
					response.getWriter().write("1");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			try {
				// 返回1表示添加失败
				response.getWriter().write("1");
			} catch (IOException e1) {
				e1.printStackTrace();
				logger.error(e1);
			}
		}
		return null;
	}
	/**
	 * 特殊车辆列表
	 */
	@RequestMapping("specialCar/list.html")
	public ModelAndView specialCar(HttpServletRequest request,HttpServletResponse response){
		Page<Specialparkinginfo> page = new Page<Specialparkinginfo>();
		PageHelper.initPage(request, page);
		//page.getParams().put("isUsed", Constants.TRUE);
		Map<String, Object> map = super.getParamMap(request);
		String queryType = (String)map.get("queryType");
		String queryValue = (String)map.get("queryValue");
		if(!StringUtils.isEmpty(queryType)){
			switch (Integer.parseInt(queryType)) {
			case 1://小区名称
				page.getParams().put("villageName", queryValue);
				break;
			case 2://车牌号
				page.getParams().put("carNumber", queryValue);
				break;
			default:
				break;
			}
		}
		String isUsed = request.getParameter("isUsed");
		if (isUsed==null)
			isUsed ="1";
		else
		if (isUsed!=null && isUsed.equals("3"))
			isUsed = null;
		page.getParams().put("isUsed",isUsed);
		UserInfo user = (UserInfo) super.getLoginUser(request);
		user = userInfoService.queryById(user.getUserId());
		if(user!=null && user.getParkingId()!=null&&!user.getParkingId().equals("")){
			String [] pkStr = user.getParkingId().split("\\,");
			if(null!=pkStr&&pkStr.length>0){
				String parkingStr = "";
				for(int i=0;i<pkStr.length;i++){
					parkingStr +="'";
					parkingStr+=pkStr[i];
					parkingStr+="'";
					parkingStr+=",";
				}
				parkingStr = parkingStr.substring(0,parkingStr.length()-1);
			page.getParams().put("villageId",parkingStr);
			}
		}else {
			page.getParams().put("villageId",null);
		}
		
		page = specialparkinginfoService.queryListPageForBack(page);
		if (isUsed==null)
		{
			page.getParams().put("isUsed","3");
		}
		PageHelper.setPageModel(request, page);
		return new ModelAndView("products/parking/specialinfo_list");
	}
	
	/**
	 * 特殊车辆进入添加页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("specialCar/add.html")
	public ModelAndView spicalCarAdd(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = super.getParamMap(request);	
		return new ModelAndView("products/parking/special_add",map);
	}
	/**
	 * 特殊车辆保存
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("specialCar/save.html")
	public ModelAndView specialCarSave(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("specialparkinginfo") Specialparkinginfo specialparkinginfo) {
		try {
			  
			Specialparkinginfo specialparkinginfo2=new Specialparkinginfo();
			specialparkinginfo2.setCarNumber(specialparkinginfo.getCarNumber());
			specialparkinginfo2.setVillageId(specialparkinginfo.getVillageId());
			    List<Specialparkinginfo> mbpList=	specialparkinginfoService.selectList(specialparkinginfo2);
			    if(mbpList!=null&&mbpList.size()>0){
				  request.setAttribute("info", "存在重复数据！");
				  request.setAttribute("add", "info");
			    }else{
			    UserInfo currUser = (UserInfo) super.getLoginUser(request);
			    specialparkinginfo.setCreateUser(currUser.getUserNum());
			    specialparkinginfo.setUpdateTime(new Date());
			    specialparkinginfo.setUpdateUser(currUser.getUserNum());
			    specialparkinginfo.setCreateTime(new Date());
			    specialparkinginfoService.add(specialparkinginfo);
				request.setAttribute("info", "添加成功");
			    	 
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加服务信息出现异常",e);
		}
		return new ModelAndView("products/parking/special_save");
	}
	/**
	 * 特殊车辆编辑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("specialCar/toedit.html")
	public ModelAndView specialCarEdit(HttpServletRequest request, HttpServletResponse response) {
	String id=request.getParameter("villageId");
	String carNumber=request.getParameter("carNumber");
	Map<String, Object> map = super.getParamMap(request);
	Parking parking=parkingService.queryById(id);
	map.put("villageName", parking.getParkingName());
	Specialparkinginfo	specialparkinginfo =new Specialparkinginfo();
	specialparkinginfo.setVillageId(id);
	specialparkinginfo.setCarNumber(carNumber);
	List<Specialparkinginfo> list=specialparkinginfoService.selectList(specialparkinginfo);
	if(list!=null&&list.size()>0){
		Specialparkinginfo	specialparkinginfo2=list.get(0);
		/*if(specialparkinginfo2.getc){
			String begin=DateUtil.date2str(monthlyparkinginfo2.getEffect_begin_time(), "yyyy-MM-dd");
			map.put("begin", begin);
		}*/
		
		map.put("specialparkinginfo",specialparkinginfo2);
	}
		return new ModelAndView("products/parking/special_edit",map);
	}
	/**
	 * 特殊车辆保存
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("specialCar/edit.html")
	public ModelAndView specialCar(@ModelAttribute("specialparkinginfo") Specialparkinginfo specialparkinginfo, HttpServletRequest request, HttpServletResponse response) {
		try {
			UserInfo currUser = (UserInfo) super.getLoginUser(request);
			specialparkinginfo.setUpdateUser(currUser.getUserNum());
			specialparkinginfo.setUpdateTime(new Date());
			specialparkinginfoService.update(specialparkinginfo);
			request.setAttribute("info", "修改成功！");			 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改服务信息出现异常",e);
		}
		return new ModelAndView("products/parking/special_save");
	}
	/**
	 * 特殊车辆删除
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("specialCar/del.html")
	public ModelAndView delSpecial(  HttpServletRequest request, HttpServletResponse response) {
		try {
			String villageId=request.getParameter("villageId");
			String carNumber=request.getParameter("carNumber");
			Specialparkinginfo specialparkinginfo=new Specialparkinginfo();
			String isUsed = request.getParameter("isUsed");
			/*if ("0".equals(isUsed)){
				specialparkinginfo.setIsUsed(Constants.TRUE);
			}else if ("1".equals(isUsed)){
				specialparkinginfo.setIsUsed(Constants.FALSE);
			}
			specialparkinginfo.setVillageId(villageId.trim());
			specialparkinginfo.setCarNumber(carNumber.trim());
			specialparkinginfoService.updateParkingInfo(specialparkinginfo);*/
			specialparkinginfo.setVillageId(villageId.trim());
			specialparkinginfo.setCarNumber(carNumber.trim());
			specialparkinginfoService.delete(specialparkinginfo);
			request.setAttribute("info", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("info", "操作失败");
			logger.error("操作出现异常",e);
		}
		return new ModelAndView("products/parking/special_save");
	}
	/**
	 * 特殊车辆导出
	 */
	@RequestMapping("specialCar/excelExport.html")
	public void specialExcelExport(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
//			Map<String, Object> map1 = super.getParamMap(request);
//			String queryType = (String)map1.get("queryType");
//			String queryValue = (String)map1.get("queryValue");
			String queryType = request.getParameter("queryType");
			String queryValue = request.getParameter("queryValue");
			String isUsed = request.getParameter("isUsed");
			if (isUsed==null){
				isUsed = "1";
			}else if (isUsed.equals("3")){
				isUsed = null;
			}
			Specialparkinginfo specialparkinginfo=new Specialparkinginfo();
			if(!StringUtils.isEmpty(queryType)){
				switch (Integer.parseInt(queryType)) {
				case 1://小区名称
					specialparkinginfo.setParkingName(queryValue);
					break;
				case 2://车牌号
					specialparkinginfo.setCarNumber(queryValue);
					//page.getParams().put("carNumber", queryValue);
					break;
				default:
					break;
				}
			}
//			UserInfo user = (UserInfo) super.getLoginUser(request);
//			if(user.getParkingId()!=null&&!user.getParkingId().equals("")){
//				String parkingIds = user.getParkingId();
//				parkingIds = "('" + parkingIds.replace(",", "','") + "')";
//				propertyparkinginfo.setVillageId(parkingIds);
//			}
//			propertyparkinginfo.setIsUsed(isUsed);
//			List<Propertyparkinginfo> monthlyList = propertyparkinginfoService.queryListExcel(propertyparkinginfo);
//			if(monthlyList!=null){
//				ExportEquityExcel.exportEquityExcel("新建", new String[]{"小区名称","车牌号","车位号","车主姓名","身份信息","车主联系地址","车主联系电话","车辆颜色","月租单价","是否违规","当月有效情况","车位地址信息","车位产权信息","车位物业信息","购买日期","有效期最大时间","有效期开始时间","有效期结束时间","是否开通线上支付","状态"}, monthlyList, response);
//			}
			UserInfo user = (UserInfo)super.getLoginUser(request);
			if (user.getParkingId() !=null && !user.getParkingId().equals("")){
				String parkingIds = user.getParkingId();
				parkingIds = "('"+parkingIds.replace(",","','")+"')";
				specialparkinginfo.setVillageId(parkingIds);
			}
			specialparkinginfo.setIsUsed(isUsed);
			List<Specialparkinginfo> spList = specialparkinginfoService.queryListExcel(specialparkinginfo);
			if(spList!=null){
				ExportSpecialExcel.exportSpecialExcel("新建", new String[]{"小区名称","车牌号","车主姓名","身份信息","车主联系地址","车主联系电话","车辆颜色","状态"}, spList, response);
			}
			Map<String, String> map=new HashMap<String, String>();
			map.put("result", "success");
			try {
				response.getWriter().print(JSONArray.fromObject(map).toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 特殊车位信息导入
	 * @return 
	 * 
	 */
	@SuppressWarnings("resource")
	@RequestMapping("specialCar/uploadExcel.html")
	public ModelAndView uploadSpecialExcel(HttpServletRequest request,HttpServletResponse response,@RequestParam("file") MultipartFile file){
		/**
		 * 得到上传文件流数据
		 */
		String originalFilename = file.getOriginalFilename();
		File newFile = null;
		// 2、设置字符编码
		response.setCharacterEncoding("UTF-8");
		// 3、内容的类型
		response.setContentType("text/html;charset=UTF-8");
		BufferedReader bufferedReader = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "specialparkinginfo-upload-";// 文件头名称
		fileName += dateFormat.format(new Date());
		fileName += ".csv";// 文件尾部名称
		// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\项目名称\\WEB-INF\\upload\\文件夹中
		String realPath = MessageFormat.format(
				request.getSession().getServletContext().getRealPath("/WEB-INF/upload") + "{0}xlsx{1}", File.separator,
				File.separator);
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath + fileName));
			// 判断原始文 件名是否存在，存在才进行保存
			if ((originalFilename != null) && !("".equals(originalFilename.trim()))) {
				newFile = new File(realPath + fileName);
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(newFile), "GBK"));
				String line = null;
				Specialparkinginfo specialparkinginfo = null;
				int count = 0;// 判断是否成功

				List<List<Specialparkinginfo>> specialparkinginfoSecondList = new ArrayList<List<Specialparkinginfo>>();

				List<Specialparkinginfo> specialparkinginfoList = null;
				while ((line = bufferedReader.readLine()) != null) {
					
				 
					specialparkinginfo = new Specialparkinginfo();
					specialparkinginfoList = new ArrayList<Specialparkinginfo>();
					String[] columns = line.split(",");
					if (columns[1].trim().equalsIgnoreCase("车牌号")) {
						continue;
					}
					for(int i=0;i<columns.length;i++)
					{ 
						columns[i]=columns[i].replace(" ", "");
					}
					
					String parkingName = columns[0].replaceAll("\"", "").trim();// 小区名称
					Parking pk=new Parking();
					pk.setParkingName(parkingName);
					List<Parking> parkingList = parkingService.selectList(pk);
					if (parkingList == null || parkingList.size() == 0) {
						response.getWriter().write("小区名称为:\"" + parkingName + "\"的小区不存在,请核对信息!");
						count++;
						break;
					}
					
					specialparkinginfo.setVillageId(parkingList.get(0).getParkingId());// 小区ID
					String carNumber = columns[1].replaceAll("\"", "").trim();// 车牌号
					Map<String, Object> map = new HashMap<String, Object>();
					Specialparkinginfo	specialparkinginfoParam=new Specialparkinginfo();
					specialparkinginfoParam.setVillageId(parkingList.get(0).getParkingId());
					specialparkinginfoParam.setCarNumber(carNumber);
					List<Specialparkinginfo> list = specialparkinginfoService.selectList(specialparkinginfoParam);
					
					Specialparkinginfo info=null;
					if(list!=null&&list.size()>0){
						info=list.get(0);
					}//findOne(map);// 查询当前小区是否已经存在此车牌号(月租车位)
					if (info != null) {
						response.getWriter().write("当前小区已存在月租车位，车牌号为:\"" + carNumber + "\"的信息,请核对信息!");
						count++;
						break;
					}
					map.put("villageId", parkingList.get(0).getParkingId());
					specialparkinginfo.setVillageId(parkingList.get(0).getParkingId());// 小区ID
					specialparkinginfo.setCarNumber(carNumber);// 车牌号
					String owner = columns[2].replaceAll("\"", "");
					if(StringUtils.isEmpty(owner)){
						owner="";// 车主姓名
					}
					specialparkinginfo.setOwner(owner);// 车主姓名
					// 判断身份信息的类型
					String certificate = columns[3].replaceAll("\"", "");
					if(StringUtils.isEmpty(certificate)){
						 certificate="";//身份证信息
					} 
					specialparkinginfo.setCertificate(certificate);//身份证信息
					String address = columns[4].replaceAll("\"", "");
					if(StringUtils.isEmpty(address)){
						address="";//车主地址
					}
					specialparkinginfo.setAddress(address);//车主地址
					
					// 判断车主联系电话的类型
					String phone = columns[5].replaceAll("\"", "");
					if(StringUtils.isEmpty(phone)){
						phone="";//电话号码
					}
					specialparkinginfo.setPhone(phone);
					String carColor =  columns[6].replaceAll("\"", "");
					if(StringUtils.isEmpty(carColor)){
						specialparkinginfo.setCarColor(1);
					}else{
						if("黑".equals(carColor)){
							specialparkinginfo.setCarColor(1);
						}else if("白".equals(carColor)){
							specialparkinginfo.setCarColor(2);
						}else{
							specialparkinginfo.setCarColor(3);
						}
					}
					
					// 判断月租单价的类型
					if (!NumberOfRegular.IsDouble(columns[7].replaceAll("\"", "").trim())) {
						response.getWriter().write("月租单价只能为整数或小数类型,请核对信息!");
						count++;
						break;
					}
					UserInfo user = (UserInfo) super.getLoginUser(request);									 
					specialparkinginfo.setCreateTime(new Date());// 创建时间
					specialparkinginfo.setCreateUser(user.getUserNum());// 创建者
					specialparkinginfo.setUpdateTime(new Date());// 修改时间
					specialparkinginfo.setUpdateUser(user.getUserNum());// 修改者
					specialparkinginfoList.add(specialparkinginfo);
					specialparkinginfoSecondList.add(specialparkinginfoList);
				}
				//插入
				for (List<Specialparkinginfo> mlist : specialparkinginfoSecondList) {
					for (Specialparkinginfo sp : mlist) {
						specialparkinginfoService.add(sp);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				// 返回1表示添加失败
				response.getWriter().write("1");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 特殊车位信息导入
	 * @return
	 *
	 */
	@SuppressWarnings("resource")
	@RequestMapping("parkingStatus/uploadExcel.html")
	public ModelAndView uploadParkingStatusExcel(HttpServletRequest request,HttpServletResponse response,@RequestParam("file") MultipartFile file){
		int countc = 0;
		/**
		 * 得到上传文件流数据
		 */
		String originalFilename = file.getOriginalFilename();
		File newFile = null;
		// 2、设置字符编码
		response.setCharacterEncoding("UTF-8");
		// 3、内容的类型
		response.setContentType("text/html;charset=UTF-8");
		BufferedReader bufferedReader = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "specialparkinginfo-upload-";// 文件头名称
		fileName += dateFormat.format(new Date());
		fileName += ".csv";// 文件尾部名称
		// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\项目名称\\WEB-INF\\upload\\文件夹中
		String realPath = MessageFormat.format(
				request.getSession().getServletContext().getRealPath("/WEB-INF/upload") + "{0}xlsx{1}", File.separator,
				File.separator);
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath + fileName));
			// 判断原始文 件名是否存在，存在才进行保存
			if ((originalFilename != null) && !("".equals(originalFilename.trim()))) {
				newFile = new File(realPath + fileName);
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(newFile), "GBK"));
				String line = null;
				ParkingStatus parkingStatus = null;
				int count = 0;// 判断是否成功

				List<List<ParkingStatus>> parkingStatusSecondList = new ArrayList<List<ParkingStatus>>();

				List<ParkingStatus> parkingStatusList = new ArrayList<ParkingStatus>();
				while ((line = bufferedReader.readLine()) != null) {
					parkingStatus = new ParkingStatus();
					//parkingStatusList = new ArrayList<ParkingStatus>();
					String[] columns = line.split(",");
					if (columns[0].trim().equalsIgnoreCase("车场名称")) {
						continue;
					}
					for (int i = 0; i < columns.length; i++) {
						columns[i] = columns[i].replace(" ", "");
					}

					String parkingName = columns[0].replaceAll("\"", "").trim();// 小区名称
					Parking pk=new Parking();
					pk.setParkingName(parkingName);
					List<Parking> parkingList = parkingService.selectListByExport(pk);
					if (parkingList == null || parkingList.size() == 0) {
						//response.getWriter().write("小区名称为:\"" + parkingName + "\"的小区不存在,请核对信息!");
						count++;
						countc++;
						continue;
					}
					//车场ID
					parkingStatus.setParkingId(parkingList.get(0).getParkingId());
					//时间段
					String hourSection = columns[1].replaceAll("\"", "").trim();// 时间段
					parkingStatus.setHourSection(Integer.parseInt(hourSection));
					//时间段
					String status = columns[2].replaceAll("\"", "").trim();// 时间段
					parkingStatus.setStatus(status);
					parkingStatus.setCreateDate(new Date());
					parkingStatus.setCreateor("admin");
					parkingStatusList.add(parkingStatus);
					//插入
				/*for (List<Specialparkinginfo> mlist : specialparkinginfoSecondList) {
					for (Specialparkinginfo sp : mlist) {
						specialparkinginfoService.add(sp);
					}
				}*/
				}
				for (ParkingStatus ps : parkingStatusList) {
					ParkingStatus psParam = new ParkingStatus();
					psParam.setHourSection(ps.getHourSection());
					psParam.setParkingId(ps.getParkingId());
					List<ParkingStatus> pList = parkingStatusService.selectList(psParam);
					if(null!=pList && pList.size()>0){
						pList.get(0).setStatus(ps.getStatus());
						parkingStatusService.update(pList.get(0));
					}else{
						parkingStatusService.add(ps);
					}
				}
				try {
					if(countc==0){
						response.getWriter().print("导入成功");
					}else{
						response.getWriter().print("导入成功,失败"+countc+"条,成功"+(parkingStatusList.size()-countc)+"条");
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				// 返回1表示添加失败
				response.getWriter().write("1");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}


	/**
	 * 特殊车辆导出
	 */
	@RequestMapping("parkingStatus/excelExport.html")
	public void parkingStatusExcelExport(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			String parkingName = request.getParameter("p_parkingName");
			String hourSection = request.getParameter("p_hourSection");
			String status = request.getParameter("p_parkingStatus");
			ParkingStatus pkParam = new ParkingStatus();
			if(!StringUtils.isEmpty(parkingName)){
				pkParam.setParkingName(parkingName);
			}
			if(!StringUtils.isEmpty(hourSection)){
				pkParam.setHourSection(Integer.parseInt(hourSection));
			}
			if(!StringUtils.isEmpty(status)){
				pkParam.setStatus(status);
			}
			List<ParkingStatus> psList = parkingStatusService.selectList(pkParam);
			if (psList != null) {
				ExportParkingStatusExcel.exportParkingStatusExcel("新建", new String[]{"时间段", "状态","车场名称" }, psList, response);
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("result", "success");
			try {
				response.getWriter().print(JSONArray.fromObject(map).toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
