package com.yus.wenda_beta_2.controllers;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yus.wenda_beta_2.WendaBeta2Application;
import com.yus.wenda_beta_2.service.UserService;

@Controller
public class LoginController {
	
	
	@Autowired
	UserService userservice;
	
//	调出登录页面
	@RequestMapping(path={"/reglogin"},method={RequestMethod.GET})
	public String regloginpage(Model model){
		
		model.addAttribute("msg", "请先注册或登录!");
		return "login";
	}
//注册页面
	@RequestMapping(path={"/reg/"},method={RequestMethod.POST})
	public String reg(Model model,
					@RequestParam("username") String username,
					@RequestParam("password") String password,
		
					@RequestParam(value="rememberme",defaultValue="false") boolean rememberme,
					HttpServletResponse response
			){
		try {
			Map<String ,Object> map=userservice.register(username, password);
			
			
			if(map.containsKey("ticket")){
				Cookie cookie = new Cookie("ticket",map.get("ticket").toString());
				cookie.setPath("/");
				if(rememberme){
					cookie.setMaxAge(3600*24*5);
					
				}
				response.addCookie(cookie);	
				/*if(!StringUtils.isEmpty(next)){
					return "redirect:"+next;
				}*/
				return "redirect:/";
			}
			else{
				model.addAttribute("msg", map.get("msg"));
				return "login";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			WendaBeta2Application.logger.info("注册失败！"+e.getMessage());
			model.addAttribute("msg"+"服务器错误！");
			return "login";
		}
	}
	
//	登录
	@RequestMapping(path={"/login/"},method={RequestMethod.POST})
	public String login(Model model,
					@RequestParam("username") String username,
					@RequestParam("password") String password,
					
					@RequestParam(value="rememberme",defaultValue="false") boolean rememberme,
					HttpServletResponse response
			){
		try {
			//WendaBeta2Application.logger.info(username);
           Map<String ,Object> map=userservice.login(username, password);
			
			if(map.containsKey("ticket")){
				Cookie cookie = new Cookie("ticket",map.get("ticket").toString());
				cookie.setPath("/");
				if(rememberme){
					cookie.setMaxAge(3600*24*5);
					
				}
				response.addCookie(cookie);	
				/*if(!StringUtils.isEmpty(next)){
					return "redirect:"+next;
				}*/
				return "redirect:/";
			}
			else{
				model.addAttribute("msg", map.get("msg"));
				return "login";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			WendaBeta2Application.logger.info("登录异常"+e.getMessage());
			return "login";
		}
	}
	
	@RequestMapping(path={"/logout"})
	public String logout(@CookieValue("ticket") String ticket){
		userservice.logout(ticket);
		return "redirect:/";
		
	}

}
