package com.boxiang.share.system.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller  
public class LoginLogoutController {
	protected static Logger logger = Logger.getLogger(LoginLogoutController.class);  
/*
    @RequestMapping("/")
	public String index(){
		return "redirect:/login.jsp";
	}*/
    /** 
     * 指向登录页面 
     */  
    @RequestMapping(value = "auth/login", method = RequestMethod.GET)  
    public String getLoginPage(  
            @RequestParam(value = "error", required = false) boolean error,  
            ModelMap model) {  
  
        logger.debug("Received request to show login page");  
  
        if (error == true) {  
            // Assign an error message  
            model.put("error",  
                    "您的账号或密码错误，请重新输入！");
        } else {  
            model.put("error", "");  
        }  
        return "auth/loginpage_v2";
  
    }  
  
    /** 
     * 指定无访问权限页面 
     *  
     * @return 
     */  
    @RequestMapping(value = "auth/denied", method = RequestMethod.GET)  
    public String getDeniedPage() {  
  
        logger.debug("Received request to show denied page");  
  
        return "auth/deniedpage";  
  
    }  
}
