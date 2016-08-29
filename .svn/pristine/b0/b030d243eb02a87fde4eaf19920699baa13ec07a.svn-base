package com.boxiang.share.product.ccic.service;

import com.boxiang.share.product.ccic.bo.CarMessageSync;
import com.boxiang.share.product.ccic.bo.CarResponse;
import com.boxiang.share.product.ccic.po.CcicOrderInfo;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;

public interface CcicOrderInfoService {

	List<CcicOrderInfo> selectList(CcicOrderInfo ccicOrderInfo);
	
	Page<CcicOrderInfo> queryListPage(Page<CcicOrderInfo> page);
	
	CcicOrderInfo queryById(Integer id);
	
	void add(CcicOrderInfo ccicOrderInfo);

	void delete(Integer id);
	
	void update(CcicOrderInfo ccicOrderInfo);
	
	void batchUpdate(List<CcicOrderInfo> ccicOrderInfoList);

	Map getOrderInfo(Map map);

	String saveCheck(Map map);

	String savePay(Map map);

	String savePolicy(Map map);
	
	CarResponse carUnderwriteSync(CarMessageSync msg);
	
	CarResponse carPaySync(CarMessageSync msg);
	
	CarResponse carPolicySync(CarMessageSync msg);
}