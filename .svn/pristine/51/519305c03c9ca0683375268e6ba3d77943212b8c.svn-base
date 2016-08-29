package com.boxiang.share.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.share.system.po.SysRoles;
import com.boxiang.share.system.po.SysUsers;
import com.boxiang.share.system.service.SysRolesService;
import com.boxiang.share.system.service.SysUsersService;
import com.boxiang.share.user.po.Menu;
import com.boxiang.share.user.po.MenuRole;
import com.boxiang.share.user.po.TreeNode;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.user.service.MenuRoleService;
import com.boxiang.share.user.service.MenuService;
import com.boxiang.share.utils.json.JacksonUtil;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.user.po.UserRole;
import com.boxiang.share.user.service.UserRoleService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("users")
public class RoleController extends BaseController {
    private static final Logger logger = Logger.getLogger(RoleController.class);

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private MenuRoleService menuRoleService;

    @Resource
    private MenuService menuService;

    @Resource
    private SysRolesService sysRolesService;

    @Resource
    private SysUsersService sysUsersService;
    
	@Resource
	private UserCache userCache;

    /**
     * 员工列表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("rolelist.html")
    public ModelAndView rolelist(HttpServletRequest request, HttpServletResponse response) {
        try {
            Page<UserRole> page = new Page<UserRole>();
            PageHelper.initPage(request, page);
            page.getParams().put("isUsed", Constants.TRUE);
            UserInfo userInfo = (UserInfo) super.getLoginUser(request);
            if (userInfo.getRoleId() == 3) {//登录用户为区域管理员时,只显示项目管理员的记录
                page.getParams().put("roleId", 2);
            }
            page = userRoleService.queryListPage(page);
            PageHelper.setPageModel(request, page);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询角色信息出现异常", e);
        }
        return new ModelAndView("role/role_list");
    }

    @RequestMapping("roleadd.html")
    public ModelAndView roleadd(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("role/role_add");
    }

    @RequestMapping("rolesave.html")
    public ModelAndView rolesave(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("userRole") UserRole userRole) {
        try {
            userRoleService.add(userRole);
            request.setAttribute("info", "添加成功");
        } catch (Exception e) {
            request.setAttribute("info", "添加失败");
            e.printStackTrace();
            logger.error("添加角色信息出现异常", e);
        }
        return new ModelAndView("role/role_save");
    }

    @RequestMapping("{roleId}/roledel.html")
    public ModelAndView roledel(@PathVariable int roleId, HttpServletRequest request, HttpServletResponse response) {
        if (roleId == 0) {
            throw new NullPointerException("角色ID不能为空!");
        }
        try {
            userRoleService.deleteFalse(roleId);
            request.setAttribute("info", "删除成功");
        } catch (NumberFormatException nfe) {
            logger.error("角色id转换类型失败，请检查参数是否正确！userId=" + roleId + ".", nfe);
            throw new NumberFormatException("角色id转换类型失败，请检查参数是否正确！" + roleId);
        } catch (Exception e) {
            request.setAttribute("info", "删除失败");
            e.printStackTrace();
            logger.error("删除角色信息出现异常", e);
        }
        return new ModelAndView("role/role_save");
    }

    /**
     * 树状菜单
     *
     * @return
     */
    @RequestMapping("treeMenu.html")
    public void treeMenu(HttpServletRequest request, HttpServletResponse response) {
        String roleName = request.getParameter("roleName");
        MenuRole menuRole = new MenuRole();
        menuRole.setIsUsed(Constants.TRUE);
        menuRole.setRoleName(roleName);
        UserInfo currUser = (UserInfo) super.getLoginUser(request);
        List<Menu> menuList = null;
        if (currUser.getRolePower() == 1) {
            menuList = menuRoleService.menuJoinMenuRole(menuRole);
        } else {
            menuRole.setRolePower(Integer.toString(currUser.getRolePower()));
            menuList = menuRoleService.menuJoinMenuRole2(menuRole);
        }
        Menu rootMenu = new Menu();
        rootMenu.setParentCode("root");
        rootMenu = menuService.selectList(rootMenu).get(0);
        TreeNode node = new TreeNode();
        node.setId(Integer.valueOf(rootMenu.getMenuCode()));
        node.setText(rootMenu.getMenuName());
        getChildNode(menuList, node);
        List<TreeNode> treeList = new ArrayList<TreeNode>();
        treeList.add(node);
        String result = JacksonUtil.toJson(treeList);

        response.setContentType("text/html;charset=UTF-8");
        try {
            response.getWriter().print(result);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    /**
     * 树状菜单
     *
     * @return
     */
    @RequestMapping("treeMenuV2.html")
    public void treeMenuV2(HttpServletRequest request, HttpServletResponse response) {
        String roleName = request.getParameter("roleName");
        MenuRole menuRole = new MenuRole();
       // menuRole.setIsUsed(Constants.TRUE);
        UserInfo userInfo = (UserInfo) super.getLoginUser(request);
        menuRole.setRoleName(roleName);
      //  menuRole.setRoleName(roleDesc);
        List<Menu> menuList = null;
       // menuRole.setIsUsed("1");
        menuRole.setUserId(userInfo.getSysUserId());
            //menuList = menuRoleService.menuJoinMenuRole(menuRole);
            //menuList = menuRoleService.menuJoinMenuRole2(menuRole);
        if(userInfo.getModule()!=null&&!"".equals(userInfo.getModule())){
            menuList=menuRoleService.menuJoinMenuRole4(menuRole);//第三方炒鸡管理
        }else {
            menuList=menuRoleService.menuJoinMenuRole3(menuRole);//炒鸡管理员
        }
        Menu rootMenu = new Menu();
        rootMenu.setParentCode("root");
        rootMenu = menuService.selectList(rootMenu).get(0);
        TreeNode node = new TreeNode();
        node.setId(Integer.valueOf(rootMenu.getMenuCode()));
        node.setText(rootMenu.getMenuName());
        getChildNode(menuList, node);
        List<TreeNode> treeList = new ArrayList<TreeNode>();
        treeList.add(node);
        String result = JacksonUtil.toJson(treeList);

        response.setContentType("text/html;charset=UTF-8");
        try {
            response.getWriter().print(result);
        } catch (IOException e) {
            logger.error("", e);
        }
    }

    /**
     * 递归出树状菜单
     */
    private void getChildNode(List<Menu> menuList, TreeNode parentNode) {
        for (Menu menu : menuList) {
            if (menu.getParentCode().equals(Integer.toString(parentNode.getId()))) {
                TreeNode node = new TreeNode();
                node.setId(Integer.valueOf(menu.getMenuCode()));
                node.setText(menu.getMenuName());
                node.setChecked(menu.getIsLeaf().equals("1") && menu.getMenuRole() != null && menu.getMenuRole().getIsUsed().equals("1"));
                if (parentNode.getChildren() == null) {
                    parentNode.setChildren(new ArrayList<TreeNode>());
                }
                parentNode.getChildren().add(node);
                getChildNode(menuList, node);
            }
        }
    }

    /**
     * 批量更新角色权限
     */
    @RequestMapping("updateRole.html")
    public ModelAndView updateRole(HttpServletRequest request,
                                   HttpServletResponse response,
                                   String menuCodeString,
                                   String roleName,
                                   Integer roleId) {
        try {
            // 查询出将要修改的角色
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setRoleName(roleName);
            userRole = userRoleService.selectList(userRole).get(0);
            // 查询当前用户数据库中的权限状态
            MenuRole menuRole = new MenuRole();
//			menuRole.setIsUsed(Constants.TRUE);
            menuRole.setRoleName(roleName);
            List<Menu> menuList = menuRoleService.menuJoinMenuRole(menuRole);
            // 当前选中的权限号码
            List<String> checkedMenuCodeArray = Arrays.asList(menuCodeString.split(","));
            menuRoleService.updateRole(menuList, checkedMenuCodeArray, userRole);
            request.setAttribute("info", "修改成功");
        } catch (Exception e) {
            request.setAttribute("info", "异常");
            logger.error("异常", e);
        }
        return new ModelAndView("role/role_save");
    }

    /**
     * 批量更新角色权限
     */
    @RequestMapping("updateRoleV2.html")
    public void updateRoleV2(HttpServletRequest request,
                             HttpServletResponse response,
                             String menuCodeString,
                             String roleId) throws IOException {
        String js;
        try {
            // 查询出将要修改的角色
            SysRoles roles = sysRolesService.queryById(roleId);
            // 查询当前用户数据库中的权限状态
            MenuRole menuRole = new MenuRole();
            menuRole.setRolePower(roles.getRoleName());
            List<Menu> menuList = menuRoleService.menuJoinMenuRole(menuRole);
            // 当前选中的权限号码
            List<String> checkedMenuCodeArray = Arrays.asList(menuCodeString.split(","));
            menuRoleService.updateRoleV2(menuList, checkedMenuCodeArray, roles);
            // 清除用户权限信息的缓存
            List<SysUsers> sysUsers = sysUsersService.queryUserByRole(roleId);
            for (SysUsers user:sysUsers) {
                userCache.removeUserFromCache(user.getUserAccount());
			}
            js = "<script>alert('修改成功');location.href='../system/roles/list.html'</script>";
        } catch (Exception e) {
            logger.error("异常", e);
            js = "<script>alert('异常');location.href='../system/roles/list.html'</script>";
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(js);
    }

}
