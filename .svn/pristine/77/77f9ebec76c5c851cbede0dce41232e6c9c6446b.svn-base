package com.boxiang.share.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.share.system.service.DictionaryService;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.user.service.UserInfoService;
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
import com.boxiang.share.user.po.DepartmentInfo;
import com.boxiang.share.user.service.DepartmentInfoService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("users")
public class DepartController extends BaseController {
	private static final Logger logger = Logger.getLogger(DepartController.class);
	
	@Resource private DepartmentInfoService departmentInfoService;
	@Resource private DictionaryService dictionaryService;
	@Resource private UserInfoService userInfoService;

	@RequestMapping("departlist.html")
	public ModelAndView departlist(HttpServletRequest request, HttpServletResponse response) {
		try {
			Page<DepartmentInfo> page = new Page<DepartmentInfo>();
			UserInfo userInfo=(UserInfo)super.getLoginUser(request);
			PageHelper.initPage(request, page);
			page.getParams().put("isUsed", Constants.TRUE);
			page.getParams().put("module", userInfo.getModule());
			page = departmentInfoService.queryListPage(page);
			PageHelper.setPageModel(request, page);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询部门信息出现异常",e);
		}
		return new ModelAndView("department/department_list");
	}

	/**
	 * 进入添加页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("departadd.html")
	public ModelAndView departadd(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("moduleDict", dictionaryService.getDictListByDictCode("module"));
		return new ModelAndView("department/department_add");
	}

	@RequestMapping("departsave.html")
	public ModelAndView departsave(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("departmentInfo") DepartmentInfo departmentInfo) {
		try {
			departmentInfoService.add(departmentInfo);
			request.setAttribute("info", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加部门信息出现异常",e);
		}
		return new ModelAndView("department/department_save");
	}

	/**
	 * 删除员工
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("{departmentId}/departdel.html")
	public void departdel(@PathVariable int departmentId, HttpServletRequest request, HttpServletResponse response)throws IOException {
		if(departmentId==0){
			throw new NullPointerException("部门ID不能为空!");
		}
		String js=null;
		try {
			UserInfo	userInfo=new UserInfo();
			userInfo.setIsUsed(Constants.TRUE);
			userInfo.setDepartmentId(departmentId);
			List<UserInfo> list=userInfoService.selectList(userInfo);
			if(list!=null&&list.size()>0){
				//request.setAttribute("info", "删除失败!此部门已被用户'"+list.get(0).getUserName()+"'绑定!");
				js = "<script>alert('删除失败!此部门已被用户"+list.get(0).getUserName()+"绑定!');location.href='../departlist.html';</script>";
				//'"+list.get(0).getUserName()+"'
			}else {
				departmentInfoService.deleteFalse(departmentId);
				//request.setAttribute("info", "删除成功");
				js = "<script>alert('删除成功');location.href='../departlist.html';</script>";
			}
		} catch (NumberFormatException nfe) {
			logger.error("部门id转换类型失败，请检查参数是否正确！userId="+departmentId+".",nfe);
			throw new NumberFormatException("部门id转换类型失败，请检查参数是否正确！"+departmentId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除部门信息出现异常",e);
		}
		/*return new ModelAndView("department/department_save");*/
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(js);
	}
}
