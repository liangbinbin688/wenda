package com.yus.wenda_beta_2.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


import com.yus.wenda_beta_2.WendaBeta2Application;

@Aspect
@Component
public class Myaspect {
	
	@Before("execution( * com.yus.wenda_beta_2.controllers.*.*(..))")
	public void before(){
		WendaBeta2Application.logger.info("访问前。。。");
	}
	
	@After("execution( * com.yus.wenda_beta_2.controllers.*.*(..))")
	public void post(){
		WendaBeta2Application.logger.info("访问后....");
	}


}
