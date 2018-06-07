package Todooo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Todooo.Dao.User;



public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		StringBuffer path = new StringBuffer(req.getRequestURI());
		
		if(req.getQueryString() != null) {
			path.append("?").append(req.getQueryString());
		}
		
		System.out.println(path.toString());
		
		if (path.toString().equals("/Index.jsp") || path.toString().equals("/")
				|| path.toString().equals("/LoginServlet")
				|| path.toString().equals("/LogoutServlet")
				|| path.toString().equals("/logout.jsp")) {
			chain.doFilter(request, response);
			
			return;
		} 
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		User user = (User)session.getAttribute("user");
		
		if (user != null) {
			chain.doFilter(request, response);
		} else {
			request.setAttribute("error", "먼저 로그인을 하세요");
			request.setAttribute("orgPath", path.toString());
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
