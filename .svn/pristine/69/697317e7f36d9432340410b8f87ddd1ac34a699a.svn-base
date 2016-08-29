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
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kaiser on 2016/4/26.
 */
@Controller
@RequestMapping("system/roles")
public class SysRolesController extends BaseController {
    private static final Logger logger = Logger.getLogger(SysRolesController.class);

    @Resource
    private SysAuthoritiesService sysAuthoritiesService;
    @Resource
    private SysRolesAuthoritiesService sysRolesAuthoritiesService;
    @Resource
    private SysRolesService sysRolesService;
    @Resource
    private SysUsersRolesService sysUsersRolesService;
    @Resource
    private SequenceService sequenceService;

    @Resource
    private SysUsersService sysUsersService;
    
	@Resource
	private UserCache userCache;
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
            Page<SysRoles> page = new Page<SysRoles>();
            PageHelper.initPage(request, page);
            String roleName = request.getParameter("roleName");
            page.getParams().put("roleName", roleName);
            UserInfo userInfo2=(UserInfo)super.getLoginUser(request);
            if(userInfo2.getModule()!=null&&!("").equals(userInfo2.getModule())){
                page.getParams().put("module", userInfo2.getModule());
            }
            page = sysRolesService.queryListPage(page);
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("进入列表页出现异常", e);
        }

        return new ModelAndView("system/sysroles_list");
    }

    //删除
    @RequestMapping("{id}/del.html")
    public void sysAuthoritiesDel(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (id == null) {
            throw new NullPointerException("权限ID不能为空!");
        }
        String js;
        try {
            //  SysAuthorities sysAuthorities=sysAuthoritiesService.queryById(id);
            SysUsersRoles   sysUsersRoles=new SysUsersRoles();
            sysUsersRoles.setEnabled(1);
            sysUsersRoles.setRoleId(id);
           List<SysUsersRoles> list= sysUsersRolesService.selectList(sysUsersRoles);
            if (list!=null&&list.size()>0){
                SysUsers sysUsers= sysUsersService.queryById(list.get(0).getUserId());
                js = "<script>alert('此角色已被用户"+sysUsers.getUserName()+"绑定,暂时不能删除！');location.href='../list.html';</script>";
                //'"+sysUsers.getUserName()+"'
            }else {
            sysRolesAuthoritiesService.del(id);
            sysUsersRolesService.del(id);
            sysRolesService.delete(id);
            // 刷新资源权限控制
            MyInvocationSecurityMetadataSourceService mySecurityMetadataSource =
            		ApplicationContextUtil.getBean("mySecurityMetadataSource", MyInvocationSecurityMetadataSourceService.class); 
            mySecurityMetadataSource.refresh();
            js = "<script>alert('删除成功');location.href='../list.html';</script>";
            }
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
        UserInfo userInfo=(UserInfo) super.getLoginUser(request);
       /* SysAuthorities sysAuthorities=new SysAuthorities();
        sysAuthorities.setEnabled(1);
        List<SysAuthorities> listAuthorities=sysAuthoritiesService.selectList(sysAuthorities);
        map.put("sysAuthorities",listAuthorities);
       //获取当前用户的角色
        SysUsersRoles sysUsersRoles=new SysUsersRoles();
        sysUsersRoles.setUserId(userInfo.getSysUserId());
        sysUsersRoles.setEnabled(1);
        List<SysUsersRoles> sysList=  sysUsersRolesService.selectList(sysUsersRoles);
        List listsysRolesAuthorities=new ArrayList<>();
        if(sysList!=null&&sysList.size()>0){
        for (SysUsersRoles sysUsersRoles2:sysList){*/
            //循环查出所拥有的权限
           /* SysRolesAuthorities   sysRolesAuthorities=new SysRolesAuthorities();
            sysRolesAuthorities.setRoleId(userInfo.getRoleId());*/
            //拿到当前登录用户的角色 userInfo.getRoleId();sys_users_roles
            List<Dictionary> list= dictionaryService.getDictListByDictCode("module");
            List<SysAuthorities>  listsysRoles  =    sysRolesAuthoritiesService.selectList2(userInfo.getSysUserId());
     /*       listsysRolesAuthorities.addAll(listsysRoles);

        }
        }*/
        map.put("module",list);
        map.put("listsysRolesAuthorities",listsysRoles);

        return new ModelAndView("system/sysroles_add", map);
    }

    //跳转编辑
    @RequestMapping("{id}/edit.html")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        if (id == null) {
            throw new NullPointerException("ID不能为空!");
        }
        Map<String, Object> map = super.getParamMap(request);
        try {
            //查询当前拥有权限
            UserInfo userInfo = (UserInfo) super.getLoginUser(request);
            List<SysAuthorities> listSysAuthor=  sysAuthoritiesService.queryAuthoritiesByRoles(id);
            map.put("listSysAuthor",listSysAuthor);
            SysRoles sysRoles=sysRolesService.queryById(id);
           map.put("sysRoles", sysRoles);
           List<SysRoles> list= sysRolesService.queryList(id,userInfo.getSysUserId());
            map.put("roles",list);
           /* List<Dictionary> list2= dictionaryService.getDictListByDictCode("module");
            map.put("module",list2);*/
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("信息出现异常", e);
        }
        return new ModelAndView("system/sysroles_edit", map);
    }

    //添加
    @RequestMapping("save.html")
    public void save(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("sysRoles") SysRoles sysRoles) throws IOException {
        String js;
        try {
            Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.SYS_ROLES);
            SysRoles sysRoles2 =new SysRoles();
            sysRoles2.setModule(sysRoles.getModule());
            sysRoles2.setEnabled(1);
            sysRoles2.setRoleName(sysRoles.getRoleName());
            sysRoles2.setRoleDesc(sysRoles.getRoleDesc());
            List<SysRoles> list=sysRolesService.selectList(sysRoles2);
            if(list!=null&&list.size()>0){
                js = "<script>alert('存在重复数据！');location.href='add.html';</script>";
            }else {

            sysRoles.setRoleId(sequence.getId());
            sysRolesService.add(sysRoles);
            //添加关联表
            String[] authorityName= request.getParameterValues("authorityName");
            if(authorityName!=null&&authorityName.length>0){
                for (int i = 0; i <authorityName.length; i++) {
                    SysRolesAuthorities  sysRolesAuthorities=new SysRolesAuthorities();
                    sysRolesAuthorities.setEnabled(1);
                    sysRolesAuthorities.setRoleId(sysRoles.getRoleId());
                    sysRolesAuthorities.setAuthorityId(authorityName[i]);
                    sysRolesAuthoritiesService.add(sysRolesAuthorities);
                }
            }
            // 刷新资源权限控制
            MyInvocationSecurityMetadataSourceService mySecurityMetadataSource =
            		ApplicationContextUtil.getBean("mySecurityMetadataSource", MyInvocationSecurityMetadataSourceService.class); 
            mySecurityMetadataSource.refresh();
            // 清除用户权限信息的缓存
            List<SysUsers> sysUsers = sysUsersService.queryUserByRole(sysRoles.getRoleId());
            for (SysUsers user:sysUsers) {
                userCache.removeUserFromCache(user.getUserAccount());
			}
            js = "<script>alert('添加成功');location.href='list.html';</script>";

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加信息出现异常", e);
            js = "<script>alert('添加失败');location.href='list.html';</script>";
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(js);
    }

    @RequestMapping("update.html")
    public void update(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("sysRoles") SysRoles sysRoles)throws IOException {
        String js = null;
        try {
            SysRoles sysRoles2 =new SysRoles();
            sysRoles2.setModule(sysRoles.getModule());
            sysRoles2.setEnabled(1);
            sysRoles2.setRoleName(sysRoles.getRoleName());
            sysRoles2.setRoleDesc(sysRoles.getRoleDesc());
            List<SysRoles> list=sysRolesService.selectList(sysRoles2);
                    sysRolesService.update(sysRoles);
                    //删除原有数据
                    sysRolesAuthoritiesService.del(sysRoles.getRoleId());
                    //添加新数据
                    String[] authorityName = request.getParameterValues("authorityName");
                    if (authorityName != null && authorityName.length > 0) {
                        for (int i = 0; i < authorityName.length; i++) {
                            SysRolesAuthorities sysRolesAuthorities = new SysRolesAuthorities();
                            sysRolesAuthorities.setEnabled(1);
                            sysRolesAuthorities.setRoleId(sysRoles.getRoleId());
                            sysRolesAuthorities.setAuthorityId(authorityName[i]);
                            sysRolesAuthoritiesService.add(sysRolesAuthorities);
                        }
                    }
                    // 刷新资源权限控制
                    MyInvocationSecurityMetadataSourceService mySecurityMetadataSource =
                            ApplicationContextUtil.getBean("mySecurityMetadataSource", MyInvocationSecurityMetadataSourceService.class);
                    mySecurityMetadataSource.refresh();
                    // 清除用户权限信息的缓存
                    List<SysUsers> sysUsers = sysUsersService.queryUserByRole(sysRoles.getRoleId());
                    for (SysUsers user : sysUsers) {
                        userCache.removeUserFromCache(user.getUserAccount());
                    }
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
