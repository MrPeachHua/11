package com.boxiang.share.app.appsideb.car.controller;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.car.service.CarService;
import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.system.synwhite.po.Specialparkinginfo;
import com.boxiang.share.system.synwhite.service.SpecialparkinginfoService;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.user.service.UserInfoService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kaiser on 2016/5/26.
 */
@Controller
@RequestMapping("app/sideb/car")
public class CarSidebController extends BaseController {

    private static final Logger logger = Logger.getLogger(CarSidebController.class);
    @Resource
    private OrderMainService orderMainService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private CarService carService;
    @Resource
    private SpecialparkinginfoService specialparkinginfoService;

    /**
     * 删除特殊车辆
     */
    @RequestMapping("deleteSpecial")
    public void deleteSpecial(@RequestParam String version,
                              @RequestParam String villageId,
                              @RequestParam String carNumber,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        String message = null;
        try {
            Specialparkinginfo entity = new Specialparkinginfo();
            entity.setCarNumber(carNumber);
            entity.setVillageId(villageId);
            entity.setIsUsed(Constants.FALSE);
            specialparkinginfoService.updateParkingInfo(entity);
            message = ShangAnMessageType.operateToJson("0", "success");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        } finally {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(message);
        }
    }

    /**
     * 车辆管理
     */
    @RequestMapping("carManage")
    public void carManage(@RequestParam String version,
                          @RequestParam String userId,
                          @RequestParam int pageIndex,
                          @RequestParam String type,
                          String parkingId,
                          String carNumber,
                          @RequestParam(required = false, defaultValue = "10") int pageSize,
                          HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String message = null;
        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setSysUserId(userId);
            List<UserInfo> userList = userInfoService.selectList(userInfo);
            if (userList == null || userList.size() == 0) {
                message = ShangAnMessageType.operateToJson("1", "userId不存在");
                return;
            }
            userInfo = userList.get(0);
            if (StringUtils.isEmpty(userInfo.getParkingId())) {
                message = ShangAnMessageType.operateToJson("1", "该用户下没有车场");
                return;
            }
            if (StringUtils.isEmpty(parkingId)) {
                parkingId = userInfo.getParkingId();
            }
            Page<Object> page = new Page<>();
            page.setCurrentPage(pageIndex);
            page.setPageSize(pageSize);
            page.getParams().put("type", type.split(","));
            page.getParams().put("parkingId", parkingId.split(","));
            if (!StringUtils.isEmpty(carNumber)) {
                page.getParams().put("carNumber", carNumber.split(","));
            }
            page = carService.carManage(page);
            message = ShangAnMessageType.toShangAnJson("0", "查询成功，无数据！", "data", page.getResultList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        } finally {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(message);
        }
    }


    @RequestMapping("queryStream")
    public void queryStream(HttpServletRequest request, HttpServletResponse response) {
        String message = null;
        String parkingId = request.getParameter("parkingId");
        String orderType = request.getParameter("orderType");
        String carNumber = request.getParameter("carNumber");
        String userId = request.getParameter("userId");
        String version = request.getParameter("version");
        String pageIndex = request.getParameter("pageIndex");
        List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();
        try {
            Page<OrderMain> page = new Page<OrderMain>();
            //选择的车场参数
            if (parkingId != null && !parkingId.equals("")) {
               if(parkingId.endsWith(",")){
                   parkingId=parkingId.substring(0,parkingId.length()-1);
               }
                parkingId="'"+parkingId.replace(",","','")+"'";
                page.getParams().put("parking", parkingId);
            } else { //  登录用户可查看的车场参数
                UserInfo userInfo = new UserInfo();
                userInfo.setSysUserId(userId);
                List<UserInfo> list = userInfoService.selectList(userInfo);
                if (list != null && list.size() > 0) {
                    UserInfo userInfo2 = list.get(0);
                    if (userInfo2.getParkingId() != null && !userInfo2.getParkingId().equals("")) {
                        String park = new String();
                        if(userInfo2.getParkingId().endsWith("'")){
                            park=userInfo2.getParkingId().substring(0,userInfo2.getParkingId().length()-1);
                        }
                        park="'"+userInfo2.getParkingId().replace(",","','")+"'";
                        page.getParams().put("parking", park);
                    }
                }
            }
            page.setCurrentPage((long) Integer.parseInt(pageIndex));
            page.setPageSize(15);
            page.getParams().put("pageIndex", pageIndex);
            page.getParams().put("carNumber", carNumber);
            List<OrderMain> orderMainList = new ArrayList<OrderMain>();
            if (orderType != null && !orderType.equals("")) {
                if(orderType.endsWith(",")){
                    orderType=orderType.substring(0,orderType.length()-1);
                }
                page.getParams().put("order", orderType);
                orderMainList = orderMainService.queryListByStream(page); //月租或者产权 临停
            } else {
                orderMainList = orderMainService.queryListByStream(page); // 月租产权临停合并
            }
            if (null != orderMainList && orderMainList.size() > 0) {
                for (OrderMain mo : orderMainList) {
                    Map<String, Object> orderMap = new HashMap<String, Object>();
                    orderMap.put("orderType", (null != mo.getOrderType()) ? mo.getOrderType() : "");
                    orderMap.put("payTime", (null != mo.getPayTime()) ? DateUtil.date2str(mo.getPayTime(), DateUtil.DATETIME_FORMAT) : DateUtil.date2str(mo.getCreateDate(), DateUtil.DATETIME_FORMAT));
                    orderMap.put("amountPaid", null != mo.getAmountPaid() ? mo.getAmountPaid() / 100 : "");
                    orderMap.put("carNumber", (null != mo.getCarNumber()) ? mo.getCarNumber() : "");
                    orderList.add(orderMap);
                }
                message = ShangAnMessageType.toShangAnJson("0", "查询成功", "order", orderList);
            } else {
                message = ShangAnMessageType.toShangAnJson("0", "查询成功无数据", "order", orderList);
            }
        }  catch (Exception e) {
            message = ShangAnMessageType.toShangAnJson("1", "异常", "order", orderList);
    }
        PrintWriter out;
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(message);
        } catch (IOException e) {
            logger.error("", e);
        }
    }


}
