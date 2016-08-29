package com.boxiang.share.product.parking.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.parking.po.DiscountParkingPrice;

public interface DiscountParkingPriceDao extends IMybatisBaseDao<DiscountParkingPrice> {
    
    void batchUpdate(List<DiscountParkingPrice> discountParkingPriceList);
}