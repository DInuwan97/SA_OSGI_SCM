package biscutfactorygui;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DemandSales.SalesDemandMsgModel;
import biscutdb.DB;
import biscutdb.IDB;
import biscutmanafacture.BiscutModel;


public class BackEndDetails implements IBackEnd{

	private Connection conn = null;
	private Statement statement = null;
	private IDB db;
	private ResultSet resultSet;
	private ArrayList<BackEndMessageDetailsModel> backEndMessageDetailList;
	private int messageInfoCount = 0;
	
	public BackEndDetails() {
		db = new DB();
		conn = db.dbConn();
	}
	@Override
	public boolean InsertBiscutManufactureDetails(BiscutModel biscutModel) {
		// TODO Auto-generated method stub
		//db = new DB();
		String sql = "INSERT INTO manufacturedetails(manufactureDate,expireDate,biscutName,materials,noOfMachines,noOfEmployees,manaufactAmount,demandReqId)"
		+ "VALUES('"+biscutModel.getManufactureDate()+"','"+biscutModel.getExpireDate()+"','"+biscutModel.getBiscutName()+"'"
		+ ",'"+biscutModel.getIngridents()+"','"+biscutModel.getNoOfMachines()+"','"+biscutModel.getNumOfEmployees()+"','"+biscutModel.getManufactAmount()+"',12)";
		
		try {
			
			
			statement = conn.createStatement();
			statement.executeUpdate(sql);
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	
	
	@Override
	public ResultSet LoadSalesMessages() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM msgInfo";	
		try {	
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return resultSet;
	}
	
	
	@Override
	public int getSalesMessageCount() {
		// TODO Auto-generated method stub
		return messageInfoCount;
	}
	
	@Override
	public ResultSet getSelectedSalesMessage(int id) {
		// TODO Auto-generated method stub
		//db = new DB();
		String sql = "SELECT * FROM msgInfo WHERE msgID = '"+id+"' ";
		
		try {
			
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return resultSet;
	}
	

}
