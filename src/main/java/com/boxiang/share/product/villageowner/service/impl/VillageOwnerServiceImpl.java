package com.boxiang.share.product.villageowner.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.villageowner.dao.VillageOwnerDao;
import com.boxiang.share.product.villageowner.po.VillageOwner;
import com.boxiang.share.product.villageowner.service.VillageOwnerService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("villageOwner")
public class VillageOwnerServiceImpl implements VillageOwnerService {
    @Resource(name = "villageOwnerDao")
    private VillageOwnerDao villageOwnerDao;

    @Override
    public List<VillageOwner> selectList(VillageOwner villageOwner) {
        return villageOwnerDao.selectList(villageOwner);
    }

    @Override
    public Page<VillageOwner> queryListPage(Page<VillageOwner> page) {
        page.setResultList(villageOwnerDao.queryListPage(page));
        return page;
    }

    @Override
    public VillageOwner queryById(Integer id) {
        return villageOwnerDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(VillageOwner villageOwner) {
        villageOwnerDao.insert(villageOwner);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(Integer id) {
        villageOwnerDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(VillageOwner villageOwner) {
        villageOwnerDao.update(villageOwner);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<VillageOwner> villageOwnerList) {
        villageOwnerDao.batchUpdate(villageOwnerList);
    }

    @Override
    public List<Object> queryListPageV2(Object page) {
        return villageOwnerDao.queryListPageV2(page);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public List<VillageOwner> batchInsert(List<VillageOwner> list) {
        List<VillageOwner> errorList = new ArrayList<>();
        for (VillageOwner item : list) {
            if (item.getMobile() == null || !Pattern.matches("^[0-9]{11}$", item.getMobile())) { // 手机号不符合规范
                item.setErrorInfo("手机号不符合规范");
                errorList.add(item);
                continue;
            } else if (item.getParkingName().length() == 0) {
                item.setErrorInfo("车场不存在");
                errorList.add(item);
                continue;
            }
            try {
                int result = villageOwnerDao.insertV2(item);
                if (result == 0) { // 车场不存在
                    item.setErrorInfo("车场不存在");
                    errorList.add(item);
                }
            } catch (DuplicateKeyException e) { // 重复数据
                item.setErrorInfo("手机号和小区重复");
                errorList.add(item);
            }
        }
        return errorList;
    }
}