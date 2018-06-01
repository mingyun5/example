package org.webapp.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webapp.dao.Todo;
import org.webapp.dao.TodoDaoImpl;
import org.webapp.dao.TodoService;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TodoService service = new TodoDaoImpl();   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		Todo todo = service.getTodo(idx);
		request.setAttribute("prevTodo", todo);
		request.getRequestDispatcher("/WEB-INF/views/updateTodo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		Todo beforeTodo = service.getTodo(idx);
		beforeTodo.setCategoryName(service.getCateName(beforeTodo.getCategory()));
		
		Todo afterTodo = new Todo();
		afterTodo.setContent(request.getParameter("content"));
		afterTodo.setTargetDate(Date.valueOf(request.getParameter("date")));
		afterTodo.setDone(Boolean.parseBoolean(request.getParameter("done")));
		afterTodo.setCategory(Integer.parseInt(request.getParameter("category")));
		afterTodo.setCategoryName(service.getCateName(Integer.parseInt(request.getParameter("category"))));
		afterTodo.setIdx(idx);
		if (service.updateTodo(afterTodo)) {
			request.setAttribute("afterTodo", afterTodo);
			request.setAttribute("beforeTodo", beforeTodo);
			request.getRequestDispatcher("/WEB-INF/views/resultUpdate.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "수정에 실패하셨습니다");
			request.getRequestDispatcher("/WEB-INF/views/updateTodo.jsp").forward(request, response);
		}	
	}

}






