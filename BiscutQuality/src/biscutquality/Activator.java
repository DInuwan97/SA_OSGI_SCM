package biscutquality;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	JFrame mainWindow;
	ServiceRegistration qualityService;

	public void start(BundleContext context) throws Exception {
		System.out.println("Quality bundle started"); 
		
		IQuality iquality = new Quality();
		qualityService = context.registerService(IQuality.class.getName(), iquality, null);
		
		new Quality().approveQuality();
		
		
	}

	public void stop(BundleContext context) throws Exception {
		 System.out.println("Quality bundle stopped");
		 qualityService.unregister();
	}
	
	
	

}
