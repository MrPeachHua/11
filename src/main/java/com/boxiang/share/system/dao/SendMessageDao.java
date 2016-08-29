package com.boxiang.share.system.dao;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.system.po.SendMessage;

public interface SendMessageDao extends IMybatisBaseDao<SendMessage> {
    
    void batchUpdate(List<SendMessage> sendMessageList);
    public List<SendMessage> selectListByParking(Page<SendMessage> sendMessagePage);
}