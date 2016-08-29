package com.boxiang.share.product.order.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.order.po.OrderRecharge;

public interface OrderRechargeDao extends IMybatisBaseDao<OrderRecharge> {
    
    void batchUpdate(List<OrderRecharge> orderRechargeList);
}