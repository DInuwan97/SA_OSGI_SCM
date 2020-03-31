package tranportationpublisher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import biscutdb.DB;
import biscutdb.IDB;

public class TransportationVehicleImpl implements TransportationVehicle{
	List<String> vehicles = new ArrayList<String>();
	private Connection conn = null;
	//private Statement statement = null;
	private IDB db;
	
	public TransportationVehicleImpl() {
		// TODO Auto-generated constructor stub
		db = new DB();
		conn = db.dbConn();
	}
	
	
	//getting all the vehicles
	public ResultSet getVehicles() {
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM transportationvehicle");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vehicles.add(rs.getString(2));
			}
			
			
			for(int i = 0; i < vehicles.size(); i++) {
				System.out.println(vehicles.get(i).toString());
				
			}
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		

		
	}

	
	@Override
	public boolean addNewVehicel(TransportationVehicleModal transportationModel) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = conn.prepareStatement("insert into transportationvehicle(plateNumber,driver)values(?,?)");
			ps.setString(1, transportationModel.getVehicelPlate());
			ps.setString(2, transportationModel.getDriverName());
			
			int n = ps.executeUpdate();
			System.out.println("Sucessfully inserted");
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean removeVehicle(String vehicleNoPlate) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try {
			
			
			ps = conn.prepareStatement("DELETE FROM transportationvehicle WHERE plateNumber = ?");
			ps.setString(1,vehicleNoPlate);
			int n = ps.executeUpdate();
			System.out.println(n);
			if(n == 0)
			{
				System.out.println("There are no Vehcles to remove");
				return false;
			}else
			{
				System.out.println("Delete Sucessfully");
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception.
			return false;
		}
		
	}


	@Override
	public int getVehicleCount() {
		PreparedStatement ps;
		int count = 0;
		try {
			ps = conn.prepareStatement("SELECT * FROM transportationvehicle WHERE availability = 'Available'");
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				return 0;
			}else {
				do {
					count++;
				}while(rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return count;
		
}


	@Override
	public List getAvailableVehicle() {
		try {
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM transportationvehicle WHERE availability = 'Available'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				vehicles.add(rs.getString(2));
			}
			return vehicles;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
	
	
	
	/*
	 * 		if(vehicles.isEmpty()) {
			System.out.println("There are no Vehcles to remove");
			return false;
		}else {
			int index = vehicles.indexOf(vehicleNoPlate);
			if(vehicles.get(index) == null) {
				System.out.println("The vehicle does not exist");
				return false;
			}else {
				vehicles.remove(index);
				System.out.println("Vehicle removed");
			}
		}
		return true;
	 * 
	 * */
	
	
	/*
	 * PreparedStatement ps = null;
		try {
			con = DbConnection.getConnection();
			
			
			ps = con.prepareStatement(DELETE_IETM);
			ps.setString(1,itemId);
			ps.execute();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	 * */

	
}
