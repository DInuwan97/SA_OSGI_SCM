package biscutdemand;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import BiscutManufactureGUI.ManufactureProductDetailsGUI;
import biscutmanafacture.BiscutModel;
import biscutmanafacture.ManufactureStore;

public class Activator implements BundleActivator {

	ServiceReference serviceReference1;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Biscut Demand Subscriber Start");
		serviceReference1 = context.getServiceReference(ManufactureStore.class.getName());
		
		ManufactureStore manufactureStore = (ManufactureStore)context.getService(serviceReference1);
	
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Biscut Demand Subscriber - GoodBye");
		context.ungetService(serviceReference1);
	}

}
