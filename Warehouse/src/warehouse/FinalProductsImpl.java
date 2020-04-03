package warehouse;

import java.util.Date;
public class FinalProductsImpl implements FinalProducts {
	private int id;
	private String name;
	private double amount;
	private Date storedDate;
	private Date manufactureDate;
	private Date expireDate;
	private double storeTemperature;
	private String ingrediants;
	private double price;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getStoredDate() {
		return storedDate;
	}

	public void setStoredDate(Date storedDate) {
		this.storedDate = storedDate;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public double getStoreTemperature() {
		return storeTemperature;
	}

	public void setStoreTemperature(double storeTemperature) {
		this.storeTemperature = storeTemperature;
	}

	public String getIngrediants() {
		return ingrediants;
	}

	public void setIngrediants(String ingrediants) {
		this.ingrediants = ingrediants;
	}

	@Override
	public String displayFinalProduct() {
		String display = "ID : "+id+"\nName : "+name+"\nStroed Date : "+storedDate+"\nManufactured Date : "+manufactureDate+"\nExpire Date : "+expireDate+"\nAmount : "+amount+"\nPrice : "+price+"\nStore Temperature"+storeTemperature+"\nIngredients : " + ingrediants;
		
		
		return display;
	}
	

}
