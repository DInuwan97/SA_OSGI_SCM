package warehouse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RawMaterialsFactory extends AbstractWarehouse {
	Connection conn;

	public RawMaterialsFactory(Connection con) {
		try {

			conn = con;
			Statement stmt = conn.createStatement();

			String str = "CREATE TABLE IF NOT EXISTS rawmaterials " + "(id INTEGER AUTO_INCREMENT PRIMARY KEY ,"
					+ " name VARCHAR(100), " + "amount REAL, " + "storedDate DATE, " + "manufactureDate DATE, "
					+ "expireDate DATE, " + "storeTemperature REAL, " + "price REAL)";
			stmt.execute(str);
			String str2 = "CREATE TABLE IF NOT EXISTS emptiedrawmaterialbatches "
					+ "(id INTEGER AUTO_INCREMENT PRIMARY KEY , batchID INTEGER," + " name VARCHAR(100), " + "amount REAL, "
					+ "storedDate DATE, " + "manufactureDate DATE, " + "expireDate DATE, " + "storeTemperature REAL, "
					+ "price REAL, emptiedDate DATE )";
			System.out.println(str2);
			stmt.execute(str2);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// -1000 = amount is larger than amount in warehouse
	// -2000 = error
	@Override
	public ArrayList<RawMaterials> getRawMaterials(String name, double amount) {

		try {
			double amountInWarehouse = checkRawMaterialsAmount(name);
			if (amount <= amountInWarehouse) {
				ArrayList<RawMaterialsImpl> rawMaterialsInWarehouse = new ArrayList<RawMaterialsImpl>();
				Statement stmt = conn.createStatement();

				String str1 = "Select * from rawmaterials where name =  " + "\"" + name + "\"";
				System.out.println(str1);
				ResultSet resultSet = stmt.executeQuery(str1);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println(simpleDateFormat.format(new Date()));
				while (resultSet.next()) {
					if (resultSet.getDate(6).after(new Date())) {
						RawMaterialsImpl rawMaterials = new RawMaterialsImpl();
						rawMaterials.setId(resultSet.getInt(1));
						rawMaterials.setName(resultSet.getString(2));
						rawMaterials.setAmount(resultSet.getDouble(3));
						rawMaterials.setManufactureDate(resultSet.getDate(5));
						rawMaterials.setStoredDate(resultSet.getDate(4));
						rawMaterials.setExpireDate(resultSet.getDate(6));
						rawMaterials.setStoreTemperature(resultSet.getDouble(7));
						rawMaterials.setPrice(resultSet.getDouble(8));
						rawMaterialsInWarehouse.add(rawMaterials);
					}
				}

				Date date = new Date();
				double currentAmount = 0;
				double moreReqiuredAmount = amount;

				ArrayList<RawMaterials> sendingArrayList = new ArrayList<RawMaterials>();
				for (RawMaterialsImpl rawMaterials : rawMaterialsInWarehouse) {
					Date expireDate = rawMaterials.getExpireDate();
					if (date.before(expireDate)) {
						if (currentAmount >= amount) {
							break;
						} else {
							if (rawMaterials.getAmount() > amount && moreReqiuredAmount == amount) {
								String str = "UPDATE rawmaterials set amount = " + (rawMaterials.getAmount() - amount)
										+ " where id=" + rawMaterials.getId();
								RawMaterialsImpl rawMaterialsSending = rawMaterials;
								rawMaterialsSending.setAmount(amount);
								sendingArrayList.add(rawMaterialsSending);
								System.out.println(str);
								stmt.execute(str);
								currentAmount = rawMaterialsSending.getAmount();
								moreReqiuredAmount -= rawMaterialsSending.getAmount();
								return sendingArrayList;
							} else if (rawMaterials.getAmount() == amount && moreReqiuredAmount == amount) {
								String str = "Delete from rawmaterials where id=" + rawMaterials.getId();
								sendingArrayList.add(rawMaterials);
								System.out.println(str);
								stmt.execute(str);
								String emptiedDate = "\"" + simpleDateFormat.format(new Date()) + "\"";
								String storeDate = "\"" + simpleDateFormat.format(rawMaterials.getStoredDate()) + "\"";
								String expireD = "\"" + simpleDateFormat.format(rawMaterials.getExpireDate()) + "\"";
								String manufactureDate = "\""
										+ simpleDateFormat.format(rawMaterials.getManufactureDate()) + "\"";
								String str2 = "INSERT INTO emptiedrawmaterialbatches (batchID, name , amount , storedDate , manufactureDate , expireDate , storeTemperature, price, emptiedDate ) VALUES ( "
										+rawMaterials.getId()+" , "+ "\"" + rawMaterials.getName() + "\"" + " , "
										+ (rawMaterials.getAmount() - amount) + " , " + storeDate + " , "
										+ manufactureDate + " , " + expireD + " , "
										+ rawMaterials.getStoreTemperature() + " , " + rawMaterials.getPrice() + " , "
										+ emptiedDate + " ) ";
								System.out.println(str2);
								stmt.execute(str2);
								
								currentAmount += rawMaterials.getAmount();
								moreReqiuredAmount -= rawMaterials.getAmount();
								return sendingArrayList;
							} else if (moreReqiuredAmount > 0 && currentAmount < amount) {
								if (moreReqiuredAmount < rawMaterials.getAmount()) {
									String str = "UPDATE rawmaterials set amount = "
											+ (rawMaterials.getAmount() - moreReqiuredAmount) + " where id="
											+ rawMaterials.getId();
									RawMaterialsImpl rawMaterialsSending = rawMaterials;
									rawMaterialsSending.setAmount(moreReqiuredAmount);
									sendingArrayList.add(rawMaterialsSending);
									System.out.println(str);
									stmt.execute(str);
									currentAmount = currentAmount + rawMaterialsSending.getAmount();
									moreReqiuredAmount -= rawMaterialsSending.getAmount();
									return sendingArrayList;
								} else if (moreReqiuredAmount == rawMaterials.getAmount()) {
									String str = "Delete from rawmaterials where id=" + rawMaterials.getId();
									sendingArrayList.add(rawMaterials);
									System.out.println(str);
									stmt.execute(str);
									String emptiedDate = "\"" + simpleDateFormat.format(new Date()) + "\"";
									String storeDate = "\"" + simpleDateFormat.format(rawMaterials.getStoredDate())
											+ "\"";
									String manufactureDate = "\""
											+ simpleDateFormat.format(rawMaterials.getManufactureDate()) + "\"";
									String expireD = "\"" + simpleDateFormat.format(rawMaterials.getExpireDate()) + "\"";
									String str2 = "INSERT INTO emptiedrawmaterialbatches (batchID, name , amount , storedDate , manufactureDate , expireDate , storeTemperature, price, emptiedDate ) VALUES ( "
											+rawMaterials.getId()+" , "+ "\"" + rawMaterials.getName() + "\"" + " , "
											+ (rawMaterials.getAmount() - moreReqiuredAmount) + " , " + storeDate
											+ " , " + manufactureDate + " , " + expireD + " , "
											+ rawMaterials.getStoreTemperature() + " , " + rawMaterials.getPrice()
											+ " , " + emptiedDate + " ) ";
									stmt.execute(str2);
									System.out.println(str2);
									currentAmount = currentAmount + rawMaterials.getAmount();
									moreReqiuredAmount -= rawMaterials.getAmount();
									return sendingArrayList;
								} else if (moreReqiuredAmount > rawMaterials.getAmount()) {
									String str = "Delete from rawmaterials where id=" + rawMaterials.getId();
									sendingArrayList.add(rawMaterials);
									System.out.println(str);
									stmt.execute(str);
									String emptiedDate = "\"" + simpleDateFormat.format(new Date()) + "\"";
									String storeDate = "\"" + simpleDateFormat.format(rawMaterials.getStoredDate())
											+ "\"";
									String manufactureDate = "\""
											+ simpleDateFormat.format(rawMaterials.getManufactureDate()) + "\"";
									String expireD = "\"" + simpleDateFormat.format(rawMaterials.getExpireDate()) + "\"";
									String str2 = "INSERT INTO emptiedrawmaterialbatches (batchID, name , amount , storedDate , manufactureDate , expireDate , storeTemperature, price, emptiedDate ) VALUES ( "
											+rawMaterials.getId()+" , "+ "\"" + rawMaterials.getName() + "\"" + " , " + 0 + " , " + storeDate
											+ " , " + manufactureDate + " , " + expireD + " , "
											+ rawMaterials.getStoreTemperature() + " , " + rawMaterials.getPrice()
											+ " , " + emptiedDate + " ) ";
									stmt.execute(str2);
									System.out.println(str2);
									currentAmount = currentAmount + rawMaterials.getAmount();
									moreReqiuredAmount -= rawMaterials.getAmount();

								}
							}
						}
					}
				}
			} else {
				ArrayList<RawMaterials> SendingList = new ArrayList<RawMaterials>();
				RawMaterialsImpl rawMaterials = new RawMaterialsImpl();
				rawMaterials.setAmount(-1000);
				SendingList.add(rawMaterials);
				return SendingList;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		ArrayList<RawMaterials> SendingList = new ArrayList<RawMaterials>();
		RawMaterialsImpl rawMaterials = new RawMaterialsImpl();
		rawMaterials.setAmount(-2000);
		SendingList.add(rawMaterials);
		return SendingList;
	}

	@Override
	public ArrayList<FinalProducts> getFinalProducts(String name, double amount) {

		return null;
	}

	@Override
	public int addRawMaterials(RawMaterialsImpl rawMaterials) {
		try {
			Statement stmt = conn.createStatement();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String storeDate = "\"" + simpleDateFormat.format(new Date()) + "\"";
			String expireDate = "\"" + simpleDateFormat.format(rawMaterials.getExpireDate()) + "\"";
			String manufactureDate = "\"" + simpleDateFormat.format(rawMaterials.getManufactureDate()) + "\"";
			String str = "INSERT INTO rawmaterials ( name , amount , storedDate , manufactureDate , expireDate , storeTemperature, price ) VALUES ( "
					+ "\"" + rawMaterials.getName() + "\"" + " , " + rawMaterials.getAmount() + " , " + storeDate
					+ " , " + manufactureDate + " , " + expireDate + " , " + rawMaterials.getStoreTemperature() + " , "
					+ rawMaterials.getPrice() + " ) ";
			System.out.println(str);
			Boolean created = stmt.execute(str);
			System.out.println(created);

			String str2 = "select * from rawmaterials where name = " + "\"" + rawMaterials.getName() + "\""
					+ " and amount = " + rawMaterials.getAmount() + " and storedDate = " + storeDate;
			System.out.println(str2);
			ResultSet resultSet = stmt.executeQuery(str2);
			int id = -1;
			while (resultSet.next()) {
				id = resultSet.getInt(1);
			}

			return id;
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;

	}

	@Override
	public int addFinalProducts(FinalProductsImpl finalProducts) {

		return 0;

	}

	@Override
	public ArrayList<RawMaterials> checkRawMaterials(String name, boolean expired) {
		try {
			ArrayList<RawMaterials> checkList = new ArrayList<RawMaterials>();
			Statement stmt = conn.createStatement();

			String str = "Select * from rawmaterials where name like  " + "\"" + name + "\"";
			System.out.println(str);
			ResultSet resultSet = stmt.executeQuery(str);
			while (resultSet.next()) {
				if (!expired) {
					if (resultSet.getDate(6).after(new Date())) {
						RawMaterialsImpl rawMaterials = new RawMaterialsImpl();
						rawMaterials.setId(resultSet.getInt(1));
						rawMaterials.setName(resultSet.getString(2));
						rawMaterials.setAmount(resultSet.getDouble(3));
						rawMaterials.setManufactureDate(resultSet.getDate(5));
						rawMaterials.setStoredDate(resultSet.getDate(4));
						rawMaterials.setExpireDate(resultSet.getDate(6));
						rawMaterials.setStoreTemperature(resultSet.getDouble(7));
						rawMaterials.setPrice(resultSet.getDouble(8));
						checkList.add(rawMaterials);
					}
				} else {
					RawMaterialsImpl rawMaterials = new RawMaterialsImpl();
					rawMaterials.setId(resultSet.getInt(1));
					rawMaterials.setName(resultSet.getString(2));
					rawMaterials.setAmount(resultSet.getDouble(3));
					rawMaterials.setManufactureDate(resultSet.getDate(5));
					rawMaterials.setStoredDate(resultSet.getDate(4));
					rawMaterials.setExpireDate(resultSet.getDate(6));
					rawMaterials.setStoreTemperature(resultSet.getDouble(7));
					rawMaterials.setPrice(resultSet.getDouble(8));
					checkList.add(rawMaterials);
				}

			}

			return checkList;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	@Override
	public ArrayList<FinalProducts> checkFinalProducts(String name, boolean expired) {

		return null;
	}

	@Override
	public ArrayList<RawMaterials> checkExpiredRawMaterials() {

		try {
			ArrayList<RawMaterials> expireList = new ArrayList<RawMaterials>();
			Statement stmt = conn.createStatement();

			String str = "Select * from rawmaterials where expireDate < now()";
			System.out.println(str);
			ResultSet resultSet = stmt.executeQuery(str);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(simpleDateFormat.format(new Date()));
			while (resultSet.next()) {
				if (resultSet.getDate(6).before(new Date())) {
					RawMaterialsImpl rawMaterials = new RawMaterialsImpl();
					rawMaterials.setId(resultSet.getInt(1));
					rawMaterials.setName(resultSet.getString(2));
					rawMaterials.setAmount(resultSet.getDouble(3));
					rawMaterials.setManufactureDate(resultSet.getDate(5));
					rawMaterials.setStoredDate(resultSet.getDate(4));
					rawMaterials.setExpireDate(resultSet.getDate(6));
					rawMaterials.setStoreTemperature(resultSet.getDouble(7));
					rawMaterials.setPrice(resultSet.getDouble(8));
					expireList.add(rawMaterials);
				}
			}

			return expireList;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public ArrayList<FinalProducts> checkExpiredFinalProducts() {

		return null;
	}

	@Override
	public double checkRawMaterialsAmount(String name) {
		try {
			ArrayList<RawMaterialsImpl> checkRawMaterialsList = new ArrayList<RawMaterialsImpl>();

			Statement stmt = conn.createStatement();

			String str = "Select * from rawmaterials where name =  " + "\"" + name + "\"";
			System.out.println(str);
			ResultSet resultSet = stmt.executeQuery(str);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(simpleDateFormat.format(new Date()));
			while (resultSet.next()) {
				if (resultSet.getDate(6).after(new Date())) {
					RawMaterialsImpl rawMaterials = new RawMaterialsImpl();
					rawMaterials.setId(resultSet.getInt(1));
					rawMaterials.setName(resultSet.getString(2));
					rawMaterials.setAmount(resultSet.getDouble(3));
					rawMaterials.setManufactureDate(resultSet.getDate(5));
					rawMaterials.setStoredDate(resultSet.getDate(4));
					rawMaterials.setExpireDate(resultSet.getDate(6));
					rawMaterials.setStoreTemperature(resultSet.getDouble(7));
					rawMaterials.setPrice(resultSet.getDouble(8));
					checkRawMaterialsList.add(rawMaterials);
				}
			}

			double amount = 0;
			for (RawMaterialsImpl rawMaterials : checkRawMaterialsList) {
				amount += rawMaterials.getAmount();
			}
			return amount;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}

	@Override
	public double checkFinalProductsAmount(String name) {

		return 0;
	}

	@Override
	public void deleteRawMaterials(int id) {
		try {
			Statement stmt = conn.createStatement();

			String str = "delete from rawmaterials where id = " + id;
			System.out.println(str);
			stmt.execute(str);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public void deleteFinalProducts(int id) {

	}

	@Override
	public void updateRawMaterial(RawMaterialsImpl rawMaterials) {
		try {
			Statement stmt = conn.createStatement();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String storeDate = "\"" + simpleDateFormat.format(rawMaterials.getStoredDate()) + "\"";
			String expireDate = "\"" + simpleDateFormat.format(rawMaterials.getExpireDate()) + "\"";
			String manufactureDate = "\"" + simpleDateFormat.format(rawMaterials.getManufactureDate()) + "\"";

			String str = "update rawmaterials set name = " + "\"" + rawMaterials.getName() + "\"" + " , amount = "
					+ rawMaterials.getAmount() + " , storedDate = " + storeDate + " , manufactureDate = "
					+ manufactureDate + " , expireDate = " + expireDate + " , storeTemperature = "
					+ rawMaterials.getStoreTemperature() + " ,price = " + rawMaterials.getPrice() + " where id =  "
					+ rawMaterials.getId();
			System.out.println(str);
			stmt.execute(str);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void updateFinalProduct(FinalProductsImpl finalProducts) {

	}

	@Override
	public ArrayList<String> getRawMaterilNames() {
		ArrayList<String> nameList = new ArrayList<>();

		try {
			String str = "Select distinct name from rawmaterials";
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(str);
			while (resultSet.next()) {
				nameList.add(resultSet.getString(1));
			}
			return nameList;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<String> getFinalProductNames() {

		return null;
	}

	@Override
	public ArrayList<RawMaterials> getRawMatetialDetails(boolean expired) {
		try {
			ArrayList<RawMaterials> list = new ArrayList<RawMaterials>();
			Statement stmt = conn.createStatement();

			String str = "Select * from rawmaterials";
			System.out.println(str);
			ResultSet resultSet = stmt.executeQuery(str);
			while (resultSet.next()) {
				if (!expired) {
					if (resultSet.getDate(6).after(new Date())) {
						RawMaterialsImpl rawMaterials = new RawMaterialsImpl();
						rawMaterials.setId(resultSet.getInt(1));
						rawMaterials.setName(resultSet.getString(2));
						rawMaterials.setAmount(resultSet.getDouble(3));
						rawMaterials.setManufactureDate(resultSet.getDate(5));
						rawMaterials.setStoredDate(resultSet.getDate(4));
						rawMaterials.setExpireDate(resultSet.getDate(6));
						rawMaterials.setStoreTemperature(resultSet.getDouble(7));
						rawMaterials.setPrice(resultSet.getDouble(8));
						list.add(rawMaterials);
					}
				} else {
					RawMaterialsImpl rawMaterials = new RawMaterialsImpl();
					rawMaterials.setId(resultSet.getInt(1));
					rawMaterials.setName(resultSet.getString(2));
					rawMaterials.setAmount(resultSet.getDouble(3));
					rawMaterials.setManufactureDate(resultSet.getDate(5));
					rawMaterials.setStoredDate(resultSet.getDate(4));
					rawMaterials.setExpireDate(resultSet.getDate(6));
					rawMaterials.setStoreTemperature(resultSet.getDouble(7));
					rawMaterials.setPrice(resultSet.getDouble(8));
					list.add(rawMaterials);
				}

			}
			return list;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	@Override
	public ArrayList<FinalProducts> getFinalProductDetails(boolean expired) {

		return null;
	}

}
