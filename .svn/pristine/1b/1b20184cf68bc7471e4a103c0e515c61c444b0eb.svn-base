package com.boxiang.share.app.appsideb.login.controller;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.product.order.po.OrderMain;
import com.boxiang.share.product.order.service.OrderMainService;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.user.service.UserInfoService;
import com.boxiang.share.utils.MD5Util;
import com.boxiang.share.utils.ShangAnMessageType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("app/loginSide")
public class LoginAppSideController extends BaseController {
    private static final Logger logger = Logger.getLogger(LoginAppSideController.class);
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private ParkingService parkingService;
    @Resource
    private OrderMainService orderMainService;

    /**
     * B端云平台 登录
     * @param request
     * @param response
     */
    @RequestMapping("login")
    public void loginSide(HttpServletRequest request,HttpServletResponse response)
    {
        String mess = null;
        Map<String,Object> ctMap = new HashMap<String,Object>();
        String userNum = request.getParameter("userNum");
        String userPw = request.getParameter("userPw");
        String version=request.getParameter("version");
        String role = "";
        boolean flag = false;
        if ("1.3.8".equals(version))
        {
            if (userNum==null || userNum.trim().length()==0){
                mess = ShangAnMessageType.operateToJson("3","用户名不能为空");
            }else if (userPw==null || userPw.trim().length()==0){
                mess = ShangAnMessageType.operateToJson("4","密码不能为空");
            }else {
                UserInfo userInfo = new UserInfo();
                userInfo.setUserNum(userNum);
                List<UserInfo> userInfos = userInfoService.selectSysUser(userInfo);
                if (userInfos!=null)
                {
                    for (UserInfo userInfo1 : userInfos)
                    {
                        if (userInfo1!=null){
                            if (userInfo1.getUserPw()!=null && userInfo1.getUserPw().equals(MD5Util.getLoginPwd(userPw)))
                            {
                                if (userInfo1.getRoleName()!=null && (userInfo1.getRoleName().equals("B_PROJECT_MANAGER")||userInfo1.getRoleName().equals("B_REGIONAL_MANAGER")||
                                        userInfo1.getRoleName().equals("B_REGIONAL_ASSISTANT")||userInfo1.getRoleName().equals("B_PROJECT_ASSISTANT")))
                                {
                                    if (userInfo1.getRoleName().equals("B_PROJECT_MANAGER"))
                                    {
                                        role = "a";
                                    }else if (userInfo1.getRoleName().equals("B_REGIONAL_MANAGER"))
                                    {
                                        role = "b";
                                    }else if (userInfo1.getRoleName().equals("B_PROJECT_ASSISTANT"))
                                    {
                                        role = "c";
                                    }else if (userInfo1.getRoleName().equals("B_REGIONAL_ASSISTANT"))
                                    {
                                        role = "d";
                                    }
                                    flag = true;
                                    break;
                                }
                            }else {
                                mess = ShangAnMessageType.operateToJson("2","密码错误");
                            }
                        }
                    }
                }else {
                    mess = ShangAnMessageType.operateToJson("5","用户不存在");
                }
                if (flag){
                    UserInfo userInfo2 = userInfoService.selectSysUser(userInfo).get(0);
                    List list = new ArrayList();
                    if (userInfo2.getParkingId()!=null)
                    {
                        String[] parkingIds = userInfo2.getParkingId().split(",");
                        for (int i=0;i<parkingIds.length;i++)
                        {
                            if (parkingIds[i]!=null && parkingIds[i].trim().length()>0)
                            {
                                Map map = new HashMap();
                                Parking parking = new Parking();
                                parking.setParkingId(parkingIds[i]);
                                Parking parking1 = parkingService.selectParkingName(parking);
                                if (parking1!=null){
                                    map.put("parkingId",parkingIds[i]);
                                    map.put("parkingName",parking1.getParkingName());
                                    list.add(map);
                                }
                            }
                        }
                    }

                    ctMap.put("userName",null==userInfo2.getUserName()?"":userInfo2.getUserName());
                    ctMap.put("userSex",null==userInfo2.getUserSex()?"":userInfo2.getUserSex());
                    ctMap.put("userAge",userInfo2.getUserAge());
//                    ctMap.put("roleName",null==userInfo1.getRoleName()?"":userInfo1.getRoleName());
                    ctMap.put("userMobile",null==userInfo2.getUserMobile()?"":userInfo2.getUserMobile());
                    ctMap.put("userIdnum",null==userInfo2.getUserIdnum()?"":userInfo2.getUserIdnum());
                    ctMap.put("sysUserId",null==userInfo2.getSysUserId()?"":userInfo2.getSysUserId());
                    String path = request.getContextPath();
                    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
                    ctMap.put("head",null==userInfo2.getHead()?"":basePath+userInfo2.getHead());
                    ctMap.put("role",role);
                    ctMap.put("parkingMap",list);
                    mess = ShangAnMessageType.toShangAnJson("0", "登录成功", "customer", ctMap);
                }else {
                    mess = ShangAnMessageType.operateToJson("1","用户没有权限");
                }
            }
        }
        try{
            //设置utf-8
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin","*");
            PrintWriter writer = response.getWriter();
            writer.print(mess);
        } catch (IOException e) {
            logger.error("",e);
        }
    }

    /**
     * B端云平台 账单核算
     * @param parkingId
     * @param payDate
     * @param request
     * @param response
     */
    @RequestMapping("amountAccount/{parkingId}/{payDate}/{version}/{summary}")
    public void amountAccount(@PathVariable String parkingId,@PathVariable String payDate,@PathVariable String version,HttpServletRequest request,HttpServletResponse response)
    {
        String message = null;
        if ("1.3.8".equals(version))
        {
            OrderMain om = new OrderMain();
            om.setParkingId(parkingId);
            om.setPayDate(payDate);
            List<OrderMain> omList = orderMainService.queryAmountPaid(om);
            List list = new ArrayList();
            Map map1 = new HashMap();
            List list1 = new ArrayList();
            List list2 = new ArrayList();
            if (omList!=null && omList.size()>0)
            {
                for (OrderMain orderMain:omList)
                {
                   if (orderMain!=null)
                   {
                       Map map = new HashMap();
                       map.put("orderType",orderMain.getOrderType());
                       map.put("amount",Integer.parseInt(orderMain.getAmount())/100);
                       list1.add(map);
                   }
                }
            }
            List<OrderMain> omListMonth = orderMainService.queryAmountPaidByMonth(om);
            if (omListMonth!=null && omListMonth.size()>0)
            {
                for (OrderMain orderMain:omListMonth)
                {
                    if (orderMain!=null)
                    {
                        Map map = new HashMap();
                        map.put("orderType",orderMain.getOrderType());
                        map.put("amount",Integer.parseInt(orderMain.getAmount())/100);
                        list2.add(map);
                    }
                }
            }
            map1.put("year",list1);
            map1.put("month",list2);
            list.add(map1);
            message= ShangAnMessageType.toShangAnJson("0","查询成功","list",list);
            PrintWriter out;
            response.setContentType("text/html;charset=UTF-8");
            try
            {
                out = response.getWriter();
                out.print(message);
            } catch (IOException e)
            {
                logger.error("", e);
            }
        }
    }
    @RequestMapping("getParking/{userId}/{version}/{summary}")
    public void getParking(@PathVariable String userId,@PathVariable String  version,HttpServletRequest request,HttpServletResponse response)
    {
        String  mess = null;
        if ("1.3.8".equals(version))
        {
            UserInfo userInfo = new UserInfo();
            userInfo.setSysUserId(userId);
            List<UserInfo> uList = userInfoService.selectList(userInfo);
            for (UserInfo userInfo1 : uList)
            {
                if (userInfo1!=null && userInfo1.getParkingId()!=null){
                    List list = new ArrayList();
                    String[] parkingIds = userInfo1.getParkingId().split(",");
                    for (int i=0;i<parkingIds.length;i++)
                    {
                        if (parkingIds[i]!=null && parkingIds[i].trim().length()>0)
                        {
                            Map map = new HashMap();
                            Parking parking = new Parking();
                            parking.setParkingId(parkingIds[i]);
                            Parking parking1 = parkingService.selectParkingName(parking);
                            map.put("parkingId",parkingIds[i]);
                            map.put("parkingName",parking1.getParkingName());
                            list.add(map);
                            mess = ShangAnMessageType.toShangAnJson("0", "登录成功", "parkingList", list);
                        }
                    }
                    break;
                }
            }
        }
        PrintWriter out;
        response.setContentType("text/html;charset=UTF-8");
        try
        {
            out = response.getWriter();
            out.print(mess);
        } catch (IOException e)
        {
            logger.error("", e);
        }
    }

}
