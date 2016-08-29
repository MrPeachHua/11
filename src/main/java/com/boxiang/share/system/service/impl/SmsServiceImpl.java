package com.boxiang.share.system.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.b2m.eucp.client.SingletonClient;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.dao.ParkingTicketDao;
import com.boxiang.share.system.dao.SmsDao;
import com.boxiang.share.system.po.Sms;
import com.boxiang.share.system.service.SmsService;

@DataSource(DataSourceEnum.MASTER)
@Service("sms")
public class SmsServiceImpl implements SmsService {
	private static final Logger logger = Logger.getLogger(SmsServiceImpl.class);
	@Resource(name="smsDao")
	private SmsDao smsDao;
	
	@Resource(name="parkingTicketDao")
	private ParkingTicketDao parkingTicketDao;
	
	@Override
	public List<Sms> selectList(Sms sms) {
		return smsDao.selectList(sms);
	}

	@Override
	public Page<Sms> queryListPage(Page<Sms> page) {
	    page.setResultList(smsDao.queryListPage(page));
		return page;
	}
	
	@Override
	public Sms queryById(java.lang.String id) {
		return smsDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(Sms sms) {
		smsDao.insert(sms);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.String id) {
		smsDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(Sms sms) {
		smsDao.update(sms);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Sms> smsList) {
		smsDao.batchUpdate(smsList);
	}
  
  /**
   * 普通&月租短信推送
   * parkingScope 0:全部 1:固定车场
   * userScope 0:全部 1：月租用户  2：产权用户
   * parkingId 停车场Id 
   */
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public String pushNomalMessage(Map<String,Object> param) {
	  	String parkingScope =  (String) param.get("parkingScope");
	  	String userScope =  (String) param.get("userScope");
	  	String msgContent =  (String) param.get("msgContent");
	  	String numbers =  (String) param.get("numbers");
	  	String parkingId =  (String) param.get("parkingId");
	  	String scheme = (String) param.get("scheme");
	  	String mbs[] = null;
	  	String flag = "0";
	  	Map<String,Object> mParam = new HashMap<String,Object>();
	  	//固定车场加入车场条件
	  	/*if("1".equals(parkingScope)){
	  		mParam.put("parkingId", parkingId);	  		
	  	}*/
	  	//方案二 
	  	/*if("2".equals(scheme)){
	  		mbs = numbers.split("\\,");
	  	}else{//方案一
	  		//月租用户
	  		if("1".equals(userScope)){	  			
	  			List<Sms> mList = smsDao.selectMonthMobile(mParam);
	  			if(null!=mList&&mList.size()>0){
	  				mbs = this.getMobiles(mList);
	  			}
	  		}else if("2".equals(userScope)){//产权用户
	  			List<Sms> pList = smsDao.selectPropertyMobile(mParam);
	  			if(null!=pList&&pList.size()>0){
	  				mbs = this.getMobiles(pList);
	  			}
	  		}else{//全部
	  			List<Sms> list = new ArrayList<Sms>();
 	  			List<Sms> mList = smsDao.selectMonthMobile(mParam);
	  			List<Sms> pList = smsDao.selectPropertyMobile(mParam);
	  			if(mList!=null&&mList.size()>0){
	  				list.addAll(mList);
	  			}
	  			if(pList!=null&&pList.size()>0){
	  				list.addAll(pList);
	  			}
	  			if(null!=list&&list.size()>0){
	  				mbs = this.getMobiles(list);
	  			}
	  		}
	  	}*/
	  	mbs = numbers.split("\\,");
	  	if(mbs!=null){
	  		int reCode = 1;
			try {
				reCode = SingletonClient.getInstance().sendMessage(mbs, msgContent, 5);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("------------信息发送异常");
				flag = "1";
			}
			
			if(reCode!=0){
				logger.info("---------信息发送异常");
				flag = "1";
				
			}
	  	}
		return flag;
	 
  }
  //返回数组
  public String[] getMobiles(List<Sms> list){
	  Set<String> set=new HashSet<String>();
	  for(Sms s :list){
		  if(!StringUtils.isEmpty(s.getMobile())){
			  set.add(s.getMobile());
		  }
	  }
	  String[] arr = new String[set.size()];
	  String [] str = (String[]) set.toArray(arr);
	  return str;
  }
}