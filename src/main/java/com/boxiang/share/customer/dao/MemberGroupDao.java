package com.boxiang.share.customer.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.customer.po.MemberGroup;

public interface MemberGroupDao extends IMybatisBaseDao<MemberGroup> {
    
    void batchUpdate(List<MemberGroup> memberGroupList);
}