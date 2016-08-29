package com.boxiang.share.product.car.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.car.po.CarModel;

public interface CarModelDao extends IMybatisBaseDao<CarModel> {

    void batchUpdate(List<CarModel> carModelList);

    Map<String, String> selectMax(Map<String, String> map);
}