package biscutfactorygui;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import DemandSales.Idemand;
import SwingGUI.BiscutManufactureDetailsGUI;
import SwingGUI.DemandRequestGUI;
import SwingGUI.GUI;
import SwingGUI.MenuWindow;
import biscutdb.IDB;
import biscutmanafacture.BiscutModel;
import biscutmanafacture.ManufactureStore;

public class Activator implements BundleActivator {

	
	
	ServiceReference serviceReference1,serviceReference2;
	ServiceReference serviceReferenceDb;
	
	public void start(BundleContext context) throws Exception {
		
		System.out.println("Biscut GUI -  Subscriber Start");
		
		//Databse Reference
		serviceReferenceDb = context.getServiceReference(IDB.class.getName());
		IDB db = (IDB)context.getService(serviceReferenceDb);
		
		//Manufacture Product Reference
		serviceReference1 = context.getServiceReference(ManufactureStore.class.getName());
		ManufactureStore manufactureStore = (ManufactureStore)context.getService(serviceReference1);
		
	
		//demand request reference
		serviceReference2 = context.getServiceReference(Idemand.class.getName());
		Idemand iDemand = (Idemand)context.getService(serviceReference2);
		
		//execute main window
		MenuWindow menuWindow = new MenuWindow();
		menuWindow.executeMenuWindow();
		
		
//		BiscutModel biscutModel = new BiscutModel();
//		BiscutManufactureDetailsGUI biscutManufactureDetailsGUI = new BiscutManufactureDetailsGUI(manufactureStore, biscutModel);
//		biscutManufactureDetailsGUI.executeMainGUI(manufactureStore, biscutModel);

		
		BackEndMessageDetailsModel bsd = new BackEndMessageDetailsModel();
		
		BackEndDetails bc = new BackEndDetails();
		bc.LoadSalesMessages();
		
		
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Biscut Factory GUI Stopped!!!!");
		//DemandRequestGUI.deleteCurrentFile();
		context.ungetService(serviceReference1);
		context.ungetService(serviceReference2);
	}

}
