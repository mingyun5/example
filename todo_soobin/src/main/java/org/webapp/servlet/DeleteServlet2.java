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

@WebServlet("/deleteServlet2")
public class DeleteServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TodoService service = new TodoDaoImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int page = Integer.parseInt(request.getParameter("page"));
		if(service.deleteTodo(idx)) {
			response.sendRedirect("/listWholeServlet?page="+page);
		} else {
			request.setAttribute("error", "삭제에 실패하셨습니다");
			String userId = (String)request.getSession().getAttribute("user_id");
			List<Todo> list = service.listTodo(page, userId);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/views/wholeTodoList.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
