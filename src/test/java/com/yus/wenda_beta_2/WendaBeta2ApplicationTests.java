package com.yus.wenda_beta_2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.yus.wenda_beta_2.async.EventType;
import com.yus.wenda_beta_2.mapper.TicketMapper;
import com.yus.wenda_beta_2.mapper.UserMapper;
import com.yus.wenda_beta_2.pojo.Comment;
import com.yus.wenda_beta_2.pojo.EntityType;
import  com.yus.wenda_beta_2.pojo.Question ;
import com.yus.wenda_beta_2.pojo.Ticket;
import com.yus.wenda_beta_2.pojo.User;
import com.yus.wenda_beta_2.pojo.ViewObject;
import com.yus.wenda_beta_2.service.CommentService;
import com.yus.wenda_beta_2.service.QuestionService;
import com.yus.wenda_beta_2.service.UserService;
import com.yus.wenda_beta_2.utils.JedisAdapter;

import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WendaBeta2ApplicationTests {
 
	@Autowired
	UserService userservice;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	UserMapper usermapper;
	@Autowired
	TicketMapper ticketmapper;
	@Autowired
	CommentService CommentService;
	@Autowired
	JedisAdapter JedisAdapter;
	
	@Test
	public void contextLoads() {
		/*Random random=new Random();
		for(int i=0;i<20;i++){
			User user=new User();
			user.setName(String.format("张三%d", i));
			user.setPassword(String.format("%d", random.nextInt(2000)));
			user.setSalt("123");
			user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
		
		   int addUser = userservice.addUser(user);
		   if(addUser>0){
			   System.out.println("添加成功！");
		   }
		   
		   Question question=new Question();
           question.setCommentCount(i);
           Date date = new Date();
           date.setTime(date.getTime() + 1000 * 3600 * 5 * i);
           question.setCreatedDate(date);
           question.setUserId(user.getId());
           question.setTitle(String.format("河北大街{%d}", i));
           question.setContent(String.format("秦皇岛燕山大学 Content %d", i));
           questionService.addQuestion(question);
		}*/
		//userservice.addUser(n)
		/*
		List<ViewObject> vo=new ArrayList<ViewObject>();
		List<Question> allQuestion = questionservice.getAllQuestion();
		for(Question question:allQuestion){
			User user = userservice.getUserById(question.getUserId());
			
			//System.out.print(user.getName()+"\t");
			ViewObject view=new ViewObject();
			view.set("question",question );
			view.set("user", user);
			vo.add(view);
			
		}
		
		//System.out.println();
		for(ViewObject view:vo){
			System.out.println(view.get("user"));
		}
		*/
	
		//WendaBeta2Application.logger.info(selectAllQuestionLimit.toString());
		/*List<Question> selectAllQuestionLimit = questionService.selectAllQuestionLimit(10);
		WendaBeta2Application.logger.info(String.format("长度为：%d", selectAllQuestionLimit.size()));
		for(Question question:selectAllQuestionLimit){
			System.out.print(question.getTitle());
		}*/
		
		/*User user = usermapper.selectUserByName("123");
		System.out.println(user.getName()+"\t"+user.getPassword()+"\t"+user.getSalt());*/
		/*Ticket selectByTicket = ticketmapper.selectByTicket("dc02b88c310f4c60973f2c5ff01016c3");
		WendaBeta2Application.logger.info(usermapper.selectByPrimaryKey(selectByTicket.getUserId()).getName());*/
		/*Comment comment=new Comment();
		comment.setContent("味道sfsdsdafjsd");
		comment.setCreatedDate(new Date());
		comment.setEntityId(4);
		comment.setEntityType(EntityType.ENTITY_QUESTION);
		comment.setStatus(0);
		comment.setUserId(3);
		CommentService.addComment(comment);	
		
		int commentCount = CommentService.getCommentCount(4, EntityType.ENTITY_QUESTION);
		
	    System.out.println(commentCount);
	    
	    List<Comment> commentsByEntity = CommentService.getCommentsByEntity(4, EntityType.ENTITY_QUESTION);
	    System.out.println(commentsByEntity.isEmpty());
	*/
		//questionService.updateCommentCount(5, 10);
		
	/*	Jedis jedis=new Jedis("127.0.0.1",6379);
		//jedis.configSet(parameter, value)
		System.out.println("链接成功！");
		jedis.set("lbb", "lllbb");
		System.out.println(jedis.get("lbb"));
		jedis.get("12");*//*
		JedisAdapter.set("lbbgbb", "shuijuejue");*/
		/*JedisAdapter.lpush("sss", "sssssss");*/
		/*EventType eventType;
		System.out.println(EventType.MAIL);
		System.out.println(EventType.);*/
		
		
		
	}
	/*@Test
	public void query(){
		User userById = userservice.getUserById(40);
		WendaBeta2Application.logger.info(userById.getHeadUrl());
	}*/
}
