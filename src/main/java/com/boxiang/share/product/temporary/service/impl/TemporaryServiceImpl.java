package com.boxiang.share.product.temporary.service.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import com.boxiang.share.product.car.dao.CarDao;
import com.boxiang.share.product.car.po.Car;
import com.boxiang.share.product.customer.dao.CustomerDao;
import com.boxiang.share.product.temporary.service.TemporaryService;
import com.boxiang.share.utils.ApiV2Util;
import com.boxiang.share.utils.ShangAnMessageType;
@Service("temporaryService")
public class TemporaryServiceImpl implements TemporaryService {
	//车辆
	@Resource(name="carDao")
	private CarDao carDao;
	
	@Resource(name="customerDao")
	private CustomerDao customerDao;
	/**
	 *获取临停订单账单信息 
	 */
	public String getCarlist(Map<String,Object> param) {
		// TODO Auto-generated method stub
		String mess = null;
		String customerId = (String) param.get("customerId");
		String carNumber = (String) param.get("carNumber");
	    List<Map<String,Object>> paraList = new ArrayList<Map<String,Object>>();
	    List<Map<String,Object>> paraListNull = new ArrayList<Map<String,Object>>();
		Car car = new Car();
		if(null==carNumber){//进入临停缴费首页的方法
			car.setCustomerId(customerId);
			List<Car> carList = carDao.selectList(car);
			if(null!=carList&&carList.size()>0){
				for(Car c:carList){
					JSONObject jss = null;
					Map<String,Object> paramMap = null;
					jss = getCarJss(c.getCarNumber());//查询车辆临停信息
					if(null!=jss){
						paramMap = getCarInfoMap(jss);
					}
					if(paramMap!=null){
						paraList.add(paramMap);
					}
					
				}
			}
		}else if(null!=carNumber&&null!=customerId){//查询单个车辆
			car.setCustomerId(customerId);
			car.setCustomerId(customerId);
			List<Car> carList = carDao.selectList(car);	
			if(null!=carList&&carList.size()>0){
					JSONObject jss = null;
					Map<String,Object> paramMap = null;
					jss = getCarJss(carNumber);//查询车辆临停信息
					if(null!=jss){
						paramMap = getCarInfoMap(jss);
					}
					if(paramMap!=null){
						paraList.add(paramMap);
					}
			}
		}
		if(null!=paraList&&paraList.size()>0){
			mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "cars", paraList);
		}else{
			mess = ShangAnMessageType.toShangAnJson("1", "无数据", "cars", paraListNull);
		}
	
		return mess;
	
	}
	/**
	 *获取林临停车辆信息
	 *@车牌号 
	 */
	public JSONObject getCarJss(String carNumber){
		
		ApiV2Util.before();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		Map<String, String> map=new HashMap<String, String>();
		map.put("carNumber", carNumber);
		map.put("chargeType", "A");
		HttpEntity<?> request = new HttpEntity(map, requestHeaders);
		String json=null;
		JSONObject jss = null;
		try {
			// 直接取出JSON串
			json = ApiV2Util.httpClientRestTemplate.postForObject(ApiV2Util.postPath+"/api/v2/getParklotFee", request, String.class);
			if(json!=null&&json.length()!=0){
				JSONObject js=JSONObject.fromObject(json);
				if ("FAIL".equals(js.getString("messageCode"))) {
					//mess = ShangAnMessageType.operateToJson("1", "无数据");
				} else {
					jss = js.getJSONObject("result");
				   
				}
				
			//end();
			}
		}catch (HttpStatusCodeException e) {
			//fail(e.getMessage());
			 //mess = ShangAnMessageType.operateToJson("2", "数据数据异常");
			e.printStackTrace();
			return jss;
		}
		//使用后销毁
		ApiV2Util.end(ApiV2Util.httpClientRequestFactory);
		return jss;
	}
	/**
	 * 获取临停缴费格式信息
	 * @param jss
	 * @return
	 */
	public Map<String,Object> getCarInfoMap(JSONObject jss){
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		    Map<String, Object> mess = new HashMap<String,Object>();
		    String carNum = jss.getString("carNumber");//车牌号
			String parkingId = jss.getString("parkId");//取得停车场ID
			String parkingName=jss.getString("parkName");//取得停车场名字
			String amountPayableStr = jss.getString("charge");
			int amountPayable = 0;
			if(null!=amountPayableStr){
				amountPayable = Integer.parseInt(amountPayableStr)/100;
			}
			String inTime = jss.getString("inTime");//取得订单开始时间
			String getTimes = jss.getString("getTimes");//取得订单结束时间
			Date begin = null;
			Date end = null;
			int times = 0;
			int day = 0;
			int hour = 0;
			int minute = 0;
			int second = 0;
			try {
				begin = sdf.parse(inTime);
			    end = sdf.parse(getTimes);
				times = (int) (end.getTime() - begin.getTime());
				day = times/(1000*60*60*24);
				hour = (times%(1000*60*60*24))/(1000*60*60);
				minute = (times%(1000*60*60))/(1000*60);
				second = (times%(1000*60))/1000;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			mess.put("carNumber", carNum);
			mess.put("parkId", parkingId);
			mess.put("parkingName", parkingName);
			mess.put("amountPayable", amountPayable);
			mess.put("beginDate", inTime);
			mess.put("parkingTime", day+" "+hour+":"+minute+":"+second);
		return mess;
		
	}

}
