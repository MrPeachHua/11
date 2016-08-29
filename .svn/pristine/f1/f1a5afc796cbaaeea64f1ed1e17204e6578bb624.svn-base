package com.boxiang.share.product.order.service;

import com.boxiang.share.product.order.po.OrderCarwash;
import com.boxiang.share.product.order.po.OrderMonthly;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;

public interface OrderCarwashService {

	List<OrderCarwash> selectList(OrderCarwash orderCarwash);
	
	Page<OrderCarwash> queryListPage(Page<OrderCarwash> page);
	
	OrderCarwash queryById(Integer id);
	
	void add(OrderCarwash orderCarwash);

	void delete(Integer id);
	
	void update(OrderCarwash orderCarwash);
	
	void batchUpdate(List<OrderCarwash> orderCarwashList);
	String OrdercCarWash(Map<String, Object> param);
	OrderCarwash queryByOrderId(String id);
}