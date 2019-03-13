package com.jun.spring_practice;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jun.spring_practice.dao.UserDao;
import com.jun.spring_practice.entity.User;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		User user = new User("whiteship", "jun", "married");
			
		dao.add(user);
		
		System.out.println(user.getId()+" added successfully");
		
		User user2 = dao.get("whiteship");
		
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
	}
}
