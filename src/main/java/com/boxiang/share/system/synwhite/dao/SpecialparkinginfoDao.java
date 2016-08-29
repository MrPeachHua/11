package com.boxiang.share.system.synwhite.dao;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.system.synwhite.po.Specialparkinginfo;

public interface SpecialparkinginfoDao extends IMybatisBaseDao<Specialparkinginfo> {
    
    void batchUpdate(List<Specialparkinginfo> tSpecialparkinginfoList);
    //查询白名单特殊车辆list
    List<Specialparkinginfo>  findByVillageIdForUserSync(Specialparkinginfo specialparkinginfo);
    
    List<Specialparkinginfo> queryListPageForBack(Page<Specialparkinginfo> page);
    
    int  deleteByCarnumberAndId(Specialparkinginfo specialparkinginfo);
    
    List<Specialparkinginfo> queryListExcel(Specialparkinginfo specialparkinginfo);
    int updateParkingInfo(Specialparkinginfo specialparkinginfo);
}