package com.boxiang.share.product.carlife.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.share.product.parker.po.Parker;
import com.boxiang.share.product.parker.service.ParkerService;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.carlife.dao.CarlifeSrvInfoDao;
import com.boxiang.share.product.carlife.po.CarlifeSrvInfo;
import com.boxiang.share.product.carlife.service.CarlifeSrvInfoService;
import com.boxiang.share.utils.ShangAnMessageType;

@DataSource(DataSourceEnum.MASTER)
@Service("carlifeSrvInfoService")
public class CarlifeSrvInfoServiceImpl implements CarlifeSrvInfoService {
	@Resource(name="carlifeSrvInfoDao")
	private CarlifeSrvInfoDao carlifeSrvInfoDao;
	@Resource
	private ParkerService parkerService;
	@Resource
	private ParkingService parkingService;
	@Override
	public List<CarlifeSrvInfo> selectList(CarlifeSrvInfo carlifeSrvInfo) {
		return carlifeSrvInfoDao.selectList(carlifeSrvInfo);
	}

	@Override
	public Page<CarlifeSrvInfo> queryListPage(Page<CarlifeSrvInfo> page) {
	    page.setResultList(carlifeSrvInfoDao.queryListPage(page));
		return page;
	}
	
	@Override
	public CarlifeSrvInfo queryById(java.lang.Integer id) {
		return carlifeSrvInfoDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(CarlifeSrvInfo carlifeSrvInfo) {
		carlifeSrvInfoDao.insert(carlifeSrvInfo);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		carlifeSrvInfoDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(CarlifeSrvInfo carlifeSrvInfo) {
		carlifeSrvInfoDao.update(carlifeSrvInfo);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<CarlifeSrvInfo> carlifeSrvInfoList) {
		carlifeSrvInfoDao.batchUpdate(carlifeSrvInfoList);
	}
  /**
   *根据手机号查询用户服务 
   */
  public List<CarlifeSrvInfo> selectCarlifeCsrByMobile(Map<String,String> param) {
		return carlifeSrvInfoDao.selectCarlifeCsrByMobile(param);
	}
	public int querySortMax(){
		return carlifeSrvInfoDao.querySortMax();
	}
   /**
    * 获得车生活服务信息
    */
    public String getCarlifeSrvInfoMessage(HttpServletRequest request, HttpServletResponse response){
    	String path = request.getContextPath();
    	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    	String mess="";
    	List <Map<String,Object>> srvList = new ArrayList<Map<String,Object>>();
		List<CarlifeSrvInfo> list = null;
    	Map<String,String> param = new HashMap<String,String>();
		try {
		    list = carlifeSrvInfoDao.selectCarlifeCsrByMobile(param);
		} catch (Exception e) {
			// TODO: handle exception
			mess = ShangAnMessageType.toShangAnJson("2","获取列表失败","srvList","");
			return mess;
		}
    	if(null!=list&&list.size()>0){
    		for(int i = 0;i<list.size();i++){
    			CarlifeSrvInfo c = list.get(i);
    			Map<String,Object> paraMap = new HashMap<String,Object>();
    			paraMap.put("srvId",null==c.getId()?"":c.getId());
    			paraMap.put("srvName", null==c.getSrvName()?"":c.getSrvName());
    			paraMap.put("logoPath", null==basePath+c.getLogoPath()?"":basePath+c.getLogoPath());
    			paraMap.put("intro", null==c.getIntro()?"":c.getIntro());
    			paraMap.put("flag", c.getFlag());
    			srvList.add(paraMap);
    			if(i==3){
    				Map<String,Object> para = new HashMap<String,Object>();
    				para.put("srvId",-1);
    				para.put("srvName", "");
        			para.put("logoPath", "");
        			para.put("intro", "");
        			para.put("flag", "");
        			srvList.add(para);
    			}
    			}
    		mess = ShangAnMessageType.toShangAnJson("0","获取服务列表成功","srvList",srvList);
    	}else{
    		mess = ShangAnMessageType.toShangAnJson("1","无信息","srvList","");
    	}
    	
    	return mess;   	
    }

@Override
public String queryCharge(String parkingId) {
	String mess=null;
	List <Map<String,Object>> srvList = new ArrayList<Map<String,Object>>();
	List<CarlifeSrvInfo> list=carlifeSrvInfoDao.queryCharge(parkingId);
	Parking parking= parkingService.queryById(parkingId);
	if(list!=null&&list.size()>0){
		for (CarlifeSrvInfo carlifeSrvInfo : list) {
			Map<String,Object> paraMap = new HashMap<String,Object>();
			 paraMap.put("parkingName",null==parking.getParkingName()?"":parking.getParkingName());
			paraMap.put("carType",null==carlifeSrvInfo.getCarType()?"":carlifeSrvInfo.getCarType());
			paraMap.put("srvFee",null==carlifeSrvInfo.getSrvFee()?"":carlifeSrvInfo.getSrvFee());
			paraMap.put("srvPrice",null==carlifeSrvInfo.getSrvPrice()?"":carlifeSrvInfo.getSrvPrice());
			paraMap.put("nowPrice",null==carlifeSrvInfo.getNowPrice()?"":carlifeSrvInfo.getNowPrice());
			paraMap.put("intro",null==carlifeSrvInfo.getIntro()?"":carlifeSrvInfo.getIntro());
			paraMap.put("description",null==carlifeSrvInfo.getDescription()?"":carlifeSrvInfo.getDescription());
			srvList.add(paraMap);
		}

		mess = ShangAnMessageType.toShangAnJson("0","查询成功","srvList",srvList);
	}else{
		mess = ShangAnMessageType.toShangAnJson("1","无信息","srvList","");
	}
	return mess; 
}

	@Override
	public String queryImage(String parkingId,HttpServletRequest request) {
		String mess=null;
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		List <Map<String,Object>> parkerList = new ArrayList<Map<String,Object>>();
		Parker parker=new Parker();
		parker.setParkingId(parkingId);
		parker.setParkerType("2");
		parker.setIsDisplay(1);
		List<Parker> list=parkerService.selectList(parker);
		if(list!=null&&list.size()>0){
			for (Parker parker1 : list) {
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("parkerHead",null==parker1.getParkerHead()?"":basePath+parker1.getParkerHead());
				paraMap.put("parkerName",null==parker1.getParkerName()?"":parker1.getParkerName());
				parkerList.add(paraMap);
			}

			mess = ShangAnMessageType.toShangAnJson("0","查询成功","parkerList",parkerList);
		}else{
			mess = ShangAnMessageType.toShangAnJson("1","无信息","parkerList",parkerList);
		}
		return mess;
	}
}