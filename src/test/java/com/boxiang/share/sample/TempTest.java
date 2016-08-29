package com.boxiang.share.sample;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.boxiang.share.bluecard.po.ParklotFee;
import com.boxiang.share.bluecard.po.ParklotFeeResult;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;

public class TempTest {
	private static final Logger logger = Logger.getLogger(TempTest.class);

	@Test
	public void getPreOrNextMonth() throws ParseException {
		String d = "2016-02-01";
		int n=1;

		Date dDay= DateUtil.str2date(d, DateUtil.DATE_FORMAT);	
		System.out.println(DateUtil.getMonthStartAndEndDate(DateUtil.getPreOrNextMonth(dDay,n))[0]);
		System.out.println(DateUtil.getMonthStartAndEndDate(DateUtil.getPreOrNextMonth(dDay,n-1))[1]);
//			
//		System.out.println(DateUtil.getMonthStartAndEndDate(dDay)[0]);
//		System.out.println(DateUtil.getMonthStartAndEndDate(dDay)[1]);
	}
	@Test
	public void test5(){
		List<Map<String,Object>> paraListNull = new ArrayList<Map<String,Object>>();
		System.out.println(ShangAnMessageType.toShangAnJson("1", "无数据", "cars", paraListNull));
	}
	@Test
	public void test4(){
		try {
			Date d = DateUtil.str2date("2016/07/01", "yyyy/MM/dd");
			System.out.println(DateUtil.date2str(d, DateUtil.DATE_FORMAT));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*Map<String,String> pramMap =new HashMap<String,String>();
		pramMap.put("uua", "aa");
		pramMap.put("uuc", "aa");
		pramMap.put("uub", "aa");
		List<String> keyList = new ArrayList<String>(pramMap.keySet());
		System.out.println(keyList.toString());
		Collections.sort(keyList);
		System.out.println(keyList.toString());*/
	}
	@Test
	public void test3(){
		System.out.println(DateUtil.getCurrDate("yyyyMMddHHmmss")+StringUtils.leftPad("abc", 10,"0"));
	}
	@Test
	public void test2(){
		logger.info("start.....................");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "spring/applicationContext.xml" });
		System.out.println(ctx);
	}
	@Test
	public void test22(){
		logger.info("start.....................");
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext(
				new String[] { "D:/eclipse/works/sharework/sharecar/src/main/resources/spring/applicationContext.xml" });
		 
		System.out.println(ctx);
	}

	@Test
	public void test() {
		logger.info("start.....................");
		int endTime = Integer.valueOf("18");
		int beginTime=Integer.valueOf("08");
		if(beginTime<=endTime){
			for (int i =beginTime ; i <= endTime; i++) {
				//map.put(i, chargeUnit);
				System.out.println(i+"--------");
			}
		}else {
			for (int i =beginTime ; i >= endTime; i--) {
				//map.put(i, chargeUnit);
				System.out.println(i+"--------");
			} 
		}
		
		Date date = new Date();
		//date.setTime(1451531157000L);
		date.setTime(1451543754000L);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		System.out.println(sdf2.format(date));
		Date temp = new Date();
		try {
			temp = sdf2.parse(sdf2.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double c = (date.getTime() % 3600000) / 3600000.0;
		long back = (date.getTime() - temp.getTime()) / 3600000;
		if (c > (1 - 15 / 60.0)) {
			System.out.println( (back + 1));
		} else {
			System.out.println( back);
		}
		//System.out.println(sdf2.format(date));
		//double price = 20d;
	}

}
