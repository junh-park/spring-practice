package com.jun.spring_practice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.jun.spring_practice.daostrategy.StatementStrategy;
import com.jun.spring_practice.entity.User;

public class JdbcContext {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void executeUpdateSql(final String query) throws SQLException {
		workWithStatementStrategy(c -> c.prepareStatement(query));
	}
	
	public void executeUpdateSql(final String query, final User user) throws SQLException {
		workWithStatementStrategy(c -> {
			PreparedStatement ps = c.prepareStatement(query);
			
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());

			return ps; 
		});
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
