package com.boxiang.share.product.order.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.boxiang.share.product.order.po.ParkerOrderVo;
import com.boxiang.share.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.order.dao.OrderParkDao;
import com.boxiang.share.product.order.po.OrderPark;
import com.boxiang.share.product.order.service.OrderParkService;

@DataSource(DataSourceEnum.MASTER)
@Service("orderPark")
public class OrderParkServiceImpl implements OrderParkService {
    @Resource(name = "orderParkDao")
    private OrderParkDao orderParkDao;

    @Override
    public List<OrderPark> selectList(OrderPark orderPark) {
        return orderParkDao.selectList(orderPark);
    }

    @Override
    public Page<OrderPark> queryListPage(Page<OrderPark> page) {
        page.setResultList(orderParkDao.queryListPage(page));
        return page;
    }

    @Override
    public OrderPark queryById(java.lang.Integer id) {
        return orderParkDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(OrderPark orderPark) {
        orderParkDao.insert(orderPark);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(java.lang.Integer id) {
        orderParkDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(OrderPark orderPark) {
        orderParkDao.update(orderPark);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<OrderPark> orderParkList) {
        orderParkDao.batchUpdate(orderParkList);
    }

    @Override
    public OrderPark queryByOrderId(String id) {
        return orderParkDao.queryByOrderId(id);
    }

    @Override
    public int querySingleHourCount(String parkingId) {
        Date end = new Date();
        String endTime = DateUtil.date2str(end, DateUtil.DATETIME_FORMAT); // 当前时间，结束时间
        Date start = DateUtil.getPreOrNextHour(end, -1);
        String startTime = DateUtil.date2str(start, DateUtil.DATETIME_FORMAT); // 开始时间
        Map<String, Object> params = new HashMap<>(3);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("parkingId", parkingId);
        return orderParkDao.queryTodayCount(params);
    }

    @Override
    public Page<Object> queryParkerOrder(Page<Object> page) {
        page.setResultList(orderParkDao.queryParkerOrder(page));
        return page;
    }

    @Override
    public ParkerOrderVo queryParkerOrderById(String orderId) {
        return orderParkDao.queryParkerOrderById(orderId);
    }

    @Override
    public int queryTodayCount(String parkingId) {
        Map<String, Object> params = new HashMap<>(3);
        String now = DateUtil.getCurrDate(DateUtil.getCurrDate(DateUtil.DATE_FORMAT));
        String startTime = now + " 00:00:00";
        String endTime = now + " 23:59:59";
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("parkingId", parkingId);
        return orderParkDao.queryTodayCount(params);
    }

    @Override
    public int queryCountJoinOrderMain(Map<String, Object> params) {
        return orderParkDao.queryCountJoinOrderMain(params);
    }

    @Override
    public List<Map> queryByOrderIspush() {
   long nowTimeStamp= System.currentTimeMillis()/1000;
        return orderParkDao.queryByOrderIspush(nowTimeStamp);
    }

    @Override
    public int merge(String id) {
        return orderParkDao.merge(id);
    }

}