package com.boxiang.share.product.activity.service;

import com.boxiang.share.product.activity.po.Activity;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface ActivityService {

	List<Activity> selectList(Activity activity);
	
	Page<Activity> queryListPage(Page<Activity> page);
	
	Activity queryById(Integer id);
	
	void add(Activity activity);

	void delete(Integer id);
	
	void update(Activity activity);
	
	void batchUpdate(List<Activity> activityList);

	List<Activity> queryListPageV2(Object page);
}