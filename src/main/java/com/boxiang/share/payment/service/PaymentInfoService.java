package com.boxiang.share.payment.service;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.payment.po.PaymentInfo;
import com.boxiang.share.product.order.po.OrderMain;

public interface PaymentInfoService {

	List<PaymentInfo> selectList(PaymentInfo paymentInfo);
	
	Page<PaymentInfo> queryListPage(Page<PaymentInfo> page);
	
	PaymentInfo queryById(java.lang.Integer id);
	
	void add(PaymentInfo paymentInfo);

	void delete(java.lang.Integer id);
	
	void update(PaymentInfo paymentInfo);
	
	void batchUpdate(List<PaymentInfo> paymentInfoList);
	
	/**
	 * @param orderId	订单号
	 * @param tradeNo	支付宝交易号
	 * @param payType	支付类型
	 * @param amountPaid	实付金额
	 * @param gmtPayment	支付时间
	 * @return
	 */
	String updateOrderByPayback(String orderId, String tradeNo, String payType,String amountPaid,String gmtPayment) throws Exception;
    void sendToCarwashManager(OrderMain order);
}