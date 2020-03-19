package biscutfactorygui;

import java.sql.*;

import biscutdb.DB;
import biscutdb.IDB;
import biscutmanafacture.BiscutModel;

public class BackEndManufactureBiscutDetails implements IBackEnd{

	private Connection conn = null;
	private Statement statement = null;
	private IDB db;
	@Override
	public boolean InsertBiscutManufactureDetails(BiscutModel biscutModel) {
		// TODO Auto-generated method stub
		db = new DB();
		
		String sql = "INSERT INTO manufacturedetails(manufactureDate,expireDate,biscutName,materials,noOfMachines,noOfEmployees,manaufactAmount,demandReqId)"
		+ "VALUES('"+biscutModel.getManufactureDate()+"','"+biscutModel.getExpireDate()+"','"+biscutModel.getBiscutName()+"'"
		+ ",'"+biscutModel.getIngridents()+"','"+biscutModel.getNoOfMachines()+"','"+biscutModel.getNumOfEmployees()+"','"+biscutModel.getManufactAmount()+"',12)";
		
		try {
			
			conn = db.dbConn();
			statement = conn.createStatement();
			statement.executeUpdate(sql);
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		
		
	}

}
