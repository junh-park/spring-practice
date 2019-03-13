package com.jun.spring_practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jun.spring_practice.connectionmaker.MConnectionMaker;
import com.jun.spring_practice.connectionmaker.OConnectionMaker;
import com.jun.spring_practice.dao.AccountDao;
import com.jun.spring_practice.dao.MessageDao;
import com.jun.spring_practice.dao.UserDao;

@Configuration
public class DaoFactory {

	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setConnectionMaker(connectionMaker());
		return userDao;
	}
	
	public MessageDao messageDao() {
		return new MessageDao(connectionMaker());
	}
	
	public AccountDao accountDao() {
		return new AccountDao(connectionMaker());
	}

	@Bean
	public MConnectionMaker connectionMaker() {
		return new MConnectionMaker();
	}
	

}
