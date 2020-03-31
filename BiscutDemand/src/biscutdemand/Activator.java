package biscutdemand;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import DemandSales.DemandRequestDBQueries;
import DemandSales.Idemand;
import DemandSales.IdemandRequstDBQueries;
import DemandSales.SalesDemandJunction;
import Quality.DemanQualityApprovalDBQueris;
import Quality.IDemandQualityApproval;
import biscutdb.IDB;
import biscutmanafacture.BiscutModel;
import biscutmanafacture.ManufactureStore;

public class Activator implements BundleActivator {

	ServiceRegistration serviceRegistration1,serviceRegistration2,serviceRegistration3;
	ServiceReference serviceReference1,serviceReference2;
	ServiceReference referenceDb;
	
	
	
	public static IDB idb;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Biscut Demand Subscriber Start");
		
		referenceDb = context.getServiceReference(IDB.class.getName());
		idb = (IDB) context.getService(referenceDb);
		
		//////////////////////////Registerting Service//////////////////////////////////////
		Idemand iDemand = new SalesDemandJunction();
		serviceRegistration1 = context.registerService(Idemand.class.getName(),iDemand, null);
		
		
		IdemandRequstDBQueries idemandRequstDBQueries = new DemandRequestDBQueries();
		serviceRegistration2 = context.registerService(IdemandRequstDBQueries.class.getName(),idemandRequstDBQueries, null);
		
		//for sachin
		IDemandQualityApproval iDemandQualityApproval = new DemanQualityApprovalDBQueris();
		serviceRegistration3 = context.registerService(IDemandQualityApproval.class.getName(), iDemandQualityApproval, null);
		
					
		//////////////////////////Referencing Service//////////////////////////////////////
		serviceReference1 = context.getServiceReference(ManufactureStore.class.getName());
		ManufactureStore manufactureStore = (ManufactureStore)context.getService(serviceReference1);
		

	
	
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Biscut Demand Subscriber - GoodBye");
		
		serviceRegistration1.unregister();
		serviceRegistration2.unregister();
		
		context.ungetService(serviceReference1);
	}

}
