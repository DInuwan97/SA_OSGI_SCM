package biscutmanafacture;

import BiscutManufactureGUI.ManufactureProductDetailsGUI;

public class BiscutManafactureJunction implements ManufactureStore {

	@Override
	public BasicBiscut createBiscut(String biscutName,BiscutModel biscutModel) {
		// TODO Auto-generated method stub

		if(biscutName.equals("LemmonPuff")) {
			return new LemonPuff(biscutName);
		}else if(biscutName.equals("ChocoCream")) {
			return new ChocoCream(biscutName);
		}else {
			return null;
		}
		
	}


	


}
