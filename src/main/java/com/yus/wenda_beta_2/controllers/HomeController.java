package com.yus.wenda_beta_2.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yus.wenda_beta_2.WendaBeta2Application;
import com.yus.wenda_beta_2.pojo.Question;
import com.yus.wenda_beta_2.pojo.User;
import com.yus.wenda_beta_2.pojo.ViewObject;
import com.yus.wenda_beta_2.service.QuestionService;
import com.yus.wenda_beta_2.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	QuestionService questionserver;
	@Autowired
	UserService userserver;
	
	@RequestMapping(path={"/","index"},method={RequestMethod.GET})
	public String index(Model model,HttpSession httpSession){
		
		List<ViewObject> vo=new ArrayList<ViewObject>();
		List<Question> allQuestion = questionserver.selectAllQuestionLimit(10);
		for(Question question:allQuestion){
			User user = userserver.getUserById(question.getUserId());
			
			ViewObject view=new ViewObject();
			view.set("question",question );
			view.set("user", user);
			vo.add(view);
			
		}
		model.addAttribute("vo", vo);
		WendaBeta2Application.logger.info(vo.toString());
		WendaBeta2Application.logger.info(String.format("长度为%d", vo.size()));
		
		return "index";
		
	}
}
