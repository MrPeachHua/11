package com.boxiang.share.product.parking.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.boxiang.share.product.parking.po.Villageinfo;
import com.boxiang.share.product.parking.service.VillageinfoService;
import com.boxiang.share.system.po.Dictionary;
import com.boxiang.share.system.service.DictionaryService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.HttpUtil;
import com.boxiang.share.utils.json.JsonMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.parking.dao.CarInOutRecordV2Dao;
import com.boxiang.share.product.parking.po.CarInOutRecordV2;
import com.boxiang.share.product.parking.service.CarInOutRecordV2Service;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("carInOutRecordV2")
public class CarInOutRecordV2ServiceImpl implements CarInOutRecordV2Service {

    private static final Logger logger = Logger.getLogger(CarInOutRecordV2ServiceImpl.class);

    @Resource(name = "carInOutRecordV2Dao")
    private CarInOutRecordV2Dao carInOutRecordV2Dao;

    @Resource
    private VillageinfoService villageinfoService;

    @Resource
    private DictionaryService dictionaryService;

    @Override
    public List<CarInOutRecordV2> selectList(CarInOutRecordV2 carInOutRecordV2) {
        return carInOutRecordV2Dao.selectList(carInOutRecordV2);
    }

    @Override
    public Page<CarInOutRecordV2> queryListPage(Page<CarInOutRecordV2> page) {
        page.setResultList(carInOutRecordV2Dao.queryListPage(page));
        return page;
    }

    @Override
    public CarInOutRecordV2 queryById(Integer id) {
        return carInOutRecordV2Dao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(CarInOutRecordV2 carInOutRecordV2) {
        carInOutRecordV2Dao.insert(carInOutRecordV2);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(Integer id) {
        carInOutRecordV2Dao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(CarInOutRecordV2 carInOutRecordV2) {
        carInOutRecordV2Dao.update(carInOutRecordV2);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<CarInOutRecordV2> carInOutRecordV2List) {
        carInOutRecordV2Dao.batchUpdate(carInOutRecordV2List);
    }

    @Override
    public List<Object> queryListPageV2(Object page) {
        return carInOutRecordV2Dao.queryListPageV2(page);
    }

    /**
     * 获取进出场记录
     */
    public Map<String, Object> getInParkInfo(String beginTime, String endTime, String parkId) throws Exception {
        String url = "http://139.196.47.68/main/open/pubApi/queryPayHistory";
        Map<String, String> params = new HashMap<>(6);
        params.put("key", "74a13bb439624bd8a1cba02a26745d2f");
        params.put("parkId", parkId);
        params.put("inbeginTime", beginTime);
        params.put("inendTime", endTime);
        params.put("outbeginTime", beginTime);
        params.put("outendTime", endTime);
        String result = HttpUtil.netWithJsonParam(url, params);
        Map<String, Object> resultMap = (Map<String, Object>) JsonMapper.fromJson(result, Map.class);
        return resultMap;
    }

    /**
     * 手动同步数据
     * @param beginTime 开始时间 yyyy-MM-dd HH:mm:ss
     * @param endTime 结束时间 yyyy-MM-dd HH:mm:ss
     */
    @Override
    public void manualSyncData(String beginTime, String endTime) throws Exception {
        // 查询所有蓝卡云车场id
        List<Villageinfo> list = villageinfoService.selectList(null);
        // 遍历所有车场,拉取数据
        for (Villageinfo item : list) {
            if (StringUtils.isEmpty(item.getItem01())) {
                continue;
            }
            Map<String, Object> data = this.getInParkInfo(beginTime, endTime, item.getItem01());
            if (!data.get("status").equals("success")) {
                continue;
            }
            logger.info("同步出入场记录--请求成功:" + item.getItem01());
            // 合并进出场数据
            List<Map<String, Object>> inpark = (List<Map<String, Object>>) data.get("inparkJson");
            logger.info("同步出入场记录--入场数据:" + inpark.size());
            List<Map<String, Object>> outpark = (List<Map<String, Object>>) data.get("outparkJson");
            logger.info("同步出入场记录--出场数据:" + outpark.size());
            this.mergeDataV2(inpark, outpark);
            logger.info("同步出入场记录--合并进出场数据成功:" + item.getItem01());
        }
    }

    /**
     * 记录进出场车辆数据
     */
    @Override
    public synchronized void recordInOrOutCar() throws Exception {
        // 查询所有蓝卡云车场id
        List<Villageinfo> list = villageinfoService.selectAllBluePark();
        // 查询上一次同步时间
        Dictionary dictionary = dictionaryService.getDictListByDictCode("car_in_out_time_v2").get(0);
        Date date = DateUtil.str2date(dictionary.getDictValue(), DateUtil.DATETIME_FORMAT);//字典表记录时间
        Date date1 = DateUtil.getPreOrNextMinute(date, -10);//字典表记录时间   -10分钟
        Date date2 = DateUtil.getPreOrNextMinute(date, 10);//字典表记录时间   加30分钟   等于每次同步1小时数据
        String beginTime = DateUtil.date2str(date1, DateUtil.DATETIME_FORMAT); //开始时间
        String endTime = DateUtil.date2str(date2,DateUtil.DATETIME_FORMAT);// 结束时间
        Date now =DateUtil.str2date(DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT),DateUtil.DATETIME_FORMAT);//当前时间
        if(date2.getTime()>=now.getTime()){
            endTime=DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT);
        }
        logger.info(beginTime+"This is show Time ~~~~~~~~~~~~~~~~~~~~~~"+endTime);
        // 遍历所有车场,拉取数据
        for (Villageinfo item : list) {
            if (StringUtils.isEmpty(item.getItem01())) {
                continue;
            }
            Map<String, Object> data = this.getInParkInfo(beginTime, endTime, item.getItem01());
            if (!data.get("status").equals("success")) {
                continue;
            }
            // 合并进出场数据
            List<Map<String, Object>> inpark = (List<Map<String, Object>>) data.get("inparkJson");
            List<Map<String, Object>> outpark = (List<Map<String, Object>>) data.get("outparkJson");
            this.mergeDataV2(inpark, outpark);
        }
        // 更新最后同步时间
        dictionary.setDictValue(endTime);
        dictionaryService.update(dictionary);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public synchronized void mergeDataV2(List<Map<String, Object>> inpark, List<Map<String, Object>> outpark) throws Exception {
        if (inpark.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("inpark", inpark);
            carInOutRecordV2Dao.insertTempIn(map);
            carInOutRecordV2Dao.mergeTempInInsert();
            carInOutRecordV2Dao.clearTempIn();
        }
        if (outpark.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("outpark", outpark);
            carInOutRecordV2Dao.insertTempOut(map);
            carInOutRecordV2Dao.mergeTempOutInsert();
            carInOutRecordV2Dao.mergeTempOutUpdate();
            carInOutRecordV2Dao.clearTempOut();
        }
    }

    public void mergeData(List<Map<String, Object>> inpark, List<Map<String, Object>> outpark) throws Exception {
        for (Map<String, Object> map : inpark) {
            // 判断记录是否存在
            CarInOutRecordV2 entity = new CarInOutRecordV2();
            entity.setInparkId((int) map.get("id"));
            List<CarInOutRecordV2> list = this.carInOutRecordV2Dao.selectList(entity);
            if (list != null && list.size() > 0) {
                continue;
            }
            // 不存在才插入
            map.put("inparkId", map.get("id"));
            map.put("createor", "admin");
            map.put("createDate", DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT));
            this.carInOutRecordV2Dao.insertV2(map);
        }
        for (Map<String, Object> map : outpark) {
            // 判断记录是否存在
            CarInOutRecordV2 entity = new CarInOutRecordV2();
            entity.setOutparkId(map.get("id").toString());
            List<CarInOutRecordV2> list = this.carInOutRecordV2Dao.selectList(entity);
            if (list != null && list.size() > 0) {
                continue;
            }
            // 判断是否有对应的入场记录
            entity = new CarInOutRecordV2();
            entity.setOrderId(map.get("orderId").toString());
            list = this.carInOutRecordV2Dao.selectList(entity);
            if (list != null && list.size() > 0) { // 存在则更新合并数据
                entity = list.get(0);
                entity.setOutparkId(MapUtils.getString(map, "id"));
                entity.setInPassageway(MapUtils.getString(map, "inPassageway"));
                entity.setInPlateId(MapUtils.getString(map, "inPlateId"));
                entity.setInTime(DateUtil.str2date(map.get("inTime").toString(), DateUtil.DATETIME_FORMAT));
                entity.setName(MapUtils.getString(map, "name"));
                entity.setOperator(MapUtils.getString(map, "operator"));
                entity.setOrderId(MapUtils.getString(map, "orderId"));
                entity.setOutPassageway(MapUtils.getString(map, "outPassageway"));
                entity.setOutPlateId(MapUtils.getString(map, "outPlateId"));
                entity.setOutTime(DateUtil.str2date(map.get("outTime").toString(), DateUtil.DATETIME_FORMAT));
                entity.setParkId(MapUtils.getString(map, "parkId"));
                entity.setPayCharge(MapUtils.getInteger(map, "payCharge"));
                entity.setPayType(MapUtils.getString(map, "payType"));
                entity.setPlateId(MapUtils.getString(map, "plateId"));
                entity.setProfitNo(MapUtils.getString(map, "profitNo"));
                entity.setProfitNum(MapUtils.getInteger(map, "profitNum"));
                entity.setRealCharge(MapUtils.getInteger(map, "realCharge"));
                entity.setRemark(MapUtils.getString(map, "remark"));
                entity.setStayTime(MapUtils.getString(map, "stayTime"));
                this.update(entity);
            } else { // 不存在则插入
                map.put("outparkId", map.get("id"));
                map.put("createor", "admin");
                map.put("createDate", DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT));
                this.carInOutRecordV2Dao.insertV2(map);
            }
        }
    }

}