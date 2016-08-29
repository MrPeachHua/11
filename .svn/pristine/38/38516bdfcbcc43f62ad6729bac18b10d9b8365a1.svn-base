package com.boxiang.share.product.order.dao;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.product.order.po.OrderMonthly;

public interface OrderMonthlyDao extends IMybatisBaseDao<OrderMonthly> {
    
    void batchUpdate(List<OrderMonthly> orderMonthlyList);
  //根据orderId
    OrderMonthly queryByOrderId(String id);
    
    List<OrderMonthly>queryListPageForMonthly(Page<OrderMonthly> page);
    List<OrderMain> queryListPageForRecharge(Page<OrderMain> page);
}