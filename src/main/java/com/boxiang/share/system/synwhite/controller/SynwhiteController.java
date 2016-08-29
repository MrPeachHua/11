package com.boxiang.share.system.synwhite.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.system.synwhite.po.ScheduleJob;
import com.boxiang.share.system.synwhite.po.WhitesynInfo;
import com.boxiang.share.system.synwhite.service.WhitesynInfoService;
import com.boxiang.share.system.synwhite.service.impl.QuartzService;
import com.boxiang.share.user.po.UserInfo;


@Controller
@RequestMapping("system/whitesyn")
public class SynwhiteController extends BaseController {
	@Resource
	private OrderMainService orderMainService;
	@Resource
	private WhitesynInfoService whitesynInfoService;
    /**
     * 查询
     */
    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
    	logger.info("query.....");
    	ModelAndView model = new ModelAndView("/system/synwhite/whitesyn_list");
		List<ScheduleJob> jobList = null;
		String parkingStr = null;
		try {
			jobList = quartzService.getJobAll();
			WhitesynInfo wsParam = new WhitesynInfo();
			wsParam.setName("syn_parking_str");
			List<WhitesynInfo> wList = whitesynInfoService.selectList(wsParam);
			if(null!=wList&&wList.size()>0){
				parkingStr = wList.get(0).getValue();
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserInfo user = (UserInfo) super.getLoginUser(request);
		model.addObject("user", user);
		model.addObject("jobList", jobList);
		model.addObject("parkingStr",parkingStr);
        return model;
    }
    
private Logger logger = LoggerFactory.getLogger(SynwhiteController.class);
	
	@Autowired
	private QuartzService quartzService;

	/**
	 * 查询正在运行的定时工作任务
	 * @throws Exception 
	 */
	@RequestMapping(value="query")
	public String query(Model model) throws Exception
	{
		logger.info("query.....");
		List<ScheduleJob> jobList= quartzService.getJobAll();
		model.addAttribute("jobList", jobList);
		return "quartz/quartzInfoList";
	}
	
	
	

	
	/**
	 * 更新任务表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 * @throws Exception 
	 */
	@RequestMapping(value="rescheduleJobForm")
	public void rescheduleJob(
			ScheduleJob scheduleJob,
			HttpServletRequest request, //
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		WhitesynInfo wsParam = new WhitesynInfo();
		wsParam.setName("syn_parking_str");
		List<WhitesynInfo> wList = whitesynInfoService.selectList(wsParam);
		if(null!=wList&&wList.size()>0){
			WhitesynInfo whitesynInfo = wList.get(0);
			whitesynInfo.setValue(scheduleJob.getParkingStr());
			whitesynInfoService.update(whitesynInfo);
		}
		WhitesynInfo wsParam1 = new WhitesynInfo();
		wsParam.setName("syn_time");
		List<WhitesynInfo> wList1 = whitesynInfoService.selectList(wsParam);
		if(null!=wList1&&wList1.size()>0){
			WhitesynInfo whitesynInfo = wList1.get(0);
			whitesynInfo.setValue(scheduleJob.getCronExpression());
			whitesynInfoService.update(whitesynInfo);
		}
		try {
			logger.info("rescheduleJob start");
			// 1、设置字符编码
			response.setCharacterEncoding("UTF-8");
			// 2、内容的类型
			response.setContentType("text/html;charset=UTF-8");
			
			quartzService.rescheduleJob(scheduleJob);
			out.write("保存成功");
			logger.info("保存成功");
		} catch (Exception e) {
			out.write("保存失败");
			logger.error("保存失败",e);
		}
	}
	
	/**
	 * 立即执行任务一次
	 * @param scheduleJob
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="triggerJobForm")
	public void triggerJobForm(
			ScheduleJob scheduleJob,
			HttpServletRequest request, //
			HttpServletResponse response) throws Exception {
		/*Dictionary dicParam = new Dictionary();
		dicParam.setDictCode("quartz_parking_scope");
		List<Dictionary> dicList = dictionaryService.selectList(dicParam);
		if(null!=dicList&&dicList.size()>0){
			Dictionary dic = dicList.get(0);
			dic.setDictValue(scheduleJob.getParkingStr());
			dictionaryService.update(dic);
		}
		*/
		PrintWriter out = response.getWriter();
		try {
			logger.info("rescheduleJob start");
			// 1、设置字符编码
			response.setCharacterEncoding("UTF-8");
			// 2、内容的类型
			response.setContentType("text/html;charset=UTF-8");
			
			quartzService.triggerJob(scheduleJob);
			out.write("执行成功");
			logger.info("执行成功");
		} catch (Exception e) {
			out.write("执行失败");
			logger.error("执行失败");
		}
	}
	
	/**
	 * 立即执行任务一次
	 * @param scheduleJob
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="excuteQuatzOnece")
	public void excuteQuatzOnece(
			ScheduleJob scheduleJob,
			HttpServletRequest request, //
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		try {
			logger.info("rescheduleJob start");
			// 1、设置字符编码
			response.setCharacterEncoding("UTF-8");
			// 2、内容的类型
			response.setContentType("text/html;charset=UTF-8");
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("parkingStr", scheduleJob.getParkingStr());
			orderMainService.synWhiteList(param);
			out.write("执行成功");
			logger.info("执行成功");
		} catch (Exception e) {
			out.write("执行失败");
			logger.error("执行失败");
		}
	}
	
	
	/**
	 * 暂停任务执行
	 * @param scheduleJob
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="pauseJobForm")
	public void pauseJobForm(
			ScheduleJob scheduleJob,
			HttpServletRequest request, //
			HttpServletResponse response) throws Exception {
		
		PrintWriter out = response.getWriter();
		try {
			logger.info("rescheduleJob start");
			// 1、设置字符编码
			response.setCharacterEncoding("UTF-8");
			// 2、内容的类型
			response.setContentType("text/html;charset=UTF-8");
			
			quartzService.pauseJob(scheduleJob);
			out.write("暂停成功");
			logger.info("暂停成功");
		} catch (Exception e) {
			out.write("暂停失败");
			logger.error("暂停失败");
		}
	}
	
	
	/**
	 * 恢复任务执行
	 * @param scheduleJob
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="resumeJobForm")
	public void resumeJobForm(
			ScheduleJob scheduleJob,
			HttpServletRequest request, //
			HttpServletResponse response) throws Exception {
		
		PrintWriter out = response.getWriter();
		try {
			logger.info("rescheduleJob start");
			// 1、设置字符编码
			response.setCharacterEncoding("UTF-8");
			// 2、内容的类型
			response.setContentType("text/html;charset=UTF-8");
			
			quartzService.resumeJob(scheduleJob);
			out.write("恢复成功");
			logger.info("恢复成功");
		} catch (Exception e) {
			out.write("恢复失败");
			logger.error("恢复失败");
		}
	}
	


	
	
  
}
