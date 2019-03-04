package com.jun.spring_practice;

import java.sql.Connection;
import java.sql.SQLException;

public class DUserDao extends UserDao{

	@Override
	public Connection getconnection() throws ClassNotFoundException, SQLException {
		return null;
	}

}
