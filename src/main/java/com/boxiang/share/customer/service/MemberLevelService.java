package com.boxiang.share.customer.service;

import com.boxiang.share.customer.po.CustomerGrowth;
import com.boxiang.share.customer.po.MemberLevel;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface MemberLevelService {

    List<MemberLevel> selectList(MemberLevel memberLevel);

    Page<MemberLevel> queryListPage(Page<MemberLevel> page);

    MemberLevel queryById(java.lang.Integer id);

    void add(MemberLevel memberLevel);

    void delete(java.lang.Integer id);

    void update(MemberLevel memberLevel);

    void batchUpdate(List<MemberLevel> memberLevelList);

}