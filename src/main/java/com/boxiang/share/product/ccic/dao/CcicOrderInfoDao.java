package com.boxiang.share.product.ccic.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.ccic.po.CcicOrderInfo;

public interface CcicOrderInfoDao extends IMybatisBaseDao<CcicOrderInfo> {
    
    void batchUpdate(List<CcicOrderInfo> ccicOrderInfoList);
}