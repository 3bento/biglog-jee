package com.kennycode.biglogjee.listener;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.kennycode.biglogjee.db.DBConnectionManager;

public class AppContextListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext ctx = servletContextEvent.getServletContext();
		
		//	String url = ctx.getInitParameter("dbURL");
		//	String u = ctx.getInitParameter("dbUser");
		//	String p = ctx.getInitParameter("dbPwd");
		//  create database connection from init parameters and set it to context
		//	DBConnectionManager dbManager = new DBConnectionManager(url, u, p);
		
		String JNDIDataSource = ctx.getInitParameter("JNDIDataSource");
		
		// change that to use the real implementation of dbManager*
		DBConnectionManager dbManager = new DBConnectionManager(JNDIDataSource);
		ctx.setAttribute("DBManager", dbManager);
		System.out.println("Database connection initialized for Application.");
	}
	
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ServletContext ctx = servletContextEvent.getServletContext();
		DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
		try {
			dbManager.closeConnection();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Database connection closed for Application.");
	}
}
