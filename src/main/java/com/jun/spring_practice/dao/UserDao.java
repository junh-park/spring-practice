package com.jun.spring_practice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.jun.spring_practice.entity.User;

public class UserDao {
	private DataSource dataSource;

	public UserDao() {
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(User user) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement("insert into users(id, name, password) values(?,?,?)");
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());

			ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
				}
			}
		}
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

	public void deleteAll() throws SQLException {
		Connection con = null;
		PreparedStatement stat = null;

		try {
			con = dataSource.getConnection();
			stat = con.prepareStatement("delete from users");
			stat.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {

			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
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
