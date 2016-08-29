package com.boxiang.share.product.comment.controller;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.base.page.PageHelper;
import com.boxiang.share.product.comment.service.CommentService;
import com.boxiang.share.product.order.controller.ExportOrderExcel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("products/comment")
public class CommentBackstageController extends BaseController {

    private static final Logger logger = Logger.getLogger(CommentBackstageController.class);

    @Resource
    private CommentService commentService;

    @RequestMapping("list.html")
    public ModelAndView list(HttpServletRequest request) {
        try {
            Page<Object> page = new Page<>();
            PageHelper.initPage(request, page);
            page.setParams(super.getParamMap(request));
            page.setResultList(commentService.queryListPageV2(page));
            PageHelper.setPageModel(request, page);
        } catch (Exception e) {
            logger.error("查询信息出现异常", e);
        }
        return new ModelAndView("products/comment/comment_list");
    }

    /**
     * 导出excel文档
     */
    @RequestMapping("excelList.html")
    public void excelRecharge(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            Map<String, Object> map = new HashMap<>();
            map.put("params", super.getParamMap(request));
            final List<Object> list = commentService.queryListPageV2(map);
            if (list != null && list.size() > 0) {
                String[][] titleArray = {
                        {"订单ID", "orderId"},
                        {"订单类型", "orderTypeName"},
                        {"客户手机", "customerMobile"},
                        {"客户姓名", "customerNickname"},
                        {"总体评价", "totalityStar"},
                        {"标签", "totalityTag"},
                        {"车管家评价", "carManagerStar"},
                        {"标签", "carManagerTag"},
                        {"商家评价", "businessStar"},
                        {"标签", "businessTag"},
                        {"评论内容", "commentContent"}
                };
                ExportOrderExcel.exportExcel(titleArray, list, response);
            } else {
                try {
                    String js = "<script>alert('没有需要导出的内容');history.back();</script>";
                    response.getWriter().print(js);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}