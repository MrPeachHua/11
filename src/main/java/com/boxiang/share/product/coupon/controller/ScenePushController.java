package com.boxiang.share.product.coupon.controller;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.coupon.po.CouponRule;
import com.boxiang.share.product.coupon.po.CouponTemplate;
import com.boxiang.share.product.coupon.service.CouponRuleService;
import com.boxiang.share.product.coupon.service.CouponTemplateService;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.system.po.Dictionary;
import com.boxiang.share.system.service.DictionaryService;
import com.boxiang.share.utils.ShangAnMessageType;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@RequestMapping("/products/scenePush")
public class ScenePushController {

    private static final Logger log = Logger.getLogger(ScenePushController.class);

    @Resource
    private CouponRuleService couponRuleService;

    @Resource
    private CouponTemplateService couponTemplateService;

    @Resource
    private ParkingService parkingService;

    @Resource
    private DictionaryService dictionaryService;

    private List<Dictionary> getCouponKind() {
        return dictionaryService.getDictListByDictCode("coupon_kind");
    }

    /**
     * 规则列表
     */
    @RequestMapping("/list.html")
    public ModelAndView list(HttpServletRequest request) {
        try {
            /*查询列表*/
            Page<CouponRule> page = new Page<>();
            PageHelper.initPage(request, page);
            page.getParams().put("isUsed", Constants.TRUE);
            page = couponRuleService.queryListPage(page);
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            log.error("异常", e);
        }
        return new ModelAndView("products/coupon/scene_push_list");
    }

    /**
     * 删除
     */
    @RequestMapping("{id}/del.html")
    public void del(@PathVariable int id, HttpServletResponse response) throws IOException {
        String js;
        try {
            CouponRule rule = couponRuleService.queryById(id);
            rule.setIsUsed(Constants.FALSE);
            couponRuleService.update(rule);
            js = "<script>alert('删除成功');location.href='../list.html';</script>";
        } catch (Exception e) {
            log.error("异常", e);
            js = "<script>alert('异常');location.href='../list.html';</script>";
        }
        response.getWriter().print(js);
    }

    /**
     * 前往新增页面
     */
    @RequestMapping("/add.html")
    public ModelAndView add(HttpServletRequest request) {
        request.setAttribute("dict", getCouponKind());
        request.setAttribute("orderTypeDict", dictionaryService.getDictListByDictCode("order_type"));
        return new ModelAndView("products/coupon/scene_push_add");
    }

    /**
     * 添加
     */
    @RequestMapping("save.html")
    public String save(HttpServletRequest request,
                       HttpServletResponse response,
                       @ModelAttribute("couponRule") CouponRule couponRule) {
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(couponRule.getEndDate());
            calendar.add(Calendar.HOUR, 23);
            calendar.add(Calendar.MINUTE, 59);
            calendar.add(Calendar.SECOND, 59);
            couponRule.setEndDate(calendar.getTime());
            couponRuleService.addWithCouponTemplate(couponRule);
            String js = "<script>alert('添加成功');location.href='list.html';</script>";
            response.getWriter().print(js);
            return null;
        } catch (Exception e) {
            request.setAttribute("info", "异常，请检查参数是否填写完整");
            log.error("异常", e);
            return "products/coupon/scene_push_add";
        }
    }

    /**
     * 进入修改页面
     */
    @RequestMapping("{id}/edit.html")
    public ModelAndView edit(@PathVariable int id, HttpServletRequest request) {
        try {
            CouponRule couponRule = couponRuleService.queryById(id);
            CouponTemplate couponTemplate = new CouponTemplate();
            couponTemplate.setType("1"); // 类型，1：车辆入场出场时送的优惠券 2：兑换码搞出来的优惠券
            couponTemplate.setTypeId(couponRule.getId()); // 外键，TYPE为1时，使用t_coupon_rule表的ID；TYPE为2时，使用t_redeem_rule表的ID
            couponTemplate.setIsUsed(Constants.TRUE);
            couponRule.setCouponTemplateList(couponTemplateService.selectList(couponTemplate));
            request.setAttribute("entity", couponRule);
            request.setAttribute("dict", getCouponKind());
            request.setAttribute("orderTypeDict", dictionaryService.getDictListByDictCode("order_type"));
        } catch (Exception e) {
            log.error("异常", e);
        }
        return new ModelAndView("products/coupon/scene_push_edit");
    }

    /**
     * 修改
     */
    @RequestMapping("/update.html")
    public String update(@ModelAttribute("couponRule") CouponRule couponRule, HttpServletResponse response) throws IOException {
        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(couponRule.getEndDate());
            calendar.add(Calendar.HOUR, 23);
            calendar.add(Calendar.MINUTE, 59);
            calendar.add(Calendar.SECOND, 59);
            couponRule.setEndDate(calendar.getTime());
            couponRuleService.updateWithCouponTemplate(couponRule);
            return "redirect:list.html";
        } catch (Exception e) {
            log.error("异常", e);
            String js = "<script>alert('异常');location.href='" + couponRule.getId() + "/edit.html';</script>";
            response.getWriter().print(js);
            return null;
        }
    }

    /**
     * 查询有蓝卡云车场id的车场
     */
    @RequestMapping("/blueParkingList.html")
    public void blueParkingList(HttpServletResponse response) throws IOException {
        String msg;
        try {
            List<Parking> list = parkingService.queryBlueParkingList();
            msg = ShangAnMessageType.toShangAnJson("0", "success", "data", list);
        } catch (Exception e) {
            log.error("异常", e);
            msg = ShangAnMessageType.operateToJson("1", "error");
        }
        response.getWriter().print(msg);
    }

}