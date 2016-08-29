package com.boxiang.share.app.appsideb.parking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.product.parking.po.CarInOutRecord;
import com.boxiang.share.product.parking.po.CarInOutRecordV2;
import com.boxiang.share.product.parking.service.CarInOutRecordService;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.user.service.UserInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boxiang.framework.base.controller.BaseController;

@Controller
@RequestMapping("app/sideb/parking")
public class ParkingSidebController extends BaseController {
    @Resource
    private OrderMainService orderMainService;
    @Resource
    private CarInOutRecordService carInOutRecordService;
    @Resource
    private UserInfoService userInfoService;
    /**
     * 临停缴费首页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("clearance/{userId}/{carNumber}/{version}/{summary}")
    public void clearance(@PathVariable String userId,@PathVariable String carNumber,HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        String mess  = null;
        //设置utf-8
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mess = orderMainService.clearanceCar(userId,carNumber);

        out.print(mess);
    }
    @RequestMapping("queryRecordPage")
    public void queryRecordPage(HttpServletRequest request,HttpServletResponse response){
        Page<CarInOutRecordV2> carInOutRecordPage = new Page<>();
        String carNumber = request.getParameter("carNumber");
        String type = request.getParameter("type");
        String parkingId = request.getParameter("parkingId");
        String chargeType = request.getParameter("chargeType");
        String pageIndex = request.getParameter("pageIndex");
        String userId = request.getParameter("userId");
        if (parkingId !=null && !"".equals(parkingId)){
            carInOutRecordPage.getParams().put("parkingId",parkingId);
        }else {
            UserInfo userInfo = new UserInfo();
            userInfo.setSysUserId(userId);
            List<UserInfo> userInfos = userInfoService.selectList(userInfo);
            UserInfo userInfo1 = userInfos.get(0);
            carInOutRecordPage.getParams().put("parkingId",userInfo1.getParkingId());
        }
        carInOutRecordPage.getParams().put("type",type);
        if (StringUtils.isNotEmpty(chargeType)) {
            carInOutRecordPage.getParams().put("chargeType", "'" + chargeType.replace(",", "','") + "'");
        }
        carInOutRecordPage.getParams().put("plateId",carNumber);
        carInOutRecordPage.getParams().put("pageIndex",pageIndex);
        carInOutRecordPage.setCurrentPage(Integer.parseInt(pageIndex));
        carInOutRecordPage.setPageSize(15);
        String mess = null;
        mess=carInOutRecordService.queryRecordPage(carInOutRecordPage);
        PrintWriter out;
        response.setContentType("text/html;charset=UTF-8");
        try {
            out=response.getWriter();
            out.print(mess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
