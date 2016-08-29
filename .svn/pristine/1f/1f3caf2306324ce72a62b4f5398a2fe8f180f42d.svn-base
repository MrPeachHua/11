package com.boxiang.share.product.customer.service.imp;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import cn.b2m.eucp.client.SingletonClient;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.bluecard.po.PayStatusSync;
import com.boxiang.share.customer.po.CustomerInfo;
import com.boxiang.share.payment.dao.PaymentInfoDao;
import com.boxiang.share.payment.po.PaymentInfo;
import com.boxiang.share.payment.service.PaymentInfoService;
import com.boxiang.share.product.coupon.dao.CouponDao;
import com.boxiang.share.product.coupon.po.Coupon;
import com.boxiang.share.product.customer.dao.CustomerDao;
import com.boxiang.share.product.customer.dao.OrderPayRecordDao;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.customer.service.RedDotService;
import com.boxiang.share.product.order.dao.OrderMainDao;
import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.product.order.po.OrderRecharge;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.product.order.service.OrderRechargeService;
import com.boxiang.share.system.controller.DictionaryController;
import com.boxiang.share.system.po.Sequence;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.MD5Util;
import com.boxiang.share.utils.OrderConstants;
import com.boxiang.share.utils.RandomUtil;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.TableNameConstants;


@DataSource(DataSourceEnum.MASTER)
@Service("customer")
public class CustomerServiceImpl implements CustomerService {
	private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class);
	@Resource(name="customerDao")
	private CustomerDao customerDao;
	//优惠券dao
	@Resource(name="couponDao")
	private CouponDao couponDao;
	@Resource(name="orderMainDao")
	private OrderMainDao orderMainDao;
	@Resource(name="orderPayRecordDao")
	private OrderPayRecordDao orderPayRecordDao;
	@Resource
	private PaymentInfoDao paymentInfoDao;
	@Autowired
	private RestTemplate restTemplate;
	@Resource
	private String APIKey;
	@Resource
	private String payStatus;

	@Resource
	private OrderRechargeService orderRechargeService;

	@Resource
	private RedDotService redDotService;
	
	@Resource
	private SequenceService sequenceService;
	
	@Resource
	private OrderMainService orderMainService;
	
	@Resource PaymentInfoService paymentInfoService;
	@Override
	public List<Customer> selectList(Customer Customer) {
		return customerDao.selectList(Customer);
	}
	@Override
	public Customer queryByWxId(Customer customer) {
		return customerDao.queryByWxId(customer);
	}
	@Override
	public Page<Customer> queryListPage(Page<Customer> page) {
	    page.setResultList(customerDao.queryListPage(page));
		return page;
	}
	@Override
	public List<Customer> queryByMobile(String customerMobile) {
		return customerDao.queryByMobile(customerMobile);
	}
	@Override
	public Customer queryByMobileV2(String customerMobile) {
		List<Customer> list = customerDao.queryByMobile(customerMobile);
		return (list != null && list.size() > 0) ? list.get(0) : null;
	}
	@Override
	public List<CustomerInfo> selectList(CustomerInfo customer) {
		return customerDao.exportCustomerInfo(customer);
	}
	@Override
	public Customer queryById(java.lang.String id) {
		return customerDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(Customer Customer) {
		customerDao.insert(Customer);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.String id) {
		customerDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(Customer Customer) {
		customerDao.update(Customer);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Customer> CustomerList) {
		customerDao.batchUpdate(CustomerList);
	}
  /**
   * 
   * 
   */
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
  public String insertCustomerByMobleAndPwd(Customer customer) throws Exception {
		int i;
		String message = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		customer.setCreatedAt(sdf.format(new Date()));
		List<Customer> cu = null;
		Customer paramCus = new Customer();
		paramCus.setCustomerMobile(customer.getCustomerMobile());
			cu = customerDao.selectList(paramCus);
		if (cu != null&&cu.size()>0) {
			message = ShangAnMessageType.toShangAnJson("2", "用户已存在", "customerId", "");
		} else {
				i = customerDao.insert(customer);
				if (i == 1) {
					message = ShangAnMessageType.toShangAnJson("0", "注册成功", "customerId", customer.getCustomerId());
					logger.info("用户:" + customer.getCustomerId() + "注册成功！");
//					//查询注册可用优惠券优惠券
//					Map<String,String> param = new HashMap<String,String>();
//					List<Coupon> list = null;
//					list = couponDao.selectRegCoupons(param);
//					if(list!=null && list.size()>0){
//						for(Coupon cup : list){
//							if(!StringUtils.isEmpty(cup.getEffectivetime())){
//								Date da = new Date();
//								Date begin_time = DateUtil.str2date(cup.getReceiveBegin(), DateUtil.DATETIME_FORMAT);
//								Date end_time = DateUtil.str2date(cup.getReceiveEnd(), DateUtil.DATETIME_FORMAT);
//								if(da.getTime()<begin_time.getTime() || da.getTime()>end_time.getTime()){
//									list.remove(cup);
//									if(list.size()==0){
//										break;
//									}
//								}
//							}else{
//								Date da = new Date();
//								Date begin_time = DateUtil.str2date(cup.getEffectiveEnd(), DateUtil.DATETIME_FORMAT);
//								Date end_time = DateUtil.str2date(cup.getEffectiveEnd(), DateUtil.DATETIME_FORMAT);
//								if(da.getTime()>end_time.getTime() || da.getTime()<begin_time.getTime()){
//									list.remove(cup);
//									if(list.size()==0){
//										break;
//									}
//								}
//							}
//						}
//					}
//					if(null!=list&&list.size()>0){
//						Coupon cou = list.get(0);
//						//并且为该用户绑定优惠券
//						cou.setCustomerId(customer.getCustomerId());
//						cou.setCouponStatus("100201");//未过期已领取未使用
//						if(!StringUtils.isEmpty(cou.getEffectivetime())){
//							Date begin_da = new Date();
//							Date end_da = DateUtil.getPreOrNextDate(begin_da, Integer.parseInt(cou.getEffectivetime()));
//							cou.setEffectiveBegin(DateUtil.date2str(begin_da, DateUtil.DATETIME_FORMAT));
//							cou.setEffectiveEnd(DateUtil.date2str(end_da, DateUtil.DATETIME_FORMAT));
//						}
//						couponDao.update(cou);
//						String msg = "【口袋停】恭喜你注册成功，并获得新人礼，我们已经将"+cou.getParAmount()+"元停车代金券放入您的账户，赶紧打开口袋停使用吧。400-006-2637";
//						String []strs = new String[1];
//						strs[0] = customer.getCustomerMobile();
//						try {
//							SingletonClient.getInstance().sendMessage(strs, msg, 5);
//						} catch (Exception e) {
//							logger.info("------------信息发送异常");
//							e.printStackTrace();
//						}
//						// 添加小红点提醒
//						redDotService.openRedDot(customer.getCustomerId(), 1, 1);
//					}else{
//						String msg = "【口袋停】终于等到你，恭喜注册成功。从此找车位，不烦恼，还能包养车管家为您效劳，快去试试吧。400-006-2637";
//						String []strs = new String[1];
//						strs[0] = customer.getCustomerMobile();
//						try {
//							SingletonClient.getInstance().sendMessage(strs, msg, 5);
//						} catch (Exception e) {
//							logger.info("------------信息发送异常");
//							e.printStackTrace();
//						}
//					}
				} else {
					message = ShangAnMessageType.toShangAnJson("2", "数据异常", "customerId", "");
					logger.error("用户：" + customer.getCustomerId() + "register fail sql！");
				}
		}
		return message;
	}

	@Override
	public Customer queryByCustomerId(String id) {
		 
		return customerDao.queryByCustomerId(id);
	}
	/**
	 * 产权
	 * 
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String postMonthlyAfterPay(String order_id, String trade_no,
			String trade_status, String buyer_email, String pay_Type,
			String total_Fee,String user_info) {
		String message = "not finish";
		String error="";
		logger.info(order_id+" " + trade_no+" "+trade_status+"liuliuliu");
		OrderMain o = orderMainDao.queryById(order_id);
		if (o != null) {
		if(OrderConstants.ORDER_STATUS_PAID.equals(o.getOrderStatus())&&null!=o.getPayTime()&&!StringUtils.isEmpty(o.getTradeNo())){
			message="success";
		}else {
			try {
					//月租产权同步时间发送短信
					orderMainService.monAndProPaid(o);
					//成功调用返回第三方成功
					message = "success";
				o.setPayType(pay_Type);
				o.setTradeNo(trade_no);
				if (o.getPayTime() == null) {
					o.setPayTime(new Date());
				}
				o.setOrderStatus(OrderConstants.ORDER_STATUS_PAID);
				orderMainDao.update(o);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("servise bug");
				message = "fail";
				error="异常"+e.getMessage();
			}
				PaymentInfo paymentInfo = new PaymentInfo();
				paymentInfo.setCreateDate(new Date());
				paymentInfo.setCreateor("admin");
				paymentInfo.setOrderId(order_id);
				paymentInfo.setPayType(pay_Type);
				paymentInfo.setUseInfo(error+user_info);
				paymentInfo.setUseType("2");
				paymentInfoDao.insert(paymentInfo);
		}
		} else {
			message = "fail";
			logger.info("can not find the order which order_id= " + order_id);
		}
		return message;
	}
	/**
	 * 产权
	 * 
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String postCardAfterPay(String order_id, String trade_no,
			String trade_status, String buyer_email, String pay_Type,
			String total_Fee,String user_info) {
		String message = "not finish";
		String error="";
		logger.info(order_id+" " + trade_no+" "+trade_status+"liuliuliu");
		OrderMain o = orderMainDao.queryById(order_id);
		if (o != null) {
			if(OrderConstants.ORDER_STATUS_PAID.equals(o.getOrderStatus())&&null!=o.getPayTime()&&!StringUtils.isEmpty(o.getTradeNo())){
				message="success";
			}else {
			try {
				orderMainDao.finishPay(order_id);
				//成功调用返回第三方成功
				message = "success";
				o.setPayType(pay_Type);
				o.setTradeNo(trade_no);
				if (o.getPayTime() == null) {
					o.setPayTime(new Date());
				}
				logger.info("1111111111111111111111111111111====" + new BigDecimal(total_Fee).compareTo(new BigDecimal(o.getAmountPaid())));
				if (new BigDecimal(total_Fee).compareTo(new BigDecimal(o.getAmountPaid())) != 0) {
					// 如果金额不一致，但已经给钱包加过钱，则扣除
					if (o.getOrderType().equals("16") && o.getOrderStatus().equals(OrderConstants.ORDER_STATUS_PAID)) {
						this.subtractMoney(o.getCustomerId(), o.getAmountPaid());
					}
					o.setOrderStatus(OrderConstants.ORDER_STATUS_EXCEPTION);//金额验证不通过，修改状态为异常
				} else {
					if (o.getOrderType().equals("16") && o.getOrderStatus().equals(OrderConstants.ORDER_STATUS_UNPAY)) { // 钱包充值
						// 修改订单状态
						o.setOrderStatus(OrderConstants.ORDER_STATUS_PAID);
						orderMainDao.updateUnpayToPaid(o.getOrderId());
					/*if (orderMainDao.updateUnpayToPaid(o.getOrderId()) != 1) {
						throw new Exception("订单状态修改异常11111111");
					}*/
						OrderRecharge orderRecharge = new OrderRecharge();
						orderRecharge.setOrderId(o.getOrderId());
						orderRecharge.setIsUsed(Constants.TRUE);
						orderRecharge = orderRechargeService.selectList(orderRecharge).get(0);
						// 给用户钱包加钱
						logger.info("22222222222222222====");
						this.addMoney(o.getCustomerId(), o.getAmountPayable() + orderRecharge.getGiftAmount());
					}
					o.setOrderStatus(OrderConstants.ORDER_STATUS_PAID);
				}
				orderMainDao.update(o);
				if (o.getOrderType().equals("16") && o.getOrderStatus().equals(OrderConstants.ORDER_STATUS_EXCEPTION)) {
					// 发送短信
					String[] mobiles = {"13917045017", "13482301239"};
					String content = "【口袋停】订单号：" + order_id + "异常，实际支付金额和订单金额不一致";
					SingletonClient.getInstance().sendMessage(mobiles, content, 5);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("servise bug");
				message = "fail";
				error="异常"+e.getMessage();
			}
			PaymentInfo paymentInfo = new PaymentInfo();
			paymentInfo.setCreateDate(new Date());
			paymentInfo.setCreateor("admin");
			paymentInfo.setOrderId(o.getOrderId());
			paymentInfo.setPayType(pay_Type);
			paymentInfo.setUseInfo(error+user_info);
			paymentInfo.setUseType("2");
			paymentInfoDao.insert(paymentInfo);
		}
		}else{
			message="fail";
			logger.info("can not find the order which order_id= "+order_id);
			return message;
		}
		return message;
	}

	/**
	 * 代泊
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String postParkAfterPay(String order_id, String trade_no, String trade_status, String buyer_email, String pay_Type, String total_Fee, String user_info) {
		String message = "not finish";
		String error="";
		logger.info(order_id+" " + trade_no+" "+trade_status+"......park.......");
		logger.info("--------------------代泊支付回调");
		OrderMain o=  orderMainDao.queryById(order_id);
		logger.info("代泊订单-------------------"+o);
		if(o!=null) {
			if (o.getPayTime()!=null&&!StringUtils.isEmpty(o.getTradeNo())) {
				message = "success";
			}else {
			try {
				logger.info("代泊短信-----------------" + o.getOrderType() + "----------------" + o.getOrderStatus());
				if (OrderConstants.ORDER_TYPE_PARK.equals(o.getOrderType())) {
					orderMainService.parkPayWithGettingCar(o);
				}
				o.setPayType(pay_Type);
				o.setTradeNo(trade_no);
				if (o.getPayTime() == null) {
					o.setPayTime(new Date());
				}
				orderMainDao.update(o);
				message = "success";
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("servise bug");
				message = "fail";
			}
			PaymentInfo paymentInfo = new PaymentInfo();
			paymentInfo.setCreateDate(new Date());
			paymentInfo.setCreateor("admin");
			paymentInfo.setOrderId(order_id);
			paymentInfo.setPayType(pay_Type);
			paymentInfo.setUseInfo(user_info);
			paymentInfo.setUseType("2");
			paymentInfoDao.insert(paymentInfo);
		}
		}else{
			message="fail";
			logger.info("can not find the order which order_id= "+order_id);
			return message;
		}
		return message;
	}

	/**
	 * 洗车
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String postCarwsahAfterPay(String order_id, String trade_no, String trade_status, String buyer_email, String pay_Type, String total_Fee, String user_info) {
		String message = "not finish";
		String error="";
		logger.info(order_id+" " + trade_no+" "+trade_status+"liuliuliu");
		OrderMain o = orderMainDao.queryById(order_id);
		if (o != null) {
			if(OrderConstants.ORDER_STATUS_PAID.equals(o.getOrderStatus())&&null!=o.getPayTime()&&!StringUtils.isEmpty(o.getTradeNo())){
				message="success";
			}else {
		try {
			logger.info("--------------------洗车微信支付回调");
				logger.info("洗车短信-----------------"+o.getOrderType()+"----------------"+o.getOrderStatus());
				if(("17".equals(o.getOrderType()))&&("10".equals(o.getOrderStatus()))){
					paymentInfoService.sendToCarwashManager(o);
				}
				message="success";
			o.setPayType(pay_Type);
			o.setTradeNo(trade_no);
			if (o.getPayTime() == null) {
				o.setPayTime(new Date());
			}
			o.setOrderStatus(OrderConstants.ORDER_STATUS_PAID);
			orderMainDao.update(o);
		} catch (Exception e) {
			e.printStackTrace();
			error="异常"+e.getMessage();
			logger.info("servise bug");
			message="fail";
		}
				PaymentInfo paymentInfo = new PaymentInfo();
				paymentInfo.setCreateDate(new Date());
				paymentInfo.setCreateor("admin");
				paymentInfo.setOrderId(order_id);
				paymentInfo.setPayType(pay_Type);
				paymentInfo.setUseInfo(error+user_info);
				paymentInfo.setUseType("2");
				paymentInfoDao.insert(paymentInfo);
			}
			}else{
				message="fail";
				logger.info("can not find the order which order_id= "+order_id);
				return message;
			}
		return message;
	}

	/**
	* 产权
	* 
	*/
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String postEquityAfterPay(String order_id, String trade_no,
			String trade_status, String buyer_email, String pay_Type,
			String total_Fee,String user_info) {
		String message = "not finish";
		String error="";
		logger.info(order_id+" " + trade_no+" "+trade_status+"liuliuliu");
		OrderMain o=  orderMainDao.queryById(order_id);
		if(o!=null){
			if(OrderConstants.ORDER_STATUS_PAID.equals(o.getOrderStatus())&&null!=o.getPayTime()&&!StringUtils.isEmpty(o.getTradeNo())){
				message="success";
			}else{
		   try {
				//月租产权同步时间发送短信
				orderMainService.monAndProPaid(o);
				//成功调用返回第三方成功
				message="success";
			o.setPayType(pay_Type);
			o.setTradeNo(trade_no);
			if (o.getPayTime() == null) {
				o.setPayTime(new Date());
			}
			o.setOrderStatus(OrderConstants.ORDER_STATUS_PAID);
			orderMainDao.update(o)	;	
		} catch (Exception e) {
			e.printStackTrace();
			   error="异常"+e.getMessage();
			logger.info("servise bug");
			message="fail";
		}
			PaymentInfo paymentInfo = new PaymentInfo();
			paymentInfo.setCreateDate(new Date());
			paymentInfo.setCreateor("admin");
			paymentInfo.setOrderId(order_id);
			paymentInfo.setPayType(pay_Type);
			paymentInfo.setUseInfo(error+user_info);
			paymentInfo.setUseType("2");
			paymentInfoDao.insert(paymentInfo);
			}
		}else{
			message="fail";
			logger.info("can not find the order which order_id= "+order_id);
		}
		return message;
	}
	/**
	 * 共享
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String payBackTempShare(String order_id, String trade_no,
		String trade_status, String buyer_email, String pay_Type,
		String total_Fee,String user_info) {
		String message = "not finish";
	String error="";
		logger.info(order_id+" " + trade_no+" "+trade_status+"liuliuliu");
		OrderMain o = orderMainDao.queryById(order_id);
		if (o != null) {
			if(OrderConstants.ORDER_STATUS_PAID.equals(o.getOrderStatus())&&null!=o.getPayTime()&&!StringUtils.isEmpty(o.getTradeNo())){
				message="success";
			}else {
				try {
					logger.info("洗车短信-----------------" + o.getOrderType() + "----------------" + o.getOrderStatus());
					if (("17".equals(o.getOrderType())) && ("10".equals(o.getOrderStatus()))) {
						paymentInfoService.sendToCarwashManager(o);
					}
					//成功调用返回第三方成功
					message = "success";
					o.setPayType(pay_Type);
					o.setTradeNo(trade_no);
					if (o.getPayTime() == null) {
						o.setPayTime(new Date());
					}
					o.setOrderStatus(OrderConstants.ORDER_STATUS_PAID);
					orderMainDao.update(o);

				} catch (Exception e) {
					e.printStackTrace();
					logger.info("servise bug");
					message = "fail";
					error="异常"+e.getMessage();
				}
				PaymentInfo paymentInfo = new PaymentInfo();
				paymentInfo.setCreateDate(new Date());
				paymentInfo.setCreateor("admin");
				paymentInfo.setOrderId(order_id);
				paymentInfo.setPayType(pay_Type);
				paymentInfo.setUseInfo(error+user_info);
				paymentInfo.setUseType("2");
				paymentInfoDao.insert(paymentInfo);
			}
			}else{
				message="fail";
				logger.info("can not find the order which order_id= "+order_id);
			}
		return message;
	}
	/**
	 * 临停
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String payBackTemp(String order_id, String trade_no,
			String trade_status, String buyer_email, String pay_Type,
			String total_Fee,String user_info) {
		 String error="";
		String message = "not finish";
		logger.info(order_id+" " + trade_no+" "+trade_status+"liuliuliu");
		OrderMain o = orderMainDao.queryById(order_id);
				if (o != null) {
					if(OrderConstants.ORDER_STATUS_PAID.equals(o.getOrderStatus())&&null!=o.getPayTime()&&!StringUtils.isEmpty(o.getTradeNo())){
						message="success";
					}else {
				try {
					orderMainDao.finishPay(order_id);
					//如果是临停订单的话执行
					//if(OrderConstants.ORDER_TYPE_TEMPORARY.equals(o.getOrderType())){
					OrderMain om = orderMainDao.selectOrderDetailT(o);
					if (null != om) {
						PayStatusSync payStatusSync = new PayStatusSync();
						payStatusSync.setPlateId(om.getCarNumber());// 车牌号
						payStatusSync.setPayAmount(
								String.valueOf(om.getAmountPayable().intValue()));// 应付金额

						payStatusSync.setPayTime(om.getPayTime() == null ? DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) :
								DateUtil.date2str(om.getPayTime(), DateUtil.DATETIME_FORMAT));// 支付时间
						payStatusSync.setGetTime(DateUtil.date2str(om.getCreateDate(), DateUtil.DATETIME_FORMAT));// 算费时间
						payStatusSync.setPayType(om.getPayType());// 支付类型
						payStatusSync.setPayNo(trade_no);// 订单交易流水号
						backForTempOrder(order_id, payStatusSync);
					}
					//成功调用返回第三方成功
					message = "success";

					//}
					o.setPayType(pay_Type);
					o.setTradeNo(trade_no);
					if (o.getPayTime() == null) {
						o.setPayTime(new Date());
					}
					o.setOrderStatus(OrderConstants.ORDER_STATUS_PAID);
					orderMainDao.update(o);

				} catch (Exception e) {
					e.printStackTrace();
					logger.info("servise bug");
					error="异常"+e.getMessage();
					message = "fail";
				}
				PaymentInfo paymentInfo = new PaymentInfo();
				paymentInfo.setCreateDate(new Date());
				paymentInfo.setCreateor("admin");
				paymentInfo.setOrderId(o.getOrderId());
				paymentInfo.setPayType(pay_Type);
				paymentInfo.setUseInfo(error+user_info);
				paymentInfo.setUseType("2");
				paymentInfoDao.insert(paymentInfo);
			}
			}else{
				message="fail";
				logger.info("can not find the order which order_id= "+order_id);
				return message;
			}
		return message;
	}

	public void backForTempOrder(String order_id,PayStatusSync payStatusSync){
		//String resultjson = payStatusSyncService.parsePayStatusSyn(payStatusSync);
		
		logger.info("into parsePayStatusSyn(PayStatusSync payStatusSync)......");
		/**--------------------调用蓝卡*/	
		// 管理账户apikey 注册，激活账户后获得
		payStatusSync.setKey(APIKey);//管理员账号
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<PayStatusSync> request = new HttpEntity<PayStatusSync>(payStatusSync, requestHeaders);
		try {
			// 返回结果处理
			String resultJson = restTemplate.postForObject(payStatus, request, String.class);
			// String resultJson2 = initRestTemplate().postForObject(HTTP_URL, request, String.class);
			logger.info("resultJson = " + resultJson);

		} catch (Exception e) {
			logger.error("在线支付结果反馈rest远程调用失败:" + e.getMessage());
		}
	}
	@Override
	public Page<Customer> queryByC(Page<Customer> page) {
		page.setResultList(customerDao.queryByC(page));
		return page;
	}
	@Override
	public List<Customer> exportByC(Customer customer) {
		return customerDao.exportByC(customer);
	}
	/**
	 * 登录
	 * @param mobile
	 * @param password
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String login(String mobile, String password) {
		String mess = null;
		Map<String,Object> ctMap = new HashMap<String,Object>();
		Customer cuParam = new Customer();
		cuParam.setCustomerMobile(mobile);
		//cuParam.setCustomerPassword(MD5Util.md5(password));
		List<Customer> cuList = customerDao.selectList(cuParam);
		if(null!=cuList&&cuList.size()>0){
			if(MD5Util.md5(password).equals(cuList.get(0).getCustomerPassword())){
//				Customer cus = new Customer();
//				cus.setLastLoginTime(new Date());
//				customerDao.update(cus);
				customerDao.updateLastLoginTime(cuList.get(0).getCustomerId(), new Date());
				ctMap.put("customerId", cuList.get(0).getCustomerId());
				ctMap.put("customerNickname", (null!=cuList.get(0).getCustomerNickname())?cuList.get(0).getCustomerNickname():"");
				ctMap.put("customerSex",(null!=cuList.get(0).getCustomerSex())?cuList.get(0).getCustomerSex():"");
				ctMap.put("customerEmail", (null!=cuList.get(0).getCustomerEmail())?cuList.get(0).getCustomerEmail():"");
				ctMap.put("customerAge", cuList.get(0).getCustomerAge()+"");
				ctMap.put("customerHead", (null!=cuList.get(0).getCustomerHead())?cuList.get(0).getCustomerHead():"");
				ctMap.put("customerMobile",(null!=cuList.get(0).getCustomerMobile())?cuList.get(0).getCustomerMobile():"");
				ctMap.put("customerCity",(null!=cuList.get(0).getCustomerRegion())?cuList.get(0).getCustomerRegion():"");
				ctMap.put("customerJob", (null!=cuList.get(0).getCustomerJob())?cuList.get(0).getCustomerJob():"");
				ctMap.put("identity", (null!=cuList.get(0).getIdentity())?cuList.get(0).getIdentity():"");
				ctMap.put("payPassword", (null!=cuList.get(0).getPay_password())&&cuList.get(0).getPay_password().length()!=0?"1":"0");
				mess = ShangAnMessageType.toShangAnJson("0", "登录成功", "customer", ctMap);
			}else{
				mess = ShangAnMessageType.operateToJson("2", "密码错误");
			}
		}else{
			mess = ShangAnMessageType.operateToJson("2", "该手机号不存在");
		}
		return mess;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void submitBluecard(String orderId,String tradeNo) {
		try {			
			OrderMain o=  orderMainDao.queryById(orderId);
			if(o!=null){
				//如果是临停订单的话执行
				OrderMain om = orderMainDao.selectOrderDetailT(o);
				if(null!=om){
					PayStatusSync payStatusSync = new PayStatusSync();
					payStatusSync.setPlateId(om.getCarNumber());// 车牌号
					payStatusSync.setPayAmount(
						String.valueOf(om.getAmountPayable().intValue()));// 应付金额
	
					payStatusSync.setPayTime(om.getPayTime()==null?DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT):
						DateUtil.date2str(om.getPayTime(), DateUtil.DATETIME_FORMAT));// 支付时间
					payStatusSync.setGetTime(DateUtil.date2str(om.getCreateDate(),DateUtil.DATETIME_FORMAT));// 算费时间
					payStatusSync.setPayType(om.getPayType());// 支付类型
					payStatusSync.setPayNo(tradeNo);// 订单交易流水号
					backForTempOrder(orderId,payStatusSync);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("servise bug");
		}
	}

	@Override
	public String getMoney(String customerId) {
    String mess=null;
		try {
			Customer customer=customerDao.queryByCustomerId(customerId);
    if(customer!=null&&!customer.equals("")){
		//BigDecimal mon=new BigDecimal(customer.getMoney());
			//	/100;
     mess=ShangAnMessageType.toShangAnJson("0", "查询成功", "money",customer.getMoney()/100.00);
	}else{
		mess=ShangAnMessageType.toShangAnJson("1", "数据异常", "money", "");
	}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("servise bug");
		}
		return mess;
	}

	@Override
	public String getPayPassWord(String customerId) {
		String mess=null;
		try {
			Customer customer=customerDao.queryByCustomerId(customerId);
			if(customer.getPay_password()!=null&&!customer.getPay_password().equals("")){
				mess=ShangAnMessageType.toShangAnJson("0", "查询成功", "payPassword","");
			}else{
				mess=ShangAnMessageType.toShangAnJson("1", "无密码", "payPassword", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("servise bug");
		}
		return mess;
	}
	public String initPayPassWord(String customerId,String payPassword) {
		String mess=null;
		try {
			Customer customer=customerDao.queryByCustomerId(customerId);
			if(customer.getPay_password()!=null&&!customer.getPay_password().equals("")){
				mess=ShangAnMessageType.toShangAnJson("1", "已存在支付密码", "payPassword", "");
			}else{
				customer.setPay_password(payPassword);
			int index=	customerDao.update(customer);
				if(index==1){
					mess=ShangAnMessageType.toShangAnJson("0", "初始化成功", "payPassword", "");
				}else{
					mess=ShangAnMessageType.toShangAnJson("1", "数据异常", "payPassword", "");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("servise bug");
		}
		return mess;
	}

	@Override
	public String resetPayPassword(String customerId, String payPassword) {
		//SingletonClient.getInstance().sendMessageCode(customerMobile);
		String message =null;
		Customer customer=customerDao.queryByCustomerId(customerId);
		if(customer!=null&&!customer.equals("")){
				customer.setPay_password(payPassword);
				int index=	customerDao.update(customer);
				if(index==1){
					message=ShangAnMessageType.toShangAnJson("0", "重置成功", "payPassword", "");
				}else{
					message=ShangAnMessageType.toShangAnJson("1", "数据异常", "payPassword", "");
				}

			}else {
			message=ShangAnMessageType.toShangAnJson("1", "数据异常", "code", "");
		}



		return message;
	}

	/**
	 * 加钱
	 * @param customerId
	 * @param money 单位 分
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void addMoney(String customerId, int money) {
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setMoney(money);
		customerDao.addMoney(customer);
	}

	/**
	 * 扣钱
	 * @param customerId
	 * @param amountPaid 单位 分
	 * @return
	 */
	@Override
	public int subtractMoney(String customerId, Integer amountPaid) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("customerId", customerId);
		map.put("amountPaid", amountPaid < 0 ? 0 : amountPaid);
		return customerDao.subtractMoney(map);
	}

	@Override
	public Page<Customer> selectByChannel(Page<Customer> page) {
		page.setResultList(customerDao.selectByChannel(page));
		return page;
	}

	private Customer hasMobile(List<Customer> list) {
		if (null != list && list.size() > 0) {//存在记录
			for (Customer c : list) {
				if (StringUtils.isNotEmpty(c.getCustomerMobile())) {
					return c;//取有手机号的记录
				}
			}
			return list.get(0);
		}
		return null;
	}

	@Override
	public Customer loginByOtherV2(String quickId, String quickType) {
		Customer ct = new Customer();
		switch (quickType) {
			case "01":  // 微信
				ct.setWxId(quickId);
				break;
			case "03": //新浪微博
				ct.setSinaId(quickId);
				break;
			case "04": //qq
				ct.setQqId(quickId);
				break;
			default:
				return null;
		}
		List<Customer> cuList = customerDao.selectList(ct);
		return hasMobile(cuList);
	}

	@Override
	public String getInfo(String customerId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> mapCust = new HashMap<String,Object>();
		String msg=null;
		Customer customer=customerDao.queryByCustomerId(customerId);// 客户信息
		if(customer!=null){
			mapCust.put("customerId",customer.getCustomerId());
			mapCust.put("customerNickname",null==customer.getCustomerNickname()?"":customer.getCustomerNickname());
			mapCust.put("customerHead",null==customer.getCustomerNickname()?"":customer.getCustomerNickname());
			mapCust.put("customerMobile",null==customer.getCustomerNickname()?"":customer.getCustomerNickname());
			mapCust.put("identity",null==customer.getCustomerNickname()?"":customer.getCustomerNickname());
			mapCust.put("growthValue",null==customer.getCustomerNickname()?"":customer.getCustomerNickname());
			mapCust.put("growthLower",null==customer.getCustomerNickname()?"":customer.getCustomerNickname());
			mapCust.put("growthUpper",null==customer.getCustomerNickname()?"":customer.getCustomerNickname());
			mapCust.put("authNum",null==customer.getCustomerNickname()?"":customer.getCustomerNickname());
			mapCust.put("customerLevelCode",null==customer.getCustomerNickname()?"":customer.getCustomerNickname());
			mapCust.put("customerLevelName",null==customer.getCustomerNickname()?"":customer.getCustomerNickname());
			map.put("wallet",null==customer.getMoney()?0:customer.getMoney());//余额
		}
		map.put("customer",mapCust);
		map.put("authIcon","");
		map.put("privilege","");
		//map.put("wallet","");
		map.put("coupon","");
		//authIcon  特权图标
		//privilege 特权详情
		//wallet
		//coupon
		msg=  ShangAnMessageType.toShangAnJson("1", "数据异常", "data", map);

		return msg;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String updateCustomerInfo(Customer customer,HttpServletRequest request) {
		String mess=null;
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		customerDao.update(customer);
		//Customer cust=	customerDao.queryByCustomerId(customer.getCustomerId());
		/*if(customer.getCustomerHead()!=null&&!customer.getCustomerHead().equals("")){
			customer.setCustomerHead(basePath+customer.getCustomerHead());
		}*/
		mess=	ShangAnMessageType.toShangAnJson("0", "修改成功", "data", customer);
		return mess;
	}

	@Override
	public void setThirdId(Customer customer, String quickId, String quickType) {
		switch (quickType) {
			case "01":  // 微信
				customer.setWxId(quickId);
				break;
			case "03": //新浪微博
				customer.setSinaId(quickId);
				break;
			case "04": //qq
				customer.setQqId(quickId);
				break;
		}
	}

	/**
	 * 1、查询快捷登录id是否有记录 (一个不含手机号的记录返回该记录   带有包含手机号两条记录：取有手机号的记录 )
	 * 2、没记录添加一条记录
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String loginByOther(String quickId,String quickType,String reg_phone){
		String message = null;
		Customer ct = new Customer();
		Customer customer = null;
		List<Customer> cuList  = null;
		if("01".equals(quickType)){//微信
			ct.setWxId(quickId);
		    cuList = customerDao.selectList(ct);
		    if(null!=cuList&&cuList.size()>0){//存在记录
		    	for(Customer c:cuList){
		    		if(!StringUtils.isEmpty(c.getCustomerMobile())){
		    			customer = c;//取有手机号的记录
		    			break;
		    		}else{
		    			customer = cuList.get(0);
		    		}
		    	}
		    }else{//不存在记录生成记录
		    	try {
					customer = otherRegist(quickType,quickId,reg_phone);
				} catch (Exception e) {
					logger.error("记录生成异常",e);
				}
		    }
		}else if("03".equals(quickType)){//新浪微博
			ct.setSinaId(quickId);
			cuList = customerDao.selectList(ct);
			if(null!=cuList&&cuList.size()>0){//存在记录
		    	for(Customer c:cuList){
		    		if(!StringUtils.isEmpty(c.getCustomerMobile())){
		    			customer = c;//取有手机号的记录
		    			break;
		    		}else{
		    			customer = cuList.get(0);
		    		}
		    	}
		    }else{//不存在记录生成记录
		    	try {
					customer = otherRegist(quickType,quickId,reg_phone);
				} catch (Exception e) {
					logger.error("记录生成异常",e);
				}
		    }
		}else if("04".equals(quickType)){//qq
			ct.setQqId(quickId);
			cuList = customerDao.selectList(ct);
			if(null!=cuList&&cuList.size()>0){//存在记录
		    	for(Customer c:cuList){
		    		if(!StringUtils.isEmpty(c.getCustomerMobile())){
		    			customer = c;//取有手机号的记录
		    			break;
		    		}else{
		    			customer = cuList.get(0);
		    		}
		    	}
		    }else{//不存在记录生成记录
		    	try {
					customer = otherRegist(quickType,quickId,reg_phone);
				} catch (Exception e) {
					logger.error("记录生成异常",e);
				}
		    }
		}else{
			message = ShangAnMessageType.operateToJson("1", "登录失败");
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
				customerDao.updateLastLoginTime(customer.getCustomerId(), new Date());
			} catch (Exception e) {
				logger.error("登录时,修改最后登录时间异常!", e);
			}

		}else{
			message = ShangAnMessageType.operateToJson("1", "登录失败用户信息获取失败");
		}
		return message;
	}
	public Customer otherRegist(String quickType,String quickId,String reg_phone ) throws Exception{
    	Customer addCustomer = new Customer();
    	Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_CUSTOMER);
    	addCustomer.setCustomerId(sequence.getId());
    	addCustomer.setCreatedAt(DateUtil.date2str(new Date(),DateUtil.DATETIME_FORMAT));
    	addCustomer.setIdentity("0");
		addCustomer.setReg_phone(reg_phone);
    	addCustomer = this.setOtherId(quickType,quickId,addCustomer);
    	customerDao.insert(addCustomer);	
	    return addCustomer; 
	}
	//设置otherId
	public Customer setOtherId(String quickType,String quickId,Customer customer){
		if("01".equals(quickType)){//微信
			customer.setWxId(quickId);
		}else if("03".equals(quickType)){//新浪微博
			customer.setSinaId(quickId);
		}else if("04".equals(quickType)){//qq
			customer.setQqId(quickId);
		}
	    return customer;
	}
	/**
	 * 绑定手机号
	 * 1、不存在该手机号记录 生成*新的记录* 存入第三方信息 ！返回用户信息
	 * 2、存在手机号记录(绑定操作、并存入其中为空的值)！返回用户信息机
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String bondByOther(String quickType,String password,String mobile ,String quickId){
		String message = null;		
		//返回的map 和用户信息
		Map<String, String> map = new HashMap<String, String>();
		Customer cusReturn = new Customer();
		//查询该手机号记录是否存在
		Customer cus = new Customer();		
		cus.setCustomerMobile(mobile);
		List<Customer> cusList = customerDao.selectList(cus);
		//查询第三方的id用户信息
		Customer cusOtherParam = new Customer();
		cusOtherParam = this.setOtherId(quickType,quickId,cusOtherParam);
		List<Customer> cusOtherList = customerDao.selectList(cusOtherParam);
		//做绑定操作时第三方信息是肯定存在的，如果不存在
		if(cusOtherList==null||cusOtherList.size()==0){
			logger.error("第三方的信息不存在无法绑定");
			return ShangAnMessageType.operateToJson("2", "第三方的信息不存在无法绑定");
		}
		//存在手机号 做绑定操作(1、将第三方信息绑定到手机号记录信息上)
		if(null!=cusList&&cusList.size()>0){
			//获取手机号记录信息
			Customer cusMobile = cusList.get(0);
			//获取第三方的信息
			Customer cusOther = cusOtherList.get(0);
			//合并数据
			Customer addCustomer = mergeCusInfo(cusMobile,cusOther);
			try {
				customerDao.update(addCustomer);
			} catch (Exception e) {
				logger.error("存在手机号 做绑定操作-第三方绑定手机customer修改异常",e);
				return ShangAnMessageType.operateToJson("2", "存在手机号 做绑定操作-第三方绑定手机customer修改异常");
			}
			cusReturn = addCustomer;
		}else{//建立一条新纪录
			Customer addCustomer = new Customer();
			addCustomer.setCustomerMobile(mobile);
			addCustomer.setCustomerPassword(MD5Util.md5(password));
	    	Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_CUSTOMER);
	    	addCustomer.setCustomerId(sequence.getId());
	    	addCustomer.setCreatedAt(DateUtil.date2str(new Date(),DateUtil.DATETIME_FORMAT));
	    	addCustomer = this.setOtherId(quickType,quickId,addCustomer);
	    	addCustomer.setIdentity("0");
	    	try {
	    		customerDao.insert(addCustomer);
			} catch (Exception e) {
				logger.error("建立一条新纪录-第三方绑定手机customer表插入异常",e);
				return ShangAnMessageType.operateToJson("2", "存在手机号 做绑定操作-第三方绑定手机customer修改异常");
			}
	    	cusReturn = addCustomer;
		}
		if(null==cusReturn){
			return ShangAnMessageType.operateToJson("2", "返回用户信息不存在");
		}
		map.put("customerId", cusReturn.getCustomerId());
		map.put("customerMobile", (null!=cusReturn.getCustomerMobile())?cusReturn.getCustomerMobile():"");
		map.put("customerNickname",(null!=cusReturn.getCustomerNickname())?cusReturn.getCustomerNickname():"");
		map.put("customerSex", (null!=cusReturn.getCustomerSex())?cusReturn.getCustomerSex()+"":"");
		map.put("customerEmail", (null!=cusReturn.getCustomerEmail())?cusReturn.getCustomerEmail():"");
		map.put("customerAge", (null!=cusReturn.getCustomerAge())?cusReturn.getCustomerAge()+"":"");
		map.put("customerHead", (null!=cusReturn.getCustomerHead())?cusReturn.getCustomerHead():"");
		map.put("customerCity", (null!=cusReturn.getCustomerRegion())?cusReturn.getCustomerRegion():"");
		map.put("customerJob", (null!=cusReturn.getCustomerJob())?cusReturn.getCustomerJob():"");
		map.put("identity", (null!=cusReturn.getIdentity())?cusReturn.getIdentity():"");
		map.put("payPassword", cusReturn.getPay_password() == null || cusReturn.getPay_password().trim().length() == 0 ? "0" : "1");
		message = ShangAnMessageType.toShangAnJson("0", "绑定成功", "customer", map);
		return message;
		
	}
	/**
	 * 合并用户数据
	 * @param cusMobile
	 * @param cusOther
	 * @return
	 */
	public Customer mergeCusInfo(Customer cusMobile,Customer cusOther){
		cusMobile.setChannel(null==cusMobile.getChannel()?cusOther.getChannel():cusMobile.getChannel());
		cusMobile.setCustomerAge(null==cusMobile.getCustomerAge()?cusOther.getCustomerAge():cusMobile.getCustomerAge());
		cusMobile.setCustomerEmail(null==cusMobile.getCustomerEmail()?cusOther.getCustomerEmail():cusMobile.getCustomerEmail());
		cusMobile.setCustomerHead(null==cusMobile.getCustomerHead()?cusOther.getCustomerHead():cusMobile.getCustomerHead());
		cusMobile.setCustomerJob(null==cusMobile.getCustomerJob()?cusOther.getCustomerJob():cusMobile.getCustomerJob());
		cusMobile.setCustomerLevel(null==cusMobile.getCustomerLevel()?cusOther.getCustomerLevel():cusMobile.getCustomerLevel());
		cusMobile.setCustomerName(null==cusMobile.getCustomerName()?cusOther.getCustomerName():cusMobile.getCustomerName());
		cusMobile.setCustomerNickname(null==cusMobile.getCustomerNickname()?cusOther.getCustomerNickname():cusMobile.getCustomerNickname());
		cusMobile.setCustomerRegion(null==cusMobile.getCustomerRegion()?cusOther.getCustomerRegion():cusMobile.getCustomerRegion());
		cusMobile.setCustomerSex(null==cusMobile.getCustomerSex()?cusOther.getCustomerSex():cusMobile.getCustomerSex());
		cusMobile.setIdentity(null==cusMobile.getIdentity()?cusOther.getIdentity():cusMobile.getIdentity());
		cusMobile.setLastLoginTime(null==cusMobile.getLastLoginTime()?cusOther.getLastLoginTime():cusMobile.getLastLoginTime());
		cusMobile.setMoney(null==cusMobile.getMoney()?cusOther.getMoney():cusMobile.getMoney());
		cusMobile.setPay_password(null==cusMobile.getPay_password()?cusOther.getPay_password():cusMobile.getPay_password());
		cusMobile.setQqId(null==cusMobile.getQqId()?cusOther.getQqId():cusMobile.getQqId());
		cusMobile.setSinaId(null==cusMobile.getSinaId()?cusOther.getSinaId():cusMobile.getSinaId());
		cusMobile.setWxId(null==cusMobile.getWxId()?cusOther.getWxId():cusMobile.getWxId());		
		return cusMobile;
	}
	@Override
	public String queryPhoneCode(String customerMobile) {
		String mess=null;
		
		try {
			Customer customer = new Customer();
			customer.setCustomerMobile(customerMobile);
			List<Customer> cuList = customerDao.selectList(customer);
			if(cuList!=null&&cuList.size()>0){
				mess=ShangAnMessageType.toShangAnJson("0", "该手机已注册", "customerMobile", "");
			}else{
				mess=ShangAnMessageType.toShangAnJson("1", "该手机未注册", "customerMobile", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("servise bug");
		}
		return mess;
	}

	@Override
	public void updateLastLoginTime2(String customerId, Date lastLoginTime,String lastLoginMachine,String appVersion,String lastLoginSys) {
		customerDao.updateLastLoginTime2(customerId, lastLoginTime,lastLoginMachine,appVersion,lastLoginSys);
	}
	@Override
	public void updateLastLoginTime(String customerId, Date lastLoginTime) {
		customerDao.updateLastLoginTime(customerId, lastLoginTime);
	}
	/**
	 * 获取兑换码
	 */
	@Override
	public String getRedeemCode(String customerId) throws Exception {
		Customer customer = this.queryByCustomerId(customerId);
		if (customer == null) {
			throw new NullPointerException("查询不到用户信息");
		}
		// 如果没有则生成一个兑换码
		if (customer.getRedeemCode() == null || customer.getRedeemCode().trim().length() == 0) {
			String redeemCode;
			// 循环生成到数据库不存在这个兑换码为止
			while (redeemCodeExist(redeemCode = RandomUtil.rand(11)));
			customer.setRedeemCode(redeemCode);
			customerDao.updateRedeemCode(customer);
		}
		return customer.getRedeemCode();
	}

	/**
	 * 判读兑换码是否存在
	 * @return true 存在
	 */
	private boolean redeemCodeExist(String redeemCode) {
		return this.queryByRedeemCode(redeemCode) != null;
	}

	/**
	 * 根据兑换码查询用户信息
	 */
	@Override
	public Customer queryByRedeemCode(String redeemCode) {
		Customer customer = new Customer();
		customer.setRedeemCode(redeemCode);
		List<Customer> list = customerDao.selectList(customer);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	/**
	 * 通过openId获取customerId
	 */
	@Override
	public String queryByOpenId(String openId){
		String mess = null;
		Map<String,Object> ctMap = new HashMap<String,Object>();
		Customer cuParam = new Customer();
		cuParam.setWxpayOpenid(openId);
		//cuParam.setCustomerPassword(MD5Util.md5(password));
		List<Customer> cuList = customerDao.selectList(cuParam);
		if(null!=cuList&&cuList.size()>0){
			ctMap.put("customerId", cuList.get(0).getCustomerId());
			mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "customer", ctMap);
		}else{
			mess = ShangAnMessageType.operateToJson("2", "该openId不存在");
		}
		return mess;
	}
	/**
	 * 微信登录
	 * @param mobile
	 * @param
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String wxLogin(String mobile,String wxpayOpenid) {
		String mess = null;
		Map<String,Object> ctMap = new HashMap<String,Object>();
		Customer cuParam = new Customer();
		cuParam.setCustomerMobile(mobile);
		//cuParam.setCustomerPassword(MD5Util.md5(password));
		List<Customer> cuList = customerDao.selectList(cuParam);
		if(null!=cuList&&cuList.size()>0){
			Customer cus = cuList.get(0);
			cus.setLastLoginTime(new Date()); // 忍不住吐槽,customer-mapper的update方法里面压根就没有lastLoginTime这个字段,你更新个啥哦
			//if(StringUtils.isEmpty(cus.getWxpayOpenid())){
			cus.setWxpayOpenid(wxpayOpenid);
			//}
			customerDao.update(cus);
			ctMap.put("customerId", cuList.get(0).getCustomerId());
			ctMap.put("customerNickname", (null!=cuList.get(0).getCustomerNickname())?cuList.get(0).getCustomerNickname():"");
			ctMap.put("customerSex",(null!=cuList.get(0).getCustomerSex())?cuList.get(0).getCustomerSex():"");
			ctMap.put("customerEmail", (null!=cuList.get(0).getCustomerEmail())?cuList.get(0).getCustomerEmail():"");
			ctMap.put("customerAge", cuList.get(0).getCustomerAge()+"");
			ctMap.put("customerHead", (null!=cuList.get(0).getCustomerHead())?cuList.get(0).getCustomerHead():"");
			ctMap.put("customerMobile",(null!=cuList.get(0).getCustomerMobile())?cuList.get(0).getCustomerMobile():"");
			ctMap.put("customerCity",(null!=cuList.get(0).getCustomerRegion())?cuList.get(0).getCustomerRegion():"");
			ctMap.put("customerJob", (null!=cuList.get(0).getCustomerJob())?cuList.get(0).getCustomerJob():"");
			ctMap.put("identity", (null!=cuList.get(0).getIdentity())?cuList.get(0).getIdentity():"");
			ctMap.put("payPassword", (null!=cuList.get(0).getPay_password())&&cuList.get(0).getPay_password().length()!=0?"1":"0");
			ctMap.put("wxpayOpenid", null!=cuList.get(0).getWxpayOpenid()?cuList.get(0).getWxpayOpenid():"");
			mess = ShangAnMessageType.toShangAnJson("0", "登录成功", "customer", ctMap);
			try {
				customerDao.updateLastLoginTime(cuList.get(0).getCustomerId(), new Date());
			} catch (Exception e) {
				logger.error("登录时,修改最后登录时间异常!", e);
			}
		}else{
			mess = ShangAnMessageType.operateToJson("2", "该手机号不存在");
		}
		return mess;
	}

	@Override
	public Map paramsFilter(Customer customer) {
		Map<String, Object> ctMap = new HashMap<>(11);
		ctMap.put("customerId", customer.getCustomerId());
		ctMap.put("customerNickname", (null != customer.getCustomerNickname()) ? customer.getCustomerNickname() : "");
		ctMap.put("customerSex", (null != customer.getCustomerSex()) ? customer.getCustomerSex() : "");
		ctMap.put("customerEmail", (null != customer.getCustomerEmail()) ? customer.getCustomerEmail() : "");
		ctMap.put("customerAge", customer.getCustomerAge() == null ? "" : Integer.toString(customer.getCustomerAge()));
		ctMap.put("customerHead", (null != customer.getCustomerHead()) ? customer.getCustomerHead() : "");
		ctMap.put("customerMobile", (null != customer.getCustomerMobile()) ? customer.getCustomerMobile() : "");
		ctMap.put("customerCity", (null != customer.getCustomerRegion()) ? customer.getCustomerRegion() : "");
		ctMap.put("customerJob", (null != customer.getCustomerJob()) ? customer.getCustomerJob() : "");
		ctMap.put("identity", (null != customer.getIdentity()) ? customer.getIdentity() : "");
		ctMap.put("payPassword", (null != customer.getPay_password()) && customer.getPay_password().length() != 0 ? "1" : "0");
		return ctMap;
	}

	@Override
	public List<Map<String,Object>> queryListPage2(Object page) {
		return customerDao.queryListPage2(page);
	}
}