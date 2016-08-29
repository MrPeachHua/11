package com.boxiang.share.product.customer.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.customer.dao.OrderPayRecordDao;
import com.boxiang.share.product.customer.po.OrderPayRecord;
import com.boxiang.share.product.customer.service.OrderPayRecordService;

@DataSource(DataSourceEnum.MASTER)
@Service("orderPayRecord")
public class OrderPayRecordServiceImpl implements OrderPayRecordService {
	@Resource(name="orderPayRecordDao")
	private OrderPayRecordDao orderPayRecordDao;
	
	@Override
	public List<OrderPayRecord> selectList(OrderPayRecord orderPayRecord) {
		return orderPayRecordDao.selectList(orderPayRecord);
	}

	@Override
	public Page<OrderPayRecord> queryListPage(Page<OrderPayRecord> page) {
	    page.setResultList(orderPayRecordDao.queryListPage(page));
		return page;
	}
	
	@Override
	public OrderPayRecord queryById(java.lang.Integer id) {
		return orderPayRecordDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(OrderPayRecord orderPayRecord) {
		orderPayRecordDao.insert(orderPayRecord);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		orderPayRecordDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(OrderPayRecord orderPayRecord) {
		orderPayRecordDao.update(orderPayRecord);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<OrderPayRecord> orderPayRecordList) {
		orderPayRecordDao.batchUpdate(orderPayRecordList);
	}
}