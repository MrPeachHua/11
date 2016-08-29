package com.boxiang.share.product.order.service;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.order.po.Invoice;

public interface InvoiceService {

	List<Invoice> selectList(Invoice tInvoice);
	
	Page<Invoice> queryListPage(Page<Invoice> page);
	
	Invoice queryById(java.lang.Integer id);
	
	void add(Invoice tInvoice);

	void delete(java.lang.Integer id);
	
	void update(Invoice tInvoice);
	
	void batchUpdate(List<Invoice> tInvoiceList);
	
	String postInvoiceInfo(Map<String,Object> param);
	
	String getLatestInvoiceInfo(String customerId,String parkingId);
}