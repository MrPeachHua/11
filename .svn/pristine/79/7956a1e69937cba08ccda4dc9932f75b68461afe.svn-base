package com.boxiang.share.product.villageowner.service;

import com.boxiang.share.product.villageowner.po.VillageOwner;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface VillageOwnerService {

	List<VillageOwner> selectList(VillageOwner villageOwner);
	
	Page<VillageOwner> queryListPage(Page<VillageOwner> page);
	
	VillageOwner queryById(Integer id);
	
	void add(VillageOwner villageOwner);

	void delete(Integer id);
	
	void update(VillageOwner villageOwner);
	
	void batchUpdate(List<VillageOwner> villageOwnerList);

	List<Object> queryListPageV2(Object page);

	List<VillageOwner> batchInsert(List<VillageOwner> list);
}