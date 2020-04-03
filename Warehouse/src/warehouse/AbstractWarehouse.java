package warehouse;

import java.util.ArrayList;

public abstract class AbstractWarehouse {

	public abstract ArrayList<RawMaterials> getRawMaterials(String name, double amount);

	public abstract ArrayList<FinalProducts> getFinalProducts(String name, double amount);

	public abstract int addRawMaterials(RawMaterialsImpl rawMaterials);

	public abstract int addFinalProducts(FinalProductsImpl finalProducts);

	public abstract ArrayList<RawMaterials> checkRawMaterials(String name, boolean expired);

	public abstract ArrayList<FinalProducts> checkFinalProducts(String name, boolean expired);

	public abstract double checkRawMaterialsAmount(String name);

	public abstract double checkFinalProductsAmount(String name);

	public abstract ArrayList<RawMaterials> checkExpiredRawMaterials();

	public abstract ArrayList<FinalProducts> checkExpiredFinalProducts();

	public abstract void deleteRawMaterials(int id);

	public abstract void deleteFinalProducts(int id);

	public abstract void updateRawMaterial(RawMaterialsImpl rawMaterials);

	public abstract void updateFinalProduct(FinalProductsImpl finalProducts);
	
	public abstract ArrayList<String> getRawMaterilNames();
	
	public abstract ArrayList<String> getFinalProductNames();
	
	public abstract ArrayList<RawMaterials> getRawMatetialDetails(boolean expired);
	
	public abstract ArrayList<FinalProducts> getFinalProductDetails(boolean expired);

}
