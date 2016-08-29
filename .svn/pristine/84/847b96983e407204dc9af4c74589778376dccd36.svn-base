package com.boxiang.share.system.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.share.system.dao.SequenceDao;
import com.boxiang.share.system.po.Sequence;
import com.boxiang.share.system.service.SequenceService;
import com.boxiang.share.utils.DateUtil;

@DataSource(DataSourceEnum.MASTER)
@Service("sequenceService")
public class SequenceServiceImpl implements SequenceService {
	@Resource(name="sequenceDao")
	private SequenceDao sequenceDao;

	private Lock lock = new ReentrantLock();
	/**
	 * 调用mysql函数获取16位ID
	 * 
	 * @param tableName
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public Sequence getNextval(String tableName) {
		try {
			lock.lock();
			Map<String, String> map = new HashMap<String, String>();
			map.put("ids", "");
			map.put("tableName", tableName);
			sequenceDao.getNextval(map);
			return new Sequence(DateUtil.getCurrDate("yyyyMMdd")+StringUtils.leftPad(map.get("ids"), 8,"0"));
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 调用mysql函数获取16位ID
	 * 如果是0，加1后返回
	 * 
	 * @param tableName
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public Sequence getNextvalandins(String tableName) {
		try {
			lock.lock();
			Map<String, String> map = new HashMap<String, String>();
			map.put("ids", "");
			map.put("tableName", tableName);
			sequenceDao.getNextvalandins(map);
			return new Sequence(DateUtil.getCurrDate("yyyyMMdd")+StringUtils.leftPad(map.get("ids"), 8,"0"));
		} finally {
			lock.unlock();
		}
	}
}
