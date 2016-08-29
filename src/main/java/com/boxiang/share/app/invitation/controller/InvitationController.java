package com.boxiang.share.app.invitation.controller;

import cn.b2m.eucp.client.SingletonClient;
import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.customer.service.CustomerService;
import com.boxiang.share.product.villageowner.po.Invitation;
import com.boxiang.share.product.villageowner.service.InvitationService;
import com.boxiang.share.product.villageowner.service.VillageOwnerService;
import com.boxiang.share.utils.DateUtil;
import com.boxiang.share.utils.ShangAnMessageType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("app/invitation")
public class InvitationController {

    private static final Logger logger = Logger.getLogger(InvitationController.class);

    @Resource
    private VillageOwnerService villageOwnerService;

    @Resource
    private InvitationService invitationService;

    @Resource
    private CustomerService customerService;

    /**
     * 查询业主信息
     */
    @RequestMapping("queryVillageOwner")
    public void queryVillageOwner(@RequestParam String customerId,
                                  String version,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        String message;
        try {
            Map<String, Object> map = new HashMap<>(1);
            Map<String, Object> params = new HashMap<>(1);
            params.put("customerId", customerId);
            map.put("params", params);
            List<Object> list = villageOwnerService.queryListPageV2(map);
            if (list != null && list.size() > 0) {
                message = ShangAnMessageType.toShangAnJson("0", "success", "data", list);
            } else {
                message = ShangAnMessageType.toShangAnJson("1", "无数据", "data", list);
            }
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(message);
    }

    /**
     * 新增一条拜访邀约
     */
    @RequestMapping("add")
    public void add(@ModelAttribute Invitation invitation,
                    String version,
                    HttpServletRequest request,
                    HttpServletResponse response) throws IOException {
        String message;
        try {
            Customer customer = customerService.queryByCustomerId(invitation.getCustomerId());
            if (customer == null) {
                message = ShangAnMessageType.operateToJson("1", "用户Id不存在");
            } else {
                // 判断今天邀请了几辆车
//                int count = invitationService.queryTodayCount(invitation.getCustomerId());
//                if (count >= 3) {
//                    message = ShangAnMessageType.operateToJson("1", "一天顶多3次");
//                } else {
                    invitation.setIsUsed(Constants.TRUE);
                    invitation.setCreateor("admin");
                    invitation.setCreateDate(new Date());
                    invitationService.add(invitation);
                    message = ShangAnMessageType.toShangAnJson("0", "邀请成功", "data", invitation.getId());
                    // 发送短信
                    String text = "【口袋停】您好，家住“" + invitation.getParkingName() + "”手机尾号“" + customer.getCustomerMobile().substring(customer.getCustomerMobile().length() - 4) + "”业主邀请您于“" + DateUtil.date2str(invitation.getInviteDate(), "YYYYMMdd") + "”到访我小区，我们深感荣幸，烦请通过以下链接“  http://www.p-share.cn/wx_share/html5/views/index.html#/home/invitCred?id=" + invitation.getId() + "  ”完善拜访内容、查看拜访凭证。";
                    SingletonClient.getInstance().sendMessage(new String[]{invitation.getMobile()}, text, 5);
//                }
            }
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(message);
    }

    /**
     * 根据id查询凭证
     */
    @RequestMapping("queryById")
    public void queryById(@RequestParam int id,
                          String version,
                          HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String message;
        try {
            Invitation invitation = invitationService.queryUnExpire(id);
            if (invitation != null) {
                String stayTime = "";
                if (invitation.getInTime() != null) {
                    if (invitation.getOutTime() == null || invitation.getOutTime().compareTo(invitation.getInTime()) < 0) {
                        invitation.setOutTime(new Date());
                    }
                    // 计算停留时间
                    long l = invitation.getOutTime().getTime() - invitation.getInTime().getTime();
                    long hour = l / (60 * 60 * 1000);
                    long min = (l / (60 * 1000)) - (hour * 60);
                    stayTime = hour + "小时" + min + "分钟";
                }
                Map<String, Object> map = invitationService.paramsFilter(invitation);
                map.put("stayTime", stayTime);
                message = ShangAnMessageType.toShangAnJson("0", "success", "data", map);
            } else {
                message = ShangAnMessageType.toShangAnJson("1", "凭证已过期", "data", "");
            }
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(message);
    }

    /**
     * 修改
     */
    @RequestMapping("update")
    public void update(@RequestParam int id,
                       @RequestParam String carNumber,
                       String version,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String message;
        try {
            Invitation invitation = invitationService.queryById(id);
            if (invitation != null) {
                invitation.setCarNumber(carNumber);
                invitationService.update(invitation);
                message = ShangAnMessageType.toShangAnJson("0", "修改成功", "data", "");
            } else {
                message = ShangAnMessageType.toShangAnJson("1", "凭证不存在", "data", "");
            }
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(message);
    }

    /**
     * 查询访客邀约历史记录
     */
    @RequestMapping("queryHistory")
    public void queryById(String customerId,
                          @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                          Integer pageSize,
                          String version,
                          HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String message;
        try {
            Page<Object> page = new Page<>();
            page.setCurrentPage(pageIndex);
            if (pageSize != null) {
                page.setPageSize(pageSize);
            }
            page.getParams().put("customerId", customerId);
            List<Object> list = invitationService.queryHistory(page);
            message = ShangAnMessageType.toShangAnJson("0", "success", "data", list);
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(message);
    }
}
