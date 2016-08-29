package com.boxiang.share.product.statistics.service.impl;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.share.product.car.po.Car;
import com.boxiang.share.product.order.dao.OrderMainDao;
import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.product.parking.dao.CarInOutRecordDao;
import com.boxiang.share.product.parking.dao.CarInOutRecordV2Dao;
import com.boxiang.share.product.parking.dao.ClearanceRecordDao;
import com.boxiang.share.product.parking.po.CarInOutRecord;
import com.boxiang.share.product.parking.po.CarInOutRecordV2;
import com.boxiang.share.product.parking.po.ClearanceRecord;
import com.boxiang.share.product.statistics.service.StatisticsService;
import com.boxiang.share.user.dao.UserInfoDao;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

@DataSource(DataSourceEnum.MASTER)
@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {
	private static final Logger logger = Logger.getLogger(StatisticsServiceImpl.class);
	@Resource(name="orderMainDao")
	private OrderMainDao orderMainDao;

	@Resource(name="userInfoDao")
	private UserInfoDao userInfoDao;

	@Resource(name="clearanceRecordDao")
	private ClearanceRecordDao clearanceRecordDao;

	@Resource(name="carInOutRecordV2Dao")
	private CarInOutRecordV2Dao carInOutRecordV2Dao;

	/**
	 * B端日销售信息
	 * @param userId
	 * @return
	 */
	@Override
	public String getDaySaleStatistics(String userId) {
		Map<String,Object> data = new HashMap<String,Object>();
		Map<String,Object> dayStatisticsMapNull = new HashMap<String,Object>();
		UserInfo userParam = new UserInfo();
		userParam.setSysUserId(userId);
		List<UserInfo> uList = userInfoDao.selectList(userParam);
		if(null==uList||uList.size()==0){
			return ShangAnMessageType.operateToJson("2","该用户不存在");
		}
		String parkingIds = uList.get(0).getParkingId();
		if(StringUtils.isEmpty(parkingIds)){
			data.put("dayStatisticsMap",dayStatisticsMapNull);
			return ShangAnMessageType.toShangAnJson("0","车场为空","data",data);//0000000000000000000000000000
		}
		String [] pkStr = parkingIds.split("\\,");
		String parkingStr = "";
		for(int i=0;i<pkStr.length;i++){
			parkingStr +="'";
			parkingStr+=pkStr[i];
			parkingStr+="'";
			parkingStr+=",";
		}
		parkingStr = parkingStr.substring(0,parkingStr.length()-1);
		//今日收入
		data = this.getDayStatistics(userId,parkingStr);
		//data.put("dayStatisticsMap",dayStatisticsMap);
		return ShangAnMessageType.toShangAnJson("0","查询成功","data",data);
	}
	/**
	 * B端周销售信息
	 * @param userId
	 * @return
	 */
	@Override
	public String getWeekSaleStatistics(String userId) {
		Map<String,Object> data = new HashMap<String,Object>();
		List<Map<String,Object>> dayStatisticsMapNull = new ArrayList<Map<String,Object>>();
		UserInfo userParam = new UserInfo();
		userParam.setSysUserId(userId);
		List<UserInfo> uList = userInfoDao.selectList(userParam);
		if(null==uList||uList.size()==0){
			return ShangAnMessageType.operateToJson("2","该用户不存在");
		}
		String parkingIds = uList.get(0).getParkingId();
		if(StringUtils.isEmpty(parkingIds)){
			data.put("dayStatisticsMap",dayStatisticsMapNull);
			return ShangAnMessageType.toShangAnJson("0","车场为空","data",data);//0000000000000000000000000000
		}
		String [] pkStr = parkingIds.split("\\,");
		String parkingStr = "";
		for(int i=0;i<pkStr.length;i++){
			parkingStr +="'";
			parkingStr+=pkStr[i];
			parkingStr+="'";
			parkingStr+=",";
		}
		parkingStr = parkingStr.substring(0,parkingStr.length()-1);
		//今日收入
		data = this.getWeekStatistics(userId, parkingStr);
		return ShangAnMessageType.toShangAnJson("0","查询成功","data",data);
	}

	/**
	 * B端项目统计一个月内周信息
	 * @param userId
	 * @return
	 */
	@Override
	public String getMonthDaySaleStatistics(String userId,String year,String month,String parkingId) {
		Map<String,Object> data = new HashMap<String,Object>();
		List<Map<String,Object>> dayStatisticsMapNull = new ArrayList<Map<String,Object>>();
		String parkingStr = "";
		if(!StringUtils.isEmpty(parkingId)){
			parkingStr = "'"+parkingId+"'";
		}else{
			UserInfo userParam = new UserInfo();
			userParam.setSysUserId(userId);
			List<UserInfo> uList = userInfoDao.selectList(userParam);
			if(null==uList||uList.size()==0){
				return ShangAnMessageType.operateToJson("2","该用户不存在");
			}
			String parkingIds = uList.get(0).getParkingId();
			if(StringUtils.isEmpty(parkingIds)){
				data.put("dayStatisticsMap",dayStatisticsMapNull);
				return ShangAnMessageType.toShangAnJson("0","车场为空","data",data);//0000000000000000000000000000
			}
			String [] pkStr = parkingIds.split("\\,");
			for(int i=0;i<pkStr.length;i++){
				parkingStr +="'";
				parkingStr+=pkStr[i];
				parkingStr+="'";
				parkingStr+=",";
			}
			parkingStr = parkingStr.substring(0,parkingStr.length()-1);
		}
		Date dat = null;
		String sr[][] = null;
		Calendar cl = Calendar.getInstance();
		cl.setTime(new Date());
		int yearIntNow = cl.get(Calendar.YEAR);
		int monthIntNow = cl.get(Calendar.MONTH)+1;
		//获取节点集合
		try {
			dat = DateUtil.str2date(year+"-"+(month.length()>1?month:"0"+month)+"-01", "yyyy-MM-dd");
			if(yearIntNow==Integer.parseInt(year)&&monthIntNow==Integer.parseInt(month)){
				dat = new Date();
			}
			sr = DateUtil.getNormalWeek(dat);
		} catch (ParseException e) {
			logger.error("-------日期转换异常");
			return ShangAnMessageType.operateToJson("2","日期转换异常");
		}
		//月租集合
		List<Integer> monList = this.getMonthWeekStream("13",year,month,parkingStr,sr);
		//产权集合
		List<Integer> proList = this.getMonthWeekStream("14",year,month,parkingStr,sr);
		//临停集合
		List<Integer> temList = this.getMonthWeekStream("11",year,month,parkingStr,sr);
		String strSt = sr[0][0];
		String strEn = sr[sr.length-1][1];
		data.put("weekDesc","本月收入");
		data.put("weekDate",strSt+"-"+strEn);
		List<String> xAxis = new ArrayList<String>();
		for(String [] v :sr){
			String v0 = v[0];
			String v1 = v[1];
			if(!StringUtils.isEmpty(v0)&&!StringUtils.isEmpty(v1)){

				v0 = this.subStrOfMonth(v0.split("-")[1])+"."+this.subStrOfMonth(v0.split("-")[2]);
				v1 = this.subStrOfMonth(v1.split("-")[1])+"."+this.subStrOfMonth(v1.split("-")[2]);
			}

			xAxis.add(v0+"-"+v1);
		}
		data.put("xAxis",xAxis);
		List<Map<String,Object>> dayStatisticsMap = new ArrayList<Map<String,Object>>();
		Map<String,Object> result1 = new HashMap<String,Object>();
		Map<String,Object> result2 = new HashMap<String,Object>();
		Map<String,Object> result3 = new HashMap<String,Object>();
		result1.put("name","月租");
		result1.put("data",monList);
		result2.put("name","产权");
		result2.put("data",proList);
		result3.put("name","临停");
		result3.put("data",temList);
		dayStatisticsMap.add(result1);
		dayStatisticsMap.add(result2);
		dayStatisticsMap.add(result3);
		data.put("dayStatisticsMap",dayStatisticsMap);
		return ShangAnMessageType.toShangAnJson("0","查询成功","data",data);
	}
	//出去单位月数前的0
	public String subStrOfMonth(String param){
		if("0".equals(param.substring(0, 1))){
			param = param.substring(1,param.length());
		}
		return param;
	}
	//销售统计日流水获取一个月内几周的数据
	List<Integer> getMonthWeekStream(String orderType, String year, String month, String parkingIds, String[][] sr){
		List<Integer> inList = new ArrayList<Integer>();
		for(String [] v :sr){
			int sumPaid = 0;
			Map<String,String> odParam = new HashMap<String,String>();
			odParam.put("orderStatus","11");
			odParam.put("orderType",orderType);
			odParam.put("startTime", v[0]+" 00:00:00");
			odParam.put("endTime",v[1]+" 23:59:59");
			odParam.put("parkingIds",parkingIds);
			odParam.put("payType","'00','01','04','05'");
			List<OrderMain> omList = null;
			List<CarInOutRecordV2> cioList = null;
			if("13".equals(orderType)){
				omList = orderMainDao.getMonthlyOrderByParam(odParam);
			}else if("14".equals(orderType)){
				omList = orderMainDao.getEquityOrderByParam(odParam);
			}else if("11".equals(orderType)){
				omList = orderMainDao.getTempOrderByParam(odParam);
				//临停线下缴费
				Map<String,String> caParam = new HashMap<String,String>();
				caParam.put("startTime", v[0]+" 00:00:00");
				caParam.put("endTime",v[1]+" 23:59:59");
				caParam.put("parkingIds",parkingIds);
				cioList =carInOutRecordV2Dao.getTempOfflineByDate(caParam);

			}
			if(null!=omList&&omList.size()>0&&null!=omList.get(0)){
				if(!StringUtils.isEmpty(omList.get(0).getSumPaid())){
					if("11".equals(orderType)){
						int tempOffline = 0;
						if(null!=cioList&&cioList.size()>0&&null!=cioList.get(0)){
							tempOffline = cioList.get(0).getRealCharge();
						}
						sumPaid = Integer.parseInt(omList.get(0).getSumPaid());
						inList.add(sumPaid / 100+tempOffline);
					}else {
						sumPaid = Integer.parseInt(omList.get(0).getSumPaid());
						inList.add(sumPaid / 100);
					}
				}
			}else{
				if("11".equals(orderType)) {
					int tempOffline = 0;
					if (null != cioList && cioList.size() > 0 && null != cioList.get(0)) {
						tempOffline = cioList.get(0).getRealCharge();
					}
					inList.add(tempOffline);
				}else {
					inList.add(0);
				}
			}
		}


		return inList;
	}

	@Override
	public String getMonthStreamStatistics(String userId, String year, String month,String parkingId) {
		Map<String,Object> data = new HashMap<String,Object>();
		List<Map<String,Object>> dayStatisticsMapNull = new ArrayList<Map<String,Object>>();
		String parkingStr = "";
		if(!StringUtils.isEmpty(parkingId)){
			parkingStr = "'"+parkingId+"'";
		}else{
			UserInfo userParam = new UserInfo();
			userParam.setSysUserId(userId);
			List<UserInfo> uList = userInfoDao.selectList(userParam);
			if(null==uList||uList.size()==0){
				return ShangAnMessageType.operateToJson("2","该用户不存在");
			}
			String parkingIds = uList.get(0).getParkingId();
			if(StringUtils.isEmpty(parkingIds)){
				data.put("dayStatisticsMap",dayStatisticsMapNull);
				return ShangAnMessageType.toShangAnJson("0","车场为空","data",data);//0000000000000000000000000000
			}
			String [] pkStr = parkingIds.split("\\,");
			for(int i=0;i<pkStr.length;i++){
				if(!StringUtils.isEmpty(pkStr[i])){
					parkingStr +="'";
					parkingStr+=pkStr[i];
					parkingStr+="'";
					parkingStr+=",";
				}

			}
			parkingStr = parkingStr.substring(0,parkingStr.length()-1);
		}
		//月租月销售
		int monthSale = this.getMonthStream(userId, parkingStr, "13", year, month)/100;
		//产权月销售
		int propertySale = this.getMonthStream(userId, parkingStr, "14", year, month)/100;
		//月租目标
		int monthSalePoint = this.getMonthStreamPoint(userId, parkingStr, "13", year, month);
		//产权目标
		int propertySalePoint = this.getMonthStreamPoint(userId, parkingStr, "14", year, month);
		//临停月销售
		int teSum = this.getMonthStream(userId, parkingStr, "11", year, month)/100;
		//临停下线月销售
		//计算临停下下的每天的
		int tempOffline = 0;
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(year),Integer.parseInt(month),0);
		Date da = c.getTime();
		String dastr = DateUtil.date2str(da,"yyyy-MM");
		Map<String,String> caParam = new HashMap<>();
		caParam.put("inputDate",dastr);
		caParam.put("parkingIds",parkingStr);
		List<CarInOutRecordV2> cioList =carInOutRecordV2Dao.getTempOfflineByMonth(caParam);
		if(null!=cioList&&cioList.size()>0&&null!=cioList.get(0)) {
			tempOffline = cioList.get(0).getRealCharge();
		}

		data.put("monthly",month+"月统计");
		Calendar ca = Calendar.getInstance();
		ca.clear();
		ca.set(Calendar.YEAR, Integer.parseInt(year));
		ca.set(Calendar.MONTH, Integer.parseInt(month));
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		data.put("monthtime",year+"."+month+".1-"+month+"."+ca.get(Calendar.DATE));
		List<String> xAxis = new ArrayList<String>();
		xAxis.add("月租");
		xAxis.add("产权");
		xAxis.add("临停");
		data.put("xAxis",xAxis);
		List<Integer> dayStatisticsMap  = new ArrayList<Integer>();
		dayStatisticsMap.add(monthSalePoint);
		dayStatisticsMap.add(propertySalePoint);
		dayStatisticsMap.add(teSum+tempOffline);
		data.put("dayStatisticsMap",dayStatisticsMap);
		List<Integer> factPrice  = new ArrayList<Integer>();
		factPrice.add(monthSale);
		factPrice.add(propertySale);
		factPrice.add(teSum+tempOffline);
		data.put("factPrice",factPrice);
		return ShangAnMessageType.toShangAnJson("0","查询成功","data",data);
	}
	public int getMonthStream(String userId, String parkingStr, String orderType, String year, String month){
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(year),Integer.parseInt(month),0);
		Date da = c.getTime();
		String dastr = DateUtil.date2str(da,"yyyy-MM");
		int price = 0;
		Map<String,Object> odParam = new HashMap<String,Object>();
		odParam.put("inputDate", dastr);
		odParam.put("parkingIds",parkingStr);
		if("13".equals(orderType)){
			OrderMain om = orderMainDao.getMonthPaid(odParam);
			if(null!=om&&!StringUtils.isEmpty(om.getSumPaid())){
				price = Integer.parseInt(om.getSumPaid());
			}
		}else if("14".equals(orderType)){
			OrderMain om = orderMainDao.getPropertyPaid(odParam);
			if(null!=om&&!StringUtils.isEmpty(om.getSumPaid())){
				price = Integer.parseInt(om.getSumPaid());
			}
		}else if("11".equals(orderType)){
			OrderMain om = orderMainDao.getTemPaid(odParam);
			if(null!=om&&!StringUtils.isEmpty(om.getSumPaid())){
				price = Integer.parseInt(om.getSumPaid());
			}
		}
		return price;
	}
	//目标额
	public int getMonthStreamPoint(String userId, String parkingStr, String orderType, String year, String month) {
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(year),Integer.parseInt(month),0);
		Date da = c.getTime();
		String dastr = DateUtil.date2str(da,"yyyy-MM");
		int price = 0;
		Map<String,Object> odParam = new HashMap<String,Object>();
		odParam.put("inputDate", dastr);
		odParam.put("parkingIds",parkingStr);
		if ("13".equals(orderType)) {
			OrderMain om = orderMainDao.getMonthPoint(odParam);
			if (null != om && !StringUtils.isEmpty(om.getSumPricePoint())) {
				price = (int) Double.parseDouble(om.getSumPricePoint());

			}
		} else if ("14".equals(orderType)) {
			OrderMain om = orderMainDao.getPropertyPoint(odParam);
			if (null != om && !StringUtils.isEmpty(om.getSumPricePoint())) {
				price = (int) Double.parseDouble(om.getSumPricePoint());
			}

		}
		return price;
	}
	/**
	 * 今日收入
	 * @param userId
	 * @return
	 */
	public Map<String,Object> getDayStatistics(String userId,String parkingIds) {
		//月租线上线下
		String monthlyOnline = "0";
		String monthlyOffline = "0";
		//产权线上线下
		String propertyOnline = "0";
		String propertyOffline = "0";
		//
		String tempOnline = "0";
		String tempOffline = "0";

		//月租线上线下
		String monthlyOnlineDiscount = "0";
		String monthlyOfflineDiscount = "0";
		//产权线上线下
		String propertyOnlineDiscount = "0";
		String propertyOfflineDiscount = "0";
		//
		String tempOnlineDiscount = "0";
		String tempOfflineDiscount = "0";
		List<Map<String,Object>> mapListResult = new ArrayList<Map<String,Object>>();
		Map<String,String> odParam = new HashMap<String,String>();
		odParam.put("orderStatus","11");
		odParam.put("orderType","13");
		odParam.put("startTime", DateUtil.date2str(new Date(),DateUtil.DATE_FORMAT)+" 00:00:00");
		odParam.put("endTime",DateUtil.date2str(new Date(),DateUtil.DATE_FORMAT)+" 23:59:59");
		odParam.put("parkingIds",parkingIds);
		odParam.put("payType","'00','01','04','05'");
		//---------------月租
		List<OrderMain> omListOnline = orderMainDao.getMonthlyOrderByParam(odParam);
		odParam.remove("payType");
		odParam.put("payType","'03'");
		List<OrderMain> omListOffline = orderMainDao.getMonthlyOrderByParam(odParam);
		if(null!=omListOnline&&omListOnline.size()>0&&null!=omListOnline.get(0)){
			String sumStr = omListOnline.get(0).getSumPaid();
			if(!StringUtils.isEmpty(sumStr)){
				monthlyOnline = Integer.parseInt(sumStr)/100+"";
			}
			String sumStrDiscount = omListOnline.get(0).getSumDiscount();
			if(!StringUtils.isEmpty(sumStrDiscount)){
				monthlyOnlineDiscount = Integer.parseInt(sumStrDiscount)/100+"";
			}
		}
		if(null!=omListOffline&&omListOffline.size()>0&&null!=omListOffline.get(0)){
			String sumStr = omListOffline.get(0).getSumPaid();
			if(!StringUtils.isEmpty(sumStr)){
				monthlyOffline = Integer.parseInt(sumStr)/100+"";
			}
			String sumStrDiscount = omListOffline.get(0).getSumDiscount();
			if(!StringUtils.isEmpty(sumStrDiscount)){
				monthlyOfflineDiscount = Integer.parseInt(sumStrDiscount)/100+"";
			}
		}
		//-----------------产权
		odParam.remove("orderType");
		odParam.put("orderType", "14");
		odParam.remove("payType");
		odParam.put("payType","'00','01','04','05'");
		List<OrderMain> propertyListOnline = orderMainDao.getEquityOrderByParam(odParam);
		odParam.remove("payType");
		odParam.put("payType","'03'");
		List<OrderMain> propertyListOffline = orderMainDao.getEquityOrderByParam(odParam);
		if(null!=propertyListOnline&&propertyListOnline.size()>0&&null!=propertyListOnline.get(0)){
			String sumStr = propertyListOnline.get(0).getSumPaid();
			if(!StringUtils.isEmpty(sumStr)){
				propertyOnline = Integer.parseInt(sumStr)/100+"";
			}
			String sumStrDiscount = propertyListOnline.get(0).getSumDiscount();
			if(!StringUtils.isEmpty(sumStrDiscount)){
				propertyOnlineDiscount = Integer.parseInt(sumStrDiscount)/100+"";
			}
		}
		if(null!=propertyListOffline&&propertyListOffline.size()>0&&null!=propertyListOffline.get(0)){
			String sumStr = propertyListOffline.get(0).getSumPaid();
			if(!StringUtils.isEmpty(sumStr)){
				propertyOffline = Integer.parseInt(sumStr)/100+"";
			}
			String sumStrDiscount = propertyListOffline.get(0).getSumDiscount();
			if(!StringUtils.isEmpty(sumStrDiscount)){
				propertyOfflineDiscount = Integer.parseInt(sumStrDiscount)/100+"";
			}
		}
		//-----------------临停
		odParam.remove("orderType");
		odParam.put("orderType", "11");
		odParam.remove("payType");
		odParam.put("payType","'00','01','04','05'");
		List<OrderMain> tempListOnline = orderMainDao.getTempOrderByParam(odParam);
		/*odParam.remove("payType");
		odParam.put("payType","'03'");
		List<OrderMain> tempListOffline = orderMainDao.getTempOrderByParam(odParam);*/
		if(null!=tempListOnline&&tempListOnline.size()>0&&null!=tempListOnline.get(0)){
			String sumStr = tempListOnline.get(0).getSumPaid();
			if(!StringUtils.isEmpty(sumStr)){
				tempOnline = Integer.parseInt(sumStr)/100+"";
			}
			String sumStrDiscount = tempListOnline.get(0).getSumDiscount();
			if(!StringUtils.isEmpty(sumStrDiscount)){
				tempOnlineDiscount = Integer.parseInt(sumStrDiscount)/100+"";
			}
		}
		/*if(null!=tempListOffline&&tempListOffline.size()>0&&null!=tempListOffline.get(0)){
			String sumStr = tempListOffline.get(0).getSumDiscount();
			if(!StringUtils.isEmpty(sumStr)){
				tempOffline = Integer.parseInt(sumStr)/100+"";
			}
			String sumStrDiscount = tempListOffline.get(0).getSumPaid();
			if(!StringUtils.isEmpty(sumStrDiscount)){
				tempOfflineDiscount = Integer.parseInt(sumStrDiscount)/100+"";
			}
		}*/
		//计算临停下下的每天的
		Map<String,String> caParam = new HashMap<>();
		caParam.put("startTime", DateUtil.date2str(new Date(),DateUtil.DATE_FORMAT)+" 00:00:00");
		caParam.put("endTime",DateUtil.date2str(new Date(),DateUtil.DATE_FORMAT)+" 23:59:59");
		caParam.put("parkingIds",parkingIds);
		List<CarInOutRecordV2> cioList =carInOutRecordV2Dao.getTempOfflineByDate(caParam);
		if(null!=cioList&&cioList.size()>0&&null!=cioList.get(0)){
			tempOffline = cioList.get(0).getRealCharge()+"";
		}
		//mapListResult
		Map<String,Object> mapParam1 = new HashMap<String,Object>();
		Map<String,Object> mapParam2 = new HashMap<String,Object>();
		Map<String,Object> mapParam3 = new HashMap<String,Object>();
		Map<String,Object> mapParam4 = new HashMap<String,Object>();
		Map<String,Object> mapParam5 = new HashMap<String,Object>();
		Map<String,Object> mapParam6 = new HashMap<String,Object>();
		mapParam1.put("desc","月租线上");
		mapParam1.put("value", monthlyOnline);
		mapListResult.add(mapParam1);

		mapParam2.put("desc","月租线下");
		mapParam2.put("value",monthlyOffline);
		mapListResult.add(mapParam2);

		mapParam3.put("desc","产权线上");
		mapParam3.put("value",propertyOnline);
		mapListResult.add(mapParam3);

		mapParam4.put("desc","产权线下");
		mapParam4.put("value",propertyOffline);
		mapListResult.add(mapParam4);

		mapParam5.put("desc","临停线上");
		mapParam5.put("value",tempOnline);
		mapListResult.add(mapParam5);

		mapParam6.put("desc","临停线下");
		mapParam6.put("value",tempOffline);
		mapListResult.add(mapParam6);
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("dayStatisticsMap",mapListResult);
		data.put("todayDesc","今日收入");
		data.put("todayIncome",
				(
				Integer.parseInt(monthlyOnline)+
				Integer.parseInt(monthlyOffline)+
				Integer.parseInt(propertyOnline)+
				Integer.parseInt(propertyOffline)+
				Integer.parseInt(tempOnline)+
				Integer.parseInt(tempOffline)
				)+""
				);
		data.put("remit",
				(
				Integer.parseInt(monthlyOnlineDiscount)+
				Integer.parseInt(monthlyOfflineDiscount)+
				Integer.parseInt(propertyOnlineDiscount)+
				Integer.parseInt(propertyOfflineDiscount)+
				Integer.parseInt(tempOnlineDiscount)+
				Integer.parseInt(tempOfflineDiscount)
				)+""
				);
		//临时放行金额
		Map<String,Object> pkParam = new HashMap<String,Object>();
		pkParam.put("userId",userId);
		ClearanceRecord crecord =  clearanceRecordDao.getSumPrice(pkParam);
		if(null!=crecord){
			data.put("freeRelease","0");
		}else {
			data.put("freeRelease","0");
		}

		return data;
	}

	/**
	 * 本周收入
	 * @param userId
	 * @return
	 */
	public Map<String,Object> getWeekStatistics(String userId,String parkingIds) {
		Map<String,Object> data = new HashMap<String,Object>();
		//近七天的日期list
		int idy = 1;
		List<String> weekDate = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			calendar.add(Calendar.DATE, -1);
			idy++;
		}
		//setToFirstDay(calendar);
		for (int i = 0; i < idy; i++) {
			weekDate.add(DateUtil.date2str(calendar.getTime(),DateUtil.DATE_FORMAT));
			calendar.add(Calendar.DATE, 1);
		}
		//月租结果集
		List<Integer> mothlyWeekSale = this.getWeekSale(weekDate,"13",parkingIds);

		//产权结果集
		List<Integer> propertyWeekSale = this.getWeekSale(weekDate,"14",parkingIds);

		//临停结果集
		List<Integer> tempWeekSale = this.getWeekSale(weekDate,"11",parkingIds);
		List<String> sd = new ArrayList<String>();
		for(String s:weekDate){
			String [] sr = s.split("-");
			String str = sr[1]+"."+sr[2];
			if("0".equals(str.substring(0, 1))){
				str = str.substring(1,str.length());
			}
			sd.add(str);
		}
		data.put("xAxis",sd);
		Map<String,Object> resultMap1 = new HashMap<String,Object>();
		Map<String,Object> resultMap2 = new HashMap<String,Object>();
		Map<String,Object> resultMap3 = new HashMap<String,Object>();
		resultMap1.put("name","月租");
		resultMap1.put("data",mothlyWeekSale);
		resultMap2.put("name","产权");
		resultMap2.put("data",propertyWeekSale);
		resultMap3.put("name","临停");
		resultMap3.put("data",tempWeekSale);
		List<Map<String,Object>> dayStatisticsMap = new ArrayList<Map<String,Object>>();
		dayStatisticsMap.add(resultMap1);
		dayStatisticsMap.add(resultMap2);
		dayStatisticsMap.add(resultMap3);
		data.put("dayStatisticsMap",dayStatisticsMap);
		data.put("weekDesc","本周收入");
		int weekDateInt = 0;
		/*String weekDateStr = "";
		String monday = weekDate.get(0);
		String sunday = weekDate.get(weekDate.size());
		weekDateStr = monday.split("-")[0]+"."+this.subStrOfMonth(monday.split("-")[1])+
				this.subStrOfMonth(monday.split("-")[2])+"-"+
				this.subStrOfMonth(sunday.split("-")[1])+"."+
				this.subStrOfMonth(sunday.split("-")[2]);*/
		for(int v:mothlyWeekSale){
			weekDateInt = v+weekDateInt;
		}
		for(int v:propertyWeekSale){
			weekDateInt = v+weekDateInt;
		}
		for(int v:tempWeekSale){
			weekDateInt = v+weekDateInt;
		}
		data.put("weekDate",weekDateInt+"元");
		return data;
	}
	//根据一周时间获取本周销售数据
	public List<Integer> getWeekSale(List<String> weekDate,String orderType,String parkingIds){
		List<Integer> mothlyWeekSale = new ArrayList<Integer>();
		for(String m : weekDate){
			Map<String,String> odParam = new HashMap<String,String>();
			odParam.put("orderStatus","11");
			odParam.put("orderType",orderType);
			odParam.put("startTime", m+" 00:00:00");
			odParam.put("endTime",m+" 23:59:59");
			odParam.put("parkingIds",parkingIds);
			odParam.put("payType","'00','01','03','04','05'");
			//---------------月租
			List<OrderMain> oList = null;
			List<CarInOutRecordV2> cioList = null;
			if("13".equals(orderType)){
				oList = orderMainDao.getMonthlyOrderByParam(odParam);
			}if("14".equals(orderType)){
				oList = orderMainDao.getEquityOrderByParam(odParam);
			}if("11".equals(orderType)){
				oList = orderMainDao.getTempOrderByParam(odParam);
				//临停线下缴费
				Map<String,String> caParam = new HashMap<String,String>();
				caParam.put("startTime", m+" 00:00:00");
				caParam.put("endTime",m+" 23:59:59");
				caParam.put("parkingIds",parkingIds);
				cioList =carInOutRecordV2Dao.getTempOfflineByDate(caParam);
			}
			//Map<String,Object> weekParam = new HashMap<String,Object>();
			//String desc = m;
			int value = 0;
			if(null!=oList&&oList.size()>0&&null!=oList.get(0)){
				String sumStr = oList.get(0).getSumPaid();
				if(!StringUtils.isEmpty(sumStr)){
					value = Integer.parseInt(sumStr)/100;
				}
				if (null != cioList && cioList.size() > 0 && null != cioList.get(0)) {
					value += cioList.get(0).getRealCharge();
				}
			}else{
				if("11".equals(orderType)) {
					if (null != cioList && cioList.size() > 0 && null != cioList.get(0)) {
						value = cioList.get(0).getRealCharge();
					}
				}
			}
			mothlyWeekSale.add(value);
		}


		return mothlyWeekSale;
	}
	/**
	 *
	 * @param userId
	 * @return
	 */
	public int getMonthStatistics(String userId,String parkingIds,String orderType,String year,String month) {
		Date firstDate = null;
		Date endDate = null;
		int sum = 0;
		Calendar c = Calendar.getInstance();
		if(!StringUtils.isEmpty(year)&&!StringUtils.isEmpty(month)){
			c.set(Integer.parseInt(year),Integer.parseInt(month),1);
		}
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		firstDate = c.getTime();
		//获取当前月最后一天
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		endDate = ca.getTime();
		//查询当月信息
		Map<String,String> odParam = new HashMap<String,String>();
		odParam.put("orderStatus","11");
		odParam.put("orderType",orderType);
		odParam.put("startTime", firstDate+" 00:00:00");
		odParam.put("endTime",endDate+" 23:59:59");
		odParam.put("parkingIds",parkingIds);
		odParam.put("payType","'00','01','03','04','05'");
		//---------------月租
		List<OrderMain> oList = null;
		if("13".equals(orderType)){
			oList = orderMainDao.getMonthlyOrderByParam(odParam);
		}if("14".equals(orderType)){
			oList = orderMainDao.getEquityOrderByParam(odParam);
		}if("11".equals(orderType)){
			oList = orderMainDao.getTempOrderByParam(odParam);
		}
		if(null!=oList&&oList.size()>0&&null!=oList.get(0)){
			if("11".equals(orderType)){
				String sumStr = oList.get(0).getSumPaid();
				if(!StringUtils.isEmpty(sumStr)){
					sum = Integer.parseInt(sumStr)/100;
				}
			}else{
				String sumStr = oList.get(0).getSumPaid();//TODO
				if(!StringUtils.isEmpty(sumStr)){
					sum = Integer.parseInt(sumStr)/100;
				}
			}

		}

		return sum;
	}
}