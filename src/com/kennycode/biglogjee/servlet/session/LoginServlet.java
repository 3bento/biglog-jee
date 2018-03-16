package com.kennycode.biglogjee.servlet.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 * 
 * @author kenny
 *
 */
// @WebServlet("/LoginServlet")
@WebServlet(urlPatterns = "/LoginServlet", initParams = { @WebInitParam(name = "user", value = "kenny"),
		@WebInitParam(name = "pwd", value = "123456") })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get request parameters for userID and password
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");

		// get init parameter from ServletConfiguration
		String userID = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("pwd");

		if (userID.equals(user) && password.equals(pwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("userSession", userID);
			session.setAttribute("last_login", new Date().getTime());
			// setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);
			Cookie loginCookie = new Cookie("userCookie", user);
			// setting cookie to expiry in 30 mins
			loginCookie.setMaxAge(30*60);
			response.addCookie(loginCookie);
			
			// Get the encoded URL String
			String encodedURL = response.encodeRedirectUrl("/biglog/jsps/auth/LoginSuccess.jsp");
		
			//String encodedURL  = URLEncoder.encode("/biglog/jsps/auth/LoginSuccess.jsp",  java.nio.charset.StandardCharsets.UTF_8.toString());
			System.out.println(encodedURL);
			response.sendRedirect(encodedURL);
		}else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsps/auth/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}
	}
}
