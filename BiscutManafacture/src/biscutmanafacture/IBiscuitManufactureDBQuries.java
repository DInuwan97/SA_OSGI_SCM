package biscutmanafacture;

import java.sql.ResultSet;

public interface IBiscuitManufactureDBQuries {
	public boolean InsertBiscutManufactureDetails(BiscutModel biscutModel);
	public ResultSet getAllManfautureProducts();
}
