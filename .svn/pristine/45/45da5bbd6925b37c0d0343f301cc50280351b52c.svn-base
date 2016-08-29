package com.boxiang.share.app.comment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.utils.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boxiang.framework.base.controller.BaseController;
import com.boxiang.share.product.comment.po.Comment;
import com.boxiang.share.product.comment.service.CommentService;
import com.boxiang.share.system.po.Sequence;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.TableNameConstants;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kaiser on 2016/5/10.
 */
@Controller
@RequestMapping("app/comment")
public class CommentController extends BaseController {
    private static final Logger logger = Logger.getLogger(CommentController.class);
    @Resource
    private CommentService commentService;
    @Resource
    private SequenceService sequenceService;

    @RequestMapping("commentc")
    public void commentc(HttpServletRequest request,HttpServletResponse response){
        String customerId = request.getParameter("customerId");
        String orderId = request.getParameter("orderId");
        String commentType = request.getParameter("commentType");
        String commentLevel = request.getParameter("commentLevel");
        String commentContent = request.getParameter("commentContent");
        Comment comment2=new Comment();
        comment2.setOrderId(orderId);
      List<Comment> list=  commentService.selectList(comment2);
      String message=null;
        if(list!=null&&list.size()>0){
        	  message = ShangAnMessageType.operateToJson("1", "该订单已评价！");
        }else{
        Comment comment=new Comment();
        Sequence sequence=sequenceService.getNextvalandins(TableNameConstants.T_COMMENT);
        comment.setCommentId(sequence.getId());
        comment.setOrderId(orderId);
        comment.setCommentContent(commentContent);
        comment.setCommentLevel(Integer.parseInt(commentLevel));
        comment.setCommentOperaterType(Integer.parseInt(commentType));
        comment.setCommentOperaterId(customerId);
            try {
                commentService.add(comment);
                message = ShangAnMessageType.operateToJson("0", "添加成功");
            } catch (DuplicateKeyException e) {
                logger.error("", e);
                message = ShangAnMessageType.operateToJson("2", "已存在这条订单的评论,请勿重复提交");
            }
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

    /**
     * 提交评论
     */
    @RequestMapping("submit")
    public void submit(@RequestParam String orderId,
                       String customerId,
                       @RequestParam(required = false, defaultValue = "0") Integer totalityStar,
                       @RequestParam(required = false, defaultValue = "0")Integer carManagerStar,
                       @RequestParam(required = false, defaultValue = "0")Integer businessStar,
                       String totalityTag,
                       String carManagerTag,
                       String businessTag,
                       String commentContent,
                       String version,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String message;
        try {
            Comment comment = new Comment();
            Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_COMMENT);
            comment.setCommentId(sequence.getId());
            comment.setOrderId(orderId);
            comment.setCommentOperaterId(customerId);
            comment.setTotalityStar(totalityStar);
            comment.setCarManagerStar(carManagerStar);
            comment.setBusinessStar(businessStar);
            comment.setTotalityTag(totalityTag);
            comment.setCarManagerTag(carManagerTag);
            comment.setBusinessTag(businessTag);
            comment.setCommentContent(commentContent);
            comment.setCreateDate(new Date());
            comment.setIsUsed(Constants.TRUE);
            commentService.add(comment);
            message = ShangAnMessageType.operateToJson("0", "成功提交");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "已存在这条订单的评论,请勿重复提交");
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(message);
    }

    /**
     * 查看评论
     */
    @RequestMapping("queryByOrderId")
    public void queryByOrderId(@RequestParam String orderId,
                               String version,
                               HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String message;
        try {
            Comment comment = new Comment();
            comment.setOrderId(orderId);
            comment.setIsUsed(Constants.TRUE);
            List<Comment> list = commentService.selectList(comment);
            if (list != null && list.size() > 0) {
                comment = list.get(0);
                Map<String, Object> map = commentService.paramsFilter(comment);
                message = ShangAnMessageType.toShangAnJson("0", "success", "data", map);
            } else {
                message = ShangAnMessageType.operateToJson("1", "无数据");
            }
        } catch (Exception e) {
            logger.error("", e);
            message = ShangAnMessageType.operateToJson("2", "异常");
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(message);
    }

}
