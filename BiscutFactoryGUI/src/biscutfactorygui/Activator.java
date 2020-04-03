package biscutfactorygui;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import DemandSales.Idemand;
import DemandSales.IdemandRequstDBQueries;
import Logistics.LogisticsGui;
import Quality.IDemandQualityApproval;
import SwingGUI.BiscutManufactureDetailsGUI;
import SwingGUI.DemandRequestGUI;
import SwingGUI.GUI;
import SwingGUI.MenuWindow;
import SwingGUI.UserLoginGUI;
import SwingGUI.Welcome;
import Warehouse.AddRawMaterial;
import Warehouse.FinalProducts;
import Warehouse.RawMaterials;
import Warehouse.UpdateFinalProduct;
import Warehouse.UpdateRawMaterial;
import biscutdb.IDB;
import biscutmanafacture.BiscutModel;
import biscutmanafacture.IBiscuitManufactureDBQuries;
import biscutmanafacture.ManufactureStore;
import logistics.Logistics;
import salespublisher.SalesService;
import stakeholdermanagement.IStakeHolder;
import tranportationpublisher.TransportationService;
import tranportationpublisher.TransportationVehicle;
import warehouse.Warehouse;

public class Activator implements BundleActivator {

	
	
	static ServiceReference serviceReference1,serviceReference2,serviceReference3,serviceReference4,serviceReference5,serviceReference6;
	static ServiceReference serviceReferenceDb;
	static ServiceReference serviceReferenceIdemand;
	static ServiceReference serviceReferenceUser,serviceReferenceSMS;
	
	//dinuka
	static ServiceReference transporationServiceReference;
	static ServiceReference transportationVehicleServiceReference;
	static ServiceReference saleServiceReferenceSales;
	//dinuka
	
	
	//pawan
	private ServiceReference<?> warehouseServiceRef;
	private ServiceReference<?> logisticsServiceRef;
	//pawan
	
	
	public static ManufactureStore manufactureStore;
	public static Idemand iDemand;
	public static IdemandRequstDBQueries idemandRequstDBQueries; 
	public static IDemandQualityApproval iDemandQualityApproval;
	public static IBiscuitManufactureDBQuries iBiscuitManufactureDBQuries;
	public static IStakeHolder iStakeHolder;
	
	//Dinuka
	public static TransportationService transportationService;
	public static TransportationVehicle transportationVehicle;
	public static SalesService salesService;
	
	
	//Dinuka
	
	public void start(BundleContext context) throws Exception {
		
		System.out.println("Biscut GUI -  Subscriber Start");
		
		//Databse Reference
		serviceReferenceDb = context.getServiceReference(IDB.class.getName());
		IDB db = (IDB)context.getService(serviceReferenceDb);
		
		//Manufacture Product Reference
		serviceReference1 = context.getServiceReference(ManufactureStore.class.getName());
		ManufactureStore manufactureStore = (ManufactureStore)context.getService(serviceReference1);
		
		
		//Demand Bunddle
		
		//demand request reference
		serviceReference2 = context.getServiceReference(Idemand.class.getName());
		iDemand = (Idemand)context.getService(serviceReference2);
		
		//demand DBQueries reference
		serviceReference3 = context.getServiceReference(IdemandRequstDBQueries.class.getName());
		idemandRequstDBQueries = (IdemandRequstDBQueries)context.getService(serviceReference3);
		
		//deamdn quality for sachin
		serviceReference5 = context.getServiceReference(IDemandQualityApproval.class.getName());
		iDemandQualityApproval = (IDemandQualityApproval) context.getService(serviceReference5);
		
		
		//getting DBQeries from manufature
		serviceReference4 = context.getServiceReference(IBiscuitManufactureDBQuries.class.getName());
		iBiscuitManufactureDBQuries = (IBiscuitManufactureDBQuries)context.getService(serviceReference4);
		//execute main window
		
		
		//uSers
		serviceReferenceUser = context.getServiceReference(IStakeHolder.class.getName());
		iStakeHolder = (IStakeHolder)context.getService(serviceReferenceUser);


		
		//Dinuka
		//System.out.println("Started the transporation service refernece");
		transporationServiceReference = context.getServiceReference(TransportationService.class.getName());
		transportationService = (TransportationService) context.getService(transporationServiceReference);
		
		transportationVehicleServiceReference = context.getServiceReference(TransportationVehicle.class.getName());
		transportationVehicle =  (TransportationVehicle)context.getService(transportationVehicleServiceReference);
		
		saleServiceReferenceSales = context.getServiceReference(SalesService.class.getName());
		salesService = (SalesService) context.getService(saleServiceReferenceSales);
		//Dinuka
		
		
		
		//pawan
		logisticsServiceRef = context.getServiceReference(Logistics.class.getName());
		Logistics logistics = (Logistics) context.getService(logisticsServiceRef);
		LogisticsGui.logistics = logistics;
		
		warehouseServiceRef = context.getServiceReference(Warehouse.class.getName());
		Warehouse warehouse = (Warehouse)context.getService(warehouseServiceRef);
		
		AddRawMaterial.warh = warehouse;
		RawMaterials.warh = warehouse;
		FinalProducts.warh = warehouse;
		UpdateFinalProduct.warh = warehouse;
		UpdateRawMaterial.warh = warehouse;
		//pawan

		
		
	
		Welcome welcomeWindow = new Welcome();
		welcomeWindow.executeWelcomeWindow();


		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Biscut Factory GUI Stopped!!!!");
		//DemandRequestGUI.deleteCurrentFile();
		context.ungetService(serviceReference1);
		context.ungetService(serviceReference2);
		context.ungetService(serviceReference3);
		context.ungetService(serviceReference4);
		context.ungetService(serviceReferenceSMS);
		//dinuwan
		
		
		
		//dinuka
		System.out.println("TransportationGUIActivator Stopped");
		context.ungetService(transporationServiceReference);
		context.ungetService(transportationVehicleServiceReference);
		
		
		
	}

}
