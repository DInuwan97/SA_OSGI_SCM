package biscutmanafacture;

public class ChocoCream extends BasicBiscut{
	
	public ChocoCream(BiscutModel biscutModel) {
		
		System.out.println("Selected Category : " +biscutModel.getBiscutName());
		IOBiscutDetails ioBiscutDetails = new IOBiscutDetails();	
		isDataInsertedDb = (boolean) ioBiscutDetails.InputBiscutDetails(biscutModel);
	}

}
