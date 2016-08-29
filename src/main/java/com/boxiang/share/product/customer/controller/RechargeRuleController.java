package com.boxiang.share.product.customer.controller;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.customer.po.RechargeRule;
import com.boxiang.share.product.customer.po.RechargeRuleGiftAmount;
import com.boxiang.share.product.customer.po.Rule;
import com.boxiang.share.product.customer.service.RechargeRuleGiftAmountService;
import com.boxiang.share.product.customer.service.RechargeRuleService;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.utils.DateUtil;
import com.ibm.icu.text.SimpleDateFormat;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("products/rule")
public class RechargeRuleController  extends BaseController {
    private static final Logger logger = Logger.getLogger(RechargeRuleController.class);
    @Resource
    private RechargeRuleService rechargeRuleService;
    @Resource
    private RechargeRuleGiftAmountService rechargeRuleGiftAmountService;
    //列表
    @RequestMapping("list.html")
    public ModelAndView rechargeRuleList(HttpServletRequest request, HttpServletResponse response) {
        try {
            Page<RechargeRule> page = new Page<RechargeRule>();
            PageHelper.initPage(request, page);
            page.getParams().put("isUsed", Constants.TRUE);
            String beginDate= request.getParameter("beginDate");
            String endDate= request.getParameter("endDate");

            page.getParams().put("beginDate", beginDate);
            page.getParams().put("endDate", endDate);
            page = rechargeRuleService.queryListPage(page);
            PageHelper.setPageModel(request, page);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("进入列表页出现异常",e);
        }
        return new ModelAndView("products/rule/rechargerule_list");
    }
    //主表删除
    @RequestMapping("/{id}/del.html")
    public ModelAndView delRecharge(@PathVariable String id,HttpServletRequest request,HttpServletResponse response){
		try {
			RechargeRule rechargeRule = new RechargeRule();
			rechargeRule.setIsUsed(Constants.FALSE);
			rechargeRule.setId(Integer.parseInt(id));
			rechargeRuleService.deleteRule(rechargeRule);
			request.setAttribute("info", "删除成功");
		} catch (NumberFormatException nfe) {
			logger.error("删除优惠券出现异常",nfe);
		} catch (Exception e) {
			logger.error("删除优惠券出现异常",e);
		}
		return new ModelAndView("products/rule/save",null);
    }
  //子表删除
    @RequestMapping("del1.html")
    public void delRechargeGift(HttpServletRequest request,HttpServletResponse response){
    	String msg = "删除失败";
		try {
			String id = request.getParameter("id");
			RechargeRuleGiftAmount rechargeRuleGiftAmount = new RechargeRuleGiftAmount();
			rechargeRuleGiftAmount.setIsUsed(Constants.FALSE);
			rechargeRuleGiftAmount.setId(Integer.parseInt(id));
			rechargeRuleGiftAmountService.deleteRuleGift(rechargeRuleGiftAmount);
//			request.setAttribute("info", "删除成功");
			msg = "删除成功";
		}catch (Exception e) {
			logger.error("",e);
		}
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    //子表列表
    @RequestMapping("rechargeRuleGiftAumountList.html")
    public ModelAndView rechargeRuleGiftAumountList(HttpServletRequest request, HttpServletResponse response) {
        try {
            Page<RechargeRuleGiftAmount> page = new Page<RechargeRuleGiftAmount>();
            PageHelper.initPage(request, page);
            page.getParams().put("isUsed", Constants.TRUE);
            String id= request.getParameter("id");
            String beginDate= request.getParameter("beginDate");
            String endDate= request.getParameter("endDate");
            page.getParams().put("beginDate", beginDate);
            page.getParams().put("endDate", endDate);
            page.getParams().put("ruleId", id);
            page = rechargeRuleGiftAmountService.queryListPage(page);
            PageHelper.setPageModel(request, page);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("进入列表页出现异常",e);
        }
        return new ModelAndView("products/rule/rechargerulegiftaumount_list");
    }
    @RequestMapping("addRule.html")
    public ModelAndView addRule(HttpServletRequest request , HttpServletResponse response){
		return new ModelAndView("products/rule/rule_add");
	}
    @RequestMapping("addRule1.html")
    public ModelAndView addRule1(HttpServletRequest request , HttpServletResponse response){
    	String ruleId= request.getParameter("ruleId");
    	request.setAttribute("ruleId",ruleId);
		return new ModelAndView("products/rule/ruleAmount_add");
	}
    @RequestMapping("rule_add.html")
    public ModelAndView ruleAdd(HttpServletRequest request , HttpServletResponse response){
    	String beginDate = request.getParameter("beginDate");
    	String imagePath = request.getParameter("imagePath");
    	String endDate = request.getParameter("endDate");
    	RechargeRule rg = new RechargeRule();
    	rg.setIsUsed(Constants.TRUE);
    	try {
			rg.setBeginDate(DateUtil.str2date(beginDate, "yyyy-MM-dd"));
			rg.setEndDate(DateUtil.str2date(endDate, "yyyy-MM-dd"));
	    	rg.setImagePath(imagePath);
	    	rg.setCreateDate(new Date());
	    	UserInfo userInfo = (UserInfo)super.getLoginUser(request);
	    	rg.setCreateor((null!=userInfo)?userInfo.getUserName():"");
	    	rechargeRuleService.add(rg);
	    	request.setAttribute("info", "添加成功");
    	} catch (ParseException e) {
			e.printStackTrace();
		}
    	return new ModelAndView("products/rule/save",null);
    }
    @RequestMapping("rule_add1.html")
    public void ruleAdd1(HttpServletRequest request , HttpServletResponse response){
    	String msg = "添加失败";
    	String amount = request.getParameter("amount");
    	String giftAmount = request.getParameter("giftAmount");
    	String ruleId = request.getParameter("ruleId");
    	RechargeRuleGiftAmount rrg = new RechargeRuleGiftAmount();
    	rrg.setAmount(Integer.parseInt(amount)*100);
    	rrg.setGiftAmount(Integer.parseInt(giftAmount)*100);
    	if(ruleId!=null && !"".equals(ruleId)){
    		rrg.setRuleId(Integer.parseInt(ruleId));
    	}
    	rrg.setIsUsed(Constants.TRUE);
    	try {
	    	rrg.setCreateDate(new Date());
	    	UserInfo userInfo = (UserInfo)super.getLoginUser(request);
	    	rrg.setCreateor((null!=userInfo)?userInfo.getUserName():"");
	    	rechargeRuleGiftAmountService.add(rrg);
	    	msg = "添加成功";
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	PrintWriter out;
		try {
			out = response.getWriter();
			out.print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    @RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		if(id==0){
			throw new NullPointerException("ID不能为空!");
		}
		Map<String,Object> map = super.getParamMap(request);
		try {
			RechargeRule rechargeRule = rechargeRuleService.queryById(id);
			map.put("rechargeRule", rechargeRule);
			String begin=null;
			String end = null;
			if(rechargeRule.getBeginDate()!=null){
				begin = new SimpleDateFormat("yyyy-MM-dd").format(rechargeRule.getBeginDate());
				end = new SimpleDateFormat("yyyy-MM-dd").format(rechargeRule.getEndDate());
			}
			map.put("begin", begin);
			map.put("end", end);
			//根据主表id查询字表信息
			RechargeRuleGiftAmount rechargeRuleGiftAmount = new RechargeRuleGiftAmount();
			rechargeRuleGiftAmount.setRuleId(id);
			rechargeRuleGiftAmount.setIsUsed(Constants.TRUE);
			List<RechargeRuleGiftAmount> lists = rechargeRuleGiftAmountService.selectList(rechargeRuleGiftAmount);
			for(RechargeRuleGiftAmount amount :lists){
				amount.setAmount(amount.getAmount()/100);
				amount.setGiftAmount(amount.getGiftAmount()/100);
			}
			map.put("rechargeRuleGiftAmountList", lists);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("信息出现异常",e);
		}
		return new ModelAndView("products/rule/rule_edit",map);
	}
    @RequestMapping("/{id}/edit1.html")
	public ModelAndView edit1(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		if(id==0){
			throw new NullPointerException("ID不能为空!");
		}
		Map<String,Object> map = super.getParamMap(request);
		try {
			String ruleId =  request.getParameter("ruleId");
			map.put("ruleId", ruleId);
			RechargeRuleGiftAmount rechargeRuleGiftAmount = rechargeRuleGiftAmountService.queryById(id);
			map.put("rechargeRuleGiftAmount", rechargeRuleGiftAmount);
		} catch (Exception e) {
			logger.error("信息出现异常",e);
		}
		return new ModelAndView("products/rule/rule_edit1",map);
	}
    @RequestMapping("rule_edit.html")
    public ModelAndView dictupdate(Rule rule, HttpServletRequest request, HttpServletResponse response) {
    		String id = request.getParameter("id");//主表id  ruleID
    		String beginDate = request.getParameter("beginDate");
    		String endDate = request.getParameter("endDate");
    		String imagePath = request.getParameter("imagePath");
    		RechargeRule rechargeRule = new RechargeRule();
    		rechargeRule.setImagePath(imagePath);
    		rechargeRule.setId(Integer.parseInt(id));
    		try {
    			rechargeRule.setBeginDate(DateUtil.str2date(beginDate, "yyyy-MM-dd"));
				rechargeRule.setEndDate(DateUtil.str2date(endDate, "yyyy-MM-dd"));
				UserInfo userInfo = (UserInfo)super.getLoginUser(request);
				rechargeRule.setModified((null!=userInfo)?userInfo.getUserName():"");
				rechargeRule.setModifyDate(new Date());
				rechargeRuleService.updateRule(rechargeRule);
				if(rule.getRechargeRuleGiftAmount().size()>0)
				{
					for(RechargeRuleGiftAmount rule1 : rule.getRechargeRuleGiftAmount()){
						rule1.setAmount(rule1.getAmount()*100);
						rule1.setGiftAmount(rule1.getGiftAmount()*100);
						rule1.setModified((null!=userInfo)?userInfo.getUserName():"");
						rule1.setModifyDate(new Date());
					}
					rechargeRuleGiftAmountService.batchUpdateRule(rule.getRechargeRuleGiftAmount());
				}
				request.setAttribute("info", "修改成功");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return new ModelAndView("products/rule/save",null);
	}
    @RequestMapping("rule_edit1.html")
    public ModelAndView dictupdate1(HttpServletRequest request, HttpServletResponse response) {
    		String id = request.getParameter("id");
    		String ruleId = request.getParameter("ruleId");
    		String amount = request.getParameter("amount");
    		String giftAmount = request.getParameter("giftAmount");
    		RechargeRuleGiftAmount rechargeRuleGiftAmount = new RechargeRuleGiftAmount();
    		rechargeRuleGiftAmount.setId(Integer.parseInt(id));
    		try {
    			rechargeRuleGiftAmount.setRuleId(Integer.parseInt(ruleId));
    			rechargeRuleGiftAmount.setAmount(Integer.parseInt(amount));
    			rechargeRuleGiftAmount.setGiftAmount(Integer.parseInt(giftAmount));
				UserInfo userInfo = (UserInfo)super.getLoginUser(request);
				rechargeRuleGiftAmount.setModified((null!=userInfo)?userInfo.getUserName():"");
				rechargeRuleGiftAmount.setModifyDate(new Date());
				rechargeRuleGiftAmountService.updateRuleAmount(rechargeRuleGiftAmount);
				request.setAttribute("info", "修改成功");
			} catch (Exception e) {
				logger.error("", e);
			}
		return new ModelAndView("products/rule/save1",null);
	}

	/**
	 * 级联新增
	 */
	@RequestMapping("saveRule")
	public String saveRule(Rule rule, RechargeRule rechargeRule, HttpServletResponse response, HttpServletRequest request) {
		try {
			UserInfo currUser = (UserInfo) super.getLoginUser(request);
			rechargeRuleGiftAmountService.saveRule(rule, rechargeRule, currUser);
			request.setAttribute("info", "新增成功");
		} catch (Exception e) {
			request.setAttribute("info", "异常,添加失败");
			logger.error("", e);
		}
		return "products/rule/save";
	}

}
