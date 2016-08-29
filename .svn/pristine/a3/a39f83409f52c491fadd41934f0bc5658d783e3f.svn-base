package com.boxiang.share.product.order.dao;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.order.po.OrderEquity;

public interface OrderEquityDao extends IMybatisBaseDao<OrderEquity> {
    
    void batchUpdate(List<OrderEquity> orderEquityList);
  //根据orderId
    OrderEquity queryByOrderId(String id);
    
    List<OrderEquity>queryListPageForEquity(Page<OrderEquity> page);
}