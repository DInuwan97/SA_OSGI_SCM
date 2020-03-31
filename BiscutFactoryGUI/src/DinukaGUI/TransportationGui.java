package DinukaGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import biscutfactorygui.Activator;
import salespublisher.SalesService;
import tranportationpublisher.TransportationModel;
import tranportationpublisher.TransportationVehicle;
import tranportationpublisher.TransportationVehicleModal;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class TransportationGui {

	private JFrame frame;
	private JTextField txtdrivername;
	private JTextField txtdriverid;
	private JTextField txtvehicleid;
	private JTextField txtvehiclenumber;
	private JTextField txtavailable;
	private JComboBox cmbtransportedItems;
	private JComboBox cmbDriver;
	
	//private SalesService salesService;
	/**
	 * Launch the application.
	 */
	public static void excecuteTransportGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransportationGui window = new TransportationGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public TransportationGui() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception{
		frame = new JFrame();
		frame.setBounds(0, 0, 1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Driver ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(44, 263, 147, 49);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblVehicleNumberPlate = new JLabel("Vehicle ID");
		lblVehicleNumberPlate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVehicleNumberPlate.setBounds(44, 372, 183, 49);
		frame.getContentPane().add(lblVehicleNumberPlate);
		
		JLabel label = new JLabel("Vehicle Number Plate");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(44, 471, 183, 49);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Driver Name");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(44, 165, 147, 49);
		frame.getContentPane().add(label_1);
		
		txtdrivername = new JTextField();
		txtdrivername.setBounds(262, 165, 288, 49);
		frame.getContentPane().add(txtdrivername);
		txtdrivername.setColumns(10);
		
		txtdriverid = new JTextField();
		txtdriverid.setColumns(10);
		txtdriverid.setBounds(262, 261, 288, 49);
		frame.getContentPane().add(txtdriverid);
		
		txtvehicleid = new JTextField();
		txtvehicleid.setColumns(10);
		txtvehicleid.setBounds(262, 370, 288, 49);
		frame.getContentPane().add(txtvehicleid);
		
		txtvehiclenumber = new JTextField();
		txtvehiclenumber.setColumns(10);
		txtvehiclenumber.setBounds(262, 469, 288, 49);
		frame.getContentPane().add(txtvehiclenumber);
		
		JButton btnaddnewvehicle = new JButton("ADD");
		

		
		btnaddnewvehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int x = JOptionPane.showConfirmDialog(null,"Do you  want to save this Record?");
				if(x == 0) {
					TransportationVehicleModal tmdeol = new TransportationVehicleModal();
					//tmdeol.set txtdriverid.getText()
					tmdeol.setDriverID(Integer.parseInt(txtdriverid.getText().toString()));
					tmdeol.setDriverName(txtdrivername.getText().toString());
					tmdeol.setVehicleid(Integer.parseInt(txtvehicleid.getText().toString()));
					tmdeol.setVehicelPlate(txtvehiclenumber.getText().toString());
					
					boolean result = (Activator.transportationVehicle).addNewVehicel(tmdeol);
					if(result == true) {
						JOptionPane.showMessageDialog(null,"Success!!!");
						reset(); 
					}else {
						JOptionPane.showMessageDialog(null,"Fail!!!");
					}
				}
				
				
				
				
				
			}
		});
		
		
		btnaddnewvehicle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnaddnewvehicle.setBounds(196, 570, 203, 58);
		frame.getContentPane().add(btnaddnewvehicle);
		
		JLabel lblNewLabel_1 = new JLabel("NEW VEHICLE REGISTRATION");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(54, 41, 524, 49);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnLoadData = new JButton("Remove Driver");
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = JOptionPane.showConfirmDialog(null,"Do you  want to Delete this Record?");
				if(x == 0) {
					boolean r = (Activator.transportationVehicle).removeVehicle(txtvehiclenumber.getText().toString());	
					if(r == true) {
						JOptionPane.showMessageDialog(null,"Success!!!");
						reset(); 
					}else {
						JOptionPane.showMessageDialog(null,"Fail!!!");
					}
				}
				
			}
		});
		btnLoadData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLoadData.setBounds(443, 570, 203, 58);
		frame.getContentPane().add(btnLoadData);
		
		JLabel Availabilty = new JLabel("Drivers Available");
		Availabilty.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Availabilty.setBounds(715, 165, 147, 49);
		frame.getContentPane().add(Availabilty);
		
		txtavailable = new JTextField();
		txtavailable.setColumns(10);
		txtavailable.setBounds(872, 154, 288, 49);
		frame.getContentPane().add(txtavailable);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtavailable.setText(String.valueOf((Activator.transportationVehicle).getVehicleCount()));
				List avaialble = (Activator.transportationVehicle).getAvailableVehicle();
				for(int i = 0;i < avaialble.size(); i++) {
					cmbDriver.addItem(avaialble.get(i).toString());
				}
			}
		});
		btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCheck.setBounds(803, 302, 203, 58);
		frame.getContentPane().add(btnCheck);
		
		JLabel lblCheckVehicleAvailabilty = new JLabel("CHECK VEHICLE AVAILABILTY");
		lblCheckVehicleAvailabilty.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckVehicleAvailabilty.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCheckVehicleAvailabilty.setBounds(746, 41, 389, 49);
		frame.getContentPane().add(lblCheckVehicleAvailabilty);
		
		JLabel lblTransportedItems = new JLabel("Transported Items");
		lblTransportedItems.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTransportedItems.setBounds(777, 471, 147, 49);
		frame.getContentPane().add(lblTransportedItems);
		
		JButton btngetItems = new JButton("Get Transported Items");
		btngetItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbtransportedItems.addItem("Sugar");
				cmbtransportedItems.addItem("Flour");
				cmbtransportedItems.addItem("Vitamin A");
				cmbtransportedItems.addItem("Preservatives");
				cmbtransportedItems.addItem("Salt");
				
			}
		});
		btngetItems.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btngetItems.setBounds(911, 570, 203, 58);
		frame.getContentPane().add(btngetItems);
		
		cmbtransportedItems = new JComboBox();
		cmbtransportedItems.setBounds(963, 469, 269, 49);
		frame.getContentPane().add(cmbtransportedItems);
		
		JLabel lblTransportedItems_1 = new JLabel("Transported Items");
		lblTransportedItems_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransportedItems_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTransportedItems_1.setBounds(734, 396, 524, 49);
		frame.getContentPane().add(lblTransportedItems_1);
		
		cmbDriver = new JComboBox();
		cmbDriver.setBounds(872, 224, 288, 49);
		frame.getContentPane().add(cmbDriver);
		
		JLabel lblRawMaterials = new JLabel("RAW MATERIALS");
		lblRawMaterials.setHorizontalAlignment(SwingConstants.CENTER);
		lblRawMaterials.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRawMaterials.setBounds(1215, 41, 346, 49);
		frame.getContentPane().add(lblRawMaterials);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(1249, 154, 288, 49);
		frame.getContentPane().add(comboBox);
		
		JButton btncheck = new JButton("Check");
//		btncheck.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				SalesGui salesGui = new SalesGui(Activator.salesService);
//				salesGui.executeMain(Activator.salesService);
//				
//			}
//		});
		btncheck.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btncheck.setBounds(1294, 224, 203, 58);
		frame.getContentPane().add(btncheck);
	}
	
	public void reset() {
		txtdrivername.setText("");
		txtdriverid.setText("");
		txtvehicleid.setText("");
		txtvehiclenumber.setText("");
	}
}
