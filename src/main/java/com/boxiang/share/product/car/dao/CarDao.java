package com.boxiang.share.product.car.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.car.po.Car;
import org.apache.ibatis.annotations.Param;


public interface CarDao extends IMybatisBaseDao<Car> {
    
    void batchUpdate(List<Car> tCarList);

    List<Car> queryCarListWithCustomer(Car car);
    List<Car> queryParkingList(String customerId);
    List<Car> queryParkingList2(Car car);
    List<Object> carManage(Page<?> page);
    List<Car> queryCarList(@Param("carId") String carId,@Param("customerId")String customerId);
    Car  queryById2(String carId);
    List<Map<String,Object>> queryListPage2(Object page);
}