package logistics;

import java.util.Date;

public class LogisticsDTOImpl implements LogisticsDTO {

	private int id;
	private String name;
	private double amount;
	private String batchNos;
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getBatchNos() {
		return batchNos;
	}

	public void setBatchNos(String batchNos) {
		this.batchNos = batchNos;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String display() {
		String str = "ID :"+id +"\nName : "+name+"\nAmount : "+amount+"\n"+"Batch No(s) : "+batchNos +"\nDate : "+date;
		return str;
	}

}
