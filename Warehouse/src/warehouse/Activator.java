package warehouse;



import java.sql.Connection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import biscutdb.IDB;

public class Activator implements BundleActivator {

	private static BundleContext context;
	
	

	static BundleContext getContext() {
		return context;
	}

	ServiceReference<?> serviceReference;
	
	ServiceRegistration<?> serviceRegistration;

	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("Start Warehouse Bundle");
		Activator.context = bundleContext;

		serviceReference = bundleContext.getServiceReference(IDB.class.getName());
		IDB conn = (IDB) bundleContext.getService(serviceReference);
		Connection con = conn.dbConn();

		
		Warehouse warehouse = new WarehouseFactoryProducer(con);
		serviceRegistration = bundleContext.registerService(Warehouse.class.getName(), warehouse, null);
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		
		System.out.println("Stop Warehouse Bundle");
		bundleContext.ungetService(serviceReference);
		serviceRegistration.unregister();
	}

}
