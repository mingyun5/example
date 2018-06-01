package org.webapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDaoImpl implements TodoService {
	private static final String URL = "jdbc:mysql://127.0.0.1/todo?useSSL=no&characterEncoding=utf8";
	private static final String USER = "hansoobin";
	private static final String PW = "1234";

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

	@Override
	public boolean addTodo(Todo todo) {
		int status = 0;
		try {
			Connection conn = getConnection();
			String sql = "insert into todo (user_id,content,target_date,category) values (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, todo.getUserId());
			ps.setString(2, todo.getContent());
			ps.setDate(3, todo.getTargetDate());
			ps.setInt(4, todo.getCategory());
			status = ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status != 0 ? true : false;
	}

	@Override
	public List<Todo> listTodo(int page, String id) {
		List<Todo> list = new ArrayList<>();
		try {
			Connection conn = getConnection();
			String sql = "select a.idx, a.content, a.target_date, a.create_date, a.done, b.name, a.category\r\n"
					+ "from todo a inner join todo_category b\r\n" + "	on a.category = b.cat_id\r\n"
					+ "where a.user_id = ?\r\n" + "limit ?,?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, (page - 1) * TodoService.page);
			ps.setInt(3, TodoService.page);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Todo todo = new Todo();
				todo.setIdx(rs.getInt(1));
				todo.setContent(rs.getString(2));
				todo.setTargetDate(rs.getDate(3));
				todo.setCreateDate(rs.getDate(4));
				todo.setDone(rs.getBoolean(5));
				todo.setCategoryName(rs.getString(6));
				todo.setCategory(rs.getInt(7));
				list.add(todo);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Todo> listTodoByCate(int cate, int page, String id) {
		List<Todo> list = new ArrayList<>();
		try {
			Connection conn = getConnection();
			String sql = "select a.idx, a.content, a.target_date, a.create_date, a.done, b.name, a.category \r\n"
					+ "from todo a inner join todo_category b\r\n" + "	on a.category = b.cat_id\r\n"
					+ "and a.category = " + cate + "\r\n" + "and a.user_id =" + "\'" + id + "\'" + "\n" + "limit ?,?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, (page - 1) * TodoService.page);
			ps.setInt(2, TodoService.page);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Todo todo = new Todo();
				todo.setIdx(rs.getInt(1));
				todo.setContent(rs.getString(2));
				todo.setTargetDate(rs.getDate(3));
				todo.setCreateDate(rs.getDate(4));
				todo.setDone(rs.getBoolean(5));
				todo.setCategoryName(rs.getString(6));
				todo.setCategory(rs.getInt(7));
				list.add(todo);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Todo getTodo(int idx) {
		Todo todo = new Todo();
		try {
			Connection conn = getConnection();
			String sql = "select content,target_date,done,category,idx from todo a where idx=" + idx;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				todo.setContent(rs.getString(1));
				todo.setTargetDate(rs.getDate(2));
				todo.setDone(rs.getBoolean(3));
				todo.setCategory(rs.getInt(4));
				todo.setIdx(rs.getInt(5));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return todo;
	}

	@Override
	public boolean deleteTodo(int idx) {
		int status = 0;
		try {
			Connection conn = getConnection();
			String sql = "delete from todo where idx=" + idx;
			PreparedStatement ps = conn.prepareStatement(sql);
			status = ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status != 0 ? true : false;
	}

	@Override
	public int getCate(int idx) {
		try {
			Connection conn = getConnection();
			String sql = "select category from todo where idx=" + idx;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next())
				return rs.getInt(1);

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String getCateName(int category) {
		try {
			Connection conn = getConnection();
			String sql = "select name from todo_category where cat_id=" + category;
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				return rs.getString(1);

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateTodo(Todo todo) {
		int status = 0;
		try {
			Connection conn = getConnection();
			String sql = "update todo set content=?, target_date=?, done=?, category=? where idx=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, todo.getContent());
			ps.setDate(2, todo.getTargetDate());
			ps.setBoolean(3, todo.isDone());
			ps.setInt(4, todo.getCategory());
			ps.setInt(5, todo.getIdx());

			status = ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status != 0 ? true : false;
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
			// TODO ??? ?????? catch ???
			e.printStackTrace();
		} 
		int maxpage = count / TodoService.page;
		if (count % TodoService.page != 0) {// 나머지가 있으면
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
			// TODO ??? ?????? catch ???
			e.printStackTrace();
		} 
		int maxpage = count / TodoService.page;
		if (count % TodoService.page != 0) {// 나머지가 있으면
			maxpage++;
		}
		return maxpage;
	}

	@Override
	public List<Todo> getCategory() {
		List<Todo> list = new ArrayList<>();
		try {
			Connection conn = getConnection();
			String sql = "select * from todo_category";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Todo todo = new Todo();
				todo.setCategory(rs.getInt(1));
				todo.setCategoryName(rs.getString(2));
				list.add(todo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
