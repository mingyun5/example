package Todooo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Todooo.Dao.Profile;
import Todooo.Dao.User;
import Todooo.Dao.UserService;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		
		UserService service = new UserService();
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if (user != null) {
			Profile profile = service.getProfile(user.getId());
			request.setAttribute("profile", profile);
			request.getRequestDispatcher("/WEB-INF/views/profile.jsp")
				.forward(request, response);
		} else {
			request.setAttribute("error", "먼저 로그인을 하셔야 합니다");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
		
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("ProfileServlet doPost....");
		doGet(request, response);
	}

}
