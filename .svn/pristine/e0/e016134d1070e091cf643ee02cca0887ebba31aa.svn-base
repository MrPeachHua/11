package com.boxiang.share.product.carlife.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.boxiang.share.product.carlife.dao.CarlifeRefuelCardDao;
import com.boxiang.share.product.carlife.po.CarlifeRefuelCard;
import com.boxiang.share.product.carlife.service.CarlifeRefuelCardService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("tCarlifeRefuelCard")
public class CarlifeRefuelCardServiceImpl implements CarlifeRefuelCardService {
	@Resource(name="carlifeRefuelCardDao")
	private CarlifeRefuelCardDao carlifeRefuelCardDao;
	
	@Override
	public List<CarlifeRefuelCard> selectList(CarlifeRefuelCard carlifeRefuelCard) {
		return carlifeRefuelCardDao.selectList(carlifeRefuelCard);
	}

	@Override
	public Page<CarlifeRefuelCard> queryListPage(Page<CarlifeRefuelCard> page) {
	    page.setResultList(carlifeRefuelCardDao.queryListPage(page));
		return page;
	}
	
	@Override
	public CarlifeRefuelCard queryById(java.lang.Integer id) {
		return carlifeRefuelCardDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(CarlifeRefuelCard carlifeRefuelCard) {
		carlifeRefuelCardDao.insert(carlifeRefuelCard);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		carlifeRefuelCardDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(CarlifeRefuelCard carlifeRefuelCard) {
		carlifeRefuelCardDao.update(carlifeRefuelCard);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<CarlifeRefuelCard> carlifeRefuelCardList) {
		carlifeRefuelCardDao.batchUpdate(carlifeRefuelCardList);
	}
}