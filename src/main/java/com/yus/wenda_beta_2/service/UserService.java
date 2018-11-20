package com.yus.wenda_beta_2.service;

import java.util.Map;

import com.yus.wenda_beta_2.pojo.User;

public interface UserService {
	
	public int addUser(User user);
	
	public  User getUserById(int id);
	
	public  Map<String,Object> register(String uesrname,String password);
	
	public Map<String,Object> login(String username,String password);
	
	public String addLoginTicket(int userId);
	
	public void logout(String ticket);
	
	public User selectByNmae(String name);
}
