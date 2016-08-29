package com.boxiang.share.product.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.order.dao.OrderDao;
import com.boxiang.share.product.order.po.Order;
import com.boxiang.share.product.order.service.OrderService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("order")
public class OrderServiceImpl implements OrderService {
	@Resource(name="orderDao")
	private OrderDao orderDao;
	
	@Override
	public List<Order> selectList(Order order) {
		return orderDao.selectList(order);
	}

	@Override
	public Page<Order> queryListPage(Page<Order> page) {
	    page.setResultList(orderDao.queryListPage(page));
		return page;
	}
	
	@Override
	public Order queryById(String id) {
		return orderDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(Order order) {
		orderDao.insert(order);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(String id) {
		orderDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(Order order) {
		orderDao.update(order);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Order> orderList) {
		orderDao.batchUpdate(orderList);
	}
}