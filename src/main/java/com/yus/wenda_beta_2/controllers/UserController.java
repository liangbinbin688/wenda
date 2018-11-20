package com.yus.wenda_beta_2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yus.wenda_beta_2.WendaBeta2Application;

@Controller
public class UserController {
	
	@RequestMapping("/user/userinfo")
	@ResponseBody
	public String userinfo(@RequestParam(value="userid" ,required=false) Integer userid){
		
		WendaBeta2Application.logger.info(String.format("用户id为%d", userid));
		
		return "hello"+String.format("用户id为%d", userid);
		
	}
	

}
