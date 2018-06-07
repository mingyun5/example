package Todooo.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Todooo.sec.PasswordAuthentication;



public class EmployeeDaoImpl implements EmployeeService {

	private static final String URL = "jdbc:mysql://192.168.0.202/test_db?useSSL=no&characterEncoding=utf8";
	private static final String USER = "cheon000";
	private static final String PW = "cheon000";
	
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PW);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	@Override
	public boolean saveEmployee(Employee e) {
		int status = 0;
		try {
			PasswordAuthentication passAuth = new PasswordAuthentication();
			
			String sql = "insert into user (name, password, email, country) values (?, ?, ?, ?)";
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, e.getName());
			
			String token = passAuth.hash(e.getPassword().toCharArray());
			ps.setString(2, token);
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getCountry());
			
			status = ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return status == 1 ? true : false;
	}

	/**
	 * 데이터 베이스에 접근해서,
	 * 쿼리 결과를 리스트로 만들어서 리턴
	 * 
	 */
	@Override
	public List<Employee> listEmployee() {
		List<Employee> list = null;
		try {
			list = new ArrayList<Employee> ();
			String sql = "select id, name, email, country from user";
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Employee emp = new Employee();
				
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String country = rs.getString(4);
				
				emp.setId(id);
				emp.setName(name);
				emp.setEmail(email);
				emp.setCountry(country);
				
				list.add(emp);
			}
			
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public boolean deleteEmployee(int id) {
		int status = 0;
		try {
			String sql = "delete from user where id = ?";
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status == 1 ? true : false;
	}

	@Override
	public boolean updateEmployee(Employee e) {
		int status = 0;
		try {
			String sql = "update user set name=?, email=?, country=? where id= ?";
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, e.getName());
			ps.setString(2, e.getEmail());
			ps.setString(3, e.getCountry());
			ps.setInt(4, e.getId());
			
			status = ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return status == 1 ? true : false;
	}

	@Override
	public Employee listEmployee(int id) {
		Employee emp = null;
		try {
			String sql = "select name, password, email, country from user where id = ?";
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				emp = new Employee();
				
				String name = rs.getString(1);
				String password = rs.getString(2);
				String email = rs.getString(3);
				String country = rs.getString(4);
				
				emp.setId(id);
				emp.setName(name);
				emp.setPassword(password);
				emp.setEmail(email);
				emp.setCountry(country);
			}
			
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emp;
	}

	@Override
	public List<Employee> listEmployeeByPage(int page) {
		List<Employee> list = null;
		try {
			list = new ArrayList<Employee> ();
			String sql = "select id, name, email, country from user limit ?, ?";
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			if (page <= 0) {
				page = 1;
			}
			
			ps.setInt(1, (page - 1) * EmployeeService.pager);
			ps.setInt(2, EmployeeService.pager);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Employee emp = new Employee();
				
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String country = rs.getString(4);
				
				emp.setId(id);
				emp.setName(name);
				emp.setEmail(email);
				emp.setCountry(country);
				
				list.add(emp);
			}
			
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int maxpage(String id) {
		int count = 0;
		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT count(*) FROM todo where user_id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		int maxpage = count / EmployeeService.pager;
		if (count % EmployeeService.pager != 0) {// 나머지가 있으면
			maxpage++;
		}
		return maxpage;
	}

	@Override
	public int maxpage(String id, int category) {
		int count = 0;
		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT count(*) FROM todo where user_id=? and category="+category);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		int maxpage = count / EmployeeService.pager;
		if (count % EmployeeService.pager != 0) {// 나머지가 있으면
			maxpage++;
		}
		return maxpage;
	}

	@Override
	public int listpage() {
		
		return 0;
	}

}
