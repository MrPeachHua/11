package com.boxiang.share.app.parker.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.product.order.po.OrderPark;
import com.boxiang.share.product.order.po.ParkerOrderVo;

import com.boxiang.share.product.parker.po.*;
import com.boxiang.share.product.parker.service.*;
import com.boxiang.share.product.parking.service.DiscountParkingPriceService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.order.service.OrderParkService;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingPriceService;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.system.po.Sequence;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.MD5Util;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.TableNameConstants;

@Controller
@RequestMapping("app/parker")
public class ParkerController extends BaseController {
    private static final Logger logger = Logger.getLogger(ParkerController.class);
    @Resource
    private QuestionnaireInfoService questionnaireInfoService;
    @Resource
    private QuestionnaireSurveyService questionnaireSurveyService;
    @Resource
    private SequenceService sequenceService;
    @Resource
    private ParkingService parkingService;
    @Resource
    private ParkerService parkerService;
    @Resource
    private OrderParkService orderParkService;
    @Resource
    private ParkingPriceService parkingPriceService;
    @Resource
    private CollectionParkingService collectionParkingService;
    @Resource
    private ParkerStateRecordService parkerStateRecordService;
    @Resource
    private DiscountParkingPriceService discountParkingPriceService;

    /**
     * 用户 关键词搜索停车场
     *
     * @param parkingName
     * @param version
     * @param request
     * @param response
     */
    @RequestMapping("searchParkListbyName/{parkingName}/{version}/{summary}")
    public void searchParkListbyName(@PathVariable String parkingName, @PathVariable String version,
                                     HttpServletRequest request, HttpServletResponse response) {
        String mess = null;
        if ("1.3.7".equals(version)) {
            Parking parking = new Parking();
            parking.setParkingName(parkingName);
            List<Parking> list = parkingService.searchParkListbyNameDemo2(parking);
            String path = request.getContextPath();
            String im = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            for (Parking p : list) {
                p.setParkingPath(im + (null != p.getParkingPath() ? p.getParkingPath() : ""));
            }
            mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "list", list);
        }
        PrintWriter out;
        //设置utf-8
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(mess);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * 用户 某经纬度附近的停车场列表
     *
     * @param parkingLatitude
     * @param parkingLongitude
     * @param version
     * @param request
     * @param response
     */
    @RequestMapping("searchParkListByLL/{parkingLatitude}/{parkingLongitude}/{version}/{summary}")
    public void searchParkListByLL(@PathVariable String parkingLatitude, @PathVariable String parkingLongitude,
                                   @PathVariable String version, HttpServletRequest request, HttpServletResponse response) {
        String mess = null;
        if ("1.3.7".equals(version)) {
            Parking parking = new Parking();
            parking.setParkingLatitude(parkingLatitude);
            parking.setParkingLongitude(parkingLongitude);
            String dayOfWeek = DateUtil.getDayOfWeek(new Date(), 0);
            String weekStr = discountParkingPriceService.numToStr(dayOfWeek);
            parking.setVipStartTime(weekStr + "_begin_time");
            parking.setVipStopTime(weekStr + "_end_time");
            parking.setVipSharePriceComment(weekStr + "_price");
            List<Parking> list = parkingService.searchParkListByLLDemo2(parking);
            if (list != null && list.size() > 0) {
                String path = request.getContextPath();
                String im = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
                for (Parking p : list) {
                    p.setParkingPath(im + (null != p.getParkingPath() ? p.getParkingPath() : ""));
                }
                mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "list", list);
            } else {
                mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "list", list);
            }
        }
        PrintWriter out;
        //设置utf-8
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(mess);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * 用户查看所有的停车场
     *
     * @param customerId
     * @param pageIndex
     * @param version
     * @param request
     * @param response
     */
    @RequestMapping("cusFindAllParking/{customerId}/{pageIndex}/{version}/{summary}")
    public void cusFindAllParking(@PathVariable String customerId, @PathVariable String pageIndex, @PathVariable String version,
                                  HttpServletRequest request, HttpServletResponse response) {
        String mess = null;
        if ("1.3.7".equals(version)) {
            Page<Parking> page = new Page<Parking>();
            page.getParams().put("customerId", customerId);
            page.getParams().put("pageIndex", pageIndex);
            page.setCurrentPage(Integer.valueOf(pageIndex));
            String path = request.getContextPath();
            String im = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            mess = parkingService.searchParking(page, im);
        }
        PrintWriter out;
        //设置utf-8
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(mess);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * 显示首页信息
     *
     * @param customerId
     * @param version
     * @param request
     * @param response
     */
    @RequestMapping("indexShow/{customerId}/{version}/{summary}")
    public void indexShow(@PathVariable String customerId, @PathVariable String version, HttpServletRequest request, HttpServletResponse response) {
        String mess = null;
        if ("1.3.7".equals(version)) {
            String homeParkingId = null;
            String workParkingId = null;
            Parking parking = new Parking();
            parking.setCustomerId(customerId);
            Parking parking1 = parkingService.selectHomeAndWorkId(parking);
            if (parking1 != null) {
                homeParkingId = parking1.getHomeParkingId();
                workParkingId = parking1.getWorkParkingId();
                List<Parking> list = new ArrayList<Parking>();
                if (homeParkingId != null && !"".equals(homeParkingId)) {
                    Parking parking2 = new Parking();
                    parking2.setParkingId(homeParkingId);
                    Parking p1 = parkingService.indexShowByPid(parking2);
                    if (p1 != null) {
                        p1.setIndexParkingType("1");
                    }
                    list.add(p1);
                }
                if (workParkingId != null && !"".equals(workParkingId)) {
                    Parking parking2 = new Parking();
                    parking2.setParkingId(workParkingId);
                    Parking p1 = parkingService.indexShowByPid(parking2);
                    if (p1 != null) {
                        p1.setIndexParkingType("2");
                    }
                    list.add(p1);
                }
                String path = request.getContextPath();
                String im = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
                for (Parking p : list) {
                    p.setParkingPath(p.getParkingPath() == null ? "" : (im + p.getParkingPath()));
                }
                mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "list", list);
            } else {
                mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "list",new ArrayList<>());
            }
        }
        PrintWriter out;
        //设置utf-8
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(mess);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * 取消收藏车场
     *
     * @param customerId
     * @param parkingId
     * @param version
     * @param request
     * @param response
     */
    @RequestMapping("cusCancelCollection/{customerId}/{parkingId}/{version}/{summary}")
    public void cusCancelCollection(@PathVariable String customerId, @PathVariable String parkingId, @PathVariable String version,
                                    HttpServletRequest request, HttpServletResponse response) {
        String message = null;
        if ("1.3.7".equals(version)) {
            CollectionParking collectionParking = new CollectionParking();
            collectionParking.setCustomerId(customerId);
            collectionParking.setParkingId(parkingId);
            int index = collectionParkingService.cusCancelCollection(collectionParking);
            if (index != 0) {
                message = ShangAnMessageType.operateToJson("0", "取消成功");
            } else {
                message = ShangAnMessageType.operateToJson("1", "用户未收藏");
            }
        } else {
            message = ShangAnMessageType.operateToJson("2", "版本输入有问题");
        }
        PrintWriter out;
        //设置utf-8
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(message);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * 设首屏
     *
     * @param customerId
     * @param parkingId
     * @param indexParkingType
     * @param version
     * @param request
     * @param response
     */
    @RequestMapping("setDefaultScan/{customerId}/{parkingId}/{indexParkingType}/{version}/{summary}")
    public void setDefaultScan(@PathVariable String customerId, @PathVariable String parkingId,
                               @PathVariable String indexParkingType, @PathVariable String version,
                               HttpServletRequest request, HttpServletResponse response) {
        String message = null;
        if ("1.3.7".equals(version)) {
            int ipt = 0;
            ipt = Integer.parseInt(indexParkingType);
            CollectionParking collectionParking = new CollectionParking();
            collectionParking.setCustomerId(customerId);
            collectionParking.setParkingId(parkingId);
            collectionParking.setIndexParkingType(ipt);
            collectionParkingService.setDefaultScan(collectionParking);
            int index = collectionParking.getDefaultScan();
            if (index == 0 || index == 1 || index == 2 || index == 3 || index == 4) {
                message = ShangAnMessageType.operateToJson("0", "设置成功");
            } else if (index == 6 || index == 7) {
                message = ShangAnMessageType.operateToJson("1", "已设置为家");
            } else {
                message = ShangAnMessageType.operateToJson("2", "数据异常");
            }
        }
        PrintWriter out;
        //设置utf-8
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(message);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * 查看收藏清单
     *
     * @param customerId
     * @param pageIndex
     * @param version
     * @param request
     * @param response
     */
    @RequestMapping("searchParkingCollection/{customerId}/{index}/{pageIndex}/{version}/{summary}")
    public void searchParkingCollection(@PathVariable String customerId, @PathVariable String index, @PathVariable String pageIndex, @PathVariable String version, HttpServletRequest request, HttpServletResponse response) {
        String mess = null;
        if ("1.3.7".equals(version)) {
            Page<CollectionParking> page = new Page<CollectionParking>();
            page.getParams().put("customerId", customerId);
            page.getParams().put("index", index);
            page.getParams().put("pageIndex", pageIndex);
            page.setCurrentPage(Integer.valueOf(pageIndex));
            String path = request.getContextPath();
            String im = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
            mess = collectionParkingService.searchParkingCollection(page, im);
        }
        PrintWriter out;
        //设置utf-8
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(mess);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * 收藏 停车场
     *
     * @param parkingId
     * @param customerId
     * @param version
     * @param request
     * @param response
     */
    @RequestMapping("saveParkingCollection/{parkingId}/{customerId}/{version}/{summary}")
    public void saveParkingCollection(@PathVariable String parkingId, @PathVariable String customerId, @PathVariable String version, HttpServletRequest request, HttpServletResponse response) {
        String mess = null;
        if ("1.3.7".equals(version)) {
            CollectionParking collectionParking = new CollectionParking();
            collectionParking.setCustomerId(customerId);
            collectionParking.setParkingId(parkingId);
            List<CollectionParking> temp = collectionParkingService.selectCollection(collectionParking);
            if (temp != null && temp.size() > 0) {
                //已被收藏
                mess = ShangAnMessageType.operateToJson("1", "已收藏");
            } else {
                collectionParkingService.add(collectionParking);
                mess = ShangAnMessageType.operateToJson("0", "收藏成功");
            }

        }
        PrintWriter out;
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(mess);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * 获取停车场详情
     *
     * @param parkingId
     * @param customerId
     * @param version
     * @param request
     * @param response
     */
    @RequestMapping("getParking/{parkingId}/{customerId}/{version}/{summary}")
    public void getParking(@PathVariable String parkingId, @PathVariable String customerId, @PathVariable String version, HttpServletRequest request, HttpServletResponse response) {
        String mess = null;
        if ("1.3.7".equals(version)) {
            Parking parking1 = new Parking();
            parking1.setParkingId(parkingId);
            parking1.setCustomerId(customerId);
            Parking parking = parkingService.parkingInfoDetail(parking1);
            if (parking != null) {
                Map<String, String> parkingMap = new HashMap<String, String>();
                parkingMap.put("parkingId", parking.getParkingId());
                parkingMap.put("parkingName", parking.getParkingName());
                parkingMap.put("peacetimePrice", null != parking.getPeacetimePrice() ? parking.getPeacetimePrice() : "");
                parkingMap.put("sharePriceComment", null != parking.getSharePriceComment() ? parking.getSharePriceComment() : "");
                parkingMap.put("parkPriceComment", null != parking.getParkPriceComment() ? parking.getParkPriceComment() : "");
                parkingMap.put("maximumHour", null != parking.getMaxinumHour() ? parking.getMaxinumHour() : "");
                parkingMap.put("parkingPath", null != parking.getParkingPath() ? parking.getParkingPath() : "");
                parkingMap.put("parkingAddress", null != parking.getParkingAddress() ? parking.getParkingAddress() : "");
                parkingMap.put("parkingCanUse", null != parking.getParkingCanUse().toString() ? parking.getParkingCanUse().toString() : "");
                parkingMap.put("parkingLatitude", null != parking.getParkingLatitude() ? parking.getParkingLatitude() : "");
                parkingMap.put("parkingLongitude", null != parking.getParkingLongitude() ? parking.getParkingLongitude() : "");
                parkingMap.put("isCharge", null != parking.getIsCharge().toString() ? parking.getIsCharge().toString() : "");
                parkingMap.put("canUse", null != parking.getCanUse().toString() ? parking.getCanUse().toString() : "");
                parkingMap.put("isCollection", null != (parking.getIsCollection() + "") ? parking.getIsCollection() + "" : "");
                parkingMap.put("defaultScan", null != (parking.getDefaultScan() + "") ? parking.getDefaultScan() + "" : "");
                parkingMap.put("parkBeginTime", null == parking.getParkBeginTime() ? "" : parking.getParkBeginTime());
                parkingMap.put("parkEndTime", null == parking.getParkEndTime() ? "" : parking.getParkEndTime());
                parkingMap.put("rule", null == parking.getRule() ? "" : parking.getRule());
                parkingMap.put("isAutoPay", null == parking.getIsAutoPay() ? "" : parking.getIsAutoPay());
                mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "parkingMap", parkingMap);
            } else {
                mess = ShangAnMessageType.operateToJson("1", "无数据");
            }
            PrintWriter out;
            response.setContentType("text/html;charset=UTF-8");
            try {
                out = response.getWriter();
                out.print(mess);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("", e);
            }
        }
    }

    /**
     * 查询是否能够代泊
     *
     * @param version   1.3.7
     * @param parkingId 车场id
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("isCanPark")
    public void isCanPark(@RequestParam String version,
                          @RequestParam String parkingId,
                          HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String message;
        try {
            Parking parking = parkingService.queryById(parkingId);
            Map<String, String> map = new HashMap<>(3);
            // 目标车场总的可用车位数
            Integer targetParkingCanUseCount = parkingService.queryTargetParkingCanUseTotalCount(parking.getParkingId());
            map.put("targetParkingCanUseCount", targetParkingCanUseCount == null ? "0" : Integer.toString(targetParkingCanUseCount));
            // 代泊服务是否已满
            boolean parkServiceIsFull = parkingService.parkIsFull(parking);
            map.put("parkServiceIsFull", parkServiceIsFull ? "1" : "0");
            // 是否有当班的代泊员
            int parkerCount = parkingService.parkerCount(parking.getParkingId());
            map.put("parkerCount", Integer.toString(parkerCount));
            message = ShangAnMessageType.toShangAnJson("0", "success", "data", map);
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(message);
    }

    /**
     * 问卷列表
     *
     * @param surveyType
     * @param request
     * @param response
     */
    @RequestMapping("questionnairelist/{surveyType}/{summary}")
    public void questionnairelist(@PathVariable String surveyType, HttpServletRequest request, HttpServletResponse response) {
        String message = null;
        QuestionnaireInfo questionnaireInfo = new QuestionnaireInfo();
        questionnaireInfo.setSurveyType(surveyType);
        message = questionnaireInfoService.selectQuestionNaireInfoList(questionnaireInfo);
        PrintWriter out;
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(message);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * 问卷提交
     *
     * @param request
     * @param response
     */
    @RequestMapping("questionnairec")
    public void questionnairec(HttpServletRequest request, HttpServletResponse response) {
        String message = null;
        String customerId = request.getParameter("customerId");
        String surveyType = request.getParameter("surveyType");
        String content = request.getParameter("content");
        //  String[] contents=	content.split(",");
        //	for (int i = 0; i < contents.length; i++) {
        QuestionnaireSurvey questionnaireSurvey = new QuestionnaireSurvey();
        Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_ORDER_MAIN);
        questionnaireSurvey.setSurveyType(surveyType);
        //questionnaireSurvey.setContent(contents[i].substring(1, contents[i].length()-1));//去掉第一位和最后一位的【】
        questionnaireSurvey.setContent(content);
        questionnaireSurvey.setCreateDate(new Date());
        questionnaireSurvey.setCreateor("admin");
        questionnaireSurvey.setCustomerId(customerId);
        questionnaireSurvey.setQuestCode(sequence.getId());
        message = questionnaireSurveyService.addQuestionnaireSurvey(questionnaireSurvey);
        //	}

        PrintWriter out;
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(message);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * 查询代泊订单
     *
     * @param customerId
     * @param request
     * @param response
     */
    @RequestMapping("queryParkerById")
    public void queryParkerById(@RequestParam String version,
                                @RequestParam String customerId,
                                @RequestParam(required = false) String carNumber,
                                @RequestParam(required = false) String parkingId,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        String message;
        message = parkerService.queryParkerById(customerId, carNumber, parkingId);
        PrintWriter out;
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(message);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * 查询已完成代泊订单
     *
     * @param customerId
     * @param request
     * @param response
     */
    @RequestMapping("queryFinishParkOrder")
    public void queryFinishParkOrder(@RequestParam String version,
                                     @RequestParam String customerId,
                                     @RequestParam int pageIndex,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        String message;
        message = parkerService.queryFinishParkOrder(customerId, pageIndex);
        PrintWriter out;
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(message);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * 计算代泊价格
     *
     * @param version   版本号 1.3.7
     * @param parkingId 车场id
     * @param startTime 开始时间 yyyy-MM-dd HH:mm:ss
     * @param endTime   结束时间 yyyy-MM-dd HH:mm:ss
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("calcParkPrice")
    public void calcParkPrice(@RequestParam String version,
                              @RequestParam String parkingId,
                              @RequestParam String startTime,
                              @RequestParam String endTime,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        String message;
        try {
            message = parkingPriceService.calcParkPrice(parkingId, startTime, endTime);
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(message);
    }

    /**
     * 更改代泊员状态
     *
     * @param version  1.3.7
     * @param parkerId 代泊员id
     * @param state    0:休班  1:当班
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("changeState")
    public void changeState(@RequestParam String version,
                            @RequestParam String parkerId,
                            @RequestParam String state,
                            String info,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        String message;
        try {
            parkerService.updateState(parkerId, state);
            try {
                ParkerStateRecord record = new ParkerStateRecord();
                record.setCreateDate(new Date());
                record.setIsUsed(Constants.TRUE);
                record.setCreateor("admin");
                record.setInfo(info);
                record.setParkerId(parkerId);
                record.setState(state);
                record.setType("1");
                parkerStateRecordService.add(record);
            } catch (Exception e) {
                logger.error("代泊员修改状态时,日志添加异常", e);
            }
            message = ShangAnMessageType.operateToJson("0", "success");
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(message);
    }

    /**
     * 代泊员端查询订单
     *
     * @param parkerId  代泊员id
     * @param tab       1:我的代泊订单  2:当日全部  3:已完成
     * @param pageIndex 页码
     * @param version   1.3.7
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("queryParkerOrder")
    public void queryParkerOrder(@RequestParam String parkerId,
                                 @RequestParam int tab,
                                 @RequestParam int pageIndex,
                                 @RequestParam String version,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {
        String message;
        try {
            Page<Object> page = new Page<>();
            page.setCurrentPage(pageIndex);
            page.getParams().put("tab", tab);
            page.getParams().put("parkerId", parkerId);
            Page<Object> result = orderParkService.queryParkerOrder(page);
            message = ShangAnMessageType.toShangAnJson("0", "success", "data", result.getResultList());
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(message);
    }

    /**
     * 代泊员端根据id查询订单
     *
     * @param orderId  订单id
     * @param response
     * @throws IOException
     */
    @RequestMapping("queryParkerOrderById")
    public void queryParkerOrder(@RequestParam String orderId, @RequestParam String version, HttpServletResponse response) throws IOException {
        String message;
        try {
            ParkerOrderVo result = orderParkService.queryParkerOrderById(orderId);
            message = ShangAnMessageType.toShangAnJson("0", "success", "data", result);
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(message);
    }

    /**
     * 开始停车
     *
     * @param version   1.3.7
     * @param orderId   订单id
     * @param imagePath 图片路径，逗号分隔
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("startPark")
    public void startPark(@RequestParam String version,
                          @RequestParam String orderId,
                          @RequestParam String imagePath,
                          HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String message;
        try {
            message = parkerService.startPark(orderId, imagePath);
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(message);
    }

    /**
     * 停车完毕
     *
     * @param version   1.3.7
     * @param orderId   订单id
     * @param imagePath 图片路径，逗号分隔
     * @param keyBox    钥匙箱
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("parked")
    public void parked(@RequestParam String version,
                       @RequestParam String orderId,
                       @RequestParam String imagePath,
                       String keyBox,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String message;
        try {
            message = parkerService.parked(orderId, imagePath, keyBox);
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(message);
    }

    /**
     * 泊回中
     *
     * @param version  1.3.7
     * @param orderId  订单id
     * @param parkerId 代泊员id
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("backParking")
    public void backParking(@RequestParam String version,
                            @RequestParam String orderId,
                            @RequestParam String parkerId,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        String message;
        try {
            message = parkerService.backParking(orderId, parkerId);
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(message);
    }

    /**
     * 已泊回
     *
     * @param version  1.3.7
     * @param orderId  订单id
     * @param backPark 泊回地点
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("backParked")
    public void backParked(@RequestParam String version,
                           @RequestParam String orderId,
                           String backPark,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        String message;
        try {
            message = parkerService.backParked(orderId, backPark);
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(message);
    }

    /**
     * 取车中
     *
     * @param version  1.3.7
     * @param orderId  订单id
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("gettingCar")
    public void gettingCar(@RequestParam String version,
                           @RequestParam String orderId,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        String message;
        try {
            message = parkerService.gettingCar(orderId);
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(message);
    }

    /**
     * 已经取到车
     *
     * @param version  1.3.7
     * @param orderId  订单id
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("gotCar")
    public void gotCar(@RequestParam String version,
                       @RequestParam String orderId,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String message;
        try {
            message = parkerService.gotCar(orderId);
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(message);
    }

    /**
     * 已完成
     *
     * @param version  1.3.7
     * @param orderId  订单id
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("parkFinished")
    public void parkFinished(@RequestParam String version,
                             @RequestParam String orderId,
                             HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        String message;
        try {
            message = parkerService.parkFinished(orderId);
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(message);
    }

    /**
     * 登录接口
     *
     * @param request
     * @return
     */
    @RequestMapping("login")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        String mess = null;
        String parkerMobile = request.getParameter("parkerMobile");
        String parkerPassword = request.getParameter("parkerPassword");
       // parkerPassword = MD5Util.md5(parkerPassword);
        if (parkerMobile != null && parkerPassword != null) {
            mess = parkerService.login(parkerMobile, parkerPassword);
        } else {
            mess = ShangAnMessageType.operateToJson("2", "手机号密码不能为空");
        }
        try {
            //设置utf-8
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            PrintWriter writer = response.getWriter();
            writer.print(mess);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    /**
     * 编辑钥匙箱或泊回地点
     */
    @RequestMapping("editOrderPark")
    public void editOrderPark(@RequestParam String version,
                              @RequestParam String orderId,
                              String keyBox,
                              String backPark,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        String message;
        try {
            OrderPark op = orderParkService.queryByOrderId(orderId);
            if (!StringUtils.isEmpty(keyBox)) {
                op.setKeyBox(keyBox);
            }
            if (!StringUtils.isEmpty(backPark)) {
                op.setBackPark(backPark);
            }
            orderParkService.update(op);
            message = ShangAnMessageType.toShangAnJson("0", "保存成功", "data", "保存成功");
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.getWriter().print(message);
    }

}
