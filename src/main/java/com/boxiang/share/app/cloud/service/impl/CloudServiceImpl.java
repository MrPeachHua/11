package com.boxiang.share.app.cloud.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.share.app.cloud.service.CloudService;
import com.boxiang.share.payment.dao.PaymentInfoDao;
import com.boxiang.share.payment.po.PaymentInfo;
import com.boxiang.share.product.car.dao.CarTypesDao;
import com.boxiang.share.product.car.po.CarTypes;
import com.boxiang.share.product.order.dao.OrderMainDao;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.json.JacksonUtil;

/**
 * Created by hua on 2016/8/19.
 */
@DataSource(DataSourceEnum.MASTER)
@Service("cloudService")
public class CloudServiceImpl implements CloudService {
    private static final Logger logger = LoggerFactory.getLogger(CloudServiceImpl.class);

    @Resource(name = "orderMainDao")
    private OrderMainDao orderMainDao;

    @Resource(name = "paymentInfoDao")
    private PaymentInfoDao paymentInfoDao;

    @Resource(name = "carTypesDao")
    private CarTypesDao carTypesDao;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public String carPaid(String data) throws ParseException,SQLException {
    	Map<String,Object> map = JacksonUtil.jsonToMap(data);
    	try {
    		final String orderId = (String)map.get("orderId");
        	final String useInfo = new String(data);
        	new Thread(new Runnable() {			
    			@Override
    			public void run() {
    		        //支付记录
    		        PaymentInfo paymentInfo = new PaymentInfo();
    		        paymentInfo.setOrderId(orderId);
    		        paymentInfo.setPayType("03");
    		        paymentInfo.setUseInfo(useInfo);
    		        paymentInfo.setUseType("2");
    		        paymentInfo.setCreateor("admin");
    		        paymentInfo.setCreateDate(new Date());
    		        paymentInfoDao.insert(paymentInfo);
    			}
    		}).start();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("线下支付通知异常：{}",e.getMessage());
		}
        
        
//        //车牌号
//        String plateId = getJsonParam(param,"plateId");
//        //蓝卡车场Id
//        String parkId =  getJsonParam(param,"parkId");
//        //订单ID
//        String orderId =  getJsonParam(param, "orderId");
//        //?
//        String recordId = getJsonParam(param, "recordId");
//        //支付时间
//        String payTime = getJsonParam(param, "payTime");
//        //应付金额
//        String payCharge = getJsonParam(param, "payCharge");
//        //优惠金额
//        String discountCharge = getJsonParam(param, "discountCharge");
//        //线上实付
//        String onlineCharge = getJsonParam(param, "onlineCharge");
//        //线下实付
//        String offlineCharge = getJsonParam(param, "offlineCharge");
//        //修改订单信息
//        Map<String,Object> omParam = new HashMap<String,Object>();
//        omParam.put("parkId",parkId);
//        omParam.put("plateId",plateId);
//        List<OrderMain> ormList = orderMainDao.getBytemCarAdPk(param);
//        if(null==ormList || ormList.size()==0){
//            return ShangAnMessageType.operateToJson("2", "找不到该订单");
//        }
//        OrderMain oMain = ormList.get(0);
//        oMain.setPayTime(DateUtil.str2date(payTime, DateUtil.DATETIME_FORMAT));
//        oMain.setOrderStatus("11");
//        oMain.setPayType("03");
//        oMain.setAmountPayable(Integer.parseInt(payCharge));
//        oMain.setAmountDiscount(Integer.parseInt(discountCharge));
//        oMain.setAmountPaid(Integer.parseInt(onlineCharge) + Integer.parseInt(offlineCharge));
//        orderMainDao.update(oMain);
        // {"info":{"orderDescribute":"口袋停缴费","orderName":"口袋停临停支付","orderID":"2016021700000056","orderPrice":"576"}}
//        Map<String,Object> pars1 = new HashMap<String,Object>();
//        Map<String,Object> pars2 = new HashMap<String,Object>();
//        pars2.put("orderDescribute","口袋停缴费");
//        pars2.put("orderName","口袋停临停支付");
//        pars2.put("orderID","XXX");
//        pars2.put("orderPrice","0");
//        pars1.put("info",pars2);
//        logger.info("---支付记录插入");
        return ShangAnMessageType.operateToJson("0","success");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public String synCarType(Map<String, Object> param) throws ParseException,SQLException  {
        //蓝卡车场ID
        String parkId = param.get("parkId").toString();
        List<Map<String,Object>> cList = (List<Map<String, Object>>) param.get("carType");
        if(StringUtils.isEmpty(parkId) || (null == cList || cList.size()==0)){
            return ShangAnMessageType.operateToJson("2","车场Id为空或carType数据为空");
        }
        CarTypes carTypesParam = new CarTypes();
        carTypesParam.setParkId(parkId);
        List<CarTypes>  carTypeList = carTypesDao.selectList(carTypesParam);
        //数据删除
        if(null!=carTypeList&&carTypeList.size()>0){
            for(CarTypes c :carTypeList){
                c.setIsUsed("0");
            }
            carTypesDao.batchUpdate(carTypeList);
        }
        logger.info("----车类型插入");
        List<CarTypes> inList = new ArrayList<CarTypes>();
        //插入数据
        for(Map<String,Object> c :cList){
            String carTypeCode = c.get("carTypeCode")==null?"":c.get("carTypeCode").toString();
            String carTypeName = c.get("carTypeName")==null?"":c.get("carTypeName").toString();
            CarTypes carType = new CarTypes();
            carType.setIsUsed("1");
            carType.setParkId(parkId);
            carType.setCarTypeCode(carTypeCode);
            carType.setCarTypeName(carTypeName);
            carType.setCreateor("admin");
            carType.setCreateDate(new Date());
            inList.add(carType);
        }
        carTypesDao.batchInsert(inList);
        return ShangAnMessageType.operateToJson("0","success");
    }
    public  String getJsonParam(Map<String,Object> map,String param){
        if(map.get(param)==null){
            logger.error("json的"+param+"key为null");
        }
        return map.get(param)==null?"":map.get(param).toString();
    }
}
