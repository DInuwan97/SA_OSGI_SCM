package stakeholdermanagement;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	ServiceRegistration serviceRegistration1;
	

	public void start(BundleContext context) throws Exception {
		
		System.out.println("Stakeholder Publisher Started");
		
		IStakeHolder iStakeHolder = new StakeHolderDBQueries();
		serviceRegistration1 = context.registerService(IStakeHolder.class.getName(),iStakeHolder,null);
		
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stakeholder Stopped - GoodBye");
		serviceRegistration1.unregister();
	}

}
