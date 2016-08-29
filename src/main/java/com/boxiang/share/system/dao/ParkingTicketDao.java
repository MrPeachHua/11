package com.boxiang.share.system.dao;

import java.util.List;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.system.po.ParkingTicket;

public interface ParkingTicketDao extends IMybatisBaseDao<ParkingTicket> {
    
    void batchUpdate(List<ParkingTicket> tParkingTicketList);
}