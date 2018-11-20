package com.yus.wenda_beta_2.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yus.wenda_beta_2.pojo.Comment;
import com.yus.wenda_beta_2.pojo.Message;

public interface MessageService {
	
	public int addMessage(Message message);
	
	List<Message> getConversationList( int userId,
             int offset,  int limit);
	
	int getConvesationUnreadCount( int userId, String conversationId);
	
	 List<Message> getConversationDetail( String conversationId,
	             int offset,  int limit);

	 
	 int updateByHasRead( Integer id, Integer hasRead);
	 
	 
}
