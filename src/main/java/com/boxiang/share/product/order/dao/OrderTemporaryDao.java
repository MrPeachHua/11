package com.boxiang.share.product.order.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.order.po.OrderTemporary;

public interface OrderTemporaryDao extends IMybatisBaseDao<OrderTemporary> {
    
    void batchUpdate(List<OrderTemporary> orderTemporaryList);
    //根据orderId
    OrderTemporary queryByOrderId(String id);
}