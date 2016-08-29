package com.boxiang.share.product.order.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.order.po.OrderCarwash;
import com.boxiang.share.product.order.po.OrderMonthly;

public interface OrderCarwashDao extends IMybatisBaseDao<OrderCarwash> {
    
    void batchUpdate(List<OrderCarwash> orderCarwashList);
    int selectCountByNow(OrderCarwash orderCarwash);
  //根据orderId
    OrderCarwash queryByOrderId(String id);
}