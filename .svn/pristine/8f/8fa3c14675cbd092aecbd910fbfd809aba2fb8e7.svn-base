package com.boxiang.share.product.parking.controller;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.order.controller.ExportOrderExcel;
import com.boxiang.share.product.parking.po.CarInOutRecord;
import com.boxiang.share.product.parking.po.CarInOutRecordV2;
import com.boxiang.share.product.parking.service.CarInOutRecordService;
import com.boxiang.share.product.parking.service.CarInOutRecordV2Service;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.utils.DateUtil;
import jxl.write.*;
import jxl.write.Number;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("products/carInOutRecord")
public class CarInOutRecordController extends BaseController {

    private static final Logger logger = Logger.getLogger(CarInOutRecordController.class);

    @Resource
    private CarInOutRecordV2Service carInOutRecordV2Service;

//    @Resource private CarInOutRecordService carInOutRecordService;
//
//    @RequestMapping("list.html")
//    public ModelAndView carRecordList(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            Page<CarInOutRecord> page = new Page<CarInOutRecord>();
//            PageHelper.initPage(request,page);
//            String startInTime = request.getParameter("form_beginTime_in");
//            String endInTime = request.getParameter("form_endTime_in");
//            String startOutTime = request.getParameter("form_beginTime_out");
//            String endOutTime = request.getParameter("form_endTime_out");
//            String parkingName = request.getParameter("parkingName");
//            String inorout = request.getParameter("inorout");
//            String plateId = request.getParameter("plateId");
//            page.getParams().put("plateId",plateId);
//            page.getParams().put("inorout",inorout);
//            page.getParams().put("parkingName",parkingName);
//            page.getParams().put("startInTime",startInTime);
//            page.getParams().put("endInTime",endInTime);
//            page.getParams().put("startOutTime",startOutTime);
//            page.getParams().put("endOutTime",endOutTime);
//            page = carInOutRecordService.selectCarRecord(page);
//            for (CarInOutRecord carInOutRecord:page.getResultList()){
//                if (carInOutRecord!=null && carInOutRecord.getPayCharge()!=null){
//                    carInOutRecord.setPayCharge(carInOutRecord.getPayCharge()/100);
//                }
//                if (carInOutRecord!=null && carInOutRecord.getRealCharge()!=null){
//                    carInOutRecord.setRealCharge(carInOutRecord.getRealCharge()/100);
//                }
//               if (carInOutRecord.getIntime()!=null && carInOutRecord.getOuttime()!=null){
//                   Long time1 = carInOutRecord.getIntime().getTime();
//                   Long time2 = carInOutRecord.getOuttime().getTime();
//                   long time3 = time2-time1;
//                   long time4 = time3/1000/60/60;
//                   long time5 = (time3-time4*60*60*1000)/1000/60;
//                   carInOutRecord.setDayTime(time4+":"+time5);
//               }
//            }
//            PageHelper.setPageModel(request, page);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ModelAndView("products/carInOutRecord/carInOutRecord_list");
//    }
//    @RequestMapping("view.html")
//    public ModelAndView view(HttpServletRequest request,HttpServletResponse response){
//        String id = request.getParameter("id");
//        CarInOutRecord carInOutRecord = carInOutRecordService.queryById(Integer.parseInt(id));
//        CarInOutRecord carInOutRecord1 = new CarInOutRecord();
//        carInOutRecord1.setId(Integer.parseInt(id));
//        CarInOutRecord carInOutRecord2 = carInOutRecordService.selectParkingName(carInOutRecord);
//        Map map = new HashMap();
//        map.put("carInOutRecord2",carInOutRecord2);
//        if (carInOutRecord!=null){
//            carInOutRecord.setRealCharge(carInOutRecord.getRealCharge()/100);
//            carInOutRecord.setPayCharge(carInOutRecord.getPayCharge()/100);
//        }
//        map.put("carInOutRecord",carInOutRecord);
//        return new ModelAndView("products/carInOutRecord/carInOutRecord_view",map);
//    }
//    @RequestMapping("excelExportCarRecord.html")
//    public void excelExportCarRecord(HttpServletRequest request,HttpServletResponse response){
//        String inorout = request.getParameter("inorout");
//        String parkingName = request.getParameter("parkingName");
//        String startInTime = request.getParameter("startInTime");
//        String endInTime = request.getParameter("endInTime");
//        String startOutTime = request.getParameter("startOutTime");
//        String endOutTime = request.getParameter("endOutTime");
//        String plateId = request.getParameter("plateId");
//        CarInOutRecord carInOutRecord = new CarInOutRecord();
//        if (inorout!=null && (inorout.equals("1") || inorout.equals("2")))
//            carInOutRecord.setInorout(inorout);
//        carInOutRecord.setParkingName(parkingName);
//        carInOutRecord.setStartInTime(startInTime);
//        carInOutRecord.setStartOutTime(startOutTime);
//        carInOutRecord.setEndInTime(endInTime);
//        carInOutRecord.setEndOutTime(endOutTime);
//        carInOutRecord.setPlateId(plateId);
//        List<CarInOutRecord> carInOutRecordList = carInOutRecordService.queryCarRecordList(carInOutRecord);
//        for (CarInOutRecord carInOutRecord1:carInOutRecordList){
//            if (carInOutRecord1.getIntime()!=null && carInOutRecord1.getOuttime()!=null){
//                Long time1 = carInOutRecord1.getIntime().getTime();
//                Long time2 = carInOutRecord1.getOuttime().getTime();
//                long time3 = time2-time1;
//                long time4 = time3/1000/60/60;
//                long time5 = (time3-time4*60*60*1000)/1000/60;
//                carInOutRecord1.setDayTime(time4+":"+time5);
//            }else {
//                carInOutRecord1.setDayTime("");
//            }
//        }
//        if (carInOutRecordList !=null){
//            ExportOrderExcel.exportCarRecordExcel("新建", new String[]{"车场名称", "车牌号", "进场/出场", "入场时间", "出场时间","应收金额","实收金额","停车时间"}, carInOutRecordList, response);
//        }
//    }

    /**
     * 查询列表
     */
    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request) {
        try {
            Page<Object> page = new Page<>();
            PageHelper.initPage(request, page);
            page.setParams(super.getParamMap(request));
            UserInfo userInfo = (UserInfo) super.getLoginUser(request);
            if (StringUtils.isNotEmpty(userInfo.getParkingId())) {
                page.getParams().put("parkingId", "'" + userInfo.getParkingId().replace(",", "','") + "'");
            }
            page.setResultList(carInOutRecordV2Service.queryListPageV2(page));
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/carInOutRecord/car_in_out_record_v2_list");
    }

    /**
     * 导出excel文档
     */
    @RequestMapping("excelList.html")
    public void excelRecharge(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> params = super.getParamMap(request);
            map.put("params", params);
            UserInfo userInfo = (UserInfo) super.getLoginUser(request);
            if (StringUtils.isNotEmpty(userInfo.getParkingId())) {
                params.put("parkingId", "'" + userInfo.getParkingId().replace(",", "','") + "'");
            }
            final List<Object> list = carInOutRecordV2Service.queryListPageV2(map);
            if (list != null && list.size() > 0) {
                ExportOrderExcel.exportExcel(new ExportOrderExcel.ExcelObject() {
                    @Override
                    public void dataSource(WritableSheet sheet, WritableCellFormat wcf_center, WritableCellFormat wcf_left) throws Exception {
                        sheet.addCell(new Label(0, 0, "车场名称", wcf_center));
                        sheet.addCell(new Label(1, 0, "车牌号", wcf_center));
                        sheet.addCell(new Label(2, 0, "进场/出场", wcf_center));
                        sheet.addCell(new Label(3, 0, "入场时间", wcf_center));
                        sheet.addCell(new Label(4, 0, "出场时间", wcf_center));
                        sheet.addCell(new Label(5, 0, "应收金额", wcf_center));
                        sheet.addCell(new Label(6, 0, "实收金额", wcf_center));
                        sheet.addCell(new Label(7, 0, "停车时间", wcf_center));
                        sheet.addCell(new Label(8, 0, "收费方式", wcf_center));
                        sheet.addCell(new Label(9, 0, "收费说明", wcf_center));
                        sheet.addCell(new Label(10, 0, "操作员", wcf_center));
                        for (int i = 0; i < list.size(); i++) {
                            CarInOutRecordV2 item = (CarInOutRecordV2) list.get(i);
                            sheet.addCell(new Label(0, i + 1, StringUtils.defaultString(item.getParkingName()), wcf_left));
                            sheet.addCell(new Label(1, i + 1, StringUtils.defaultString(item.getPlateId()), wcf_left));
                            sheet.addCell(new Label(2, i + 1, item.getOutparkId() == null ? "入场" : "出场", wcf_left));
                            sheet.addCell(new Label(3, i + 1, item.getInTime() == null ? "" : DateUtil.date2str(item.getInTime(), DateUtil.DATETIME_FORMAT), wcf_left));
                            sheet.addCell(new Label(4, i + 1, item.getOutTime() == null ? "" : DateUtil.date2str(item.getOutTime(), DateUtil.DATETIME_FORMAT), wcf_left));
                            sheet.addCell(new Number(5, i + 1, item.getPayCharge() == null ? 0 : item.getPayCharge() / 100.00, wcf_left));
                            sheet.addCell(new Number(6, i + 1, item.getRealCharge() == null ? 0 : item.getRealCharge() / 100.00, wcf_left));
                            sheet.addCell(new Label(7, i + 1, StringUtils.defaultString(item.getStayTime()), wcf_left));
                            sheet.addCell(new Label(8, i + 1, StringUtils.defaultString(item.getPayType()), wcf_left));
                            sheet.addCell(new Label(9, i + 1, StringUtils.defaultString(item.getRemark()), wcf_left));
                            sheet.addCell(new Label(10, i + 1, StringUtils.defaultString(item.getOperator()), wcf_left));
                        }
                    }
                }, response);
            } else {
                String js = "<script>alert('没有需要导出的内容');history.back();</script>";
                response.getWriter().print(js);
            }
        } catch (Exception e) {
            logger.error("异常", e);
        }
    }

    @RequestMapping("view.html")
    public ModelAndView view(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        Map<String, Object> map = new HashMap<>(1);
        map.put("params", params);
        List<Object> list = carInOutRecordV2Service.queryListPageV2(map);
        request.setAttribute("entity", list.get(0));
        return new ModelAndView("products/carInOutRecord/car_in_out_record_v2_view");
    }

}
