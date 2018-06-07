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

@WebServlet("/UpdateEmp")
public class UpdateEmpServlet extends HttpServlet{

	private EmployeeService service = new EmployeeDaoImpl();
	/**
	 * 1. 데이터 베이스에 접근 해서,
	 * 2. select * from user;
	 * 3. 추출된 값을 리스트에 담아서 클라이언트에게 답
	 * 
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Employee emp = service.listEmployee(id);
		
		request.setAttribute("emp", emp);
		request.getRequestDispatcher("/WEB-INF/views/updateForm.jsp").forward(request, response);
	}
}
