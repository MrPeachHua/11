package com.boxiang.share.product.ccic.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.ccic.dao.CcicInsureDao;
import com.boxiang.share.product.ccic.po.CcicInsure;
import com.boxiang.share.product.ccic.service.CcicInsureService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("ccicInsure")
public class CcicInsureServiceImpl implements CcicInsureService {
    @Resource(name = "ccicInsureDao")
    private CcicInsureDao ccicInsureDao;

    @Override
    public List<CcicInsure> selectList(CcicInsure ccicInsure) {
        return ccicInsureDao.selectList(ccicInsure);
    }

    @Override
    public Page<CcicInsure> queryListPage(Page<CcicInsure> page) {
        page.setResultList(ccicInsureDao.queryListPage(page));
        return page;
    }

    @Override
    public CcicInsure queryById(Integer id) {
        return ccicInsureDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(CcicInsure ccicInsure) {
        ccicInsureDao.insert(ccicInsure);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchAdd(List<CcicInsure> ccicInsureList) {
        ccicInsureDao.batchAdd(ccicInsureList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(Integer id) {
        ccicInsureDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(CcicInsure ccicInsure) {
        ccicInsureDao.update(ccicInsure);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<CcicInsure> ccicInsureList) {
        ccicInsureDao.batchUpdate(ccicInsureList);
    }

    @Override
    public int deleteByInsuranceApplicantNo(String insuranceApplicantNo) {
        return ccicInsureDao.deleteByInsuranceApplicantNo(insuranceApplicantNo);
    }
}