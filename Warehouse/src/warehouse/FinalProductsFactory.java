package warehouse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FinalProductsFactory extends AbstractWarehouse {
	Connection conn;

	public FinalProductsFactory(Connection con) {

		try {
			conn = con;
			Statement stmt = conn.createStatement();

			String str = "CREATE TABLE IF NOT EXISTS finalproducts " + "(id INTEGER AUTO_INCREMENT PRIMARY KEY ,"
					+ " name VARCHAR(100), " + "amount REAL, " + "storedDate DATE, " + "manufactureDate DATE, "
					+ "expireDate DATE, " + "storeTemperature REAL, " + "price REAL, " + " ingredients varchar(1000) )";
			stmt.execute(str);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public ArrayList<RawMaterials> getRawMaterials(String name, double amount) {

		return null;
	}

	@Override
	public ArrayList<FinalProducts> getFinalProducts(String name, double amount) {
		try {
			double amountInWarehouse = checkFinalProductsAmount(name);
			if (amount <= amountInWarehouse) {
				ArrayList<FinalProductsImpl> finalProductsWarehouse = new ArrayList<FinalProductsImpl>();
				Statement stmt = conn.createStatement();

				String str1 = "Select * from finalproducts where name =  " + "\"" + name + "\"";
				System.out.println(str1);
				ResultSet resultSet = stmt.executeQuery(str1);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println(simpleDateFormat.format(new Date()));
				while (resultSet.next()) {
					if (resultSet.getDate(6).after(new Date())) {
						FinalProductsImpl finalProductsImpl = new FinalProductsImpl();
						finalProductsImpl.setId(resultSet.getInt(1));
						finalProductsImpl.setName(resultSet.getString(2));
						finalProductsImpl.setAmount(resultSet.getDouble(3));
						finalProductsImpl.setManufactureDate(resultSet.getDate(5));
						finalProductsImpl.setStoredDate(resultSet.getDate(4));
						finalProductsImpl.setExpireDate(resultSet.getDate(6));
						finalProductsImpl.setStoreTemperature(resultSet.getDouble(7));
						finalProductsImpl.setPrice(resultSet.getDouble(8));
						finalProductsImpl.setIngrediants(resultSet.getString(9));
						finalProductsWarehouse.add(finalProductsImpl);
					}
				}

				Date date = new Date();
				double currentAmount = 0;
				double moreReqiuredAmount = amount;

				ArrayList<FinalProducts> sendingArrayList = new ArrayList<FinalProducts>();
				for (FinalProductsImpl finalProductsImpl : finalProductsWarehouse) {
					Date expireDate = finalProductsImpl.getExpireDate();
					if (date.before(expireDate)) {
						if (currentAmount >= amount) {
							break;
						} else {
							if (finalProductsImpl.getAmount() > amount && moreReqiuredAmount == amount) {
								String str = "UPDATE finalproducts set amount = "
										+ (finalProductsImpl.getAmount() - amount) + " where id="
										+ finalProductsImpl.getId();
								FinalProductsImpl finalProductSending = finalProductsImpl;
								finalProductSending.setAmount(amount);
								sendingArrayList.add(finalProductSending);
								System.out.println(str);
								stmt.execute(str);
								currentAmount = finalProductSending.getAmount();
								moreReqiuredAmount -= finalProductSending.getAmount();
								return sendingArrayList;
							} else if (finalProductsImpl.getAmount() == amount && moreReqiuredAmount == amount) {
								String str = "Delete from finalproducts where id=" + finalProductsImpl.getId();
								sendingArrayList.add(finalProductsImpl);
								System.out.println(str);
								stmt.execute(str);
								currentAmount += finalProductsImpl.getAmount();
								moreReqiuredAmount -= finalProductsImpl.getAmount();
								return sendingArrayList;
							} else if (moreReqiuredAmount > 0 && currentAmount < amount) {
								if (moreReqiuredAmount < finalProductsImpl.getAmount()) {
									String str = "UPDATE finalproducts set amount = "
											+ (finalProductsImpl.getAmount() - moreReqiuredAmount) + " where id="
											+ finalProductsImpl.getId();
									FinalProductsImpl finalProductSending = finalProductsImpl;
									finalProductSending.setAmount(moreReqiuredAmount);
									sendingArrayList.add(finalProductSending);
									System.out.println(str);
									stmt.execute(str);
									currentAmount = currentAmount + finalProductSending.getAmount();
									moreReqiuredAmount -= finalProductSending.getAmount();
									return sendingArrayList;
								} else if (moreReqiuredAmount == finalProductsImpl.getAmount()) {
									String str = "Delete from finalproducts where id=" + finalProductsImpl.getId();
									sendingArrayList.add(finalProductsImpl);
									System.out.println(str);
									stmt.execute(str);
									currentAmount = currentAmount + finalProductsImpl.getAmount();
									moreReqiuredAmount -= finalProductsImpl.getAmount();
									return sendingArrayList;
								} else if (moreReqiuredAmount > finalProductsImpl.getAmount()) {
									String str = "Delete from finalproducts where id=" + finalProductsImpl.getId();
									sendingArrayList.add(finalProductsImpl);
									System.out.println(str);
									stmt.execute(str);
									currentAmount = currentAmount + finalProductsImpl.getAmount();
									moreReqiuredAmount -= finalProductsImpl.getAmount();

								}
							}
						}
					}
				}
			} else {
				ArrayList<FinalProducts> SendingList = new ArrayList<FinalProducts>();
				FinalProductsImpl finalProduct = new FinalProductsImpl();
				finalProduct.setAmount(-1000);
				SendingList.add(finalProduct);
				return SendingList;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		ArrayList<FinalProducts> SendingList = new ArrayList<FinalProducts>();
		FinalProductsImpl finalProduct = new FinalProductsImpl();
		finalProduct.setAmount(-2000);
		SendingList.add(finalProduct);
		return SendingList;
	}

	@Override
	public int addRawMaterials(RawMaterialsImpl rawMaterials) {

		return 0;
	}

	@Override
	public int addFinalProducts(FinalProductsImpl finalProducts) {
		try {
			Statement stmt = conn.createStatement();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String storeDate = "\"" + simpleDateFormat.format(new Date()) + "\"";
			String expireDate = "\"" + simpleDateFormat.format(finalProducts.getExpireDate()) + "\"";
			String manufactureDate = "\"" + simpleDateFormat.format(finalProducts.getManufactureDate()) + "\"";
			String str = "INSERT INTO finalproducts ( name , amount , storedDate , manufactureDate , expireDate , storeTemperature, price,ingredients ) VALUES ( "
					+ "\"" + finalProducts.getName() + "\"" + " , " + finalProducts.getAmount() + " , " + storeDate
					+ " , " + manufactureDate + " , " + expireDate + " , " + finalProducts.getStoreTemperature() + " , "
					+ finalProducts.getPrice() + " , " + "\"" + finalProducts.getIngrediants() + "\"" + " ) ";
			System.out.println(str);
			Boolean created = stmt.execute(str);
			System.out.println(created);

			String str2 = "select * from finalproducts where name = " + "\"" + finalProducts.getName() + "\""
					+ " and amount = " + finalProducts.getAmount() + " and storedDate = " + storeDate;
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
	public ArrayList<RawMaterials> checkRawMaterials(String name, boolean expired) {

		return null;
	}

	@Override
	public ArrayList<FinalProducts> checkFinalProducts(String name, boolean expired) {
		try {
			ArrayList<FinalProducts> checkList = new ArrayList<FinalProducts>();
			Statement stmt = conn.createStatement();

			String str = "Select * from finalproducts where name =  " + "\"" + name + "\"";
			System.out.println(str);
			ResultSet resultSet = stmt.executeQuery(str);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(simpleDateFormat.format(new Date()));
			while (resultSet.next()) {
				if (!expired ) {
					if (resultSet.getDate(6).after(new Date())) {
						FinalProductsImpl finalProductsImpl = new FinalProductsImpl();
						finalProductsImpl.setId(resultSet.getInt(1));
						finalProductsImpl.setName(resultSet.getString(2));
						finalProductsImpl.setAmount(resultSet.getDouble(3));
						finalProductsImpl.setManufactureDate(resultSet.getDate(5));
						finalProductsImpl.setStoredDate(resultSet.getDate(4));
						finalProductsImpl.setExpireDate(resultSet.getDate(6));
						finalProductsImpl.setStoreTemperature(resultSet.getDouble(7));
						finalProductsImpl.setPrice(resultSet.getDouble(8));
						finalProductsImpl.setIngrediants(resultSet.getString(9));
						checkList.add(finalProductsImpl);
					}
				}else {
					FinalProductsImpl finalProductsImpl = new FinalProductsImpl();
					finalProductsImpl.setId(resultSet.getInt(1));
					finalProductsImpl.setName(resultSet.getString(2));
					finalProductsImpl.setAmount(resultSet.getDouble(3));
					finalProductsImpl.setManufactureDate(resultSet.getDate(5));
					finalProductsImpl.setStoredDate(resultSet.getDate(4));
					finalProductsImpl.setExpireDate(resultSet.getDate(6));
					finalProductsImpl.setStoreTemperature(resultSet.getDouble(7));
					finalProductsImpl.setPrice(resultSet.getDouble(8));
					finalProductsImpl.setIngrediants(resultSet.getString(9));
					checkList.add(finalProductsImpl);
				}
			}

			return checkList;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	

	@Override
	public double checkRawMaterialsAmount(String name) {

		return 0;
	}

	@Override
	public double checkFinalProductsAmount(String name) {
		try {
			ArrayList<FinalProductsImpl> checkFinalProducts = new ArrayList<FinalProductsImpl>();

			Statement stmt = conn.createStatement();

			String str = "Select * from finalproducts where name =  " + "\"" + name + "\"";
			System.out.println(str);
			ResultSet resultSet = stmt.executeQuery(str);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(simpleDateFormat.format(new Date()));
			while (resultSet.next()) {
				if (resultSet.getDate(6).after(new Date())) {
					FinalProductsImpl finalProductsImpl = new FinalProductsImpl();
					finalProductsImpl.setId(resultSet.getInt(1));
					finalProductsImpl.setName(resultSet.getString(2));
					finalProductsImpl.setAmount(resultSet.getDouble(3));
					finalProductsImpl.setManufactureDate(resultSet.getDate(5));
					finalProductsImpl.setStoredDate(resultSet.getDate(4));
					finalProductsImpl.setExpireDate(resultSet.getDate(6));
					finalProductsImpl.setStoreTemperature(resultSet.getDouble(7));
					finalProductsImpl.setPrice(resultSet.getDouble(8));
					finalProductsImpl.setIngrediants(resultSet.getString(9));
					checkFinalProducts.add(finalProductsImpl);
				}
			}

			double amount = 0;
			for (FinalProductsImpl finalProductsImpl : checkFinalProducts) {
				amount += finalProductsImpl.getAmount();
			}
			return amount;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public ArrayList<RawMaterials> checkExpiredRawMaterials() {

		return null;
	}

	@Override
	public ArrayList<FinalProducts> checkExpiredFinalProducts() {
		try {
			ArrayList<FinalProducts> expireList = new ArrayList<FinalProducts>();
			Statement stmt = conn.createStatement();

			String str = "Select * from finalproducts where expireDate < now()";
			System.out.println(str);
			ResultSet resultSet = stmt.executeQuery(str);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(simpleDateFormat.format(new Date()));
			while (resultSet.next()) {
				if (resultSet.getDate(6).before(new Date())) {
					FinalProductsImpl finalProductsImpl = new FinalProductsImpl();
					finalProductsImpl.setId(resultSet.getInt(1));
					finalProductsImpl.setName(resultSet.getString(2));
					finalProductsImpl.setAmount(resultSet.getDouble(3));
					finalProductsImpl.setManufactureDate(resultSet.getDate(5));
					finalProductsImpl.setStoredDate(resultSet.getDate(4));
					finalProductsImpl.setExpireDate(resultSet.getDate(6));
					finalProductsImpl.setStoreTemperature(resultSet.getDouble(7));
					finalProductsImpl.setPrice(resultSet.getDouble(8));
					finalProductsImpl.setIngrediants(resultSet.getString(9));
					expireList.add(finalProductsImpl);
				}
			}

			return expireList;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public void deleteRawMaterials(int id) {

	}

	@Override
	public void deleteFinalProducts(int id) {
		try {
			Statement stmt = conn.createStatement();

			String str = "delete from finalproducts where id = " + id;
			System.out.println(str);
			stmt.execute(str);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public void updateRawMaterial(RawMaterialsImpl rawMaterials) {

	}

	@Override
	public void updateFinalProduct(FinalProductsImpl finalProducts) {
		try {
			Statement stmt = conn.createStatement();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String storeDate = "\"" + simpleDateFormat.format(finalProducts.getStoredDate()) + "\"";
			String expireDate = "\"" + simpleDateFormat.format(finalProducts.getExpireDate()) + "\"";
			String manufactureDate = "\"" + simpleDateFormat.format(finalProducts.getManufactureDate()) + "\"";

			String str = "update finalproducts set name = " + "\"" + finalProducts.getName() + "\"" + " , amount = "
					+ finalProducts.getAmount() + " , storedDate = " + storeDate + " , manufactureDate = "
					+ manufactureDate + " , expireDate = " + expireDate + " , storeTemperature = "
					+ finalProducts.getStoreTemperature() + " ,price = " + finalProducts.getPrice() + ", ingredients = "
					+ "\"" + finalProducts.getIngrediants() + "\"  " + " where id =  " + finalProducts.getId();
			System.out.println(str);
			stmt.execute(str);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public ArrayList<String> getRawMaterilNames() {

		return null;
	}

	@Override
	public ArrayList<String> getFinalProductNames() {
		ArrayList<String> nameList = new ArrayList<>();

		try {
			String str = "Select distinct name from finalproducts";
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
	public ArrayList<RawMaterials> getRawMatetialDetails(boolean expired) {
		return null;
	}

	@Override
	public ArrayList<FinalProducts> getFinalProductDetails(boolean expired) {
		try {
			ArrayList<FinalProducts> list = new ArrayList<FinalProducts>();
			Statement stmt = conn.createStatement();

			String str = "Select * from finalproducts";
			System.out.println(str);
			ResultSet resultSet = stmt.executeQuery(str);
			while (resultSet.next()) {
				if (expired) {
					FinalProductsImpl finalProductsImpl = new FinalProductsImpl();
					finalProductsImpl.setId(resultSet.getInt(1));
					finalProductsImpl.setName(resultSet.getString(2));
					finalProductsImpl.setAmount(resultSet.getDouble(3));
					finalProductsImpl.setManufactureDate(resultSet.getDate(5));
					finalProductsImpl.setStoredDate(resultSet.getDate(4));
					finalProductsImpl.setExpireDate(resultSet.getDate(6));
					finalProductsImpl.setStoreTemperature(resultSet.getDouble(7));
					finalProductsImpl.setPrice(resultSet.getDouble(8));
					finalProductsImpl.setIngrediants(resultSet.getString(9));
					list.add(finalProductsImpl);
				} else {
					if (resultSet.getDate(6).after(new Date())) {
						FinalProductsImpl finalProductsImpl = new FinalProductsImpl();
						finalProductsImpl.setId(resultSet.getInt(1));
						finalProductsImpl.setName(resultSet.getString(2));
						finalProductsImpl.setAmount(resultSet.getDouble(3));
						finalProductsImpl.setManufactureDate(resultSet.getDate(5));
						finalProductsImpl.setStoredDate(resultSet.getDate(4));
						finalProductsImpl.setExpireDate(resultSet.getDate(6));
						finalProductsImpl.setStoreTemperature(resultSet.getDouble(7));
						finalProductsImpl.setPrice(resultSet.getDouble(8));
						finalProductsImpl.setIngrediants(resultSet.getString(9));
						list.add(finalProductsImpl);
					}
				}
			}
			return list;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
