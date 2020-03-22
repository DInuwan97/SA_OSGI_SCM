package biscutmanafacture;

public class LemonPuff extends BasicBiscut {
	
	
	public LemonPuff(BiscutModel biscutModel) {
	
		System.out.println("Selected Category : " +biscutModel.getBiscutName());
		
		IOBiscutDetails ioBiscutDetails = new IOBiscutDetails();	
		isDataInsertedDb = (boolean) ioBiscutDetails.InputBiscutDetails(biscutModel);
	
	}

}
