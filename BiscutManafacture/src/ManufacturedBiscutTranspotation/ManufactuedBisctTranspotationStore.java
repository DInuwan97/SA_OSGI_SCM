package ManufacturedBiscutTranspotation;

public interface ManufactuedBisctTranspotationStore {
	public BasicSuggestedBiscutsProducts addBiscutToTranpotation(String biscutName,int amount);
	public void setTransportDetails();
	public void getTransportDetails();
}
