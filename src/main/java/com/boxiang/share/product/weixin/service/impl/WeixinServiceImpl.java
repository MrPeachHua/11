package com.boxiang.share.product.weixin.service.impl;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.share.product.order.dao.MonthlyparkinginfoDao;
import com.boxiang.share.product.order.dao.OrderMainDao;
import com.boxiang.share.product.order.dao.PropertyparkinginfoDao;
import com.boxiang.share.product.order.po.Monthlyparkinginfo;
import com.boxiang.share.product.order.po.Propertyparkinginfo;
import com.boxiang.share.product.order.service.OrderTemporaryService;
import com.boxiang.share.product.parking.dao.ParkingDao;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.weixin.service.WeixinService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.json.JsonMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hua on 2016/7/14.
 */
@DataSource(DataSourceEnum.MASTER)
@Service("weixinService")
public class WeixinServiceImpl implements WeixinService {
    private final static Logger logger = Logger.getLogger(WeixinServiceImpl.class);

    @Resource(name="orderMainDao")
    private OrderMainDao orderMainDao;

    @Resource(name="monthlyparkinginfoDao")
    private MonthlyparkinginfoDao monthlyparkinginfoDao;

    @Resource(name="propertyparkinginfoDao")
    private PropertyparkinginfoDao propertyparkinginfoDao;
    @Resource
    private OrderTemporaryService orderTemporaryService;

    @Resource
    private ParkingDao parkingDao;


    /**
     * 获取微信停车场列表
     * @param parkingId
     * @param customerId
     * @param type
     * @return
     */
//    @Override
//    @Transactional(propagation= Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
//    public String getDataByScanOrMenu(String parkingId, String customerId, String type,String carNumber) throws IOException {
//        //是否
//        boolean isHashScan = false;
//        //返回数据
//        List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
//        Set<String> parkingSet = new HashSet<String>();
//        //默认车场Id
//        String defParkingId = "";
//        List<OrderMain> oList0 = null;
//        //扫码进入
//        if("00".equals(type)){
//            defParkingId = parkingId;
//        }else{
//            Map<String,Object> param0 = new HashMap<String,Object>();
//            param0.put("customerId",customerId);
//            //param0.put("orderType",type);
//            oList0 = orderMainDao.getWeixinPkList(param0);
//            Map<String,Object> paramTp = new HashMap<String,Object>();
//            //该类型最新订单车场为default
//            paramTp.put("customerId",customerId);
//            paramTp.put("orderType",type);
//            List<OrderMain> oListTp = orderMainDao.getWeixinPkList(paramTp);
//            if(null!=oListTp&&oListTp.size()>0&&oListTp.get(0)!=null){
//                defParkingId = oListTp.get(0).getParkingId();
//            }
//        }
//        //获取用户所有车场
//        Map<String,Object> param = new HashMap<String,Object>();
//        param.put("customerId",customerId);
//        List<OrderMain> oList = null;
//        if("00".equals(type)){
//            //oList = orderMainDao.getWeixinPkList(param);
//            Parking pkParam = new Parking();
//            pkParam.setParkingId(parkingId);
//            List<Parking> pkList = parkingDao.selectList(pkParam);
//            List<String> carList = new ArrayList<String>();
//            if(null!=pkList&&pkList.size()>0){
//                Map<String,Object> dfMap = new HashMap<String,Object>();
//                dfMap.put("parkingId",parkingId);
//                dfMap.put("parkingName",pkList.get(0).getParkingName());
//                dfMap.put("carNumList", new ArrayList<String>());
//                dfMap.put("flag","1");
//                resultList.add(dfMap);
//            }
//            return ShangAnMessageType.toShangAnJson("0","查询成功","data",resultList);
//        }else{
//            oList = oList0;
//        }
//        //无数据返回
//        if(null!=oList&&oList.size()>0){
//            //获取车场集合
//            for(OrderMain o:oList){
//                if(null!=o&&!StringUtils.isEmpty(o.getParkingId())){
//                    parkingSet.add(o.getParkingId());
//                }
//            }
//            if(parkingSet.size()>0){
//                for(String s:parkingSet){
//                    String parkingName = "";
//                    List<String> carNumList = new ArrayList<String>();
//                    for(OrderMain o:oList){
//                        //同一个车场的车牌放入carList
//                        if(s.equals(o.getParkingId())){
//                            carNumList.add(o.getCarNumber());
//                            if(StringUtils.isEmpty(parkingName)){
//                              parkingName = o.getParkingName();
//                            }
//                        }
//                    }
//                    Map<String,Object> mp = new HashMap<String,Object>();
//                    mp.put("parkingId",s);
//                    mp.put("parkingName",parkingName);
//                    mp.put("carNumList",carNumList);
//                    //如果与默认车场匹配标识falg为1
//                    if(defParkingId.equals(s)){
//                        mp.put("flag","1");
//                        if(isHashScan == false){
//                            isHashScan = true;
//                        }
//                    }else{
//                        mp.put("flag","0");
//                    }
//                    resultList.add(mp);
//                }
//                //如果传入车牌的话将车牌的车场传入resultlist
//                if(!StringUtils.isEmpty(carNumber)){
//                    List<Map<String,Object>> cMapList = this.getParingInfoByCarNumber(customerId,carNumber,type,parkingId);
//                    List<Map<String,Object>> cMapListRmove = new ArrayList<Map<String,Object>>();
//                    if(null!=cMapList&&cMapList.size()>0){
//                        for(Map<String,Object> m:resultList){
//                            String pId = (String) m.get("parkingId");
//                            for(Map<String,Object> c:cMapList){
//                                if(pId.equals((String)c.get("parkingId"))){
//                                    cMapListRmove.add(m);
//                                }
//                            }
//                        }
//                        if(cMapList.size()>0){//如果还有存在要add的先把默认去掉
//                            for(Map<String,Object> m:resultList) {
//                                m.remove("flag");
//                                m.put("flag","0");
//                            }
//                        }
//                        resultList.removeAll(cMapListRmove);
//                        /*String isHasDefault="0";
//                        for(Map<String,Object> mp:resultList){
//                            if("1".equals((String) mp.get("flag"))){
//                                isHasDefault="1";
//                            }
//                        }
//                        if("0".equals(isHasDefault)){
//                            if(null!=cMapList&&cMapList.size()>0){
//                                cMapList.get(0).remove("flag");
//                                cMapList.get(0).put("flag","1");
//                            }
//                        }*/
//
//                        //集合无数据时
//                        logger.info("---------------resultList"+resultList.size());
//                        logger.info("---------------resultList"+resultList.size());
//                        resultList.addAll(cMapList);
//
//                       /* String isHasDefaultV1="0";
//                        for(Map<String,Object> mp:resultList){
//                            if("1".equals((String) mp.get("flag"))){
//                                isHasDefaultV1="1";
//                            }
//                        }
//                        if("0".equals(isHasDefaultV1)){
//                            if(null!=resultList&&resultList.size()>0){
//                                resultList.get(0).remove("flag");
//                                resultList.get(0).put("flag","1");
//                            }
//                        }*/
//                    }else{
//                        if(null!=resultList&&resultList.size()>0){
//                            resultList.get(0).remove("flag");
//                            resultList.get(0).put("flag","1");
//                        }
//
//                    }
//                }
//
//
//               return ShangAnMessageType.toShangAnJson("0","查询成功","data",resultList);
//            }else{
//                return ShangAnMessageType.toShangAnJson("0","无数据","data",resultList);
//            }
//        }else{
//            //如果传入车牌的话将车牌的车场传入resultlist
//            //如果传入车牌的话将车牌的车场传入resultlist
//            if(!StringUtils.isEmpty(carNumber)){
//                List<Map<String,Object>> cMapList = this.getParingInfoByCarNumber(customerId,carNumber,type,parkingId);
//                List<Map<String,Object>> cMapListRmove = new ArrayList<Map<String,Object>>();
//                if(null!=cMapList&&cMapList.size()>0){
//                    for(Map<String,Object> m:resultList){
//                        String pId = (String) m.get("parkingId");
//                        for(Map<String,Object> c:cMapList){
//                            if(pId.equals((String)c.get("parkingId"))){
//                                cMapListRmove.add(c);
//                            }
//                        }
//                    }
//                    cMapList.removeAll(cMapListRmove);
//                    logger.info("没有数据返回json"+JacksonUtil.toJson(cMapList));
//                    if(resultList.size()==0){
//                        logger.info("---------------resultList"+resultList.size());
//                        logger.info("99999999999");
//                        logger.info("99999999999");
//                        logger.info("99999999999");
//                        logger.info("99999999999");
//
//                    }
//                    resultList.addAll(cMapList);
//                }
//            }
//            return ShangAnMessageType.toShangAnJson("0","查询成功","data",resultList);
//        }
//
//    }
    /**
     * 获取微信停车场列表
     * @param carNumber
     * @param orderType
     * @return
     */
    public String getDataByScanOrMenu(String carNumber,String orderType,String customerId) throws IOException {
        List<Map<String,Object>> mList = this.getParingInfoByCarNumber(customerId,carNumber,orderType);
        if(null!=mList&&mList.size()>0){
            return ShangAnMessageType.toShangAnJson("0","查询成功","data",mList);
        }else{
            return ShangAnMessageType.operateToJson("1","无数据");
        }
    }

    /**
     *
     * @param parkingId
     * @param customerId
     * @param carNumber
     * @return
     */
    @Override
    public String getOrderData(String parkingId, String customerId, String carNumber ,String orderType) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if("14".equals(orderType)){
            //产权时
            Propertyparkinginfo prParam = new Propertyparkinginfo();
            prParam.setVillageId(parkingId);
            prParam.setCarNumber(carNumber);
            prParam.setIsUsed("1");
            List<Propertyparkinginfo> pList = propertyparkinginfoDao.selectList(prParam);
            if (null != pList && pList.size() > 0) {
                Parking parking = this.getParkingByBk(pList.get(0).getVillageId());
                resultMap.put("orderType", "14");
                resultMap.put("parkingId",pList.get(0).getVillageId());
                resultMap.put("parkingName",parking==null?"":parking.getParkingName());
                resultMap.put("carNumber",pList.get(0).getCarNumber());
                resultMap.put("effectEndTime", pList.get(0).getEffect_end_time() != null ?
                        DateUtil.date2str(pList.get(0).getEffect_end_time(), "yyyy/MM/dd")
                        : "");
                resultMap.put("price", pList.get(0).getManagementFeeMonthlyUnit());
                return ShangAnMessageType.toShangAnJson("0", "查询成功", "data", resultMap);
            }else{
                return ShangAnMessageType.operateToJson("2", "当前车场无您车辆产权订单，请选择正确车场后查询");
            }
        }else if("13".equals(orderType)){
            //判断是否为月租
            Monthlyparkinginfo mParam = new Monthlyparkinginfo();
            mParam.setVillageId(parkingId);
            mParam.setCarNumber(carNumber);
            mParam.setIsUsed("1");
            List<Monthlyparkinginfo> mList = monthlyparkinginfoDao.selectList(mParam);
            if (null != mList && mList.size() > 0) {
                Parking parking = this.getParkingByBk(mList.get(0).getVillageId());
                resultMap.put("orderType", "13");
                resultMap.put("parkingId",mList.get(0).getVillageId());
                resultMap.put("parkingName",parking==null?"":parking.getParkingName());
                resultMap.put("carNumber",mList.get(0).getCarNumber());
                resultMap.put("effectEndTime", mList.get(0).getEffect_end_time() != null ?
                        DateUtil.date2str(mList.get(0).getEffect_end_time(), "yyyy/MM/dd")
                        : "");
                resultMap.put("price", mList.get(0).getMonthlyRentalPrice());
                return ShangAnMessageType.toShangAnJson("0", "查询成功", "data", resultMap);
            }else {
                return ShangAnMessageType.operateToJson("2", "当前车场无您车辆月租订单，请选择正确车场后查询\n");
            }
        }else {
            //临停时蓝卡
            String temjson = orderTemporaryService.getCarlist(customerId, carNumber);
            Map mp = (Map) JsonMapper.fromJson(temjson, Map.class);
            Map<String, Object> car = new HashMap<String, Object>();
            List<Map<String, Object>> carList = (List<Map<String, Object>>) mp.get("cars");
            if (null != carList && carList.size() > 0) {
                car = carList.get(0);
                if (!parkingId.equals(car.get("parkingId"))) {
                    return ShangAnMessageType.operateToJson("2", "当前车场无您车辆临停订单，请选择正确车场后查询");
                }
                Map<String, Object> temMap = new HashMap<String, Object>();
                temMap.put("parkingId", car.get("parkingId"));
                temMap.put("parkingName", car.get("parkingName"));
                temMap.put("carNumber", car.get("carNumber"));
                temMap.put("beginDate", car.get("beginDate"));
                temMap.put("parkingTime", car.get("parkingTime"));
                temMap.put("amountPayable", car.get("amountPayable"));
                temMap.put("orderType", "11");
                resultMap.put("data", temMap);
                return ShangAnMessageType.toShangAnJson("0", "查询成功", "data", resultMap);
            } else {
                return ShangAnMessageType.operateToJson("2", "当前车场无您车辆临停订单，请选择正确车场后查询");
            }
        }


    }

    /**
     *
     * @param carNumber
     * @param customerId
     * @return
     */
    public List<Map<String,Object>>  getParingInfoByCarNumber(String customerId,String carNumber,String orderType) throws IOException {
        List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
        if("13".equals(orderType)){
        //判断是否为月租
        logger.info("333333333333333333333333333");
            Monthlyparkinginfo mParam = new Monthlyparkinginfo();
            mParam.setCarNumber(carNumber);
            mParam.setIsUsed("1");
            List<Monthlyparkinginfo> mList = monthlyparkinginfoDao.selectListGroup(mParam);
            if (null != mList && mList.size() > 0) {
                for(int i=0;i<mList.size();i++){
                    Parking param = new Parking();
                    param.setParkingId(mList.get(i).getVillageId());
                    Parking parking = parkingDao.selectParkingName(param);
                    Map<String, Object> resultMap = new HashMap<String, Object>();
                    resultMap.put("parkingId",mList.get(i).getVillageId());
                    resultMap.put("parkingName",parking.getParkingName());
                    resultList.add(resultMap);
                }
            }
        }else if("14".equals(orderType)){
            //产权时
            Propertyparkinginfo prParam = new Propertyparkinginfo();
            prParam.setCarNumber(carNumber);
            prParam.setIsUsed("1");
            List<Propertyparkinginfo> pList = propertyparkinginfoDao.selectListGroup(prParam);
            if (null != pList && pList.size() > 0) {
                for(int i = 0;i<pList.size();i++){
                    Parking param = new Parking();
                    param.setParkingId(pList.get(i).getVillageId());
                    Parking parking = parkingDao.selectParkingName(param);
                    Map<String, Object> resultMap = new HashMap<String, Object>();
                    resultMap.put("parkingId",pList.get(i).getVillageId());
                    resultMap.put("parkingName",parking.getParkingName());
                    resultList.add(resultMap);
                }
            }
        }else if("11".equals(orderType)){
            //临停时蓝卡
            String temjson = orderTemporaryService.getCarlist(customerId, carNumber);
            Map mp = (Map) JsonMapper.fromJson(temjson, Map.class);
            Map<String, Object> car = new HashMap<String, Object>();
            List<Map<String, Object>> carList = (List<Map<String, Object>>) mp.get("cars");
            if (null != carList && carList.size() > 0) {
                car = carList.get(0);
                Map<String, Object> resultMap = new HashMap<String, Object>();
                resultMap.put("parkingId",car.get("parkingId"));
                resultMap.put("parkingName",car.get("parkingName"));
                resultList.add(resultMap);
            }
        }
        return resultList;
    }

    public Parking getParkingByBk(String parkingId) {
        Parking param = new Parking();
        param.setParkingId(parkingId);
        List<Parking> list = parkingDao.selectList(param);
        if(null!=list&&list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
}
