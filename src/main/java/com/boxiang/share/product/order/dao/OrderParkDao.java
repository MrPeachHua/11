package com.boxiang.share.product.order.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.order.po.OrderPark;
import com.boxiang.share.product.order.po.ParkerOrderVo;

public interface OrderParkDao extends IMybatisBaseDao<OrderPark> {

    void batchUpdate(List<OrderPark> orderParkList);

    //根据orderId
    OrderPark queryByOrderId(String id);

    List<Object> queryParkerOrder(Page<Object> page);

    ParkerOrderVo queryParkerOrderById(String orderId);

    int queryTodayCount(Map<String, Object> params);

    int queryCountJoinOrderMain(Map<String, Object> params);
    List<Map> queryByOrderIspush(long nowTimeStamp);
    int merge(String id);
}