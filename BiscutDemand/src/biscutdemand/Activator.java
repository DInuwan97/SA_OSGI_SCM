package biscutdemand;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;


import DemandSales.Idemand;
import DemandSales.SalesDemandJunction;
import biscutmanafacture.BiscutModel;
import biscutmanafacture.ManufactureStore;

public class Activator implements BundleActivator {

	ServiceRegistration serviceRegistration1;
	ServiceReference serviceReference1,serviceReference2;
	ServiceReference referenceDb;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Biscut Demand Subscriber Start");
		
		//////////////////////////Registerting Service//////////////////////////////////////
		Idemand iDemand = new SalesDemandJunction();
		serviceRegistration1 = context.registerService(Idemand.class.getName(),iDemand, null);
		
		
		//////////////////////////Referencing Service//////////////////////////////////////
		serviceReference1 = context.getServiceReference(ManufactureStore.class.getName());
		ManufactureStore manufactureStore = (ManufactureStore)context.getService(serviceReference1);
	
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Biscut Demand Subscriber - GoodBye");
		
		serviceRegistration1.unregister();
		context.ungetService(serviceReference1);
	}

}
