package com.boxiang.share.system.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.system.po.Sms;

public interface SmsDao extends IMybatisBaseDao<Sms> {
    
    void batchUpdate(List<Sms> tSmsList);
    
    //获取月租手机号
    List<Sms> selectMonthMobile(Map<String,Object> param);
    
    //获取产权手机号
    List<Sms> selectPropertyMobile(Map<String,Object> param);
}