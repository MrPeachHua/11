package com.boxiang.share.product.comment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.boxiang.share.utils.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boxiang.share.product.comment.dao.CommentDao;
import com.boxiang.share.product.comment.po.Comment;
import com.boxiang.share.product.comment.service.CommentService;
import com.boxiang.framework.base.datasource.DataSource;
import com.boxiang.framework.base.datasource.DataSourceEnum;
import com.boxiang.framework.base.page.Page;

@DataSource(DataSourceEnum.MASTER)
@Service("comment")
public class CommentServiceImpl implements CommentService {
	@Resource(name="commentDao")
	private CommentDao commentDao;

	@Override
	public Map<String, Object> paramsFilter(Comment comment) {
		Map<String, Object> map = new HashMap<>(7);
		map.put("orderId", comment.getOrderId());
		map.put("commentContent", StringUtils.defaultString(comment.getCommentContent()));
		map.put("totalityStar", comment.getTotalityStar() == null ? 0 : comment.getTotalityStar());
		map.put("carManagerStar", comment.getCarManagerStar() == null ? 0 : comment.getCarManagerStar());
		map.put("businessStar", comment.getBusinessStar() == null ? 0 : comment.getBusinessStar());
		map.put("totalityTag", StringUtils.defaultString(comment.getTotalityTag()));
		map.put("carManagerTag", StringUtils.defaultString(comment.getCarManagerTag()));
		map.put("businessTag", StringUtils.defaultString(comment.getBusinessTag()));
		map.put("createDate", comment.getCreateDate() == null ? "" : DateUtil.date2str(comment.getCreateDate(), DateUtil.DATETIME_FORMAT));
		return map;
	}

	@Override
	public List<Comment> selectList(Comment comment) {
		return commentDao.selectList(comment);
	}

	@Override
	public Page<Comment> queryListPage(Page<Comment> page) {
	    page.setResultList(commentDao.queryListPage(page));
		return page;
	}

	@Override
	public List<Object> queryListPageV2(Object page) {
		return commentDao.queryListPageV2(page);
	}
	
	@Override
	public Comment queryById(String id) {
		return commentDao.queryById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void add(Comment comment) throws DuplicateKeyException {
		commentDao.insert(comment);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void delete(String id) {
		commentDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void update(Comment comment) {
		commentDao.update(comment);
	}

  @Override
  @Transactional(propagation=Propagation.REQUIRED,rollbackFor={RuntimeException.class, Exception.class})
	public void batchUpdate(List<Comment> commentList) {
		commentDao.batchUpdate(commentList);
	}
}