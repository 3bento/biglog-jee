package com.kennycode.biglogjee.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBConnectionManager {
	// thati s the info to connect with the database.
	private String dbURL;
	private String user;
	private String password;
	// that is the link of connection by web service
	private String JNDIDataSource;
	private Connection con;
	private DataSource dataSource;

	public DBConnectionManager(String url, String u, String p) {
		this.dbURL = url;
		this.user = u;
		this.password = p;
		initializeDataSource();
	}
	public DBConnectionManager(String JNDI_datasource) {
		this.JNDIDataSource = JNDI_datasource;
		initializeDataSource();
	}

	private void initializeDataSource() {
		Context context;
		try {
			context = new InitialContext();
			if(this.JNDIDataSource != null && !this.JNDIDataSource.isEmpty()) {
				this.dataSource = (DataSource) context.lookup(this.JNDIDataSource);
			}else {
				MysqlDataSource mysqlDataSource  = new MysqlDataSource();
				mysqlDataSource.setURL(this.dbURL);
				mysqlDataSource.setUser(this.user);
				mysqlDataSource.setPassword(this.password);
				this.dataSource = mysqlDataSource;
			}
			this.con = this.dataSource.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	};

	public Connection getConnection() throws SQLException{
		return this.con;
	}

	public void closeConnection() throws SQLException{
		this.dataSource.getConnection().close();
	}
}
