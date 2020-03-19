package biscuttranspotation;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import ManufacturedBiscutTranspotation.ManufactuedBisctTranspotationStore;
import ManufacturedBiscutTranspotation.TransportationDetailsModel;
import biscutmanafacture.ManufactureStore;

public class Activator implements BundleActivator {

	ServiceReference serviceReference1;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Biscut Transpotation Subscriber Start");
		
		
		serviceReference1 = context.getServiceReference(ManufactuedBisctTranspotationStore.class.getName());
		
		
		
		
		
		
		/////////////////////////////From Manufature Bundle////////////////////////////////////
		ManufactuedBisctTranspotationStore manufactedBiscutTranspotationStore = (ManufactuedBisctTranspotationStore)context.getService(serviceReference1);
		System.out.println("============================================Transport Vehicle Details=========================================");
		manufactedBiscutTranspotationStore.setTransportDetails();
		manufactedBiscutTranspotationStore.getTransportDetails();
		System.out.println("============================================Transport Packing Details=========================================");
		manufactedBiscutTranspotationStore.addBiscutToTranpotation("LemmonPuff",23);
		/////////////////////////////From Manufature Bundle////////////////////////////////////
		
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Biscut Transport Subscriber - GoodBye");
		context.ungetService(serviceReference1);
	}

}
