package tranportationpublisher;

public class TransportationModel {
	
	
	private double transportationCost;
	private int noOfVehicels;
	private boolean checkVehicleAvailability;
	
	
	
	public TransportationModel() {
		super();
	}

	public TransportationModel(int noOfVehicels) {
		super();
		this.noOfVehicels = noOfVehicels;
	}

	
	public double getTransportationCost() {
		return transportationCost;
	}
	
	public void setTransportationCost(double transportationCost) {
		this.transportationCost = transportationCost;
	}
	
	public int getNoOfVehicels() {
		return noOfVehicels;
	}
	
	public void setNoOfVehicels(int noOfVehicels) {
		this.noOfVehicels = noOfVehicels;
	}
	
	public boolean isCheckVehicleAvailability() {
		return checkVehicleAvailability;
	}
	
	public void setCheckVehicleAvailability(boolean checkVehicleAvailability) {
		this.checkVehicleAvailability = checkVehicleAvailability;
	}
	
	
	
}
