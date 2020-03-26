package logistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import warehouse.AbstractWarehouse;
import warehouse.FinalProducts;
import warehouse.RawMaterials;

public class LogisticsImpl implements Logistics {
	private AbstractWarehouse rawMaterialsWarehouse;
	private AbstractWarehouse finalProductsWarehouse;
	private Connection conn;

	public LogisticsImpl(Connection conn, AbstractWarehouse rawMaterialsWarehouse,
			AbstractWarehouse finalProductsWarehouse) {
		this.conn = conn;
		if (rawMaterialsWarehouse != null) {
			this.rawMaterialsWarehouse = rawMaterialsWarehouse;
		}
		if (finalProductsWarehouse != null) {
			this.finalProductsWarehouse = finalProductsWarehouse;
		}

		try {

			Statement stmt = conn.createStatement();

			String str = "CREATE TABLE IF NOT EXISTS logisticsRM (id INTEGER AUTO_INCREMENT PRIMARY KEY ,"
					+ " name VARCHAR(100), amount REAL, batchNo VARCHAR(1000), date DATE)";
			stmt.execute(str);

			String str2 = "CREATE TABLE IF NOT EXISTS logisticsFP (id INTEGER AUTO_INCREMENT PRIMARY KEY ,"
					+ " name VARCHAR(100), amount REAL, batchNo VARCHAR(1000), date DATE)";
			stmt.execute(str2);
			System.out.println(str);
			System.out.println(str2);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public ArrayList<RawMaterials> getRawMaterials(String name, double amount) {
		try {
			ArrayList<RawMaterials> materialList = rawMaterialsWarehouse.getRawMaterials(name, amount);
			
			if (materialList.get(0).getAmount() > 0) {
				ArrayList<String> IDs = new ArrayList<String>();
				for (RawMaterials rawMaterials : materialList) {
					IDs.add(String.valueOf(rawMaterials.getId()));
				}
				
				String bathcID = "\""+String.join(" , ", IDs)+"\"";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String Date = "\"" + simpleDateFormat.format(new Date()) + "\"";
				Statement statement = conn.createStatement();
				String str = "Insert into logisticsRM(name, amount, batchNo , date) values ( \"" + name + "\" , "
						+ amount + " , " + bathcID + " , " + Date + " )";
				System.out.println(str);
				statement.execute(str);

				return materialList;
			} else {
				System.out.println("Not enought Raw Materials");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	@Override
	public ArrayList<FinalProducts> getFinalProducts(String name, double amount) {
		try {
			ArrayList<FinalProducts> productList = finalProductsWarehouse.getFinalProducts(name, amount);
			if (productList.get(0).getAmount() > 0) {
				System.out.println(productList.get(0).displayFinalProduct());
				ArrayList<String> IDs = new ArrayList<String>();
				for (FinalProducts finalProducts : productList) {
					IDs.add(String.valueOf(finalProducts.getId()));
				}
				String bathcID ="\""+ String.join(" , ", IDs)+"\"";

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String Date = "\"" + simpleDateFormat.format(new Date()) + "\"";
				Statement statement = conn.createStatement();
				String str = "Insert into logisticsFP (name, amount, batchNo , date) values ( \"" + name + "\" , "
						+ amount + " , " + bathcID + " , " + Date + " )";
				
				statement.execute(str);

				return productList;
			}else {
				System.out.println("Not Enough ");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public ArrayList<LogisticsDTO> monthReportRawMaterials(int year, int month) {

		ArrayList<LogisticsDTO> list = new ArrayList<LogisticsDTO>();
		try {
			Statement stmt = conn.createStatement();
			String str = "Select * from logisticsRM where EXTRACT(year from date) = " + year
					+ " and EXTRACT(MONTH from date) = " + month;
			ResultSet resultSet = stmt.executeQuery(str);

			
			while (resultSet.next()) {
				LogisticsDTOImpl logisticsDTOImpl = new LogisticsDTOImpl();
				logisticsDTOImpl.setId(resultSet.getInt(1));
				logisticsDTOImpl.setName(resultSet.getString(2));
				logisticsDTOImpl.setAmount(resultSet.getDouble(3));
				logisticsDTOImpl.setBatchNos(resultSet.getString(4));
				logisticsDTOImpl.setDate(resultSet.getDate(5));
				list.add(logisticsDTOImpl);
			}

			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public ArrayList<LogisticsDTO> monthReportFinalProducts(int year, int month) {
		ArrayList<LogisticsDTO> list = new ArrayList<LogisticsDTO>();
		try {
			Statement stmt = conn.createStatement();
			String str = "Select * from logisticsFP where EXTRACT(year from date) = " + year
					+ " and EXTRACT(MONTH from date) = " + month;
			ResultSet resultSet = stmt.executeQuery(str);

			
			while (resultSet.next()) {
				LogisticsDTOImpl logisticsDTOImpl = new LogisticsDTOImpl();
				logisticsDTOImpl.setId(resultSet.getInt(1));
				logisticsDTOImpl.setName(resultSet.getString(2));
				logisticsDTOImpl.setAmount(resultSet.getDouble(3));
				logisticsDTOImpl.setBatchNos(resultSet.getString(4));
				logisticsDTOImpl.setDate(resultSet.getDate(5));
				list.add(logisticsDTOImpl);
			}

			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public ArrayList<LogisticsDTO> reportRawMaterialsForAPeriod(Date start, Date end) {
		ArrayList<LogisticsDTO> list = new ArrayList<LogisticsDTO>();
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String startDate = "\"" + simpleDateFormat.format(start) + "\"";
			String endDate = "\"" + simpleDateFormat.format(end) + "\"";
			Statement stmt = conn.createStatement();
			String str = "Select * from logisticsRM where date >= " + startDate + " and date <= " + endDate;
			ResultSet resultSet = stmt.executeQuery(str);

			
			while (resultSet.next()) {
				LogisticsDTOImpl logisticsDTOImpl = new LogisticsDTOImpl();
				logisticsDTOImpl.setId(resultSet.getInt(1));
				logisticsDTOImpl.setName(resultSet.getString(2));
				logisticsDTOImpl.setAmount(resultSet.getDouble(3));
				logisticsDTOImpl.setBatchNos(resultSet.getString(4));
				logisticsDTOImpl.setDate(resultSet.getDate(5));
				list.add(logisticsDTOImpl);
			}

			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	@Override
	public ArrayList<LogisticsDTO> reportFinalProductsForAPeriod(Date start, Date end) {
		ArrayList<LogisticsDTO> list = new ArrayList<LogisticsDTO>();
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String startDate = "\"" + simpleDateFormat.format(start) + "\"";
			String endDate = "\"" + simpleDateFormat.format(end) + "\"";
			Statement stmt = conn.createStatement();
			String str = "Select * from logisticsFP where date >= " + startDate + " and date <= " + endDate;
			ResultSet resultSet = stmt.executeQuery(str);

			
			while (resultSet.next()) {
				LogisticsDTOImpl logisticsDTOImpl = new LogisticsDTOImpl();
				logisticsDTOImpl.setId(resultSet.getInt(1));
				logisticsDTOImpl.setName(resultSet.getString(2));
				logisticsDTOImpl.setAmount(resultSet.getDouble(3));
				logisticsDTOImpl.setBatchNos(resultSet.getString(4));
				logisticsDTOImpl.setDate(resultSet.getDate(5));
				list.add(logisticsDTOImpl);
			}

			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public ArrayList<LogisticsDTO> reportRawMaterials() {
		ArrayList<LogisticsDTO> list = new ArrayList<LogisticsDTO>();
		try {

			Statement stmt = conn.createStatement();
			String str = "Select * from logisticsRM ";
			ResultSet resultSet = stmt.executeQuery(str);

			
			while (resultSet.next()) {
				LogisticsDTOImpl logisticsDTOImpl = new LogisticsDTOImpl();
				logisticsDTOImpl.setId(resultSet.getInt(1));
				logisticsDTOImpl.setName(resultSet.getString(2));
				logisticsDTOImpl.setAmount(resultSet.getDouble(3));
				logisticsDTOImpl.setBatchNos(resultSet.getString(4));
				logisticsDTOImpl.setDate(resultSet.getDate(5));
				list.add(logisticsDTOImpl);
			}

			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public ArrayList<LogisticsDTO> reportFinalProducts() {
		ArrayList<LogisticsDTO> list = new ArrayList<LogisticsDTO>();
		try {

			Statement stmt = conn.createStatement();
			String str = "Select * from logisticsFP ";
			ResultSet resultSet = stmt.executeQuery(str);

			
			while (resultSet.next()) {
				LogisticsDTOImpl logisticsDTOImpl = new LogisticsDTOImpl();
				logisticsDTOImpl.setId(resultSet.getInt(1));
				logisticsDTOImpl.setName(resultSet.getString(2));
				logisticsDTOImpl.setAmount(resultSet.getDouble(3));
				logisticsDTOImpl.setBatchNos(resultSet.getString(4));
				logisticsDTOImpl.setDate(resultSet.getDate(5));
				list.add(logisticsDTOImpl);
			}

			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
