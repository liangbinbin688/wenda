package com.yus.wenda_beta_2.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yus.wenda_beta_2.interceptor.LoginInterceptor;
import com.yus.wenda_beta_2.interceptor.PassportInterceptor;

@SuppressWarnings("deprecation")
@Component
public class WendaWebConfiguration extends WebMvcConfigurerAdapter{
	@Autowired
	PassportInterceptor passportInterceptor;
	
	@Autowired
	LoginInterceptor logininterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		
		registry.addInterceptor(passportInterceptor);
		registry.addInterceptor(logininterceptor).addPathPatterns("/user/*","/comment/*","/message/*","/letter/*").excludePathPatterns("/reglogin");
		super.addInterceptors(registry);
	}
}
