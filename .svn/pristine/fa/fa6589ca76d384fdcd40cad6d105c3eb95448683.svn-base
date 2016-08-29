package com.boxiang.share.customer.controller;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.customer.po.MemberGroup;
import com.boxiang.share.customer.po.MemberLevel;
import com.boxiang.share.customer.service.MemberGroupService;
import com.boxiang.share.customer.service.MemberLevelService;
import org.apache.commons.lang.StringUtils;
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
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by dfl on 2016/8/23.
 */
@Controller
@RequestMapping("customer")
public class MemberGroupController extends BaseController {

    private static final Logger logger = Logger.getLogger(MemberGroupController.class);

    @Resource
    private MemberGroupService memberGroupService;

    /**
     * 会员分组列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("member_group/list.html")
    public ModelAndView memberGroupList(HttpServletRequest request, HttpServletResponse response) {
        try {
            Page<MemberGroup> page = new Page<MemberGroup>();
            PageHelper.initPage(request, page);
            Map<String, Object> map = super.getParamMap(request);
            String queryGroup = (String)map.get("queryGroup");
            String queryStatus = (String)map.get("queryStatus");
            if(!StringUtils.isEmpty(queryGroup)){
                page.getParams().put("groupName", queryGroup);
            }
            if(!StringUtils.isEmpty(queryStatus)){
                page.getParams().put("isValid", queryStatus);
            }
            page = memberGroupService.queryListPage(page);
            PageHelper.setPageModel(request, page);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询会员分组出现异常", e);
        }
        return new ModelAndView("customer/member_group_list");
    }

    /**
     * 进入添加页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("member_group/add.html")
    public ModelAndView memberLevelAdd(HttpServletRequest request, HttpServletResponse response) {
        //request.setAttribute("moduleDict", dictionaryService.getDictListByDictCode("module"));
        return new ModelAndView("customer/member_group_add");
    }

    @RequestMapping("member_group/save.html")
    public ModelAndView memberGroupSave(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("memberGroup") MemberGroup memberGroup) {
        try {
           // UserInfo currUser = (UserInfo) super.getLoginUser(request);
            java.util.Date nowdate = new java.util.Date();
            memberGroup.setCreateor("admin");
            memberGroup.setCreateDate(nowdate);
            memberGroupService.add(memberGroup);
            request.setAttribute("info", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加会员分组出现异常",e);
        }
        return new ModelAndView("customer/member_group_save");
    }

    /**
     * 会员等级修改回显
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/member_group/{id}/edit.html")
    public ModelAndView memberGroupEdit(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = super.getParamMap(request);
        try {
            MemberGroup memberGroup = memberGroupService.queryById(id);
            map.put("memberGroup",memberGroup);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("进入会员分组修改在出现异常",e);
        }
        return new ModelAndView("customer/member_group_edit",map);
    }

    /**
     * 修改员工
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("member_group/update.html")
    public ModelAndView memberLevelUpdate(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("memberGroup") MemberGroup memberGroup) {
        try {
            //userInfo.setUserChangeman(currUser.getUserName());
            //userInfo.setUserChangetime(DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT));
            java.util.Date nowdate = new java.util.Date();
            memberGroup.setModified("admin");
            memberGroup.setModifyDate(nowdate);
            memberGroupService.update(memberGroup);
            request.setAttribute("info", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("修改会员分组出现异常",e);
        }
        return new ModelAndView("customer/member_group_save");
    }

    @RequestMapping("member_group/check.html")
    public void check(@RequestParam("groupCode") String groupCode,HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try{
            response.setContentType("text/plain; charset=UTF-8");
            out = response.getWriter();
            MemberGroup memberGroup = new MemberGroup();
            memberGroup.setGroupCode(groupCode);
            List<MemberGroup> list = memberGroupService.selectList(memberGroup);
            if(list!=null && list.size()>0){
                out.print("0");
            }else {
                out.print("1");
            }
            out.flush();
        }catch(IOException e){
            logger.error("",e);
        }
    }


}
