package DemandSales;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import biscutdb.DB;
import biscutdb.IDB;
import biscutdemand.Activator;


public class DemandRequestDBQueries implements IdemandRequstDBQueries {


	private Connection conn = null;
	private Statement statement = null;
	private IDB db;
	private ResultSet resultSet;
	
	public  DemandRequestDBQueries() {
		//Activator.idb = new DB();
		conn = Activator.idb.dbConn();
	}
		
	@Override
	public boolean insertDemandReq(SalesDemandMsgModel salesDemandMsgModel) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO demandrequests(productdetails,demadReason,salesMsgId,reqDate,status) VALUES('"+salesDemandMsgModel.getDemandRequest()+"','"+salesDemandMsgModel.getDescription()+"','"+salesDemandMsgModel.getMsgId()+"','"+salesDemandMsgModel.getReqDate()+"','false')";
		
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
	public ResultSet getSelectedSalesMessage(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM msgInfo WHERE msgID = '"+id+"' ";
		
		try {
			
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return resultSet;
	}

	@Override
	public boolean deleteSalesMsg(int id) {
		// TODO Auto-generated method stub
		
		String sql= "DELETE FROM msgInfo WHERE msgId = '"+id+"'";
		
		try {
			statement = conn.createStatement();
			statement.executeUpdate(sql);
			return true;
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			return false;
		}
		
	}

	@Override
	public ResultSet viewDemandsOnDate(SalesMessageDetailsModel salesDemandMsgModel) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM demandrequests WHERE reqDate BETWEEN '"+salesDemandMsgModel.getDate_1()+"' AND '"+salesDemandMsgModel.getDate_2()+"' ";
		
		try {
			
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return resultSet;
	}
	
	@Override
	public ResultSet LoadSalesMessages() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM msgInfo";	
		try {	
			System.out.println("Con is : " +conn);
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return resultSet;
	}

	
	
	
}
