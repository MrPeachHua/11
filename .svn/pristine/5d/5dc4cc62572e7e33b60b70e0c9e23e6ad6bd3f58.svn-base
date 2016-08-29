package com.boxiang.share.product.parker.dao;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.parker.po.CollectionParking;

public interface CollectionParkingDao extends IMybatisBaseDao<CollectionParking> {
    
    void batchUpdate(List<CollectionParking> tCollectionParkingList);
    List<CollectionParking> selectCollection(CollectionParking collectionParking);
    List<CollectionParking> collectionForIndex(Page<CollectionParking> page);
    List<CollectionParking> collectionForIndexSecond(Page<CollectionParking> page);
    void setDefaultScan(CollectionParking collectionParking);
    int cusCancelCollection(CollectionParking collectionParking);
    int deleteData(CollectionParking collectionParking);
}