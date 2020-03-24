package DemandSales;

import java.sql.ResultSet;

public interface IdemandRequstDBQueries {
	public boolean insertDemandReq(SalesDemandMsgModel salesDemandMsgModel);
	public ResultSet getSelectedSalesMessage(int id);
	public boolean deleteSalesMsg(int id);
	
	public ResultSet viewDemandsOnDate(SalesMessageDetailsModel salesDemandMsgModel);
}
