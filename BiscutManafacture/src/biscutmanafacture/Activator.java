package biscutmanafacture;

import java.sql.Connection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;


import ManufacturedBiscutTranspotation.ManufactuedBisctTranspotationStore;
import ManufacturedBiscutTranspotation.SuggestedBiscutTranspotationJunction;


import biscutdb.IDB;

public class Activator implements BundleActivator {

	ServiceRegistration serviceRegistration1,serviceRegistration2,serviceRegistration3;
	ServiceReference referenceDb,referenceGUI;

	public void start(BundleContext context) throws Exception {
		
		System.out.println("Biscut Manafacture Publisher Start");
		
		///////////////////////////////////////Publish Bundles///////////////////////////////////////////////////////
		ManufactureStore manufactureStore = new BiscutManafactureJunction();	
		ManufactuedBisctTranspotationStore manufactedBiscutTranspotationStore = new SuggestedBiscutTranspotationJunction();
		IBiscuitManufactureDBQuries iBiscuitManufactureDBQuries = new ManufactureBiscuitDBQueries();
		
		serviceRegistration1 = context.registerService(ManufactureStore.class.getName(),manufactureStore,null);
		serviceRegistration2 = context.registerService(ManufactuedBisctTranspotationStore.class.getName(),manufactedBiscutTranspotationStore,null);
		serviceRegistration3 = context.registerService(IBiscuitManufactureDBQuries.class.getName(),iBiscuitManufactureDBQuries,null);
		///////////////////////////////////////Publish Bundles///////////////////////////////////////////////////////
		
		
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Biscut Manafacture Publisher Stop");
		serviceRegistration1.unregister();
		serviceRegistration2.unregister();
		serviceRegistration3.unregister();
		context.ungetService(referenceDb);
	}

}
