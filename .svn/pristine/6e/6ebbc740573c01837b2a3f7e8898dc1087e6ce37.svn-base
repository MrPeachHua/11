package com.boxiang.share.app.record.controller;

import cn.b2m.eucp.client.SingletonClient;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.product.record.po.ParkingScanRecord;
import com.boxiang.share.product.record.service.ParkingScanRecordService;
import com.boxiang.share.utils.ShangAnMessageType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by kaiser on 2016/7/13.
 */
@Controller
@RequestMapping("app/event")
public class RecordController extends BaseController {
    private static final Logger logger = Logger.getLogger(RecordController.class);
    @Resource
    private ParkingScanRecordService parkingScanRecordService;

    @RequestMapping("scancode")
    public void scancode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String message = null;
        try {
            String parkingId = request.getParameter("parkingId");
            ParkingScanRecord   parkingScanRecord =parkingScanRecordService.queryById(parkingId);
            if(parkingScanRecord!=null){
                parkingScanRecord.setCount(parkingScanRecord.getCount()+1);
                parkingScanRecordService.update(parkingScanRecord);
            }
        }catch (Exception e) {
            logger.error("", e);
        }

        response.sendRedirect("http://www.p-share.cn/p_share_weixin/About/download");
    }
}
