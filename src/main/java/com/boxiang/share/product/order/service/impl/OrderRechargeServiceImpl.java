package com.boxiang.share.product.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.payment.po.PaymentInfo;
import com.boxiang.share.payment.service.PaymentInfoService;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.customer.service.RechargeRuleGiftAmountService;
import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.system.po.Sequence;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.LogTypeEnum;
import com.boxiang.share.utils.OrderConstants;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.TableNameConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.order.dao.OrderMainDao;
import com.boxiang.share.product.order.dao.OrderRechargeDao;
import com.boxiang.share.product.order.po.OrderRecharge;
import com.boxiang.share.product.order.service.OrderRechargeService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.log.SystemLog;

@DataSource(DataSourceEnum.MASTER)
@Service("orderRecharge")
public class OrderRechargeServiceImpl implements OrderRechargeService {
	private static final Logger logger = Logger.getLogger(OrderRechargeServiceImpl.class);
	
    @Resource(name = "orderRechargeDao")
    private OrderRechargeDao orderRechargeDao;
    @Resource(name="orderMainDao")
    private OrderMainDao orderMainDao;
    @Resource
    private OrderMainService orderMainService;

    @Resource
    private SequenceService sequenceService;

    @Resource
    private RechargeRuleGiftAmountService rechargeRuleGiftAmountService;

    @Resource
    private PaymentInfoService paymentInfoService;

    @Resource
    private CustomerService customerService;

    @Override
    public List<OrderRecharge> selectList(OrderRecharge orderRecharge) {
        return orderRechargeDao.selectList(orderRecharge);
    }

    @Override
    public Page<OrderRecharge> queryListPage(Page<OrderRecharge> page) {
        page.setResultList(orderRechargeDao.queryListPage(page));
        return page;
    }

    @Override
    public OrderRecharge queryById(Integer id) {
        return orderRechargeDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(OrderRecharge orderRecharge) {
        orderRechargeDao.insert(orderRecharge);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(Integer id) {
        orderRechargeDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(OrderRecharge orderRecharge) {
        orderRechargeDao.update(orderRecharge);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<OrderRecharge> orderRechargeList) {
        orderRechargeDao.batchUpdate(orderRechargeList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
	@SystemLog(logType=LogTypeEnum.ORDER_INFO,logSummary="创建钱包充值订单")
    public String createRechargeOrder(String customerId, String amountPayable, String useInfo, String payType,String version) {

        if (payType.equals("05")) {
            return ShangAnMessageType.operateToJson("1", "不能用钱包支付给钱包充值");
        }
            if(payType.equals("00")&& StringUtils.isEmpty(version)){
                return  ShangAnMessageType.operateToJson("1", "支付宝异常,请选择其他支付方式！");
        }

        // 添加主订单
        Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_ORDER_MAIN);
        OrderMain orderMain = new OrderMain();
        orderMain.setOrderId(sequence.getId());
        orderMain.setOrderType("16");
        orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_UNPAY);
        orderMain.setInvoiceStatus("0");
        orderMain.setCustomerId(customerId);
        orderMain.setAmountPayable(new BigDecimal(amountPayable).multiply(new BigDecimal("100")).intValue());
        orderMain.setAmountDiscount(0);
        Integer amountPaid = orderMain.getAmountPayable() - orderMain.getAmountDiscount();
        orderMain.setAmountPaid(amountPaid < 0 ? 0 : amountPaid);
        orderMain.setIsUsed(Constants.TRUE);
        orderMain.setCreateor("admin");
        orderMain.setCreateDate(new Date());
        orderMain.setPayType(payType);
        orderMainService.add(orderMain);

        // 添加钱包充值订单
        OrderRecharge orderRecharge = new OrderRecharge();
        orderRecharge.setIsUsed(Constants.TRUE);
        orderRecharge.setCreateDate(new Date());
        orderRecharge.setCreateor("admin");
        orderRecharge.setOrderId(orderMain.getOrderId());
        // 获得赠送金额数
        Integer giftAmount = rechargeRuleGiftAmountService.getGiftAmount(orderMain.getAmountPayable());
        orderRecharge.setGiftAmount(giftAmount);
        orderRechargeDao.insert(orderRecharge);

        // 保存支付记录
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderId(orderMain.getOrderId());
        paymentInfo.setPayType(payType);
        paymentInfo.setUseInfo(useInfo);
        paymentInfo.setUseType("1");
        paymentInfo.setCreateor("admin");
        paymentInfo.setCreateDate(new Date());
        paymentInfoService.add(paymentInfo);

        // 返回值
        Map<String, String> orderMap = new HashMap<String, String>();
        orderMap.put("orderId", orderMain.getOrderId());
        orderMap.put("orderType", orderMain.getOrderType());
        orderMap.put("orderStatus", orderMain.getOrderStatus());
        orderMap.put("amountPayable", amountPayable); // 应付
        orderMap.put("amountPaid", amountPayable); // 实付
        orderMap.put("giftAmount", Double.toString(orderRecharge.getGiftAmount() / 100.00)); // 赠送金额
        return ShangAnMessageType.toShangAnJson("0", "订单创建成功", "order", orderMap);
    }
    public String getRechargeList(Map<String,Object> param){
		String customerId = (String) param.get("customerId");
		String pageIndexStr = (String) param.get("pageIndex");
		String mess = null;
		long pageIndex = 1;
		if(null!=customerId && null!=pageIndexStr){
			pageIndex = (long)Integer.parseInt(pageIndexStr);
			Page<OrderMain> page = new Page<OrderMain>();
			page.setCurrentPage(pageIndex);
			page.setPageSize(Page.PAGE_SIZE_10);
			page.getParams().put("customerId", customerId);
			List<OrderMain> rechargeList = new ArrayList<OrderMain>();
			List<OrderMain> rechargeListNull = new ArrayList<OrderMain>();
			try {
				rechargeList = orderMainDao.queryListPageForRecharge(page);
				List <Map<String,Object>> orderList = new ArrayList<Map<String,Object>>();
				if(null!=rechargeList&&rechargeList.size()>0){
					for(OrderMain mo:rechargeList){
						Map<String,Object> orderMap = new HashMap<String,Object>();
						orderMap.put("payType", (null!=mo.getPayType())?mo.getPayType():"");
						orderMap.put("payTimeForDate", (null!=mo.getPayTime())?DateUtil.date2str(mo.getPayTime(), DateUtil.DATE_FORMAT):"");
						orderMap.put("payTimeForTime", (null!=mo.getPayTime())?DateUtil.date2str(mo.getPayTime(), DateUtil.TIME_FORMAT):"");
						orderMap.put("amountPaid", (null!=mo.getAmountPaid())?mo.getAmountPaid()/100.00:"");
						orderMap.put("amountDiscount", (null!=mo.getAmountDiscount())?mo.getAmountDiscount()/100.00:"");
						orderMap.put("amountPayable", (null!=mo.getAmountPayable())?mo.getAmountPayable()/100.00:"");
						orderMap.put("orderType",(null!=mo.getOrderType())?mo.getOrderType():"");
						orderList.add(orderMap);
					}
					mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "order",orderList);
				}else{
					mess = ShangAnMessageType.toShangAnJson("1", "查询成功无数据", "order",rechargeListNull);
				}
				
			} catch (Exception e) {
				mess = ShangAnMessageType.toShangAnJson("2", "查询失败", "order", "");
			}
		}else{
			mess = ShangAnMessageType.toShangAnJson("2", "查询失败", "order", "");
		}
		return mess;
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public String getPaidMessageByRecharge(String orderId, String orderType) throws Exception {
        String message = null;

        // 查询订单主表
        OrderMain orderMain = new OrderMain();
        orderMain.setOrderId(orderId);
        orderMain.setOrderType(orderType);
        orderMain = orderMainService.queryByIdAndType(orderMain);
        if (orderMain == null) {
            message = ShangAnMessageType.operateToJson("1", "修改失败，不存在该订单");
            return message;
        }

        // 查询钱包充值订单表
        OrderRecharge orderRecharge = new OrderRecharge();
        orderRecharge.setOrderId(orderId);
        orderRecharge.setIsUsed(Constants.TRUE);
        List<OrderRecharge> list = orderRechargeDao.selectList(orderRecharge);
        if (list == null || list.size() == 0) {
            message = ShangAnMessageType.operateToJson("1", "修改失败，不存在该订单");
            return message;
        }
        orderRecharge = list.get(0);

        // 查询用户表
        Customer customer = customerService.queryByCustomerId(orderMain.getCustomerId());
        if (customer == null) {
            message = ShangAnMessageType.operateToJson("1", "修改失败，用户不存在");
            return message;
        }

        // 判断订单状态
        if (!orderMain.getOrderStatus().equals(OrderConstants.ORDER_STATUS_UNPAY)) {
            message = ShangAnMessageType.operateToJson("1", "该订单已经处理过");
            return message;
        }

        // 修改订单状态
        /*orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_PAID);
        if (orderMainDao.updateUnpayToPaid(orderMain.getOrderId()) != 1) {
            throw new Exception("订单状态修改异常");
        }

        // 给用户钱包加钱
        logger.info("===============================================");
        logger.info(customer.getCustomerId()+ "给用户钱包加钱"+orderMain.getAmountPayable() + orderRecharge.getGiftAmount());
        customerService.addMoney(customer.getCustomerId(), orderMain.getAmountPayable() + orderRecharge.getGiftAmount());

        // 修改订单主表
        orderMain.setPayTime(new Date()); // 支付时间
        orderMain.setModifyDate(orderMain.getPayTime()); // 修改时间
        orderMain.setOrderStatus(OrderConstants.ORDER_STATUS_PAID); // 已付款
        orderMainService.update(orderMain);*/

        message = ShangAnMessageType.operateToJson("0", "充值成功");
        return message;
    }

    public String getConsumList(Map<String,Object> param){
    	String customerId = (String) param.get("customerId");
		String pageIndexStr = (String) param.get("pageIndex");
		String mess = null;
		long pageIndex = 1;
		if(null!=customerId && null!=pageIndexStr){
			pageIndex = (long)Integer.parseInt(pageIndexStr);
			Page<OrderMain> page = new Page<OrderMain>();
			page.setCurrentPage(pageIndex);
			page.setPageSize(Page.PAGE_SIZE_10);
			page.getParams().put("customerId", customerId);
			List<OrderMain> rechargeList = new ArrayList<OrderMain>();
			List<OrderMain> rechargeListNull = new ArrayList<OrderMain>();
			try {
				rechargeList = orderMainDao.queryListPageForConsum(page);
				List <Map<String,Object>> orderList = new ArrayList<Map<String,Object>>();
				if(null!=rechargeList&&rechargeList.size()>0){
					for(OrderMain mo:rechargeList){
						Map<String,Object> orderMap = new HashMap<String,Object>();
						orderMap.put("payTimeForDate", (null!=mo.getPayTime())?DateUtil.date2str(mo.getPayTime(), DateUtil.DATE_FORMAT):"");
						orderMap.put("payTimeForTime", (null!=mo.getPayTime())?DateUtil.date2str(mo.getPayTime(), DateUtil.TIME_FORMAT):"");
						orderMap.put("orderType",(null!=mo.getOrderType())?mo.getOrderType():"");
						if(mo.getOrderType()!=null && mo.getOrderType().equals("1")){
							orderMap.put("amountPaid", (null!=mo.getAmountPaid())?mo.getAmountPaid():"");
							orderMap.put("amountDiscount", (null!=mo.getAmountDiscount())?mo.getAmountDiscount():"");
							orderMap.put("amountPayable", (null!=mo.getAmountPayable())?mo.getAmountPayable():"");
						}else{
							orderMap.put("amountPaid", (null!=mo.getAmountPaid())?mo.getAmountPaid()/100.00:"");
							orderMap.put("amountDiscount", (null!=mo.getAmountDiscount())?mo.getAmountDiscount()/100.00:"");
							orderMap.put("amountPayable", (null!=mo.getAmountPayable())?mo.getAmountPayable()/100.00:"");
						}
						orderList.add(orderMap);
					}
					mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "order",orderList);
				}else{
					mess = ShangAnMessageType.toShangAnJson("1", "查询成功无数据", "order",rechargeListNull);
				}
				
			} catch (Exception e) {
				mess = ShangAnMessageType.toShangAnJson("2", "查询失败", "order", "");
			}
		}else{
			mess = ShangAnMessageType.toShangAnJson("2", "查询失败", "order", "");
		}
		return mess;
    }

}