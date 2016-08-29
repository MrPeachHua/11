package com.boxiang.share.other;

import java.awt.image.BufferedImage;
import java.io.*;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.share.app.parker.controller.ImageController;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.product.activity.po.Activity;
import com.boxiang.share.product.activity.po.ActivityQrCode;
import com.boxiang.share.product.activity.service.ActivityQrCodeService;
import com.boxiang.share.product.activity.service.ActivityService;
import com.boxiang.share.product.ccic.bo.CarMessageSync;
import com.boxiang.share.product.ccic.bo.CarResponse;
import com.boxiang.share.product.ccic.service.CcicCustomerService;
import com.boxiang.share.product.ccic.service.CcicOrderInfoService;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.parking.service.CarInOutRecordV2Service;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.MD5Util;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.XMLUtil;
import com.boxiang.share.utils.json.JacksonUtil;
import com.boxiang.share.utils.weixinUtil.WeixinUtil;
import com.boxiang.share.utils.xml.XmlStreamFactory;

import cn.b2m.eucp.client.Client;
import cn.b2m.eucp.client.SingletonClient;

@Controller
@RequestMapping("other")
public class OtherController extends BaseController {
	private static final Logger logger = Logger.getLogger(OtherController.class);
	 
	@Resource 
	private CustomerService customerService;

    @Resource
    private ActivityQrCodeService activityQrCodeService;

	@Resource
	private CarInOutRecordV2Service carInOutRecordV2Service;

	@Resource
	private CcicCustomerService ccicCustomerService;

	@Resource
	private CcicOrderInfoService ccicOrderInfoService;

	@Resource
	private ActivityService activityService;
	@Resource
	private ImageController imageController;
	@Resource
	private String uploadImageType;

	@Resource
	private String uploadImagePath;
	/**
	 * @author junior
	 * @param xml
	 * @param sellerId
	 * @param utmsn
	 * @param sign
	 * @param encoding
	 * @return  大地保险签名验证
	 */
	private boolean validMd5Str(String xml,String sellerId,String utmsn,String sign,String encoding){
		final String VALIDATECODE = "dadi20130128";
		String md5 = VALIDATECODE + xml + "sellerId=" + sellerId +  "&tbsn=" + utmsn;
		//sign = Md5Encrypter.md5(md5);
		return MD5Util.sign(md5, "", encoding).equalsIgnoreCase(sign);
	}

	/**
	 * 大地保险
	 * @author junior
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("ccic/carsync")
	public void ccicCarSync(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		String encoding = "GBK";
		request.setCharacterEncoding(encoding);
		response.setContentType("application/xml;charset=" + encoding);
		CarResponse carResponse = new CarResponse();
		String resXml = "";
		try {
			String sellerId = request.getParameter("sellerId");
			String tbsn = request.getParameter("tbsn");
			String sign = request.getParameter("sign");
			logger.info("大地保险：sellerId="+sellerId+";tbsn="+tbsn+";sign="+sign);
			String data = request.getParameter("data");
			logger.info("大地保险XML数据：data="+data);
			if(validMd5Str(data,sellerId,tbsn,sign,encoding)){
				carResponse.setFinishTime(new Date());
				carResponse.setIsSuccess("F");
				carResponse.setInsuranceApplicantNo("");
				carResponse.setErrorCode("1000");
				carResponse.setErrorReason("签名错误");
				resXml = XmlStreamFactory.getInstance().toXml(carResponse, encoding);
			} else {
				CarMessageSync msg = new CarMessageSync();
				msg = XmlStreamFactory.getInstance().fromXml(CarMessageSync.class, data,encoding);
				
				
				switch (msg.getRequest().getFunc()) {
				case "car_underwrite_sync": // 核保
					carResponse = ccicOrderInfoService.carUnderwriteSync(msg);
					break;
				case "car_pay_sync": // 支付
					carResponse = ccicOrderInfoService.carPaySync(msg);
					break;
				case "car_policy_sync": // 出单
					carResponse = ccicOrderInfoService.carPolicySync(msg);
					break;
				case "car_info_sync": // 信息同步
					if(sellerId==null || !sellerId.equalsIgnoreCase(msg.getRequest().getFrom())){
			        	logger.warn(msg.getRequest().getFrom()+"**********************from and sellerId not match**********************"+sellerId);
						carResponse.setFinishTime(new Date());
						carResponse.setIsSuccess("F");
						carResponse.setErrorCode("2000");
						carResponse.setErrorReason("from and sellerId not match");						
					}else {
						carResponse = ccicCustomerService.carInfoSync(msg);
					}
					break;
				default:
					carResponse.setFinishTime(new Date());
					carResponse.setIsSuccess("F");
					carResponse.setInsuranceApplicantNo("");
					carResponse.setErrorCode("1010");
					carResponse.setErrorReason("type error");
					break;
				}
				resXml = XmlStreamFactory.getInstance().toXml(carResponse, encoding);
			}
		} catch (Exception e) {
			logger.error("**********************", e);
			carResponse.setFinishTime(new Date());
			carResponse.setIsSuccess("F");
			carResponse.setInsuranceApplicantNo("");
			carResponse.setErrorCode("1010");
			carResponse.setErrorReason("出现异常："+e.getMessage());
			resXml = XmlStreamFactory.getInstance().toXml(carResponse, encoding);
		}
		try {
			PrintWriter out = response.getWriter();
			out.print(resXml);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 大地保险
	 */
	@RequestMapping("ccic/sync")
	public void ccicSync(String tbsn,
						 String sellerId,
						 String sign,
						 String signType,
						 String type,
						 HttpServletRequest request,
						 HttpServletResponse response) throws IOException {
		String msg = "";
		if (sellerId == null || !sellerId.equals("123")) {
			msg = "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
					"<response finishTime=\"" + DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) + "\">" +
					"<isSuccess>F</isSuccess>" +
					"<insuranceApplicantNo></insuranceApplicantNo>" +
					"<errorCode>1000</errorCode>" +
					"<errorReason>sellerId error;目前默认写死为123</errorReason>" +
					"</response>";
		} else {
			try {
				String data = request.getParameter("data");
				logger.info(data);
				Map map = XMLUtil.xmlToMap(data);
				String func = ((Map) map.get("request")).get("@func").toString();
				switch (func) {
					case "car_info_sync": // 信息同步
						msg = ccicCustomerService.saveInfo(map);
						break;
					case "car_underwrite_sync": // 核保
						msg = ccicOrderInfoService.saveCheck(map);
						break;
					case "car_pay_sync": // 支付
						msg = ccicOrderInfoService.savePay(map);
						break;
					case "car_policy_sync": // 出单
						msg = ccicOrderInfoService.savePolicy(map);
						break;
					default:
						msg = "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
								"<response finishTime=\"" + DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) + "\">" +
								"<isSuccess>F</isSuccess>" +
								"<insuranceApplicantNo></insuranceApplicantNo>" +
								"<errorCode>1010</errorCode>" +
								"<errorReason>type error</errorReason>" +
								"</response>";
						break;
				}
			} catch (Exception e) {
				logger.error("", e);
				msg = "<?xml version=\"1.0\" encoding=\"GBK\"?>" +
						"<response finishTime=\"" + DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) + "\">" +
						"<isSuccess>F</isSuccess>" +
						"<insuranceApplicantNo></insuranceApplicantNo>" +
						"<errorCode>1010</errorCode>" +
						"<errorReason>xml parse exception</errorReason>" +
						"</response>";
			}
		}
		response.getWriter().print(msg);
	}

	/**
	 * 手动同步数据
	 */
	@RequestMapping("syncCarRecord")
	public synchronized void syncCarRecord(@RequestParam String beginDate,
							  @RequestParam String endDate,
							  HttpServletRequest request,
							  HttpServletResponse response) throws IOException {
		try {
			logger.info("同步出入场记录--开始");
			Date begin = DateUtil.str2date(beginDate, DateUtil.DATE_FORMAT);
			Date end = DateUtil.str2date(endDate, DateUtil.DATE_FORMAT);
			while (begin.compareTo(end) < 0) {
				try {
					String b = DateUtil.date2str(begin, DateUtil.DATE_FORMAT) + " 00:00:00";
					String e = DateUtil.date2str(begin, DateUtil.DATE_FORMAT) + " 23:59:59";
					logger.info("同步出入场记录--同步中:" + b + "\t" + e);
					carInOutRecordV2Service.manualSyncData(b, e);
					logger.info("同步出入场记录--同步成功:" + b + "\t" + e);
					begin = DateUtil.getPreOrNextDate(begin, 1);
				} catch (Exception e1) {
					logger.error("同步出入场记录--异常:" + DateUtil.date2str(begin, DateUtil.DATETIME_FORMAT), e1);
				}
			}
			logger.info("同步出入场记录--完成");
		} catch (Exception e) {
			logger.error("同步出入场记录--手动同步车辆数据异常", e);
		}
	}

	/**
	 * 查短信余额
	 */
	@RequestMapping("getBalance")
	public void getBalance(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Double balance = null;
		try {
			Client client = SingletonClient.getClient(Constants.SMS_SERIAL_NO, Constants.SMS_KEY);
			balance = client.getBalance();
		} catch (RemoteException e) {
			logger.error("", e);
		}
		response.getWriter().print(balance);
	}

	/**
	 * 改送短信验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping("sendSmsCode")
	public void sendSmsCode(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin","*");
		try {
			out = response.getWriter();
			String mobile=request.getParameter("mobile");
			logger.info((StringUtils.isEmpty(mobile) || "请输入手机号".equals(mobile))+"~~~~~~~~~~");
			if(StringUtils.isEmpty(mobile) || "请输入手机号".equals(mobile)){
				out.print(JacksonUtil.toJson("请输入手机号"));
			}else {
				// 推送验证码信息
				String message = SingletonClient.getInstance().sendMessageCode(mobile);
				out.print(JacksonUtil.toJson(message));
			}
		} catch (IOException e) {
			logger.error("",e);
		}
	}
	
	/**
	 * 注册提交
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("registerbyqrcode")
	public ModelAndView registerbyqrcode(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		String channel = request.getParameter("channel");//获取渠道
		String code = request.getParameter("smsCode");//验证码
		String mobile = request.getParameter("mobile");//手机号 
		String password = request.getParameter("password");//密码
		String reg_phone = request.getParameter("reg_phone");//注册机型
		if(!StringUtils.isEmpty(code) && !StringUtils.isEmpty(mobile) && !StringUtils.isEmpty(password)){
			//验证注册短信验证码
			int flag = SingletonClient.validMessageCode(mobile, code);
			if(flag==1){
				Customer paramCus = new Customer();
				paramCus.setCustomerMobile(mobile);
				List<Customer> list = customerService.selectList(paramCus);
				if(list!=null&&list.size()>0){
					map.put("msg", "用户已存在！");
				}else{
				    Customer cus = new Customer();
					String s=UUID.randomUUID().toString();
					cus.setCustomerId(s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24));
					cus.setCustomerMobile(mobile);
					cus.setChannel(channel);
					cus.setCustomerPassword(MD5Util.md5(password));
					cus.setIdentity(Constants.FALSE);
					cus.setReg_phone(reg_phone);
					try {
					 	customerService.insertCustomerByMobleAndPwd(cus);
					} catch (Exception e) {
						logger.error("",e);
						map.put("msg", e.getMessage());
					}
				 	/*map.put("msg", "注册成功！");
				 	if("ios".equalsIgnoreCase(platform)){
				 		return new ModelAndView("redirect:http://beta.qq.com/m/v1xw");
				 	}else {
				 		return new ModelAndView("redirect:https://www.pgyer.com/bkP9");
				 	}*/
				}
			}else {
				map.put("msg", flag==2?"验证码失效":(flag==3?"没有该手机的验证码":"未知错误"));
			}
		}else {
			map.put("msg", "请检查手机号、密码、验证码是否为空！");
		}
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin","*");
		try {
			out = response.getWriter();
			out.print(JacksonUtil.toJson(map));
		} catch (IOException e) {
			logger.error("",e);
		}
		return null;
	}
	
	/**
	 * 改送短信验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping("testXhr")
	public void testXhr(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		response.setHeader("Access-Control-Allow-Origin","*"); 
		
			try {
				out = response.getWriter();
				out.print("{\"a\":\"678\"}");	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
	}
		
	/**
	 * 获取加密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("getSummary")
	public void getSummary(HttpServletRequest request, HttpServletResponse response) {
		String summary = null;
		//如果get 用以下方法
		if(Constants.REQUEST_METHOD_GET.equalsIgnoreCase(request.getMethod())){
			String getUrl = request.getParameter("getUrl");
			@SuppressWarnings("unchecked")
			Map<String,String[]> pramMap = request.getParameterMap();
			summary = MD5Util.getSummary(pramMap,getUrl);
		}else{
			@SuppressWarnings("unchecked")
			Map<String,String[]> pramMap = request.getParameterMap();
			summary = MD5Util.getSummary(pramMap,null);
		}
		
		
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		response.setHeader("Access-Control-Allow-Origin","*"); 
		
			try {
				out = response.getWriter();
				out.print("{\"summary\":\""+summary+"\"}");	
				//out.print("23456784580789dfghjrtyuiortyuioprtyuiop[ertyuiop[rtyuio");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
	}	
	

	@RequestMapping("testAAA")
	public ModelAndView testAAA(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("other/html5/RedInvitation.html");
	}

	/**
	 * 获取微信分享授权
	 * @param request
	 * @param response
	 */
	@RequestMapping("weixinUtil")
	public void weixinUtil(@RequestParam String url, HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> mp = WeixinUtil.getWxConfig(url);
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		response.setHeader("Access-Control-Allow-Origin","*"); 
		try {
			out = response.getWriter();
			out.print(ShangAnMessageType.toShangAnJson("0", "su", "mp", mp));	
			//out.print("23456784580789dfghjrtyuiortyuioprtyuiop[ertyuiop[rtyuio");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String summary = null;
	}

    @RequestMapping("scanQrCode")
    public String scanQrCode(@RequestParam int id,
                             @RequestParam String jumpUrl,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        try {
			ActivityQrCode activityQrCode = activityQrCodeService.queryById(id);
			if (activityQrCode == null || activityQrCode.getIsUsed().equals(Constants.FALSE)) {
				return "redirect:http://p-share.cn/wx_share/html5/views/index.html#/home/actOver";
			}
			if (activityQrCode.getType().equals("2")) { // 活动二维码
				Map<String, Object> params = new HashMap<>(1);
				params.put("id", activityQrCode.getActivityId());
				params.put("state", "1"); // 0:已过期 1:未过期 2:未开始
				Map<String, Object> map = new HashMap<>(1);
				map.put("params", params);
				List<Activity> activityList = activityService.queryListPageV2(map);
				if (activityList == null || activityList.size() == 0) {
					return "redirect:http://p-share.cn/wx_share/html5/views/index.html#/home/actOver";
				}
			}
			activityQrCodeService.updateScanCount(id, 1);
        } catch (Exception e) {
            logger.error("", e);
        }
        return "redirect:" + jumpUrl;
    }
	//修改个人信息
	@RequestMapping("customer/updateImage")
	public void updateCustomerInfo( @RequestParam(required = false) MultipartFile[] myFiles,
									HttpServletRequest request,
									HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		String msg = null;
		try {
			//String customerHead=request.getParameter("customerHead");
			String customerId=request.getParameter("customerId");
			/*String customerNickname=request.getParameter("customerNickname");
			String customerAge=request.getParameter("customerAge");
			String customerJob=request.getParameter("customerJob");
			String customerRegion=request.getParameter("customerRegion");
			String customerMobile=request.getParameter("customerMobile");
			String customerEmail=request.getParameter("customerEmail");
			String customerSex =request.getParameter("customerSex");*/
			String version =request.getParameter("version");
			List<String>  customerHead=	imageController.fileUpload2(myFiles, request, response);
			Customer customer=customerService.queryByCustomerId(customerId);
			customer.setCustomerId(customerId);
			if(customerHead!=null&&customerHead.size()>0){
				customer.setCustomerHead(customerHead.get(0));
			}
		/*	logger.info(customer.getCustomerHead());
			customer.setCustomerNickname(customerNickname);
			customer.setCustomerEmail(customerEmail);
			customer.setCustomerAge(customerAge==null?0:Integer.parseInt(customerAge));
			customer.setCustomerJob(customerJob);
			customer.setCustomerRegion(customerRegion);
			customer.setCustomerMobile(customerMobile);
			customer.setCustomerSex(customerSex==null?3:Integer.parseInt(customerSex));*/
			msg = customerService.updateCustomerInfo(customer,request);
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
			msg = ShangAnMessageType.operateToJson("2", "异常");
		}
		response.getWriter().print(msg);
	}
	//修改个人信息
	@RequestMapping("customer/updateCustomerInfo")
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

	public static void byte2File(byte[] buf, String filePath, String fileName)
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
	}
}
