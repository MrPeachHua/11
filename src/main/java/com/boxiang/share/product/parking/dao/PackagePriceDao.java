package com.boxiang.share.product.parking.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.parking.po.PackagePrice;
import org.apache.ibatis.annotations.Param;

public interface PackagePriceDao extends IMybatisBaseDao<PackagePrice> {
    
    void batchUpdate(List<PackagePrice> packagePriceList);

    List<PackagePrice> queryByParkingId(@Param("parkingId") String parkingId);

    List<PackagePrice> queryByParkingIdAndWeek(@Param("parkingId")String parkingId, @Param("weeks")String[] weeks);
}