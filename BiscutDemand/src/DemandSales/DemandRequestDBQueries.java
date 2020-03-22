package DemandSales;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import biscutdb.DB;
import biscutdb.IDB;


public class DemandRequestDBQueries implements IdemandRequstDBQueries {


	private Connection conn = null;
	private Statement statement = null;
	private IDB db;
	private ResultSet resultSet;
	
	
	public  DemandRequestDBQueries() {
		db = new DB();
		conn = db.dbConn();
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

	
	
	
}
