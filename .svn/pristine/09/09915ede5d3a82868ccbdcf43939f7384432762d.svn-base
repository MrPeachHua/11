package com.boxiang.share.payment.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.b2m.eucp.client.SingletonClient;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.payment.dao.PaymentInfoDao;
import com.boxiang.share.payment.po.PaymentInfo;
import com.boxiang.share.payment.service.PaymentInfoService;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.order.dao.OrderCarwashDao;
import com.boxiang.share.product.order.dao.OrderMainDao;
import com.boxiang.share.product.order.po.OrderCarwash;
import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.product.order.po.OrderRecharge;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.product.order.service.OrderRechargeService;
import com.boxiang.share.product.parker.dao.ParkerDao;
import com.boxiang.share.system.dao.DictionaryDao;
import com.boxiang.share.system.dao.SendMessageDao;
import com.boxiang.share.system.po.Dictionary;
import com.boxiang.share.system.po.SendMessage;
import com.boxiang.share.user.dao.UserInfoDao;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.OrderConstants;

@DataSource(DataSourceEnum.MASTER)
@Service("PaymentInfo")
public class PaymentInfoServiceImpl implements PaymentInfoService {
	private static final Logger logger = Logger.getLogger(PaymentInfoServiceImpl.class);
	
	@Resource(name = "paymentInfoDao")
	private PaymentInfoDao paymentInfoDao;

	@Resource(name="orderMainDao")
	private OrderMainDao orderMainDao;

	@Resource
	private OrderRechargeService orderRechargeService;

	@Resource
	private CustomerService customerService;
	
	@Resource
	private OrderMainService orderMainService;
	
	@Resource
	private OrderCarwashDao orderCarwashDao;
	
	@Resource
	private UserInfoDao userInfoDao;
	
	@Resource
	private ParkerDao parkerDao;
	
	@Resource
	private SendMessageDao sendMessageDao;
	
	@Resource
	private DictionaryDao dictionaryDao;
	@Override
	public List<PaymentInfo> selectList(PaymentInfo paymentInfo) {
		return paymentInfoDao.selectList(paymentInfo);
	}

	@Override
	public Page<PaymentInfo> queryListPage(Page<PaymentInfo> page) {
		page.setResultList(paymentInfoDao.queryListPage(page));
		return page;
	}

	@Override
	public PaymentInfo queryById(java.lang.Integer id) {
		return paymentInfoDao.queryById(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class, Exception.class })
	public void add(PaymentInfo paymentInfo) {
		paymentInfoDao.insert(paymentInfo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class, Exception.class })
	public void delete(java.lang.Integer id) {
		paymentInfoDao.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class, Exception.class })
	public void update(PaymentInfo paymentInfo) {
		paymentInfoDao.update(paymentInfo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { RuntimeException.class, Exception.class })
	public void batchUpdate(List<PaymentInfo> paymentInfoList) {
		paymentInfoDao.batchUpdate(paymentInfoList);
	}
	
	/**
	 * @param orderId	订单号
	 * @param tradeNo	支付宝交易号
	 * @param payType	支付类型
	 * @param amountPaid	实付金额
	 * @param gmtPayment	支付时间
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public  String updateOrderByPayback(String orderId, String tradeNo, String payType,String amountPaid,String gmtPayment) throws Exception{
		String message = "fail";
		logger.info(orderId+" " + tradeNo);
			OrderMain order = orderMainDao.queryById(orderId);
		if (null == order) return "fail";
		if((OrderConstants.ORDER_STATUS_PAID.equals(order.getOrderStatus()) || OrderConstants.ORDER_STATUS_FINISHED.equals(order.getOrderStatus()))
				&&order.getPayTime()!=null && !StringUtils.isEmpty(order.getTradeNo())){
			message="success";
		}else {
			logger.info("洗车短信-----------------" + order.getOrderType() + "----------------" + order.getOrderStatus());
			//发送短信给洗车管理员
			if ((OrderConstants.ORDER_TYPE_CARWASH.equals(order.getOrderType())) && (OrderConstants.ORDER_STATUS_UNPAY.equals(order.getOrderStatus()))) {
			final OrderMain ordre1 = order;
				new Thread(new Runnable() {
				 @Override
				 public void run() {
					 sendToCarwashManager(ordre1);
				 }
			 }).start();
			}

			order.setTradeNo(tradeNo);
			order.setPayType(payType);
			if (order.getPayTime() == null) {
					order.setPayTime(DateUtil.str2date(gmtPayment, DateUtil.DATETIME_FORMAT));
			}

			BigDecimal amount = new BigDecimal(amountPaid);
			if (amount.compareTo(new BigDecimal(order.getAmountPaid() / 100)) != 0) {
				// 如果金额不一致，但已经给钱包加过钱，则扣除
				if (order.getOrderType().equals("16") && order.getOrderStatus().equals(OrderConstants.ORDER_STATUS_PAID)) {
					customerService.subtractMoney(order.getCustomerId(), order.getAmountPaid());
				}
				order.setOrderStatus(OrderConstants.ORDER_STATUS_EXCEPTION);//金额验证不通过，修改状态为异常
			} else {
				if (order.getOrderType().equals("16") && order.getOrderStatus().equals(OrderConstants.ORDER_STATUS_UNPAY)) { // 钱包充值
					// 修改订单状态
					order.setOrderStatus(OrderConstants.ORDER_STATUS_PAID);
					orderMainDao.updateUnpayToPaid(order.getOrderId());
					/*if (orderMainDao.updateUnpayToPaid(order.getOrderId()) != 1) {
						throw new Exception("订单状态修改异常");
					}*/
					OrderRecharge orderRecharge = new OrderRecharge();
					orderRecharge.setOrderId(orderId);
					orderRecharge.setIsUsed(Constants.TRUE);
					orderRecharge = orderRechargeService.selectList(orderRecharge).get(0);
					// 给用户钱包加钱
					customerService.addMoney(order.getCustomerId(), order.getAmountPayable() + orderRecharge.getGiftAmount());
				}
				order.setOrderStatus(OrderConstants.ORDER_STATUS_PAID);
			}

			// 处理代泊
			if (OrderConstants.ORDER_TYPE_PARK.equals(order.getOrderType())) {
				orderMainService.parkPayWithGettingCar(order);
			}

			orderMainDao.update(order);
			//月租产权同步时间发送短信
			if (OrderConstants.ORDER_TYPE_EQUITY.equals(order.getOrderType()) || OrderConstants.ORDER_TYPE_MONTHLY.equals(order.getOrderType())) {
				orderMainService.monAndProPaid(order);
			}
			if ( order.getOrderStatus().equals(OrderConstants.ORDER_STATUS_EXCEPTION)) {
				// 发送短信
				String[] mobiles = {"13917045017", "13482301239"};
				String content = "【口袋停】订单号：" + orderId + "异常，实际支付金额和订单金额不一致";
				SingletonClient.getInstance().sendMessage(mobiles, content, 5);
			}
			message="success";
		}

		return message;
	}
	/**
	 * 洗车订单结束给洗车管理员发短信
	 * @return
	 */	
	public void sendToCarwashManager(OrderMain order) {
		logger.info("------------------给洗车管理员发短信");
		logger.info("------------------给洗车管理员发短信");
		logger.info("------------------给洗车管理员发短信");
		logger.info("------------------给洗车管理员发短信");
		logger.info("------------------给洗车管理员发短信");
			OrderCarwash ocParam = new OrderCarwash();
			ocParam.setOrderId(order.getOrderId());
			List<OrderCarwash> ocList = orderCarwashDao.selectList(ocParam);
			if(null!=ocList&&ocList.size()>0){
				String parkingId = ocList.get(0).getParkingId();
				String carNumner = ocList.get(0).getCarNumber()==null?"":ocList.get(0).getCarNumber();
				String carType = ocList.get(0).getCarType()==null?"":ocList.get(0).getCarType();
				String carTypeName = "默认";
				Dictionary dtParam = new Dictionary();
				dtParam.setDictCode("car_type");
				List<Dictionary> dList = dictionaryDao.selectList(dtParam);
				if(null!=dList&&dList.size()>0){
					for(Dictionary d:dList){
						if(carType.equals(d.getDictValue())){
							carTypeName = d.getDictName();
						}
					}
				}
				/*Parker parkerParam = new Parker();
				parkerParam.setParkingId(parkingId);
				parkerParam.setParkerType("2");
				List<Parker> parkerList = parkerDao.selectList(parkerParam);*/
				SendMessage smeParam = new SendMessage();
	        	smeParam.setOrderType("17");
	        	smeParam.setParkingId(parkingId);
	        	List<SendMessage> sList = sendMessageDao.selectList(smeParam);
				
				//发送短信
				//List<String> mList = new ArrayList<String>();
	        	Set<String> set = new HashSet<String>();
	            for (SendMessage si : sList) {
	                if (!StringUtils.isEmpty(si.getPersonMobile())) {
	                    logger.info("手机号-----------------" + si.getPersonMobile());
	                    set.add(si.getPersonMobile());
	                }
	            }
				logger.info("手机号-----------------"+set.size());
				if(set.size()>0){
					String mobile = "";
					Customer cuParam = new Customer();
					cuParam.setCustomerId(order.getCustomerId());
					List<Customer> cuList = customerService.selectList(cuParam);
					if(null!=cuList&&cuList.size()>0){
						mobile = cuList.get(0).getCustomerMobile();
					}
					String dateStr = DateUtil.date2str(ocList.get(0).getReserveDate(), DateUtil.DATE_FORMAT);
					String	content ="【口袋停】手机"+mobile+"车牌"+carNumner+",车型"+carTypeName+"的客户预约洗车，预约时间为"+dateStr+"，请联系客户确定具体业务时间";
					SingletonClient.getInstance().sendMessage((String[])set.toArray(new String[set.size()]), content, 5);

					String sendToCustomerContent = "【口袋停】亲,恭喜您成功下单,如您的洗车服务人员在30分钟内未能联系您,您可直接联系400-006-2637.谢谢您的支持!";
					SingletonClient.getInstance().sendMessage(new String[]{mobile}, sendToCustomerContent, 5);
				}		
			}
		} 
	
}