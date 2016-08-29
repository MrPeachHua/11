package com.boxiang.share.product.order.dao;

import java.util.List;
import java.util.Map;

import cn.b2m.eucp.sdkhttp.Mo;
import org.apache.ibatis.annotations.Param;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.order.po.Monthlyparkinginfo;

public interface MonthlyparkinginfoDao extends IMybatisBaseDao<Monthlyparkinginfo> {
    
    void batchUpdate(List<Monthlyparkinginfo> monthlyparkinginfoList);
    int updateParkingInfo(Monthlyparkinginfo monthlyparkinginfo);
    int updateParkingTime(Monthlyparkinginfo monthlyparkinginfo);
    int  deleteByCarnumberAndId(Monthlyparkinginfo Monthlyparkinginfo);
    List<Monthlyparkinginfo> queryListExcel(Monthlyparkinginfo Monthlyparkinginfo);
    //根据传入的小区ID查询月租车位，并且要满足当前时间在订单表的有效期内，当月的积分要小于12
    List<Monthlyparkinginfo> findByVillageIdForUserSync(Monthlyparkinginfo Monthlyparkinginfo);
    
    // 如果没有当月的，查看是否存在以前的订单数据
    List<Monthlyparkinginfo> findMaxByVillageIdForUserSync(@Param(value="carNumberList") List<String> carNumberList);
    List<Monthlyparkinginfo> getMonProSpeByCarnumber(Map<String,String> param);

    List<Map<String,Object>> getMonthlyEquity(Object obj);
    List<Monthlyparkinginfo> selectListGroup(Monthlyparkinginfo monthlyparkinginfo);
}