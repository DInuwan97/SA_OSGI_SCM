package biscutfinance;

import java.util.ArrayList;

import biscutquality.ManufactureModal;

public interface IFinanceDB {
	public ArrayList<ManufactureModal> getManufactures();
	public int setPrice(int row, double price);
}
