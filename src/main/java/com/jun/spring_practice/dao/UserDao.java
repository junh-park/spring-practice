package com.jun.spring_practice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.jun.spring_practice.daostrategy.StatementStrategy;
import com.jun.spring_practice.entity.User;

public class UserDao {
	private DataSource dataSource;
	private JdbcContext jdbcContext;
	
	public UserDao() {
	}

	public void setDataSource(DataSource dataSource) {
		this.jdbcContext = new JdbcContext();
		jdbcContext.setDataSource(dataSource);
		this.dataSource = dataSource;
	}

	public void add(final User user) throws SQLException {

		this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
			// class AddStatement implements StatementStrategy {
			// private User user;

			// public AddStatement(User user) {
			// this.user = user;
			// }

			@Override
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());

				return ps;
			}
			// }
		});
	}

	public void deleteAll() throws SQLException {
		this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
			@Override
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				return c.prepareStatement("delete from users");
			}
		});
	}

	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;

		try {
			con = dataSource.getConnection();

			ps = con.prepareStatement("select * from users where id = ?");
			ps.setString(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
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

		if (user == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return user;
	}

	public int getCount() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement("select count(*) from users");

			rs = ps.executeQuery();

			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
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
