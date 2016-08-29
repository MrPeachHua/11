package com.boxiang.share.product.order.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.boxiang.share.bluecard.po.ParklotFee;
import com.boxiang.share.bluecard.po.ParklotFeeResult;
import com.boxiang.share.bluecard.po.PayStatusSync;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.json.JsonMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.order.dao.OrderTemporaryShareDao;
import com.boxiang.share.product.order.po.OrderTemporaryShare;
import com.boxiang.share.product.order.service.OrderTemporaryShareService;
import com.boxiang.share.utils.ShangAnMessageType;

@DataSource(DataSourceEnum.MASTER)
@Service("orderTemporaryShare")
public class OrderTemporaryShareServiceImpl implements OrderTemporaryShareService {

	private static final Logger logger = Logger.getLogger(OrderTemporaryShareServiceImpl.class);

	@Resource(name="orderTemporaryShareDao")
	private OrderTemporaryShareDao orderTemporaryShareDao;

	@Resource
	private OrderMainService orderMainService;

	@Override
	public List<OrderTemporaryShare> selectList(OrderTemporaryShare orderTemporaryShare) {
		return orderTemporaryShareDao.selectList(orderTemporaryShare);
	}

	@Override
	public Page<OrderTemporaryShare> queryListPage(Page<OrderTemporaryShare> page) {
	    page.setResultList(orderTemporaryShareDao.queryListPage(page));
		return page;
	}

	@Override
	public OrderTemporaryShare queryById(java.lang.Integer id) {
		return orderTemporaryShareDao.queryById(id);
	}
	@Override
	public OrderTemporaryShare selectByOrderId(Map<String,String> map){
		return orderTemporaryShareDao.selectByOrderId(map);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(OrderTemporaryShare orderTemporaryShare) {
		orderTemporaryShareDao.insert(orderTemporaryShare);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		orderTemporaryShareDao.delete(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(OrderTemporaryShare orderTemporaryShare) {
		orderTemporaryShareDao.update(orderTemporaryShare);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<OrderTemporaryShare> orderTemporaryShareList) {
		orderTemporaryShareDao.batchUpdate(orderTemporaryShareList);
	}
@Override
@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
public void updateByOrderId(OrderTemporaryShare orderTemporaryShare){
	orderTemporaryShareDao.updateByOrderId(orderTemporaryShare);
}
@Override
public OrderTemporaryShare queryByOrderId(String id) {
	return orderTemporaryShareDao.queryByOrderId(id);
}
	@Override
	public List<OrderTemporaryShare> selectTime(OrderTemporaryShare orderTemporaryShare){
		return orderTemporaryShareDao.selectTime(orderTemporaryShare);
	}
@Override
public String queryVoucherPage(Page<OrderTemporaryShare> page) {
	String mess=null;
	List <Map<String,Object>> temporaryList = new ArrayList<Map<String,Object>>();
	List<OrderTemporaryShare> list=orderTemporaryShareDao.queryVoucherPage(page);
	if(list!=null&&list.size()>0){
		for (OrderTemporaryShare orderTemporaryShare:list) {
			if(orderTemporaryShare !=null)
			{
				Map<String,Object> paraMap = new HashMap<String,Object>();
				paraMap.put("mondayBeginTime",orderTemporaryShare.getMondayBeginTime());
				paraMap.put("mondayEndTime",orderTemporaryShare.getMondayEndTime());
				paraMap.put("fridayBeginTime",orderTemporaryShare.getFridayBeginTime());
				paraMap.put("fridayEndTime",orderTemporaryShare.getFridayEndTime());
				paraMap.put("saturdayBeginTime",orderTemporaryShare.getSaturdayBeginTime());
				paraMap.put("saturdayEndTime",orderTemporaryShare.getSaturdayEndTime());
				paraMap.put("sundayBeginTime",orderTemporaryShare.getSundayBeginTime());
				paraMap.put("sundayEndTime",orderTemporaryShare.getSundayEndTime());
				paraMap.put("thursdayBeginTime",orderTemporaryShare.getThursdayBeginTime());
				paraMap.put("thursdayEndTime",orderTemporaryShare.getThursdayEndTime());
				paraMap.put("tuesdayBeginTime",orderTemporaryShare.getTuesdayBeginTime());
				paraMap.put("tuesdayEndTime",orderTemporaryShare.getTuesdayEndTime());
				paraMap.put("wednesdayBeginTime",orderTemporaryShare.getWednesdayBeginTime());
				paraMap.put("wednesdayEndTime",orderTemporaryShare.getWednesdayEndTime());
				Object object = null;
				if (orderTemporaryShare.getStartEndTime()!=null)
				{
					try {
						object = JsonMapper.fromJson(orderTemporaryShare.getStartEndTime(), Map.class);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				paraMap.put("startEndTime",object);
				paraMap.put("carNumber",null==orderTemporaryShare.getCarNumber()?"":orderTemporaryShare.getCarNumber());
				paraMap.put("appointmentDate",null==orderTemporaryShare.getAppointmentDate()?"":orderTemporaryShare.getAppointmentDate());
				paraMap.put("parkingCode",null==orderTemporaryShare.getParkingCode()?"":orderTemporaryShare.getParkingCode());
				paraMap.put("parkingName",null==orderTemporaryShare.getParkingName()?"":orderTemporaryShare.getParkingName());
				paraMap.put("startTime",null==orderTemporaryShare.getStartTime()?"":orderTemporaryShare.getStartTime());
				paraMap.put("stopTime",null==orderTemporaryShare.getStopTime()?"":orderTemporaryShare.getStopTime());
				paraMap.put("orderId",null==orderTemporaryShare.getOrderId()?"":orderTemporaryShare.getOrderId());
				paraMap.put("parkingId",null==orderTemporaryShare.getParkingId()?"":orderTemporaryShare.getParkingId());
				paraMap.put("payTime",null==orderTemporaryShare.getPayTime()?"":DateUtil.date2str(orderTemporaryShare.getPayTime(), DateUtil.DATETIME_FORMAT));
				temporaryList.add(paraMap);
			}

		}
		mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "carwashList", temporaryList);
	}else{
		mess = ShangAnMessageType.operateToJson("1", "无数据");
	}
	return mess;
}

	@Override
	public void updateParking(OrderTemporaryShare orderTemporaryShare) {
		orderTemporaryShareDao.updateParking(orderTemporaryShare);
	}

	@Override
	public List<OrderTemporaryShare> queryDate(OrderTemporaryShare orderTemporaryShare) {
		return orderTemporaryShareDao.queryDate(orderTemporaryShare);
	}

	@Override
	public List<OrderTemporaryShare> selectCountToday(OrderTemporaryShare orderTemporaryShare) {
		return orderTemporaryShareDao.selectCountToday(orderTemporaryShare);
	}

	@Override
 public OrderTemporaryShare scanOrderId(OrderTemporaryShare orderTemporaryShare)
	{
		return orderTemporaryShareDao.scanOrderId(orderTemporaryShare);
	}
	@Override
	public String selectAppByToday(Page<OrderTemporaryShare> page){
		String mess = null;
		try {
			List<OrderTemporaryShare> list = orderTemporaryShareDao.selectAppByToday(page);
			List <Map<String,Object>> osList = new ArrayList<Map<String,Object>>();
			if (list !=null && list.size()>0){
				for (OrderTemporaryShare orderTemporaryShare:list) {
//					if(orderTemporaryShare !=null && orderTemporaryShare.getAmountDiscount()!=null && orderTemporaryShare.getAppointmentDate().contains(new SimpleDateFormat("yyyy-MM-dd").format(new Date())))
//					{
						Map<String,Object> paraMap = new HashMap<String,Object>();
						paraMap.put("carNumber",null==orderTemporaryShare.getCarNumber()?"":orderTemporaryShare.getCarNumber());
						paraMap.put("appointmentDate",null==orderTemporaryShare.getAppointmentDate()?"":orderTemporaryShare.getAppointmentDate());
						paraMap.put("parkingCode",null==orderTemporaryShare.getParkingCode()?"":orderTemporaryShare.getParkingCode());
						paraMap.put("parkingName",null==orderTemporaryShare.getParkingName()?"":orderTemporaryShare.getParkingName());
						paraMap.put("startTime",null==orderTemporaryShare.getStartTime()?"":orderTemporaryShare.getStartTime());
						paraMap.put("stopTime",null==orderTemporaryShare.getStopTime()?"":orderTemporaryShare.getStopTime());
						paraMap.put("orderId",null==orderTemporaryShare.getOrderId()?"":orderTemporaryShare.getOrderId());
						osList.add(paraMap);
//					}
				}
				if (osList.size()>0)
					mess = ShangAnMessageType.toShangAnJson("0","查询成功","orderTemporaryShareList",osList);
				else
					mess = ShangAnMessageType.toShangAnJson("1","无数据","orderTemporaryShareList","");
			}else {
				mess = ShangAnMessageType.toShangAnJson("1","无数据","orderTemporaryShareList","");
			}
		}catch (Exception e){
			mess = ShangAnMessageType.operateToJson("2", "查询失败");
		}
		return  mess;
	}
@Override
public String queryAppointment(String parkingId,String carNumber,String appointmentDate) {
	String mess=ShangAnMessageType.operateToJson("0", "预约日期可用");
	OrderTemporaryShare orderTemporaryShare=new OrderTemporaryShare();
	orderTemporaryShare.setParkingId(parkingId);
	orderTemporaryShare.setCarNumber(carNumber);
	List<OrderTemporaryShare> list = orderTemporaryShareDao.queryAppointment(orderTemporaryShare);
	if(list !=null){
		for (OrderTemporaryShare orderTemporaryShare1 :list){
			if(orderTemporaryShare1 !=null && orderTemporaryShare1.getAppointmentDate() !=null){
				for (int i=0;i<orderTemporaryShare1.getAppointmentDate().split(",").length;i++){
					if(appointmentDate.contains(orderTemporaryShare1.getAppointmentDate().split(",")[i])){
						mess = ShangAnMessageType.toShangAnJson("1", "预约日期重复", "appointmentDate", orderTemporaryShare1.getAppointmentDate().split(",")[i]);
					}
					break;
				}
//				break;
			}
		}
	}
	return mess;
	/*if(orderTemporaryShareDao.queryAppointment(orderTemporaryShare)!=null)
	{
		for(OrderTemporaryShare os : orderTemporaryShareDao.queryAppointment(orderTemporaryShare)){
			if(os !=null && os.getAppointmentDate()!=null && appointmentDate.contains(os.getAppointmentDate().split(",")[0])){
				mess=ShangAnMessageType.toShangAnJson("0", "预约日期重复", "appointmentDate", appointmentDate);
				break;
			}
		}
	}
	return mess;*/
}

	/**
	 * 定时任务,共享临停订单 通知蓝卡抬杆
	 */
	@Override
    public synchronized boolean timeTaskForCommitToBlue(int minutes) throws Exception {
        List<OrderTemporaryShare> list = orderTemporaryShareDao.getCommitToBlueOrder();
        if (list == null || list.size() == 0) {
            return true;
        }
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < list.size(); i++) {
            final OrderTemporaryShare ots = list.get(i);
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sendToBlue(ots);
                }
            });
        }
        executor.shutdown();
        return executor.awaitTermination(minutes, TimeUnit.MINUTES);
    }

	/**
	 * 发送请求到蓝卡,要他抬杆
	 */
	private void sendToBlue(OrderTemporaryShare ots) {
		try {
			// 拿到套餐A的价格
			ParklotFeeResult parklotFeeResult = orderMainService.sendJsonDataToRest(new ParklotFee(null, ots.getCarNumber(), "A"));
			if (parklotFeeResult == null || parklotFeeResult.getStatus() == null || (!parklotFeeResult.getStatus().equals("success"))) {
                return;
            }
			// 拿到套餐A的价格
			String payCharge = StringUtils.isEmpty(parklotFeeResult.getDatas().getPayCharge()) || parklotFeeResult.getDatas().getPayCharge().trim().equals("null") ? parklotFeeResult.getDatas().getCharge() : parklotFeeResult.getDatas().getPayCharge();
			// 告诉蓝卡云已经付款
			PayStatusSync payStatusSync = new PayStatusSync();
			payStatusSync.setPlateId(ots.getCarNumber());// 车牌号
			payStatusSync.setPayAmount(payCharge);// 应付金额
			payStatusSync.setPayTime(ots.getOrderMain().getPayTime() == null ? DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT) : DateUtil.date2str(ots.getOrderMain().getPayTime(), DateUtil.DATETIME_FORMAT));// 支付时间
			payStatusSync.setGetTime(DateUtil.date2str(ots.getOrderMain().getCreateDate(), DateUtil.DATETIME_FORMAT));// 算费时间
			payStatusSync.setPayType(ots.getOrderMain().getPayType());// 支付类型
			payStatusSync.setPayNo(ots.getOrderId());// 订单交易流水号
			String resultJson = orderMainService.backForTempOrder(payStatusSync);
			Map<String, Object> map = (Map) JsonMapper.fromJson(resultJson, Map.class);
			if (map.get("status").toString().equals("success")) {
                // 修改订单状态
                orderTemporaryShareDao.updateIsPushToBlue(ots.getOrderId());
            }
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}