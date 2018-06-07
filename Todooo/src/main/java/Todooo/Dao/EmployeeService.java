package Todooo.Dao;

import java.util.List;

public interface EmployeeService {
	int pager = 10;
	
	public boolean saveEmployee(Employee e);
	
	public List<Employee> listEmployee();
	
	public Employee listEmployee(int id);
	
	public List<Employee> listEmployeeByPage(int page);
	
	public boolean deleteEmployee(int id);
	
	public boolean updateEmployee(Employee e);
	
	public int maxpage(String id);

	public int maxpage(String id, int page);
	
	public int listpage();
}
