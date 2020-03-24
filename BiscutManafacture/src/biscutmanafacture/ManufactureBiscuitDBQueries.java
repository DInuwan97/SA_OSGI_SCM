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
		+ ",'"+biscutModel.getIngridents()+"','"+biscutModel.getNoOfMachines()+"','"+biscutModel.getNumOfEmployees()+"','"+biscutModel.getManufactAmount()+"','"+biscutModel.getDemandReqId()+"')";
		
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
		
		String sql = "SELECT * FROM manufacturedetails";
		
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return resultSet;
		
	}

	@Override
	public boolean updateManufactureProductDetails(BiscutModel biscutModel) {
		// TODO Auto-generated method stub
		String sql = "UPDATE manufacturedetails SET manufactureDate = '"+biscutModel.getManufactAmount()+"',expireDate = '"+biscutModel.getExpireDate()+"',"
				+ "biscutName = '"+biscutModel.getBiscutName()+"',noOfMachines = '"+biscutModel.getNoOfMachines()+"',noOfEmployees = '"+biscutModel.getNumOfEmployees()+"',"
						+ "manaufactAmount = '"+biscutModel.getManufactAmount()+"' WHERE manufactureId = '"+biscutModel.getManufactId()+"'";
		
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
	public ResultSet searchManufaturedProduct(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM manufacturedetails WHERE manufactureId = '"+id+"'";
		
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultSet;
	}

}
