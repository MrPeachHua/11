package com.boxiang.share.product.advertising.controller;

import java.util.Date;
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

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.advertising.po.Advertising;
import com.boxiang.share.product.advertising.service.AdvertisingService;
import com.boxiang.share.system.po.MessagePub;
import com.boxiang.share.user.po.UserInfo;

@Controller
@RequestMapping("products/advertising")
public class AdvertisingController extends BaseController{

	private static final Logger logger = Logger.getLogger(AdvertisingController.class);

	@Resource
	private AdvertisingService advertisingService;

	@RequestMapping("list.html")
	public ModelAndView list(String name, HttpServletRequest request , HttpServletResponse response){
		Page<Advertising> pageList = new Page<Advertising>();
		PageHelper.initPage(request, pageList);
		pageList.getParams().put("name", name);
		pageList = advertisingService.queryListPage(pageList);
		PageHelper.setPageModel(request, pageList);
		return new ModelAndView("products/advertising/advertising_list");
}

	@RequestMapping("add.html")
	public ModelAndView addEadvertisingtPage(HttpServletRequest request , HttpServletResponse response){
		return new ModelAndView("products/advertising/advertising_add");
	}

	@RequestMapping("adver_add.html")
	public ModelAndView adveradd(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("advertisingtPage") Advertising advertisingtPage) {
//		try {
//			advertisingtPage.setCreateDate(new Date());
//			advertisingService.add(advertisingtPage);
//			request.setAttribute("info", "添加成功");
//		} catch (Exception e) {
//			request.setAttribute("info", "添加失败");
//			request.setAttribute("add", "fail");
//			e.printStackTrace();
//			logger.error("添加出现异常",e);
//		}
//		return new ModelAndView("products/advertising/save",null);
		try {
			Advertising advertising=new Advertising();
			advertising.setIsUsed(Constants.TRUE);
			List<Advertising> list=advertisingService.selectList(advertising);
			if(list!=null&&list.size()>0&&advertisingtPage.getIsUsed().equals("1")){
				request.setAttribute("info", "添加失败!只能存在一条有效的推广信息！");
				request.setAttribute("add", "fail");
			}else{
				advertising.setIsUsed(Constants.TRUE);
				advertisingtPage.setCreateDate(new Date());
				advertisingService.add(advertisingtPage);
				request.setAttribute("info", "添加成功");
			}
		} catch (Exception e) {
			request.setAttribute("info", "添加失败");
			request.setAttribute("add", "fail");
			e.printStackTrace();
			logger.error("添加出现异常",e);
		}
		return new ModelAndView("products/advertising/save",null);
	}
	
	
	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		if(id==0){
			throw new NullPointerException("ID不能为空!");
		}
		Map<String,Object> map = super.getParamMap(request);
		try {
			Advertising advertising = advertisingService.queryById(id);
			map.put("advertising", advertising);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("信息出现异常",e);
		}
		return new ModelAndView("products/advertising/advertising_edit",map);
	}


	@RequestMapping("adver_edit.html")
	public ModelAndView adverupdate(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("advertisingtPage") Advertising advertisingtPage) {
		try {
			if (advertisingtPage.getIsUsed().equals(Constants.TRUE)) {
				Advertising advertising = new Advertising();
				advertising.setId(advertisingtPage.getId());
				advertising.setIsUsed(Constants.TRUE);
				List<Advertising> list = advertisingService.selectList(advertising);
				if (list != null && list.size() > 0) {
					request.setAttribute("info", "修改失败，已存在可用的图片");
					request.setAttribute("advertising", advertisingtPage);
					return new ModelAndView("products/advertising/advertising_edit");
				}
			}
			request.setAttribute("info", "修改成功！");
			advertisingService.update(advertisingtPage);
		} catch (Exception e) {
			request.setAttribute("info", "修改失败");
			request.setAttribute("add", "fail");
			logger.error("修改出现异常", e);
		}
		return new ModelAndView("products/advertising/save", null);
	}
	@RequestMapping("/{id}/del.html")
	public ModelAndView adverdel(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		try {
			advertisingService.delete(id);
			request.setAttribute("info", "删除成功");
		}  catch (Exception e) {
			e.printStackTrace();
			logger.error("删除出现异常",e);
		}
		return new ModelAndView("products/advertising/save",null);
	}
	
}
