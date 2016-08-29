package com.boxiang.share.product.activity.controller;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.activity.po.Activity;
import com.boxiang.share.product.activity.po.ActivityQrCode;
import com.boxiang.share.product.activity.service.ActivityQrCodeService;
import com.boxiang.share.product.activity.service.ActivityService;
import com.boxiang.share.product.parker.po.Parker;
import com.boxiang.share.product.parker.service.ParkerService;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.utils.QRCodeUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("products/activityQrCode")
public class ActivityQrCodeController extends BaseController {

    private static final Logger logger = Logger.getLogger(ActivityQrCodeController.class);

    @Resource
    private ActivityQrCodeService activityQrCodeService;

    @Resource
    private ParkingService parkingService;

    @Resource
    private ActivityService activityService;

    @Resource
    private ParkerService parkerService;

    /**
     * 查询列表
     */
    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request) {
        try {
            Page<Map<String, Object>> page = new Page<>();
            PageHelper.initPage(request, page);
            page.setParams(super.getParamMap(request));
            page.setResultList(activityQrCodeService.queryListPageV2(page));
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/activity/activity_qr_code_list");
    }

    /**
     * 删除
     */
    @RequestMapping("{id}/delete.html")
    public void delete(@PathVariable int id, HttpServletResponse response) throws IOException {
        String js;
        try {
            ActivityQrCode entity = activityQrCodeService.queryById(id);
            entity.setIsUsed(Constants.FALSE);
            activityQrCodeService.update(entity);
            js = "<script>alert('删除成功');location.href='../list.html';</script>";
        } catch (Exception e) {
            logger.error("异常", e);
            js = "<script>alert('异常');location.href='../list.html';</script>";
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(js);
    }

    /**
     * 前往新增页面
     */
    @RequestMapping("/add.html")
    public ModelAndView add(HttpServletRequest request) {
        Parking parking = new Parking();
        parking.setIsDirect(2);
        parking.setIsCooperate(2);
        List<Parking> parkingList = parkingService.selectList(parking);
        List<Parker> parkerList = parkerService.selectList(null);
        Map<String, Object> params = new HashMap<>(1);
        params.put("state", "1");
        Map<String, Object> map = new HashMap<>(1);
        map.put("params", params);
        List<Activity> activityList = activityService.queryListPageV2(map);
        request.setAttribute("parkingList", parkingList);
        request.setAttribute("parkerList", parkerList);
        request.setAttribute("activityList", activityList);
        return new ModelAndView("products/activity/activity_qr_code_add");
    }

    /**
     * 批量添加
     */
    @RequestMapping("batchSave.html")
    public String batchSave(@RequestParam String type,
                            String activityId,
                            @RequestParam String parkingId,
                            @RequestParam String userId,
                            @RequestParam String isDownload,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        String js;
        try {
            if (isDownload.equals(Constants.TRUE)) {
                String downloadUrl = activityQrCodeService.batchSaveAndDownload(type, activityId, parkingId, userId, request);
                request.setAttribute("url", downloadUrl);
                return "products/activity/activity_qr_code_download";
            } else {
                activityQrCodeService.batchSave(type, activityId, parkingId, userId);
                js = "<script>alert('添加成功');location.href='list.html';</script>";
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print(js);
            }
        } catch (Exception e) {
            logger.error("异常", e);
            js = "<script>alert('异常');history.back();</script>";
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(js);
        }
        return null;
    }

    /**
     * 前往上传二维码页面
     */
    @RequestMapping("/upload.html")
    public ModelAndView upload() {
        return new ModelAndView("products/activity/activity_qr_code_upload");
    }

    /**
     * 添加
     */
    @RequestMapping("save.html")
    public void save(HttpServletRequest request,
                     HttpServletResponse response,
                     @ModelAttribute ActivityQrCode activityQrCode) throws IOException {
        String js;
        try {
            activityQrCodeService.setBaseProperty(activityQrCode);
            activityQrCodeService.add(activityQrCode);
            js = "<script>alert('添加成功');location.href='list.html';</script>";
        } catch (Exception e) {
            logger.error("异常", e);
            js = "<script>alert('异常');history.back();</script>";
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(js);
    }

    /**
     * 进入修改页面
     */
    @RequestMapping("{id}/edit.html")
    public ModelAndView edit(@PathVariable String id, HttpServletRequest request) {
        request.setAttribute("entity", activityQrCodeService.queryByIdV2(id).get(0));
        return new ModelAndView("products/activity/activity_qr_code_edit");
    }

    /**
     * 修改
     */
    @RequestMapping("/update.html")
    public String update(@ModelAttribute ActivityQrCode activityQrCode, HttpServletResponse response) throws IOException {
        try {
            activityQrCode.setIsUsed(Constants.TRUE);
            activityQrCodeService.update(activityQrCode);
            return "redirect:list.html";
        } catch (Exception e) {
            logger.error("异常", e);
            String js = "<script>alert('异常');location.href='" + activityQrCode.getId() + "/edit.html';</script>";
            response.getWriter().print(js);
            return null;
        }
    }

    /**
     * 下载二维码
     */
    @RequestMapping("/download.html")
    public void download(@RequestParam String id,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        try {
            Map<String, Object> entity = activityQrCodeService.queryByIdV2(id).get(0);
            String url = activityQrCodeService.getEncodeUrl(entity, request);
            String fileName = activityQrCodeService.getFileName(entity);
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
            response.setContentType("image/jpeg");
            QRCodeUtil.encode(url, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            logger.error("异常", e);
        }
    }

}