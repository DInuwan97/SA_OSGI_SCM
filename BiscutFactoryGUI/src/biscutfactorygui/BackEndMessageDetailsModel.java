package biscutfactorygui;

import java.util.ArrayList;

public class BackEndMessageDetailsModel {

	
	public static int[] msgId = new int[1000];
	public static int[] saleId = new int[1000];
	public static String[] message = new String[10000];
	public static String[] addedDate = new String[1000];
	public static String[] addedTine = new String[1000];
	public static String[] demandAcceptConfirmation = new String[1000];
	
	public static int[] getMsgId() {
		return msgId;
	}



	public static int[] getSaleId() {
		return saleId;
	}



	public static String[] getMessage() {
		return message;
	}



	public static String[] getAddedDate() {
		return addedDate;
	}



	public static String[] getAddedTine() {
		return addedTine;
	}



	public static String[] getDemandAcceptConfirmation() {
		return demandAcceptConfirmation;
	}



	public ArrayList<BackEndMessageDetailsModel> getMessageDetailsList() {
		return messageDetailsList;
	}



	public ArrayList<BackEndMessageDetailsModel> messageDetailsList;
	
	public BackEndMessageDetailsModel() {
		
	}

	
	
	public BackEndMessageDetailsModel(int[] msgId, int[] saleId, String[] message, String[] addedDate,
			String[] addedTine, String[] demandAcceptConfirmation) {
		super();
		this.msgId = msgId;
		this.saleId = saleId;
		this.message = message;
		this.addedDate = addedDate;
		this.addedTine = addedTine;
		this.demandAcceptConfirmation = demandAcceptConfirmation;
	}



	
	


	
	
	
}
