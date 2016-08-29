package com.boxiang.share.product.comment.service;

import com.boxiang.share.product.comment.po.Comment;

import java.util.List;
import java.util.Map;

import com.boxiang.framework.base.page.Page;
import org.springframework.dao.DuplicateKeyException;

public interface CommentService {

	Map<String, Object> paramsFilter(Comment comment);

	List<Comment> selectList(Comment comment);
	
	Page<Comment> queryListPage(Page<Comment> page);

	List<Object> queryListPageV2(Object page);

	Comment queryById(String id);
	
	void add(Comment comment) throws DuplicateKeyException;

	void delete(String id);
	
	void update(Comment comment);
	
	void batchUpdate(List<Comment> commentList);
}