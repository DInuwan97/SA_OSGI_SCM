package biscutdb;

import java.sql.*;


public class DB implements IDB{

	private Connection conn;
	@Override
	public Connection dbConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/biscuitfactory", "root","");
			System.out.println("Successfully DB Connected!!!");
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Mismatching the Connection String");
			e.printStackTrace();
		}
		return null;
		
	
	}

}
