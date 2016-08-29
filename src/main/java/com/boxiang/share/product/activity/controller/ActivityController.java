package com.boxiang.share.product.activity.controller;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.activity.po.Activity;
import com.boxiang.share.product.activity.service.ActivityService;
import com.boxiang.share.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("products/activity")
public class ActivityController extends BaseController {

    private static final Logger logger = Logger.getLogger(ActivityController.class);

    @Resource
    private ActivityService activityService;

    /**
     * 查询列表
     */
    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request) {
        try {
            Page<Activity> page = new Page<>();
            PageHelper.initPage(request, page);
            page.setParams(super.getParamMap(request));
            page.setResultList(activityService.queryListPageV2(page));
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/activity/activity_list");
    }

    /**
     * 删除
     */
    @RequestMapping("{id}/delete.html")
    public void delete(@PathVariable int id, HttpServletResponse response) throws IOException {
        String js;
        try {
            // 开始时间大于当前时间
            Activity activity = activityService.queryById(id);
            if (activity.getStartDate().compareTo(new Date()) > 0) {
                activityService.delete(id);
                js = "<script>alert('删除成功');location.href='../list.html';</script>";
            } else {
                js = "<script>alert('只有开始时间大于当前时间才能删除');location.href='../list.html';</script>";
            }
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
        return new ModelAndView("products/activity/activity_add");
    }

    /**
     * 添加
     */
    @RequestMapping("save.html")
    public String save(HttpServletRequest request,
                       HttpServletResponse response,
                       @ModelAttribute Activity activity) {
        try {
            activity.setEndDate(DateUtil.getEndDate(activity.getEndDate()));
            activity.setIsUsed(Constants.TRUE);
            if (activityService.selectList(activity).size() > 0) {
                request.setAttribute("info", "存在相同的记录,无法新增");
                return "products/activity/activity_add";
            }
            activity.setCreateDate(new Date());
            activity.setCreateor("admin");
            activityService.add(activity);
            String js = "<script>alert('添加成功');location.href='list.html';</script>";
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(js);
            return null;
        } catch (Exception e) {
            request.setAttribute("info", "异常，请检查参数是否填写正确");
            logger.error("异常", e);
            return "products/activity/activity_add";
        }
    }

    /**
     * 进入修改页面
     */
    @RequestMapping("{id}/edit.html")
    public ModelAndView edit(@PathVariable int id, HttpServletRequest request) {
        request.setAttribute("entity", activityService.queryById(id));
        return new ModelAndView("products/activity/activity_edit");
    }

    /**
     * 修改
     */
    @RequestMapping("/update.html")
    public String update(@ModelAttribute Activity activity, HttpServletResponse response) throws IOException {
        try {
            activity.setEndDate(DateUtil.getEndDate(activity.getEndDate()));
            activity.setIsUsed(Constants.TRUE);
            activityService.update(activity);
            return "redirect:list.html";
        } catch (Exception e) {
            logger.error("异常", e);
            String js = "<script>alert('异常');location.href='" + activity.getId() + "/edit.html';</script>";
            response.getWriter().print(js);
            return null;
        }
    }

}