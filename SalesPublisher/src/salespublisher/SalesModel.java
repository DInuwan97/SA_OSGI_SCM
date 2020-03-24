package salespublisher;

public class SalesModel {
	private int totalSellingProductsBought;
	private int itemsSold;
	private double salesIncome;
	private double profit;
	private int saleesid;
	private String message,date,time;
	
	public int getSaleesid() {
		return saleesid;
	}
	public void setSaleesid(int saleesid) {
		this.saleesid = saleesid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getTotalSellingProductsBought() {
		return totalSellingProductsBought;
	}
	public void setTotalSellingProductsBought(int totalSellingProductsBought) {
		this.totalSellingProductsBought = totalSellingProductsBought;
	}
	public int getItemsSold() {
		return itemsSold;
	}
	public void setItemsSold(int itemsSold) {
		this.itemsSold = itemsSold;
	}
	public double getSalesIncome() {
		return salesIncome;
	}
	public void setSalesIncome(double salesIncome) {
		this.salesIncome = salesIncome;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	
}
