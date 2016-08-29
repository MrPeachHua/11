package com.boxiang.share.product.parking.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.parking.dao.DiscountParkingPriceDao;
import com.boxiang.share.product.parking.po.DiscountParkingPrice;
import com.boxiang.share.product.parking.service.DiscountParkingPriceService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("discountParkingPrice")
public class DiscountParkingPriceServiceImpl implements DiscountParkingPriceService {
	@Resource(name="discountParkingPriceDao")
	private DiscountParkingPriceDao discountParkingPriceDao;
	
	@Override
	public List<DiscountParkingPrice> selectList(DiscountParkingPrice discountParkingPrice) {
		return discountParkingPriceDao.selectList(discountParkingPrice);
	}

	@Override
	public Page<DiscountParkingPrice> queryListPage(Page<DiscountParkingPrice> page) {
	    page.setResultList(discountParkingPriceDao.queryListPage(page));
		return page;
	}
	
	@Override
	public DiscountParkingPrice queryById(String id) {
		return discountParkingPriceDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(DiscountParkingPrice discountParkingPrice) {
		discountParkingPriceDao.insert(discountParkingPrice);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(String id) {
		discountParkingPriceDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(DiscountParkingPrice discountParkingPrice) {
		discountParkingPriceDao.update(discountParkingPrice);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<DiscountParkingPrice> discountParkingPriceList) {
		discountParkingPriceDao.batchUpdate(discountParkingPriceList);
	}

	@Override
	public String numToStr(String dayOfWeek) {
		switch (dayOfWeek) {
			case "1":
				return "monday";
			case "2":
				return "tuesday";
			case "3":
				return "wednesday";
			case "4":
				return "thursday";
			case "5":
				return "friday";
			case "6":
				return "saturday";
			case "7":
				return "sunday";
			default:
				return null;
		}
	}

}