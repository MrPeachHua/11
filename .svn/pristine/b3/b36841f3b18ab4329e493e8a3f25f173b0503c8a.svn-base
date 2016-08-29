package com.boxiang.share.system.controller;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.system.po.AppVersion;
import com.boxiang.share.system.service.AppVersionService;
import com.boxiang.share.system.service.DictionaryService;
import com.boxiang.share.user.po.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("system/appversion")
public class AppVersionController extends BaseController {

    private static final Logger logger = Logger.getLogger(DictionaryController.class);

    @Resource
    private AppVersionService appVersionService;

    @Resource
    private DictionaryService dictionaryService;

    /**
     * 查询
     */
    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        try {
            Page<AppVersion> page = new Page<AppVersion>();
            PageHelper.initPage(request, page);
            Map<String, Object> map = super.getParamMap(request);
            page.getParams().put("platformCode", map.get("platformCode"));
            page.getParams().put("versionChannel", map.get("versionChannel"));
            page.getParams().put("isUsed", Constants.TRUE);
            page.getParams().put("startDate", map.get("startDate"));
            page.getParams().put("endDate", map.get("endDate"));
            page = appVersionService.queryListPage(page);
            PageHelper.setPageModel(request, page);
            request.setAttribute("platformCodeList", dictionaryService.getDictListByDictCode("platform_code"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("进入列表页出现异常", e);
        }
        return new ModelAndView("system/appversion_list");
    }

    /**
     * 删除
     */
    @RequestMapping("{id}/del.html")
    public ModelAndView del(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        if (id == 0) {
            throw new NullPointerException("ID不能为空!");
        }
        try {
            AppVersion appVersion = appVersionService.queryById(id);
            appVersion.setIsUsed(Constants.FALSE);
            appVersionService.update(appVersion);
            request.setAttribute("info", "删除成功");
        } catch (NumberFormatException nfe) {
            logger.error("id转换类型失败，请检查参数是否正确！id=" + id + ".", nfe);
            throw new NumberFormatException("id转换类型失败，请检查参数是否正确！" + id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除信息出现异常", e);
        }
        return new ModelAndView("system/appversion_save", null);
    }

    /**
     * 进入修改页面
     */
    @RequestMapping("{id}/edit.html")
    public ModelAndView edit(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        if (id == 0) {
            throw new NullPointerException("ID不能为空!");
        }
//        Map<String, Object> map = super.getParamMap(request);
        try {
            AppVersion appVersion = appVersionService.queryById(id);
            request.setAttribute("appVersion", appVersion);
            request.setAttribute("platformCodeList", dictionaryService.getDictListByDictCode("platform_code"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("信息出现异常", e);
        }
        return new ModelAndView("system/appversion_edit");
    }

    /**
     * 修改
     */
    @RequestMapping("update.html")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("appVersion") AppVersion appVersion) {
        try {
            appVersionService.update(appVersion);
            request.setAttribute("info", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("修改信息出现异常", e);
        }
        return new ModelAndView("system/appversion_save");
    }

    /**
     * 进入添加页
     */
    @RequestMapping("add.html")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("platformCodeList", dictionaryService.getDictListByDictCode("platform_code"));
        return new ModelAndView("system/appversion_add");
    }

    /**
     * 添加
     */
    @RequestMapping("save.html")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("appVersion") AppVersion appVersion) {
        try {
            UserInfo currUser = (UserInfo) super.getLoginUser(request);
            appVersion.setCreateor(currUser.getUserNum());
            appVersion.setCreateDate(new Date());
            appVersion.setIsUsed(Constants.TRUE);
            appVersionService.add(appVersion);
            request.setAttribute("info", "添加成功");
            return new ModelAndView("system/appversion_save");
        } catch (Exception e) {
            request.setAttribute("info", "添加失败");
            e.printStackTrace();
            logger.error("添加信息出现异常", e);
            return new ModelAndView("system/appversion_add");
        }
    }

}
