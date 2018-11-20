package com.yus.wenda_beta_2.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yus.wenda_beta_2.WendaBeta2Application;
import com.yus.wenda_beta_2.pojo.Comment;
import com.yus.wenda_beta_2.pojo.EntityType;
import com.yus.wenda_beta_2.pojo.HostHolder;
import com.yus.wenda_beta_2.pojo.Question;
import com.yus.wenda_beta_2.pojo.User;
import com.yus.wenda_beta_2.pojo.ViewObject;
import com.yus.wenda_beta_2.service.CommentService;
import com.yus.wenda_beta_2.service.QuestionService;
import com.yus.wenda_beta_2.service.UserService;
import com.yus.wenda_beta_2.service.serviceImpl.LikeService;
import com.yus.wenda_beta_2.utils.WendaUtil;

@Controller
public class QuestionController {
	
	
	@Autowired
	HostHolder HostHolder;
	@Autowired
	QuestionService QuestionService;
	
	@Autowired
	CommentService CommentService;
	
	@Autowired
	UserService UserService;
	
	@Autowired
	LikeService likeService;
	
	@RequestMapping(value = "/question/add", method = {RequestMethod.POST})
    @ResponseBody
	public String addquestion(@RequestParam("title") String title, @RequestParam("content") String content){
		
		try{
			Question question=new Question();
			question.setTitle(title);
			question.setContent(content);
			question.setUserId(HostHolder.getUser().getId());
			question.setCreatedDate(new Date());
			question.setCommentCount(0);
			
			int addQuestion = QuestionService.addQuestion(question);
			if(addQuestion>0){
				return WendaUtil.getJSONString(0);
			}
		}catch (Exception e) {
			// TODO: handle exception
			//记录错误
		}
		
		
		return WendaUtil.getJSONString(1, "失败");
		
	}
	
	@RequestMapping("/question/query")
	public String queryQuestion(@RequestParam("id") Integer id,Model model){
		
		Question selectQuestionById = QuestionService.selectQuestionById(id);
		//System.out.println(selectQuestionById.getTitle());
		//WendaBeta2Application.logger.info(selectQuestionById.getTitle());
		model.addAttribute("question", selectQuestionById);
		List<Comment> comments = CommentService.getCommentsByEntity(id, EntityType.ENTITY_QUESTION);
		List<ViewObject> vos=new ArrayList<ViewObject>();
		for(Comment comment:comments){
			ViewObject ov=new ViewObject();
			ov.set("comment", comment);
			ov.set("user", UserService.getUserById(comment.getUserId()));
			if (HostHolder.getUser() == null) {
                ov.set("liked", 0);
            } else {
                ov.set("liked", likeService.getLikeStatus(HostHolder.getUser().getId(), EntityType.ENTITY_COMMENT, comment.getId()));
            }
		    ov.set("likeCount", likeService.getLikeCount(EntityType.ENTITY_COMMENT, comment.getId()));
			vos.add(ov);
		}
		model.addAttribute("vos", vos);
		
		return "detail";
		
	}

}
