package com.boxiang.share.system.controller;

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
import com.boxiang.share.system.po.MessagePub;
import com.boxiang.share.system.service.MessagePubService;
import com.boxiang.share.user.po.UserInfo;

@Controller
@RequestMapping("system/msgpub")
public class MessagePubController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(MessagePubController.class);
	
	@Resource private MessagePubService messagePubService;
	
	@RequestMapping("list.html")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		try {
			Page<MessagePub> page = new Page<MessagePub>();
			PageHelper.initPage(request, page);
			//page.getParams().put("isUsed", Constants.FALSE);
			Map<String, Object> map = super.getParamMap(request);
			String queryType = (String)map.get("queryType");
			String queryValue = (String)map.get("queryValue");
			if(!StringUtils.isEmpty(queryType)){
				switch (Integer.parseInt(queryType)) {
				case 1://标题
					page.getParams().put("title", queryValue);
					break;
				case 2://消息内容
					page.getParams().put("message", queryValue);
					break;
	
				default:
					break;
				}
			}
			page = messagePubService.queryListPage(page);
			PageHelper.setPageModel(request, page);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询广播信息出现异常",e);
		}
		return new ModelAndView("system/msgpub_list");
	}
//删除
	@RequestMapping("{messageId}/msgdel.html")
	public ModelAndView msgdel(@PathVariable int messageId, HttpServletRequest request, HttpServletResponse response) {
		if(messageId==0){
			throw new NullPointerException("广播ID不能为空!");
		}
		try {
			MessagePub messagePub=messagePubService.queryById(messageId);
			messagePub.setIsUsed(Constants.FALSE);
			messagePubService.update(messagePub);
			request.setAttribute("info", "删除成功");
		} catch (NumberFormatException nfe) {
			logger.error("广播id转换类型失败，请检查参数是否正确！userId="+messageId+".",nfe);
			throw new NumberFormatException("广播id转换类型失败，请检查参数是否正确！"+messageId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除广播信息出现异常",e);
		}
		return new ModelAndView("system/msgpub_save",null);
	}
	//跳转添加
	@RequestMapping("msgadd.html")
	public ModelAndView msgadd(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("system/msgpub_add");
	}
	//添加
	@RequestMapping("msgsave.html")
	public ModelAndView magsave(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("messagepub") MessagePub messagePub) {
		try {
			MessagePub messagePub2=new MessagePub();
			messagePub2.setIsUsed(Constants.TRUE);
			List<MessagePub> list=messagePubService.selectList(messagePub2);
			if(list!=null&&list.size()>0){
				request.setAttribute("info", "添加失败!只能存在一条有效的广播信息！");
				request.setAttribute("add", "fail");
			}else{
				UserInfo currUser = (UserInfo) super.getLoginUser(request);
				messagePub.setCreateor(currUser.getUserNum());
				messagePub.setCreateDate(new Date());
				messagePub.setIsUsed(Constants.TRUE);
				messagePubService.add(messagePub);
				request.setAttribute("info", "添加成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加广播信息出现异常",e);
		}
		return new ModelAndView("system/msgpub_save",null);
	}
}
