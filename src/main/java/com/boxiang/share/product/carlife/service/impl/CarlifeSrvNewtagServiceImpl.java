package com.boxiang.share.product.carlife.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.carlife.dao.CarlifeSrvNewtagDao;
import com.boxiang.share.product.carlife.po.CarlifeSrvNewtag;
import com.boxiang.share.product.carlife.service.CarlifeSrvNewtagService;
import com.boxiang.share.product.customer.dao.CustomerDao;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.utils.ShangAnMessageType;

@DataSource(DataSourceEnum.MASTER)
@Service("carlifeSrvNewtag")
public class CarlifeSrvNewtagServiceImpl implements CarlifeSrvNewtagService {
	@Resource(name="carlifeSrvNewtagDao")
	private CarlifeSrvNewtagDao carlifeSrvNewtagDao;
	
	@Resource(name="customerDao")
	private CustomerDao customerDao;
	
	@Override
	public List<CarlifeSrvNewtag> selectList(CarlifeSrvNewtag tCarlifeSrvNewtag) {
		return carlifeSrvNewtagDao.selectList(tCarlifeSrvNewtag);
	}

	@Override
	public Page<CarlifeSrvNewtag> queryListPage(Page<CarlifeSrvNewtag> page) {
	    page.setResultList(carlifeSrvNewtagDao.queryListPage(page));
		return page;
	}
	
	@Override
	public CarlifeSrvNewtag queryById(java.lang.Integer id) {
		return carlifeSrvNewtagDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(CarlifeSrvNewtag tCarlifeSrvNewtag) {
		carlifeSrvNewtagDao.insert(tCarlifeSrvNewtag);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		carlifeSrvNewtagDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(CarlifeSrvNewtag tCarlifeSrvNewtag) {
		carlifeSrvNewtagDao.update(tCarlifeSrvNewtag);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<CarlifeSrvNewtag> tCarlifeSrvNewtagList) {
		carlifeSrvNewtagDao.batchUpdate(tCarlifeSrvNewtagList);
	}
  /**
   * 更新newFlag
   */
  	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
  	public String updateNewFlag(HttpServletRequest request, HttpServletResponse response,String mobile,String srvId){
	  String mess="";
	  String customer_id = "";
	  Customer customer = new Customer();
	  customer.setCustomerMobile(mobile);
	  List<Customer> customerList = null;
	  List <CarlifeSrvNewtag> list = null;
	  try {
		 customerList = customerDao.selectList(customer);
		} catch (Exception e) {
			// TODO: handle exception
			mess = ShangAnMessageType.operateToJson("2","selecterro");
			return mess;
		}
	 
	  if(null!=customerList&&customerList.size()>0){
		  customer_id = customerList.get(0).getCustomerId();
	  }
	  CarlifeSrvNewtag carlifeSrvNewtag =new CarlifeSrvNewtag();
	  carlifeSrvNewtag.setIsUsed(Constants.TRUE);
	  Map<String,String> param =new HashMap<String,String>(); 
	  param.put("customer_id", customer_id);
	  param.put("srvId", srvId);
	  try {
		  list = carlifeSrvNewtagDao.selectNewFlagByMs(param);
		} catch (Exception e) {
			// TODO: handle exception
			mess = ShangAnMessageType.operateToJson("2","selecterro");
			return mess;
		}
	  
	  if(null!=list&&list.size()>0){//有记录的话置为1
		  CarlifeSrvNewtag param1 = new CarlifeSrvNewtag();
		  param1 = list.get(0);
		  param1.setFlag(Constants.TRUE);
		  try {
			  carlifeSrvNewtagDao.update(param1);
			} catch (Exception e) {
				// TODO: handle exception
			mess = ShangAnMessageType.operateToJson("2","updateerro");
			return mess;
			}
		  
	  }else{//无记录时添加记录
		  CarlifeSrvNewtag carfn = new CarlifeSrvNewtag();
		  if(null!=srvId&&!"".equals(srvId)){
			  carfn.setSrvId(Integer.parseInt(srvId));
		  }
		  carfn.setCustomerId(customer_id);
		  carfn.setCreateDate(new Date());
		  carfn.setFlag(Constants.TRUE);
		  carfn.setIsUsed(Constants.TRUE);
		  carfn.setModifyDate(new Date());
		  carfn.setCreateor("");
		  carfn.setModified("");
		  try {
			  carlifeSrvNewtagDao.insert(carfn);
			} catch (Exception e) {
				// TODO: handle exception
				mess = ShangAnMessageType.operateToJson("2","inserterro");
				return mess;
			}
		 
	  }	  
	  mess = ShangAnMessageType.operateToJson("0", "sucess");
	  return mess;
  }
}