package biscutfactorygui;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import DemandSales.Idemand;
import DemandSales.IdemandRequstDBQueries;
import Quality.IDemandQualityApproval;
import SwingGUI.BiscutManufactureDetailsGUI;
import SwingGUI.DemandRequestGUI;
import SwingGUI.GUI;
import SwingGUI.MenuWindow;
import SwingGUI.UserLoginGUI;
import SwingGUI.Welcome;
import biscutdb.IDB;
import biscutmanafacture.BiscutModel;
import biscutmanafacture.IBiscuitManufactureDBQuries;
import biscutmanafacture.ManufactureStore;
import stakeholdermanagement.IStakeHolder;

public class Activator implements BundleActivator {

	
	
	static ServiceReference serviceReference1,serviceReference2,serviceReference3,serviceReference4,serviceReference5,serviceReference6;
	static ServiceReference serviceReferenceDb;
	static ServiceReference serviceReferenceIdemand;
	static ServiceReference serviceReferenceUser,serviceReferenceSMS;
	
	public static ManufactureStore manufactureStore;
	public static Idemand iDemand;
	public static IdemandRequstDBQueries idemandRequstDBQueries; 
	public static IDemandQualityApproval iDemandQualityApproval;
	public static IBiscuitManufactureDBQuries iBiscuitManufactureDBQuries;
	public static IStakeHolder iStakeHolder;
	
	public void start(BundleContext context) throws Exception {
		
		System.out.println("Biscut GUI -  Subscriber Start");
		
		//Databse Reference
		serviceReferenceDb = context.getServiceReference(IDB.class.getName());
		IDB db = (IDB)context.getService(serviceReferenceDb);
		
		//Manufacture Product Reference
		serviceReference1 = context.getServiceReference(ManufactureStore.class.getName());
		ManufactureStore manufactureStore = (ManufactureStore)context.getService(serviceReference1);
		
		
		//Demand Bunddle
		
		//demand request reference
		serviceReference2 = context.getServiceReference(Idemand.class.getName());
		iDemand = (Idemand)context.getService(serviceReference2);
		
		//demand DBQueries reference
		serviceReference3 = context.getServiceReference(IdemandRequstDBQueries.class.getName());
		idemandRequstDBQueries = (IdemandRequstDBQueries)context.getService(serviceReference3);
		
		//deamdn quality for sachin
		serviceReference5 = context.getServiceReference(IDemandQualityApproval.class.getName());
		iDemandQualityApproval = (IDemandQualityApproval) context.getService(serviceReference5);
		
		
		//getting DBQeries from manufature
		serviceReference4 = context.getServiceReference(IBiscuitManufactureDBQuries.class.getName());
		iBiscuitManufactureDBQuries = (IBiscuitManufactureDBQuries)context.getService(serviceReference4);
		//execute main window
		
		
		//uSers
		serviceReferenceUser = context.getServiceReference(IStakeHolder.class.getName());
		iStakeHolder = (IStakeHolder)context.getService(serviceReferenceUser);

		
//		serviceReferenceSMS = context.getServiceReference(ISms.class.getName());
//		isms = (ISms) context.getService(serviceReferenceSMS);
		
		
		//UserLoginGUI.executeLogin();
	
		Welcome welcomeWindow = new Welcome();
		welcomeWindow.executeWelcomeWindow();


		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Biscut Factory GUI Stopped!!!!");
		//DemandRequestGUI.deleteCurrentFile();
		context.ungetService(serviceReference1);
		context.ungetService(serviceReference2);
		context.ungetService(serviceReference3);
		context.ungetService(serviceReference4);
		context.ungetService(serviceReferenceSMS);
	}

}
