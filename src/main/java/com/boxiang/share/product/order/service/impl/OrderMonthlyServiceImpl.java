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
import com.boxiang.share.product.order.dao.OrderMonthlyDao;
import com.boxiang.share.product.order.po.OrderMonthly;
import com.boxiang.share.product.order.service.OrderMonthlyService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;

@DataSource(DataSourceEnum.MASTER)
@Service("orderMonthly")
public class OrderMonthlyServiceImpl implements OrderMonthlyService {
	private static final Logger logger = Logger.getLogger(OrderMonthlyServiceImpl.class);
	@Resource(name="orderMonthlyDao")
	private OrderMonthlyDao orderMonthlyDao;
	@Override
	public List<OrderMonthly> selectList(OrderMonthly orderMonthly) {
		return orderMonthlyDao.selectList(orderMonthly);
	}

	@Override
	public Page<OrderMonthly> queryListPage(Page<OrderMonthly> page) {
	    page.setResultList(orderMonthlyDao.queryListPage(page));
		return page;
	}
	
	@Override
	public OrderMonthly queryById(java.lang.Integer id) {
		return orderMonthlyDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(OrderMonthly orderMonthly) {
		orderMonthlyDao.insert(orderMonthly);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		orderMonthlyDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(OrderMonthly orderMonthly) {
		orderMonthlyDao.update(orderMonthly);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<OrderMonthly> orderMonthlyList) {
		orderMonthlyDao.batchUpdate(orderMonthlyList);
	}

@Override
public OrderMonthly queryByOrderId(String id) {
	return orderMonthlyDao.queryByOrderId(id);
}	
	/**
	 * 获取月租产权订单列表
	 * @param param
	 * @return
	 */
	public String getMonthlyOrders(Map<String,Object> param){
		String customerId = (String) param.get("customerId");
		String orderType = (String) param.get("orderType");
		String orderStatus = (String) param.get("orderStatus");
		String pageIndexStr = (String) param.get("pageIndex");
		String mess = null;
		long pageIndex = 1;
		if(null!=customerId&&null!=orderType&&null!=orderStatus&&null!=pageIndexStr){
			pageIndex = (long)Integer.parseInt(pageIndexStr);
			Page<OrderMonthly> page = new Page<OrderMonthly>();
			page.setCurrentPage(pageIndex);
			page.setPageSize(Page.PAGE_SIZE_10);
			page.getParams().put("orderStatus", orderStatus);
			page.getParams().put("customerId", customerId);
			List<OrderMonthly> monthlyList = new ArrayList<OrderMonthly>();
			List<OrderMonthly> monthlyListNull = new ArrayList<OrderMonthly>();
			try {
				monthlyList = orderMonthlyDao.queryListPageForMonthly(page);
				
				List <Map<String,Object>> orderList = new ArrayList <Map<String,Object>>();
				if(null!=monthlyList&&monthlyList.size()>0){
					for(OrderMonthly mo:monthlyList){
						Map<String,Object> orderMap = new HashMap<String,Object>();
						orderMap.put("orderId",(null!=mo.getOrderId())?mo.getOrderId():"");
						orderMap.put("orderType",(null!=mo.getOrderType())?mo.getOrderType():"");
						orderMap.put("orderTypeName","月租");
						orderMap.put("orderStatus",(null!=mo.getOrderStatus())?mo.getOrderStatus():"");
						orderMap.put("orderStatusName ","未付款");
						orderMap.put("carNumber",(null!=mo.getCarNumber())?mo.getCarNumber():"");
						orderMap.put("parkingId",(null!=mo.getParkingId())?mo.getParkingId():"");
						orderMap.put("parkingName",(null!=mo.getParkingName())?mo.getParkingName():"");
						orderMap.put("beginDate",(null!=mo.getBeginDate())?DateUtil.date2str(mo.getBeginDate(), DateUtil.DATETIME_FORMAT):"");
						String endDate = null;
						if(null!=mo.getBeginDate()&&null!=mo.getMonthNum()){
							Date date = null;
							Calendar cl = Calendar.getInstance(); 
							cl.setTime(mo.getBeginDate());  
						    cl.add(Calendar.MONTH, mo.getMonthNum());  
						    date = cl.getTime();
						    endDate = DateUtil.date2str(date, DateUtil.DATETIME_FORMAT);
						}  
						orderMap.put("endDate",(null!=endDate)?endDate:"");
						Integer  price =null;
						if(null!=mo.getPrice()&&null!=mo.getMonthNum()){
							price = mo.getPrice()/100;
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
					mess = ShangAnMessageType.toShangAnJson("1", "查询成功无数据", "order",monthlyListNull);
				}
				
			} catch (Exception e) {
				logger.error("查询失败", e);
				mess = ShangAnMessageType.toShangAnJson("2", "查询失败", "order", "");
			}
			
			
		}else{
			mess = ShangAnMessageType.toShangAnJson("2", "查询失败", "order", "");
		}
		
		
		
		return mess;
	}
}