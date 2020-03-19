package biscutmanafacture;

public class BiscutModel {
	
	private String biscutName;
	private String manufactureDate;
	private String expireDate;
	private int manufactAmount;
	private int numOfEmployees;
	private int noOfMachines;
	private String ingridents;
	
	public String getBiscutName() {
		return biscutName;
	}
	public void setBiscutName(String biscutName) {
		this.biscutName = biscutName;
	}
	public String getManufactureDate() {
		return manufactureDate;
	}
	public void setManufactureDate(String manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
	public int getManufactAmount() {
		return manufactAmount;
	}
	public void setManufactAmount(int manufactAmount) {
		this.manufactAmount = manufactAmount;
	}
	
	public int getNumOfEmployees() {
		return numOfEmployees;
	}
	public void setNumOfEmployees(int numOfEmployees) {
		this.numOfEmployees = numOfEmployees;
	}
	
	public int getNoOfMachines() {
		return noOfMachines;
	}
	
	public void setNoOfMachines(int noOfMachines) {
		this.noOfMachines = noOfMachines;
	}
	public String getIngridents() {
		return ingridents;
	}
	public void setIngridents(String ingridents) {
		this.ingridents = ingridents;
	}
	
	
	
	
}
