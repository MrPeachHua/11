package com.boxiang.share.product.order.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.order.po.*;
import com.boxiang.share.product.order.service.*;
import com.boxiang.share.product.parking.po.CarInOutRecord;
import com.boxiang.share.product.parking.service.CarInOutRecordService;
import com.boxiang.share.user.po.RegionUser;
import com.boxiang.share.user.service.RegionUserService;
import com.boxiang.share.utils.json.JsonMapper;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.carlife.po.OrderRefuel;
import com.boxiang.share.product.carlife.service.OrderRefuelService;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.system.po.Dictionary;
import com.boxiang.share.system.po.Sequence;
import com.boxiang.share.system.service.DictionaryService;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.user.service.UserInfoService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.OrderConstants;
import com.boxiang.share.utils.TableNameConstants;
import com.boxiang.share.utils.json.JacksonUtil;

@Controller
@RequestMapping("products/order")
public class OrderMainController extends BaseController {
    private static final Logger logger = Logger.getLogger(OrderMainController.class);
    @Resource
    private OrderMainService orderMainService;
    @Resource
    private DictionaryService dictionaryService;
    @Resource
    private OrderEquityService orderEquityService;
    @Resource
    private OrderMonthlyService orderMonthlyService;
    @Resource
    private OrderTemporaryService orderTemporaryService;
    @Resource
    private OrderTemporaryShareService orderTemporaryShareService;
    @Resource
    private OrderParkService orderParkService;
    @Resource
    private OrderRefuelService orderRefuelService;
    @Resource
    private SequenceService sequenceService;
    @Resource
    private MonthlyparkinginfoService monthlyparkinginfoService;
    @Resource
    private PropertyparkinginfoService propertyparkinginfoService;
    @Resource
    private ParkingService parkingService;
    @Resource
    private   OrderRechargeService OrderRechargeService;
    @Resource
    private   UserInfoService userInfoService;
    @Resource
    private CenuseService cenuseService;
    @Resource
    private OrderCarwashService orderCarwashService;

    //发票
    @Resource
    private InvoiceService invoiceService;
    @Resource
    private CustomerService customerService;

    @Resource
    private CarInOutRecordService carInOutRecordService;

    @Resource
    private RegionUserService regionUserService;

    @RequestMapping("rechargelist.html")
    public ModelAndView rechargelist(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = super.getParamMap(request);
        try {
            Page<OrderMain> page = new Page<OrderMain>();
            PageHelper.initPage(request, page);
            if (request.getParameterMap().containsKey("orderStatus")){
                page.getParams().put("orderStatus", request.getParameter("orderStatus"));
            }else {
                page.getParams().put("orderStatus","11");
            }
           // page.getParams().put("orderStatus", request.getParameter("orderStatus"));
            page.getParams().put("orderId", request.getParameter("orderId"));
            page.getParams().put("payType", request.getParameter("payType"));
            page.getParams().put("customerMobile", request.getParameter("customerMobile"));
            page.getParams().put("beginTime", request.getParameter("beginTime"));
            page.getParams().put("stopTime", request.getParameter("stopTime"));
            page = orderMainService.rechargelist(page);
            PageHelper.setPageModel(request, page);

            Dictionary dictionary = new Dictionary();
            dictionary.setDictCode("order_state");
            dictionary.setIsUsed(Constants.TRUE);
            List<Dictionary> list = dictionaryService.selectList(dictionary);
            map.put("orderStatus", list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/order/order_main_recharge", map);
    }

    /*导出excel文档*/
    @RequestMapping("excelRecharge.html")
    public void excelRecharge(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("orderStatus", request.getParameter("orderStatus"));
            params.put("orderId", request.getParameter("orderId"));
            params.put("payType", request.getParameter("payType"));
            params.put("customerMobile", request.getParameter("customerMobile"));
            params.put("beginTime", request.getParameter("beginTime"));
            params.put("stopTime", request.getParameter("stopTime"));
            final List<OrderMainExcel> list = orderMainService.rechargeExcel(params);
            if (list != null && list.size() > 0) {
//                String[][] titleArray = {
//                        {"订单ID", "orderId"},
//                        {"订单状态", "orderStatus"},
//                        {"发票状态", "invoiceStatus"},
//                        {"客户名称", "customer_nickname"},
//                        {"客户手机", "customer_mobile"},
//                        {"应付金额", "amountPayable"},
//                        {"优惠金额", "amountDiscount"},
//                        {"实付金额", "amountPaid"},
//                        {"赠送金额", "giftAmount"},
//                        {"支付类型", "payType"},
//                        {"支付时间", "payTime"},
//                        {"支付交易号", "tradeNo"}
//                };
//                ExportOrderExcel.exportExcel(titleArray, list, response);
                ExportOrderExcel.exportExcel(new ExportOrderExcel.ExcelObject() {
                    @Override
                    public void dataSource(WritableSheet sheet, WritableCellFormat wcf_center, WritableCellFormat wcf_left) throws Exception {
                        sheet.addCell(new Label(0, 0, "订单ID", wcf_center));
                        sheet.addCell(new Label(1, 0, "订单状态", wcf_center));
                        sheet.addCell(new Label(2, 0, "发票状态", wcf_center));
                        sheet.addCell(new Label(3, 0, "客户名称", wcf_center));
                        sheet.addCell(new Label(4, 0, "客户手机", wcf_center));
                        sheet.addCell(new Label(5, 0, "应付金额", wcf_center));
                        sheet.addCell(new Label(6, 0, "优惠金额", wcf_center));
                        sheet.addCell(new Label(7, 0, "实付金额", wcf_center));
                        sheet.addCell(new Label(8, 0, "赠送金额", wcf_center));
                        sheet.addCell(new Label(9, 0, "支付类型", wcf_center));
                        sheet.addCell(new Label(10, 0, "支付时间", wcf_center));
                        sheet.addCell(new Label(11, 0, "支付交易号", wcf_center));
                        for (int i = 0; i < list.size(); i++) {
                            OrderMainExcel ome = list.get(i);
                            sheet.addCell(new Label(0, i + 1, ome.getOrderId().toString(), wcf_left));
                            sheet.addCell(new Label(1, i + 1, ome.getOrderStatus().toString(), wcf_left));
                            sheet.addCell(new Label(2, i + 1, ome.getInvoiceStatus().toString(), wcf_left));
                            sheet.addCell(new Label(3, i + 1, ome.getCustomer_nickname() == null ? "" : ome.getCustomer_nickname(), wcf_left));
                            sheet.addCell(new Label(4, i + 1, ome.getCustomer_mobile() == null ? "" : ome.getCustomer_mobile(), wcf_left));
                            sheet.addCell(new Number(5, i + 1, ome.getAmountPayable() / 100.00, wcf_left));
                            sheet.addCell(new Number(6, i + 1, ome.getAmountDiscount() / 100.00, wcf_left));
                            sheet.addCell(new Number(7, i + 1, ome.getAmountPaid() / 100.00, wcf_left));
                            sheet.addCell(new Number(8, i + 1, ome.getGiftAmount() == null? 0 : ome.getGiftAmount() / 100.00, wcf_left));
                            sheet.addCell(new Label(9, i + 1, ome.getPayType().toString(), wcf_left));
                            sheet.addCell(new Label(10, i + 1, ome.getPayTime() == null ? "" : DateUtil.date2str(ome.getPayTime(), DateUtil.DATETIME_FORMAT), wcf_left));
                            sheet.addCell(new Label(11, i + 1, ome.getTradeNo() == null ? "" : ome.getTradeNo(), wcf_left));
                        }
                    }
                }, response);
            } else {
                try {
                    String js = "<script>alert('没有需要导出的内容');history.back();</script>";
                    response.getWriter().print(js);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = super.getParamMap(request);
        try {
            Page<OrderMain> page = new Page<OrderMain>();
            UserInfo currUser = (UserInfo) super.getLoginUser(request);
            String parkingId = currUser.getParkingId();
            PageHelper.initPage(request, page);
            page.getParams().put("isUsed", Constants.TRUE);
            String orderType = request.getParameter("orderType");
            String orderStatus = request.getParameter("orderStatus");
            String customerName = request.getParameter("customerName");
            String customerPhone = request.getParameter("customerPhone");
            String startTime = request.getParameter("form_beginTime");
            String stopTime = request.getParameter("form_endTime");
            String payType = request.getParameter("payType");
            String orderId = request.getParameter("orderId");
            page.getParams().put("orderId", orderId);
            page.getParams().put("orderType", orderType);
           // page.getParams().put("orderStatus", orderStatus);
            if (request.getParameterMap().containsKey("orderStatus")){
                page.getParams().put("orderStatus", orderStatus);
            }else {
                page.getParams().put("orderStatus","11");
            }
            page.getParams().put("customer_nickname", customerName);
            page.getParams().put("customer_mobile", customerPhone);
            page.getParams().put("startTime", startTime);
            page.getParams().put("stopTime", stopTime);
            page.getParams().put("payType", payType);
            if ("2".equals(currUser.getRoleId() + "")) {
                page.getParams().put("parkingId", parkingId);
            } else {
                page.getParams().put("parkingId", null);
            }
            page = orderMainService.queryListPage(page);
            PageHelper.setPageModel(request, page);
            Dictionary dictionary = new Dictionary();
            dictionary.setDictCode("order_type");
            dictionary.setIsUsed(Constants.TRUE);
            List<Dictionary> list = dictionaryService.selectList(dictionary);
            if ("2".equals(currUser.getRoleId() + "")) {
                for (int i = 0; i < list.size(); i++) {
                    if ("15".equals(list.get(i).getDictValue())) {
                        list.remove(list.get(i));
                        break;
                    }
                }
            }
            Dictionary dictionary1 = new Dictionary();
            dictionary1.setDictCode("order_state");
            dictionary1.setIsUsed(Constants.TRUE);
            List<Dictionary> list1 = dictionaryService.selectList(dictionary1);
            map.put("orderType", list);
            map.put("orderStatus", list1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/order/order_main_list", map);
    }
/**
 * 临停
 * @param request
 * @param response
 */
    @RequestMapping("listTemp.html")
    public ModelAndView listTemp(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = super.getParamMap(request);
        try {
            Page<OrderMain> page = new Page<OrderMain>();
            UserInfo currUser = (UserInfo) super.getLoginUser(request);
            currUser = userInfoService.queryById(currUser.getUserId());
            String parkingId = currUser.getParkingId();
            PageHelper.initPage(request, page);
            page.getParams().put("isUsed", Constants.TRUE);
          //  String orderType = request.getParameter("orderType");
            String orderId = request.getParameter("orderId");
            String customerName = request.getParameter("customerName");
            String orderStatus = request.getParameter("orderStatus");
            String customerPhone = request.getParameter("customerPhone");
            String startTime = request.getParameter("form_beginTime");
            String stopTime = request.getParameter("form_endTime");
            String payType = request.getParameter("payType");
            String carNumber = request.getParameter("carNumber");
            page.getParams().put("carNumber", carNumber);
            page.getParams().put("orderType", "11");//
            page.getParams().put("orderId", orderId);
            String parkingName = request.getParameter("parkingName");
            if (request.getParameterMap().containsKey("orderStatus")){
                page.getParams().put("orderStatus", orderStatus);
            }else {
                page.getParams().put("orderStatus","11");
            }
            page.getParams().put("parkingName", parkingName);
            page.getParams().put("customer_nickname", customerName);
            page.getParams().put("customer_mobile", customerPhone);
            page.getParams().put("startTime", startTime);
            page.getParams().put("stopTime", stopTime);
            page.getParams().put("payType", payType);
           /* if ("2".equals(currUser.getRoleId() + "")) {
                page.getParams().put("parkingId", parkingId);
            } else {
                page.getParams().put("parkingId", null);
            }*/
            if(StringUtils.isEmpty(parkingId)){
        		page.getParams().put("parkingId", null);
        	}else{
        		String [] pkStr = parkingId.split("\\,");
        		String parkingStr = "";
    			for(int i=0;i<pkStr.length;i++){
    				parkingStr +="'";
    				parkingStr+=pkStr[i];
    				parkingStr+="'";
    				parkingStr+=",";
    			}
    			parkingStr = parkingStr.substring(0,parkingStr.length()-1);
        		page.getParams().put("parkingId", parkingStr);
        	}
//            page = orderMainService.queryListPage(page);
            page = orderMainService.queryTempPage(page);
            PageHelper.setPageModel(request, page);
         //   Dictionary dictionary = new Dictionary();
//            dictionary.setDictCode("order_type");
//            dictionary.setIsUsed(Constants.TRUE);
//            List<Dictionary> list = dictionaryService.selectList(dictionary);
//            if ("2".equals(currUser.getRoleId() + "")) {
//                for (int i = 0; i < list.size(); i++) {
//                    if ("15".equals(list.get(i).getDictValue())) {
//                        list.remove(list.get(i));
//                        break;
//                    }
//                }
//            }
            Dictionary dictionary1 = new Dictionary();
            dictionary1.setDictCode("order_state");
            dictionary1.setIsUsed(Constants.TRUE);
            List<Dictionary> list1 = dictionaryService.selectList(dictionary1);
        //    map.put("orderType", list);
            map.put("orderStatus", list1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/order/order_main_temp", map);
    }
    @RequestMapping("check.html")
    public  void checkParkingCode(HttpServletRequest request,HttpServletResponse response){
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String orderId = request.getParameter("parkingCode");
        OrderTemporaryShare orderTemporaryShare = new OrderTemporaryShare();
        orderTemporaryShare.setOrderId(orderId);
        List<OrderTemporaryShare> orderList  = orderTemporaryShareService.selectList(orderTemporaryShare);
        for (OrderTemporaryShare orderTemporaryShare1 :orderList)
        {
            try{
                if (orderTemporaryShare1.getVerifiedDate()!=null && orderTemporaryShare1.getVerifiedDate().equals(currentDate))
                {
                    response.getWriter().print("今天已经验证过了");
                }else {
                    if (Integer.parseInt(orderTemporaryShare1.getParkingNum())>1){
                        //减少一天
                        orderTemporaryShare1.setParkingNum((Integer.parseInt(orderTemporaryShare1.getParkingNum())-1)+"");
                    }else {
                        orderTemporaryShare1.setParkingNum("0");
                        orderTemporaryShare1.setVoucherStatus("1");
                    }
                    orderTemporaryShare1.setVerifiedDate(currentDate);
                    orderTemporaryShareService.updateByOrderId(orderTemporaryShare1);
                    response.getWriter().print("验证成功");
                }
            }catch (Exception e){
                logger.error(e.getMessage(),e);
            }
            break;
        }

        /*String voucherStatus = "1";
        OrderTemporaryShare orderTemporaryShare = new OrderTemporaryShare();
        orderTemporaryShare.setOrderId(orderId);
        orderTemporaryShare.setVoucherStatus(voucherStatus);
        try {
            orderTemporaryShareService.updateByOrderId(orderTemporaryShare);
            response.getWriter().print("验证成功");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }*/
    }
    /**
     * 共享临停
     * @param request
     * @param response
     */
    @RequestMapping("listTempShare.html")
    public ModelAndView listTempShare(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = super.getParamMap(request);
        try {
            Page<OrderMain> page = new Page<OrderMain>();
            UserInfo currUser = (UserInfo) super.getLoginUser(request);
            String parkingId = currUser.getParkingId();
            PageHelper.initPage(request, page);
            page.getParams().put("isUsed", Constants.TRUE);
          //  String orderType = request.getParameter("orderType");
            String orderId = request.getParameter("orderId");
            String orderStatus = request.getParameter("orderStatus");
            String customerName = request.getParameter("customerName");
            String customerPhone = request.getParameter("customerPhone");
            String startTime = request.getParameter("form_beginTime");
            String stopTime = request.getParameter("form_endTime");
            String payType = request.getParameter("payType");
            String parkingName = request.getParameter("parkingName");
            String parkingCode = request.getParameter("parkingCode");
            String carNumber = request.getParameter("carNumber");
            page.getParams().put("parkingCode",parkingCode);
            page.getParams().put("parkingName", parkingName);
            page.getParams().put("orderType", "10");
            page.getParams().put("carNumber", carNumber);
            page.getParams().put("orderId", orderId);
            if (request.getParameterMap().containsKey("orderStatus")){
                page.getParams().put("orderStatus", orderStatus);
            }else {
                page.getParams().put("orderStatus","11");
            }
            page.getParams().put("customer_nickname", customerName);
            page.getParams().put("customer_mobile", customerPhone);
            page.getParams().put("startTime", startTime);
            page.getParams().put("stopTime", stopTime);
            page.getParams().put("payType", payType);
            if ("2".equals(currUser.getRoleId() + "")) {
                page.getParams().put("parkingId", parkingId);
            } else {
                page.getParams().put("parkingId", null);
            }
//            page = orderMainService.queryListPage(page);
            page = orderMainService.queryTempSharePage(page);
            
            PageHelper.setPageModel(request, page);
//            Dictionary dictionary = new Dictionary();
//            dictionary.setDictCode("order_type");
//            dictionary.setIsUsed(Constants.TRUE);
//            List<Dictionary> list = dictionaryService.selectList(dictionary);
//            if ("2".equals(currUser.getRoleId() + "")) {
//                for (int i = 0; i < list.size(); i++) {
//                    if ("15".equals(list.get(i).getDictValue())) {
//                        list.remove(list.get(i));
//                        break;
//                    }
//                }
//            }
            Dictionary dictionary1 = new Dictionary();
            dictionary1.setDictCode("order_state");
            dictionary1.setIsUsed(Constants.TRUE);
            List<Dictionary> list1 = dictionaryService.selectList(dictionary1);
//            map.put("orderType", list);
            map.put("orderStatus", list1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/order/order_main_tempShare", map);
    }

    /**
     * 加油卡充值列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("listCarlife.html")
    public ModelAndView listCarlife(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = super.getParamMap(request);
        
        try {
            Page<OrderMain> page = new Page<OrderMain>();
            UserInfo currUser = (UserInfo) super.getLoginUser(request);
            String parkingId = currUser.getParkingId();
            PageHelper.initPage(request, page);
            page.getParams().put("isUsed", Constants.TRUE);
            String orderType = "15"; // 加油卡充值
            String orderId = request.getParameter("orderId");
            String orderStatus = request.getParameter("orderStatus");
            String customerName = request.getParameter("customerName");
            String customerPhone = request.getParameter("customerPhone");
            String startTime = request.getParameter("form_beginTime");
            String stopTime = request.getParameter("form_endTime");
            String payType = request.getParameter("payType");
            page.getParams().put("orderType", orderType);
            if (request.getParameterMap().containsKey("orderStatus")){
                page.getParams().put("orderStatus", orderStatus);
            }else {
                page.getParams().put("orderStatus","11");
            }
            page.getParams().put("customer_nickname", customerName);
            page.getParams().put("customer_mobile", customerPhone);
            page.getParams().put("startTime", startTime);
            page.getParams().put("stopTime", stopTime);
            page.getParams().put("payType", payType);
            page.getParams().put("orderId", orderId);
            if ("2".equals(currUser.getRoleId() + "")) {
                page.getParams().put("parkingId", parkingId);
            } else {
                page.getParams().put("parkingId", null);
            }
            page = orderMainService.queryListPage(page);
            PageHelper.setPageModel(request, page);
//            Dictionary dictionary = new Dictionary();
//            dictionary.setDictCode("order_type");
//            dictionary.setIsUsed(Constants.TRUE);
//            List<Dictionary> list = dictionaryService.selectList(dictionary);
//            if ("2".equals(currUser.getRoleId() + "")) {
//                for (int i = 0; i < list.size(); i++) {
//                    if ("15".equals(list.get(i).getDictValue())) {
//                        list.remove(list.get(i));
//                        break;
//                    }
//                }
//            }
            Dictionary dictionary1 = new Dictionary();
            dictionary1.setDictCode("order_state");
            dictionary1.setIsUsed(Constants.TRUE);
            List<Dictionary> list1 = dictionaryService.selectList(dictionary1);
//            map.put("orderType", list);
            map.put("orderStatus", list1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/order/order_main_carlife", map);
    }

    /*导出excel文档*/
    @RequestMapping("excelExport.html")
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            String orderId = request.getParameter("orderId");
            String orderType = request.getParameter("orderType");
            String orderStatus = request.getParameter("orderStatus");
            String customerName = request.getParameter("customerName");
            String customerPhone = request.getParameter("customerPhone");
            String startTime = request.getParameter("form_beginTime");
            String stopTime = request.getParameter("form_endTime");
            String payType = request.getParameter("payType");
            String parkingName = request.getParameter("parkingName");
            OrderMain orderMain = new OrderMain();
            if (orderId != null && !orderId.equals("")) {
                orderMain.setOrderId(orderId);
            }
            if (orderType != null && !orderType.equals("")) {
                orderMain.setOrderType(orderType);
            }
            if (orderStatus != null && !orderStatus.equals("")) {
                orderMain.setOrderStatus(orderStatus);
            }
            if (customerName != null && !customerName.equals("")) {
                orderMain.setCustomer_nickname(customerName);
            }
            if (customerPhone != null && !customerPhone.equals("")) {
                orderMain.setCustomer_mobile(customerPhone);
            }
            if (startTime != null && !startTime.equals("")) {
                orderMain.setStartTime(startTime);
            }
            if (stopTime != null && !stopTime.equals("")) {
                orderMain.setStopTime(stopTime);
            }
            if (payType != null && !payType.equals("")) {
                orderMain.setPayType(payType);
            }
            if (parkingName != null && !parkingName.equals("")) {
                orderMain.setParkingName(parkingName);
            }
            orderMain.setIsUsed("1");
            UserInfo currUser = (UserInfo) super.getLoginUser(request);
            String parkingId = currUser.getParkingId();
            if ("2".equals(currUser.getRoleId() + "")) {
                orderMain.setParkingId(parkingId);
            }
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<OrderMain> orderList = orderMainService.selectListExcel(orderMain);
            if (orderList != null) {
                ExportOrderExcel.exportOrderExcel("新建", new String[]{"订单ID", "订单类型", "订单状态", "发票状态", "客户名称", "客户手机", "应付金额", "优惠金额", "实付金额", "支付类型", "支付时间", "支付交易号"}, orderList, response);
            }
            Map<String, String> map = new HashMap<String, String>();
            map.put("result", "success");
            try {
                response.getWriter().print(JSONArray.fromObject(map).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    /*导出excel文档*/
    @RequestMapping("excelTempExport.html")
    public void excelTempExport(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            String orderId = request.getParameter("orderId");
            String orderType = request.getParameter("orderType");
            String orderStatus = request.getParameter("orderStatus");
            String customerName = request.getParameter("customerName");
            String customerPhone = request.getParameter("customerPhone");
            String startTime = request.getParameter("form_beginTime");
            String stopTime = request.getParameter("form_endTime");
            String payType = request.getParameter("payType");
            String parkingName = request.getParameter("parkingName");
            String carNumber = request.getParameter("carNumber");
            OrderMain orderMain = new OrderMain();
            if (orderId != null && !orderId.equals("")) {
                orderMain.setOrderId(orderId);
            }
            if (orderType != null && !orderType.equals("")) {
                orderMain.setOrderType(orderType);
            }
            if (orderStatus != null && !orderStatus.equals("")) {
                orderMain.setOrderStatus(orderStatus);
            }
            if (customerName != null && !customerName.equals("")) {
                orderMain.setCustomer_nickname(customerName);
            }
            if (customerPhone != null && !customerPhone.equals("")) {
                orderMain.setCustomer_mobile(customerPhone);
            }
            if (startTime != null && !startTime.equals("")) {
                orderMain.setStartTime(startTime);
            }
            if (stopTime != null && !stopTime.equals("")) {
                orderMain.setStopTime(stopTime);
            }
            if (payType != null && !payType.equals("")) {
                orderMain.setPayType(payType);
            }
            if (parkingName != null && !parkingName.equals("")) {
                orderMain.setParkingName(parkingName);
            }
            orderMain.setCarNumber(carNumber);
            orderMain.setIsUsed("1");
            UserInfo currUser = (UserInfo) super.getLoginUser(request);
            String parkingId = currUser.getParkingId();
            if ("2".equals(currUser.getRoleId() + "")) {
                orderMain.setParkingId(parkingId);
            }
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<OrderMain> orderList =null;
            List<OrderMain> orderList1 =null;//共享临停
            if(orderType.equals("11")){//
                orderList=orderMainService.queryTempExcel(orderMain);
            }else if(orderType.equals("10")){//共享
//                if(parkingCode !=null && !parkingCode.equals("")){
//                    orderMain.setParkingCode(parkingCode);
//                }
                orderList1=orderMainService.queryTempShareExcel(orderMain);
            }
            if (orderList != null) {
                ExportOrderExcel.exportOrderExcel("新建", new String[]{"订单ID", "订单类型", "订单状态", "发票状态", "客户名称", "客户手机", "应付金额", "优惠金额", "实付金额", "支付类型", "支付时间", "支付交易号","车场名称","车牌号"}, orderList, response);
            }
            if (orderType.equals("10") && orderList1!=null){
                final List<OrderMain> list = orderList1;
                ExportOrderExcel.exportExcel(new ExportOrderExcel.ExcelObject() {
                    @Override
                    public void dataSource(WritableSheet sheet, WritableCellFormat wcf_center, WritableCellFormat wcf_left) throws Exception {
                        sheet.addCell(new Label(0, 0, "订单ID", wcf_center));
                        sheet.addCell(new Label(1, 0, "订单类型", wcf_center));
                        sheet.addCell(new Label(2, 0, "订单状态", wcf_center));
                        sheet.addCell(new Label(3, 0, "发票状态", wcf_center));
                        sheet.addCell(new Label(4, 0, "客户名称", wcf_center));
                        sheet.addCell(new Label(5, 0, "客户手机", wcf_center));
                        sheet.addCell(new Label(6, 0, "应付金额", wcf_center));
                        sheet.addCell(new Label(7, 0, "优惠金额", wcf_center));
                        sheet.addCell(new Label(8, 0, "实付金额", wcf_center));
                        sheet.addCell(new Label(9, 0, "支付类型", wcf_center));
                        sheet.addCell(new Label(10, 0, "支付时间", wcf_center));
                        sheet.addCell(new Label(11, 0, "支付交易号", wcf_center));
                        sheet.addCell(new Label(12, 0, "车场名称", wcf_center));
                        sheet.addCell(new Label(13, 0, "停车码", wcf_center));
                        sheet.addCell(new Label(14, 0, "预约凭证状态", wcf_center));
                        sheet.addCell(new Label(15, 0, "车牌号", wcf_center));
                        for (int i = 0; i < list.size(); i++) {
                            OrderMain ome = list.get(i);
                            ome.setOrderStatus(OrderConstants.getOrderStatusName(ome.getOrderStatus()));
                            if("0".equals(ome.getInvoiceStatus()))
                                ome.setInvoiceStatus("未开具");
                            else if ("1".equals(ome.getInvoiceStatus()))
                                ome.setInvoiceStatus("已开具");
                            if ("00".equals(ome.getPayType()))
                                ome.setPayType("支付宝");
                            else if ("01".equals(ome.getPayType()))
                                ome.setPayType("微信");
                            else if ("02".equals(ome.getPayType()))
                                ome.setPayType("银联");
                            else if ("05".equals(ome.getPayType()))
                                ome.setPayType("钱包支付");
                            if ("0".equals(ome.getVoucherStatus()))
                                ome.setVoucherStatus("未使用");
                            else if ("1".equals(ome.getVoucherStatus()))
                                ome.setVoucherStatus("已使用");
                            else if ("2".equals(ome.getVoucherStatus()))
                                ome.setVoucherStatus("已失效");
                            sheet.addCell(new Label(0, i + 1, ome.getOrderId().toString(), wcf_left));
                            sheet.addCell(new Label(1, i+1,"优惠停车",wcf_left));
                            sheet.addCell(new Label(2, i + 1, ome.getOrderStatus().toString(), wcf_left));
                            sheet.addCell(new Label(3, i + 1, ome.getInvoiceStatus().toString(), wcf_left));
                            sheet.addCell(new Label(4, i + 1, ome.getCustomer_nickname() == null ? "" : ome.getCustomer_nickname(), wcf_left));
                            sheet.addCell(new Label(5, i + 1, ome.getCustomer_mobile() == null ? "" : ome.getCustomer_mobile(), wcf_left));
                            sheet.addCell(new Number(6, i + 1, ome.getAmountPayable()==null?0:(ome.getAmountPayable() / 100.00), wcf_left));
                            sheet.addCell(new Number(7, i + 1, ome.getAmountDiscount()==null?0: (ome.getAmountDiscount()/ 100.00), wcf_left));
                            sheet.addCell(new Number(8, i + 1, ome.getAmountPaid()==null?0:(ome.getAmountPaid() / 100.00), wcf_left));
                            sheet.addCell(new Label(9, i + 1, ome.getPayType()==null ?"":ome.getPayType(), wcf_left));
                            sheet.addCell(new Label(10, i + 1, ome.getPayTime() == null ? "" : DateUtil.date2str(ome.getPayTime(), DateUtil.DATETIME_FORMAT), wcf_left));
                            sheet.addCell(new Label(11, i + 1, ome.getTradeNo() == null ? "" : ome.getTradeNo(), wcf_left));
                            sheet.addCell(new Label(12, i + 1, ome.getParkingName() == null ? "" : ome.getParkingName(), wcf_left));
                            sheet.addCell(new Label(13, i + 1, ome.getParkingCode() == null ? "" : ome.getParkingCode(), wcf_left));
                            sheet.addCell(new Label(14, i + 1, ome.getVoucherStatus() == null ? "" : ome.getVoucherStatus(), wcf_left));
                            sheet.addCell(new Label(15, i + 1, ome.getCarNumber() == null ? "" : ome.getCarNumber(), wcf_left));
                        }
                    }
                }, response);
            }
            //共享临停
//            if(orderList1 !=null){
//                ExportOrderExcel.exportOrderExcel1("新建", new String[]{"订单ID", "订单类型", "订单状态", "发票状态", "客户名称", "客户手机", "应付金额", "优惠金额", "实付金额", "支付类型", "支付时间", "支付交易号","车场名称","停车码","凭证状态"}, orderList1, response);
//            }
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("result", "success");
//            try {
//                response.getWriter().print(JSONArray.fromObject(map).toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    //详情
    @RequestMapping("view.html")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = super.getParamMap(request);
        String id = request.getParameter("id");
        String orderType = request.getParameter("orderType");
        if (orderType.equals("10")) {//共享临停
            OrderTemporaryShare orderTemporaryShare = orderTemporaryShareService.queryByOrderId(id);
            StringBuilder stringBuilder = new StringBuilder();
            if (orderTemporaryShare.getStartEndTime()!=null){
                Map object = null;
                try {
                    object = (Map) JsonMapper.fromJson(orderTemporaryShare.getStartEndTime(), Map.class);
                    String mondayBeginTime = (String)object.get("mondayBeginTime");
                    String mondayEndTime = (String)object.get("mondayEndTime");
                    String tuesdayBeginTime = (String)object.get("tuesdayBeginTime");
                    String tuesdayEndTime = (String)object.get("tuesdayEndTime");
                    String wednesdayBeginTime = (String)object.get("wednesdayBeginTime");
                    String wednesdayEndTime = (String)object.get("wednesdayEndTime");
                    String thursdayBeginTime = (String)object.get("thursdayBeginTime");
                    String thursdayEndTime = (String)object.get("thursdayEndTime");
                    String fridayBeginTime = (String)object.get("fridayBeginTime");
                    String fridayEndTime = (String)object.get("fridayEndTime");
                    String saturdayBeginTime = (String)object.get("saturdayBeginTime");
                    String saturdayEndTime = (String)object.get("saturdayEndTime");
                    String sundayBeginTime = (String)object.get("sundayBeginTime");
                    String sundayEndTime = (String)object.get("sundayEndTime");
                    String packageDay =  orderTemporaryShare.getPackageDay();
                    if (packageDay.contains("周一")){
                        stringBuilder.append("周一 "+mondayBeginTime+"-"+mondayEndTime+",");
                    }
                    if (packageDay.contains("周二")){
                        stringBuilder.append("周二 "+tuesdayBeginTime+"-"+tuesdayEndTime+",");
                    }
                    if (packageDay.contains("周三")){
                        stringBuilder.append("周三 "+wednesdayBeginTime+"-"+wednesdayEndTime+",");
                    }
                    if (packageDay.contains("周四")){
                        stringBuilder.append("周四 "+thursdayBeginTime+"-"+thursdayEndTime+",");
                    }
                    if (packageDay.contains("周五")){
                        stringBuilder.append("周五 "+fridayBeginTime+"-"+fridayEndTime+",");
                    }
                    if (packageDay.contains("周六")){
                        stringBuilder.append("周六 "+saturdayBeginTime+"-"+saturdayEndTime+",");
                    }
                    if (packageDay.contains("周日")){
                        stringBuilder.append("周日 "+sundayBeginTime+"-"+sundayEndTime+",");
                    }
                    if (stringBuilder!=null){
                        String sb = stringBuilder.toString();
                        sb = sb.substring(0,sb.length()-1);
                        orderTemporaryShare.setDateDetail(sb);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            map.put("orderTemporaryShare", orderTemporaryShare);
            if (orderTemporaryShare != null) {
                Parking parking = parkingService.queryById(orderTemporaryShare.getParkingId());
                map.put("parking", parking);
            }
            return new ModelAndView("products/order/order_temporary_share_view_v2", map);
        } else if (orderType.equals("11")) {//临停缴费
            OrderTemporary orderTemporary = orderTemporaryService.queryByOrderId(id);
            map.put("orderTemporary", orderTemporary);
            if (orderTemporary != null) {
                Parking parking = parkingService.queryById(orderTemporary.getParkingId());
                map.put("parking", parking);
            }
            return new ModelAndView("products/order/order_temporary_view_v2", map);
        } else if (orderType.equals("12")) {//代泊
            OrderPark orderPark = orderParkService.queryByOrderId(id);
            map.put("orderPark", orderPark);
            if (orderPark != null) {
                Parking parking =parkingService.queryById(orderPark.getParkingId());
                map.put("parking", parking);
            }
            return new ModelAndView("products/order/order_park_view", map);
        } else if (orderType.equals("13")) {//月租
            OrderMonthly orderMonthly = orderMonthlyService.queryByOrderId(id);
            map.put("orderMonthly", orderMonthly);
            //查询发票信息
            Invoice invoiceParam = new Invoice();
            invoiceParam.setOrderId(orderMonthly.getOrderId());
            List<Invoice> inList = invoiceService.selectList(invoiceParam);
            if(null!=inList&&inList.size()>0){
            	map.put("invoice", inList.get(0));
            }
            if (orderMonthly != null) {
                Parking parking = parkingService.queryById(orderMonthly.getParkingId());
                map.put("parking", parking);
            }
            return new ModelAndView("products/order/order_monthly_view_v2", map);
        } else if (orderType.equals("14")) {//产权
            OrderEquity orderEquity = orderEquityService.queryByOrderId(id);
            map.put("orderEquity", orderEquity);
            //查询发票信息
            Invoice invoiceParam = new Invoice();
            invoiceParam.setOrderId(orderEquity.getOrderId());
            List<Invoice> inList = invoiceService.selectList(invoiceParam);
            if(null!=inList&&inList.size()>0){
            	map.put("invoice", inList.get(0));
            }
            if (orderEquity != null) {
                Parking parking = parkingService.queryById(orderEquity.getParkingId());
                map.put("parking", parking);
            }
            return new ModelAndView("products/order/order_equity_view_v2", map);
        }
        if (orderType.equals("16")) {//加油卡
            OrderRefuel orderRefuel = orderRefuelService.queryByOrderId(id);
            orderRefuel.setTopupPrice(orderRefuel.getTopupPrice() / 100);
            orderRefuel.setOrderCash(orderRefuel.getOrderCash() / 100);
            map.put("orderRefuel", orderRefuel);
            return new ModelAndView("products/order/order_refuel_view", map);
        }
//        if (orderType.equals("17")) {//洗车
//        	 OrderCarwash orderCarwash = orderCarwashService.queryByOrderId(id);
//             map.put("orderCarwash", orderCarwash);
//             if (orderCarwash != null) {
//                 Parking parking = parkingService.queryById(orderCarwash.getParkingId());
//                 map.put("parking", parking);
//             }
//
//        }
        return new ModelAndView("products/order/order_monthly_view_v2", map);

    }

    // 跳转添加
    @RequestMapping("add.html")
    public ModelAndView addOrder(HttpServletRequest request,
                                 HttpServletResponse response) {
        Map<String, Object> map = super.getParamMap(request);
         UserInfo user = (UserInfo) getLoginUser(request);
       /* if(user.getParkingId()!=null&&!user.getParkingId().equals("")){
        	Parking parking=parkingService.queryById(user.getParkingId());
        	map.put("parkingName", parking.getParkingName());
        }*/
        Dictionary dictionary = new Dictionary();
        dictionary.setDictCode("order_state");
        dictionary.setIsUsed(Constants.TRUE);
        List<Dictionary> list = dictionaryService.selectList(dictionary);
        map.put("orderStatus", list);
        return new ModelAndView("products/order/order_add", map);
    }

    // 取得单价
    @RequestMapping("getPrice.html")
    public void getPrice(HttpServletRequest request, HttpServletResponse response) {
        try {
            String carNumber = request.getParameter("carNumber");
            String parkingId = request.getParameter("parkingId");
            String orderType = request.getParameter("orderType");
            long price = 0L;
            if (orderType.equals("13")) {
                price = orderMainService.getMonPrice2(carNumber, parkingId);
                response.getWriter().print(JacksonUtil.toJson(price));
            } else if (orderType.equals("14")) {
                price = orderMainService.getEquiPrice2(carNumber, parkingId);
                response.getWriter().print(JacksonUtil.toJson(price));
            }
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    //	// 取得月数
//		@RequestMapping("getMonthNum.html")
//		public void getMonthNum(HttpServletRequest request, HttpServletResponse response) {
//			try {
//			Map<String, Object> map = super.getParamMap(request);
//	       String beginDate=request.getParameter("beginDate");
//	       String monthNum=request.getParameter("monthNum");
//	       if(beginDate!=null&&!beginDate.equals("")){
//	       int bd=Integer.parseInt(beginDate.substring(5)); //截取月份
//			int mn=Integer.parseInt(monthNum);//缴费月数
//			String end=beginDate.substring(0, 5);//如果不超过当前年 这是年份 后有-
//			String year=beginDate.substring(0, 4);//年
//			int yue=bd+mn;//结束时间的月份 未计算
//			int year2=Integer.parseInt(year)+1;//超过当前年 则加一年
//			String endDate="";
//			if(yue<=12){ 
//				if(yue<=10&&yue>1){
//					endDate=end+"0"+((yue-1)!=0?(yue-1):12);
//				}else if(yue==1){
//					endDate=end+""+12;
//				}else {
//					endDate=end+""+(yue-1);
//				}
//			}else {
//				if(yue==13){
//					endDate=year+"-"+12;
//				}else {
//					if((yue-12)<=10){
//						endDate=year2+"-0"+(yue-13);
//					}else {
//						endDate=year2+"-"+(yue-13);
//					}
//				}
//			}
//			map.put("endDate", endDate);
//				response.getWriter().print(JacksonUtil.toJson(map));
//	       }
//			} catch (IOException e) {
//				logger.error("",e);
//			}
//		}
    // 取得月数
    @RequestMapping("getMonthNum.html")
    public void getMonthNum(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> map = super.getParamMap(request);
        String beginDate = request.getParameter("beginDate");
        String monthNum = request.getParameter("monthNum");
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");
            Date beginTime = fmt.parse(beginDate);
            String endTime = DateUtil.getMonthStartAndEndDate(DateUtil.getPreOrNextMonth(beginTime, Integer.parseInt(monthNum) - 1))[1];
            map.put("endDate", endTime.substring(0, 7));
            response.getWriter().print(JacksonUtil.toJson(map));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("save.html")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response
                             //,@ModelAttribute("orderMain")OrderMain orderMain
    ) {
        try {
            String orderType = request.getParameter("orderType");
            String monthNum = request.getParameter("monthNum");
            String carNumber = request.getParameter("carNumber");
            String payType = request.getParameter("payType");
            String payTime = request.getParameter("payTime");
            String price = request.getParameter("price");
            String amountDiscount = request.getParameter("amountDiscount");
            String invoiceStatus = request.getParameter("invoiceStatus");
            String beginTime = request.getParameter("beginTime") + "-01";
            String endTime = request.getParameter("endTime");
            String orderStatus = request.getParameter("orderStatus");
            String parkingId = request.getParameter("parkingId");
            int youhui =0;
            if(amountDiscount!=null&&!amountDiscount.equals("")){
            	   youhui = Integer.parseInt(amountDiscount) * 100;
            }
            int yingfu = Integer.parseInt(monthNum) * Integer.parseInt(price) * 100;
            int shifu = yingfu - youhui;
          
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date zhyt = sdf.parse(endTime);//当前月最后一天
            String[] zh = DateUtil.getMonthStartAndEndDate(zhyt);
      
            
             Parking parking =parkingService.queryById(parkingId);
            OrderMain orderMain = new OrderMain();
            Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_ORDER_MAIN);
            orderMain.setOrderId((null==parking.getParkingIdentifier()?"":parking.getParkingIdentifier())+orderType+sequence.getId());
            orderMain.setOrderType(orderType);
            orderMain.setAmountDiscount(youhui);
            if (youhui <= yingfu) {
                orderMain.setAmountPaid(shifu);
            } else {
                orderMain.setAmountPaid(0);
            }
            orderMain.setAmountPayable(yingfu);
            //获取customerId
            if(orderType.equals("13")){
                Monthlyparkinginfo monthlyparkinginfo=new Monthlyparkinginfo();
                monthlyparkinginfo.setVillageId(parkingId);
                monthlyparkinginfo.setIsUsed(Constants.TRUE);
                monthlyparkinginfo.setCarNumber(carNumber);
                List<Monthlyparkinginfo> listmon=monthlyparkinginfoService.selectList(monthlyparkinginfo);
                if(listmon!=null&&listmon.size()>0){
                Customer customer= customerService.queryByMobileV2(listmon.get(0).getPhone());
                    if(customer!=null&&!"".equals(customer)){
                        orderMain.setCustomerId(customer.getCustomerId());//用户Id
                    }else {
                        orderMain.setCustomerId("");//用户Id
                    }
                }
            }else {
                Propertyparkinginfo propertyparkinginfo=new Propertyparkinginfo();
                propertyparkinginfo.setVillageId(parkingId);
                propertyparkinginfo.setIsUsed(Constants.TRUE);
                propertyparkinginfo.setCarNumber(carNumber);
                List<Propertyparkinginfo> listequ=propertyparkinginfoService.selectList(propertyparkinginfo);
                if(listequ!=null&&listequ.size()>0){
                    Customer customer= customerService.queryByMobileV2(listequ.get(0).getPhone());
                    if(customer!=null&&!"".equals(customer)){
                        orderMain.setCustomerId(customer.getCustomerId());//用户Id
                    }else {
                        orderMain.setCustomerId("");//用户Id
                    }
                }
            }

//			orderMain.setBeginTime(DateUtil.str2date(beginTime, DateUtil.DATE_FORMAT));
//			orderMain.setEndTime(DateUtil.str2date(endTime, DateUtil.DATE_FORMAT));
            orderMain.setInvoiceStatus(invoiceStatus);
            orderMain.setPayType(payType);
            if (orderStatus.equals("11") && (orderStatus.equals("") || orderStatus == null)) {
                orderMain.setPayTime(new Date());
            } else if (orderStatus.equals("11") && orderStatus != null && !orderStatus.equals("")) {
                orderMain.setPayTime(DateUtil.str2date(payTime, DateUtil.DATETIME_FORMAT));
            } else {
                orderMain.setPayTime(null);
            }
            UserInfo user = (UserInfo) getLoginUser(request);
            orderMain.setOrderStatus(orderStatus);
            orderMain.setParkingId(parkingId);
            orderMain.setCreateor(user.getUserNum());
            orderMain.setCreateDate(new Date());
            orderMain.setIsUsed(Constants.TRUE);
            orderMainService.add(orderMain);
            if (orderType.equals("13")) {
                Monthlyparkinginfo monthlyparkinginfo = new Monthlyparkinginfo();
                monthlyparkinginfo.setCarNumber(carNumber);
                monthlyparkinginfo.setVillageId(parkingId);
                List<Monthlyparkinginfo> list = monthlyparkinginfoService.selectList(monthlyparkinginfo);
                if (list != null && list.size() > 0) {
                    Monthlyparkinginfo monthlyparkinginfo2 = list.get(0);
                    boolean flag = true;
                    if (monthlyparkinginfo2.getMax_date() != null && !monthlyparkinginfo2.getMax_date().equals("")) {
                        Calendar c1 = Calendar.getInstance();
                        Calendar c2 = Calendar.getInstance();
                        c1.setTime(DateUtil.str2date(zh[1], DateUtil.DATE_FORMAT));
                        c2.setTime(monthlyparkinginfo2.getMax_date());
                        int result = c1.compareTo(c2);
                        if (result >= 0) {//end 大于MAX
                            //不添加记录
                            request.setAttribute("info", "付款超出范围！");
                            request.setAttribute("add", "info");
                            flag = false;
                        }
                    }
                    if (flag) {
                        OrderMonthly orderMonthly = new OrderMonthly();
                        orderMonthly.setCarNumber(carNumber);
                        orderMonthly.setBeginDate(DateUtil.str2date(beginTime, DateUtil.DATE_FORMAT));
                        orderMonthly.setEndDate(DateUtil.str2date(zh[1], DateUtil.DATE_FORMAT));
                        orderMonthly.setMonthNum(Integer.parseInt(monthNum));
                        orderMonthly.setOrderId(orderMain.getOrderId());
                        orderMonthly.setPrice(price != null ? Integer.parseInt(price)*100 : 0);
                        orderMonthly.setParkingId(parkingId);

                        orderMonthly.setIsUsed(Constants.TRUE);
                        orderMonthly.setCreateDate(new Date());
                        orderMonthly.setCreateor(user.getUserNum());
                        orderMonthlyService.add(orderMonthly);
                        request.setAttribute("info", "添加成功");
                    }
                } else {
                    request.setAttribute("info", "找不到车辆！");
                    request.setAttribute("add", "info");
                }
            } else if (orderType.equals("14")) {
                Propertyparkinginfo propertyparkinginfo = new Propertyparkinginfo();
                propertyparkinginfo.setCarNumber(carNumber);
                propertyparkinginfo.setVillageId(parkingId);
                List<Propertyparkinginfo> list = propertyparkinginfoService.selectList(propertyparkinginfo);
                if (list != null && list.size() > 0) {
                    boolean flag = true;
                    Propertyparkinginfo propertyparkinginfo2 = list.get(0);
                    if (propertyparkinginfo2.getMax_date() != null && !propertyparkinginfo2.getMax_date().equals("")) {
                        Calendar c1 = Calendar.getInstance();
                        Calendar c2 = Calendar.getInstance();
                        c1.setTime(DateUtil.str2date(zh[1], DateUtil.DATE_FORMAT));
                        c2.setTime(propertyparkinginfo2.getMax_date());
                        int result = c1.compareTo(c2);
                        if (result >= 0) {//end 大于MAX
                            //不添加记录
                            request.setAttribute("info", "付款超出范围！");
                            request.setAttribute("add", "info");
                            flag = false;
                        }
                    }
                    if (flag) {
                        OrderEquity orderEquity = new OrderEquity();
                        orderEquity.setOrderId(orderMain.getOrderId());
                        orderEquity.setParkingId(parkingId);
                        orderEquity.setCarNumber(carNumber);
                        orderEquity.setPrice(Integer.parseInt(price)*100);
                        orderEquity.setBeginDate(DateUtil.str2date(beginTime, DateUtil.DATE_FORMAT));
                        orderEquity.setEndDate(DateUtil.str2date(zh[1], DateUtil.DATE_FORMAT));
                        orderEquity.setMonthNum(Integer.parseInt(monthNum));
                        orderEquity.setParkingNo(null);
                        orderEquity.setIsUsed(Constants.TRUE);
                        orderEquity.setCreateDate(new Date());
                        orderEquity.setCreateor(user.getUserNum());
                        orderEquityService.add(orderEquity);
                        request.setAttribute("info", "添加成功");
                    }
                } else {
                    request.setAttribute("info", "找不到车辆！");
                    request.setAttribute("add", "info");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加订单信息出现异常", e);
        }


        return new ModelAndView("products/order/save");
    }

    /**
     * 分页查询，带车牌号
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("listWithCarNumber.html")
    public ModelAndView listWithCarNumber(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = super.getParamMap(request);
        try {
            Page<OrderMain> page = new Page<OrderMain>();
            UserInfo currUser = (UserInfo) super.getLoginUser(request);
            currUser = userInfoService.queryById(currUser.getUserId());
            String parkingId = currUser.getParkingId();
            PageHelper.initPage(request, page);
            page.getParams().put("isUsed", Constants.TRUE);
            String carNumber = request.getParameter("carNumber");
            String orderType = request.getParameter("orderType");
            String orderId= request.getParameter("orderId");
            String orderStatus = request.getParameter("orderStatus");
            String customerName = request.getParameter("customerName");
            String customerPhone = request.getParameter("customerPhone");
            String startTime = request.getParameter("form_beginTime");
            String stopTime = request.getParameter("form_endTime");
            String payType = request.getParameter("payType");
            String parkingName = request.getParameter("parkingName");
            page.getParams().put("parkingName", parkingName);
            page.getParams().put("carNumber", carNumber);
            page.getParams().put("orderId", orderId);
            page.getParams().put("orderType", orderType);
            if (request.getParameterMap().containsKey("orderStatus")){
                page.getParams().put("orderStatus", orderStatus);
            }else {
                page.getParams().put("orderStatus","11");
            }
            page.getParams().put("customer_nickname", customerName);
            page.getParams().put("customer_mobile", customerPhone);
            page.getParams().put("startTime", startTime);
            page.getParams().put("stopTime", stopTime);
            page.getParams().put("payType", payType);
           // if ("2".equals(currUser.getRoleId() + "")) {
        	if(StringUtils.isEmpty(parkingId)){
        		page.getParams().put("parkingId", null);
        	}else{
        		String [] pkStr = parkingId.split("\\,");
        		String parkingStr = "";
    			for(int i=0;i<pkStr.length;i++){
    				parkingStr +="'";
    				parkingStr+=pkStr[i];
    				parkingStr+="'";
    				parkingStr+=",";
    			}
    			parkingStr = parkingStr.substring(0,parkingStr.length()-1);
        		page.getParams().put("parkingId", parkingStr);
        	}
            /*} else {
                page.getParams().put("parkingId", null);
            }*/
            page = orderMainService.queryListPageWithCarNumber(page);
            PageHelper.setPageModel(request, page);
//            Dictionary dictionary = new Dictionary();
//            dictionary.setDictCode("order_type");
//            dictionary.setIsUsed(Constants.TRUE);
//            List<Dictionary> list = dictionaryService.selectList(dictionary);
//            if ("2".equals(currUser.getRoleId() + "")) {
//                for (int i = 0; i < list.size(); i++) {
//                    if ("15".equals(list.get(i).getDictValue())) {
//                        list.remove(list.get(i));
//                        break;
//                    }
//                }
//            }
            Dictionary dictionary1 = new Dictionary();
            dictionary1.setDictCode("order_state");
            dictionary1.setIsUsed(Constants.TRUE);
            List<Dictionary> list1 = dictionaryService.selectList(dictionary1);
//            map.put("orderType", list);
            map.put("orderStatus", list1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/order/order_main_list_with_car_number", map);
    }

    /**
     * 导出excel文档，带车牌号
     */
    @RequestMapping("excelExportWithCarNumber.html")
    public void excelExportWithCarNumber(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            String carNumber = request.getParameter("carNumber");
            String orderType = request.getParameter("orderType");
            String orderStatus = request.getParameter("orderStatus");
            String customerName = request.getParameter("customerName");
            String orderId = request.getParameter("orderId");
            String customerPhone = request.getParameter("customerPhone");
            String startTime = request.getParameter("form_beginTime");
            String stopTime = request.getParameter("form_endTime");
            String payType = request.getParameter("payType");
            String parkingName = request.getParameter("parkingName");
            OrderMain orderMain = new OrderMain();
            if (parkingName != null && !parkingName.equals("")) {
                orderMain.setParkingName(parkingName);
            }
            if (carNumber != null && !carNumber.equals("")) {
                orderMain.setCarNumber(carNumber);
            }
            if (orderType != null && !orderType.equals("")) {
                orderMain.setOrderType(orderType);
            }
            if (orderStatus != null && !orderStatus.equals("")) {
                orderMain.setOrderStatus(orderStatus);
            }
            if (customerName != null && !customerName.equals("")) {
                orderMain.setCustomer_nickname(customerName);
            }
            if (customerPhone != null && !customerPhone.equals("")) {
                orderMain.setCustomer_mobile(customerPhone);
            }
            if (startTime != null && !startTime.equals("")) {
                orderMain.setStartTime(startTime);
            }
            if (stopTime != null && !stopTime.equals("")) {
                orderMain.setStopTime(stopTime);
            }
            if (payType != null && !payType.equals("")) {
                orderMain.setPayType(payType);
            }
            if (orderId != null && !orderId.equals("")) {
                orderMain.setOrderId(orderId);
            }
            orderMain.setIsUsed("1");
            UserInfo currUser = (UserInfo) super.getLoginUser(request);
            String parkingId = currUser.getParkingId();
            if ("2".equals(currUser.getRoleId() + "")) {
                orderMain.setParkingId(parkingId);
            }
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<OrderMain> orderList = orderMainService.selectListExcelWithCarNumber(orderMain);
            if (orderList != null) {
                ExportOrderExcel.exportOrderExcel("新建", new String[]{"订单ID", "订单类型", "订单状态", "发票状态", "客户名称", "客户手机", "应付金额", "优惠金额", "实付金额", "支付类型", "支付时间", "支付交易号",  "车场名称","车牌号", "开始时间", "结束时间"}, orderList, response);
            }
            Map<String, String> map = new HashMap<String, String>();
            map.put("result", "success");
            try {
                response.getWriter().print(JSONArray.fromObject(map).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    // 取得月数
    @RequestMapping("whiteSyn.html")
    public void whiteSyn(HttpServletRequest request, HttpServletResponse response) {
    	/**
		 * 任务查询比较耗时,发送邮件比较耗时,远程访问比较耗时,都可能导致触发器线程的阻塞
		 * 解决这个问题的方法是，另启一个工作线程来执行定时任务
		 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					orderMainService.synWhiteList(null);
				} catch (Exception e) {
					logger.info("in 白名单接口同步自动调度出错:" + e.getMessage());
				}
			}
		}).start();
	
        try {
            response.getWriter().print("同步成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  //查询洗车订单
    @RequestMapping("carwashList.html")
    public ModelAndView carwashList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = super.getParamMap(request);
        try {
            Page<OrderMain> page = new Page<OrderMain>();
            UserInfo currUser = (UserInfo) super.getLoginUser(request);
            currUser = userInfoService.queryById(currUser.getUserId());
            String parkingId = currUser.getParkingId();
            PageHelper.initPage(request, page);
            page.getParams().put("isUsed", Constants.TRUE);
            String orderType = request.getParameter("orderType");
            String orderStatus = request.getParameter("orderStatus");
            String customerName = request.getParameter("customerName");
            String customerPhone = request.getParameter("customerPhone");
            String startTime = request.getParameter("form_beginTime");
            String stopTime = request.getParameter("form_endTime");
            String payType = request.getParameter("payType");
            String orderId = request.getParameter("orderId");
            String reserveDate = request.getParameter("reserveDate");
            String carNumber = request.getParameter("carNumber");
            page.getParams().put("carNumber", carNumber);
            page.getParams().put("orderId", orderId);
            page.getParams().put("orderType", "17");
            if (request.getParameterMap().containsKey("orderStatus")){
                page.getParams().put("orderStatus", orderStatus);
            }else {
                page.getParams().put("orderStatus","11");
            }
            page.getParams().put("customer_nickname", customerName);
            page.getParams().put("customer_mobile", customerPhone);
            page.getParams().put("startTime", startTime);
            page.getParams().put("stopTime", stopTime);
            page.getParams().put("payType", payType);
            page.getParams().put("reserveDate", reserveDate);
            
            
            /*if ("2".equals(currUser.getRoleId() + "")) {
                page.getParams().put("parkingId", parkingId);
            } else {
                page.getParams().put("parkingId", null);
            }*/
            if(StringUtils.isEmpty(parkingId)){
        		page.getParams().put("parkingId", null);
        	}else{
        		String [] pkStr = parkingId.split("\\,");
        		String parkingStr = "";
    			for(int i=0;i<pkStr.length;i++){
    				parkingStr +="'";
    				parkingStr+=pkStr[i];
    				parkingStr+="'";
    				parkingStr+=",";
    			}
				parkingStr = parkingStr.substring(0,parkingStr.length()-1);
	    		page.getParams().put("parkingId", parkingStr);
        	}
    		page = orderMainService.queryCarwashPage(page);
            PageHelper.setPageModel(request, page);
            Dictionary dictionary = new Dictionary();
            dictionary.setDictCode("order_type");
            dictionary.setIsUsed(Constants.TRUE);
            List<Dictionary> list = dictionaryService.selectList(dictionary);
            Dictionary dictionary1 = new Dictionary();
            dictionary1.setDictCode("order_state");
            dictionary1.setIsUsed(Constants.TRUE);
            List<Dictionary> list1 = dictionaryService.selectList(dictionary1);
            map.put("orderType", list);
            map.put("orderStatus", list1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/order/order_carwash_list", map);
    }
    /*导出excel文档*/
    @RequestMapping("queryCarwasExport.html")
    public void queryCarwasExport(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            String orderId = request.getParameter("orderId");//订单Id
            String orderType = request.getParameter("orderType");//订单类型
            String orderStatus = request.getParameter("orderStatus");//订单状态
            String invoiceStatus = request.getParameter("invoiceStatus");//发票状态
            String reserveDate = request.getParameter("reserveDate");//预约日期
            String parkingName = request.getParameter("parkingName");//车场名称
            String carType = request.getParameter("carType");//车类型
            String carNumber = request.getParameter("carNumber");//车牌号
            String customerNickname = request.getParameter("customer_nickname");//客户名称
            String customerMobile = request.getParameter("customer_mobile");//客户手机
            String amountPayable = request.getParameter("amountPayable");//应付金额
            String amountDiscount = request.getParameter("amountDiscount");//优惠金额
            String amountPaid = request.getParameter("amountPaid");//实惠金额
            String payType = request.getParameter("payType");//支付类型
            String payTime = request.getParameter("payTime");//支付时间
            String tradeNo = request.getParameter("tradeNo");//支付交易号
            OrderMain orderMain = new OrderMain();
            if (orderId != null && !orderId.equals("")) {
                orderMain.setOrderId(orderId);
            }
            if (orderType != null && !orderType.equals("")) {
                orderMain.setOrderType(orderType);
            }
            if (orderStatus != null && !orderStatus.equals("")) {
                orderMain.setOrderStatus(orderStatus);
            }
            if (invoiceStatus != null && !invoiceStatus.equals("")) {
                orderMain.setCustomer_nickname(invoiceStatus);
            }
            if (reserveDate != null && !reserveDate.equals("")) {
                orderMain.setCustomer_mobile(reserveDate);
            }
            if (parkingName != null && !parkingName.equals("")) {
                orderMain.setStartTime(parkingName);
            }
            if (carType != null && !carType.equals("")) {
                orderMain.setStopTime(carType);
            }
            if (carNumber != null && !carNumber.equals("")) {
                orderMain.setCarNumber(carNumber);
            }
            if (customerNickname != null && !customerNickname.equals("")) {
                orderMain.setParkingName(customerNickname);
            }
            if (customerMobile != null && !customerMobile.equals("")) {
                orderMain.setParkingName(customerMobile);
            }
            if (customerMobile != null && !customerMobile.equals("")) {
                orderMain.setParkingName(customerMobile);
            }
            if (amountPayable != null && !amountPayable.equals("")) {
                orderMain.setParkingName(amountPayable);
            }
            if (amountDiscount != null && !amountDiscount.equals("")) {
                orderMain.setParkingName(amountDiscount);
            }
            if (amountPaid != null && !amountPaid.equals("")) {
                orderMain.setParkingName(amountPaid);
            }
            
            if (payType != null && !payType.equals("")) {
                orderMain.setParkingName(payType);
            } 
            if (payTime != null && !payTime.equals("")) {
                orderMain.setParkingName(payTime);
            }
            if (tradeNo != null && !tradeNo.equals("")) {
                orderMain.setParkingName(tradeNo);
            }
            orderMain.setIsUsed(Constants.TRUE);
            UserInfo currUser = (UserInfo) super.getLoginUser(request);
            String parkingId = currUser.getParkingId();
            if ("2".equals(currUser.getRoleId() + "")) {
                orderMain.setParkingId(parkingId);
            }
            List<OrderMain> orderList =null;
            if(orderType.equals("17")){
                orderList=orderMainService.queryCarwasExport(orderMain);
            }
            if (orderList != null) {
                ExportOrderExcel.exportOrderCarwashExcel("新建", new String[]{"订单ID", "订单类型", "订单状态", "发票状态", "预约日期", "车场名称", "车类型", "车牌号", "客户名称", "客户手机", "应付金额", "优惠金额","实付金额","支付类型","支付时间","支付交易号"}, orderList, response);
            }
            Map<String, String> map = new HashMap<String, String>();
            map.put("result", "success");
            try {
                response.getWriter().print(JSONArray.fromObject(map).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    /*导出excel文档*/
    @RequestMapping("queryParkExport.html")
    public void queryParkExport(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("carNumber", request.getParameter("carNumber"));
            params.put("orderType", request.getParameter("orderType"));
            params.put("orderStatus", request.getParameter("orderStatus"));
            params.put("customerName", request.getParameter("customerName"));
            params.put("orderId", request.getParameter("orderId"));
            params.put("customerPhone", request.getParameter("customerPhone"));
            params.put("payType", request.getParameter("payType"));
            params.put("parkingName", request.getParameter("parkingName"));
            params.put("carNumber", request.getParameter("carNumber"));
            final List<OrderMainExcel> list = orderMainService.queryParkExport(params);
            if (list != null && list.size() > 0) {
                ExportOrderExcel.exportExcel(new ExportOrderExcel.ExcelObject() {
                    @Override
                    public void dataSource(WritableSheet sheet, WritableCellFormat wcf_center, WritableCellFormat wcf_left) throws Exception {
                        sheet.addCell(new Label(0, 0, "订单ID", wcf_center));
                        sheet.addCell(new Label(1, 0, "订单类型", wcf_center));
                        sheet.addCell(new Label(2, 0, "订单状态", wcf_center));
                        sheet.addCell(new Label(3, 0, "发票状态", wcf_center));
                        sheet.addCell(new Label(4, 0, "车场名称", wcf_center));
                        sheet.addCell(new Label(5, 0, "车牌号", wcf_center));
                        sheet.addCell(new Label(6, 0, "客户名称", wcf_center));
                        sheet.addCell(new Label(7, 0, "客户手机", wcf_center));
                        sheet.addCell(new Label(8, 0, "应付金额", wcf_center));
                        sheet.addCell(new Label(9, 0, "优惠金额", wcf_center));
                        sheet.addCell(new Label(10, 0, "实付金额", wcf_center));
                        sheet.addCell(new Label(11, 0, "支付类型", wcf_center));
                        sheet.addCell(new Label(12, 0, "支付时间", wcf_center));
                        sheet.addCell(new Label(13, 0, "支付交易号", wcf_center));
                        for (int i = 0; i < list.size(); i++) {
                        	OrderMainExcel ome =  list.get(i);
                            sheet.addCell(new Label(0, i + 1, ome.getOrderId().toString(), wcf_left));
                            sheet.addCell(new Label(1, i + 1, ome.getOrderType().toString(), wcf_left));
                            sheet.addCell(new Label(2, i + 1, ome.getOrderStatus().toString(), wcf_left));
                            sheet.addCell(new Label(3, i + 1, ome.getInvoiceStatus().toString(), wcf_left));
                            sheet.addCell(new Label(4, i + 1, ome.getParkingName()==null?"":ome.getParkingName().toString(), wcf_left));
                            sheet.addCell(new Label(5, i + 1, ome.getCarNumber()==null?"":ome.getCarNumber().toString(), wcf_left));
                            sheet.addCell(new Label(6, i + 1, ome.getCustomer_nickname()==null?"":ome.getCustomer_nickname().toString(), wcf_left));
                            sheet.addCell(new Label(7, i + 1, ome.getCustomer_mobile()==null?"":ome.getCustomer_mobile().toString(), wcf_left));
                            sheet.addCell(new Number(8, i + 1, ome.getAmountPayable() / 100.00, wcf_left));
                            sheet.addCell(new Number(9, i + 1, ome.getAmountDiscount() / 100.00, wcf_left));
                            sheet.addCell(new Number(10, i + 1, ome.getAmountPaid() / 100.00, wcf_left));
                            sheet.addCell(new Label(11, i + 1, ome.getPayType()==null?"":ome.getPayType().toString(), wcf_left));
                            sheet.addCell(new Label(12, i + 1, ome.getPayTime() == null ? "" : DateUtil.date2str(ome.getPayTime(), DateUtil.DATETIME_FORMAT), wcf_left));
                            sheet.addCell(new Label(13, i + 1, ome.getTradeNo() == null ? "" : ome.getTradeNo(), wcf_left));                            
                        }
                    }
                }, response);
            } else {
                try {
                    String js = "<script>alert('没有需要导出的内容');history.back();</script>";
                    response.getWriter().print(js);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    //查询代泊订单
    @RequestMapping("parkList.html")
    public ModelAndView parkList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = super.getParamMap(request);
        try {
            Page<OrderMain> page = new Page<OrderMain>();
            UserInfo currUser = (UserInfo) super.getLoginUser(request);
          //  String parkingId = currUser.getParkingId();
            PageHelper.initPage(request, page);
            page.getParams().put("isUsed", Constants.TRUE);
            String orderType = request.getParameter("orderType");
            String orderStatus = request.getParameter("orderStatus");
            String customerName = request.getParameter("customerName");
            String customerPhone = request.getParameter("customerPhone");
            String startTime = request.getParameter("form_beginTime");
            String stopTime = request.getParameter("form_endTime");
            String payType = request.getParameter("payType");
            String orderId = request.getParameter("orderId");
            String carNumber = request.getParameter("carNumber");
            page.getParams().put("carNumber", carNumber);
            page.getParams().put("orderId", orderId);
            page.getParams().put("orderType", orderType);
            if (request.getParameterMap().containsKey("orderStatus")){
                page.getParams().put("orderStatus", orderStatus);
            }else {
                page.getParams().put("orderStatus","5");
            }
            page.getParams().put("customer_nickname", customerName);
            page.getParams().put("customer_mobile", customerPhone);
            page.getParams().put("startTime", startTime);
            page.getParams().put("stopTime", stopTime);
            page.getParams().put("payType", payType);
          //  page.getParams().put("reserveDate", reserveDate);
//            if ("2".equals(currUser.getRoleId() + "")) {
//                page.getParams().put("parkingId", parkingId);
//            } else {
//                page.getParams().put("parkingId", null);
//            }
            page = orderMainService.queryParkPage(page);
            PageHelper.setPageModel(request, page);
            Dictionary dictionary = new Dictionary();
            dictionary.setDictCode("order_type");
            dictionary.setIsUsed(Constants.TRUE);
            List<Dictionary> list = dictionaryService.selectList(dictionary);
            Dictionary dictionary1 = new Dictionary();
            dictionary1.setDictCode("order_state");
            dictionary1.setIsUsed(Constants.TRUE);
            List<Dictionary> list1 = dictionaryService.selectList(dictionary1);
            map.put("orderType", list);
            map.put("orderStatus", list1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/order/order_main_park", map);
    }

    //项目缴费流水详情
    @RequestMapping("paymentDetail.html/{date}/{region}/{parkingId}")
    public ModelAndView queryStatisticsDetail(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String date,
            @PathVariable String region,
            @PathVariable String parkingId)
    {
        Map<String, Object> map = super.getParamMap(request);
        String cType = request.getParameter("cType");
        String onlineType = request.getParameter("onlineType");
        try {
            List<OrderMain> odList = orderMainService.getPaymentDetail(date,region,parkingId);
            List<OrderMain> rmList = new ArrayList<OrderMain>();
           //去除
            if(StringUtils.isEmpty(cType)&&!StringUtils.isEmpty(onlineType)){
                    for(OrderMain o:odList){
                        if(!onlineType.equals(o.getOnlineType())){
                            rmList.add(o);
                        }
                }
           }else if(!StringUtils.isEmpty(cType)&&StringUtils.isEmpty(onlineType)){
                for(OrderMain o:odList) {
                    if (!cType.equals(o.getOrderType())) {
                        rmList.add(o);
                    }
                }
            }else if(!StringUtils.isEmpty(cType)&&!StringUtils.isEmpty(onlineType)) {
                for (OrderMain o : odList) {
                    if (!cType.equals(o.getOrderType())||!onlineType.equals(o.getOnlineType())) {
                        rmList.add(o);
                    }
                }
            }
            odList.removeAll(rmList);
           Parking parking= parkingService.queryById(parkingId);
            map.put("odList",odList);
            map.put("cDate",date);
            map.put("region",region);
            if(parking!=null){
                map.put("parkingName",parking.getParkingName());
            }
            map.put("parkingId",parkingId);
            map.put("cType",cType);
            map.put("onlineType",onlineType);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/order/payment_detail",map);
    }

    //项目缴费流水详情
    @RequestMapping("queryPayfeelsStatistics.html")
    public ModelAndView queryPayfeelsStatistics(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Map<String, Object> map = super.getParamMap(request);
        Page page=new Page();
        page.setParams(super.getParamMap(request));

        //如果没有日期  默认为当天
        if (request.getParameterMap().containsKey("statistics_date")){
            page.getParams().put("statistics_date",DateUtil.str2date(page.getParams().get("statistics_date").toString(),DateUtil.DATE_FORMAT));
        }else {
            page.getParams().put("statistics_date",DateUtil.date2str(new Date(),DateUtil.DATE_FORMAT));
        }
        PageHelper.initPage(request, page);
        try {
         //orderMainService.queryPayfeelsStatistics(page);
            List<Map<String,Object>> list = orderMainService.queryPayfeelsStatistics(page);
            page.setResultList(list);
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/order/payment_detail2",map);
    }
    //项目缴费流水详情
    @RequestMapping("paymentDetail2.html")
    public ModelAndView queryStatisticsDetail2(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Map<String, Object> map = super.getParamMap(request);
        String cType = request.getParameter("cType");
        String onlineType = request.getParameter("onlineType");
        String date = request.getParameter("data");
        String region = request.getParameter("region");
        String parkingId = request.getParameter("parkingName");
        if("".equals(date)||date==null||"".equals(region)||region==null||"".equals(parkingId)||parkingId==null){
        }else {
            try {
                List<OrderMain> odList = orderMainService.getPaymentDetail(date, region, parkingId);
                List<OrderMain> rmList = new ArrayList<OrderMain>();
                //去除
                if (StringUtils.isEmpty(cType) && !StringUtils.isEmpty(onlineType)) {
                    for (OrderMain o : odList) {
                        if (!onlineType.equals(o.getOnlineType())) {
                            rmList.add(o);
                        }
                    }
                } else if (!StringUtils.isEmpty(cType) && StringUtils.isEmpty(onlineType)) {
                    for (OrderMain o : odList) {
                        if (!cType.equals(o.getOrderType())) {
                            rmList.add(o);
                        }
                    }
                } else if (!StringUtils.isEmpty(cType) && !StringUtils.isEmpty(onlineType)) {
                    for (OrderMain o : odList) {
                        if (!cType.equals(o.getOrderType()) || !onlineType.equals(o.getOnlineType())) {
                            rmList.add(o);
                        }
                    }
                }
                odList.removeAll(rmList);
                Parking parking= parkingService.queryById(parkingId);
                map.put("odList",odList);
                map.put("cDate",date);
                map.put("region",region);
                if(parking!=null){
                    map.put("parkingName",parking.getParkingName());
                }
                map.put("parkingId", parkingId);
                map.put("cType", cType);
                map.put("onlineType", onlineType);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("查询信息出现异常", e);
            }
        }
        return new ModelAndView("products/order/payment_detail2",map);
    }
    //查询缴费统计
    @RequestMapping("queryStatistics.html")
    public ModelAndView queryStatistics(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = super.getParamMap(request);
        try {
            Page page = new Page();
            UserInfo user=(UserInfo) super.getLoginUser(request);
            PageHelper.initPage(request, page);
            page.setParams(super.getParamMap(request));
            if(user.getParkingId()!=null&&!("").equals(user.getParkingId())){
                String[] parkingId=user.getParkingId().split(",");
                String parkingStr = "";
                for(int i=0;i<parkingId.length;i++){
                    parkingStr +="'";
                    parkingStr+=parkingId[i];
                    parkingStr+="'";
                    parkingStr+=",";
                }
                parkingStr = parkingStr.substring(0,parkingStr.length()-1);
                page.getParams().put("parkingId", parkingStr);
            }
         /*   if (request.getParameterMap().containsKey("p_beginDate")){
                page.getParams().put("beginDate",DateUtil.str2date(page.getParams().get("p_beginDate").toString(),DateUtil.DATE_FORMAT));
            }else {
                page.getParams().put("beginDate",DateUtil.date2str(new Date(),DateUtil.DATE_FORMAT));
            }*/
            List<Map<String,Object>> list = orderMainService.queryStatistics(page);
            page.setResultList(list);
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/order/order_statistics_list",map);
    }
    @RequestMapping("list2.html")
    public ModelAndView list2(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = super.getParamMap(request);
        Map<String, Object> map2 = super.getParamMap(request);
        Map<String, Object> map3 = super.getParamMap(request);
        try {
            Page page = new Page();
         /*   UserInfo currUser = (UserInfo) super.getLoginUser(request);
            String parkingId = currUser.getParkingId();*/
            //详情页进入订单页参数
            String inputDate = request.getParameter("inputDate");
            String parkingId = request.getParameter("parkingId");
            String region = request.getParameter("region");
            String onlineType = request.getParameter("onlineType");
           // String sumPaid = request.getParameter("sumPaid");
            map.put("onlineType",onlineType);
            map.put("region",region);
            map.put("inputDate",inputDate);
            map.put("parkingId",parkingId);
            PageHelper.initPage(request, page);
            if(!StringUtils.isEmpty(inputDate)){
                page.getParams().put("inputDate", inputDate);
                map2.put("inputDate", inputDate);
            }
            if(!StringUtils.isEmpty(onlineType)){
                if(onlineType.equals("1")){
                    page.getParams().put("onlineType", "00,01,02,04,05");
                    map2.put("onlineType", "00,01,02,04,05");
                }else if(onlineType.equals("2")){
                    page.getParams().put("onlineType", "03");
                    map2.put("onlineType", "03");
                }
            }
            if(!StringUtils.isEmpty(parkingId)){
                page.getParams().put("parkingId", "'"+parkingId+"'");
                map2.put("parkingId", "'"+parkingId+"'");
            }
            /*if(!StringUtils.isEmpty(region)){
                if(region.equals("1")){
                    page.getParams().put("region", "1");
                    map2.put("region", "1");
                }else  if(region.equals("第二区域")){
                    page.getParams().put("region", "2");
                    map2.put("region", "2");
                }else  if(region.equals("第三区域")){
                    page.getParams().put("region", "3");
                    map2.put("region", "3");
                }else  if(region.equals("未定义")){
                    page.getParams().put("region", "");
                    map2.put("region", "");
                }
               // page.getParams().put("region", region);
            }*/
            if(!StringUtils.isEmpty(region)){
                page.getParams().put("region", region);
                map2.put("region", region);
            }
            page.getParams().put("isUsed", Constants.TRUE);
            String orderType = request.getParameter("orderType");
            map.put("orderType",orderType);
            String orderStatus = request.getParameter("orderStatus");
            String carNumber = request.getParameter("carNumber");
            String customerName = request.getParameter("customerName");
            String customerPhone = request.getParameter("customerPhone");
            String startTime = request.getParameter("form_beginTime");
            String stopTime = request.getParameter("form_endTime");
            String payType = request.getParameter("payType");
            if(orderType!=null){
                if(orderType.equals("11")){
                    page.getParams().put("tableName", "t_order_temporary");
                    map2.put("tableName", "t_order_temporary");
                  /*  map.put("temporary",sumPaid);
                    map.put("monthly",0);
                    map.put("equity",0);*/
                }else  if(orderType.equals("13")){
                    page.getParams().put("tableName", "t_order_monthly");
                    map2.put("tableName", "t_order_monthly");
                 /*   map.put("temporary",0);
                    map.put("monthly", sumPaid);
                    map.put("equity",0);*/
                }else  if(orderType.equals("14")){
                    page.getParams().put("tableName", "t_order_equity");
                    map2.put("tableName", "t_order_equity");
                  /*  map.put("temporary",0);
                    map.put("monthly",0);
                    map.put("equity",sumPaid);*/
                }

            }
            page.getParams().put("carNumber", carNumber);
            map2.put("carNumber", carNumber);
            page.getParams().put("customer_nickname", customerName);
            map2.put("customer_nickname", customerName);
            page.getParams().put("customer_mobile", customerPhone);
            map2.put("customer_mobile", customerPhone);
            page.getParams().put("startTime", startTime);
            map2.put("startTime", startTime);
            page.getParams().put("stopTime", stopTime);
            map2.put("stopTime", stopTime);
            page.getParams().put("payType", payType);
            map2.put("payType", payType);
            map3.put("params",map2);
            List<OrderMain> list=null;
            List<OrderMain> listOrder=null;
            if(orderType.equals("11")&& onlineType.equals("2")){
               list = orderMainService.queryListPage3(page); //临停线下订单
                listOrder = orderMainService.queryListPage3(map3); //临停线下订单
            }else {
               list = orderMainService.queryListPage2(page);
                listOrder = orderMainService.queryListPage2(map3);
            }
            //单页金额
            Integer alipay=0;
            Integer wechat=0;
            Integer wallet=0;
            Integer lineoff=0;
            Integer temp=0;
           if(list!=null&&list.size()>0){
              for (OrderMain orderMain:list){
                if(orderMain.getPayType().equals("00")){
                    alipay+=orderMain.getAmountPaid();//支付宝
                }
                if(orderMain.getPayType().equals("01")){
                    wechat+=orderMain.getAmountPaid();//微信
                }
                if(orderMain.getPayType().equals("05")){
                    wallet+=orderMain.getAmountPaid();//钱包
                  }
                  if(orderMain.getPayType().equals("03")){
                      lineoff+=orderMain.getAmountPaid();//线下
                  }
                  temp+=orderMain.getAmountPaid();
              }
           }
            map.put("alipay",alipay);
            map.put("wechat",wechat);
            map.put("wallet",wallet);
            map.put("lineoff",lineoff);
            //全部金额
            Integer alipay1=0;
            Integer wechat1=0;
            Integer wallet1=0;
            Integer lineoff1=0;
            Integer temp1=0;
            if(listOrder!=null&&listOrder.size()>0){
                for (OrderMain orderMain:listOrder){
                    if(orderMain.getPayType().equals("00")){
                        alipay1+=orderMain.getAmountPaid();//支付宝
                    }
                    if(orderMain.getPayType().equals("01")){
                        wechat1+=orderMain.getAmountPaid();//微信
                    }
                    if(orderMain.getPayType().equals("05")){
                        wallet1+=orderMain.getAmountPaid();//钱包
                    }
                    if(orderMain.getPayType().equals("03")){
                        lineoff1+=orderMain.getAmountPaid();//线下
                    }
                    temp1+=orderMain.getAmountPaid();
                }
            }
            map.put("alipay1",alipay1);
            map.put("wechat1",wechat1);
            map.put("wallet1",wallet1);
            map.put("lineoff1",lineoff1);
            map.put("temp1",temp1);
            if(orderType!=null){
                if(orderType.equals("11")){
                    map.put("temporary",temp);
                    map.put("monthly",0);
                    map.put("equity",0);
                    map.put("temporary1",temp1);
                    map.put("monthly1",0);
                    map.put("equity1",0);
                }else  if(orderType.equals("13")){
                    map.put("temporary",0);
                    map.put("monthly", temp);
                    map.put("equity",0);
                    map.put("temporary1",0);
                    map.put("monthly1", temp1);
                    map.put("equity1",0);
                }else  if(orderType.equals("14")){
                    map.put("temporary",0);
                    map.put("monthly",0);
                    map.put("equity",temp);
                    map.put("temporary1",0);
                    map.put("monthly1",0);
                    map.put("equity1",temp1);
                }

            }
            map.put("temp",temp);
            page.setResultList(list);
            PageHelper.setPageModel(request, page);
          //  Object obj=orderMainService.queryMoney(); //总金额
            //map.put("obj",obj);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/order/order_main_list2", map);
    }
    @RequestMapping("list3.html")
    public ModelAndView list3(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = super.getParamMap(request);
        Map<String, Object> map2 = super.getParamMap(request);
        Map<String, Object> map3 = super.getParamMap(request);
        try {
            Page page = new Page();
            String inputDate = request.getParameter("inputDate");
            String parkingName = request.getParameter("parkingName");
            PageHelper.initPage(request, page);
            UserInfo user=(UserInfo)super.getLoginUser(request);
            if(user.getParkingId()!=null&&!("").equals(user.getParkingId())){
                String[] parkingId=user.getParkingId().split(",");
                String parkingStr = "";
                for(int i=0;i<parkingId.length;i++){
                    parkingStr +="'";
                    parkingStr+=parkingId[i];
                    parkingStr+="'";
                    parkingStr+=",";
                }
                parkingStr = parkingStr.substring(0,parkingStr.length()-1);
                page.getParams().put("parkingId", parkingStr);
                map2.put("parkingId", parkingStr);
            }
            page.getParams().put("isUsed", Constants.TRUE);
            String orderType = request.getParameter("orderType");
            String carNumber = request.getParameter("carNumber");
            page.getParams().put("carNumber", carNumber);
            map2.put("carNumber", carNumber);
            String customerPhone = request.getParameter("customerPhone");
            String startTime = request.getParameter("form_beginTime");
            String stopTime = request.getParameter("form_endTime");
            String payType = request.getParameter("payType");
            if (request.getParameterMap().containsKey("form_beginTime")){
                page.getParams().put("startTime", startTime);
                map2.put("startTime", startTime);
            }else {
                page.getParams().put("startTime",DateUtil.date2str(new Date(),DateUtil.DATE_FORMAT));
                map2.put("startTime",DateUtil.date2str(new Date(),DateUtil.DATE_FORMAT));
            }
            if (request.getParameterMap().containsKey("form_endTime")){
                page.getParams().put("stopTime", stopTime);
                map2.put("stopTime", stopTime);
            }else {
                page.getParams().put("stopTime",DateUtil.date2str(new Date(),DateUtil.DATE_FORMAT));
                map2.put("stopTime",DateUtil.date2str(new Date(),DateUtil.DATE_FORMAT));
            }
            page.getParams().put("customer_mobile", customerPhone);
            map2.put("customer_mobile", customerPhone);
            page.getParams().put("orderType", orderType);
            map2.put("orderType", orderType);
            page.getParams().put("payType", payType);
            map2.put("payType", payType);
            page.getParams().put("inputDate", inputDate);
            map2.put("inputDate", inputDate);
            page.getParams().put("parkingName", parkingName);
            map2.put("parkingName", parkingName);
            map3.put("params",map2);
            List<OrderMain> list=null;
            list = orderMainService.queryListPage4(page);
            List<OrderMain> listOrder = orderMainService.queryListPage4(map3);
            Integer alipay=0;
            Integer wechat=0;
            Integer wallet=0;
            Integer lineoff=0;
            Integer temp=0;
            Integer monthly=0;
            Integer equity=0;
            if(list!=null&&list.size()>0){
                for (OrderMain orderMain:list){
                    if(orderMain.getPayType().equals("00")){
                        alipay+=orderMain.getAmountPaid();//支付宝
                    }
                    if(orderMain.getPayType().equals("01")){
                        wechat+=orderMain.getAmountPaid();//微信
                    }
                    if(orderMain.getPayType().equals("05")){
                        wallet+=orderMain.getAmountPaid();//钱包
                    }
                    if(orderMain.getPayType().equals("03")){
                        lineoff+=orderMain.getAmountPaid();//线下
                    }
                    if(orderMain.getOrderType().equals("11")){
                        temp+=orderMain.getAmountPaid();
                    }
                    if(orderMain.getOrderType().equals("13")){
                        monthly+=orderMain.getAmountPaid();
                    }
                    if(orderMain.getOrderType().equals("14")){
                        equity+=orderMain.getAmountPaid();
                    }

                }
            }
            map.put("alipay",alipay);
            map.put("wechat",wechat);
            map.put("wallet",wallet);
            map.put("lineoff",lineoff);
            map.put("temp",temp);
            map.put("monthly",monthly);
            map.put("equity",equity);
            //当前条件下全部
            Integer alipay1=0;
            Integer wechat1=0;
            Integer wallet1=0;
            Integer lineoff1=0;
            Integer temp1=0;
            Integer monthly1=0;
            Integer equity1=0;
            if(listOrder!=null&&listOrder.size()>0){
                for (OrderMain orderMain:listOrder){
                    if(orderMain.getPayType().equals("00")){
                        alipay1+=orderMain.getAmountPaid();//支付宝
                    }
                    if(orderMain.getPayType().equals("01")){
                        wechat1+=orderMain.getAmountPaid();//微信
                    }
                    if(orderMain.getPayType().equals("05")){
                        wallet1+=orderMain.getAmountPaid();//钱包
                    }
                    if(orderMain.getPayType().equals("03")){
                        lineoff1+=orderMain.getAmountPaid();//线下
                    }
                    if(orderMain.getOrderType().equals("11")){
                        temp1+=orderMain.getAmountPaid();
                    }
                    if(orderMain.getOrderType().equals("13")){
                        monthly1+=orderMain.getAmountPaid();
                    }
                    if(orderMain.getOrderType().equals("14")){
                        equity1+=orderMain.getAmountPaid();
                    }

                }
            }
            map.put("alipay1",alipay1);
            map.put("wechat1",wechat1);
            map.put("wallet1",wallet1);
            map.put("lineoff1",lineoff1);
            map.put("temp1",temp1);
            map.put("monthly1",monthly1);
            map.put("equity1",equity1);

            page.setResultList(list);
            PageHelper.setPageModel(request, page);
           /* Object obj=orderMainService.queryMoney();
            map.put("obj",obj);*/
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/order/order_main_list3", map);
    }
    /**
     * 导出excel文档，带车牌号
     */
    @RequestMapping("excelQueryStatistics.html")
    public void excelQueryStatistics(HttpServletRequest request, HttpServletResponse response){

            Map<String, Object> map = super.getParamMap(request);
            try {
                Page page = new Page();
                page.setParams(super.getParamMap(request));
      /*          String region=request.getParameter("regionValue");
                String parkingName=request.getParameter("parkingName");
                page.getParams().put("region1", region);
                page.getParams().put("parkingName1", parkingName);*/
                page.setPageSize(10000);  //数据如果超过11111   这就不对
                PageHelper.initPage(request, page);
                UserInfo user=(UserInfo)super.getLoginUser(request);
                if(user.getParkingId()!=null&&!("").equals(user.getParkingId())){
                    String[] parkingId=user.getParkingId().split(",");
                    String parkingStr = "";
                    for(int i=0;i<parkingId.length;i++){
                        parkingStr +="'";
                        parkingStr+=parkingId[i];
                        parkingStr+="'";
                        parkingStr+=",";
                    }
                    parkingStr = parkingStr.substring(0,parkingStr.length()-1);
                    page.getParams().put("parkingId", parkingStr);
                }
                final List<Map<String,Object>> list = orderMainService.queryStatistics(page);
                page.setResultList(list);
               // Parking parking = new Parking();
                //final List<Parking> list1 = parkingService.selectList(parking);
                ExportOrderExcel.exportExcel(new ExportOrderExcel.ExcelObject() {
                    @Override
                    public void dataSource(WritableSheet sheet, WritableCellFormat wcf_center, WritableCellFormat wcf_left) throws Exception {
                        sheet.addCell(new Label(0, 0, "日期", wcf_center));
                        sheet.addCell(new Label(1, 0, "所属区域", wcf_center));
                        sheet.addCell(new Label(2, 0, "车场名称", wcf_center));
                        sheet.addCell(new Label(3, 0, "线上收入（元）", wcf_center));
                        sheet.addCell(new Label(4, 0, "线下收入（元）", wcf_center));
                        sheet.addCell(new Label(5, 0, "合计收入（元）", wcf_center));
                        sheet.addCell(new Label(6, 0, "项目负责人", wcf_center));
                        sheet.addCell(new Label(7, 0, "区域负责人", wcf_center));
                        for (int i = 0; i < list.size(); i++) {
                            Map ome = (Map) list.get(i);
                            //String pt =  DateUtil.date2str((Date) , DateUtil.DATE_FORMAT);
                            sheet.addCell(new Label(0, i + 1, ome.get("statistics_date").toString()));
                            sheet.addCell(new Label(1, i + 1,  ome.get("regionName")==null?"":ome.get("regionName").toString()));
                            sheet.addCell(new Label(2, i + 1,  ome.get("parking_name")==null?"":ome.get("parking_name").toString()));
                            sheet.addCell(new Number(3, i + 1, Double.parseDouble(ome.get("amount_online").toString())));
                            sheet.addCell(new Number(4, i + 1, Double.parseDouble(ome.get("amount_offline").toString())));
                            sheet.addCell(new Number(5, i + 1, Double.parseDouble(ome.get("amount_total").toString())));
                            sheet.addCell(new Label(6, i + 1,  ome.get("REGION_MANAGER")==null?"":ome.get("REGION_MANAGER").toString()));
                            sheet.addCell(new Label(7, i + 1,   ome.get("REGION_LEADER")==null?"":ome.get("REGION_LEADER").toString()));
//                            sheet.addCell(new Label(3, i + 1, ome.getCustomer_nickname() == null ? "" : ome.getCustomer_nickname(), wcf_left));
//                            sheet.addCell(new Label(4, i + 1, ome.getCustomer_mobile() == null ? "" : ome.getCustomer_mobile(), wcf_left));
//                            sheet.addCell(new Number(5, i + 1, ome.getAmountPayable() / 100.00, wcf_left));
//                            sheet.addCell(new Number(6, i + 1, ome.getAmountDiscount() / 100.00, wcf_left));
//                            sheet.addCell(new Number(7, i + 1, ome.getAmountPaid() / 100.00, wcf_left));
//                            sheet.addCell(new Number(8, i + 1, ome.getGiftAmount() == null? 0 : ome.getGiftAmount() / 100.00, wcf_left));
                        }
                    }
                }, response);
                map.put("result", "success");

                response.getWriter().print(JSONArray.fromObject(map).toString());

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    /**
     * 导出excel文档，带车牌号
     */
    @RequestMapping("excelCarInOutReport.html")
    public void excelCarInOutReport(HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> map = super.getParamMap(request);
        try {
            Page<CarInOutRecord> page = new Page<CarInOutRecord>();
            PageHelper.initPage(request,page);
            String startInTime = request.getParameter("form_beginTime_in");
            String endInTime = request.getParameter("form_endTime_in");
            String startOutTime = request.getParameter("form_beginTime_out");
            String endOutTime = request.getParameter("form_endTime_out");
            String parkingName = request.getParameter("parkingName");
            String inorout = request.getParameter("inorout");
            String plateId = request.getParameter("plateId");
            page.getParams().put("plateId",plateId);
            page.getParams().put("inorout",inorout);
            page.getParams().put("parkingName",parkingName);
            page.getParams().put("startInTime",startInTime);
            page.getParams().put("endInTime",endInTime);
            page.getParams().put("startOutTime",startOutTime);
            page.getParams().put("endOutTime",endOutTime);
            page.getParams().put("report","1");
            page.setPageSize(10000);
            page = carInOutRecordService.selectCarRecord(page);
            for (CarInOutRecord carInOutRecord:page.getResultList()){
                if (carInOutRecord!=null && carInOutRecord.getPayCharge()!=null){
                    carInOutRecord.setPayCharge(carInOutRecord.getPayCharge()/100);
                }
                if (carInOutRecord!=null && carInOutRecord.getRealCharge()!=null){
                    carInOutRecord.setRealCharge(carInOutRecord.getRealCharge()/100);
                }
                if (carInOutRecord.getIntime()!=null && carInOutRecord.getOuttime()!=null){
                    Long time1 = carInOutRecord.getIntime().getTime();
                    Long time2 = carInOutRecord.getOuttime().getTime();
                    long time3 = time2-time1;
                    long time4 = time3/1000/60/60;
                    long time5 = (time3-time4*60*60*1000)/1000/60;
                    carInOutRecord.setDayTime(time4+"小时"+time5+"分");
                }
            }
            final List<CarInOutRecord> list1 = page.getResultList();

            ExportOrderExcel.exportExcel(new ExportOrderExcel.ExcelObject() {
                @Override
                public void dataSource(WritableSheet sheet, WritableCellFormat wcf_center, WritableCellFormat wcf_left) throws Exception {
                    sheet.addCell(new Label(0, 0, "车场名称", wcf_center));
                    sheet.addCell(new Label(1, 0, "类型", wcf_center));
                    sheet.addCell(new Label(2, 0, "车牌号", wcf_center));
                    sheet.addCell(new Label(3, 0, "入场时间", wcf_center));
                    sheet.addCell(new Label(4, 0, "出场时间", wcf_center));
                    sheet.addCell(new Label(5, 0, "停车时间", wcf_center));
                    sheet.addCell(new Label(6, 0, "收费方式", wcf_center));
                    sheet.addCell(new Label(7, 0, "收费说明", wcf_center));
                    sheet.addCell(new Label(8, 0, "应收金额", wcf_center));
                    sheet.addCell(new Label(9, 0, "实收金额", wcf_center));
                    for (int i = 0; i < list1.size(); i++) {
                        String inTime = "";
                        String outTime = "";
                        CarInOutRecord ome = list1.get(i);
                        if(null!=ome.getIntime()){
                            inTime = DateUtil.date2str(ome.getIntime(), DateUtil.DATE_FORMAT);
                        }
                        if(null!=ome.getOuttime()){
                            outTime = DateUtil.date2str(ome.getOuttime(), DateUtil.DATE_FORMAT);
                        }
                        sheet.addCell(new Label(0, i + 1, ome.getParkingName()));
                        sheet.addCell(new Label(1, i + 1, ""));
                        sheet.addCell(new Label(2, i + 1, ome.getPlateId()));
                        sheet.addCell(new Label(3, i + 1, inTime));
                        sheet.addCell(new Label(4, i + 1, outTime));
                        sheet.addCell(new Label(5, i + 1, ome.getDayTime()));
                        sheet.addCell(new Label(6, i + 1, ""));
                        sheet.addCell(new Label(7, i + 1, ""));
                        sheet.addCell(new Number(8, i + 1, ome.getPayCharge()));
                        sheet.addCell(new Number(9, i + 1, ome.getPayCharge()));
                    }
                }
            }, response);
            map.put("result", "success");

            response.getWriter().print(JSONArray.fromObject(map).toString());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 导出excel文档，项目统计详情
     */
    @RequestMapping("excelPaymentDetailReport.html/{date}/{region}/{parkingId}")
    public void excelPaymentDetailReport(HttpServletRequest request, HttpServletResponse response,
                                         @PathVariable String date,
                                         @PathVariable String region,
                                         @PathVariable String parkingId){
        try{
            Map<String, Object> map = super.getParamMap(request);
            String cType = request.getParameter("cType");
            String onlineType = request.getParameter("onlineType");
            List<OrderMain> outList = null;
            final List<OrderMain> odList = orderMainService.getPaymentDetail(date,region,parkingId);
            List<OrderMain> rmList = new ArrayList<OrderMain>();
            //去除
            if(StringUtils.isEmpty(cType)&&!StringUtils.isEmpty(onlineType)){
                for(OrderMain o:odList){
                    if(!onlineType.equals(o.getOnlineType())){
                        rmList.add(o);
                    }
                }
            }else if(!StringUtils.isEmpty(cType)&&StringUtils.isEmpty(onlineType)){
                for(OrderMain o:odList) {
                    if (!cType.equals(o.getOrderType())) {
                        rmList.add(o);
                    }
                }
            }else if(!StringUtils.isEmpty(cType)&&!StringUtils.isEmpty(onlineType)) {
                for (OrderMain o : odList) {
                    if (!cType.equals(o.getOrderType())||!onlineType.equals(o.getOnlineType())) {
                        rmList.add(o);
                    }
                }
            }
            odList.removeAll(rmList);

            ExportOrderExcel.exportExcel(new ExportOrderExcel.ExcelObject() {
                @Override
                public void dataSource(WritableSheet sheet, WritableCellFormat wcf_center, WritableCellFormat wcf_left) throws Exception {
                    sheet.addCell(new Label(0, 0, "日期", wcf_center));
                    sheet.addCell(new Label(1, 0, "车场名称", wcf_center));
                    sheet.addCell(new Label(2, 0, "所属区域", wcf_center));
                    sheet.addCell(new Label(3, 0, "类型", wcf_center));
                    sheet.addCell(new Label(4, 0, "支付类型", wcf_center));
                    sheet.addCell(new Label(5, 0, "收入金额", wcf_center));
                    for (int i = 0; i < odList.size(); i++) {
                        OrderMain ome = odList.get(i);
                        String tDate = "";
                        if(null!=ome.getPayTime()){
                            tDate = DateUtil.date2str(ome.getPayTime(), DateUtil.DATE_FORMAT);
                        }
                        String orderType = "";
                        String onlineType = "";
                        if("11".equals(ome.getOrderType())){
                            orderType = "临停";
                        }else if("13".equals(ome.getOrderType())){
                            orderType = "月租";
                        }else if("14".equals(ome.getOrderType())){
                            orderType = "产权";
                        }
                        if("1".equals(ome.getOnlineType())){
                            onlineType = "线上";
                        }else{
                            onlineType = "线下";
                        }
                        sheet.addCell(new Label(0, i + 1, tDate));
                        sheet.addCell(new Label(1, i + 1, ome.getParkingName()));
                        sheet.addCell(new Label(2, i + 1, ome.getRegion()));
                        sheet.addCell(new Label(3, i + 1, orderType));
                        sheet.addCell(new Label(4, i + 1, onlineType));
                        sheet.addCell(new Label(5, i + 1, StringUtils.isEmpty(ome.getSumPaid())?"0":new BigDecimal(ome.getSumPaid()).setScale(2).toString()));
                    }
                }
            }, response);
            map.put("result", "success");

            response.getWriter().print(JSONArray.fromObject(map).toString());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 导出excel文档，带车牌号
     */
    @RequestMapping("excelReport.html")
    public void excelReport(HttpServletRequest request, HttpServletResponse response){

       Map<String, Object> map1 = super.getParamMap(request);
        try {
            Map<String, Object> map = super.getParamMap(request);
            String parkingName = request.getParameter("parkingName");
            final String orderType = request.getParameter("orderType");
            String customerPhone = request.getParameter("customerPhone");
            String startTime = request.getParameter("form_beginTime");
            String stopTime = request.getParameter("form_endTime");
            String payType = request.getParameter("payType");
            String carNumber = request.getParameter("carNumber");
            map.put("customer_mobile", customerPhone);
            map.put("orderType", orderType);
            try {
                map.put("startTime", DateUtil.str2date(startTime, DateUtil.DATE_FORMAT));
                map.put("stopTime", DateUtil.str2date(stopTime, DateUtil.DATE_FORMAT));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            UserInfo user=(UserInfo)super.getLoginUser(request);
            if(user.getParkingId()!=null&&!("").equals(user.getParkingId())){
                String[] parkingId1=user.getParkingId().split(",");
                String parkingStr = "";
                for(int i=0;i<parkingId1.length;i++){
                    parkingStr +="'";
                    parkingStr+=parkingId1[i];
                    parkingStr+="'";
                    parkingStr+=",";
                }
                parkingStr = parkingStr.substring(0,parkingStr.length()-1);
                map.put("parkingId", parkingStr);
            }
            map.put("payType", payType);
            map.put("carNumber", carNumber);
            map.put("parkingName", parkingName);
            map1.put("params",map);
           // final  Map obj=(Map)  orderMainService.queryMoney();
              final  List<OrderMain>  list = orderMainService.queryListPage4(map1);
           // final List<Object> list = orderMainService.queryStatistics(page);
            Integer alipay=0;
            Integer wechat=0;
            Integer wallet=0;
            Integer lineoff=0;
            Integer temp=0;
            Integer monthly=0;
            Integer equity=0;
            if(list!=null&&list.size()>0){
                for (OrderMain orderMain:list){
                    if(orderMain.getPayType().equals("00")){
                        alipay+=orderMain.getAmountPaid();//支付宝
                    }
                    if(orderMain.getPayType().equals("01")){
                        wechat+=orderMain.getAmountPaid();//微信
                    }
                    if(orderMain.getPayType().equals("05")){
                        wallet+=orderMain.getAmountPaid();//钱包
                    }
                    if(orderMain.getPayType().equals("03")){
                        lineoff+=orderMain.getAmountPaid();//线下
                    }
                    if(orderMain.getOrderType().equals("11")){
                        temp+=orderMain.getAmountPaid();
                    }
                    if(orderMain.getOrderType().equals("13")){
                        monthly+=orderMain.getAmountPaid();
                    }
                    if(orderMain.getOrderType().equals("14")){
                        equity+=orderMain.getAmountPaid();
                    }

                }
            }
            final  Integer alipay1=alipay;
            final  Integer wechat1=wechat;
            final  Integer wallet1=wallet;
            final  Integer lineoff1=lineoff;
            final  Integer temp1=temp;
            final  Integer monthly1=monthly;
            final  Integer equity1=equity;
            ExportOrderExcel.exportExcel(new ExportOrderExcel.ExcelObject() {
                @Override
                public void dataSource(WritableSheet sheet, WritableCellFormat wcf_center, WritableCellFormat wcf_left) throws Exception {
                    sheet.addCell(new Label(0, 0, "车场名称", wcf_center));
                    sheet.addCell(new Label(1, 0, "订单类型", wcf_center));
                    sheet.addCell(new Label(2, 0, "车牌号", wcf_center));
                    sheet.addCell(new Label(3, 0, "客户姓名", wcf_center));
                    sheet.addCell(new Label(4, 0, "客户手机", wcf_center));
                    sheet.addCell(new Label(5, 0, "缴费时间", wcf_center));
                    sheet.addCell(new Label(6, 0, "缴费金额", wcf_center));
                    sheet.addCell(new Label(7, 0, "支付类型", wcf_center));
                    for (int i = 0; i < list.size(); i++) {
                        OrderMain orderMain = list.get(i);
                        sheet.addCell(new Label(0, i + 1, orderMain.getParkingName()==null?"":orderMain.getParkingName()));
                        sheet.addCell(new Label(1, i + 1, orderMain.getOrderType()==null?"":OrderConstants.getOrderTypeName(orderMain.getOrderType())));
                        sheet.addCell(new Label(2, i + 1, orderMain.getCarNumber()==null?"":orderMain.getCarNumber()));
                        if(orderMain.getCustomer_nickname()!=null&&!orderMain.getCustomer_nickname().equals("")){
                           sheet.addCell(new Label(3, i + 1, orderMain.getCustomer_nickname()));
                        }else {
                            sheet.addCell(new Label(3, i + 1, orderMain.getCustomer_mobile()));
                        }
                        sheet.addCell(new Label(4, i + 1, orderMain.getCustomer_mobile()==null?"":orderMain.getCustomer_mobile()));
                        sheet.addCell(new Label(5, i + 1, orderMain.getPayTime()==null?"":DateUtil.date2str(orderMain.getPayTime(),DateUtil.DATE_FORMAT)));
                        sheet.addCell(new Number(6, i + 1, orderMain.getAmountPaid()==null?0:orderMain.getAmountPaid()/100));
                        sheet.addCell(new Label(7, i + 1,  orderMain.getPayType()==null?"":OrderConstants.getPayTypeName(orderMain.getPayType())));
                    }
                    sheet.addCell(new Label(0, list.size()+1,"临停合计(元)"));
                    sheet.addCell(new Number(1, list.size()+1,temp1/100));
                    sheet.addCell(new Label(2, list.size()+1,"月租合计(元)"));
                    sheet.addCell(new Number(3, list.size()+1,monthly1/100));
                    sheet.addCell(new Label(4, list.size()+1,"产权合计(元)"));
                    sheet.addCell(new Number(5, list.size()+1 ,equity1/100));

                    sheet.addCell(new Label(0, list.size()+2,"支付宝合计(元)"));
                    sheet.addCell(new Number(1, list.size()+2,alipay1/100));
                    sheet.addCell(new Label(2, list.size()+2,"线下合计(元)"));
                    sheet.addCell(new Number(3, list.size()+2,lineoff1/100));
                    sheet.addCell(new Label(4, list.size()+2,"微信合计(元)"));
                    sheet.addCell(new Number(5, list.size()+2,wechat1/100));
                    sheet.addCell(new Label(6, list.size()+2,"钱包合计(元)"));
                    sheet.addCell(new Number(7, list.size()+2 ,wallet1/100));
                }
            }, response);
            map.put("result", "success");

            response.getWriter().print(JSONArray.fromObject(map).toString());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("excelReport2.html")
    public void excelReport2(HttpServletRequest request, HttpServletResponse response){

        // Map<String, Object> map = super.getParamMap(request);
        try {
            Map<String, Object> map = super.getParamMap(request);
            Map<String, Object> map1 = new HashMap<>();
            String customerPhone = request.getParameter("customerPhone");
            // String payType = request.getParameter("payType");
            String carNumber = request.getParameter("carNumber");
            String inputDate = request.getParameter("inputDate");
            String parkingName = request.getParameter("parkingName");
            String parkingId = request.getParameter("parkingId");
            String region = request.getParameter("region");
            String onlineType = request.getParameter("onlineType");
            String orderType = request.getParameter("orderType");
            map.put("customer_mobile", customerPhone);
            //  map.put("payType", payType);
            map.put("carNumber", carNumber);
            map.put("inputDate", inputDate);
            map.put("parkingName", parkingName);
            map.put("parkingId", "'"+parkingId+"'");
            UserInfo user=(UserInfo)super.getLoginUser(request);
            if(user.getParkingId()!=null&&!("").equals(user.getParkingId())){
                String[] parkingId1=user.getParkingId().split(",");
                String parkingStr = "";
                for(int i=0;i<parkingId1.length;i++){
                    parkingStr +="'";
                    parkingStr+=parkingId1[i];
                    parkingStr+="'";
                    parkingStr+=",";
                }
                parkingStr = parkingStr.substring(0,parkingStr.length()-1);
                map.put("parkingId", parkingStr);
            }
            map.put("region", region);
            if (!StringUtils.isEmpty(onlineType)) {
                if (onlineType.equals("1")) {
                    map.put("onlineType", "00,01,02,04,05");
                } else if (onlineType.equals("2")) {
                    map.put("onlineType", "03");
                }
            }
            if (orderType != null) {
                if (orderType.equals("11")) {
                    map.put("tableName", "t_order_temporary");
                } else if (orderType.equals("13")) {
                    map.put("tableName", "t_order_monthly");
                } else if (orderType.equals("14")) {
                    map.put("tableName", "t_order_equity");
                }
            }
            map1.put("params", map);
            List<OrderMain> list1 = null;
            if (orderType.equals("11") && onlineType.equals("2")) {
                list1 = orderMainService.queryListPage3(map1);
            } else {
                list1 = orderMainService.queryListPage2(map1);
            }
            Integer alipay=0;
            Integer wechat=0;
            Integer wallet=0;
            Integer lineoff=0;
            Integer temp=0;
            Integer monthly=0;
            Integer equity=0;
            if(list1!=null&&list1.size()>0){
                for (OrderMain orderMain:list1){
                    if(orderMain.getPayType().equals("00")){
                        alipay+=orderMain.getAmountPaid();//支付宝
                    }
                    if(orderMain.getPayType().equals("01")){
                        wechat+=orderMain.getAmountPaid();//微信
                    }
                    if(orderMain.getPayType().equals("05")){
                        wallet+=orderMain.getAmountPaid();//钱包
                    }
                    if(orderMain.getPayType().equals("03")){
                        lineoff+=orderMain.getAmountPaid();//线下
                    }
                    if(orderMain.getOrderType().equals("11")){
                        temp+=orderMain.getAmountPaid();
                    }
                    if(orderMain.getOrderType().equals("13")){
                        monthly+=orderMain.getAmountPaid();
                    }
                    if(orderMain.getOrderType().equals("14")){
                        equity+=orderMain.getAmountPaid();
                    }

                }
            }
            final  Integer alipay1=alipay;
            final  Integer wechat1=wechat;
            final  Integer wallet1=wallet;
            final  Integer lineoff1=lineoff;
            final  Integer temp1=temp;
            final  Integer monthly1=monthly;
            final  Integer equity1=equity;
         // final   Map obj=(Map) orderMainService.queryMoney();
           final List<OrderMain>  list=list1;
            // final List<Object> list = orderMainService.queryStatistics(page);
            ExportOrderExcel.exportExcel(new ExportOrderExcel.ExcelObject() {
                @Override
                public void dataSource(WritableSheet sheet, WritableCellFormat wcf_center, WritableCellFormat wcf_left) throws Exception {
                    sheet.addCell(new Label(0, 0, "车场名称", wcf_center));
                    sheet.addCell(new Label(1, 0, "订单类型", wcf_center));
                    sheet.addCell(new Label(2, 0, "车牌号", wcf_center));
                    sheet.addCell(new Label(3, 0, "客户姓名", wcf_center));
                    sheet.addCell(new Label(4, 0, "客户手机", wcf_center));
                    sheet.addCell(new Label(5, 0, "缴费时间", wcf_center));
                    sheet.addCell(new Label(6, 0, "缴费金额", wcf_center));
                    sheet.addCell(new Label(7, 0, "支付类型", wcf_center));
                    for (int i = 0; i < list.size(); i++) {
                        OrderMain orderMain = list.get(i);
                        sheet.addCell(new Label(0, i + 1, orderMain.getParkingName()==null?"":orderMain.getParkingName()));
                        sheet.addCell(new Label(1, i + 1, orderMain.getOrderType()==null?"":OrderConstants.getOrderTypeName(orderMain.getOrderType())));
                        sheet.addCell(new Label(2, i + 1, orderMain.getCarNumber()==null?"":orderMain.getCarNumber()));
                        if(orderMain.getCustomer_nickname()!=null&&!orderMain.getCustomer_nickname().equals("")){
                            sheet.addCell(new Label(3, i + 1, orderMain.getCustomer_nickname()));
                        }else {
                            sheet.addCell(new Label(3, i + 1, orderMain.getCustomer_mobile()));
                        }
                        sheet.addCell(new Label(4, i + 1, orderMain.getCustomer_mobile()==null?"":orderMain.getCustomer_mobile()));
                        sheet.addCell(new Label(5, i + 1, orderMain.getPayTime()==null?"":DateUtil.date2str(orderMain.getPayTime(),DateUtil.DATE_FORMAT)));
                        sheet.addCell(new Number(6, i + 1, orderMain.getAmountPaid()==null?0:orderMain.getAmountPaid()/100));
                        sheet.addCell(new Label(7, i + 1,  orderMain.getPayType()==null?"":OrderConstants.getPayTypeName(orderMain.getPayType())));
                    }
                    sheet.addCell(new Label(0, list.size()+1,"临停合计(元)"));
                    sheet.addCell(new Number(1, list.size()+1,temp1/100));
                    sheet.addCell(new Label(2, list.size()+1,"月租合计(元)"));
                    sheet.addCell(new Number(3, list.size()+1,monthly1/100));
                    sheet.addCell(new Label(4, list.size()+1,"产权合计(元)"));
                    sheet.addCell(new Number(5, list.size()+1 ,equity1/100));

                    sheet.addCell(new Label(0, list.size()+2,"支付宝合计(元)"));
                    sheet.addCell(new Number(1, list.size()+2,alipay1/100));
                    sheet.addCell(new Label(2, list.size()+2,"线下合计(元)"));
                    sheet.addCell(new Number(3, list.size()+2,lineoff1/100));
                    sheet.addCell(new Label(4, list.size()+2,"微信合计(元)"));
                    sheet.addCell(new Number(5, list.size()+2,wechat1/100));
                    sheet.addCell(new Label(6, list.size()+2,"钱包合计(元)"));
                    sheet.addCell(new Number(7, list.size()+2 ,wallet1/100));
                }
            }, response);
            map.put("result", "success");

            response.getWriter().print(JSONArray.fromObject(map).toString());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
