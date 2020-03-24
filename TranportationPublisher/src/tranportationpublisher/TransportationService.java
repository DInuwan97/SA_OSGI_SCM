package tranportationpublisher;

public interface TransportationService {

	public boolean checktransportationAvailability(int requiredVehicels);
	public String getFinishGoods();
	public void transportationDetails();
	public void assignVehicle();
	public double transportationCost(double costPerKm,double distanceTravel);
	public void test();
	
}
