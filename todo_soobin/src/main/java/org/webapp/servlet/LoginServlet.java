package org.webapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.webapp.dao.UserService;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private UserService service = new UserService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		if (service.isValidUser(id, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("user_id", id);
			request.setAttribute("name", service.getName(id));
			request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "로그인에 실패하셨습니다");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
	
	}
}
