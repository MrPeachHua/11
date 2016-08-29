package com.boxiang.share.product.order.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.order.po.OrderTemporaryShare;

public interface OrderTemporaryShareDao extends IMybatisBaseDao<OrderTemporaryShare> {
    
    void batchUpdate(List<OrderTemporaryShare> orderTemporaryShareList);
    //根据orderId
    OrderTemporaryShare queryByOrderId(String id);
    //查看凭证
    List<OrderTemporaryShare> queryVoucherPage(Page<OrderTemporaryShare> page);
    OrderTemporaryShare selectByOrderId(Map<String,String> map);
    void updateByOrderId(OrderTemporaryShare orderTemporaryShare);
    List<OrderTemporaryShare> queryAppointment(OrderTemporaryShare orderTemporaryShare);
    List<OrderTemporaryShare> selectTime(OrderTemporaryShare orderTemporaryShare);
    List<OrderTemporaryShare> selectAppByToday(Page<OrderTemporaryShare> page);
    OrderTemporaryShare scanOrderId(OrderTemporaryShare orderTemporaryShare);

    List<OrderTemporaryShare> getCommitToBlueOrder();

    int updateIsPushToBlue(String orderId);
    List<OrderTemporaryShare> queryDate(OrderTemporaryShare orderTemporaryShare);
    List<OrderTemporaryShare> selectCountToday(OrderTemporaryShare orderTemporaryShare);
    void updateParking(OrderTemporaryShare orderTemporaryShare);
}