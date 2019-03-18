package com.jun.spring_practice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.jun.spring_practice.daostrategy.StatementStrategy;

public class JdbcContext {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();

			ps = stmt.makePreparedStatement(con);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}
 