package com.yus.wenda_beta_2.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.yus.wenda_beta_2.mapper.CommentMapper;
import com.yus.wenda_beta_2.pojo.Comment;
import com.yus.wenda_beta_2.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentMapper CommentMapper;
	@Autowired
	 SensitiveService sensitiveService;

	@Override
	public List<Comment> getCommentsByEntity(int entityId, int entityType) {
		// TODO Auto-generated method stub
		List<Comment> selectByEntity = CommentMapper.selectByEntity(entityId, entityType);
		return selectByEntity;
	}

	@Override
	public int addComment(Comment comment) {
		// TODO Auto-generated method stub
		comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
		//comment.setContent();
		comment.setContent(sensitiveService.filter(comment.getContent()));
		
		int insert = CommentMapper.insert(comment);
		return insert;
	}

	@Override
	public int getCommentCount(int entityId, int entityType) {
		// TODO Auto-generated method stub
		int commentCount = CommentMapper.getCommentCount(entityId, entityType);
		return commentCount;
	}

	@Override
	public void deleteComment(int entityId, int entityType) {
		// TODO Auto-generated method stub
		CommentMapper.updateStatus(entityId, entityType, 1);
	}

	@Override
	public Comment getCommentById(Integer id) {
		// TODO Auto-generated method stub
		return CommentMapper.selectByPrimaryKey(id);
	}
}
