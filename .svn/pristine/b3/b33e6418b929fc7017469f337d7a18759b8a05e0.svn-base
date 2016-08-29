package com.boxiang.share.product.carlife.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.boxiang.share.product.carlife.po.CarlifeSrvInfo;
import com.boxiang.share.product.carlife.service.CarlifeSrvInfoService;
import com.boxiang.share.system.po.Dictionary;
import com.boxiang.share.system.service.DictionaryService;
import com.boxiang.share.user.po.UserInfo;

@Controller
@RequestMapping("products/carlife/srvinfo")
public class CarlifeSrvInfoController extends BaseController {

	private static final Logger logger = Logger.getLogger(CarlifeSrvInfoController.class);

	@Resource private CarlifeSrvInfoService carlifeSrvInfoService;
	@Resource private DictionaryService dictionaryService;
	@RequestMapping("list.html")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		try {
			Page<CarlifeSrvInfo> page = new Page<CarlifeSrvInfo>();
			PageHelper.initPage(request, page);
			page.getParams().put("isUsed", Constants.TRUE);
			Map<String, Object> map = super.getParamMap(request);
			String queryType = (String)map.get("queryType");
			String queryValue = (String)map.get("queryValue");
			if(!StringUtils.isEmpty(queryType)){
				switch (Integer.parseInt(queryType)) {
				case 1://服务名称
					page.getParams().put("srvName", queryValue);
					break;
				default:
					break;
				}
			}
			page = carlifeSrvInfoService.queryListPage(page);
			PageHelper.setPageModel(request, page);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询服务信息出现异常",e);
		}
		return new ModelAndView("products/carlife/srvinfo_list");
	}
	//删除
	@RequestMapping("{id}/del.html")
	public ModelAndView msgdel(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		if(id==0){
			throw new NullPointerException("车管家服务信息ID不能为空!");
		}
		try {
			CarlifeSrvInfo carlifeSrvInfo=carlifeSrvInfoService.queryById(id);
			carlifeSrvInfo.setIsUsed(Constants.FALSE);
			carlifeSrvInfoService.update(carlifeSrvInfo);
			request.setAttribute("info", "删除成功");
		} catch (NumberFormatException nfe) {
			logger.error("车管家服务信息ID转换类型失败，请检查参数是否正确！userId="+id+".",nfe);
			throw new NumberFormatException("车管家服务信息ID转换类型失败，请检查参数是否正确！"+id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除车管家服务信息出现异常",e);
		}
		return new ModelAndView("products/carlife/srvinfo_save",null);
	}
	//跳转添加
	@RequestMapping("add.html")
	public ModelAndView msgadd(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = super.getParamMap(request);
		int max=carlifeSrvInfoService.querySortMax();
		Dictionary dictionary=new Dictionary();
		dictionary.setDictCode("srv_type");
		List<Dictionary> dictionaryList=dictionaryService.selectList(dictionary);
		map.put("dictionary", dictionaryList);
		Dictionary dict = new Dictionary();
		dict.setDictCode("srv_flag");
		List<Dictionary> dictList = dictionaryService.selectList(dict);
		map.put("dictList", dictList);
		map.put("max",max+1);
		return new ModelAndView("products/carlife/srvinfo_add",map);
	}
	//跳转编辑
	@RequestMapping("{id}/edit.html")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,@PathVariable int id) {
		if(id==0){
			throw new NullPointerException("ID不能为空!");
		}	
		Map<String,Object> map = super.getParamMap(request);
		try {
			Dictionary dictionary=new Dictionary();
			dictionary.setDictCode("srv_type");
			List<Dictionary> dictionaryList=	dictionaryService.selectList(dictionary);
			CarlifeSrvInfo carlifeSrvInfo = carlifeSrvInfoService.queryById(id);
			map.put("carlifeSrvInfo", carlifeSrvInfo);
			map.put("dictionary", dictionaryList);
			Dictionary dict = new Dictionary();
			dict.setDictCode("srv_flag");
			List<Dictionary> dictList = dictionaryService.selectList(dict);
			map.put("dictList", dictList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("信息出现异常",e);
		}
		return new ModelAndView("products/carlife/srvinfo_edit",map);
	}
	//添加
	@RequestMapping("save.html")
	public ModelAndView magsave(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("carlifeSrvInfo") CarlifeSrvInfo carlifeSrvInfo) {
		try {
			CarlifeSrvInfo carlifeSrvInfo2=new CarlifeSrvInfo();
			carlifeSrvInfo2.setSrvName(carlifeSrvInfo.getSrvName());
			carlifeSrvInfo2.setSrvType(carlifeSrvInfo.getSrvType());
			carlifeSrvInfo2.setIsUsed(Constants.TRUE);
			List<CarlifeSrvInfo> list = carlifeSrvInfoService.selectList(carlifeSrvInfo2);
			if (list != null && list.size() > 0) {
				request.setAttribute("info", "存在重复数据！");
				request.setAttribute("add", "info");
			} else {
				UserInfo currUser = (UserInfo) super.getLoginUser(request);
				carlifeSrvInfo.setCreateor(currUser.getUserNum());
				carlifeSrvInfo.setCreateDate(new Date());
				// carlifeSrvInfo.setLogoPath(request.getParameter("savePath"));
				carlifeSrvInfo.setIsUsed(Constants.TRUE);
				// carlifeSrvInfo.setStatus(Constants.TRUE);
				carlifeSrvInfoService.add(carlifeSrvInfo);
				request.setAttribute("info", "添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加服务信息出现异常", e);
		}
		return new ModelAndView("products/carlife/srvinfo_save", null);
	}
	@RequestMapping("update.html")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("carlifeSrvInfo") CarlifeSrvInfo carlifeSrvInfo) {
		try {
			CarlifeSrvInfo carlifeSrvInfo2=new CarlifeSrvInfo();
			carlifeSrvInfo2.setSrvName(carlifeSrvInfo.getSrvName());
			carlifeSrvInfo2.setSrvType(carlifeSrvInfo.getSrvType());
			carlifeSrvInfo2.setIsUsed(Constants.TRUE);
			List<CarlifeSrvInfo> list = carlifeSrvInfoService.selectList(carlifeSrvInfo2);
			boolean flag = true;
			if (list != null && list.size() > 1) {
				flag = false;
			} else if(list.size()==1){
				if(!carlifeSrvInfo.getId().equals(list.get(0).getId())){
					flag = false;
				}
			} 
			if(flag){
				UserInfo currUser = (UserInfo) super.getLoginUser(request);
				carlifeSrvInfo.setModified(currUser.getUserNum());
				carlifeSrvInfo.setModifyDate(new Date());
				//	carlifeSrvInfo.setLogoPath(request.getParameter("savePath"));
				//carlifeSrvInfo.setIsUsed(Constants.TRUE);
				//carlifeSrvInfo.setStatus(Constants.TRUE);
				carlifeSrvInfoService.update(carlifeSrvInfo);
				request.setAttribute("info", "修改成功");
			}else{
				request.setAttribute("info", "存在重复数据！");
				request.setAttribute("add", "info");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改服务信息出现异常",e);
			request.setAttribute("info", "请检查参数是否完整！");
		}
		return new ModelAndView("products/carlife/srvinfo_save",null);
	}
	
}
