package com.boxiang.share.product.parking.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.parking.po.Villageuserinfo;

public interface VillageuserinfoDao extends IMybatisBaseDao<Villageuserinfo> {
    
    void batchUpdate(List<Villageuserinfo> tVillageuserinfoList);
}