package com.boxiang.share.product.coupon.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.coupon.dao.SceneRecordDao;
import com.boxiang.share.product.coupon.po.SceneRecord;
import com.boxiang.share.product.coupon.service.SceneRecordService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("sceneRecord")
public class SceneRecordServiceImpl implements SceneRecordService {
	@Resource(name="sceneRecordDao")
	private SceneRecordDao sceneRecordDao;

	@Override
	public List<SceneRecord> selectList(SceneRecord sceneRecord) {
		return sceneRecordDao.selectList(sceneRecord);
	}

	@Override
	public Page<SceneRecord> queryListPage(Page<SceneRecord> page) {
		page.setResultList(sceneRecordDao.queryListPage(page));
		return page;
	}

	@Override
	public SceneRecord queryById(java.lang.Integer id) {
		return sceneRecordDao.queryById(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(SceneRecord sceneRecord) {
		sceneRecordDao.insert(sceneRecord);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		sceneRecordDao.delete(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(SceneRecord sceneRecord) {
		sceneRecordDao.update(sceneRecord);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<SceneRecord> sceneRecordList) {
		sceneRecordDao.batchUpdate(sceneRecordList);
	}
}