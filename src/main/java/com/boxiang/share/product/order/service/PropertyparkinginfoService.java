package com.boxiang.share.product.order.service;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.order.po.Propertyparkinginfo;

public interface PropertyparkinginfoService {

	List<Propertyparkinginfo> selectList(Propertyparkinginfo propertyparkinginfo);
	
	Page<Propertyparkinginfo> queryListPage(Page<Propertyparkinginfo> page);
	
	Propertyparkinginfo queryById(java.lang.String id);
	List<Propertyparkinginfo> queryListExcel(Propertyparkinginfo propertyparkinginfo);
	void add(Propertyparkinginfo propertyparkinginfo);
	void updateParkingInfo(Propertyparkinginfo propertyparkinginfo);
	void updateParkingTime(Propertyparkinginfo propertyparkinginfo);
	void delete(java.lang.String id);
	void deleteByCarNumber(Propertyparkinginfo propertyparkinginfo);
	void update(Propertyparkinginfo propertyparkinginfo);
	
	void batchUpdate(List<Propertyparkinginfo> propertyparkinginfoList);
	 boolean  saveList(List<List<Propertyparkinginfo>> propertyparkinginfoSecondList, int count);
}