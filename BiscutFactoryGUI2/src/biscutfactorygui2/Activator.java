package biscutfactorygui2;

import biscutfinance.IFinanceDB;
import biscutquality.IQualityDB;
import biscutquality.QualityDB;
import finance.Finance;
import finance.IFinance;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import quality.IQuality;
import quality.Quality;

public class Activator implements BundleActivator {
	ServiceRegistration qualityGUIService, financeGUIService;
	ServiceReference qualityDBService, financialDBService;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Biscut GUI 2 - Subscriber Start");
		
		// register services
		IQuality iquality = (IQuality) new Quality();
		qualityGUIService = context.registerService(IQuality.class.getName(), iquality, null);
		
		IFinance iFinance = (IFinance) new Finance();
		financeGUIService = context.registerService(IFinance.class.getName(), iFinance, null);
		
		// get DB services
		qualityDBService = context.getServiceReference(IQualityDB.class.getName());
		IQualityDB qualityDB = (IQualityDB) context.getService(qualityDBService);
		
		financialDBService = context.getServiceReference(IFinanceDB.class.getName());
		IFinanceDB financeDB = (IFinanceDB) context.getService(financialDBService);
		
		//testing
		new Quality().approveQuality();
		// new Quality().setTransportOrStore();
		// new Finance().setPrice();
	}
	

	public void stop(BundleContext context) throws Exception {
		 System.out.println("Quality GUI bundle stopped");
		 qualityGUIService.unregister();
		 financeGUIService.unregister();

	}

}
