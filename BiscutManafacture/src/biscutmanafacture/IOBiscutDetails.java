package biscutmanafacture;

public class IOBiscutDetails {
	
	public void InputBiscutDetails(BiscutModel biscutModel,String biscutName) { 
		
		biscutModel.setBiscutName(biscutName);
		biscutModel.setExpireDate("2020/12/05");
		biscutModel.setIngridents("Water,Sugar,Milik");
		biscutModel.setManufactAmount(100);
		biscutModel.setManufactureDate("2020/03/18");
		biscutModel.setNoOfMachines(60);
		biscutModel.setNumOfEmployees(20);
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
