package Todooo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Todooo.Dao.Employee;
import Todooo.Dao.EmployeeDaoImpl;
import Todooo.Dao.EmployeeService;
import Todooo.sec.PasswordAuthentication;



/**
 * Servlet implementation class SaveServlet
 */
@WebServlet("/UpdateProc") 
public class UpdateEmpProcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmployeeService service = new EmployeeDaoImpl();	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */ 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PasswordAuthentication passAuth = new PasswordAuthentication();
		Employee e = new Employee();
		int id = Integer.parseInt(request.getParameter("id"));
		Employee emp = service.listEmployee(id);
		
		e.setId(Integer.parseInt(request.getParameter("id")));   
		e.setName(request.getParameter("name"));
		e.setEmail(request.getParameter("email"));
		e.setCountry(request.getParameter("country"));
		
		if (passAuth.authenticate(request.getParameter("password").toCharArray(),
				emp.getPassword())) {	// 패스워드 인증 성공
			
			if (service.updateEmployee(e)) {
				response.sendRedirect("/ListPageEmp?page=1");
			} else {
				request.setAttribute("error", "직원 정보변경에 실패했습니다.");
				request.setAttribute("emp", e);
				request.getRequestDispatcher("/WEB-INF/views/updateForm.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("error", "패스워드가 맞지 않습니다.");
			request.setAttribute("emp", e);
			request.getRequestDispatcher("/WEB-INF/views/updateForm.jsp").forward(request, response);
		}
		
		
		
		
		
	}

}
