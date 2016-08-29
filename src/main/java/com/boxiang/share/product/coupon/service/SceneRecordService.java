package com.boxiang.share.product.coupon.service;

import com.boxiang.share.product.coupon.po.SceneRecord;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface SceneRecordService {

	List<SceneRecord> selectList(SceneRecord sceneRecord);
	
	Page<SceneRecord> queryListPage(Page<SceneRecord> page);
	
	SceneRecord queryById(Integer id);
	
	void add(SceneRecord sceneRecord);

	void delete(Integer id);
	
	void update(SceneRecord sceneRecord);
	
	void batchUpdate(List<SceneRecord> sceneRecordList);
}