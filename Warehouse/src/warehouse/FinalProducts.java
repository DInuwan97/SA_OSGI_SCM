package warehouse;

import java.util.Date;

public interface FinalProducts {

	public int getId();
	
	public String getName();

	public double getPrice();

	public double getAmount();

	public Date getStoredDate();

	public Date getManufactureDate();

	public Date getExpireDate();

	public double getStoreTemperature();

	public String getIngrediants();
	
	public String displayFinalProduct();

}
