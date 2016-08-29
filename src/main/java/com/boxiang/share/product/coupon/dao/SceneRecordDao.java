package com.boxiang.share.product.coupon.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.coupon.po.SceneRecord;

public interface SceneRecordDao extends IMybatisBaseDao<SceneRecord> {
    
    void batchUpdate(List<SceneRecord> sceneRecordList);
}