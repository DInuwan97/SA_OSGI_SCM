package DemandSales;

public class SalesDemandJunction implements Idemand{

	@Override
	public void getSalesMsg(int msgId,String reqDate,String demandReq,String description) {
		// TODO Auto-generated method stub
		SalesDemandMsgModel salesDemandMsgModel = new SalesDemandMsgModel();
		salesDemandMsgModel.setMsgId(msgId);
		salesDemandMsgModel.setReqDate(reqDate);
		salesDemandMsgModel.setDemandRequest(demandReq);
		salesDemandMsgModel.setDescription(description);
		
		
		System.out.println("Message ID : " +salesDemandMsgModel.getMsgId());
		System.out.println("Req Date : " +salesDemandMsgModel.getReqDate());
		System.out.println("Original Sales Request : " +salesDemandMsgModel.getDemandRequest());
		System.out.println("Demand Description : " +salesDemandMsgModel.getDescription());
		
		
	}

}
