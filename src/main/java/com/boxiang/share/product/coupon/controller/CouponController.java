package com.boxiang.share.product.coupon.controller;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingService;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.coupon.po.Coupon;
import com.boxiang.share.product.coupon.service.CouponService;
import com.boxiang.share.system.po.Dictionary;
import com.boxiang.share.system.service.DictionaryService;
import com.boxiang.share.user.po.UserInfo;

@Controller
@RequestMapping(value = "products/coupon")
public class CouponController extends BaseController {
	private static final Logger logger = Logger.getLogger(CouponController.class);
	@Resource private CouponService couponService;
	@Resource private DictionaryService dictionaryService;
	@Resource private ParkingService parkingService;
	/**
	 * 进优惠券管理页面
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list.html")
	public ModelAndView list(HttpServletRequest request , HttpServletResponse response) throws Exception {
		try{
			request.setCharacterEncoding("UTF-8");
			/*优惠券状态*/
			Dictionary dict1 = new Dictionary();
			dict1.setDictCode("coupon_status");
			dict1.setIsUsed(Constants.TRUE);
			List<Dictionary> dicList1 = dictionaryService.selectList(dict1);
			request.setAttribute("dicList1",dicList1);
			/*优惠券类型*/
			Dictionary dict2 = new Dictionary();
			dict2.setDictCode("coupon_type");
			dict2.setIsUsed(Constants.TRUE);
			List<Dictionary> dicList2 = dictionaryService.selectList(dict2);
			request.setAttribute("dicList2",dicList2);
			/*通用券类型*/
			Dictionary dict3 = new Dictionary();
			dict3.setDictCode("coupon_kind");
			dict3.setIsUsed(Constants.TRUE);
			List<Dictionary> dicList3 = dictionaryService.selectList(dict3);
			request.setAttribute("dicList3", dicList3);
			Dictionary dict4 = new Dictionary();
			dict4.setDictCode("channel");
			dict4.setIsUsed(Constants.TRUE);
			List<Dictionary> dicList4 = dictionaryService.selectList(dict4);
			request.setAttribute("dicList4", dicList4);
//			Dictionary dict5 = new Dictionary();
//			dict5.setDictCode("coupon_type");
//			List<Dictionary> dicList5 = dictionaryService.selectList(dict5);
//			request.setAttribute("dicList5", dicList5);
			/*查询列表*/
			Page<Coupon> page = new Page<Coupon>();
			PageHelper.initPage(request, page);
			String couponStatus = request.getParameter("couponStatus");
//			String couponType = request.getParameter("couponType");
			String couponKind = request.getParameter("couponKind");
			String vouchersName = request.getParameter("vouchersName");
			String creator = request.getParameter("creator");
			page.getParams().put("creator", creator);
			String createBeginTime = request.getParameter("createBeginTime");
			String createEndTime = request.getParameter("createEndTime");
			page.getParams().put("stopTime", createEndTime);
			page.getParams().put("startTime", createBeginTime);
			page.getParams().put("couponId", request.getParameter("couponId"));
			if(couponKind !=null && couponKind.length()>0)
			{
				page.getParams().put("couponKind", couponKind);
			}
			page.getParams().put("vouchersName", vouchersName);
			page.getParams().put("couponStatus", couponStatus);
			page = couponService.queryListPage(page);
			PageHelper.setPageModel(request, page);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("进入优惠券列表页出现异常",e);
		}
		return new ModelAndView("products/coupon/coupon_list");
	}
	
	/*优惠券详情*/
	@RequestMapping(value="couponDetail.html")
	public ModelAndView couponDetail(HttpServletRequest request,HttpServletResponse response){
		String couponId = request.getParameter("couponId");
		Coupon cp = couponService.queryById(couponId);
		List<String> parkingList =new ArrayList();
		String parkingName=null;
		if (cp.getParkingId()!=null&&!cp.getParkingId().equals("")) {
			String[] parkingId=cp.getParkingId().split(",");
			for (int i = 0; i <parkingId.length ; i++) {
				Parking parking= parkingService.queryById(parkingId[i]);
				parkingList.add(parking.getParkingName());
			}
			parkingName=	StringUtils.join(parkingList, ",");
		}
		request.setAttribute("coupon", cp);
		request.setAttribute("parkingName",parkingName);
		return new ModelAndView("products/coupon/coupon_detail");
	}
	/*删除优惠券*/
	/*@RequestMapping(value="/{id}/del.html")
	public ModelAndView carLifedel(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		if(id==0){
			throw new NullPointerException("优惠券ID不能为空!");
		}
		try {
			couponService.delete(id);
			request.setAttribute("info", "删除成功");
		} catch (NumberFormatException nfe) {
			logger.error("优惠券类型id转换类型失败，请检查参数是否正确！id="+id+".",nfe);
			throw new NumberFormatException("优惠券类型id转换类型失败，请检查参数是否正确！"+id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除优惠券出现异常",e);
		}
		return new ModelAndView("products/coupon/save",null);
	}*/
	/*导出excel文档*/
	@RequestMapping(value="excelExport.html")
	public void excelExport(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
			String vouchersName = request.getParameter("vouchersName");
			String couponKind = request.getParameter("couponKind");
			String couponStatus = request.getParameter("couponStatus");
			String creator = request.getParameter("creator");
			String couponId = request.getParameter("couponId");
			String createBeginTime = request.getParameter("createBeginTime");
			String createEndTime = request.getParameter("createEndTime");
			Coupon coupon = new Coupon();
			if(vouchersName !=null && vouchersName.length()>0)
				coupon.setVouchersName(vouchersName);
			if(couponKind !=null && couponKind.length()>0)
				coupon.setCouponKind(couponKind);
			if(couponStatus !=null && couponStatus.length()>0)
				coupon.setCouponStatus(couponStatus);
			if(creator !=null && creator.length()>0)
				coupon.setCreator(creator);
			if(createBeginTime !=null && createBeginTime.length()>0)
				coupon.setStartTime(createBeginTime);
			if(couponId !=null && couponId.length()>0)
				coupon.setCouponId(couponId);
			if(createEndTime !=null && createBeginTime.length()>0)
				coupon.setStopTime(createEndTime);
			List<Coupon> couponList = couponService.selectList(coupon);
			if(couponList!=null){
				ExportExcel.exportYouhuiExcel("新建", new String[]{"优惠券ID","券（活动）名称","优惠券种类","发放理由","面值（元）","面值（折）","激活有效开始时间","激活有效结束时间","使用有效开始时间","使用有结束始时间","优惠券使用时间","发放人","发放时间"}, couponList, response);
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
	 * 新增插入优惠券
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "insertCoupon.html")
	public ModelAndView insertCoupons(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
			String p_coupon_kind = request.getParameter("form_couponType");
			String p_vouchers_type = request.getParameter("form_couponType");//通用券类型
			String p_vouchers_name = request.getParameter("form_vouchersName");//券名
			String p_number = request.getParameter("form_vouchersNum");//发放张数
			String p_channel = request.getParameter("issueReason");//发放原因
			String p_coupon_type = request.getParameter("form_typeRadio");//优惠券类型
			String p_minconsumption = request.getParameter("form_limitFee");
			String p_par_amount = request.getParameter("form_parAmount1");//面值 元
			String p_par_discount = request.getParameter("form_parAmount2");//面值  折
			String p_maxdiscount = request.getParameter("form_maxDiscount");//最高折扣
			String p_receive_begin = request.getParameter("form_beginTime1");//领取开始时间
			String p_receive_end = request.getParameter("form_endTime1");//领取结束时间
			String p_effectivetime = request.getParameter("dayTimeNum");//有效期
			String p_effective_begin = request.getParameter("form_beginTime2");//有效开始时间
			String p_effective_end = request.getParameter("form_endTime2");//有效结束时间
			String p_exclusion_rule = request.getParameter("exclusiveRule");//互斥规则
			String p_info = request.getParameter("infoText");//说明
			String form_typeRadio = request.getParameter("form_typeRadio");//定额 折扣 两选一
			String parkingId = request.getParameter("parkingId");//车场名称
			if("1".equals(form_typeRadio)){
				p_par_discount = "";
				p_maxdiscount = "";
			}else if("2".equals(form_typeRadio)){
				p_par_amount = "";
			}
			boolean addCp = true;
			if("1".equals(p_channel)){
				Coupon cp = new Coupon();
				cp.setChannel(p_channel);
				cp.setCouponStatus("100101");
				cp.setReceiveBegin(p_receive_begin);
				cp.setReceiveEnd(p_receive_end);
				cp.setEffectiveBegin(p_effective_begin);
				cp.setEffectiveEnd(p_effective_end);
				List<Coupon> couponNums = couponService.selectNum(cp);
				List<Coupon> couponNums2 = couponService.selectNums(cp);
				if((couponNums!=null && couponNums.size()>0) || (couponNums2!=null && couponNums2.size()>0)){
					addCp = false;
				}
			}
			if(addCp){
//				if(Integer.parseInt(p_par_amount)<Integer.parseInt(p_minconsumption)){
				Coupon coupon = new Coupon();
				coupon.setCouponKind(p_coupon_kind);
				coupon.setVouchersType(p_vouchers_type);
				coupon.setVouchersName(p_vouchers_name);
				coupon.setChannel(p_channel);
				coupon.setCouponType(p_coupon_type);
				if(p_minconsumption !=null && p_minconsumption.length()>0)
					coupon.setMinconsumption(Integer.parseInt(p_minconsumption));
				else 
					coupon.setMinconsumption(0);
				if(p_par_amount !=null && p_par_amount.length()>0)
					coupon.setParAmount(Integer.parseInt(p_par_amount));
				else 
					coupon.setParAmount(0);
				if(p_par_discount != null && p_par_discount.length()>0)
					coupon.setParDiscount(Integer.parseInt(p_par_discount));
				else 
					coupon.setParDiscount(0);
				if(p_maxdiscount !=null && p_maxdiscount.length()>0)
					coupon.setMaxdiscount(Integer.parseInt(p_maxdiscount));
				else 
					coupon.setMaxdiscount(0);
				if(p_receive_begin!=null&&!p_receive_begin.equals("")){
					coupon.setReceiveBegin(p_receive_begin+" 0:00:00");
				}
				if(p_receive_end!=null&&!p_receive_end.equals("")){
					coupon.setReceiveEnd(p_receive_end +" 23:59:59");
				}
				coupon.setEffectivetime(p_effectivetime);
				if(p_effective_begin!=null&&!p_effective_begin.equals("")){
					coupon.setEffectiveBegin(p_effective_begin+" 0:00:00");
				}
				if(p_effective_end!=null&&!p_effective_end.equals("")){
					coupon.setEffectiveEnd(p_effective_end+" 23:59:59");
				}
				coupon.setExclusionRule(p_exclusion_rule);
				coupon.setInfo(p_info);
				coupon.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				UserInfo userInfo = (UserInfo)super.getLoginUser(request);
				coupon.setCreator(userInfo.getUserName());
				coupon.setCouponStatus("100101");
				coupon.setParkingId(parkingId);
				if(p_number !=null && p_number.length()>0)
				{
					for(int i = 1;i<=Integer.parseInt(p_number);i++){
						coupon.setCouponId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
						couponService.add(coupon);
						request.setAttribute("info", "添加成功");
					}
				}
//				}else {
//					request.setAttribute("info", "添加失败,定额券面值不得大于限用最低消费！");
//					request.setAttribute("add", "fail");
//				}
			}else{
				request.setAttribute("info", "添加失败,不同优惠券有效期不能交叉");
				request.setAttribute("add", "fail");
			}
		} catch (Exception e) {
			request.setAttribute("info", "添加失败");
			request.setAttribute("add", "fail");
			e.printStackTrace();
			logger.error("",e);
		}
		return new ModelAndView("products/coupon/save",null);
	}
	/*判断是否存在相同名称的优惠券*/
	@RequestMapping(value="getCouponName.html")
	public void getCouponName(HttpServletRequest request,HttpServletResponse response){
		try {
			String couponName = request.getParameter("couponName");
			Coupon coupon=new Coupon();
			coupon.setVouchersName(couponName);
		    List<Coupon> list=	couponService.selectList(coupon);
			if(list!=null&&list.size()>0){
				response.getWriter().print("0");
			}else{
				response.getWriter().print("1");
			}
		} catch (Exception e) {
			logger.error("", e);
		}

	}

}
