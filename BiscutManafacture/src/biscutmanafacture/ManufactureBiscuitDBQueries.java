package biscutmanafacture;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import biscutdb.DB;
import biscutdb.IDB;


public class ManufactureBiscuitDBQueries implements IBiscuitManufactureDBQuries{
	
	private Connection conn = null;
	private Statement statement = null;
	private IDB db;
	private ResultSet resultSet;
	private int messageInfoCount = 0;
	
	public ManufactureBiscuitDBQueries() {
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
			System.out.println("Success");
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		
		
	}
	@Override
	public ResultSet getAllManfautureProducts() {
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
