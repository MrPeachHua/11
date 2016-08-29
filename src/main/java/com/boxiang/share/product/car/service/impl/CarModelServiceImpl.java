package com.boxiang.share.product.car.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.Constants;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.car.dao.CarModelDao;
import com.boxiang.share.product.car.po.CarModel;
import com.boxiang.share.product.car.service.CarModelService;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

@DataSource(DataSourceEnum.MASTER)
@Service("carModel")
public class CarModelServiceImpl implements CarModelService {
    @Resource(name = "carModelDao")
    private CarModelDao carModelDao;
    @Resource(name="dataEhCache") 
    private Cache  ehCache;

    @Override
    public List<CarModel> selectList(CarModel carModel) {
        return carModelDao.selectList(carModel);
    }

    @Override
    public Page<CarModel> queryListPage(Page<CarModel> page) {
        page.setResultList(carModelDao.queryListPage(page));
        return page;
    }

    @Override
    public CarModel queryById(Integer id) {
        return carModelDao.queryById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void add(CarModel carModel) {
        carModelDao.insert(carModel);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void delete(Integer id) {
        carModelDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void update(CarModel carModel) {
        carModelDao.update(carModel);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void batchUpdate(List<CarModel> carModelList) {
        carModelDao.batchUpdate(carModelList);
    }

    /**
     * 获得递归之后的车型选择列表
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<CarModel> getRecursionCarModel() {
    	if(ehCache.get("getRecursionCarModel")!=null && ehCache.get("getRecursionCarModel").getObjectValue()!=null){
    		return (List<CarModel>) ehCache.get("getRecursionCarModel").getObjectValue();
    	}
        CarModel queryModel = new CarModel();
        queryModel.setIsUsed(Constants.TRUE);

        List<CarModel> allList = this.selectList(queryModel);
        List<CarModel> rootList = new ArrayList<>(26);
        for (int i = allList.size() - 1; i >= 0; i--) {
            if (!allList.get(i).getParentCode().equals("0")) continue;
            rootList.add(0, allList.remove(i));
        }
        for (CarModel cm : rootList) recursionCarModel(cm, allList);
        
        ehCache.put(new Element("getRecursionCarModel", rootList));
        return rootList;
    }

    @Override
    public Map<String, String> selectMax(String nodeCode) {
        Map<String, String> map = new HashMap<>(1);
        map.put("nodeCode", nodeCode);
        return carModelDao.selectMax(map);
    }

    /**
     * 递归追加子节点
     */
    private void recursionCarModel(CarModel carModel, List<CarModel> allCarModelList) {
        for (CarModel cm : allCarModelList) {
            if (!cm.getParentCode().equals(carModel.getNodeCode())) continue;
            carModel.getChildrenList().add(cm);
            recursionCarModel(cm, allCarModelList);
        }
    }

}