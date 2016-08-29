package com.boxiang.share.product.parking.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.boxiang.share.product.parking.dao.ParkingDao;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingStatusService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.parking.dao.ParkingStatusDao;
import com.boxiang.share.product.parking.po.ParkingStatus;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("parkingStatusService")
public class ParkingStatusServiceImpl implements ParkingStatusService {
	@Resource(name="parkingStatusDao")
	private ParkingStatusDao parkingStatusDao;

	@Resource(name="parkingDao")
	private ParkingDao parkingDao;
	@Override
	public List<ParkingStatus> selectList(ParkingStatus parkingStatus) {
		return parkingStatusDao.selectList(parkingStatus);
	}

	@Override
	public Page<ParkingStatus> queryListPage(Page<ParkingStatus> page) {
	    page.setResultList(parkingStatusDao.queryListPage(page));
		return page;
	}
	
	@Override
	public ParkingStatus queryById(Integer id) {
		return parkingStatusDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(ParkingStatus ParkingStatus) {
		parkingStatusDao.insert(ParkingStatus);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		parkingStatusDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(ParkingStatus ParkingStatus) {
		parkingStatusDao.update(ParkingStatus);
	}

	/**
	 * 获取停车场空位信息
	 * @param parkingId
	 * @return
	 */
	@Override
	public String getParkingStatus(String parkingId){
		int nowHour = 0;
		String nowTimeStr = "";
		String nextTimeStr = "";
		String statusStr = "";
		int parkingCanUse = 0;
		String parkingPrice = "";
		String startTime = "";
		String stopTime = "";
		//String isCommunity = "0";
		String parkingStatus = "0";
		//当前时间下一小时
		Calendar ca=Calendar.getInstance();
		nowHour = ca.get(Calendar.HOUR_OF_DAY);
		nowTimeStr = DateUtil.date2str(ca.getTime(), "HH:mm");
		ca.add(Calendar.HOUR_OF_DAY, 1);
		nextTimeStr = DateUtil.date2str(ca.getTime(),"HH:mm");
		ParkingStatus parkingStatusParam = new ParkingStatus();
		parkingStatusParam.setParkingId(parkingId);
		parkingStatusParam.setHourSection(nowHour);
		List<ParkingStatus> paList = parkingStatusDao.selectList(parkingStatusParam);
		//获取是否紧张
		if(null!=paList&&paList.size()>0){
			parkingStatus = paList.get(0).getStatus();
			if("0".equals(paList.get(0).getStatus())){
				statusStr = "空";
			}else if("1".equals(paList.get(0).getStatus())){
				statusStr = "紧张";
			}else if("2".equals(paList.get(0).getStatus())){
				statusStr = "满";
			}
		}
		Parking parkingParam = new Parking();
		parkingParam.setParkingId(parkingId);
		List<Parking> parkingList = parkingDao.selectList(parkingParam);
		if(null!=parkingList&&parkingList.size()>0){
			parkingCanUse = parkingList.get(0).getParkingCanUse();
			parkingPrice = parkingList.get(0).getSharePrice()+"";
			startTime = parkingList.get(0).getStartTime();
			stopTime = parkingList.get(0).getStopTime();
			//isCommunity = parkingList.get(0).getIsCommunity()+"";
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("nowTime",nowTimeStr);
		result.put("nextTime",nextTimeStr);
		result.put("statusInfo",statusStr);
		result.put("parkingCanUse",parkingCanUse);
		result.put("parkingPrice",parkingPrice==null?"":parkingPrice);
		result.put("startTime",startTime==null?"":startTime);
		result.put("stopTime",stopTime==null?"":stopTime);
		//result.put("isCommunity",isCommunity);
		result.put("status","1");
		return ShangAnMessageType.toShangAnJson("0","查询成功","data",result);
	}
	/**
	 * 获取停车场空位信息
	 * @param parkingId
	 * @return
	 */
	@Override
	public String getParkingStatus2_0_1(String parkingId) {
		int nowHour = 0;
		String nowTimeStr = "";
		String nextTimeStr = "";
		String statusStr = "";
		int parkingCanUse = 0;
		String parkingPrice = "";
		String startTime = "";
		String stopTime = "";
		//String isCommunity = "0";
		String parkingStatus = "0";
		//当前时间下一小时
		Calendar ca=Calendar.getInstance();
		nowHour = ca.get(Calendar.HOUR_OF_DAY);
		nowTimeStr = DateUtil.date2str(ca.getTime(), "HH:mm");
		ca.add(Calendar.HOUR_OF_DAY, 1);
		nextTimeStr = DateUtil.date2str(ca.getTime(),"HH:mm");
		ParkingStatus parkingStatusParam = new ParkingStatus();
		parkingStatusParam.setParkingId(parkingId);
		parkingStatusParam.setHourSection(nowHour);
		List<ParkingStatus> paList = parkingStatusDao.selectList(parkingStatusParam);
		//获取是否紧张
		if(null!=paList&&paList.size()>0){
			parkingStatus = paList.get(0).getStatus();
			if("0".equals(paList.get(0).getStatus())){
				statusStr = "空";
			}else if("1".equals(paList.get(0).getStatus())){
				statusStr = "紧张";
			}else if("2".equals(paList.get(0).getStatus())){
				statusStr = "满";
			}
		}
		Parking parkingParam = new Parking();
		parkingParam.setParkingId(parkingId);
		List<Parking> parkingList = parkingDao.selectList(parkingParam);
		if(null!=parkingList&&parkingList.size()>0){
			parkingCanUse = parkingList.get(0).getParkingCanUse();
			parkingPrice = parkingList.get(0).getSharePrice()+"";
			startTime = parkingList.get(0).getStartTime();
			stopTime = parkingList.get(0).getStopTime();
			//isCommunity = parkingList.get(0).getIsCommunity()+"";
		}
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("nowTime",nowTimeStr);
		result.put("nextTime",nextTimeStr);
		result.put("statusInfo",statusStr);
		result.put("parkingCanUse",parkingCanUse);
		result.put("parkingPrice",parkingPrice==null?"":parkingPrice);
		result.put("startTime",startTime==null?"":startTime);
		result.put("stopTime",stopTime==null?"":stopTime);
		//result.put("isCommunity",isCommunity);
		result.put("status",paList.get(0).getStatus());
		return ShangAnMessageType.toShangAnJson("0","查询成功","data",result);
	}
  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<ParkingStatus> ParkingStatusList) {
		parkingStatusDao.batchUpdate(ParkingStatusList);
	}

	@Override
	public List<ParkingStatus> tenseTime(Map<String, Object> map) {
		return parkingStatusDao.tenseTime(map);
	}
}