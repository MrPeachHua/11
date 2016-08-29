package com.boxiang.share.product.carlife.controller;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.carlife.po.CarRent;
import com.boxiang.share.product.carlife.service.CarRentService;
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
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("products/carlife/carRent")
public class CarRentController extends BaseController {

    private static final Logger logger = Logger.getLogger(CarRentController.class);

    @Resource
    private CarRentService carRentService;

    /**
     * 查询
     */
    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        try {
            Page<CarRent> page = new Page<CarRent>();
            PageHelper.initPage(request, page);
            Map<String, Object> map = super.getParamMap(request);
            page.getParams().put("isUsed", Constants.TRUE);
            page = carRentService.queryListPage(page);
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("进入列表页出现异常", e);
        }
        return new ModelAndView("products/carlife/carRent_list");
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
            CarRent carRent = carRentService.queryById(id);
            carRent.setIsUsed(Constants.FALSE);
            carRentService.update(carRent);
            request.setAttribute("info", "删除成功");
        } catch (NumberFormatException nfe) {
            logger.error("id转换类型失败，请检查参数是否正确！id=" + id + ".", nfe);
            throw new NumberFormatException("id转换类型失败，请检查参数是否正确！" + id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除信息出现异常", e);
        }
        return new ModelAndView("products/carlife/carRent_save");
    }

    /**
     * 进入修改页面
     */
    @RequestMapping("{id}/edit.html")
    public ModelAndView edit(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        if (id == 0) {
            throw new NullPointerException("ID不能为空!");
        }
        try {
            CarRent carRent = carRentService.queryById(id);
            request.setAttribute("carRent", carRent);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("信息出现异常", e);
        }
        return new ModelAndView("products/carlife/carRent_edit");
    }

    /**
     * 修改
     */
    @RequestMapping("update.html")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("carRent") CarRent carRent) {
        try {
            UserInfo currUser = (UserInfo) super.getLoginUser(request);
            carRent.setPrice(new BigDecimal(carRent.getPrice().toString()).multiply(new BigDecimal("100")));
            carRent.setModifyDate(new Date());
            carRent.setModified(currUser.getUserNum());
            carRentService.update(carRent);
            request.setAttribute("info", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("修改信息出现异常", e);
        }
        return new ModelAndView("products/carlife/carRent_save");
    }

    /**
     * 进入添加页
     */
    @RequestMapping("add.html")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("products/carlife/carRent_add");
    }

    /**
     * 添加
     */
    @RequestMapping("save.html")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("carRent") CarRent carRent) {
        try {
            UserInfo currUser = (UserInfo) super.getLoginUser(request);
            carRent.setPrice(new BigDecimal(carRent.getPrice().toString()).multiply(new BigDecimal("100")));
            carRent.setCreateor(currUser.getUserNum());
            carRent.setCreateDate(new Date());
            carRent.setIsUsed(Constants.TRUE);
            carRentService.add(carRent);
            request.setAttribute("info", "添加成功");
            return new ModelAndView("products/carlife/carRent_save");
        } catch (Exception e) {
            request.setAttribute("info", "添加失败");
            e.printStackTrace();
            logger.error("添加信息出现异常", e);
            return new ModelAndView("products/carlife/carRent_add");
        }
    }

}
