package com.boxiang.share.product.parker.service.impl;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.parker.dao.CollectionParkingDao;
import com.boxiang.share.product.parker.po.CollectionParking;
import com.boxiang.share.product.parker.service.CollectionParkingService;
import com.boxiang.share.utils.ShangAnMessageType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/3.
 */
@DataSource(DataSourceEnum.MASTER)
@Service("collectionParking")
public class CollectionParkingServiceImpl implements CollectionParkingService {
    @Resource(name="collectionParkingDao")
    private CollectionParkingDao collectionParkingDao;
    @Override
    public int cusCancelCollection(CollectionParking collectionParking){
        return  collectionParkingDao.cusCancelCollection(collectionParking);
    }
    @Override
    public void setDefaultScan(CollectionParking collectionParking)
    {
        collectionParkingDao.setDefaultScan(collectionParking);
    }
    @Override
    public List<CollectionParking> selectCollection(CollectionParking collectionParking)
    {
        return collectionParkingDao.selectCollection(collectionParking);
    }

    @Override
    public int deleteData(CollectionParking collectionParking) {
        return collectionParkingDao.deleteData(collectionParking);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
    public void add(CollectionParking collectionParking) {
        collectionParkingDao.insert(collectionParking);
    }
    @Override
    public String searchParkingCollection(Page<CollectionParking> page,String im)
    {
        String message = null;
        List<CollectionParking> list = new ArrayList<CollectionParking>();
        List <Map<String,Object>> collectionList = new ArrayList<Map<String,Object>>();
        if ("1".equals(page.getParams().get("index")))
        {
            list = collectionParkingDao.collectionForIndex(page);
        }else if ("2".equals(page.getParams().get("index")))
        {
            list = collectionParkingDao.collectionForIndexSecond(page);
        }else {
            list = null;
        }
        if (list!=null && list.size()>0)
        {
            for (CollectionParking collectionParking : list) {
                Map<String,Object> paraMap = new HashMap<String,Object>();
                paraMap.put("parkingName",collectionParking.getParkingName());
                paraMap.put("parkingCountry",collectionParking.getParkingCountry());
                paraMap.put("parkingProvince",collectionParking.getParkingProvince());
                paraMap.put("parkingCity",collectionParking.getParkingCity());
                paraMap.put("parkingCounty",collectionParking.getParkingCounty());
                paraMap.put("parkingArea",collectionParking.getParkingArea());
                paraMap.put("parkingAddress",collectionParking.getParkingAddress());
                paraMap.put("parkingLatitude",collectionParking.getParkingLatitude());
                paraMap.put("parkingLongitude",collectionParking.getParkingLongitude());
                paraMap.put("parkingCount",collectionParking.getParkingCount());
                paraMap.put("parkingCanUse",collectionParking.getParkingCanUse());
                paraMap.put("parkingId",collectionParking.getParkingId());
                paraMap.put("parkingPath",im+collectionParking.getParkingPath());
                paraMap.put("peacetimePrice",collectionParking.getPeacetimePrice());
                paraMap.put("canUse",collectionParking.getCanUse());
                paraMap.put("isCharge",collectionParking.getIsCharge());
                collectionList.add(paraMap);
            }
           message = ShangAnMessageType.toShangAnJson("0", "查询成功", "collectionList", collectionList);
        }else {
            List<CollectionParking> list1 = new ArrayList<>();
            message = ShangAnMessageType.toShangAnJson("0", "查询成功", "collectionList",list1);
        }
        return message;
    }

    @Override
    public List<CollectionParking> selectList(CollectionParking collectionParking) {
        return collectionParkingDao.selectList(collectionParking);
    }
}
