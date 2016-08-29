package com.boxiang.share.product.comment.dao;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.comment.po.Comment;

public interface CommentDao extends IMybatisBaseDao<Comment> {
    
    void batchUpdate(List<Comment> commentList);

    List<Object> queryListPageV2(Object page);
}