package biscutdb;

import java.sql.*;

import com.mysql.jdbc.Connection;

public class DB implements IDB{

	private Connection conn;
	@Override
	public Connection dbConn() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/biscuitfactory?useSSL=false", "root", "ThirtyFirst9731@");
			System.out.println("Successfully DB Connected!!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Mismatching the Connection String");
			e.printStackTrace();
		}finally{
			return conn;
		}
		
	
	}

}
