package com.boxiang.share.product.car.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.car.po.CarUpkeep;

public interface CarUpkeepDao extends IMybatisBaseDao<CarUpkeep> {
    
    void batchUpdate(List<CarUpkeep> carUpkeepList);

    List<Map<String,Object>> gainupkeep(Map<String, Object> params);
}