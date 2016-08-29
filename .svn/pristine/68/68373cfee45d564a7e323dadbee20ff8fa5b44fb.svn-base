package com.boxiang.share.product.villageowner.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.villageowner.po.Invitation;

public interface InvitationDao extends IMybatisBaseDao<Invitation> {
    
    void batchUpdate(List<Invitation> invitationList);

    int queryTodayCount(String customerId);

    Invitation queryUnExpire(int id);

    List<Object> queryHistory(Object page);
}