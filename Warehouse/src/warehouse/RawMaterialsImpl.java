package warehouse;

import java.util.Date;

public class RawMaterialsImpl implements RawMaterials {

	private int id;
	private String name;
	private double amount;
	private Date storedDate;
	private Date manufactureDate;
	private Date expireDate;
	private double storeTemperature;
	private double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStoredDate() {
		return storedDate;
	}

	public void setStoredDate(Date storedDate) {
		this.storedDate = storedDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getStoreTemperature() {
		return storeTemperature;
	}

	public void setStoreTemperature(double storeTemperature) {
		this.storeTemperature = storeTemperature;
	}

	@Override
	public String displayRawMaterial() {
		String display = "ID : "+id+"\nName : "+name+"\nStored Date : "+storedDate+"\nManufactured Date : "+manufactureDate+"\nExpire Date : "+expireDate+"\nAmount : "+amount+"\nStore Temperature : "+storeTemperature+"\nPrice : "+price;
		return display;
	}

}
