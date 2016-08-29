package com.boxiang.share.product.carlife.service;


import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.carlife.po.OrderRefuel;

public interface OrderRefuelService {

	List<OrderRefuel> selectList(OrderRefuel orderRefuel);
	String getRefuelList(String customerId, String pageIndex);
	Page<OrderRefuel> queryListPage(Page<OrderRefuel> page);
	
	OrderRefuel queryById(java.lang.Integer id);
	OrderRefuel queryByOrderId(String id);
	void add(OrderRefuel orderRefuel);

	void delete(java.lang.Integer id);
	
	void update(OrderRefuel orderRefuel);
	
	void batchUpdate(List<OrderRefuel> orderRefuelList);
	
	/**
	 * 查询支付完成的加油卡订单
	 * @param param
	 * @return
	 */
    List<OrderRefuel> selectListForQuartz(Map<String,Object> param);

	Map queryRefuelByOrderId(String orderId);

	/**
	 * 调用聚合API充值加油卡
	 * @param orderId 订单ID
	 * @return
	 */
	public String rechargeByJuheAPI(String orderId) throws Exception;

	/**
	 * 调用聚合API查询订单信息
	 * @param orderId
	 * @return
	 */
	public String getOrderStateByJuheAPI(String orderId) throws Exception;
}