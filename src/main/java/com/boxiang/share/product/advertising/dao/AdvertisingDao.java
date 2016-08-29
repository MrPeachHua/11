package com.boxiang.share.product.advertising.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.advertising.po.Advertising;
import com.boxiang.share.product.carlife.po.CarlifeEventPage;

public interface AdvertisingDao extends IMybatisBaseDao<Advertising> {
    
    void batchUpdate(List<Advertising> advertisingList);
    List <Advertising> selectAdvertising(Advertising advertising);
}