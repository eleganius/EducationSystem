package com.example.app.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminAuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		if (session.getAttribute("loginStatus") == null) {
			res.sendRedirect("/admin/login");
			return;
		}

		LoginStatus status = (LoginStatus) session.getAttribute("loginStatus");
		if (status.getAuthority() != LoginAuthority.ADMIN) {
			res.sendRedirect("/admin/login");
			return;
		}

		chain.doFilter(request, response);
	}

}
