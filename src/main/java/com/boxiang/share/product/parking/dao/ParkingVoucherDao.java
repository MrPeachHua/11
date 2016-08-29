package com.boxiang.share.product.parking.dao;
import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.parking.po.ParkingVoucher;

public interface ParkingVoucherDao extends IMybatisBaseDao<ParkingVoucher> {
    
    void batchUpdate(List<ParkingVoucher> parkingVoucherList);
    List<ParkingVoucher> selectListByCustomer(Page<ParkingVoucher> page);
    List<ParkingVoucher> selectListByDate(ParkingVoucher pv);
    List<ParkingVoucher> queryCountStatus(ParkingVoucher pv);
}