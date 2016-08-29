package com.boxiang.share.product.coupon.dao;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.coupon.po.RedeemRecord;

public interface RedeemRecordDao extends IMybatisBaseDao<RedeemRecord> {
    
    void batchUpdate(List<RedeemRecord> redeemRecordList);

    int selectCount(RedeemRecord redeemRecord);

    List<Object> queryListPageBack(Object page);
}