package com.boxiang.share.product.comment.controller;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.activity.po.CarLifeActivity;
import com.boxiang.share.product.activity.service.CarLifeActivityService;
import com.boxiang.share.utils.DateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("products/carLifeActivity")
public class CarLifeActivityBackController extends BaseController {

    private static final Logger logger = Logger.getLogger(CarLifeActivityBackController.class);

    @Resource
    private String uploadImageType;

    @Resource
    private String uploadImagePath;

    @Resource
    private CarLifeActivityService carLifeActivityService;

    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request) {
        try {
            Page<Object> page = new Page<>();
            PageHelper.initPage(request, page);
            page.setParams(super.getParamMap(request));
            page.setResultList(carLifeActivityService.queryListPageV3(page));
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/activity/car_life_activity_list");
    }

    /**
     * 删除
     */
    @RequestMapping("{id}/delete.html")
    public void delete(@PathVariable int id, HttpServletResponse response) throws IOException {
        String js;
        try {
            CarLifeActivity entity = carLifeActivityService.queryById(id);
            entity.setIsUsed(Constants.FALSE);
            carLifeActivityService.update(entity);
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
    public ModelAndView add() {
        return new ModelAndView("products/activity/car_life_activity_add");
    }

    /**
     * 添加
     */
    @RequestMapping("save.html")
    public String save(HttpServletRequest request,
                       HttpServletResponse response,
                       @ModelAttribute CarLifeActivity carLifeActivity) {
        try {
            if (carLifeActivity.getContentType().equals("1")) {
                this.uploadHTML(request, carLifeActivity);
            }
            if (carLifeActivity.getType().equals("1")) {
                carLifeActivity.setEndDate(DateUtil.getEndDate(carLifeActivity.getEndDate()));
            }
            carLifeActivity.setIsUsed(Constants.TRUE);
            carLifeActivity.setCreateDate(new Date());
            carLifeActivity.setCreateor("admin");
            carLifeActivityService.add(carLifeActivity);
            String js = "<script>alert('添加成功');location.href='list.html';</script>";
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(js);
            return null;
        } catch (Exception e) {
            request.setAttribute("info", "异常，请检查参数是否填写正确");
            logger.error("异常", e);
            return "products/activity/car_life_activity_add";
        }
    }

    /**
     * 进入修改页面
     */
    @RequestMapping("{id}/edit.html")
    public ModelAndView edit(@PathVariable int id, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(1);
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        map.put("params", params);
        List<Object> list = carLifeActivityService.queryListPageV3(map);
        request.setAttribute("entity", list.get(0));
        return new ModelAndView("products/activity/car_life_activity_edit");
    }

    /**
     * 修改
     */
    @RequestMapping("/update.html")
    public String update(@ModelAttribute CarLifeActivity carLifeActivity, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if (carLifeActivity.getContentType().equals("1")) {
                String oldHtml = carLifeActivityService.queryById(carLifeActivity.getId()).getHtml();
                if (oldHtml == null || !oldHtml.equals(carLifeActivity.getHtml())) {
                    this.uploadHTML(request, carLifeActivity);
                }
            }
            if (carLifeActivity.getType().equals("1")) {
                carLifeActivity.setEndDate(DateUtil.getEndDate(carLifeActivity.getEndDate()));
            }
            carLifeActivity.setIsUsed(Constants.TRUE);
            carLifeActivityService.update(carLifeActivity);
            return "redirect:list.html";
        } catch (Exception e) {
            logger.error("异常", e);
            String js = "<script>alert('异常');location.href='" + carLifeActivity.getId() + "/edit.html';</script>";
            response.getWriter().print(js);
            return null;
        }
    }

    /**
     * 上传HTML文件
     */
    private void uploadHTML(HttpServletRequest request, CarLifeActivity carLifeActivity) throws IOException {
        String dict = "carLifeActivityHtml/" + DateUtil.getCurrDate(DateUtil.DATE_FORMAT) + "/";
        String path;
        if (Constants.TRUE.equals(uploadImageType)) {
            path = "/usr/local/nginx/html/" + uploadImagePath + dict;
        } else {
            path = request.getSession().getServletContext().getRealPath("/") + (uploadImagePath + dict);
        }
        String fileName = DateUtil.getCurrDate("YYYYMMddHHmmss") + "_" + (int) (Math.random() * 1000000) + "_" + carLifeActivity.getType() + ".html";
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0\"/>");
        sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>");
        sb.append("<style>" +
                "body{word-break: break-all}" +
                "img{width:320px}");
        sb.append("</style>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append(carLifeActivity.getHtml());
        sb.append("</body>");
        sb.append("</html>");
        FileUtils.write(new File(path + fileName), sb);
        String realPath = uploadImagePath + dict + fileName;
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
        carLifeActivity.setUrl(basePath + realPath);
    }

}