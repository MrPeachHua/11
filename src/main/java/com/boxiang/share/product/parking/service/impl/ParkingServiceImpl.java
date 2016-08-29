package com.boxiang.share.product.parking.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

import javax.annotation.Resource;

import com.boxiang.share.product.order.service.OrderParkService;
import com.boxiang.share.product.parker.po.Parker;
import com.boxiang.share.product.parker.service.ParkerService;
import com.boxiang.share.product.parking.po.DiscountParkingPrice;
import com.boxiang.share.product.parking.po.WeekPriceModel;
import com.boxiang.share.product.parking.service.DiscountParkingPriceService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.parking.dao.ParkingDao;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("tParking")
public class ParkingServiceImpl implements ParkingService {
    @Resource(name = "parkingDao")
    private ParkingDao parkingDao;

    @Resource
    private OrderParkService orderParkService;

    @Override
    public Parking selectParkingName(Parking parking) {
        return parkingDao.selectParkingName(parking);
    }

    @Resource
    private ParkerService parkerService;

    @Resource
    private DiscountParkingPriceService discountParkingPriceService;

    @Override
    public List<Parking> queryCooperation() {
        return parkingDao.queryCooperation();
    }

    @Override
    public List<Parking> carListByCM(Parking parking) {
        return parkingDao.carListByCM(parking);
    }

    @Override
    public List<Parking> carListByCP(Parking parking) {
        return parkingDao.carListByCP(parking);
    }

    @Override
    public List<Parking> selectList(Parking parking) {
        return parkingDao.selectList(parking);
    }

    @Override
    public List<Parking> selectListByExport(Parking parking) {
        return parkingDao.selectListByExport(parking);
    }

    @Override
    public List<Parking> getParkingListForCarLov(Page<Parking> page) {
        return parkingDao.getParkingListForCarLov(page);
    }

    @Override
    public String getAppoPkList(Map<String, Object> param) throws ParseException {
        List<Map<String, Object>> resultMapList = new ArrayList<>();
        Page<Parking> page = new Page<>();
        page.setPageSize(Integer.parseInt((String)param.get("pageSize")));
        page.setCurrentPage(Integer.parseInt((String)param.get("pageIndex")));
        page.setParams(param);
        List<Parking> pkList = parkingDao.getAppoPkList(page);
        if(null==pkList||pkList.size()==0){
            return ShangAnMessageType.operateToJson("1", "无数据");
        }
        for (Parking pk : pkList) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("parkingId", pk.getParkingId() == null ? "" : pk.getParkingId());
            resultMap.put("parkingName", pk.getParkingName() == null ? "" : pk.getParkingName());
            resultMap.put("parkingAddress", pk.getParkingAddress() == null ? "" : pk.getParkingAddress());
            resultMap.put("parkingId", pk.getParkingId() == null ? "" : pk.getParkingId());
            resultMap.put("len", pk.getLen() == null ? "" : new DecimalFormat("0").format(new BigDecimal(pk.getLen())) + "m");
            resultMap.put("parkingPath",StringUtils.isEmpty(pk.getParkingPath())?"":(String)param.get("basePath")+pk.getParkingPath());
            DiscountParkingPrice dparam = new DiscountParkingPrice();
            dparam.setParkingId(pk.getParkingId());
            List<DiscountParkingPrice> dList = discountParkingPriceService.selectList(dparam);
            String beginTime = "";
            String endTime = "";
            Integer price = 0;
            if (null != dList && dList.size() > 0) {
                DiscountParkingPrice dpp = dList.get(0);
                switch (getDay(new Date())) {
                    case 2:
                        beginTime = dpp.getMondayBeginTime();
                        endTime = dpp.getMondayEndTime();
                        price = dpp.getMondayPrice();
                        break;
                    case 3:
                        beginTime = dpp.getTuesdayBeginTime();
                        endTime = dpp.getTuesdayEndTime();
                        price = dpp.getTuesdayPrice();
                        break;
                    case 4:
                        beginTime = dpp.getWednesdayBeginTime();
                        endTime = dpp.getWednesdayEndTime();
                        price = dpp.getWednesdayPrice();
                        break;
                    case 5:
                        beginTime = dpp.getThursdayBeginTime();
                        endTime = dpp.getThursdayEndTime();
                        price = dpp.getThursdayPrice();
                        break;
                    case 6:
                        beginTime = dpp.getFridayBeginTime();
                        endTime = dpp.getFridayEndTime();
                        price = dpp.getFridayPrice();
                        break;
                    case 7:
                        beginTime = dpp.getSaturdayBeginTime();
                        endTime = dpp.getSaturdayEndTime();
                        price = dpp.getSaturdayPrice();
                        break;
                    case 1:
                        beginTime = dpp.getSundayBeginTime();
                        endTime = dpp.getSundayEndTime();
                        price = dpp.getSundayPrice();
                        break;
                }
            }
            if (!StringUtils.isEmpty(beginTime) && !StringUtils.isEmpty(endTime)) {
                Date beginTimeDate = DateUtil.str2date(beginTime, "HH:mm");
                Date endTimeDate = DateUtil.str2date(endTime, "HH:mm");
                if (beginTimeDate.getTime() < endTimeDate.getTime()) {//跨日
                    resultMap.put("dateStr", "当日" + beginTime + "-" + "当日" + endTime);
                } else {
                    resultMap.put("dateStr", "当日" + beginTime + "-" + "次日" + endTime);
                }
            } else {
                resultMap.put("dateStr", "当日" + beginTime + "-" + "当日" + endTime);
            }

            resultMap.put("price", (price==null?0:price)/100 + "元");
            resultMapList.add(resultMap);
        }

        return ShangAnMessageType.toShangAnJson("0","查询成功","data",resultMapList);
    }

    @Override
    public List<Parking> getList(Parking parking) {
        return parkingDao.getList(parking);
    }

    @Override
    public Page<Parking> queryListPage(Page<Parking> page) {
        page.setResultList(parkingDao.queryListPage(page));
        return page;
    }

    @Override
    public Parking parkingInfoDetail(Parking parking) {
        return parkingDao.parkingInfoDetail(parking);
    }
@Override
public List<Parking> searchParkListByLLDemo2(Parking parking)
{
    return parkingDao.searchParkListByLLDemo2(parking);
}
    @Override
    public List<Parking> searchParkListbyNameDemo2(Parking parking)
    {
        return parkingDao.searchParkListbyNameDemo2(parking);
    }
    @Override
    public int updateParkingCanUse(String parkingId, int count) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("parkingId", parkingId);
        params.put("count", count);
        return parkingDao.updateParkingCanUse(params);
    }
    @Override
    public Parking selectHomeAndWorkId(Parking parking)
    {
        return parkingDao.selectHomeAndWorkId(parking);
    }
    @Override
    public Parking indexShowByPid(Parking parking)
    {
        return parkingDao.indexShowByPid(parking);
    }
    /**
     * 用户查看所有停车场
     *
     * @param page
     * @return
     */
    @Override
    public String searchParking(Page<Parking> page,String im) {
        String message = null;
        List<Parking> parkingList = new ArrayList<Parking>();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Parking parking = new Parking();
        parking.setCustomerId(page.getParams().get("customerId").toString());
        Integer integer = parkingDao.countByIndexParking(parking);
        if (integer != null) {
            if (integer.intValue() > 0) {
                parkingList = parkingDao.getParkingByCustomerIdAndIn(page);
            } else {
                parkingList = parkingDao.getParkingByCustomerId(page);
            }
        } else {
            message = ShangAnMessageType.operateToJson("2", "查询失败");
        }
        if (parkingList != null && parkingList.size() > 0) {
            for (Parking parking1 : parkingList) {
                Map<String, Object> paraMap = new HashMap<String, Object>();
                paraMap.put("parkingName", parking1.getParkingName());
                paraMap.put("parkingCountry", parking1.getParkingCountry());
                paraMap.put("parkingProvince", parking1.getParkingProvince());
                paraMap.put("parkingCity", parking1.getParkingCity());
                paraMap.put("parkingCounty", parking1.getParkingCounty());
                paraMap.put("parkingArea", parking1.getParkingArea());
                paraMap.put("parkingAddress", parking1.getParkingAddress());
                paraMap.put("parkingLatitude", parking1.getParkingLatitude());
                paraMap.put("parkingLongitude", parking1.getParkingLongitude());
                paraMap.put("parkingCount", parking1.getParkingCount());
                paraMap.put("parkingCanUse", parking1.getParkingCanUse());
                paraMap.put("parkingId", parking1.getParkingId());
                paraMap.put("parkingPath", im+parking1.getParkingPath());
                paraMap.put("peacetimePrice", parking1.getPeacetimePrice());
                paraMap.put("canUse", parking1.getCanUse());
                paraMap.put("isCharge", parking1.getIsCharge());
                paraMap.put("ln", parking1.getLn());
                paraMap.put("isAutoPay", parking1.getIsAutoPay());
                mapList.add(paraMap);
            }
            message = ShangAnMessageType.toShangAnJson("0", "查询成功", "mapList", mapList);
        }else {
            message = ShangAnMessageType.toShangAnJson("1","无数据","mapList","");
        }
        return message;
    }
    /**
     * 判断代泊服务是否已满
     *
     * @param
     * @return
     */
    @Override
    public List<Map> queryParkingRelevance(String parkingId) {
        return parkingDao.queryParkingRelevance(parkingId);
    }

    @Override
    public List<Parking> queryTargetParking(String parkingId) {
        return parkingDao.queryTargetParking(parkingId);
    }

    @Override
    public boolean parkIsFull(Parking parking) {
        // 查询今日该车场的订单数量
        int count = orderParkService.queryTodayCount(parking.getParkingId());
        if (parking.getMaximumDay() == null) return true;
        return count >= parking.getMaximumDay();
    }

    /**
     * 查询当班的代泊员数量
     *
     * @param parkingId
     * @return
     */
    @Override
    public int parkerCount(String parkingId) {
        Parker parker = new Parker();
        parker.setState("1"); // 当班
        parker.setParkingId(parkingId);
        parker.setParkerType("0"); /** 代泊员身份0：代泊员1：代泊车管家 */
        return parkerService.selectCount(parker);
    }

    /**
     * 查询目标车场空位数
     *
     * @param parkingId
     * @return
     */
    @Override
    public Integer queryTargetParkingCanUseTotalCount(String parkingId) {
        return parkingDao.queryTargetParkingCanUseTotalCount(parkingId);
    }

    @Override
    public Parking queryById(java.lang.String id) {
        return parkingDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void updateCanUse() {
        parkingDao.updateCanUse();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(Parking parking) {
        parkingDao.insert(parking);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(java.lang.String id) {
        parkingDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(Parking parking) {
        parkingDao.update(parking);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<Parking> tParkingList) {
        parkingDao.batchUpdate(tParkingList);
    }

    @Override
    public Parking queryByIdTest(String id) {
        return parkingDao.queryByIdTest(id);
    }

    @Override
    public List<Parking> getListParking(Parking parking) {
        return parkingDao.getListParking(parking);
    }

    @Override
    public List<Parking> queryBlueParkingList() {
        return parkingDao.queryBlueParkingList();
    }

    @Override
    public List<?> canParkList(String lng, String lat) {
        return parkingDao.canParkList(lng, lat);
    }

    @Override
    public List<WeekPriceModel> getWeekPriceModel(String parkingId) {
        DiscountParkingPrice discountParkingPrice = discountParkingPriceService.queryById(parkingId);
        List<WeekPriceModel> list = new ArrayList<>(7);
        if (discountParkingPrice == null) {
            return list;
        }
        Date today = new Date();
        for (int i = 0; i < 7; i++) {
            WeekPriceModel model = new WeekPriceModel();
            Date tempDate = DateUtil.getPreOrNextDate(today, i);
            String date = DateUtil.date2str(tempDate, "M.d");
            String week = DateUtil.getDayOfWeek(tempDate, 1);
            model.setDate(date);
            model.setWeek("周" + week);
            if (week.equals("一")) {
                model.setPrice(discountParkingPrice.getMondayPrice());
                model.setStartTime(discountParkingPrice.getMondayBeginTime());
                model.setEndTime(discountParkingPrice.getMondayEndTime());
            } else if (week.equals("二")) {
                model.setPrice(discountParkingPrice.getTuesdayPrice());
                model.setStartTime(discountParkingPrice.getTuesdayBeginTime());
                model.setEndTime(discountParkingPrice.getTuesdayEndTime());
            } else if (week.equals("三")) {
                model.setPrice(discountParkingPrice.getWednesdayPrice());
                model.setStartTime(discountParkingPrice.getWednesdayBeginTime());
                model.setEndTime(discountParkingPrice.getWednesdayEndTime());
            } else if (week.equals("四")) {
                model.setPrice(discountParkingPrice.getThursdayPrice());
                model.setStartTime(discountParkingPrice.getThursdayBeginTime());
                model.setEndTime(discountParkingPrice.getThursdayEndTime());
            } else if (week.equals("五")) {
                model.setPrice(discountParkingPrice.getFridayPrice());
                model.setStartTime(discountParkingPrice.getFridayBeginTime());
                model.setEndTime(discountParkingPrice.getFridayEndTime());
            } else if (week.equals("六")) {
                model.setPrice(discountParkingPrice.getSaturdayPrice());
                model.setStartTime(discountParkingPrice.getSaturdayBeginTime());
                model.setEndTime(discountParkingPrice.getSaturdayEndTime());
            } else if (week.equals("日")) {
                model.setPrice(discountParkingPrice.getSundayPrice());
                model.setStartTime(discountParkingPrice.getSundayBeginTime());
                model.setEndTime(discountParkingPrice.getSundayEndTime());
            }
            list.add(model);
        }
        return list;
    }
    public  int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

}