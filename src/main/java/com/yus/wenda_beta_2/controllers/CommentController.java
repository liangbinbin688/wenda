package com.yus.wenda_beta_2.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yus.wenda_beta_2.pojo.Comment;
import com.yus.wenda_beta_2.pojo.EntityType;
import com.yus.wenda_beta_2.pojo.HostHolder;
import com.yus.wenda_beta_2.service.CommentService;
import com.yus.wenda_beta_2.service.QuestionService;

@Controller
public class CommentController {
	@Autowired
	HostHolder hostholder;

	@Autowired
	CommentService CommentService;
	
	@Autowired
	QuestionService QuestionService;
	
	@RequestMapping("/comment/add" )
	public String addComment(@RequestParam("questionId") int questionId,
							@RequestParam("content")   String content,
							Model model
			){
		Comment comment=new Comment();
		comment.setContent(content);
		comment.setEntityId(questionId);
		comment.setEntityType(EntityType.ENTITY_QUESTION);
		comment.setStatus(0);
		comment.setCreatedDate(new Date());
		if(hostholder.getUser()!=null){
			comment.setUserId(hostholder.getUser().getId());
		}
		else{
			comment.setUserId(1);
		}
		CommentService.addComment(comment);
		
		int count = CommentService.getCommentCount(comment.getEntityId(), comment.getEntityType());
		
		QuestionService.updateCommentCount(questionId, count);
		return "redirect:/question/query?id="+String.valueOf(questionId);
	}

}
