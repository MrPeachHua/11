package com.boxiang.share.product.carlife.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.carlife.po.CarRent;

public interface CarRentDao extends IMybatisBaseDao<CarRent> {
    
    void batchUpdate(List<CarRent> carRentList);
}