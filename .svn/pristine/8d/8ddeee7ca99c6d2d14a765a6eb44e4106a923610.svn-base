package com.boxiang.share.product.order.service;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.customer.po.Customer;
import com.boxiang.share.product.order.po.Monthlyparkinginfo;

public interface MonthlyparkinginfoService {

	List<Monthlyparkinginfo> selectList(Monthlyparkinginfo monthlyparkinginfo);
	
	Page<Monthlyparkinginfo> queryListPage(Page<Monthlyparkinginfo> page);
	
	Monthlyparkinginfo queryById(java.lang.String id);
	
	void add(Monthlyparkinginfo monthlyparkinginfo);

	void delete(java.lang.String id);
	
	void update(Monthlyparkinginfo monthlyparkinginfo);
	void updateParkingInfo(Monthlyparkinginfo monthlyparkinginfo);
	void updateParkingTime(Monthlyparkinginfo monthlyparkinginfo);
	void batchUpdate(List<Monthlyparkinginfo> monthlyparkinginfoList);

	void deleteByCarnumberAndId(Monthlyparkinginfo monthlyparkinginfo);
	  List<Monthlyparkinginfo> queryListExcel(Monthlyparkinginfo monthlyparkinginfo);
	 boolean  saveList(List<List<Monthlyparkinginfo>> monthlyParkingInfoSecondList, int count);

	List<Map<String,Object>> getMonthlyEquity(Object obj);
}