package com.yus.wenda_beta_2.async.handles;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yus.wenda_beta_2.async.EventHandler;
import com.yus.wenda_beta_2.async.EventModel;
import com.yus.wenda_beta_2.async.EventType;
import com.yus.wenda_beta_2.pojo.Message;
import com.yus.wenda_beta_2.pojo.User;
import com.yus.wenda_beta_2.service.MessageService;
import com.yus.wenda_beta_2.service.UserService;

@Component
public class LikeHandler implements EventHandler{

	
	@Autowired
    MessageService messageService;

    @Autowired
    UserService userService;
	@Override
	public void doHandle(EventModel model) {
		// TODO Auto-generated method stub
		Message message=new Message();
		message.setFromId(model.getActorId());
		message.setToId(model.getEntityOwnerId());
		message.setCreatedDate(new Date());
		message.setHasRead(0);
		User user = userService.getUserById(model.getActorId());
		
		message.setContent("用户"+user.getName()+
		"赞了你的评论！"+"针对如下问题：" +model.getExt("question"));
		messageService.addMessage(message);
	}

	@Override
	public List<EventType> getSupportEventTypes() {
		// TODO Auto-generated method stub
		return Arrays.asList(EventType.LIKE);
	}

}
