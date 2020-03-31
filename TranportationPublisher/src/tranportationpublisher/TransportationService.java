package tranportationpublisher;

import java.util.*;

public interface TransportationService {

	public boolean checktransportationAvailability(int requiredVehicels);
	public String getFinishGoods();
	public void transportationDetails();
	public void assignVehicle();
	public double transportationCost(double costPerKm,double distanceTravel);
	public boolean sendRawMaterialToManufacturer(List rawMaterial);
	public void test();
	
}
