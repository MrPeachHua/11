package com.boxiang.share.app.sample.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.sample.student.po.Student;
import com.boxiang.share.sample.student.service.IStudentService;
import com.boxiang.share.utils.json.JacksonUtil;

@Controller
@RequestMapping("app/sample")
public class AppController extends BaseController {
	@Resource 	private IStudentService studentService;

	//http://127.0.0.1:9090/share/app/sample/3/25a0ca3ed86a2e177b6893563c22d0c2
	@RequestMapping("{stuId}/{summary}")
	public ModelAndView queryById(@PathVariable int stuId, HttpServletRequest request, HttpServletResponse response) {
		try {
			Student student = studentService.queryById(stuId);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html;charset=UTF-8");
			out.print(JacksonUtil.toJson(student));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
