package biscutmanafacture;


public class BiscutManafactureJunction implements ManufactureStore {

	@Override
	public BasicBiscut createBiscut(BiscutModel biscutModel) {
		// TODO Auto-generated method stub

		if(biscutModel.getBiscutName().equals("LemmonPuff")) {
			return new LemonPuff(biscutModel);
		}else if(biscutModel.getBiscutName().equals("ChocoCream")) {
			return new ChocoCream(biscutModel);
		}else {
			return null;
		}
		
	}

	@Override
	public boolean isBiscutProductionInsertered() {
		// TODO Auto-generated method stub
		return BasicBiscut.isDataInsertedDb;
	}


	


}
