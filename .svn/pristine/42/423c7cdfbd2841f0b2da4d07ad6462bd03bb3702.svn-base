package com.boxiang.share.app.version.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.system.po.AppVersion;
import com.boxiang.share.system.service.AppVersionService;
import com.boxiang.share.utils.ShangAnMessageType;
@Controller
@RequestMapping("app/version")
public class VersionController extends BaseController {
	@Resource
	private AppVersionService appVersionService;
	/**
	 * 版本更新
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("update/{version}/{channel}/{platform}/{summary}")
	public void carlist(@PathVariable String version,@PathVariable String channel,@PathVariable String platform,HttpServletRequest request, HttpServletResponse response) {
		String mess = null;
		Map<String,Object> paraMap = new HashMap<String,Object>();
		//版本号和平台不能为空
		if(!StringUtils.isEmpty(version)&&!StringUtils.isEmpty(platform)){
			AppVersion versionPram = new AppVersion();
			//渠道号
			//versionPram.setVersionChannel(channel);
			//平台号
			versionPram.setPlatformCode(platform);
			//有效
			versionPram.setIsUsed(Constants.TRUE);
			List<AppVersion> versionList = appVersionService.selectList(versionPram);
			if(null!=versionList&&versionList.size()>0){
				//如果是ios的话
				if(platform.equals("ios")){
					//系统最高版本号
					String pl = versionList.get(0).getVersionCode();
					//例 1.3.1 取  1 3 1 如果取出错误返回失败
					if(null!=pl){
						String [] t1 = pl.split("\\.");
						String [] t2 = version.split("\\.");
						//最高版本>客户端版本
						if((t1.length==3)&&(t2.length==3)&&compareVersion(t1,t2)){
							//版本号
							paraMap.put("versionCode", versionList.get(0).getVersionCode());
							//更新提示内容
							paraMap.put("notice", versionList.get(0).getNotice());
							//是否强制更新（0：否 1：是）
							paraMap.put("isNeeded", versionList.get(0).getIsNeeded());
							//url
							paraMap.put("updateUrl", versionList.get(0).getUpdateUrl());
							mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "version", paraMap);
						}else{
							mess = ShangAnMessageType.toShangAnJson("2", "ios版本号不需要更新", "version", paraMap);
						}
					}else{
						mess = ShangAnMessageType.toShangAnJson("2", "记录中版本号为空", "version", paraMap);
					}
				}else if(platform.equals("android")){
					//系统存最高版本
					String pl = versionList.get(0).getVersionCode();
					//如果客户端版本低于库中最高版本返回信息
					if(null!=pl&&(Integer.parseInt(version)<Integer.parseInt(pl))){
						//版本号
						paraMap.put("versionCode", versionList.get(0).getVersionCode());
						//更新提示内容
						paraMap.put("notice", versionList.get(0).getNotice());
						//是否强制更新（0：否 1：是）
						paraMap.put("isNeeded", versionList.get(0).getIsNeeded());
						//url
						paraMap.put("updateUrl", versionList.get(0).getUpdateUrl());
						mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "version", paraMap);
					}
				}else {
					mess = ShangAnMessageType.toShangAnJson("2", "平台参数错误", "version", paraMap);
				}
			}else{
				mess = ShangAnMessageType.toShangAnJson("1", "查询结果为空", "version", paraMap);
			}
		}else{
			mess = ShangAnMessageType.toShangAnJson("2", "版本号或平台号为空", "version", paraMap);
		}
		//设置utf-8
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");	
		try {
			out = response.getWriter();
			out.print(mess);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	//true为t1>t2
	public boolean compareVersion(String []t1,String []t2){
		int t11 = Integer.parseInt(t1[0]);
		int t12 = Integer.parseInt(t1[1]);
		int t13 = Integer.parseInt(t1[2]);
		int t21 = Integer.parseInt(t2[0]);
		int t22 = Integer.parseInt(t2[1]);
		int t23 = Integer.parseInt(t2[2]);
		System.out.println(t13>t23);
		if(t11>t21){
			return true;
		}else if((t11==t21)&&(t12>t22)){
			return true;
		}else if((t11==t21)&&(t12==t22)&&(t13>t23)){
			return true;
		}else{
			return false;
		}
}

	/**
	 * b端版本更新
	 * @param request
	 * @param response
	 */
	@RequestMapping("updateSideb")
	public void updateSideb(HttpServletRequest request, HttpServletResponse response) {
		//设置utf-8
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String mess = null;
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String updateVersion = request.getParameter("updateVersion");
		String version = request.getParameter("version");
		//String patform = request.getParameter("platform");
		if("1.3.8".equals(version)){
			if (!StringUtils.isEmpty(updateVersion)) {
				AppVersion versionPram = new AppVersion();
				//平台号
				versionPram.setPlatformCode("sideb");
				//有效
				versionPram.setIsUsed(Constants.TRUE);
				List<AppVersion> versionList = appVersionService.selectList(versionPram);
				//系统存最高版本
				String pl = versionList.get(0).getVersionCode();
				//如果客户端版本低于库中最高版本返回信息
				if (null != pl && (Integer.parseInt(updateVersion) < Integer.parseInt(pl))) {
					//版本号
					paraMap.put("versionCode", versionList.get(0).getVersionCode());
					//更新提示内容
					paraMap.put("notice", versionList.get(0).getNotice());
					//是否强制更新（0：否 1：是）
					paraMap.put("isNeeded", versionList.get(0).getIsNeeded());
					//url
					paraMap.put("updateUrl", versionList.get(0).getUpdateUrl());
					mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "version", paraMap);
				}else{
					mess = ShangAnMessageType.operateToJson("2", "户端版本不低于库中最高版本");
				}
			}else{
				mess = ShangAnMessageType.operateToJson("2","参数错误");
			}
		}else{
			mess = ShangAnMessageType.operateToJson("2","版本错误");
		}

		out.print(mess);
	}
	/**
	 * 代泊端版本更新
	 * @param request
	 * @param response
	 */
	@RequestMapping("updateDaibo")
	public void updateDaibo(HttpServletRequest request, HttpServletResponse response) {
		//设置utf-8
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String mess = null;
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String updateVersion = request.getParameter("updateVersion");
		String version = request.getParameter("version");
		//String patform = request.getParameter("platform");
		if("1.0.0".equals(version)){
			if (!StringUtils.isEmpty(updateVersion)) {
				AppVersion versionPram = new AppVersion();
				//平台号
				versionPram.setPlatformCode("daibo");
				//有效
				versionPram.setIsUsed(Constants.TRUE);
				List<AppVersion> versionList = appVersionService.selectList(versionPram);
				//系统存最高版本
				String pl = versionList.get(0).getVersionCode();
				//如果客户端版本低于库中最高版本返回信息
				if (null != pl && (Integer.parseInt(updateVersion) < Integer.parseInt(pl))) {
					//版本号
					paraMap.put("versionCode", versionList.get(0).getVersionCode());
					//更新提示内容
					paraMap.put("notice", versionList.get(0).getNotice());
					//是否强制更新（0：否 1：是）
					paraMap.put("isNeeded", versionList.get(0).getIsNeeded());
					//url
					paraMap.put("updateUrl", versionList.get(0).getUpdateUrl());
					mess = ShangAnMessageType.toShangAnJson("0", "查询成功", "version", paraMap);
				}else{
					mess = ShangAnMessageType.operateToJson("2", "户端版本不低于库中最高版本");
				}
			}else{
				mess = ShangAnMessageType.operateToJson("2","参数错误");
			}
		}else{
			mess = ShangAnMessageType.operateToJson("2","版本错误");
		}

		out.print(mess);
	}
}
