package com.yus.wenda_beta_2.service;

import java.util.List;

import com.yus.wenda_beta_2.pojo.Comment;

public interface CommentService {
	
	 public List<Comment> getCommentsByEntity(int entityId, int entityType);
	 
	 public int addComment(Comment comment);

	 public int getCommentCount(int entityId, int entityType);
	 
	 public void deleteComment(int entityId, int entityType);
	 
	 
	 Comment getCommentById(Integer id);
}
