package com.boxiang.share.product.ccic.service;

import com.boxiang.share.product.ccic.po.CcicInsure;

import java.util.List;
import com.boxiang.framework.base.page.Page;

public interface CcicInsureService {

	List<CcicInsure> selectList(CcicInsure ccicInsure);
	
	Page<CcicInsure> queryListPage(Page<CcicInsure> page);
	
	CcicInsure queryById(Integer id);
	
	void add(CcicInsure ccicInsure);

	void batchAdd(List<CcicInsure> ccicInsureList);
	
	void delete(Integer id);
	
	void update(CcicInsure ccicInsure);
	
	void batchUpdate(List<CcicInsure> ccicInsureList);

	int deleteByInsuranceApplicantNo(String insuranceApplicantNo);
}