package com.boxiang.share.product.parking.service;

import com.boxiang.share. product.parking.po.Parking;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.parking.po.WeekPriceModel;

public interface ParkingService {

	List<Parking> selectList(Parking parking);
	
	Page<Parking> queryListPage(Page<Parking> page);
	
	Parking queryById(java.lang.String id);
	Parking queryByIdTest(java.lang.String id);
	
	void add(Parking parking);

	void delete(java.lang.String id);
	
	void update(Parking parking);
	
	void batchUpdate(List<Parking> tParkingList);
	
	List<Parking> getList(Parking parking);
	List<Parking> carListByCM(Parking parking);
    List<Parking> carListByCP(Parking parking);
    List<Parking> queryCooperation();
    List<Parking> getListParking(Parking parking);

	List<Parking> queryBlueParkingList();
	Parking parkingInfoDetail(Parking parking);

	int updateParkingCanUse(String parkingId, int count);
	String searchParking(Page<Parking> page,String im);

	boolean parkIsFull(Parking parking);

	int parkerCount(String parkingId);

	Integer queryTargetParkingCanUseTotalCount(String parkingId);
	List<Map> queryParkingRelevance(String parkingId);

	List<Parking> queryTargetParking(String parkingId);
	Parking selectHomeAndWorkId(Parking parking);
	Parking indexShowByPid(Parking parking);
	List<Parking> searchParkListByLLDemo2(Parking parking);
	List<Parking> searchParkListbyNameDemo2(Parking parking);

	List<?> canParkList(String lng, String lat);

	List<WeekPriceModel> getWeekPriceModel(String parkingId);
	Parking selectParkingName(Parking parking);
	void updateCanUse();

	List<Parking> selectListByExport(Parking parking);

	List<Parking> getParkingListForCarLov(Page<Parking> page);

	String getAppoPkList(Map<String,Object> param) throws ParseException;
}