package logistics;

import java.sql.Connection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import biscutdb.IDB;
import warehouse.Warehouse;

public class Activator implements BundleActivator {

	ServiceReference<?> serviceReference;
	ServiceRegistration<?> serviceRegistration;

	public void start(BundleContext bundleContext) throws Exception {
		
		
		serviceReference = bundleContext.getServiceReference(IDB.class.getName());
		IDB conn = (IDB) bundleContext.getService(serviceReference);
		Connection con = conn.dbConn();
		
		
		serviceReference = bundleContext.getServiceReference(Warehouse.class.getName());
		Warehouse warehouse = (Warehouse) bundleContext.getService(serviceReference);
		Logistics logistics = new LogisticsImpl(con, warehouse.getFactory("rawmaterials"), warehouse.getFactory("finalproducts"));
		serviceRegistration = bundleContext.registerService(Logistics.class.getName(), logistics, null);
		
	}
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stop Logistics Bundle");
		serviceRegistration.unregister();
		bundleContext.ungetService(serviceReference);
	}

}
