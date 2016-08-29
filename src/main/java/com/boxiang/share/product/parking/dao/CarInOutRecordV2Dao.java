package com.boxiang.share.product.parking.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.parking.po.CarInOutRecordV2;
import org.apache.ibatis.annotations.Param;

public interface CarInOutRecordV2Dao extends IMybatisBaseDao<CarInOutRecordV2> {
    
    void batchUpdate(List<CarInOutRecordV2> carInOutRecordV2List);

    int insertV2(Map<String, Object> map);

    List<Object> queryListPageV2(Object page);

    List<CarInOutRecordV2> getTempOfflineByDate(Map<String, String> caParam);

    List<CarInOutRecordV2> getTempOfflineByMonth(Map<String, String> caParam);

    List<CarInOutRecordV2> selectCarRecordApp(Page<CarInOutRecordV2> page);

    Integer getByToday(Map<String,Object> param);

    void insertTempIn(Map<String, Object> map);

    void mergeTempInInsert();

    void clearTempIn();

    void insertTempOut(Map<String, Object> map);

    void mergeTempOutInsert();

    void mergeTempOutUpdate();

    void clearTempOut();
}