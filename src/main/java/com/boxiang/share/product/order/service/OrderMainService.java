package com.boxiang.share.product.order.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.bluecard.po.ParklotFee;
import com.boxiang.share.bluecard.po.ParklotFeeResult;
import com.boxiang.share.bluecard.po.PayStatusSync;
import com.boxiang.share.product.order.po.Cenuse;
import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.product.order.po.OrderMainExcel;

public interface OrderMainService {

	Map queryTemporaryByOrderId(String orderId, String orderType) throws ParseException;

	List<OrderMain> selectList(OrderMain orderMain);
	List<OrderMain> selectListExcel(OrderMain orderMain);
	Page<OrderMain> queryListPage(Page<OrderMain> page);
	
	OrderMain queryById(java.lang.String id);
	
	void add(OrderMain orderMain);

	void delete(java.lang.String id);
	
	void update(OrderMain orderMain);
	
	void batchUpdate(List<OrderMain> orderMainList);
	
	String ordercTemporary(Map<String,Object> param) throws Exception;
	String ordercTemporaryShare(Map<String,Object> param);
	String ordercMonthly(Map<String,Object> param);
	String ordercEquity(Map<String,Object> param);
	String ordercCarlife(Map<String, Object> param) throws Exception;
	OrderMain queryByIdAndType(OrderMain orderMain);
	
	String getMonPrice(Map<String,Object> param);
	long getMonPrice2(String carNumber,String parkingId);

	ParklotFeeResult sendJsonDataToRest(ParklotFee parklotFee);

	List<OrderMain> selectListByStatus();
	String getEquiPrice(Map<String,Object> param);
	long getEquiPrice2(String carNumber,String parkingId);
	String getOrderMoneqList(String customerId,String pageIndex);
	String getOrderTempList(String customerId,String pageIndex);
	
	/**
     * 查询历史订单
     * @param orderMain
     * @return
     */
    String selectOrderDetailT(OrderMain orderMain);
    String selectOrderDetailTS(OrderMain orderMain);
    String selectOrderDetailM(OrderMain orderMain);
    String selectOrderDetailE(OrderMain orderMain);

	public Page<OrderMain> queryListPageWithCarNumber(Page<OrderMain> page);
	public List<OrderMain> selectListExcelWithCarNumber (OrderMain orderMain);

	public List<OrderMain> queryMonthlyEquityListPage(Page<OrderMain> page);
	//定时同步白名单
	void synWhiteList(Map<String,Object> param);
	public String cancelOrder(String orderId) throws Exception;
	Page<OrderMain> queryTempSharePage(Page<OrderMain> page);
	Page<OrderMain> queryParkPage(Page<OrderMain> page);
	Page<OrderMain> queryTempPage(Page<OrderMain> page);
	List<OrderMain> queryTempExcel(OrderMain orderMain);
	List<OrderMain> queryTempShareExcel(OrderMain orderMain);
	Page<OrderMain> rechargelist(Page<OrderMain> page);
	List<OrderMain> queryListPage2(Object page);
	List<OrderMain> queryListPage3(Object page);
	List<OrderMain> queryListPage4(Object page);
	void parkPayWithGettingCar(OrderMain orderMain) throws Exception;

	String walletPay(String orderId, String payPassword) throws Exception;

	String backForTempOrder(PayStatusSync payStatusSync);

	String payParking(String orderId, String price, String payPassword) throws Exception;
	String selectCount(String customerId);

	List<OrderMainExcel> rechargeExcel(Map<String, Object> params);

	String blueWalletPay(String parkId, String parkName, String orderId, String carNumber, String carType, Integer amountPayable, String beginDate, String endDate) throws Exception;
	
	void monAndProPaid(OrderMain order);
	String queryOrderStatus(Page<OrderMain> page);
	Page<OrderMain> queryCarwashPage(Page<OrderMain> page);
	List<OrderMain> queryCarwasExport(OrderMain orderMain);
	List<OrderMainExcel> queryParkExport(Map<String, Object> params);

	Map queryCarwashByOrderId(String orderId);

	/* sideB临时放行**/
	String clearanceCar(String userId,String carNumber);
	List<OrderMain> queryStream(Page<OrderMain> page);
	List<OrderMain> queryListByStream(Page<OrderMain> page);
	List<OrderMain> queryAmountPaid(OrderMain orderMain);
	List<OrderMain> queryAmountPaidByMonth(OrderMain orderMain);

	/** 查询所有订单 */
	Page<Object> queryAllOrder(Page<Object> page);

	Object queryMonthlyEquityByOrderId(String orderId);
	List<OrderMain> getPaymentDetail(String inputDate,String region,String parkingId);
	List<Map<String,Object>> queryStatistics(Object page);
	Object queryMoney();
	void getStatistics(Map<String,String> map);
	List<Map<String,Object>> queryPayfeelsStatistics(Object page);
}