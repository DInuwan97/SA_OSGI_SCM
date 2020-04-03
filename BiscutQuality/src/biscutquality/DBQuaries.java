package biscutquality;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class DBQuaries {
	
	private Connection conn = null;
	
	public Connection dbConn() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/biscuitfactory?useSSL=false", "root", "password");
			if(conn != null)
				System.out.println("Successfully DB Connected!!!");
			else
				System.out.println("Connection error");
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Mismatching the Connection String");
			e.printStackTrace();
		}finally{
			return conn;
		}
		
	
	}
	
	public ArrayList<DemandModal> getDemand() {
		ArrayList<DemandModal> demands = new ArrayList<DemandModal>();
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM demandrequests ";
		
		try {
			con = dbConn();
			stat = (Statement) con.createStatement();
			rs = stat.executeQuery(sql);
			
			while(rs.next()) {
				DemandModal dm = new DemandModal();
				dm.setDemandReqId(rs.getInt(1));
				dm.setProductDetails(rs.getString(2));
				dm.setDemandReason(rs.getString(3));
				dm.setSalesMsgId(rs.getInt(4));
				dm.setReqDate(rs.getString(5));
				dm.setApprovalState(rs.getString(6));
				demands.add(dm);
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return demands;
	}
	
	
	public int approveDemand(ArrayList<DemandModal> list, int index, String value) {
		// System.out.println(index + " " + value);
		
		Connection con = null;
		Statement stat = null;
		int result = 0, id = 0, i = 0;
		
		// find id
		for (DemandModal dm : list) {
			if(i > index) {
				break;
			}
			id = dm.getDemandReqId();
			i++;
		}
		
		System.out.println("id = " + id);
		
		String sql = "UPDATE demandrequests SET status='"+ value +"' WHERE demandReqId='"+ id +"' ";
		
		try {
			con = dbConn();
			stat = (Statement) con.createStatement();
			result = stat.executeUpdate(sql);
			// System.out.println("result = " + result);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
		
	}
	

}
