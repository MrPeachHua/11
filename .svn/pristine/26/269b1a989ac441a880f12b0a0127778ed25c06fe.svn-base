package com.boxiang.share.product.parker.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.parker.po.Parker;

public interface ParkerDao extends IMybatisBaseDao<Parker> {
    
    void batchUpdate(List<Parker> parkerList);
    List<Object> queryParkerById(Object params);

    Map<String, Object> queryBusyCount(Map<String, Object> params);

    Parker dispatchParker(String parkingId);

    int updateLastOperTime(Parker parker);

    int updateState(Parker parker);

    int selectCount(Parker parker);
    List<Parker> selectParkingIdByParker(Parker parker);
  //登录
    List<Parker> selectLogin(Parker parkerParam);
  //	String login(String parkerMobile,String parkerPassword);

    List<Parker> selctDaiBoUser(Parker parker);
}