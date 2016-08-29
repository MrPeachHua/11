package com.boxiang.share.product.order.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.order.dao.InvoiceDao;
import com.boxiang.share.product.order.po.Invoice;
import com.boxiang.share.product.order.service.InvoiceService;
import com.boxiang.share.product.parking.dao.ParkingDao;
import com.boxiang.share.product.parking.po.Parking;
import com.boxiang.share.system.po.Sequence;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.utils.ShangAnMessageType;
import com.boxiang.share.utils.TableNameConstants;

@DataSource(DataSourceEnum.MASTER)
@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {
	private static final Logger logger = Logger.getLogger(InvoiceServiceImpl.class);
	@Resource(name="invoiceDao")
	private InvoiceDao invoiceDao;
	
	@Resource(name="parkingDao")
	private ParkingDao parkingDao;
	
	@Resource
	private SequenceService sequenceService;
	@Override
	public List<Invoice> selectList(Invoice tInvoice) {
		return invoiceDao.selectList(tInvoice);
	}

	@Override
	public Page<Invoice> queryListPage(Page<Invoice> page) {
	    page.setResultList(invoiceDao.queryListPage(page));
		return page;
	}
	
	@Override
	public Invoice queryById(java.lang.Integer id) {
		return invoiceDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(Invoice tInvoice) {
		invoiceDao.insert(tInvoice);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		invoiceDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(Invoice tInvoice) {
		invoiceDao.update(tInvoice);
	}

    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Invoice> tInvoiceList) {
		invoiceDao.batchUpdate(tInvoiceList);
	}
   /**
    * 提交发票
    * @param Map
    * @author hua
    * @return String
    */
    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
  	public String postInvoiceInfo(Map<String,Object> param){
	  String invoiceId = null;
	  String customerId = (String) param.get("customerId");
	  String invoiceName = (String) param.get("invoiceName");
	  String sendType = (String) param.get("sendType");
	  String sendAddress = (String) param.get("sendAddress");
	  String carNumber = (String) param.get("carNumber");
	  //1、查询有无相同数据的 有:修改is_default 为1
	  Invoice iv = new Invoice();
	  iv.setIsUsed("1");
	  iv.setCustomerId(customerId);
	  iv.setInvoiceName(invoiceName);
	  iv.setSendType(sendType);
	  iv.setSendAddress(sendAddress);
	  iv.setCarNumber(carNumber);	  
	  try {
		  List<Invoice> iList = invoiceDao.selectList(iv);
		  //找出所有is_default=1的设为0
		  Invoice iparam = new Invoice();
		  iparam.setCustomerId(customerId);
		  iparam.setIsDefault("1");
		  List<Invoice> deList = invoiceDao.selectList(iparam);
		  if(null!=deList&&deList.size()>0){
			  for(Invoice inv:deList){
				  inv.setIsDefault("0");
			  }
			  invoiceDao.batchUpdate(deList);
		  }	 
		  //2、无匹配数据添加数据
		  if(null==iList || iList.size()==0){
			  Sequence sequence = sequenceService.getNextvalandins(TableNameConstants.T_INVOICE);
			  iv.setId(sequence.getId());
			  iv.setCreateor("admin");
			  iv.setCreateDate(new Date());
			  iv.setIsDefault("1");
			  invoiceDao.insert(iv);	  
			  invoiceId = sequence.getId();
			  logger.info("----------------------"+invoiceId);
		  }else{
			  //有匹配数据			 
			  //将匹配数据的is_default 设为1
			  iList.get(0).setIsDefault("1");
			  iList.get(0).setModifyDate(new Date());
			  invoiceDao.update(iList.get(0));
			  invoiceId = iList.get(0).getId();
			  logger.info("----------------------"+invoiceId);
		  }
		} catch (Exception e) {
			logger.error("数据库异常",e);
			return ShangAnMessageType.operateToJson("2", "提交发票数据库异常");
		}
	  
	 return ShangAnMessageType.toShangAnJson("0", "提交成功", "invoiceId", invoiceId);
    }
    /**
     * 获取发票
     * @author hua
     * @return String
     */
    @Override
  	public String getLatestInvoiceInfo(String customerId,String parkingId){
    	Map<String,Object> mp = new HashMap<String,Object>();
    	//1、查是否有默认的 如果有默认的取默认的
    	Invoice inParam = new Invoice();
    	inParam.setCustomerId(customerId);
    	inParam.setIsDefault("1");
    	List<Invoice> ivList = invoiceDao.selectList(inParam);
    	//查询发票说明
    	String invoiceDescribe = null;
    	Parking parking = new Parking();
    	parking.setParkingId(parkingId);
    	List<Parking> pList = parkingDao.selectList(parking);
    	if(null!=pList&&pList.size()>0){
    		invoiceDescribe = pList.get(0).getInvoiceDescribe(); 
    	}
    	if(null!=ivList&&ivList.size()>0){
    		mp.put("invoiceName", ivList.get(0).getInvoiceName()==null?"":ivList.get(0).getInvoiceName());
    		mp.put("sendType", ivList.get(0).getSendType()==null?"":ivList.get(0).getSendType());
    		mp.put("sendAddress", ivList.get(0).getSendAddress()==null?"":ivList.get(0).getSendAddress());
    		mp.put("invoiceDescribe", invoiceDescribe==null?"":invoiceDescribe);
    		return ShangAnMessageType.toShangAnJson("0", "查询成功", "invoice", mp);
    	}else{
    		Invoice invoParam = new Invoice();
    		invoParam.setCustomerId(customerId);
    		invoParam.setIsDefault("0");
    		List<Invoice> daList = invoiceDao.selectList(invoParam);
    		if(null==daList || daList.size()==0){
    			mp.put("invoiceName", "");
        		mp.put("sendType", "");
        		mp.put("sendAddress", "");
    			mp.put("invoiceDescribe", invoiceDescribe==null?"":invoiceDescribe);
    			return ShangAnMessageType.toShangAnJson("0", "查询成功", "invoice", mp); 
    		}else{
    			mp.put("invoiceName", daList.get(0).getInvoiceName());
        		mp.put("sendType", daList.get(0).getSendType());
        		mp.put("sendAddress", daList.get(0).getSendAddress());
        		mp.put("invoiceDescribe", invoiceDescribe==null?"":invoiceDescribe);
    		}
    		return ShangAnMessageType.toShangAnJson("0", "查询成功", "invoice", mp);
    	}
    }
}