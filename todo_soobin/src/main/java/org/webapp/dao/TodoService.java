package org.webapp.dao;

import java.util.List;

public interface TodoService {
	int page = 10;
	
	public boolean addTodo(Todo todo);
	
	public List<Todo> listTodo(int page, String id);
	
	public List<Todo> listTodoByCate(int cate, int page, String id);
	
	public Todo getTodo(int idx);
	
	public boolean deleteTodo(int idx); 
		
	public int getCate(int idx);
	
	public boolean updateTodo(Todo todo);
	
	public String getCateName(int category);
	
	public int maxpage(String id);

	public int maxpage(String id, int category);
	
	public List<Todo> getCategory();
}
