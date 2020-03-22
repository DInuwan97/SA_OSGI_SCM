package biscutmanafacture;

public class IOBiscutDetails {
	
	public boolean InputBiscutDetails(BiscutModel biscutModel) { 
		
		ManufactureBiscuitDBQueries manufactureBiscuitDBQueries = new ManufactureBiscuitDBQueries();
		return manufactureBiscuitDBQueries.InsertBiscutManufactureDetails(biscutModel);
	}
	
	public void OutPutBiscutDetails(BiscutModel biscutModel) {
		
		System.out.println("Biscut Name      : " +biscutModel.getBiscutName());
		System.out.println("Manufac DATE     : " +biscutModel.getManufactureDate());
		System.out.println("Expire DATE      : " +biscutModel.getExpireDate());
		System.out.println("Ingredients      : " +biscutModel.getIngridents());
		System.out.println("Manufact Amount  : " +biscutModel.getManufactAmount());
		System.out.println("Employees        : " +biscutModel.getNumOfEmployees());
		System.out.println("Machines         : " +biscutModel.getNoOfMachines());
		
	}
}
