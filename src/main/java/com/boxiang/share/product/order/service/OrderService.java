package com.boxiang.share.product.order.service;

import com.boxiang.share.product.order.po.Order;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface OrderService {

	List<Order> selectList(Order order);
	
	Page<Order> queryListPage(Page<Order> page);
	
	Order queryById(String id);
	
	void add(Order order);

	void delete(String id);
	
	void update(Order order);
	
	void batchUpdate(List<Order> orderList);
}