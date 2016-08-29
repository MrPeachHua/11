package com.boxiang.share.product.activity.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.activity.po.ActivityQrCode;
import org.apache.ibatis.annotations.Param;

public interface ActivityQrCodeDao extends IMybatisBaseDao<ActivityQrCode> {

    void batchUpdate(List<ActivityQrCode> activityQrCodeList);

    List<Map<String, Object>> queryListPageV2(Object page);

    int updateScanCount(@Param("id") int id, @Param("scanCount") int scanCount);
}