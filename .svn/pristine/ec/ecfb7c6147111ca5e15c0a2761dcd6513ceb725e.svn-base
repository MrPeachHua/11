package com.boxiang.share.product.order.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.order.po.Order;

public interface OrderDao extends IMybatisBaseDao<Order> {
    
    void batchUpdate(List<Order> orderList);
    int selectCount(String customerId);
}