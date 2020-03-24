package salespublisher;

import biscutdb.IDB;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import tranportationpublisher.TransportationService;
import tranportationpublisher.TransportationVehicleModel;

public class SalesActivator implements BundleActivator {

	ServiceRegistration publishSalesRegister;
	ServiceReference transporationServiceReference;
	ServiceReference serviceReferenceDb;
	//ServiceReference transporationModelReference;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Sales Publisher started");
		SalesService salesService = new SalesServiceImpl();
		publishSalesRegister = context.registerService(SalesService.class.getName(), salesService, null);
	
		//requesting for sales reference
		System.out.println("Started the transporation service refernece");
		transporationServiceReference = context.getServiceReference(TransportationService.class.getName());
		TransportationService transportationService = (TransportationService) context.getService(transporationServiceReference);
		
		//Database reference
		serviceReferenceDb = context.getServiceReference(IDB.class.getName());
		IDB db = (IDB)context.getService(serviceReferenceDb);
		
		//getting reference to the transportation model
		//transporationModelReference = context.getServiceReference(TransportationVehicleModel.class.getName());
		//TransportationVehicleModel transportationVehicleModel =  (TransportationVehicleModel) context.getService(transporationModelReference);
		
		transportationService.getFinishGoods();

		//getting the transporatio cost
		transportationService.transportationCost(100.0,50);
		
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Sales Service Stopped");
		publishSalesRegister.unregister();
		context.ungetService(transporationServiceReference);
		context.ungetService(serviceReferenceDb);
	}

}
