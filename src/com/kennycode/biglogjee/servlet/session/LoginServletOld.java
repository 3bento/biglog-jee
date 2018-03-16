package com.kennycode.biglogjee.servlet.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet: Auth example
 * 
 * @author kenny
 *
 */
@WebServlet(description = " Login Servlet - sample login", urlPatterns = { "/LoginServletOld" }, initParams = {
		@WebInitParam(name = "user", value = "kenny"), @WebInitParam(name = "pwd", value = "123456") })
public class LoginServletOld extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// check if all parameter is ok!
	private boolean checkParameterDb() {
		return getServletContext().getInitParameter("dbURL").equals("jdbc:mysql://localhost/biglog")
				&& getServletContext().getInitParameter("dbUser").equals("biglog_user")
				&& getServletContext().getInitParameter("dbUserPwd").equals("biglog");
	}

	public void init() throws ServletException {
		// we can create DB connection resource here and set it to Servlet Context
		if (checkParameterDb()) {
			getServletContext().setAttribute("DB_Success", "True");
		} else {
			throw new ServletException("DB Connection error!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get request parameters for userID and password!
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");

		// get servlet config init params
		String userID = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("pwd");

		// logging example
		log("User=" + user + "::password=" + pwd);
		log("User=" + userID + "::password=" + password);
		if (userID.equals(user) && password.equals(pwd)) {
			response.sendRedirect("/biglog/jsps/auth/LoginSuccess.jsp");
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsps/auth/login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}
	}
}
