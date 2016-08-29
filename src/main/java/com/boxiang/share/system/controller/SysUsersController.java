package com.boxiang.share.system.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.system.po.Sequence;
import com.boxiang.share.system.po.SysRoles;
import com.boxiang.share.system.po.SysUsers;
import com.boxiang.share.system.po.SysUsersRoles;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.system.service.SysRolesService;
import com.boxiang.share.system.service.SysUsersRolesService;
import com.boxiang.share.system.service.SysUsersService;
import com.boxiang.share.user.service.UserInfoService;
import com.boxiang.share.utils.TableNameConstants;


/**
 * Created by kaiser on 2016/4/22.
 */
@Controller
@RequestMapping("system/users")
public class SysUsersController extends BaseController {
    private static final Logger logger = Logger.getLogger(SysUsersController.class);

    @Resource
    private SysUsersService sysUsersService;
    @Resource
    private SequenceService sequenceService;
    @Resource
    private SysRolesService sysRolesService;
    @Resource
    private SysUsersRolesService sysUsersRolesService;
    @Resource
    private UserInfoService userInfoService;
    //查询
    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
    	try {
			Page<SysUsers> page=new Page<SysUsers>();
			PageHelper.initPage(request, page);
			String userName=request.getParameter("userName");
			String userDept=request.getParameter("userDept");
			page.getParams().put("userName", userName);
			page.getParams().put("userDept", userDept);
			page=sysUsersService.queryListPage(page);
			PageHelper.setPageModel(request, page);;
		} catch (Exception e) {
			e.printStackTrace();
			 logger.error("进入列表页出现异常",e);
		}
    	 return new ModelAndView("system/users_list");
    }
    //跳转添加
    @RequestMapping("add.html")
    public ModelAndView msgadd(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = super.getParamMap(request);
        SysRoles sysRoles=new SysRoles();
        sysRoles.setEnabled(1);
      List<SysRoles> list=  sysRolesService.selectList(sysRoles);
        map.put("list",list);
        return new ModelAndView("system/users_add",map);
    }
    //添加
    @RequestMapping("save.html")
    public void save(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("sysUsers") SysUsers sysUsers) throws IOException{
        String js;
        try {
            Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_CUSTOMER);
            sysUsers.setUserId(sequence.getId());
            sysUsersService.add(sysUsers);
            //关联
            String sysRoles=request.getParameter("sysRoles");
            SysUsersRoles sysUsersRoles=new SysUsersRoles();
            sysUsersRoles.setEnabled(1);
            sysUsersRoles.setRoleId(sysRoles);
            sysUsersRoles.setUserId(sysUsers.getUserId());
            sysUsersRolesService.add(sysUsersRoles);






            js = "<script>alert('添加成功');location.href='list.html';</script>";

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加信息出现异常", e);
              js = "<script>alert('添加失败');location.href='list.html';</script>";
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(js);
    }

    //删除
    @RequestMapping("{id}/del.html")
    public void sysUsersDel(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(id==null){
            throw new NullPointerException("用户ID不能为空!");
        }
        String js;
        try {
          //  SysAuthorities sysAuthorities=sysAuthoritiesService.queryById(id);
            sysUsersService.delete(id);
            js = "<script>alert('删除成功');location.href='../list.html';</script>";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除信息出现异常",e);
            js = "<script>alert('删除失败');location.href='../list.html';</script>";
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(js);
    }
    //跳转编辑
    @RequestMapping("{id}/edit.html")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,@PathVariable String id) {
        if(id==null){
            throw new NullPointerException("ID不能为空!");
        }
        Map<String,Object> map = super.getParamMap(request);
        try {
            SysUsers sysUsers=sysUsersService.queryById(id);
            map.put("sysUsers",sysUsers);
            SysRoles sysRoles=new SysRoles();
            sysRoles.setEnabled(1);
            List<SysRoles> list=  sysRolesService.selectList(sysRoles);
            map.put("list",list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("信息出现异常",e);
        }
        return new ModelAndView("system/users_edit",map);
    }
    @RequestMapping("update.html")
    public void update(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("sysUsers") SysUsers sysUsers)throws IOException {
        String js;
        try {
            sysUsersService.update(sysUsers);
            js = "<script>alert('修改成功');location.href='list.html';</script>";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("修改信息出现异常",e);
            js = "<script>alert('修改失败');location.href='list.html';</script>";
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(js);
    }
}
