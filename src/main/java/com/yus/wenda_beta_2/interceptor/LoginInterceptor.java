package com.yus.wenda_beta_2.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yus.wenda_beta_2.WendaBeta2Application;
import com.yus.wenda_beta_2.pojo.HostHolder;

@Component
public class LoginInterceptor implements HandlerInterceptor{

	@Autowired
	HostHolder hostholder;
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		if(hostholder.getUser()==null){
			
			HttpSession session = request.getSession();
			session.setAttribute("msg", "请先登录或注册");
			WendaBeta2Application.logger.info("请先登录！");
			//request.getRequestDispatcher("/wenda/reglogin").forward(request, response);
			response.sendRedirect("/wenda/reglogin");
			return false;
		}
		return true;
	}

}
