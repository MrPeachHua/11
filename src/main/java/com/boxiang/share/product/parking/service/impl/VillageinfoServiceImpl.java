package com.boxiang.share.product.parking.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.parking.dao.VillageinfoDao;
import com.boxiang.share.product.parking.po.Villageinfo;
import com.boxiang.share.product.parking.service.VillageinfoService;

@DataSource(DataSourceEnum.MASTER)
@Service("villageinfo")
public class VillageinfoServiceImpl implements VillageinfoService {
	@Resource(name="villageinfoDao")
	private VillageinfoDao villageinfoDao;
	
	@Override
	public List<Villageinfo> selectList(Villageinfo villageinfo) {
		return villageinfoDao.selectList(villageinfo);
	}

	@Override
	public Page<Villageinfo> queryListPage(Page<Villageinfo> page) {
	    page.setResultList(villageinfoDao.queryListPage(page));
		return page;
	}
	
	@Override
	public Villageinfo queryById(java.lang.String id) {
		return villageinfoDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(Villageinfo villageinfo) {
		villageinfoDao.insert(villageinfo);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.String id) {
		villageinfoDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(Villageinfo villageinfo) {
		villageinfoDao.update(villageinfo);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Villageinfo> villageinfoList) {
	  villageinfoDao.batchUpdate(villageinfoList);
	}

	@Override
	public List<Villageinfo> selectAllBluePark() {
		return villageinfoDao.selectAllBluePark();
	}
}