package com.yus.wenda_beta_2.interceptor;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yus.wenda_beta_2.WendaBeta2Application;
import com.yus.wenda_beta_2.mapper.TicketMapper;
import com.yus.wenda_beta_2.pojo.HostHolder;
import com.yus.wenda_beta_2.pojo.Ticket;
import com.yus.wenda_beta_2.pojo.User;
import com.yus.wenda_beta_2.service.UserService;

/**
 * @author lbb
 *
 */
@Configuration
public class PassportInterceptor implements HandlerInterceptor{

	@Autowired
	TicketMapper ticketmapper;
	@Autowired
	UserService userservice;
	@Autowired
	HostHolder HostHolder;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HostHolder.clear();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		if(modelAndView!=null&&HostHolder.getUser()!=null){
			modelAndView.addObject("user", HostHolder.getUser());
			WendaBeta2Application.logger.info("放入试图渲染前,modelandview"+HostHolder.getUser().getName());
		}
	}

	
//	访问所有的页面，都要经过这里
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String ticket=null;
		if(request.getCookies()!=null){
			for(Cookie cookie:request.getCookies()){
				if(cookie.getName().equals("ticket")){
					ticket=cookie.getValue();
					break;
				}
			}
		}
		if(ticket!=null){
			Ticket selectByTicket = ticketmapper.selectByTicket(ticket);
			if(selectByTicket==null||selectByTicket.getExpired().before(new Date())||selectByTicket.getStatus()!=0){
				return true;
			}
			else{
				User user=userservice.getUserById(selectByTicket.getUserId());
				//WendaBeta2Application.logger.info("根据cookie获得了用户信息"+user.getName());
				HostHolder.setUser(user);
			}
			
		}
		return true;
	}
	

}
