package com.boxiang.share.customer.controller;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.car.dao.CarMainDao;
import com.boxiang.share.product.car.po.Car;
import com.boxiang.share.product.car.po.CarMain;
import com.boxiang.share.product.car.service.CarMainService;
import com.boxiang.share.product.car.service.CarService;
import com.boxiang.share.product.carBrand.po.CarBrand;
import com.boxiang.share.product.carBrand.service.CarBrandService;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.order.controller.ExportOrderExcel;
import com.boxiang.share.system.service.DictionaryService;
import com.boxiang.share.utils.json.JacksonUtil;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kaiser on 2016/8/23.
 */
@Controller
@RequestMapping(value = "customer/customer_car")
public class CustomerCarController  extends BaseController{
    private static final Logger log = Logger
            .getLogger(CustomerCarController.class);


    @Resource
    private DictionaryService dictionaryService;
    @Resource
    private CustomerService customerService;
    @Resource
    private CarService carService;
    @Resource
    private CarBrandService carBrandService;
    @Resource
    private CarMainDao carMainDao;

    @RequestMapping("list.html")
    public ModelAndView showList(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object> map=new HashMap();
        try{
            request.setCharacterEncoding("UTF-8");
			/*查询列表*/
            Page page = new Page<>();
            PageHelper.initPage(request, page);
           // page.setParams(super.getParamMap(request));
            List<Map<String,Object>> list = carService.queryListPage2(page);
            page.setResultList(list);
            PageHelper.setPageModel(request, page);
            List<CarBrand> listCarBrand=carBrandService.selectList2(null);//全部品牌
            map.put("listCarBrand",listCarBrand);
        }catch(Exception e){
            e.printStackTrace();
            log.error("进入客户信息列表页出现异常",e);
        }
        return new ModelAndView("customer/car_list",map);
    }
    //联动
    @RequestMapping("queryCarSeries.html")
    public void queryCarSeries(HttpServletRequest request, HttpServletResponse response) {
        try {
            String carBrand = request.getParameter("carBrand");
          List list=  carMainDao.queryCarSeries2(carBrand);
                response.getWriter().print(JacksonUtil.toJson(list));

        } catch (IOException e) {
         e.printStackTrace();
        }
    }
    //联动
    @RequestMapping("queryDisplacement.html")
    public void queryDisplacement(HttpServletRequest request, HttpServletResponse response) {
        try {
            String carBrand = request.getParameter("carBrand");
            String carSeries = request.getParameter("carSeries");
            CarMain carMain=new CarMain();
            carMain.setBrand(carBrand);
            carMain.setCarSeries(carSeries);
            List list=  carMainDao.queryDisplacement3(carMain);
            response.getWriter().print(JacksonUtil.toJson(list));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //联动
    @RequestMapping("queryStyleYear.html")
    public void queryStyleYear(HttpServletRequest request, HttpServletResponse response) {
        try {
            String carBrand = request.getParameter("carBrand");
            String carSeries = request.getParameter("carSeries");
            String displacement = request.getParameter("displacement");
            CarMain carMain=new CarMain();
            carMain.setBrand(carBrand);
            carMain.setCarSeries(carSeries);
            carMain.setDisplacement(displacement);
            List list=  carMainDao.queryDisplacement4(carMain);
            response.getWriter().print(JacksonUtil.toJson(list));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出
     * @param request
     * @param response
     */
    @RequestMapping(value="excelExport.html")
    public void excelExport(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> map3 = super.getParamMap(request);
        try {

            request.setCharacterEncoding("UTF-8");
            Map<String,Object> map=new HashMap();
            Map<String,Object> map2=new HashMap();
            String customerMobile = request.getParameter("customerMobile");
            String customerCardId = request.getParameter("customerCardId");
            String appVersion = request.getParameter("appVersion");
            String registerBegin = request.getParameter("registerBegin");
            String registerEnd = request.getParameter("registerEnd");
            String loginBegin = request.getParameter("loginBegin");
            String loginEnd = request.getParameter("loginEnd");
            map2.put("customerMobile",customerMobile);
            map2.put("customerCardId",customerCardId);
            map2.put("appVersion",appVersion);
            map2.put("registerBegin",registerBegin);
            map2.put("registerEnd",registerEnd);
            map2.put("loginBegin",loginBegin);
            map2.put("loginEnd",loginEnd);
            map.put("params",map2);


            final	List<Map<String,Object>> list = carService.queryListPage2(map);
            ExportOrderExcel.exportExcel(new ExportOrderExcel.ExcelObject() {
                @Override
                public void dataSource(WritableSheet sheet, WritableCellFormat wcf_center, WritableCellFormat wcf_left) throws Exception {
                    sheet.addCell(new Label(0, 0, "用户ID", wcf_center));
                    sheet.addCell(new Label(1, 0, "手机号码", wcf_center));
                    sheet.addCell(new Label(2, 0, "车牌号码", wcf_center));
                    sheet.addCell(new Label(3, 0, "行驶里程（公里）", wcf_center));
                    sheet.addCell(new Label(4, 0, "上路时间", wcf_center));
                    sheet.addCell(new Label(5, 0, "自动支付", wcf_center));
                    sheet.addCell(new Label(6, 0, "车架号", wcf_center));
                    sheet.addCell(new Label(7, 0, "发动机号", wcf_center));
                    sheet.addCell(new Label(8, 0, "车型", wcf_center));
                    sheet.addCell(new Label(9, 0, "状态", wcf_center));
                    sheet.addCell(new Label(10, 0, "添加时间", wcf_center));
                    String isauto="";
                    for (int i = 0; i < list.size(); i++) {
                        Map ome = (Map) list.get(i);
                        if(ome.get("is_auto_pay").toString().equals("0")){
                            isauto="否";
                        }else if(ome.get("is_auto_pay").toString().equals("1")){
                            isauto="是";
                        }
                        sheet.addCell(new Label(0, i + 1, ome.get("customer_id") == null ? "" : ome.get("customer_id").toString()));
                        sheet.addCell(new Label(1, i + 1, ome.get("customer_mobile") == null ? "" : ome.get("customer_mobile").toString()));
                        sheet.addCell(new Label(2, i + 1, ome.get("car_number") == null ? "" : ome.get("car_number").toString()));
                        sheet.addCell(new Label(3, i + 1, ome.get("travlled_distance") == null ? "" : ome.get("travlled_distance").toString()));
                        sheet.addCell(new Label(4, i + 1, ome.get("car_use_date") == null ? "" : ome.get("car_use_date").toString()));
                        sheet.addCell(new Label(5, i + 1, isauto));
                        sheet.addCell(new Label(6, i + 1, ome.get("frame_num") == null ? "" : ome.get("frame_num").toString()));
                        sheet.addCell(new Label(7, i + 1, ome.get("engine_num") == null ? "" : ome.get("engine_num").toString()));
                        sheet.addCell(new Label(8, i + 1,(ome.get("car_brand") == null ? "" : ome.get("car_brand").toString())+(ome.get("CAR_SERIES") == null ? "" : ome.get("CAR_SERIES").toString())+(ome.get("DISPLACEMENT") == null ? "" : ome.get("DISPLACEMENT").toString())+(ome.get("intake_name") == null ? "" : ome.get("intake_name").toString())+(ome.get("STYLE_YEAR") == null ? "" : ome.get("STYLE_YEAR").toString()+"年产")));
                        sheet.addCell(new Label(9, i + 1, ome.get("engine_num") == null ? "" : ome.get("engine_num").toString()));
                        sheet.addCell(new Label(10, i + 1, ome.get("engine_num") == null ? "" : ome.get("engine_num").toString()));
                    }
                }
            }, response);
            map3.put("result", "success");

            response.getWriter().print(JSONArray.fromObject(map3).toString());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }






















}
