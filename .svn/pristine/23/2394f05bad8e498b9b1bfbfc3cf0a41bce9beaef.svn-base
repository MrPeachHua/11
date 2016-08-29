package com.boxiang.share.product.carlife.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.carlife.po.CarlifeSrvBilling;
import com.boxiang.share.product.parking.po.Parking;

public interface CarlifeSrvBillingDao extends IMybatisBaseDao<CarlifeSrvBilling> {
    
    void batchUpdate(List<CarlifeSrvBilling> carlifeSrvBillingList);
    
    List<CarlifeSrvBilling> selectSrvInfo(Map<String,String> param);
    List<Parking> selectListParking(String srvId);

  
    
}