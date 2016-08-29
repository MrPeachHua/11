package com.boxiang.share.reports.contoller;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.order.controller.ExportOrderExcel;
import com.boxiang.share.product.parking.po.CarInOutRecord;
import com.boxiang.share.product.parking.service.CarInOutRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("reports/carInOutRecord")
public class CarInOutRecordReportController extends BaseController{
    @Resource private CarInOutRecordService carInOutRecordService;

    @RequestMapping("list.html")
    public ModelAndView carRecordList(HttpServletRequest request, HttpServletResponse response) {
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
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("products/carInOutRecord/carInOutRecord_report_list");
    }
    @RequestMapping("view.html")
    public ModelAndView view(HttpServletRequest request,HttpServletResponse response){
        String id = request.getParameter("id");
        CarInOutRecord carInOutRecord = carInOutRecordService.queryById(Integer.parseInt(id));
        CarInOutRecord carInOutRecord1 = new CarInOutRecord();
        carInOutRecord1.setId(Integer.parseInt(id));
        CarInOutRecord carInOutRecord2 = carInOutRecordService.selectParkingName(carInOutRecord);
        Map map = new HashMap();
        map.put("carInOutRecord2",carInOutRecord2);
        if (carInOutRecord!=null){
            carInOutRecord.setRealCharge(carInOutRecord.getRealCharge()/100);
            carInOutRecord.setPayCharge(carInOutRecord.getPayCharge()/100);
        }
        map.put("carInOutRecord",carInOutRecord);
        return new ModelAndView("products/carInOutRecord/carInOutRecord_view",map);
    }
    @RequestMapping("excelExportCarRecord.html")
    public void excelExportCarRecord(HttpServletRequest request,HttpServletResponse response){
        String inorout = request.getParameter("inorout");
        String parkingName = request.getParameter("parkingName");
        String startInTime = request.getParameter("startInTime");
        String endInTime = request.getParameter("endInTime");
        String startOutTime = request.getParameter("startOutTime");
        String endOutTime = request.getParameter("endOutTime");
        String plateId = request.getParameter("plateId");
        CarInOutRecord carInOutRecord = new CarInOutRecord();
        if (inorout!=null && (inorout.equals("1") || inorout.equals("2")))
            carInOutRecord.setInorout(inorout);
        carInOutRecord.setParkingName(parkingName);
        carInOutRecord.setStartInTime(startInTime);
        carInOutRecord.setStartOutTime(startOutTime);
        carInOutRecord.setEndInTime(endInTime);
        carInOutRecord.setEndOutTime(endOutTime);
        carInOutRecord.setPlateId(plateId);
        List<CarInOutRecord> carInOutRecordList = carInOutRecordService.queryCarRecordList(carInOutRecord);
        for (CarInOutRecord carInOutRecord1:carInOutRecordList){
            if (carInOutRecord1.getIntime()!=null && carInOutRecord1.getOuttime()!=null){
                Long time1 = carInOutRecord1.getIntime().getTime();
                Long time2 = carInOutRecord1.getOuttime().getTime();
                long time3 = time2-time1;
                long time4 = time3/1000/60/60;
                long time5 = (time3-time4*60*60*1000)/1000/60;
                carInOutRecord1.setDayTime(time4+":"+time5);
            }else {
                carInOutRecord1.setDayTime("");
            }
        }
        if (carInOutRecordList !=null){
            ExportOrderExcel.exportCarRecordExcel("新建", new String[]{"车场名称", "车牌号", "进场/出场", "入场时间", "出场时间","应收金额","实收金额","停车时间"}, carInOutRecordList, response);
        }
    }
}
