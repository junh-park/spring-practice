package com.jun.spring_practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

	@Bean
	public UserDao userDao() {
		return new UserDao(connectionMaker());
	}
	
	public MessageDao messageDao() {
		return new MessageDao(connectionMaker());
	}
	
	public AccountDao accountDao() {
		return new AccountDao(connectionMaker());
	}

	@Bean
	private DConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}

}
