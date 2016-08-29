package com.boxiang.share.customer.controller;
import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.customer.po.CustomerGrowth;
import com.boxiang.share.customer.po.MemberGroup;
import com.boxiang.share.customer.po.MemberLevel;
import com.boxiang.share.customer.service.*;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.order.controller.ExportOrderExcel;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.system.po.SysRoles;
import com.boxiang.share.system.po.SysUsers;
import com.boxiang.share.system.po.SysUsersRoles;
import com.boxiang.share.user.po.DepartmentInfo;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.utils.DateUtil;
import java.text.*;
import java.util.Calendar;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
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
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dfl on 2016/8/23.
 */
@Controller
@RequestMapping("customer")
public class CustomerGrowthController extends BaseController {

	private static final Logger logger = Logger.getLogger(MemberLevelController.class);

	@Resource
	private CustomerGrowthService customerGrowthService;
	@Resource
	private CustomerService customerService;
	@Resource
	private MemberGroupService memberGroupService;

	/**
	 * 成长值流水列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("customer_growth/list.html")
	public ModelAndView customerGrowthList(HttpServletRequest request, HttpServletResponse response) {
		try {
			Page<CustomerGrowth> page = new Page<CustomerGrowth>();
			PageHelper.initPage(request, page);
			Map<String, Object> map = super.getParamMap(request);
			String queryMobile = (String)map.get("queryMobile");
			String queryGroup = (String)map.get("queryGroup");
			String queryLevel = (String)map.get("queryLevel");
			String beginTime = (String)map.get("beginTime");
			String endTime = (String)map.get("endTime");
			if(!StringUtils.isEmpty(endTime) && !StringUtils.isEmpty(beginTime)){
				page.getParams().put("endTime", endTime);
				page.getParams().put("startTime", beginTime);
			}
			if(StringUtils.isEmpty(endTime) && !StringUtils.isEmpty(beginTime)){
				page.getParams().put("endTime", beginTime);
				page.getParams().put("startTime", beginTime);
			}
			if(!StringUtils.isEmpty(endTime) && StringUtils.isEmpty(beginTime)){
				page.getParams().put("endTime", endTime);
				page.getParams().put("startTime", endTime);
			}
			if(!StringUtils.isEmpty(queryMobile)){
				page.getParams().put("customerMobile", queryMobile);
			}
			if(!StringUtils.isEmpty(queryGroup)){
				page.getParams().put("groupId", queryGroup);
			}
			if(!StringUtils.isEmpty(queryLevel)){
				page.getParams().put("customerLevel", queryLevel);
			}
			page = customerGrowthService.queryListPage1(page);
			for (int i = 0; i < page.getResultList().size(); i++) {
				CustomerGrowth gg = page.getResultList().get(i);
				Customer customer = customerService.queryByCustomerId(gg.getCustomerId());
				page.getResultList().get(i).setCustomerName(customer.getCustomerName());
				MemberGroup mg = memberGroupService.queryById(gg.getGroupId());
				page.getResultList().get(i).setGroupName(mg.getGroupName());

			}
			PageHelper.setPageModel(request, page);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询成长值流水出现异常", e);
		}
		return new ModelAndView("customer/customer_growth_list");
	}



	/**
	 * 导出excel文档，项目统计详情
	 */
	@RequestMapping("excelGrowthValueReport.html")
	public void excelPaymentDetailReport(HttpServletRequest request, HttpServletResponse response){
		try{

			Page<CustomerGrowth> page = new Page<CustomerGrowth>();
			PageHelper.initPage(request, page);
			final Page<CustomerGrowth> p = customerGrowthService.queryListPage(page);


			ExportOrderExcel.exportExcel(new ExportOrderExcel.ExcelObject() {
				@Override
				public void dataSource(WritableSheet sheet, WritableCellFormat wcf_center, WritableCellFormat wcf_left) throws Exception {
					sheet.addCell(new Label(0, 0, "用户名称", wcf_center));
					sheet.addCell(new Label(1, 0, "手机", wcf_center));
					sheet.addCell(new Label(2, 0, "成长值获取时间", wcf_center));
					sheet.addCell(new Label(3, 0, "动作", wcf_center));
					sheet.addCell(new Label(4, 0, "分组", wcf_center));
					sheet.addCell(new Label(5, 0, "明细", wcf_center));
					sheet.addCell(new Label(6, 0, "成长值", wcf_center));
					sheet.addCell(new Label(7, 0, "备注", wcf_center));
					for (int i = 0; i < p.getResultList().size(); i++) {
						CustomerGrowth gg = p.getResultList().get(i);
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						//Customer customer = customerService.queryByCustomerId(gg.getCustomerId());
						MemberGroup mg = memberGroupService.queryById(gg.getGroupId());
						sheet.addCell(new Label(0, i + 1, gg.getCustomerId()));
						sheet.addCell(new Label(1, i + 1, gg.getCustomerMobile()));
						sheet.addCell(new Label(2, i + 1, df.format(gg.getGrowthTime())));
						sheet.addCell(new Label(3, i + 1, String.valueOf(gg.getActionId())));
						sheet.addCell(new Label(4, i + 1, mg.getGroupName()));
						sheet.addCell(new Label(5, i + 1, gg.getDetailInfo()));
						sheet.addCell(new Label(6, i + 1, String.valueOf(gg.getGrowthValue())));
						sheet.addCell(new Label(7, i + 1, gg.getMemo()));
						//sheet.addCell(new Label(5, i + 1, StringUtils.isEmpty(ome.getSumPaid()) ? "0" : new BigDecimal(ome.getSumPaid()).setScale(2).toString()));
					}
				}
			}, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
