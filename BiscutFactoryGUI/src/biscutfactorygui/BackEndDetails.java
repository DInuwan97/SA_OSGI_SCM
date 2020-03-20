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
		int msgId[] = new int[1000];
		int salesId[] = new int[1000];
		String message[] = new String[10000];
		String addedDate[] = new String[1000];
		String addedTime[] = new String[1000];
		String confirmationStatus[] = new String[1000];
		
		backEndMessageDetailList = new ArrayList<>();
		//db = new DB();
		String sql = "SELECT * FROM msgInfo";
		
		try {
			
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			
//		int i = messageInfoCount;	
//		while(resultSet.next()) {
//		
//			backEndMessageDetailsModel.msgId[i] = Integer.parseInt(resultSet.getString("msgId").toString());
//			backEndMessageDetailsModel.saleId[i] = Integer.parseInt(resultSet.getString("salesId").toString());
//			backEndMessageDetailsModel.message[i] = resultSet.getString("message");
//			addedDate[i] = resultSet.getString("addedDate").toString();
//			addedTime[i] = resultSet.getString("addedTime").toString();
//			confirmationStatus[i] = resultSet.getString("demandConfirm").toString();		
//
//			
//			backEndMessageDetailList.add(new BackEndMessageDetailsModel(msgId,salesId,message,addedDate,addedTime,confirmationStatus));
//			backEndMessageDetailsModel.messageDetailsList = backEndMessageDetailList;
//			i++;
//		}
//		messageInfoCount = i;
//		
//			for(int j = 0 ; j < 2; j++) {
//				System.out.println("msg id : " +backEndMessageDetailsModel.msgId[j]);
//				System.out.println("message : " +backEndMessageDetailsModel.message[j]);
//				System.out.println("=====================================");
//			}
			
		//return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return false;
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
	@Override
	public boolean insertDemandReq(DemandRequestDataModel demandRequestDataModel) {
		// TODO Auto-generated method stub
		
		
		String sql = "INSERT INTO demandrequests(productdetails,demadReason,salesMsgId,reqDate) VALUES('"+demandRequestDataModel.getDemandRequest()+"','"+demandRequestDataModel.getDescription()+"','"+demandRequestDataModel.getMsgId()+"','"+demandRequestDataModel.getReqDate()+"')";
		
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

}
