package biscutquality;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	ServiceRegistration qualityDBService;

	public void start(BundleContext context) throws Exception {
		System.out.println("Quality bundle started"); 
		
		IQualityDB iQualityDB = (IQualityDB) new QualityDB();
		qualityDBService = context.registerService(IQualityDB.class.getName(), iQualityDB, null);
		
		
		
		
	}
	
	

	public void stop(BundleContext context) throws Exception {
		 System.out.println("Quality bundle stopped");
		 qualityDBService.unregister();
	}
	
	
	

}
