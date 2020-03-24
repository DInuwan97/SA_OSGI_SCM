package salespublisher;

public interface SalesService {
	
	public double calculateSalesIncome();
	public double generateProfite();
	public boolean sendDemandRequest(SalesModel salesModel);
	
}
