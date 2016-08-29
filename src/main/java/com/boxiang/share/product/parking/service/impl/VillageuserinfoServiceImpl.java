package com.boxiang.share.product.parking.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.parking.dao.VillageuserinfoDao;
import com.boxiang.share.product.parking.po.Villageuserinfo;
import com.boxiang.share.product.parking.service.VillageuserinfoService;

@DataSource(DataSourceEnum.MASTER)
@Service("tVillageuserinfo")
public class VillageuserinfoServiceImpl implements VillageuserinfoService {
	@Resource(name="villageuserinfoDao")
	private VillageuserinfoDao villageuserinfoDao;
	
	@Override
	public List<Villageuserinfo> selectList(Villageuserinfo villageuserinfo) {
		return villageuserinfoDao.selectList(villageuserinfo);
	}

	@Override
	public Page<Villageuserinfo> queryListPage(Page<Villageuserinfo> page) {
	    page.setResultList(villageuserinfoDao.queryListPage(page));
		return page;
	}
	
	@Override
	public Villageuserinfo queryById(java.lang.String id) {
		return villageuserinfoDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(Villageuserinfo villageuserinfo) {
		villageuserinfoDao.insert(villageuserinfo);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.String id) {
		villageuserinfoDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(Villageuserinfo villageuserinfo) {
		villageuserinfoDao.update(villageuserinfo);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Villageuserinfo> villageuserinfoList) {
		villageuserinfoDao.batchUpdate(villageuserinfoList);
	}
}