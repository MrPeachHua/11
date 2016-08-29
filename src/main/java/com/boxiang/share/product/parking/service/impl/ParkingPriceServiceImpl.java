package com.boxiang.share.product.parking.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.boxiang.share.product.coupon.service.CouponRuleService;
import com.boxiang.share.product.parking.po.DiscountParkingPrice;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.po.Villageinfo;
import com.boxiang.share.product.parking.service.DiscountParkingPriceService;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.product.parking.service.VillageinfoService;
import com.boxiang.share.system.po.Sequence;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.TableNameConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.parking.dao.ParkingPriceDao;
import com.boxiang.share.product.parking.po.ParkingPrice;
import com.boxiang.share.product.parking.service.ParkingPriceService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("parkingPrice")
public class ParkingPriceServiceImpl implements ParkingPriceService {

    private static final Logger logger = Logger.getLogger(ParkingPriceServiceImpl.class);

    @Resource(name = "parkingPriceDao")
    private ParkingPriceDao parkingPriceDao;

    @Resource
    private CouponRuleService couponRuleService;

    @Resource
    private ParkingService parkingService;

    @Resource
    private SequenceService sequenceService;

    @Resource
    private VillageinfoService villageinfoService;
    @Resource
    private DiscountParkingPriceService discountParkingPriceService;


    @Override
    public List<ParkingPrice> selectList(ParkingPrice parkingPrice) {
        return parkingPriceDao.selectList(parkingPrice);
    }

    @Override
    public Page<ParkingPrice> queryListPage(Page<ParkingPrice> page) {
        page.setResultList(parkingPriceDao.queryListPage(page));
        return page;
    }

    @Override
    public ParkingPrice queryById(String id) {
        return parkingPriceDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(ParkingPrice parkingPrice) {
        parkingPriceDao.insert(parkingPrice);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(String id) {
        parkingPriceDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(ParkingPrice parkingPrice) {
        parkingPriceDao.update(parkingPrice);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<ParkingPrice> parkingPriceList) {
        parkingPriceDao.batchUpdate(parkingPriceList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delParkingAndParkingPrice(String id) throws Exception {
        Parking parking = parkingService.queryById(id);
        if (parking == null) {
            throw new NullPointerException("没有id为：" + id + "的数据!");
        }
        parkingService.delete(id);

        // 删除对应的价格规则信息
        ParkingPrice parkingPrice = new ParkingPrice();
        parkingPrice.setParkingId(id);
        List<ParkingPrice> priceList = this.selectList(parkingPrice);
        if (priceList != null && priceList.size() != 0) {
            for (ParkingPrice item : priceList) {
                this.delete(item.getId());
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void updateParkingAndParkingPrice(Parking parking) throws Exception {
        try {
            // 查询车场原来的状态
            Parking oldParking = parkingService.queryById(parking.getParkingId());
            if (!oldParking.getParkingStatus().equals("满") && parking.getParkingStatus().equals("满")) {
                // 执行推送操作
                final String parkingId = parking.getParkingId();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        couponRuleService.parkingStatusPush(parkingId);
                    }
                }).start();
            }
        } catch (Exception e) {
            logger.error("", e);
        }

        parkingService.update(parking);
        // 查询对应的价格规则信息
        ParkingPrice parkingPrice = new ParkingPrice();
        parkingPrice.setParkingId(parking.getParkingId());
        List<ParkingPrice> priceList = this.selectList(parkingPrice);
        boolean scheme1 = false;
        boolean scheme2 = false;
        boolean scheme3 = false;
        boolean scheme4 = false;
        boolean scheme5 = false;
        boolean scheme6 = false;
        boolean scheme7 = false;
        boolean scheme8 = false;
        if (priceList != null && priceList.size() != 0) {
            for (ParkingPrice item : priceList) {
                if (item.getSchemeId().equals("1")) {
                    item.setPrice(parking.getScheme_init_price());
                    scheme1 = true;
                } else if (item.getSchemeId().equals("2")) {
                    item.setPrice(parking.getScheme_exceed_price());
                    scheme2 = true;
                } else if (item.getSchemeId().equals("3")) {
                    item.setPrice(parking.getScheme_proxy_price());
                    scheme3 = true;
                } else if (item.getSchemeId().equals("4")) {
                    item.setPrice(parking.getScheme_park_price());
                    scheme4 = true;
                } else if (item.getSchemeId().equals("5")) {
                    item.setPrice(parking.getScheme_exceed_night_price());
                    scheme5 = true;
                } else if (item.getSchemeId().equals("6")) {
                    item.setPrice(parking.getScheme_top_price());
                    scheme6 = true;
                } else if (item.getSchemeId().equals("7")) {
                    item.setPrice(parking.getScheme_discount());
                    scheme7 = true;
                } else if (item.getSchemeId().equals("8")) {
                    item.setPrice(parking.getScheme_init_hour());
                    scheme8 = true;
                }
            }
            this.batchUpdate(priceList);
        }
        if (scheme1 == false) {
            ParkingPrice scheme_init_price = new ParkingPrice();
            scheme_init_price.setId(sequenceService.getNextvalandins(TableNameConstants.T_PARKING_PRICE).getId());
            scheme_init_price.setParkingId(parking.getParkingId());
            scheme_init_price.setPrice(parking.getScheme_init_price());
            scheme_init_price.setSchemeId("1");
            this.add(scheme_init_price);
        }
        if (scheme2 == false) {
            ParkingPrice scheme_exceed_price = new ParkingPrice();
            scheme_exceed_price.setId(sequenceService.getNextvalandins(TableNameConstants.T_PARKING_PRICE).getId());
            scheme_exceed_price.setParkingId(parking.getParkingId());
            scheme_exceed_price.setPrice(parking.getScheme_exceed_price());
            scheme_exceed_price.setSchemeId("2");
            this.add(scheme_exceed_price);
        }
        if (scheme3 == false) {
            ParkingPrice scheme_proxy_price = new ParkingPrice();
            scheme_proxy_price.setId(sequenceService.getNextvalandins(TableNameConstants.T_PARKING_PRICE).getId());
            scheme_proxy_price.setParkingId(parking.getParkingId());
            scheme_proxy_price.setPrice(parking.getScheme_proxy_price());
            scheme_proxy_price.setSchemeId("3");
            this.add(scheme_proxy_price);
        }
        if (scheme4 == false) {
            ParkingPrice scheme_park_price = new ParkingPrice();
            scheme_park_price.setId(sequenceService.getNextvalandins(TableNameConstants.T_PARKING_PRICE).getId());
            scheme_park_price.setParkingId(parking.getParkingId());
            scheme_park_price.setPrice(parking.getScheme_park_price());
            scheme_park_price.setSchemeId("4");
            this.add(scheme_park_price);
        }
        if (scheme5 == false) {
            ParkingPrice scheme_exceed_night_price = new ParkingPrice();
            scheme_exceed_night_price.setId(sequenceService.getNextvalandins(TableNameConstants.T_PARKING_PRICE).getId());
            scheme_exceed_night_price.setParkingId(parking.getParkingId());
            scheme_exceed_night_price.setPrice(parking.getScheme_exceed_night_price());
            scheme_exceed_night_price.setSchemeId("5");
            this.add(scheme_exceed_night_price);
        }
        if (scheme6 == false) {
            ParkingPrice scheme_top_price = new ParkingPrice();
            scheme_top_price.setId(sequenceService.getNextvalandins(TableNameConstants.T_PARKING_PRICE).getId());
            scheme_top_price.setParkingId(parking.getParkingId());
            scheme_top_price.setPrice(parking.getScheme_top_price());
            scheme_top_price.setSchemeId("6");
            this.add(scheme_top_price);
        }
        if (scheme7 == false) {
            ParkingPrice scheme_discount = new ParkingPrice();
            scheme_discount.setId(sequenceService.getNextvalandins(TableNameConstants.T_PARKING_PRICE).getId());
            scheme_discount.setParkingId(parking.getParkingId());
            scheme_discount.setPrice(parking.getScheme_discount());
            scheme_discount.setSchemeId("7");
            this.add(scheme_discount);
        }
        if (scheme8 == false) {
            ParkingPrice scheme_init_hour = new ParkingPrice();
            scheme_init_hour.setId(sequenceService.getNextvalandins(TableNameConstants.T_PARKING_PRICE).getId());
            scheme_init_hour.setParkingId(parking.getParkingId());
            scheme_init_hour.setPrice(parking.getScheme_init_hour());
            scheme_init_hour.setSchemeId("8");
            this.add(scheme_init_hour);
        }
        // 更新车场关联表
        Villageinfo villageinfo = villageinfoService.queryById(parking.getParkingId());
        if (villageinfo == null && (parking.getVillageinfo().getItem01() == null || parking.getVillageinfo().getItem01().trim().length() == 0)) {
            return;
        }
        if (villageinfo == null) { // 新增
            villageinfo = new Villageinfo();
            villageinfo.setId(parking.getParkingId());
            villageinfo.setItem01(parking.getVillageinfo().getItem01());
            villageinfo.setCreateTime(new Date());
            villageinfo.setUpdateTime(villageinfo.getCreateTime());
            villageinfo.setCreateUser("admin");
            villageinfo.setUpdateUser(villageinfo.getCreateUser());
            villageinfoService.add(villageinfo);
        } else { // 修改
            villageinfo.setItem01(parking.getVillageinfo().getItem01());
            villageinfoService.update(villageinfo);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void saveParkingAndParkingPrice(Parking parking) throws Exception {
        Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_PARKING);
        parking.setParkingId(/*StringUtils.defaultString(parking.getParkingIdentifier(),"")+*/sequence.getId());
        parkingService.add(parking);
        ParkingPrice scheme_init_price = new ParkingPrice();
        scheme_init_price.setId(sequenceService.getNextvalandins(TableNameConstants.T_PARKING_PRICE).getId());
        scheme_init_price.setParkingId(parking.getParkingId());
        scheme_init_price.setPrice(parking.getScheme_init_price());
        scheme_init_price.setSchemeId("1");
        this.add(scheme_init_price);

        ParkingPrice scheme_exceed_price = new ParkingPrice();
        scheme_exceed_price.setId(sequenceService.getNextvalandins(TableNameConstants.T_PARKING_PRICE).getId());
        scheme_exceed_price.setParkingId(parking.getParkingId());
        scheme_exceed_price.setPrice(parking.getScheme_exceed_price());
        scheme_exceed_price.setSchemeId("2");
        this.add(scheme_exceed_price);

        ParkingPrice scheme_proxy_price = new ParkingPrice();
        scheme_proxy_price.setId(sequenceService.getNextvalandins(TableNameConstants.T_PARKING_PRICE).getId());
        scheme_proxy_price.setParkingId(parking.getParkingId());
        scheme_proxy_price.setPrice(parking.getScheme_proxy_price());
        scheme_proxy_price.setSchemeId("3");
        this.add(scheme_proxy_price);

        ParkingPrice scheme_park_price = new ParkingPrice();
        scheme_park_price.setId(sequenceService.getNextvalandins(TableNameConstants.T_PARKING_PRICE).getId());
        scheme_park_price.setParkingId(parking.getParkingId());
        scheme_park_price.setPrice(parking.getScheme_park_price());
        scheme_park_price.setSchemeId("4");
        this.add(scheme_park_price);

        ParkingPrice scheme_exceed_night_price = new ParkingPrice();
        scheme_exceed_night_price.setId(sequenceService.getNextvalandins(TableNameConstants.T_PARKING_PRICE).getId());
        scheme_exceed_night_price.setParkingId(parking.getParkingId());
        scheme_exceed_night_price.setPrice(parking.getScheme_exceed_night_price());
        scheme_exceed_night_price.setSchemeId("5");
        this.add(scheme_exceed_night_price);

        ParkingPrice scheme_top_price = new ParkingPrice();
        scheme_top_price.setId(sequenceService.getNextvalandins(TableNameConstants.T_PARKING_PRICE).getId());
        scheme_top_price.setParkingId(parking.getParkingId());
        scheme_top_price.setPrice(parking.getScheme_top_price());
        scheme_top_price.setSchemeId("6");
        this.add(scheme_top_price);

        ParkingPrice scheme_discount = new ParkingPrice();
        scheme_discount.setId(sequenceService.getNextvalandins(TableNameConstants.T_PARKING_PRICE).getId());
        scheme_discount.setParkingId(parking.getParkingId());
        scheme_discount.setPrice(parking.getScheme_discount());
        scheme_discount.setSchemeId("7");
        this.add(scheme_discount);

        ParkingPrice scheme_init_hour = new ParkingPrice();
        scheme_init_hour.setId(sequenceService.getNextvalandins(TableNameConstants.T_PARKING_PRICE).getId());
        scheme_init_hour.setParkingId(parking.getParkingId());
        scheme_init_hour.setPrice(parking.getScheme_init_hour());
        scheme_init_hour.setSchemeId("8");
        this.add(scheme_init_hour);

        // 添加车场关联
        if (parking.getVillageinfo().getItem01() == null || parking.getVillageinfo().getItem01().trim().length() == 0) {
            return;
        }
        Villageinfo villageinfo = new Villageinfo();
        villageinfo.setId(parking.getParkingId());
        villageinfo.setItem01(parking.getVillageinfo().getItem01());
        villageinfo.setCreateTime(new Date());
        villageinfo.setUpdateTime(villageinfo.getCreateTime());
        villageinfo.setCreateUser("admin");
        villageinfo.setUpdateUser(villageinfo.getCreateUser());
        villageinfoService.add(villageinfo);
    }

    /**
     * 计算代泊价格
     *
     * @param parkingId 车场id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    @Override
    public String calcParkPrice(String parkingId, String startTime, String endTime) throws Exception {
        if (compare(startTime, endTime) >= 0) {
            return ShangAnMessageType.operateToJson("3", "开始时间不得大于结束时间");
        }
        // 查询车场信息，附带代泊价格
        Parking parking = queryParkingWithPrice(parkingId);
        if (StringUtils.isEmpty(parking.getParkBeginTime()) || StringUtils.isEmpty(parking.getParkEndTime())) {
            return ShangAnMessageType.operateToJson("4", "没有设置服务时间");
        }
        //判断开始日期是否在服务时间范围内
        String now = DateUtil.getCurrDate(DateUtil.DATE_FORMAT);
        String parkBeginTime = now + " " + parking.getParkBeginTime();
        if (compare(parkBeginTime, startTime) == 1) {
            return ShangAnMessageType.operateToJson("5", "开始时间不在服务时间范围内");
        }
        if (parking.getRule().equals("2")) {
            // 明天的日期
            String tomorrow = DateUtil.date2str(DateUtil.getPreOrNextDate(new Date(), 1), DateUtil.DATE_FORMAT);
            String parkEndTime = tomorrow + " " + parking.getParkEndTime();
            // 判断结束时间是否在服务时间范围内
            if (compare(endTime, parkEndTime) == 1) {
                return ShangAnMessageType.operateToJson("6", "已超出服务时间，请选择合适的取车时间！");
            }
        }
        if (StringUtils.isEmpty(parking.getRule())) {
            return ShangAnMessageType.operateToJson("7", "没有设置代泊规则");
        }
        Integer price = calcPrice(parking, startTime, endTime);
        // 代泊价格描述
        Map<String, String> map = new HashMap<>(2);
        map.put("price", Integer.toString(price));
        String description = "";
        if (parking.getRule().equals("1")) { // 规则1描述
            description = String.format(
                    "起步价%s元，起步价时间%s小时，超过每小时%s元，封顶%s",
                    parking.getScheme_init_price(),
                    parking.getScheme_init_hour(),
                    parking.getScheme_exceed_price(),
                    parking.getScheme_top_price() == null ? "不限" : parking.getScheme_top_price() + "元");
        } else if (parking.getRule().equals("2")) { // 规则2描述
            description = String.format(
                    "代泊服务费%s元，停车费%s元",
                    parking.getScheme_proxy_price(),
                    parking.getScheme_park_price());
        }
        map.put("description", description);
        return ShangAnMessageType.toShangAnJson("0", "success", "data", map);
    }

    @Override
    public Integer calcPrice(Parking parking, String startTime, String endTime) throws Exception {
        Integer price = null;
        if (parking.getRule().equals("1")) { // 根据规则1计算
            price = calcPriceWithRule1(parking, startTime, endTime);
        } else if (parking.getRule().equals("2")) { // 根据规则2计算
            price = calcPriceWithRule2(parking, startTime, endTime);
        }
        return price;
    }

    /**
     * 根据规则1计算价格
     *
     * @param parking
     * @param startTime
     * @param endTime
     */
    private Integer calcPriceWithRule1(Parking parking, String startTime, String endTime) throws ParseException {
        // 起步价
        Integer initPrice = parking.getScheme_init_price() == null ? null : parking.getScheme_init_price().intValue();
        // 起步价时间
        Integer initHour = parking.getScheme_init_hour() == null ? null : parking.getScheme_init_hour().intValue();
        // 超过每小时
        Integer exceedPrice = parking.getScheme_exceed_price() == null ? null : parking.getScheme_exceed_price().intValue();
        // 封顶价格
        Integer topPrice = parking.getScheme_top_price() == null ? null : parking.getScheme_top_price().intValue();
        // 折扣
        Integer discount = parking.getScheme_discount() == null ? null : parking.getScheme_discount().intValue();
        // 计算开始时间到结束时间的小时数，不满一小时按一小时计算
        Integer hourDiff = calcHourDiff(startTime, endTime);
        // 没有超过起步价时间，按起步价计算
        Integer price;
        if (hourDiff <= initHour) {
            price = initPrice;
        } else {
            // 超过起步价，追加每小时的价格  每小时价格 * 超过的小时数 + 起步价
            price = exceedPrice * (hourDiff - initHour) + initPrice;
            // 如果大于封顶价格，按封顶价格计算
            if (topPrice != null && price > topPrice) {
                price = topPrice;
            }
        }
        // 计算折扣
        if (discount != null && discount > 0 && discount < 10) {
            price = (int) (discount / 10.0 * price + 0.5); // 四舍五入 +0.5强转整形
        }
        return price;
    }

    /**
     * 根据规则2计算价格
     *
     * @param parking
     * @param startTime
     * @param endTime
     */
    private Integer calcPriceWithRule2(Parking parking, String startTime, String endTime) throws ParseException {
        // 代泊费
        Integer proxyPrice = parking.getScheme_proxy_price() == null ? null : parking.getScheme_proxy_price().intValue();
        // 停车场价格
        Integer parkPrice = parking.getScheme_park_price() == null ? null : parking.getScheme_park_price().intValue();
//        // 超过夜间停车时间
//        Integer exceedNightPrice = parking.getScheme_exceed_night_price() == null ? null : parking.getScheme_exceed_night_price().intValue();

        return proxyPrice + parkPrice;

//        // 车场开始日期字符串
//        String startDate = startTime.split(" ")[0];
//        String parkStartTime = startDate + " " + parking.getStartTime();
//        // 车场结束日期字符串
//        String endDate = endTime.split(" ")[0];
//        String parkStopTime = endDate + " " + parking.getStopTime();
//
//        // 超出的小时数
//        int hour = 0;
//        // 基础费用：代泊费
//        Integer price = proxyPrice;
//        // 计算开始和结束日期间隔多少天
//        int daysInterval = DateUtil.getDaysInterval(startTime, endTime);
//        if (daysInterval == 0) { // 当天停，当天就要取车
//            // 没有到达包夜时间段，按超过夜间停车时间每小时的价格计算
//            if (compare(parkStartTime, endTime) >= 0) {
//                hour = calcHourDiff(startTime, endTime); // 停车开始到结束的小时数
//                price = hour * exceedNightPrice + price;
//                return price;
//            } else if (compare(startTime, parkStartTime) >= 0) { // 开始时间正好在包夜范围内
//                price += parkPrice; // 加上包夜价格
//                return price;
//            } else { // 从白天一直停到包夜时间，只要达到了夜间时间，默认加上夜间价格，哪怕只停一分钟
//                hour = calcHourDiff(startTime, parkStartTime);
//                // 小时数 * 超时价格 + 夜间价格 + 基础代泊费用
//                price = hour * exceedNightPrice + parkPrice + price;
//                return price;
//            }
//        } else { // 停了一个或多个晚上
//            price = daysInterval * parkPrice + price;
//            // 还没到包夜时间，需要另外加钱
//            if (compare(parkStartTime, startTime) == 1) {
//                // 计算到达包夜时间的小时数
//                hour += calcHourDiff(parkStartTime, startTime);
//            }
//            // 判断结束时间是否在包夜范围内
//            if (compare(endTime, parkStopTime) == 1) {
//                hour += calcHourDiff(endTime, parkStopTime);
//            }
//            // 计算出超出的小时数
//            if (hour > 0) {
//                // 超出的小时数 * 超过夜间停车时间 + 基本费用
//                price = exceedNightPrice * hour + price;
//            }
//            // 多个晚上的时候，计算白天的小时数
//            if (daysInterval > 1) {
//                String nowDate = DateUtil.getCurrDate(DateUtil.DATE_FORMAT);
//                // 每天白天的小时数
//                int everyDayHour = calcHourDiff(nowDate + " " + parking.getStartTime(), nowDate + " " + parking.getStopTime());
//                // 白天的小时数 * 超时每小时价格 * （间隔天数 - 1） + 基础费用
//                price = (everyDayHour * exceedNightPrice) * (daysInterval - 1) + price;
//            }
//            return price;
//        }
    }

    /**
     * 判断两个日期的大小，只精确到分钟
     *
     * @param date1
     * @param date2
     * @return date1 > date2 时返回 1
     */
    private int compare(String date1, String date2) throws ParseException {
        Date d1 = DateUtil.str2date(date1, "yyyy-MM-dd HH:mm");
        Date d2 = DateUtil.str2date(date2, "yyyy-MM-dd HH:mm");
        return d1.compareTo(d2);
    }

    /**
     * 计算小时差，不满一小时按一小时计算，只精确到分钟
     *
     * @param startTime
     * @param endTime
     * @return
     */
    private int calcHourDiff(String startTime, String endTime) throws ParseException {
        long start = DateUtil.str2date(startTime, "yyyy-MM-dd HH:mm").getTime();
        long end = DateUtil.str2date(endTime, "yyyy-MM-dd HH:mm").getTime();
        long diff = Math.abs(end - start);
        double hour = diff / 1000.0 / 60.0 / 60.0;
        return (int) Math.ceil(hour);
    }

    /**
     * 查询车场信息，附带代泊价格规则
     *
     * @param parkingId 车场id
     * @return
     */
    @Override
    public Parking queryParkingWithPrice(String parkingId) {
        Parking parking = parkingService.queryById(parkingId);
        if (parking == null) {
            throw new NullPointerException("没有id为：" + parkingId + "的数据!");
        }
        // 查询对应的价格规则信息
        ParkingPrice parkingPrice = new ParkingPrice();
        parkingPrice.setParkingId(parkingId);
        List<ParkingPrice> priceList = this.selectList(parkingPrice);
        if (priceList != null && priceList.size() != 0) {
            for (ParkingPrice item : priceList) {
                if (item.getSchemeId().equals("1")) {
                    parking.setScheme_init_price(item.getPrice());
                } else if (item.getSchemeId().equals("2")) {
                    parking.setScheme_exceed_price(item.getPrice());
                } else if (item.getSchemeId().equals("3")) {
                    parking.setScheme_proxy_price(item.getPrice());
                } else if (item.getSchemeId().equals("4")) {
                    parking.setScheme_park_price(item.getPrice());
                } else if (item.getSchemeId().equals("5")) {
                    parking.setScheme_exceed_night_price(item.getPrice());
                } else if (item.getSchemeId().equals("6")) {
                    parking.setScheme_top_price(item.getPrice());
                } else if (item.getSchemeId().equals("7")) {
                    parking.setScheme_discount(item.getPrice());
                } else if (item.getSchemeId().equals("8")) {
                    parking.setScheme_init_hour(item.getPrice());
                }
            }
        }
        return parking;
    }

}