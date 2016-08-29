package com.boxiang.share.product.order.service;

import com.boxiang.share.product.order.po.OrderRecharge;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;

public interface OrderRechargeService {

	List<OrderRecharge> selectList(OrderRecharge orderRecharge);
	
	Page<OrderRecharge> queryListPage(Page<OrderRecharge> page);
	
	OrderRecharge queryById(Integer id);
	
	void add(OrderRecharge orderRecharge);

	void delete(Integer id);
	
	void update(OrderRecharge orderRecharge);
	
	void batchUpdate(List<OrderRecharge> orderRechargeList);

	String createRechargeOrder(String customerId, String amountPayable, String useInfo, String payType,String version);
	public String getRechargeList(Map<String,Object> param);


	String getPaidMessageByRecharge(String orderId, String orderType) throws Exception;

	public String getConsumList(Map<String,Object> param);

}