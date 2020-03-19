package ManufacturedBiscutTranspotation;

import java.util.ArrayList;

public class IOBiscutDetailsInTransport {
	
	//private final ArrayList<StoreBiscutInTransport> storedBiscuts;
	
	//public IOBiscutDetailsInTransport() {
		//storedBiscuts = null;
	//}
	
	public void InputTransportedBiscutDetails(StoreBiscutInTransport storeBiscutDetails,String biscutName,int amount) {
	
		storeBiscutDetails.setBiscutName(biscutName);
		storeBiscutDetails.setStoredAmound(amount);
		
		//storedBiscuts.add(storeBiscutDetails);
	}
	

	
	public void OutputTransportedBiscutDetails(StoreBiscutInTransport storeBiscutDetails) {
		
		//for(StoreBiscutInTransport storeBiscutInTransport : storedBiscuts) {
			//System.out.println(storeBiscutInTransport.getBiscutName()+ " : " +storeBiscutInTransport.getStoredAmound());
		//}

		System.out.println(storeBiscutDetails.getBiscutName()+ " : " +storeBiscutDetails.getStoredAmound());
		
		
		
	}
	
}
