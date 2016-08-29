package com.boxiang.share.system.service;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.po.ParkingTicket;

public interface ParkingTicketService {

	List<ParkingTicket> selectList(ParkingTicket parkingTicket);
	
	Page<ParkingTicket> queryListPage(Page<ParkingTicket> page);
	
	ParkingTicket queryById(java.lang.String id);
	
	void add(ParkingTicket parkingTicket);

	void delete(java.lang.String id);
	
	void update(ParkingTicket parkingTicket);
	
	void batchUpdate(List<ParkingTicket> parkingTicketList);
}