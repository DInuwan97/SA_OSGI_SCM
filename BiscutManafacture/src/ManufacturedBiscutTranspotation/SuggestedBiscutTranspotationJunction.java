package ManufacturedBiscutTranspotation;

import biscutmanafacture.BiscutModel;

public class SuggestedBiscutTranspotationJunction implements ManufactuedBisctTranspotationStore {

	private TransportationDetailsModel transpotationDetailsModel;
	private StoreBiscutInTransport storeBiscutInTransport;
	
	public SuggestedBiscutTranspotationJunction() {
		// TODO Auto-generated constructor stub
		transpotationDetailsModel = new TransportationDetailsModel();
		storeBiscutInTransport = new StoreBiscutInTransport();
	}

	@Override
	public void setTransportDetails() {
		
		transpotationDetailsModel.setDriverName("Dinuka Shameera");
		transpotationDetailsModel.setCleanerName("Dinuwan Kalubowila");
		transpotationDetailsModel.setVehicleType("Prime Mover");
		transpotationDetailsModel.setVehicleNumber("LM - 2763");
		transpotationDetailsModel.setPackedDate("2020/03/15");
		transpotationDetailsModel.setPackedTime("05:12 AM");
		transpotationDetailsModel.setEsmitedTimeToDestination("02 Hrs : 15 min");
	
		
	}
	@Override
	public void getTransportDetails() {
		System.out.println("Driver Name 				 :   "+transpotationDetailsModel.getDriverName());
		System.out.println("Cleaner Name 				 :   "+transpotationDetailsModel.getCleanerName());
		System.out.println("Vehicle Type 				 :   "+transpotationDetailsModel.getVehicleType());
		System.out.println("Vehicle Number 				 :   "+transpotationDetailsModel.getVehicleNumber());
		System.out.println("Packed Date 				 :   "+transpotationDetailsModel.getPackedDate());
		System.out.println("Packed Time 				 :   "+transpotationDetailsModel.getPackedTime());
		System.out.println("Estimated Time to Destination:   "+transpotationDetailsModel.getEsmitedTimeToDestination());
	}
	
	@Override
	//add selected biscuts to the traspotation
	public BasicSuggestedBiscutsProducts addBiscutToTranpotation(String biscutName,int amount) {
		// TODO Auto-generated method stub
		
		if(biscutName.equals("LemmonPuff")) {
			return new LemmonPuff(storeBiscutInTransport,biscutName,amount);
		}else if(biscutName.equals("ChocoCream")) {
			return new ChocoCream();
		}else {
			return null;
		}

	}
	





}
