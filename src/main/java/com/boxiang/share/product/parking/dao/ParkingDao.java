package com.boxiang.share.product.parking.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.product.parking.po.Parking;
import org.apache.ibatis.annotations.Param;

public interface ParkingDao extends IMybatisBaseDao<Parking> {
    
    void batchUpdate(List<Parking> tParkingList);
    Parking queryByIdTest(String id);
    List<Parking> getList(Parking parking);
    List<Parking> carListByCM(Parking parking);
    List<Parking> carListByCP(Parking parking);
    List<Parking> queryCooperation();
    List<Parking> getListParking(Parking parking);

    List<Parking> queryBlueParkingList();

    Parking parkingInfoDetail(Parking parking);

    int updateParkingCanUse(Map<String, Object> params);
    Integer countByIndexParking(Parking parking);
    List<Parking> getParkingByCustomerId(Page<Parking> page);
    List<Parking> getParkingByCustomerIdAndIn(Page<Parking> page);
    List<Map> queryParkingRelevance(String parkingId);
    Integer queryTargetParkingCanUseTotalCount(String parkingId);

    List<Parking> queryTargetParking(String parkingId);
    Parking selectHomeAndWorkId(Parking parking);
    Parking indexShowByPid(Parking parking);
    List<Parking> searchParkListByLLDemo2(Parking parking);
    List<Parking> searchParkListbyNameDemo2(Parking parking);

    List<?> canParkList(@Param("lng") String lng, @Param("lat") String lat);
    Parking selectParkingName(Parking parking);
    List<Parking> selectListByExport(Parking parking);
    void updateCanUse();
    List<Parking> getParkingListForCarLov(Page<Parking> page);
    List<Parking> getAppoPkList(Page<Parking> page);
}