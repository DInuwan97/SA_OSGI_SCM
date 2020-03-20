package biscutsales;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import DemandSales.Idemand;

public class Activator implements BundleActivator {


	ServiceReference serviceReference1;
	
	
	public void start(BundleContext context) throws Exception {
	
		System.out.println("Sales Activator Starts");
		serviceReference1 = context.getServiceReference(Idemand.class.getName());
		Idemand iDemand = (Idemand)context.getService(serviceReference1);
		
		
	}

	public void stop(BundleContext context) throws Exception {
		context.ungetService(serviceReference1);
	}

}
