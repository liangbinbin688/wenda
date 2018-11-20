package com.yus.wenda_beta_2.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.catalina.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yus.wenda_beta_2.WendaBeta2Application;
import com.yus.wenda_beta_2.pojo.HostHolder;
import com.yus.wenda_beta_2.pojo.Message;
import com.yus.wenda_beta_2.pojo.User;
import com.yus.wenda_beta_2.pojo.ViewObject;
import com.yus.wenda_beta_2.service.MessageService;
import com.yus.wenda_beta_2.service.UserService;
import com.yus.wenda_beta_2.utils.WendaUtil;

@Controller
public class MessageController {
	public  static final Logger logger=LoggerFactory.getLogger(MessageController.class);
	@Autowired
	HostHolder HostHolder;
	
	@Autowired
	UserService UserService;
	
	@Autowired
	MessageService MessageService;
	
	@RequestMapping("/message/page")
	public String messagePage(){
		return "Messagepage";
	}
	
	
	@RequestMapping("/message/add")
	@ResponseBody
	public String addMessage(@RequestParam("toName") String toName,
							@RequestParam("content") String content
			){
		try{
			User selectByNmae = UserService.selectByNmae(toName);
			User fromUser=HostHolder.getUser();
			if(selectByNmae==null){
				new Exception("发送人不存在！");
			}
			Message message=new Message();
			message.setContent(content);
			message.setCreatedDate(new Date());
			message.setHasRead(0);
			message.setToId(selectByNmae.getId());
			message.setFromId(fromUser.getId());
			Integer fromId = message.getFromId();
			Integer toId = message.getToId();
			message.setConversationId(fromId < toId ? String.format("%d_%d", fromId, toId) : String.format("%d_%d", toId, fromId));
			int addMessage = MessageService.addMessage(message);
			if(addMessage>0){
				return WendaUtil.getJSONString(0);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
			logger.info(e.getMessage());
		}
		return WendaUtil.getJSONString(1,"失败！");
	}
	
	@RequestMapping(path = {"/message/list"}, method = {RequestMethod.GET})
    public String conversationList(Model model) {
        try {
            int localUserId = HostHolder.getUser().getId();
            List<ViewObject> conversations = new ArrayList<ViewObject>();
            List<Message> conversationList = MessageService.getConversationList(localUserId, 0, 10);
            for (Message msg : conversationList) {
                ViewObject vo = new ViewObject();
                vo.set("conversation", msg);
                int targetId = msg.getFromId() == localUserId ? msg.getToId() : msg.getFromId();
                User user = UserService.getUserById(targetId);
                vo.set("user", user);
                vo.set("unread", MessageService.getConvesationUnreadCount(localUserId, msg.getConversationId()));
                conversations.add(vo);
            }
                model.addAttribute("conversations", conversations);
        } catch (Exception e) {
            logger.error("获取站内信列表失败" + e.getMessage());
        }
        return "letter";
    }
	
	@RequestMapping(path = {"/message/detail"}, method = {RequestMethod.GET})
    public String conversationDetail(Model model,
    		
    		@RequestParam("conversationId") String conversationId) {
        try {
        	List<Message> conversationDetails = MessageService.getConversationDetail(conversationId, 0, 10);
        	List<ViewObject> vos=new ArrayList<ViewObject>();
        	for(Message message:conversationDetails){
        		MessageService.updateByHasRead(message.getId(), 1);
        		ViewObject vo = new ViewObject();
        		vo.set("conversation",message);
        		vo.set("user", UserService.getUserById(message.getFromId()));
        		vos.add(vo);
        	}
        	model.addAttribute("vos", vos);
        	
              
        } catch (Exception e) {
            logger.error("获取站内信列表失败" + e.getMessage());
        }
        return "letterDetail";
    }

}
