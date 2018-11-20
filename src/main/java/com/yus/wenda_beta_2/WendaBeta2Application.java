 package com.yus.wenda_beta_2;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author lbb
 *
 */
@SpringBootApplication
@MapperScan("com.yus.wenda_beta_2.mapper")
public class WendaBeta2Application {
	
	public  static final Logger logger=LoggerFactory.getLogger(WendaBeta2Application.class);
	public static void main(String[] args) {
		SpringApplication.run(WendaBeta2Application.class, args);
		logger.info("service starting.....");
	}
	
	//添加分页操作
	
}
