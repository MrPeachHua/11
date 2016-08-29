package com.boxiang.share.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.controller.BaseController;

@Controller
@RequestMapping("frame")
public class MainController extends BaseController {

	@RequestMapping("down.html")
	public ModelAndView down(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("frame/down");
	}

	@RequestMapping("top.html")
	public ModelAndView top(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("frame/top");
	}

	@RequestMapping("menu.html")
	public ModelAndView menu(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("frame/left_v2");
	}

	@RequestMapping("desktop.html")
	public ModelAndView desktop(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("frame/center");
	}

	@RequestMapping("page.html")
	public ModelAndView page(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("frame/page_v2");
	}

}
