package biscutfactorygui;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import SwingGUI.BiscutManufactureDetailsGUI;
import SwingGUI.GUI;
import biscutdb.IDB;
import biscutmanafacture.BiscutModel;
import biscutmanafacture.ManufactureStore;

public class Activator implements BundleActivator {

	
	
	ServiceReference serviceReference1;
	ServiceReference serviceReferenceDb;
	
	public void start(BundleContext context) throws Exception {
		
		System.out.println("Biscut GUI -  Subscriber Start");
		serviceReference1 = context.getServiceReference(ManufactureStore.class.getName());
		ManufactureStore manufactureStore = (ManufactureStore)context.getService(serviceReference1);
		
		serviceReferenceDb = context.getServiceReference(IDB.class.getName());
		IDB db = (IDB)context.getService(serviceReferenceDb);
		db.dbConn();
		
		
		BiscutModel biscutModel = new BiscutModel();
		BiscutManufactureDetailsGUI biscutManufactureDetailsGUI = new BiscutManufactureDetailsGUI(manufactureStore, biscutModel);
		biscutManufactureDetailsGUI.executeMainGUI(manufactureStore, biscutModel);

	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Biscut Factory GUI Stopped!!!!");
		context.ungetService(serviceReference1);
	}

}
