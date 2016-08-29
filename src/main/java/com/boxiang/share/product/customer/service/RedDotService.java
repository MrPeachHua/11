package com.boxiang.share.product.customer.service;

import com.boxiang.share.product.customer.po.RedDot;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface RedDotService {

	List<RedDot> selectList(RedDot redDot);
	
	Page<RedDot> queryListPage(Page<RedDot> page);
	
	RedDot queryById(Integer id);
	
	void add(RedDot redDot);

	void delete(Integer id);
	
	void update(RedDot redDot);
	
	void batchUpdate(List<RedDot> redDotList);

	/**
	 * 开启小红点
	 * @param customerId 用户id
	 * @param type 类型：1：优惠券
	 * @param newCount 新的记录，未浏览的数量
	 * @throws Exception
	 */
	void openRedDot(String customerId, int type, int newCount) throws Exception;

	/**
	 * 关闭小红点
	 * @param customerId 用户id
	 * @param type 类型：1：优惠券
	 * @throws Exception
	 */
	void closeRedDot(String customerId, int type) throws Exception;

	/**
	 * 查询用户的小红点信息
	 * @param customerId
	 * @param type 类型：1：优惠券
	 * @return
	 */
	RedDot queryRedDot(String customerId, int type) throws Exception;
}