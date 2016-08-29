package com.boxiang.share.product.customer.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.customer.po.CustomerInfo;
import com.boxiang.share.product.customer.po.Customer;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

public interface CustomerService {

	List<Customer> selectList(Customer Customer);

	Customer queryByMobileV2(String customerMobile);

	List<CustomerInfo> selectList(CustomerInfo customer);
	Page<Customer> queryListPage(Page<Customer> page);
	
	Customer queryById(java.lang.String id);
	 Customer queryByCustomerId(String id);
	
	void add(Customer Customer);

	void delete(java.lang.String id);
	
	void update(Customer Customer);
	
	void batchUpdate(List<Customer> CustomerList);
	
	String insertCustomerByMobleAndPwd(Customer customer) throws Exception;
	Customer queryByWxId(Customer customer);
	List<Customer> queryByMobile(String customerMobile);
	/**
	 * 支付宝回调
	 * @param villageId
	 * @return
	 */
	String payBackTempShare(String order_id,String trade_no,String trade_status,String buyer_email,String pay_Type,String total_Fee,String user_info);
	String payBackTemp(String order_id,String trade_no,String trade_status,String buyer_email,String pay_Type,String total_Fee,String user_info);
	String postMonthlyAfterPay(String order_id,String trade_no,String trade_status,String buyer_email,String pay_Type,String total_Fee,String user_info);
	String postEquityAfterPay(String order_id, String trade_no,String trade_status, String buyer_email, String pay_Type,String total_Fee,String user_info);
	String postCardAfterPay(String order_id, String trade_no,String trade_status, String buyer_email, String pay_Type,String total_Fee,String user_info);

	@Transactional(propagation= Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	String postParkAfterPay(String order_id, String trade_no, String trade_status, String buyer_email, String pay_Type, String total_Fee, String user_info);

	String postCarwsahAfterPay(String order_id, String trade_no,String trade_status, String buyer_email, String pay_Type,String total_Fee,String user_info);
	Page<Customer> queryByC(Page<Customer> page);
	List<Customer> exportByC(Customer customer);
	//用户登录
	String login(String mobile,String password);
	Page<Customer> selectByChannel(Page<Customer> page);
	void submitBluecard(String orderId,String tradeNo);
	String getMoney(String customerId);
	String getPayPassWord(String customerId);
	String initPayPassWord(String customerId,String payPassword);
	String resetPayPassword(String customerId,String payPassword);
	void addMoney(String customerId, int money);

	int subtractMoney(String customerId, Integer amountPaid);

	void setThirdId(Customer customer, String quickId, String quickType);

	//第三方登陆
	String loginByOther(String quickId,String quickType,String reg_phone);
   //第三方绑定
	String bondByOther(String quickType,String password,String mobile ,String quickId);
	/**
	 * 判断手机是否存在
	 * @param customerMobile
	 * @return
	 */
	String queryPhoneCode(String customerMobile);

	public String getRedeemCode(String customerId) throws Exception;

	public Customer queryByRedeemCode(String redeemCode);
	
	String queryByOpenId(String openId);
	
	//用户登录
    String wxLogin(String mobile,String wxpayOpenid);

	void updateLastLoginTime(String customerId, Date lastLoginTime);
	void updateLastLoginTime2(String customerId, Date lastLoginTime,String lastLoginMachine,String appVersion,String lastLoginSys);
	Map paramsFilter(Customer customer);
	List<Map<String,Object>>  queryListPage2(Object page);
	Customer loginByOtherV2(String quickId, String quickType);
	String getInfo(String customerId);
	String updateCustomerInfo(Customer customer,HttpServletRequest request);
}