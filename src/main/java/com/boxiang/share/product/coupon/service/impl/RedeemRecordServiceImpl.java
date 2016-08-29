package com.boxiang.share.product.coupon.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.coupon.dao.RedeemRecordDao;
import com.boxiang.share.product.coupon.po.RedeemRecord;
import com.boxiang.share.product.coupon.service.RedeemRecordService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("redeemRecord")
public class RedeemRecordServiceImpl implements RedeemRecordService {
    @Resource(name = "redeemRecordDao")
    private RedeemRecordDao redeemRecordDao;

    @Override
    public List<RedeemRecord> selectList(RedeemRecord redeemRecord) {
        return redeemRecordDao.selectList(redeemRecord);
    }

    @Override
    public Page<RedeemRecord> queryListPage(Page<RedeemRecord> page) {
        page.setResultList(redeemRecordDao.queryListPage(page));
        return page;
    }

    @Override
    public List<Object> queryListPageBack(Object page) {

        return redeemRecordDao.queryListPageBack(page);
    }

    @Override
    public RedeemRecord queryById(Integer id) {
        return redeemRecordDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(RedeemRecord redeemRecord) {
        redeemRecordDao.insert(redeemRecord);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(Integer id) {
        redeemRecordDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(RedeemRecord redeemRecord) {
        redeemRecordDao.update(redeemRecord);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<RedeemRecord> redeemRecordList) {
        redeemRecordDao.batchUpdate(redeemRecordList);
    }

    @Override
    public int selectCount(RedeemRecord redeemRecord) {
        return redeemRecordDao.selectCount(redeemRecord);
    }
}