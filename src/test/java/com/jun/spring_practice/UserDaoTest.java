package com.jun.spring_practice;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.jun.spring_practice.dao.UserDao;
import com.jun.spring_practice.entity.User;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class UserDaoTest {

	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);
		User user = new User("whiteship", "jun", "married");

		dao.add(user);

		User user2 = dao.get("whiteship");
		
		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
	}
}
