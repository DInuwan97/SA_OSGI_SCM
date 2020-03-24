package salespublisher;

import java.sql.*;

import biscutdb.DB;
import biscutdb.IDB;

public class SalesServiceImpl implements SalesService{

	SalesModel salesModel = new SalesModel();
	private Connection conn = null;
	//private Statement statement = null;
	private IDB db;
	private ResultSet resultSet;
	
	
	public SalesServiceImpl() {
		// TODO Auto-generated constructor stub
		db = new DB();
		conn = db.dbConn();
	}
	
	@Override
	public double calculateSalesIncome() {
		
		return 0;
	}

	@Override
	public double generateProfite() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean sendDemandRequest(SalesModel salesModel) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = conn.prepareStatement("insert into msginfo(salesId,message,addedDate,addedTime,demandConfirm)values(?,?,?,?,?)");
			ps.setInt(1, salesModel.getSaleesid());
			ps.setString(2, salesModel.getMessage());
			ps.setString(3, salesModel.getDate());
			ps.setString(4, salesModel.getTime());
			ps.setString(5, "pending");
			
			int n = ps.executeUpdate();
			System.out.println("Sucessfully inserted");
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		
	}

}
