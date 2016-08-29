package com.boxiang.share.customer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.customer.dao.MemberLevelDao;
import com.boxiang.share.customer.po.MemberLevel;
import com.boxiang.share.customer.service.MemberLevelService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("memberLevelService")
public class MemberLevelServiceImpl implements MemberLevelService {
    @Resource(name="memberLevelDao")
    private MemberLevelDao memberLevelDao;

    @Override
    public List<MemberLevel> selectList(MemberLevel memberLevel) {
        return memberLevelDao.selectList(memberLevel);
    }

    @Override
    public Page<MemberLevel> queryListPage(Page<MemberLevel> page) {
        page.setResultList(memberLevelDao.queryListPage(page));
        return page;
    }

    @Override
    public MemberLevel queryById(java.lang.Integer id) {
        return memberLevelDao.queryById(id);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
    public void add(MemberLevel memberLevel) {
        memberLevelDao.insert(memberLevel);
    }
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
    public void delete(java.lang.Integer id) {
        memberLevelDao.delete(id);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
    public void update(MemberLevel memberLevel) {
        memberLevelDao.update(memberLevel);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
    public void batchUpdate(List<MemberLevel> memberLevelList) {
        memberLevelDao.batchUpdate(memberLevelList);
    }
}