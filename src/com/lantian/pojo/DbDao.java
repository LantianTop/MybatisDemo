package com.lantian.pojo;

import java.sql.*;

public class DbDao 
{

	private static DbDao op;
	private Connection conn;
	private String driver;
	private String url;
	private String username;
	private String pass;

	private DbDao()
	{
	}

	private DbDao(String driver,String url,String username,String pass)
		throws  Exception
	{
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.pass = pass; 
		Class.forName(driver);
		conn = DriverManager.getConnection(url,username,pass);
	}

	public void setDriver(String driver) {
		this.driver = driver; 
	}
	public void setUrl(String url) {
		this.url = url; 
	}
	public void setUsername(String username) {
		this.username = username; 
	}
	public void setPass(String pass) {
		this.pass = pass; 
	}
	public String getDriver() {
		return (this.driver); 
	}
	public String getUrl() {
		return (this.url); 
	}
	public String getUsername() {
		return (this.username); 
	}
	public String getPass() {
		return (this.pass); 
	}

	public void getConnection() throws Exception
	{
		if (conn == null)
		{
			Class.forName(this.driver);
			conn = DriverManager.getConnection(url,username,
				this. pass);
		}
	}

	public static DbDao instance()
	{
		if (op == null)
		{
			op = new DbDao();
		}
		return op;
	}

	public static DbDao instance(String driver,String url,
		String username, String pass) throws Exception
	{
		if (op == null)
		{
			op = new DbDao(driver,url,username,pass);
		}
		return op;
	}

	public boolean insert(String sql) throws Exception
	{
		getConnection();
		Statement stmt = this.conn.createStatement();
		if (stmt.executeUpdate(sql) != 1)
		{
			return false;
		}
		return true;
	}

	public ResultSet query(String sql) throws Exception
	{
		getConnection();
		Statement stmt = this.conn.createStatement();
		return stmt.executeQuery(sql);
	}

	public void delete(String sql) throws Exception
	{
		getConnection();
		Statement stmt = this.conn.createStatement();
		stmt.executeUpdate(sql);
	}

	public void update(String sql) throws Exception
	{
		getConnection();
		Statement stmt = this.conn.createStatement();
		stmt.executeUpdate(sql);
	}
}

