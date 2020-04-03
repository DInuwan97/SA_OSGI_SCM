package logistics;

import java.util.ArrayList;
import java.util.Date;


import warehouse.FinalProducts;
import warehouse.RawMaterials;

public interface Logistics {
	
	public ArrayList<RawMaterials> getRawMaterials(String name, double amount);
	
	public ArrayList<FinalProducts> getFinalProducts(String name, double amount);
	
	public ArrayList<LogisticsDTO> monthReportRawMaterials(int year, int month);
	
	public ArrayList<LogisticsDTO> monthReportFinalProducts(int year, int month);
	
	public ArrayList<LogisticsDTO> reportRawMaterialsForAPeriod(Date start, Date end);
	
	public ArrayList<LogisticsDTO> reportFinalProductsForAPeriod(Date start, Date end);
	
	public ArrayList<LogisticsDTO> reportRawMaterials();
	
	public ArrayList<LogisticsDTO> reportFinalProducts();
}
