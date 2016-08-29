package com.boxiang.share.sample.student.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.product.order.service.OrderTemporaryService;
import com.boxiang.share.sample.student.po.Student;
import com.boxiang.share.sample.student.service.IStudentService;
import com.boxiang.share.utils.MD5Util;

@Controller
@RequestMapping("sample/student")
public class StudentController extends BaseController {
	@Resource 	private IStudentService studentService;
	@Resource 	private OrderTemporaryService orderTemporaryService;
	@Resource   private RestTemplate restTemplate;

	@RequestMapping("temporary/{customerId}/{carNumber}")
	public void temporary(@PathVariable String customerId, @PathVariable String carNumber, HttpServletRequest request, HttpServletResponse response) {
		try {
			String msg = orderTemporaryService.getCarlist(customerId, carNumber);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html;charset=UTF-8");
			out.print(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("temporary/{customerId}")
	public void temporary(@PathVariable String customerId, HttpServletRequest request, HttpServletResponse response) {
		try {
			String msg = orderTemporaryService.getCarlist(customerId, null);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html;charset=UTF-8");
			out.print(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("{stuId}")
	public ModelAndView queryById(@PathVariable int stuId, HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("333");
			Student student = studentService.queryById(stuId);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html;charset=UTF-8");
			out.print("【学生主键ID：" + student.getStuId() + "】，【学生姓名：" + student.getStuName() + "】");
			ModelAndView mv = new ModelAndView("student");
			mv.addObject("student", student);

			return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
    /**
     * Json对象格式化测试：Date
     * @return
     */
    @RequestMapping("/ccc") @ResponseBody 
    public Student ccc(){
    	Student student = new Student();
    	student.setStuId(1);
    	student.setStuName("小明");
    	student.setCreateDate(new Date());
        return student;
    }
	
	@RequestMapping("/ajaxTest") @ResponseBody
	public Map<String, Object> ajaxTest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-------------------------------------");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "TEST");
		map.put("content", "这是一个测试！");
		map.put("other", null);
		return map;
	}
    
    @RequestMapping("/aaa")
    public void aaa(HttpServletRequest request,HttpServletResponse response){
		try {
	        String url ="carlife/eventpage/list/{summary}";//请求接口地址
	        Map<String, Object> params = new HashMap<String, Object>();//请求参数
	        params.put("summary",MD5Util.sign("", null,"UTF-8"));
	        String resultJson = restTemplate.getForObject("http://127.0.0.1:9090/share/app/"+url, String.class,MD5Util.sign("", null,"UTF-8"));
			PrintWriter out = response.getWriter();
			response.setContentType("text/html;charset=UTF-8");
			out.print(resultJson);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @RequestMapping("/myjsp")
    public ModelAndView myjsp(){
    	return new ModelAndView("redirect:/other/register_local.html");
    }
}
