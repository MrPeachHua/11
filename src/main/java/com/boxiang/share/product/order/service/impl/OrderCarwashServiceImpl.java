package com.boxiang.share.product.order.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.product.carlife.dao.CarlifeSrvInfoDao;
import com.boxiang.share.product.carlife.po.CarlifeSrvInfo;
import com.boxiang.share.product.carlife.service.CarlifeSrvInfoService;
import com.boxiang.share.product.order.dao.OrderMainDao;
import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.system.po.Sequence;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.LogTypeEnum;
import com.boxiang.share.utils.OrderConstants;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.TableNameConstants;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.order.dao.OrderCarwashDao;
import com.boxiang.share.product.order.po.OrderCarwash;
import com.boxiang.share.product.order.service.OrderCarwashService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.log.SystemLog;

@DataSource(DataSourceEnum.MASTER)
@Service("orderCarwash")
public class OrderCarwashServiceImpl implements OrderCarwashService {
	@Resource(name="orderCarwashDao")
	private OrderCarwashDao orderCarwashDao;
	@Resource
	private SequenceService sequenceService;
	@Resource(name="dataEhCache")
	private Cache ehCache;
	@Resource
	private OrderMainDao orderMainDao;
	@Resource
	private ParkingService parkingService;
	@Resource
	private CarlifeSrvInfoDao carlifeSrvInfoDao;
	@Override
	public List<OrderCarwash> selectList(OrderCarwash orderCarwash) {
		return orderCarwashDao.selectList(orderCarwash);
	}

	@Override
	public Page<OrderCarwash> queryListPage(Page<OrderCarwash> page) {
	    page.setResultList(orderCarwashDao.queryListPage(page));
		return page;
	}
	
	@Override
	public OrderCarwash queryById(Integer id) {
		return orderCarwashDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(OrderCarwash orderCarwash) {
		orderCarwashDao.insert(orderCarwash);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		orderCarwashDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(OrderCarwash orderCarwash) {
		orderCarwashDao.update(orderCarwash);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<OrderCarwash> orderCarwashList) {
		orderCarwashDao.batchUpdate(orderCarwashList);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
	@SystemLog(logType=LogTypeEnum.ORDER_INFO,logSummary="创建洗车订单")
	public String OrdercCarWash(Map<String, Object> param) {
		String mess=null;
		String parkingId = (String) param.get("parkingId");
		String customerId = (String) param.get("customerId");
		String orderType = (String) param.get("orderType");
		String carNumber = (String) param.get("carNumber");
		String amountPayable = (String) param.get("amountPayable");//应付
		String reserveDate = (String) param.get("reserveDate");//预约时间
		String carType = (String) param.get("carType");//
		Parking parkingEhca=new Parking();
		if(ehCache.get("t_parking"+parkingId)!=null && ehCache.get("t_parking"+parkingId).getObjectValue()!=null){
			parkingEhca= (Parking) ehCache.get("t_parking"+parkingId).getObjectValue();
		}else {
			parkingEhca = parkingService.queryById(parkingId);
			ehCache.put(new Element("carwash"+parkingId, parkingEhca));
		}
        int maxCount=0;
		List<CarlifeSrvInfo> list=carlifeSrvInfoDao.queryCharge(parkingId);
		boolean flag=true;
		if(list!=null&&list.size()>0){
			CarlifeSrvInfo carlifeSrvInfo=list.get(0);
			if(carlifeSrvInfo.getMaxCount()!=null&&!carlifeSrvInfo.getMaxCount().equals("")){
		       maxCount=Integer.parseInt(carlifeSrvInfo.getMaxCount());
			}
			OrderCarwash carwa2=new OrderCarwash();
			try {
				carwa2.setReserveDate(DateUtil.str2date(reserveDate,DateUtil.DATE_FORMAT));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int orderNow=	orderCarwashDao.selectCountByNow(carwa2);
			if(maxCount!=0){
				if(orderNow>=maxCount){
					mess = ShangAnMessageType.toShangAnJson("2", "当日预约已满,请选择其他时间！", "order", "");
					flag=false;
				}
			}else {
				flag=true;
			}
		}
		if(flag&&parkingId!=null&&customerId!=null) {
			OrderMain orderMain = new OrderMain();
			Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_ORDER_MAIN);
			int amountPayable2 = 0;
			int amountDiscount = 0;
			int amountPaid = 0;
			if(amountPayable!=null&&!amountPayable.equals("")){
				amountPayable2=Integer.parseInt(amountPayable);
			}
			orderMain.setOrderId((null==parkingEhca.getParkingIdentifier()?"":parkingEhca.getParkingIdentifier())+orderType+sequence.getId());
			orderMain.setOrderType(orderType);
			orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_UNPAY);
			orderMain.setInvoiceStatus("0");
			orderMain.setCustomerId(customerId);
			orderMain.setAmountPayable(amountPayable2);
			orderMain.setAmountDiscount(amountDiscount);
			Integer paid = amountPayable2 - amountDiscount;
			orderMain.setAmountPaid(paid < 0 ? 0 : paid);
			orderMain.setIsUsed(Constants.TRUE);
			orderMain.setCreateor("admin");
			orderMain.setCreateDate(new Date());
			try {
				orderMainDao.insert(orderMain);
			} catch (Exception e) {
				e.printStackTrace();
				mess = ShangAnMessageType.toShangAnJson("2", "添加订单失败", "order", "");
				return mess;
			}
			OrderCarwash carwash=new OrderCarwash();
			carwash.setCarType(carType);
			carwash.setOrderId(orderMain.getOrderId());
			carwash.setParkingId(parkingId);
			carwash.setCarNumber(carNumber);
			carwash.setIsUsed(Constants.TRUE);
			try {
				carwash.setReserveDate(DateUtil.str2date(reserveDate,DateUtil.DATE_FORMAT));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			carwash.setCreateor("admin");
			carwash.setCreateDate(new Date());
			orderCarwashDao.insert(carwash);
			Map<String,Object> orderMap = new HashMap<String,Object>();
			orderMap.put("orderId", orderMain.getOrderId());
			orderMap.put("orderType", orderMain.getOrderType());
			orderMap.put("orderStatus", orderMain.getOrderStatus());
			orderMap.put("amountPayable",orderMain.getAmountPayable()/100);//应付
			orderMap.put("amountDiscount", amountDiscount/100);//优惠
			orderMap.put("amountPaid",(orderMain.getAmountPayable()-amountDiscount)/100);//实付

			mess = ShangAnMessageType.toShangAnJson("0", "洗车订单创建成功", "order", orderMap);

		}
		return mess;
	}

	@Override
	public OrderCarwash queryByOrderId(String id) {
		return orderCarwashDao.queryByOrderId(id);
	}
}