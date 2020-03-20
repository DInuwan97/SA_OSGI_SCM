package DemandSales;

public class SalesMessageDetailsModel {

	
	private int msgId;
	private int saleId;
	private String message;
	private String addedDate;
	private String addedTine;
	private String demandAcceptConfirmation;
	
	
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
