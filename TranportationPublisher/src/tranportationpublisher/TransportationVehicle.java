package tranportationpublisher;

import java.sql.ResultSet;
import java.util.List;

public interface TransportationVehicle {
	public boolean addNewVehicel(TransportationVehicleModal transportationModel);
	public boolean removeVehicle(String vehicleNoPlate);
	public ResultSet getVehicles();
	public List getAvailableVehicle();
	public int getVehicleCount();
}
