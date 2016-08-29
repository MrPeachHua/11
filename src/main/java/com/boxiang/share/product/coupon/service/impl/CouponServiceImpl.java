package com.boxiang.share.product.coupon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.coupon.dao.CouponDao;
import com.boxiang.share.product.coupon.po.Coupon;
import com.boxiang.share.product.coupon.service.CouponService;
import com.boxiang.share.utils.ShangAnMessageType;

@DataSource(DataSourceEnum.MASTER)
@Service("coupon")
public class CouponServiceImpl implements CouponService {
	@Resource(name="couponDao")
	private CouponDao couponDao;

	@Resource
	private OrderMainService orderMainService;
	@Resource
	private ParkingService parkingService;
	@Override
	public List<Coupon> selectList(Coupon coupon) {
		return couponDao.selectList(coupon);
	}
	@Override
	public List<Coupon> selectCouponByData(Coupon coupon){
		return couponDao.selectCouponByData(coupon);
	}
	@Override
	public List<Coupon> selectCouponByCus(Coupon coupon){
		return couponDao.selectCouponByCus(coupon);
	}
	@Override
	public List<Coupon> queryCouponByOrder(Coupon coupon){
		return couponDao.queryCouponByOrder(coupon);
	}
	@Override
	public Page<Coupon> queryListPage(Page<Coupon> page) {
		page.setResultList(couponDao.queryListPage(page));
		return page;
	}
	@Override
	public List<Coupon> selectNum(Coupon coupon) {
		return couponDao.selectNum(coupon);
	}
	@Override
	public List<Coupon> selectNums(Coupon coupon) {
		return couponDao.selectNums(coupon);
	}
	@Override
	public Coupon queryById(java.lang.String id) {
		return couponDao.queryById(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(Coupon coupon) {
		couponDao.insert(coupon);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.String id) {
		couponDao.delete(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(Coupon coupon) {
		couponDao.update(coupon);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Coupon> tCouponList) {
		couponDao.batchUpdate(tCouponList);
	}
	@Override
	public List<Coupon> queryCouponByOrderId() {
		// TODO Auto-generated method stub
		return couponDao.queryCouponByOrderId();
	}
	@Override
	public List<Coupon> queryByStatusAndCustomerId(Coupon coupon) {
		return couponDao.queryByStatusAndCustomerId(coupon);
	}

	@Override
	public List<Coupon> queryList(Coupon coupon) {
		return couponDao.queryList(coupon);
	}
	@Override
	public List<Coupon> queryByCouponStatus() {
		return couponDao.queryByCouponStatus();
	}
	@Override
	public String getcouponlist(Page<Coupon> page) {
		String mess=null;
		List<Coupon> list=couponDao.getcouponlist(page);
		List <Map<String,Object>> couponList = new ArrayList<Map<String,Object>>();
		if(list!=null&&list.size()>0){
			for (Coupon coupon : list) {
				String parkingName="";
				List<String> parkingList =new ArrayList();
				if (coupon.getParkingId()!=null&&!coupon.getParkingId().equals("")) {
				String[] parkingId=coupon.getParkingId().split(",");
				for (int i = 0; i <parkingId.length ; i++) {
				 Parking parking= parkingService.queryById(parkingId[i]);
					if (parking == null) continue;
					parkingList.add(parking.getParkingName());
				}
					parkingName=	StringUtils.join(parkingList, ",");
				}
				Map<String, Object> paraMap=new HashMap<String, Object>();
				paraMap.put("couponId",null==coupon.getCouponId()?"":coupon.getCouponId());
				paraMap.put("parAmount",null==coupon.getParAmount()?"":coupon.getParAmount());
				paraMap.put("couponKind",null==coupon.getCouponKind()?"":coupon.getCouponKind());
				paraMap.put("effectiveBegin",null==coupon.getEffectiveBegin()?"":coupon.getEffectiveBegin());
				paraMap.put("effectiveEnd",null==coupon.getEffectiveEnd()?"":coupon.getEffectiveEnd());
				paraMap.put("exclusionRule",null==coupon.getExclusionRule()?"":coupon.getExclusionRule());
				paraMap.put("minconsumption",null==coupon.getMinconsumption()?"":coupon.getMinconsumption());
				paraMap.put("vouchersName",null==coupon.getVouchersName()?"":coupon.getVouchersName());
				paraMap.put("vouchersType",null==coupon.getVouchersType()?"":coupon.getVouchersType());
				paraMap.put("customerId",null==coupon.getCustomerId()?"":coupon.getCustomerId());
				paraMap.put("couponStatus",null==coupon.getCouponStatus()?"":coupon.getCouponStatus());
				paraMap.put("couponType",null==coupon.getCouponType()?"":coupon.getCouponType());
				paraMap.put("parDiscount",null==coupon.getParDiscount()?"":coupon.getParDiscount());
				paraMap.put("parkingNames",parkingName);
				couponList.add(paraMap);
			}
			
			mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "couponList", couponList);
			
		}else{
			mess = ShangAnMessageType.toShangAnJson("1", "没有更多数据", "couponList", new ArrayList<>());
		}
		
		return mess;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String cancelCoupon(String orderId, String orderType) {
		String message;
		OrderMain orderMain1 = new OrderMain();
		orderMain1.setOrderId(orderId);
		orderMain1.setOrderType(orderType);
		OrderMain orderMain = orderMainService.queryByIdAndType(orderMain1);
		if(orderMain !=null){
			Coupon coupon = new Coupon();
			coupon.setCouponOrder(orderId);
			List<Coupon> couponList = this.queryCouponByOrder(coupon);
			if(couponList !=null && couponList.size()>0){
				for(Coupon cp : couponList){
					cp.setCouponOrder("");
					cp.setUseTime("");
					cp.setCouponStatus("100201");
					this.update(cp);
				}
				orderMain.setAmountDiscount(0);
				orderMain.setAmountPaid(orderMain.getAmountPayable());
				orderMainService.update(orderMain);
				message = ShangAnMessageType.operateToJson("0", "修改成功");
			}else{
				message = ShangAnMessageType.operateToJson("1", "修改失败，找不到该订单下的优惠券");
			}

		}else{
			message = ShangAnMessageType.operateToJson("1", "修改失败，不存在该订单");
		}
		return message;
	}

}