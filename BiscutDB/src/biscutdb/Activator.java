package biscutdb;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


public class Activator implements BundleActivator {

	ServiceRegistration<?> serviceRegistration1;
	
	public void start(BundleContext context) throws Exception {
		
		System.out.println("Biscut DB Publisher Start");
		IDB db = new DB();
		
		serviceRegistration1 = context.registerService(IDB.class.getName(),db,null);
		
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop the DB");
		serviceRegistration1.unregister();
	}

}
