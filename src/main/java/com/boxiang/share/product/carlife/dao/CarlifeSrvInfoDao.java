package com.boxiang.share.product.carlife.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.carlife.po.CarlifeSrvInfo;

public interface CarlifeSrvInfoDao extends IMybatisBaseDao<CarlifeSrvInfo> {
    
    void batchUpdate(List<CarlifeSrvInfo> carlifeSrvInfoList);
    /**
     *根据手机号查询用户服务 
     */
    List<CarlifeSrvInfo> selectCarlifeCsrByMobile(Map<String,String> param);
   int querySortMax();
   List<CarlifeSrvInfo> queryCharge(String parkingId);
}