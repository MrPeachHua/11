package com.boxiang.share.product.order.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.product.order.po.Monthlyparkinginfo;
import com.boxiang.share.product.order.po.Propertyparkinginfo;
import com.boxiang.share.product.order.service.MonthlyparkinginfoService;
import com.boxiang.share.product.order.service.PropertyparkinginfoService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.bluecard.po.ParklotFee;
import com.boxiang.share.bluecard.po.ParklotFeeResult;
import com.boxiang.share.product.car.dao.CarDao;
import com.boxiang.share.product.car.po.Car;
import com.boxiang.share.product.customer.dao.CustomerDao;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.order.dao.OrderTemporaryDao;
import com.boxiang.share.product.order.po.OrderTemporary;
import com.boxiang.share.product.order.service.OrderTemporaryService;
import com.boxiang.share.product.parking.dao.ParkingDao;
import com.boxiang.share.product.parking.dao.ParkingVoucherDao;
import com.boxiang.share.product.parking.dao.VillageinfoDao;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.po.ParkingVoucher;
import com.boxiang.share.product.parking.po.Villageinfo;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;
import com.fasterxml.jackson.databind.ObjectMapper;

@DataSource(DataSourceEnum.MASTER)
@Service("orderTemporary")
public class OrderTemporaryServiceImpl implements OrderTemporaryService {
	private static final Logger logger = Logger.getLogger(OrderTemporaryServiceImpl.class);
	@Resource(name="orderTemporaryDao")
	private OrderTemporaryDao orderTemporaryDao;
	//车辆
	@Resource(name="carDao")
	private CarDao carDao;
	
	
	@Resource(name="villageinfoDao")
	private VillageinfoDao villageinfoDao;

	@Resource(name="parkingDao")
	private ParkingDao parkingDao;
	
	@Resource
	private String APIKey;
	@Resource
	private String parklotFee;
	@Resource
	private String outputCarImage;
	// json处理对象
	private static ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private RestTemplate restTemplate;
	
	@Resource
	private CustomerDao customerDao;
	
	@Resource
	private ParkingVoucherDao parkingVoucherDao;
	@Resource
	private MonthlyparkinginfoService monthlyparkinginfoService;
	@Resource
	private PropertyparkinginfoService propertyparkinginfoService;
	@Override
	public List<OrderTemporary> selectList(OrderTemporary orderTemporary) {
		return orderTemporaryDao.selectList(orderTemporary);
	}

	@Override
	public Page<OrderTemporary> queryListPage(Page<OrderTemporary> page) {
	    page.setResultList(orderTemporaryDao.queryListPage(page));
		return page;
	}
	
	@Override
	public OrderTemporary queryById(java.lang.Integer id) {
		return orderTemporaryDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(OrderTemporary orderTemporary) {
		orderTemporaryDao.insert(orderTemporary);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		orderTemporaryDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(OrderTemporary orderTemporary) {
		orderTemporaryDao.update(orderTemporary);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<OrderTemporary> orderTemporaryList) {
		orderTemporaryDao.batchUpdate(orderTemporaryList);
	}

	@Override
	public OrderTemporary queryByOrderId(String id) {
		return orderTemporaryDao.queryByOrderId(id);
	}

	@Override
	public String getCarlist(String customerId,String carNumber){
		List<Map<String,Object>> paraList = new Vector<Map<String,Object>>();
		//查询用户
		String identity = "0";
		Customer cu = new Customer();
		cu.setCustomerId(customerId);
		List<Customer> cuList = customerDao.selectList(cu);
		if(null!=cuList&&cuList.size()>0){
			identity = cuList.get(0).getIdentity();
		}else{
			logger.error("用户"+customerId+"不存在");
			return ShangAnMessageType.toShangAnJson("2", "用户不存在", "cars", paraList);
		}
		if(StringUtils.isEmpty(carNumber)){//进入临停缴费首页显示绑定车辆
			if(StringUtils.isEmpty(customerId)){
				// 参数不能为空
				return ShangAnMessageType.toShangAnJson("2", "客户ID参数不能为空", "cars", paraList);
			}
			
			Car car = new Car();
			car.setCustomerId(customerId);
			List<Car> carList = carDao.selectList(car);
			
			if(carList!=null && carList.size()>0){
				ExecutorService executor = Executors.newFixedThreadPool(carList.size());
				long b = System.currentTimeMillis();
				for(Car c:carList){		              
					executor.execute(new CarParklotFee(c.getCarNumber(),paraList,identity,customerId));
				}
				// 关闭启动线程
				executor.shutdown();
		        // 等待子线程结束，再继续执行下面的代码
				try {
					executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				logger.info("all thread complete:"+(System.currentTimeMillis()-b));
			}
		}else {
			// 根据车牌查询
			ExecutorService executor = Executors.newSingleThreadExecutor();
			long b = System.currentTimeMillis();
			executor.execute(new CarParklotFee(carNumber,paraList,identity,customerId));
			// 关闭启动线程
			executor.shutdown();
	        // 等待子线程结束，再继续执行下面的代码
			try {
				executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("all thread complete:"+(System.currentTimeMillis()-b));
		}
		return ShangAnMessageType.toShangAnJson("0", "查询成功", "cars", paraList);
	}
	
	
	public class CarParklotFee implements Runnable {
		private String carNumber;
		List<Map<String,Object>> paraList;
		String identify;
		String customerId;
		public CarParklotFee(String carNumber,List<Map<String,Object>> paraList,String identify,String customerId){
			this.carNumber = carNumber;
			this.paraList = paraList;
			this.identify = identify;
			this.customerId = customerId;
		}
		public void run(){
			//0:无凭证1:有凭证
			String isVoucher = "0";
			String parkingId = "";
			//查出车场
			ParklotFeeResult plt = sendJsonDataToRest(new ParklotFee(APIKey,carNumber));
			if (plt.getStatus() == null || (!plt.getStatus().equals("success")) || StringUtils.isEmpty(plt.getDatas().getParkId())) {
				logger.debug("-----调蓝卡云返回失败1---OrderTemporaryServiceImpl");
				return;
			}
			Villageinfo villageinfo1 = new Villageinfo();
			villageinfo1.setItem01(plt.getDatas().getParkId());//蓝卡车场ID
			List<Villageinfo> list1 = villageinfoDao.selectList(villageinfo1);
			if(!list1.isEmpty() && list1.size()>0){
				villageinfo1 = list1.get(0);
				Parking parking = parkingDao.queryById(villageinfo1.getId());
				if(null!=parking){
					parkingId = parking.getParkingId();
				}
			}
			//查询改用沪是否有凭证
			ParkingVoucher parkingVoucher = new ParkingVoucher();
			parkingVoucher.setCustomerId(customerId);
			parkingVoucher.setCarNumber(carNumber);
			parkingVoucher.setPvStatus("0");
			parkingVoucher.setParkingId(parkingId);
			List<ParkingVoucher> pvList = parkingVoucherDao.selectList(parkingVoucher);
			if(null!=pvList&&pvList.size()>0){
				isVoucher = "1";
			}
			ParklotFeeResult parklotFeeResult =null;
			if("1".equals(identify)){
				 parklotFeeResult = sendJsonDataToRest(new ParklotFee(APIKey,carNumber,"B"));
			}else{
				if("1".equals(isVoucher)){
					parklotFeeResult = sendJsonDataToRest(new ParklotFee(APIKey,carNumber,"A"));
				}else{
					parklotFeeResult = sendJsonDataToRest(new ParklotFee(APIKey,carNumber));
				}
			}
			logger.debug(parklotFeeResult.toString());
			if (parklotFeeResult.getStatus() == null || (!parklotFeeResult.getStatus().equals("success"))) {
				logger.debug("-----调蓝卡云返回失败2---OrderTemporaryServiceImpl");
				return;
			}
			if ((parklotFeeResult != null) && (parklotFeeResult.getDatas() != null)
					&& (parklotFeeResult.getDatas().getOrderId() != null)) {
				Map<String, Object> mess = new HashMap<String,Object>();
				mess.put("carNumber", carNumber);
				
				// 获取在本系统内的车场ID和车场名
				Villageinfo villageinfo = new Villageinfo();
				villageinfo.setItem01(parklotFeeResult.getDatas().getParkId());//蓝卡车场ID
				List<Villageinfo> list = villageinfoDao.selectList(villageinfo);
				Parking parking=null;
				if(!list.isEmpty() && list.size()>0){
					villageinfo = list.get(0);
					 parking = parkingDao.queryById(villageinfo.getId());
					//过滤
					if("0".equals(parking.getIsOpenPayment())){
						return;
					}
					// 本系统内的车场ID
					mess.put("parkingId", parking.getParkingId());
					// 本系统内的车场名

					mess.put("parkingName", parking.getParkingName());
				}
				String payCharge = StringUtils.isEmpty(parklotFeeResult.getDatas().getPayCharge()) || parklotFeeResult.getDatas().getPayCharge().trim().equals("null") ? parklotFeeResult.getDatas().getCharge() : parklotFeeResult.getDatas().getPayCharge();
				logger.debug("蓝卡云端返回应缴费用：{"+ payCharge +"}");
				int amountPayable = Integer.parseInt(payCharge)/100;
				mess.put("amountPayable", amountPayable);
				//进场时间
				Date beginDate;
				//算费时间
				Date endDate;
				if(parking!=null){
					Monthlyparkinginfo monthlyparkinginfo =new Monthlyparkinginfo();
					monthlyparkinginfo.setCarNumber(carNumber);
					monthlyparkinginfo.setVillageId(parking.getParkingId());
					monthlyparkinginfo.setIsUsed(Constants.TRUE);
					List<Monthlyparkinginfo> list6=   monthlyparkinginfoService.selectList(monthlyparkinginfo);
					Propertyparkinginfo propertyparkinginfo=new Propertyparkinginfo();
					propertyparkinginfo.setCarNumber(carNumber);
					propertyparkinginfo.setVillageId(parking.getParkingId());
					propertyparkinginfo.setIsUsed(Constants.TRUE);
					List<Propertyparkinginfo> list7= propertyparkinginfoService.selectList(propertyparkinginfo);
					if ((list6!=null&&list6.size()>0)||(list7!=null&&list7.size()>0)){
					}else {
						try {
							beginDate = DateUtil.str2date(parklotFeeResult.getDatas().getInTime(), DateUtil.DATETIME_FORMAT);
							endDate =DateUtil.str2date(parklotFeeResult.getDatas().getGetTimes(), DateUtil.DATETIME_FORMAT);
							long times = endDate.getTime() - beginDate.getTime();
							long day = times/(1000*60*60*24);
							long hour = (times%(1000*60*60*24))/(1000*60*60);
							long hour1 = times/(1000*60*60);
							long minute = (times%(1000*60*60))/(1000*60);
							long second = (times%(1000*60))/1000;
							//mess.put("parkingTime", hour1+":"+minute);
							mess.put("parkingTime", day+"天"+hour+"时"+minute+"分"+second+"秒");
							//截取为yyyy-MM-dd HH:mi
							mess.put("beginDate", parklotFeeResult.getDatas().getInTime().substring(0, parklotFeeResult.getDatas().getInTime().length()-3));
							paraList.add(mess);
						} catch (ParseException e) {
							e.printStackTrace();
							logger.error("",e);
						}
					}
				}
				//String carPhoto = getCarPhotoByParklotFeeResult(parklotFeeResult);// 获取照片流
				/*
				parklotFeeRe = new ParklotFeeRe();
				parklotFeeRe.setStatus(parklotFeeResult.getStatus());
				parklotFeeRe.setErrorCode(parklotFeeResult.getErrorCode());
				parklotFeeRe.setBlueCardOrderId(parklotFeeResult.getDatas().getOrderId());
				parklotFeeRe.setBlueCardParkId(parklotFeeResult.getDatas().getParkId());
				parklotFeeRe.setCarNumber(parklotFeeResult.getDatas().getPlateId());
				parklotFeeRe.setCarType(parklotFeeResult.getDatas().getCarType());
				parklotFeeRe.setBlueCardParkName(parklotFeeResult.getDatas().getParkName());
				parklotFeeRe.setInTime(parklotFeeResult.getDatas().getInTime());
				parklotFeeRe.setGetTimes(parklotFeeResult.getDatas().getGetTimes());*/
			} else {
				// 获取蓝卡数据失败
				logger.warn("获取蓝卡数据失败::::::::status::" + parklotFeeResult.getStatus());
				logger.warn("获取蓝卡数据失败::::::::errorCode::" + parklotFeeResult.getErrorCode());
			}
		}
	}
	/**
	 * 根据传入的parklotFeeResult对象,拼装出carPhoto字段
	 * 
	 * @param
	 */
	@SuppressWarnings("unused")
	private String getCarPhotoByParklotFeeResult(ParklotFeeResult parklotFeeResult) {
		logger.info("into sendJsonDataToRestGetcarPhoto()...");
		String carPhoto = "";
		if ((parklotFeeResult != null) && "success".equals(parklotFeeResult.getStatus())
				&& (parklotFeeResult.getDatas().getParkId() != null)) {
			carPhoto = String.format(outputCarImage, parklotFeeResult.getDatas().getParkId(),
					parklotFeeResult.getDatas().getOrderId());
			logger.info("carPhoto:{"+carPhoto+"}");
		} 
		return carPhoto;
	}

	/**
	 * 根据传入的ParklotFee对象，处理成json数据，调用post方法发送
	 * 
	 * @param parklotFee
	 */
	private ParklotFeeResult sendJsonDataToRest(ParklotFee parklotFee) {
		logger.info("into sendJsonDataToRest()...");
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ParklotFee> request = new HttpEntity<ParklotFee>(parklotFee, requestHeaders);
		try {
			// 返回结果处理
			String resultJson = restTemplate.postForObject(this.parklotFee, request, String.class);

			logger.info("调用蓝卡云算费接口-resultJson:" + resultJson);

			ParklotFeeResult result = mapper.readValue(resultJson, ParklotFeeResult.class);

			logger.info("调用蓝卡云算费接口-result:" + result.toString());

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("停车场算费接口rest远程调用失败:" + e.getMessage());
		}
		return null;
	}
	
}