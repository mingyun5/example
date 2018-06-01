package org.webapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class LoginFilter implements Filter {

	FilterConfig config;
	String[] excludedUrls;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest rq = ((HttpServletRequest)request);
		StringBuffer path = new StringBuffer(rq.getRequestURI());
		if (rq.getQueryString() != null)
			path.append("?").append(rq.getQueryString());
	
		
		for (String s : excludedUrls) {
			if(path.toString().equals(s) || path.toString().contains("/resources")) {
				chain.doFilter(request, response);
				return;
			}
		}
		HttpSession session = rq.getSession();
		String id = (String)session.getAttribute("user_id");
		if (id != null) {
			chain.doFilter(request, response);
		} else {
			request.setAttribute("error", "먼저 로그인을 하세요");
			request.setAttribute("orgPath", path.toString());
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		String s = config.getInitParameter("excluded");
		excludedUrls = s.split(",");
		
		for (int i=0; i<excludedUrls.length; i++) {
			excludedUrls[i] = excludedUrls[i].trim();
		}
	}
}
