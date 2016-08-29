package com.boxiang.share.product.car.service.impl;

import java.text.ParseException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.boxiang.share.product.customer.dao.CustomerDao;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.car.dao.CarDao;
import com.boxiang.share.product.car.po.Car;
import com.boxiang.share.product.car.service.CarService;

@DataSource(DataSourceEnum.MASTER)
@Service("car")
public class CarServiceImpl implements CarService {
	@Resource(name="carDao")
	private CarDao carDao;
	@Resource(name="customerDao")
	private CustomerDao customerDao;

	@Override
	public Page<Object> carManage(Page<Object> page) {
		page.setResultList(carDao.carManage(page));
		return page;
	}

	@Override
	public List<Car> selectList(Car car) {
		return carDao.selectList(car);
	}

	@Override
	public Page<Car> queryListPage(Page<Car> page) {
	    page.setResultList(carDao.queryListPage(page));
		return page;
	}
	
	@Override
	public Car queryById(java.lang.String id) {
		return carDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(Car car) {
		carDao.insert(car);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.String id) {
		carDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(Car car) {
		carDao.update(car);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Car> carList) {
		carDao.batchUpdate(carList);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String insertCar(Car car) throws Exception{
		String mess=null;
		int i=0;
		Car car1=new Car();
		car1.setCustomerId(car.getCustomerId());
		car1.setCarNumber(car.getCarNumber());
		List<Car> list=carDao.selectList(car1);
		if(list!=null&&list.size()>0){
          mess= ShangAnMessageType.toShangAnJson("1", "你已绑定过此车辆", "car", "");
		}else {
			if(StringUtils.isEmpty(car.getIsAutoPay())){
				car.setIsAutoPay("0");
			}
			i=carDao.insert(car);
			if(i==1){
                Customer customer=customerDao.queryByCustomerId(car.getCustomerId());
				Car car2=new Car();
				car2.setCustomerId(car.getCustomerId());
				car2.setCarNumber(car.getCarNumber());
				List<Car> listCar=carDao.selectList(car2);
				Car car3=null;
				if(listCar!=null&&listCar.size()>0&&customer!=null){
					car3=listCar.get(0);
					car3.setIdentity(customer.getIdentity());
					mess= ShangAnMessageType.toShangAnJson("0", "绑定成功", "car", car3);
				}else {
					throw new Exception("没有这个customer_id");
				}

			}else{
				mess= ShangAnMessageType.toShangAnJson("1", "绑定失败", "car", "");
			}
		}
     return  mess;
	}

	@Override
	public String queryParkingList(String customerId,HttpServletRequest request) {
       String mess=null;
		Map<String,Object> map=new HashMap();
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		List<Map<String, Object>> carList = new ArrayList<Map<String, Object>>();
		List<Car> list=carDao.queryParkingList(customerId);
		if (list != null && list.size() > 0) {
			for (Car car :list){
				Map<String, Object> carMap = new HashMap<String, Object>();
			carMap.put("carId", (null != car.getCarId()) ? car.getCarId() : "");
			carMap.put("carNumber", (null != car.getCarNumber()) ? car.getCarNumber() : "");
			carMap.put("carName", (null !=car.getCarBrand()?car.getCarBrand()+" ":"")+(null !=car.getCarSeries()?car.getCarSeries():"")+" "/*+(null !=car.getIntakeType()?car.getIntakeType():"")*/+(null!=car.getDisplacement()?car.getDisplacement():"")+(null!=car.getIntakeName()?car.getIntakeName()+" ":"")+(null!=car.getStyleYear()?car.getStyleYear()+"年产":""));
			carMap.put("travlledDistance", (null != car.getTravlledDistance()) ? car.getTravlledDistance() : "");
				if( car.getCarUseDate()!=null&& !car.getCarUseDate().equals("")){
					carMap.put("carUseDate", car.getCarUseDate().substring(0,7));
				}else {
					carMap.put("carUseDate", "");
				}
			carMap.put("intakeType", (null !=car.getIntakeType()?car.getIntakeType():""));
			carMap.put("isAutoPay", (null != car.getIsAutoPay()) ? car.getIsAutoPay() : "");
			carMap.put("isDefault", (null != car.getIsDefault()) ? car.getIsDefault() : "");
			carMap.put("brandIcon", (null != car.getBrandIcon()) ? basePath+car.getBrandIcon() : "");
			carList.add(carMap);
		}
		}
		//map.put("liveCars",carList);
		mess= ShangAnMessageType.toShangAnJson("0", "查询成功无数据", "data", carList);
		return mess;
	}

	@Override
	public String queryParkingList2(String customerId,HttpServletRequest request) {
		String mess=null;
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		Map<String, Object> carMap = new HashMap<String, Object>();
		Car car1=new Car();
		car1.setCustomerId(customerId);
		car1.setIsDefault(1);
		List<Car> list=carDao.queryParkingList2(car1);
		if (list != null && list.size() > 0) {
			     Car car =list.get(0);
				carMap.put("carId", (null != car.getCarId()) ? car.getCarId() : "");
				carMap.put("carNumber", (null != car.getCarNumber()) ? car.getCarNumber() : "");
				carMap.put("carName", (null !=car.getCarBrand()?car.getCarBrand()+" ":"")+(null !=car.getCarSeries()?car.getCarSeries()+" ":"")+(null!=car.getDisplacement()?car.getDisplacement()+"T ":"")+(null!=car.getStyleYear()?car.getStyleYear()+"年产":""));
				carMap.put("travlledDistance", (null != car.getTravlledDistance()) ? car.getTravlledDistance() : "");
				if( car.getCarUseDate()!=null&& !car.getCarUseDate().equals("")){
					carMap.put("carUseDate", car.getCarUseDate().substring(0,7));
				}else {
					carMap.put("carUseDate", "");
				}
				carMap.put("isAutoPay", (null != car.getIsAutoPay()) ? car.getIsAutoPay() : "");
				carMap.put("isDefault", (null != car.getIsDefault()) ? car.getIsDefault() : "");
				carMap.put("brandIcon", (null != car.getBrandIcon()) ? basePath+car.getBrandIcon() : "");
			}else {
			Car car2=new Car();
			car2.setCustomerId(customerId);
			//car2.setIsDefault(0);
			List<Car> list2=carDao.queryParkingList2(car2);
			if (list2 != null && list2.size() > 0) {
				Car car =list2.get(0);
				car.setIsDefault(1);
				carDao.update(car);
				carMap.put("carId", (null != car.getCarId()) ? car.getCarId() : "");
				carMap.put("carNumber", (null != car.getCarNumber()) ? car.getCarNumber() : "");
				carMap.put("carName", (null !=car.getCarBrand()?car.getCarBrand()+" ":"")+(null !=car.getCarSeries()?car.getCarSeries()+" ":"")+(null!=car.getDisplacement()?car.getDisplacement()+"T ":"")+(null!=car.getStyleYear()?car.getStyleYear()+"年产":""));
				carMap.put("travlledDistance", (null != car.getTravlledDistance()) ? car.getTravlledDistance() : "");
				if( car.getCarUseDate()!=null&& !car.getCarUseDate().equals("")){
					carMap.put("carUseDate", car.getCarUseDate().substring(0,7));
				}else {
					carMap.put("carUseDate", "");
				}
				carMap.put("isAutoPay", (null != car.getIsAutoPay()) ? car.getIsAutoPay() : "");
				carMap.put("isDefault", (null != car.getIsDefault()) ? car.getIsDefault() : "");
				carMap.put("brandIcon", (null != car.getBrandIcon()) ? car.getBrandIcon() : "");
			}
		}
			mess= ShangAnMessageType.toShangAnJson("0", "查询成功无数据", "data", carMap);
		return mess;
	}

	@Override
	public List<Car> queryCarListWithCustomer(Car car) {
		return carDao.queryCarListWithCustomer(car);
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String defaultcar(Car car,String carId,String customerId) {
		String mess="";
		carDao.update(car);//修改为默认车辆
		List<Car> list=carDao.queryCarList(carId,customerId);//查询除了这个车之外的车
		if(list!=null&&list.size()>0){
			for (Car car1 :list){
				car1.setIsDefault(0);
			}
			carDao.batchUpdate(list);// 将车辆修改为非默认
		}
		mess= ShangAnMessageType.toShangAnJson("0", "success","data","{}");
		return mess;
	}

	@Override
	public Car queryById2(String carId) {
		return carDao.queryById2(carId);
	}

	@Override
	public List<Map<String, Object>> queryListPage2(Object page) {
		return carDao.queryListPage2(page);
	}
}