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

@WebServlet("/listTodoServlet")
public class ListTodoPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TodoService service = new TodoDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cate = Integer.parseInt(request.getParameter("category"));
		int page = Integer.parseInt(request.getParameter("page"));
		String userId = (String)request.getSession().getAttribute("user_id");
		List<Todo> list = service.listTodoByCate(cate, page, userId);
		List<Todo> catList = service.getCategory();
		request.setAttribute("page", page);
		request.setAttribute("cate", cate);
		request.setAttribute("list", list);
		request.setAttribute("catList", catList);
		request.getRequestDispatcher("/WEB-INF/views/todoList.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
