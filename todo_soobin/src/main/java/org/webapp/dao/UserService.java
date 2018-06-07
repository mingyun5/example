package org.webapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.study.sec.PasswordAuthentication;


public class UserService {
	private static final String URL = "jdbc:mysql://192.168.0.203/test_db?useSSL=no&characterEncoding=utf8";
	private static final String USER = "mingyun";
	private static final String PW = "alsrbs3193!";
	
	private static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PW);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public boolean saveUser(User user) {
		int status = 0;
		try {
			Connection conn = getConnection();
			PasswordAuthentication passAuth = new PasswordAuthentication();
			String sql = "insert into todo_user (id, password, name, gender) values (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getId());
			ps.setString(2, passAuth.hash(user.getPassword().toCharArray()));
			ps.setString(3, user.getName());
			ps.setString(4, user.getGender());
			
			status = ps.executeUpdate();
			conn.close();
			ps.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return status !=0 ? true:false; 
	}
	
	public boolean isOverLap(String id) {
		try {
			Connection conn = getConnection();
			String sql = "select id from todo_user where id="+"\'"+id+"\'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if (rs.getString(1).equals(id)) 
					return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isValidUser(String id, String password) {
		try {
			Connection conn = getConnection();
			PasswordAuthentication passAuth = new PasswordAuthentication();
			String sql = "select id, password from todo_user where id="+"\'"+id+"\'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				if (rs.getString(1).equals(id) && passAuth.authenticate(password.toCharArray(), rs.getString(2)))
					return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String getName(String id) {
		try {
			Connection conn = getConnection();
			String sql = "select name from todo_user where id="+"\'"+id+"\'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				return rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
