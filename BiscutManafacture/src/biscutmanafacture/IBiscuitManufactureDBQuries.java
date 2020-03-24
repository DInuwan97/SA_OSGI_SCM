package biscutmanafacture;

import java.sql.ResultSet;

public interface IBiscuitManufactureDBQuries {
	public boolean InsertBiscutManufactureDetails(BiscutModel biscutModel);
	public ResultSet getAllManfautureProducts();
	public boolean updateManufactureProductDetails(BiscutModel biscutModel);
	public ResultSet searchManufaturedProduct(int id);
}
