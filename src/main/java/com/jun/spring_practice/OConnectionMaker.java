package com.jun.spring_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "Trainee1", "!QAZSE4");
		return con;
	}

}
