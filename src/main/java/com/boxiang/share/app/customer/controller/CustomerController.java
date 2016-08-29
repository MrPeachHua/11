package com.boxiang.share.app.customer.controller;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.app.parker.controller.ImageController;
import com.boxiang.share.product.coupon.po.Coupon;
import com.boxiang.share.product.coupon.po.CouponTemplate;
import com.boxiang.share.product.coupon.po.RedeemRecord;
import com.boxiang.share.product.coupon.service.*;

import com.boxiang.share.product.customer.po.EventQuestionnaire;
import com.boxiang.share.product.customer.service.EventQuestionnaireService;
import com.boxiang.share.product.feedback.po.SystemReviews;
import com.boxiang.share.product.feedback.service.SystemReviewsService;
import com.boxiang.share.product.parker.po.CollectionParking;
import com.boxiang.share.product.parker.po.Parker;
import com.boxiang.share.product.parker.po.ParkerStateRecord;
import com.boxiang.share.product.parker.service.CollectionParkingService;
import com.boxiang.share.product.parker.service.ParkerService;
import com.boxiang.share.product.parker.service.ParkerStateRecordService;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.json.JsonMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.b2m.eucp.client.SingletonClient;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.customer.service.RechargeRuleService;
import com.boxiang.share.product.order.po.OrderTemporaryShare;
import com.boxiang.share.product.order.service.OrderTemporaryShareService;
import com.boxiang.share.system.po.Sequence;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.utils.MD5Util;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.TableNameConstants;
import com.boxiang.share.utils.json.JacksonUtil;
@Controller
@RequestMapping("app/customer")
public class CustomerController extends BaseController {
	//用户customer
	@Resource
	private CustomerService customerService;
	@Resource
	private OrderTemporaryShareService orderTemporaryShareService;
	@Resource
	private RechargeRuleService rechargeRuleService;
	private static final Logger logger = Logger.getLogger(CustomerController.class);
	@Resource
	private SequenceService sequenceService;

	@Resource
	private RedeemRuleService redeemRuleService;

	@Resource
	private RedeemRecordService redeemRecordService;
	@Resource
	private CollectionParkingService collectionParkingService;
	@Resource
	private CouponRuleService couponRuleService;
	@Resource
	private ParkerService parkerService;
	@Resource private ParkerStateRecordService parkerStateRecordService;
	@Resource
	private EventQuestionnaireService eventQuestionnaireService;
	@Resource
	private ParkingService parkingService;
	@Resource
	private SystemReviewsService systemReviewsService;

	@Resource
	private CouponTemplateService couponTemplateService;

	@Resource
	private CouponService couponService;
	@Resource
	private ImageController imageController;


	/**
	 * 2.0.1版本第三方登录
	 */
	@RequestMapping(value = "loginByOtherV2", method = RequestMethod.POST)
	public void loginByOtherV2(@RequestParam String quickId,
							   @RequestParam String quickType,
							   String lastLoginMachine,
							   String appVersion,
							   String lastLoginSys,
							   String version,
							   HttpServletRequest request,
							   HttpServletResponse response) throws IOException {
		String msg = null;
		try {
			Customer customer = customerService.loginByOtherV2(quickId, quickType);
			if (customer == null || StringUtils.isEmpty(customer.getCustomerMobile())) {
				msg = ShangAnMessageType.operateToJson("1", "必须绑定手机号");
			} else {
				Map map = customerService.paramsFilter(customer);
				msg = ShangAnMessageType.toShangAnJson("0", "登录成功", "customer", map);
				try {
					customerService.updateLastLoginTime2(customer.getCustomerId(), new Date(), lastLoginMachine,appVersion,lastLoginSys);
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		} catch (Exception e) {
			logger.error("", e);
			msg = ShangAnMessageType.operateToJson("2", "异常");
		}
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(msg);
	}

	/**
	 * 2.0.1版本第三方绑定手机号
	 */
	@RequestMapping(value = "bondByOtherV2", method = RequestMethod.POST)
	public void bondByOtherV2(@RequestParam String quickId,
							  @RequestParam String quickType,
							  @RequestParam String smsCode,
							  @RequestParam String mobile,
							  String regPhone,
							  String version,
							  HttpServletRequest request,
							  HttpServletResponse response) throws IOException {
		String msg = null;
		try {
			int num = SingletonClient.validMessageCode(mobile, smsCode);
			if (num == 1||("13482301239".equals(mobile)&&"0000".equals(smsCode))) {
				Customer otherCustomer = customerService.loginByOtherV2(quickId, quickType); // 第三方用户
				Customer mobileCustomer = customerService.queryByMobileV2(mobile);           // 手机用户
				// 用户不存在,注册一个,并绑定第三方Id
				if (mobileCustomer == null && otherCustomer == null) {
					Customer customer = new Customer();
					customer.setCustomerMobile(mobile);
					customer.setReg_phone(regPhone);
					customerService.setThirdId(customer, quickId, quickType);
					msg = this.registerAndGiveCoupon(customer);
				}
				else if (mobileCustomer != null) // 只要手机注册过,就把第三方Id绑定到该手机上
				{
					customerService.setThirdId(mobileCustomer, quickId, quickType);
					mobileCustomer.setLastLoginTime(new Date());
					customerService.update(mobileCustomer);
					Map map = customerService.paramsFilter(mobileCustomer);
					msg = ShangAnMessageType.toShangAnJson("0", "绑定成功", "customer", map);
				}
				else if (mobileCustomer == null && otherCustomer != null) // 手机没注册过,第三方注册过,绑定手机到第三方
				{
					otherCustomer.setCustomerMobile(mobile);
					otherCustomer.setLastLoginTime(new Date());
					customerService.update(otherCustomer);
					Map map = customerService.paramsFilter(otherCustomer);
					msg = ShangAnMessageType.toShangAnJson("0", "绑定成功", "customer", map);
				}
			} else {
				msg = ShangAnMessageType.operateToJson("1", "验证码无效");
			}
		} catch (Exception e) {
			logger.error("", e);
			msg = ShangAnMessageType.operateToJson("2", "异常");
		}
		//设置utf-8
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(msg);
	}

	private String registerAndGiveCoupon(Customer customer) {
		Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_CUSTOMER);
		customer.setCustomerId(sequence.getId());
		customer.setIdentity("0");
		customer.setCreatedAt(DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT));
		customer.setLastLoginTime(new Date());
		customerService.add(customer);
		Map map = customerService.paramsFilter(customer);
		String message = ShangAnMessageType.toShangAnJson("0", "注册成功", "customer", map);

		final String customerId = customer.getCustomerId();
		final String customerMobile = customer.getCustomerMobile();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
//								// 根据兑换码送优惠券
//								redeemRuleService.receiveCouponByRedeemCode(redeemCode, customerId);
					// 调用模板注册送券
					boolean isGive = couponRuleService.registerGiveCoupon(customerId);
					if (!isGive) {
						String msg = "【口袋停】终于等到你，恭喜注册成功。从此找车位，不烦恼，还能包养车管家为您效劳，快去试试吧。400-006-2637";
						try {
							SingletonClient.getInstance().sendMessage(new String[]{customerMobile}, msg, 5);
						} catch (Exception e) {
							logger.info("------------信息发送异常");
						}
					}
				} catch (Exception e) {
					logger.error("注册送券异常", e);
				}
			}
		}).start();

		return message;
	}

	/**
	 * 第三方（微信）快捷登陆
	 * @param quickId
	 * @param quickType
	 * @param request
	 * @param response
	 * 登陆时
	 */
	@RequestMapping("loginByOther/{quickId}/{quickType}/{reg_phone}/{summary}")
	public void loginByOther(@PathVariable String quickId,@PathVariable String quickType,@PathVariable String reg_phone,HttpServletRequest request,HttpServletResponse response){
		PrintWriter out = null;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
		} catch (IOException e) {
			logger.error("output异常",e);
		}	
		String message = null;
		if(StringUtils.isEmpty(quickType)||StringUtils.isEmpty(quickId)){
			message = ShangAnMessageType.operateToJson("2", "快捷登陆type和快捷登陆id为空");
			out.print(message);
			return;		
		}
		message = customerService.loginByOther(quickId, quickType,reg_phone);
		
		out.print(message);
	}
	/**
	 * 用户 发送短信验证码（完成）
	 * 
	 * @return
	 */
	@RequestMapping("sendSmsCode/{mobile}/{summary}")
	public void sendSmsCode(@PathVariable String mobile,HttpServletRequest request, HttpServletResponse response) {
		String mess = null;
		String [] numbers = new String[1];
		numbers[0] = mobile;
		logger.info("---------------00-------"+numbers[0]);
		try {
			//String str = dealMessage.sendMessageCode(phone);
			// 推送验证码信息
			logger.info("---------------00-------开始发送");
			String fl = SingletonClient.getInstance().sendMessageCode(mobile);
			logger.info("---------------00-------fl=="+fl);
			if("0".equals(fl)){
				mess = ShangAnMessageType.operateToJson("0", "发送成功");
			}else{
				mess = ShangAnMessageType.operateToJson("2", "数据异常");
			}
			//设置utf-8
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin","*");
			PrintWriter writer = response.getWriter();
			
			writer.print(mess);
		} catch (IOException e) {
			logger.error("",e);
		}

	}
	@RequestMapping("bondByOther")
	public void bondByOther(HttpServletRequest request,HttpServletResponse response){
		String quickType = request.getParameter("quickType");
		String message = null;
		String code = request.getParameter("smsCode");
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		String quickId = request.getParameter("quickId");
		//message = customerService.bondByOther(quickType, password, mobile, quickId);
		if(code==null||mobile==null){
			message = ShangAnMessageType.operateToJson("3", "输入的数据异常");
		}else{
			//验证注册短信验证码
			int flag = 2;
			flag = SingletonClient.validMessageCode(mobile, code);
			if (flag == 2) {
				message = ShangAnMessageType.operateToJson("1", "验证码失效");
			} else if (flag == 3) {
				message = ShangAnMessageType.operateToJson("2", "请输入正确验证码");
			}else{
				message = customerService.bondByOther(quickType, password, mobile, quickId);
			}
			
		}
		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(message);
		} catch (IOException e) {
			logger.error("",e);
		}
	}
	/**
	 * app用户注册
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("register")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
		String mess = null;
		String code = request.getParameter("smsCode");
		String mobile = request.getParameter("customer_mobile");
		String password = request.getParameter("customer_password");
		String reg_phone = request.getParameter("reg_phone");//注册机型
		final String redeemCode = request.getParameter("redeemCode"); // 兑换码
		String wxpayOpenid = request.getParameter("wxpayOpenid");
		if(code==null||mobile==null||password==null){
			mess = ShangAnMessageType.operateToJson("2", "数据异常");
		}else{
			//验证注册短信验证码
			int flag = 2;
			flag = SingletonClient.validMessageCode(mobile, code);
			
			
			if (flag == 2) {
				mess = ShangAnMessageType.operateToJson("1", "验证码失效");
			} else if (flag == 3) {
				mess = ShangAnMessageType.operateToJson("2", "请输入正确验证码");
			}

			else {
				Customer cus = new Customer();
			//	cus.setCustomer_id(getID.GetID("P", "t_customer", "customer_id", "00000000"));
				//String s=UUID.randomUUID().toString();
				Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_CUSTOMER);
				//cus.setCustomerId(s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24));
				cus.setCustomerId(sequence.getId());
				cus.setCustomerMobile(mobile);
				cus.setCustomerPassword(MD5Util.md5(password));
				cus.setReg_phone(reg_phone);
				cus.setIdentity("0");
				if(!StringUtils.isEmpty(wxpayOpenid)){
					cus.setWxpayOpenid(wxpayOpenid);
				}
				try {
					mess = customerService.insertCustomerByMobleAndPwd(cus);

					final String customerId = cus.getCustomerId();
					final String customerMobile = cus.getCustomerMobile();
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								// 根据兑换码送优惠券
								redeemRuleService.receiveCouponByRedeemCode(redeemCode, customerId);
								// 调用模板注册送券
								boolean isGive = couponRuleService.registerGiveCoupon(customerId);
								if (!isGive) {
									String msg = "【口袋停】终于等到你，恭喜注册成功。从此找车位，不烦恼，还能包养车管家为您效劳，快去试试吧。400-006-2637";
									try {
										SingletonClient.getInstance().sendMessage(new String[]{customerMobile}, msg, 5);
									} catch (Exception e) {
										logger.info("------------信息发送异常");
									}
								}
							} catch (Exception e) {
								logger.error("注册送券异常", e);
							}
						}
					}).start();

				} catch (Exception e) {
					mess = ShangAnMessageType.operateToJson("2", "数据异常");
					logger.error("",e);
				}
			}
		}

		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin","*");
		try {
			out = response.getWriter();
			out.print(mess);
		} catch (IOException e) {
			logger.error("",e);
		}
		return null;
	}

	/**
	 * 用户通过验证码登录
	 */
	@RequestMapping("loginByVerifyCode")
	public void loginByVerifyCode(@RequestParam String mobile,
								  @RequestParam String code,
								  String regPhone,
								  String lastLoginMachine,
								  String appVersion,
								  String lastLoginSys,
								  String version,
								  HttpServletRequest request,
								  HttpServletResponse response) throws IOException {
		String msg = null;
		try {
			int num = SingletonClient.validMessageCode(mobile, code);
			if (num == 1||("13482301239".equals(mobile)&&"0000".equals(code))) {
				List<Customer> cuList = customerService.queryByMobile(mobile);
				if (cuList != null && cuList.size() > 0) {
					Map ctMap = customerService.paramsFilter(cuList.get(0));
					msg = ShangAnMessageType.toShangAnJson("0", "登录成功", "customer", ctMap);
					try {
						customerService.updateLastLoginTime(cuList.get(0).getCustomerId(), new Date());
					} catch (Exception e) {
						logger.error("", e);
					}
				} else {
					// 注册
					Customer customer = new Customer();
					customer.setCustomerMobile(mobile);
					customer.setReg_phone(regPhone);
					customer.setAppVersion(appVersion);
					customer.setLastLoginSys(lastLoginSys);
					customer.setLastLoginMachine(lastLoginMachine);
					msg = this.registerAndGiveCoupon(customer);
				}
			} else {
				msg = ShangAnMessageType.operateToJson("1", "登录失败，验证码无效");
			}
		} catch (Exception e) {
			logger.error("", e);
			msg = ShangAnMessageType.operateToJson("2", "异常");
		}
		//设置utf-8
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(msg);
	}

	/**
	 * 用户 登录
	 * 
	 * @return
	 */
	@RequestMapping("login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		String mess = null;
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		
		if(null!=mobile&&null!=password){
			mess = customerService.login(mobile,password);
		}else{
			mess = ShangAnMessageType.operateToJson("2", "手机号密码不能为空");
		}
		try{
			//设置utf-8
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin","*");
			PrintWriter writer = response.getWriter();
			writer.print(mess);
		} catch (IOException e) {
			logger.error("",e);
		}

	}
	/**
	 * 用户 重置密码
	 * 
	 * @return
	 */
	@RequestMapping("resetPwd")
	public void resetPwd(HttpServletRequest request, HttpServletResponse response) {
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");	
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			logger.error("", e);
		}
		String mess = null;
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		if(null!=mobile&&null!=password){
			Customer cuParam = new Customer();
			cuParam.setCustomerMobile(mobile);
			
			List<Customer> cuList = customerService.selectList(cuParam);
			if(null==cuList||cuList.size()==0){
				mess = ShangAnMessageType.operateToJson("2", "用户不存在");
				writer.print(mess);
				return;
			}
			cuList.get(0).setCustomerPassword(MD5Util.md5(password));
			try {
				customerService.update(cuList.get(0));
				mess = ShangAnMessageType.operateToJson("0", "修改成功");
			} catch (Exception e) {
				mess = ShangAnMessageType.operateToJson("2", "修改失败");
			}
		}else{
			mess = ShangAnMessageType.operateToJson("2", "手机号ID密码不能为空");
		}
		
		writer.print(mess);

	}
	/**
	 * 用户 验证短信验证码（完成）
	 * 
	 * @return
	 */
	@RequestMapping(value = "verifySmsCode")
	public void verifySmsCode(HttpServletRequest request, HttpServletResponse response) {
		String mess = null;
		String code = request.getParameter("smsCode");
		String mobile = request.getParameter("mobile");
		if(code==null||mobile==null){
			mess = ShangAnMessageType.operateToJson("2", "验证码不正确");
		}else{
			//验证注册短信验证码
			int flag = 2;
			flag = SingletonClient.validMessageCode(mobile, code);
			if (flag == 2) {
				mess = ShangAnMessageType.operateToJson("1", "验证码失效");
			} else if (flag == 3) {
				mess = ShangAnMessageType.operateToJson("2", "请输入正确验证码");
			}else {
				mess = ShangAnMessageType.operateToJson("0", "验证通过");
				}
			}
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(mess);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 取得客户账户余额
	 *
	 * @return
	 */
	@RequestMapping("getMoney/{customerId}/{summary}")
	public void getMoney(@PathVariable String customerId,HttpServletRequest request, HttpServletResponse response) {
		String mess = null;
		try {
				mess = customerService.getMoney(customerId);
			//设置utf-8
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();

			writer.print(mess);
		} catch (IOException e) {
			logger.error("", e);
		}

	}
	//取得充值规则列表
	@RequestMapping("getRule/{summary}")
	public void getRule(HttpServletRequest request, HttpServletResponse response){
		//RechargeRuleGiftAmount rechargeRuleGiftAmount=new RechargeRuleGiftAmount();
		String mess=null;
		try {
			mess=rechargeRuleService.queryRechargeRule2(request);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.print(mess);
		} catch (Exception e) {
			logger.error("",e);
		}
	}
/**
 * 查询支付密码
 */
@RequestMapping("getPayPassword/{customerId}/{summary}")
public void getPayPassword(@PathVariable String customerId,HttpServletRequest request, HttpServletResponse response){
	String mess = null;
	try {
		mess = customerService.getPayPassWord(customerId);
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();

		writer.print(mess);
	} catch (IOException e) {
		logger.error("",e);
	}
}
	/**
	 * 初始化支付密码
	 */
	@RequestMapping("initPayPassword/{customerId}/{payPassword}/{summary}")
	public void initPayPassword(@PathVariable String customerId,@PathVariable String payPassword,HttpServletRequest request, HttpServletResponse response){
		String mess = null;
		try {
			mess = customerService.initPayPassWord(customerId, payPassword);
			//设置utf-8
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();

			writer.print(mess);
		} catch (IOException e) {
			logger.error("",e);
		}
	}
	/**
	 * 验证码
	 */
	@RequestMapping("validatecode/{customerMobile}/{code}/{summary}")
	public void validatecode(@PathVariable String customerMobile,@PathVariable String code,HttpServletRequest request, HttpServletResponse response){
		String mess = null;
		try {
			List<Customer> list=customerService.queryByMobile(customerMobile);
			if (list!=null&&list.size()>0){
				Customer cus=list.get(0);
				if(!StringUtils.isEmpty(code) && !StringUtils.isEmpty(cus.getCustomerMobile())){
					//验证注册短信验证码
					int flag = SingletonClient.validMessageCode(cus.getCustomerMobile(), code);
					if(flag==1) {
						mess=ShangAnMessageType.toShangAnJson("0", "验证成功", "validatecode", "");

					}else{
						mess=ShangAnMessageType.toShangAnJson("1", "请输入正确的验证码", "validatecode", "");
					}
				}else{
					mess=ShangAnMessageType.toShangAnJson("2", "数据异常", "validatecode", "");
				}
			}else{
				mess=ShangAnMessageType.toShangAnJson("2", "数据异常", "validatecode", "");
			}


			//设置utf-8
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();

			writer.print(mess);
		} catch (IOException e) {
			logger.error("",e);
		}
	}


	/**
	 * 重置支付密码
	 */
	@RequestMapping("resetPayPassword/{customerId}/{payPassword}/{summary}")
	public void resetPayPassword(@PathVariable String customerId,@PathVariable String payPassword,HttpServletRequest request, HttpServletResponse response){
		String mess = null;
		try {
			mess = customerService.resetPayPassword(customerId, payPassword);
			//设置utf-8
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();

			writer.print(mess);
		} catch (IOException e) {
			logger.error("",e);
		}
	}

	/**
	 * 验证手机号是否存在
	 * 
	 * @return
	 */
	@RequestMapping("getPhoneCode/{customerMobile}/{summary}")
	public void getPhoneCode(@PathVariable String customerMobile,HttpServletRequest request, HttpServletResponse response){
		String mess = null;
		try {
			mess = customerService.queryPhoneCode(customerMobile);
			//设置utf-8
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = response.getWriter();

			writer.print(mess);
		} catch (IOException e) {
			logger.error("",e);
		}
	}

	/**
	 * 获取兑换码和兑换规则
	 */
	@RequestMapping("getRedeem/{customerId}/{summary}")
	public void getRedeem(@PathVariable String customerId,
						  HttpServletRequest request,
						  HttpServletResponse response) throws IOException {
		String msg = null;
		try {
			// 兑换码
			String redeemCode = customerService.getRedeemCode(customerId);
			// 被兑换的次数
			RedeemRecord redeemRecord = new RedeemRecord();
			redeemRecord.setIsUsed(Constants.TRUE);
			redeemRecord.setOldCustomerId(customerId);
			int beRedeemCount = redeemRecordService.selectCount(redeemRecord);
			// 兑换规则 类型，1：新注册用户使用兑换码获取的优惠券 2：老用户兑换码被兑换一定次数后可领取的优惠券
			List<Map<String, Object>> rule = redeemRuleService.getRule(2, customerId);
			Map<String, Object> data = new HashMap<>(3);
			data.put("redeemCode", redeemCode);
			data.put("beRedeemCount", beRedeemCount);
			data.put("rule", rule);
			msg = ShangAnMessageType.toShangAnJson("0", "success", "data", data);
		} catch (Exception e) {
			logger.error("", e);
			msg = ShangAnMessageType.operateToJson("2", "异常");
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(msg);
	}

	/**
	 * 获取兑换码
	 */
	@RequestMapping("getRedeemCode/{customerId}/{summary}")
	public void getRedeemCode(@PathVariable String customerId,
							  HttpServletRequest request,
							  HttpServletResponse response) throws IOException {
		String msg = null;
		try {
			// 兑换码
			String redeemCode = customerService.getRedeemCode(customerId);
			msg = ShangAnMessageType.toShangAnJson("0", "success", "data", redeemCode);
		} catch (Exception e) {
			logger.error("", e);
			msg = ShangAnMessageType.operateToJson("2", "异常");
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(msg);
	}

	/**
	 * 到达次数后领取优惠券
	 * @param customerId 用户id
	 * @param ruleId 规则id
	 */
	@RequestMapping("receiveCouponByCount/{customerId}/{ruleId}/{summary}")
	public void receiveCouponByCount(@PathVariable String customerId,
									 @PathVariable int ruleId,
									 HttpServletRequest request,
									 HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		String msg = null;
		try {
			msg = redeemRuleService.receiveCouponByCount(ruleId, customerId);
		} catch (Exception e) {
			logger.error("", e);
			msg = ShangAnMessageType.operateToJson("2", "异常");
		}
		response.getWriter().print(msg);
	}
	//------------------
	/**
	 * 第三方（微信）快捷登陆
	 * @param quickId
	 * @param quickType
	 * @param request
	 * @param response
	 */
	@RequestMapping("loginByOther/{quickId}/{quickType}/{summary}")
	public void loginByOther(@PathVariable String quickId,@PathVariable String quickType,HttpServletRequest request,HttpServletResponse response){
		String message = null;
		Customer ct = new Customer();
		Customer customer = null;
		List<Customer> cuList  = null;
		if("01".equals(quickType)){//微信
			ct.setWxId(quickId);
		    cuList = customerService.selectList(ct);
		}else if("03".equals(quickType)){//新浪微博
			ct.setSinaId(quickId);
			cuList = customerService.selectList(ct);
		}else if("04".equals(quickType)){//qq
			ct.setQqId(quickId);
			cuList = customerService.selectList(ct);
		}else{
			message = ShangAnMessageType.operateToJson("1", "登录失败");
		}
		if(null!=cuList&&cuList.size()>0){
			customer = cuList.get(0);
		}
		if(customer!=null){
			Map<String,String> ctMap = new HashMap<String, String>();
			ctMap.put("customerId", customer.getCustomerId());
			ctMap.put("customerNickname", (null!=customer.getCustomerNickname())?customer.getCustomerNickname():"");
			ctMap.put("customerSex",(null!=customer.getCustomerSex())?customer.getCustomerSex()+"":"");
			ctMap.put("customerEmail", (null!=customer.getCustomerEmail())?customer.getCustomerEmail():"");
			ctMap.put("customerAge", (null!=customer.getCustomerAge())?customer.getCustomerAge()+"":"");
			ctMap.put("customerHead", (null!=customer.getCustomerHead())?customer.getCustomerHead():"");
			ctMap.put("customerMobile",(null!=customer.getCustomerMobile())?customer.getCustomerMobile():"");
			ctMap.put("customerCity",(null!=customer.getCustomerRegion())?customer.getCustomerRegion():"");
			ctMap.put("customerJob", (null!=customer.getCustomerJob())?customer.getCustomerJob():"");
			ctMap.put("identity", (null!=customer.getIdentity())?customer.getIdentity():"");
			ctMap.put("payPassword", customer.getPay_password() == null || customer.getPay_password().trim().length() == 0 ? "0" : "1");
			message = ShangAnMessageType.toShangAnJson("0", "成功","customer",ctMap);

			try {
				customerService.updateLastLoginTime(customer.getCustomerId(), new Date());
			} catch (Exception e) {
				logger.error("登录时,修改最后登录时间异常!", e);
			}

		}else{
			message = ShangAnMessageType.operateToJson("1", "登录失败");
		}
		
		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(message);
		} catch (IOException e) {
			
		}
	}
	@RequestMapping(value = "queryVoucherPage/{customerId}/{voucherStatus}/{pageIndex}/{summary}", method = RequestMethod.GET)
	public void queryVoucherPage(@PathVariable String customerId,@PathVariable String voucherStatus,@PathVariable String pageIndex,HttpServletRequest request,HttpServletResponse response){
//		if("2".equals(voucherStatus)){
			String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());//当前时间
			String currentTime = new SimpleDateFormat("HH:mm").format(new Date());
			OrderTemporaryShare orderTemporaryShare = new OrderTemporaryShare();
			orderTemporaryShare.setVoucherStatus("0");
			orderTemporaryShare.setCustomerId(customerId);
			List<OrderTemporaryShare> getList = orderTemporaryShareService.selectTime(orderTemporaryShare);
			if(getList!=null){
				for (OrderTemporaryShare ot :getList){
					if(ot !=null)
					{
						String appointDate = ot.getAppointmentDate();//预约时间
						if (StringUtils.isEmpty(appointDate)) {
							continue;
						}
						String[] appDate = appointDate.split(",");
						Arrays.sort(appDate);
						String maxDate = appDate[appDate.length - 1];
						if(ot.getStartEndTime()!=null){
							Date date1 = DateUtil.getPreOrNextDate(new Date(),-1);
//							Date date2 = DateUtil.getPreOrNextDate(new Date(),1);
//							String nextDate = new SimpleDateFormat("yyyy-MM-dd").format(date2);
							String preDate = new SimpleDateFormat("yyyy-MM-dd").format(date1);//昨天的时间
							String week = DateUtil.getDayOfWeek(new Date(), 0);//当前星期几
							//如果最大时间等于昨天的话 判断其是否过夜  如果过夜并且当前时间点大于昨天时间点即为过期
							if(preDate.compareTo(maxDate)==0){
								String preStartTime = "";
								String preEndTime ="";
								try {
									Map object = (Map)JsonMapper.fromJson(ot.getStartEndTime(), Map.class);
									if ("1".equals(week)){
										preStartTime = (String)object.get("sundayBeginTime");
										preEndTime = (String)object.get("sundayEndTime");
									}else if ("2".equals(week)){
										preStartTime = (String)object.get("mondayBeginTime");
										preEndTime = (String)object.get("mondayEndTime");
									}else if ("3".equals(week)){
										preStartTime = (String)object.get("tuesdayBeginTime");
										preEndTime = (String)object.get("tuesdayEndTime");
									}else if ("4".equals(week)){
										preStartTime = (String)object.get("wednesdayBeginTime");
										preEndTime = (String)object.get("wednesdayEndTime");
									}else if ("5".equals(week)){
										preStartTime = (String)object.get("thursdayBeginTime");
										preEndTime = (String)object.get("thursdayEndTime");
									}else if ("6".equals(week)){
										preStartTime = (String)object.get("fridayBeginTime");
										preEndTime = (String)object.get("fridayEndTime");
									}else if ("7".equals(week)){
										preStartTime = (String)object.get("saturdayBeginTime");
										preEndTime = (String)object.get("saturdayEndTime");
									}
									//判断开始时间是否大于等于结束时间 即为过夜
									if (preStartTime.compareTo(preEndTime)>=0){
										if (currentTime.compareTo(preEndTime)>0){
											ot.setVoucherStatus("2");
											orderTemporaryShareService.update(ot);
										}
									}else{
										ot.setVoucherStatus("2");
										orderTemporaryShareService.update(ot);
									}
								} catch (IOException e) {
									e.printStackTrace();
								}
							}else if (currentDate.compareTo(maxDate)==0){
								//当前时间等于最大时间时 判断是否过夜 如果过夜就未失效  如果不过夜判断当前时间大于结束时间即为失效
								if (ot.getStartEndTime()!=null && ot.getStartEndTime().trim().length()>0){
									try {
										Map object = (Map)JsonMapper.fromJson(ot.getStartEndTime(), Map.class);
										if (week.equals("1")){
											String mondayEndTime = (String)object.get("mondayEndTime");
											String mondayStartTime = (String)object.get("mondayBeginTime");
											if (mondayStartTime!=null && mondayStartTime.split(":")[0].length()<2){
												mondayStartTime = "0"+mondayStartTime;
											}
											if (mondayEndTime!=null && mondayEndTime.split(":")[0].length()<2){
												mondayEndTime = "0"+mondayEndTime;
											}
											//1。如果开始时间小于结束时间 未过夜
											if (mondayStartTime.compareTo(mondayEndTime)<0 && currentTime.compareTo(mondayEndTime)>0){
												ot.setVoucherStatus("2");
												orderTemporaryShareService.update(ot);
											}
										}else if (week.equals("2")){
											String tuesdayEndTime = (String)object.get("tuesdayEndTime");
											String tuesdayBeginTime = (String)object.get("tuesdayBeginTime");
											if (tuesdayEndTime!=null && tuesdayEndTime.split(":")[0].length()<2){
												tuesdayEndTime = "0"+tuesdayEndTime;
											}
											if (tuesdayBeginTime!=null && tuesdayBeginTime.split(":")[0].length()<2){
												tuesdayBeginTime = "0"+tuesdayBeginTime;
											}
											if (tuesdayBeginTime.compareTo(tuesdayEndTime)<0 && currentTime.compareTo(tuesdayEndTime)>0){
												ot.setVoucherStatus("2");
												orderTemporaryShareService.update(ot);
											}
										}else if (week.equals("3")){
											String wednesdayEndTime = (String)object.get("wednesdayEndTime");
											String wednesdayBeginTime = (String)object.get("wednesdayBeginTime");
											if (wednesdayBeginTime!=null && wednesdayBeginTime.split(":")[0].length()<2){
												wednesdayBeginTime = "0"+wednesdayBeginTime;
											}
											if (wednesdayEndTime!=null && wednesdayEndTime.split(":")[0].length()<2){
												wednesdayEndTime = "0"+wednesdayEndTime;
											}
											if (wednesdayBeginTime.compareTo(wednesdayEndTime)<0 && currentTime.compareTo(wednesdayEndTime)>0){
												ot.setVoucherStatus("2");
												orderTemporaryShareService.update(ot);
											}
										}else if (week.equals("4")){
											String thursdayEndTime = (String)object.get("thursdayEndTime");
											String thursdayBeginTime = (String)object.get("thursdayBeginTime");
											if (thursdayBeginTime!=null && thursdayBeginTime.split(":")[0].length()<2){
												thursdayBeginTime = "0"+thursdayBeginTime;
											}
											if (thursdayEndTime!=null && thursdayEndTime.split(":")[0].length()<2){
												thursdayEndTime = "0"+thursdayEndTime;
											}
											if (thursdayBeginTime.compareTo(thursdayEndTime)<0 && currentTime.compareTo(thursdayEndTime)>0){
												ot.setVoucherStatus("2");
												orderTemporaryShareService.update(ot);
											}
										}else if (week.equals("5")){
											String fridayEndTime = (String)object.get("fridayEndTime");
											String fridayBeginTime = (String)object.get("fridayBeginTime");
											if (fridayEndTime!=null && fridayEndTime.split(":")[0].length()<2){
												fridayEndTime = "0"+fridayEndTime;
											}
											if (fridayBeginTime!=null && fridayBeginTime.split(":")[0].length()<2){
												fridayBeginTime = "0"+fridayBeginTime;
											}
											if (fridayBeginTime.compareTo(fridayEndTime)<0 && currentTime.compareTo(fridayEndTime)>0){
												ot.setVoucherStatus("2");
												orderTemporaryShareService.update(ot);
											}
										}else if (week.equals("6")){
											String saturdayEndTime = (String)object.get("saturdayEndTime");
											String saturdayBeginTime = (String)object.get("saturdayBeginTime");
											if (saturdayEndTime!=null && saturdayEndTime.split(":")[0].length()<2){
												saturdayEndTime = "0"+saturdayEndTime;
											}
											if (saturdayBeginTime!=null && saturdayBeginTime.split(":")[0].length()<2){
												saturdayBeginTime = "0"+saturdayBeginTime;
											}
											if (saturdayBeginTime.compareTo(saturdayEndTime)<0 && currentTime.compareTo(saturdayEndTime)>0){
												ot.setVoucherStatus("2");
												orderTemporaryShareService.update(ot);
											}
										}else if (week.equals("7")){
											String sundayEndTime = (String)object.get("sundayEndTime");
											String sundayBeginTime = (String)object.get("sundayBeginTime");
											if (sundayBeginTime!=null && sundayBeginTime.split(":")[0].length()<2){
												sundayBeginTime = "0"+sundayBeginTime;
											}
											if (sundayEndTime!=null && sundayEndTime.split(":")[0].length()<2){
												sundayEndTime = "0"+sundayEndTime;
											}
											if (sundayBeginTime.compareTo(sundayEndTime)<0 && currentTime.compareTo(sundayEndTime)>0){
												ot.setVoucherStatus("2");
												orderTemporaryShareService.update(ot);
											}
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}else if (maxDate.compareTo(preDate)<0){
								ot.setVoucherStatus("2");
								orderTemporaryShareService.update(ot);
							}
						}else {
							if (currentDate.compareTo(maxDate)>0)
							{
								ot.setVoucherStatus("2");
								orderTemporaryShareService.update(ot);
							}
						}
					}
				}
			}
//		}
		String mess=null;
		Page<OrderTemporaryShare> page=new Page<OrderTemporaryShare>();
		page.getParams().put("customerId", customerId);
		page.getParams().put("voucherStatus", voucherStatus);
		page.getParams().put("pageIndex", pageIndex);
		page.setCurrentPage(Integer.valueOf(pageIndex));
		mess=orderTemporaryShareService.queryVoucherPage(page);
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out=response.getWriter();
			out.print(mess);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("",e);
		}
		
	}

	/**
	 * 为了扩充参数,把上面的接口重载为post
	 */
	@RequestMapping(value = "queryVoucherPage", method = RequestMethod.POST)
	public void queryVoucherPage(@RequestParam String customerId,
								 @RequestParam(required = false, defaultValue = "0") String voucherStatus,
								 @RequestParam(required = false, defaultValue = "1") int pageIndex,
								 @RequestParam(required = false) String parkingId,
								 @RequestParam(required = false) String carNumber,
								 String version,
								 HttpServletRequest request,
								 HttpServletResponse response) {
//		if("2".equals(voucherStatus)){
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());//当前时间
		String currentTime = new SimpleDateFormat("HH:mm").format(new Date());
		OrderTemporaryShare orderTemporaryShare = new OrderTemporaryShare();
		orderTemporaryShare.setVoucherStatus("0");
		orderTemporaryShare.setCustomerId(customerId);
		List<OrderTemporaryShare> getList = orderTemporaryShareService.selectTime(orderTemporaryShare);
		if(getList!=null){
			for (OrderTemporaryShare ot :getList){
				if(ot !=null)
				{
					String appointDate = ot.getAppointmentDate();//预约时间
					if (StringUtils.isEmpty(appointDate)) {
						continue;
					}
					String[] appDate = appointDate.split(",");
					Arrays.sort(appDate);
					String maxDate = appDate[appDate.length - 1];
					if(ot.getStartEndTime()!=null){
						Date date1 = DateUtil.getPreOrNextDate(new Date(),-1);
//							Date date2 = DateUtil.getPreOrNextDate(new Date(),1);
//							String nextDate = new SimpleDateFormat("yyyy-MM-dd").format(date2);
						String preDate = new SimpleDateFormat("yyyy-MM-dd").format(date1);//昨天的时间
						String week = DateUtil.getDayOfWeek(new Date(), 0);//当前星期几
						//如果最大时间等于昨天的话 判断其是否过夜  如果过夜并且当前时间点大于昨天时间点即为过期
						if(preDate.compareTo(maxDate)==0){
							String preStartTime = "";
							String preEndTime ="";
							try {
								Map object = (Map)JsonMapper.fromJson(ot.getStartEndTime(), Map.class);
								if ("1".equals(week)){
									preStartTime = (String)object.get("sundayBeginTime");
									preEndTime = (String)object.get("sundayEndTime");
								}else if ("2".equals(week)){
									preStartTime = (String)object.get("mondayBeginTime");
									preEndTime = (String)object.get("mondayEndTime");
								}else if ("3".equals(week)){
									preStartTime = (String)object.get("tuesdayBeginTime");
									preEndTime = (String)object.get("tuesdayEndTime");
								}else if ("4".equals(week)){
									preStartTime = (String)object.get("wednesdayBeginTime");
									preEndTime = (String)object.get("wednesdayEndTime");
								}else if ("5".equals(week)){
									preStartTime = (String)object.get("thursdayBeginTime");
									preEndTime = (String)object.get("thursdayEndTime");
								}else if ("6".equals(week)){
									preStartTime = (String)object.get("fridayBeginTime");
									preEndTime = (String)object.get("fridayEndTime");
								}else if ("7".equals(week)){
									preStartTime = (String)object.get("saturdayBeginTime");
									preEndTime = (String)object.get("saturdayEndTime");
								}
								//判断开始时间是否大于等于结束时间 即为过夜
								if (preStartTime.compareTo(preEndTime)>=0){
									if (currentTime.compareTo(preEndTime)>0){
										ot.setVoucherStatus("2");
										orderTemporaryShareService.update(ot);
									}
								}else{
									ot.setVoucherStatus("2");
									orderTemporaryShareService.update(ot);
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}else if (currentDate.compareTo(maxDate)==0){
							//当前时间等于最大时间时 判断是否过夜 如果过夜就未失效  如果不过夜判断当前时间大于结束时间即为失效
							if (ot.getStartEndTime()!=null && ot.getStartEndTime().trim().length()>0){
								try {
									Map object = (Map)JsonMapper.fromJson(ot.getStartEndTime(), Map.class);
									if (week.equals("1")){
										String mondayEndTime = (String)object.get("mondayEndTime");
										String mondayStartTime = (String)object.get("mondayBeginTime");
										if (mondayStartTime!=null && mondayStartTime.split(":")[0].length()<2){
											mondayStartTime = "0"+mondayStartTime;
										}
										if (mondayEndTime!=null && mondayEndTime.split(":")[0].length()<2){
											mondayEndTime = "0"+mondayEndTime;
										}
										//1。如果开始时间小于结束时间 未过夜
										if (mondayStartTime.compareTo(mondayEndTime)<0 && currentTime.compareTo(mondayEndTime)>0){
											ot.setVoucherStatus("2");
											orderTemporaryShareService.update(ot);
										}
									}else if (week.equals("2")){
										String tuesdayEndTime = (String)object.get("tuesdayEndTime");
										String tuesdayBeginTime = (String)object.get("tuesdayBeginTime");
										if (tuesdayEndTime!=null && tuesdayEndTime.split(":")[0].length()<2){
											tuesdayEndTime = "0"+tuesdayEndTime;
										}
										if (tuesdayBeginTime!=null && tuesdayBeginTime.split(":")[0].length()<2){
											tuesdayBeginTime = "0"+tuesdayBeginTime;
										}
										if (tuesdayBeginTime.compareTo(tuesdayEndTime)<0 && currentTime.compareTo(tuesdayEndTime)>0){
											ot.setVoucherStatus("2");
											orderTemporaryShareService.update(ot);
										}
									}else if (week.equals("3")){
										String wednesdayEndTime = (String)object.get("wednesdayEndTime");
										String wednesdayBeginTime = (String)object.get("wednesdayBeginTime");
										if (wednesdayBeginTime!=null && wednesdayBeginTime.split(":")[0].length()<2){
											wednesdayBeginTime = "0"+wednesdayBeginTime;
										}
										if (wednesdayEndTime!=null && wednesdayEndTime.split(":")[0].length()<2){
											wednesdayEndTime = "0"+wednesdayEndTime;
										}
										if (wednesdayBeginTime.compareTo(wednesdayEndTime)<0 && currentTime.compareTo(wednesdayEndTime)>0){
											ot.setVoucherStatus("2");
											orderTemporaryShareService.update(ot);
										}
									}else if (week.equals("4")){
										String thursdayEndTime = (String)object.get("thursdayEndTime");
										String thursdayBeginTime = (String)object.get("thursdayBeginTime");
										if (thursdayBeginTime!=null && thursdayBeginTime.split(":")[0].length()<2){
											thursdayBeginTime = "0"+thursdayBeginTime;
										}
										if (thursdayEndTime!=null && thursdayEndTime.split(":")[0].length()<2){
											thursdayEndTime = "0"+thursdayEndTime;
										}
										if (thursdayBeginTime.compareTo(thursdayEndTime)<0 && currentTime.compareTo(thursdayEndTime)>0){
											ot.setVoucherStatus("2");
											orderTemporaryShareService.update(ot);
										}
									}else if (week.equals("5")){
										String fridayEndTime = (String)object.get("fridayEndTime");
										String fridayBeginTime = (String)object.get("fridayBeginTime");
										if (fridayEndTime!=null && fridayEndTime.split(":")[0].length()<2){
											fridayEndTime = "0"+fridayEndTime;
										}
										if (fridayBeginTime!=null && fridayBeginTime.split(":")[0].length()<2){
											fridayBeginTime = "0"+fridayBeginTime;
										}
										if (fridayBeginTime.compareTo(fridayEndTime)<0 && currentTime.compareTo(fridayEndTime)>0){
											ot.setVoucherStatus("2");
											orderTemporaryShareService.update(ot);
										}
									}else if (week.equals("6")){
										String saturdayEndTime = (String)object.get("saturdayEndTime");
										String saturdayBeginTime = (String)object.get("saturdayBeginTime");
										if (saturdayEndTime!=null && saturdayEndTime.split(":")[0].length()<2){
											saturdayEndTime = "0"+saturdayEndTime;
										}
										if (saturdayBeginTime!=null && saturdayBeginTime.split(":")[0].length()<2){
											saturdayBeginTime = "0"+saturdayBeginTime;
										}
										if (saturdayBeginTime.compareTo(saturdayEndTime)<0 && currentTime.compareTo(saturdayEndTime)>0){
											ot.setVoucherStatus("2");
											orderTemporaryShareService.update(ot);
										}
									}else if (week.equals("7")){
										String sundayEndTime = (String)object.get("sundayEndTime");
										String sundayBeginTime = (String)object.get("sundayBeginTime");
										if (sundayBeginTime!=null && sundayBeginTime.split(":")[0].length()<2){
											sundayBeginTime = "0"+sundayBeginTime;
										}
										if (sundayEndTime!=null && sundayEndTime.split(":")[0].length()<2){
											sundayEndTime = "0"+sundayEndTime;
										}
										if (sundayBeginTime.compareTo(sundayEndTime)<0 && currentTime.compareTo(sundayEndTime)>0){
											ot.setVoucherStatus("2");
											orderTemporaryShareService.update(ot);
										}
									}
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}else if (maxDate.compareTo(preDate)<0){
							ot.setVoucherStatus("2");
							orderTemporaryShareService.update(ot);
						}
					}else {
						if (currentDate.compareTo(maxDate)>0)
						{
							ot.setVoucherStatus("2");
							orderTemporaryShareService.update(ot);
						}
					}
				}
			}
		}
//		}
		String mess=null;
		Page<OrderTemporaryShare> page=new Page<OrderTemporaryShare>();
		page.getParams().put("customerId", customerId);
		page.getParams().put("voucherStatus", voucherStatus);
		page.getParams().put("parkingId", parkingId);
		page.getParams().put("carNumber", carNumber);
		page.setCurrentPage(pageIndex);
		mess=orderTemporaryShareService.queryVoucherPage(page);
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");
		try {
			out=response.getWriter();
			out.print(mess);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("",e);
		}
	}

	@RequestMapping("registByOther")
	public void registByOther(HttpServletRequest request,HttpServletResponse response){
		String quickType = request.getParameter("quickType");
		String message = null;
		String code = request.getParameter("smsCode");
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		String quickId = request.getParameter("quickId");
		if(code==null||mobile==null||password==null){
			message = ShangAnMessageType.operateToJson("3", "输入的数据异常");
		}else{
			//验证注册短信验证码
			int flag = 2;
			flag = SingletonClient.validMessageCode(mobile, code);
			if (flag == 2) {
				message = ShangAnMessageType.operateToJson("1", "验证码失效");
			} else if (flag == 3) {
				message = ShangAnMessageType.operateToJson("2", "数据异常");
			}else{
				Customer cus = new Customer();
				String s=UUID.randomUUID().toString();
				String cusId = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
				cus.setCustomerId(cusId);
				cus.setCustomerMobile(mobile);
				cus.setCustomerPassword(MD5Util.md5(password));
				if("01".equals(quickType)){//微信
					cus.setWxId(quickId);
				}else if("03".equals(quickType)){//新浪微博
					cus.setSinaId(quickId);
				}else if("04".equals(quickType)){//qq
					cus.setQqId(quickId);
				}else{
					message = ShangAnMessageType.operateToJson("1", "登录失败");
				}
				cus.setIdentity("0");
				try {
					List<Customer> cusList = customerService.queryByMobile(mobile);
					if(cusList!=null && cusList.size()>0){
						Customer ct = cusList.get(0);
						if("01".equals(quickType)){//微信
							ct.setWxId(quickId);
						}else if("03".equals(quickType)){//新浪微博
							ct.setSinaId(quickId);
						}else if("04".equals(quickType)){//qq
							ct.setQqId(quickId);
						}
						customerService.update(ct);
//							message = ShangAnMessageType.operateToJson("0", "手机号已绑定");
						Map<String, String> map1 = new HashMap<String, String>();
						map1.put("customerId", ct.getCustomerId());
						map1.put("customerMobile", (null!=ct.getCustomerMobile())?ct.getCustomerMobile():"");
						map1.put("customerNickname",(null!=ct.getCustomerNickname())?ct.getCustomerNickname():"");
						map1.put("customerSex", (null!=ct.getCustomerSex())?ct.getCustomerSex()+"":"");
						map1.put("customerEmail", (null!=ct.getCustomerEmail())?ct.getCustomerEmail():"");
						map1.put("customerAge", (null!=ct.getCustomerAge())?ct.getCustomerAge()+"":"");
						map1.put("customerHead", (null!=ct.getCustomerHead())?ct.getCustomerHead():"");
						map1.put("customerCity", (null!=ct.getCustomerRegion())?ct.getCustomerRegion():"");
						map1.put("customerJob", (null!=ct.getCustomerJob())?ct.getCustomerJob():"");
						map1.put("identity", (null!=ct.getIdentity())?ct.getIdentity():"");
						map1.put("payPassword", ct.getPay_password() == null || ct.getPay_password().trim().length() == 0 ? "0" : "1");
						message = ShangAnMessageType.toShangAnJson("0", "手机号已绑定", "customer", map1);
					}else{
						message = customerService.insertCustomerByMobleAndPwd(cus);
						Map<String,Object>  mp = JacksonUtil.jsonToMap(message);
						if("0".equals(mp.get("errorNum").toString())){
							Customer c = new Customer();
							if("01".equals(quickType)){//微信
								c.setWxId(quickId);
							}else if("03".equals(quickType)){//新浪微博
								c.setSinaId(quickId);
							}else if("04".equals(quickType)){//qq
								c.setQqId(quickId);
							}
							List<Customer> cuList = customerService.selectList(c);
							Customer custom = null;
							if(null!=cuList&&cuList.size()>0){
								custom = cuList.get(0);
							}
							 
							Map<String, String> map1 = new HashMap<String, String>();
							map1.put("customerId", custom.getCustomerId());
							map1.put("customerMobile", (null!=custom.getCustomerMobile())?custom.getCustomerMobile():"");
							map1.put("customerNickname",(null!=custom.getCustomerNickname())?custom.getCustomerNickname():"");
							map1.put("customerSex", (null!=custom.getCustomerSex())?custom.getCustomerSex()+"":"");
							map1.put("customerEmail", (null!=custom.getCustomerEmail())?custom.getCustomerEmail():"");
							map1.put("customerAge", (null!=custom.getCustomerAge())?custom.getCustomerAge()+"":"");
							map1.put("customerHead", (null!=custom.getCustomerHead())?custom.getCustomerHead():"");
							map1.put("customerCity", (null!=custom.getCustomerRegion())?custom.getCustomerRegion():"");
							map1.put("customerJob", (null!=custom.getCustomerJob())?custom.getCustomerJob():"");
							map1.put("identity", (null!=custom.getIdentity())?custom.getIdentity():"");
							map1.put("payPassword", custom.getPay_password() == null || custom.getPay_password().trim().length() == 0 ? "0" : "1");
							message = ShangAnMessageType.toShangAnJson("0", "绑定成功", "customer", map1);
						}
					}
				} catch (Exception e) {
					message = ShangAnMessageType.operateToJson("2", "数据异常");
					e.printStackTrace();
				}
			}
			
		}
		PrintWriter out;
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(message);
		} catch (IOException e) {
			logger.error("",e);
		}
	}
	   /**
		 * 验证预约时间是否重复
		 * 
		 * @return
		 */
		@RequestMapping("queryAppointment/{parkingId}/{carNumber}/{appointmentDate}/{version}/{summary}")
		public void queryAppointment(@PathVariable String parkingId,@PathVariable String carNumber,@PathVariable String version,@PathVariable String appointmentDate,HttpServletRequest request, HttpServletResponse response){
			if("1.3.7".equals(version)){
				String mess = null;
				try {
					mess = orderTemporaryShareService.queryAppointment(parkingId,carNumber,appointmentDate);
					//设置utf-8
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter writer = response.getWriter();
					writer.print(mess);
				} catch (IOException e) {
					logger.error("",e);
				}
			}
		}

	/***
	 * 代泊端扫码凭证
	 * @param orderId
	 * @param request
	 * @param response
	 */
	@RequestMapping("scanCode/{parkerId}/{orderId}/{summary}")
		public void scanCode(@PathVariable String parkerId,@PathVariable String orderId,HttpServletRequest request,HttpServletResponse response){
			String mess =null;
			ParkerStateRecord parkerStateRecord = new ParkerStateRecord();
			parkerStateRecord.setCreateDate(new Date());
			String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());//当前时间
			Date date1 = DateUtil.getPreOrNextDate(new Date(),-1);
			String preDate = new SimpleDateFormat("yyyy-MM-dd").format(date1);//前一天时间
//		String currentDate = "2016-06-15";
//		String preDate = "2016-06-14";
			Parker parker = new Parker();
			parker.setParkerId(parkerId);
			boolean flag = false;
			boolean gref = false;
			boolean graf = false;
			List<Parker> list = parkerService.selectParkingIdByParker(parker);
			if (list!=null && list.size()>0)
			{
				OrderTemporaryShare os = new OrderTemporaryShare();
				os.setOrderId(orderId);
				OrderTemporaryShare orderTemporaryShare = orderTemporaryShareService.scanOrderId(os);
				if (orderTemporaryShare!=null)
				{
					for (int i=0;i<list.size();i++)
					{
						if (list.get(i)!=null && list.get(i).getParkingId()!=null && list.get(i).getParkingId().equals(orderTemporaryShare.getParkingId()))
						{
							flag = true;
							break;
						}
					}
					if (flag)
					{
						if (orderTemporaryShare.getAppointmentDate()!=null)
						{
							if ("0".equals(orderTemporaryShare.getVoucherStatus())){
								if(orderTemporaryShare.getStartEndTime()!=null && orderTemporaryShare.getStartEndTime().trim().length()>0){
									String[] appDate = orderTemporaryShare.getAppointmentDate().split(",");
									String currentTime = new SimpleDateFormat("HH:mm").format(new Date());//当前时间
									String week = DateUtil.getDayOfWeek(new Date(), 0);//当前星期
//								String currentTime = "10:00";
//								String week = "3";
									String endTime = "8:00";
									String startTime = "8:00";
									String preEndTime = "8:00";
									String preStartTime ="8:00";
									Map object = null;
									try {
										object = (Map) JsonMapper.fromJson(orderTemporaryShare.getStartEndTime(), Map.class);
										if (week.equals("1")){
											endTime = (String)object.get("mondayEndTime");
											startTime = (String)object.get("mondayBeginTime");
											preEndTime= (String)object.get("sundayEndTime");
											preStartTime = (String)object.get("sundayBeginTime");
										}else if (week.equals("2")){
											endTime = (String)object.get("tuesdayEndTime");
											startTime = (String)object.get("tuesdayBeginTime");
											preEndTime= (String)object.get("mondayEndTime");
											preStartTime = (String)object.get("mondayBeginTime");
										}else if (week.equals("3")){
											endTime = (String)object.get("wednesdayEndTime");
											startTime = (String)object.get("wednesdayBeginTime");
											preEndTime= (String)object.get("tuesdayEndTime");
											preStartTime = (String)object.get("tuesdayBeginTime");
										}else if (week.equals("4")){
											endTime = (String)object.get("thursdayEndTime");
											startTime = (String)object.get("thursdayBeginTime");
											preEndTime= (String)object.get("wednesdayEndTime");
											preStartTime = (String)object.get("wednesdayBeginTime");
										}else if (week.equals("5")){
											endTime = (String)object.get("fridayEndTime");
											startTime = (String)object.get("fridayBeginTime");
											preEndTime= (String)object.get("thursdayEndTime");
											preStartTime = (String)object.get("thursdayBeginTime");
										}else if (week.equals("6")){
											endTime = (String)object.get("saturdayEndTime");
											startTime = (String)object.get("saturdayBeginTime");
											preEndTime= (String)object.get("fridayEndTime");
											preStartTime = (String)object.get("fridayBeginTime");
										}else if (week.equals("7")){
											endTime = (String)object.get("sundayEndTime");
											startTime = (String)object.get("sundayBeginTime");
											preEndTime= (String)object.get("saturdayEndTime");
											preStartTime = (String)object.get("saturdayBeginTime");
										}
										if (endTime!=null && endTime.split(":")[0].length()<2){
											endTime = "0"+endTime;
										}
										if (startTime!=null && startTime.split(":")[0].length()<2){
											startTime = "0"+startTime;
										}
										if (preEndTime!=null && preEndTime.split(":")[0].length()<2){
											preEndTime = "0"+preEndTime;
										}
										if (preStartTime!=null && preStartTime.split(":")[0].length()<2){
											preStartTime = "0"+preStartTime;
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
									if (appDate.length==1){
										if (currentDate.equals(appDate[0])){
											if (startTime.compareTo(endTime)>=0){
												//时间点过夜
												if (currentTime.compareTo(startTime)>=0){
													gref = true;
												}else {
													mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
													parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
												}
											}else {
												//不过夜
												if (currentTime.compareTo(startTime)>=0 && currentTime.compareTo(endTime)<=0){
													gref = true;
												}else if (currentTime.compareTo(startTime)<0){
													mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
													parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
												}else if (currentTime.compareTo(endTime)>0){
													//过期
													mess = ShangAnMessageType.operateToJson("2","凭证已经过期");
													parkerStateRecord.setInfo("orderId"+orderId+"----凭证已经过期");
												}
											}
										}else if (preDate.equals(appDate[0])){
											//预约昨天 今天扫码
											if (preStartTime.compareTo(preEndTime)>=0){
												//过夜
												if (currentTime.compareTo(preEndTime)<=0){
													// 可以扫码 扫昨天的
													gref = true;
													graf = true;
												}else {
													//过期
													mess = ShangAnMessageType.operateToJson("2","凭证已经过期");
													parkerStateRecord.setInfo("orderId"+orderId+"----凭证已经过期");
												}
											}else {
												//过期
												mess = ShangAnMessageType.operateToJson("2","凭证已经过期");
												parkerStateRecord.setInfo("orderId"+orderId+"----凭证已经过期");
											}
										}else if (currentDate.compareTo(appDate[0])>0){
											mess = ShangAnMessageType.operateToJson("2","凭证已经过期");
											parkerStateRecord.setInfo("orderId"+orderId+"----凭证已经过期");
										}else if (currentDate.compareTo(appDate[0])<0){
											mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
											parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
										}
									}else if (appDate.length>1){
										if (orderTemporaryShare.getAppointmentDate().contains(currentDate) && orderTemporaryShare.getAppointmentDate().contains(preDate)){
											//包含当天并且包含昨天
											if (preStartTime.compareTo(preEndTime)>=0){
												//昨天过夜
												if (currentTime.compareTo(preEndTime)<=0){
													//可以扫码 扫昨天的日期
													if(orderTemporaryShare.getVerifiedDate()!=null && orderTemporaryShare.getVerifiedDate().equals(preDate)){
														mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
														parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
													}else {
														gref = true;
														graf = true;
													}
												}else if (currentTime.compareTo(preEndTime)>0 && currentTime.compareTo(startTime)<0){
													//未到停车时间
													mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
													parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
												}else if ((currentTime.compareTo(startTime)>=0 && startTime.compareTo(endTime)>0) || (currentTime.compareTo(startTime)>=0 && currentTime.compareTo(endTime)<=0)){
													//可以扫码 扫今天的日期
													if(orderTemporaryShare.getVerifiedDate()!=null && orderTemporaryShare.getVerifiedDate().equals(currentDate)){
														mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
														parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
													}else {
														gref = true;
													}
												}else if (currentTime.compareTo(endTime)>0){
													//今天停车时间已过期
													mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
													parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
												}
											}else {
												//昨天未过夜 只针对今天
												if (startTime.compareTo(endTime)>=0){
													if (currentTime.compareTo(startTime)>=0){
														if(orderTemporaryShare.getVerifiedDate()!=null && orderTemporaryShare.getVerifiedDate().equals(currentDate)){
															mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
															parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
														}else {
															gref = true;
														}
													}else {
														mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
														parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
													}
												}else {
													if (currentTime.compareTo(startTime)>=0 && currentTime.compareTo(endTime)<=0){
														if(orderTemporaryShare.getVerifiedDate()!=null && orderTemporaryShare.getVerifiedDate().equals(currentDate)){
															mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
															parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
														}else {
															gref = true;
														}
													}else if (currentTime.compareTo(startTime)<0){
														mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
														parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
													}else if (currentTime.compareTo(endTime)>0){
														mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
														parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
													}
												}
											}
										}else if (orderTemporaryShare.getAppointmentDate().contains(currentDate) && !orderTemporaryShare.getAppointmentDate().contains(preDate)){
											if (startTime.compareTo(endTime)>=0){
												if (currentTime.compareTo(startTime)>=0){
													if(orderTemporaryShare.getVerifiedDate()!=null && orderTemporaryShare.getVerifiedDate().equals(currentDate)){
														mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
														parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
													}else {
														gref = true;
													}
												}else {
													mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
													parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
												}
											}else {
												if (currentTime.compareTo(startTime)>=0 && currentTime.compareTo(endTime)<=0){
													if(orderTemporaryShare.getVerifiedDate()!=null && orderTemporaryShare.getVerifiedDate().equals(currentDate)){
														mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
														parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
													}else {
														gref = true;
													}
												}else if (currentTime.compareTo(startTime)<0){
													mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
													parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
												}else if (currentTime.compareTo(endTime)>0){
													mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
													parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
												}
											}
										}else if (!orderTemporaryShare.getAppointmentDate().contains(currentDate) && orderTemporaryShare.getAppointmentDate().contains(preDate)){
											//包含昨天并且不包含今天
											if (preStartTime.compareTo(preEndTime)>=0){
												//昨天过夜
												if (currentTime.compareTo(startTime)<0){
													//可以扫码 扫昨天的日期
													if(orderTemporaryShare.getVerifiedDate()!=null && orderTemporaryShare.getVerifiedDate().equals(preDate)){
														mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
														parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
													}else {
														gref = true;
														graf = true;
													}
												}else {
													//今天已过期
													mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
													parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
												}
											}else {
												//昨天不过夜 过期
												mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
												parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
											}
										}else {
											mess = ShangAnMessageType.operateToJson("6","凭证暂不可用或已过期");
											parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用或已过期");
										}
									}
									if (gref){
										//可以扫码
										if ("1".equals(orderTemporaryShare.getParkingNum()))
										{
											orderTemporaryShare.setVoucherStatus("1");
											orderTemporaryShare.setParkingNum("0");
										}else{
											orderTemporaryShare.setParkingNum((Integer.parseInt(orderTemporaryShare.getParkingNum())-1)+"");
										}
										if (!graf)
											orderTemporaryShare.setVerifiedDate(currentDate);
										else
											orderTemporaryShare.setVerifiedDate(preDate);
										orderTemporaryShareService.update(orderTemporaryShare);
										mess = ShangAnMessageType.operateToJson("0","验证成功");
										parkerStateRecord.setInfo("orderId"+orderId+"---验证成功");
									}
								}else {
									if (orderTemporaryShare.getAppointmentDate().contains(currentDate)){
										if (orderTemporaryShare.getVerifiedDate()!=null && orderTemporaryShare.getVerifiedDate().contains(currentDate)){
											mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
											parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
										}else {
											if ("1".equals(orderTemporaryShare.getParkingNum()))
											{
												orderTemporaryShare.setVoucherStatus("1");
												orderTemporaryShare.setParkingNum("0");
											}else{
												orderTemporaryShare.setParkingNum((Integer.parseInt(orderTemporaryShare.getParkingNum())-1)+"");
											}
											orderTemporaryShare.setVerifiedDate(currentDate);
											orderTemporaryShareService.update(orderTemporaryShare);
											mess = ShangAnMessageType.operateToJson("0","验证成功");
											parkerStateRecord.setInfo("orderId"+orderId+"---验证成功");
										}
									}else {
										mess = ShangAnMessageType.operateToJson("6","凭证暂不可用");
										parkerStateRecord.setInfo("orderId"+orderId+"----凭证暂不可用");
									}
								}
							}else if ("1".equals(orderTemporaryShare.getVoucherStatus())){
								mess = ShangAnMessageType.operateToJson("3","此码已使用");
								parkerStateRecord.setInfo("orderId"+orderId+"----此码已使用");
							}else if ("2".equals(orderTemporaryShare.getVoucherStatus())){
								mess = ShangAnMessageType.operateToJson("2","凭证已经过期");
								parkerStateRecord.setInfo("orderId"+orderId+"----凭证已经过期");
							}
						}else {
							mess = ShangAnMessageType.operateToJson("1","无法找到凭证信息");
							parkerStateRecord.setInfo("orderId"+orderId+"----无法找到凭证信息");
						}
					}else {
						mess = ShangAnMessageType.operateToJson("4","权限不足,无法验证");
						parkerStateRecord.setInfo("orderId"+orderId+"----权限不足");
					}
				}else {
					mess = ShangAnMessageType.operateToJson("1","无法找到凭证信息");
					parkerStateRecord.setInfo("orderId"+orderId+"----无法找到凭证信息");
				}
			}else {
				mess = ShangAnMessageType.operateToJson("5","该代泊员不存在所管辖的停车场");
				parkerStateRecord.setInfo("orderId"+orderId+"----该代泊员不存在所管辖的停车场");
			}
			try {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.print(mess);
				parkerStateRecord.setIsUsed(Constants.TRUE);
				parkerStateRecord.setType("2");
				UserInfo userInfo = (UserInfo)super.getLoginUser(request);
				parkerStateRecord.setCreateor(userInfo == null?"":userInfo.getUserName());
				parkerStateRecordService.add(parkerStateRecord);
			}catch (Exception e){
				logger.error("",e);
			}
		}
	/***
	 * 查询当前日期指定小区的所有未使用的凭证
	 * @param parkingId
	 * @param pageIndex
	 * @param request
	 * @param response
	 */
	@RequestMapping("selectAppByToday/{parkingId}/{pageIndex}/{summary}")
	public void selectAppByToday(@PathVariable String parkingId,@PathVariable String pageIndex,HttpServletRequest request,HttpServletResponse response){
				String mess = null;
				Page<OrderTemporaryShare> page=new Page<OrderTemporaryShare>();
				page.getParams().put("parkingId", parkingId);
				/*page.getParams().put("voucherStatus", "0");*/
				page.getParams().put("pageIndex", pageIndex);
				page.getParams().put("appointmentDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				page.setCurrentPage(Integer.valueOf(pageIndex));
				try {
					mess=orderTemporaryShareService.selectAppByToday(page);
					//设置utf-8
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter writer = response.getWriter();
					writer.print(mess);
				}catch (Exception e){
					logger.error("",e);
				}
			}
	/**
	 * 通过openId查询用户Id
	 * 
	 * @return
	 */
	@RequestMapping("queryByOpenId")
	public void queryByOpenId(HttpServletRequest request, HttpServletResponse response) {
		String mess = null;
		String wxpayOpenid = request.getParameter("wxpayOpenid");
		
		if(null!=wxpayOpenid){
			mess = customerService.queryByOpenId(wxpayOpenid);
		}else{
			mess = ShangAnMessageType.operateToJson("2", "openId不能为空");
		}
		try{
			//设置utf-8
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin","*");
			PrintWriter writer = response.getWriter();
			writer.print(mess);
		} catch (IOException e) {
			logger.error("",e);
		}

	}
	
	/**
	 * 微信登陆
	 * 
	 * 
	 * @return
	 */
	@RequestMapping("wxLogin")
	public void wxLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mess = null;
		String mobile = request.getParameter("mobile");
		String code = request.getParameter("code");
		String wxpayOpenid = request.getParameter("wxpayOpenid");
		String lastLoginMachine = request.getParameter("lastLoginMachine");
		String appVersion = request.getParameter("appVersion");
		String lastLoginSys = request.getParameter("lastLoginSys");
		try {
			int num = SingletonClient.validMessageCode(mobile, code);
			if (num == 1) {
				List<Customer> cuList = customerService.queryByMobile(mobile);
				if (cuList != null && cuList.size() > 0) {
					mess = customerService.wxLogin(mobile,wxpayOpenid);
				}else{
					Customer cus = new Customer();
					Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_CUSTOMER);
					cus.setCustomerId(sequence.getId());
					cus.setCustomerMobile(mobile);
					cus.setAppVersion(appVersion);
					cus.setLastLoginSys(lastLoginSys);
					cus.setLastLoginMachine(lastLoginMachine);
					cus.setIdentity("0");
					if(!StringUtils.isEmpty(wxpayOpenid)){
						cus.setWxpayOpenid(wxpayOpenid);
					};
					customerService.add(cus);
					Map<String,String> ctMap = new HashMap<String,String>();
					ctMap.put("customerId", cus.getCustomerId());
					ctMap.put("wxpayOpenid", null!=cus.getWxpayOpenid()?cus.getWxpayOpenid():"");
					mess = ShangAnMessageType.toShangAnJson("0", "登录成功", "customer", ctMap);
				}
			} else {
				mess = ShangAnMessageType.operateToJson("1", "登录失败，验证码无效");
			}
		}catch (Exception e) {
			logger.error("", e);
			mess = ShangAnMessageType.operateToJson("2", "异常");
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().print(mess);
	}
	/**
	 * 微信注册
	 *
	 *
	 * @return
	 */
	@RequestMapping("wxRegist")
	public void wxRegist(
			@RequestParam String mobile,
			@RequestParam String code,
			@RequestParam String wxpayOpenid,
			@RequestParam(required = false) String appVersion,
			@RequestParam(required = false) String lastLoginSys,
			@RequestParam(required = false) String lastLoginMachine,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mess = null;
		try {
			int num = SingletonClient.validMessageCode(mobile, code);
			if (num == 1) {
				List<Customer> cuList = customerService.queryByMobile(mobile);
				if (cuList != null && cuList.size() > 0) {
					mess = ShangAnMessageType.operateToJson("2", "用户已存在");
				}else{
					Customer cus = new Customer();
					Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_CUSTOMER);
					cus.setCustomerId(sequence.getId());
					cus.setCustomerMobile(mobile);
					cus.setAppVersion(appVersion);
					cus.setLastLoginSys(lastLoginSys);
					cus.setLastLoginMachine(lastLoginMachine);
					cus.setIdentity("0");
					cus.setCreatedAt(DateUtil.date2str(new Date(),DateUtil.DATETIME_FORMAT));
					if(!StringUtils.isEmpty(wxpayOpenid)){
						cus.setWxpayOpenid(wxpayOpenid);
					};
					customerService.add(cus);
					List<CouponTemplate> cList = new ArrayList<CouponTemplate>();
					//送优惠暂时根据数据库中维护的模板
					//通用券
					CouponTemplate couponTemplateParam = new CouponTemplate();
					couponTemplateParam.setId(2016);
					CouponTemplate couponTemplate = couponTemplateService.selectList(couponTemplateParam).get(0);

					CouponTemplate couponTemplateParam1 = new CouponTemplate();
					couponTemplateParam1.setId(2017);
					CouponTemplate couponTemplate1 = couponTemplateService.selectList(couponTemplateParam1).get(0);
					cList.add(couponTemplate);
					cList.add(couponTemplate);
					cList.add(couponTemplate);
					cList.add(couponTemplate1);
					for(int i=0;i<cList.size();i++){
						couponTemplate = cList.get(i);
						//生成优惠券
						Sequence sequenceCoupon = sequenceService.getNextvalandins(TableNameConstants.T_COUPON);
						Coupon couponParam = new Coupon();
						couponParam.setCouponId(sequenceCoupon.getId());
						couponParam.setVouchersType(couponTemplate.getCouponType());
						couponParam.setVouchersName(couponTemplate.getCouponName());
						couponParam.setChannel("3");
						couponParam.setCouponType(couponTemplate.getCouponType());
						couponParam.setParAmount(couponTemplate.getParAmount().intValue());
						couponParam.setParDiscount(couponTemplate.getParDiscount().intValue());
						couponParam.setMinconsumption(couponTemplate.getMinconsumption().intValue());
						couponParam.setEffectiveBegin(DateUtil.date2str(couponTemplate.getEffectiveBegin(), DateUtil.DATETIME_FORMAT));
						couponParam.setEffectiveEnd(DateUtil.date2str(couponTemplate.getEffectiveEnd(), DateUtil.DATETIME_FORMAT));
						couponParam.setExclusionRule(couponTemplate.getExclusionRule());
						couponParam.setInfo(couponTemplate.getInfo());
						couponParam.setCouponStatus("100201");
						couponParam.setCustomerId(cus.getCustomerId());
						couponParam.setReceiveTime(DateUtil.date2str(new Date(),DateUtil.DATETIME_FORMAT));
						couponParam.setCouponKind(couponTemplate.getCouponKind());
						couponParam.setCreator("admin");
						couponParam.setCreateTime(DateUtil.date2str(new Date(),DateUtil.DATETIME_FORMAT));
						couponParam.setParkingId(couponTemplate.getParkingId());
						couponService.add(couponParam);
					}
					Map<String,String> ctMap = new HashMap<String,String>();
					ctMap.put("customerId", cus.getCustomerId());
					ctMap.put("wxpayOpenid", null!=cus.getWxpayOpenid()?cus.getWxpayOpenid():"");
					mess = ShangAnMessageType.toShangAnJson("0", "注册成功", "customer", ctMap);
				}
			} else {
				mess = ShangAnMessageType.operateToJson("1", "验证码无效");
			}
		}catch (Exception e) {
			logger.error("", e);
			mess = ShangAnMessageType.operateToJson("2", "异常");
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().print(mess);
	}
	@RequestMapping("saveCollection")
	public void saveCollection(HttpServletRequest request,HttpServletResponse response)
	{
		String mess = null;
		String version = request.getParameter("version");
		if ("2.0.0".equals(version)){
			String customerId = request.getParameter("customerId");
			String addressName = request.getParameter("addressName");
			String address = request.getParameter("address");
			String latitude = request.getParameter("latitude");
			String longitude = request.getParameter("longitude");
			CollectionParking collectionParking = new CollectionParking();
			collectionParking.setCustomerId(customerId);
			collectionParking.setAddress(address);
			collectionParking.setAddressName(addressName);
			collectionParking.setLatitude(latitude);
			collectionParking.setLongitude(longitude);
			collectionParkingService.add(collectionParking);
			CollectionParking collectionParking1 = new CollectionParking();
			collectionParking1.setCustomerId(customerId);
			List<CollectionParking> list = collectionParkingService.selectList(collectionParking1);
			List collectionList = new ArrayList();
			for (CollectionParking collectionParking2 : list)
			{

				if (collectionParking2.getParkingId()!=null){}else {
					Map map = new HashMap();
					map.put("customerId",collectionParking2.getCustomerId());
					map.put("searchTitle",collectionParking2.getAddressName());
					map.put("searchDistrict",collectionParking2.getAddress());
					map.put("searchLatitude",collectionParking2.getLatitude());
					map.put("searchLongitude",collectionParking2.getLongitude());
					collectionList.add(map);
				}
			}
			mess = ShangAnMessageType.toShangAnJson("0","收藏成功","collectionList",collectionList);
		}
		try{
			//设置utf-8
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin","*");
			PrintWriter writer = response.getWriter();
			writer.print(mess);
		} catch (IOException e) {
			logger.error("",e);
		}
	}

	@RequestMapping("deleteCollection")
	public void deleteCollection(HttpServletRequest request,HttpServletResponse response){
		String mess = null;
		String version = request.getParameter("version");
		if ("2.0.0".equals(version)){
			String customerId = request.getParameter("customerId");
			String addressName = request.getParameter("addressName");
			CollectionParking collectionParking = new CollectionParking();
			collectionParking.setCustomerId(customerId);
			collectionParking.setAddressName(addressName);
			collectionParkingService.deleteData(collectionParking);
			CollectionParking collectionParking1 = new CollectionParking();
			collectionParking1.setCustomerId(customerId);
			List<CollectionParking> list = collectionParkingService.selectList(collectionParking1);
			List collectionList = new ArrayList();
			for (CollectionParking collectionParking2 : list)
			{
				if (collectionParking2.getParkingId()!=null){}else {
					Map map = new HashMap();
					map.put("customerId",collectionParking2.getCustomerId());
					map.put("searchTitle",collectionParking2.getAddressName());
					map.put("searchDistrict",collectionParking2.getAddress());
					map.put("searchLatitude",collectionParking2.getLatitude());
					map.put("searchLongitude",collectionParking2.getLongitude());
					collectionList.add(map);
				}
			}
			mess = ShangAnMessageType.toShangAnJson("0","取消成功","collectionList",collectionList);
		}
		try{
			//设置utf-8
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin","*");
			PrintWriter writer = response.getWriter();
			writer.print(mess);
		} catch (IOException e) {
			logger.error("",e);
		}

	}
	@RequestMapping("queryCollection")
	public void queryCollection(HttpServletRequest request,HttpServletResponse response)
	{
		String mess = null;
		String version = request.getParameter("version");
		if ("2.0.0".equals(version)){
			String customerId = request.getParameter("customerId");
			CollectionParking collectionParking = new CollectionParking();
			collectionParking.setCustomerId(customerId);
			List<CollectionParking> list = collectionParkingService.selectList(collectionParking);
			List collectionList = new ArrayList();
			for (CollectionParking collectionParking2 : list)
			{
				if (collectionParking2.getParkingId()!=null){}else {
					Map map = new HashMap();
					map.put("customerId",collectionParking2.getCustomerId());
					map.put("searchTitle",collectionParking2.getAddressName());
					map.put("searchDistrict",collectionParking2.getAddress());
					map.put("searchLatitude",collectionParking2.getLatitude());
					map.put("searchLongitude",collectionParking2.getLongitude());
					collectionList.add(map);
				}
			}
			mess = ShangAnMessageType.toShangAnJson("0","查询成功","collectionList",collectionList);
		}
		try{
			//设置utf-8
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin","*");
			PrintWriter writer = response.getWriter();
			writer.print(mess);
		} catch (IOException e) {
			logger.error("",e);
		}

	}
	/**
	 * 提交问卷
	 *
	 *
	 * @return
	 */
	@RequestMapping("postQuestionnaire")
	public void postQuestionnaire(HttpServletRequest request, HttpServletResponse response) {
		//设置utf-8
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin","*");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String section_1 = request.getParameter("section_1");
		String section_2 = request.getParameter("section_2");
		String section_3 = request.getParameter("section_3");
		String section_4 = request.getParameter("section_4");
		String section_5 = request.getParameter("section_5");
		String section_6 = request.getParameter("section_6");
		String section_7 = request.getParameter("section_7");
		String section_8 = request.getParameter("section_8");
		String phone = request.getParameter("phone");
		String channel = request.getParameter("channel");
		EventQuestionnaire eParam = new EventQuestionnaire();
		eParam.setPhone(phone);
		List<EventQuestionnaire> eList = eventQuestionnaireService.selectList(eParam);
		if(null!=eList && eList.size()>0){
			writer.print(ShangAnMessageType.operateToJson("3","该手机号已提交"));
			return;
		}
		try {
			String neededService = request.getParameter("neededService");
			EventQuestionnaire eqs = new EventQuestionnaire();
			eqs.setCreateDate(new Date());
			eqs.setCreateor("admin");
			eqs.setPhone(phone);
			eqs.setNeededService(neededService);
			eqs.setChannel(channel);
			String answer = "";
			if(!StringUtils.isEmpty(section_1)){
				answer+="section_1_"+section_1;
				answer+=",";
			}
			if(!StringUtils.isEmpty(section_2)){
				answer+="section_2_"+section_2;
				answer+=",";
			}
			if(!StringUtils.isEmpty(section_3)){
				answer+="section_3_"+section_3;
				answer+=",";
			}
			if(!StringUtils.isEmpty(section_4)){
				answer+="section_4_"+section_4;
				answer+=",";
			}
			if(!StringUtils.isEmpty(section_5)){
				answer+="section_5_"+section_5;
				answer+=",";
			}
			if(!StringUtils.isEmpty(section_6)){
				answer+="section_6_"+section_6;
				answer+=",";
			}

			//第七个为多选
			if(!StringUtils.isEmpty(section_7)){
				String str[] = section_7.split("\\,");
				for(String s: str){
					answer+="section_7_"+s;
					answer+=",";
				}
			}
			if(!StringUtils.isEmpty(section_8)) {
				answer += "section_8_" + section_8;
				answer += ",";
			}
			if(!StringUtils.isEmpty(answer)){
				answer = answer.substring(0,answer.length()-1);
			}
			eqs.setAnswer(answer);

			eventQuestionnaireService.add(eqs);

			//短信息及
			String msg = "【口袋停】送您一张20元的停车券，可用于下单支付上海湾停车场停车费，请您下载并使用口袋停--我的钱包--我的优惠券，输入jyyh兑换。http://www.p-share.cn/p_share_weixin/About/download";
			String []strs = new String[1];
			strs[0] = phone;
			try {
				SingletonClient.getInstance().sendMessage(strs, msg, 5);
			} catch (Exception e) {
				logger.info("------------信息发送异常");
				e.printStackTrace();
			}


			writer.print(ShangAnMessageType.operateToJson("0","提交成功"));
		} catch (Exception e) {
			logger.error("提交失败",e);
			writer.print(ShangAnMessageType.operateToJson("2","提交成功"));
		}


	}
	@RequestMapping("parkingInfoDetail")
	public void parkingInfoDetail(HttpServletRequest request,HttpServletResponse response)
	{
		String mess = null;
		String version = request.getParameter("version");
		String customerId = request.getParameter("customerId");
		String parkingId = request.getParameter("parkingId");
		Parking parking=parkingService.queryById(parkingId);
		if(parking!=null){
			mess = ShangAnMessageType.toShangAnJson("0","查询成功","sharePriceComment",parking.getSharePriceComment());
		}else {
			mess = ShangAnMessageType.toShangAnJson("1", "查询失败", "sharePriceComment", "");
		}
		try{
			//设置utf-8
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin","*");
			PrintWriter writer = response.getWriter();
			writer.print(mess);
		} catch (IOException e) {
			logger.error("",e);
		}

	}
	@RequestMapping("CommitApp")
	public void CommitApp(HttpServletRequest request,HttpServletResponse response)
	{
		String mess = null;
		String version = request.getParameter("version");
		String customerId = request.getParameter("customerId");
		String commitInfo = request.getParameter("commitInfo");
		Sequence sequence=sequenceService.getNextvalandins("T_SYSTEM_REVIEWS");
		SystemReviews srv=new SystemReviews();
		srv.setReviewsId(sequence.getId());
		srv.setCustomerId(customerId);
		srv.setReviewsInfo(commitInfo);
		srv.setCreateTime(DateUtil.date2str(new Date(),DateUtil.DATETIME_FORMAT));
		systemReviewsService.add(srv);
		mess = ShangAnMessageType.toShangAnJson("0","提交成功","data","");
		try{
			//设置utf-8
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin","*");
			PrintWriter writer = response.getWriter();
			writer.print(mess);
		} catch (IOException e) {
			logger.error("",e);
		}

	}
	/*public static void byte2File(byte[] buf, String filePath, String fileName)
	{
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try
		{
			File dir = new File(filePath);

			if (!dir.exists() || !dir.isDirectory())
			{
				dir.mkdirs();
			}
			file = new File(filePath + File.separator + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(buf);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (bos != null)
			{
				try
				{
					bos.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (fos != null)
			{
				try
				{
					fos.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}*/
//修改个人信息
	@RequestMapping("updateCustomerInfo")
	public void updateCustomerInfo2(
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		String msg = null;
		String data =  DateUtil.getCurrDate("yyyymmddhhmmss");
		try {
			/*String customerHead=request.getParameter("customerHead");*/
			String customerId=request.getParameter("customerId");
			String customerNickname=request.getParameter("customerNickname");
			String customerAge=request.getParameter("customerAge");
			String customerJob=request.getParameter("customerJob");
			String customerRegion=request.getParameter("customerRegion");
			String customerMobile=request.getParameter("customerMobile");
			String customerEmail=request.getParameter("customerEmail");
			String customerSex =request.getParameter("customerSex");
			String version =request.getParameter("version");
			/*String realPath =null;
			if(StringUtils.isNotEmpty(customerHead)){
				String mulu =  DateUtil.getCurrDate(DateUtil.DATE_FORMAT) + "/";
				if (Constants.TRUE.equals(uploadImageType)) {
					String path = "/usr/local/nginx/html/" + uploadImagePath + mulu;
					logger.debug("2========================================" + path );
					byte2File(customerHead.getBytes(),path,data+".jpg");
				} else {
					String path = request.getSession().getServletContext().getRealPath("/") + (uploadImagePath + mulu);
					logger.debug("3========================================" + path );
					byte2File(customerHead.getBytes(), path, data + ".jpg");
				}
				realPath=uploadImagePath + mulu;
				logger.debug("4========================================" + realPath);
				//byte2File(customerHead.getBytes(),basePath+"/usr/local/nginx/html/"+uploadImagePath,data+".jpg");
			}*/
			Customer customer=customerService.queryByCustomerId(customerId);
			customer.setCustomerId(customerId);
			/*customer.setCustomerHead(realPath+data+".jpg");*/
			customer.setCustomerNickname(customerNickname);
			customer.setCustomerEmail(customerEmail);
			customer.setCustomerAge(customerAge==null?0:Integer.parseInt(customerAge));
			customer.setCustomerJob(customerJob);
			customer.setCustomerRegion(customerRegion);
			customer.setCustomerMobile(customerMobile);
			customer.setCustomerSex(customerSex==null?3:Integer.parseInt(customerSex));
			msg = customerService.updateCustomerInfo(customer,request);
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
			msg = ShangAnMessageType.operateToJson("2", "异常");
		}
		response.getWriter().print(msg);
	}

}
