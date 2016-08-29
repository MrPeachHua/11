package com.boxiang.share.user.controller;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.user.dao.RegionUserDao;
import com.boxiang.share.user.po.RegionUser;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.user.service.RegionUserService;
import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("regionUser")
public class RegionUserController extends BaseController {
	private static final Logger logger = Logger.getLogger(RegionUserController.class);

	@Resource
	private RegionUserService regionUserService;
	@Resource
	private ParkingService parkingService;
	/**
	 * 区域负责人列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("list.html")
	public ModelAndView userlist(HttpServletRequest request, HttpServletResponse response) {
		try {
			String parkingName = request.getParameter("parkingName");
			String regionManager = request.getParameter("regionManager");
			String regionLeader = request.getParameter("regionLeader");
			Page<RegionUser> page = new Page<RegionUser>();
			PageHelper.initPage(request, page);
			//page.getParams().put("isUsed", Constants.TRUE);
			page.getParams().put("parkingName", parkingName);
			page.getParams().put("regionManager", regionManager);
			page.getParams().put("regionLeader", regionLeader);
			Map<String, Object> map = super.getParamMap(request);
			page = regionUserService.queryListPage(page);
			PageHelper.setPageModel(request, page);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询区域负责人",e);
		}
		return new ModelAndView("user/regionuser/region_user_list");
	}
	/**
	 * 删除
	 */
	@RequestMapping("{id}/delete.html")
	public void delete(@PathVariable int id, HttpServletResponse response) throws IOException {
		String js;
		try {
			regionUserService.delete(id);
			js = "<script>alert('删除成功');location.href='../list.html';</script>";
		} catch (Exception e) {
			logger.error("异常", e);
			js = "<script>alert('异常');location.href='../list.html';</script>";
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(js);
	}

	/**
	 * 前往新增页面
	 */
	@RequestMapping("/add.html")
	public ModelAndView add() {
		return new ModelAndView("user/regionuser/region_user_add");
	}

	/**
	 * 添加
	 */
	@RequestMapping("save.html")
	public String save(HttpServletRequest request,
					   HttpServletResponse response,
					   @ModelAttribute("couponRule") RegionUser regionUser) {
		try {
			regionUser.setCreateDate(new Date());
			regionUser.setCreateor("admin");
			regionUserService.add(regionUser);
			String js = "<script>alert('添加成功');location.href='list.html';</script>";
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(js);
			return null;
		} catch (Exception e) {
			request.setAttribute("info", "异常，请检查参数是否填写正确");
			logger.error("异常", e);
			return "regionUser/region_user_add";
		}
	}

	/**
	 * 进入修改页面
	 */
	@RequestMapping("{id}/edit.html")
	public ModelAndView edit(@PathVariable int id, HttpServletRequest request) {
		RegionUser params = new RegionUser();
		params.setId(id);
		List<RegionUser> list = regionUserService.selectList(params);
		if(list!=null&&list.size()>0){
			RegionUser regionUser=list.get(0);
			Parking parking=parkingService.queryById(regionUser.getParkingId());
			request.setAttribute("parkingName",parking.getParkingName());
			request.setAttribute("entity",regionUser);
		}

		return new ModelAndView("user/regionuser/region_user_edit");
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update.html")
	public String update(@ModelAttribute("regionUser") RegionUser regionUser, HttpServletResponse response) throws IOException {
		try {
			//regionUser.setIsUsed(Constants.TRUE);
			regionUserService.update(regionUser);
			return "redirect:list.html";
		} catch (Exception e) {
			logger.error("异常", e);
			String js = "<script>alert('异常');location.href='" + regionUser.getId() + "/edit.html';</script>";
			response.getWriter().print(js);
			return null;
		}
	}

}
