package Todooo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Todooo.Dao.Employee;
import Todooo.Dao.EmployeeDaoImpl;
import Todooo.Dao.EmployeeService;

@WebServlet("/ListPageEmp")
public class ListEmpPageServlet extends HttpServlet{

	private EmployeeService service = new EmployeeDaoImpl();
	/**
	 * 1. 데이터 베이스에 접근 해서,
	 * 2. select * from user;
	 * 3. 추출된 값을 리스트에 담아서 클라이언트에게 답
	 * 
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		int page = Integer.parseInt(request.getParameter("page"));
		List<Employee> list = service.listEmployeeByPage(page);
		
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		try {
			request.getRequestDispatcher("/WEB-INF/views/listPageEmp.jsp").forward(request, response);
		} catch (ServletException se) {
			
		} catch (IOException ioe) {
			
		}
	}
}
