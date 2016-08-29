package com.boxiang.share.product.carlife.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.boxiang.share.product.parking.po.Parking;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.carlife.dao.CarlifeSrvBillingDao;
import com.boxiang.share.product.carlife.po.CarlifeSrvBilling;
import com.boxiang.share.product.carlife.service.CarlifeSrvBillingService;
import com.boxiang.share.utils.ShangAnMessageType;

@DataSource(DataSourceEnum.MASTER)
@Service("carlifeSrvBilling")
public class CarlifeSrvBillingServiceImpl implements CarlifeSrvBillingService {
	@Resource(name="carlifeSrvBillingDao")
	private CarlifeSrvBillingDao carlifeSrvBillingDao;
	
	@Override
	public List<CarlifeSrvBilling> selectList(CarlifeSrvBilling carlifeSrvBilling) {
		return carlifeSrvBillingDao.selectList(carlifeSrvBilling);
	}

	@Override
	public Page<CarlifeSrvBilling> queryListPage(Page<CarlifeSrvBilling> page) {
	    page.setResultList(carlifeSrvBillingDao.queryListPage(page));
		return page;
	}
	
	@Override
	public CarlifeSrvBilling queryById(java.lang.Integer id) {
		return carlifeSrvBillingDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(CarlifeSrvBilling carlifeSrvBilling) {
		carlifeSrvBillingDao.insert(carlifeSrvBilling);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		carlifeSrvBillingDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(CarlifeSrvBilling carlifeSrvBilling) {
		carlifeSrvBillingDao.update(carlifeSrvBilling);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<CarlifeSrvBilling> carlifeSrvBillingList) {
		carlifeSrvBillingDao.batchUpdate(carlifeSrvBillingList);
	}
  /**
   * 获取车管家服务信息简介
   */
    public String getSvrFeeInfo(String parkingId,String srvId){
    	String mess = "";
    	List<CarlifeSrvBilling> list = null; 
    	Map<String,String> param = new HashMap<String,String>();
    	param.put("parkingId", parkingId);
    	param.put("srvId",srvId); 	
    	try {
    		list = carlifeSrvBillingDao.selectSrvInfo(param);
		} catch (Exception e) {
			// TODO: handle exception
			mess = ShangAnMessageType.toShangAnJson("2","erro","srvInfo","");
			return mess;
		}
    	if(null!=list&&list.size()>0){
    		Map<String,Object> paMap = new HashMap<String,Object>();
    		List<String> bilList = new ArrayList<String>();//srvBilling服务字符串
    		for(CarlifeSrvBilling c :list){
    			String srvFee = "";
    			String srvPrice = "";
    			if(null!=c.getSrvFee()){
    				srvFee = c.getSrvFee()+"";
    			}
    			if(null!=c.getSrvPrice()){
    				srvPrice = c.getSrvPrice()+"";
    			}
    			String str = c.getDict_name()+"  "+"服务费"+srvFee+"元  "+"服务价格"+srvPrice+"元";
    			bilList.add(str);    			
    		}
    		paMap.put("srvId",(null!=list.get(0).getSrvId())?list.get(0).getSrvId():"");
    		paMap.put("srvName",(null!=list.get(0).getSrv_name())?list.get(0).getSrv_name():"");
    		paMap.put("intro", (null!=list.get(0).getIntro())?list.get(0).getIntro():"");
    		paMap.put("srvBilling", bilList);    		    		
    		mess = ShangAnMessageType.toShangAnJson("0","sucess","srvInfo",paMap);
    	}else{
    		mess = ShangAnMessageType.toShangAnJson("1","sucess","srvInfo","");
    	}
		return mess;
    	
    }

	@Override
	public String selectListParking(String srvId) {
      String mess=null;
		List<Parking>  list=carlifeSrvBillingDao.selectListParking(srvId);
      if(list!=null&&list.size()>0){
		  mess = ShangAnMessageType.toShangAnJson("0","sucess","parkingList",list);
	  }else {
		  mess = ShangAnMessageType.toShangAnJson("1","sucess","parkingList","");
	  }

		return mess;
	}

}