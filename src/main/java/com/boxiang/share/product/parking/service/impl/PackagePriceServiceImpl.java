package com.boxiang.share.product.parking.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.parking.dao.PackagePriceDao;
import com.boxiang.share.product.parking.po.PackagePrice;
import com.boxiang.share.product.parking.service.PackagePriceService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("packagePrice")
public class PackagePriceServiceImpl implements PackagePriceService {
	@Resource(name="packagePriceDao")
	private PackagePriceDao packagePriceDao;
	
	@Override
	public List<PackagePrice> selectList(PackagePrice packagePrice) {
		return packagePriceDao.selectList(packagePrice);
	}

	@Override
	public Page<PackagePrice> queryListPage(Page<PackagePrice> page) {
	    page.setResultList(packagePriceDao.queryListPage(page));
		return page;
	}
	
	@Override
	public PackagePrice queryById(Integer id) {
		return packagePriceDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(PackagePrice packagePrice) {
		packagePriceDao.insert(packagePrice);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		packagePriceDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(PackagePrice packagePrice) {
		packagePriceDao.update(packagePrice);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<PackagePrice> packagePriceList) {
		packagePriceDao.batchUpdate(packagePriceList);
	}

	@Override
	public List<PackagePrice> queryByParkingIdAndWeek(String parkingId, String[] weeks) {
		return packagePriceDao.queryByParkingIdAndWeek(parkingId, weeks);
	}

	@Override
	public List<PackagePrice> queryByParkingId(String parkingId) {
		PackagePrice entity = new PackagePrice();
		entity.setParkingId(parkingId);
		return packagePriceDao.queryByParkingId(parkingId);
	}
}