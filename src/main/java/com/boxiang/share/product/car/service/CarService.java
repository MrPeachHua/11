package com.boxiang.share.product.car.service;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.car.po.Car;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;

public interface CarService {

	Page<Object> carManage(Page<Object> page);

	List<Car> selectList(Car car);
	
	Page<Car> queryListPage(Page<Car> page);
	
	Car queryById(java.lang.String id);
	
	void add(Car car);

	void delete(java.lang.String id);
	
	void update(Car car);
	
	void batchUpdate(List<Car> carList);
	String insertCar(Car car) throws Exception;
	String queryParkingList(String customerId,HttpServletRequest request);
	String queryParkingList2(String customerId,HttpServletRequest request);
	List<Car> queryCarListWithCustomer(Car car);
	String defaultcar(Car car,String carId,String customerId);
	Car  queryById2(String carId);
	List<Map<String,Object>> queryListPage2(Object page);
}