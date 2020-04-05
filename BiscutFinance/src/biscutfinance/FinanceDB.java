package biscutfinance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import biscutquality.ManufactureModal;

public class FinanceDB implements IFinanceDB {
	private Connection conn = null;

	public Connection dbConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/biscuitfactory?useSSL=false",
					"root", "password");
			if (conn != null)
				System.out.println("Successfully DB Connected!!!");
			else
				System.out.println("Connection error");
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Mismatching the Connection String");
			e.printStackTrace();
		} finally {
			return conn;
		}
	}

	public ArrayList<ManufactureModal> getManufactures() {
		ArrayList<ManufactureModal> manufactures = new ArrayList<ManufactureModal>();
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM manufacturedetails ";

		try {
			con = dbConn();
			stat = (Statement) con.createStatement();
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				ManufactureModal mfac = new ManufactureModal();
				mfac.setManufactureId(rs.getInt(1));
				mfac.setManufactureDate(rs.getString(2));
				mfac.setExpireDate(rs.getString(3));
				mfac.setBiscutName(rs.getString(4));
				mfac.setMaterials(rs.getString(5));
				mfac.setNoOfMachines(rs.getInt(6));
				mfac.setNoOfEmployees(rs.getInt(7));
				mfac.setManufactureAmount(rs.getInt(8));
				mfac.setDemandReqId(rs.getInt(9));
				manufactures.add(mfac);
				// System.out.println("a");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return manufactures;
	}

	public int setPrice(int manuID, double price) {
		System.out.println(manuID + " " + price);

		// check alredy in product_price table
		boolean stored = false;
		int result = 0;
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM product_price ";

		try {
			con = dbConn();
			stat = (Statement) con.createStatement();
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				if (rs.getInt(1) == manuID) {
					stored = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (stored) {
			// update
			System.out.println("update");
			String sql1 = "UPDATE product_price SET price='" + price + "' WHERE manufactureID='"+ manuID + "' ";
			try {
				stat = (Statement) con.createStatement();
				result = stat.executeUpdate(sql1);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			// insert
			System.out.println("insert");
			String sql1 = "INSERT INTO product_price (manufactureID, price) VALUES('" + manuID + "', '"+ price + "')";
			try {
				stat = (Statement) con.createStatement();
				result = stat.executeUpdate(sql1);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return result;
	}

}
