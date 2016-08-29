package com.boxiang.share.system.controller;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.framework.security.MyInvocationSecurityMetadataSourceService;
import com.boxiang.share.system.po.*;
import com.boxiang.share.system.service.*;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.utils.ApplicationContextUtil;
import com.boxiang.share.utils.TableNameConstants;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kaiser on 2016/4/22.
 */
@Controller
@RequestMapping("system/authority")
public class SysAuthoritiesController extends BaseController {
    private static final Logger logger = Logger.getLogger(SysAuthoritiesController.class);

    @Resource
    private SysAuthoritiesService sysAuthoritiesService;
    @Resource
    private SequenceService sequenceService;
    @Resource
    private SysRolesService sysRolesService;
    @Resource
    private SysUsersRolesService sysUsersRolesService;
    @Resource
    private SysAuthoritiesResourcesService sysAuthoritiesResourcesService;
    @Resource
    private SysRolesAuthoritiesService sysRolesAuthoritiesService;
    @Resource
    private DictionaryService dictionaryService;
    /**
     * 权限信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
        try {
            Page<SysAuthorities> page = new Page<SysAuthorities>();
            PageHelper.initPage(request, page);
            UserInfo userInfo = (UserInfo)super.getLoginUser(request);
            String authorityName = request.getParameter("authorityName");
            String authorityDesc = request.getParameter("authorityDesc");
          //  String enabled = (String) map.get("enabled");
            page.getParams().put("authorityName", authorityName);
            page.getParams().put("authorityDesc", authorityDesc);
            page.getParams().put("module", userInfo.getModule());
            //page.getParams().put("enabled", enabled);
            page = sysAuthoritiesService.queryListPage(page);
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("进入列表页出现异常",e);
        }

        return new ModelAndView("system/authorities_list");
    }

    //删除
    @RequestMapping("{id}/del.html")
    public void sysAuthoritiesDel(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException{
        if(id==null){
            throw new NullPointerException("权限ID不能为空!");
        }
        String js;
        try {
          //  SysAuthorities sysAuthorities=sysAuthoritiesService.queryById(id);
            sysAuthoritiesService.delete(id);
            // 刷新资源权限控制
            MyInvocationSecurityMetadataSourceService mySecurityMetadataSource =
            		ApplicationContextUtil.getBean("mySecurityMetadataSource", MyInvocationSecurityMetadataSourceService.class); 
            mySecurityMetadataSource.refresh();
            js = "<script>alert('删除成功');location.href='../list.html';</script>";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除信息出现异常",e);
            js = "<script>alert('删除失败');location.href='../list.html';</script>";
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(js);
    }
    //跳转添加
    @RequestMapping("add.html")
    public ModelAndView msgadd(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = super.getParamMap(request);
        SysRoles sysRoles=new SysRoles();
        sysRoles.setEnabled(1);
        List<SysRoles> list=  sysRolesService.selectList(sysRoles);
        map.put("userRoles", list);
      /*  Dictionary dictionary = new Dictionary();
        dictionary.setDictCode("module");
        dictionary.setIsUsed(Constants.TRUE);
        List<Dictionary> list123 = dictionaryService.selectList(dictionary);
        map.put("module", list123);*/
        return new ModelAndView("system/authorities_add",map);
    }
    //跳转编辑
    @RequestMapping("{id}/edit.html")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,@PathVariable String id) {
        if(id==null){
            throw new NullPointerException("ID不能为空!");
        }
        Map<String,Object> map = super.getParamMap(request);
        UserInfo userInfo = (UserInfo)super.getLoginUser(request);
        Map<String,Object> map1=new HashMap<>();
        map1.put("authorityId",id);
        map1.put("module",userInfo.getModule());
        try {
             SysAuthorities sysAuthorities=  sysAuthoritiesService.queryById(id);
            List<SysRoles> list= sysRolesService.queryListRoles2(map1);
            map.put("roles",list);
            map.put("sysAuthorities",sysAuthorities);
            Dictionary dictionary = new Dictionary();
            dictionary.setDictCode("module");
            dictionary.setIsUsed(Constants.TRUE);
            List<Dictionary> list123 = dictionaryService.selectList(dictionary);
            map.put("module", list123);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("信息出现异常",e);
        }
        return new ModelAndView("system/authorities_edit",map);
    }
    //添加
    @RequestMapping("save.html")
    public void save(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("sysAuthorities") SysAuthorities sysAuthorities) throws IOException{
        String js;
        try {
            Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.SYS_AUTHORITIES);
            sysAuthorities.setAuthorityId(sequence.getId());
            sysAuthoritiesService.add(sysAuthorities);
            String[] sysRoles=request.getParameterValues("sysRoles");//角色数组
            //关联
            if(sysRoles!=null&&sysRoles.length>0){
                    for (int i = 0; i <sysRoles.length; i++) {
                        SysRolesAuthorities sysRolesAuthorities=new SysRolesAuthorities();
                        sysRolesAuthorities.setEnabled(1);
                        sysRolesAuthorities.setRoleId(sysRoles[i]);
                        sysRolesAuthorities.setAuthorityId(sysAuthorities.getAuthorityId());
                        sysRolesAuthoritiesService.add(sysRolesAuthorities);
                    }
            }

            // 刷新资源权限控制
            MyInvocationSecurityMetadataSourceService mySecurityMetadataSource =
            		ApplicationContextUtil.getBean("mySecurityMetadataSource", MyInvocationSecurityMetadataSourceService.class); 
            mySecurityMetadataSource.refresh();
            js = "<script>alert('添加成功');location.href='list.html';</script>";

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加信息出现异常", e);
              js = "<script>alert('添加失败');location.href='list.html';</script>";
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(js);
    }
    @RequestMapping("update.html")
    public void update(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("sysAuthorities") SysAuthorities sysAuthorities)throws IOException {
        String js;
        try {
            sysAuthoritiesService.update(sysAuthorities);
            //删除原有数据
            sysRolesAuthoritiesService.delByAuthId(sysAuthorities.getAuthorityId());
            //添加新数据
            String[] sysRoles=request.getParameterValues("sysRoles");//角色数组
            if(sysRoles!=null&&sysRoles.length>0){
                for (int i = 0; i <sysRoles.length; i++) {
                    SysRolesAuthorities sysRolesAuthorities=new SysRolesAuthorities();
                    sysRolesAuthorities.setEnabled(1);
                    sysRolesAuthorities.setRoleId(sysRoles[i]);
                    sysRolesAuthorities.setAuthorityId(sysAuthorities.getAuthorityId());
                    sysRolesAuthoritiesService.add(sysRolesAuthorities);
                }
            }

            // 刷新资源权限控制
            MyInvocationSecurityMetadataSourceService mySecurityMetadataSource =
            		ApplicationContextUtil.getBean("mySecurityMetadataSource", MyInvocationSecurityMetadataSourceService.class); 
            mySecurityMetadataSource.refresh();
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
