package com.boxiang.share.product.car.service;

import com.boxiang.share.product.car.po.CarModel;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;

public interface CarModelService {

    List<CarModel> selectList(CarModel carModel);

    Page<CarModel> queryListPage(Page<CarModel> page);

    CarModel queryById(Integer id);

    void add(CarModel carModel);

    void delete(Integer id);

    void update(CarModel carModel);

    void batchUpdate(List<CarModel> carModelList);

    public List<CarModel> getRecursionCarModel();

    Map<String, String> selectMax(String nodeCode);
}