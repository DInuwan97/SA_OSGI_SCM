package biscutquality;

import java.sql.Connection;
import java.util.ArrayList;

public interface IQualityDB {
	public Connection dbConn();
	public ArrayList<DemandModal> getDemand();
	public int approveDemand(ArrayList<DemandModal> list, int index, String value);
	
	public ArrayList<ManufactureModal> getManufactures();
	public int setAction(int manufacId, String action);
	
}
