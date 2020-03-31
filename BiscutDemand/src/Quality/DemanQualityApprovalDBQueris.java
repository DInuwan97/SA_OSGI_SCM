package Quality;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import biscutdb.DB;
import biscutdb.IDB;
import biscutdemand.Activator;

public class DemanQualityApprovalDBQueris implements IDemandQualityApproval{

	
	private Connection conn = null;
	private Statement statement = null;
	private IDB db;
	private ResultSet resultSet;
	
	
	public DemanQualityApprovalDBQueris() {
		conn = Activator.idb.dbConn();
	}
	
	@Override
	public ResultSet viewDemandRequstbyId(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM demandrequests WHERE demandReqId = '"+id+"' ";
		
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return resultSet;
	}


	@Override
	public boolean updateDemandRequestApproval(int id,DemandQualityApprovalModel demandQualityApprovalModel) {
		
		String sql = "UPDATE demandrequests SET status = '"+demandQualityApprovalModel.isApprovalState()+"' WHERE demandrequests = '"+id+"'";
		
		try {
			statement = conn.createStatement();
			statement.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public ResultSet viewAllDemandRequstes() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM demandrequests";
		
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return resultSet;
	}
	
	

}
