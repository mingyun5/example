package org.webapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webapp.dao.Todo;
import org.webapp.dao.TodoDaoImpl;
import org.webapp.dao.TodoService;

@WebServlet("/listWholeServlet")
public class ListTodoWholeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TodoService service = new TodoDaoImpl();  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		List<Todo> list = service.listTodo(page, (String)request.getSession().getAttribute("user_id"));
		List<Todo> catList = service.getCategory();
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		request.setAttribute("catList", catList);
		request.getRequestDispatcher("/WEB-INF/views/wholeTodoList.jsp").forward(request, response);
	}
}





