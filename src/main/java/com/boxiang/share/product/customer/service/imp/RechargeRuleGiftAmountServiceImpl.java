package com.boxiang.share.product.customer.service.imp;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.product.customer.po.RechargeRule;
import com.boxiang.share.product.customer.po.Rule;
import com.boxiang.share.product.customer.service.RechargeRuleService;
import com.boxiang.share.user.po.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.customer.dao.RechargeRuleGiftAmountDao;
import com.boxiang.share.product.customer.po.RechargeRuleGiftAmount;
import com.boxiang.share.product.customer.service.RechargeRuleGiftAmountService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("rechargeRuleGiftAmount")
public class RechargeRuleGiftAmountServiceImpl implements RechargeRuleGiftAmountService {
	@Resource(name="rechargeRuleGiftAmountDao")
	private RechargeRuleGiftAmountDao rechargeRuleGiftAmountDao;

	@Resource
	private RechargeRuleService rechargeRuleService;
	
	@Override
	public List<RechargeRuleGiftAmount> selectList(RechargeRuleGiftAmount rechargeRuleGiftAmount) {
		return rechargeRuleGiftAmountDao.selectList(rechargeRuleGiftAmount);
	}

	@Override
	public Page<RechargeRuleGiftAmount> queryListPage(Page<RechargeRuleGiftAmount> page) {
	    page.setResultList(rechargeRuleGiftAmountDao.queryListPage(page));
		return page;
	}
	
	@Override
	public RechargeRuleGiftAmount queryById(Integer id) {
		return rechargeRuleGiftAmountDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(RechargeRuleGiftAmount rechargeRuleGiftAmount) {
		rechargeRuleGiftAmountDao.insert(rechargeRuleGiftAmount);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		rechargeRuleGiftAmountDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(RechargeRuleGiftAmount rechargeRuleGiftAmount) {
		rechargeRuleGiftAmountDao.update(rechargeRuleGiftAmount);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void deleteRuleGift(RechargeRuleGiftAmount rechargeRuleGiftAmount){
		rechargeRuleGiftAmountDao.deleteRuleGift(rechargeRuleGiftAmount);
	}
  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<RechargeRuleGiftAmount> rechargeRuleGiftAmountList) {
		rechargeRuleGiftAmountDao.batchUpdate(rechargeRuleGiftAmountList);
	}
  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
public void batchUpdateRule(
		List<RechargeRuleGiftAmount> rechargeRuleGiftAmountList) {
	  	rechargeRuleGiftAmountDao.batchUpdateRule(rechargeRuleGiftAmountList);
}
  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
  public void updateRuleAmount(RechargeRuleGiftAmount rechargeRuleGiftAmount){
	  rechargeRuleGiftAmountDao.updateRuleAmount(rechargeRuleGiftAmount);
  }
	/**
	 * 根据充值金额获取赠送金额
	 * @param amount 充值的金额，单位 分
	 * @return 返回赠送金额，单位 分
	 */
	@Override
	public Integer getGiftAmount(Integer amount) {
		RechargeRuleGiftAmount ruleGiftAmount = new RechargeRuleGiftAmount();
		ruleGiftAmount.setAmount(amount);
		ruleGiftAmount = rechargeRuleGiftAmountDao.getGiftAmount(ruleGiftAmount);
		if (ruleGiftAmount != null) {
			int giftAmount = ruleGiftAmount.getGiftAmount();
			int scale = amount / ruleGiftAmount.getAmount();
			if (scale > 0) {
				giftAmount = scale * giftAmount;
			}
			return giftAmount;
		}
		return 0;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void saveRule(Rule rule, RechargeRule rechargeRule, UserInfo currUser) throws Exception {
		rechargeRule.setCreateor(currUser.getUserNum());
		rechargeRule.setIsUsed(Constants.TRUE);
		rechargeRule.setCreateDate(new Date());
		rechargeRuleService.add(rechargeRule);
		if (rule.getRechargeRuleGiftAmount().size() != 0) {
			for (RechargeRuleGiftAmount giftAmount : rule.getRechargeRuleGiftAmount()) {
				giftAmount.setAmount(giftAmount.getAmount() * 100);
				giftAmount.setGiftAmount(giftAmount.getGiftAmount() * 100);
				giftAmount.setRuleId(rechargeRule.getId());
				giftAmount.setIsUsed(Constants.TRUE);
				giftAmount.setCreateor(currUser.getUserNum());
				giftAmount.setCreateDate(new Date());
				this.add(giftAmount);
			}
		}
	}


}