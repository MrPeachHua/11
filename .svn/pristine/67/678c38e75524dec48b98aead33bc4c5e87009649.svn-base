package com.boxiang.share.product.order.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.order.po.Propertyparkinginfo;

public interface PropertyparkinginfoDao extends IMybatisBaseDao<Propertyparkinginfo> {
    
    void batchUpdate(List<Propertyparkinginfo> propertyparkinginfoList);
    int deleteByCarNumber(Propertyparkinginfo propertyparkinginfo);
    int updateParkingInfo(Propertyparkinginfo propertyparkinginfo);
    int updateParkingTime(Propertyparkinginfo propertyparkinginfo);
    List<Propertyparkinginfo> queryListExcel(Propertyparkinginfo propertyparkinginfo);
    //根据传入的小区ID查询月租车位，并且要满足当前时间在订单表的有效期内，当月的积分要小于12
    List<Propertyparkinginfo> findByVillageIdForUserSync(Propertyparkinginfo propertyparkinginfo);
    
    // 如果没有当月的，查看是否存在以前的订单数据
    List<Propertyparkinginfo> findMaxByVillageIdForUserSync(@Param(value="carNumberList") List<String> carNumberList);
    List<Propertyparkinginfo> selectListGroup(Propertyparkinginfo propertyparkinginfo);
}