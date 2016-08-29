package com.boxiang.share.system.controller;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.share.utils.ShangAnMessageType;
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
import com.boxiang.share.system.po.Dictionary;
import com.boxiang.share.system.service.DictionaryService;
import com.boxiang.share.user.po.UserInfo;

@Controller
@RequestMapping("system")
public class DictionaryController extends BaseController{
	private static final Logger logger = Logger.getLogger(DictionaryController.class);
	@Resource private DictionaryService dictionaryService;

	/**
	 * 根据 dictCode 查询数据字典
	 */
	@RequestMapping("/dict/queryDictionary")
	public void queryDictionary(String dictCode, String dictValue, HttpServletResponse response) throws IOException {
		String msg;
		try {
			Dictionary dictionary = new Dictionary();
			dictionary.setDictCode(dictCode);
			if (StringUtils.isNotEmpty(dictValue)) {
				dictionary.setDictValue(dictValue);
			}
			dictionary.setIsUsed(Constants.TRUE);
			List<Dictionary> list = dictionaryService.selectList(dictionary);
			msg = ShangAnMessageType.toShangAnJson("0", "success", "data", list);
		} catch (Exception e) {
			logger.error("数据字典查询异常", e);
			msg = ShangAnMessageType.operateToJson("2", "error");
		}
		response.getWriter().print(msg);
	}

	/**
	 * 查询数据字典
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/dict/list.html")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		try {
			Page<Dictionary> page = new Page<Dictionary>();
			PageHelper.initPage(request, page);
			page.getParams().put("isUsed", Constants.TRUE);
			String queryCode = request.getParameter("p_queryCode");
			String queryValue = request.getParameter("p_queryValue");
			String queryRex = request.getParameter("p_queryRex");
			page.getParams().put("dictCode", queryCode);
			page.getParams().put("dictName", queryValue);
			page.getParams().put("memo",queryRex);
			page = dictionaryService.queryListPage(page);
			PageHelper.setPageModel(request, page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数据字典查询异常",e);
		}
		return new ModelAndView("system/dict_list");
	}
	/**
	 * 删除数据字典
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/dict/{id}/del.html")
	public ModelAndView userdel(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		if(id==0){
			throw new NullPointerException("字典不能为空!");
		}
		try {
			Dictionary dictionary = dictionaryService.queryById(id);
			dictionary.setIsUsed(Constants.FALSE);
			dictionaryService.update(dictionary);
			request.setAttribute("info", "删除成功");
		} catch (NumberFormatException nfe) {
			logger.error("字典id转换类型失败，请检查参数是否正确！id="+id+".",nfe);
			throw new NumberFormatException("字典id转换类型失败，请检查参数是否正确！"+id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除字典信息出现异常",e);
		}
		return new ModelAndView("system/save",null);
	}
	/**
	 * 进入修改字典数据页面
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/dict/{id}/edit.html")
	public ModelAndView edit(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		if(id==0){
			throw new NullPointerException("ID不能为空!");
		}
		Map<String,Object> map = super.getParamMap(request);
		try {
			Dictionary dictionary = dictionaryService.queryById(id);
			map.put("dictionary", dictionary);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("信息出现异常",e);
		}
		return new ModelAndView("system/dict_edit",map);
	}
	/**
	 * 修改字典数据
	 * @param request
	 * @param response
	 * @param dictionary
	 * @return
	 */
	@RequestMapping("dictupdate.html")
	public ModelAndView dictupdate(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("dictionary") Dictionary dictionary) {
		try {
			UserInfo currUser = (UserInfo) super.getLoginUser(request);
			dictionary.setModifyDate(new Date());
			dictionary.setModified(currUser.getUserNum());
			dictionary.setDictCode(request.getParameter("dictCode1"));
//			dictionary.setCreateor(currUser.getUserNum());
			dictionaryService.update(dictionary);
			request.setAttribute("info", "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改字典信息出现异常",e);
		}
		return new ModelAndView("system/save",null);
	}
	/**
	 * 进入字典添加页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("dict/add.html")
	public ModelAndView roleadd(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("system/dict_add");
	}
	/**
	 * 添加数据字典
	 * @param request
	 * @param response
	 * @param dictionary
	 * @return
	 */
	@RequestMapping("dict_add.html")
	public ModelAndView dictadd(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("dictionary") Dictionary dictionary) {
		try {
			Dictionary dic = new Dictionary();
			dic.setDictName(dictionary.getDictName());
			dic.setDictCode(dictionary.getDictCode());
			dic.setIsUsed(Constants.TRUE);
			List<Dictionary> list = dictionaryService.selectList(dic);
			if (list != null && list.size() > 0) {
				request.setAttribute("info", "存在重复数据！");
				request.setAttribute("add", "info");
			} else {
				UserInfo currUser = (UserInfo) super.getLoginUser(request);
				dictionary.setCreateor(currUser.getUserNum());
				dictionary.setCreateDate(new Date());
				dictionary.setIsUsed(Constants.TRUE);
				dictionaryService.add(dictionary);
				request.setAttribute("info", "添加成功");

			}
		} catch (Exception e) {
			request.setAttribute("info", "添加失败");
			e.printStackTrace();
			logger.error("添加字典信息出现异常",e);
			//return new ModelAndView("system/dict_add");
		}
		return new ModelAndView("system/save", null);
	}
}
