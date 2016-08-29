package com.boxiang.share.product.parking.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.parking.po.CarInOutRecord;

public interface CarInOutRecordDao extends IMybatisBaseDao<CarInOutRecord> {
    
    void batchUpdate(List<CarInOutRecord> tCarInOutRecordList);
    List<CarInOutRecord> selectCarRecord(Page<CarInOutRecord> page);
    List<CarInOutRecord> selectLastOne(CarInOutRecord carInOutRecord);
    CarInOutRecord selectParkingName(CarInOutRecord carInOutRecord);
    List<CarInOutRecord> queryCarRecordList(CarInOutRecord carInOutRecord);
    List<CarInOutRecord> getTempOfflineByDate(Map<String,String> param);
    List<CarInOutRecord> getTempOfflineByMonth(Map<String,String> param);
    List<CarInOutRecord> selectCarRecordApp(Page<CarInOutRecord> page);
}