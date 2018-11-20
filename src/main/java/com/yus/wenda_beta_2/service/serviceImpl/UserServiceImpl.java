package com.yus.wenda_beta_2.service.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yus.wenda_beta_2.WendaBeta2Application;
import com.yus.wenda_beta_2.mapper.TicketMapper;
import com.yus.wenda_beta_2.mapper.UserMapper;
import com.yus.wenda_beta_2.pojo.Ticket;
import com.yus.wenda_beta_2.pojo.User;
import com.yus.wenda_beta_2.service.UserService;
import com.yus.wenda_beta_2.utils.WendaUtil;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserMapper usermapper;
	
	@Autowired
	TicketMapper ticketmapper;
	
	@Override
	public int addUser(User user) {
		
		return usermapper.insert(user);
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		User selectByPrimaryKey = usermapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

	@Override
	public Map<String, Object> register(String username, String password) {
		// TODO Auto-generated method stub
		Map<String ,Object> map=new HashMap<String, Object>();
		if(StringUtils.isEmpty(username)){
			map.put("msg", "用户名不能为空！");
			return map;
		}
		if(StringUtils.isEmpty(password)){
			map.put("msg", "密码不能为空！");
			return map;
		}
		User user = usermapper.selectUserByName(username);
			
		if(user!=null)	{
			map.put("msg", "用户名已注册！");
			return map;
		}
//	密码强度
		user=new User();
		user.setName(username);
		user.setSalt(UUID.randomUUID().toString().substring(0, 5));
		user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
		user.setPassword(WendaUtil.MD5(password+user.getSalt()));
		int insert = usermapper.insert(user);
			
//		注册之后直接登录
		String addLoginTicket = addLoginTicket(user.getId());
		map.put("ticket", addLoginTicket);
		return map;
	}

	@Override
	public Map<String, Object> login(String username, String password) {
		// TODO Auto-generated method stub
		User user = usermapper.selectUserByName(username);
		Map<String ,Object> map=new HashMap<String, Object>();
		if(StringUtils.isEmpty(username)){
			map.put("msg", "用户名不能为空！");
			return map;
		}
		if(StringUtils.isEmpty(password)){
			map.put("msg", "密码不能为空！");
			return map;
		}
		
		
		WendaBeta2Application.logger.info(username);
		WendaBeta2Application.logger.info(user.getPassword());
		WendaBeta2Application.logger.info(user.getSalt());
		WendaBeta2Application.logger.info(user.getName());
			
		if(user==null)	{
			map.put("msg", "用户名不存在！");
			return map;
		}
		if(!(WendaUtil.MD5(password+user.getSalt()).equals(user.getPassword()))){
			WendaBeta2Application.logger.info(WendaUtil.MD5(password+user.getSalt()));
			WendaBeta2Application.logger.info(WendaUtil.MD5(user.getPassword()));
			
			map.put("msg", "密码错误！");
			return map;
		}
		
		String addLoginTicket = addLoginTicket(user.getId());
		map.put("ticket", addLoginTicket);
		
		return map;
	}

	@Override
	public String addLoginTicket(int userId) {
		// TODO Auto-generated method stub
		Ticket ticket = new Ticket(); 
		ticket.setUserId(userId);
		ticket.setStatus(0);
		Date date=new Date();
		date.setTime(date.getTime()+1000*3600*24);
		ticket.setExpired(date);
		ticket.setTicket(UUID.randomUUID().toString().replace("-", ""));
		ticketmapper.insert(ticket);
		return ticket.getTicket();
	}

	@Override
	public void logout(String ticket) {
		// TODO Auto-generated method stub
		ticketmapper.updateByTicket(ticket, 1);
	}

	
	@Override
	public User selectByNmae(String name) {
		// TODO Auto-generated method stub
		User selectUserByName = usermapper.selectUserByName(name);
		return selectUserByName;
	}

	
	

}
