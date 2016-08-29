package com.boxiang.share.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.boxiang.framework.base.Constants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;
import com.boxiang.share.system.dao.DictionaryDao;
import com.boxiang.share.system.po.Dictionary;
import com.boxiang.share.system.service.DictionaryService;

@DataSource(DataSourceEnum.MASTER)
@Service("dictionary")
public class DictionaryServiceImpl implements DictionaryService {
	@Resource(name="dictionaryDao")
	private DictionaryDao dictionaryDao;
	
	@Override
	public List<Dictionary> selectList(Dictionary tDictionary) {
		return dictionaryDao.selectList(tDictionary);
	}

	@Override
	public Page<Dictionary> queryListPage(Page<Dictionary> page) {
	    page.setResultList(dictionaryDao.queryListPage(page));
		return page;
	}
	
	@Override
	public Dictionary queryById(java.lang.Integer id) {
		return dictionaryDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(Dictionary tDictionary) {
		dictionaryDao.insert(tDictionary);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(java.lang.Integer id) {
		dictionaryDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(Dictionary tDictionary) {
		dictionaryDao.update(tDictionary);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Dictionary> tDictionaryList) {
		dictionaryDao.batchUpdate(tDictionaryList);
	}

	/**
	 * 根据字典代码获取字典数据列表
	 * @param dictCode 字典代码
	 * @return
	 */
	@Override
	public List<Dictionary> getDictListByDictCode(String dictCode) {
		Dictionary dictionary = new Dictionary();
		dictionary.setDictCode(dictCode);
		dictionary.setIsUsed(Constants.TRUE);
		List<Dictionary> dicList = this.selectList(dictionary);
		return dicList;
	}
}