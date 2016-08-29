package com.boxiang.share.product.coupon.controller;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.coupon.po.CouponTemplate;
import com.boxiang.share.product.coupon.po.RedeemRule;
import com.boxiang.share.product.coupon.service.CouponTemplateService;
import com.boxiang.share.product.coupon.service.RedeemRuleService;
import com.boxiang.share.system.service.DictionaryService;
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

@Controller
@RequestMapping("/products/redeemRule")
public class RedeemRuleController extends BaseController {

    private static final Logger log = Logger.getLogger(RedeemRuleController.class);

    @Resource
    private RedeemRuleService redeemRuleService;

    @Resource
    private CouponTemplateService couponTemplateService;

    @Resource
    private DictionaryService dictionaryService;

    /**
     * 兑换规则列表
     */
    @RequestMapping("/list.html")
    public ModelAndView list(HttpServletRequest request) {
        try {
            /*查询列表*/
            Page<RedeemRule> page = new Page<>();
            PageHelper.initPage(request, page);
            page.getParams().put("id", request.getParameter("id"));
            page.getParams().put("type", request.getParameter("type"));
            page.getParams().put("isUsed", Constants.TRUE);
            page = redeemRuleService.queryListPage(page);
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("异常", e);
        }
        return new ModelAndView("products/coupon/redeem_rule_list");
    }

    /**
     * 前往新增页面
     */
    @RequestMapping("/add.html")
    public ModelAndView add(HttpServletRequest request) {
        request.setAttribute("dict", dictionaryService.getDictListByDictCode("coupon_kind"));
        return new ModelAndView("products/coupon/redeem_rule_add");
    }

    /**
     * 添加
     */
    @RequestMapping("save.html")
    public String save(HttpServletRequest request,
                       HttpServletResponse response,
                       @ModelAttribute("redeemRule") RedeemRule redeemRule) {
        try {
            redeemRuleService.addWithCouponTemplate(redeemRule);
            String js = "<script>alert('添加成功');location.href='list.html';</script>";
            response.getWriter().print(js);
            return null;
        } catch (Exception e) {
            request.setAttribute("info", "异常，请检查参数是否填写完整");
            log.error("异常", e);
            return "products/coupon/redeem_rule_add";
        }
    }

    /**
     * 删除
     */
    @RequestMapping("{id}/del.html")
    public void del(@PathVariable int id, HttpServletResponse response) throws IOException {
        String js;
        try {
            RedeemRule redeemRule = redeemRuleService.queryById(id);
            redeemRule.setIsUsed(Constants.FALSE);
            redeemRuleService.update(redeemRule);
            js = "<script>alert('删除成功');location.href='../list.html';</script>";
        } catch (Exception e) {
            log.error("异常", e);
            js = "<script>alert('异常');location.href='../list.html';</script>";
        }
        response.getWriter().print(js);
    }

    /**
     * 进入修改页面
     */
    @RequestMapping("{id}/edit.html")
    public ModelAndView edit(@PathVariable int id, HttpServletRequest request) {
        try {
            RedeemRule redeemRule = redeemRuleService.queryById(id);
            CouponTemplate couponTemplate = new CouponTemplate();
            couponTemplate.setType("2"); // 类型，1：车辆入场出场时送的优惠券 2：兑换码搞出来的优惠券
            couponTemplate.setTypeId(redeemRule.getId()); // 外键，TYPE为1时，使用t_coupon_rule表的ID；TYPE为2时，使用t_redeem_rule表的ID
            couponTemplate.setIsUsed(Constants.TRUE);
            redeemRule.setCouponTemplateList(couponTemplateService.selectList(couponTemplate));
            request.setAttribute("entity", redeemRule);
            request.setAttribute("dict", dictionaryService.getDictListByDictCode("coupon_kind"));
        } catch (Exception e) {
            log.error("异常", e);
        }
        return new ModelAndView("products/coupon/redeem_rule_edit");
    }

    /**
     * 删除优惠券模板
     */
    @RequestMapping("{id}/delTemplate.html")
    public void delTemplate(@PathVariable int id, HttpServletResponse response) throws IOException {
        String result;
        try {
            CouponTemplate couponTemplate = couponTemplateService.queryById(id);
            couponTemplate.setIsUsed(Constants.FALSE);
            couponTemplateService.update(couponTemplate);
            result = "0";
        } catch (Exception e) {
            log.error("异常", e);
            result = "1";
        }
        response.getWriter().print(result);
    }

    /**
     * 修改
     */
    @RequestMapping("/update.html")
    public String update(@ModelAttribute("redeemRule") RedeemRule redeemRule, HttpServletResponse response) throws IOException {
        try {
            redeemRuleService.updateWithCouponTemplate(redeemRule);
            return "redirect:list.html";
        } catch (Exception e) {
            log.error("异常", e);
            String js = "<script>alert('异常');location.href='" + redeemRule.getId() + "/edit.html';</script>";
            response.getWriter().print(js);
            return null;
        }
    }

}
