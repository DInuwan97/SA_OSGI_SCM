package tranportationpublisher;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import biscutdb.IDB;

public class TransportationActivator implements BundleActivator {
	ServiceRegistration transportationServiceRegistration;
	ServiceRegistration transportationModelServiceRegistration;
	ServiceReference serviceReferenceDb;
	//Dinuwan manufacturing reference
	//ServiceReference manufacturingReference;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Transportation Service Registration Started");
		TransportationService transportationService = new TransportationServiceImpl();
		transportationServiceRegistration = context.registerService(TransportationService.class.getName(), transportationService,null);
		
		TransportationVehicle transportationModel = new TransportationVehicleImpl();
		transportationModelServiceRegistration = context.registerService(TransportationVehicle.class.getName(), transportationModel,null);
		
		//Database reference
		serviceReferenceDb = context.getServiceReference(IDB.class.getName());
		IDB db = (IDB)context.getService(serviceReferenceDb);
		
		
		//registering transportation vehicle when the bundle start
		System.out.println("Regsitering the  vehicle started");
		TransportationVehicle vehicle = new TransportationVehicleImpl();
//		vehicle.addNewVehicel("BD1236", "Upul");
//		vehicle.addNewVehicel("AB5467", "Kamla");
//		vehicle.addNewVehicel("RT6489", "Nimal");
//		vehicle.addNewVehicel("TT6583", "Thmal");
				
		
		//removing a vehicle
		vehicle.removeVehicle("BD1236");
		vehicle.getVehicles();
		
		
	}

	public void stop(BundleContext context) throws Exception {	
		System.out.println("Transportation Service Publisher Stopped");
		transportationServiceRegistration.unregister();
		transportationModelServiceRegistration.unregister();
		context.ungetService(serviceReferenceDb);
	}

}
