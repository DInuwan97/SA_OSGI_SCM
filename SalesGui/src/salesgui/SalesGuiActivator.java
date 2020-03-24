package salesgui;

import org.osgi.framework.BundleActivator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import salespublisher.SalesService;
import tranportationpublisher.TransportationService;

public class SalesGuiActivator implements BundleActivator {
	
	ServiceReference saleServiceReference;
	ServiceReference transportReference;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Sales Gui Started");
		saleServiceReference = context.getServiceReference(SalesService.class.getName());
		SalesService salesService = (SalesService) context.getService(saleServiceReference);
		
		//transportReference = context.getServiceReference(TransportationService.class.getName());
		//TransportationService transportService = (TransportationService) context.getService(transportReference);
		
		
		
		
		SalesGui salegui = new SalesGui(salesService);
		salegui.main(salesService);
		
		
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Sales GUI Stopped");
		context.ungetService(saleServiceReference);
	}

}
