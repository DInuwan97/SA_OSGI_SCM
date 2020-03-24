package DemandSales;

public class SalesMessageDetailsModel {

	
	private int msgId;
	private int saleId;
	private String message;
	private String addedDate;
	private String addedTine;
	private String demandAcceptConfirmation;
	
	private String date_1,date_2;
	
	
	public String getDate_1() {
		return date_1;
	}
	public void setDate_1(String date_1) {
		this.date_1 = date_1;
	}
	public String getDate_2() {
		return date_2;
	}
	public void setDate_2(String date_2) {
		this.date_2 = date_2;
	}
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}
	public String getAddedTine() {
		return addedTine;
	}
	public void setAddedTine(String addedTine) {
		this.addedTine = addedTine;
	}
	public String getDemandAcceptConfirmation() {
		return demandAcceptConfirmation;
	}
	public void setDemandAcceptConfirmation(String demandAcceptConfirmation) {
		this.demandAcceptConfirmation = demandAcceptConfirmation;
	}
	
	
}
