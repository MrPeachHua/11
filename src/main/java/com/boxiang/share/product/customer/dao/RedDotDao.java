package com.boxiang.share.product.customer.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.customer.po.RedDot;

public interface RedDotDao extends IMybatisBaseDao<RedDot> {
    
    void batchUpdate(List<RedDot> redDotList);
}