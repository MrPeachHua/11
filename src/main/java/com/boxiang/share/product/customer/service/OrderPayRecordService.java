package com.boxiang.share.product.customer.service;

import com.boxiang.share.product.customer.po.OrderPayRecord;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface OrderPayRecordService {

	List<OrderPayRecord> selectList(OrderPayRecord orderPayRecord);
	
	Page<OrderPayRecord> queryListPage(Page<OrderPayRecord> page);
	
	OrderPayRecord queryById(java.lang.Integer id);
	
	void add(OrderPayRecord orderPayRecord);

	void delete(java.lang.Integer id);
	
	void update(OrderPayRecord orderPayRecord);
	
	void batchUpdate(List<OrderPayRecord> orderPayRecordList);
}