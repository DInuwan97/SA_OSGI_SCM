package biscutfactorygui;

import java.sql.ResultSet;

import DemandSales.SalesDemandMsgModel;
import biscutmanafacture.BiscutModel;

public interface IBackEnd {
	public boolean InsertBiscutManufactureDetails(BiscutModel biscutModel);
	public ResultSet LoadSalesMessages();
	public int getSalesMessageCount();
	public ResultSet getSelectedSalesMessage(int id);
	public boolean insertDemandReq(DemandRequestDataModel demandRequestDataModel);
}
