package com.boxiang.share.product.qrcode.service;


import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.product.qrcode.po.CarlovQrcode;

public interface CarlovQrcodeService {

	List<CarlovQrcode> selectList(CarlovQrcode carlovQrcode);
	
	Page<CarlovQrcode> queryListPage(Page<CarlovQrcode> page);
	
	CarlovQrcode queryById(String id);
	
	void add(CarlovQrcode carlovQrcode);

	void delete(String id);
	
	void update(CarlovQrcode carlovQrcode);
	
	void batchUpdate(List<CarlovQrcode> cavarlovQrcodeList);

	Page<CarlovQrcode> queryListPageForQrcode(Page<CarlovQrcode> page);

	List<CarlovQrcode> selectDayData(Map<String,Object> param);
}