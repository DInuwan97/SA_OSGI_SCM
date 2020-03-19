package ManufacturedBiscutTranspotation;

public class LemmonPuff extends BasicSuggestedBiscutsProducts {

	IOBiscutDetailsInTransport ioBiscutDetailsInTransport;
	
	public LemmonPuff(StoreBiscutInTransport storeBiscutDetails,String biscutName,int amount) {

		ioBiscutDetailsInTransport = new IOBiscutDetailsInTransport();
		
		ioBiscutDetailsInTransport.InputTransportedBiscutDetails(storeBiscutDetails,biscutName,amount);
		ioBiscutDetailsInTransport.OutputTransportedBiscutDetails(storeBiscutDetails);
	}
	
	
}
