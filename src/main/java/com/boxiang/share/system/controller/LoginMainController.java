package com.boxiang.share.system.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.security.MyUsers;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.user.service.MenuService;
import com.boxiang.share.user.service.UserInfoService;

@Controller
@RequestMapping("/main")
public class LoginMainController {
	private static final Logger logger = Logger.getLogger(LoginMainController.class);
	
	@Resource 	private UserInfoService userInfoService;
	
	@Resource   private MenuService menuService;

	/**
	 * 跳转到commonpage页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String getCommonPage(HttpServletRequest request,HttpServletResponse response) {
		logger.info("Received request to show common page...");

		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			MyUsers userDetails = (MyUsers) authentication.getPrincipal();
			/**
			 * 为了避免改动太大，暂时用原来的用户类
			 */
			UserInfo userInfos = new UserInfo();
			userInfos.setSysUserId(userDetails.getSysUsers().getUserId());
			userInfos.setIsUsed(Constants.TRUE);
			List<UserInfo> userInfoList = userInfoService.selectList2(userInfos);
			if (userInfoList!=null&&userInfoList.size()>0){
				logger.info("test..................");
				logger.info( userInfoList.get(0).getParkingId());
				request.getSession().setAttribute(Constants.LOGIN_USER, userInfoList.get(0));
				request.getSession().setAttribute("menuMap", userDetails.getMenuMap());
				return "frame/main_v2";
			}else {
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/auth/logout";
			   String	js = "<script>alert('账号或者密码错误');location.href='"+basePath+"';</script>";
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(js);
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("出现异常",e);
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/auth/logout";
			return "redirect:"+basePath;
		}

	}

	/**
	 * 跳转到adminpage页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getAadminPage() {
		logger.debug("Received request to show admin page");
		return "adminpage";

	}

}
