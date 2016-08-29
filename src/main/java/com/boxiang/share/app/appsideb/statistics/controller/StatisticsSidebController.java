package com.boxiang.share.app.appsideb.statistics.controller;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.product.statistics.service.StatisticsService;
import com.boxiang.share.utils.ShangAnMessageType;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("app/sideb/statistics")
public class StatisticsSidebController extends BaseController {
    @Resource
    private OrderMainService orderMainService;

    @Resource
    private StatisticsService statisticsService;
    /**
     * 查询用户管理车场下日销售额
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("getDaySale")
    public void getDaySale(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String version = request.getParameter("version");
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
        if(!"1.3.8".equals(version)){
            mess = ShangAnMessageType.operateToJson("2","版本不正确");
            out.print(mess);
            return;
        }
        mess = statisticsService.getDaySaleStatistics(userId);

        out.print(mess);
    }
    /**
     * 查询用户管理车场下日销售额
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("getWeekSale")
    public void getWeekSale(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String version = request.getParameter("version");
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
        if(!"1.3.8".equals(version)){
            mess = ShangAnMessageType.operateToJson("2","版本不正确");
            out.print(mess);
            return;
        }
        mess = statisticsService.getWeekSaleStatistics(userId);

        out.print(mess);
    }
    /**
     * 查询用户管理车场下月销售额
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("getMonthSale")
    public void getMonthSale(HttpServletRequest request, HttpServletResponse response) {
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String userId = request.getParameter("userId");
        String version = request.getParameter("version");
        String parkingId = request.getParameter("parkingId");
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
        if(StringUtils.isEmpty(year) || StringUtils.isEmpty(month)){
            mess = ShangAnMessageType.operateToJson("2","month year不能为空");
            out.print(mess);
            return;
        }
        if(!"1.3.8".equals(version)){
            mess = ShangAnMessageType.operateToJson("2","版本不正确");
            out.print(mess);
            return;
        }
        mess = statisticsService.getMonthStreamStatistics(userId, year, month,parkingId);

        out.print(mess);


    }
    /**
     * 查询项目统计日流水
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("getMonthStream")
    public void getMonthStream(HttpServletRequest request, HttpServletResponse response) {
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String userId = request.getParameter("userId");
        String version = request.getParameter("version");
        String parkingId = request.getParameter("parkingId");
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
        if(StringUtils.isEmpty(year) || StringUtils.isEmpty(month)){
            mess = ShangAnMessageType.operateToJson("2","month year不能为空");
            out.print(mess);
            return;
        }
        if(!"1.3.8".equals(version)){
            mess = ShangAnMessageType.operateToJson("2","版本不正确");
            out.print(mess);
            return;
        }
        mess = statisticsService.getMonthDaySaleStatistics(userId, year, month,parkingId);

        out.print(mess);
    }
}
