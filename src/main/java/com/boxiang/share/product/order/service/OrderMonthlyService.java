package com.boxiang.share.product.order.service;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.order.po.OrderMonthly;

public interface OrderMonthlyService {

	List<OrderMonthly> selectList(OrderMonthly orderMonthly);
	
	Page<OrderMonthly> queryListPage(Page<OrderMonthly> page);
	
	OrderMonthly queryById(java.lang.Integer id);
	OrderMonthly queryByOrderId(String id);
	void add(OrderMonthly orderMonthly);

	void delete(java.lang.Integer id);
	
	void update(OrderMonthly orderMonthly);
	
	void batchUpdate(List<OrderMonthly> orderMonthlyList);
	
	String getMonthlyOrders(Map<String,Object> param);
}