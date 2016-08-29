package com.boxiang.share.product.coupon.service;

import com.boxiang.share.product.coupon.po.RedeemRecord;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface RedeemRecordService {

	List<RedeemRecord> selectList(RedeemRecord redeemRecord);
	
	Page<RedeemRecord> queryListPage(Page<RedeemRecord> page);

	List<Object> queryListPageBack(Object page);

	RedeemRecord queryById(Integer id);
	
	void add(RedeemRecord redeemRecord);

	void delete(Integer id);
	
	void update(RedeemRecord redeemRecord);
	
	void batchUpdate(List<RedeemRecord> redeemRecordList);

	int selectCount(RedeemRecord redeemRecord);
}