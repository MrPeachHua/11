package com.boxiang.share.product.order.service;

import com.boxiang.share.product.order.po.OrderPark;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.order.po.ParkerOrderVo;

public interface OrderParkService {

	List<OrderPark> selectList(OrderPark orderPark);
	
	Page<OrderPark> queryListPage(Page<OrderPark> page);
	
	OrderPark queryById(java.lang.Integer id);
	OrderPark	queryByOrderId(String id);
	void add(OrderPark orderPark);

	void delete(java.lang.Integer id);
	
	void update(OrderPark orderPark);
	
	void batchUpdate(List<OrderPark> orderParkList);

	int querySingleHourCount(String parkingId);

	Page<Object> queryParkerOrder(Page<Object> page);

	ParkerOrderVo queryParkerOrderById(String orderId);

	int queryTodayCount(String parkingId);

	int queryCountJoinOrderMain(Map<String, Object> params);
	List<Map> queryByOrderIspush();
	int merge(String id);
}