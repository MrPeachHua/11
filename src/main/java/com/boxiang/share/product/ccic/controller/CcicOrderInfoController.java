package com.boxiang.share.product.ccic.controller;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.ccic.po.CcicCustomer;
import com.boxiang.share.product.ccic.po.CcicInsure;
import com.boxiang.share.product.ccic.po.CcicOrderInfo;
import com.boxiang.share.product.ccic.service.CcicCustomerService;
import com.boxiang.share.product.ccic.service.CcicInsureService;
import com.boxiang.share.product.ccic.service.CcicOrderInfoService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Created by kaiser on 2016/8/12.


@Controller
@RequestMapping("products/ccic")
public class CcicOrderInfoController extends BaseController {

    private static final Logger logger = Logger.getLogger(CcicOrderInfoController.class);

    @Resource
    private CcicOrderInfoService ccicOrderInfoService;

    @Resource
    private CcicCustomerService ccicCustomerService;

    @Resource
    private CcicInsureService ccicInsureService;

    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request) {
        try {
            Page<CcicOrderInfo> page = new Page<>();
            PageHelper.initPage(request, page);
            page.setParams(super.getParamMap(request));
            page=ccicOrderInfoService.queryListPage(page);
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("customer/order_ccic_list");
    }
    @RequestMapping("view.html")
    public ModelAndView view(HttpServletRequest request) {
        Map<String,Object> map=new HashMap<>();
        try {
            String id=request.getParameter("id");
            CcicOrderInfo ccicOrderInfo= ccicOrderInfoService.queryById(Integer.parseInt(id));
            CcicCustomer ccicCustomer=new CcicCustomer();
            ccicCustomer.setUtmsn(ccicOrderInfo.getUtmsn());
           List<CcicCustomer> listCcicCustomer= ccicCustomerService.selectList(ccicCustomer);
            CcicInsure ccicInsure=new CcicInsure();
            ccicInsure.setInsuranceApplicantNo(ccicOrderInfo.getInsuranceApplicantNo());
            List<CcicInsure> listCcicInsure =ccicInsureService.selectList(ccicInsure);
            map.put("ccicOrderInfo",ccicOrderInfo);
            if(listCcicCustomer!=null&&listCcicCustomer.size()>0){
                map.put("ccicCustomer",listCcicCustomer.get(0));
            }
            if(listCcicInsure!=null&&listCcicInsure.size()>0){
                map.put("ccicInsure",listCcicInsure);
            }

        } catch (Exception e) {
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("customer/order_ccic_view",map);
    }
}
