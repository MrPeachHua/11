package com.boxiang.share.product.customer.service;

import com.boxiang.share.product.customer.po.RechargeRule;
import com.boxiang.share.product.customer.po.RechargeRuleGiftAmount;

import java.util.List;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.customer.po.Rule;
import com.boxiang.share.user.po.UserInfo;



public interface RechargeRuleGiftAmountService {

	List<RechargeRuleGiftAmount> selectList(RechargeRuleGiftAmount rechargeRuleGiftAmount);
	
	Page<RechargeRuleGiftAmount> queryListPage(Page<RechargeRuleGiftAmount> page);
	
	RechargeRuleGiftAmount queryById(Integer id);
	
	void add(RechargeRuleGiftAmount rechargeRuleGiftAmount);

	void delete(Integer id);
	
	void update(RechargeRuleGiftAmount rechargeRuleGiftAmount);
	void batchUpdateRule(List<RechargeRuleGiftAmount> rechargeRuleGiftAmountList);
	void deleteRuleGift(RechargeRuleGiftAmount rechargeRuleGiftAmount);
	void batchUpdate(List<RechargeRuleGiftAmount> rechargeRuleGiftAmountList);
	void updateRuleAmount(RechargeRuleGiftAmount rechargeRuleGiftAmount);
	Integer getGiftAmount(Integer amount);

	void saveRule(Rule rule, RechargeRule rechargeRule, UserInfo currUser) throws Exception;
}