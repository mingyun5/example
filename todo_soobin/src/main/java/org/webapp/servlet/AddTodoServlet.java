package org.webapp.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.webapp.dao.Todo;
import org.webapp.dao.TodoDaoImpl;
import org.webapp.dao.TodoService;

@WebServlet("/addServlet")
public class AddTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static TodoService service = new TodoDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/addTodo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Todo todo = new Todo();
		HttpSession session = request.getSession();
		todo.setUserId((String)session.getAttribute("user_id"));
		todo.setContent(request.getParameter("content"));
		todo.setTargetDate(Date.valueOf(request.getParameter("date")));
		int cat = Integer.parseInt(request.getParameter("category"));
		todo.setCategory(cat);
		
		if(service.addTodo(todo)) {
			response.sendRedirect("/listTodoServlet?category="+cat+"&page=1");
		} else {
			request.setAttribute("error", "일정 추가에 실패하셨습니다");
			request.getRequestDispatcher("/WEB-INF/views/addTodo.jsp").forward(request, response);
		}
	}
}
