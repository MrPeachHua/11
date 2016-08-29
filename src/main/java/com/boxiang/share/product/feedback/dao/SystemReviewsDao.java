package com.boxiang.share.product.feedback.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.feedback.po.SystemReviews;

public interface SystemReviewsDao extends IMybatisBaseDao<SystemReviews> {
    
    void batchUpdate(List<SystemReviews> systemReviewsList);
}