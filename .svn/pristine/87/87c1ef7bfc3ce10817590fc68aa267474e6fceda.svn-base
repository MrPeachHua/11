package com.boxiang.share.product.parking.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.customer.dao.CustomerDao;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.parking.dao.ParkingVoucherDao;
import com.boxiang.share.product.parking.po.ParkingVoucher;
import com.boxiang.share.product.parking.service.ParkingVoucherService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("parkingVoucher")
public class ParkingVoucherServiceImpl implements ParkingVoucherService {
	@Resource(name="parkingVoucherDao")
	private ParkingVoucherDao parkingVoucherDao;
	
	@Resource(name="customerDao")
	private CustomerDao customerDao;
	@Override
	public List<ParkingVoucher> selectList(ParkingVoucher parkingVoucher) {
		return parkingVoucherDao.selectList(parkingVoucher);
	}

	@Override
	public Page<ParkingVoucher> queryListPage(Page<ParkingVoucher> page) {
	    page.setResultList(parkingVoucherDao.queryListPage(page));
		return page;
	}
	
	@Override
	public ParkingVoucher queryById(java.lang.Integer id) {
		return parkingVoucherDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(ParkingVoucher parkingVoucher) {
		parkingVoucherDao.insert(parkingVoucher);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		parkingVoucherDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(ParkingVoucher parkingVoucher) {
		parkingVoucherDao.update(parkingVoucher);
	}
	@Override
	public List<ParkingVoucher> selectListByDate(ParkingVoucher pv) {
		return parkingVoucherDao.selectListByDate(pv);
	}
  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<ParkingVoucher> tParkingVoucherList) {
		parkingVoucherDao.batchUpdate(tParkingVoucherList);
	}
  @Override
public List<ParkingVoucher> queryCountStatus(ParkingVoucher pv) {
	return parkingVoucherDao.queryCountStatus(pv);
}
  @Override
  public String getList(String customerId, String pageIndex) {
	  String mess=null;
	  Customer cust = customerDao.queryByCustomerId(customerId);
	  Page<ParkingVoucher> page=new Page<ParkingVoucher>();
	  page.getParams().put("customerId", customerId);
	  page.getParams().put("isUsed", Constants.TRUE);
	  long pageIndex2 = 1;
	  if(customerId!=null&&pageIndex!=null){
		  pageIndex2= (long)Integer.parseInt(pageIndex);
		  page.setCurrentPage(pageIndex2);
		  page.setPageSize(Page.PAGE_SIZE_10);
		  List<ParkingVoucher> list = parkingVoucherDao.selectListByCustomer(page);
		  List <Map<String,Object>> pvList = new ArrayList <Map<String,Object>>();
		  if(list!=null&&list.size()>0){
			 for(ParkingVoucher pv : list) {
				Map<String,Object> pvMap = new HashMap<String,Object>();
				pvMap.put("parkingName", (null!=pv.getParkingName())?pv.getParkingName():"");
				pvMap.put("startTime", (null!=pv.getStartTime())?pv.getStartTime():"");
				pvMap.put("stopTime", (null!=pv.getStopTime())?pv.getStopTime():"");
				if("1".equals(cust.getIdentity())){
					pvMap.put("sharePrice", (null!=pv.getVipSharePrice())?(int)((double)pv.getVipSharePrice()):"");
				}else{
					pvMap.put("sharePrice", (null!=pv.getSharePrice())?(int)((double)pv.getSharePrice()):"");
				}
//				pvMap.put("sharePrice", (null!=pv.getSharePrice())?(int)((double)pv.getSharePrice()):"");
				pvMap.put("createDate",(null!=pv.getCreateDate())?DateUtil.date2str(pv.getCreateDate(), "yyyy-MM-dd"):"");
				pvMap.put("carNumber", (null!=pv.getCarNumber())?pv.getCarNumber():"");
//				pvMap.put("pvStatus", (null!=pv.getPvStatus())?pv.getPvStatus():"");
				pvList.add(pvMap);
			 }
				mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "voucherList", pvList);
		  }else {
				mess = ShangAnMessageType.toShangAnJson("1", "无数据", "voucherList", "[]");
		  }
	 }
	return mess;
  }
}