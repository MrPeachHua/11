package com.boxiang.share.product.carlife.controller;
import java.util.Date;
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
import com.boxiang.share.product.carlife.po.CarlifeEventPage;
import com.boxiang.share.product.carlife.service.CarlifeEventPageService;
import com.boxiang.share.user.po.UserInfo;

@Controller
@RequestMapping("products/carlife/eventpage")
public class CarlifeEventPageController extends BaseController{
	private static final Logger logger = Logger.getLogger(CarlifeSrvInfoController.class);
	@Resource private CarlifeEventPageService carlifeEventPageService;
	@RequestMapping("list.html")
	public ModelAndView list(HttpServletRequest request , HttpServletResponse response){
		Page<CarlifeEventPage> pageList = new Page<CarlifeEventPage>();
		PageHelper.initPage(request, pageList);
		pageList.getParams().put("isUsed", Constants.TRUE);
		pageList = carlifeEventPageService.queryListPage(pageList);
		PageHelper.setPageModel(request, pageList);
		return new ModelAndView("products/carlife/eventpage_list");
	}
	@RequestMapping("add.html")
	public ModelAndView addEventPage(HttpServletRequest request , HttpServletResponse response){
		return new ModelAndView("products/carlife/eventpage_add");
	}
	@RequestMapping("event_add.html")
	public ModelAndView dictadd(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("carlifeEventPage") CarlifeEventPage carlifeEventPage) {
		try {
			CarlifeEventPage carLife  = new CarlifeEventPage();
			carLife.setIsUsed(Constants.TRUE);
			carLife.setImageType(carlifeEventPage.getImageType());
			int i = 0;
			if(carlifeEventPageService.selectList(carLife) !=null)
				i = carlifeEventPageService.selectList(carLife).size();
			if("1".equals(carlifeEventPage.getImageType())){
				//轮播图
				if(i<5){
					//可以添加
					UserInfo currUser = (UserInfo) super.getLoginUser(request);
					carlifeEventPage.setCreateor(currUser.getUserNum());
					carlifeEventPage.setCreateDate(new Date());
					carlifeEventPage.setIsUsed(Constants.TRUE);
					carlifeEventPageService.add(carlifeEventPage);
					request.setAttribute("info", "添加成功");
				}else{
					//不可用添加
					request.setAttribute("info", "添加失败!只能存在5条有效轮播活动！");
					request.setAttribute("add", "fail");
				}
			}else if("2".equals(carlifeEventPage.getImageType())){
				//车管家
				if(i<1){
					//可以添加
					UserInfo currUser = (UserInfo) super.getLoginUser(request);
					carlifeEventPage.setCreateor(currUser.getUserNum());
					carlifeEventPage.setCreateDate(new Date());
					carlifeEventPage.setIsUsed(Constants.TRUE);
					carlifeEventPageService.add(carlifeEventPage);
					request.setAttribute("info", "添加成功");
				}else{
					//不可用添加
					request.setAttribute("info", "添加失败!只能存在1条有效车管家图片！");
					request.setAttribute("add", "fail");
				}
			}
			
			
		} catch (Exception e) {
			request.setAttribute("info", "添加失败");
			request.setAttribute("add", "fail");
			e.printStackTrace();
			logger.error("添加出现异常",e);
		}
		return new ModelAndView("products/carlife/eventpage/save",null);
	}
	@RequestMapping("/{id}/del.html")
	public ModelAndView carLifedel(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		if(id==0){
			throw new NullPointerException("轮播活动不能为空!");
		}
		try {
			CarlifeEventPage carlifeEventPage = carlifeEventPageService.queryById(id);
			carlifeEventPage.setIsUsed(Constants.FALSE);
			carlifeEventPageService.update(carlifeEventPage);
			request.setAttribute("info", "删除成功");
		} catch (NumberFormatException nfe) {
			logger.error("轮播活动id转换类型失败，请检查参数是否正确！id="+id+".",nfe);
			throw new NumberFormatException("轮播活动id转换类型失败，请检查参数是否正确！"+id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除轮播活动出现异常",e);
		}
		return new ModelAndView("products/carlife/eventpage/save",null);
	}
	
	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		if(id==0){
			throw new NullPointerException("ID不能为空!");
		}
		Map<String,Object> map = super.getParamMap(request);
		try {
			CarlifeEventPage carlifeEventPage = carlifeEventPageService.queryById(id);
			map.put("carlifeEventPage", carlifeEventPage);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("信息出现异常",e);
		}
		return new ModelAndView("products/carlife/eventpage_edit",map);
	}
	
	@RequestMapping("event_edit.html")
	public ModelAndView dictupdate(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("carlifeEventPage") CarlifeEventPage carlifeEventPage) {
		try {
			CarlifeEventPage carlife= new CarlifeEventPage();
			carlife.setIsUsed(Constants.TRUE);
			carlife.setImageType(carlifeEventPage.getImageType());
			boolean flag = true;
			int i = 0;
			if(carlifeEventPageService.selectList(carlife) !=null)
				i=carlifeEventPageService.selectList(carlife).size();
			if("1".equals(carlifeEventPage.getImageType())){
				if(i>5){
					flag = false;
				}else if(i==5){
					int key = 0;//判断当条数据的id与其他5条数据的id都不相等的次数
					for(CarlifeEventPage carPage:carlifeEventPageService.selectList(carlife)){
						if(carPage.getId().equals(carlifeEventPage.getId())){
							flag = true;
						}else{
							key++;
						}
					}
					//当都不相等的时候 设置不能修改
					if(key==5){
						flag=false;
					}
				}
				if(flag){
					UserInfo currUser = (UserInfo) super.getLoginUser(request);
					carlifeEventPage.setModifyDate(new Date());
					carlifeEventPage.setModified(currUser.getUserNum());
					carlifeEventPage.setIsUsed(Constants.TRUE);
					carlifeEventPageService.update(carlifeEventPage);
					request.setAttribute("info", "修改成功");
				}else{
					request.setAttribute("info", "修改失败!只能存在5条有效轮播活动！");
					request.setAttribute("add", "fail");
				}
			}else if("2".equals(carlifeEventPage.getImageType())){
				if(i>1){
					flag = false;
				}else if(i==1){
					for(CarlifeEventPage carPage:carlifeEventPageService.selectList(carlife)){
						if(!carPage.getId().equals(carlifeEventPage.getId())){
							flag = false;
						}
					}
				}
				if(flag){
					UserInfo currUser = (UserInfo) super.getLoginUser(request);
					carlifeEventPage.setModifyDate(new Date());
					carlifeEventPage.setModified(currUser.getUserNum());
					carlifeEventPage.setIsUsed(Constants.TRUE);
					carlifeEventPageService.update(carlifeEventPage);
					request.setAttribute("info", "修改成功");
				}else{
					request.setAttribute("info", "修改失败!只能存在1条有效车管家活动！");
					request.setAttribute("add", "fail");
				}
			}
		} catch (Exception e) {
			request.setAttribute("info", "修改失败");
			request.setAttribute("add", "fail");
			e.printStackTrace();
			logger.error("修改轮播活动出现异常",e);
		}
		return new ModelAndView("products/carlife/eventpage/save",null);
	}
}
