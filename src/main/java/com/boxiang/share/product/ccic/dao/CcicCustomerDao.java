package com.boxiang.share.product.ccic.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.ccic.po.CcicCustomer;

public interface CcicCustomerDao extends IMybatisBaseDao<CcicCustomer> {
    
    void batchUpdate(List<CcicCustomer> ccicCustomerList);
}