package com.boxiang.share.product.customer.service.imp;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.customer.dao.RechargeRuleDao;

import com.boxiang.share.product.customer.po.RechargeRule;
import com.boxiang.share.product.customer.po.RechargeRuleGiftAmount;
import com.boxiang.share.product.customer.service.RechargeRuleService;

import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("rechargeRule")
public class RechargeRuleServiceImpl implements RechargeRuleService {
	@Resource(name="rechargeRuleDao")
	private RechargeRuleDao rechargeRuleDao;
	
	@Override
	public List<RechargeRule> selectList(RechargeRule rechargeRule) {
		return rechargeRuleDao.selectList(rechargeRule);
	}

	@Override
	public Page<RechargeRule> queryListPage(Page<RechargeRule> page) {
	    page.setResultList(rechargeRuleDao.queryListPage(page));
		return page;
	}
	
	@Override
	public RechargeRule queryById(Integer id) {
		return rechargeRuleDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(RechargeRule rechargeRule) {
		rechargeRuleDao.insert(rechargeRule);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(Integer id) {
		rechargeRuleDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(RechargeRule rechargeRule) {
		rechargeRuleDao.update(rechargeRule);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void updateRule(RechargeRule rechargeRule){
		rechargeRuleDao.updateRule(rechargeRule);
	}
	 @Override
	  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void deleteRule(RechargeRule rechargeRule){
		rechargeRuleDao.deleteRule(rechargeRule);
	}
  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<RechargeRule> rechargeRuleList) {
		rechargeRuleDao.batchUpdate(rechargeRuleList);
	}

//@Override
//public List<RechargeRule> queryRechargeRule() {
//	return rechargeRuleDao.selectRule();
//}

@Override
public String queryRechargeRule2(HttpServletRequest request) {
	 String mess=null;
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	 List<RechargeRuleGiftAmount> list=rechargeRuleDao.selectRule();
	 String message2[]=new String[list.size()+1];
	 String mess2=null;
	 if(null!=list&&list.size()>0){
		 for (int i=0;i<list.size();i++) {
			 RechargeRuleGiftAmount rechargeRuleGiftAmount=list.get(i);			
			  message2[i]="充"+rechargeRuleGiftAmount.getAmount()/100+"元送"+rechargeRuleGiftAmount.getGiftAmount()/100+"元";
			  mess2=basePath+rechargeRuleGiftAmount.getImagePath();
		}
		 message2[list.size()]=mess2;
		 mess=ShangAnMessageType.toShangAnJson("0", "查询成功", "rule", message2);
	 }else{
			mess = ShangAnMessageType.operateToJson("1", "无数据");
	 }
	 
	return mess;
	
}






}