package com.boxiang.share.customer.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.customer.po.CustomerGrowth;

public interface CustomerGrowthDao extends IMybatisBaseDao<CustomerGrowth> {
    
    void batchUpdate(List<CustomerGrowth> customerGrowthList);

    List<CustomerGrowth> queryListPage1(Page<CustomerGrowth> page);


}