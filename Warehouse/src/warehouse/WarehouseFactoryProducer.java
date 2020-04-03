package warehouse;

import java.sql.Connection;

public class WarehouseFactoryProducer implements Warehouse{
	
	Connection conn;
	public WarehouseFactoryProducer(Connection conn){
		this.conn = conn;
	}
	
	

	public  AbstractWarehouse getFactory(String name) {
		if (name.equalsIgnoreCase("rawmaterials")) {
			System.out.println("raw");
			return new RawMaterialsFactory(conn);
		} else if (name.equalsIgnoreCase("finalproducts")) {
			System.out.println("final");
			return new FinalProductsFactory(conn);
		} else {
			System.out.println("d");
			return null;
		}
	}


}
