package com.boxiang.share.user.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.user.po.Menu;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.user.service.MenuService;
import com.boxiang.share.user.service.UserInfoService;
import com.boxiang.share.utils.MD5Util;

@Controller
@RequestMapping("")
public class LoginController extends BaseController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@Resource 	private UserInfoService userInfoService;
	
	@Resource   private MenuService menuService;

	@RequestMapping("main.html")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("redirect:/login.jsp");
		try {
			Map<String, Object> map = super.getParamMap(request);
			String userNum =  (String)map.get("userNum");
			String userPw =  (String)map.get("userPw");
			//logger.info(userNum + "  ----------  "+ userPw);
			
			if(StringUtils.isEmpty(userNum) || StringUtils.isEmpty(userPw)){
				request.getSession().setAttribute("errorMsg","用户或密码不能为空！");
				return mv;
			}
			UserInfo userInfo = new UserInfo();
			userInfo.setUserNum(userNum);
			userInfo.setUserPw(MD5Util.sign(userPw, null, Constants.CHARACTER_ENCODING_UTF8));
			userInfo.setIsUsed(Constants.TRUE);
			List<UserInfo> userInfos = userInfoService.selectList(userInfo);
			if(userInfos!=null && userInfos.size()>0){
				request.getSession().setAttribute(Constants.LOGIN_USER, userInfos.get(0));
				request.getSession().removeAttribute("errorMsg");
				
				Map<Menu,List<Menu>> menuMap = new LinkedHashMap<Menu,List<Menu>>();
				Menu menu = new Menu();
				menu.setParentCode("10");
				menu.setRolePower(String.valueOf(userInfos.get(0).getRolePower()));
				List<Menu> menuList = menuService.queryByPower(menu);
				List<Menu> tmpList = null;
				for(Menu me:menuList){
					menu = new Menu();
					menu.setParentCode(me.getMenuCode());
					menu.setRolePower(String.valueOf(userInfos.get(0).getRolePower()));
					tmpList = menuService.queryByPower(menu);
					if(tmpList!=null && tmpList.size()>0){
						menuMap.put(me, tmpList);
					}
				}
				request.getSession().setAttribute("menuMap", menuMap);
				
				return new ModelAndView("frame/main");
			}
			request.getSession().setAttribute("errorMsg","用户或密码错误！");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("登录 出现异常",e);
		}
		return mv;
	}


	@RequestMapping("logout.html")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute(Constants.LOGIN_USER);
		ModelAndView mv = new ModelAndView("redirect:/login.jsp");
		return mv;
	}
}
