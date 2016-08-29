package com.boxiang.share.product.customer.controller;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.coupon.po.RedeemRecord;
import com.boxiang.share.product.coupon.service.RedeemRecordService;
import com.boxiang.share.product.order.controller.ExportOrderExcel;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.product.qrcode.service.CarlovQrcodeService;
import com.boxiang.share.user.service.RegionUserService;
import com.boxiang.share.utils.DateUtil;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
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

@Controller
@RequestMapping("customer/member_recommend")
public class MemberRecommendController extends BaseController {
    private static final Logger logger = Logger.getLogger(MemberRecommendController.class);

    @Resource
    private RegionUserService regionUserService;
    @Resource
    private ParkingService parkingService;

    @Resource
    private CarlovQrcodeService carlovQrcodeService;

    @Resource
    private RedeemRecordService redeemRecordService;
    /**
     * 区域负责人列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        try {
            Page<Object> page = new Page<>();
            PageHelper.initPage(request, page);
            page.setParams(super.getParamMap(request));
            page.setResultList(redeemRecordService.queryListPageBack(page));
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询异常",e);
        }
        return new ModelAndView("customer/member_recommend_list");
    }

    /**
     * 导出excel文档
     */
    @RequestMapping("excelList.html")
    public void excelRecharge(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            Map<String, Object> map = new HashMap<>();
            map.put("params", super.getParamMap(request));
            final List<Object> list = redeemRecordService.queryListPageBack(map);
            ExportOrderExcel.exportExcel(new ExportOrderExcel.ExcelObject() {
                @Override
                public void dataSource(WritableSheet sheet, WritableCellFormat wcf_center, WritableCellFormat wcf_left) throws Exception {
                    sheet.addCell(new Label(0, 0, "类型", wcf_center));
                    sheet.addCell(new Label(1, 0, "被领取者的用户ID", wcf_center));
                    sheet.addCell(new Label(2, 0, "领取者的用户ID", wcf_center));
                    sheet.addCell(new Label(3, 0, "是否可用", wcf_center));
                    sheet.addCell(new Label(4, 0, "创建人", wcf_center));
                    sheet.addCell(new Label(5, 0, "创建时间", wcf_center));
                    for (int i = 0; i < list.size(); i++) {
                        RedeemRecord ome = (RedeemRecord) list.get(i);
                        String type = "";
                        String isUsed = "";
                        String creator = "";
                        String creatDate = "";
                        if("1".equals(ome.getType())){
                            type = "新注册用户使用兑换码获取的优惠券";
                        }else{
                            type = "老用户兑换码被兑换一定次数后可领取的优惠券";
                        }
                        if("0".equals(ome.getIsUsed())){
                            isUsed = "否";
                        }else{
                            isUsed = "是";
                        }
                        if(null!=ome.getCreateDate()){
                           creatDate = DateUtil.date2str(ome.getCreateDate(),DateUtil.DATETIME_FORMAT);
                        }
                        sheet.addCell(new Label(0, i + 1, type));
                        sheet.addCell(new Label(1, i + 1, ome.getOldCustomerId()));
                        sheet.addCell(new Label(2, i + 1, ome.getNewCustomerId()));
                        sheet.addCell(new Label(3, i + 1, isUsed));
                        sheet.addCell(new Label(4, i + 1, ome.getCreateor()));
                        sheet.addCell(new Label(5, i + 1, creatDate));
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
//    /**
//     * 删除
//     */
//    @RequestMapping("{parkingId}/delete.html")
//    public void delete(@PathVariable String parkingId, HttpServletResponse response) throws IOException {
//        String js;
//        try {
//            carlovQrcodeService.delete(parkingId);
//            js = "<script>alert('删除成功');location.href='../list.html';</script>";
//        } catch (Exception e) {
//            logger.error("异常", e);
//            js = "<script>alert('异常');location.href='../list.html';</script>";
//        }
//        response.setContentType("text/html;charset=UTF-8");
//        response.getWriter().print(js);
//    }
//
//    /**
//     * 前往新增页面
//     */
//    @RequestMapping("/add.html")
//    public ModelAndView add() {
//        return new ModelAndView("products/qrcode/qrcode_add");
//    }
//
//    /**
//     * 添加
//     */
//    @RequestMapping("save.html")
//    public String save(HttpServletRequest request,
//                       HttpServletResponse response,
//                       @ModelAttribute CarlovQrcode carlovQrcode) {
//        try {
//            carlovQrcode.setCreateDate(new Date());
//            carlovQrcode.setModifyDate(new Date());
//            UserInfo userInfo = (UserInfo) super.getLoginUser(request);
//            carlovQrcode.setCreateor(userInfo.getUserNum());
//            carlovQrcode.setModified(userInfo.getUserNum());
//            carlovQrcodeService.add(carlovQrcode);
//            String js = "<script>alert('添加成功');location.href='list.html';</script>";
//            response.setContentType("text/html;charset=UTF-8");
//            response.getWriter().print(js);
//            return null;
//        } catch (Exception e) {
//            request.setAttribute("info", "异常，请检查车场是否重复");
//            logger.error("异常", e);
//            return "products/qrcode/qrcode_add";
//        }
//    }
//
//    /**
//     * 进入修改页面
//     */
//    @RequestMapping("{parkingId}/edit.html")
//    public ModelAndView edit(@PathVariable String parkingId, HttpServletRequest request) {
//        CarlovQrcode params = new CarlovQrcode();
//        params.setParkingId(parkingId);
//        List<CarlovQrcode> list = carlovQrcodeService.selectList(params);
//        if(list!=null&&list.size()>0){
//            CarlovQrcode carlovQrcode=list.get(0);
//            Parking parking=parkingService.queryById(carlovQrcode.getParkingId());
//            request.setAttribute("parkingName",parking.getParkingName());
//            request.setAttribute("entity",carlovQrcode);
//        }
//
//        return new ModelAndView("products/qrcode/qrcode_edit");
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update.html")
//    public String update(@ModelAttribute CarlovQrcode carlovQrcode,HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//            //regionUser.setIsUsed(Constants.TRUE);
//            CarlovQrcode params = carlovQrcodeService.queryById(carlovQrcode.getParkingId());
//            carlovQrcode.setModifyDate(new Date());
//            if(null!=params){
//                carlovQrcode.setCreateDate(params.getCreateDate());
//            }
//            UserInfo userInfo = (UserInfo) super.getLoginUser(request);
//            carlovQrcode.setCreateor(params.getCreateor());
//            carlovQrcode.setModified(userInfo.getUserNum());
//            carlovQrcodeService.update(carlovQrcode);
//            return "redirect:list.html";
//        } catch (Exception e) {
//            logger.error("异常", e);
//            String js = "<script>alert('异常');location.href='" + carlovQrcode.getParkingId() + "/edit.html';</script>";
//            response.getWriter().print(js);
//            return null;
//        }
//    }
}
