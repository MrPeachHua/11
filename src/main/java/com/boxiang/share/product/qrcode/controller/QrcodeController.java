package com.boxiang.share.product.qrcode.controller;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.product.qrcode.po.CarlovQrcode;
import com.boxiang.share.product.qrcode.service.CarlovQrcodeService;
import com.boxiang.share.user.po.RegionUser;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.user.service.RegionUserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("products/qrcode")
public class QrcodeController extends BaseController {
	private static final Logger logger = Logger.getLogger(QrcodeController.class);

	@Resource
	private RegionUserService regionUserService;
	@Resource
	private ParkingService parkingService;

	@Resource
	private CarlovQrcodeService carlovQrcodeService;
	/**
	 * 区域负责人列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("list.html")
	public ModelAndView userlist(HttpServletRequest request, HttpServletResponse response) {
		try {
			Page<CarlovQrcode> page = new Page<CarlovQrcode>();
			PageHelper.initPage(request, page);
			//page.getParams().put("isUsed", Constants.TRUE);
			Map<String, Object> map = super.getParamMap(request);
			page = carlovQrcodeService.queryListPageForQrcode(page);
			PageHelper.setPageModel(request, page);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询区域负责人",e);
		}
		return new ModelAndView("products/qrcode/qrcode_list");
	}
	/**
	 * 删除
	 */
	@RequestMapping("{parkingId}/delete.html")
	public void delete(@PathVariable String parkingId, HttpServletResponse response) throws IOException {
		String js;
		try {
			carlovQrcodeService.delete(parkingId);
			js = "<script>alert('删除成功');location.href='../list.html';</script>";
		} catch (Exception e) {
			logger.error("异常", e);
			js = "<script>alert('异常');location.href='../list.html';</script>";
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(js);
	}

	/**
	 * 前往新增页面
	 */
	@RequestMapping("/add.html")
	public ModelAndView add() {
		return new ModelAndView("products/qrcode/qrcode_add");
	}

	/**
	 * 添加
	 */
	@RequestMapping("save.html")
	public String save(HttpServletRequest request,
					   HttpServletResponse response,
					   @ModelAttribute CarlovQrcode carlovQrcode) {
		try {
			carlovQrcode.setCreateDate(new Date());
			carlovQrcode.setModifyDate(new Date());
			UserInfo userInfo = (UserInfo) super.getLoginUser(request);
			carlovQrcode.setCreateor(userInfo.getUserNum());
			carlovQrcode.setModified(userInfo.getUserNum());
			carlovQrcodeService.add(carlovQrcode);
			String js = "<script>alert('添加成功');location.href='list.html';</script>";
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(js);
			return null;
		} catch (Exception e) {
			request.setAttribute("info", "异常，请检查车场是否重复");
			logger.error("异常", e);
			return "products/qrcode/qrcode_add";
		}
	}

	/**
	 * 进入修改页面
	 */
	@RequestMapping("{parkingId}/edit.html")
	public ModelAndView edit(@PathVariable String parkingId, HttpServletRequest request) {
		CarlovQrcode params = new CarlovQrcode();
		params.setParkingId(parkingId);
		List<CarlovQrcode> list = carlovQrcodeService.selectList(params);
		if(list!=null&&list.size()>0){
			CarlovQrcode carlovQrcode=list.get(0);
			Parking parking=parkingService.queryById(carlovQrcode.getParkingId());
			request.setAttribute("parkingName",parking.getParkingName());
			request.setAttribute("entity",carlovQrcode);
		}

		return new ModelAndView("products/qrcode/qrcode_edit");
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update.html")
	public String update(@ModelAttribute CarlovQrcode carlovQrcode,HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			//regionUser.setIsUsed(Constants.TRUE);
			CarlovQrcode params = carlovQrcodeService.queryById(carlovQrcode.getParkingId());
			carlovQrcode.setModifyDate(new Date());
			if(null!=params){
				carlovQrcode.setCreateDate(params.getCreateDate());
			}
			UserInfo userInfo = (UserInfo) super.getLoginUser(request);
			carlovQrcode.setCreateor(params.getCreateor());
			carlovQrcode.setModified(userInfo.getUserNum());
			carlovQrcodeService.update(carlovQrcode);
			return "redirect:list.html";
		} catch (Exception e) {
			logger.error("异常", e);
			String js = "<script>alert('异常');location.href='" + carlovQrcode.getParkingId() + "/edit.html';</script>";
			response.getWriter().print(js);
			return null;
		}
	}
}
