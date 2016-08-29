package com.boxiang.share.system.service;

import java.util.List;

import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.po.Dictionary;

public interface DictionaryService {

	List<Dictionary> selectList(Dictionary tDictionary);
	
	Page<Dictionary> queryListPage(Page<Dictionary> page);
	
	Dictionary queryById(java.lang.Integer id);
	
	void add(Dictionary tDictionary);

	void delete(java.lang.Integer id);
	
	void update(Dictionary tDictionary);
	
	void batchUpdate(List<Dictionary> tDictionaryList);

	public List<Dictionary> getDictListByDictCode(String dictCode);

	
}