package transportationgui;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import tranportationpublisher.TransportationModel;
import tranportationpublisher.TransportationService;
import tranportationpublisher.TransportationVehicle;
import tranportationpublisher.TransportationVehicleImpl;

public class TransportationGUIActivator implements BundleActivator {
	
	ServiceReference transporationServiceReference;
	ServiceReference transportationVehicleServiceReference;

	public void start(BundleContext context) {
		//requesting for sales reference
		System.out.println("Started the transporation service refernece");
		transporationServiceReference = context.getServiceReference(TransportationService.class.getName());
		TransportationService transportationService = (TransportationService) context.getService(transporationServiceReference);
		
		transportationVehicleServiceReference = context.getServiceReference(TransportationVehicle.class.getName());
		TransportationVehicle transportationVehicle =  (TransportationVehicle)context.getService(transportationVehicleServiceReference);
		
		
			
		
		//TransportationGui t = new TransportationGui();
		
		//transportationVehicle.addNewVehicel(plateNumber, driver)
		
		try {
			TransportationGui tgui = new TransportationGui(transportationVehicle);
			tgui.excecuteMain(transportationVehicle);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("TransportationGUIActivator Stopped");
		context.ungetService(transporationServiceReference);
		context.ungetService(transportationVehicleServiceReference);
	}

}
