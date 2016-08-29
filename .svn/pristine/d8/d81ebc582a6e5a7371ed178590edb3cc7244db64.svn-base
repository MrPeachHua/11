package com.boxiang.share.product.order.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.order.po.OrderTemporaryShare;

public interface OrderTemporaryShareService {

	List<OrderTemporaryShare> selectList(OrderTemporaryShare orderTemporaryShare);
	
	Page<OrderTemporaryShare> queryListPage(Page<OrderTemporaryShare> page);
	
	OrderTemporaryShare queryById(java.lang.Integer id);
	OrderTemporaryShare queryByOrderId(String id); 
	void add(OrderTemporaryShare orderTemporaryShare);

	void delete(java.lang.Integer id);
	
	void update(OrderTemporaryShare orderTemporaryShare);
	
	void batchUpdate(List<OrderTemporaryShare> orderTemporaryShareList);
	
	String queryVoucherPage(Page<OrderTemporaryShare> page);
	public OrderTemporaryShare selectByOrderId(Map<String,String> map);
	public void updateByOrderId(OrderTemporaryShare orderTemporaryShare);
	String queryAppointment(String parkingId,String carNumber,String appointmentDate);
	public List<OrderTemporaryShare> selectTime(OrderTemporaryShare orderTemporaryShare);
	String selectAppByToday(Page<OrderTemporaryShare> page);
	public OrderTemporaryShare scanOrderId(OrderTemporaryShare orderTemporaryShare);

	boolean timeTaskForCommitToBlue(int minutes) throws Exception;
	List<OrderTemporaryShare> queryDate(OrderTemporaryShare orderTemporaryShare);
	List<OrderTemporaryShare> selectCountToday(OrderTemporaryShare orderTemporaryShare);
	void updateParking(OrderTemporaryShare orderTemporaryShare);
}