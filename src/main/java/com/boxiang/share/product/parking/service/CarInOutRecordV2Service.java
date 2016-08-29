package com.boxiang.share.product.parking.service;

import com.boxiang.share.product.parking.po.CarInOutRecordV2;

import java.util.List;

import com.boxiang.framework.base.page.Page;

public interface CarInOutRecordV2Service {

    List<CarInOutRecordV2> selectList(CarInOutRecordV2 carInOutRecordV2);

    Page<CarInOutRecordV2> queryListPage(Page<CarInOutRecordV2> page);

    CarInOutRecordV2 queryById(Integer id);

    void add(CarInOutRecordV2 carInOutRecordV2);

    void delete(Integer id);

    void update(CarInOutRecordV2 carInOutRecordV2);

    void batchUpdate(List<CarInOutRecordV2> carInOutRecordV2List);

    void manualSyncData(String beginTime, String endTime) throws Exception;

    void recordInOrOutCar() throws Exception;

    List<Object> queryListPageV2(Object page);
}