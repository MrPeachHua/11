package com.boxiang.share.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jpush.api.report.UsersResult;
import com.boxiang.share.system.po.*;
import com.boxiang.share.system.service.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.parker.po.Parker;
import com.boxiang.share.product.parker.service.ParkerService;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.user.po.DepartmentInfo;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.user.service.DepartmentInfoService;
import com.boxiang.share.user.service.UserInfoService;
import com.boxiang.share.user.service.UserRoleService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.MD5Util;
import com.boxiang.share.utils.TableNameConstants;

@Controller
@RequestMapping("users")
public class UserInfoController extends BaseController {
	private static final Logger logger = Logger.getLogger(UserInfoController.class);
	
	@Resource private UserInfoService userInfoService;
	
	@Resource private DepartmentInfoService departmentInfoService;
	
	@Resource private UserRoleService userRoleService;
	@Resource private ParkingService parkingService;
	@Resource
	private SysRolesService sysRolesService;
	@Resource
	private SysUsersService sysUsersService;
	@Resource
	private SequenceService sequenceService;
	@Resource
	private SysUsersRolesService sysUsersRolesService;
	@Resource private ParkerService parkerService;
	@Resource private DictionaryService dictionaryService;

	/**
	 * 员工列表 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("userlist.html")
	public ModelAndView userlist(HttpServletRequest request, HttpServletResponse response) {
		try {
			Page<UserInfo> page = new Page<UserInfo>();
			PageHelper.initPage(request, page);
			page.getParams().put("isUsed", Constants.TRUE);
			Map<String, Object> map = super.getParamMap(request);
			String queryType = (String)map.get("queryType");
			String queryValue = (String)map.get("queryValue");
			UserInfo userInfo2=(UserInfo)super.getLoginUser(request);
			if(userInfo2.getModule()!=null&&!("").equals(userInfo2.getModule())){
				page.getParams().put("module", userInfo2.getModule());
			}
			if(!StringUtils.isEmpty(queryType)){
				switch (Integer.parseInt(queryType)) {
				case 1://员工姓名
					page.getParams().put("userName", queryValue);
					break;
				case 2://部门名称
					page.getParams().put("departmentName", queryValue);
					break;
				case 3://角色名称
					page.getParams().put("roleName", queryValue);
					break;
				case 4://员工学历
					page.getParams().put("userDiploma", queryValue);
					break;
	
				default:
					break;
				}
			}
//			UserInfo userInfo=(UserInfo) super.getLoginUser(request);
//			if(userInfo.getRoleId()==3){
//				//如果是区域管理员,查询自己与其创建的用户,暂时只包含项目管理员,如需添加,roleId逗号隔开
//				page.getParams().put("roleId", "2");
//				page.getParams().put("me", userInfo.getUserId());
//			}
			page = userInfoService.queryListPage(page);
		   for(UserInfo userInfo:page.getResultList()){
			   List<SysRoles> list=  sysRolesService.selectListRoles(userInfo.getSysUserId());
			   if(list!=null&&list.size()>0){
				   StringBuilder rolesName =new StringBuilder();;
				   for (SysRoles sysRoles :list){
					   rolesName.append(sysRoles.getRoleDesc()).append(",");
				   }
				   userInfo.setRolesName(rolesName.substring(0, rolesName.length() - 1));
			   }
		   }
			PageHelper.setPageModel(request, page);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询员工信息出现异常",e);
		}
		return new ModelAndView("user/user_list");
	}

	/**
	 * 进入员工查看页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("{userId}/userview.html")
	public ModelAndView userview(@PathVariable int userId, HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = super.getParamMap(request);
		
		try {
			UserInfo userInfo = userInfoService.queryById(userId);
			List<SysRoles> list=  sysRolesService.selectListRoles(userInfo.getSysUserId());
			if(list!=null&&list.size()>0){
				StringBuilder rolesName =new StringBuilder();;
				for (SysRoles sysRoles :list){
					rolesName.append(sysRoles.getRoleDesc()).append(",");
				}
				userInfo.setRolesName(rolesName.substring(0, rolesName.length() - 1));
			}
			if(!StringUtils.isEmpty(userInfo.getParkingId())){
				String [] st = userInfo.getParkingId().split("\\,");
				String parkingNames = "";
				for(String v : st){
					Parking parking = parkingService.queryById(v);
					if(null!= parking){
						parkingNames += parking.getParkingName();
						parkingNames +=",";
					}
				}
				map.put("parkingName",parkingNames);
			}
			
			map.put("userInfo", userInfo);
			//Parking parking = parkingService.queryById(userInfo.getParkingId());
			//map.put("parkingName", (null!=parking && null!=parking.getParkingName())?parking.getParkingName():"");
		} catch (NumberFormatException nfe) {
			logger.error("用户id转换类型失败，请检查参数是否正确！userId="+userId+".",nfe);
			throw new NumberFormatException("用户id转换类型失败，请检查参数是否正确！"+userId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入个人资料修改在出现异常",e);
		}
		return new ModelAndView("user/user_more_v2",map);
	}

	/**
	 * 进入员工修改页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("{userId}/userself.html")
	public ModelAndView userself(@PathVariable int userId, HttpServletRequest request, HttpServletResponse response) {
		return userdetail(userId,request,response);
	}

	/**
	 * 进入员工修改页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("{userId}/userdetail.html")
	public ModelAndView userdetail(@PathVariable int userId, HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = super.getParamMap(request);
		Map<String,Object> map1 = super.getParamMap(request);
		try {
			UserInfo userInfo = userInfoService.queryById(userId);

			SysUsers sysUsers=sysUsersService.queryById(userInfo.getSysUserId());
			UserInfo user=(UserInfo)super.getLoginUser(request);
			map1.put("userId",sysUsers.getUserId());
			map1.put("module",user.getModule());
			if(sysUsers!=null&&!sysUsers.equals("")){
				List<SysRoles> listSysRoles=sysRolesService.queryListRoles(map1);
					map.put("userRoles",listSysRoles);
			}
			map.put("userInfo", userInfo);
			map.put("sysUsers",sysUsers);
			if(!StringUtils.isEmpty(userInfo.getParkingId())){
				String [] st = userInfo.getParkingId().split("\\,");
				String parkingNames = "";
				for(String v : st){
					Parking parking = parkingService.queryById(v);
					if(null!= parking){
						parkingNames += parking.getParkingName();
						parkingNames +=",";
					}
				}
				if(!StringUtils.isEmpty(parkingNames)){
					parkingNames = parkingNames.substring(0,parkingNames.length()-1);
				}
				map.put("parkingName", (null!=parkingNames)?parkingNames:"");
			}else {
				map.put("parkingName", "");
			}
			DepartmentInfo departmentInfo = new DepartmentInfo();
			departmentInfo.setIsUsed(Constants.TRUE);
			departmentInfo.setModule(user.getModule());
			List<DepartmentInfo> departInfos = departmentInfoService.selectList(departmentInfo);
			map.put("departInfos",departInfos);
		} catch (NumberFormatException nfe) {
			logger.error("用户id转换类型失败，请检查参数是否正确！userId="+userId+".",nfe);
			throw new NumberFormatException("用户id转换类型失败，请检查参数是否正确！"+userId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入个人资料修改在出现异常",e);
		}
		return new ModelAndView("user/user_update_v2",map);
	}

	/**
	 * 进入员工添加页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("useradd.html")
	public ModelAndView useradd(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = super.getParamMap(request);
		try {
			UserInfo userInfo =(UserInfo) super.getLoginUser(request);
			DepartmentInfo departmentInfo = new DepartmentInfo();
			departmentInfo.setIsUsed(Constants.TRUE);
			departmentInfo.setModule(userInfo.getModule());
			List<DepartmentInfo> departInfos = departmentInfoService.selectList(departmentInfo);
			SysRoles sysRoles=new SysRoles();
			sysRoles.setEnabled(1);
			sysRoles.setModule(userInfo.getModule());
			List<SysRoles> list=  sysRolesService.selectList(sysRoles);
			map.put("departInfos",departInfos);
			map.put("userRoles", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入员工添加页出现异常",e);
		}
		return new ModelAndView("user/user_add",map);
	}

	/**
	 * 修改员工
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("userupdate.html")
	public ModelAndView userupdate(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("userInfo") UserInfo userInfo) {
		UserInfo currUser = (UserInfo) super.getLoginUser(request);
		try {
			String[] sysRoles=request.getParameterValues("sysRoles");//角色数组
			String module=request.getParameter("module");//所属系统
			userInfo.setUserChangeman(currUser.getUserName());
			userInfo.setUserChangetime(DateUtil.getCurrDate(DateUtil.DATETIME_FORMAT));
			userInfoService.update(userInfo);

                 //修改新表只会有一个部门让修改
			SysUsers sysUsers3=sysUsersService.queryById(userInfo.getSysUserId());
			if(sysUsers3!=null&&!sysUsers3.equals("")){
				sysUsers3.setUserDept(userInfo.getDepartmentName());
				sysUsers3.setModule(module);
				sysUsersService.update(sysUsers3);
			}

			 //以对应Id拿到新表的数据
			//SysUsers sysUsers=sysUsersService.queryById(userInfo.getSysUserId());
			//删除原有关联
			if(userInfo.getSysUserId()!=null&&!userInfo.getSysUserId().equals("")){
				SysUsersRoles	sysUsersRoles=new SysUsersRoles();
				sysUsersRoles.setUserId(userInfo.getSysUserId());//要传新表的userId
				List<SysUsersRoles> list2=	sysUsersRolesService.selectList(sysUsersRoles);
				if(list2!=null&&list2.size()>0){
					for (SysUsersRoles sysUsersRoles2 :list2){
						sysUsersRolesService.delete(sysUsersRoles2.getId());
					}
				}
				//添加新的关联
				if(sysRoles!=null&&sysRoles.length>0){
					for (int i = 0; i <sysRoles.length ; i++) {
						SysUsersRoles sysUsersRoles3=new SysUsersRoles();
						sysUsersRoles3.setEnabled(1);
						sysUsersRoles3.setRoleId(sysRoles[i]);
						sysUsersRoles3.setUserId(userInfo.getSysUserId());//新表的Userid
						sysUsersRolesService.add(sysUsersRoles3);
					}
				}

			}
            
			request.setAttribute("info", "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改员工信息出现异常",e);
		}
		ModelAndView mv = new ModelAndView("redirect:"+userInfo.getUserId()+"/userview.html");
		return mv;
		//return new ModelAndView("user/user_save",null);
	}

	/**
	 * 添加员工
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("usersave.html")
	public ModelAndView usersave(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("userInfo") UserInfo userInfo) {
		try {
			String module=request.getParameter("module");
			UserInfo userInfo1 = new UserInfo();
			userInfo1.setUserNum(userInfo.getUserNum());
			userInfo1.setIsUsed("1");
			List<UserInfo> list = userInfoService.selectList(userInfo1);
			if(list!=null&&list.size()>0){
				request.setAttribute("info", "存在重复账号！");
				request.setAttribute("add", "存在重复账号！");
			}else{
				userInfoService.relationAdd(request,response,userInfo,module);
				request.setAttribute("info", "添加成功");
			}


		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加员工信息出现异常",e);
		}
		return new ModelAndView("user/user_save",null);
	}

	/**
	 * 删除员工
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("{userId}/userdel.html")
	public ModelAndView userdel(@PathVariable int userId, HttpServletRequest request, HttpServletResponse response) {
		if(userId==0){
			throw new NullPointerException("用户ID不能为空!");
		}
		try {

 			//修改sysusers和其关联
		    UserInfo userInfo=	userInfoService.queryById(userId);
			SysUsersRoles sysUsersRoles=new SysUsersRoles();
			sysUsersRoles.setUserId(userInfo.getSysUserId());
 			List<SysUsersRoles> list=	sysUsersRolesService.selectList(sysUsersRoles);
			if(list!=null&&list.size()>0){
				for (SysUsersRoles sysUsersRoles1:list){
					sysUsersRoles1.setEnabled(0);
					sysUsersRolesService.update(sysUsersRoles1);
				}
				//request.setAttribute("info", "删除失败");
			}
                userInfoService.del(userInfo,userId);
				request.setAttribute("info", "删除成功");
		} catch (NumberFormatException nfe) {
			logger.error("用户id转换类型失败，请检查参数是否正确！userId="+userId+".",nfe);
			throw new NumberFormatException("用户id转换类型失败，请检查参数是否正确！"+userId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除员工信息出现异常",e);
		}
		return new ModelAndView("user/user_save",null);
	}

	@RequestMapping("check.html")
	public void check(@RequestParam("userNum") String userNum,HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try{
			response.setContentType("text/plain; charset=UTF-8");
			out = response.getWriter();
			UserInfo userInfo = new UserInfo();
			userInfo.setUserNum(userNum);
			userInfo.setIsUsed("1");
			List<UserInfo> list = userInfoService.selectList(userInfo);
			if(list!=null && list.size()>0){
				out.print("0");
			}else {
		        out.print("1");
			}
	        out.flush();
		}catch(IOException e){
			logger.error("",e);
		}
	}
	@RequestMapping("{userId}/resetpwd.html")
	public ModelAndView resetpwd(@PathVariable int userId, HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = super.getParamMap(request);
		
		try {
			UserInfo userInfo = userInfoService.queryById(userId);
			map.put("userInfo", userInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入密码重置出现异常",e);
		}
		return new ModelAndView("user/user_resetpwd_v2",map);
	}
	@RequestMapping("updatepwd.html")
	public ModelAndView updatepwd(@ModelAttribute("userInfo") UserInfo userInfo,HttpServletRequest request, HttpServletResponse response) {
		logger.info(userInfo.getUserId() +"  " + userInfo.getUserPw());
		try {
			userInfo.setUserPw(MD5Util.getLoginPwd(userInfo.getUserPw()));
			userInfoService.updatePw(userInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入密码重置出现异常",e);
		}
		ModelAndView mv = new ModelAndView("redirect:../frame/desktop.html");
		return mv;
	}
	
	/**
	 * 车场用户列表 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("parkerlist.html")
	public ModelAndView parkerlist(HttpServletRequest request, HttpServletResponse response) {
		try {
			Page<Parker> page = new Page<Parker>();
			PageHelper.initPage(request, page);
			page.getParams().put("isUsed", Constants.TRUE);
			Map<String, Object> map = super.getParamMap(request);
			String queryType = (String)map.get("queryType");
			String queryValue = (String)map.get("queryValue");
			if(!StringUtils.isEmpty(queryType)){
				switch (Integer.parseInt(queryType)) {
				case 1://姓名
					page.getParams().put("parkerName", queryValue);
					break;
				case 2://手机号
					page.getParams().put("parkerMobile", queryValue);
					break;	
				default:
					break;
				}
			}
			page = parkerService.queryListPage(page);
			PageHelper.setPageModel(request, page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询员工信息出现异常",e);
		}
		return new ModelAndView("user/parker/parker_list");
	}
	/**
	 * 车场用户添加 页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("parkeradd.html")
	public ModelAndView parkeradd(HttpServletRequest request, HttpServletResponse response) {
		try {
			/*Page<Parker> page = new Page<Parker>();
			PageHelper.initPage(request, page);
			page.getParams().put("isUsed", Constants.TRUE);
			page = parkerService.queryListPage(page);
			PageHelper.setPageModel(request, page);*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询车场用户信息出现异常",e);
		}
		return new ModelAndView("user/parker/parker_add");
	}
	
	/**
	 * 添加车场用户保存
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("parkersave.html")
	public ModelAndView parkersave(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("parker") Parker parker) {
		try {
			Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_PARKER);
			parker.setParkerId(sequence.getId());
			parker.setParkerPassword(MD5Util.md5(parker.getParkerPassword()));
			parker.setLastOperTime(new Date());
			if (!StringUtils.isEmpty(parker.getParkerHead())) {
				//String path = request.getContextPath();
				//String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
				parker.setParkerHead(parker.getParkerHead());
			}
			parkerService.add(parker);
			request.setAttribute("info", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加车场用户信息出现异常", e);
		}
		return new ModelAndView("user/parker/parker_save", null);
	}
	
	/**
	 * 添加车场用户保存
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("{parkerId}/toparkerupdate.html")
	public ModelAndView toparkerupdate(HttpServletRequest request, HttpServletResponse response,@PathVariable String  parkerId) {
		Map<String,Object> map = super.getParamMap(request);
		try {
			Parker parker = new Parker();
			parker.setParkerId(parkerId);
			List<Parker>  parkerList = parkerService.selectList(parker);
			if(null!=parkerList && parkerList.size()>0){
				//查找车场姓名
				Parking parking = new Parking();
				parking.setParkingId(parkerList.get(0).getParkingId());
				List<Parking> parkingList = parkingService.selectList(parking);
				parkerList.get(0).setParkingName(parkingList.get(0).getParkingName());
				map.put("parker", parkerList.get(0));
			}
		} catch (Exception e) {
			logger.error("添加车场用户信息出现异常",e);
		}
		
		return new ModelAndView("user/parker/parker_update",map);
	}
	
	/**
	 * 添加车场用户保存
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/parkerupdate.html")
	public ModelAndView parkerupdate(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("parker") Parker parker) {
		//Map<String,Object> map = super.getParamMap(request);
		try {
			if (!StringUtils.isEmpty(parker.getParkerHead())) {
				//String path = request.getContextPath();
				//String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
				parker.setParkerHead(parker.getParkerHead());
			}
			if(StringUtils.isEmpty(parker.getParkerPassword())){
				Parker pk = parkerService.queryById(parker.getParkerId());
				parker.setParkerPassword(pk.getParkerPassword());
			}else{
				parker.setParkerPassword(MD5Util.md5(parker.getParkerPassword()));
			}
			parkerService.update(parker);			
		} catch (Exception e) {
			logger.error("添加车场用户信息出现异常",e);
		}
		ModelAndView mv = new ModelAndView("redirect:/users/parkerlist.html");
		//return new ModelAndView("user/parker/parker_update",map);
		return mv;
	}
	
	/**
	 * 删除车场用户
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("{parkerId}/parkerdel.html")
	public void parkerdel(HttpServletRequest request, HttpServletResponse response,@PathVariable String  parkerId) throws IOException {
		//Map<String,Object> map = super.getParamMap(request);
		String alert;
		try {
			Parker parker = parkerService.queryById(parkerId);
			if (parker.getParkerType().equals("0") && parker.getState().equals("1")) {
				alert = "这个代泊员还在当班，不能删除哦！";
			} else {
				parkerService.delete(parkerId);
				alert = "删除成功！";
			}
		} catch (Exception e) {
			logger.error("异常",e);
			alert = "异常";
		}
		String js = String.format("<script>alert('%s');location.href='../parkerlist.html';</script>", alert);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(js);
	}
}
