package com.boxiang.share.app.cloud.cloudController;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.app.cloud.service.CloudService;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.json.JsonMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

/**
 * Created by hua on 2016/8/18.
 */
@Controller
@RequestMapping("app")
public class CloudController extends BaseController {
    private static final Logger logger = Logger.getLogger(CloudController.class);

    @Resource
    private CloudService cloudService;

    /**
     * @param request
     * @param response
     */
    @RequestMapping("carPaid")
    public void delete(@RequestParam String data,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        String message = "";
        try {
            //Map<String, Object> params = (Map<String, Object>) JsonMapper.fromJson(data, Map.class);
            message = cloudService.carPaid(data);
            print(message,response);
        } catch (ParseException e) {
            logger.error("日期转换异常", e);
            print(ShangAnMessageType.operateToJson("2","日期转换异常"),response);
        } catch (SQLException e) {
            logger.error("数据库异常", e);
            print(ShangAnMessageType.operateToJson("2","数据库异常"),response);
        }
    }
    /**
     * @param request
     * @param response
     */
    @RequestMapping("synCarType")
    public void synCarType(@RequestParam String data,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        String message = "";
        try {
            Map<String, Object> params = (Map<String, Object>) JsonMapper.fromJson(data, Map.class);
            message = cloudService.synCarType(params);
            print(message,response);
        } catch (IOException e) {
            logger.error("json转换错误", e);
            print(ShangAnMessageType.operateToJson("2","json转换错误"),response);
        } catch (ParseException e) {
            logger.error("日期转换异常", e);
            print(ShangAnMessageType.operateToJson("2","日期转换异常"),response);
        } catch (SQLException e) {
            logger.error("数据库异常", e);
            print(ShangAnMessageType.operateToJson("2","数据库异常"),response);
        }
    }

    private void print(String message, HttpServletResponse response) {
        PrintWriter out;
        //response.setContentType("application/json;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            out.print(message);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

}
