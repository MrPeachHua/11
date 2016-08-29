package com.boxiang.share.product.carlife.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.carlife.dao.CarlifeRefuelCardDao;
import com.boxiang.share.product.carlife.dao.OrderRefuelDao;
import com.boxiang.share.product.carlife.po.CarlifeRefuelCard;
import com.boxiang.share.product.carlife.po.OrderRefuel;
import com.boxiang.share.product.carlife.po.Refuel;
import com.boxiang.share.product.carlife.service.OrderRefuelService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.HttpUtil;
import com.boxiang.share.utils.MD5Util;
import com.boxiang.share.utils.ShangAnMessageType;

@DataSource(DataSourceEnum.MASTER)
@Service("orderRefuel")
public class OrderRefuelServiceImpl implements OrderRefuelService {

	@Resource(name="orderRefuelDao")
	private OrderRefuelDao orderRefuelDao;

	@Resource(name="carlifeRefuelCardDao")
	private CarlifeRefuelCardDao carlifeRefuelCardDao;

	@Override
	public List<OrderRefuel> selectList(OrderRefuel orderRefuel) {
		return orderRefuelDao.selectList(orderRefuel);
	}

	@Override
	public Page<OrderRefuel> queryListPage(Page<OrderRefuel> page) {
	    page.setResultList(orderRefuelDao.queryListPage(page));
		return page;
	}
	
	@Override
	public OrderRefuel queryById(java.lang.Integer id) {
		return orderRefuelDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(OrderRefuel tOrderRefuel) {
		orderRefuelDao.insert(tOrderRefuel);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		orderRefuelDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(OrderRefuel tOrderRefuel) {
		orderRefuelDao.update(tOrderRefuel);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<OrderRefuel> orderRefuelList) {
		orderRefuelDao.batchUpdate(orderRefuelList);
	}
  /**
   * 获取加油卡订单列表用于定时任务
   * @param map param
   * @return string mess
   */
  @Override
  public List<OrderRefuel> selectListForQuartz(Map<String,Object> param){
  	return orderRefuelDao.selectListForQuartz(param);
  }
  @Override
  public String getRefuelList(String customerId, String pageIndex) {
	String mess=null;
	//Map<String,Object> refuelMap = new HashMap<String,Object>();
	 Page<OrderRefuel> page=new Page<OrderRefuel>();
	 page.getParams().put("customerId", customerId);
	 long pageIndex2 = 1;
	 if(customerId!=null&&pageIndex!=null){
		 pageIndex2= (long)Integer.parseInt(pageIndex);
			page.setCurrentPage(pageIndex2);
			page.setPageSize(Page.PAGE_SIZE_10);
			List<Refuel> list=	orderRefuelDao.queryListByRefuelPage(page);
			List <Map<String,Object>> refuelList = new ArrayList <Map<String,Object>>();
			if(list!=null&&list.size()>0){
				 for (Refuel refuel : list) {
					 Map<String,Object> refuelMap = new HashMap<String,Object>();
					 refuelMap.put("orderId",(null!=refuel.getOrderId())?refuel.getOrderId():"");
					 refuelMap.put("cardType",(null!=refuel.getCardType())?refuel.getCardType():"");
					 refuelMap.put("cardNo",(null!=refuel.getCardNo())?refuel.getCardNo():"");
					 refuelMap.put("cardName",(null!=refuel.getCardName())?refuel.getCardName():"");
					 refuelMap.put("cardMobile",(null!=refuel.getCardMobile())?refuel.getCardMobile():"");
					 refuelMap.put("payTime",(null!=refuel.getPayTime())?DateUtil.date2str(refuel.getPayTime(), "yyyy-MM-dd HH:mm:ss"):"");
					 float amountPayable = refuel.getAmountPayable();
					 float amountDiscount = refuel.getAmountDiscount();
					 float amountPaid = refuel.getAmountPaid();
					 refuelMap.put("amountPayable", amountPayable/100);
					 refuelMap.put("amountDiscount", amountDiscount/100);
					 refuelMap.put("amountPaid",amountPaid/100 );
					 
					 refuelList.add(refuelMap);
				}
					mess = ShangAnMessageType.toShangAnJson("0", "订单列表", "order", refuelList);
			 }else {
					mess = ShangAnMessageType.toShangAnJson("1", "无数据", "order", "[]");
			 }
			}
	return mess;
  }

	@Override
	public Map queryRefuelByOrderId(String orderId) {
		Refuel refuel = orderRefuelDao.queryRefuelByOrderId(orderId);
		Map<String, Object> refuelMap = new HashMap<String, Object>();
		if (refuel != null) {
			refuelMap.put("orderId", (null != refuel.getOrderId()) ? refuel.getOrderId() : "");
			refuelMap.put("cardType", (null != refuel.getCardType()) ? refuel.getCardType() : "");
			refuelMap.put("cardNo", (null != refuel.getCardNo()) ? refuel.getCardNo() : "");
			refuelMap.put("cardName", (null != refuel.getCardName()) ? refuel.getCardName() : "");
			refuelMap.put("cardMobile", (null != refuel.getCardMobile()) ? refuel.getCardMobile() : "");
			refuelMap.put("payTime", (null != refuel.getPayTime()) ? DateUtil.date2str(refuel.getPayTime(), "yyyy-MM-dd HH:mm:ss") : "");
			float amountPayable = refuel.getAmountPayable();
			float amountDiscount = refuel.getAmountDiscount();
			float amountPaid = refuel.getAmountPaid();
			refuelMap.put("amountPayable", amountPayable / 100);
			refuelMap.put("amountDiscount", amountDiscount / 100);
			refuelMap.put("amountPaid", amountPaid / 100);
		}
		return refuelMap;
	}

	private static final String appKey = "22c27f25ec197caecd8c0a6600656c53"; // 聚合api的appKey
	private static final String openID = "JHe0c56045ca2602e15f7c3dc8cdd63f03"; // 聚合api的openID

	/**
	 * 调用聚合API充值加油卡
	 * @param orderId 订单ID
	 * @return
	 */
	@Override
	public String rechargeByJuheAPI(String orderId) throws Exception {
		String result = null;

		// 查询加油卡订单表
		OrderRefuel orderRefuel = new OrderRefuel();
		orderRefuel.setOrderId(orderId);
		orderRefuel.setIsUsed(Constants.TRUE);
		List<OrderRefuel> list = orderRefuelDao.selectList(orderRefuel);
		if (list == null || list.size() == 0) {
			result = ShangAnMessageType.operateToJson("1", "无数据");
			return result;
		}
		orderRefuel = list.get(0);

		// 查询加油卡信息表
		CarlifeRefuelCard carlifeRefuelCard = carlifeRefuelCardDao.queryById(orderRefuel.getCardId());
		if (carlifeRefuelCard == null) {
			result = ShangAnMessageType.operateToJson("1", "无数据");
			return result;
		}

		final String url = "http://op.juhe.cn/ofpay/sinopec/onlineorder";//请求接口地址
		String proid = orderRefuel.getTopupType();
		String cardnum = proid.equals("10007") || proid.equals("10008") ? Integer.toString(orderRefuel.getTopupPrice() / 100) : "1";
		String game_userid = carlifeRefuelCard.getCardNo();
		String sign = MD5Util.md5(openID + appKey + proid + cardnum + game_userid + orderId);

		Map<String, String> params = new HashMap<String, String>();//请求参数
		params.put("proid", proid);//产品id:10000(中石化50元加油卡)、10001(中石化100元加油卡)、10003(中石化500元加油卡)、10004(中石化1000元加油卡)、10007(中石化任意金额充值)、10008(中石油任意金额充值)
		params.put("cardnum", cardnum);//充值数量 任意充 （整数（元）），其余面值固定值为1
		params.put("orderid", orderId);//商家订单号，8-32位字母数字组合
		params.put("game_userid", game_userid);//加油卡卡号，中石化：以100011开头的卡号、中石油：以9开头的卡号
		params.put("gasCardTel", carlifeRefuelCard.getCardMobile());//持卡人手机号码
//		params.put("gasCardName", carlifeRefuelCard.getCardName());//持卡人姓名
		if  (game_userid.startsWith("9")) { // 中石油：以9开头的卡号
			params.put("chargeType", "2");//加油卡类型 （1:中石化、2:中石油；默认为1)
		}
		params.put("key", appKey);//应用APPKEY(应用详细页查询)
		params.put("sign", sign);//校验值，md5(OpenID+key+proid+cardnum+game_userid+orderid)，OpenID在个人中心查询

		result = HttpUtil.net(url, params, "GET");

		return result;
	}

	/**
	 * 调用聚合API查询订单信息
	 * @param orderId
	 * @return
	 */
	public String getOrderStateByJuheAPI(String orderId) throws Exception {
		final String url = "http://op.juhe.cn/ofpay/sinopec/ordersta";//请求接口地址
		Map<String, String> params = new HashMap<String, String>();//请求参数
		params.put("key", appKey);
		params.put("orderid", orderId);
		return HttpUtil.net(url, params, "GET");
	}

	@Override
	public OrderRefuel queryByOrderId(String id) {
		 
		return orderRefuelDao.queryByOrderId(id);
	}

}