package Todooo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Todooo.Dao.User;



@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if (user != null) {
			session.invalidate();
			
			
			response.sendRedirect("/logout.jsp");
		} else {
			request.setAttribute("error", "먼저 로그인을 하세요");;
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}

	}
}
