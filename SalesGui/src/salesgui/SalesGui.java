package salesgui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.List;
import com.toedter.calendar.JDateChooser;

import salespublisher.SalesModel;
import salespublisher.SalesService;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SalesGui {

	private JFrame frame;
	private JTextField txtmessage;
	private JTextField txtAddTime;
	private JTextField txtdistance;
	private JTextField txtkm;
	private JTextField txttotalCost;
	private JComboBox combo;
	private int salesId = 1;

	/**
	 * Launch the application.
	 */
	public static void main(SalesService salesService) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesGui window = new SalesGui(salesService);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param salesService 
	 */
	public SalesGui(SalesService salesService) {
		initialize(salesService);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param salesService 
	 */
	private void initialize(SalesService salesService) {
		frame = new JFrame();
		frame.setBounds(100, 100, 1300, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SALES PANEL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(154, 26, 703, 77);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblManufacturer = new JLabel("Manufacturer");
		lblManufacturer.setHorizontalAlignment(SwingConstants.CENTER);
		lblManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblManufacturer.setBounds(38, 143, 174, 63);
		frame.getContentPane().add(lblManufacturer);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMessage.setBounds(38, 225, 174, 63);
		frame.getContentPane().add(lblMessage);
		
		JLabel lblAddedDate = new JLabel("Added Date");
		lblAddedDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddedDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddedDate.setBounds(38, 315, 174, 63);
		frame.getContentPane().add(lblAddedDate);
		
		JLabel lblAddedTime = new JLabel("Added Time");
		lblAddedTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddedTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddedTime.setBounds(38, 416, 174, 63);
		frame.getContentPane().add(lblAddedTime);
		
//		JComboBox cmbManufacturer = new JComboBox();
//		cmbManufacturer.addItem("Munchee");
//		cmbManufacturer.addItem("Maliban");
//		cmbManufacturer.addItem("Hawain");
//		cmbManufacturer.addItem("Crisco");
//		cmbManufacturer.addItem("Oreo");
//		cmbManufacturer.setSelectedItem(null);
//		cmbManufacturer.setBounds(415, 436, 230, 43);
//		frame.getContentPane().add(cmbManufacturer);
		
		
		txtmessage = new JTextField();
		txtmessage.setColumns(10);
		txtmessage.setBounds(415, 245, 230, 43);
		frame.getContentPane().add(txtmessage);
		
		txtAddTime = new JTextField();
		txtAddTime.setColumns(10);
		txtAddTime.setBounds(415, 436, 230, 43);
		frame.getContentPane().add(txtAddTime);
		
		JButton btnrequestDemand = new JButton("Request Demand");
		btnrequestDemand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				SalesModel smodel = new SalesModel();
				smodel.setSaleesid(salesId);
				smodel.setMessage(txtmessage.getText().toString());
				smodel.setDate("2019/01/12");
				smodel.setTime(txtAddTime.getText().toString());
				
				int x = JOptionPane.showConfirmDialog(null,"Do you  want to save this Record?");
				if(x == 0) {
					boolean result =salesService.sendDemandRequest(smodel);
					if(result == true) {
						JOptionPane.showMessageDialog(null,"Success!!!");
						reset(); 
					}else {
						JOptionPane.showMessageDialog(null,"Fail!!!");
						reset(); 
					}
				}
			
			
			}
		});
		btnrequestDemand.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnrequestDemand.setBounds(123, 536, 300, 63);
		frame.getContentPane().add(btnrequestDemand);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(415, 335, 230, 43);
		frame.getContentPane().add(dateChooser);
		
		JLabel label = new JLabel("");
		label.setBounds(760, 170, 46, 13);
		frame.getContentPane().add(label);
		
		JLabel lblTransportationCost = new JLabel("Transportation Cost");
		lblTransportationCost.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTransportationCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransportationCost.setBounds(776, 143, 459, 34);
		frame.getContentPane().add(lblTransportationCost);
		
		JLabel lblNewLabel_1 = new JLabel("Distance Travelled");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(795, 234, 148, 49);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblCostPerKm = new JLabel("Cost Per KM");
		lblCostPerKm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCostPerKm.setBounds(797, 329, 135, 49);
		frame.getContentPane().add(lblCostPerKm);
		
		txtdistance = new JTextField();
		txtdistance.setBounds(999, 234, 198, 49);
		frame.getContentPane().add(txtdistance);
		txtdistance.setColumns(10);
		
		txtkm = new JTextField();
		txtkm.setColumns(10);
		txtkm.setBounds(999, 329, 198, 49);
		frame.getContentPane().add(txtkm);
		
		JButton btntransportationCost = new JButton("GET COST");
		btntransportationCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double distance= Double.parseDouble(txtdistance.getText().toString());
				double costperKm = Double.parseDouble(txtkm.getText().toString());
				txttotalCost.setText(String.valueOf(distance * costperKm));
			}
		});
		btntransportationCost.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btntransportationCost.setBounds(891, 544, 213, 44);
		frame.getContentPane().add(btntransportationCost);
		
		JLabel lblTotalCost = new JLabel("Total Cost");
		lblTotalCost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalCost.setBounds(797, 418, 135, 49);
		frame.getContentPane().add(lblTotalCost);
		
		txttotalCost = new JTextField();
		txttotalCost.setColumns(10);
		txttotalCost.setBounds(999, 418, 198, 49);
		frame.getContentPane().add(txttotalCost);
		
		JButton btnNewButton = new JButton("INCOME TAB >");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Income_Gui incomeGui = new Income_Gui();
//				incomeGui.incomeWindow();
//				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(1037, 26, 198, 43);
		frame.getContentPane().add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salesId = comboBox.getSelectedIndex();
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Munchee", "Maliban", "Hawain", "Crisco", "Oreo"}));
		comboBox.setBounds(415, 154, 230, 43);
		frame.getContentPane().add(comboBox);
		

	}
	public void reset() {
		 txtmessage.setText("");
		txtAddTime.setText("");
		 txtdistance.setText("");
		 txtkm.setText("");
		 txttotalCost.setText("");
	}
}
