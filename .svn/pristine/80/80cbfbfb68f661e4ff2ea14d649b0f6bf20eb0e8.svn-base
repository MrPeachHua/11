package com.boxiang.share.product.car.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.car.po.CarMain;
import org.apache.ibatis.annotations.Param;

public interface CarMainDao extends IMybatisBaseDao<CarMain> {
    
    void batchUpdate(List<CarMain> carMainList);
    List<Map<String,Object>> queryTrandName(String carBrand);
    List<Map<String,Object>> queryCarSeries(@Param("carBrand")String carBrand,@Param("trandName")String trandName);
    List<Map<String,Object>> queryDisplacement(CarMain carMain);
    List<Map<String,Object>> queryDisplacement2(CarMain carMain);
    List<Map<String,Object>> queryCarSeries2(String carBrand);
    List<Map<String,Object>> queryDisplacement3(CarMain carMain);
    List<Map<String,Object>> queryDisplacement4(CarMain carMain);
}