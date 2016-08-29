package com.boxiang.share.system.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.share.user.po.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.framework.security.MyInvocationSecurityMetadataSourceService;
import com.boxiang.share.system.po.Dictionary;
import com.boxiang.share.system.po.Sequence;
import com.boxiang.share.system.po.SysAuthorities;
import com.boxiang.share.system.po.SysAuthoritiesResources;
import com.boxiang.share.system.po.SysResources;
import com.boxiang.share.system.service.DictionaryService;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.system.service.SysAuthoritiesResourcesService;
import com.boxiang.share.system.service.SysAuthoritiesService;
import com.boxiang.share.system.service.SysResourcesService;
import com.boxiang.share.system.service.SysRolesAuthoritiesService;
import com.boxiang.share.utils.ApplicationContextUtil;
import com.boxiang.share.utils.TableNameConstants;
import com.boxiang.share.utils.json.JacksonUtil;

/**
 * Created by kaiser on 2016/4/22.
 */
@Controller
@RequestMapping("system/resources")
public class SysResourcesController extends BaseController {
    private static final Logger logger = Logger.getLogger(SysResourcesController.class);

    @Resource
    private SysResourcesService sysResourcesService;
    @Resource
    private SysAuthoritiesService sysAuthoritiesService;
    @Resource
    private SysAuthoritiesResourcesService sysAuthoritiesResourcesService;
    @Resource
    private SysRolesAuthoritiesService sysRolesAuthoritiesService;
    @Resource
    private SequenceService sequenceService;
    @Resource
    private DictionaryService dictionaryService;

    /**
     * 权限信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        try {
            Page<SysResources> page = new Page<SysResources>();
            PageHelper.initPage(request, page);
            UserInfo userInfo= (UserInfo) super.getLoginUser(request);
            page.getParams().put("module",userInfo.getModule());
            Map<String, Object> map = super.getParamMap(request);
            //    String authorityName = (String) map.get("authorityName");
            //  String enabled = (String) map.get("enabled");
            //   page.getParams().put("authorityName", authorityName);
            //page.getParams().put("enabled", enabled);
            page = sysResourcesService.queryListPage(page);
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("进入列表页出现异常", e);
        }

        return new ModelAndView("system/resources_list");
    }

    //删除
    @RequestMapping("{id}/del.html")
    public void sysAuthoritiesDel(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (id == null) {
            throw new NullPointerException("权限ID不能为空!");
        }
        String js;
        try {
            // SysResources sysResources=sysResourcesService.queryById(id);
            sysAuthoritiesResourcesService.del(id);
            sysResourcesService.delete(id);
            // 刷新资源权限控制
            MyInvocationSecurityMetadataSourceService mySecurityMetadataSource =
            		ApplicationContextUtil.getBean("mySecurityMetadataSource", MyInvocationSecurityMetadataSourceService.class); 
            mySecurityMetadataSource.refresh();
            js = "<script>alert('删除成功');location.href='../list.html';</script>";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除信息出现异常", e);
            js = "<script>alert('删除失败');location.href='../list.html';</script>";
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(js);
    }

    //跳转添加
    @RequestMapping("add.html")
    public ModelAndView msgadd(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = super.getParamMap(request);
        SysAuthorities sysAuthorities=new SysAuthorities();
        sysAuthorities.setEnabled(1);
        List<SysAuthorities> listAuthorities=sysAuthoritiesService.selectList(sysAuthorities);
        Dictionary dictionary1 = new Dictionary();
        dictionary1.setDictCode("resource_type");
        dictionary1.setIsUsed(Constants.TRUE);
        List<Dictionary> list = dictionaryService.selectList(dictionary1);
        map.put("list",list);
        map.put("sysAuthorities",listAuthorities);
        Dictionary dictionary = new Dictionary();
        dictionary.setDictCode("module");
        dictionary.setIsUsed(Constants.TRUE);
        List<Dictionary> list123 = dictionaryService.selectList(dictionary);
        map.put("module", list123);
        return new ModelAndView("system/resources_add", map);
    }

    //跳转编辑
    @RequestMapping("{id}/edit.html")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        if (id == null) {
            throw new NullPointerException("ID不能为空!");
        }
        Map<String, Object> map = super.getParamMap(request);
        try {
            SysResources sysResources = sysResourcesService.queryById(id);
            map.put("sysResources", sysResources);
            SysAuthorities sysAuthorities=new SysAuthorities();
            sysAuthorities.setEnabled(1);
            List<SysAuthorities> listSysAuthorities = sysAuthoritiesService.selectList(sysAuthorities);
            //查询当前拥有权限
          List<SysAuthorities> listSysAuthor=  sysAuthoritiesService.queryAuthorities(id);
            map.put("listSysAuthor",listSysAuthor);
            //关联
           List<SysResources> listResources=  sysResourcesService.queryList(id);
            map.put("listResources",listResources);
            Dictionary dictionary1 = new Dictionary();
            dictionary1.setDictCode("resource_type");
            dictionary1.setIsUsed(Constants.TRUE);
            List<Dictionary> list = dictionaryService.selectList(dictionary1);
            map.put("list",list);
            Dictionary dictionary = new Dictionary();
            dictionary.setDictCode("module");
            dictionary.setIsUsed(Constants.TRUE);
            List<Dictionary> list123 = dictionaryService.selectList(dictionary);
            map.put("module", list123);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("信息出现异常", e);
        }
        return new ModelAndView("system/resources_edit", map);
    }

    //添加
    @RequestMapping("save.html")
    public void save(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("sysResources") SysResources sysResources) throws IOException {
        String js;
        try {
            //不能重复
            SysResources sysResources2=new SysResources();
            sysResources2.setResourceName(sysResources.getResourceName());
            sysResources2.setEnabled(1);
            List<SysResources> list=sysResourcesService.selectList(sysResources2);
            if(list!=null&&list.size()>0){
                js = "<script>alert('重复记录');location.href='add.html';</script>";
            }else{
            Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.SYS_RESOURCES);
            sysResources.setResourceId(sequence.getId());
            sysResourcesService.add(sysResources);
            //添加关联表
            String[] authorityName= request.getParameterValues("authorityName");
            if(authorityName!=null&&authorityName.length>0){
                for (int i = 0; i <authorityName.length; i++) {
                    SysAuthoritiesResources  sysAuthoritiesResources=new SysAuthoritiesResources();
                    sysAuthoritiesResources.setEnabled(1);
                    sysAuthoritiesResources.setResourceId(sysResources.getResourceId());
                    sysAuthoritiesResources.setAuthorityId(authorityName[i]);
                    sysAuthoritiesResourcesService.add(sysAuthoritiesResources);
                }
            }
            js = "<script>alert('添加成功');location.href='list.html';</script>";
            }
            // 刷新资源权限控制
            MyInvocationSecurityMetadataSourceService mySecurityMetadataSource =
            		ApplicationContextUtil.getBean("mySecurityMetadataSource", MyInvocationSecurityMetadataSourceService.class); 
            mySecurityMetadataSource.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加信息出现异常", e);
            js = "<script>alert('添加失败');location.href='list.html';</script>";
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(js);
    }

    @RequestMapping("update.html")
    public void update(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("sysResources") SysResources sysResources)throws IOException {
        String js;
        try {
            sysResourcesService.update(sysResources);
            sysAuthoritiesResourcesService.del(sysResources.getResourceId());
            String[] authorityName= request.getParameterValues("authorityName");
            if(authorityName!=null&&authorityName.length>0){
                for (int i = 0; i <authorityName.length; i++) {
                    SysAuthoritiesResources  sysAuthoritiesResources=new SysAuthoritiesResources();
                    sysAuthoritiesResources.setEnabled(1);
                    sysAuthoritiesResources.setResourceId(sysResources.getResourceId());
                    sysAuthoritiesResources.setAuthorityId(authorityName[i]);
                    sysAuthoritiesResourcesService.add(sysAuthoritiesResources);
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

    //查询重复记录
    @RequestMapping("repeat.html")
    public void repeat(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> map = super.getParamMap(request);
       String resourceName=request.getParameter("resourceName");
        SysResources sysResources=new SysResources();
        sysResources.setResourceName(resourceName);
        sysResources.setEnabled(1);
        List<SysResources> list=sysResourcesService.selectList(sysResources);
        if(list!=null&&list.size()>0){
            response.getWriter().print(JacksonUtil.toJson(1));
        }

    }
}
