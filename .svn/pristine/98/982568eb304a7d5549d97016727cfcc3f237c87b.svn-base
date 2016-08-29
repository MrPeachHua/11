package com.boxiang.share.system.synwhite.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.boxiang.share.product.order.po.Propertyparkinginfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.synwhite.dao.SpecialparkinginfoDao;
import com.boxiang.share.system.synwhite.po.Specialparkinginfo;
import com.boxiang.share.system.synwhite.service.SpecialparkinginfoService;

@DataSource(DataSourceEnum.MASTER)
@Service("tSpecialparkinginfo")
public class SpecialparkinginfoServiceImpl implements SpecialparkinginfoService {
	@Resource(name="specialparkinginfoDao")
	private SpecialparkinginfoDao tSpecialparkinginfoDao;
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void updateParkingInfo(Specialparkinginfo specialparkinginfo){
		tSpecialparkinginfoDao.updateParkingInfo(specialparkinginfo);
	}
	@Override
	public List<Specialparkinginfo> selectList(Specialparkinginfo tSpecialparkinginfo) {
		return tSpecialparkinginfoDao.selectList(tSpecialparkinginfo);
	}

	@Override
	public Page<Specialparkinginfo> queryListPage(Page<Specialparkinginfo> page) {
	    page.setResultList(tSpecialparkinginfoDao.queryListPage(page));
		return page;
	}
	
	@Override
	public Specialparkinginfo queryById(java.lang.String id) {
		return tSpecialparkinginfoDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(Specialparkinginfo tSpecialparkinginfo) {
		tSpecialparkinginfoDao.insert(tSpecialparkinginfo);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Specialparkinginfo specialparkinginfo) {
		tSpecialparkinginfoDao.delete(specialparkinginfo);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(Specialparkinginfo tSpecialparkinginfo) {
		tSpecialparkinginfoDao.update(tSpecialparkinginfo);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Specialparkinginfo> tSpecialparkinginfoList) {
		tSpecialparkinginfoDao.batchUpdate(tSpecialparkinginfoList);
	}
	@Override
	public Page<Specialparkinginfo> queryListPageForBack(Page<Specialparkinginfo> page) {
	    page.setResultList(tSpecialparkinginfoDao.queryListPageForBack(page));
		return page;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void deleteByCarnumberAndId(Specialparkinginfo specialparkinginfo) {
		tSpecialparkinginfoDao.deleteByCarnumberAndId(specialparkinginfo);
	}
	
	@Override
	public List<Specialparkinginfo> queryListExcel(Specialparkinginfo specialparkinginfo) {
		return tSpecialparkinginfoDao.queryListExcel(specialparkinginfo);
	}
}