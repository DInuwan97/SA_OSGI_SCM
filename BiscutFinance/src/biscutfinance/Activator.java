package biscutfinance;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;



public class Activator implements BundleActivator {
	ServiceRegistration financeDBService;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Finance db bundle started");
		
		IFinanceDB iFinanceDB = (IFinanceDB) new FinanceDB();
		financeDBService = context.registerService(IFinanceDB.class.getName(), iFinanceDB, null);
		
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Finance db bundle stopped");
		financeDBService.unregister();
		
		
	}

}
