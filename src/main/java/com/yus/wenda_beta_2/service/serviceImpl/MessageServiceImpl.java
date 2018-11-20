package com.yus.wenda_beta_2.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yus.wenda_beta_2.mapper.MessageMapper;
import com.yus.wenda_beta_2.pojo.Message;
import com.yus.wenda_beta_2.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	MessageMapper  MessageMapper;
	@Override
	public int addMessage(Message message) {
		// TODO Auto-generated method stub
		return MessageMapper.insert(message);
	}
	@Override
	public List<Message> getConversationList(int userId, int offset, int limit) {
		// TODO Auto-generated method stub
		List<Message> conversationList = MessageMapper.getConversationList(userId, offset, limit);
		return conversationList;
	}
	@Override
	public int getConvesationUnreadCount(int userId, String conversationId) {
		// TODO Auto-generated method stub
		int convesationUnreadCount = MessageMapper.getConvesationUnreadCount(userId, conversationId);
		return convesationUnreadCount;
	}
	@Override
	public List<Message> getConversationDetail(String conversationId, int offset, int limit) {
		// TODO Auto-generated method stub
		List<Message> conversationDetail = MessageMapper.getConversationDetail(conversationId, offset, limit);
		return conversationDetail;
	}
	@Override
	public int updateByHasRead(Integer id, Integer hasRead) {
		// TODO Auto-generated method stub
		return MessageMapper.updateByHasRead(id, hasRead);
	}

}
