package com.boxiang.share.system.controller;
import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.product.parking.service.ParkingService;
import com.boxiang.share.system.po.Dictionary;
import com.boxiang.share.system.po.SendMessage;
import com.boxiang.share.system.service.*;
import com.boxiang.share.user.po.UserInfo;
import com.boxiang.share.user.service.UserInfoService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("system/sendMessage")
public class SendMessageController extends BaseController{
    private static final Logger logger = Logger.getLogger(DictionaryController.class);
    @Resource private SendMessageService sendMessageService;
    @Resource private DictionaryService dictionaryService;
    @Resource private UserInfoService userInfoService;
    @Resource private ParkingService parkingService;

    //修改
    @RequestMapping("update.html")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        try {
            String parkingId = request.getParameter("parkingId");
            String id = request.getParameter("id");
            String orderType = request.getParameter("orderType");
            String personName = request.getParameter("personName");
            String personMobile = request.getParameter("personMobile");
            UserInfo userInfo = (UserInfo)request.getSession().getAttribute(Constants.LOGIN_USER);
            Date updateTime = new Date();
            SendMessage sendMessage = sendMessageService.queryById(Integer.parseInt(id));
            sendMessage.setParkingId(parkingId);
            sendMessage.setOrderType(orderType);
            sendMessage.setPersonName(personName);
            sendMessage.setPersonMobile(personMobile);
            sendMessage.setUpdateTime(updateTime);
            sendMessage.setUpdateUser(userInfo.getUserName());
            sendMessageService.update(sendMessage);
            request.setAttribute("info", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("修改短信名单出现异常",e);
        }
        return new ModelAndView("system/message_save",null);
    }

    //跳转编辑
    @RequestMapping("{id}/edit.html")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response,@PathVariable int id) {
        if(id==0){
            throw new NullPointerException("ID不能为空!");
        }
        Map<String,Object> map = super.getParamMap(request);
        try {
            Dictionary dictionary=new Dictionary();
            dictionary.setDictCode("order_type");
            dictionary.setIsUsed(Constants.TRUE);
            List<Dictionary> dictionaryList=dictionaryService.selectList(dictionary);
            SendMessage sendMessage = sendMessageService.queryById(id);
            Parking parking = parkingService.queryById(sendMessage.getParkingId());
            sendMessage.setParkingName(parking.getParkingName());
            map.put("sendMessage", sendMessage);
            map.put("dictionary", dictionaryList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("信息出现异常",e);
        }
        return new ModelAndView("system/message_edit",map);
    }

    @RequestMapping("{id}/del.html")
    public ModelAndView msgdel(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
        if(id==0){
            throw new NullPointerException("短信名单ID不能为空!");
        }
        try {
            sendMessageService.delete(id);
            request.setAttribute("info", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("删除短信名单出现异常",e);
        }
        return new ModelAndView("system/message_save",null);
    }
    @RequestMapping("/list.html")
    public ModelAndView list(HttpServletRequest request,HttpServletResponse response)
    {
        try {
            Dictionary dictionary = new Dictionary();
            dictionary.setDictCode("order_type");
            dictionary.setIsUsed(Constants.TRUE);
            List<Dictionary> dictList =dictionaryService.selectList(dictionary);
            request.setAttribute("dictList",dictList);
            Page<SendMessage> page = new Page<SendMessage>();
            PageHelper.initPage(request, page);
            String parkingName = request.getParameter("parkingName");
            String orderType = request.getParameter("orderType");
            String personName = request.getParameter("personName");
            String personMobile = request.getParameter("personMobile");
            page.getParams().put("parkingName", parkingName);
            page.getParams().put("orderType", orderType);
            page.getParams().put("personName",personName);
            page.getParams().put("personMobile",personMobile);
            page = sendMessageService.selectListByParking(page);
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("短信发送名单查询异常",e);
        }
        return new ModelAndView("system/sendMessage_list");
    }
    @RequestMapping("/add.html")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = super.getParamMap(request);
        Dictionary dictionary=new Dictionary();
        dictionary.setDictCode("order_type");
        dictionary.setIsUsed(Constants.TRUE);
        List<Dictionary> dictionaryList=dictionaryService.selectList(dictionary);
        map.put("dictionary", dictionaryList);
        return new ModelAndView("system/sendMessage_add",map);
    }

    //添加
    @RequestMapping("save.html")
    public ModelAndView magsave(HttpServletRequest request, HttpServletResponse response) {
            String parkingId = request.getParameter("parkingId");
            String orderType = request.getParameter("orderType");
            String personName = request.getParameter("personName");
            String personMobile = request.getParameter("personMobile");
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute(Constants.LOGIN_USER);
            Date createTime = new Date();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setParkingId(parkingId);
            sendMessage.setOrderType(orderType);
            sendMessage.setPersonName(personName);
            sendMessage.setPersonMobile(personMobile);
            sendMessage.setCreateTime(createTime);
            sendMessage.setCreateUser(userInfo.getUserName());
            sendMessageService.add(sendMessage);
            request.setAttribute("info", "添加成功");
            return new ModelAndView("system/message_save",null);
    }
}
