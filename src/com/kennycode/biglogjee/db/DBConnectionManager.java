package com.kennycode.biglogjee.db;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.mysql.jdbc.Connection;

public class DBConnectionManager {
	private String dbURL;
	private String user;
	private String password;
	private Connection con;
	private DataSource dataSource;

	public DBConnectionManager(String url, String u, String p) {
		this.dbURL = url;
		this.user = u;
		this.password = p;
		// create db Connection now!
		// Context context;
		// try {
		// context = new Context();
		// this.dataSource = (DataSource) context.lookup("java:comp/env/jdbc/biglogdb");
		// boolean isClosed = this.dataSource.getConnection().isClosed();
		// if (isClosed) {
		// System.out.println("Connection is connected!");
		// }
		// } catch (NamingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }

	}

	public Connection getConnection() {
		return this.con;
	}

	public void closeConnection() {
		// close DB connection here!
		System.out.println("Connection is closed!");
	}
}
