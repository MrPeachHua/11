package com.boxiang.share.product.customer.service;

import com.boxiang.share.product.customer.po.RechargeRule;


import java.util.List;

import com.boxiang.framework.base.page.Page;

import javax.servlet.http.HttpServletRequest;

public interface RechargeRuleService {

	List<RechargeRule> selectList(RechargeRule rechargeRule);
	
	Page<RechargeRule> queryListPage(Page<RechargeRule> page);
	
	RechargeRule queryById(Integer id);
	
	void add(RechargeRule rechargeRule);

	void delete(Integer id);
	
	void update(RechargeRule rechargeRule);
	
	void batchUpdate(List<RechargeRule> rechargeRuleList);
	
	// List<RechargeRule> queryRechargeRule();
	 String queryRechargeRule2(HttpServletRequest request);
	 void deleteRule(RechargeRule rechargeRule);
	 void updateRule(RechargeRule rechargeRule);
}