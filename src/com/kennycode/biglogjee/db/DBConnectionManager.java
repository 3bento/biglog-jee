package com.kennycode.biglogjee.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnectionManager {

	// that is the link of connection by web service
	private String JNDIDataSource;
	private Connection con;
	private DataSource dataSource;

	public DBConnectionManager(String JNDI_datasource) {
		this.JNDIDataSource = JNDI_datasource;
		initializeDataSource();
	}

	private void initializeDataSource() {
		Context context;
		try {
			context = new InitialContext();
			if (this.JNDIDataSource != null && !this.JNDIDataSource.isEmpty()) {
				this.dataSource = (DataSource) context.lookup(this.JNDIDataSource);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};

	public Connection getConnection() throws SQLException {
		return this.con;
	}

	public void closeConnection() throws SQLException {
		this.dataSource.getConnection().close();
	}
}