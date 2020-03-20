package SwingGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import DemandSales.Idemand;
import DemandSales.SalesDemandJunction;
import DemandSales.SalesDemandMsgModel;
import biscutmanafacture.BiscutManafactureJunction;
import biscutmanafacture.BiscutModel;
import biscutmanafacture.ManufactureStore;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class MenuWindow extends JFrame{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void executeMenuWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuWindow window = new MenuWindow();
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
	public MenuWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1182, 689);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnManufact = new JButton("Manufacture");
	
		btnManufact.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnManufact.setBounds(336, 377, 437, 62);
		frame.getContentPane().add(btnManufact);
		
		JButton btnDemand = new JButton("Demand Forecasting");
	
		btnDemand.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnDemand.setBounds(336, 245, 437, 62);
		frame.getContentPane().add(btnDemand);
		
		
		
		
		
		//Add Manufacture Product
		btnManufact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManufactureStore manufactureStore = new BiscutManafactureJunction();
				BiscutModel biscutModel = new BiscutModel();
				BiscutManufactureDetailsGUI biscutManufactureDetailsGUI = new BiscutManufactureDetailsGUI(manufactureStore, biscutModel);
				biscutManufactureDetailsGUI.executeMainGUI(manufactureStore, biscutModel);
				displayManufactureGUI();
				
			}
		});
		
		
		//Add Demand Forecasting
		btnDemand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Idemand iDemand = new SalesDemandJunction();
				SalesDemandMsgModel salesDemandMsgModel = new SalesDemandMsgModel();
				DemandRequestGUI demandRequestGUI = new DemandRequestGUI(iDemand);
				demandRequestGUI.executeDemandGUI(iDemand);
							
			}
		});
	}
	
	public void displayManufactureGUI() {
		new BiscutManufactureDetailsGUI().setVisible(true);
		dispose();
	}

}
