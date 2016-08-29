package com.boxiang.share.system.dao;

import java.util.Map;

import com.boxiang.framework.mybatis.dao.IMybatisBaseDao;
import com.boxiang.share.system.po.Sequence;

public interface SequenceDao extends IMybatisBaseDao<Sequence> {
	
	/**
	 * 调用mysql函数获取16位ID
	 * @param id
	 * @return
	 */
	public Map<String,String> getNextval(Map<String,String> map);	
	
	/**
	 * 调用mysql函数获取16位ID
	 * 如果是0，加1后返回
	 * @param map
	 * @return
	 */
	public Map<String,String> getNextvalandins(Map<String,String> map);
	
}
