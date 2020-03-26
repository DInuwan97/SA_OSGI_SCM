package SwingGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import DemandSales.Idemand;
import DemandSales.SalesDemandJunction;
import DemandSales.SalesDemandMsgModel;
import Logistics.LogisticsGui;
import Warehouse.WarehouseManager;
import biscutmanafacture.BiscutManafactureJunction;
import biscutmanafacture.BiscutModel;
import biscutmanafacture.ManufactureStore;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class MenuWindow extends JFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void executeMenuWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuWindow window = new MenuWindow("hello");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuWindow(String msg) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1366, 768);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnManufact = new JButton("Manufacture");

		btnManufact.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnManufact.setBounds(555, 238, 437, 62);
		frame.getContentPane().add(btnManufact);

		JButton btnDemand = new JButton("Demand Forecasting");

		btnDemand.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnDemand.setBounds(56, 238, 437, 62);
		frame.getContentPane().add(btnDemand);

		JButton btnWearhousing = new JButton("Wearhousing");
		btnWearhousing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WarehouseManager warehouse = new WarehouseManager();

				frame.setVisible(false);
				warehouse.main();
			}
		});
		btnWearhousing.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnWearhousing.setBounds(1046, 238, 437, 62);
		frame.getContentPane().add(btnWearhousing);

		JButton btnSalesForecasting = new JButton("Sales Forecasting");
		btnSalesForecasting.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnSalesForecasting.setBounds(56, 383, 437, 62);
		frame.getContentPane().add(btnSalesForecasting);

		JButton btnTransportManagement = new JButton("Transport Management");
		btnTransportManagement.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnTransportManagement.setBounds(555, 383, 437, 62);
		frame.getContentPane().add(btnTransportManagement);

		JButton btnLogisticManagement = new JButton("Logistics Management");
		btnLogisticManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogisticsGui logisticsGui = new LogisticsGui();
				frame.setVisible(false);
				logisticsGui.main();
			}
		});
		btnLogisticManagement.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnLogisticManagement.setBounds(1046, 383, 437, 62);
		frame.getContentPane().add(btnLogisticManagement);

		JButton btnCustomerSatisfactions = new JButton("Customer Satisfactions");
		btnCustomerSatisfactions.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnCustomerSatisfactions.setBounds(1046, 524, 437, 62);
		frame.getContentPane().add(btnCustomerSatisfactions);

		JButton btnQualityAssurance = new JButton("Quality Assurance");
		btnQualityAssurance.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnQualityAssurance.setBounds(555, 524, 437, 62);
		frame.getContentPane().add(btnQualityAssurance);

		JButton btnFinaceManagement = new JButton("Finace Management");
		btnFinaceManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFinaceManagement.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnFinaceManagement.setBounds(56, 524, 437, 62);
		frame.getContentPane().add(btnFinaceManagement);

		// Add Manufacture Product
		btnManufact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManufactureStore manufactureStore = new BiscutManafactureJunction();
				BiscutManufactureDetailsGUI.executeMainGUI(manufactureStore);
				frame.setVisible(false);
				// displayManufactureGUI();

			}
		});

		// Add Demand Forecasting
		btnDemand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Idemand iDemand = new SalesDemandJunction();
				DemandRequestGUI.executeDemandGUI(iDemand);

			}
		});
	}

//	public void displayManufactureGUI() {
//		new BiscutManufactureDetailsGUI().setVisible(true);
//		frame.setVisible(false);
//	}

}
