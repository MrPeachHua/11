package com.boxiang.share.product.order.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.order.dao.OrderEquityDao;
import com.boxiang.share.product.order.po.OrderEquity;
import com.boxiang.share.product.order.service.OrderEquityService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;

@DataSource(DataSourceEnum.MASTER)
@Service("orderEquity")
public class OrderEquityServiceImpl implements OrderEquityService {
	private static final Logger logger = Logger.getLogger(OrderEquityServiceImpl.class);
	@Resource(name="orderEquityDao")
	private OrderEquityDao orderEquityDao;
	
	@Override
	public List<OrderEquity> selectList(OrderEquity orderEquity) {
		return orderEquityDao.selectList(orderEquity);
	}

	@Override
	public Page<OrderEquity> queryListPage(Page<OrderEquity> page) {
	    page.setResultList(orderEquityDao.queryListPage(page));
		return page;
	}
	
	@Override
	public OrderEquity queryById(java.lang.Integer id) {
		return orderEquityDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(OrderEquity orderEquity) {
		orderEquityDao.insert(orderEquity);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		orderEquityDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(OrderEquity orderEquity) {
		orderEquityDao.update(orderEquity);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<OrderEquity> orderEquityList) {
		orderEquityDao.batchUpdate(orderEquityList);
	}

@Override
public OrderEquity queryByOrderId(String id) {
	return orderEquityDao.queryByOrderId(id);
}

/**
 * 获取月租产权订单列表
 * @param param
 * @return
 */
public String getEquityOrders(Map<String,Object> param){
	String customerId = (String) param.get("customerId");
	String orderType = (String) param.get("orderType");
	String orderStatus = (String) param.get("orderStatus");
	String pageIndexStr = (String) param.get("pageIndex");
	String mess = null;
	long pageIndex = 1;
	if(null!=customerId&&null!=orderType&&null!=orderStatus&&null!=pageIndexStr){
		pageIndex = (long)Integer.parseInt(pageIndexStr);
		Page<OrderEquity> page = new Page<OrderEquity>();
		page.setCurrentPage(pageIndex);
		page.setPageSize(Page.PAGE_SIZE_10);
		page.getParams().put("orderStatus", orderStatus);
		page.getParams().put("customerId", customerId);
		List<OrderEquity> equityList = new ArrayList<OrderEquity>();
		List<OrderEquity> equityListNull = new ArrayList<OrderEquity>();
		try {
			equityList = orderEquityDao.queryListPageForEquity(page);
			List <Map<String,Object>> orderList = new ArrayList <Map<String,Object>>();
			if(null!=equityList&&equityList.size()>0){
				for(OrderEquity mo:equityList){
					Map<String,Object> orderMap = new HashMap<String,Object>();
					orderMap.put("orderId",(null!=mo.getOrderId())?mo.getOrderId():"");
					orderMap.put("orderType",(null!=mo.getOrderType())?mo.getOrderType():"");
					orderMap.put("orderTypeName","产权");
					orderMap.put("orderStatus",(null!=mo.getOrderStatus())?mo.getOrderStatus():"");
					orderMap.put("orderStatusName ","未付款");
					orderMap.put("carNumber",(null!=mo.getCarNumber())?mo.getCarNumber():"");
					orderMap.put("parkingId",(null!=mo.getParkingId())?mo.getParkingId():"");
					orderMap.put("parkingName",(null!=mo.getParkingName())?mo.getParkingName():"");
					orderMap.put("beginDate",(null!=mo.getBeginDate())?DateUtil.date2str(mo.getBeginDate(), DateUtil.DATETIME_FORMAT):"");
					String endDate = null;
					logger.info("--------------------------");
					if(null!=mo.getBeginDate()&&null!=mo.getMonthNum()){
						Date date = null;
						logger.info(mo.getBeginDate()+"----------------");
						Calendar cl = Calendar.getInstance(); 
						cl.setTime(mo.getBeginDate());  
					    cl.add(Calendar.MONTH, mo.getMonthNum());  
					    logger.info("---------dsfdsf----"+cl);
					    date = cl.getTime();
					    endDate = DateUtil.date2str(date, DateUtil.DATETIME_FORMAT);
					    logger.info("--------------------------"+cl);
					    logger.info("--------------------------"+endDate);
					}  
					orderMap.put("endDate",(null!=endDate)?endDate:"");
					Integer price =null;
					if(null!=mo.getPrice()&&null!=mo.getMonthNum()){
						price = mo.getPrice();
					}
					orderMap.put("price",(null!=price)?price:"");
					orderMap.put("customer",(null!=mo.getCustomer())?mo.getCustomer():"");
					orderMap.put("amountPaid",(null!=mo.getAmountPaid())?Integer.parseInt(mo.getAmountPaid())/100:"");
					orderMap.put("amountPayable",(null!=mo.getAmountPayable())?Integer.parseInt(mo.getAmountPaid())/100:"");
					orderMap.put("amountDiscount",(null!=mo.getAmountDiscount())?Integer.parseInt(mo.getAmountDiscount())/100:"");
					orderList.add(orderMap);
				}
				mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "order",orderList);
			}else{
				mess = ShangAnMessageType.toShangAnJson("1", "查询成功无数据", "order",equityListNull);
			}
			
		} catch (Exception e) {
			logger.error("查询失败", e);
			mess = ShangAnMessageType.toShangAnJson("2", "查询失败", "order", equityListNull);
		}
		
		
	}else{
		mess = ShangAnMessageType.toShangAnJson("2", "查询失败", "order", "");
	}
	
	
	
	return mess;
}
}