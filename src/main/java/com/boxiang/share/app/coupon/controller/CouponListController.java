package com.boxiang.share.app.coupon.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.coupon.po.Coupon;
import com.boxiang.share.product.coupon.service.CouponService;
import com.boxiang.share.product.coupon.service.RedeemRuleService;
import com.boxiang.share.product.customer.po.RedDot;
import com.boxiang.share.product.customer.service.RedDotService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;
@Controller
@RequestMapping("app/coupon")
public class CouponListController extends BaseController{
	private static final Logger logger = Logger.getLogger(CouponListController.class);
	@Resource private CouponService couponService;

	@Resource
	private RedDotService redDotService;

	@Resource
	private RedeemRuleService redeemRuleService;

	@RequestMapping("receiveCoupon/{customerId}/{cdkeyOrVouchersName}/{summary}")
	public void ReceiveCoupon(@PathVariable String customerId,@PathVariable
			String cdkeyOrVouchersName,HttpServletRequest request, HttpServletResponse response){
		//把message信息返回到手机端
		PrintWriter out = null;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
				}
		String message = null;
		try {

			Coupon coupon1 = new Coupon();
			coupon1.setCustomerId(customerId);
			coupon1.setVouchersName(cdkeyOrVouchersName);
			List<Coupon> list = couponService.selectCouponByCus(coupon1);
			if(list!=null && list.size()>0){
				message = ShangAnMessageType.operateToJson("1","您已经领取过优惠券了");
			}else{
				Coupon coupon = new Coupon();
				coupon.setCouponId(cdkeyOrVouchersName);
				coupon.setVouchersName(cdkeyOrVouchersName);
				coupon.setCouponStatus("100101");
				List<Coupon> couponList = couponService.selectCouponByData(coupon);
				//如果设置的是领取时间的话去除领取时间外的
				logger.info("--------去除领取范围外的优惠券");
				if(couponList!=null && couponList.size()>0){
					for(Coupon cu : couponList){
						if(!StringUtils.isEmpty(cu.getEffectivetime())){
							Date da = new Date();
							Date begin_time = DateUtil.str2date(cu.getReceiveBegin(), DateUtil.DATETIME_FORMAT);
							Date end_time = DateUtil.str2date(cu.getReceiveEnd(), DateUtil.DATETIME_FORMAT);
							if(da.getTime()<begin_time.getTime() || da.getTime()>end_time.getTime()){
								couponList.remove(cu);
								if(couponList.size()==0){
									break;
								}
							}
						}else{
							Date da = new Date();
							//Date begin_time = DateUtil.str2date(cu.getReceiveBegin(), DateUtil.DATETIME_FORMAT);
							Date end_time = DateUtil.str2date(cu.getEffectiveEnd(), DateUtil.DATETIME_FORMAT);
							if(da.getTime()>=end_time.getTime()){
								couponList.remove(cu);
								if(couponList.size()==0){
									break;
								}
							}
						}
					}
				}
				if(couponList!=null && couponList.size()>0){
					for(int i = 0;i<couponList.size();){
						Coupon cp = couponList.get(i);
						cp.setUseTime(DateUtil.getCurrDate(DateUtil.DATE_FORMAT));
						cp.setCouponStatus("100201");
						cp.setCustomerId(customerId);
						//设置有效时间
						try {
							if(!StringUtils.isEmpty(cp.getEffectivetime())){
								Date begin_da = new Date();
								Date end_da = DateUtil.getPreOrNextDate(begin_da, Integer.parseInt(cp.getEffectivetime()));
								cp.setEffectiveBegin(DateUtil.date2str(begin_da, DateUtil.DATETIME_FORMAT));
								cp.setEffectiveEnd(DateUtil.date2str(end_da, DateUtil.DATETIME_FORMAT));
							}
						} catch (Exception e) {
							logger.info("设置有效时间错误");
							logger.error("",e);
							out.print(message);
							return;
						}
						
						couponService.update(cp);
						message = ShangAnMessageType.operateToJson("0","兑换成功");
						break;
					}
				}else{
					message = ShangAnMessageType.operateToJson("1","兑换失败");
				}
			}

			// 根据兑换码找优惠券
			if (redeemRuleService.receiveCouponByRedeemCode(cdkeyOrVouchersName, customerId)) {
				message = ShangAnMessageType.operateToJson("0", "兑换成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = ShangAnMessageType.operateToJson("2","查询失败");
			logger.error("",e);
		}
		out.print(message);
	}
	//判断用户是否有优惠券(得到已领取未使用的优惠券数目)
	@RequestMapping("getCouponNum/{customerId}/{summary}")
	public void getCouponNum(@PathVariable String customerId,HttpServletRequest request,HttpServletResponse response){
		//100201 未过期已领取未使用
		Coupon coupon = new Coupon();
		coupon.setCouponStatus("100201");
		coupon.setCustomerId(customerId);
		List<Coupon> list = couponService.queryByStatusAndCustomerId(coupon);
		String message = ShangAnMessageType.toShangAnJson("0", "查询成功", "num", list.size());
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

//	public ModelAndView ReceiveCoupon(@PathVariable String customerId,@PathVariable 
//			String cdkeyOrVouchersName,HttpServletRequest request, HttpServletResponse response){
//		//先按照id来查询
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		String message = null;
//		Coupon cp = new Coupon();
//		coupon1.setCouponId(cdkeyOrVouchersName);
//		cp = couponService.queryById(cdkeyOrVouchersName);
//		if(cp!=null){
//			if(cp.getCouponStatus()!=null && cp.getCouponStatus().equals("100101")){
//				String sEffectiveTime =cp.getEffectivetime();
//				if (sEffectiveTime == null) {
//					sEffectiveTime = "";
//				}
//				if (!"".equals(sEffectiveTime)) {
//					int i = Integer.parseInt(sEffectiveTime);
//					Date dEffective_begin = new Date();
//					String dEffective_end = "";
//					dEffective_begin.setDate(dEffective_begin.getDay() + i);
//					dEffective_end = format.format(dEffective_begin);
//					cp.setEffectiveBegin(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//					cp.setEffectiveEnd(dEffective_end);
//				}
//				cp.setUseTime(format.format(new Date()));
//				cp.setCouponStatus("100201");
//				cp.setCustomerId(customerId);
//				couponService.update(cp);
//				message = ShangAnMessageType.toShangAnJson("0","success","coupon",cp);
//			}else if(cp.getCustomerId()!=null&&!"".equals(cp.getCustomerId())){
//				message = ShangAnMessageType.toShangAnJson("1","fail","该优惠券已使用",cp);
//			}else{
//				message = ShangAnMessageType.toShangAnJson("1","fail","该优惠券不可用",cp);
//			}
//		}else{
//			//按照id查询不到相关优惠券 则按照券名查找券
//			Coupon coupon2 = new Coupon();
//			coupon2.setVouchersName(cdkeyOrVouchersName);
//			coupon2.setCustomerId(customerId);
//			List<Coupon> couponList =  couponService.selectList(coupon2);
//			if(couponList !=null){
//				message = ShangAnMessageType.toShangAnJson("1","fail","您已领取过该优惠券！",coupon2);
//			}else{
//				Coupon coupon3 = new Coupon();
//				coupon3.setVouchersName(cdkeyOrVouchersName);
//				List<Coupon> coupons = couponService.selectList(coupon3);//查询所有优惠券
//				if(coupons!=null){
//					for(int i = 0;i<coupons.size();i++){
//						if((coupons.get(i).getCustomerId()==null || "".equals(coupons.get(i).getCustomerId())) && "100101".equals(coupons.get(i).getCouponStatus())){
//							Coupon coupon4 = coupons.get(i);
//							if(coupon4.getEffectivetime()!=null && !"".equals(coupon4.getEffectivetime())){
//								int j = Integer.parseInt(coupon4.getEffectivetime());
//								Date dEffective_begin = new Date();
//								String dEffective_end = "";
//								dEffective_begin.setDate(dEffective_begin.getDay() + j);
//								dEffective_end = format.format(dEffective_begin);
//								coupon4.setEffectiveBegin(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//								coupon4.setEffectiveEnd(dEffective_end);
//							}
//							coupon4.setCouponStatus("100201");
//							coupon4.setUseTime(format.format(new Date()));
//							coupon4.setCustomerId(customerId);
//							couponService.update(coupon4);
//							message = ShangAnMessageType.toShangAnJson("0","success","coupon",coupon4);
//							break;
//						}
//					}
//				}else{
//					message = ShangAnMessageType.toShangAnJson("1","fail","该优惠券不存在，请重新输入！",coupon2);
//				}
//			}
//		}
//		//把message信息返回到手机端
//		PrintWriter out;
//		//设置utf-8
//		response.setContentType("text/html;charset=UTF-8");	
//		try {
//			out = response.getWriter();
//			out.print(message);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

    /**
     * 查询小红点（提醒）
     * @param customerId
     * @param request
     * @param response
     */
    @RequestMapping("queryRedDot/{customerId}/{summary}")
    public void queryRedDot(@PathVariable String customerId,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        String message = "";
        try {
            RedDot redDot = redDotService.queryRedDot(customerId, 1);
            message = ShangAnMessageType.toShangAnJson("0", "查询成功", "count", redDot == null ? 0 : redDot.getNewCount());
        } catch (Exception e) {
            logger.error("", e);
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
     * 关闭小红点（提醒）
     * @param customerId
     * @param request
     * @param response
     */
    @RequestMapping("closeRedDot/{customerId}/{summary}")
    public void closeRedDot(@PathVariable String customerId,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        String message = "";
        try {
            redDotService.closeRedDot(customerId, 1);
            message = ShangAnMessageType.operateToJson("0", "修改成功");
        } catch (Exception e) {
            logger.error("", e);
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
	 * 点击获取所有该客户领取的优惠券列表，分页处理
	 * 
	 * @param request
	 * @param response
	 */
    @RequestMapping(value = "getCouponList")
	public void getCouponList(HttpServletRequest request, HttpServletResponse response){
//		System.out.println("==================执行了后边代码");
//		String sCdkey = request.getParameter("cdkey");
		String sCustomerId = request.getParameter("customerId");
		String orderType = request.getParameter("orderType");
		String amount_payableStr = request.getParameter("amountPayable");
		String couponStatus = request.getParameter("couponStatus");
		String parkingId = request.getParameter("parkingId");
		String pageIndex=request.getParameter("pageIndex");
		Integer amount_payable = 0;
		if(null!=amount_payableStr){
			amount_payable = Integer.parseInt(amount_payableStr);
		}else{
			amount_payable = null;
		}
		 Page<Coupon> page = new Page<Coupon>();
         page.getParams().put("parAmount", amount_payable);
         page.getParams().put("customerId", sCustomerId);
		page.getParams().put("parkingId", parkingId);
         page.getParams().put("orderType", orderType);
		page.getParams().put("couponStatus", couponStatus);
         page.setCurrentPage(Integer.valueOf(pageIndex));
 
		if(orderType!=null&&!"".equals(orderType)){
		if(orderType.equals("10")){//临
			page.getParams().put("couponKind", "0");
			page.getParams().put("couponKind2", "1");
		}
		if(orderType.equals("11")){//停
			page.getParams().put("couponKind", "0");
			page.getParams().put("couponKind2", "1");
		}
		if(orderType.equals("12")){//代泊
			page.getParams().put("couponKind", "0");
			page.getParams().put("couponKind2", "3");
		}
		if(orderType.equals("13")){//月租
			page.getParams().put("couponKind", "0");
			page.getParams().put("couponKind2", "2");
		}
		if(orderType.equals("14")){//产权
			page.getParams().put("couponKind", "0");
			page.getParams().put("couponKind2", "2");
		}
		if(orderType.equals("17")){//洗车
			page.getParams().put("couponKind", "0");
			page.getParams().put("couponKind2", "5");
		}
		}
		
		String message = null;
		try {
			message = couponService.getcouponlist(page);
		} catch (Exception e) {
			logger.error("", e);
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
	

}
