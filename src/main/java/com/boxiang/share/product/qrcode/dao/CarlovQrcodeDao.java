package com.boxiang.share.product.qrcode.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.qrcode.po.CarlovQrcode;

public interface CarlovQrcodeDao extends IMybatisBaseDao<CarlovQrcode> {
    
    void batchUpdate(List<CarlovQrcode> tCarlovQrcodeList);

    List<CarlovQrcode> queryListPageForQrcode(Page<CarlovQrcode> page);

    List<CarlovQrcode> selectDayData(Map<String,Object> param);
}