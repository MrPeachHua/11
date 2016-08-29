package com.boxiang.share.product.order.service;

import com.boxiang.share.product.order.po.OrderEquity;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;

public interface OrderEquityService {

	List<OrderEquity> selectList(OrderEquity orderEquity);
	
	Page<OrderEquity> queryListPage(Page<OrderEquity> page);
	
	OrderEquity queryById(java.lang.Integer id);
	OrderEquity queryByOrderId(String id);
	void add(OrderEquity orderEquity);

	void delete(java.lang.Integer id);
	
	void update(OrderEquity orderEquity);
	
	void batchUpdate(List<OrderEquity> orderEquityList);
	
	String getEquityOrders(Map<String,Object> param);
}