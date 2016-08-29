package com.boxiang.share.product.carlife.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.carlife.po.CarlifeSrvNewtag;

public interface CarlifeSrvNewtagDao extends IMybatisBaseDao<CarlifeSrvNewtag> {
    
    void batchUpdate(List<CarlifeSrvNewtag> carlifeSrvNewtagList);
    
    List<CarlifeSrvNewtag> selectNewFlagByMs(Map<String,String> param);
}