package DemandSales;

public class SalesDemandJunction implements Idemand{

	@Override
	public void getSalesMsg(SalesDemandMsgModel salesDemandMsgModel) {
		// TODO Auto-generated method stub

		System.out.println("Message ID : " +salesDemandMsgModel.getMsgId());
		System.out.println("Req Date : " +salesDemandMsgModel.getReqDate());
		System.out.println("Original Sales Request : " +salesDemandMsgModel.getDemandRequest());
		System.out.println("Demand Description : " +salesDemandMsgModel.getDescription());
		
		
	}

}
