package com.boxiang.share.product.customer.dao;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;

import com.boxiang.share.product.customer.po.RechargeRule;
import com.boxiang.share.product.customer.po.RechargeRuleGiftAmount;

public interface RechargeRuleDao extends IMybatisBaseDao<RechargeRule> {
    
    void batchUpdate(List<RechargeRule> rechargeRuleList);
    List<RechargeRule> queryRechargeRule(Map<String,String> param);
    List<RechargeRuleGiftAmount> selectRule();
    void deleteRule(RechargeRule rechargeRule);
    void updateRule(RechargeRule rechargeRule);
}