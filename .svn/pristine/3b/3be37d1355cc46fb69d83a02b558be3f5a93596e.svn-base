package com.boxiang.share.blue;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.utils.ShangAnMessageType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("blue/pay")
public class PayController extends BaseController {

    private static final Logger logger = Logger.getLogger(PayController.class);

    @Resource
    private OrderMainService orderMainService;

//    /**
//     * 给蓝卡云调用的自动支付接口
//     * @param parkId 蓝卡云的车场id
//     * @param parkName 蓝卡云的车场名称
//     * @param orderId 蓝卡云的订单id
//     * @param carNumber 临停的车牌号
//     * @param carType 用户类型，来源于蓝卡云
//     * @param amountPayable 应付金额 单位 分
//     * @param beginDate 停车开始时间 格式 yyyy-MM-dd HH:mm:ss
//     * @param endDate 停车结束时间 格式 yyyy-MM-dd HH:mm:ss
//     */
//    @RequestMapping("walletPay")
//    public void walletPay(HttpServletRequest request,
//                          HttpServletResponse response,
//                          @RequestParam String parkId,
//                          @RequestParam String parkName,
//                          @RequestParam String orderId,
//                          @RequestParam String carNumber,
//                          @RequestParam String carType,
//                          @RequestParam Integer amountPayable,
//                          @RequestParam String beginDate,
//                          @RequestParam String endDate) throws IOException {
//        String msg = null;
//        try {
//            msg = orderMainService.blueWalletPay(parkId, parkName, orderId, carNumber, carType, amountPayable, beginDate, endDate);
//        } catch (Exception e) {
//            logger.error("", e);
//            msg = ShangAnMessageType.operateToJson("2", "异常");
//        }
//        response.setContentType("text/html;charset=UTF-8");
//        response.getWriter().print(msg);
//    }

    /**
     * 给蓝卡云调用的自动支付接口
     *
     * @param parkId        蓝卡云的车场id
     * @param parkName      蓝卡云的车场名称
     * @param orderId       蓝卡云的订单id
     * @param carNumber     临停的车牌号
     * @param carType       用户类型，来源于蓝卡云
     * @param amountPayable 应付金额 单位 分
     * @param beginDate     停车开始时间 格式 yyyy-MM-dd HH:mm:ss
     * @param endDate       停车结束时间 格式 yyyy-MM-dd HH:mm:ss
     */
    @RequestMapping("walletPay")
    public void walletPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String msg = null;
        try {
            Map<String, String> params = (Map<String, String>) request.getAttribute(SignInterceptor.PARAMS);
            String parkId = params.get("parkId");
            String parkName = params.get("parkName");
            String orderId = params.get("orderId");
            String carNumber = params.get("carNumber");
            String carType = params.get("carType");
            Integer amountPayable = Integer.valueOf(params.get("amountPayable"));
            String beginDate = params.get("beginDate");
            String endDate = params.get("endDate");
            msg = orderMainService.blueWalletPay(parkId, parkName, orderId, carNumber, carType, amountPayable, beginDate, endDate);
        } catch (Exception e) {
            logger.error("", e);
            msg = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.setContentType("text/html;charset=UTF-8");
        logger.info("blue/pay--------------------------" + msg);
        response.getWriter().print(msg);
    }

}
