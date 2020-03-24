package tranportationpublisher;

public class TransportationServiceImpl implements TransportationService{
	private TransportationModel transportationModel = new TransportationModel();
	//private TransportationVehicleModel vehicle = new TransportationVehicleModel();

	@Override
	public boolean checktransportationAvailability(int requiredVehicels) {
		// TODO Auto-generated method stub
		if(transportationModel.getNoOfVehicels() < requiredVehicels) {
			System.out.println("Transportation demand do not satisfy");
			return false;
		}else {
			System.out.println("There is enough Transportation");
			return true;
		}
	}

	@Override
	public String getFinishGoods() {
		// TODO Auto-generated method stub
		System.out.println("100 Finidh goods arrived");
		return null;
	}

	@Override
	public void transportationDetails() {
		// TODO Auto-generated method stub
		
//		vehicle.setDriverId("1");
//		vehicle.setDriverName("Upul");
//		vehicle.setVehicleNoPlate("AB1234");
//		System.out.println("===================Vehicle arrived to the Shop the Details are=========================");
//		System.out.println("Name of the ID : " + vehicle.getDriverId());
//		System.out.println("Name of the Drive : " + vehicle.getDriverName());
//		System.out.println("Vheicle Number Plate : " + vehicle.getVehicleNoPlate());
	}

	@Override
	public void assignVehicle() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public double transportationCost(double costPerKm,double distanceTravel) {
		
		double cost =  distanceTravel * costPerKm;
		return cost;
		
	}

	@Override
	public void test() {
		System.out.println("This is the test method");
		
	}

}
