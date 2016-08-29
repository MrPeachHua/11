package com.boxiang.share.product.parker.service;

import com.boxiang.share.product.parker.po.Parker;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;

public interface ParkerService {

    List<Parker> selectList(Parker parker);

    Page<Parker> queryListPage(Page<Parker> page);

    Parker queryById(String id);

    void add(Parker parker);

    void delete(String id);

    void update(Parker parker);

    void batchUpdate(List<Parker> parkerList);

    String queryParkerById(String customerId, String carNumber, String parkingId);

    String queryFinishParkOrder(String customerId, int pageIndex);

    public Map<String, Object> queryBusyCount(String parkingId);

    String ordercPark(String customerId, String parkingId, String startTime, String endTime, String carNumber, String isContinue, String version) throws Exception;

    void updateState(String parkerId, String state);

    Parker dispatchParker(String parkingId);

    List<Parker> selectParkingIdByParker(Parker parker);

    int selectCount(Parker parker);

    int updateLastOperTime(Parker parker);

    String startPark(String orderId, String imagePath);

    String parked(String orderId, String imagePath, String keyBox) throws Exception;

    String backParking(String orderId, String parkerId);

    String backParked(String orderId, String backPark);

    String gettingCar(String orderId) throws Exception;

    String gotCar(String orderId);

    String parkFinished(String orderId);

    String login(String parkerMobile, String parkerPassword);

    Object queryParkerByOrderId(String orderId);

    List<Parker> selctDaiBoUser(Parker parker);
}