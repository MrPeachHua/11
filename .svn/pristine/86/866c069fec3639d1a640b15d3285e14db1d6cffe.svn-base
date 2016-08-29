package com.boxiang.share.product.order.service;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.order.po.OrderTemporary;

public interface OrderTemporaryService {

	List<OrderTemporary> selectList(OrderTemporary orderTemporary);
	
	Page<OrderTemporary> queryListPage(Page<OrderTemporary> page);
	
	OrderTemporary queryById(java.lang.Integer id);
	OrderTemporary queryByOrderId(String id); 
	void add(OrderTemporary orderTemporary);

	void delete(java.lang.Integer id);
	
	void update(OrderTemporary orderTemporary);
	
	void batchUpdate(List<OrderTemporary> orderTemporaryList);
	
	String getCarlist(String customerId,String carNumber);
}