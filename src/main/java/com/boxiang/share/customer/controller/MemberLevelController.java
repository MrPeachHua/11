package com.boxiang.share.customer.controller;
import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.customer.po.MemberLevel;
import com.boxiang.share.customer.service.*;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.system.po.SysRoles;
import com.boxiang.share.system.po.SysUsers;
import com.boxiang.share.system.po.SysUsersRoles;
import com.boxiang.share.user.po.DepartmentInfo;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.utils.DateUtil;
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
public class MemberLevelController extends BaseController {

    private static final Logger logger = Logger.getLogger(MemberLevelController.class);

    @Resource
    private MemberLevelService memberLevelService;

    /**
     * 会员等级列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("member_level/list.html")
    public ModelAndView memberLevelList(HttpServletRequest request, HttpServletResponse response) {
        try {
            Page<MemberLevel> page = new Page<MemberLevel>();
            PageHelper.initPage(request, page);
            Map<String, Object> map = super.getParamMap(request);
            String queryValue = (String)map.get("queryValue");
            if(!StringUtils.isEmpty(queryValue)){
                page.getParams().put("levelName", queryValue);
            }
            page = memberLevelService.queryListPage(page);
            PageHelper.setPageModel(request, page);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询会员等级出现异常", e);
        }
        return new ModelAndView("customer/member_level_list");
    }

    /**
     * 进入添加页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("member_level/add.html")
    public ModelAndView memberLevelAdd(HttpServletRequest request, HttpServletResponse response) {
        //request.setAttribute("moduleDict", dictionaryService.getDictListByDictCode("module"));
        return new ModelAndView("customer/member_level_add");
    }

    @RequestMapping("member_level/save.html")
    public ModelAndView memberLevelSave(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("memberLevel") MemberLevel memberLevel) {
        try {
            UserInfo currUser = (UserInfo) super.getLoginUser(request);
            java.util.Date nowdate = new java.util.Date();
            memberLevel.setCreateor("admin");
            memberLevel.setCreateDate(nowdate);
            memberLevelService.add(memberLevel);
            request.setAttribute("info", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加会员等级出现异常",e);
        }
        return new ModelAndView("customer/member_level_save");
    }

    /**
     * 会员等级修改回显
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("member_level/{id}/edit.html")
    public ModelAndView memberLevelEdit(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = super.getParamMap(request);
        try {
            MemberLevel memberLevel = memberLevelService.queryById(id);
            map.put("memberLevel",memberLevel);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("进入会员等级资料修改在出现异常",e);
        }
        return new ModelAndView("customer/member_level_edit",map);
    }

    /**
     * 修改员工
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("member_level/update.html")
    public ModelAndView memberLevelUpdate(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("memberLevel") MemberLevel memberLevel) {
        try {
            //userInfo.setUserChangeman(currUser.getUserName());
            //userInfo.setUserChangetime(DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT));
            java.util.Date nowdate = new java.util.Date();
            memberLevel.setModified("admin");
            memberLevel.setModifyDate(nowdate);
            memberLevelService.update(memberLevel);
            request.setAttribute("info", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("修改会员等级信息出现异常",e);
        }
        return new ModelAndView("customer/member_level_save");
    }

    @RequestMapping("member_level/check.html")
    public void check(@RequestParam("levelCode") String levelCode,HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try{
            response.setContentType("text/plain; charset=UTF-8");
            out = response.getWriter();
            MemberLevel memberLevel = new MemberLevel();
            memberLevel.setLevelCode(levelCode);
            List<MemberLevel> list = memberLevelService.selectList(memberLevel);
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

    @RequestMapping("member_level/check1.html")
    public void check1(@RequestParam("levelName") String levelName,HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try{
            response.setContentType("text/plain; charset=UTF-8");
            out = response.getWriter();
            MemberLevel memberLevel = new MemberLevel();
            memberLevel.setLevelName(levelName);
            List<MemberLevel> list = memberLevelService.selectList(memberLevel);
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
