package DemandSales;

import java.io.File;

public class SalesDemandMsgModel {
	
	private int salesId;
	private int salesDemandMsgId;
	private int msgId;
	private String demandRequest;
	private String reqDate;
	private File fileName;
	private String description;
	
	
	public File getFileName() {
		return fileName;
	}
	public void setFileName(File fileName) {
		this.fileName = fileName;
	}
	public int getSalesId() {
		return salesId;
	}
	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}
	public int getSalesDemandMsgId() {
		return salesDemandMsgId;
	}
	public void setSalesDemandMsgId(int salesDemandMsgId) {
		this.salesDemandMsgId = salesDemandMsgId;
	}
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public String getDemandRequest() {
		return demandRequest;
	}
	public void setDemandRequest(String demandRequest) {
		this.demandRequest = demandRequest;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	

}
