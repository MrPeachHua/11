package com.boxiang.share.app.advertising.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.product.advertising.po.Advertising;
import com.boxiang.share.product.advertising.service.AdvertisingService;
import com.boxiang.share.utils.ShangAnMessageType;

@Controller
@RequestMapping("app/advertising")
public class AdverController extends BaseController{
	private static final Logger logger = Logger.getLogger(AdverController.class);
		
		@Resource
		 private AdvertisingService advertisingService;
		 /**
		 * 首页推广
		 * @param request
		 * @param response
		 * @param
		 * @return
		 */
		
		@RequestMapping("adverList/{version}/{summary}")
		public void adverList(@PathVariable String version,HttpServletRequest request,HttpServletResponse response){
			String message = null;
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			Advertising advertising = new Advertising();
			advertising.setIsUsed(Constants.TRUE);
			try{
				List<Advertising> advertisingList 
					= advertisingService.selectList(advertising);
				if(advertisingList!=null && advertisingList.size()>0){
					Advertising advertising2=advertisingList.get(0);
						Map<String, String> refuelMap = new HashMap<String, String>();
						refuelMap.put("name", advertising2.getName() == null ? "" : advertising2.getName());
						refuelMap.put("imagePath", advertising2.getImagePath() == null ? "" :basePath+advertising2.getImagePath());
						refuelMap.put("imageLink", advertising2.getImageLink() == null ? "" : advertising2.getImageLink());
					 
					message = ShangAnMessageType.toShangAnJson("0", "success", "refuelCard", refuelMap);
				}else{
					message = ShangAnMessageType.operateToJson("1", "无数据");
				}
			}catch(Exception e){
				e.printStackTrace();
				message = ShangAnMessageType.operateToJson("2", "查询失败");
			}
			PrintWriter out;
			response.setContentType("text/html;charset=UTF-8");	
			try {
				out = response.getWriter();
				out.print(message);
			} catch (IOException e) {
				logger.error("", e);
			}
		}
		
	
}
